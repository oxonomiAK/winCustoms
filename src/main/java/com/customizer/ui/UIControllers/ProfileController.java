package com.customizer.ui.UIControllers;

import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;
import com.customizer.ui.ButtonEffectUtils.HoverEffect;
import com.customizer.ui.ButtonEffectUtils.UpdateCoins;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


public class ProfileController  {
    boolean isWindowOpened;
    public static String picImage;
    @FXML
    private Button BtnBoost;

    @FXML
    private Label coinsLabel;

    @FXML
    private ProgressBar ExperienceBar;

    double currentProgress = ReadFromJson.ReadFromJSONDouble("currentProgress"); 

    @FXML
    private Button BtnHome;
       
    @FXML
    private Label ProgressLabel;

    @FXML
    private Button BtnUsername;

    private TextField textField; // Text field for text input
    private boolean isTextFieldVisible = false;

    @FXML
    private Button BtnSettings;

    @FXML
    private Button BtnUserPicture;

    @FXML
    private Button BtnWallpapers;

    @FXML
    private Button closeButton;

    @FXML
    private StackPane root;

    @FXML
    private ImageView AchImage;

    @FXML
    private Label TextLable;

    @FXML
    private Label TitleLable;

    @FXML
    private Button btnArentYouBored;

    @FXML
    private Button btnBoostAch;

    @FXML
    private ImageView dynamicImageView, dynamicImageView1;
    
    @FXML
    private Button btnCutOnce;
    
    @FXML
    private Button btnIndianaJones;
    
    @FXML
    private Button btnMaxLvl;

    @FXML
    private Button BtnProfile;
    
    @FXML
    private Button btnMeasureTwice;
    
    @FXML
    private Button btnNewFace;
    
    @FXML
    private Button btnRecycler;
    
    @FXML
    private Button btnRefresh;
    
    @FXML
    private Button btnSizeDoesntMatter;
    
    @FXML
    private Button btnSizeWizard;
    
    @FXML
    private Button btnYouHaveTaste;
    
    private MainUI mainApp;
    
    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    public void initialize() {
        
        //Functions to take profile picture from another location
        if(!MainUI.FirstProfilePicChange){
            dynamicImageView.setImage(new Image(ImageCropperController.UserProfilePic)); 
            dynamicImageView1.setImage(new Image(ImageCropperController.UserProfilePic));
        }
        //Check if the last rank has been reached
        if(ranks[currentRank].contains("Legend") && currentProgress >= 1.0) 
            ProgressLabel.setText(ranks[currentRank] + " (Max)"); 
        else
            ProgressLabel.setText(ranks[currentRank]); 
  
        HoverEffect.setupButtonHoverEffect(BtnBoost);
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnHome);
        HoverEffect.setupButtonHoverEffect(BtnSettings);
    
        // Set the initial value of the experience
        ExperienceBar.setProgress(currentProgress);

        //Getting current Windows Username and setting it into profile name in case that name have never been changed
        if(ReadFromJson.ReadFromJSONString("Username").equals("null")){
            String username = System.getProperty("user.name");
            BtnUsername.setText(username);
            BtnProfile.setText(username); 
        }
        else{
            String username = ReadFromJson.ReadFromJSONString("Username");
            BtnProfile.setText(username);
            BtnUsername.setText(username);
        }
        
        textField = new TextField();
        textField.setPromptText("Enter text...");
        textField.setOnAction(event -> onTextEntered()); // Processing of pressing Enter
        
        textField.setVisible(false);
            
