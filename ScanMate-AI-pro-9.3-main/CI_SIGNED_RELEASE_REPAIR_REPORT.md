# ScanMate AI Pro — Signed Release CI Repair

## Goal
Make GitHub Actions build **Debug APK + signed Release APK + signed Release AAB** using the repository secrets already configured by the owner.

## Changed files

### `.github/workflows/android-build.yml`
- Release signing is now mandatory.
- Validates required secrets before building:
  - `SIGNING_KEY`
  - `KEY_STORE_PASSWORD` or legacy `STORE_PASSWORD`
  - `KEY_ALIAS`
  - `KEY_PASSWORD`
- Decodes the base64 JKS into `app/keystore/scanmate-release.jks`.
- Validates the keystore alias with `keytool` before Gradle starts release builds.
- Uses the newest installed Android SDK `apksigner`, instead of hardcoding `build-tools/34.0.0`.
- Uploads artifacts with `if-no-files-found: error`.
- Names release artifacts as signed APK/AAB.

### `app/build.gradle.kts`
- Keeps environment-secret signing support.
- Ignores placeholder signing values such as `YOUR_KEYSTORE_PASSWORD`.
- Adds `SCANMATE_REQUIRE_RELEASE_SIGNING=true` support.
- When strict signing is required, Gradle fails clearly instead of silently producing an unsigned release.

### `keystore.properties`
- Removed from the project ZIP because real signing credentials should not be committed.

### `keystore.properties.example`
- Added as a safe local template only.

## Required GitHub secrets
Use these exact names:

```text
SIGNING_KEY
KEY_STORE_PASSWORD
KEY_ALIAS
KEY_PASSWORD
```

`STORE_PASSWORD` is still accepted as a legacy fallback, but `KEY_STORE_PASSWORD` is preferred.

## Expected GitHub Actions output
- `ScanMate-AI-Pro-debug-apk`
- `ScanMate-AI-Pro-release-apk-signed`
- `ScanMate-AI-Pro-release-aab-signed`
