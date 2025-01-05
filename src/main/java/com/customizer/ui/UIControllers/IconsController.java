package com.customizer.ui.UIControllers;

import com.customizer.core.utils.RegistryUtils;
import com.customizer.ui.ButtonEffectUtils.HoverEffect;
import com.customizer.ui.ButtonEffectUtils.ProfileNameController;
import com.customizer.ui.ButtonEffectUtils.ProfilePicController;
import com.customizer.ui.ButtonEffectUtils.UpdateCoins;
import com.customizer.services.ReadFromJson;
import com.customizer.services.RestartExplorer;
import com.customizer.services.WriteToJson;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class IconsController  {

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

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
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
        ScrollBarValue1.textProperty().addListener((observable, oldValue, newValue) -> { //addListner requires the use of a lambda function
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

    private boolean FirstIconsControllerLaunch = ReadFromJson.ReadFromJSONBooleanT("FirstIconsControllerLaunch"); //серый
    @FXML
    void BtnSetDefaultSize(ActionEvent event) {//серый
        if(FirstIconsControllerLaunch) {//серый
            WriteToJson.WriteToJSON("defaultIconSize", RegistryUtils.getIconSize());//серый
            WriteToJson.WriteToJSON("FirstIconsControllerLaunch", false);//серый
            FirstIconsControllerLaunch = false;//серый
        }
        int defaultIconSize = ReadFromJson.ReadFromJSONint("defaultIconSize");//серый

        RegistryUtils.setIconSize(defaultIconSize);//серый
        RestartExplorer.restartExplorer();//серый
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
        if(FirstIconsControllerLaunch) {//серый
            WriteToJson.WriteToJSON("defaultIconSize", RegistryUtils.getIconSize());//серый
            WriteToJson.WriteToJSON("FirstIconsControllerLaunch", false);//серый
            FirstIconsControllerLaunch = false;//серый
        }//серый
        int confirmedValue = (int) IcnScrollBar1.getValue(); //серый
        System.out.println("Confirmed value: " + confirmedValue);//серый
        RegistryUtils.setIconSize(confirmedValue);//серый
        RestartExplorer.restartExplorer();//серый
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


