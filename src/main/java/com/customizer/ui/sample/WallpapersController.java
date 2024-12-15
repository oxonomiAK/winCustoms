package com.customizer.ui.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.customizer.core.User32F;
import com.customizer.core.dwTemp;
import com.customizer.core.utils.WallpaperUtils;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class WallpapersController {

    @FXML
    private Button BtnBoost;

    @FXML
    private Button BtnWallpapers;

    @FXML
    private Button BtnProfile;

    @FXML
    private Button BtnSettings;

    @FXML
    private Button BtnHome;

    @FXML
    private Button closeButton;

    @FXML
    private Button BtnWallpapers1;
    @FXML
    private Button BtnWallpapers2; // Кнопка, которая будет увеличиваться
    @FXML
    private Button BtnWallpapers3; // Кнопка, которая будет увеличиваться
    @FXML
    private Button BtnWallpapers4; // Кнопка, которая будет увеличиваться
<<<<<<< HEAD
=======

    private final Map<Button, String> wallpaperPaths = new HashMap<>();

>>>>>>> origin/Vladmini
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

        wallpaperPaths.put(BtnWallpapers1, "src/main/java/com/customizer/ui/resources/Horses.jpg");
        wallpaperPaths.put(BtnWallpapers2, "src/main/java/com/customizer/ui/resources/gori.png");
        wallpaperPaths.put(BtnWallpapers3, "src/main/java/com/customizer/ui/resources/pole.png");
        wallpaperPaths.put(BtnWallpapers4, "src/main/java/com/customizer/ui/resources/japan.png");

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
void BtnWallpapers1Clicked(ActionEvent event) {
    // Получаем текущую сцену
    javafx.scene.Scene scene = BtnWallpapers1.getScene();

    // Вычисляем координаты центра экрана
    double centerX = scene.getWidth() / 2 - BtnWallpapers1.getWidth() / 2;
    double centerY = scene.getHeight() / 2 - BtnWallpapers1.getHeight() / 2;

    // Перемещаем кнопку в центр экрана
    BtnWallpapers1.setLayoutX(centerX);
    BtnWallpapers1.setLayoutY(centerY);
<<<<<<< HEAD
    BtnWallpapers2.setVisible(false);
    BtnWallpapers3.setVisible(false);
    BtnWallpapers4.setVisible(false);
=======

    // Прячем остальные кнопки
    BtnWallpapers2.setVisible(false);
    BtnWallpapers3.setVisible(false);
    BtnWallpapers4.setVisible(false);

    // Получаем путь к изображению из UserData текущей кнопки
    Button clickedButton = (Button) event.getSource();  // Получаем кнопку, на которую кликнули
    String imagePath = (String) clickedButton.getUserData();  // Получаем путь к изображению из UserData

  //я понял что не так, кнопка берет информацию о обоях из userdata, но картинка лошадей привязана к кнопке по ее fxid поскольку функция
  // написана не правильно и обращается к одной картинке. Это надо исправить.  

    Image image = new Image("file:" + imagePath); // Создаем изображение из пути
    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    BtnWallpapers1.setBackground(new Background(backgroundImage)); // Применяем фон на кнопку

>>>>>>> origin/Vladmini
    // Анимация увеличения кнопки
    ScaleTransition scaleUp = new ScaleTransition(Duration.millis(300), BtnWallpapers1);
    scaleUp.setToX(2.0); // Увеличение по оси X
    scaleUp.setToY(2.0); // Увеличение по оси Y

    scaleUp.setOnFinished(e -> {
        // Создаем новую кнопку "Set Wallpaper"
        Button setWallpaperButton = new Button("Set Wallpaper");

        // Задаем размеры и координаты новой кнопки
        setWallpaperButton.setPrefWidth(150);
        setWallpaperButton.setPrefHeight(50);
        setWallpaperButton.setLayoutX(centerX + 150); // Смещаем по оси X
        setWallpaperButton.setLayoutY(centerY + 275); // По той же оси Y, что и увеличенная кнопка

        // Добавляем обработчик события для новой кнопки
        setWallpaperButton.setOnAction(ev -> {
            // Устанавливаем обои с использованием правильного пути
            File f = new File(imagePath); // Используем путь, переданный через UserData
            String absolutePath = f.getAbsolutePath(); // Получаем абсолютный путь
            System.out.println(absolutePath); // Выводим путь в консоль для отладки

            // Устанавливаем обои
            WallpaperUtils.setWallpaper(absolutePath); // Здесь вызываем логику установки обоев
        });

        // Добавляем кнопку в родительский контейнер
        ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(setWallpaperButton);

        // Создаем новую кнопку "Go Back"
        Button setGoBackButton = new Button("Go Back");

        // Задаем размеры и координаты новой кнопки
        setGoBackButton.setPrefWidth(150);
        setGoBackButton.setPrefHeight(50);
        setGoBackButton.setLayoutX(centerX - 50); // Смещаем по оси X
        setGoBackButton.setLayoutY(centerY + 275); // По той же оси Y, что и увеличенная кнопка

        // Добавляем обработчик события для кнопки "Go Back"
        setGoBackButton.setOnAction(ev -> {
            // Анимация уменьшения кнопки обратно в исходное состояние
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(300), BtnWallpapers1);
            scaleDown.setToX(1.0); // Возврат к исходному размеру по оси X
            scaleDown.setToY(1.0); // Возврат к исходному размеру по оси Y
<<<<<<< HEAD
            scaleDown.setOnFinished(animationEvent -> {
                BtnWallpapers2.setVisible(true);
                BtnWallpapers3.setVisible(true);
                BtnWallpapers4.setVisible(true);
                // Возвращаем кнопку на изначальное место
                BtnWallpapers1.setLayoutX(235); // Замените на изначальные координаты X
                BtnWallpapers1.setLayoutY(93); // Замените на изначальные координаты Y
=======
>>>>>>> origin/Vladmini

            scaleDown.setOnFinished(animationEvent -> {
                // Показываем скрытые кнопки снова
                BtnWallpapers2.setVisible(true);
                BtnWallpapers3.setVisible(true);
                BtnWallpapers4.setVisible(true);

                // Возвращаем кнопку в изначальные координаты
                BtnWallpapers1.setLayoutX(235); // Изначальные координаты X
                BtnWallpapers1.setLayoutY(93); // Изначальные координаты Y

                // Удаляем кнопки "Set Wallpaper" и "Go Back"
                ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().removeIf(node -> 
                    node instanceof Button && ("Set Wallpaper".equals(((Button) node).getText()) || "Go Back".equals(((Button) node).getText())));
            });

            // Запуск анимации уменьшения
            scaleDown.play();
        });

        // Добавляем кнопку "Go Back" в родительский контейнер
        ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(setGoBackButton);
    });

    // Запуск анимации увеличения
    scaleUp.play();
}
<<<<<<< HEAD

@FXML
void BtnWallpapers2Clicked(ActionEvent event) {
    // Получаем текущую сцену
    javafx.scene.Scene scene = BtnWallpapers2.getScene();

    // Вычисляем координаты центра экрана
    double centerX = scene.getWidth() / 2 - BtnWallpapers2.getWidth() / 2;
    double centerY = scene.getHeight() / 2 - BtnWallpapers2.getHeight() / 2;

    // Перемещаем кнопку в центр экрана
    BtnWallpapers2.setLayoutX(centerX);
    BtnWallpapers2.setLayoutY(centerY);
    BtnWallpapers1.setVisible(false);
    BtnWallpapers3.setVisible(false);
    BtnWallpapers4.setVisible(false);
    // Анимация увеличения кнопки
    ScaleTransition scaleUp = new ScaleTransition(Duration.millis(300), BtnWallpapers2);
    scaleUp.setToX(2.0); // Увеличение по оси X
    scaleUp.setToY(2.0); // Увеличение по оси Y
    scaleUp.setOnFinished(e -> {
            
        // Создаем новую кнопку "Set Wallpaper"
        Button setWallpaperButton = new Button("Set Wallpaper");

        // Задаем размеры и координаты новой кнопки
        setWallpaperButton.setPrefWidth(150);
        setWallpaperButton.setPrefHeight(50);
        setWallpaperButton.setLayoutX(centerX + 150); // Смещаем по оси X
        setWallpaperButton.setLayoutY(centerY + 275); // По той же оси Y, что и увеличенная кнопка

        // Добавляем обработчик события для новой кнопки
        setWallpaperButton.setOnAction(ev -> {
          String copiedImage = "src/main/java/com/customizer/ui/resources/gori.png";
        File f = new File(copiedImage);
        String absolute = f.getAbsolutePath(); 
        System.out.println(absolute);

           WallpaperUtils.setWallpaper(absolute); // Здесь можно добавить логику установки обоев
            
        });

        // Добавляем кнопку в родительский контейнер
        ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(setWallpaperButton);

        // Создаем новую кнопку "Cancel"
        Button setGoBackButton = new Button("Go Back");

        // Задаем размеры и координаты новой кнопки
        setGoBackButton.setPrefWidth(150);
        setGoBackButton.setPrefHeight(50);
        setGoBackButton.setLayoutX(centerX - 50); // Смещаем по оси X
        setGoBackButton.setLayoutY(centerY + 275); // По той же оси Y, что и увеличенная кнопка

        // Добавляем обработчик события для новой кнопки
        setGoBackButton.setOnAction(ev -> {

            // Анимация уменьшения кнопки обратно в исходное состояние
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(300), BtnWallpapers2);
            scaleDown.setToX(1.0); // Возврат к исходному размеру по оси X
            scaleDown.setToY(1.0); // Возврат к исходному размеру по оси Y
            scaleDown.setOnFinished(animationEvent -> {
                BtnWallpapers1.setVisible(true);
                BtnWallpapers3.setVisible(true);
                BtnWallpapers4.setVisible(true);
                // Возвращаем кнопку на изначальное место
                BtnWallpapers2.setLayoutX(510); // Замените на изначальные координаты X
                BtnWallpapers2.setLayoutY(93); // Замените на изначальные координаты Y

                // Удаляем кнопки "Set Wallpaper" и "Go Back" из контейнера
                ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().removeIf(node -> 
                    node instanceof Button && ("Set Wallpaper".equals(((Button) node).getText()) || "Go Back".equals(((Button) node).getText())));
            });

            // Запуск анимации уменьшения
            scaleDown.play();
        });

        // Добавляем кнопку в родительский контейнер
        ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(setGoBackButton);
    });

    // Запуск анимации
    scaleUp.play();
}


@FXML
void BtnWallpapers3Clicked(ActionEvent event) {
    // Получаем текущую сцену
    javafx.scene.Scene scene = BtnWallpapers3.getScene();

    // Вычисляем координаты центра экрана
    double centerX = scene.getWidth() / 2 - BtnWallpapers3.getWidth() / 2;
    double centerY = scene.getHeight() / 2 - BtnWallpapers3.getHeight() / 2;

    // Перемещаем кнопку в центр экрана
    BtnWallpapers3.setLayoutX(centerX);
    BtnWallpapers3.setLayoutY(centerY);
    BtnWallpapers1.setVisible(false);
    BtnWallpapers2.setVisible(false);
    BtnWallpapers4.setVisible(false);
    // Анимация увеличения кнопки
    ScaleTransition scaleUp = new ScaleTransition(Duration.millis(300), BtnWallpapers3);
    scaleUp.setToX(2.0); // Увеличение по оси X
    scaleUp.setToY(2.0); // Увеличение по оси Y
    scaleUp.setOnFinished(e -> {
            
        // Создаем новую кнопку "Set Wallpaper"
        Button setWallpaperButton = new Button("Set Wallpaper");

        // Задаем размеры и координаты новой кнопки
        setWallpaperButton.setPrefWidth(150);
        setWallpaperButton.setPrefHeight(50);
        setWallpaperButton.setLayoutX(centerX + 150); // Смещаем по оси X
        setWallpaperButton.setLayoutY(centerY + 275); // По той же оси Y, что и увеличенная кнопка

        // Добавляем обработчик события для новой кнопки
        setWallpaperButton.setOnAction(ev -> {
          String copiedImage = "src/main/java/com/customizer/ui/resources/pole.png";
        File f = new File(copiedImage);
        String absolute = f.getAbsolutePath(); 
        System.out.println(absolute);

           WallpaperUtils.setWallpaper(absolute); // Здесь можно добавить логику установки обоев
            
        });

        // Добавляем кнопку в родительский контейнер
        ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(setWallpaperButton);

        // Создаем новую кнопку "Cancel"
        Button setGoBackButton = new Button("Go Back");

        // Задаем размеры и координаты новой кнопки
        setGoBackButton.setPrefWidth(150);
        setGoBackButton.setPrefHeight(50);
        setGoBackButton.setLayoutX(centerX - 50); // Смещаем по оси X
        setGoBackButton.setLayoutY(centerY + 275); // По той же оси Y, что и увеличенная кнопка

        // Добавляем обработчик события для новой кнопки
        setGoBackButton.setOnAction(ev -> {

            // Анимация уменьшения кнопки обратно в исходное состояние
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(300), BtnWallpapers3);
            scaleDown.setToX(1.0); // Возврат к исходному размеру по оси X
            scaleDown.setToY(1.0); // Возврат к исходному размеру по оси Y
            scaleDown.setOnFinished(animationEvent -> {
                BtnWallpapers1.setVisible(true);
                BtnWallpapers2.setVisible(true);
                BtnWallpapers4.setVisible(true);
                // Возвращаем кнопку на изначальное место
                BtnWallpapers3.setLayoutX(780); // Замените на изначальные координаты X
                BtnWallpapers3.setLayoutY(93); // Замените на изначальные координаты Y

                // Удаляем кнопки "Set Wallpaper" и "Go Back" из контейнера
                ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().removeIf(node -> 
                    node instanceof Button && ("Set Wallpaper".equals(((Button) node).getText()) || "Go Back".equals(((Button) node).getText())));
            });

            // Запуск анимации уменьшения
            scaleDown.play();
        });

        // Добавляем кнопку в родительский контейнер
        ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(setGoBackButton);
    });

    // Запуск анимации
    scaleUp.play();
}


