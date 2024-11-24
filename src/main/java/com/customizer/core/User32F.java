package com.customizer.core;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;

public class User32F {
    // Интерфейс для User32 с добавлением метода SystemParametersInfo
    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        // constant to set desktop wallpaper
        // constant to update it in registry
        // constant to notify other software about changes
        // constant to change icon size
        final int SPI_SETDESKWALLPAPER = 0x0014;
        final int SPIF_UPDATEINIFILE = 0x01;
        final int SPIF_SENDCHANGE = 0x02;
        
        // constant to get desktop wallpapers path
        final int SPI_GETDESKWALLPAPER = 0x0073;
        final int MAX_PATH = 260;

        final int HWND_BROADCAST = 0xFFFF;

        final int WM_SETTINGCHANGE = 0x001A;

    

        // Определяем метод SystemParametersInfoW с поддержкой Unicode
        boolean SystemParametersInfoW(int uiAction, int uiParam, String pvParam, int fWinIni);

        boolean SystemParametersInfoW(int uiAction, int uiParam, int pvParam, int fWinIni);

        boolean SystemParametersInfoW(int uiAction, int uiParam, char[] pvParam, int fWinIni);

        void SendMessage(int hwndBroadcast, int wmSettingchange, int i, int j);
    }
}
