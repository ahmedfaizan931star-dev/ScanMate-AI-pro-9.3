# ScanMate AI Pro Principal Repair v3 Verification

Baseline: `ScanMate-AI-Pro-principal-repair-v2-fixed.zip`

## File read pass
- Every file in the ZIP was streamed before editing.
- `FILE_MANIFEST_BEFORE.tsv` and `FILE_MANIFEST_AFTER.tsv` contain SHA-256 hashes.

## Fix results

### FIX 1 · DocumentDetailViewModel.kt + AppViewModel.kt + PageEditorViewModel.kt
- `DocumentDetailViewModel.publishError()` now sends the same message to `_exportState` and the shared global async error channel.
- `PageEditorViewModel` now publishes failures to the same async error channel.
- Hilt cannot safely inject one `@HiltViewModel` into another `@HiltViewModel`, so the global async error channel is backed by `AppErrorBus` and exposed through `AppViewModel`. Both child ViewModels call `appViewModel.reportError(...)` through the injected reporter property.

Verification:
```text
55:    private fun publishError(throwable: Throwable) {
57:        _exportState.value = ExportState.Error(msg)
58:        appViewModel.reportError(msg)
66:                _exportState.value = ExportState.Error("Run OCR first, then export DOCX")
78:        }.onFailure { throwable -> publishError(throwable) }
91:                _exportState.value = ExportState.Error("No pages found to export")
119:        }.onFailure { throwable -> publishError(throwable) }
125:                _exportState.value = ExportState.Error("No pages available for OCR")
150:                _exportState.value = ExportState.Error("No readable text found")
156:        }.onFailure { throwable -> publishError(throwable) }
161:            .onFailure { throwable -> publishError(throwable) }
166:            .onFailure { throwable -> publishError(throwable) }
171:            .onFailure { throwable -> publishError(throwable) }
182:        }.onFailure { throwable -> publishError(throwable) }
189:        }.onFailure { throwable -> publishError(throwable) }
194:            .onFailure { throwable -> publishError(throwable) }
206:            .onFailure { throwable -> publishError(throwable) }
213:        }.onFailure { throwable -> publishError(throwable) }
220:        }.onFailure { throwable -> publishError(throwable) }
227:        }.onFailure { throwable -> publishError(throwable) }
234:        }.onFailure { throwable -> publishError(throwable) }
241:        }.onFailure { throwable -> publishError(throwable) }
51:    private fun publishError(throwable: Throwable, fallback: String = "Unknown error") {
54:        appViewModel.reportError(msg)
67:        }.onFailure { throwable -> publishError(throwable, "Filter failed") }
77:        }.onFailure { throwable -> publishError(throwable) }
86:        }.onFailure { throwable -> publishError(throwable) }
95:        }.onFailure { throwable -> publishError(throwable) }
109:        }.onFailure { throwable -> publishError(throwable) }
122:        }.onFailure { throwable -> publishError(throwable) }
127:            .onFailure { throwable -> publishError(throwable) }
```

### FIX 2 · FileUtils.kt extraction
- `FileUtils.kt` is now a thin facade.
- Created `PdfExporter.kt`, `DocxExporter.kt`, `ImageProcessor.kt`, plus `FileCore.kt` for non-export shared file helpers.
- Existing public `FileUtils.*` call sites remain valid.

Verification:
```text
87 app/src/main/java/com/synthbyte/scanmate/utils/FileUtils.kt
app/src/main/java/com/synthbyte/scanmate/utils/DocxExporter.kt
app/src/main/java/com/synthbyte/scanmate/utils/ImageProcessor.kt
app/src/main/java/com/synthbyte/scanmate/utils/PdfExporter.kt
```

### FIX 3 · QR history ViewModel boundary
- `QrScreen.kt` no longer calls `dao.*` directly.
- `DocumentViewModel` exposes `insertQrHistory()` and `clearQrHistory()`.

