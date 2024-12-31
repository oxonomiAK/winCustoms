package com.customizer.ui.UIControllers;

import com.customizer.ui.ButtonEffectUtils.HoverEffect;
import com.customizer.ui.ButtonEffectUtils.ProfilePicController;
import com.customizer.ui.ButtonEffectUtils.UpdateCoins;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;



public class BoostController  {

    @FXML
    private Button BtnBoost;

    @FXML
    private Label coinsLabel;

    @FXML
    private Button BtnProfile;

    @FXML
    private Button BtnRocket;

    @FXML
    private Button BtnHome;

    @FXML
    private Button BtnIcons;

    @FXML
    private Button BtnSettings;

    @FXML
    private Button BtnCompComponents;

    @FXML
    private Button BtnVolume;
    
    @FXML
    private ImageView dynamicImageView1;

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
        ProfilePicController.CheckProfilePic(dynamicImageView1);
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        HoverEffect.setupButtonHoverEffect(BtnBoost);
        HoverEffect.setupButtonHoverEffect(BtnIcons);
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnVolume);
        HoverEffect.setupButtonHoverEffect(BtnCompComponents);
        HoverEffect.setupButtonHoverEffect(BtnRocket);
        HoverEffect.setupButtonHoverEffect(BtnSettings);

    }


    @FXML
    void BtnBoostClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Boost.fxml");
    }


    @FXML
    void BtnRocketClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Rocket.fxml");
    }

    @FXML
    void BtnHomeClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Home.fxml");
    }

    @FXML
    void BtnIconsClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Icons.fxml");
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
    void BtnCompComponentsClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/CompComponents.fxml");
    }

    @FXML
    void BtnVolumeClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Volume.fxml");
    }

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Wallpapers.fxml");
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
