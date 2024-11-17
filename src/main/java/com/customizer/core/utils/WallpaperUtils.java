package com.customizer.core.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;

public class WallpaperUtils {

    // Интерфейс для User32 с добавлением метода SystemParametersInfo
    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        // constant to set desktop wallpaper
        // constant to update it in registry
        // constant to notify other software about changes
        final int SPI_SETDESKWALLPAPER = 0x0014;
        final int SPIF_UPDATEINIFILE = 0x01;
        final int SPIF_SENDCHANGE = 0x02;

        // constant to get desktop wallpapers path
        final int SPI_GETDESKWALLPAPER = 0x0073;
        final int MAX_PATH = 260;

        // Определяем метод SystemParametersInfoW с поддержкой Unicode
        boolean SystemParametersInfoA(int uiAction, int uiParam, String pvParam, int fWinIni);

        boolean SystemParametersInfoW(int uiAction, int uiParam, char[] pvParam, int fWinIni);
    }

    public static void setWallpaper(String imagePath) {
        // Передаем параметры в SystemParametersInfo для смены обоев
        User32.INSTANCE.SystemParametersInfoA(
                User32.SPI_SETDESKWALLPAPER,
                0,
                imagePath,
                User32.SPIF_UPDATEINIFILE | User32.SPIF_SENDCHANGE);
    }

    public static void setWallpaperWithoutUpdateInReg(String imagePath) {
        // same with prev function
        // But changes will be temp. and we dont save it in registry, it means that
        // after OS restart changes will be cancelled
        User32.INSTANCE.SystemParametersInfoA(
                User32.SPI_SETDESKWALLPAPER,
                0,
                imagePath,
                0);
    }

    public static void getWallpaperPathFromWinIni() {
        // method to get desktop wallpapers path
        // wallpaper path saves in defaultWallpaper list of chars

        char[] defaultWalppaper = new char[User32.MAX_PATH];

        User32.INSTANCE.SystemParametersInfoW(
                User32.SPI_GETDESKWALLPAPER,
                User32.MAX_PATH,
                defaultWalppaper,
                0);

    }

}
