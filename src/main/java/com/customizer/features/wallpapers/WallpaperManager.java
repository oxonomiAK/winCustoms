package com.customizer.features.wallpapers;

import com.customizer.core.utils.WallpaperUtils;

public class WallpaperManager {

    // applying wallpaper with updating in Registry
    public static void applyWallpaper(String imagePath) {
        WallpaperUtils.setWallpaper(imagePath);
    }

    // applying wallpaper without updating in Registry
    public static void applyWallpaperWithoutUpdateInReg(String imagePath) {
        WallpaperUtils.setWallpaperWithoutUpdateInReg(imagePath);
    }

    // get desktop wallpaper path
    public static void getDeskWallpaperPath() {
        WallpaperUtils.getWallpaperPathFromWinIni();
    }

}
