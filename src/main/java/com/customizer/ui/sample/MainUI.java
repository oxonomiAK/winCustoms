package com.customizer.ui.sample;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class MainUI extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    private Stage primaryStage; // Используем один основной Stage

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Сохраняем основной Stage
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        loadScene("Home.fxml"); // Загружаем стартовую сцену
    }

    // Универсальный метод для загрузки сцен
    public void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Object controller = loader.getController();
           
            if (controller instanceof HomeController) {
                ((HomeController) controller).setMainApp(this);
            }  else if (controller instanceof WallpapersController) {
                ((WallpapersController) controller).setMainApp(this);
            } else if (controller instanceof IconsController) {
                ((IconsController) controller).setMainApp(this);
            }else if (controller instanceof BoostController) {
                ((BoostController) controller).setMainApp(this);
            }


            // Включаем обработку перемещения окна
            enableWindowDragging(root);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Универсальный метод для обработки перемещения окна
    private void enableWindowDragging(Parent root) {
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


