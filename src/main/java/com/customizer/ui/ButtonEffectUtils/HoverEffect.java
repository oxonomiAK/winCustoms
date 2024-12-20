package com.customizer.ui.ButtonEffectUtils;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

<<<<<<< HEAD:src/main/java/com/customizer/ui/sample/HomeController.java
public class HomeController {

    @FXML
    private Button BtnWallpapers;

    @FXML
    private Button closeButton;

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        // Добавляем эффект увеличения при наведении для всех кнопок, кроме closeButton
        setupButtonHoverEffect(BtnWallpapers);
    }

    private void setupButtonHoverEffect(Button button) {
=======
public class HoverEffect {
    public static void setupButtonHoverEffect(Button button) {
>>>>>>> sergo:src/main/java/com/customizer/ui/ButtonEffectUtils/HoverEffect.java
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
<<<<<<< HEAD:src/main/java/com/customizer/ui/sample/HomeController.java

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("Wallpapers.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
=======
>>>>>>> sergo:src/main/java/com/customizer/ui/ButtonEffectUtils/HoverEffect.java
}
