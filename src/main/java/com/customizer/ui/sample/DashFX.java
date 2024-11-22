package com.customizer.ui.sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashFX {

    @FXML
    private Button closeButton;

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void BtnDownloadedClicked(ActionEvent event) {
        mainApp.loadScene("Downloaded.fxml");
    }

    @FXML
    void BtnIconsClicked(ActionEvent event) {
        mainApp.loadScene("Icons.fxml");
    }

    @FXML
    void BtnTaskBarClicked(ActionEvent event) {
        mainApp.loadScene("TaskBar.fxml");
    }

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("Wallpapers.fxml");
    }

    @FXML
    void BtnWidgetsClicked(ActionEvent event) {
        mainApp.loadScene("Widgets.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
}


