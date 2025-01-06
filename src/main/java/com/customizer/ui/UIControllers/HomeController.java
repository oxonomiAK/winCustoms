package com.customizer.ui.UIControllers;

import com.customizer.Main;
import com.customizer.ui.UiManagers.HoverEffect;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button BtnWallpapers;

    @FXML
    private Button closeButton;


    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
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
