package com.synthbyte.scanmate.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderZip
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

private enum class ToolTone { PRIMARY, ACCENT, DANGER, NEUTRAL }

private data class ToolAction(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val tone: ToolTone,
    val enabled: Boolean = false,
    val stateLabel: String = if (enabled) "Ready" else "Unavailable offline",
    val onClick: (() -> Unit)? = null
)

private data class ToolSection(
    val title: String,
    val subtitle: String,
    val actions: List<ToolAction>
)

@Composable
fun ToolsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToPdfTools: () -> Unit,
    onNavigateToQr: () -> Unit,
    onNavigateToQrScanner: () -> Unit,
    onNavigateToTranslate: () -> Unit,
    onNavigateToAi: () -> Unit,
    onNavigateToVault: () -> Unit,
    onNavigateToZip: () -> Unit,
    onNavigateToFiles: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToCamera: () -> Unit
) {
    val sections = listOf(
        ToolSection(
            title = "Convert",
            subtitle = "Offline-safe PDF, image, OCR and Office export tools. Unsupported DOCX import stays disabled clearly instead of producing invalid files.",
            actions = listOf(
                ToolAction("pdf_word", "PDF to Word", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, stateLabel = "OCR DOCX", onClick = onNavigateToPdfTools),
                ToolAction("pdf_excel", "PDF to Excel", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, stateLabel = "OCR XLSX", onClick = onNavigateToPdfTools),
                ToolAction("pdf_ppt", "PDF to PPT", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, stateLabel = "Valid PPTX", onClick = onNavigateToPdfTools),
                ToolAction("pdf_images", "PDF to Images", Icons.Default.Image, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToPdfTools),
                ToolAction("pdf_long_image", "PDF to Long Image", Icons.Default.Image, ToolTone.PRIMARY, enabled = true, stateLabel = "PNG", onClick = onNavigateToPdfTools),
                ToolAction("images_pdf", "Images to PDF", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToPdfTools),
                ToolAction("word_pdf", "Word to PDF", Icons.Default.PictureAsPdf, ToolTone.NEUTRAL),
                ToolAction("text_pdf", "Text to PDF", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToPdfTools)
            )
        ),
        ToolSection(
            title = "Import",
            subtitle = "Bring files into the local workspace without cloud sync or account requirements.",
            actions = listOf(
                ToolAction("import_images", "Import Images", Icons.Default.PhotoLibrary, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToFiles),
                ToolAction("import_files", "Import Files", Icons.Default.Folder, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToFiles),
                ToolAction("import_gallery", "Import from Gallery", Icons.Default.PhotoLibrary, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToFiles),
                ToolAction("import_multi_pdf", "Import Multiple PDFs", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToPdfTools)
            )
        ),
        ToolSection(
            title = "Edit",
            subtitle = "Core PDF/page actions route to existing offline editors; unsupported lock/unlock actions are disabled clearly.",
            actions = listOf(
                ToolAction("sign", "Sign", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, stateLabel = "Open doc", onClick = onNavigateToFiles),
                ToolAction("watermark", "Add Watermark", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, stateLabel = "Open doc", onClick = onNavigateToFiles),
                ToolAction("merge", "Merge Files", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToPdfTools),
                ToolAction("extract_pages", "Extract PDF Pages", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToPdfTools),
                ToolAction("reorder", "Reorder Pages", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, stateLabel = "Open doc", onClick = onNavigateToFiles),
                ToolAction("lock_pdf", "Lock PDF", Icons.Default.Lock, ToolTone.NEUTRAL),
                ToolAction("unlock_pdf", "Unlock PDF", Icons.Default.Lock, ToolTone.NEUTRAL),
                ToolAction("compress", "Compress PDF", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToPdfTools),
                ToolAction("rotate", "Rotate Pages", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, stateLabel = "Open doc", onClick = onNavigateToFiles),
                ToolAction("delete_pages", "Delete Pages", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, stateLabel = "Open doc", onClick = onNavigateToFiles),
                ToolAction("rename", "Rename Document", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, stateLabel = "Open doc", onClick = onNavigateToFiles),
                ToolAction("duplicate", "Duplicate Document", Icons.Default.ContentCopy, ToolTone.PRIMARY, enabled = true, stateLabel = "Open doc", onClick = onNavigateToFiles)
            )
        ),
        ToolSection(
            title = "AI Tools",
            subtitle = "Offline-first enhancement helpers. Online Gemini features remain optional and user-key controlled.",
            actions = listOf(
                ToolAction("erase_marks", "Erase Marks", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = false, stateLabel = "Coming soon"),
                ToolAction("enhance", "Enhance Documents", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = false, stateLabel = "Coming soon"),
                ToolAction("auto_crop", "Auto Crop", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = true, stateLabel = "Camera", onClick = onNavigateToPdfTools),
                ToolAction("auto_deskew", "Auto Deskew", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = false, stateLabel = "Coming soon"),
                ToolAction("shadow", "Shadow Removal", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = false, stateLabel = "Coming soon"),
                ToolAction("contrast", "Contrast Boost", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = true, stateLabel = "Filters", onClick = onNavigateToPdfTools),
                ToolAction("clean_bg", "Clean Background", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = false, stateLabel = "Coming soon"),
                ToolAction("ocr_cleanup", "OCR Cleanup", Icons.Default.Translate, ToolTone.ACCENT, enabled = true, stateLabel = "Ready", onClick = onNavigateToTranslate),
                ToolAction("smart_title", "Smart Title Suggestion", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = true, stateLabel = "Open AI", onClick = onNavigateToAi),
                ToolAction("quality_score", "Document Quality Score", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = false, stateLabel = "Coming soon")
            )
        ),
        ToolSection(
            title = "Scan",
            subtitle = "Purpose-built entry points for common document capture workflows.",
            actions = listOf(
                ToolAction("id_cards", "ID Cards", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, stateLabel = "Scan", onClick = onNavigateToCamera),
                ToolAction("extract_text", "Extract Text", Icons.Default.Translate, ToolTone.PRIMARY, enabled = true, stateLabel = "OCR", onClick = onNavigateToTranslate),
                ToolAction("id_photo", "ID Photo Maker", Icons.Default.CameraAlt, ToolTone.NEUTRAL),
                ToolAction("scan_excel", "Scan to Excel", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, stateLabel = "OCR XLSX", onClick = onNavigateToFiles),
                ToolAction("question_set", "Question Set", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, stateLabel = "Scan", onClick = onNavigateToCamera),
                ToolAction("book_scan", "Book Scan", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, stateLabel = "Scan", onClick = onNavigateToCamera),
                ToolAction("slides_scan", "Slides Scan", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, stateLabel = "Scan", onClick = onNavigateToCamera),
                ToolAction("receipt_scan", "Receipt Scan", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, stateLabel = "Scan", onClick = onNavigateToCamera),
                ToolAction("business_card", "Business Card Scan", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, stateLabel = "Scan", onClick = onNavigateToCamera),
                ToolAction("whiteboard", "Whiteboard Scan", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, stateLabel = "Scan", onClick = onNavigateToCamera),
                ToolAction("assignment", "Assignment Scan", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, stateLabel = "Scan", onClick = onNavigateToCamera),
                ToolAction("notes", "Notes Scan", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, stateLabel = "Scan", onClick = onNavigateToCamera)
            )
        ),
        ToolSection(
            title = "Other",
            subtitle = "Everyday utilities already available in ScanMate AI Pro.",
            actions = listOf(
                ToolAction("scan_qr", "Scan QR Code", Icons.Default.QrCode2, ToolTone.ACCENT, enabled = true, stateLabel = "Ready", onClick = onNavigateToQrScanner),
                ToolAction("generate_qr", "Generate QR Code", Icons.Default.QrCode2, ToolTone.ACCENT, enabled = true, stateLabel = "Ready", onClick = onNavigateToQr),
                ToolAction("print", "Print", Icons.Default.Print, ToolTone.NEUTRAL),
                ToolAction("share", "Share", Icons.Default.Share, ToolTone.PRIMARY, enabled = true, stateLabel = "Open files", onClick = onNavigateToFiles),
                ToolAction("zip", "ZIP Backup", Icons.Default.FolderZip, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToZip),
                ToolAction("vault", "Vault", Icons.Default.Lock, ToolTone.DANGER, enabled = true, stateLabel = "Ready", onClick = onNavigateToVault),
                ToolAction("settings", "Settings", Icons.Default.Settings, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToSettings),
                ToolAction("files", "File Manager", Icons.Default.Folder, ToolTone.PRIMARY, enabled = true, stateLabel = "Ready", onClick = onNavigateToFiles)
            )
        )
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Tools") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 72.dp),
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }, key = "tools_intro") {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(
                        text = "ALL TOOLS",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.72f),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 10.sp,
                        letterSpacing = 0.14.em
                    )
                    Text("Premium offline workspace", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                    Text(
                        "Clean, local-first document tools. Disabled items show what is unsupported without generating invalid output.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            sections.forEach { section ->
                item(span = { GridItemSpan(maxLineSpan) }, key = "header_${section.title}") {
                    SectionHeader(section)
                }
                items(section.actions, key = { it.id }) { tool ->
                    ToolCard(tool)
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(section: ToolSection) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp), modifier = Modifier.padding(top = 8.dp, bottom = 2.dp)) {
        Text(section.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold)
        Text(section.subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun colorsForTone(tone: ToolTone) = when (tone) {
    ToolTone.PRIMARY -> MaterialTheme.colorScheme.primaryContainer to MaterialTheme.colorScheme.primary
    ToolTone.ACCENT -> MaterialTheme.colorScheme.secondaryContainer to MaterialTheme.colorScheme.secondary
    ToolTone.DANGER -> MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.error
    ToolTone.NEUTRAL -> MaterialTheme.colorScheme.surfaceVariant to MaterialTheme.colorScheme.onSurfaceVariant
}

@Composable
private fun ToolCard(tool: ToolAction) {
    val (iconContainer, iconTint) = colorsForTone(tool.tone)
    Card(
        onClick = { tool.onClick?.invoke() },
        enabled = tool.enabled && tool.onClick != null,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (tool.enabled) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.44f),
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.44f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            val contentAlpha = if (tool.enabled) 1f else 0.45f
            Box(
                modifier = Modifier.size(40.dp).background(iconContainer, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(tool.icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(21.dp).alpha(contentAlpha))
            }
            Text(
                tool.title,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.alpha(contentAlpha)
            )
            Text(
                tool.stateLabel,
                color = if (tool.enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.alpha(contentAlpha)
            )
        }
    }
}
