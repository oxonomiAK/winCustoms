package com.customizer.ui.sample;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


public class BoostController {

    @FXML
    private Button BtnBoost;

    @FXML
    private Button BtnProfile;

    @FXML
    private Button BtnRocket;

    @FXML
    private Button BtnHome;

    @FXML
    private Button BtnIcons;

    @FXML
    private Button BtnPerformace;

    @FXML
    private Button BtnSettings;

    @FXML
    private Button BtnTemperature;

    @FXML
    private Button BtnVolume;

    @FXML
    private Button BtnWallpapers;

    @FXML
    private Button closeButton;

     private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        setupButtonHoverEffect(BtnBoost);
        setupButtonHoverEffect(BtnIcons);
        setupButtonHoverEffect(BtnWallpapers);
        setupButtonHoverEffect(BtnVolume);
        setupButtonHoverEffect(BtnTemperature);
        setupButtonHoverEffect(BtnPerformace);
        setupButtonHoverEffect(BtnRocket);
        setupButtonHoverEffect(BtnSettings);

    }

    private void setupButtonHoverEffect(Button button) {
        // Создаем анимацию увеличения
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), button);
        scaleUp.setToX(1.05); // Увеличение по оси X
        scaleUp.setToY(1.05); // Увеличение по оси Y

        // Создаем анимацию уменьшения
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), button);
        scaleDown.setToX(1.0); // Возврат к исходному размеру по оси X
        scaleDown.setToY(1.0); // Возврат к исходному размеру по оси Y

        // Устанавливаем обработчики событий
        button.setOnMouseEntered(e -> scaleUp.play()); // Анимация увеличения при наведении
        button.setOnMouseExited(e -> scaleDown.play()); // Анимация уменьшения при убирании мыши
    }


    @FXML
    void BtnBoostClicked(ActionEvent event) {
        mainApp.loadScene("Boost.fxml");
    }


    @FXML
    void BtnRocketClicked(ActionEvent event) {
        mainApp.loadScene("Rocket.fxml");
    }

    @FXML
    void BtnHomeClicked(ActionEvent event) {
        mainApp.loadScene("Home.fxml");
    }

    @FXML
    void BtnIconsClicked(ActionEvent event) {
        mainApp.loadScene("Icons.fxml");
    }

    @FXML
    void BtnPerformaceClicked(ActionEvent event) {
        System.out.println("Performances button clicked!");
    }

    @FXML
    void BtnProfileClicked(ActionEvent event) {
        mainApp.loadScene("Profile.fxml");
    }

    @FXML
    void BtnSettingsClicked(ActionEvent event) {
        mainApp.loadScene("Settings.fxml");
    }

    @FXML
    void BtnTemperatureClicked(ActionEvent event) {
        System.out.println("temp button clicked!");
    }

    @FXML
    void BtnVolumeClicked(ActionEvent event) {
        mainApp.loadScene("Volume.fxml");
    }

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("Wallpapers.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }

}
