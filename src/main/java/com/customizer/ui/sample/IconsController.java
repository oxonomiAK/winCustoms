package com.customizer.ui.sample;


import com.customizer.core.utils.RegistryUtils;

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
    private Button BtnDownloaded;

    @FXML
    private Button BtnIcons;

    @FXML
    private Button BtnTaskBar;

    @FXML
    private ScrollBar IcnScrollBar;

    @FXML
    private TextField ScrollBarValue;

    @FXML
    private Button BtnApply;

    @FXML
    private Button BtnDefaultSize;

    @FXML
    private Button BtnWallpapers;

    @FXML
    private Button BtnWidgets;

    @FXML
    private Button closeButton;

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        setupButtonHoverEffect(BtnDownloaded);
        setupButtonHoverEffect(BtnIcons);
        setupButtonHoverEffect(BtnTaskBar);
        setupButtonHoverEffect(BtnWallpapers);
        setupButtonHoverEffect(BtnWidgets);

<<<<<<< HEAD
      // Инициализация ScrollBar и синхронизация с TextField
=======
     // Инициализация ScrollBar и синхронизация с TextField
>>>>>>> sergo
    ScrollBarValue.setText(String.valueOf((int) IcnScrollBar.getValue()));

    // Обновляем TextField при изменении ScrollBar
    IcnScrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
        ScrollBarValue.setText(String.valueOf(newValue.intValue()));
    });

    // Слушатель для TextField
    ScrollBarValue.textProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.isEmpty()) {
            return; // Если поле пустое, ничего не делаем
        }

        try {
            int value = Integer.parseInt(newValue); // Пробуем преобразовать в число

            if (value < IcnScrollBar.getMin()) {
                value = (int) IcnScrollBar.getMin(); // Ограничиваем значение минимумом
            } else if (value > IcnScrollBar.getMax()) {
                value = (int) IcnScrollBar.getMax(); // Ограничиваем значение максимумом
            }

            IcnScrollBar.setValue(value); // Устанавливаем значение ScrollBar
            ScrollBarValue.setText(String.valueOf(value)); // Обновляем TextField
        } catch (NumberFormatException e) {
            // Если некорректное значение, возвращаем старое
            ScrollBarValue.setText(oldValue);
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
    void BtnDownloadedClicked(ActionEvent event) {
        mainApp.loadScene("Downloaded.fxml");
    }

    @FXML
    void BtnIconsClicked(ActionEvent event) {
        mainApp.loadScene("Icons.fxml");
    }

    @FXML
    void BtnSetDefaultSize(ActionEvent event) {
        RegistryUtils.setIconSize(48);
        RegistryUtils.restartExplorer();
    }

    @FXML
    void BtnSetIconSize(ActionEvent event) {
        int confirmedValue = (int) IcnScrollBar.getValue();
        System.out.println("Confirmed value: " + confirmedValue);
        RegistryUtils.setIconSize(confirmedValue);
        RegistryUtils.restartExplorer();
        // Здесь можно добавить дополнительную логику
    }
    

    @FXML
    void onScrollBarValueChanged(ActionEvent event) {
        try {
            String input = ScrollBarValue.getText();
            if (input.isEmpty()) {
                return; // Если поле пустое, не делаем ничего
            }
            int value = Integer.parseInt(input);
            if (value >= IcnScrollBar.getMin() && value <= IcnScrollBar.getMax()) {
                IcnScrollBar.setValue(value); // Обновляем ScrollBar
            } else {
                ScrollBarValue.setText(String.valueOf((int) IcnScrollBar.getValue())); // Возвращаем корректное значение
            }
        } catch (NumberFormatException e) {
            ScrollBarValue.setText(String.valueOf((int) IcnScrollBar.getValue())); // Возвращаем последнее значение
        }

    }


    @FXML
    void BtnTaskBarClicked(ActionEvent event) {
        mainApp.loadScene("TaskBar.fxml");
    }

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("Wallpapers.fxml");
    }

    @FXML
    void BtnWidgetsClicked(ActionEvent event) {
        mainApp.loadScene("Widgets.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
}


