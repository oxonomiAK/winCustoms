package com.customizer.features.wallpapers;

import com.customizer.core.utils.WallpaperUtils;

public class WallpaperManager {

    public void applyWallpaper(String imagePath) {
        WallpaperUtils.setWallpaper(imagePath);
    }

}
