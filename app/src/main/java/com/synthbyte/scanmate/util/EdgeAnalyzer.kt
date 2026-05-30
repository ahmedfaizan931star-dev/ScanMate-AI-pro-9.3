package com.synthbyte.scanmate.util

import android.graphics.Bitmap
import android.graphics.Color
import androidx.camera.core.ImageProxy
import androidx.compose.ui.geometry.Offset
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Lightweight offline document edge detector used by the CameraX preview analyzer.
 *
 * The detector intentionally stays dependency-free: it samples the Y plane, builds an adaptive
 * bright-region mask, extracts projection bounds, and returns normalized preview corners.
 */
object EdgeAnalyzer {

    data class Result(val corners: List<Offset>, val confidence: Float)

    fun ImageProxy.toGrayscaleBitmap(): Bitmap {
        val yPlane = planes[0]
        val imageWidth = width
        val imageHeight = height
        val rowStride = yPlane.rowStride
        val pixelStride = yPlane.pixelStride.coerceAtLeast(1)
        val buffer = yPlane.buffer
        val bitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
        val pixels = IntArray(imageWidth * imageHeight)
        val row = ByteArray(rowStride)

        buffer.rewind()
        for (y in 0 until imageHeight) {
            val rowStart = y * rowStride
            if (rowStart >= buffer.limit()) break
            buffer.position(rowStart)
            val bytesToRead = min(rowStride, buffer.remaining())
            if (bytesToRead <= 0) break
            buffer.get(row, 0, bytesToRead)

            for (x in 0 until imageWidth) {
                val sourceIndex = min(x * pixelStride, bytesToRead - 1).coerceAtLeast(0)
                val luma = row[sourceIndex].toInt() and 0xFF
                pixels[y * imageWidth + x] = Color.rgb(luma, luma, luma)
            }
        }

        bitmap.setPixels(pixels, 0, imageWidth, 0, 0, imageWidth, imageHeight)
        return bitmap
    }

    fun detect(image: ImageProxy): Result? {
        var bitmap: Bitmap? = null
        var scaled: Bitmap? = null
        return try {
            val grayscale = image.toGrayscaleBitmap()
            bitmap = grayscale
            scaled = Bitmap.createScaledBitmap(grayscale, 320, 240, true)
            val previewWidth = scaled.width
            val previewHeight = scaled.height
            val pixels = IntArray(previewWidth * previewHeight)
            scaled.getPixels(pixels, 0, previewWidth, 0, 0, previewWidth, previewHeight)

            val luma = IntArray(pixels.size) { index -> Color.red(pixels[index]) }
            val mean = luma.average().toFloat()
            val sorted = luma.copyOf().also { it.sort() }
            val p70 = sorted[(sorted.lastIndex * 0.70f).toInt().coerceIn(0, sorted.lastIndex)]
            val threshold = max(mean + 18f, p70.toFloat()).coerceIn(72f, 235f)

            val mask = BooleanArray(luma.size)
            var brightPixels = 0
            for (index in luma.indices) {
                val isDocumentPixel = luma[index] >= threshold
                mask[index] = isDocumentPixel
                if (isDocumentPixel) brightPixels++
            }
            if (brightPixels < mask.size * 0.05f) return null

            val rowSum = IntArray(previewHeight)
            val colSum = IntArray(previewWidth)
            for (y in 0 until previewHeight) {
                val offset = y * previewWidth
                for (x in 0 until previewWidth) {
                    if (mask[offset + x]) {
                        rowSum[y]++
                        colSum[x]++
                    }
                }
            }

            fun findBounds(sumArray: IntArray): Pair<Int, Int>? {
                val maxValue = sumArray.maxOrNull() ?: return null
                if (maxValue == 0) return null
                val projectionThreshold = max(3, (maxValue * 0.32f).toInt())
                var start = -1
                var bestStart = -1
                var bestEnd = -1
                var bestScore = 0

                fun commit(endExclusive: Int) {
                    if (start == -1) return
                    val end = endExclusive - 1
                    val score = (start..end).sumOf { sumArray[it] }
                    val length = end - start + 1
                    if (length >= sumArray.size * 0.18f && score > bestScore) {
                        bestScore = score
                        bestStart = start
                        bestEnd = end
                    }
                    start = -1
                }

                for (i in sumArray.indices) {
                    if (sumArray[i] >= projectionThreshold) {
                        if (start == -1) start = i
                    } else {
                        commit(i)
                    }
                }
                commit(sumArray.size)
                return if (bestStart >= 0 && bestEnd > bestStart) bestStart to bestEnd else null
            }

            val vertical = findBounds(rowSum) ?: return null
            val horizontal = findBounds(colSum) ?: return null

            val paddingX = (previewWidth * 0.015f).coerceAtLeast(4f)
            val paddingY = (previewHeight * 0.015f).coerceAtLeast(4f)
            val left = ((horizontal.first - paddingX) / previewWidth).coerceIn(0f, 1f)
            val right = ((horizontal.second + paddingX) / previewWidth).coerceIn(0f, 1f)
            val top = ((vertical.first - paddingY) / previewHeight).coerceIn(0f, 1f)
            val bottom = ((vertical.second + paddingY) / previewHeight).coerceIn(0f, 1f)

            val detectedWidth = right - left
            val detectedHeight = bottom - top
            if (detectedWidth <= 0f || detectedHeight <= 0f) return null

            val area = detectedWidth * detectedHeight
            if (area !in 0.12f..0.88f) return null

            val detectedAspect = detectedWidth / detectedHeight
            val documentAspect = 1f / 1.4142f
            val inverseDocumentAspect = 1.4142f
            val aspectScore = max(
                0f,
                1f - min(abs(detectedAspect - documentAspect), abs(detectedAspect - inverseDocumentAspect))
            )
            val fillConfidence = (brightPixels.toFloat() / mask.size.toFloat() / area).coerceIn(0f, 1f)
            val areaScore = (area / 0.55f).coerceIn(0f, 1f)
            val confidence = (areaScore * 0.45f + aspectScore * 0.35f + fillConfidence * 0.20f).coerceIn(0f, 1f)
            if (confidence < 0.38f) return null

            val corners = listOf(
                Offset(left, top),
                Offset(right, top),
                Offset(right, bottom),
                Offset(left, bottom)
            ).rotateNormalized(image.imageInfo.rotationDegrees)

            Result(corners, confidence)
        } catch (_: Throwable) {
            null
        } finally {
            scaled?.recycle()
            bitmap?.recycle()
        }
    }

    private fun List<Offset>.rotateNormalized(rotationDegrees: Int): List<Offset> {
        return when (((rotationDegrees % 360) + 360) % 360) {
            90 -> map { Offset(1f - it.y, it.x) }
            180 -> map { Offset(1f - it.x, 1f - it.y) }
            270 -> map { Offset(it.y, 1f - it.x) }
            else -> this
        }
    }
}
