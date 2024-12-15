package com.customizer.ui.sample;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.util.Duration;


public class ProfileController {

    @FXML
    private Button BtnBoost;

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

        // Устанавливаем начальное значение опыта
        ExperienceBar.setProgress(currentProgress);


        // Получение имени пользователя из ОС
        String username = System.getProperty("user.name");
        // Установка имени пользователя как текста кнопки
        BtnUsername.setText(username);
        
        textField = new TextField();
        textField.setPromptText("Введите текст...");
        textField.setOnAction(event -> onTextEntered()); // Обработка нажатия Enter
        textField.setVisible(false);
        
        // Добавление TextField в родительский контейнер кнопки
        ((StackPane) BtnUsername.getParent()).getChildren().add(textField);

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
    void onIncreaseProgressClicked(ActionEvent event) {
        gainExperienceWithRanks(0.10); // Увеличиваем прогресс на 10% с анимацией
    }
     
    private int currentRank = 0; // Индекс текущего ранга
    private final String[] ranks = {"Beginner", "Apprentice", "Explorer", "Adept", "Master", "Expert", "Professional", "Virtuoso", "Grandmaster", "Legend"}; // Список рангов

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
        mainApp.loadScene("Home.fxml");
    }

    @FXML
    void BtnChangePicture(ActionEvent event) {
        selectPicture();
    }

    public void selectPicture() { //Функция открытия диалогового окна с выбором файла
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose your profile picture");//Имя окна
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg")); //Фильтр расширения файлов
    File selectedFile = fileChooser.showOpenDialog(null);//Открытие диалогового окна с выбором файла

    if (selectedFile != null) {
        String imagePath = selectedFile.getAbsolutePath(); //Получение абсолютного пути выбранной картинки
        String extension = FilenameUtils.getExtension(imagePath);
        String copiedImage = null;

        if (extension.equals("png")) {
            File source = new File(imagePath);
            File dest = new File("src/main/java/com/customizer/ui/resources/test.png");
            try {   //Копирование файла в корневую папку проекта resources
                FileUtils.copyFile(source, dest, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            copiedImage = "src/main/java/com/customizer/ui/resources/test.png";
            System.out.println(extension);
        } else if (extension.equals("jpg")) {
            File source = new File(imagePath);
            File dest = new File("src/main/java/com/customizer/ui/resources/test.jpg");
            try {   //Копирование файла в корневую папку проекта resources
                FileUtils.copyFile(source, dest, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            copiedImage = "src/main/java/com/customizer/ui/resources/test.jpg";
            System.out.println(extension);
        }

        File f = new File(copiedImage);
        String absolute = f.getAbsolutePath(); 
        String absolutePathForImageInsert = "file:/" + absolute.replace('\\', '/'); //Получение абсолютного пути для вставки картинки из корневой папки resources

        setDynamicImage(absolutePathForImageInsert);
    }
}

@FXML
private ImageView dynamicImageView, dynamicImageView1;

public void setDynamicImage(String imagePath) { //Функция установки картинки в ячейку
    // Проверяем наличие ссылки на ImageView
    if (dynamicImageView != null) {
        setAdjustedImage(dynamicImageView, imagePath);
    }
    if (dynamicImageView1 != null) {
        setAdjustedImage(dynamicImageView1, imagePath);
    }
}

private void setAdjustedImage(ImageView imageView, String imagePath) {
    Image image = new Image(imagePath);
    imageView.setImage(image);

    // Устанавливаем размеры ImageView
    imageView.setFitWidth(100); // Или задайте ширину динамически
    imageView.setFitHeight(100); // Или задайте высоту динамически
    imageView.setPreserveRatio(true);

    // Получаем фактические размеры изображения
    double imageWidth = image.getWidth();
    double imageHeight = image.getHeight();

    // Определяем радиус круга
    double radius = Math.min(imageView.getFitWidth(), imageView.getFitHeight()) / 2;

    // Создаем круг с центром, привязанным к центру изображения
    Circle clip = new Circle(imageView.getFitWidth() / 2, imageView.getFitHeight() / 2, radius);

    // Устанавливаем круговую обрезку
    imageView.setClip(clip);

    // Создаем изображение с прозрачным фоном для визуализации обрезки
    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setFill(Color.TRANSPARENT);
    Image roundedImage = imageView.snapshot(parameters, null);

    // Сбрасываем clip и устанавливаем окончательное изображение
    imageView.setClip(null);
    imageView.setImage(roundedImage);
}



    @FXML
    void BtnSettingsClicked(ActionEvent event) {
        mainApp.loadScene("Settings.fxml");
    }

    @FXML
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("Wallpapers.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }

}
