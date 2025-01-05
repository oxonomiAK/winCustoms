package com.customizer.ui.ButtonEffectUtils;

import com.customizer.ui.UIControllers.DesktopNotification;

import javafx.application.Platform;

public class DesktopNotificationManager {
    public static void SendNotif(String titile, String message){
         DesktopNotification desktopNotification = new DesktopNotification();
            Platform.runLater(() -> {
            desktopNotification.getAchievement(titile, message);
        });
    }
}
