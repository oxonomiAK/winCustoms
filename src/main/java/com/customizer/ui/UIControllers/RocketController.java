package com.customizer.ui.UIControllers;

import java.io.IOException;
import com.customizer.services.RequestAdmin;
import com.customizer.ui.ButtonEffectUtils.HoverEffect;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;


public class RocketController {

    @FXML
    private Button BtnBoost;

    @FXML
    private Button BtnProfile;


    @FXML
    private Button BtnToolTip;

    @FXML
    private Tooltip ToolTip;

    @FXML
    private Button BtnHome;

    @FXML
    private Button BtnPerformances;

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
    
        // Устанавливаем Tooltip для BtnToolTip с задержкой 50 мс
        ToolTip.setText("You need administrator rights to run it"); // Ваш текст подсказки
        ToolTip.setShowDelay(javafx.util.Duration.millis(200)); // Устанавливаем задержку 50 мс
        ToolTip.setShowDuration(javafx.util.Duration.seconds(3)); // Время отображения 2 секунды
        ToolTip.setHideDelay(javafx.util.Duration.millis(100)); // Задержка перед скрытием 100 мс
        BtnPerformances.setTooltip(ToolTip); // Привязываем Tooltip к кнопке
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
    void BtnPerformancesClicked(ActionEvent event) throws IOException {
        RequestAdmin.RequestAdminRights();
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
