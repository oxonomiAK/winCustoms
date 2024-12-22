package com.customizer;

import com.customizer.core.dwTemp;
import com.customizer.features.wallpapers.WallpaperManager;
import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;
import com.customizer.ui.ButtonEffectUtils.CoinsController;
import com.customizer.ui.ButtonEffectUtils.CoinsManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;


import com.customizer.core.PlatformVersionInfo;
import com.customizer.core.acTemp;
import com.customizer.features.themes.ThemeManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        CoinsController controller = new CoinsController();

        // Добавляем монеты
        controller.addCoins(10);

        // Сохраняем монеты
        controller.saveCoins();
    }
}
