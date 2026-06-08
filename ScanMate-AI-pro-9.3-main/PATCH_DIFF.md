diff -ruN scanmate_orig/app/build.gradle.kts scanmate_work/app/build.gradle.kts
--- scanmate_orig/app/build.gradle.kts	2026-05-29 10:03:26.000000000 +0000
+++ scanmate_work/app/build.gradle.kts	2026-05-29 10:54:31.676239925 +0000
@@ -128,7 +128,7 @@
     implementation(libs.converter.moshi)
     implementation(libs.kotlinx.coroutines.android)
     implementation(libs.kotlinx.coroutines.core)
-    implementation(libs.logging.interceptor)
+    debugImplementation(libs.logging.interceptor)
     implementation(libs.moshi.kotlin)
     implementation(libs.okhttp)
     implementation(libs.retrofit)
diff -ruN scanmate_orig/app/proguard-rules.pro scanmate_work/app/proguard-rules.pro
--- scanmate_orig/app/proguard-rules.pro	2026-05-29 10:09:37.000000000 +0000
+++ scanmate_work/app/proguard-rules.pro	2026-05-29 10:56:04.882203277 +0000
@@ -30,3 +30,8 @@
 -keep class com.synthbyte.scanmate.utils.** { *; }
 -keep class com.synthbyte.scanmate.util.** { *; }
 -keep class com.synthbyte.scanmate.widgets.** { *; }
+
+-assumenosideeffects class okhttp3.logging.HttpLoggingInterceptor {
+    void log(java.lang.String);
+}
+-dontwarn okhttp3.logging.**
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/MainActivity.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/MainActivity.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/MainActivity.kt	2026-05-29 10:08:09.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/MainActivity.kt	2026-05-29 10:54:23.267946342 +0000
@@ -29,6 +29,7 @@
 import androidx.navigation.compose.rememberNavController
 import com.synthbyte.scanmate.data.SettingsRepository
 import com.synthbyte.scanmate.data.ThemeMode
+import com.synthbyte.scanmate.ui.components.GlobalErrorBoundary
 import com.synthbyte.scanmate.ui.navigation.Routes
 import com.synthbyte.scanmate.ui.screens.AiScreen
 import com.synthbyte.scanmate.ui.screens.CameraScreen
@@ -106,6 +107,7 @@
                         }
 
                         val startDestination = if (onboardingComplete) Routes.HOME else Routes.ONBOARDING
+                        GlobalErrorBoundary {
                         NavHost(navController = navController, startDestination = startDestination) {
                         composable(Routes.ONBOARDING) {
                             OnboardingScreen(
@@ -225,6 +227,7 @@
                             VaultScreen(onNavigateBack = { navController.popBackStack() })
                         }
                     }
+                    }
                 }
                 }
             }
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/data/DocDao.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/data/DocDao.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/data/DocDao.kt	2026-05-29 10:03:26.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/data/DocDao.kt	2026-05-29 10:54:03.176532735 +0000
@@ -136,7 +136,7 @@
     suspend fun insertQrHistory(item: QrHistory): Long
 
     @Query("SELECT * FROM qr_history ORDER BY timestamp DESC LIMIT :limit")
-    fun getQrHistory(limit: Int = 25): Flow<List<QrHistory>>
+    fun getQrHistory(limit: Int = 50): Flow<List<QrHistory>>
 
     @Query("DELETE FROM qr_history")
     suspend fun clearQrHistory()
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/domain/GeminiApiService.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/domain/GeminiApiService.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/domain/GeminiApiService.kt	2026-05-29 10:03:26.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/domain/GeminiApiService.kt	2026-05-29 10:56:00.208601317 +0000
@@ -1,5 +1,6 @@
 package com.synthbyte.scanmate.domain
 
+import com.synthbyte.scanmate.BuildConfig
 import com.squareup.moshi.Moshi
 import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
 import kotlinx.coroutines.Dispatchers
@@ -82,8 +83,23 @@
         .connectTimeout(30, TimeUnit.SECONDS)
         .readTimeout(60, TimeUnit.SECONDS)
         .writeTimeout(60, TimeUnit.SECONDS)
+        .apply {
+            if (BuildConfig.DEBUG) addDebugLoggingInterceptor()
+        }
         .build()
 
+
+    private fun OkHttpClient.Builder.addDebugLoggingInterceptor() {
+        runCatching {
+            val interceptorClass = Class.forName("okhttp3.logging.HttpLoggingInterceptor")
+            val levelClass = Class.forName("okhttp3.logging.HttpLoggingInterceptor\$Level")
+            val interceptor = interceptorClass.getDeclaredConstructor().newInstance()
+            val basicLevel = levelClass.enumConstants.firstOrNull { (it as? Enum<*>)?.name == "BASIC" } ?: return@runCatching
+            interceptorClass.getMethod("setLevel", levelClass).invoke(interceptor, basicLevel)
+            addInterceptor(interceptor as okhttp3.Interceptor)
+        }
+    }
+
     val moshi: Moshi = Moshi.Builder()
         .add(KotlinJsonAdapterFactory())
         .build()
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/components/GlobalErrorBoundary.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/components/GlobalErrorBoundary.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/components/GlobalErrorBoundary.kt	1970-01-01 00:00:00.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/components/GlobalErrorBoundary.kt	2026-05-29 10:54:22.059676454 +0000
@@ -0,0 +1,60 @@
+package com.synthbyte.scanmate.ui.components
+
+import androidx.compose.foundation.layout.Arrangement
+import androidx.compose.foundation.layout.Column
+import androidx.compose.foundation.layout.Spacer
+import androidx.compose.foundation.layout.fillMaxSize
+import androidx.compose.foundation.layout.height
+import androidx.compose.foundation.layout.padding
+import androidx.compose.material.icons.Icons
+import androidx.compose.material.icons.filled.ErrorOutline
+import androidx.compose.material3.Button
+import androidx.compose.material3.Icon
+import androidx.compose.material3.MaterialTheme
+import androidx.compose.material3.Text
+import androidx.compose.runtime.Composable
+import androidx.compose.runtime.getValue
+import androidx.compose.runtime.mutableIntStateOf
+import androidx.compose.runtime.mutableStateOf
+import androidx.compose.runtime.remember
+import androidx.compose.runtime.setValue
+import androidx.compose.ui.Alignment
+import androidx.compose.ui.Modifier
+import androidx.compose.ui.text.font.FontWeight
+import androidx.compose.ui.text.style.TextAlign
+import androidx.compose.ui.unit.dp
+
+@Composable
+fun GlobalErrorBoundary(content: @Composable () -> Unit) {
+    var error by remember { mutableStateOf<Throwable?>(null) }
+    var retryKey by remember { mutableIntStateOf(0) }
+    val activeError = error
+    if (activeError == null) {
+        try {
+            androidx.compose.runtime.key(retryKey) { content() }
+        } catch (throwable: Throwable) {
+            error = throwable
+        }
+    } else {
+        Column(
+            modifier = Modifier.fillMaxSize().padding(24.dp),
+            horizontalAlignment = Alignment.CenterHorizontally,
+            verticalArrangement = Arrangement.Center
+        ) {
+            Icon(Icons.Default.ErrorOutline, contentDescription = null, tint = MaterialTheme.colorScheme.error)
+            Spacer(Modifier.height(12.dp))
+            Text("Something went wrong", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold)
+            Spacer(Modifier.height(6.dp))
+            Text(
+                activeError.localizedMessage ?: activeError::class.java.simpleName,
+                color = MaterialTheme.colorScheme.onSurfaceVariant,
+                textAlign = TextAlign.Center
+            )
+            Spacer(Modifier.height(16.dp))
+            Button(onClick = {
+                error = null
+                retryKey++
+            }) { Text("Try again") }
+        }
+    }
+}
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/CameraScreen.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/CameraScreen.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/CameraScreen.kt	2026-05-29 10:07:30.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/CameraScreen.kt	2026-05-29 10:53:03.085182841 +0000
@@ -94,6 +94,7 @@
 import com.google.accompanist.permissions.isGranted
 import com.google.accompanist.permissions.rememberPermissionState
 import coil.compose.AsyncImage
