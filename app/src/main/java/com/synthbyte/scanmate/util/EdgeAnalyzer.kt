package com.synthbyte.scanmate.util

import androidx.camera.core.ImageProxy
import androidx.compose.ui.geometry.Offset
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * ScanMate document edge analyzer v2.
 *
 * Legal/safety note:
 * - Original Kotlin implementation written for ScanMate.
 * - No copied GitHub scanner code.
 * - No OpenCV/native dependency required.
 *
 * Design goals:
 * - Keep the public API compatible with the existing CameraScreen integration.
 * - Prefer stable detections over jumpy aggressive detections.
 * - Handle normal paper, low-light paper, shadows, and high-contrast receipts.
 * - Fail safely so the manual crop/default scanner frame remains available.
 */
object EdgeAnalyzer {

    data class Result(val corners: List<Offset>, val confidence: Float)

    private const val SAMPLE_WIDTH = 352
    private const val SAMPLE_HEIGHT = 264
    private const val MIN_CONFIDENCE = 0.31f
    private const val RETURN_PREVIOUS_CONFIDENCE = 0.27f
    private const val STRONG_LOCK_CONFIDENCE = 0.72f
    private const val BASE_SMOOTHING_ALPHA = 0.38f

    private var smoothedCorners: List<Offset>? = null
    private var smoothedConfidence = 0f
    private var missedFrames = 0
    private var lastRotation = Int.MIN_VALUE

    fun detect(image: ImageProxy): Result? {
        val rotation = ((image.imageInfo.rotationDegrees % 360) + 360) % 360
        if (rotation != lastRotation) {
            resetSmoothing()
            lastRotation = rotation
        }

        val raw = runCatching { detectRaw(image, rotation) }.getOrNull()
        if (raw == null) {
            missedFrames += 1
            if (missedFrames >= 7) resetSmoothing()
            val previous = smoothedCorners ?: return null
            val decayed = (smoothedConfidence * 0.82f).coerceIn(0f, 1f)
            return if (decayed >= RETURN_PREVIOUS_CONFIDENCE) Result(previous, decayed) else null
        }

        missedFrames = 0
        val previous = smoothedCorners
        val nextCorners = if (previous != null && previous.size == 4 && raw.corners.size == 4) {
            val maxJump = previous.zip(raw.corners).maxOf { (old, new) ->
                max(abs(old.x - new.x), abs(old.y - new.y))
            }
            val alpha = when {
                raw.confidence >= STRONG_LOCK_CONFIDENCE && maxJump < 0.08f -> 0.28f
                maxJump > 0.24f -> 0.76f
                maxJump > 0.14f -> 0.58f
                else -> BASE_SMOOTHING_ALPHA
            }
            previous.zip(raw.corners).map { (old, new) ->
                Offset(
                    x = (old.x * (1f - alpha) + new.x * alpha).coerceIn(0f, 1f),
                    y = (old.y * (1f - alpha) + new.y * alpha).coerceIn(0f, 1f)
                )
            }
        } else {
            raw.corners
        }

        smoothedCorners = nextCorners
        smoothedConfidence = (smoothedConfidence * 0.40f + raw.confidence * 0.60f).coerceIn(0f, 1f)
        return Result(nextCorners, smoothedConfidence)
    }

