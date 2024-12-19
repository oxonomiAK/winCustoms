package com.customizer.ui.sample;

import com.customizer.core.utils.RegistryUtils;
import com.customizer.services.RestartExplorer;
import com.customizer.services.WriteToJson;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class IconsController {

    @FXML
    private Button BtnBoost;

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
    private Button BtnHome;

    @FXML
    private Button closeButton;

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        setupButtonHoverEffect(BtnBoost);
        setupButtonHoverEffect(BtnWallpapers);
        setupButtonHoverEffect(BtnHome);
        setupButtonHoverEffect(BtnSettings);

        // Инициализация ScrollBar и синхронизация с TextField
        ScrollBarValue1.setText(String.valueOf((int) IcnScrollBar1.getValue()));

        // Обновляем TextField при изменении ScrollBar
        IcnScrollBar1.valueProperty().addListener((observable, oldValue, newValue) -> {
            ScrollBarValue1.setText(String.valueOf(newValue.intValue()));
        });

        // Слушатель для TextField
        ScrollBarValue1.textProperty().addListener((observable, oldValue, newValue) -> {
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
    }

    private void setupButtonHoverEffect(Button button) {
        // Создаем анимацию увеличения
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), button);
        scaleUp.setToX(1.05); // Увеличение по оси X
        scaleUp.setToY(1.05); // Увеличение по оси Y

        // Создаем анимацию уменьшения
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), button);
        scaleDown.setToX(1.0); // Возврат к исходному размеру по оси X
        scaleDown.setToY(1.0); // Возврат к исходному размеру по оси Y

        // Устанавливаем обработчики событий
        button.setOnMouseEntered(e -> scaleUp.play()); // Анимация увеличения при наведении
        button.setOnMouseExited(e -> scaleDown.play()); // Анимация уменьшения при убирании мыши
    }

    @FXML
    void BtnBoostClicked(ActionEvent event) {
        mainApp.loadScene("Boost.fxml");
    }

    @FXML
    void BtnSetDefaultSize(ActionEvent event) {
        WriteToJson.WriteToJSON("defaultIconSize", RegistryUtils.getIconSize());
        RegistryUtils.setIconSize(48);
        RestartExplorer.restartExplorer();
        int defaultValue = 48; // Значение по умолчанию
        IcnScrollBar1.setValue(defaultValue); // Устанавливаем значение на ползунке
        ScrollBarValue1.setText(String.valueOf(defaultValue)); // Обновляем текст в текстовом поле
    }

    @FXML
    void BtnSetIconSize(ActionEvent event) {
        WriteToJson.WriteToJSON("defaultIconSize", RegistryUtils.getIconSize());
        int confirmedValue = (int) IcnScrollBar1.getValue();
        System.out.println("Confirmed value: " + confirmedValue);
        RegistryUtils.setIconSize(confirmedValue);
        RestartExplorer.restartExplorer();
        // Здесь можно добавить дополнительную логику
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
                ScrollBarValue1.setText(String.valueOf((int) IcnScrollBar1.getValue())); // Возвращаем корректное
                                                                                         // значение
            }
        } catch (NumberFormatException e) {
            ScrollBarValue1.setText(String.valueOf((int) IcnScrollBar1.getValue())); // Возвращаем последнее значение
        }

    }

    @FXML
    void BtnProfileClicked(ActionEvent event) {
        mainApp.loadScene("Profile.fxml");
    }

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("Wallpapers.fxml");
    }

    @FXML
    void BtnSettingsClicked(ActionEvent event) {
        mainApp.loadScene("Settings.fxml");
    }

    @FXML
    void BtnHomeClicked(ActionEvent event) {
        mainApp.loadScene("Home.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
}
