# ScanMate AI Pro Phase 1 + Phase 2 Stability Report

## Scope

This pass focused on release runtime stability and scanner edge-detection quality only.

## Phase 1 — Signed APK DOCX Crash Repair

### Problem
The signed release APK showed a runtime error around the previous POI/XWPF DOCX path. Debug builds could pass because debug builds are not aggressively optimized by R8.

### Fix
- Replaced the Apache POI `XWPFDocument` runtime export path with a lightweight Office Open XML DOCX writer based on `ZipOutputStream`.
- The exporter now writes the required DOCX package parts directly:
  - `[Content_Types].xml`
  - `_rels/.rels`
  - `docProps/core.xml`
  - `docProps/app.xml`
  - `word/document.xml`
  - `word/_rels/document.xml.rels`
  - `word/styles.xml`
  - `word/settings.xml`
- Removed the unused `org.apache.poi:poi-ooxml:5.2.3` runtime dependency from `app/build.gradle.kts`.
- Removed the unused POI version pin from `gradle/libs.versions.toml`.
- Sanitized invalid XML control characters from OCR text before DOCX export.

### Expected result
- Release APK should no longer show the DOCX/XWPF runtime crash.
- R8 should have less heavy desktop-Java dependency surface.
- AAB/APK size may reduce.

## Phase 2 — Scanner / Edge Detection Upgrade

### Fixes and improvements
- Replaced bitmap-heavy analyzer preprocessing with direct Y-plane luma sampling.
- Added adaptive brightness thresholding using percentiles.
- Added gradient/edge scoring instead of bright-area-only detection.
- Added projection smoothing and gap-tolerant document bounds extraction.
- Added skew-aware quadrilateral estimation from top/bottom edge bands.
- Added temporal smoothing of corners and confidence to reduce jumping overlays.
- Added rotation-aware smoothing reset.
- Tuned auto-capture to require higher confidence and stable frames.
- Improved perspective correction output sizing and point ordering.

### Expected result
- More stable document overlay.
- Fewer false auto captures.
- More reliable auto crop/perspective correction.
- Better camera-scanner feel without adding risky native/OpenCV dependencies.

## Changed files

- `app/src/main/java/com/synthbyte/scanmate/utils/DocxExporter.kt`
- `app/src/main/java/com/synthbyte/scanmate/util/EdgeAnalyzer.kt`
- `app/src/main/java/com/synthbyte/scanmate/ui/screens/CameraScreen.kt`
- `app/src/main/java/com/synthbyte/scanmate/utils/ImageProcessor.kt`
- `app/build.gradle.kts`
- `gradle/libs.versions.toml`
- `app/proguard-rules.pro`

## Verification performed in this environment

- Kotlin syntax checked for `EdgeAnalyzer.kt` using local stubs.
- Kotlin syntax checked for `DocxExporter.kt` using local stubs.
- Kotlin syntax checked for `ImageProcessor.kt` using local stubs.
- Generated a sample DOCX with the new package writer and verified ZIP integrity using `unzip -t`.

## Verification not performed here

Full Android Gradle build could not be run in this sandbox because Gradle distribution/dependencies require internet access and Android SDK tooling. GitHub Actions should run the full authoritative check after pushing.

## Post-push checklist

1. Push the fixed project to `main`.
2. Confirm GitHub Actions green.
3. Download signed release APK.
4. Test DOCX export in signed release APK.
5. Test auto edge detection on Android 11 and Android 16 phones.
6. Only proceed to Play Console after release APK runtime testing passes.