    private fun detectRaw(image: ImageProxy, rotation: Int): Result? {
        val luma = sampleLuma(image, SAMPLE_WIDTH, SAMPLE_HEIGHT) ?: return null
        val width = SAMPLE_WIDTH
        val height = SAMPLE_HEIGHT
        val sorted = luma.copyOf().also { it.sort() }

        val p05 = sorted.percentile(0.05f)
        val p12 = sorted.percentile(0.12f)
        val p35 = sorted.percentile(0.35f)
        val p55 = sorted.percentile(0.55f)
        val p72 = sorted.percentile(0.72f)
        val p88 = sorted.percentile(0.88f)
        val p95 = sorted.percentile(0.95f)
        val contrast = (p95 - p05).coerceAtLeast(1)
        if (contrast < 10) return null

        val edgeThreshold = max(18, (contrast * 0.18f).toInt()).coerceIn(18, 82)
        val brightThreshold = max(p72, p55 + max(8, contrast / 9)).coerceIn(58, 242)
        val darkInkThreshold = min(p35, p12 + max(8, contrast / 10)).coerceIn(12, 210)
        val likelyWhiteBackground = p88 > 178 && contrast < 82

        val candidateMask = BooleanArray(width * height)
        val edgeMap = IntArray(width * height)
        val rowProjection = IntArray(height)
        val colProjection = IntArray(width)
        var candidateCount = 0
        var edgeCount = 0
        var innerBrightCount = 0

        for (y in 1 until height - 1) {
            val row = y * width
            for (x in 1 until width - 1) {
                val index = row + x
                val center = luma[index]
                val gx = abs(luma[index - 1] - luma[index + 1])
                val gy = abs(luma[index - width] - luma[index + width])
                val diag = abs(luma[index - width - 1] - luma[index + width + 1]) / 2 +
                    abs(luma[index - width + 1] - luma[index + width - 1]) / 2
                val gradient = gx + gy + diag
                edgeMap[index] = gradient

                val brightPaper = center >= brightThreshold
                val shadowedPaper = center >= p55 + 4 && gradient >= edgeThreshold
                val darkContentOnBrightScene = likelyWhiteBackground && center <= darkInkThreshold && gradient >= edgeThreshold / 2
                val strongEdge = gradient >= edgeThreshold
                val candidate = brightPaper || shadowedPaper || darkContentOnBrightScene || strongEdge

                if (candidate) {
                    candidateMask[index] = true
                    candidateCount += 1
                    if (brightPaper || shadowedPaper) innerBrightCount += 1
                    val weight = when {
                        brightPaper -> 5
                        shadowedPaper -> 4
                        strongEdge -> 3
                        else -> 1
                    }
                    rowProjection[y] += weight
                    colProjection[x] += weight
                }
                if (strongEdge) edgeCount += 1
            }
        }

        val totalPixels = width * height
        if (candidateCount < totalPixels * 0.025f || edgeCount < totalPixels * 0.006f) return null

        val verticalRange = findProjectionRange(rowProjection, minLength = (height * 0.14f).toInt(), relaxed = false)
            ?: findProjectionRange(rowProjection, minLength = (height * 0.10f).toInt(), relaxed = true)
            ?: return null
        val horizontalRange = findProjectionRange(colProjection, minLength = (width * 0.14f).toInt(), relaxed = false)
            ?: findProjectionRange(colProjection, minLength = (width * 0.10f).toInt(), relaxed = true)
            ?: return null

        val paddingX = max(4, (width * 0.016f).toInt())
        val paddingY = max(4, (height * 0.016f).toInt())
        val left = (horizontalRange.first - paddingX).coerceIn(0, width - 2)
        val right = (horizontalRange.second + paddingX).coerceIn(left + 1, width - 1)
        val top = (verticalRange.first - paddingY).coerceIn(0, height - 2)
        val bottom = (verticalRange.second + paddingY).coerceIn(top + 1, height - 1)

        val quad = estimateQuadrilateral(
            mask = candidateMask,
            edgeMap = edgeMap,
            edgeThreshold = edgeThreshold,
            width = width,
            height = height,
            left = left,
            top = top,
            right = right,
            bottom = bottom
        ) ?: listOf(
            Offset(left / width.toFloat(), top / height.toFloat()),
            Offset(right / width.toFloat(), top / height.toFloat()),
            Offset(right / width.toFloat(), bottom / height.toFloat()),
            Offset(left / width.toFloat(), bottom / height.toFloat())
        )

        if (!isStableQuad(quad)) return null

        val boxWidth = (right - left).toFloat() / width.toFloat()
        val boxHeight = (bottom - top).toFloat() / height.toFloat()
        val boxArea = boxWidth * boxHeight
        if (boxArea !in 0.055f..0.955f) return null

        val aspect = boxWidth / boxHeight.coerceAtLeast(0.001f)
        val portraitA4 = 1f / 1.4142f
        val landscapeA4 = 1.4142f
        val aspectError = min(abs(aspect - portraitA4), abs(aspect - landscapeA4))
        val aspectScore = (1f - aspectError / 1.05f).coerceIn(0f, 1f)
        val areaScore = when {
            boxArea < 0.12f -> (boxArea / 0.12f) * 0.65f
            boxArea <= 0.72f -> 0.75f + (boxArea / 0.72f) * 0.20f
            else -> 0.94f - ((boxArea - 0.72f) / 0.24f).coerceIn(0f, 1f) * 0.18f
        }.coerceIn(0f, 1f)
        val centerX = (left + right) / 2f / width.toFloat()
        val centerY = (top + bottom) / 2f / height.toFloat()
        val centerScore = (1f - (abs(centerX - 0.5f) + abs(centerY - 0.5f)) / 0.72f).coerceIn(0f, 1f)
        val edgeScore = (edgeCount.toFloat() / totalPixels.toFloat() / 0.10f).coerceIn(0f, 1f)
        val fillScore = (innerBrightCount.toFloat() / totalPixels.toFloat() / boxArea.coerceAtLeast(0.06f)).coerceIn(0f, 1f)
        val geometryScore = quadGeometryScore(quad)
        val borderPenalty = listOf(
            left / width.toFloat(),
            top / height.toFloat(),
            1f - right / width.toFloat(),
            1f - bottom / height.toFloat()
        ).minOrNull()?.let { if (it < 0.006f) 0.10f else 0f } ?: 0f

        val confidence = (
            areaScore * 0.22f +
                aspectScore * 0.14f +
                centerScore * 0.12f +
                edgeScore * 0.20f +
                fillScore * 0.18f +
                geometryScore * 0.14f -
                borderPenalty
            ).coerceIn(0f, 1f)

        if (confidence < MIN_CONFIDENCE) return null
        return Result(quad.rotateNormalized(rotation), confidence)
    }

