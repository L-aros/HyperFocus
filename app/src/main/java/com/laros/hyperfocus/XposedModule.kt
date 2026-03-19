package com.laros.hyperfocus

import android.app.AndroidAppHelper
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XposedModule : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != "com.android.systemui") return

        try {
            val context = AndroidAppHelper.currentApplication()
            if (!PreferencesHelper.isEnabled(context)) {
                android.util.Log.d("HyperFocus", "Feature disabled, skipping hooks")
                return
            }

            val notificationSettingsManagerClass = XposedHelpers.findClass(
                "miui.systemui.notification.NotificationSettingsManager",
                lpparam.classLoader
            )

            XposedHelpers.findAndHookMethod(
                notificationSettingsManagerClass,
                "canShowFocus",
                String::class.java,
                object : XC_MethodReplacement() {
                    override fun replaceHookedMethod(param: MethodHookParam): Any {
                        return true
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                notificationSettingsManagerClass,
                "canCustomFocus",
                String::class.java,
                object : XC_MethodReplacement() {
                    override fun replaceHookedMethod(param: MethodHookParam): Any {
                        return true
                    }
                }
            )

            android.util.Log.d("HyperFocus", "Hooks applied successfully")
        } catch (e: Throwable) {
            android.util.Log.e("HyperFocus", "Hook failed", e)
        }
    }
}
