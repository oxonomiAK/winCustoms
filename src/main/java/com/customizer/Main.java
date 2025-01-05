package com.customizer;

<<<<<<< HEAD
import com.customizer.core.dwTemp;
import com.customizer.features.wallpapers.WallpaperManager;

import com.customizer.core.PlatformVersionInfo;

import com.customizer.features.themes.ThemeManager;

public class Main {
        public static void main(String[] args) {
                System.out.println("Hello, World!");
                WallpaperManager.getDeskWallpaperPath();
                System.out.print("Path of default wallpaper: ");
                System.out.println(dwTemp.defaultWallpaper);
                PlatformVersionInfo.PlatformInfo();
                ThemeManager.setShadows();

        }
=======


import com.customizer.ui.ButtonEffectUtils.CoinsController;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        CoinsController controller = new CoinsController();

        // Добавляем монеты
        controller.addCoins(10);

        // Сохраняем монеты
        controller.saveCoins();
    }
>>>>>>> Vladmini
}
