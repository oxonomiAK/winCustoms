package com.customizer.ui.UIControllers;

import com.customizer.core.DeviceInfo;
import com.customizer.ui.ButtonEffectUtils.HoverEffect;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import java.io.IOException;

public class VolumeController {

    @FXML
    private Button BtnBoost;

    @FXML
    private Button ApplyButton;

    @FXML
    private Button BtnProfile;

    @FXML
    private Button BtnHome;

    @FXML
    private Button BtnSettings;

    @FXML
    private Button BtnWallpapers;
    
    @FXML
    private ImageView dynamicImageView1;

    @FXML
    private Label ConectedDevices;

    @FXML
    private Label ConectedDevices1;

    @FXML
    private Label ConectedDevices2;

    @FXML
    private Label ConectedDevices3;

    @FXML
    private Label ConectedDevices4;

    @FXML
    private Button DeviseSettings;

    @FXML
    private CheckBox Headphones;

    @FXML
    private Button closeButton;

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        ProfilePicController.CheckProfilePic(dynamicImageView1);
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        HoverEffect.setupButtonHoverEffect(BtnBoost);
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnHome);
        HoverEffect.setupButtonHoverEffect(BtnSettings);


         // Привязываем действие к кнопке ApplyButton
         ApplyButton.setOnAction(event -> openMicrophoneSettings());

            ConectedDevices.setText(DeviceInfo.getHeadphonesInfo());
            ConectedDevices1.setText(DeviceInfo.getMicrophoneInfo());
        
        //     // Получение имени пользователя из ОС
        // String username = System.getProperty("user.name");
        // // Установка имени пользователя как текста кнопки
        // BtnProfile.setText(username);

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

    // Метод для открытия вкладки "Запись" и свойств микрофона
    private void openMicrophoneSettings() {
        try {
            // Открытие окна настроек звука и переход на вкладку "Запись"
            String command = "control mmsys.cpl,,1"; // Это открывает вкладку записи устройства
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void BtnProfileClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Profile.fxml");
    }
    

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Wallpapers.fxml");
    }

    @FXML
    void BtnSettingsClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Settings.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }

}
