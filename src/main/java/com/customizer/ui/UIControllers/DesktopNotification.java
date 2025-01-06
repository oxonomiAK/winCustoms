package com.customizer.ui.UIControllers;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class DesktopNotification {
    private static DesktopNotification instance; // Singleton instance
    private final List<Achievement> achievements = new ArrayList<>();
    private boolean isShowing = false;

    private DesktopNotification() {}

    public static synchronized DesktopNotification getInstance() {
        if (instance == null) {
            instance = new DesktopNotification();
        }
        return instance;
    }

    public synchronized void getAchievement(String title, String message) {
        achievements.add(new Achievement(title, message));
        if (!isShowing) {
            showNextAchievement();
        }
    }

    private void showNextAchievement() {
        if (achievements.isEmpty()) {
            isShowing = false;
            return;
        }

        isShowing = true;
        Achievement achievement = achievements.remove(0);
        showNotification(achievement.getTitle(), achievement.getMessage());
    }

    private void showNotification(String title, String message) {
        // Оригинальная реализация с добавлением isShowing
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);

        Label label = new Label(title + "\n" + message);
        label.setWrapText(true);
        label.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 20; -fx-font-size: 14; -fx-border-radius: 10; -fx-background-radius: 10;");

        StackPane root = new StackPane(label);
        root.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(root, 350, 150);
        scene.setFill(null);
        stage.setScene(scene);

        // Расчёт позиции
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        double notificationWidth = 300;
        double notificationHeight = 100;
        stage.setX(screenBounds.getMinX() + screenWidth - notificationWidth - 50);
        stage.setY(screenBounds.getMinY() + screenHeight - notificationHeight - 50);

        // Анимации
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(2));
        fadeOut.setOnFinished(event -> {
            stage.close();
            isShowing = false;
            showNextAchievement();
        });

        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), root);
        slideIn.setFromY(50);
        slideIn.setToY(0);

        fadeIn.setOnFinished(event -> fadeOut.play());
        slideIn.setOnFinished(event -> fadeIn.play());
        slideIn.play();

        stage.show();
    }

    static class Achievement {
        private final String title;
        private final String message;

        public Achievement(String title, String message) {
            this.title = title;
            this.message = message;
        }

        public String getTitle() {
            return title;
        }

        public String getMessage() {
            return message;
        }
    }
}

