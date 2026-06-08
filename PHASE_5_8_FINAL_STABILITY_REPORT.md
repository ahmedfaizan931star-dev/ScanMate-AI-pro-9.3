# ScanMate AI Pro — Phase 5 to Phase 8 Final Stability Pass

## Scope

This pass was applied on top of the Phase 1–4 repaired project.

The goal was not to add random new features. The goal was to harden the release candidate so the already-fixed scanner, DOCX export, OCR, signed APK, and AAB pipeline are less likely to regress.

## Phase 5 — Stability rules implemented

- Kept package name unchanged: `com.synthbyte.scanmate`.
- Kept Room schema and navigation routes unchanged.
- Kept offline-first behavior unchanged.
- Kept GitHub signed release pipeline unchanged except for adding a safe static sanity-check step.
- Added scanner auto-capture cooldown to prevent repeated accidental captures after one stable detection.
- Added better scanner guidance text so the user gets clear feedback instead of guessing why auto edge detection has not locked.
- Added OCR preprocessing compatibility method so existing tests and future unit-test runs do not fail after the OCR pipeline refactor.

## Phase 6 — Files inspected/changed

Changed files:

- `.github/workflows/android-build.yml`
- `app/src/main/java/com/synthbyte/scanmate/ui/screens/CameraScreen.kt`
- `app/src/main/java/com/synthbyte/scanmate/utils/OcrHelper.kt`
- `app/src/test/java/com/synthbyte/scanmate/DocxExporterTest.kt`
- `app/src/test/java/com/synthbyte/scanmate/ReleaseRuntimeGuardTest.kt`
- `scripts/verify_release_candidate.sh`

Previously implemented Phase 1–4 items remain in place:

- Apache POI/XWPF runtime path removed from DOCX export.
- DOCX export uses a lightweight `ZipOutputStream` Office package writer.
- Edge detection uses Y-plane luma sampling, adaptive thresholds, edge scoring, skew-aware corners, and smoothing.
- Crop/perspective correction and OCR multi-candidate preprocessing remain in place.

## Phase 7 — Build/test readiness implemented

Added `scripts/verify_release_candidate.sh`, then added it to GitHub Actions before the signed build steps.

The sanity check verifies:

- No `XWPFDocument`, `poi-ooxml`, or `org.apache.poi` production/runtime references in Gradle or main source.
- DOCX exporter still uses `ZipOutputStream` and writes `word/document.xml`.
- Edge analyzer still contains luma-frame analysis and temporal smoothing.
- OCR helper still contains the multi-candidate recognition pipeline.
- GitHub Actions still requires strict release signing through `SCANMATE_REQUIRE_RELEASE_SIGNING`.

## Phase 8 — Acceptance criteria status

Static acceptance criteria now covered:

- Signed APK DOCX crash source removed from production runtime path.
- DOCX package writer validated through a new unit test.
- Apache POI/XWPF runtime regression guarded through a new unit test.
- Auto edge detection behavior hardened with confidence threshold + frame stability + cooldown.
- OCR test compatibility restored after refactor.
- GitHub Actions now catches accidental POI/XWPF reintroduction before the build.

## Verification performed in this sandbox

Completed:

- `bash scripts/verify_release_candidate.sh` passed.
- Basic Kotlin brace/parenthesis/package balance checks passed for changed Kotlin files.
- Confirmed no production `XWPFDocument`, `poi-ooxml`, or `org.apache.poi` runtime references remain.
- Generated `PHASE_5_8_DIFF.patch`.

Not completed here:

- Full Android Gradle build could not be run because this sandbox has no internet access to download the Gradle distribution/Android dependencies.

Required external verification after pushing:

```bash
./gradlew clean assembleDebug --stacktrace
./gradlew assembleRelease --stacktrace
./gradlew bundleRelease --stacktrace
```

Then test signed APK on device:

- App launch
- Camera screen
- Auto edge detection
- Manual capture
- DOCX export
- PDF export
- OCR
- QR tools
- Vault/settings

## Important note

This is a strong offline scanner implementation, but it is not Samsung proprietary code. It is a Samsung-style stability and UX approach implemented with your existing offline Android/Kotlin stack.
