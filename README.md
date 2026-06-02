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

Add these 4 secrets in GitHub repo → Settings → Secrets → Actions:

| Secret | Value |
|--------|-------|
| SIGNING_KEY | Base64 encoded .jks keystore file |
| KEY_STORE_PASSWORD | Your keystore password |
| KEY_ALIAS | Your key alias (e.g. scanmate-key) |
| KEY_PASSWORD | Your key password |

To encode your keystore:

```bash
base64 -i scanmate-release.jks | pbcopy
```

Generate keystore locally:

```bash
keytool -genkey -v \
  -keystore scanmate-release.jks \
  -alias scanmate-key \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000
```

Place local keystores under `app/keystore/` or provide the path through `KEYSTORE_PATH`. Never commit real keystore files or real passwords.
