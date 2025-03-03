package com.customizer.core;

import java.io.File;

import com.customizer.Main;
import com.customizer.core.utils.WallpaperUtils;

public class WallpaperApply {
    public static void WallApply(Main mainApp, String imagePath){
        //Getting wallpaper name from path
        int lastSlash = imagePath.lastIndexOf('/');
        int lastDot = imagePath.lastIndexOf('.');
        String wallpaperName = imagePath.substring(lastSlash + 1, lastDot);

        File destFile = new File("NewLookResources/"+wallpaperName+".jpg");
        //Checking if file exist to avoid every time copying
        if(!destFile.exists())
            WallpaperTransfer.Transfer(imagePath , wallpaperName);
        //Set new wallpaper
        WallpaperUtils.setWallpaper(destFile.getAbsolutePath());
    }
}
