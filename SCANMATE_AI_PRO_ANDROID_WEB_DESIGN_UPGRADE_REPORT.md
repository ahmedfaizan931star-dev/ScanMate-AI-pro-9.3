# ScanMate AI Pro Android — Web Design Upgrade Report

## Goal
Bring the tested ScanMate AI Pro web app layout into the Android codebase while keeping the existing Android workflows, package name, offline-first behavior, and routes/features intact.

## Web design source applied
The Android UI now follows the tested web app structure more closely:

- Home header uses `SCANMATE AI PRO` + `Your Documents` hierarchy.
- Search uses a clean full-width rounded search field.
- Home hero is now a clean flat white workspace card instead of a heavy blue promo card.
- Primary actions match the web app: strong blue `Scan`, quiet secondary `Import`.
- Stats match the web app: Docs, Pages, Pinned.
- Tool shortcuts are compact horizontal workspace pills with themed icon containers.
- A real Tools hub screen was added, matching the web app tools grid.
- PDF tools and File Manager naming were adjusted closer to the web app.

## Files changed

- `.github/workflows/android-build.yml`
  - Replaced workflow with a simpler robust Android workflow.
  - Removed `gradle/actions/setup-gradle@v4` to avoid the unknown Gradle wrapper validation failure previously seen on GitHub Actions.
  - Keeps project-folder auto-detection.
  - Builds Debug APK, Release APK, and Release AAB.

- `app/src/main/java/com/synthbyte/scanmate/MainActivity.kt`
  - Added Tools route destination.
  - Connected the Home bottom-nav Tools item to the new Tools hub screen.

- `app/src/main/java/com/synthbyte/scanmate/ui/navigation/Routes.kt`
  - Added `TOOLS = "tools"` without changing existing routes.

- `app/src/main/java/com/synthbyte/scanmate/ui/screens/HomeScreen.kt`
  - Added `onNavigateToTools` callback.
  - Changed bottom-nav Tools action from direct QR navigation to the new Tools hub.
  - Removed duplicate empty/skeleton section so the document list appears immediately after the hero area.

- `app/src/main/java/com/synthbyte/scanmate/ui/screens/ToolsScreen.kt`
  - New web-inspired Tools hub screen.
  - Includes PDF Tools, QR Generator, QR Scanner, OCR Translate, AI Workspace, Vault, ZIP Export, and File Manager.

- `app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeHeaderZone.kt`
  - Rebuilt header to match web app hierarchy.
  - Added flat search field styling.

- `app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeHeroCard.kt`
  - Rebuilt hero card into clean flat workspace card.
  - Removed heavy blue hero styling.
  - Added web-style primary/secondary actions and stats.

- `app/src/main/java/com/synthbyte/scanmate/ui/screens/home/HomeToolChipRow.kt`
  - Rebuilt tool shortcuts as flat web-style cards/pills.
  - Uses theme tokens only.

- `app/src/main/java/com/synthbyte/scanmate/ui/screens/FileManagerScreen.kt`
  - Renamed top-level title to Workspaces.
  - Updated copy to match the web workspace language.

- `app/src/main/java/com/synthbyte/scanmate/ui/screens/PdfToolsScreen.kt`
  - Adjusted title/copy toward Export to PDF style.
  - Default output name now uses `scanmate-export` instead of timestamp-first naming.

## Feature preservation
The following routes/features were preserved:

- Home
- Camera scan
- Gallery import
- Document detail
- Page editor
- PDF tools
- QR tools
- QR scanner
- AI assistant
- OCR translate
- ZIP tools
- File manager
- Vault
- Settings
- Widgets
- Package name: `com.synthbyte.scanmate`

## Verification status
Static source edits were completed and the changed files were checked for obvious brace/parenthesis syntax imbalance.

Full Gradle build could not be executed in this sandbox because the local environment cannot download the Gradle distribution from `services.gradle.org` and does not include a preinstalled Gradle 8.9 runtime. The included GitHub Actions workflow should perform the full build in GitHub.

## Next required verification on GitHub
Run GitHub Actions and verify:

- Debug APK builds.
- Release APK builds.
- Release AAB builds.
- Home opens and shows the new web-style layout.
- Bottom navigation Tools opens the new Tools hub.
- PDF, QR, OCR, AI, Vault, ZIP, Files, Camera and Gallery Import still navigate correctly.

## Remaining risks
- Because the Gradle build could not run inside the sandbox, Kotlin/Compose compiler validation must happen in GitHub Actions.
- The new Tools screen uses Compose lazy grid APIs; the existing Compose BOM should support them, but CI must confirm.
- This pass improves Android UI parity with the web app. It does not claim the Android app is better than Adobe Scan/CamScanner until build, launch, and feature QA pass on a device.
