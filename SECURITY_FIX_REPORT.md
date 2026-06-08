# ScanMate AI Pro Security Fix Report

## Scope
Patched only the requested security, navigation, and UX files. Existing features, screens, methods, routes, and XML entries were preserved; additions/replacements are documented in `SECURITY_FIX_DIFFS.patch`.

## Line-count preservation check
| File | Before | After | Result |
|---|---:|---:|---|
| `AndroidManifest.xml` | 83 | 86 | PASS |
| `proguard-rules.pro` | 44 | 53 | PASS |
| `PdfExporter.kt` | 420 | 420 | PASS |
| `DocumentDetailScreen.kt` | 787 | 821 | PASS |
| `EncryptedVaultUtils.kt` | 80 | 86 | PASS |
| `SettingsRepository.kt` | 86 | 107 | PASS |
| `app/build.gradle.kts` | 176 | 177 | PASS |
| `gradle/libs.versions.toml` | 88 | 90 | PASS |
| `data_extraction_rules.xml` | 15 | 23 | PASS |
| `backup_rules.xml` | 7 | 11 | PASS |
| `ToolsScreen.kt` | 287 | 287 | PASS |
| `VaultScreen.kt` | 245 | 266 | PASS |
| `network_security_config.xml` | 0 | 8 | PASS |

## Fixes applied
- **SECURITY FIX 1 — `AndroidManifest.xml`:** Added USE_BIOMETRIC and USE_FINGERPRINT permissions before the application block.
- **SECURITY FIX 2 — `proguard-rules.pro`:** Added missing iTextG/BouncyCastle dontwarn/keep release rules while preserving existing rules.
- **SECURITY FIX 3 — `PdfExporter.kt`:** Replaced predictable `${userPassword}_owner` owner password derivation with a 32-byte SecureRandom owner key.
- **SECURITY FIX 4 — `DocumentDetailScreen.kt`:** Added confirm password state, minimum length check, mismatch feedback, and enforced valid matching password before protected export.
- **SECURITY FIX 5 — `EncryptedVaultUtils.kt`:** Set AES key size to 256 bits and added requested user-auth parameter handling on Android 11+.
- **SECURITY FIX 6 — `SettingsRepository.kt + app/build.gradle.kts + gradle/libs.versions.toml`:** Moved Gemini API key storage to EncryptedSharedPreferences with legacy DataStore migration and added androidx.security:security-crypto.
- **SECURITY FIX 7 — `data_extraction_rules.xml + backup_rules.xml`:** Excluded scanmate_database, WAL/SHM files, and datastore folder from cloud/device backup paths.
- **NAVIGATION FIX 8 — `ToolsScreen.kt`:** Routed Erase Marks, Auto Deskew, and Shadow Removal to camera flow so users can reach page editing tools.
- **UX FIX 9 — `DocumentDetailScreen.kt`:** Added password strength indicator using Weak/Fair/Strong labels and progress feedback.
- **UX FIX 10 — `VaultScreen.kt`:** Added authError state and Try again button after biometric cancellation/failure.
- **SECURITY FIX 11 — `proguard-rules.pro`:** Added androidx.security.crypto and Tink keep/dontwarn rules.
- **SECURITY FIX 12 — `AndroidManifest.xml + network_security_config.xml`:** Added application-level networkSecurityConfig and a cleartextTrafficPermitted=false base config.

## Verification
- Grep checks 1–19 passed. Full command output is in `SECURITY_FIX_VERIFICATION.log`.
- XML parsing passed for AndroidManifest.xml, backup_rules.xml, data_extraction_rules.xml, and network_security_config.xml.
- Check 20 (`./gradlew assembleRelease`) was attempted but could not run in this sandbox because Gradle 8.9 was not locally cached and the environment cannot resolve `services.gradle.org`. The exact failure is preserved in `SECURITY_FIX_VERIFICATION.log`.

## Exact before/after diff
See `SECURITY_FIX_DIFFS.patch` at the repository root.
