package com.customizer.core.utils;

import com.customizer.core.User32F;

public class WallpaperUtils {

    public static void setWallpaper(String imagePath) {
        // Передаем параметры в SystemParametersInfo для смены обоев
        User32F.User32.INSTANCE.SystemParametersInfoA(
                User32F.User32.SPI_SETDESKWALLPAPER,
                0,
                imagePath,
                User32F.User32.SPIF_UPDATEINIFILE | User32F.User32.SPIF_SENDCHANGE);
    }

    public static void setWallpaperWithoutUpdateInReg(String imagePath) {
        // same with prev function
        // But changes will be temp. and we dont save it in registry, it means that
        // after OS restart changes will be cancelled
        User32F.User32.INSTANCE.SystemParametersInfoA(
                User32F.User32.SPI_SETDESKWALLPAPER,
                0,
                imagePath,
                0);
    }

    public static void getWallpaperPathFromWinIni() {
        // method to get desktop wallpapers path
        // wallpaper path saves in defaultWallpaper list of chars

        char[] defaultWalppaper = new char[User32F.User32.MAX_PATH];

        User32F.User32.INSTANCE.SystemParametersInfoW(
                User32F.User32.SPI_GETDESKWALLPAPER,
                User32F.User32.MAX_PATH,
                defaultWalppaper,
                0);

    }

}
