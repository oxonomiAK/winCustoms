package com.customizer.ui.UIControllers;


import java.io.File;
import java.lang.reflect.AnnotatedArrayType;
import java.util.HashMap;
import java.util.Map;
import com.customizer.core.utils.WallpaperUtils;
import com.customizer.ui.ButtonEffectUtils.HoverEffect;
import com.customizer.ui.ButtonEffectUtils.LockManager;
import com.customizer.ui.ButtonEffectUtils.UpdateCoins;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    private Button closeButton;
       
    @FXML
    private Label coinsLabel;

    @FXML
    private Button BtnWallpapers1;
    @FXML
    private Button BtnWallpapers2; // Кнопка, которая будет увеличиваться
    @FXML
    private Button BtnWallpapers3; // Кнопка, которая будет увеличиваться
    @FXML
    private Button BtnWallpapers4; // Кнопка, которая будет увеличиваться

    private final Map<Button, String> wallpaperPaths = new HashMap<>();

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }
        int AnimeCond = 10;
        int PinkVilageCond = 20;
        int RetroCond = 30;
        int RabbitCond = 40;

        boolean AnimeUnlocked = LockManager.CheckForLock("WallpaperAnime", "Coins", AnimeCond);
        boolean PinkVilageUnlocked = LockManager.CheckForLock("WallpaperPinkVilage", "Coins", PinkVilageCond);
        boolean RetroUnlocked = LockManager.CheckForLock("WallpaperRetro", "Coins", RetroCond);
        boolean RabbitUnlocked = LockManager.CheckForLock("WallpaperRabbit", "Coins", RabbitCond);
    @FXML
    public void initialize() {
        String lockIncon = "com/customizer/ui/resources/lock.png";
        if(!AnimeUnlocked) Wall1.setImage(new Image(lockIncon));
        if(!PinkVilageUnlocked) Wall2.setImage(new Image(lockIncon));
        if(!RetroUnlocked) Wall3.setImage(new Image(lockIncon));
        if(!RabbitUnlocked) Wall4.setImage(new Image(lockIncon));
        
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        HoverEffect.setupButtonHoverEffect(BtnBoost);
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnHome);
        HoverEffect.setupButtonHoverEffect(BtnSettings);

        wallpaperPaths.put(BtnWallpapers1, "src/main/java/com/customizer/ui/resources/anime.png" );
        wallpaperPaths.put(BtnWallpapers2, "src/main/java/com/customizer/ui/resources/PinkVilage.png");
        wallpaperPaths.put(BtnWallpapers3, "src/main/java/com/customizer/ui/resources/Retro.png");
        wallpaperPaths.put(BtnWallpapers4, "src/main/java/com/customizer/ui/resources/Rabbit.png");

        // Сохраняем исходные позиции кнопок
        originalPositions.put(BtnWallpapers1, new Double[]{BtnWallpapers1.getLayoutX(), BtnWallpapers1.getLayoutY()});
        originalPositions.put(BtnWallpapers2, new Double[]{BtnWallpapers2.getLayoutX(), BtnWallpapers2.getLayoutY()});
        originalPositions.put(BtnWallpapers3, new Double[]{BtnWallpapers3.getLayoutX(), BtnWallpapers3.getLayoutY()});
        originalPositions.put(BtnWallpapers4, new Double[]{BtnWallpapers4.getLayoutX(), BtnWallpapers4.getLayoutY()});
    
        // Устанавливаем начальное состояние кнопок
        buttonStates.put(BtnWallpapers1, false);
        buttonStates.put(BtnWallpapers2, false);
        buttonStates.put(BtnWallpapers3, false);
        buttonStates.put(BtnWallpapers4, false);

        // Получение имени пользователя из ОС
        String username = System.getProperty("user.name");
        // Установка имени пользователя как текста кнопки
        BtnProfile.setText(username);
    }
    
        private final Map<Button, Boolean> buttonStates = new HashMap<>();
        private final Map<Button, Double[]> originalPositions = new HashMap<>();

    @FXML
    void BtnBoostClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Boost.fxml");
    }
    

    @FXML
    void BtnWallpapers1Clicked(ActionEvent event) {
        if (AnimeUnlocked) 
            handleWallpaperButtonClick(BtnWallpapers1, Wall1, event);
        else 
            showNotification("Необходимо "+ AnimeCond +" монет для разблокировки!");
    }
    
    @FXML
    void BtnWallpapers2Clicked(ActionEvent event) {
        if (PinkVilageUnlocked) 
            handleWallpaperButtonClick(BtnWallpapers2, Wall2, event);
         else 
            showNotification("Необходимо "+ PinkVilageCond +" монет для разблокировки!");
    }
    
    @FXML
    void BtnWallpapers3Clicked(ActionEvent event) {
        if (RetroUnlocked) 
            handleWallpaperButtonClick(BtnWallpapers3, Wall3, event);
        else 
            showNotification("Необходимо "+ RetroCond +" монет для разблокировки!");
    }
    
    @FXML
    void BtnWallpapers4Clicked(ActionEvent event) {
        if (RabbitUnlocked)
            handleWallpaperButtonClick(BtnWallpapers4, Wall4, event);
        else 
            showNotification("Необходимо "+ RabbitCond +" монет для разблокировки!");
    }
    
        private void showNotification(String message) {
            Label notification = new Label(message);
            
            notification.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-text-fill: white; "
                    + "-fx-padding: 10px; -fx-font-size: 14px; -fx-border-radius: 10; -fx-background-radius: 10;");
            notification.setAlignment(Pos.CENTER);
            notification.setPrefWidth(450);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), notification);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);

            PauseTransition pause = new PauseTransition(Duration.millis(1500));

            FadeTransition fadeOut = new FadeTransition(Duration.millis(500), notification);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);

            SequentialTransition sequentialTransition = new SequentialTransition(fadeIn, pause, fadeOut);
                sequentialTransition.play();
            
            

            Scene scene = BtnWallpapers1.getScene(); // Получаем текущую сцену
            Pane rootPane = (Pane) scene.getRoot(); // Корневой узел

            notification.setLayoutX((scene.getWidth() - notification.getPrefWidth()) / 2);
            notification.setLayoutY(scene.getHeight() - 60); // Располагаем внизу экрана

            rootPane.getChildren().add(notification);

            // Убираем уведомление через 5 секунд
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ev -> {
                rootPane.getChildren().remove(notification);
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }

    private void handleWallpaperButtonClick(Button button, ImageView wall, ActionEvent event) {
        boolean isEnlarged = buttonStates.getOrDefault(button, false);
    
        if (isEnlarged) {
            // Если кнопка уже увеличена, вернуть её в исходное состояние
            resetButton(button);
            buttonStates.put(button, false);
            return;
        }
    
        // Увеличиваем кнопку
        buttonStates.put(button, true);
    
        javafx.scene.Scene scene = button.getScene();
    
        double newX = 500;
        double newY = 220;
    
        // Блокируем обработчики событий у остальных кнопок
        blockButtonClicks(true);
    
        // Оставляем видимой только активную кнопку
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
                    File f = new File(imagePath);
                    String absolutePath = f.getAbsolutePath();
                    System.out.println("Устанавливаем обои: " + absolutePath);
                
                    if (wall != null) {
                        WallpaperUtils.setWallpaper(absolutePath);
                    }
                
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
            // Возвращаем кнопку на исходное положение
            button.setLayoutX(originalPosition[0]);
            button.setLayoutY(originalPosition[1]);
    
            // Показываем скрытые кнопки
            BtnWallpapers1.setVisible(true);
            BtnWallpapers2.setVisible(true);
            BtnWallpapers3.setVisible(true);
            BtnWallpapers4.setVisible(true);
            BtnArrowRight.setVisible(true);
            BtnArrowLeft.setVisible(true);
    
            // Включаем обработчики событий для всех кнопок
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


