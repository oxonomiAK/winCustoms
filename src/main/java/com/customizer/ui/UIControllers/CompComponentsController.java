package com.customizer.ui.UIControllers;

import com.customizer.core.SystemInfoExample;
import com.customizer.ui.ButtonEffectUtils.HoverEffect;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class CompComponentsController {

    @FXML
    private Button BtnBoost;

    @FXML
    private Button BtnProfile;

    @FXML
    private Label SystemInfoArea;

    @FXML
    private Button BtnHome;

    @FXML
    private Button BtnSettings;

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
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnHome);
        HoverEffect.setupButtonHoverEffect(BtnSettings);
    
        
        SystemInfoArea.setText(SystemInfoExample.displaySystemInfo()); //Обращение к файлу SystemInfoExample
    }
    
    @FXML
    void BtnBoostClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Boost.fxml");
    }

    @FXML
    void BtnHomeClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Home.fxml");
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
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Wallpapers.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }

}
