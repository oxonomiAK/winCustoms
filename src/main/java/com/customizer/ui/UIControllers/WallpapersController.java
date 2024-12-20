package com.customizer.ui.UIControllers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.customizer.core.utils.WallpaperUtils;
import com.customizer.ui.ButtonEffectUtils.HoverEffect;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WallpapersController {

    @FXML
    private Button BtnBoost;

    @FXML
    private Button BtnWallpapers;

    @FXML
    private ImageView Wall1;

    @FXML
    private ImageView Wall2;

    @FXML
    private ImageView Wall3;

    @FXML
    private ImageView Wall4;

    @FXML
    private Button BtnProfile;

    @FXML
    private Button BtnSettings;

    @FXML
    private Button BtnHome;

    @FXML
    private Button closeButton;

    @FXML
    private Button BtnWallpapers1;
    @FXML
    private Button BtnWallpapers2; // Кнопка, которая будет увеличиваться
    @FXML
    private Button BtnWallpapers3; // Кнопка, которая будет увеличиваться
    @FXML
    private Button BtnWallpapers4; // Кнопка, которая будет увеличиваться

    private final Map<Button, String> wallpaperPaths = new HashMap<>();

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        HoverEffect.setupButtonHoverEffect(BtnBoost);
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnHome);
        HoverEffect.setupButtonHoverEffect(BtnSettings);

        wallpaperPaths.put(BtnWallpapers1, "src/main/java/com/customizer/ui/resources/Horses.jpg");
        wallpaperPaths.put(BtnWallpapers2, "src/main/java/com/customizer/ui/resources/gori.png");
        wallpaperPaths.put(BtnWallpapers3, "src/main/java/com/customizer/ui/resources/pole.png");
        wallpaperPaths.put(BtnWallpapers4, "src/main/java/com/customizer/ui/resources/japan.png");

    }

    @FXML
    void BtnBoostClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Boost.fxml");
    }

    @FXML
    void BtnWallpapers1Clicked(ActionEvent event) {
        handleWallpaperButtonClick(BtnWallpapers1, Wall1, event);
    }

    @FXML
    void BtnWallpapers2Clicked(ActionEvent event) {
        handleWallpaperButtonClick(BtnWallpapers2, Wall2, event);
    }

    @FXML
    void BtnWallpapers3Clicked(ActionEvent event) {
        handleWallpaperButtonClick(BtnWallpapers3, Wall3, event);
    }

    @FXML
    void BtnWallpapers4Clicked(ActionEvent event) {
        handleWallpaperButtonClick(BtnWallpapers4, Wall4, event);
    }

    private void handleWallpaperButtonClick(Button button, ImageView wall, ActionEvent event) {
        // Получаем текущую сцену
        javafx.scene.Scene scene = button.getScene();

        // Сохраняем исходные координаты кнопки
        double originalX = button.getLayoutX();
        double originalY = button.getLayoutY();

        // Задаем новые координаты для перемещения кнопки
        double newX = 500;
        double newY = 220;

        // Прячем остальные кнопки
        BtnWallpapers1.setVisible(button == BtnWallpapers1);
        BtnWallpapers2.setVisible(button == BtnWallpapers2);
        BtnWallpapers3.setVisible(button == BtnWallpapers3);
        BtnWallpapers4.setVisible(button == BtnWallpapers4);

        // Получаем путь к изображению из UserData
        String imagePath = (String) button.getUserData();

        // Проверяем, что путь не пуст
        if (imagePath == null || imagePath.isEmpty()) {
            System.out.println("UserData кнопки пустое или отсутствует!");
            return;
        }

        // Перемещаем кнопку
        button.setLayoutX(newX);
        button.setLayoutY(newY);

        // Анимация увеличения кнопки
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(300), button);
        scaleUp.setToX(1.2);
        scaleUp.setToY(1.2);

        scaleUp.setOnFinished(e -> {
            // Создаем кнопку "Go Back"
            Button goBackButton = new Button("Go Back");
            goBackButton.setPrefWidth(100);
            goBackButton.setPrefHeight(35);
            goBackButton.setLayoutX(newX + 55);
            goBackButton.setLayoutY(newY + 285);

            // Создаем кнопку "Set Wallpaper"
            Button setWallpaperButton = new Button("Set Wallpaper");
            setWallpaperButton.setPrefWidth(100);
            setWallpaperButton.setPrefHeight(35);
            setWallpaperButton.setLayoutX(newX + 235);
            setWallpaperButton.setLayoutY(newY + 285);

            // Обработчик для кнопки "Set Wallpaper"
            setWallpaperButton.setOnAction(ev -> {
                File f = new File(imagePath);
                String absolutePath = f.getAbsolutePath();
                System.out.println("Устанавливаем обои: " + absolutePath);

                // Устанавливаем изображение в область обоев
                if (wall != null) {
                    WallpaperUtils.setWallpaper(absolutePath); // Здесь вызываем логику установки обоев
                }
            });

            // Обработчик для кнопки "Go Back"
            goBackButton.setOnAction(ev -> {
                // Анимация уменьшения кнопки обратно
                ScaleTransition scaleDown = new ScaleTransition(Duration.millis(300), button);
                scaleDown.setToX(1.0);
                scaleDown.setToY(1.0);

                scaleDown.setOnFinished(animationEvent -> {
                    // Возвращаем кнопку на исходные координаты
                    button.setLayoutX(originalX);
                    button.setLayoutY(originalY);

                    // Показываем скрытые кнопки
                    BtnWallpapers1.setVisible(true);
                    BtnWallpapers2.setVisible(true);
                    BtnWallpapers3.setVisible(true);
                    BtnWallpapers4.setVisible(true);

                    // Удаляем кнопки "Go Back" и "Set Wallpaper"
                    ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().remove(goBackButton);
                    ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().remove(setWallpaperButton);
                });

                // Запуск уменьшения кнопки
                scaleDown.play();
            });

            // Добавляем кнопки "Go Back" и "Set Wallpaper" на сцену
            ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(goBackButton);
            ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(setWallpaperButton);
        });

        // Запуск анимации увеличения кнопки
        scaleUp.play();
    }

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Wallpapers.fxml");
    }

    @FXML
    void BtnProfileClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Profile.fxml");
    }

    @FXML
    void BtnSettingsClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Settings.fxml");
    }

    @FXML
    void BtnHomeClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Home.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
}
