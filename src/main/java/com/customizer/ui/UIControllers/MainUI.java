package com.customizer.ui.UIControllers;

import java.io.IOException;

import com.customizer.ui.ButtonEffectUtils.CoinsController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class MainUI extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    private Stage primaryStage; // Используем один основной Stage
    @FXML
    private Label coinsLabel;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        loadScene("/com/customizer/ui/fxml/Home.fxml");
    }

    // Универсальный метод для загрузки сцен
    public void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Object controller = loader.getController();
    
            if (controller instanceof HomeController) {
                ((HomeController) controller).setMainApp(this);
            } else if (controller instanceof WallpapersController) {
                ((WallpapersController) controller).setMainApp(this);
                ((WallpapersController) controller).updateCoinsDisplay(); // Обновляем монеты
            } else if (controller instanceof WallpapersController2) {
                ((WallpapersController2) controller).setMainApp(this);
                ((WallpapersController2) controller).updateCoinsDisplay();
            } else if (controller instanceof WallpapersController3) {
                ((WallpapersController3) controller).setMainApp(this);
                ((WallpapersController3) controller).updateCoinsDisplay();
            } else if (controller instanceof WallpapersController4) {
                ((WallpapersController4) controller).setMainApp(this);
                ((WallpapersController4) controller).updateCoinsDisplay();
            } else if (controller instanceof IconsController) {
                ((IconsController) controller).setMainApp(this);
                ((IconsController) controller).updateCoinsDisplay();
            } else if (controller instanceof BoostController) {
                ((BoostController) controller).setMainApp(this);
                ((BoostController) controller).updateCoinsDisplay();
            } else if (controller instanceof SettingsController) {
                ((SettingsController) controller).setMainApp(this);
                ((SettingsController) controller).updateCoinsDisplay();
            } else if (controller instanceof ProfileController) {
                ((ProfileController) controller).setMainApp(this);
                ((ProfileController) controller).updateCoinsDisplay();
            } else if (controller instanceof VolumeController) {
                ((VolumeController) controller).setMainApp(this);
            } else if (controller instanceof RocketController) {
                ((RocketController) controller).setMainApp(this);
                ((RocketController) controller).updateCoinsDisplay();
            } else if (controller instanceof CompComponentsController) {
                ((CompComponentsController) controller).setMainApp(this);
                ((CompComponentsController) controller).updateCoinsDisplay();
            } else if (controller instanceof ImageCropperController) {
                ((ImageCropperController) controller).setMainApp(this);
    
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                return;
            }
    
            enableWindowDragging(root);
    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
    
            // Обновляем отображение монет
            updateCoinsLabel();
    
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

    CoinsController controller = new CoinsController();
    private int coins = controller.getCoins(); // Счетчик монет

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
        System.out.println("Монет добавлено: " + amount + ". Всего монет: " + coins);
        updateCoinsLabel(); // Обновляем Label
    }
    
    public void spendCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
            System.out.println("Монет потрачено: " + amount + ". Осталось монет: " + coins);
            updateCoinsLabel(); // Обновляем Label
        } else {
            System.out.println("Недостаточно монет!");
        }
    }

    public void updateCoinsLabel() {
        if (coinsLabel != null) {
            coinsLabel.setText("Монеты: " + coins);
        }
    }

    public void changeWindowSize(double width, double height) {
        // Устанавливаем максимальные размеры окна
        primaryStage.setMaxWidth(width);
        primaryStage.setMaxHeight(height);
        
        // Устанавливаем минимальные размеры окна (чтобы окно не могло быть меньше заданного размера)
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
        
        // Переустанавливаем текущие размеры окна
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
    }
    
    

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
    }
    

    public static void main(String[] args) {
        launch(args);
           
        CoinsController controller = new CoinsController();

        // Сохраняем монеты
        controller.saveCoins();

        // Добавляем монеты
        controller.addCoins(controller.getCoins());

        // Получаем текущее количество монет
        System.out.println("Всего монет: " + controller.getCoins());
    
    }
}