+import coil.request.ImageRequest
 import com.synthbyte.scanmate.data.SettingsRepository
 import com.synthbyte.scanmate.ui.components.DocumentOverlay
 import com.synthbyte.scanmate.ui.viewmodels.rememberCameraViewModel
@@ -499,6 +500,7 @@
 
 @Composable
 private fun CapturedThumbnailStrip(capturedImages: List<File>) {
+    val ctx = LocalContext.current
     AnimatedVisibility(visible = capturedImages.isNotEmpty()) {
         Row(
             modifier = Modifier.fillMaxWidth().animateContentSize(),
@@ -523,8 +525,16 @@
             ) {
                 itemsIndexed(capturedImages, key = { index, file -> "${file.absolutePath}_$index" }) { index, file ->
                     Box {
+                        val imageRequest = remember(file.absolutePath) {
+                            ImageRequest.Builder(ctx)
+                                .data(file)
+                                .diskCacheKey(file.absolutePath)
+                                .memoryCacheKey(file.absolutePath)
+                                .crossfade(true)
+                                .build()
+                        }
                         AsyncImage(
-                            model = file,
+                            model = imageRequest,
                             contentDescription = "Captured page ${index + 1}",
                             modifier = Modifier
                                 .width(52.dp)
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/DocumentDetailScreen.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/DocumentDetailScreen.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/DocumentDetailScreen.kt	2026-05-29 10:07:01.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/DocumentDetailScreen.kt	2026-05-29 10:52:24.970570722 +0000
@@ -7,6 +7,7 @@
 import androidx.compose.foundation.Image
 import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
 import androidx.compose.foundation.layout.Arrangement
+import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.Spacer
@@ -89,15 +90,11 @@
 import com.synthbyte.scanmate.utils.DocumentAnalyticsEngine
 import com.synthbyte.scanmate.utils.EncryptedVaultUtils
 import com.synthbyte.scanmate.utils.FileUtils
-import com.synthbyte.scanmate.utils.OcrHelper
 import com.synthbyte.scanmate.utils.PdfExportQuality
 import com.synthbyte.scanmate.utils.PdfPageSize
-import com.synthbyte.scanmate.ui.viewmodels.DocumentDetailViewModel
+import com.synthbyte.scanmate.ui.viewmodels.ExportState
 import com.synthbyte.scanmate.ui.viewmodels.rememberDocumentDetailViewModel
-import kotlinx.coroutines.CoroutineScope
-import kotlinx.coroutines.Dispatchers
 import kotlinx.coroutines.launch
-import kotlinx.coroutines.withContext
 import java.io.File
 import kotlin.math.roundToInt
 
@@ -113,6 +110,7 @@
     val viewModel = rememberDocumentDetailViewModel(docId)
     val documentWithPages by viewModel.documentWithPages.collectAsState(initial = null)
     val coroutineScope = rememberCoroutineScope()
+    val exportState by viewModel.exportState.collectAsState()
     var isProcessing by remember { mutableStateOf(false) }
     var showDeleteDialog by remember { mutableStateOf(false) }
     var showRenameDialog by remember { mutableStateOf(false) }
@@ -139,6 +137,47 @@
         workspace = documentWithPages?.document?.workspace ?: "Inbox"
     }
 
+
+    LaunchedEffect(exportState) {
+        when (val state = exportState) {
+            ExportState.Idle -> {
+                isProcessing = false
+                exportProgress = null
+            }
+            is ExportState.Loading -> {
+                isProcessing = true
+                exportProgress = state.message
+            }
+            is ExportState.PdfSuccess -> {
+                isProcessing = false
+                exportProgress = null
+                exportedPdf = state.file
+                Toast.makeText(context, "PDF exported", Toast.LENGTH_SHORT).show()
+                viewModel.clearExportState()
+            }
+            is ExportState.DocxSuccess -> {
+                isProcessing = false
+                exportProgress = null
+                exportedDocx = state.file
+                Toast.makeText(context, "DOCX exported", Toast.LENGTH_SHORT).show()
+                viewModel.clearExportState()
+            }
+            is ExportState.OcrSuccess -> {
+                isProcessing = false
+                exportProgress = null
+                clipboardManager.setPrimaryClip(ClipData.newPlainText("Extracted Text", state.text))
+                Toast.makeText(context, "OCR completed · ${state.qualityLabel}", Toast.LENGTH_SHORT).show()
+                viewModel.clearExportState()
+            }
+            is ExportState.Error -> {
+                isProcessing = false
+                exportProgress = null
+                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
+                viewModel.clearExportState()
+            }
+        }
+    }
+
     Scaffold(
         topBar = {
             TopAppBar(
@@ -155,8 +194,9 @@
                         }
                     }
                     IconButton(onClick = { showExportDialog = true }) { Icon(Icons.Default.PictureAsPdf, "Export PDF") }
-                    IconButton(onClick = { topBarMenuExpanded = true }) { Icon(Icons.Default.MoreVert, "More") }
-                    DropdownMenu(expanded = topBarMenuExpanded, onDismissRequest = { topBarMenuExpanded = false }) {
+                    Box {
+                        IconButton(onClick = { topBarMenuExpanded = true }) { Icon(Icons.Default.MoreVert, "More") }
+                        DropdownMenu(expanded = topBarMenuExpanded, onDismissRequest = { topBarMenuExpanded = false }) {
                         DropdownMenuItem(
                             text = { Text("Rename") },
                             leadingIcon = { Icon(Icons.Default.DriveFileRenameOutline, null) },
@@ -170,7 +210,7 @@
                             leadingIcon = { Icon(Icons.Default.TextSnippet, null) },
                             onClick = {
                                 topBarMenuExpanded = false
-                                extractOcr(coroutineScope, documentWithPages, context, viewModel, clipboardManager) { isProcessing = it }
+                                viewModel.extractOcr(documentWithPages)
                             }
                         )
                         documentWithPages?.let { dwp ->
@@ -191,6 +231,7 @@
                                 showDeleteDialog = true
                             }
                         )
+                        }
                     }
                 }
             )
@@ -219,7 +260,7 @@
                         }
                     },
                     onExport = { showExportDialog = true },
-                    onExportDocx = { exportDocx(coroutineScope, documentWithPages, context, onDocxReady = { exportedDocx = it }) { isProcessing = it } },
+                    onExportDocx = { viewModel.exportDocx(documentWithPages) },
                     onSignature = { onNavigateToSignature(docId) },
                     onMeta = { showMetaDialog = true }
                 )
@@ -344,7 +385,7 @@
                     PdfExportQuality.entries.forEach { quality ->
                         OutlinedButton(onClick = {
                             showExportDialog = false
-                            exportPdf(coroutineScope, documentWithPages, context, quality, exportName, selectedPageSize, onPdfReady = { exportedPdf = it }, onProgress = { exportProgress = it }) { isProcessing = it }
+                            viewModel.exportPdf(documentWithPages, quality, exportName, selectedPageSize)
                         }, modifier = Modifier.fillMaxWidth()) {
                             Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                                 Text(quality.label, fontWeight = FontWeight.Bold)
@@ -505,6 +546,7 @@
 private fun OcrCard(dwp: DocumentWithPages, clipboardManager: ClipboardManager, context: Context, onDocxReady: (File) -> Unit) {
     val text = dwp.document.ocrText
     val coroutineScope = rememberCoroutineScope()
+    val exportState by viewModel.exportState.collectAsState()
     if (!text.isNullOrBlank()) {
         Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(20.dp)) {
             Column(modifier = Modifier.padding(16.dp)) {
@@ -664,135 +706,3 @@
         }
     }
 }
-
-
-private fun exportDocx(
-    coroutineScope: CoroutineScope,
-    dwp: DocumentWithPages?,
-    context: Context,
-    onDocxReady: (File) -> Unit,
-    setProcessing: (Boolean) -> Unit
-) {
-    if (dwp == null) return
-    val text = dwp.document.ocrText.orEmpty().trim()
-    if (text.isBlank()) {
-        Toast.makeText(context, "Run OCR first, then export DOCX", Toast.LENGTH_SHORT).show()
-        return
-    }
-    coroutineScope.launch {
-        setProcessing(true)
-        val file = withContext(Dispatchers.IO) {
-            FileUtils.saveDocxText(
-                context = context,
-                text = text,
-                filename = dwp.document.title.ifBlank { "ScanMate_${dwp.document.id}_${System.currentTimeMillis()}" }
-            )
-        }
-        setProcessing(false)
-        if (file != null) {
-            Toast.makeText(context, "DOCX exported", Toast.LENGTH_SHORT).show()
-            onDocxReady(file)
-        } else {
-            Toast.makeText(context, "DOCX export failed", Toast.LENGTH_SHORT).show()
-        }
-    }
-}
-
-private fun exportPdf(
-    coroutineScope: CoroutineScope,
-    dwp: DocumentWithPages?,
-    context: Context,
-    quality: PdfExportQuality,
-    filename: String,
-    pageSize: PdfPageSize = PdfPageSize.A4,
-    onPdfReady: (File) -> Unit,
-    onProgress: (String?) -> Unit = {},
-    setProcessing: (Boolean) -> Unit
-) {
-    if (dwp == null) return
-    val pages = dwp.pages.sortedBy { it.pageOrder }
-    if (pages.isEmpty()) {
-        Toast.makeText(context, "No pages found to export", Toast.LENGTH_SHORT).show()
-        return
-    }
-    coroutineScope.launch {
-        setProcessing(true)
-        onProgress("Preparing PDF…")
-        val imagePaths = pages.map { it.imagePath }
-        val ocrRectsByPath = withContext(Dispatchers.IO) {
-            imagePaths.associateWith { path ->
-                val pageFile = File(path)
-                if (pageFile.exists() && pageFile.length() > 0L) {
-                    OcrHelper.extractBlocksFromFile(context, pageFile)
-                } else {
-                    emptyList()
-                }
-            }
-        }
-        val pdfFile = withContext(Dispatchers.IO) {
-            FileUtils.generatePdfFromPaths(
-                context = context,
-                imagePaths = imagePaths,
-                filename = filename.ifBlank { FileUtils.sanitizeFileBaseName(dwp.document.title) },
-                quality = quality,
-                pageSize = pageSize,
-                onProgress = onProgress,
-                ocrRectsByPath = ocrRectsByPath
-            )
-        }
-        onProgress(null)
-        setProcessing(false)
-        if (pdfFile != null) {
-            Toast.makeText(context, "PDF exported", Toast.LENGTH_SHORT).show()
-            onPdfReady(pdfFile)
-        } else {
-            Toast.makeText(context, "PDF export failed. Check that pages are valid images.", Toast.LENGTH_SHORT).show()
-        }
-    }
-}
-
-private fun extractOcr(
-    coroutineScope: CoroutineScope,
-    dwp: DocumentWithPages?,
-    context: Context,
-    viewModel: DocumentDetailViewModel,
-    clipboardManager: ClipboardManager,
-    setProcessing: (Boolean) -> Unit
-) {
-    if (dwp == null || dwp.pages.isEmpty()) {
-        Toast.makeText(context, "No pages available for OCR", Toast.LENGTH_SHORT).show()
-        return
-    }
-    val pages = dwp.pages.sortedBy { it.pageOrder }
-    coroutineScope.launch {
-        setProcessing(true)
-        val text = withContext(Dispatchers.IO) {
-            pages.mapIndexedNotNull { index, page ->
-                val file = File(page.imagePath)
-                if (!file.exists() || file.length() == 0L) return@mapIndexedNotNull null
-                val fileResult = OcrHelper.extractTextFromFile(context, file)
-                val result = if (fileResult.startsWith("OCR failed", ignoreCase = true)) {
-                    FileUtils.decodeSampledBitmap(file.absolutePath, 1800, 1800)?.let { bitmap ->
-                        try {
-                            OcrHelper.extractTextFromBitmap(bitmap)
-                        } finally {
-                            if (!bitmap.isRecycled) runCatching { bitmap.recycle() }
-                        }
-                    }.orEmpty()
-                } else {
-                    fileResult
-                }
-                if (result.isBlank() || result.startsWith("OCR failed", ignoreCase = true)) null else "Page ${index + 1}:\n$result"
-            }.joinToString(separator = "\n\n")
-        }
-        setProcessing(false)
-        if (text.isBlank()) {
-            Toast.makeText(context, "No readable text found", Toast.LENGTH_SHORT).show()
-            return@launch
-        }
-        val stats = OcrHelper.buildStats(text)
-        viewModel.updateOcrAndMetadata(stats.text, dwp.document.workspace.ifBlank { "Inbox" })
-        clipboardManager.setPrimaryClip(ClipData.newPlainText("Extracted Text", stats.text))
-        Toast.makeText(context, "OCR completed · ${stats.qualityLabel}", Toast.LENGTH_SHORT).show()
-    }
-}
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/FileManagerScreen.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/FileManagerScreen.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/FileManagerScreen.kt	2026-05-29 10:09:18.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/FileManagerScreen.kt	2026-05-29 10:53:55.395345735 +0000
@@ -3,13 +3,17 @@
 import android.widget.Toast
 import androidx.compose.foundation.BorderStroke
 import androidx.compose.foundation.ExperimentalFoundationApi
+import androidx.compose.foundation.background
 import androidx.compose.foundation.layout.Arrangement
+import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.Spacer
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.height
+import androidx.compose.foundation.layout.size
+import androidx.compose.foundation.layout.width
 import androidx.compose.foundation.layout.padding
 import androidx.compose.foundation.lazy.LazyColumn
 import androidx.compose.foundation.lazy.items
@@ -50,10 +54,14 @@
 import androidx.compose.runtime.setValue
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
+import androidx.compose.ui.draw.clip
+import androidx.compose.ui.layout.ContentScale
 import androidx.compose.ui.platform.LocalContext
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.text.style.TextOverflow
 import androidx.compose.ui.unit.dp
+import coil.compose.AsyncImage
+import coil.request.ImageRequest
 import com.synthbyte.scanmate.utils.FileUtils
 import kotlinx.coroutines.Dispatchers
 import kotlinx.coroutines.withContext
@@ -242,6 +250,49 @@
 }
 
 @Composable
+private fun FileThumb(file: File, modifier: Modifier = Modifier) {
+    val context = LocalContext.current
+    val extension = file.extension.lowercase(Locale.getDefault())
+    val imageExtensions = setOf("jpg", "jpeg", "png", "webp", "heic", "heif")
+    Box(
+        modifier = modifier
+            .clip(RoundedCornerShape(10.dp))
+            .background(MaterialTheme.colorScheme.surfaceVariant),
+        contentAlignment = Alignment.Center
+    ) {
+        when {
+            extension in imageExtensions -> {
+                val request = remember(file.absolutePath) {
+                    ImageRequest.Builder(context)
+                        .data(file)
+                        .diskCacheKey(file.absolutePath)
+                        .memoryCacheKey(file.absolutePath)
+                        .crossfade(true)
+                        .build()
+                }
+                AsyncImage(
+                    model = request,
+                    contentDescription = file.name,
+                    modifier = Modifier.matchParentSize(),
+                    contentScale = ContentScale.Crop
+                )
+            }
+            extension == "pdf" -> {
+                Box(
+                    modifier = Modifier
+                        .matchParentSize()
+                        .background(MaterialTheme.colorScheme.errorContainer),
+                    contentAlignment = Alignment.Center
+                ) {
+                    Text("PDF", color = MaterialTheme.colorScheme.onErrorContainer, fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.labelMedium)
+                }
+            }
+            else -> Icon(Icons.Default.InsertDriveFile, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(28.dp))
+        }
+    }
+}
+
+@Composable
 private fun ManagedFileRow(file: File, onOpen: () -> Unit, onShare: () -> Unit, onDelete: () -> Unit) {
     val date = remember(file.lastModified()) { formatShortDate(file.lastModified()) }
     val size = remember(file.length()) { formatFileSize(file.length()) }
@@ -252,7 +303,7 @@
         modifier = Modifier.fillMaxWidth()
     ) {
         Row(modifier = Modifier.padding(14.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
-            Icon(Icons.Default.InsertDriveFile, null, tint = MaterialTheme.colorScheme.primary)
+            FileThumb(file = file, modifier = Modifier.width(48.dp).height(60.dp))
             Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                 Text(file.name, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                 Text("${file.parentFile?.name ?: "App"} · $size · $date", color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1, overflow = TextOverflow.Ellipsis)
@@ -276,7 +327,7 @@
         modifier = Modifier.fillMaxWidth()
     ) {
         Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
-            Icon(Icons.Default.InsertDriveFile, null, tint = MaterialTheme.colorScheme.primary)
+            FileThumb(file = file, modifier = Modifier.width(48.dp).height(60.dp))
             Text(file.name, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
             Text(size, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelSmall, maxLines = 1)
             Text(date, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelSmall, maxLines = 1)
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/HomeScreen.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/HomeScreen.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/HomeScreen.kt	2026-05-29 10:10:39.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/HomeScreen.kt	2026-05-29 10:53:16.459341156 +0000
@@ -35,7 +35,7 @@
 import com.synthbyte.scanmate.ui.screens.home.HomeBottomNavigation
 import com.synthbyte.scanmate.ui.screens.home.DocumentRow
 import com.synthbyte.scanmate.ui.screens.home.HomeDocumentFilterRow
-import com.synthbyte.scanmate.ui.screens.home.HomeDocumentList
+import com.synthbyte.scanmate.ui.screens.home.HomeDocumentEmptyState
 import com.synthbyte.scanmate.ui.screens.home.HomeDocumentSectionHeader
 import com.synthbyte.scanmate.ui.screens.home.HomeHeaderZone
 import com.synthbyte.scanmate.ui.screens.home.HomeHeroCard
@@ -182,20 +182,8 @@
                 )
             }
             if (visibleDocuments.isEmpty()) {
-                item(key = "home_documents_empty") {
-                    HomeDocumentList(
-                        title = filterMode.sectionTitle,
-                        countLabel = "${visibleDocuments.size} shown",
-                        documents = visibleDocuments,
-                        firstPageByDocument = firstPageByDocument,
-                        selectedFilter = filterMode.label,
-                        filters = DocumentFilterMode.entries.map { it.label },
-                        onFilterSelected = { label -> filterMode = DocumentFilterMode.entries.first { it.label == label } },
-                        onDocumentClick = { onNavigateToDoc(it.id) },
-                        onFavorite = { viewModel.toggleFavorite(it) },
-                        onPin = { viewModel.togglePinned(it) },
-                        onScanClick = onNavigateToCamera
-                    )
+                item(key = "home_empty") {
+                    HomeDocumentEmptyState(onScanClick = onNavigateToCamera)
                 }
             } else {
                 items(visibleDocuments, key = { it.id }) { doc ->
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/PageEditorScreen.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/PageEditorScreen.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/PageEditorScreen.kt	2026-05-29 10:07:53.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/PageEditorScreen.kt	2026-05-29 10:52:46.156328214 +0000
@@ -84,7 +84,7 @@
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.unit.IntOffset
 import androidx.compose.ui.unit.dp
-import com.synthbyte.scanmate.data.AppDatabase
+import com.synthbyte.scanmate.ui.viewmodels.rememberPageEditorViewModel
 import com.synthbyte.scanmate.data.Page
 import com.synthbyte.scanmate.utils.FileUtils
 import com.synthbyte.scanmate.utils.FilterType
@@ -98,8 +98,8 @@
 @Composable
 fun PageEditorScreen(docId: Long, pageId: Long, onNavigateBack: () -> Unit) {
     val context = LocalContext.current
-    val dao = remember { AppDatabase.getDatabase(context).docDao() }
-    val page by remember(pageId) { dao.getPage(pageId) }.collectAsState(initial = null)
+    val viewModel = rememberPageEditorViewModel(docId, pageId)
+    val page by viewModel.page.collectAsState(initial = null)
     val scope = rememberCoroutineScope()
     val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
 
@@ -161,7 +161,7 @@
                 val file = withContext(Dispatchers.IO) { FileUtils.copyUriToImageFile(context, uri) }
                 val currentPage = page
                 if (file != null && currentPage != null) {
-                    withContext(Dispatchers.IO) { dao.updatePageImage(currentPage.id, file.absolutePath) }
+                    viewModel.replacePageImage(currentPage.id, uri)
                     sourcePath = null
                     workingBitmap = null
                     Toast.makeText(context, "Page replaced", Toast.LENGTH_SHORT).show()
@@ -204,9 +204,8 @@
                         val currentPage = page ?: return@IconButton
                         scope.launch {
                             isProcessing = true
-                            val file = FileUtils.saveEditedBitmap(context, bitmap, "PAGE_${currentPage.id}")
+                            val file = viewModel.saveEditedPage(currentPage.id, bitmap)
                             if (file != null) {
-                                withContext(Dispatchers.IO) { dao.updatePageImage(currentPage.id, file.absolutePath) }
                                 Toast.makeText(context, "Edited page saved", Toast.LENGTH_SHORT).show()
                                 sourcePath = null
                                 changeVersion++
@@ -368,7 +367,7 @@
                 OutlinedButton(onClick = {
                     val currentPage = page ?: return@OutlinedButton
                     scope.launch {
-                        duplicatePage(context, dao, docId, currentPage)
+                        viewModel.duplicatePage(currentPage)
                         Toast.makeText(context, "Page duplicated", Toast.LENGTH_SHORT).show()
                     }
                 }, modifier = Modifier.weight(1f)) {
@@ -380,14 +379,14 @@
                 OutlinedButton(onClick = {
                     val currentPage = page ?: return@OutlinedButton
                     scope.launch {
-                        movePage(dao, docId, currentPage, -1)
+                        viewModel.movePage(currentPage, -1)
                         Toast.makeText(context, "Page moved", Toast.LENGTH_SHORT).show()
                     }
                 }, modifier = Modifier.weight(1f)) { Text("Move up") }
                 OutlinedButton(onClick = {
                     val currentPage = page ?: return@OutlinedButton
                     scope.launch {
-                        movePage(dao, docId, currentPage, 1)
+                        viewModel.movePage(currentPage, 1)
                         Toast.makeText(context, "Page moved", Toast.LENGTH_SHORT).show()
                     }
                 }, modifier = Modifier.weight(1f)) { Text("Move down") }
@@ -403,7 +402,7 @@
                             Toast.makeText(context, "No readable text found on this page", Toast.LENGTH_SHORT).show()
                         } else {
                             clipboardManager.setPrimaryClip(ClipData.newPlainText("Page OCR", text))
-                            withContext(Dispatchers.IO) { dao.updateOcrText(docId, "Page ${currentPage.pageOrder + 1}:\n$text") }
+                            viewModel.savePageOcr(currentPage, text)
                             Toast.makeText(context, "Page OCR copied and saved", Toast.LENGTH_SHORT).show()
                         }
                     }
@@ -500,10 +499,7 @@
                 Button(onClick = {
                     val currentPage = page ?: return@Button
                     scope.launch {
-                        withContext(Dispatchers.IO) {
-                            dao.deletePageById(currentPage.id)
-                            renumberPages(dao, docId)
-                        }
+                        viewModel.deleteCurrentPage(currentPage.id)
                         Toast.makeText(context, "Page deleted", Toast.LENGTH_SHORT).show()
                         showDeleteDialog = false
                         onNavigateBack()
@@ -649,31 +645,3 @@
         Slider(value = value, onValueChange = onChange, valueRange = 0f..0.35f)
     }
 }
-
-private suspend fun duplicatePage(context: Context, dao: com.synthbyte.scanmate.data.DocDao, docId: Long, page: Page) = withContext(Dispatchers.IO) {
-    val copied = FileUtils.duplicateImageFile(context, page.imagePath) ?: return@withContext
-    val pages = dao.getPagesForDocumentOnce(docId).sortedBy { it.pageOrder }
-    val insertIndex = pages.indexOfFirst { it.id == page.id }.takeIf { it >= 0 }?.plus(1) ?: pages.size
-    pages.forEachIndexed { index, existing ->
-        val order = if (index >= insertIndex) index + 1 else index
-        dao.updatePageOrder(existing.id, order)
-    }
-    dao.insertPage(Page(documentId = docId, imagePath = copied.absolutePath, pageOrder = insertIndex))
-    renumberPages(dao, docId)
-}
-
-private suspend fun movePage(dao: com.synthbyte.scanmate.data.DocDao, docId: Long, page: Page, direction: Int) = withContext(Dispatchers.IO) {
-    val pages = dao.getPagesForDocumentOnce(docId).sortedBy { it.pageOrder }.toMutableList()
-    val index = pages.indexOfFirst { it.id == page.id }
-    val newIndex = (index + direction).coerceIn(0, pages.lastIndex)
-    if (index < 0 || index == newIndex) return@withContext
-    val current = pages.removeAt(index)
-    pages.add(newIndex, current)
-    pages.forEachIndexed { order, existing -> dao.updatePageOrder(existing.id, order) }
-}
-
-private suspend fun renumberPages(dao: com.synthbyte.scanmate.data.DocDao, docId: Long) {
-    dao.getPagesForDocumentOnce(docId).sortedBy { it.pageOrder }.forEachIndexed { index, page ->
-        dao.updatePageOrder(page.id, index)
-    }
-}
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/QrScreen.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/QrScreen.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/QrScreen.kt	2026-05-29 10:03:26.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/QrScreen.kt	2026-05-29 10:54:03.177663553 +0000
@@ -72,6 +72,7 @@
 import androidx.compose.ui.text.style.TextOverflow
 import androidx.compose.ui.unit.dp
 import com.synthbyte.scanmate.data.AppDatabase
+import com.synthbyte.scanmate.ui.viewmodels.rememberDocumentViewModel
 import com.synthbyte.scanmate.data.QrHistory
 import com.synthbyte.scanmate.utils.BarcodeScannerHelper
 import com.synthbyte.scanmate.utils.FileUtils
@@ -101,7 +102,8 @@
     var isProcessing by remember { mutableStateOf(false) }
     val context = LocalContext.current
     val dao = remember { AppDatabase.getDatabase(context).docDao() }
-    val history by dao.getQrHistory().collectAsState(initial = emptyList())
+    val documentViewModel = rememberDocumentViewModel()
+    val history by documentViewModel.qrHistory.collectAsState(initial = emptyList())
     val coroutineScope = rememberCoroutineScope()
     val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
     val primaryArgb = MaterialTheme.colorScheme.primary.toArgb()
@@ -338,7 +340,7 @@
 @Composable
 private fun HistoryRow(item: QrHistory, clipboardManager: ClipboardManager, context: Context) {
     val date = remember(item.timestamp) {
-        SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date(item.timestamp))
+        SimpleDateFormat("dd MMM HH:mm", Locale.getDefault()).format(Date(item.timestamp))
     }
     Card(shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth()) {
         Row(modifier = Modifier.padding(14.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeDocumentList.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeDocumentList.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeDocumentList.kt	2026-05-29 10:10:39.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeDocumentList.kt	2026-05-29 10:53:16.458653575 +0000
@@ -43,22 +43,8 @@
 import java.util.Date
 import java.util.Locale
 
-@Suppress("UNUSED_PARAMETER")
 @Composable
-fun HomeDocumentList(
-    title: String,
-    countLabel: String,
-    documents: List<Document>,
-    firstPageByDocument: Map<Long, Page>,
-    selectedFilter: String,
-    filters: List<String>,
-    onFilterSelected: (String) -> Unit,
-    onDocumentClick: (Document) -> Unit,
-    onFavorite: (Document) -> Unit,
-    onPin: (Document) -> Unit,
-    onScanClick: () -> Unit
-) {
-    if (documents.isEmpty()) {
+fun HomeDocumentEmptyState(onScanClick: () -> Unit) {
         Card(
             shape = RoundedCornerShape(16.dp),
             colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
@@ -93,7 +79,6 @@
                 }
             }
         }
-    }
 }
 
 @Composable
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeHeroCard.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeHeroCard.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeHeroCard.kt	2026-05-29 10:03:26.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeHeroCard.kt	2026-05-29 10:53:28.052867587 +0000
@@ -19,7 +19,6 @@
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
-import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.unit.dp
@@ -90,26 +89,30 @@
                 }
             }
 
-            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
-                HeroStat(documentCount.toString(), "Docs", Modifier.weight(1f))
-                HeroStat(pageCount.toString(), "Pages", Modifier.weight(1f))
-                HeroStat(pinnedCount.toString(), "Pinned", Modifier.weight(1f))
+            val greeting = when {
+                documentCount == 0 -> "Ready to scan your first document"
+                documentCount in 1..4 -> "You have $documentCount document${if (documentCount == 1) "" else "s"}"
+                else -> "$documentCount documents · $pageCount pages"
+            }
+            Column(
+                modifier = Modifier
+                    .fillMaxWidth()
+                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
+                    .padding(vertical = 12.dp, horizontal = 14.dp),
+                verticalArrangement = Arrangement.spacedBy(4.dp)
+            ) {
+                Text(greeting, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
+                if (pinnedCount > 0) {
+                    Text(
+                        "$pinnedCount pinned",
+                        color = MaterialTheme.colorScheme.primary,
+                        fontWeight = FontWeight.ExtraBold,
+                        fontSize = 11.sp
+                    )
+                }
             }
 
             toolContent()
         }
     }
 }
-
-@Composable
-private fun HeroStat(value: String, label: String, modifier: Modifier) {
-    Column(
-        modifier = modifier
-            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
-            .padding(vertical = 12.dp, horizontal = 8.dp),
-        horizontalAlignment = Alignment.CenterHorizontally
-    ) {
-        Text(value, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
-        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = 11.sp)
-    }
-}
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentDetailViewModel.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentDetailViewModel.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentDetailViewModel.kt	2026-05-29 10:03:26.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentDetailViewModel.kt	2026-05-29 10:55:43.111621188 +0000
@@ -7,13 +7,28 @@
 import com.synthbyte.scanmate.data.DocDao
 import com.synthbyte.scanmate.data.DocumentWithPages
 import com.synthbyte.scanmate.data.Page
+import com.synthbyte.scanmate.utils.PdfExportQuality
+import com.synthbyte.scanmate.utils.PdfPageSize
 import com.synthbyte.scanmate.utils.DocumentIntelligence
 import com.synthbyte.scanmate.utils.FileUtils
 import com.synthbyte.scanmate.utils.OcrHelper
 import kotlinx.coroutines.Dispatchers
 import kotlinx.coroutines.flow.Flow
+import kotlinx.coroutines.flow.MutableStateFlow
+import kotlinx.coroutines.flow.StateFlow
+import kotlinx.coroutines.flow.asStateFlow
 import kotlinx.coroutines.launch
 import kotlinx.coroutines.withContext
+import java.io.File
+
+sealed interface ExportState {
+    data object Idle : ExportState
+    data class Loading(val message: String? = null) : ExportState
+    data class PdfSuccess(val file: File) : ExportState
+    data class DocxSuccess(val file: File) : ExportState
+    data class OcrSuccess(val text: String, val qualityLabel: String) : ExportState
+    data class Error(val message: String) : ExportState
+}
 
 class DocumentDetailViewModel(
     private val dao: DocDao,
@@ -21,6 +36,106 @@
     private val docId: Long
 ) : ViewModel() {
     val documentWithPages: Flow<DocumentWithPages?> = dao.getDocumentWithPages(docId)
+    private val _exportState = MutableStateFlow<ExportState>(ExportState.Idle)
+    val exportState: StateFlow<ExportState> = _exportState.asStateFlow()
+
+
+
+    fun clearExportState() {
+        _exportState.value = ExportState.Idle
+    }
+
+    fun exportDocx(dwp: DocumentWithPages?) = viewModelScope.launch {
+        if (dwp == null) return@launch
+        val text = dwp.document.ocrText.orEmpty().trim()
+        if (text.isBlank()) {
+            _exportState.value = ExportState.Error("Run OCR first, then export DOCX")
+            return@launch
+        }
+        _exportState.value = ExportState.Loading("Preparing DOCX…")
+        val file = withContext(Dispatchers.IO) {
+            FileUtils.saveDocxText(
+                context = context,
+                text = text,
+                filename = dwp.document.title.ifBlank { "ScanMate_${dwp.document.id}_${System.currentTimeMillis()}" }
+            )
+        }
+        _exportState.value = if (file != null) ExportState.DocxSuccess(file) else ExportState.Error("DOCX export failed")
+    }
+
+    fun exportPdf(
+        dwp: DocumentWithPages?,
+        quality: PdfExportQuality,
+        filename: String,
+        pageSize: PdfPageSize = PdfPageSize.A4
+    ) = viewModelScope.launch {
+        if (dwp == null) return@launch
+        val pages = dwp.pages.sortedBy { it.pageOrder }
+        if (pages.isEmpty()) {
+            _exportState.value = ExportState.Error("No pages found to export")
+            return@launch
+        }
+        _exportState.value = ExportState.Loading("Preparing PDF…")
+        val imagePaths = pages.map { it.imagePath }
+        val ocrBlocksByPath: Map<String, List<Pair<android.graphics.Rect, String>>> = withContext(Dispatchers.IO) {
+            imagePaths.associateWith { path ->
+                val pageFile = File(path)
+                if (pageFile.exists() && pageFile.length() > 0L) {
+                    OcrHelper.extractBlocksFromFile(context, pageFile)
+                } else {
+                    emptyList()
+                }
+            }
+        }
+        _exportState.value = ExportState.Loading("Building PDF…")
+        val pdfFile = withContext(Dispatchers.IO) {
+            FileUtils.generatePdfFromPaths(
+                context = context,
+                imagePaths = imagePaths,
+                filename = filename.ifBlank { FileUtils.sanitizeFileBaseName(dwp.document.title) },
+                quality = quality,
+                pageSize = pageSize,
+                onProgress = { message -> _exportState.value = ExportState.Loading(message) },
+                ocrRectsByPath = ocrBlocksByPath
+            )
+        }
+        _exportState.value = if (pdfFile != null) ExportState.PdfSuccess(pdfFile) else ExportState.Error("PDF export failed. Check that pages are valid images.")
+    }
+
+    fun extractOcr(dwp: DocumentWithPages?) = viewModelScope.launch {
+        if (dwp == null || dwp.pages.isEmpty()) {
+            _exportState.value = ExportState.Error("No pages available for OCR")
+            return@launch
+        }
+        _exportState.value = ExportState.Loading("Running OCR…")
+        val pages = dwp.pages.sortedBy { it.pageOrder }
+        val text = withContext(Dispatchers.IO) {
+            pages.mapIndexedNotNull { index, page ->
+                val file = File(page.imagePath)
+                if (!file.exists() || file.length() == 0L) return@mapIndexedNotNull null
+                val fileResult = OcrHelper.extractTextFromFile(context, file)
+                val result = if (fileResult.startsWith("OCR failed", ignoreCase = true)) {
+                    FileUtils.decodeSampledBitmap(file.absolutePath, 1800, 1800)?.let { bitmap ->
+                        try {
+                            OcrHelper.extractTextFromBitmap(bitmap)
+                        } finally {
+                            if (!bitmap.isRecycled) runCatching { bitmap.recycle() }
+                        }
+                    }.orEmpty()
+                } else {
+                    fileResult
+                }
+                if (result.isBlank() || result.startsWith("OCR failed", ignoreCase = true)) null else "Page ${index + 1}:\n$result"
+            }.joinToString(separator = "\n\n")
+        }
+        if (text.isBlank()) {
+            _exportState.value = ExportState.Error("No readable text found")
+            return@launch
+        }
+        val stats = OcrHelper.buildStats(text)
+        updateOcrAndMetadata(stats.text, dwp.document.workspace.ifBlank { "Inbox" })
+        _exportState.value = ExportState.OcrSuccess(stats.text, stats.qualityLabel)
+    }
 
     fun setFavorite(favorite: Boolean) = viewModelScope.launch(Dispatchers.IO) {
         dao.setFavorite(docId, favorite)
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModel.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModel.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModel.kt	2026-05-29 10:03:26.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModel.kt	2026-05-29 10:54:03.177009783 +0000
@@ -8,6 +8,7 @@
 import com.synthbyte.scanmate.data.DocDao
 import com.synthbyte.scanmate.data.Document
 import com.synthbyte.scanmate.data.Page
+import com.synthbyte.scanmate.data.QrHistory
 import com.synthbyte.scanmate.utils.FileUtils
 import com.synthbyte.scanmate.utils.OcrHelper
 import kotlinx.coroutines.Dispatchers
@@ -23,6 +24,7 @@
     val allPages: Flow<List<Page>> = dao.getFirstPagePerDocument()
     val pageCount: Flow<Int> = dao.getPageCountFlow()
     val pdfCount: Flow<Int> = dao.getPdfCountFlow()
+    val qrHistory: Flow<List<QrHistory>> = dao.getQrHistory()
 
     fun createDocumentFromUris(
         uris: List<Uri>,
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModelProvider.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModelProvider.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModelProvider.kt	2026-05-29 10:03:26.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModelProvider.kt	2026-05-29 10:52:37.741035499 +0000
@@ -27,3 +27,11 @@
     val dao = remember { AppDatabase.getDatabase(context).docDao() }
     return viewModel(factory = CameraViewModelFactory(dao, context))
 }
+
+
+@Composable
+fun rememberPageEditorViewModel(docId: Long, pageId: Long): PageEditorViewModel {
+    val context = LocalContext.current.applicationContext
+    val dao = remember { AppDatabase.getDatabase(context).docDao() }
+    return viewModel(key = "page-editor-$docId-$pageId", factory = PageEditorViewModelFactory(dao, context, docId, pageId))
+}
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/PageEditorViewModel.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/PageEditorViewModel.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/PageEditorViewModel.kt	1970-01-01 00:00:00.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/PageEditorViewModel.kt	2026-05-29 10:52:36.502188508 +0000
@@ -0,0 +1,89 @@
+package com.synthbyte.scanmate.ui.viewmodels
+
+import android.content.Context
+import android.net.Uri
+import androidx.lifecycle.ViewModel
+import androidx.lifecycle.ViewModelProvider
+import androidx.lifecycle.viewModelScope
+import com.synthbyte.scanmate.data.DocDao
+import com.synthbyte.scanmate.data.Page
+import com.synthbyte.scanmate.utils.FileUtils
+import kotlinx.coroutines.Dispatchers
+import kotlinx.coroutines.flow.Flow
+import kotlinx.coroutines.launch
+import kotlinx.coroutines.withContext
+
+class PageEditorViewModel(
+    private val dao: DocDao,
+    private val context: Context,
+    private val docId: Long,
+    pageId: Long
+) : ViewModel() {
+    val page: Flow<Page?> = dao.getPage(pageId)
+
+    suspend fun saveEditedPage(pageId: Long, bitmap: android.graphics.Bitmap): java.io.File? = withContext(Dispatchers.IO) {
+        val file = FileUtils.saveEditedBitmap(context, bitmap, "PAGE_${pageId}")
+        if (file != null) dao.updatePageImage(pageId, file.absolutePath)
+        file
+    }
+
+    suspend fun replacePageImage(pageId: Long, uri: Uri): java.io.File? = withContext(Dispatchers.IO) {
+        val file = FileUtils.copyUriToImageFile(context, uri)
+        if (file != null) dao.updatePageImage(pageId, file.absolutePath)
+        file
+    }
+
+    fun deleteCurrentPage(pageId: Long, onDone: () -> Unit = {}) = viewModelScope.launch(Dispatchers.IO) {
+        dao.deletePageById(pageId)
+        renumberPagesInternal()
+        withContext(Dispatchers.Main) { onDone() }
+    }
+
+    suspend fun duplicatePage(page: Page) = withContext(Dispatchers.IO) {
+        val copied = FileUtils.duplicateImageFile(context, page.imagePath) ?: return@withContext
+        val pages = dao.getPagesForDocumentOnce(docId).sortedBy { it.pageOrder }
+        val insertIndex = pages.indexOfFirst { it.id == page.id }.takeIf { it >= 0 }?.plus(1) ?: pages.size
+        pages.forEachIndexed { index, existing ->
+            val order = if (index >= insertIndex) index + 1 else index
+            dao.updatePageOrder(existing.id, order)
+        }
+        dao.insertPage(Page(documentId = docId, imagePath = copied.absolutePath, pageOrder = insertIndex))
+        renumberPagesInternal()
+    }
+
+    suspend fun movePage(page: Page, direction: Int) = withContext(Dispatchers.IO) {
+        val pages = dao.getPagesForDocumentOnce(docId).sortedBy { it.pageOrder }.toMutableList()
+        val index = pages.indexOfFirst { it.id == page.id }
+        if (index < 0) return@withContext
+        val newIndex = (index + direction).coerceIn(0, pages.lastIndex)
+        if (index == newIndex) return@withContext
+        val current = pages.removeAt(index)
+        pages.add(newIndex, current)
+        pages.forEachIndexed { order, existing -> dao.updatePageOrder(existing.id, order) }
+    }
+
+    suspend fun savePageOcr(page: Page, text: String) = withContext(Dispatchers.IO) {
+        dao.updateOcrText(docId, "Page ${page.pageOrder + 1}:\n$text")
+    }
+
+    private suspend fun renumberPagesInternal() {
+        dao.getPagesForDocumentOnce(docId).sortedBy { it.pageOrder }.forEachIndexed { index, page ->
+            dao.updatePageOrder(page.id, index)
+        }
+    }
+}
+
+class PageEditorViewModelFactory(
+    private val dao: DocDao,
+    private val context: Context,
+    private val docId: Long,
+    private val pageId: Long
+) : ViewModelProvider.Factory {
+    override fun <T : ViewModel> create(modelClass: Class<T>): T {
+        if (modelClass.isAssignableFrom(PageEditorViewModel::class.java)) {
+            @Suppress("UNCHECKED_CAST")
+            return PageEditorViewModel(dao, context, docId, pageId) as T
+        }
+        throw IllegalArgumentException("Unknown ViewModel class")
+    }
+}
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/utils/FileUtils.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/utils/FileUtils.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/utils/FileUtils.kt	2026-05-29 10:12:42.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/utils/FileUtils.kt	2026-05-29 10:51:48.358127841 +0000
@@ -244,7 +244,7 @@
         quality: PdfExportQuality = PdfExportQuality.BALANCED,
         pageSize: PdfPageSize = PdfPageSize.AUTO,
         onProgress: ((String) -> Unit)? = null,
-        ocrRectsByPath: Map<String, List<Rect>> = emptyMap()
+        ocrRectsByPath: Map<String, List<Pair<Rect, String>>> = emptyMap()
     ): File? = withContext(Dispatchers.IO) {
         val targetSize = when (quality) {
             PdfExportQuality.SMALL -> 1200
@@ -266,7 +266,7 @@
         filename: String,
         pageSize: PdfPageSize,
         onProgress: ((String) -> Unit)?,
-        ocrRectsByPath: Map<String, List<Rect>>
+        ocrRectsByPath: Map<String, List<Pair<Rect, String>>>
     ): File? {
         if (imagePaths.isEmpty()) return null
         val pdfDocument = PdfDocument()
@@ -381,8 +381,8 @@
         val rect = RectF(left, top, left + targetWidth, top + targetHeight)
         drawBitmap(bitmap, null, rect, Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG or Paint.DITHER_FLAG))
     }
-    private fun Canvas.drawInvisibleOcrLayer(bitmap: Bitmap, pageWidthPx: Int, pageHeightPx: Int, ocrRects: List<Rect>) {
-        if (ocrRects.isEmpty() || bitmap.width <= 0 || bitmap.height <= 0) return
+    private fun Canvas.drawInvisibleOcrLayer(bitmap: Bitmap, pageWidthPx: Int, pageHeightPx: Int, ocrBlocks: List<Pair<Rect, String>>) {
+        if (ocrBlocks.isEmpty() || bitmap.width <= 0 || bitmap.height <= 0) return
         val textPaint = Paint().apply {
             color = Color.TRANSPARENT
             alpha = 1
@@ -391,8 +391,9 @@
         }
         val sx = pageWidthPx.toFloat() / bitmap.width
         val sy = pageHeightPx.toFloat() / bitmap.height
-        ocrRects.forEach { rect ->
-            drawText(" ", rect.left * sx, rect.bottom * sy, textPaint)
+        ocrBlocks.forEach { (rect, text) ->
+            val safeText = text.ifBlank { " " }
+            drawText(safeText, rect.left * sx, rect.bottom * sy, textPaint)
         }
     }
 
diff -ruN scanmate_orig/app/src/main/java/com/synthbyte/scanmate/utils/OcrHelper.kt scanmate_work/app/src/main/java/com/synthbyte/scanmate/utils/OcrHelper.kt
--- scanmate_orig/app/src/main/java/com/synthbyte/scanmate/utils/OcrHelper.kt	2026-05-29 10:10:39.000000000 +0000
+++ scanmate_work/app/src/main/java/com/synthbyte/scanmate/utils/OcrHelper.kt	2026-05-29 10:51:48.357285694 +0000
@@ -19,6 +19,7 @@
 import kotlin.coroutines.resume
 import kotlin.math.abs
 import kotlin.math.max
+import kotlin.math.min
 import kotlin.math.roundToInt
 
 private const val OCR_MAX_SIDE = 2048
@@ -49,7 +50,7 @@
     }
 
     @Suppress("UNUSED_PARAMETER")
-    suspend fun extractBlocksFromFile(context: Context, file: File): List<Rect> {
+    suspend fun extractBlocksFromFile(context: Context, file: File): List<Pair<Rect, String>> {
         val source = FileUtils.decodeSampledBitmap(file.absolutePath, OCR_MAX_SIDE, OCR_MAX_SIDE) ?: return emptyList()
         val fixed = fixExifRotation(source, file)
         fun recycleBitmaps() {
@@ -63,7 +64,7 @@
                         block.lines.map { line -> line.boundingBox to line.text }
                     }.mapNotNull { (rect, text) -> rect?.let { Pair(it, text) } }
                     recycleBitmaps()
-                    if (continuation.isActive) continuation.resume(rects.map { it.first })
+                    if (continuation.isActive) continuation.resume(rects)
                 }
                 .addOnFailureListener {
                     recycleBitmaps()
@@ -191,38 +192,74 @@
     }
 
     private fun estimateSkewAndRotate(source: Bitmap): Bitmap {
-        val w = source.width
-        val h = source.height
-        val sampleW = (w * 0.6f).toInt().coerceAtLeast(1)
-        val sampleH = (h * 0.6f).toInt().coerceAtLeast(1)
-        val offsetX = (w - sampleW) / 2
-        val offsetY = (h - sampleH) / 2
-        val sample = Bitmap.createBitmap(source, offsetX, offsetY, sampleW, sampleH)
-        val pixels = IntArray(sampleW * sampleH)
-        sample.getPixels(pixels, 0, sampleW, 0, 0, sampleW, sampleH)
-        sample.recycle()
-        val threshold = 128
-        var sumAngle = 0.0
-        var count = 0
-        for (y in 1 until sampleH) {
-            for (x in 1 until sampleW) {
-                val cur = android.graphics.Color.red(pixels[y * sampleW + x])
-                val left = android.graphics.Color.red(pixels[y * sampleW + (x - 1)])
-                val above = android.graphics.Color.red(pixels[(y - 1) * sampleW + x])
-                if (abs(cur - left) > threshold || abs(cur - above) > threshold) {
-                    sumAngle += kotlin.math.atan2((cur - above).toDouble(), (cur - left).toDouble())
-                    count++
-                }
+        if (source.width <= 1 || source.height <= 1) return source
+        val maxSampleSide = 720
+        val sampleScale = min(1f, maxSampleSide.toFloat() / max(source.width, source.height).toFloat())
+        val sample = if (sampleScale < 1f) {
+            Bitmap.createScaledBitmap(
+                source,
+                (source.width * sampleScale).roundToInt().coerceAtLeast(1),
+                (source.height * sampleScale).roundToInt().coerceAtLeast(1),
+                true
+            )
+        } else {
+            source
+        }
+
+        val angles = generateSequence(-10f) { previous ->
+            val next = previous + 0.5f
+            if (next <= 10.0001f) next else null
+        }.toList()
+        var bestAngle = 0f
+        var bestScore = Double.NEGATIVE_INFINITY
+        angles.forEach { angle ->
+            val rotated = rotateForSkewScore(sample, angle)
+            val score = horizontalProjectionVariance(rotated)
+            if (rotated !== sample && !rotated.isRecycled) runCatching { rotated.recycle() }
+            if (score > bestScore) {
+                bestScore = score
+                bestAngle = angle
             }
         }
-        if (count == 0) return source
-        val avgAngleDeg = Math.toDegrees(sumAngle / count).toFloat()
-        val clampedAngle = avgAngleDeg.coerceIn(-12f, 12f)
-        if (abs(clampedAngle) < 0.4f) return source
-        val matrix = Matrix().apply { postRotate(-clampedAngle) }
+        if (sample !== source && !sample.isRecycled) runCatching { sample.recycle() }
+        if (abs(bestAngle) < 0.4f) return source
+        val matrix = Matrix().apply { postRotate(bestAngle) }
+        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
+    }
+
+    private fun rotateForSkewScore(source: Bitmap, angle: Float): Bitmap {
+        if (abs(angle) < 0.001f) return source
+        val matrix = Matrix().apply { postRotate(angle) }
         return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
     }
 
+    private fun horizontalProjectionVariance(bitmap: Bitmap): Double {
+        val width = bitmap.width
+        val height = bitmap.height
+        if (width <= 1 || height <= 1) return 0.0
+        val pixels = IntArray(width * height)
+        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
+        val rowSums = DoubleArray(height)
+        for (y in 0 until height) {
+            var sum = 0.0
+            var previousDark = false
+            for (x in 0 until width) {
+                val color = pixels[y * width + x]
+                val luminance = (android.graphics.Color.red(color) * 0.299f + android.graphics.Color.green(color) * 0.587f + android.graphics.Color.blue(color) * 0.114f)
+                val dark = luminance < 190f
+                if (dark && !previousDark) sum += 1.0
+                if (dark) sum += (255f - luminance) / 255f
+                previousDark = dark
+            }
+            rowSums[y] = sum
+        }
+        val mean = rowSums.average()
+        return rowSums.fold(0.0) { acc, value ->
+            val delta = value - mean
+            acc + delta * delta
+        } / height.toDouble()
+    }
+
     private fun fixExifRotation(bitmap: Bitmap, file: File): Bitmap {
         val degrees = runCatching {
             when (ExifInterface(file.absolutePath).getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)) {
