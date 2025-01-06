package com.customizer.ui.UiManagers;

import com.customizer.ui.UIControllers.DesktopNotification;

import javafx.application.Platform;

public class DesktopNotificationController {
    public static void SendNotif(String title, String message) {
        DesktopNotification desktopNotification = DesktopNotification.getInstance();
        Platform.runLater(() -> desktopNotification.getAchievement(title, message));
    }
}
