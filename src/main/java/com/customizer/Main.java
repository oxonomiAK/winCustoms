package com.customizer;

import com.customizer.core.dwTemp;
import com.customizer.features.wallpapers.WallpaperManager;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        WallpaperManager.getDeskWallpaperPath();
        System.out.print("Path of default wallpaper: ");
        System.out.println(dwTemp.defaultWallpaper);
    }
}
