package com.customizer.ui.UIControllers;


import com.customizer.Main;
import com.customizer.services.RequestAdmin;
import com.customizer.ui.UiManagers.HoverEffect;
import com.customizer.ui.UiManagers.ProfileNameController;
import com.customizer.ui.UiManagers.ProfilePicController;
import com.customizer.ui.UiManagers.UpdateCoins;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;


public class RocketController {

    @FXML
    private Button BtnBoost;

    @FXML
    private Button BtnGoBack;

    @FXML
    private Button BtnProfile;
   
    @FXML
    private Label coinsLabel;

    @FXML
    private Button BtnToolTip;

    @FXML
    private Tooltip ToolTip;

    @FXML
    private Button BtnHome;

    @FXML
    private Button BtnPerformances;

    @FXML
    private Button BtnSettings;

    @FXML
    private Button BtnWallpapers;
    
    @FXML
    private ImageView dynamicImageView1;

    @FXML
    private Button closeButton;

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        ProfilePicController.CheckProfilePic(dynamicImageView1);

        HoverEffect.setupButtonHoverEffect(BtnBoost);
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnHome);
        HoverEffect.setupButtonHoverEffect(BtnSettings);
    
        // Set Tooltip for BtnToolTip with a delay of 50ms
        ToolTip.setText("You need administrator rights to run it"); // Tooltip text
        ToolTip.setShowDelay(javafx.util.Duration.millis(200)); // Set the delay to 200 ms
        ToolTip.setShowDuration(javafx.util.Duration.seconds(3)); // Display time 3 seconds
        ToolTip.setHideDelay(javafx.util.Duration.millis(100)); // Delay before hiding 100 ms
        BtnPerformances.setTooltip(ToolTip); // Bind Tooltip to the button

        ProfileNameController.ProfileName(BtnProfile);
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
    void BtnPerformancesClicked(ActionEvent event) throws Exception {
        //Request admin rights for executing SystemPropertiesPerformance.exe
        RequestAdmin.RequestAdminRights(mainApp, coinsLabel); 
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
    void BtnGoBackClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Boost.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }

}
