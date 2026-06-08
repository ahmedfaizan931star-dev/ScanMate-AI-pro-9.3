# ScanMate AI Pro

**Package:** `com.synthbyte.scanmate`

ScanMate AI Pro is an offline-first Android document scanner with CameraX scanning, English OCR, PDF/TXT/DOCX/ZIP export, QR tools, encrypted vault, widgets, launcher shortcuts, onboarding, and optional user-key AI workflows.

## Build outputs

GitHub Actions is configured to produce:

- Debug APK
- Release APK
- Release AAB

The app does not require cloud sync, login/auth, paid subscriptions, or hardcoded API keys.


## Signing Setup (GitHub Actions)

GitHub Actions is configured for **mandatory signed release builds**.

It builds Debug APK first, then requires release signing secrets for Release APK/AAB. If a secret is missing, the workflow fails early with a clear error instead of uploading an unsigned release by mistake.

Add these secrets in GitHub repo → Settings → Secrets and variables → Actions:

| Secret | Value |
|--------|-------|
| SIGNING_KEY | Base64 encoded `.jks` keystore file |
| KEY_STORE_PASSWORD | Keystore password. `STORE_PASSWORD` is also supported as a legacy fallback. |
| KEY_ALIAS | Key alias, for example `scanmate-key` |
| KEY_PASSWORD | Key password |

Generate keystore locally:

```bash
keytool -genkey -v \
  -keystore scanmate-release.jks \
  -alias scanmate-key \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000
```

Encode your keystore for GitHub Actions:

```bash
base64 -w 0 scanmate-release.jks
```

For local signed release builds, copy `keystore.properties.example` to `keystore.properties`, fill in real values, and place local keystores under `app/keystore/` or provide the path through `KEYSTORE_PATH`. Never commit real keystore files or real passwords.

For strict local validation matching CI, run with `SCANMATE_REQUIRE_RELEASE_SIGNING=true`.
