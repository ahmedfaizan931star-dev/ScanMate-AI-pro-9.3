package com.synthbyte.scanmate.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.TextSnippet
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.RotateLeft
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.synthbyte.scanmate.ui.viewmodels.PageEditorViewModel
import com.synthbyte.scanmate.data.Page
import com.synthbyte.scanmate.utils.FileUtils
import com.synthbyte.scanmate.utils.FilterType
import com.synthbyte.scanmate.utils.OcrHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageEditorScreen(docId: Long, pageId: Long, onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val viewModel: PageEditorViewModel = hiltViewModel()
    val page by viewModel.page.collectAsState(initial = null)
    val workingBitmap by viewModel.workingBitmap.collectAsState()
    val pageEditorError by viewModel.errorState.collectAsState()
    val viewModelProcessing by viewModel.isProcessing.collectAsState()
    val scope = rememberCoroutineScope()
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    var sourcePath by remember { mutableStateOf<String?>(null) }
    var selectedFilter by remember { mutableStateOf(FilterType.ORIGINAL) }
    var showCropDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showWatermarkDialog by remember { mutableStateOf(false) }
    var showNoteDialog by remember { mutableStateOf(false) }
    var showPerspectiveDialog by remember { mutableStateOf(false) }
    var watermarkText by remember { mutableStateOf("ScanMate AI Pro") }
    var noteText by remember { mutableStateOf("Reviewed") }
    var isProcessing by remember { mutableStateOf(false) }
    val isEditorProcessing = isProcessing || viewModelProcessing
    var changeVersion by remember { mutableIntStateOf(0) }
    val undoStack = remember { mutableStateListOf<android.graphics.Bitmap>() }
    val redoStack = remember { mutableStateListOf<android.graphics.Bitmap>() }

    fun pushUndoSnapshot() {
        workingBitmap?.let { bmp ->
            val maxSide = 900
            val scale = maxSide.toFloat() / maxOf(bmp.width, bmp.height).coerceAtLeast(1)
            val snapshot = if (scale >= 1f) {
                bmp.copy(android.graphics.Bitmap.Config.ARGB_8888, false)
            } else {
                android.graphics.Bitmap.createScaledBitmap(
                    bmp,
                    (bmp.width * scale).toInt().coerceAtLeast(1),
                    (bmp.height * scale).toInt().coerceAtLeast(1),
                    true
                )
            }
            if (undoStack.size >= 5) {
                undoStack.removeAt(0).also { runCatching { it.recycle() } }
            }
            undoStack.add(snapshot)
            redoStack.forEach { runCatching { it.recycle() } }
            redoStack.clear()
        }
    }

    fun clearUndoRedoStacks() {
        undoStack.forEach { runCatching { it.recycle() } }
        undoStack.clear()
        redoStack.forEach { runCatching { it.recycle() } }
        redoStack.clear()
    }

    DisposableEffect(Unit) {
        onDispose { clearUndoRedoStacks() }
    }

    LaunchedEffect(pageEditorError) {
        pageEditorError?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    val replaceLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            scope.launch {
                isProcessing = true
                val currentPage = page
                val file = if (currentPage != null) viewModel.replacePageImage(currentPage.id, uri) else null
                if (file != null && currentPage != null) {
                    sourcePath = null
                    viewModel.pushBitmap(null)
                    Toast.makeText(context, "Page replaced", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Could not replace this page", Toast.LENGTH_SHORT).show()
                }
                isProcessing = false
            }
        }
    }

    LaunchedEffect(page?.imagePath, changeVersion) {
        val path = page?.imagePath
        if (path != null && path != sourcePath) {
            sourcePath = path
            selectedFilter = FilterType.ORIGINAL
            clearUndoRedoStacks()
            viewModel.loadPageBitmap(path)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit page", fontWeight = FontWeight.ExtraBold) },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } },
                actions = {
                    IconButton(onClick = {
                        val path = page?.imagePath ?: return@IconButton
                        scope.launch {
                            isProcessing = true
                            viewModel.loadPageBitmap(path)
                            selectedFilter = FilterType.ORIGINAL
                            clearUndoRedoStacks()
                            isProcessing = false
                        }
                    }) { Icon(Icons.Default.Restore, "Reset") }
                    IconButton(onClick = {
                        val bitmap = workingBitmap ?: return@IconButton
                        val currentPage = page ?: return@IconButton
                        scope.launch {
                            isProcessing = true
                            val file = viewModel.saveEditedPage(currentPage.id, bitmap)
                            if (file != null) {
                                Toast.makeText(context, "Edited page saved", Toast.LENGTH_SHORT).show()
                                sourcePath = null
                                changeVersion++
                            } else {
                                Toast.makeText(context, "Could not save edited page", Toast.LENGTH_SHORT).show()
                            }
                            isProcessing = false
                        }
                    }) { Icon(Icons.Default.Save, "Save") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Make this page cleaner", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold)
                    Text("Crop, rotate, enhance, OCR and save without leaving the document.", color = MaterialTheme.colorScheme.onPrimaryContainer, style = MaterialTheme.typography.bodyMedium)
                }
            }
            if (isProcessing) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

            val bitmap = workingBitmap
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Edited page preview",
                        modifier = Modifier.fillMaxWidth().height(460.dp).padding(12.dp),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth().height(260.dp).padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (page == null) CircularProgressIndicator() else Icon(Icons.Default.ImageNotSupported, null, modifier = Modifier.size(42.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(if (page == null) "Loading page..." else "This page image could not be loaded")
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = {
                        val previous = if (undoStack.isNotEmpty()) undoStack.removeAt(undoStack.lastIndex) else null
                        if (previous != null) {
                            workingBitmap?.let { redoStack.add(it.copy(android.graphics.Bitmap.Config.ARGB_8888, false)) }
                            viewModel.pushBitmap(previous)
                            selectedFilter = FilterType.ORIGINAL
                        }
                    },
                    enabled = undoStack.isNotEmpty(),
                    modifier = Modifier.weight(1f)
                ) { Text("Undo") }
                OutlinedButton(
                    onClick = {
                        val next = if (redoStack.isNotEmpty()) redoStack.removeAt(redoStack.lastIndex) else null
                        if (next != null) {
                            workingBitmap?.let { undoStack.add(it.copy(android.graphics.Bitmap.Config.ARGB_8888, false)) }
                            viewModel.pushBitmap(next)
                        }
                    },
                    enabled = redoStack.isNotEmpty(),
                    modifier = Modifier.weight(1f)
                ) { Text("Redo") }
            }

            ToolSectionTitle("Layout and crop")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = {
                    pushUndoSnapshot()
                    workingBitmap?.let { viewModel.pushBitmap(FileUtils.rotateBitmap(it, -90f)) }
                }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.RotateLeft, null)
                    Text(" Rotate left")
                }
                OutlinedButton(onClick = {
                    pushUndoSnapshot()
                    workingBitmap?.let { viewModel.pushBitmap(FileUtils.rotateBitmap(it, 90f)) }
                }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.RotateRight, null)
                    Text(" Rotate right")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { showCropDialog = true }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Crop, null)
                    Text(" Manual crop")
                }
                OutlinedButton(onClick = {
                    scope.launch {
                        val current = workingBitmap ?: return@launch
                        pushUndoSnapshot()
                        isProcessing = true
                        viewModel.pushBitmap(withContext(Dispatchers.Default) { FileUtils.autoCropDocument(current) })
                        isProcessing = false
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.FilterAlt, null)
                    Text(" Auto crop")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { showPerspectiveDialog = true }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Crop, null)
                    Text(" Adjust corners")
                }
                OutlinedButton(onClick = {
                    val current = workingBitmap ?: return@OutlinedButton
                    scope.launch {
                        pushUndoSnapshot()
                        isProcessing = true
                        viewModel.pushBitmap(withContext(Dispatchers.Default) { FileUtils.autoCropDocument(current) })
                        viewModel.applyFilter(FilterType.ENHANCED_COLOR.name)
                        selectedFilter = FilterType.ENHANCED_COLOR
                        isProcessing = false
                    }
                }, modifier = Modifier.weight(1f)) {
                    Text("Enhance")
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { showWatermarkDialog = true }, modifier = Modifier.weight(1f)) {
                    Text("Watermark")
                }
                OutlinedButton(onClick = { showNoteDialog = true }, modifier = Modifier.weight(1f)) {
                    Text("Text note")
                }
            }

            ToolSectionTitle("Filters and cleanup")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                items(FilterType.entries, key = { it.name }) { filter ->
                    FilterChip(
                        selected = selectedFilter == filter,
                        onClick = {
                            workingBitmap ?: return@FilterChip
                            scope.launch {
                                pushUndoSnapshot()
                                isProcessing = true
                                viewModel.applyFilter(filter.name)
                                selectedFilter = filter
                                isProcessing = false
                            }
                        },
                        label = { Text(filter.label) }
                    )
                }
                item(key = "erase_marks_chip") {
                    FilterChip(
                        selected = false,
                        enabled = !isEditorProcessing,
                        onClick = {
                            pushUndoSnapshot()
                            viewModel.removeMarks()
                        },
                        label = { Text("Clean marks") },
                        leadingIcon = { Icon(Icons.Default.AutoAwesome, null, Modifier.size(16.dp)) }
                    )
                }
                item(key = "remove_shadow_chip") {
                    FilterChip(
                        selected = false,
                        enabled = !isEditorProcessing,
                        onClick = {
                            pushUndoSnapshot()
                            viewModel.removeShadow()
                        },
                        label = { Text("Reduce shadow") },
                        leadingIcon = { Icon(Icons.Default.WbSunny, null, Modifier.size(16.dp)) }
                    )
                }
                item(key = "deskew_chip") {
                    FilterChip(
                        selected = false,
                        enabled = !isEditorProcessing,
                        onClick = {
                            pushUndoSnapshot()
                            viewModel.deskew()
                        },
                        label = { Text("Deskew") },
                        leadingIcon = { Icon(Icons.Default.RotateRight, null, Modifier.size(16.dp)) }
                    )
                }
                item(key = "editor_tools_processing") {
                    if (isEditorProcessing) CircularProgressIndicator(Modifier.size(18.dp), strokeWidth = 2.dp)
                }
            }

            ToolSectionTitle("Page actions")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = {
                    replaceLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.SwapHoriz, null)
                    Text(" Replace")
                }
                OutlinedButton(onClick = {
                    val currentPage = page ?: return@OutlinedButton
                    scope.launch {
                        viewModel.duplicatePage(currentPage)
                        Toast.makeText(context, "Page duplicated", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.ContentCopy, null)
                    Text(" Duplicate")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = {
                    val currentPage = page ?: return@OutlinedButton
                    scope.launch {
                        viewModel.movePage(currentPage, -1)
                        Toast.makeText(context, "Page moved", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier.weight(1f)) { Text("Move up") }
                OutlinedButton(onClick = {
                    val currentPage = page ?: return@OutlinedButton
                    scope.launch {
                        viewModel.movePage(currentPage, 1)
                        Toast.makeText(context, "Page moved", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier.weight(1f)) { Text("Move down") }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = {
                    val currentPage = page ?: return@OutlinedButton
                    scope.launch {
                        isProcessing = true
                        val text = withContext(Dispatchers.IO) { OcrHelper.extractTextFromFile(context, java.io.File(currentPage.imagePath)) }
                        isProcessing = false
                        if (text.isBlank() || text.startsWith("OCR failed", ignoreCase = true)) {
                            Toast.makeText(context, "No readable text found on this page", Toast.LENGTH_SHORT).show()
                        } else {
                            clipboardManager.setPrimaryClip(ClipData.newPlainText("Page OCR", text))
                            viewModel.savePageOcr(currentPage, text)
                            Toast.makeText(context, "Page OCR copied and saved", Toast.LENGTH_SHORT).show()
                        }
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.TextSnippet, null)
                    Text(" OCR page")
                }
                OutlinedButton(onClick = { showDeleteDialog = true }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Delete, null)
                    Text(" Delete")
                }
            }
            AssistChip(onClick = {}, label = { Text("Save after crop, filter, text, watermark or rotation.") })
        }
    }

    if (showCropDialog) {
        ManualCropDialog(
            bitmap = workingBitmap,
            onDismiss = { showCropDialog = false },
            onApply = { left, top, right, bottom ->
                pushUndoSnapshot()
                workingBitmap?.let { viewModel.pushBitmap(FileUtils.cropBitmapNormalized(it, left, top, right, bottom)) }
                showCropDialog = false
            }
        )
    }

    if (showWatermarkDialog) {
        AlertDialog(
            onDismissRequest = { showWatermarkDialog = false },
            title = { Text("Add watermark") },
            text = {
                OutlinedTextField(
                    value = watermarkText,
                    onValueChange = { watermarkText = it },
                    label = { Text("Watermark text") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            },
            confirmButton = {
                Button(onClick = {
                    pushUndoSnapshot()
                    workingBitmap?.let { viewModel.pushBitmap(FileUtils.drawWatermarkOnBitmap(it, watermarkText)) }
                    showWatermarkDialog = false
                }) { Text("Apply") }
            },
            dismissButton = { TextButton(onClick = { showWatermarkDialog = false }) { Text("Cancel") } }
        )
    }

    if (showNoteDialog) {
        AlertDialog(
            onDismissRequest = { showNoteDialog = false },
            title = { Text("Add text note") },
            text = {
                OutlinedTextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    label = { Text("Note text") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2
                )
            },
            confirmButton = {
                Button(onClick = {
                    pushUndoSnapshot()
                    workingBitmap?.let { viewModel.pushBitmap(FileUtils.drawNoteStampOnBitmap(it, noteText)) }
                    showNoteDialog = false
                }) { Text("Apply") }
            },
            dismissButton = { TextButton(onClick = { showNoteDialog = false }) { Text("Cancel") } }
        )
    }

    if (showPerspectiveDialog) {
        PerspectiveDialog(
            bitmap = workingBitmap,
            onDismiss = { showPerspectiveDialog = false },
            onApply = { tlx, tly, trx, tryY, brx, bry, blx, bly ->
                pushUndoSnapshot()
                workingBitmap?.let { viewModel.pushBitmap(FileUtils.perspectiveCorrectBitmapNormalized(it, tlx, tly, trx, tryY, brx, bry, blx, bly)) }
                showPerspectiveDialog = false
            }
        )
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete page?") },
            text = { Text("This removes this page from the document. Other pages will be re-numbered safely.") },
            confirmButton = {
                Button(onClick = {
                    val currentPage = page ?: return@Button
                    scope.launch {
                        viewModel.deleteCurrentPage(currentPage.id)
                        Toast.makeText(context, "Page deleted", Toast.LENGTH_SHORT).show()
                        showDeleteDialog = false
                        onNavigateBack()
                    }
                }) { Text("Delete") }
            },
            dismissButton = { TextButton(onClick = { showDeleteDialog = false }) { Text("Cancel") } }
        )
    }
}

@Composable
private fun ToolSectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
}

@Composable
private fun ManualCropDialog(
    bitmap: android.graphics.Bitmap?,
    onDismiss: () -> Unit,
    onApply: (Float, Float, Float, Float) -> Unit
) {
    var left by remember { mutableFloatStateOf(0.04f) }
    var top by remember { mutableFloatStateOf(0.04f) }
    var right by remember { mutableFloatStateOf(0.04f) }
    var bottom by remember { mutableFloatStateOf(0.04f) }
    var selectedPreset by remember { mutableStateOf("balanced") }
    val presets = listOf(
        CropPreset("wide", "Wide", 0.02f),
        CropPreset("balanced", "Balanced", 0.05f),
        CropPreset("tight", "Tight", 0.09f)
    )
    val cropIsValid = remember(left, top, right, bottom) {
        val visibleWidth = 1f - left - right
        val visibleHeight = 1f - top - bottom
        left in 0f..0.45f && top in 0f..0.45f && right in 0f..0.45f && bottom in 0f..0.45f &&
            visibleWidth >= 0.30f && visibleHeight >= 0.30f
    }

    fun setPreset(preset: CropPreset) {
        selectedPreset = preset.id
        val value = preset.value
        left = value
        top = value
        right = value
        bottom = value
    }

    fun fitPage() {
        selectedPreset = "fit"
        left = 0f
        top = 0f
        right = 0f
        bottom = 0f
    }

    fun resetBalanced() {
        setPreset(CropPreset("balanced", "Balanced", 0.05f))
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .padding(12.dp)
                .widthIn(max = 640.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text("Crop page", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                            Text(
                                "Use a safe crop preset or fine tune each edge. The preview keeps extra margin so document content is not cut.",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    item {
                        CropPreviewBox(
                            bitmap = bitmap,
                            left = left,
                            top = top,
                            right = right,
                            bottom = bottom,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 260.dp)
                        )
                    }
                    item {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                            presets.forEach { preset ->
                                FilterChip(
                                    selected = selectedPreset == preset.id,
                                    onClick = { setPreset(preset) },
                                    label = { Text(preset.label, softWrap = false, maxLines = 1) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                    item { CropSlider("Left edge", left) { left = it; selectedPreset = "custom" } }
                    item { CropSlider("Top edge", top) { top = it; selectedPreset = "custom" } }
                    item { CropSlider("Right edge", right) { right = it; selectedPreset = "custom" } }
                    item { CropSlider("Bottom edge", bottom) { bottom = it; selectedPreset = "custom" } }
                    if (!cropIsValid) {
                        item {
                            Text(
                                "Crop is too tight. Keep at least 30% of page width and height visible.",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextButton(onClick = ::fitPage) { Text("Fit") }
                        TextButton(onClick = ::resetBalanced) { Text("Reset") }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextButton(onClick = onDismiss) { Text("Cancel") }
                        Button(
                            enabled = cropIsValid,
                            onClick = { onApply(left, top, right, bottom) }
                        ) { Text("Apply crop") }
                    }
                }
            }
        }
    }
}

@Composable
private fun CropPreviewBox(
    bitmap: android.graphics.Bitmap?,
    left: Float,
    top: Float,
    right: Float,
    bottom: Float,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(18.dp))
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        val boxWidth = constraints.maxWidth.toFloat().coerceAtLeast(1f)
        val boxHeight = constraints.maxHeight.toFloat().coerceAtLeast(1f)
        val bitmapAspect = bitmap?.let { it.width.toFloat() / it.height.toFloat().coerceAtLeast(1f) } ?: 0.72f
        val boxAspect = boxWidth / boxHeight
        val imageWidth = if (bitmapAspect > boxAspect) boxWidth else boxHeight * bitmapAspect
        val imageHeight = if (bitmapAspect > boxAspect) boxWidth / bitmapAspect else boxHeight
        val imageLeft = (boxWidth - imageWidth) / 2f
        val imageTop = (boxHeight - imageHeight) / 2f

        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Crop preview",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
        val guideColor = MaterialTheme.colorScheme.primary
        val maskColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.68f)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cropLeft = imageLeft + imageWidth * left.coerceIn(0f, 0.45f)
            val cropTop = imageTop + imageHeight * top.coerceIn(0f, 0.45f)
            val cropRight = imageLeft + imageWidth * (1f - right.coerceIn(0f, 0.45f))
            val cropBottom = imageTop + imageHeight * (1f - bottom.coerceIn(0f, 0.45f))
            drawRect(maskColor, topLeft = Offset(imageLeft, imageTop), size = androidx.compose.ui.geometry.Size(imageWidth, (cropTop - imageTop).coerceAtLeast(0f)))
            drawRect(maskColor, topLeft = Offset(imageLeft, cropBottom), size = androidx.compose.ui.geometry.Size(imageWidth, (imageTop + imageHeight - cropBottom).coerceAtLeast(0f)))
            drawRect(maskColor, topLeft = Offset(imageLeft, cropTop), size = androidx.compose.ui.geometry.Size((cropLeft - imageLeft).coerceAtLeast(0f), (cropBottom - cropTop).coerceAtLeast(0f)))
            drawRect(maskColor, topLeft = Offset(cropRight, cropTop), size = androidx.compose.ui.geometry.Size((imageLeft + imageWidth - cropRight).coerceAtLeast(0f), (cropBottom - cropTop).coerceAtLeast(0f)))
            drawLine(guideColor, Offset(cropLeft, cropTop), Offset(cropRight, cropTop), strokeWidth = 5f)
            drawLine(guideColor, Offset(cropRight, cropTop), Offset(cropRight, cropBottom), strokeWidth = 5f)
            drawLine(guideColor, Offset(cropRight, cropBottom), Offset(cropLeft, cropBottom), strokeWidth = 5f)
            drawLine(guideColor, Offset(cropLeft, cropBottom), Offset(cropLeft, cropTop), strokeWidth = 5f)
        }
    }
}

private data class CropPreset(val id: String, val label: String, val value: Float)

@Composable
private fun PerspectiveDialog(
    bitmap: android.graphics.Bitmap?,
    onDismiss: () -> Unit,
    onApply: (Float, Float, Float, Float, Float, Float, Float, Float) -> Unit
) {
    val defaultCorners = listOf(
        Offset(0.06f, 0.06f),
        Offset(0.94f, 0.06f),
        Offset(0.94f, 0.94f),
        Offset(0.06f, 0.94f)
    )
    val fitCorners = listOf(
        Offset(0.02f, 0.02f),
        Offset(0.98f, 0.02f),
        Offset(0.98f, 0.98f),
        Offset(0.02f, 0.98f)
    )
    var corners by remember { mutableStateOf(defaultCorners) }
    var activeCorner by remember { mutableStateOf<Int?>(null) }
    val isValid = remember(corners) { cornersAreValid(corners) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.94f)
                .padding(10.dp)
                .widthIn(max = 720.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Drag page corners", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Place each large handle on the real document corner. Handles stay inside bounds and cannot cross.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .heightIn(min = 320.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(18.dp))
                        .padding(10.dp)
                ) {
                    val boxWidth = constraints.maxWidth.toFloat().coerceAtLeast(1f)
                    val boxHeight = constraints.maxHeight.toFloat().coerceAtLeast(1f)
                    val bitmapAspect = bitmap?.let { it.width.toFloat() / it.height.toFloat().coerceAtLeast(1f) } ?: 0.72f
                    val boxAspect = boxWidth / boxHeight
                    val imageWidth = if (bitmapAspect > boxAspect) boxWidth else boxHeight * bitmapAspect
                    val imageHeight = if (bitmapAspect > boxAspect) boxWidth / bitmapAspect else boxHeight
                    val imageLeft = (boxWidth - imageWidth) / 2f
                    val imageTop = (boxHeight - imageHeight) / 2f

                    bitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Perspective preview",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                    val handleColor = MaterialTheme.colorScheme.primary
                    val handleBorder = MaterialTheme.colorScheme.surface
                    val guideColor = if (isValid) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val points = corners.map { point ->
                            Offset(imageLeft + point.x * imageWidth, imageTop + point.y * imageHeight)
                        }
                        drawLine(guideColor, points[0], points[1], strokeWidth = 5f)
                        drawLine(guideColor, points[1], points[2], strokeWidth = 5f)
                        drawLine(guideColor, points[2], points[3], strokeWidth = 5f)
                        drawLine(guideColor, points[3], points[0], strokeWidth = 5f)
                        points.forEachIndexed { index, point ->
                            val radius = if (activeCorner == index) 28f else 23f
                            drawCircle(handleBorder, radius = radius + 7f, center = point)
                            drawCircle(handleColor, radius = radius, center = point)
                            drawCircle(handleBorder, radius = radius, center = point, style = Stroke(width = 4f))
                        }
                    }
                    corners.forEachIndexed { index, point ->
                        CornerHandle(
                            position = point,
                            imageLeft = imageLeft,
                            imageTop = imageTop,
                            imageWidth = imageWidth,
                            imageHeight = imageHeight,
                            onDragStateChange = { dragging -> activeCorner = if (dragging) index else null }
                        ) { next ->
                            corners = corners.updateCorner(index, next)
                        }
                    }
                }
                Text(
                    text = if (isValid) "Tip: drag slowly near each corner for better OCR/export alignment." else "Move the handles apart so the page outline does not cross or become too small.",
                    color = if (isValid) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextButton(onClick = { corners = defaultCorners }) { Text("Reset") }
                        TextButton(onClick = { corners = fitCorners }) { Text("Fit page") }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextButton(onClick = onDismiss) { Text("Cancel") }
                        Button(
                            enabled = isValid && bitmap != null,
                            onClick = {
                                val topLeft = corners[0]
                                val topRight = corners[1]
                                val bottomRight = corners[2]
                                val bottomLeft = corners[3]
                                onApply(
                                    topLeft.x,
                                    topLeft.y,
                                    1f - topRight.x,
                                    topRight.y,
                                    1f - bottomRight.x,
                                    1f - bottomRight.y,
                                    bottomLeft.x,
                                    1f - bottomLeft.y
                                )
                            }
                        ) { Text("Apply") }
                    }
                }
            }
        }
    }
}