    private fun sampleLuma(image: ImageProxy, targetWidth: Int, targetHeight: Int): IntArray? {
        val plane = image.planes.firstOrNull() ?: return null
        val buffer = plane.buffer
        val rowStride = plane.rowStride
        val pixelStride = plane.pixelStride.coerceAtLeast(1)
        val sourceWidth = image.width.coerceAtLeast(1)
        val sourceHeight = image.height.coerceAtLeast(1)
        val output = IntArray(targetWidth * targetHeight)

        for (y in 0 until targetHeight) {
            val sourceY = ((y + 0.5f) * sourceHeight / targetHeight).toInt().coerceIn(0, sourceHeight - 1)
            for (x in 0 until targetWidth) {
                val sourceX = ((x + 0.5f) * sourceWidth / targetWidth).toInt().coerceIn(0, sourceWidth - 1)
                val index = sourceY * rowStride + sourceX * pixelStride
                output[y * targetWidth + x] = if (index in 0 until buffer.limit()) buffer.get(index).toInt() and 0xFF else 0
            }
        }
        return output
    }

    private fun findProjectionRange(values: IntArray, minLength: Int, relaxed: Boolean): Pair<Int, Int>? {
        if (values.isEmpty()) return null
        val radius = max(2, values.size / 160).coerceAtMost(if (relaxed) 10 else 7)
        val smoothed = IntArray(values.size)
        for (i in values.indices) {
            var sum = 0
            var count = 0
            for (j in i - radius..i + radius) {
                if (j in values.indices) {
                    sum += values[j]
                    count += 1
                }
            }
            smoothed[i] = if (count == 0) values[i] else sum / count
        }

        val peak = smoothed.maxOrNull() ?: return null
        if (peak <= 0) return null
        val sorted = smoothed.copyOf().also { it.sort() }
        val noise = sorted.percentile(if (relaxed) 0.50f else 0.58f)
        val thresholdRatio = if (relaxed) 0.105f else 0.145f
        val threshold = max(noise + 1, (peak * thresholdRatio).toInt()).coerceAtLeast(2)
        val maxGap = max(5, values.size / if (relaxed) 62 else 86).coerceAtMost(if (relaxed) 18 else 12)

        var start = -1
        var gap = 0
        var bestStart = -1
        var bestEnd = -1
        var bestScore = Int.MIN_VALUE

        fun commit(endExclusive: Int) {
            if (start < 0) return
            val end = (endExclusive - gap - 1).coerceAtLeast(start)
            val length = end - start + 1
            if (length >= minLength) {
                val score = (start..end).sumOf { smoothed[it] } + length * if (relaxed) 5 else 8
                if (score > bestScore) {
                    bestScore = score
                    bestStart = start
                    bestEnd = end
                }
            }
            start = -1
            gap = 0
        }

        for (i in smoothed.indices) {
            if (smoothed[i] >= threshold) {
                if (start < 0) start = i
                gap = 0
            } else if (start >= 0) {
                gap += 1
                if (gap > maxGap) commit(i + 1)
            }
        }
        commit(smoothed.size)
        return if (bestStart >= 0 && bestEnd > bestStart) bestStart to bestEnd else null
    }

