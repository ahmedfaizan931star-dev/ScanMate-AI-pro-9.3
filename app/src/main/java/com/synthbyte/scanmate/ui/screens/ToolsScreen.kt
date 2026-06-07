package com.synthbyte.scanmate.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
    val stateLabel: String = "",
    val onClick: (() -> Unit)? = null
)

private data class ToolSection(
    val title: String,
    val subtitle: String = "",
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
            title = "Scan & Import",
            actions = listOf(
                ToolAction("scan", "Scan", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToCamera),
                ToolAction("import_images", "Import Images", Icons.Default.PhotoLibrary, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToFiles),
                ToolAction("import_files", "Import Files", Icons.Default.Folder, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToFiles),
                ToolAction("ocr", "OCR", Icons.Default.Translate, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToTranslate),
                ToolAction("book_scan", "Book Scan", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToCamera),
                ToolAction("receipt_scan", "Receipt Scan", Icons.Default.CameraAlt, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToCamera)
            )
        ),
        ToolSection(
            title = "Convert & Export",
            actions = listOf(
                ToolAction("pdf_word", "PDF to Word", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, stateLabel = "DOCX", onClick = onNavigateToPdfTools),
                ToolAction("pdf_excel", "PDF to Excel", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, stateLabel = "XLSX", onClick = onNavigateToPdfTools),
                ToolAction("pdf_ppt", "PDF to PPT", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, stateLabel = "PPTX", onClick = onNavigateToPdfTools),
                ToolAction("pdf_images", "PDF to Images", Icons.Default.Image, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToPdfTools),
                ToolAction("images_pdf", "Images to PDF", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToPdfTools),
                ToolAction("text_pdf", "Text to PDF", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToPdfTools),
                ToolAction("merge", "Merge PDF", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToPdfTools),
                ToolAction("compress", "Compress PDF", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToPdfTools)
            )
        ),
        ToolSection(
            title = "Edit",
            actions = listOf(
                ToolAction("auto_crop", "Auto Crop", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = true, onClick = onNavigateToPdfTools),
                ToolAction("erase_marks", "Erase Marks", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = true, onClick = onNavigateToCamera),
                ToolAction("watermark", "Watermark", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToFiles),
                ToolAction("sign", "Sign", Icons.Default.Description, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToFiles),
                ToolAction("reorder", "Reorder Pages", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToFiles),
                ToolAction("rotate", "Rotate Pages", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToFiles),
                ToolAction("delete_pages", "Delete Pages", Icons.Default.PictureAsPdf, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToFiles),
                ToolAction("duplicate", "Duplicate", Icons.Default.ContentCopy, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToFiles)
            )
        ),
        ToolSection(
            title = "Security",
            actions = listOf(
                ToolAction("vault", "Vault", Icons.Default.Lock, ToolTone.DANGER, enabled = true, onClick = onNavigateToVault),
                ToolAction("zip", "ZIP Backup", Icons.Default.FolderZip, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToZip),
                ToolAction("settings", "Settings", Icons.Default.Settings, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToSettings),
                ToolAction("files", "File Manager", Icons.Default.Folder, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToFiles)
            )
        ),
        ToolSection(
            title = "AI Tools",
            actions = listOf(
                ToolAction("ai_workspace", "AI Workspace", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = true, stateLabel = "Optional", onClick = onNavigateToAi),
                ToolAction("ocr_cleanup", "OCR Cleanup", Icons.Default.Translate, ToolTone.ACCENT, enabled = true, onClick = onNavigateToTranslate),
                ToolAction("smart_title", "Smart Title", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = true, stateLabel = "Optional", onClick = onNavigateToAi),
                ToolAction("deskew", "Deskew", Icons.Default.AutoAwesome, ToolTone.ACCENT, enabled = true, onClick = onNavigateToCamera)
            )
        ),
        ToolSection(
            title = "More tools",
            actions = listOf(
                ToolAction("scan_qr", "Scan QR", Icons.Default.QrCode2, ToolTone.ACCENT, enabled = true, onClick = onNavigateToQrScanner),
                ToolAction("generate_qr", "Generate QR", Icons.Default.QrCode2, ToolTone.ACCENT, enabled = true, onClick = onNavigateToQr),
                ToolAction("share", "Share", Icons.Default.Share, ToolTone.PRIMARY, enabled = true, onClick = onNavigateToFiles),
                ToolAction("print", "Print", Icons.Default.Print, ToolTone.NEUTRAL, enabled = true, onClick = onNavigateToFiles)
            )
        ),
        ToolSection(
            title = "Coming soon / Unsupported",
            actions = listOf(
                ToolAction("protect_pdf", "Protect PDF", Icons.Default.Lock, ToolTone.NEUTRAL, stateLabel = "Soon"),
                ToolAction("word_pdf", "Word to PDF", Icons.Default.PictureAsPdf, ToolTone.NEUTRAL, stateLabel = "Soon"),
                ToolAction("id_photo", "ID Photo", Icons.Default.CameraAlt, ToolTone.NEUTRAL, stateLabel = "Soon"),
                ToolAction("clean_bg", "Clean BG", Icons.Default.AutoAwesome, ToolTone.NEUTRAL, stateLabel = "Soon")
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
                    Text("Tools", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                    Text(
                        "Offline document tools",
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
        if (section.subtitle.isNotBlank()) {
            Text(section.subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
        }
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
        modifier = Modifier.fillMaxWidth().height(if (tool.stateLabel.isBlank()) 88.dp else 100.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 9.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val contentAlpha = if (tool.enabled) 1f else 0.45f
            Box(
                modifier = Modifier.size(34.dp).background(iconContainer, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(tool.icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(19.dp).alpha(contentAlpha))
            }
            Text(
                tool.title,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Clip,
                modifier = Modifier.alpha(contentAlpha)
            )
            if (tool.stateLabel.isNotBlank()) {
                Text(
                    tool.stateLabel,
                    color = if (tool.enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier.alpha(contentAlpha)
                )
            }
        }
    }
}
