package com.customizer.ui.UIControllers;

import com.customizer.ui.ButtonEffectUtils.HoverEffect;
import com.customizer.ui.ButtonEffectUtils.ProfilePicController;
import com.customizer.ui.ButtonEffectUtils.UpdateCoins;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SettingsController {

    @FXML
    private Button BtnBoost;

    @FXML
    private Label coinsLabel;

    @FXML
    private Button BtnProfile;

    @FXML
    private Button BtnHome;

    @FXML
    private ScrollPane InfoPane;

    @FXML
    private Button BtnSettings;

    @FXML
    private Button BtnWallpapers;

    @FXML
    private Button closeButton;

    @FXML
    private Label InfoLabel; // Устаревшее. Заменяем на TextFlow ниже.

    @FXML
    private ImageView dynamicImageView1;

    private MainUI mainApp;

    // Новый элемент для форматированного текста
    private TextFlow formattedInfo;

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

        InfoPane.getStyleClass().add("scroll-pane");

        // Заменяем InfoLabel на форматированный текст в ScrollPane
        setupFormattedInfo();
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

    private void setupFormattedInfo() {
        formattedInfo = new TextFlow();
    
        // Создаём текстовые элементы
        Text title = new Text("NewLook\n");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: white;");
    
        Text paragraph1 = new Text(
                "NewLook is a versatile application for personalizing your workspace and making your computer experience more convenient.\n\n");
        paragraph1.setStyle("-fx-fill: white;");
    
        Text subheading1 = new Text("Main menu\n");
        subheading1.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-fill: white;");
    
        Text paragraph2 = new Text(
                "The main menu will give you a brief introduction to the program's features that will help make your computer more functional and stylish.\n\n");
        paragraph2.setStyle("-fx-fill: white;");
    
        Text subheading2 = new Text("Wallpapers\n");
        subheading2.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-fill: white;");
    
        Text paragraph3 = new Text(
                "Create a unique desktop by selecting wallpapers from the gallery or opening hidden backgrounds. For completing Achievements you get coins that can be used to buy exclusive wallpapers.\n\n");
        paragraph3.setStyle("-fx-fill: white;");
    
        Text subheading3 = new Text("Boost\n");
        subheading3.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-fill: white;");
    
        Text paragraph4 = new Text(
                "Optimize and improve your computer with 4 functional items:\n");
        paragraph4.setStyle("-fx-fill: white;");
        Text paragraph5 = new Text(
                "1. System Optimization - speed up your computer with automated settings.\n");
        paragraph5.setStyle("-fx-fill: white;");
        Text paragraph6 = new Text(
                "2. System Information - find out detailed information about the components of your device.\n");
        paragraph6.setStyle("-fx-fill: white;");
        Text paragraph7 = new Text(
                "3. Microphone Control - quickly adjust the microphone volume through the app.\n");
        paragraph7.setStyle("-fx-fill: white;");
        Text paragraph8 = new Text(
                "4. Icon resizing - customize the size of your desktop icons from 1 to 256 pixels.\n");
        paragraph8.setStyle("-fx-fill: white;");
    
        Text subheading4 = new Text("Levels, Achievements and Coins\n");
        subheading4.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-fill: white;");
    
        Text paragraph9 = new Text(
                "NewLook makes using your computer fun. By completing tasks and earning Achievements, you level up and earn coins that can be spent on unique wallpapers. The more you explore the program's features, the more rewards and personalization become available!\n");
        paragraph9.setStyle("-fx-fill: white;");
    
        // Добавляем текст в TextFlow
        formattedInfo.getChildren().addAll(title, paragraph1, subheading1, paragraph2, subheading2, paragraph3, subheading3, paragraph4, paragraph5, paragraph6, paragraph7, paragraph8, subheading4, paragraph9);
    
        // Обеспечиваем перенос текста и задаем общие стили
        formattedInfo.setStyle("-fx-font-size: 14px; -fx-line-spacing: 5px;");
        formattedInfo.setPrefWidth(800); // Устанавливаем ширину для автоматического переноса
    
        // Оборачиваем TextFlow в VBox (если нужен отступ)
        VBox contentBox = new VBox(formattedInfo);
        contentBox.setSpacing(10); // Отступы между элементами
        InfoPane.setContent(contentBox);
    }
}
