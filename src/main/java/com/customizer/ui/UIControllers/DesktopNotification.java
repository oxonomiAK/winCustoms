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

    private final List<Achievement> achievements = new ArrayList<>(); // Очередь достижений
    private boolean isShowing = false; // Флаг отображения уведомления

    // Метод для добавления уведомления в очередь
    public void getAchievement(String title, String message) {
        achievements.add(new Achievement(title, message));
        showNextAchievement();
    }

    // Метод для отображения следующего уведомления
    private void showNextAchievement() {
        if (isShowing || achievements.isEmpty()) return;

        isShowing = true; // Устанавливаем флаг, что уведомление отображается
        Achievement achievement = achievements.remove(0); // Берем первое достижение из очереди
        showNotification(achievement.getTitle(), achievement.getMessage());
    }

    // Метод для отображения одного уведомления
    public void showNotification(String title, String message) {
        // Создание нового окна для уведомления
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT); // Устанавливаем прозрачный стиль окна
        stage.setAlwaysOnTop(true); // Поверх всех окон

        // Контент уведомления
        Label label = new Label(title + "\n" + message);
        label.setWrapText(true);
        label.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 20; -fx-font-size: 14; -fx-border-radius: 10; -fx-background-radius: 10;");

        StackPane root = new StackPane(label);
        root.setStyle("-fx-background-color: transparent;"); // Прозрачный фон контейнера

        Scene scene = new Scene(root, 350, 150);
        scene.setFill(null); // Убираем фон сцены
        stage.setScene(scene);

        // Получаем размер экрана
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Устанавливаем позицию окна в правом нижнем углу
        double notificationWidth = 300; // Ширина уведомления
        double notificationHeight = 100; // Высота уведомления
        stage.setX(screenBounds.getMinX() + screenWidth - notificationWidth - 50); // 50 px отступ от края
        stage.setY(screenBounds.getMinY() + screenHeight - notificationHeight - 50); // 50 px отступ от края

        // Анимация появления (Fade In)
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        // Анимация исчезновения (Fade Out)
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(2)); // Через сколько секунд исчезнет уведомление
        fadeOut.setOnFinished(event -> {
            stage.close();
            isShowing = false; // Сбрасываем флаг
            showNextAchievement(); // Показываем следующее уведомление
        });

        // Анимация сдвига (Slide In)
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), root);
        slideIn.setFromY(50); // Сдвиг снизу вверх
        slideIn.setToY(0);

        // Последовательный запуск анимаций
        fadeIn.setOnFinished(event -> fadeOut.play());
        slideIn.setOnFinished(event -> fadeIn.play());
        slideIn.play();

        // Показать окно
        stage.show();
    }

    // Класс для хранения достижений
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
