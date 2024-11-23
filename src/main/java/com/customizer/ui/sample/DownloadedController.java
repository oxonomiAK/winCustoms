package com.customizer.ui.sample;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.customizer.core.ProfileManager;
import com.customizer.core.utils.WallpaperUtils;
import org.apache.commons.io.FilenameUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class DownloadedController {

    @FXML
    private Button closeButton;

    private MainUI mainApp;

    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
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
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png") );//Фильтр расширения файлов
        File selectedFile = fileChooser.showOpenDialog(null);//Открытие диалогового окна с выбором файла
        imagePath = selectedFile.getAbsolutePath();//Получение абсолютного пути выбранной картинки

        File source = new File(imagePath);
        File dest = new File("src/main/java/com/customizer/ui/resources/test.png");
        try {   //Копирование файла в корневую папку проекта resources
            FileUtils.copyFile(source, dest, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String copiedImage = "src/main/java/com/customizer/ui/resources/test.png";
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



