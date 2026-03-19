# HyperFocus

HyperFocus is an LSPosed/Xposed module for HyperOS that removes the focus-notification whitelist restriction in `com.android.systemui`.

## What It Does

- Lets any app use focus notifications after the module is enabled.
- Provides a simple Compose-based settings UI.
- Hooks HyperOS SystemUI through LSPosed.

## Requirements

- HyperOS device
- Root access
- LSPosed installed
- Module scope enabled for `com.android.systemui`

## Local Build

1. Configure `local.properties` with your Android SDK path.
2. Put your release keystore in `keystore/`.
3. Create `keystore/keystore.properties`:

```properties
storeFile=keystore/hyperfocus-release.jks
storePassword=your_store_password
keyAlias=your_key_alias
keyPassword=your_key_password
```

4. Build:

```bash
./gradlew assembleRelease
```

## GitHub Release Automation

This repo includes `.github/workflows/release.yml`.

Pushing a tag like `v1.0.0` will:

- build the signed release APK
- create a GitHub Release
- upload the APK as a release asset

Required repository secrets:

- `KEYSTORE_BASE64`
- `KEYSTORE_PASSWORD`
- `KEY_ALIAS`
- `KEY_PASSWORD`

PowerShell command to generate `KEYSTORE_BASE64` from the local keystore:

```powershell
[Convert]::ToBase64String([IO.File]::ReadAllBytes("D:\Program\Codex\HyperFocus\keystore\hyperfocus-release.jks"))
```

## Notes

- Keystore files and local signing metadata are ignored by Git.
- The module entry point is `com.laros.hyperfocus.XposedModule`.
