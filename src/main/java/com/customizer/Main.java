package com.customizer;

import com.customizer.core.dwTemp;
import com.customizer.features.wallpapers.WallpaperManager;
import com.customizer.core.PlatformVersionInfo;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        

        System.out.print("Path of default wallpaper: ");
        System.out.println(dwTemp.defaultWallpaper);
        PlatformVersionInfo.PlatformInfo();
    }
}
