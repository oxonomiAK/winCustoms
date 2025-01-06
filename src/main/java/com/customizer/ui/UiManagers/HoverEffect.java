package com.customizer.ui.UiManagers;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class HoverEffect {
    public static void setupButtonHoverEffect(Button button) {
        // Create zoom animation on buttons
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), button); //Button zoom speed in ms
        scaleUp.setToX(1.05); // Zoom in on the X-axis
        scaleUp.setToY(1.05); // Zoom in on the Y-axis

        // Создаем анимацию уменьшения
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), button); //Button zoom speed in ms
        scaleDown.setToX(1.0); // Return to the default size in the X axis
        scaleDown.setToY(1.0); // Return to the default size in the Y axis

        // Set event handlers when the mouse hovers
        button.setOnMouseEntered(e -> scaleUp.play()); // Animation of zooming on hover
        button.setOnMouseExited(e -> scaleDown.play()); // Decrease animation when mouse is removed
    }
}
