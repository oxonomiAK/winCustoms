package com.customizer.ui.ButtonEffectUtils;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class NotificationManager {
    public static void showNotification(String message, Button BtnWallpapers1) {
        Label notification = new Label(message);
        
        notification.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-text-fill: white; "
                + "-fx-padding: 10px; -fx-font-size: 14px; -fx-border-radius: 10; -fx-background-radius: 10;");
        notification.setAlignment(Pos.CENTER);
        notification.setPrefWidth(450);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), notification);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), notification);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
        SequentialTransition sequentialTransition = new SequentialTransition(fadeIn, pause, fadeOut);
            sequentialTransition.play();
        
        
        Scene scene = BtnWallpapers1.getScene(); // Получаем текущую сцену
        Pane rootPane = (Pane) scene.getRoot(); // Корневой узел
        notification.setLayoutX((scene.getWidth() - notification.getPrefWidth()) / 2);
        notification.setLayoutY(scene.getHeight() - 60); // Располагаем внизу экрана
        rootPane.getChildren().add(notification);
        // Убираем уведомление через 5 секунд
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ev -> {
            rootPane.getChildren().remove(notification);
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
