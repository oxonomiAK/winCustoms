package com.customizer.ui.sample;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.customizer.core.ProfileManager;
import com.customizer.core.utils.WallpaperUtils;
import org.apache.commons.io.FilenameUtils;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class DownloadedController {

    @FXML
    private Button BtnDownloaded;

    @FXML
    private Button BtnIcons;

    @FXML
    private Button BtnTaskBar;

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
    void BtnTaskBarClicked(ActionEvent event) {
        mainApp.loadScene("TaskBar.fxml");
    }

    @FXML
    void BtnUploadClicked(ActionEvent event) {
        selectWallpaper();
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
    String imagePath;

    



    public void selectWallpaper() { //Функция открытия диалогового окна с выбором файла
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your wallpaper");//Имя окна
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg") );//Фильтр расширения файлов
        File selectedFile = fileChooser.showOpenDialog(null);//Открытие диалогового окна с выбором файла
        imagePath = selectedFile.getAbsolutePath();//Получение абсолютного пути выбранной картинки
        
        String extension = FilenameUtils.getExtension(imagePath);
        String copiedImage = null;

        if(extension.equals("png")){
        File source = new File(imagePath);
        File dest = new File("src/main/java/com/customizer/ui/resources/test.png");
        try {   //Копирование файла в корневую папку проекта resources
            FileUtils.copyFile(source, dest, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        copiedImage = "src/main/java/com/customizer/ui/resources/test.png";
        System.out.println(extension);
    }

    else if(extension.equals("jpg")){
        File source = new File(imagePath);
        File dest = new File("src/main/java/com/customizer/ui/resources/test.jpg");
        try {   //Копирование файла в корневую папку проекта resources
            FileUtils.copyFile(source, dest, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(extension);
        copiedImage = "src/main/java/com/customizer/ui/resources/test.jpg";
    }


        
        File f = new File(copiedImage);
        String absolute = f.getAbsolutePath(); 
        String absolutePathForImageInsert = "file:/"+ absolute.replace('\\', '/'); //Получение абсолютного пути для вставки кратинки из корневой папки resources 

        setDynamicImage(absolutePathForImageInsert);
        handleButtonClick();
     }



     @FXML
     private ImageView dynamicImageView; 
     public void setDynamicImage(String imagePath) { //Функция установки картинки в ячейку
        // Проверяем наличие ссылки на ImageView
        if (dynamicImageView != null) {
            dynamicImageView.setImage(new Image(imagePath));
        }
    }


    @FXML
    private Button Button1; 
    public void handleButtonClick() {  //Замена текста в ячейке на название картинки
        File wallpaper = new File(imagePath);
        String wallpaperName = FilenameUtils.removeExtension(wallpaper.getName()); // Получаем имя файла без расширения
        Button1.setText(wallpaperName);     // Устанавливаем текст кнопки
    }
    }



