package com.yuchen.qingyi.utils

import android.os.Build

@Suppress("UNUSED")
object SystemVersionUtils {
    /**
     * 判断当前设备是否运行 Android 12 或更高版本。
     *
     * @return `true` 表示 API Level ≥ 31 (Android 12+)
     * @since API 31
     */
    fun isAtLeastS() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    /**
     * 判断当前设备是否运行 Android 12L 或更高版本。
     *
     * @return `true` 表示 API Level ≥ 32 (Android 12.1+)
     * @since API 32
     */
    fun isAtLeastSV2() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2

    /**
     * 判断当前设备是否运行 Android 13 或更高版本。
     *
     * @return `true` 表示 API Level ≥ 33 (Android 13+)
     * @since API 33
     */
    fun isAtLeastTiramisu() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

    /**
     * 判断当前设备是否运行 Android 14 或更高版本。
     *
     * @return `true` 表示 API Level ≥ 34 (Android 14+)
     * @since API 34
     */
    fun isAtLeastUpside() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE


    /**
     * 判断当前设备是否运行 Android 15 或更高版本。
     *
     * @return `true` 表示 API Level ≥ 35 (Android 15+)
     * @since API 35
     */
    fun isAtLeastVanilla() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM


    /**
     * 判断当前设备是否运行 Android 16 (Baklava) 或更高版本。
     *
     * @return `true` 表示 API Level ≥ 36 (Android 16+)
     * @since API 36
     */
    fun isAtLeastBaklava() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.BAKLAVA
}