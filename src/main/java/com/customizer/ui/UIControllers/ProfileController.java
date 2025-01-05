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
    private ImageView Wallpaper10 ;
    @FXML
    private ImageView growup;
    @FXML
    private ImageView growdown;
    @FXML
    private ImageView Wallpaper1;
    @FXML
    private ImageView bin;
    @FXML
    private ImageView ProfilePic;
    @FXML
    private ImageView iconchange;
    @FXML
    private ImageView maxlvl;
    @FXML
    private ImageView adventurer ;
    @FXML
    private ImageView icondefault;
    @FXML
    private ImageView boostach;
    @FXML
    private ImageView Wallpaper100;

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
    String lockIncon = "com/customizer/ui/resources/lock.png";

    boolean Wallpaper10Ach = ReadFromJson.ReadFromJSONBooleanF("Wallpaper10Ach");
    boolean growupAch = ReadFromJson.ReadFromJSONBooleanF("growupAch");
    boolean growdownAch = ReadFromJson.ReadFromJSONBooleanF("growdownAch");
    boolean Wallpaper1Ach = ReadFromJson.ReadFromJSONBooleanF("Wallpaper1Ach");
    boolean binAch = ReadFromJson.ReadFromJSONBooleanF("binAch");
    boolean ProfilePicAch = ReadFromJson.ReadFromJSONBooleanF("ProfilePicAch");
    boolean iconchangeAch = ReadFromJson.ReadFromJSONBooleanF("iconchangeAch");
    boolean maxlvlAch = ReadFromJson.ReadFromJSONBooleanF("maxlvlAch");
    boolean adventurerAch = ReadFromJson.ReadFromJSONBooleanF("adventurerAch");
    boolean icondefaultAch = ReadFromJson.ReadFromJSONBooleanF("icondefaultAch");
    boolean boostAch = ReadFromJson.ReadFromJSONBooleanF("boostAch");
    boolean Wallpaper100Ach = ReadFromJson.ReadFromJSONBooleanF("Wallpaper100Ach");

    String imageWallpaper10 = Wallpaper10Ach ? "com/customizer/ui/resources/Wallpaper10.png" : lockIncon;
    String imageGrowup = growupAch ? "com/customizer/ui/resources/growup.png" : lockIncon;
    String imageGrowdown = growdownAch ? "com/customizer/ui/resources/growdown.png" : lockIncon;
    String imageWallpaper1 = Wallpaper1Ach ? "com/customizer/ui/resources/Wallpaper1.png" : lockIncon;
    String imageBin = binAch ? "com/customizer/ui/resources/bin.png" : lockIncon;
    String imageProfilePic = ProfilePicAch ? "com/customizer/ui/resources/ProfilePic.png" : lockIncon;
    String imageIconchange = iconchangeAch ? "com/customizer/ui/resources/iconchange.png" : lockIncon;
    String imageMaxlvl = maxlvlAch ? "com/customizer/ui/resources/maxlvl.png" : lockIncon;
    String imageAdventurer = adventurerAch ? "com/customizer/ui/resources/adventurer.png" : lockIncon;
    String imageicondefault = icondefaultAch ? "com/customizer/ui/resources/icondefault.png" : lockIncon;
    String imageboost = boostAch ? "com/customizer/ui/resources/boostach.png" : lockIncon;
    String imageWallpaper100 = Wallpaper100Ach ? "com/customizer/ui/resources/Wallpaper100.png" : lockIncon;

    @FXML
    public void initialize() {
        Wallpaper10.setImage(new Image(imageWallpaper10));
        growup.setImage(new Image(imageGrowup));
        growdown.setImage(new Image(imageGrowdown));
        Wallpaper1.setImage(new Image(imageWallpaper1));
        bin.setImage(new Image(imageBin));
        ProfilePic.setImage(new Image(imageProfilePic));
        iconchange.setImage(new Image(imageIconchange));
        maxlvl.setImage(new Image(imageMaxlvl));
        adventurer.setImage(new Image(imageAdventurer));
        icondefault.setImage(new Image(imageicondefault));
        boostach.setImage(new Image(imageboost));
        Wallpaper100.setImage(new Image(imageWallpaper100));
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
        updateContent("Aren`t You Bored?", "You have changed the wallpaper 16 times!", imageWallpaper100);
    }

    @FXML
    void btnBoostAchClicked(ActionEvent event) {
        updateContent("Boost!", "You changed the performance settings!", imageboost);
    }

    @FXML
    void btnCutOnceClicked(ActionEvent event) {
        updateContent("...Cut Once", "You resized the icons back to their original size!", imageicondefault);
    }

    @FXML
    void btnIndianaJonesClicked(ActionEvent event) {
        updateContent("Indiana Jones", "Unlock all wallpapers!", imageAdventurer);
    }

    @FXML
    void btnMaxLvlClicked(ActionEvent event) {
        updateContent("Fashionista?", "You reached the maximum level!", imageMaxlvl);
    }

    @FXML
    void btnMeasureTwiceClicked(ActionEvent event) {
        updateContent("Measure Twice...", "You resized the icons!", imageIconchange);
    }

    @FXML
    void btnNewFaceClicked(ActionEvent event) {
        updateContent("New Face", "You have changed your profile picture!", imageProfilePic);
    }

    @FXML
    void btnRecyclerClicked(ActionEvent event) {
        updateContent("Recycler", "You have emptied the recycle garbage can!", imageBin);
    }

    @FXML
    void btnRefreshClicked(ActionEvent event) {
        updateContent("Refresh", "You have installed a new wallpaper!", imageWallpaper1);
    }

    @FXML
    void btnSizeDoesntMatterClicked(ActionEvent event) {
        updateContent("Size Doesn't Matter", "You resized the icons to the minimum possible size!", imageGrowdown);
    }

    @FXML
    void btnSizeWizardClicked(ActionEvent event) {
        updateContent("Size Wizard", "You resized the icons to the maximum possible size!", imageGrowup);
    }

    @FXML
    void btnYouHaveTasteClicked(ActionEvent event) {
        updateContent("You Have Taste", "You have changed the wallpaper 10 times", imageWallpaper10);
    }

    private void updateContent(String title, String text, String imagePath) {
        TitleLable.setText(title);
        TextLable.setText(text);
        AchImage.setImage(new Image(imagePath));
    }
}