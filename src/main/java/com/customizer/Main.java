package com.customizer;

import com.customizer.services.ReadFromJson;
import com.customizer.ui.ButtonEffectUtils.CoinsController;

public class Main {
    public static void main(String[] args) {
        System.out.println(ReadFromJson.ReadFromJSONString("Username"));
        CoinsController controller = new CoinsController();

        // Добавляем монеты
        controller.addCoins(10);

        // Сохраняем монеты
        controller.saveCoins();
    }
}
