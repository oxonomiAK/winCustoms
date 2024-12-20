package com.customizer.ui.UIControllers;

import com.customizer.core.CoreAudio;
import com.customizer.ui.ButtonEffectUtils.HoverEffect;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import com.sun.jna.ptr.FloatByReference;

public class VolumeController {

    @FXML
    private Button BtnBoost;

    @FXML
    private Button ApplyButton;

    @FXML
    private Slider VolumeSlider;

    @FXML
    private Button BtnProfile;

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

        // Установить начальное значение громкости из системы
        VolumeSlider.setValue(getSystemVolume() * 100);

        // Обработчик нажатия кнопки "ОК"
        // ApplyButton.setOnAction(event -> SetSystemVolume((float)
        // VolumeSlider.getValue() / 100));
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
    void SetSystemVolume(ActionEvent event) {

    }

    // Получить текущий уровень громкости
    private float getSystemVolume() {
        try {
            CoreAudio.IAudioEndpointVolume audioEndpoint = getAudioEndpoint();
            FloatByReference volumeRef = new FloatByReference();
            audioEndpoint.GetMasterVolumeLevelScalar(volumeRef);
            return volumeRef.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Установить громкость системы
    private void SetSystemVolume(float volume) {
        try {
            CoreAudio.IAudioEndpointVolume audioEndpoint = getAudioEndpoint();
            audioEndpoint.SetMasterVolumeLevelScalar(volume, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Получить IAudioEndpointVolume
    private CoreAudio.IAudioEndpointVolume getAudioEndpoint() {
        // Логика инициализации COM-объекта (например, через CoCreateInstance)
        throw new UnsupportedOperationException("Добавьте реализацию получения IAudioEndpointVolume");
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
