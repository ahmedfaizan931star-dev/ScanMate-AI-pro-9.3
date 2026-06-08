# Principal Repair v3 Changes

## FIX 1 · Global async error reporting
- `DocumentDetailViewModel.kt`: `publishError()` now emits to `_exportState` and the shared async global error channel.
- `PageEditorViewModel.kt`: failures from editor operations now report to the shared async global error channel.
- `AppViewModel.kt`: converted to Hilt ViewModel and backed by retained `AppErrorBus`.
- `AppErrorBus.kt`: added retained global error bus and reporter interface.
- `AppErrorModule.kt`: added Hilt binding for the reporter interface.

## FIX 2 · FileUtils extraction
- `FileUtils.kt`: reduced to an 87-line facade preserving existing public signatures.
- `PdfExporter.kt`: extracted PDF generation, OCR text layer drawing, PDF rendering, text PDF, rendered page export, and long image export.
- `DocxExporter.kt`: extracted DOCX/XLSX/PPTX Office export logic.
- `ImageProcessor.kt`: extracted bitmap decode, save, duplicate, crop, perspective, filters, watermark, stamps, signature, and compression.
- `FileCore.kt`: added shared non-export file helpers used by the facade.

## FIX 3 · QR ViewModel boundary
- `DocumentViewModel.kt`: added `insertQrHistory()` and `clearQrHistory()`.
- `QrScreen.kt`: replaced direct DAO writes/deletes with `DocumentViewModel` calls.

## FIX 4 · PageEditor bitmap/filter state
- `PageEditorViewModel.kt`: added `workingBitmap: StateFlow<Bitmap?>`, `pushBitmap()`, and `applyFilter()`.
- `PageEditorScreen.kt`: collects ViewModel bitmap state and uses ViewModel filter application while keeping undo/redo UI-local.

## FIX 5 · Hilt migration
- `gradle/libs.versions.toml`: added Hilt and kapt plugin/dependency aliases.
- `build.gradle.kts`: added root Hilt/kapt plugin aliases.
- `app/build.gradle.kts`: applied Hilt/kapt plugins and dependencies.
- `ScanMateApplication.kt`: added `@HiltAndroidApp` application class.
- `AndroidManifest.xml`: registered `ScanMateApplication`.
- `MainActivity.kt`: annotated `@AndroidEntryPoint`, uses `hiltViewModel()`, and declares typed nav arguments for doc/page IDs.
- `DocumentViewModel.kt`, `DocumentDetailViewModel.kt`, `PageEditorViewModel.kt`, `CameraViewModel.kt`, `AppViewModel.kt`: converted to Hilt-backed ViewModels.
- Deleted `DocumentViewModelProvider.kt` and `PageEditorViewModelProvider.kt`.

## Verification artifacts
- `PRINCIPAL_HILT_REPAIR_DIFF.patch`: exact unified before/after diff.
- `VERIFY_REPORT_HILT.md`: grep verification and build attempt log.
- `FILE_MANIFEST_BEFORE_V3.tsv` / `FILE_MANIFEST_AFTER_V3.tsv`: SHA-256 manifests.
