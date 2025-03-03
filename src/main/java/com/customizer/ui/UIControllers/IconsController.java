package com.customizer.ui.UIControllers;

import com.customizer.Main;
import com.customizer.core.GameUtils.AchievementController;
import com.customizer.core.utils.RegistryUtils;
import com.customizer.services.ReadFromJson;
import com.customizer.services.RestartExplorer;
import com.customizer.services.WriteToJson;
import com.customizer.ui.UiManagers.HoverEffect;
import com.customizer.ui.UiManagers.ProfileNameController;
import com.customizer.ui.UiManagers.ProfilePicController;
import com.customizer.ui.UiManagers.UpdateCoins;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class IconsController {

    @FXML
    private Button BtnGoBack;

    @FXML
    private Button BtnBoost;

    @FXML
    private Label coinsLabel;

    @FXML
    private ScrollBar IcnScrollBar1;

    @FXML
    private TextField ScrollBarValue1;

    @FXML
    private Button BtnApply1;

    @FXML
    private Button BtnDefaultSize1;

    @FXML
    private Button BtnProfile;

    @FXML
    private Button BtnWallpapers;

    @FXML
    private Button BtnSettings;

    @FXML
    private ImageView dynamicImageView1;

    @FXML
    private Button BtnHome;

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

        IcnScrollBar1.getStyleClass().add("scroll-bar");
        ScrollBarValue1.getStyleClass().add("text-field");

        // Initialization of ScrollBar and synchronization with TextField
        ScrollBarValue1.setText(String.valueOf((int) IcnScrollBar1.getValue()));

        // Update TextField when ScrollBar is changed
        IcnScrollBar1.valueProperty().addListener((observable, oldValue, newValue) -> {
            ScrollBarValue1.setText(String.valueOf(newValue.intValue()));
        });

        // Listener for TextField
        ScrollBarValue1.textProperty().addListener((observable, oldValue, newValue) -> { // addListner requires the use
                                                                                         // of a lambda function
            if (newValue.isEmpty()) {
                return; // If the field is empty, do nothing
            }

            try {
                int value = Integer.parseInt(newValue); // Try to convert to a number

                if (value < IcnScrollBar1.getMin()) {
                    value = (int) IcnScrollBar1.getMin(); // Limit the value to the minimum
                } else if (value > IcnScrollBar1.getMax()) {
                    value = (int) IcnScrollBar1.getMax(); // Limit the value to the maximum
                }

                IcnScrollBar1.setValue(value); // Set ScrollBar value
                ScrollBarValue1.setText(String.valueOf(value)); // Update TextField
            } catch (NumberFormatException e) {
                // If the value is incorrect, return the old value
                ScrollBarValue1.setText(oldValue);
            }
        });

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

    // Check if the icons have ever been resized to get it original size
    private boolean FirstIconsControllerLaunch = ReadFromJson.ReadFromJSONBooleanT("FirstIconsControllerLaunch");

    @FXML
    void BtnSetDefaultSize(ActionEvent event) {
        // Getting original icon size before changing
        if (FirstIconsControllerLaunch) {
            WriteToJson.WriteToJSON("defaultIconSize", RegistryUtils.getIconSize());
            WriteToJson.WriteToJSON("FirstIconsControllerLaunch", false);
        }
        AchievementController.UnlockIcondefault(mainApp, coinsLabel);
        // Getting original icon size
        int defaultIconSize = ReadFromJson.ReadFromJSONint("defaultIconSize");
        // Setting default size
        RegistryUtils.setIconSize(defaultIconSize);
        // Restarting explorer.exe to see chachges
        RestartExplorer.restartExplorer();
        int defaultValue = defaultIconSize; // Default value
        IcnScrollBar1.setValue(defaultValue); // Set the value on the slider
        ScrollBarValue1.setText(String.valueOf(defaultValue)); // Update the text in the text field
        if (mainApp != null) {
            mainApp.addCoins(10);
            updateCoinsDisplay();
        }
    }

    @FXML
    void BtnSetIconSize(ActionEvent event) {
        // Getting original icon size before changing
        if (FirstIconsControllerLaunch) {
            WriteToJson.WriteToJSON("defaultIconSize", RegistryUtils.getIconSize());
            WriteToJson.WriteToJSON("FirstIconsControllerLaunch", false);
            FirstIconsControllerLaunch = false;
            AchievementController.UnlockIconchange(mainApp, coinsLabel);
        }
        
        int confirmedValue = (int) IcnScrollBar1.getValue();
        if(confirmedValue == 0) confirmedValue = 1;
        // Setting new size value
        if(confirmedValue == 256) {
            AchievementController.UnlockGrowup(mainApp, coinsLabel);
        }
        if(confirmedValue == 1) {
            AchievementController.UnlockGrowdown(mainApp, coinsLabel);
        }
        System.out.println(confirmedValue);
        RegistryUtils.setIconSize(confirmedValue);
        // Restarting explorer.exe to see chachges
        RestartExplorer.restartExplorer();
        if (mainApp != null) {
            mainApp.addCoins(10);
            updateCoinsDisplay();
        }
    }

    @FXML
    void onScrollBarValueChanged(ActionEvent event) {
        try {
            String input = ScrollBarValue1.getText();
            if (input.isEmpty()) {
                return; // If the field is empty, do nothing
            }
            int value = Integer.parseInt(input);
            if (value >= IcnScrollBar1.getMin() && value <= IcnScrollBar1.getMax()) {
                IcnScrollBar1.setValue(value); // Update ScrollBar
            } else {
                ScrollBarValue1.setText(String.valueOf((int) IcnScrollBar1.getValue())); // Return the correct value
            }
        } catch (NumberFormatException e) {
            ScrollBarValue1.setText(String.valueOf((int) IcnScrollBar1.getValue())); // Return the last value
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
    void BtnHomeClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Home.fxml");
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
