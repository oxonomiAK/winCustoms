package com.customizer.ui.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class WallpapersController {

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
    void BtnWallpapersClicked(ActionEvent event) {
        mainApp.loadScene("Wallpapers.fxml");
    }

    @FXML
    void BtnWidgetsClicked(ActionEvent event) {
        mainApp.loadScene("Widgets.fxml");
    }

      @FXML
    void getWallpaperInfo(ActionEvent event) {
try {
            // Команда для получения текущего обоя из реестра
            String command = "reg query \"HKCU\\Control Panel\\Desktop\" /v Wallpaper";
            
            // Запуск команды
            Process process = Runtime.getRuntime().exec(command);
            
            // Чтение результата команды
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            reader.close();

            // Парсим результат, чтобы получить путь к файлу
            String result = output.toString();
            String wallpaperPath = parseWallpaperPath(result);

            // Показываем путь в Alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wallpaper Info");
            alert.setHeaderText("Current Wallpaper Path");
            alert.setContentText(wallpaperPath);
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            // Показываем ошибку в Alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not fetch wallpaper info");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    // Метод для парсинга пути из результата команды
    private String parseWallpaperPath(String regOutput) {
        String[] lines = regOutput.split("\n");
        for (String line : lines) {
            if (line.contains("Wallpaper")) {
                String[] parts = line.split("\\s{2,}"); // Разделение по пробелам
                return parts[parts.length - 1]; // Путь к обоям
            }
        }
        return "No wallpaper path found";
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
}