    private fun estimateQuadrilateral(
        mask: BooleanArray,
        edgeMap: IntArray,
        edgeThreshold: Int,
        width: Int,
        height: Int,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ): List<Offset>? {
        val scanHeight = (bottom - top + 1).coerceAtLeast(1)
        val bandHeight = max(10, (scanHeight * 0.24f).toInt())
        val topLefts = mutableListOf<Int>()
        val topRights = mutableListOf<Int>()
        val bottomLefts = mutableListOf<Int>()
        val bottomRights = mutableListOf<Int>()

        fun collectRow(y: Int, lefts: MutableList<Int>, rights: MutableList<Int>) {
            if (y !in 1 until height - 1) return
            val row = y * width
            var rowLeft = -1
            var rowRight = -1
            for (x in left..right) {
                val index = row + x
                if (index in mask.indices && (mask[index] || edgeMap[index] >= edgeThreshold)) {
                    rowLeft = x
                    break
                }
            }
            for (x in right downTo left) {
                val index = row + x
                if (index in mask.indices && (mask[index] || edgeMap[index] >= edgeThreshold)) {
                    rowRight = x
                    break
                }
            }
            if (rowLeft >= 0 && rowRight > rowLeft && rowRight - rowLeft > (right - left) * 0.38f) {
                lefts += rowLeft
                rights += rowRight
            }
        }

        for (y in top until min(bottom, top + bandHeight)) collectRow(y, topLefts, topRights)
        for (y in max(top, bottom - bandHeight)..bottom) collectRow(y, bottomLefts, bottomRights)

        if (topLefts.size < 3 || topRights.size < 3 || bottomLefts.size < 3 || bottomRights.size < 3) return null

        val tlX = topLefts.robustMedian().coerceIn(0, width - 1)
        val trX = topRights.robustMedian().coerceIn(0, width - 1)
        val blX = bottomLefts.robustMedian().coerceIn(0, width - 1)
        val brX = bottomRights.robustMedian().coerceIn(0, width - 1)

        val quad = listOf(
            Offset(tlX / width.toFloat(), top / height.toFloat()),
            Offset(trX / width.toFloat(), top / height.toFloat()),
            Offset(brX / width.toFloat(), bottom / height.toFloat()),
            Offset(blX / width.toFloat(), bottom / height.toFloat())
        ).map { Offset(it.x.coerceIn(0f, 1f), it.y.coerceIn(0f, 1f)) }

        return quad.takeIf(::isStableQuad)
    }

    private fun isStableQuad(corners: List<Offset>): Boolean {
        if (corners.size != 4) return false
        val area = polygonArea(corners)
        if (area !in 0.045f..0.965f) return false
        val top = distance(corners[0], corners[1])
        val right = distance(corners[1], corners[2])
        val bottom = distance(corners[3], corners[2])
        val left = distance(corners[0], corners[3])
        val minSide = min(min(top, bottom), min(left, right))
        val maxSide = max(max(top, bottom), max(left, right))
        if (minSide < 0.10f || maxSide / minSide.coerceAtLeast(0.001f) > 6.0f) return false
        return corners[0].x < corners[1].x &&
            corners[3].x < corners[2].x &&
            corners[0].y < corners[3].y &&
            corners[1].y < corners[2].y
    }

    private fun quadGeometryScore(corners: List<Offset>): Float {
        if (corners.size != 4) return 0f
        val top = distance(corners[0], corners[1])
        val right = distance(corners[1], corners[2])
        val bottom = distance(corners[3], corners[2])
        val left = distance(corners[0], corners[3])
        val horizontalBalance = min(top, bottom) / max(top, bottom).coerceAtLeast(0.001f)
        val verticalBalance = min(left, right) / max(left, right).coerceAtLeast(0.001f)
        val areaScore = (polygonArea(corners) / 0.55f).coerceIn(0f, 1f)
        return (horizontalBalance * 0.35f + verticalBalance * 0.35f + areaScore * 0.30f).coerceIn(0f, 1f)
    }

    private fun IntArray.percentile(percent: Float): Int {
        if (isEmpty()) return 0
        val index = (lastIndex * percent).toInt().coerceIn(0, lastIndex)
        return this[index]
    }

    private fun List<Int>.robustMedian(): Int {
        if (isEmpty()) return 0
        val sorted = sorted()
        val from = (sorted.size * 0.12f).toInt().coerceIn(0, sorted.lastIndex)
        val to = (sorted.size * 0.88f).toInt().coerceIn(from, sorted.lastIndex)
        val trimmed = sorted.subList(from, to + 1)
        return trimmed[trimmed.size / 2]
    }

    private fun List<Offset>.rotateNormalized(rotationDegrees: Int): List<Offset> {
        return when (((rotationDegrees % 360) + 360) % 360) {
            90 -> map { Offset(1f - it.y, it.x) }
            180 -> map { Offset(1f - it.x, 1f - it.y) }
            270 -> map { Offset(it.y, 1f - it.x) }
            else -> this
        }
    }

    private fun polygonArea(points: List<Offset>): Float {
        if (points.size < 3) return 0f
        return abs(points.indices.sumOf { i ->
            val a = points[i]
            val b = points[(i + 1) % points.size]
            (a.x * b.y - b.x * a.y).toDouble()
        }.toFloat()) / 2f
    }

    private fun distance(a: Offset, b: Offset): Float {
        val dx = a.x - b.x
        val dy = a.y - b.y
        return sqrt(dx * dx + dy * dy)
    }

    private fun resetSmoothing() {
        smoothedCorners = null
        smoothedConfidence = 0f
        missedFrames = 0
    }
}
