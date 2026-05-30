package com.synthbyte.scanmate.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.media.ExifInterface
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File
import kotlin.coroutines.resume
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

private const val OCR_MAX_SIDE = 2048

data class OcrExtractionResult(
    val text: String,
    val confidencePercent: Int,
    val wordCount: Int,
    val qualityLabel: String
)

object OcrHelper {
    @Volatile
    private var recognizer: TextRecognizer? = null

    private fun getRecognizer(): TextRecognizer {
        return recognizer ?: synchronized(this) {
            recognizer ?: TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS).also { recognizer = it }
        }
    }

    suspend fun extractTextFromBitmap(bitmap: Bitmap, rotationDegrees: Int = 0): String {
        return extractStatsFromBitmap(bitmap, rotationDegrees).text
    }

    suspend fun extractTextFromFile(context: Context, file: File): String {
        return extractTextWithStatsFromFile(context, file).text
    }

    @Suppress("UNUSED_PARAMETER")
    suspend fun extractBlocksFromFile(context: Context, file: File): List<Pair<Rect, String>> {
        val source = FileUtils.decodeSampledBitmap(file.absolutePath, OCR_MAX_SIDE, OCR_MAX_SIDE) ?: return emptyList()
        val fixed = fixExifRotation(source, file)
        fun recycleBitmaps() {
            if (fixed !== source && !fixed.isRecycled) runCatching { fixed.recycle() }
            if (!source.isRecycled) runCatching { source.recycle() }
        }
        return suspendCancellableCoroutine { continuation ->
            getRecognizer().process(InputImage.fromBitmap(fixed, 0))
                .addOnSuccessListener { result ->
                    val rects = result.textBlocks.flatMap { block ->
                        block.lines.map { line -> line.boundingBox to line.text }
                    }.mapNotNull { (rect, text) -> rect?.let { Pair(it, text) } }
                    recycleBitmaps()
                    if (continuation.isActive) continuation.resume(rects)
                }
                .addOnFailureListener {
                    recycleBitmaps()
                    if (continuation.isActive) continuation.resume(emptyList())
                }
        }
    }

    suspend fun extractTextWithStatsFromFile(context: Context, file: File): OcrExtractionResult {
        val source = FileUtils.decodeSampledBitmap(file.absolutePath, OCR_MAX_SIDE, OCR_MAX_SIDE)
            ?: return buildStats("OCR failed: Could not decode image", 0)
        return try {
            val fixed = fixExifRotation(source, file)
            val prepared = preprocessForOcr(fixed)
            try {
                runTextRecognition(prepared, 0)
            } finally {
                if (prepared !== fixed && !prepared.isRecycled) runCatching { prepared.recycle() }
                if (fixed !== source && !fixed.isRecycled) runCatching { fixed.recycle() }
                if (!source.isRecycled) runCatching { source.recycle() }
            }
        } catch (e: Exception) {
            if (!source.isRecycled) runCatching { source.recycle() }
            buildStats("OCR failed: ${e.localizedMessage ?: "Unknown error"}", 0)
        }
    }

    suspend fun extractStatsFromBitmap(bitmap: Bitmap, rotationDegrees: Int = 0): OcrExtractionResult {
        val rotated = if (rotationDegrees != 0) rotate(bitmap, rotationDegrees.toFloat()) else bitmap
        val prepared = preprocessForOcr(rotated)
        return try {
            runTextRecognition(prepared, 0)
        } finally {
            if (prepared !== rotated && !prepared.isRecycled) runCatching { prepared.recycle() }
            if (rotated !== bitmap && !rotated.isRecycled) runCatching { rotated.recycle() }
        }
    }

    fun buildStats(text: String): OcrExtractionResult = buildStats(text, null)

    private fun buildStats(text: String, mlKitConfidence: Int?): OcrExtractionResult {
        val clean = DocumentIntelligence.cleanOcrText(text)
        val words = clean.split(Regex("\\s+")).filter { it.isNotBlank() }
        val confidence = when {
            clean.isBlank() || clean.startsWith("OCR failed", ignoreCase = true) -> 0
            mlKitConfidence != null -> mlKitConfidence.coerceIn(0, 100)
            words.size >= 120 -> 82
            words.size >= 40 -> 74
            words.size >= 12 -> 62
            else -> 45
        }
        val label = when {
            confidence >= 88 -> "High confidence"
            confidence >= 72 -> "Good confidence"
            confidence >= 55 -> "Needs review"
            confidence > 0 -> "Low confidence"
            else -> "No OCR text"
        }
        return OcrExtractionResult(clean, confidence, words.size, label)
    }

    fun closeRecognizer() {
        synchronized(this) {
            runCatching { recognizer?.close() }
            recognizer = null
        }
    }

    private suspend fun runTextRecognition(bitmap: Bitmap, rotationDegrees: Int): OcrExtractionResult =
        suspendCancellableCoroutine { continuation ->
            try {
                val activeRecognizer = getRecognizer()
                activeRecognizer.process(InputImage.fromBitmap(bitmap, rotationDegrees))
                    .addOnSuccessListener { result ->
                        if (continuation.isActive) {
                            continuation.resume(buildStats(reconstructParagraphs(result.textBlocks), result.symbolConfidencePercent()))
                        }
                    }
                    .addOnFailureListener { e ->
                        if (continuation.isActive) continuation.resume(buildStats("OCR failed: ${e.localizedMessage ?: "Unknown error"}", 0))
                    }
            } catch (e: Exception) {
                if (continuation.isActive) continuation.resume(buildStats("OCR failed: ${e.localizedMessage ?: "Unknown error"}", 0))
            }
        }


    private fun reconstructParagraphs(blocks: List<com.google.mlkit.vision.text.Text.TextBlock>): String {
        if (blocks.isEmpty()) return ""
        val sorted = blocks.sortedWith(compareBy({ it.boundingBox?.top ?: 0 }, { it.boundingBox?.left ?: 0 }))
        val paras = mutableListOf<StringBuilder>()
        var cur = StringBuilder()
        var lastBottom = sorted.first().boundingBox?.bottom ?: 0
        sorted.forEach { block ->
            val top = block.boundingBox?.top ?: 0
            val gap = top - lastBottom
            val h = ((block.boundingBox?.bottom ?: 0) - top).coerceAtLeast(1)
            if (gap > h * 1.4f && cur.isNotBlank()) {
                paras.add(cur)
                cur = StringBuilder()
            }
            block.lines.forEach { line ->
                val t = line.text.trim()
                if (t.isNotBlank()) {
                    if (cur.isNotBlank()) cur.append(" ")
                    cur.append(t)
                }
            }
            lastBottom = block.boundingBox?.bottom ?: lastBottom
        }
        if (cur.isNotBlank()) paras.add(cur)
        return paras.joinToString("\n\n") { paragraph ->
            var t = paragraph.toString().trim()
            if (t.isNotEmpty() && t.last().isLetter()) t += "."
            t.replace(Regex(" {2,}"), " ").replace(Regex("([a-z])([A-Z])"), "$1 $2")
        }
    }

    private fun Text.toSortedText(): String {
        return textBlocks
            .sortedWith(compareBy<Text.TextBlock> { it.boundingBox?.top ?: Int.MAX_VALUE }.thenBy { it.boundingBox?.left ?: Int.MAX_VALUE })
            .joinToString("\n") { block ->
                block.lines
                    .sortedWith(compareBy<Text.Line> { it.boundingBox?.top ?: Int.MAX_VALUE }.thenBy { it.boundingBox?.left ?: Int.MAX_VALUE })
                    .joinToString("\n") { line -> line.text }
            }
            .trim()
    }

    private fun Text.symbolConfidencePercent(): Int? {
        val values = textBlocks
            .flatMap { it.lines }
            .flatMap { it.elements }
            .flatMap { it.symbols }
            .mapNotNull { symbol ->
                val confidence = symbol.confidence
                if (confidence >= 0f) confidence else null
            }
        if (values.isEmpty()) return null
        return (values.average() * 100.0).roundToInt().coerceIn(0, 100)
    }

    private fun preprocessForOcr(source: Bitmap): Bitmap {
        val deskewed = estimateSkewAndRotate(source)
        val scaled = deskewed.scaleDownToMax(OCR_MAX_SIDE)
        val gray = Bitmap.createBitmap(scaled.width, scaled.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(gray)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG).apply {
            colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f) })
        }
        canvas.drawBitmap(scaled, 0f, 0f, paint)
        if (scaled !== deskewed && !scaled.isRecycled) runCatching { scaled.recycle() }
        if (deskewed !== source && deskewed !== scaled && !deskewed.isRecycled) runCatching { deskewed.recycle() }
        val highContrast = FileUtils.applyFilter(gray, FilterType.HIGH_CONTRAST)
        if (!gray.isRecycled) runCatching { gray.recycle() }
        return highContrast
    }

    private fun estimateSkewAndRotate(source: Bitmap): Bitmap {
        if (source.width < 100 || source.height < 100) return source
        val maxSampleSide = 720
        val sampleScale = min(1f, maxSampleSide.toFloat() / max(source.width, source.height).toFloat())
        val sample = if (sampleScale < 1f) {
            Bitmap.createScaledBitmap(
                source,
                (source.width * sampleScale).roundToInt().coerceAtLeast(1),
                (source.height * sampleScale).roundToInt().coerceAtLeast(1),
                true
            )
        } else {
            source
        }

        val angles = listOf(0f) + generateSequence(-10f) { previous ->
            val next = previous + 0.5f
            if (next <= 10.0001f) next else null
        }.filter { kotlin.math.abs(it) >= 0.001f }.toList()
        var bestAngle = 0f
        var bestScore = Double.NEGATIVE_INFINITY
        angles.forEach { angle ->
            val rotated = rotateForSkewScore(sample, angle)
            val score = horizontalProjectionVariance(rotated)
            if (rotated !== sample && !rotated.isRecycled) runCatching { rotated.recycle() }
            if (score > bestScore) {
                bestScore = score
                bestAngle = angle
            }
        }
        if (sample !== source && !sample.isRecycled) runCatching { sample.recycle() }
        if (abs(bestAngle) < 0.4f) return source
        val matrix = Matrix().apply { postRotate(bestAngle) }
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    private fun rotateForSkewScore(source: Bitmap, angle: Float): Bitmap {
        if (abs(angle) < 0.001f) return source
        val matrix = Matrix().apply { postRotate(angle) }
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    private fun horizontalProjectionVariance(bitmap: Bitmap): Double {
        val width = bitmap.width
        val height = bitmap.height
        if (width <= 1 || height <= 1) return 0.0
        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
        val rowSums = DoubleArray(height)
        for (y in 0 until height) {
            var sum = 0.0
            var previousDark = false
            for (x in 0 until width) {
                val color = pixels[y * width + x]
                val luminance = (android.graphics.Color.red(color) * 0.299f + android.graphics.Color.green(color) * 0.587f + android.graphics.Color.blue(color) * 0.114f)
                val dark = luminance < 190f
                if (dark && !previousDark) sum += 1.0
                if (dark) sum += (255f - luminance) / 255f
                previousDark = dark
            }
            rowSums[y] = sum
        }
        val mean = rowSums.average()
        return rowSums.fold(0.0) { acc, value ->
            val delta = value - mean
            acc + delta * delta
        } / height.toDouble()
    }

    private fun fixExifRotation(bitmap: Bitmap, file: File): Bitmap {
        val degrees = runCatching {
            when (ExifInterface(file.absolutePath).getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)) {
                ExifInterface.ORIENTATION_ROTATE_90 -> 90f
                ExifInterface.ORIENTATION_ROTATE_180 -> 180f
                ExifInterface.ORIENTATION_ROTATE_270 -> 270f
                else -> 0f
            }
        }.getOrDefault(0f)
        return if (degrees == 0f) bitmap else rotate(bitmap, degrees)
    }

    private fun rotate(source: Bitmap, degrees: Float): Bitmap {
        val matrix = Matrix().apply { postRotate(degrees) }
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    private fun Bitmap.scaleDownToMax(maxSide: Int): Bitmap {
        val side = max(width, height)
        if (side <= maxSide) return this
        val ratio = maxSide.toFloat() / side.toFloat()
        val targetWidth = (width * ratio).roundToInt().coerceAtLeast(1)
        val targetHeight = (height * ratio).roundToInt().coerceAtLeast(1)
        return Bitmap.createScaledBitmap(this, targetWidth, targetHeight, true)
    }
}
