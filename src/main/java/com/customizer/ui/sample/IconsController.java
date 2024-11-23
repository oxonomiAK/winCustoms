package com.customizer.ui.sample;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class IconsController {

    @FXML
    private Button BtnDownloaded;

    @FXML
    private Button BtnIcons;

    @FXML
    private Button BtnTaskBar;

    @FXML
    private Button BtnWallpapers;

    @FXML
    private Button BtnWidgets;

    @FXML
    private Button closeButton;

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        setupButtonHoverEffect(BtnDownloaded);
        setupButtonHoverEffect(BtnIcons);
        setupButtonHoverEffect(BtnTaskBar);
        setupButtonHoverEffect(BtnWallpapers);
        setupButtonHoverEffect(BtnWidgets);
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


