# CHANGES — Principal Repair v2

## FIX 1 — PageEditorScreen.kt + PageEditorViewModelProvider.kt + PageEditorViewModel.kt
- Confirmed `PageEditorScreen.kt` has zero `AppDatabase` hits.
- Moved `rememberPageEditorViewModel(docId, pageId)` into dedicated `PageEditorViewModelProvider.kt` following the provider pattern.
- Removed the PageEditor provider helper from `DocumentViewModelProvider.kt`.
- Kept PageEditor composable mutations routed through `PageEditorViewModel` methods: `saveEditedPage`, `replacePageImage`, `deleteCurrentPage`, `duplicatePage`, `movePage`, `savePageOcr`.
- Added PageEditor async error state so ViewModel launch failures are surfaced to UI.

## FIX 2 — DocumentDetailScreen.kt + DocumentDetailViewModel.kt
- Added `reorderPage(pageId, newOrder)` to `DocumentDetailViewModel` with `viewModelScope.launch(Dispatchers.IO)` and page renumbering.
- Replaced data-mutation `coroutineScope.launch` blocks in `DocumentDetailScreen.kt` page management with ViewModel launch methods and completion callbacks.
- Remaining `coroutineScope.launch` hits are file/OCR UI export actions inside `OcrCard`.

## FIX 3 — AppViewModel.kt + GlobalErrorBoundary.kt + MainActivity.kt + ViewModels
- Added `AppViewModel` with `globalError: StateFlow<String?>`, `reportError`, and `clearError`.
- Updated `GlobalErrorBoundary` to render either synchronous composition exceptions or async app errors.
- Provided `AppViewModel` at the top of `MainActivity.setContent` and passed it into `GlobalErrorBoundary`.
- Wrapped `DocumentDetailViewModel` `viewModelScope.launch` bodies in `runCatching` and publish errors through `ExportState.Error`.
- Wrapped `PageEditorViewModel.deleteCurrentPage` launch body in `runCatching` and exposed a UI-visible error state.

## FIX 4 — CameraScreen.kt
- Replaced CapturedThumbnailStrip Coil model path usage with `ImageRequest.Builder(ctx).data(file).diskCacheKey(...).memoryCacheKey(...).crossfade(true).build()`.

## FIX 5 — DocumentDetailScreen.kt
- Ensured the MoreVert overflow `IconButton` and its `DropdownMenu` are anchored together inside a `Box`.
- Updated icon content description to `More options`.

## FIX 6 — FileManagerScreen.kt
- Added `FileThumb(file, modifier)` that renders image previews, a PDF badge, or a fallback file icon by extension.
- Replaced generic thumbnails in both list row and grid cell with `FileThumb(file, Modifier.size(48.dp, 60.dp))`.

## FIX 7 — HomeDocumentList.kt + HomeScreen.kt
- Added standalone `HomeDocumentEmptyState(onScanClick)` composable.
- Home empty list now calls `item(key = "home_empty") { HomeDocumentEmptyState(onScanClick = onNavigateToCamera) }`.
- Confirmed zero `@Suppress("UNUSED_PARAMETER")` annotations in `HomeDocumentList.kt`.

## FIX 8 — HomeHeroCard.kt
- Removed HeroStat boxes and replaced them with a context-aware greeting and pinned count text only when pinned documents exist.

## FIX 9 — app/build.gradle.kts + GeminiApiService.kt + proguard-rules.pro
- Confirmed logging interceptor dependency is `debugImplementation(libs.logging.interceptor)`.
- Kept Gemini OkHttp logging behind `BuildConfig.DEBUG` using reflection so release source compilation does not require the debug-only logging artifact.
- Added/normalized R8 no-side-effects rule for `HttpLoggingInterceptor.log(String)`.

## FIX 10 — DocDao.kt + DocumentViewModel.kt + QrScreen.kt
- Added fixed Room query `getQrHistory(): Flow<List<QrHistory>>` with `LIMIT 50`.
- Confirmed `DocumentViewModel.qrHistory` exposes DAO QR history.
- Added QR history `Recent` section to `QrScreen` with a copy `IconButton` per entry.

## Verification
- See `VERIFY_REPORT.md`.
- Full exact unified diff is in `PRINCIPAL_REPAIR_DIFF.patch`.
