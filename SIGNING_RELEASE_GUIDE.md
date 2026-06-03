# Release Signing Guide

This project uses environment-based signing.

## Generate an upload key locally or in a secure Colab session

```bash
keytool -genkeypair \
  -v \
  -keystore scanmate-upload-key.jks \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000 \
  -alias upload
```

Store this file securely. Do not commit it to GitHub and do not include it in public ZIP files.

## Sign release builds

```bash
export KEYSTORE_PATH="/content/drive/MyDrive/scanmate-upload-key.jks"
export STORE_PASSWORD="your_store_password"
export KEY_ALIAS="upload"
export KEY_PASSWORD="your_key_password"

./gradlew assembleRelease --no-daemon --stacktrace
./gradlew bundleRelease --no-daemon --stacktrace
```

## Output paths

```text
app/build/outputs/apk/release/app-release.apk
app/build/outputs/bundle/release/app-release.aab
```

In GitHub Actions, release signing is mandatory. If signing variables are missing or the keystore alias is wrong, CI fails before uploading release artifacts.

For strict local validation matching CI:

```bash
export SCANMATE_REQUIRE_RELEASE_SIGNING=true
./gradlew assembleRelease --no-daemon --stacktrace
./gradlew bundleRelease --no-daemon --stacktrace
```
