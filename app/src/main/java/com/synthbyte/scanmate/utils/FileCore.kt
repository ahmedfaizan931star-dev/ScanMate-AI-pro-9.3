package com.synthbyte.scanmate.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FileCore {
    fun createUniqueImageFile(context: Context): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(Date())
        val imageFileName = "SCAN_${timeStamp}_"
        val storageDir = context.getExternalFilesDir("Scans")
        if (storageDir?.exists() == false) storageDir.mkdirs()
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    fun appFolder(context: Context, name: String): File? {
        val dir = context.getExternalFilesDir(name)
        if (dir?.exists() == false) dir.mkdirs()
        return dir
    }

    fun listManagedFiles(context: Context): List<File> {
        val folders = listOf("Scans", "PDFs", "QRCodes", "OCR", "Backups", "Signatures", "Vault", "Exports")
        return folders.flatMap { folder ->
            appFolder(context, folder)?.listFiles()?.filter { it.isFile } ?: emptyList()
        }.sortedByDescending { it.lastModified() }
    }

    fun shareFile(context: Context, file: File, mimeType: String = mimeTypeFor(file)) {
        if (!file.exists() || file.length() == 0L) {
            Toast.makeText(context, "File is missing or empty", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = mimeType
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(shareIntent, "Share File"))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "No app found to share this file", Toast.LENGTH_SHORT).show()
        }
    }

    fun openFile(context: Context, file: File, mimeType: String = mimeTypeFor(file)) {
        if (!file.exists() || file.length() == 0L) {
            Toast.makeText(context, "File is missing or empty", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, mimeType)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(intent, "Open with"))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "No app found to open this file", Toast.LENGTH_SHORT).show()
        }
    }

    fun shareText(context: Context, text: String, title: String = "Share Text") {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }
            context.startActivity(Intent.createChooser(shareIntent, title))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "No app found to share text", Toast.LENGTH_SHORT).show()
        }
    }

    fun copyUriToImageFile(context: Context, uri: Uri): File? {
        return try {
            val file = createUniqueImageFile(context)
            context.contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(file).use { output -> input.copyTo(output) }
            } ?: return null
            if (file.exists() && file.length() > 0L) file else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun saveTextFile(context: Context, text: String, filename: String): File? = withContext(Dispatchers.IO) {
        try {
            val storageDir = appFolder(context, "OCR") ?: return@withContext null
            val safeName = sanitizeFileBaseName(filename.ifBlank { "OCR_${System.currentTimeMillis()}" })
            val file = File(storageDir, if (safeName.endsWith(".txt")) safeName else "$safeName.txt")
            FileOutputStream(file).use { out -> out.write(text.toByteArray()) }
            file.takeIf { it.exists() && it.length() > 0L }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun saveBitmapAsPng(context: Context, bitmap: Bitmap, filename: String): File? =
        saveBitmapToFolder(context, bitmap, "QRCodes", filename, Bitmap.CompressFormat.PNG, 100)

    suspend fun saveBitmapToFolder(
        context: Context,
        bitmap: Bitmap,
        folderName: String,
        filename: String,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 92
    ): File? = withContext(Dispatchers.IO) {
        try {
            val storageDir = appFolder(context, folderName) ?: return@withContext null
            val safeName = sanitizeFileBaseName(filename.ifBlank { "ScanMate_${System.currentTimeMillis()}" })
            val extension = when (format) {
                Bitmap.CompressFormat.PNG -> ".png"
                Bitmap.CompressFormat.WEBP -> ".webp"
                else -> ".jpg"
            }
            val finalName = if (safeName.endsWith(extension, ignoreCase = true)) safeName else "$safeName$extension"
            val file = File(storageDir, finalName)
            FileOutputStream(file).use { out ->
                if (!bitmap.compress(format, quality.coerceIn(1, 100), out)) return@withContext null
            }
            file.takeIf { it.exists() && it.length() > 0L }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    fun getDisplayName(context: Context, uri: Uri): String {
        return runCatching {
            context.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)?.use { cursor ->
                val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (cursor.moveToFirst() && index >= 0) cursor.getString(index) else null
            }
        }.getOrNull() ?: uri.lastPathSegment ?: "Selected file"
    }

    fun mimeTypeFor(file: File): String = when (file.extension.lowercase(Locale.US)) {
        "pdf" -> "application/pdf"
        "png" -> "image/png"
        "jpg", "jpeg" -> "image/jpeg"
        "txt" -> "text/plain"
        "docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        "xlsx" -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        "pptx" -> "application/vnd.openxmlformats-officedocument.presentationml.presentation"
        "zip" -> "application/zip"
        "vault" -> "application/octet-stream"
        else -> "application/octet-stream"
    }
    fun sanitizeFileBaseName(value: String): String = value
        .trim()
        .ifBlank { "ScanMate_${System.currentTimeMillis()}" }
        .replace(Regex("[^A-Za-z0-9._-]"), "_")
        .take(90)
}
