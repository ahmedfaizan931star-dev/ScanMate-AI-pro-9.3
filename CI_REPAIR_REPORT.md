# ScanMate AI Pro — GitHub Actions Signed Release Repair Report

Date: 2026-06-03

## Goal
Make the uploaded ScanMate AI Pro project reliable in GitHub Actions **with your existing signing secrets**.

The workflow is now strict: it must build Debug APK, signed Release APK, and signed Release AAB. It must not silently upload unsigned release artifacts.

## Main CI risks fixed

1. The original workflow only decoded the keystore when `SIGNING_KEY` existed, but release builds still ran even if required signing inputs were incomplete.
2. The original APK signature verification hardcoded `build-tools/34.0.0`, which can fail on GitHub runners if that exact folder is unavailable.
3. The project contained a placeholder `keystore.properties`, which could confuse local or CI signing detection.
4. Gradle could attach release signing too early or fail with a vague keystore/password error.

## Patch applied

### `.github/workflows/android-build.yml`
- Makes release signing mandatory.
- Validates required GitHub secrets before release builds:
  - `SIGNING_KEY`
  - `KEY_STORE_PASSWORD` or legacy `STORE_PASSWORD`
  - `KEY_ALIAS`
  - `KEY_PASSWORD`
- Decodes `SIGNING_KEY` into `app/keystore/scanmate-release.jks`.
- Validates the JKS alias using `keytool` before Gradle release tasks run.
- Builds Debug APK.
- Builds signed Release APK.
- Builds signed Release AAB.
- Verifies APK signature using the latest installed Android SDK build-tools folder.
- Uploads artifacts with `if-no-files-found: error`.

### `app/build.gradle.kts`
- Reads signing values from GitHub Actions environment variables.
- Supports `KEY_STORE_PASSWORD`, internal `SCANMATE_STORE_PASSWORD`, and legacy `STORE_PASSWORD`.
- Ignores placeholder values beginning with `YOUR_`.
- Adds `SCANMATE_REQUIRE_RELEASE_SIGNING=true`.
- Fails clearly when strict signing is required but the keystore/password/alias config is incomplete.

### Signing files
- Removed committed `keystore.properties` placeholder file.
- Added `keystore.properties.example` as a safe local template.

## Required GitHub secrets
Use these exact names:

```text
SIGNING_KEY
KEY_STORE_PASSWORD
KEY_ALIAS
KEY_PASSWORD
```

`STORE_PASSWORD` is still accepted as a fallback if your previous workflow used that name, but `KEY_STORE_PASSWORD` is preferred.

## Expected GitHub Actions artifacts

```text
ScanMate-AI-Pro-debug-apk
ScanMate-AI-Pro-release-apk-signed
ScanMate-AI-Pro-release-aab-signed
```

## Verification performed here
- Workflow YAML parses successfully.
- Static checks confirm no hardcoded `build-tools/34.0.0` verifier remains.
- Static checks confirm no `if:` line uses `secrets.*` directly.
- Static checks confirm `SCANMATE_REQUIRE_RELEASE_SIGNING` exists in Gradle.
- Full Gradle build could not run in this sandbox because it has no network access to download Gradle 8.9 and no Android SDK. GitHub Actions should have both.
