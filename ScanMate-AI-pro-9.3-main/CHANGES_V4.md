# ScanMate AI Pro V4 Changes

## DocumentDetailScreen.kt
- Replaced composition-blocking bitmap decode in document preview with `produceState` + IO dispatcher.
- Replaced LazyRow thumbnail decode in `remember {}` with `produceState` + IO dispatcher.
- Added loading indicators for pending bitmap decode.

## DocumentDetailViewModel.kt
- Fixed duplicate `reorderPage(...)` declaration found in the v3 baseline.
- Added `publishErrorMessage(...)` so direct export error states also report through the global async error channel.
- Made `exportDocx(null)` emit `ExportState.Error` instead of silently returning.

## CameraViewModel.kt
- Added `previewBitmap: StateFlow<Bitmap?>`.
- Added `applyPreviewFilter(bitmap, filter)`.
- Added `sharpenCapturedImageFile(photoFile)` to keep MAX-quality sharpening work out of the composable.

## CameraScreen.kt
- Removed direct bitmap filter helper and `FileUtils.applyFilter(bitmap, FilterType.SHARPEN)` call.
- Routed MAX-quality sharpening through `CameraViewModel`.

## PageEditorViewModel.kt
- Added `loadPageBitmap(path)` using `ImageProcessor.decodeSampledBitmap(...)` on `Dispatchers.IO`.

## PageEditorScreen.kt
- Replaced direct large bitmap decode calls with `viewModel.loadPageBitmap(path)`.

## OcrHelper.kt
- Added a tiny-image guard to `estimateSkewAndRotate(...)`.
- Reordered skew-angle scoring to prefer `0f` on ties, preventing blank/low-signal images from rotating unnecessarily.

## app/build.gradle.kts + libs.versions.toml
- Added Hilt testing dependency alias and kapt test compiler wiring.

## Tests
- Added `ExportStateTest.kt`.
- Added `OcrHelperTest.kt`.
- Added `FileUtilsFacadeTest.kt`.
- Added `PdfExporterTest.kt`.
- Added `MainDispatcherRule.kt`.
