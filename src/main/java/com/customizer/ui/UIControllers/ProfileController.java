package com.customizer.ui.UIControllers;


import java.io.File;

import com.customizer.services.ReadFromJson;
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

        // Хранит текущее значение опыта (от 0.0 до 1.0)
        private double currentProgress = 0.0;

        // Метод, который увеличивает прогресс опыта
        public void gainExperience(double amount) {
            // Увеличиваем прогресс, не превышая 1.0
            currentProgress = Math.min(currentProgress + amount, 1.0);
            ExperienceBar.setProgress(currentProgress);
    
            // Проверяем, если шкала заполнена
            if (currentProgress >= 1.0) {
                System.out.println("Level up!");
            }
        }

    @FXML
    private Button BtnHome;
       
    @FXML
    private Label ProgressLabel;

    @FXML
    private Button BtnUsername;

    private TextField textField; // Текстовое поле для ввода текста
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
        if(!MainUI.FirstProfilePicChange){
            dynamicImageView.setImage(new Image(ImageCropperController.UserProfilePic));
            dynamicImageView1.setImage(new Image(ImageCropperController.UserProfilePic));
        }
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        HoverEffect.setupButtonHoverEffect(BtnBoost);
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnHome);
        HoverEffect.setupButtonHoverEffect(BtnSettings);
    
        // Устанавливаем начальное значение опыта
        ExperienceBar.setProgress(currentProgress);
        
    
        // Получение имени пользователя из ОС
        String username = System.getProperty("user.name");
        // Установка имени пользователя как текста кнопки
        BtnUsername.setText(username);

        // Получение имени пользователя из ОС
        String username1 = System.getProperty("user.name");
        // Установка имени пользователя как текста кнопки
        BtnProfile.setText(username1); 
            
        textField = new TextField();
        textField.setPromptText("Введите текст...");
        textField.setOnAction(event -> onTextEntered()); // Обработка нажатия Enter
        textField.setVisible(false);
            
        // Добавление TextField в родительский контейнер кнопки
        ((StackPane) BtnUsername.getParent()).getChildren().add(textField);
    
    }
    

    
    
    @FXML
    void BtnBoostClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Boost.fxml");
    }
    
    @FXML
    void onIncreaseProgressClicked(ActionEvent event) {
        gainExperienceWithRanks(0.5); // Увеличиваем прогресс на 10% с анимацией
    }
         
    private int currentRank = 0; // Индекс текущего ранга
    private final String[] ranks = {"Beginner", "Adept", "Master", "Expert", "Professional", "Legend"}; // Список рангов
    
    public void gainExperienceWithRanks(double amount) {
    double targetProgress = Math.min(currentProgress + amount, 1.0); // Целевой прогресс для текущей шкалы
    
    Timeline timeline = new Timeline(
        new KeyFrame(
            Duration.millis(20), // Обновление каждые 20 мс
            event -> {
                if (currentProgress < targetProgress) {
                    currentProgress += 0.01;
    
                    // Исправление для точного достижения 100%
                    if (currentProgress >= targetProgress || targetProgress - currentProgress < 0.01) {
                        currentProgress = targetProgress;
                    }
    
                    ExperienceBar.setProgress(currentProgress);
    
                    // Когда шкала полностью заполнена
                    if (currentProgress >= 1.0) {
                        if (currentRank < ranks.length - 1) {
                            // Повышаем ранг
                            currentRank++;
                            currentProgress = 0; // Сбрасываем прогресс
                            ExperienceBar.setProgress(currentProgress);
                            ProgressLabel.setText(ranks[currentRank]);
                            if (mainApp != null) {
                                mainApp.addCoins(10);
                                updateCoinsDisplay();
                            }
                        } else {
                            // Достигнут последний ранг
                            ProgressLabel.setText(ranks[currentRank] + " (Max)");
                        }
                    }
                }
            }
        )
    );
    
    // Устанавливаем количество шагов
    timeline.setCycleCount((int) ((targetProgress - currentProgress) * 100));
    timeline.play();
    }
    
    UpdateCoins updateCoins = new UpdateCoins();
    public void updateCoinsDisplay() {
       updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
    }
    @FXML
    void BtnChangeUsername(ActionEvent event) {
        if (!isTextFieldVisible) {
            // Позиционируем TextField поверх кнопки
            textField.setLayoutX(BtnUsername.getLayoutX());
            textField.setLayoutY(BtnUsername.getLayoutY());
            textField.setPrefWidth(BtnUsername.getWidth()); // Ширина текстового поля как у кнопки
            textField.setPrefHeight(BtnUsername.getHeight()); // Высота текстового поля как у кнопки
                
            textField.setText(BtnUsername.getText()); // Заполняем текущим текстом кнопки
            textField.setVisible(true);
            textField.requestFocus(); // Фокус на текстовом поле
            isTextFieldVisible = true;
        }
    }
    
    private void onTextEntered() {
        // Изменяем текст кнопки на введенный текст
        BtnUsername.setText(textField.getText());
    
        // Скрываем TextField
        textField.setVisible(false);
        isTextFieldVisible = false;
    }
    
    @FXML
    void BtnHomeClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Home.fxml");
    }
    
    @FXML
    void BtnChangePicture(ActionEvent event) {
        if(!isWindowOpened){
            isWindowOpened = true;
            picImage = ImageCropperController.chooseImage();
            isWindowOpened = false;
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

     /**
     * Обновление текста и изображения.
     *
     * @param title Текст для `TitleLabel`.
     * @param text Текст для `TextLabel`.
     * @param imagePath Путь к изображению.
     */
    private void updateContent(String title, String text, String imagePath) {
        TitleLable.setText(title);
        TextLable.setText(text);
        AchImage.setImage(new Image(imagePath));
    }
}