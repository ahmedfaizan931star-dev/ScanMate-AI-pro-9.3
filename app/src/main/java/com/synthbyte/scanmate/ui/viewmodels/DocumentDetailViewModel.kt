package com.synthbyte.scanmate.ui.viewmodels

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synthbyte.scanmate.data.DocDao
import com.synthbyte.scanmate.data.DocumentWithPages
import com.synthbyte.scanmate.data.Page
import com.synthbyte.scanmate.utils.PdfExportQuality
import com.synthbyte.scanmate.utils.PdfPageSize
import com.synthbyte.scanmate.utils.DocumentIntelligence
import com.synthbyte.scanmate.utils.FileUtils
import com.synthbyte.scanmate.utils.OcrHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

sealed interface ExportState {
    data object Idle : ExportState
    data class Loading(val message: String? = null) : ExportState
    data class PdfSuccess(val file: File) : ExportState
    data class DocxSuccess(val file: File) : ExportState
    data class OcrSuccess(val text: String, val qualityLabel: String) : ExportState
    data class QualitySuccess(val report: DocumentIntelligence.QualityReport) : ExportState
    data class Error(val message: String) : ExportState
}

@HiltViewModel
class DocumentDetailViewModel @Inject constructor(
    private val dao: DocDao,
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle,
    private val appViewModel: AppErrorReporter
) : ViewModel() {
    private val docId: Long = savedStateHandle.get<Long>("docId")
        ?: savedStateHandle.get<String>("docId")?.toLongOrNull()
        ?: 0L

    val documentWithPages: Flow<DocumentWithPages?> = dao.getDocumentWithPages(docId)
    private val _exportState = MutableStateFlow<ExportState>(ExportState.Idle)
    val exportState: StateFlow<ExportState> = _exportState.asStateFlow()

    fun clearExportState() {
        _exportState.value = ExportState.Idle
    }

    private fun publishError(throwable: Throwable) {
        publishErrorMessage(throwable.localizedMessage ?: "Unknown error")
    }

    private fun publishErrorMessage(message: String) {
        _exportState.value = ExportState.Error(message)
        appViewModel.reportError(message)
    }

    fun exportDocx(dwp: DocumentWithPages?) = viewModelScope.launch {
        runCatching {
            if (dwp == null) {
                publishErrorMessage("No document available for DOCX export")
                return@runCatching
            }
            val text = dwp.document.ocrText.orEmpty().trim()
            if (text.isBlank()) {
                publishErrorMessage("Run OCR first, then export DOCX")
                return@runCatching
            }
            _exportState.value = ExportState.Loading("Preparing DOCX…")
            val file = withContext(Dispatchers.IO) {
                FileUtils.saveDocxText(
                    context = context,
                    text = text,
                    filename = dwp.document.title.ifBlank { "ScanMate_${dwp.document.id}_${System.currentTimeMillis()}" }
                )
            }
            if (file != null) {
                _exportState.value = ExportState.DocxSuccess(file)
            } else {
                publishErrorMessage("DOCX export failed")
            }
        }.onFailure { throwable -> publishError(throwable) }
    }

    fun exportPdf(
        dwp: DocumentWithPages?,
        quality: PdfExportQuality,
        filename: String,
        pageSize: PdfPageSize = PdfPageSize.A4
    ) = viewModelScope.launch {
        runCatching {
            if (dwp == null) return@runCatching
            val pages = dwp.pages.sortedBy { it.pageOrder }
            if (pages.isEmpty()) {
                publishErrorMessage("No pages found to export")
                return@runCatching
            }
            _exportState.value = ExportState.Loading("Preparing PDF…")
            val imagePaths = pages.map { it.imagePath }
            val ocrBlocksByPath: Map<String, List<Pair<android.graphics.Rect, String>>> = withContext(Dispatchers.IO) {
                imagePaths.associateWith { path ->
                    val pageFile = File(path)
                    if (pageFile.exists() && pageFile.length() > 0L) {
                        OcrHelper.extractBlocksFromFile(context, pageFile)
                    } else {
                        emptyList()
                    }
                }
            }
            _exportState.value = ExportState.Loading("Building PDF…")
            val pdfFile = withContext(Dispatchers.IO) {
                FileUtils.generatePdfFromPaths(
                    context = context,
                    imagePaths = imagePaths,
                    filename = filename.ifBlank { FileUtils.sanitizeFileBaseName(dwp.document.title) },
                    quality = quality,
                    pageSize = pageSize,
                    onProgress = { message -> _exportState.value = ExportState.Loading(message) },
                    ocrRectsByPath = ocrBlocksByPath
                )
            }
            if (pdfFile != null) {
                _exportState.value = ExportState.PdfSuccess(pdfFile)
            } else {
                publishErrorMessage("PDF export failed. Check that pages are valid images.")
            }
        }.onFailure { throwable -> publishError(throwable) }
    }


    fun exportProtectedPdf(dwp: DocumentWithPages?, password: String, filename: String) = viewModelScope.launch {
        if (dwp == null || dwp.pages.isEmpty()) {
            publishErrorMessage("No pages to export")
            return@launch
        }
        _exportState.value = ExportState.Loading("Encrypting PDF…")
        runCatching {
            val paths = dwp.pages.sortedBy { it.pageOrder }.map { it.imagePath }
            val file = FileUtils.generatePasswordProtectedPdf(context, paths, filename, password)
            _exportState.value = if (file != null) ExportState.PdfSuccess(file) else ExportState.Error("PDF encryption failed")
        }.onFailure { throwable -> publishError(throwable) }
    }

    fun scoreQuality(dwp: DocumentWithPages?) = viewModelScope.launch {
        if (dwp == null) return@launch
        val text = dwp.document.ocrText.orEmpty()
        val confidence = OcrHelper.buildStats(text).confidencePercent / 100f
        _exportState.value = ExportState.QualitySuccess(DocumentIntelligence.scoreDocumentQuality(text, confidence, dwp.pages.size))
    }

    fun extractOcr(dwp: DocumentWithPages?) = viewModelScope.launch {
        runCatching {
            if (dwp == null || dwp.pages.isEmpty()) {
                publishErrorMessage("No pages available for OCR")
                return@runCatching
            }
            _exportState.value = ExportState.Loading("Running OCR…")
            val pages = dwp.pages.sortedBy { it.pageOrder }
            val text = withContext(Dispatchers.IO) {
                pages.mapIndexedNotNull { index, page ->
                    val file = File(page.imagePath)
                    if (!file.exists() || file.length() == 0L) return@mapIndexedNotNull null
                    val fileResult = OcrHelper.extractTextFromFile(context, file)
                    val result = if (fileResult.startsWith("OCR failed", ignoreCase = true)) {
                        FileUtils.decodeSampledBitmap(file.absolutePath, 1800, 1800)?.let { bitmap ->
                            try {
                                OcrHelper.extractTextFromBitmap(bitmap)
                            } finally {
                                if (!bitmap.isRecycled) runCatching { bitmap.recycle() }
                            }
                        }.orEmpty()
                    } else {
                        fileResult
                    }
                    if (result.isBlank() || result.startsWith("OCR failed", ignoreCase = true)) null else "Page ${index + 1}:\n$result"
                }.joinToString(separator = "\n\n")
            }
            if (text.isBlank()) {
                publishErrorMessage("No readable text found")
                return@runCatching
            }
            val stats = OcrHelper.buildStats(text)
            updateOcrAndMetadata(stats.text, dwp.document.workspace.ifBlank { "Inbox" })
            _exportState.value = ExportState.OcrSuccess(stats.text, stats.qualityLabel)
        }.onFailure { throwable -> publishError(throwable) }
    }

    fun setFavorite(favorite: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        runCatching { dao.setFavorite(docId, favorite) }
            .onFailure { throwable -> publishError(throwable) }
    }

    fun setPinned(pinned: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        runCatching { dao.setPinned(docId, pinned) }
            .onFailure { throwable -> publishError(throwable) }
    }

    fun rename(title: String) = viewModelScope.launch(Dispatchers.IO) {
        runCatching { dao.renameDocument(docId, title.trim().ifBlank { "Untitled Scan" }) }
            .onFailure { throwable -> publishError(throwable) }
    }

    fun updateMeta(category: String, tags: String, workspace: String) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            dao.updateCategoryTags(
                id = docId,
                category = category.trim().ifBlank { "General" },
                tags = tags.trim(),
                workspace = workspace.trim().ifBlank { "Inbox" }
            )
        }.onFailure { throwable -> publishError(throwable) }
    }

    fun delete(onDeleted: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            dao.deleteDocumentById(docId)
            withContext(Dispatchers.Main) { onDeleted() }
        }.onFailure { throwable -> publishError(throwable) }
    }

    fun updateOcr(text: String) = viewModelScope.launch(Dispatchers.IO) {
        runCatching { dao.updateOcrText(docId, text) }
            .onFailure { throwable -> publishError(throwable) }
    }

    suspend fun updateOcrAndMetadata(text: String, currentWorkspace: String) = withContext(Dispatchers.IO) {
        dao.updateOcrText(docId, text)
        val suggested = DocumentIntelligence.suggestedCategory(text)
        val keywords = DocumentIntelligence.keywordList(text, 8).joinToString(", ")
        dao.updateCategoryTags(docId, suggested, keywords, currentWorkspace.ifBlank { "Inbox" })
    }

    fun updatePageImage(pageId: Long, imagePath: String) = viewModelScope.launch(Dispatchers.IO) {
        runCatching { dao.updatePageImage(pageId, imagePath) }
            .onFailure { throwable -> publishError(throwable) }
    }

    fun deletePage(pageId: Long, onDone: () -> Unit = {}) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            deletePageInternal(pageId)
            withContext(Dispatchers.Main) { onDone() }
        }.onFailure { throwable -> publishError(throwable) }
    }

    fun duplicatePage(page: Page, onDone: () -> Unit = {}) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            duplicatePageInternal(page)
            withContext(Dispatchers.Main) { onDone() }
        }.onFailure { throwable -> publishError(throwable) }
    }

    fun movePage(page: Page, direction: Int, onDone: () -> Unit = {}) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            movePageInternal(page, direction)
            withContext(Dispatchers.Main) { onDone() }
        }.onFailure { throwable -> publishError(throwable) }
    }

    fun reorderPage(pageId: Long, newOrder: Int) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            dao.updatePageOrder(pageId, newOrder)
            renumberPages()
        }.onFailure { throwable -> publishError(throwable) }
    }

    fun reorderPages(pages: List<Page>, onDone: () -> Unit = {}) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            pages.forEachIndexed { order, page -> dao.updatePageOrder(page.id, order) }
            withContext(Dispatchers.Main) { onDone() }
        }.onFailure { throwable -> publishError(throwable) }
    }

    private suspend fun deletePageInternal(pageId: Long) {
        dao.deletePageById(pageId)
        renumberPages()
    }

    private suspend fun duplicatePageInternal(page: Page) {
        val copied = FileUtils.duplicateImageFile(context, page.imagePath) ?: return
        val pages = dao.getPagesForDocumentOnce(docId).sortedBy { it.pageOrder }
        val insertIndex = pages.indexOfFirst { it.id == page.id }.takeIf { it >= 0 }?.plus(1) ?: pages.size
        pages.forEachIndexed { index, existing ->
            val order = if (index >= insertIndex) index + 1 else index
            dao.updatePageOrder(existing.id, order)
        }
        dao.insertPage(Page(documentId = docId, imagePath = copied.absolutePath, pageOrder = insertIndex))
        renumberPages()
    }

    private suspend fun movePageInternal(page: Page, direction: Int) {
        val pages = dao.getPagesForDocumentOnce(docId).sortedBy { it.pageOrder }.toMutableList()
        val index = pages.indexOfFirst { it.id == page.id }
        if (index < 0) return
        val newIndex = (index + direction).coerceIn(0, pages.lastIndex)
        if (index == newIndex) return
        val current = pages.removeAt(index)
        pages.add(newIndex, current)
        pages.forEachIndexed { order, existing -> dao.updatePageOrder(existing.id, order) }
    }

    private suspend fun renumberPages() {
        dao.getPagesForDocumentOnce(docId).sortedBy { it.pageOrder }.forEachIndexed { index, page ->
            dao.updatePageOrder(page.id, index)
        }
    }

    override fun onCleared() {
        OcrHelper.closeRecognizer()
        super.onCleared()
    }
}
