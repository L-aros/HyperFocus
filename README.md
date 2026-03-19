# HyperFocus

HyperFocus 是一个面向 HyperOS 的 LSPosed/Xposed 模块，用于移除系统对焦点通知白名单的限制。启用后，系统不再只允许少数白名单应用使用焦点通知，普通应用也可以正常触发相关能力。

## 为什么做这个模块？

LSPosed 发布 API 101 时，HyperCeiler 还没有完成适配，这项功能因此暂时不可用。为了避免依赖大型模块的更新节奏，HyperFocus 参考并整理了 HyperCeiler 在这部分的实现思路，将“移除焦点通知白名单限制”单独拆分为一个独立模块。

## 功能特性

- 移除 HyperOS 焦点通知白名单限制
- 提供独立设置界面，可在应用内开启或关闭模块功能
- 基于 LSPosed 对 `com.android.systemui` 进行 Hook
- 支持通过 GitHub Actions 自动构建并发布 Release APK

## 使用要求

- HyperOS 设备
- Root 权限
- 已安装 LSPosed
- 已为模块勾选 `com.android.systemui` 作用域

## 使用方法

1. 在设备上安装 HyperFocus APK。
2. 打开应用，确认功能开关处于启用状态。
3. 打开 LSPosed，启用 HyperFocus 模块。
4. 在作用域列表中勾选 `com.android.systemui`。
5. 重启 SystemUI 或直接重启设备，使 Hook 生效。

## 作用域说明

HyperFocus 当前只针对 `com.android.systemui` 生效。若未在 LSPosed 中勾选该作用域，模块不会接管 HyperOS 的焦点通知判断逻辑。

## 本地构建

1. 在项目根目录创建或修改 `local.properties`，设置 Android SDK 路径：

```properties
sdk.dir=D:\\Android\\Sdk
```

2. 如需本地签名 Release，请将 keystore 放入 `keystore/` 目录，并创建 `keystore/keystore.properties`：

```properties
storeFile=keystore/hyperfocus-release.jks
storePassword=your_store_password
keyAlias=your_key_alias
keyPassword=your_key_password
```

3. 执行构建命令：

```bash
./gradlew assembleRelease
```

Windows 环境也可以使用：

```powershell
.\gradlew.bat assembleRelease
```

## 自动发布

仓库内置了 [`.github/workflows/release.yml`](./.github/workflows/release.yml) 自动发布流程。推送形如 `v1.0.0` 的标签后，GitHub Actions 会自动完成以下操作：

- 读取仓库 Secrets 中的签名信息
- 构建签名版 Release APK
- 创建或更新对应版本的 GitHub Release
- 上传 APK 附件并使用标签注释作为发布说明

## 致谢

HyperFocus 的实现离不开以下开源项目与社区贡献：

- [LSPosed](https://github.com/LSPosed/LSPosed)
- [miuix](https://github.com/compose-miuix-ui/miuix)
- [HyperCeiler](https://github.com/ReChronoRain/HyperCeiler)
- [XposedBridge API](https://github.com/rovo89/XposedBridge)

感谢所有为 Android Root、Xposed 与 HyperOS 生态持续贡献的开发者。

## LICENSE 说明

本项目采用 [MIT License](./LICENSE) 开源发布。你可以在遵守许可证条款的前提下使用、修改、分发本项目代码。

项目引用的第三方组件、框架与库，其版权和许可证归各自作者或维护者所有，使用时请同时遵守对应项目的许可证要求。
