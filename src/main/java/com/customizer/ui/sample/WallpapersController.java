package com.customizer.ui.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.customizer.core.dwTemp;
import com.customizer.core.utils.WallpaperUtils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class WallpapersController {

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
    void getWallpaperInfo(ActionEvent event) {

            WallpaperUtils.getWallpaperPathFromWinIni();
            String wallpaperPath = new String(dwTemp.defaultWallpaper);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wallpaper Info");
            alert.setHeaderText("Current Wallpaper Path");
            alert.setContentText(wallpaperPath);
            alert.showAndWait();

        
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
}


