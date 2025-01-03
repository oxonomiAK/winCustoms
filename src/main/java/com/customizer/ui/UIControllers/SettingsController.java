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





public class SettingsController  {

    @FXML
    private Button BtnBoost;
       
    @FXML
    private Label coinsLabel;

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


    @FXML
    private ImageView dynamicImageView1;
    

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
public void initialize() {
    ProfilePicController.CheckProfilePic(dynamicImageView1);
    // Добавляем эффект увеличения при наведении
    HoverEffect.setupButtonHoverEffect(BtnBoost);
    HoverEffect.setupButtonHoverEffect(BtnWallpapers);
    HoverEffect.setupButtonHoverEffect(BtnHome);
    HoverEffect.setupButtonHoverEffect(BtnSettings);

    // Установка имени пользователя на кнопку профиля
    BtnProfile.setText(System.getProperty("user.name"));

}

    
    UpdateCoins updateCoins = new UpdateCoins();
    public void updateCoinsDisplay() {
       updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
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
