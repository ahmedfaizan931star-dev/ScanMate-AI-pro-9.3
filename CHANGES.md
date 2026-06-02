# CHANGES.md

## 2026-05-31 — Six-bug export and OOXML repair pass

### Fix 1 — Legal page-size chip clipping
- **File:** `app/src/main/java/com/synthbyte/scanmate/ui/screens/DocumentDetailScreen.kt`
- **Line range:** 431-447
- Replaced the page-size chip row with a horizontally scrollable row.
- Each page-size chip now uses `wrapContentWidth()`, 44dp height, 16dp label-side padding, and `RoundedCornerShape(10.dp)` with no weight/fill-width chip sizing.

### Fix 2 — PDF export name default
- **File:** `app/src/main/java/com/synthbyte/scanmate/ui/screens/DocumentDetailScreen.kt`
- **Line range:** 153-159 and 171-178
- Export name now derives from `FileUtils.sanitizeFileBaseName(title)` once the document is loaded.
- Blank titles fall back to `ScanMate_Export`, not `ScanMate_2`/document-id naming.

### Fix 3 — PDF compression quality now affects output
- **File:** `app/src/main/java/com/synthbyte/scanmate/utils/PdfExporter.kt`
- **Line range:** 60-103, 122-143, 181-200, and 225-249
- Added JPEG quality/downscale compression before each page draw.
- `SMALL`, `BALANCED`, and `HIGH` now use different scale/JPEG quality values.
- Intermediate and source bitmaps are recycled in `finally` blocks.

### Fix 4 — Export confirmation button
- **File:** `app/src/main/java/com/synthbyte/scanmate/ui/screens/DocumentDetailScreen.kt`
- **Line range:** 166-178 and 517-535
- Added a bottom action row with `Cancel` and `Export PDF` buttons.
- Export now uses the selected quality option and existing protected-PDF validation before closing the dialog.

### Fix 5 — Export dialog no longer cuts off content
- **File:** `app/src/main/java/com/synthbyte/scanmate/ui/screens/DocumentDetailScreen.kt`
- **Line range:** 413-421
- Added `DialogProperties(usePlatformDefaultWidth = false)`.
- Dialog card uses `Modifier.fillMaxWidth(0.92f)`.
- Dialog content column is vertically scrollable with `verticalScroll(rememberScrollState())`.

### Fix 6 — DOCX/PPTX corruption repair
- **File:** `app/src/main/java/com/synthbyte/scanmate/utils/DocxExporter.kt`
- **Line range:** 276-400
- DOCX export now writes a valid OOXML package with:
  - `[Content_Types].xml`
  - `_rels/.rels`
  - `word/document.xml`
  - `word/_rels/document.xml.rels`
  - `word/styles.xml`
  - `word/settings.xml`
- All generated DOCX XML parts start with `<?xml version="1.0" encoding="UTF-8" standalone="yes"?>` and use UTF-8 without BOM.
- `[Content_Types].xml` is written as a STORED zip entry with size and CRC populated.
- Existing PPTX export path was verified to include required presentation, slide, layout, master, theme, and relationship parts; no PPTX option was removed.

### Self-verification summary
- Line counts increased or stayed above original for all changed source files.
- `DocumentDetailScreen.kt` has no `Color(0x...)` usage after this patch.
- DOCX required package entries and PPTX required source entries passed static verification.
- `./gradlew assembleRelease --warning-mode all` was attempted, but the sandbox could not resolve `services.gradle.org` to download Gradle 8.9. The failure occurs before Android/Kotlin compilation.

---

# ScanMate AI Pro — Final 3-Fix Pass

## Scope control
- Source files inspected for this pass: 171 files.
- Source files modified: 2 Kotlin files.
- Allowed target files checked:
  1. `app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeDocumentList.kt`
  2. `app/src/main/java/com/synthbyte/scanmate/ui/screens/AiScreen.kt`
  3. `app/src/main/java/com/synthbyte/scanmate/ui/screens/HomeScreen.kt`
- No Gradle, Room schema, navigation route, backend, auth, Firebase, cloud, or subscription files were modified.

## Fix 1 — HomeDocumentList.kt
**File:** `app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeDocumentList.kt`

**Verification:** line 1–178 inspected. This file contains no `LazyColumn` `items()` calls, so there was no `items(documents) { ... }` call to patch in this attached ZIP.

**Result:** `grep -n "items(" HomeDocumentList.kt` returns zero hits.

## Fix 2 — AiScreen.kt
**File:** `app/src/main/java/com/synthbyte/scanmate/ui/screens/AiScreen.kt`

