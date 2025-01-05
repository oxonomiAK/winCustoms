package com.customizer.ui.ButtonEffectUtils;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class NotificationManager {
    public static void showNotification(String message, Button BtnWallpapers1) {
        Label notification = new Label(message); //Creating a label with notification
        
        notification.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-text-fill: white; "
                + "-fx-padding: 10px; -fx-font-size: 14px; -fx-border-radius: 10; -fx-background-radius: 10;"); //Set the style
        notification.setAlignment(Pos.CENTER); //Set position
        notification.setPrefWidth(450); //Size of notification
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), notification); //Smooth appearance of the notification
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
        PauseTransition pause = new PauseTransition(Duration.millis(1500)); //How long the notification will be on the screen in ms
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), notification); //Smooth disappearance of the notification
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
        SequentialTransition sequentialTransition = new SequentialTransition(fadeIn, pause, fadeOut); //Sequential playback of multiple notifications.
            sequentialTransition.play(); //Start animation
        
        
        Scene scene = BtnWallpapers1.getScene(); // Get the current scene
        Pane rootPane = (Pane) scene.getRoot(); // Root node 
        notification.setLayoutX((scene.getWidth() - notification.getPrefWidth()) / 2);
        notification.setLayoutY(scene.getHeight() - 60); // Place at the bottom of the screen
        rootPane.getChildren().add(notification);
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ev -> {  // Remove the notification after 3 seconds
            rootPane.getChildren().remove(notification);
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
