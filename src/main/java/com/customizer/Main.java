package com.customizer;



import com.customizer.core.GameUtils.RecycleBinMonitor;
import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;
import com.customizer.ui.ButtonEffectUtils.CoinsController;
import com.customizer.ui.UIControllers.DesktopNotification;

import javafx.application.Platform;


public class Main {
    public static void main(String[] args) throws InterruptedException {

     DesktopNotification desktopNotification = new DesktopNotification();
        Platform.runLater(() -> {
        desktopNotification.getAchievement("Уведомление 2", "Это текст второго уведомления.");
    });
    
     

        RecycleBinMonitor.StartMonitoring();
        CoinsController controller = new CoinsController();

        // Добавляем монеты
        controller.addCoins(10);

        // Сохраняем монеты
        controller.saveCoins();
    }
}