Verification:
```text

app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModel.kt:33:    val qrHistory: Flow<List<QrHistory>> = dao.getQrHistory()
app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModel.kt:128:    fun insertQrHistory(value: String, type: String) = viewModelScope.launch(Dispatchers.IO) {
app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModel.kt:129:        runCatching { dao.insertQrHistory(QrHistory(value = value, type = type)) }
app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModel.kt:133:    fun clearQrHistory() = viewModelScope.launch(Dispatchers.IO) {
app/src/main/java/com/synthbyte/scanmate/ui/viewmodels/DocumentViewModel.kt:134:        runCatching { dao.clearQrHistory() }
app/src/main/java/com/synthbyte/scanmate/data/DocDao.kt:136:    suspend fun insertQrHistory(item: QrHistory): Long
app/src/main/java/com/synthbyte/scanmate/data/DocDao.kt:139:    fun getQrHistory(): Flow<List<QrHistory>>
app/src/main/java/com/synthbyte/scanmate/data/DocDao.kt:142:    suspend fun clearQrHistory()
```

### FIX 4 · PageEditor bitmap/filter state
- `PageEditorViewModel` owns `workingBitmap: StateFlow<Bitmap?>`.
- `PageEditorScreen` collects `workingBitmap` and uses `viewModel.pushBitmap(...)` / `viewModel.applyFilter(...)`.
- Undo/redo stacks remain local UI state.

Verification:
```text
44:    private val _workingBitmap = MutableStateFlow<Bitmap?>(null)
45:    val workingBitmap: StateFlow<Bitmap?> = _workingBitmap.asStateFlow()
58:        _workingBitmap.value = bitmap
61:    fun applyFilter(filterId: String) = viewModelScope.launch(Dispatchers.IO) {
62:        val current = _workingBitmap.value ?: return@launch
66:            _workingBitmap.value = filtered
70:    fun applyFilter(filter: FilterType) = applyFilter(filter.name)
```

### FIX 5 · Hilt migration
- Added Hilt Gradle plugin/dependencies/version catalog entries.
- Added `ScanMateApplication.kt` with `@HiltAndroidApp` and registered it in `AndroidManifest.xml`.
- Annotated `MainActivity` with `@AndroidEntryPoint`.
- Converted `DocumentViewModel`, `DocumentDetailViewModel`, `PageEditorViewModel`, `AppViewModel`, and `CameraViewModel` to Hilt ViewModels. `CameraViewModel` was converted because deleting the provider file would otherwise break Camera.
- Deleted `DocumentViewModelProvider.kt` and `PageEditorViewModelProvider.kt`.
- Replaced provider calls with `hiltViewModel()`.

