# ScanMate AI Pro — Scanner UI/UX + Edge Overlay Polish Report

## Goal
This pass focused on the signed APK scanner runtime issue and camera/editor usability without removing features or changing package name, Room schema, or navigation routes.

## Implemented

### 1. Signed APK edge overlay visibility
- `DocumentOverlay` no longer returns early when detection is null.
- A default scanner guide frame is always drawn so the camera screen never appears blank.
- Live detected corners still replace the guide when `EdgeAnalyzer` returns a document candidate.

### 2. Edge detection tuning
- Lowered overly strict confidence gate in `EdgeAnalyzer`.
- Made projection bounds more tolerant for low-light / low-contrast pages.
- Increased missed-frame tolerance so the overlay does not disappear immediately.
- Reduced threshold/gap strictness to improve real camera detection recall.

### 3. Camera Material 3 polish
- Camera preview mapping changed to `FIT_CENTER` to reduce crop/mapping mismatch between CameraX analysis and overlay coordinates.
- Added analyzer frame state, clearer scanner hints, and an edge-confidence guide chip.
- Capture button now has a Material-style ring/progress state instead of an empty white button.
- Auto-capture remains guarded by stability/cooldown, but detection display is less silent.

### 4. Crop UX improvement
- Manual crop dialog now uses presets: Wide, Balanced, Tight.
- Removed percent-style user-facing crop labels.
- Fine-tune sliders are labeled as edge controls with “Keep more / Trim more.”
- Existing draggable perspective corner tool remains available.

### 5. ML Kit OCR setup
- Added ML Kit manifest dependency metadata for OCR and barcode models:
  `com.google.mlkit.vision.DEPENDENCIES = ocr,barcode`.
- Existing OCR rotation and multi-candidate OCR preprocessing pipeline remains intact.

## Files changed
- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/synthbyte/scanmate/ui/components/DocumentOverlay.kt`
- `app/src/main/java/com/synthbyte/scanmate/ui/screens/CameraScreen.kt`
- `app/src/main/java/com/synthbyte/scanmate/ui/screens/PageEditorScreen.kt`
- `app/src/main/java/com/synthbyte/scanmate/util/EdgeAnalyzer.kt`

## Verification performed in this environment
- ZIP/source patch prepared.
- Release sanity script passed.
- Basic brace/parenthesis balance checked for changed Kotlin files.
- Full Gradle/Android build cannot be run in this sandbox due missing Android SDK/dependency environment; GitHub Actions remains the required final verification.

## Required device testing after GitHub Actions green
1. Signed APK opens.
2. Camera screen always shows a scanner guide.
3. Live edge border appears when a document is placed under good lighting.
4. Auto capture does not fire repeatedly.
5. Manual capture works even when live detection is weak.
6. Crop presets and corner crop work.
7. OCR, PDF export, DOCX export, QR, Vault, and Settings still work.