@Composable
private fun CornerHandle(
    position: Offset,
    imageLeft: Float,
    imageTop: Float,
    imageWidth: Float,
    imageHeight: Float,
    onDragStateChange: (Boolean) -> Unit = {},
    onMove: (Offset) -> Unit
) {
    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    (imageLeft + position.x * imageWidth).roundToInt() - 34,
                    (imageTop + position.y * imageHeight).roundToInt() - 34
                )
            }
            .size(68.dp)
            .pointerInput(position) {
                detectDragGestures(
                    onDragStart = { onDragStateChange(true) },
                    onDragCancel = { onDragStateChange(false) },
                    onDragEnd = { onDragStateChange(false) }
                ) { change, dragAmount ->
                    change.consume()
                    val next = Offset(
                        (position.x + dragAmount.x / imageWidth.coerceAtLeast(1f)).coerceIn(0.02f, 0.98f),
                        (position.y + dragAmount.y / imageHeight.coerceAtLeast(1f)).coerceIn(0.02f, 0.98f)
                    )
                    onMove(next)
                }
            }
    )
}

@Composable
private fun CropSlider(label: String, value: Float, onChange: (Float) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(label, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold)
            Text("${(value * 100f).roundToInt()}%", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Slider(value = value, onValueChange = onChange, valueRange = 0f..0.22f)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Keep more", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text("Trim more", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

private fun List<Offset>.updateCorner(index: Int, next: Offset): List<Offset> {
    if (size != 4) return this
    val minGap = 0.08f
    fun Float.inRange(min: Float, max: Float): Float {
        val low = min.coerceIn(0.02f, 0.98f)
        val high = max.coerceIn(0.02f, 0.98f)
        return if (low <= high) coerceIn(low, high) else ((low + high) / 2f).coerceIn(0.02f, 0.98f)
    }
    val tl = this[0]
    val tr = this[1]
    val br = this[2]
    val bl = this[3]
    val clamped = when (index) {
        0 -> Offset(next.x.inRange(0.02f, minOf(tr.x, br.x) - minGap), next.y.inRange(0.02f, minOf(bl.y, br.y) - minGap))
        1 -> Offset(next.x.inRange(maxOf(tl.x, bl.x) + minGap, 0.98f), next.y.inRange(0.02f, minOf(bl.y, br.y) - minGap))
        2 -> Offset(next.x.inRange(maxOf(tl.x, bl.x) + minGap, 0.98f), next.y.inRange(maxOf(tl.y, tr.y) + minGap, 0.98f))
        3 -> Offset(next.x.inRange(0.02f, minOf(tr.x, br.x) - minGap), next.y.inRange(maxOf(tl.y, tr.y) + minGap, 0.98f))
        else -> next
    }
    return toMutableList().also { it[index] = clamped }
}

private fun cornersAreValid(corners: List<Offset>): Boolean {
    if (corners.size != 4) return false
    if (corners.any { it.x !in 0f..1f || it.y !in 0f..1f }) return false
    if (segmentsIntersect(corners[0], corners[1], corners[2], corners[3])) return false
    if (segmentsIntersect(corners[1], corners[2], corners[3], corners[0])) return false
    val area = abs(
        corners.indices.sumOf { i ->
            val a = corners[i]
            val b = corners[(i + 1) % corners.size]
            (a.x * b.y - b.x * a.y).toDouble()
        }.toFloat()
    ) / 2f
    val topWidth = distance(corners[0], corners[1])
    val bottomWidth = distance(corners[3], corners[2])
    val leftHeight = distance(corners[0], corners[3])
    val rightHeight = distance(corners[1], corners[2])
    val aspect = maxOf(topWidth, bottomWidth) / maxOf(leftHeight, rightHeight, 0.001f)
    return area >= 0.12f &&
        aspect in 0.22f..4.8f &&
        topWidth >= 0.16f && bottomWidth >= 0.16f && leftHeight >= 0.16f && rightHeight >= 0.16f &&
        corners[0].x < corners[1].x &&
        corners[3].x < corners[2].x &&
        corners[0].y < corners[3].y &&
        corners[1].y < corners[2].y
}

private fun distance(a: Offset, b: Offset): Float {
    val dx = a.x - b.x
    val dy = a.y - b.y
    return kotlin.math.sqrt(dx * dx + dy * dy)
}

private fun segmentsIntersect(a: Offset, b: Offset, c: Offset, d: Offset): Boolean {
    fun orientation(p: Offset, q: Offset, r: Offset): Float {
        return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y)
    }
    val o1 = orientation(a, b, c)
    val o2 = orientation(a, b, d)
    val o3 = orientation(c, d, a)
    val o4 = orientation(c, d, b)
    return o1 * o2 < 0f && o3 * o4 < 0f
}