@FXML
void BtnWallpapers4Clicked(ActionEvent event) {
    // Получаем текущую сцену
    javafx.scene.Scene scene = BtnWallpapers4.getScene();

    // Вычисляем координаты центра экрана
    double centerX = scene.getWidth() / 2 - BtnWallpapers4.getWidth() / 2;
    double centerY = scene.getHeight() / 2 - BtnWallpapers4.getHeight() / 2;

    // Перемещаем кнопку в центр экрана
    BtnWallpapers4.setLayoutX(centerX);
    BtnWallpapers4.setLayoutY(centerY);
    BtnWallpapers1.setVisible(false);
    BtnWallpapers2.setVisible(false);
    BtnWallpapers3.setVisible(false);
    // Анимация увеличения кнопки
    ScaleTransition scaleUp = new ScaleTransition(Duration.millis(300), BtnWallpapers4);
    scaleUp.setToX(2.0); // Увеличение по оси X
    scaleUp.setToY(2.0); // Увеличение по оси Y
    scaleUp.setOnFinished(e -> {
            
        // Создаем новую кнопку "Set Wallpaper"
        Button setWallpaperButton = new Button("Set Wallpaper");

        // Задаем размеры и координаты новой кнопки
        setWallpaperButton.setPrefWidth(150);
        setWallpaperButton.setPrefHeight(50);
        setWallpaperButton.setLayoutX(centerX + 150); // Смещаем по оси X
        setWallpaperButton.setLayoutY(centerY + 275); // По той же оси Y, что и увеличенная кнопка

        // Добавляем обработчик события для новой кнопки
        setWallpaperButton.setOnAction(ev -> {
          String copiedImage = "src/main/java/com/customizer/ui/resources/japan.png";
        File f = new File(copiedImage);
        String absolute = f.getAbsolutePath(); 
        System.out.println(absolute);

           WallpaperUtils.setWallpaper(absolute); // Здесь можно добавить логику установки обоев
            
        });

        // Добавляем кнопку в родительский контейнер
        ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(setWallpaperButton);

        // Создаем новую кнопку "Cancel"
        Button setGoBackButton = new Button("Go Back");

        // Задаем размеры и координаты новой кнопки
        setGoBackButton.setPrefWidth(150);
        setGoBackButton.setPrefHeight(50);
        setGoBackButton.setLayoutX(centerX - 50); // Смещаем по оси X
        setGoBackButton.setLayoutY(centerY + 275); // По той же оси Y, что и увеличенная кнопка

        // Добавляем обработчик события для новой кнопки
        setGoBackButton.setOnAction(ev -> {

            // Анимация уменьшения кнопки обратно в исходное состояние
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(300), BtnWallpapers4);
            scaleDown.setToX(1.0); // Возврат к исходному размеру по оси X
            scaleDown.setToY(1.0); // Возврат к исходному размеру по оси Y
            scaleDown.setOnFinished(animationEvent -> {
                BtnWallpapers1.setVisible(true);
                BtnWallpapers2.setVisible(true);
                BtnWallpapers3.setVisible(true);
                // Возвращаем кнопку на изначальное место
                BtnWallpapers4.setLayoutX(235); // Замените на изначальные координаты X
                BtnWallpapers4.setLayoutY(313); // Замените на изначальные координаты Y

                // Удаляем кнопки "Set Wallpaper" и "Go Back" из контейнера
                ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().removeIf(node -> 
                    node instanceof Button && ("Set Wallpaper".equals(((Button) node).getText()) || "Go Back".equals(((Button) node).getText())));
            });

            // Запуск анимации уменьшения
            scaleDown.play();
        });

        // Добавляем кнопку в родительский контейнер
        ((javafx.scene.layout.Pane) scene.getRoot()).getChildren().add(setGoBackButton);
    });

    // Запуск анимации
    scaleUp.play();
}


// Метод для смены обоев
public static void setWallpaper(String imagePath) {
    // Передаем параметры в SystemParametersInfo для смены обоев
    User32F.User32.INSTANCE.SystemParametersInfoW(
            User32F.User32.SPI_SETDESKWALLPAPER,
            0,
            imagePath,
            User32F.User32.SPIF_UPDATEINIFILE | User32F.User32.SPIF_SENDCHANGE);
}
=======
    
>>>>>>> origin/Vladmini

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("Wallpapers.fxml");
    }

    @FXML
    void BtnProfileClicked(ActionEvent event) {
        mainApp.loadScene("Profile.fxml");
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


