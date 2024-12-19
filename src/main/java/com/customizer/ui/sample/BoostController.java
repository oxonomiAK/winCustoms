package com.customizer.ui.sample;

import com.customizer.ui.ButtonEffectUtils.HoverEffect;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



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
    private Button BtnSettings;

    @FXML
    private Button BtnCompComponents;

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
        HoverEffect.setupButtonHoverEffect(BtnBoost);
        HoverEffect.setupButtonHoverEffect(BtnIcons);
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnVolume);
        HoverEffect.setupButtonHoverEffect(BtnCompComponents);
        HoverEffect.setupButtonHoverEffect(BtnRocket);
        HoverEffect.setupButtonHoverEffect(BtnSettings);

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
    void BtnProfileClicked(ActionEvent event) {
        mainApp.loadScene("Profile.fxml");
    }

    @FXML
    void BtnSettingsClicked(ActionEvent event) {
        mainApp.loadScene("Settings.fxml");
    }

    @FXML
    void BtnCompComponentsClicked(ActionEvent event) {
        mainApp.loadScene("CompComponents.fxml");
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
