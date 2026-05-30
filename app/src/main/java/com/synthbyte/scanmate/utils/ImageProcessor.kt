package com.synthbyte.scanmate.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

object ImageProcessor {
    suspend fun saveEditedBitmap(context: Context, bitmap: Bitmap, sourceName: String = "EDITED"): File? {
        val name = "${sourceName}_${System.currentTimeMillis()}"
        return FileUtils.saveBitmapToFolder(context, bitmap, "Scans", name, Bitmap.CompressFormat.JPEG, 94)
    }

    fun duplicateImageFile(context: Context, sourcePath: String): File? {
        return try {
            val source = File(sourcePath)
            if (!source.exists() || source.length() == 0L) return null
            val copy = FileUtils.createUniqueImageFile(context)
            source.inputStream().use { input -> copy.outputStream().use { output -> input.copyTo(output) } }
            copy.takeIf { it.exists() && it.length() > 0L }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun decodeSampledBitmap(path: String, reqWidth: Int = 1600, reqHeight: Int = 1600): Bitmap? {
        return try {
            val file = File(path)
            if (!file.exists() || file.length() == 0L) return null
            val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeFile(path, options)
            if (options.outWidth <= 0 || options.outHeight <= 0) return null
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
            options.inJustDecodeBounds = false
            options.inPreferredConfig = Bitmap.Config.ARGB_8888
            BitmapFactory.decodeFile(path, options)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return max(1, inSampleSize)
    }

    fun applyPerspectiveCorrection(
        file: File,
        corners: List<Offset>,
        previewWidth: Int,
        previewHeight: Int
    ): File? {
        if (corners.size != 4) return null
        val bitmap = decodeSampledBitmap(file.absolutePath, 2048, 2048) ?: return null
        val safePreviewWidth = previewWidth.coerceAtLeast(1)
        val safePreviewHeight = previewHeight.coerceAtLeast(1)
        val scaleX = bitmap.width.toFloat() / safePreviewWidth
        val scaleY = bitmap.height.toFloat() / safePreviewHeight
        val cornersAreNormalized = corners.all { it.x in 0f..1f && it.y in 0f..1f }
        fun mappedX(point: Offset): Float = if (cornersAreNormalized) point.x * bitmap.width else point.x * scaleX
        fun mappedY(point: Offset): Float = if (cornersAreNormalized) point.y * bitmap.height else point.y * scaleY
        val src = floatArrayOf(
            mappedX(corners[0]), mappedY(corners[0]),
            mappedX(corners[1]), mappedY(corners[1]),
            mappedX(corners[2]), mappedY(corners[2]),
            mappedX(corners[3]), mappedY(corners[3])
        )
        val w = bitmap.width.toFloat()
        val h = bitmap.height.toFloat()
        val dst = floatArrayOf(0f, 0f, w, 0f, w, h, 0f, h)
        val matrix = Matrix()
        val mapped = matrix.setPolyToPoly(src, 0, dst, 0, 4)
        if (!mapped) {
            bitmap.recycle()
            return null
        }
        val warped = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(warped)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, matrix, Paint(Paint.FILTER_BITMAP_FLAG))
        bitmap.recycle()
        val parent = file.parent ?: run {
            warped.recycle()
            return null
        }
        val out = File(parent, "warped_${file.name}")
        FileOutputStream(out).use { warped.compress(Bitmap.CompressFormat.JPEG, 93, it) }
        warped.recycle()
        return out.takeIf { it.exists() && it.length() > 0L }
    }

    fun rotateBitmap(source: Bitmap, degrees: Float): Bitmap {
        val matrix = android.graphics.Matrix().apply { postRotate(degrees) }
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    fun cropBitmapNormalized(source: Bitmap, leftPercent: Float, topPercent: Float, rightPercent: Float, bottomPercent: Float): Bitmap {
        val left = (source.width * leftPercent.coerceIn(0f, 0.85f)).roundToInt()
        val top = (source.height * topPercent.coerceIn(0f, 0.85f)).roundToInt()
        val rightMargin = (source.width * rightPercent.coerceIn(0f, 0.85f)).roundToInt()
        val bottomMargin = (source.height * bottomPercent.coerceIn(0f, 0.85f)).roundToInt()
        val width = (source.width - left - rightMargin).coerceAtLeast(64)
        val height = (source.height - top - bottomMargin).coerceAtLeast(64)
        return Bitmap.createBitmap(source, left.coerceAtMost(source.width - 1), top.coerceAtMost(source.height - 1), min(width, source.width - left), min(height, source.height - top))
    }

    fun autoCropDocument(source: Bitmap): Bitmap {
        if (source.width < 80 || source.height < 80) return source.copy(Bitmap.Config.ARGB_8888, false)
        val maxSampleSide = 700
        val sampled = source.scaleDownToMax(maxSampleSide) ?: return source.copy(Bitmap.Config.ARGB_8888, false)
        val threshold = 235
        var minX = sampled.width
        var minY = sampled.height
        var maxX = 0
        var maxY = 0
        val step = max(1, sampled.width.coerceAtLeast(sampled.height) / 350)
        for (y in 0 until sampled.height step step) {
            for (x in 0 until sampled.width step step) {
                val color = sampled.getPixel(x, y)
                val r = Color.red(color)
                val g = Color.green(color)
                val b = Color.blue(color)
                val brightness = (r + g + b) / 3
                val contrast = max(r, max(g, b)) - min(r, min(g, b))
                if (brightness < threshold || contrast > 28) {
                    minX = min(minX, x)
                    minY = min(minY, y)
                    maxX = max(maxX, x)
                    maxY = max(maxY, y)
                }
            }
        }
        if (maxX <= minX || maxY <= minY) return source.copy(Bitmap.Config.ARGB_8888, false)
        val marginX = (sampled.width * 0.025f).roundToInt()
        val marginY = (sampled.height * 0.025f).roundToInt()
        val sx = source.width.toFloat() / sampled.width.toFloat()
        val sy = source.height.toFloat() / sampled.height.toFloat()
        val left = ((minX - marginX).coerceAtLeast(0) * sx).roundToInt()
        val top = ((minY - marginY).coerceAtLeast(0) * sy).roundToInt()
        val right = ((maxX + marginX).coerceAtMost(sampled.width - 1) * sx).roundToInt()
        val bottom = ((maxY + marginY).coerceAtMost(sampled.height - 1) * sy).roundToInt()
        val width = (right - left).coerceAtLeast(64)
        val height = (bottom - top).coerceAtLeast(64)
        if (width < source.width * 0.35f || height < source.height * 0.35f) {
            return source.copy(Bitmap.Config.ARGB_8888, false)
        }
        return Bitmap.createBitmap(source, left.coerceAtLeast(0), top.coerceAtLeast(0), min(width, source.width - left), min(height, source.height - top))
    }

    fun perspectiveCorrectBitmapNormalized(
        source: Bitmap,
        topLeftX: Float,
        topLeftY: Float,
        topRightX: Float,
        topRightY: Float,
        bottomRightX: Float,
        bottomRightY: Float,
        bottomLeftX: Float,
        bottomLeftY: Float
    ): Bitmap {
        if (source.width < 80 || source.height < 80) return source.copy(Bitmap.Config.ARGB_8888, false)
        val w = source.width.toFloat()
        val h = source.height.toFloat()
        val src = floatArrayOf(
            (topLeftX.coerceIn(0f, 0.45f) * w), (topLeftY.coerceIn(0f, 0.45f) * h),
            (w - topRightX.coerceIn(0f, 0.45f) * w), (topRightY.coerceIn(0f, 0.45f) * h),
            (w - bottomRightX.coerceIn(0f, 0.45f) * w), (h - bottomRightY.coerceIn(0f, 0.45f) * h),
            (bottomLeftX.coerceIn(0f, 0.45f) * w), (h - bottomLeftY.coerceIn(0f, 0.45f) * h)
        )
        val dst = floatArrayOf(0f, 0f, w, 0f, w, h, 0f, h)
        val matrix = android.graphics.Matrix()
        if (!matrix.setPolyToPoly(src, 0, dst, 0, 4)) return source.copy(Bitmap.Config.ARGB_8888, false)
        val result = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        canvas.drawColor(Color.WHITE)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG or Paint.DITHER_FLAG)
        canvas.drawBitmap(source, matrix, paint)
        return result
    }

    fun applyFilter(original: Bitmap, type: FilterType): Bitmap {
        val safe = original.copy(Bitmap.Config.ARGB_8888, false)
        if (type == FilterType.ORIGINAL) return safe
        if (type == FilterType.SHARPEN || type == FilterType.SHARP_SCAN) return sharpenBitmap(safe)

        val result = Bitmap.createBitmap(safe.width, safe.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        val colorMatrix = ColorMatrix()

        when (type) {
            FilterType.ORIGINAL -> Unit
            FilterType.GRAYSCALE -> colorMatrix.setSaturation(0f)
            FilterType.BLACK_WHITE -> {
                colorMatrix.setSaturation(0f)
                colorMatrix.postConcat(contrastMatrix(1.85f, -85f))
            }
            FilterType.ENHANCED_COLOR, FilterType.MAGIC_COLOR -> {
                colorMatrix.setSaturation(1.35f)
                colorMatrix.postConcat(contrastMatrix(1.18f, 10f))
            }
            FilterType.LIGHTEN -> colorMatrix.set(contrastMatrixValues(1.08f, 34f))
            FilterType.SHARPEN, FilterType.SHARP_SCAN -> Unit
            FilterType.HIGH_CONTRAST -> colorMatrix.set(contrastMatrixValues(1.45f, -38f))
            FilterType.SOFT_SCAN -> {
                colorMatrix.setSaturation(0.82f)
                colorMatrix.postConcat(contrastMatrix(1.04f, 28f))
            }
            FilterType.RECEIPT_MODE -> {
                colorMatrix.setSaturation(0f)
                colorMatrix.postConcat(contrastMatrix(1.62f, -28f))
            }
            FilterType.BOOK_PAGE -> {
                colorMatrix.setSaturation(0.68f)
                colorMatrix.postConcat(contrastMatrix(1.12f, 38f))
            }
            FilterType.LOW_LIGHT_CLEANUP -> {
                colorMatrix.setSaturation(1.08f)
                colorMatrix.postConcat(contrastMatrix(1.16f, 54f))
            }
            FilterType.SHADOW_REDUCTION -> {
                colorMatrix.setSaturation(0.92f)
                colorMatrix.postConcat(contrastMatrix(1.1f, 42f))
            }
        }

        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(safe, 0f, 0f, paint)
        if (!safe.isRecycled) runCatching { safe.recycle() }
        return result
    }

    fun drawSignatureOnBitmap(pageBitmap: Bitmap, signatureBitmap: Bitmap, alignRight: Boolean = true): Bitmap {
        val result = pageBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(result)
        val targetWidth = (pageBitmap.width * 0.34f).roundToInt().coerceAtLeast(120)
        val scale = targetWidth.toFloat() / signatureBitmap.width.toFloat().coerceAtLeast(1f)
        val targetHeight = (signatureBitmap.height * scale).roundToInt().coerceAtLeast(60)
        val margin = (pageBitmap.width * 0.06f).roundToInt().coerceAtLeast(24)
        val left = if (alignRight) pageBitmap.width - targetWidth - margin else margin
        val top = pageBitmap.height - targetHeight - margin
        val rect = RectF(left.toFloat(), top.toFloat(), (left + targetWidth).toFloat(), (top + targetHeight).toFloat())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        canvas.drawBitmap(signatureBitmap, null, rect, paint)
        return result
    }

    fun drawWatermarkOnBitmap(source: Bitmap, text: String = "ScanMate AI Pro"): Bitmap {
        val cleanText = text.trim().ifBlank { "ScanMate AI Pro" }.take(70)
        val result = source.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(result)
        val textSize = max(30f, source.width * 0.038f)
        val margin = max(28f, source.width * 0.045f)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.SUBPIXEL_TEXT_FLAG).apply {
            color = Color.argb(82, 0, 0, 0)
            this.textSize = textSize
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.RIGHT
            setShadowLayer(textSize * 0.08f, 1.5f, 1.5f, Color.argb(70, 255, 255, 255))
        }
        canvas.drawText(cleanText, source.width - margin, source.height - margin, paint)
        return result
    }

    fun drawNoteStampOnBitmap(source: Bitmap, text: String): Bitmap {
        val cleanText = text.trim().ifBlank { "Reviewed in ScanMate AI Pro" }.take(140)
        val result = source.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(result)
        val textSize = max(28f, source.width * 0.032f)
        val margin = max(24f, source.width * 0.035f)
        val maxChars = 34
        val lines = cleanText.chunked(maxChars).take(3)
        val lineHeight = textSize * 1.35f
        val boxWidth = source.width * 0.78f
        val boxHeight = lineHeight * lines.size.toFloat() + margin
        val rect = RectF(margin, margin, margin + boxWidth, margin + boxHeight)
        val boxPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(214, 255, 255, 255) }
        val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(175, 30, 30, 30)
            style = Paint.Style.STROKE
            strokeWidth = max(2f, source.width * 0.002f)
        }
        val textPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.SUBPIXEL_TEXT_FLAG).apply {
            color = Color.argb(230, 20, 20, 20)
            this.textSize = textSize
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }
        canvas.drawRoundRect(rect, 18f, 18f, boxPaint)
        canvas.drawRoundRect(rect, 18f, 18f, strokePaint)
        lines.forEachIndexed { index, line ->
            canvas.drawText(line, margin * 1.45f, margin + lineHeight * (index.toFloat() + 0.95f), textPaint)
        }
        return result
    }




    private fun contrastMatrix(contrast: Float, brightness: Float): ColorMatrix = ColorMatrix(contrastMatrixValues(contrast, brightness))

    private fun contrastMatrixValues(contrast: Float, brightness: Float): FloatArray = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    private fun sharpenBitmap(source: Bitmap): Bitmap {
        val result = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG).apply {
            colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(1.05f) })
        }
        canvas.drawBitmap(source, 0f, 0f, paint)
        val overlayPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG).apply { alpha = 42 }
        canvas.drawBitmap(source, 0f, 0f, overlayPaint)
        return result
    }

    private fun Bitmap.scaleDownToMax(maxSide: Int): Bitmap? {
        if (width <= 0 || height <= 0) return null
        val side = max(width, height)
        if (side <= maxSide) return this
        val ratio = maxSide.toFloat() / side.toFloat()
        val targetWidth = (width * ratio).roundToInt().coerceAtLeast(1)
        val targetHeight = (height * ratio).roundToInt().coerceAtLeast(1)
        return Bitmap.createScaledBitmap(this, targetWidth, targetHeight, true)
    }

    fun compressBitmap(source: Bitmap, quality: Int = 90, format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG): ByteArray {
        return ByteArrayOutputStream().use { output ->
            source.compress(format, quality.coerceIn(1, 100), output)
            output.toByteArray()
        }
    }

    fun applyWatermark(source: Bitmap, text: String = "ScanMate AI Pro"): Bitmap = drawWatermarkOnBitmap(source, text)
}