        // Add TextField to the parent container of the button
        ((StackPane) BtnUsername.getParent()).getChildren().add(textField);
    
    }
    
    @FXML
    void BtnBoostClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Boost.fxml");
    }
    
    // @FXML
    // void onIncreaseProgressClicked(ActionEvent event) {
    //     gainExperienceWithRanks(0.5); // Увеличиваем прогресс на 10% с анимацией  
    // }
         
    private int currentRank = ReadFromJson.ReadFromJSONint("currentRank"); // Current rank index
    private final String[] ranks = {"Beginner", "Adept", "Master", "Expert", "Professional", "Legend"}; // List of ranks
    
    public void gainExperienceWithRanks(double amount) {
    double targetProgress = Math.min(currentProgress + amount, 1.0); // Current progress target for the current scale
    
    Timeline timeline = new Timeline(
        new KeyFrame(
            Duration.millis(20), // Update every 20 ms
            event -> {
                if (currentProgress < targetProgress) {
                    currentProgress += 0.01;
                    
                    // Corrects for accurate achievement of 100%
                    if (currentProgress >= targetProgress || targetProgress - currentProgress < 0.01) {
                        currentProgress = targetProgress;
                    }
    
                    ExperienceBar.setProgress(currentProgress);
                    
                    // When the scale is completely filled
                    if (currentProgress >= 1.0) {
                        if (currentRank < ranks.length - 1) {
                            // Raise the rank
                            currentRank++;
                            currentProgress = 0; // Reset progress
                            ExperienceBar.setProgress(currentProgress);
                            ProgressLabel.setText(ranks[currentRank]);
                            WriteToJson.WriteToJSON("currentRank", currentRank);
                            if (mainApp != null) {
                                mainApp.addCoins(10);
                                updateCoinsDisplay();
                            }
                        } else {
                            // The last rank has been reached
                            ProgressLabel.setText(ranks[currentRank] + " (Max)");
                        }
                    }
                }
            }
        )
    );
    
    // Set the number of steps
    timeline.setCycleCount((int) ((targetProgress - currentProgress) * 100));
    timeline.play();
    timeline.setOnFinished(event -> WriteToJson.WriteToJSON("currentProgress", currentProgress)); //ceрый
    }
    
    UpdateCoins updateCoins = new UpdateCoins();
    public void updateCoinsDisplay() {
       updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
    }
    @FXML
    void BtnChangeUsername(ActionEvent event) {
        if (!isTextFieldVisible) {
            // Position the TextField on top of the button
            textField.setLayoutX(BtnUsername.getLayoutX());
            textField.setLayoutY(BtnUsername.getLayoutY());
            textField.setPrefWidth(BtnUsername.getWidth()); // The width of the text field is the same as for the button
            textField.setPrefHeight(BtnUsername.getHeight()); // The height of the text field is the same as for the button
                
            textField.setText(BtnUsername.getText()); // Fill with the current button text
            textField.setVisible(true);
            textField.requestFocus(); // Focus on the text field
            isTextFieldVisible = true;
        }
    }
    
    private void onTextEntered() {
        // Change the button text to the entered text
        String username = textField.getText();
        BtnUsername.setText(username);
        BtnProfile.setText(username);
        //Write to .json file new username
        WriteToJson.WriteToJSON("Username", username);
        // Hide TextField
        textField.setVisible(false);
        isTextFieldVisible = false;
    }
    
    @FXML
    void BtnHomeClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Home.fxml");
    }
    
    @FXML
    void BtnChangePicture(ActionEvent event) {
        //Preventing from opening multiple logs with image choosing
        if(!isWindowOpened){
            isWindowOpened = true;
            picImage = ImageCropperController.chooseImage();
            isWindowOpened = false;
        //Do not proceed if the user has not selected anything
            if(picImage != null)
                mainApp.loadScene("/com/customizer/ui/fxml/ImageCropper.fxml");

        }
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

    @FXML
    void btnArentYouBoredClicked(ActionEvent event) {
        updateContent("Aren`t You Bored?", "You have changed the wallpaper 16 times!", "com/customizer/ui/resources/Wallpaper100.png");
    }

    @FXML
    void btnBoostAchClicked(ActionEvent event) {
        updateContent("Boost!", "You changed the performance settings!", "com/customizer/ui/resources/boostach.png");
    }

    @FXML
    void btnCutOnceClicked(ActionEvent event) {
        updateContent("...Cut Once", "You resized the icons back to their original size!", "com/customizer/ui/resources/icondefault.png");
    }

    @FXML
    void btnIndianaJonesClicked(ActionEvent event) {
        updateContent("Indiana Jones", "Unlock all wallpapers!", "com/customizer/ui/resources/adventurer.png");
    }

    @FXML
    void btnMaxLvlClicked(ActionEvent event) {
        updateContent("Fashionista?", "You reached the maximum level!", "com/customizer/ui/resources/maxlvl.png");
    }

    @FXML
    void btnMeasureTwiceClicked(ActionEvent event) {
        updateContent("Measure Twice...", "You resized the icons!", "com/customizer/ui/resources/iconchange.png");
    }

    @FXML
    void btnNewFaceClicked(ActionEvent event) {
        updateContent("New Face", "You have changed your profile picture!", "com/customizer/ui/resources/ProfilePic.png");
    }

    @FXML
    void btnRecyclerClicked(ActionEvent event) {
        updateContent("Recycler", "You have emptied the recycle garbage can!", "com/customizer/ui/resources/bin.png");
    }

    @FXML
    void btnRefreshClicked(ActionEvent event) {
        updateContent("Refresh", "You have installed a new wallpaper!", "com/customizer/ui/resources/Wallpaper1.png");
    }

    @FXML
    void btnSizeDoesntMatterClicked(ActionEvent event) {
        updateContent("Size Doesn't Matter", "You resized the icons to the minimum possible size!", "com/customizer/ui/resources/growdown.png");
    }

    @FXML
    void btnSizeWizardClicked(ActionEvent event) {
        updateContent("Size Wizard", "You resized the icons to the maximum possible size!", "com/customizer/ui/resources/growdown.png");
    }

    @FXML
    void btnYouHaveTasteClicked(ActionEvent event) {
        updateContent("You Have Taste", "You have changed the wallpaper 10 times", "com/customizer/ui/resources/Wallpaper10.png");
    }

    private void updateContent(String title, String text, String imagePath) {
        TitleLable.setText(title);
        TextLable.setText(text);
        AchImage.setImage(new Image(imagePath));
    }
}