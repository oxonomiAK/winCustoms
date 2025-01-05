package com.customizer.ui.UIControllers;

import com.customizer.core.DeviceInfo;
import com.customizer.ui.ButtonEffectUtils.HoverEffect;
import com.customizer.ui.ButtonEffectUtils.ProfileNameController;
import com.customizer.ui.ButtonEffectUtils.ProfilePicController;
import com.customizer.ui.ButtonEffectUtils.UpdateCoins;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class VolumeController {

    @FXML
    private Button BtnBoost;

    @FXML
    private Button BtnGoBack;

    @FXML
    private Button ApplyButton;

    @FXML
    private Button BtnProfile;

    @FXML
    private Label coinsLabel;

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
    private Button DeviseSettings;

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

        // Bind the action to the ApplyButton
        ApplyButton.setOnAction(event -> openMicrophoneSettings());

        ConectedDevices.setText(DeviceInfo.getHeadphonesInfo());
        ConectedDevices1.setText(DeviceInfo.getMicrophoneInfo());

        // Getting username from OS
        String username = System.getProperty("user.name");
        // Set the user name as the button text
        BtnProfile.setText(username);

        ProfileNameController.ProfileName(BtnProfile);
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

    // Method to open the ‘Record’ tab and microphone properties
    private void openMicrophoneSettings() {
        try {
            // Open the audio settings window and switch to the ‘Record’ tab
            String command = "control mmsys.cpl,,1"; // This opens the device record tab
            Runtime.getRuntime().exec(command);
        } catch (IOException e) { // Error handling
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
    void BtnGoBackClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Boost.fxml");
    }

    UpdateCoins updateCoins = new UpdateCoins();

    public void updateCoinsDisplay() {
        updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }

}
