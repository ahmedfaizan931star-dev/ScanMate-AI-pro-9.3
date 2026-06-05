package com.synthbyte.scanmate.util

import androidx.camera.core.ImageProxy
import androidx.compose.ui.geometry.Offset
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Offline document edge detector for the CameraX preview analyzer.
 *
 * This implementation intentionally avoids OpenCV/native dependencies so release APK/AAB builds stay
 * small and stable. It samples the Y plane, builds adaptive brightness + edge projections, estimates
 * skew-aware corners, then smooths consecutive detections to avoid jumpy scanner overlays.
 */
object EdgeAnalyzer {

    data class Result(val corners: List<Offset>, val confidence: Float)

    private const val SAMPLE_WIDTH = 320
    private const val SAMPLE_HEIGHT = 240
    private const val MIN_CONFIDENCE = 0.42f
    private const val SMOOTHING_ALPHA = 0.36f

    private var smoothedCorners: List<Offset>? = null
    private var smoothedConfidence = 0f
    private var missedFrames = 0
    private var lastRotation = Int.MIN_VALUE

    fun detect(image: ImageProxy): Result? {
        val rotation = ((image.imageInfo.rotationDegrees % 360) + 360) % 360
        if (lastRotation != rotation) {
            resetSmoothing()
            lastRotation = rotation
        }

        val raw = runCatching { detectRaw(image, rotation) }.getOrNull()
        if (raw == null) {
            missedFrames += 1
            if (missedFrames >= 2) resetSmoothing()
            val previous = smoothedCorners ?: return null
            val decayed = (smoothedConfidence * 0.84f).coerceIn(0f, 1f)
            return if (decayed >= MIN_CONFIDENCE) Result(previous, decayed) else null
        }

        missedFrames = 0
        val previous = smoothedCorners
        val nextCorners = if (previous != null && previous.size == 4) {
            val maxJump = previous.zip(raw.corners).maxOf { (old, new) ->
                max(abs(old.x - new.x), abs(old.y - new.y))
            }
            val alpha = if (maxJump > 0.22f) 0.72f else SMOOTHING_ALPHA
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
        smoothedConfidence = (smoothedConfidence * 0.45f + raw.confidence * 0.55f).coerceIn(0f, 1f)
        return Result(nextCorners, smoothedConfidence)
    }

    private fun detectRaw(image: ImageProxy, rotation: Int): Result? {
        val sample = sampleLuma(image, SAMPLE_WIDTH, SAMPLE_HEIGHT) ?: return null
        val width = SAMPLE_WIDTH
        val height = SAMPLE_HEIGHT
        val sorted = sample.copyOf().also { it.sort() }
        val p10 = sorted.percentile(0.10f)
        val p55 = sorted.percentile(0.55f)
        val p72 = sorted.percentile(0.72f)
        val p88 = sorted.percentile(0.88f)
        val contrastRange = (p88 - p10).coerceAtLeast(1)
        val threshold = max(p72, p55 + max(12, (contrastRange * 0.16f).toInt())).coerceIn(68, 236)
        val edgeThreshold = max(24, (contrastRange * 0.22f).toInt()).coerceIn(24, 88)

        val brightMask = BooleanArray(sample.size)
        val edgeMap = IntArray(sample.size)
        var brightCount = 0
        var edgeCount = 0

        for (y in 1 until height - 1) {
            val rowOffset = y * width
            for (x in 1 until width - 1) {
                val index = rowOffset + x
                val center = sample[index]
                val horizontal = abs(sample[index - 1] - sample[index + 1])
                val vertical = abs(sample[index - width] - sample[index + width])
                val gradient = horizontal + vertical
                edgeMap[index] = gradient
                val isBright = center >= threshold
                val isDocumentCandidate = isBright || (center >= p55 + 8 && gradient >= edgeThreshold)
                brightMask[index] = isDocumentCandidate
                if (isDocumentCandidate) brightCount += 1
                if (gradient >= edgeThreshold) edgeCount += 1
            }
        }

        if (brightCount < sample.size * 0.045f || edgeCount < sample.size * 0.018f) return null

        val rowProjection = IntArray(height)
        val colProjection = IntArray(width)
        for (y in 1 until height - 1) {
            val rowOffset = y * width
            for (x in 1 until width - 1) {
                val index = rowOffset + x
                val brightWeight = if (brightMask[index]) 3 else 0
                val edgeWeight = if (edgeMap[index] >= edgeThreshold) 2 else 0
                val weight = brightWeight + edgeWeight
                if (weight > 0) {
                    rowProjection[y] += weight
                    colProjection[x] += weight
                }
            }
        }

        val verticalBounds = findProjectionBounds(rowProjection, minLengthRatio = 0.22f) ?: return null
        val horizontalBounds = findProjectionBounds(colProjection, minLengthRatio = 0.22f) ?: return null

        val paddingX = max(5, (width * 0.018f).toInt())
        val paddingY = max(5, (height * 0.018f).toInt())
        val leftBound = (horizontalBounds.first - paddingX).coerceIn(0, width - 2)
        val rightBound = (horizontalBounds.second + paddingX).coerceIn(leftBound + 1, width - 1)
        val topBound = (verticalBounds.first - paddingY).coerceIn(0, height - 2)
        val bottomBound = (verticalBounds.second + paddingY).coerceIn(topBound + 1, height - 1)

        val quadrilateral = estimateQuadrilateral(
            mask = brightMask,
            edgeMap = edgeMap,
            edgeThreshold = edgeThreshold,
            width = width,
            height = height,
            left = leftBound,
            top = topBound,
            right = rightBound,
            bottom = bottomBound
        ) ?: listOf(
            Offset(leftBound / width.toFloat(), topBound / height.toFloat()),
            Offset(rightBound / width.toFloat(), topBound / height.toFloat()),
            Offset(rightBound / width.toFloat(), bottomBound / height.toFloat()),
            Offset(leftBound / width.toFloat(), bottomBound / height.toFloat())
        )

        val detectedWidth = (rightBound - leftBound).toFloat() / width.toFloat()
        val detectedHeight = (bottomBound - topBound).toFloat() / height.toFloat()
        val area = detectedWidth * detectedHeight
        if (area !in 0.11f..0.90f) return null

        val aspect = detectedWidth / detectedHeight.coerceAtLeast(0.001f)
        val a4Portrait = 1f / 1.4142f
        val a4Landscape = 1.4142f
        val aspectError = min(abs(aspect - a4Portrait), abs(aspect - a4Landscape))
        val aspectScore = (1f - aspectError / 0.95f).coerceIn(0f, 1f)
        val areaScore = (area / 0.58f).coerceIn(0f, 1f)
        val centerX = (leftBound + rightBound) / 2f / width.toFloat()
        val centerY = (topBound + bottomBound) / 2f / height.toFloat()
        val centerScore = (1f - (abs(centerX - 0.5f) + abs(centerY - 0.5f)) / 0.70f).coerceIn(0f, 1f)
        val fillScore = (brightCount.toFloat() / sample.size.toFloat() / area.coerceAtLeast(0.08f)).coerceIn(0f, 1f)
        val edgeScore = (edgeCount.toFloat() / sample.size.toFloat() / 0.12f).coerceIn(0f, 1f)
        val borderPenalty = listOf(
            leftBound / width.toFloat(),
            topBound / height.toFloat(),
            1f - rightBound / width.toFloat(),
            1f - bottomBound / height.toFloat()
        ).minOrNull()?.let { if (it < 0.012f) 0.12f else 0f } ?: 0f

        val confidence = (
            areaScore * 0.30f +
                aspectScore * 0.18f +
                fillScore * 0.18f +
                edgeScore * 0.22f +
                centerScore * 0.12f -
                borderPenalty
            ).coerceIn(0f, 1f)

        if (confidence < MIN_CONFIDENCE) return null
        return Result(quadrilateral.rotateNormalized(rotation), confidence)
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
                output[y * targetWidth + x] = if (index in 0 until buffer.limit()) {
                    buffer.get(index).toInt() and 0xFF
                } else {
                    0
                }
            }
        }
        return output
    }

    private fun findProjectionBounds(values: IntArray, minLengthRatio: Float): Pair<Int, Int>? {
        val smoothed = IntArray(values.size)
        for (i in values.indices) {
            var total = 0
            var count = 0
            for (j in (i - 2)..(i + 2)) {
                if (j in values.indices) {
                    total += values[j]
                    count += 1
                }
            }
            smoothed[i] = if (count == 0) values[i] else total / count
        }

        val maxValue = smoothed.maxOrNull() ?: return null
        if (maxValue <= 0) return null
        val threshold = max(4, (maxValue * 0.20f).toInt())
        val minLength = max(8, (values.size * minLengthRatio).toInt())
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
                val score = (start..end).sumOf { smoothed[it] } + length * 3
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
                if (gap > 4) commit(i + 1)
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
        val bandHeight = max(10, (scanHeight * 0.22f).toInt())
        val topLefts = mutableListOf<Int>()
        val topRights = mutableListOf<Int>()
        val bottomLefts = mutableListOf<Int>()
        val bottomRights = mutableListOf<Int>()

        fun collectForRow(y: Int, lefts: MutableList<Int>, rights: MutableList<Int>) {
            if (y !in 1 until height - 1) return
            var rowLeft = -1
            var rowRight = -1
            val offset = y * width
            for (x in left..right) {
                val index = offset + x
                if (index in mask.indices && (mask[index] || edgeMap[index] >= edgeThreshold)) {
                    rowLeft = x
                    break
                }
            }
            for (x in right downTo left) {
                val index = offset + x
                if (index in mask.indices && (mask[index] || edgeMap[index] >= edgeThreshold)) {
                    rowRight = x
                    break
                }
            }
            if (rowLeft >= 0 && rowRight > rowLeft && rowRight - rowLeft > (right - left) * 0.45f) {
                lefts += rowLeft
                rights += rowRight
            }
        }

        for (y in top until min(bottom, top + bandHeight)) collectForRow(y, topLefts, topRights)
        for (y in max(top, bottom - bandHeight)..bottom) collectForRow(y, bottomLefts, bottomRights)

        if (topLefts.size < 4 || topRights.size < 4 || bottomLefts.size < 4 || bottomRights.size < 4) return null

        val tlX = topLefts.trimmedMedian().coerceIn(0, width - 1)
        val trX = topRights.trimmedMedian().coerceIn(0, width - 1)
        val blX = bottomLefts.trimmedMedian().coerceIn(0, width - 1)
        val brX = bottomRights.trimmedMedian().coerceIn(0, width - 1)

        return listOf(
            Offset(tlX / width.toFloat(), top / height.toFloat()),
            Offset(trX / width.toFloat(), top / height.toFloat()),
            Offset(brX / width.toFloat(), bottom / height.toFloat()),
            Offset(blX / width.toFloat(), bottom / height.toFloat())
        ).map { Offset(it.x.coerceIn(0f, 1f), it.y.coerceIn(0f, 1f)) }
    }

    private fun IntArray.percentile(percent: Float): Int {
        if (isEmpty()) return 0
        val index = (lastIndex * percent).toInt().coerceIn(0, lastIndex)
        return this[index]
    }

    private fun List<Int>.trimmedMedian(): Int {
        if (isEmpty()) return 0
        val sorted = sorted()
        val from = (sorted.size * 0.15f).toInt().coerceIn(0, sorted.lastIndex)
        val to = (sorted.size * 0.85f).toInt().coerceIn(from, sorted.lastIndex)
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

    private fun resetSmoothing() {
        smoothedCorners = null
        smoothedConfidence = 0f
        missedFrames = 0
    }
}
