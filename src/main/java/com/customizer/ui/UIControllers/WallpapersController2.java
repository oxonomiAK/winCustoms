package com.customizer.ui.UIControllers;

import java.util.HashMap;
import java.util.Map;

import com.customizer.Main;
import com.customizer.core.WallpaperApply;
import com.customizer.core.GameUtils.AchievementController;
import com.customizer.services.ReadFromJson;
import com.customizer.ui.UiManagers.HoverEffect;
import com.customizer.ui.UiManagers.LockManager;
import com.customizer.ui.UiManagers.NotificationManager;
import com.customizer.ui.UiManagers.ProfileNameController;
import com.customizer.ui.UiManagers.ProfilePicController;
import com.customizer.ui.UiManagers.UpdateCoins;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WallpapersController2  {

    @FXML
    private Button BtnBoost;

    @FXML
    private Button BtnArrowLeft;

    @FXML
    private Button BtnArrowRight;

    @FXML
    private Button BtnWallpapers;

    @FXML
    private ImageView Wall1;

    @FXML
    private ImageView Wall2;

    @FXML
    private ImageView Wall3;

    @FXML
    private ImageView Wall4;

    @FXML
    private Button BtnProfile;

    @FXML
    private Button BtnSettings;

    @FXML
    private Button BtnHome;
    
    @FXML
    private ImageView dynamicImageView1;

    @FXML
    private Button closeButton;
       
    @FXML
    private Label coinsLabel;

    @FXML
    private Button BtnWallpapers1;

    @FXML
    private Button BtnWallpapers2; 

    @FXML
    private Button BtnWallpapers3; 
    
    @FXML
    private Button BtnWallpapers4; 

    private final Map<Button, String> wallpaperPaths = new HashMap<>();

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
        //Wallpapers prices
        int AnimeCond = 10;
        int PinkVilageCond = 20;
        int RetroCond = 30;
        int RabbitCond = 40;

        //Can be wallpapers unlocked
        boolean AnimeStatus = LockManager.CanUnlock("WallpaperAnime", "Coins", AnimeCond);
        boolean PinkVilageStatus = LockManager.CanUnlock("WallpaperPinkVilage", "Coins", PinkVilageCond);
        boolean RetroStatus = LockManager.CanUnlock("WallpaperRetro", "Coins", RetroCond);
        boolean RabbitStatus = LockManager.CanUnlock("WallpaperRabbit", "Coins", RabbitCond);
        
        //Current lock status of wallpapers
        boolean AnimeUnlocked = ReadFromJson.ReadFromJSONBooleanF("WallpaperAnime");
        boolean PinkVilageUnlocked = ReadFromJson.ReadFromJSONBooleanF("WallpaperPinkVilage");
        boolean RetroUnlocked = ReadFromJson.ReadFromJSONBooleanF("WallpaperRetro");
        boolean RabbitUnlocked = ReadFromJson.ReadFromJSONBooleanF("WallpaperRabbit");
        public static boolean Wallpaper2page  = false;
    @FXML
    public void initialize() {
        if(AnimeUnlocked && PinkVilageUnlocked && RetroUnlocked && RabbitUnlocked) Wallpaper2page = true;
        AchievementController.UnlockAllWallp();
        ProfilePicController.CheckProfilePic(dynamicImageView1);
        //Replace wallpaper with lock icon if hasn't been purhcased
        String lockIncon = "com/customizer/ui/resources/lock.png";
        if(!AnimeUnlocked) Wall1.setImage(new Image(lockIncon));
        if(!PinkVilageUnlocked) Wall2.setImage(new Image(lockIncon));
        if(!RetroUnlocked) Wall3.setImage(new Image(lockIncon));
        if(!RabbitUnlocked) Wall4.setImage(new Image(lockIncon));
        
        HoverEffect.setupButtonHoverEffect(BtnBoost);
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnHome);
        HoverEffect.setupButtonHoverEffect(BtnSettings);

        wallpaperPaths.put(BtnWallpapers1, "/com/customizer/ui/resources/anime.png" );
        wallpaperPaths.put(BtnWallpapers2, "/com/customizer/ui/resources/PinkVilage.png");
        wallpaperPaths.put(BtnWallpapers3, "/com/customizer/ui/resources/Retro.png");
        wallpaperPaths.put(BtnWallpapers4, "/com/customizer/ui/resources/Rabbit.png");

        originalPositions.put(BtnWallpapers1, new Double[]{BtnWallpapers1.getLayoutX(), BtnWallpapers1.getLayoutY()});
        originalPositions.put(BtnWallpapers2, new Double[]{BtnWallpapers2.getLayoutX(), BtnWallpapers2.getLayoutY()});
        originalPositions.put(BtnWallpapers3, new Double[]{BtnWallpapers3.getLayoutX(), BtnWallpapers3.getLayoutY()});
        originalPositions.put(BtnWallpapers4, new Double[]{BtnWallpapers4.getLayoutX(), BtnWallpapers4.getLayoutY()});
    
        buttonStates.put(BtnWallpapers1, false);
        buttonStates.put(BtnWallpapers2, false);
        buttonStates.put(BtnWallpapers3, false);
        buttonStates.put(BtnWallpapers4, false);


        ProfileNameController.ProfileName(BtnProfile);
    }
    
        private final Map<Button, Boolean> buttonStates = new HashMap<>();
        private final Map<Button, Double[]> originalPositions = new HashMap<>();

    @FXML
    void BtnBoostClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Boost.fxml");
    }
    

    @FXML
    void BtnWallpapers1Clicked(ActionEvent event) {
        //Unlocking wallpapers
        LockManager.CheckAndUnlock(AnimeStatus, AnimeUnlocked, AnimeCond, "WallpaperAnime", "/com/customizer/ui/fxml/Wallpapers2.fxml", mainApp);
        //Get notigication if wallpapers are locked
        if (AnimeUnlocked){
            handleWallpaperButtonClick(BtnWallpapers1, Wall1, event);
        }
        else 
            NotificationManager.showNotification("Necessary "+ AnimeCond +" coins to unlock!", BtnWallpapers1);
    }
    
    @FXML
    void BtnWallpapers2Clicked(ActionEvent event) {

        LockManager.CheckAndUnlock(PinkVilageStatus, PinkVilageUnlocked, PinkVilageCond, "WallpaperPinkVilage", "/com/customizer/ui/fxml/Wallpapers2.fxml", mainApp);

        if (PinkVilageUnlocked) 
            handleWallpaperButtonClick(BtnWallpapers2, Wall2, event);
        else 
            NotificationManager.showNotification("Necessary "+ PinkVilageCond +" coins to unlock!", BtnWallpapers1);
    }
    
    @FXML
    void BtnWallpapers3Clicked(ActionEvent event) {

        LockManager.CheckAndUnlock(RetroStatus, RetroUnlocked, RetroCond, "WallpaperRetro", "/com/customizer/ui/fxml/Wallpapers2.fxml", mainApp);
        
        if (RetroUnlocked) 
            handleWallpaperButtonClick(BtnWallpapers3, Wall3, event);
        else 
            NotificationManager.showNotification("Necessary "+ RetroCond +" coins to unlock!", BtnWallpapers1);
    }
    
    @FXML
    void BtnWallpapers4Clicked(ActionEvent event) {

        LockManager.CheckAndUnlock(RabbitStatus, RabbitUnlocked, RabbitCond, "WallpaperRabbit", "/com/customizer/ui/fxml/Wallpapers2.fxml", mainApp);

        if (RabbitUnlocked)
            handleWallpaperButtonClick(BtnWallpapers4, Wall4, event);
        else 
            NotificationManager.showNotification("Necessary "+ RabbitCond +" coins to unlock!", BtnWallpapers1);
    }
    

    private void handleWallpaperButtonClick(Button button, ImageView wall, ActionEvent event) {
        boolean isEnlarged = buttonStates.getOrDefault(button, false);
    
        if (isEnlarged) {
            resetButton(button);
            buttonStates.put(button, false);
            return;
        }
    
        // Enlarge the button
        buttonStates.put(button, true);
    
        javafx.scene.Scene scene = button.getScene();
    
        double newX = 500;
        double newY = 220;
    
        // Block event handlers of other buttons
        blockButtonClicks(true);
    
        // Leave only the active button visible
        BtnWallpapers1.setVisible(button == BtnWallpapers1);
        BtnWallpapers2.setVisible(button == BtnWallpapers2);
        BtnWallpapers3.setVisible(button == BtnWallpapers3);
        BtnWallpapers4.setVisible(button == BtnWallpapers4);
        BtnArrowRight.setVisible(button == BtnArrowRight);
        BtnArrowLeft.setVisible(button == BtnArrowLeft);
    
        String imagePath = wallpaperPaths.get(button);
    
        if (imagePath == null || imagePath.isEmpty()) {
            System.out.println("UserData кнопки пустое или отсутствует!");
            return;
        }
    
        button.setLayoutX(newX);
        button.setLayoutY(newY);
    
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(300), button);
        scaleUp.setToX(1.2);
        scaleUp.setToY(1.2);
    
        scaleUp.setOnFinished(e -> {
            Button goBackButton = new Button("Go Back");
            goBackButton.setPrefWidth(100);
            goBackButton.setPrefHeight(35);
            goBackButton.setLayoutX(newX + 55);
            goBackButton.setLayoutY(newY + 285);
            goBackButton.setStyle("-fx-background-color: #2a2a2a; -fx-text-fill: white; -fx-font-family: 'Myanmar Text'; -fx-font-size: 12px; -fx-font-weight: bold;");
            
            
                Button setWallpaperButton = new Button("Set Wallpaper");                
                setWallpaperButton.setPrefWidth(100);
                setWallpaperButton.setPrefHeight(35);
                setWallpaperButton.setLayoutX(newX + 235);
                setWallpaperButton.setLayoutY(newY + 285);
                setWallpaperButton.setStyle("-fx-background-color: #2a2a2a; -fx-text-fill: white; -fx-font-family: 'Myanmar Text'; -fx-font-size: 12px; -fx-font-weight: bold;");
                
                setWallpaperButton.setOnAction(ev -> {
                    WallpaperApply.WallApply(mainApp, imagePath);
                    AchievementController.CountWallpaper(mainApp, coinsLabel);

                });
            
            goBackButton.setOnAction(ev -> {
                resetButton(button);
                buttonStates.put(button, false);
    
                ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().remove(goBackButton);
                ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().remove(setWallpaperButton);
            });
    
            ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(goBackButton);
            ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(setWallpaperButton);
        });
    
        scaleUp.play();
    
    }
    
    private void blockButtonClicks(boolean block) {
        if (block) {
            BtnWallpapers1.setOnAction(null);
            BtnWallpapers2.setOnAction(null);
            BtnWallpapers3.setOnAction(null);
            BtnWallpapers4.setOnAction(null);
        } else {
            BtnWallpapers1.setOnAction(this::BtnWallpapers1Clicked);
            BtnWallpapers2.setOnAction(this::BtnWallpapers2Clicked);
            BtnWallpapers3.setOnAction(this::BtnWallpapers3Clicked);
            BtnWallpapers4.setOnAction(this::BtnWallpapers4Clicked);
        }
    }
    private void resetButton(Button button) {
        Double[] originalPosition = originalPositions.get(button);

        if (originalPosition == null) {
            
            System.err.println("Исходные координаты для кнопки не найдены!");
            return;
        }
    
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(300), button);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);
    
        scaleDown.setOnFinished(animationEvent -> {
            // Return the button to default position
            button.setLayoutX(originalPosition[0]);
            button.setLayoutY(originalPosition[1]);
    
            // Show hidden buttons
            BtnWallpapers1.setVisible(true);
            BtnWallpapers2.setVisible(true);
            BtnWallpapers3.setVisible(true);
            BtnWallpapers4.setVisible(true);
            BtnArrowRight.setVisible(true);
            BtnArrowLeft.setVisible(true);
    
            // Enable event handlers for all buttons
            blockButtonClicks(false);
        });
    
        scaleDown.play();
    }
    
    UpdateCoins updateCoins = new UpdateCoins();
    public void updateCoinsDisplay() {
       updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
    }

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Wallpapers.fxml");
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
    void BtnHomeClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Home.fxml");
    }

    @FXML
    void BtnArrowRightClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Wallpapers3.fxml");
    }

    @FXML
    void BtnArrowLeftClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Wallpapers.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
}


