package com.customizer.core.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;

public class WallpaperUtils {

    // Определяем необходимые константы
    public static final int SPI_SETDESKWALLPAPER = 0x0014;
    public static final int SPIF_UPDATEINIFILE = 0x01;
    public static final int SPIF_SENDCHANGE = 0x02;

    // Интерфейс для User32 с добавлением метода SystemParametersInfo
    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        // Определяем метод SystemParametersInfoW с поддержкой Unicode
        boolean SystemParametersInfoA(int uiAction, int uiParam, String pvParam, int fWinIni);
    }

    public static void setWallpaper(String imagePath) {
        // Передаем параметры в SystemParametersInfo для смены обоев
        User32.INSTANCE.SystemParametersInfoA(
                SPI_SETDESKWALLPAPER,
                0,
                imagePath,
                SPIF_UPDATEINIFILE | SPIF_SENDCHANGE);
    }

    public static void setWallpaperWithoutUpdateInReg(String imagePath) {
        // same with prev function
        // But changes will be temp. and we dont save it in registry, it means that
        // after OS restart your changes will be cancelled
        User32.INSTANCE.SystemParametersInfoA(
                SPI_SETDESKWALLPAPER,
                0,
                imagePath,
                0);
    }

}