**Changed lines:** 227–231

**Change:** Removed the duplicate grey offline status message from the `OutlinedTextField` supporting text below the input field. The retained offline notice is the inline banner above the input box.

**Result:** `grep -n "Offline intelligence is active" AiScreen.kt` returns zero hits.

## Fix 3 — HomeScreen.kt
**File:** `app/src/main/java/com/synthbyte/scanmate/ui/screens/HomeScreen.kt`

**Changed lines:** 8–35, 177–214

**Change:** Replaced the filter-row call with the requested `FilterChip` implementation:
- Selected chip uses `primary` / `onPrimary`.
- Unselected chip uses `surface` plus a 1.dp `outline` border.
- All chips use `RoundedCornerShape(20.dp)`, bold label text, and `12.sp`.

**Result:** `grep -n "selectedContainerColor = MaterialTheme.colorScheme.primary" HomeScreen.kt` confirms selected styling, and `grep -n "borderColor = MaterialTheme.colorScheme.outline" HomeScreen.kt` confirms unselected border styling.

## Final confirmation
- [x] HomeDocumentList.kt checked for every `items()` call; there are zero `items()` calls in this ZIP.
- [x] AiScreen.kt duplicate grey offline message removed.
- [x] HomeScreen.kt filter chips are visually distinct: selected primary, unselected bordered surface.
- [x] No schema or navigation routes changed.
- [x] No cloud, login, backend, Firebase, auth, or subscription added.
- [x] No hardcoded `Color(0x...)` added to modified UI files.


## Build attempt
Command run from project root:

```bash
bash ./gradlew assembleRelease --warning-mode all
```

Result: Gradle wrapper could not download Gradle 8.9 in this sandbox because DNS/network access to `services.gradle.org` is blocked. Compilation did not start here.

Exit code: `1`

## 2026-05-31 — PDF, Office Export, Dispatcher, and Signing Fix Pass

### Fix 1 — True PDF password protection
- `gradle/libs.versions.toml`: lines 33-34 add `pdfbox-android` and `poi-ooxml` version pins.
- `app/build.gradle.kts`: lines 159-163 add PDFBox Android and Apache POI dependencies.
- `app/src/main/java/com/synthbyte/scanmate/utils/PdfExporter.kt`: lines 17-20 import PDFBox Android encryption APIs.
- `app/src/main/java/com/synthbyte/scanmate/utils/PdfExporter.kt`: lines 47-65 apply PDFBox `StandardProtectionPolicy` with print/copy permission control after the PDF is generated.

### Fix 2 — DOCX/PPTX Office export hardening
- `app/src/main/java/com/synthbyte/scanmate/utils/DocxExporter.kt`: line 14 imports Apache POI `XWPFDocument`.
- `app/src/main/java/com/synthbyte/scanmate/utils/DocxExporter.kt`: lines 293-309 generate DOCX through Apache POI instead of hand-built WordprocessingML.
- `app/src/main/java/com/synthbyte/scanmate/utils/DocxExporter.kt`: lines 80-177 keep the Android-compatible PPTX exporter with required OOXML package parts, slide size, slide master, slide layout, theme, and slide relationships.

### Fix 3 — Image processing dispatcher cap
- `app/src/main/java/com/synthbyte/scanmate/utils/ImageProcessor.kt`: line 24 adds `ImageProcessingDispatcher = Dispatchers.Default.limitedParallelism(2)` for bounded CPU image work.
- `app/src/main/java/com/synthbyte/scanmate/utils/OcrHelper.kt`: verified no `withContext(Dispatchers.Default)` calls remain to replace.

### Fix 4 — Release signing for APK and AAB
- `app/build.gradle.kts`: lines 16-36 load signing values from environment, `keystore.properties`, or Gradle properties.
- `app/build.gradle.kts`: lines 56-62 create the release signing config.
- `app/build.gradle.kts`: line 81 applies the release signing config to release APK and AAB outputs.
- `.github/workflows/android-build.yml`: lines 38-66 decode the GitHub Actions keystore secret, build signed APK/AAB outputs, and verify the APK signature with `apksigner`.
- `keystore.properties`: lines 1-5 provide the local signing template only.
- `.gitignore`: lines 3-7 exclude signing secrets and keystore files.
- `README.md`: lines 18-46 document the required GitHub Actions signing secrets and local keystore generation commands.

### UI color safety check
- `app/src/main/java/com/synthbyte/scanmate/ui/components/DocumentOverlay.kt`: line 52 uses `MaterialTheme.colorScheme` for the animated overlay color instead of hardcoded UI hex colors.
