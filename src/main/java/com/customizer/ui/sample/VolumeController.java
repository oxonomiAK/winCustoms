package com.customizer.ui.sample;


import com.customizer.core.CoreAudio;
import com.customizer.core.User32F;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.util.Duration;
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
        setupButtonHoverEffect(BtnBoost);
        setupButtonHoverEffect(BtnWallpapers);
        setupButtonHoverEffect(BtnHome);
        setupButtonHoverEffect(BtnSettings);

         // Установить начальное значение громкости из системы
         VolumeSlider.setValue(getSystemVolume() * 100);

         // Обработчик нажатия кнопки "ОК"
         //ApplyButton.setOnAction(event -> SetSystemVolume((float) VolumeSlider.getValue() / 100));
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
    void BtnHomeClicked(ActionEvent event) {
        mainApp.loadScene("Home.fxml");
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
        mainApp.loadScene("Profile.fxml");
    }

    @FXML
    void BtnSettingsClicked(ActionEvent event) {
        mainApp.loadScene("Settings.fxml");
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
