package com.customizer.ui.UIControllers;

import com.customizer.core.utils.RegistryUtils;
import com.customizer.ui.ButtonEffectUtils.HoverEffect;
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
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        HoverEffect.setupButtonHoverEffect(BtnBoost);
        HoverEffect.setupButtonHoverEffect(BtnWallpapers);
        HoverEffect.setupButtonHoverEffect(BtnHome);
        HoverEffect.setupButtonHoverEffect(BtnSettings);

        IcnScrollBar1.getStyleClass().add("scroll-bar");
        ScrollBarValue1.getStyleClass().add("text-field");

         // Инициализация ScrollBar и синхронизация с TextField
    ScrollBarValue1.setText(String.valueOf((int) IcnScrollBar1.getValue()));

    // Обновляем TextField при изменении ScrollBar
    IcnScrollBar1.valueProperty().addListener((observable, oldValue, newValue) -> {
        ScrollBarValue1.setText(String.valueOf(newValue.intValue()));
    });

    // Слушатель для TextField
    ScrollBarValue1.textProperty().addListener((observable, oldValue, newValue) -> { //addListner требует использование лямбда функции
        if (newValue.isEmpty()) {
            return; // Если поле пустое, ничего не делаем
        }

        try {
            int value = Integer.parseInt(newValue); // Пробуем преобразовать в число

            if (value < IcnScrollBar1.getMin()) {
                value = (int) IcnScrollBar1.getMin(); // Ограничиваем значение минимумом
            } else if (value > IcnScrollBar1.getMax()) {
                value = (int) IcnScrollBar1.getMax(); // Ограничиваем значение максимумом
            }

            IcnScrollBar1.setValue(value); // Устанавливаем значение ScrollBar
            ScrollBarValue1.setText(String.valueOf(value)); // Обновляем TextField
        } catch (NumberFormatException e) {
            // Если некорректное значение, возвращаем старое
            ScrollBarValue1.setText(oldValue);
        }
    });

        // Получение имени пользователя из ОС
        String username = System.getProperty("user.name");
        // Установка имени пользователя как текста кнопки
        BtnProfile.setText(username);
}

   UpdateCoins updateCoins = new UpdateCoins();
    public void updateCoinsDisplay() {
       updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
    }

    @FXML
    void BtnBoostClicked(ActionEvent event) {
        mainApp.loadScene("/com/customizer/ui/fxml/Boost.fxml");
    }

    private boolean FirstIconsControllerLaunch = ReadFromJson.ReadFromJSONBooleanT("FirstIconsControllerLaunch");
    @FXML
    void BtnSetDefaultSize(ActionEvent event) {
        if(FirstIconsControllerLaunch) {
            WriteToJson.WriteToJSON("defaultIconSize", RegistryUtils.getIconSize());
            WriteToJson.WriteToJSON("FirstIconsControllerLaunch", false);
            FirstIconsControllerLaunch = false;
        }
        int defaultIconSize = ReadFromJson.ReadFromJSONint("defaultIconSize");

        RegistryUtils.setIconSize(defaultIconSize);
        RestartExplorer.restartExplorer();
        int defaultValue = defaultIconSize; // Значение по умолчанию
        IcnScrollBar1.setValue(defaultValue); // Устанавливаем значение на ползунке
        ScrollBarValue1.setText(String.valueOf(defaultValue)); // Обновляем текст в текстовом поле
        if (mainApp != null) {
            mainApp.addCoins(10);
            updateCoinsDisplay();
        }
    }

      @FXML
    void BtnSetIconSize(ActionEvent event) {
        if(FirstIconsControllerLaunch) {
            WriteToJson.WriteToJSON("defaultIconSize", RegistryUtils.getIconSize());
            WriteToJson.WriteToJSON("FirstIconsControllerLaunch", false);
            FirstIconsControllerLaunch = false;
        }
        int confirmedValue = (int) IcnScrollBar1.getValue();
        System.out.println("Confirmed value: " + confirmedValue);
        RegistryUtils.setIconSize(confirmedValue);
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
                return; // Если поле пустое, не делаем ничего
            }
            int value = Integer.parseInt(input);
            if (value >= IcnScrollBar1.getMin() && value <= IcnScrollBar1.getMax()) {
                IcnScrollBar1.setValue(value); // Обновляем ScrollBar
            } else {
                ScrollBarValue1.setText(String.valueOf((int) IcnScrollBar1.getValue())); // Возвращаем корректное значение
            }
        } catch (NumberFormatException e) {
            ScrollBarValue1.setText(String.valueOf((int) IcnScrollBar1.getValue())); // Возвращаем последнее значение
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
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
}


