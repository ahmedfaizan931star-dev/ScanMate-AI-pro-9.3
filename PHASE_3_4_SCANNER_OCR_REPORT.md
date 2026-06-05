# ScanMate AI Pro — Phase 3 + Phase 4 Scanner/OCR Repair

## Scope
This pass continues from the Phase 1 + 2 ZIP and focuses only on scanner output stability and OCR quality:

- Phase 3: better crop, perspective correction, and scan enhancement.
- Phase 4: OCR preprocessing, layout reconstruction, and fallback recognition.

No package name, Room schema, navigation route, cloud/login/backend, or Firebase changes were made.

## Changed files

- `app/src/main/java/com/synthbyte/scanmate/ui/screens/CameraScreen.kt`
- `app/src/main/java/com/synthbyte/scanmate/utils/ImageProcessor.kt`
- `app/src/main/java/com/synthbyte/scanmate/utils/OcrHelper.kt`

## Phase 3 changes

### Camera capture pipeline
- Perspective correction now runs synchronously in the capture coroutine before the page is added to the captured page list.
- This avoids a race where the user could finish/save the document before the async perspective-correction job replaced the raw page.
- Captured images and filtered images are recycled after use to reduce memory pressure.

### Perspective correction
- Increased decode target for capture correction to preserve more detail.
- Added a shared `perspectiveWarp` implementation for both camera auto-crop and manual perspective correction.
- Output dimensions are now based on the detected page edge lengths instead of forcing the original frame size.
- Added a maximum output side to avoid huge bitmap allocations on very high-resolution photos.
- Added scan cleanup after perspective correction before writing the JPEG output.

### Auto crop and scan enhancement
- Replaced fixed-threshold auto crop with adaptive document-bound estimation.
- Added border/background luminance estimation, percentile-based thresholds, projection smoothing, and margin cleanup.
- Added `cleanDocumentBitmap` for shadow reduction, paper whitening, contrast normalization, and optional color preservation.
- Fixed the whiteboard filter path so it produces white background + dark text instead of inverted output.

## Phase 4 changes

### OCR pipeline
- OCR no longer depends on one preprocessing output only.
- Added multi-candidate OCR recognition:
  1. deskewed base image
  2. cleaned document image
  3. grayscale image
  4. adaptive black-and-white image
  5. high-contrast image
- The best OCR result is selected using confidence, word count, text length, and line-structure score.

### OCR layout reconstruction
- Reworked paragraph reconstruction to preserve line breaks instead of flattening whole blocks into one sentence.
- Removed aggressive automatic period insertion that could corrupt receipts, lists, codes, names, and form fields.
- Lines are sorted by bounding boxes and grouped into paragraphs by vertical spacing.

## Verification performed in sandbox

- Static file inspection for changed Kotlin files.
- Brace/parenthesis balance checks for changed Kotlin files.
- Diff generated in `PHASE_3_4_DIFF.patch`.

## Not verified here

A full Gradle/Android build could not be run in this sandbox because the Gradle wrapper needs to download Gradle from `services.gradle.org`, and external network access is blocked here.

Run these in GitHub Actions/Codespaces:

```bash
./gradlew clean assembleDebug --stacktrace
./gradlew assembleRelease --stacktrace
./gradlew bundleRelease --stacktrace
```

## Acceptance testing on device

After GitHub Actions passes, test signed Release APK first:

- Camera opens without crash.
- Auto edge detection overlay appears and stays stable.
- Capture saves the perspective-corrected page, not the raw page.
- Manual perspective correction still works in the editor.
- Whiteboard filter is not inverted.
- OCR returns readable text from clean document photos.
- OCR preserves line breaks for receipts/forms/lists.
- PDF/DOCX export still works.