Verification:
```text
app/src/main/java/com/synthbyte/scanmate/MainActivity.kt:32:import androidx.hilt.navigation.compose.hiltViewModel
app/src/main/java/com/synthbyte/scanmate/MainActivity.kt:80:            val appViewModel: AppViewModel = hiltViewModel()
app/src/main/java/com/synthbyte/scanmate/ui/components/GlobalErrorBoundary.kt:27:import androidx.hilt.navigation.compose.hiltViewModel
app/src/main/java/com/synthbyte/scanmate/ui/components/GlobalErrorBoundary.kt:32:    appViewModel: AppViewModel = hiltViewModel(),
app/src/main/java/com/synthbyte/scanmate/ui/screens/AiScreen.kt:67:import androidx.hilt.navigation.compose.hiltViewModel
app/src/main/java/com/synthbyte/scanmate/ui/screens/AiScreen.kt:89:    val documentViewModel: DocumentViewModel = hiltViewModel()
app/src/main/java/com/synthbyte/scanmate/ui/screens/CameraScreen.kt:100:import androidx.hilt.navigation.compose.hiltViewModel
app/src/main/java/com/synthbyte/scanmate/ui/screens/CameraScreen.kt:142:    val viewModel: CameraViewModel = hiltViewModel()
app/src/main/java/com/synthbyte/scanmate/ui/screens/DocumentDetailScreen.kt:111:    val viewModel: DocumentDetailViewModel = hiltViewModel()
app/src/main/java/com/synthbyte/scanmate/ui/screens/DocumentDetailScreen.kt:96:import androidx.hilt.navigation.compose.hiltViewModel
app/src/main/java/com/synthbyte/scanmate/ui/screens/HomeScreen.kt:44:import androidx.hilt.navigation.compose.hiltViewModel
app/src/main/java/com/synthbyte/scanmate/ui/screens/HomeScreen.kt:76:    val viewModel: DocumentViewModel = hiltViewModel()
app/src/main/java/com/synthbyte/scanmate/ui/screens/PageEditorScreen.kt:102:    val viewModel: PageEditorViewModel = hiltViewModel()
app/src/main/java/com/synthbyte/scanmate/ui/screens/PageEditorScreen.kt:87:import androidx.hilt.navigation.compose.hiltViewModel
app/src/main/java/com/synthbyte/scanmate/ui/screens/QrScreen.kt:105:    val documentViewModel: DocumentViewModel = hiltViewModel()
app/src/main/java/com/synthbyte/scanmate/ui/screens/QrScreen.kt:75:import androidx.hilt.navigation.compose.hiltViewModel

app/src/main/java/com/synthbyte/scanmate/ScanMateApplication.kt:4:import dagger.hilt.android.HiltAndroidApp
app/src/main/java/com/synthbyte/scanmate/ScanMateApplication.kt:6:@HiltAndroidApp
app/src/main/AndroidManifest.xml:11:        android:name=".ScanMateApplication"
55:import dagger.hilt.android.AndroidEntryPoint
58:@AndroidEntryPoint
59:class MainActivity : ComponentActivity() {
```

## Build result
`./gradlew assembleRelease --warning-mode all` could not execute in this sandbox because the Gradle wrapper could not download Gradle 8.9 from `services.gradle.org` due blocked network/DNS.

```text
Downloading Gradle distribution: https://services.gradle.org/distributions/gradle-8.9-bin.zip
Exception in thread "main" java.io.IOException: Unable to download Gradle distribution. Check internet access or install Gradle 8.9 locally.
	at org.gradle.wrapper.GradleWrapperMain.main(GradleWrapperMain.java:59)
Caused by: java.net.UnknownHostException: services.gradle.org
	at java.base/sun.nio.ch.NioSocketImpl.connect(NioSocketImpl.java:567)
	at java.base/java.net.SocksSocketImpl.connect(SocksSocketImpl.java:327)
	at java.base/java.net.Socket.connect(Socket.java:751)
	at java.base/sun.security.ssl.SSLSocketImpl.connect(SSLSocketImpl.java:304)
	at java.base/sun.net.NetworkClient.doConnect(NetworkClient.java:178)
	at java.base/sun.net.www.http.HttpClient.openServer(HttpClient.java:531)
	at java.base/sun.net.www.http.HttpClient.openServer(HttpClient.java:636)
	at java.base/sun.net.www.protocol.https.HttpsClient.<init>(HttpsClient.java:264)
	at java.base/sun.net.www.protocol.https.HttpsClient.New(HttpsClient.java:377)
	at java.base/sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection.getNewHttpClient(AbstractDelegateHttpsURLConnection.java:193)
	at java.base/sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:1257)
	at java.base/sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:1143)
	at java.base/sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection.connect(AbstractDelegateHttpsURLConnection.java:179)
	at java.base/sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1705)
	at java.base/sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1629)
	at java.base/sun.net.www.protocol.https.HttpsURLConnectionImpl.getInputStream(HttpsURLConnectionImpl.java:223)
	at org.gradle.wrapper.GradleWrapperMain.download(GradleWrapperMain.java:95)
	at org.gradle.wrapper.GradleWrapperMain.main(GradleWrapperMain.java:57)
```
