package com.customizer;

import com.customizer.core.dwTemp;
import com.customizer.features.wallpapers.WallpaperManager;
import com.customizer.core.PlatformVersionInfo;
import com.customizer.core.acTemp;
import com.customizer.features.themes.ThemeManager;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        WallpaperManager.getDeskWallpaperPath();
        System.out.print("Path of default wallpaper: ");
        System.out.println(dwTemp.defaultWallpaper);
        PlatformVersionInfo.PlatformInfo();
        ThemeManager.getAccentColorFromWinIni();
        System.out.print("Accent Color Hex: ");
        System.out.println(acTemp.defaultAccentColor);
    }
}
