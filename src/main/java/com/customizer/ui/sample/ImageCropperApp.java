package com.customizer.ui.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageCropperApp extends Application {
    private double startX, startY, endX, endY;

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Upload and Crop Image");
        ImageView buttonImageView = new ImageView();

        btn.setOnAction(e -> openCropWindow(primaryStage, buttonImageView));

        StackPane buttonPane = new StackPane(buttonImageView, btn);
        buttonPane.setPrefSize(200, 200);

        Scene scene = new Scene(buttonPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Image Cropper");
        primaryStage.show();
    }

    private void openCropWindow(Stage primaryStage, ImageView targetImageView) {
        // Open file chooser to select an image
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile == null) return;

        Image image;
        try {
            image = new Image(new FileInputStream(selectedFile));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        Stage cropStage = new Stage();
        cropStage.initModality(Modality.APPLICATION_MODAL);
        cropStage.initOwner(primaryStage);
        cropStage.setTitle("Crop Image");

        Canvas canvas = new Canvas(image.getWidth(), image.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw the original image
        gc.drawImage(image, 0, 0);

        // Rectangle to show cropping area
        Rectangle selectionRect = new Rectangle(0, 0, 0, 0);
        selectionRect.setStroke(Color.BLUE);
        selectionRect.setFill(Color.TRANSPARENT);
        selectionRect.setStrokeWidth(2);

        VBox layout = new VBox(canvas, selectionRect);
        Scene cropScene = new Scene(layout);

        // Add event listeners for cropping
        canvas.setOnMousePressed(event -> {
            startX = event.getX();
            startY = event.getY();
            selectionRect.setX(startX);
            selectionRect.setY(startY);
            selectionRect.setWidth(0);
            selectionRect.setHeight(0);
        });

        canvas.setOnMouseDragged(event -> {
            endX = event.getX();
            endY = event.getY();
            selectionRect.setX(Math.min(startX, endX));
            selectionRect.setY(Math.min(startY, endY));
            selectionRect.setWidth(Math.abs(endX - startX));
            selectionRect.setHeight(Math.abs(endY - startY));
        });

        canvas.setOnMouseReleased(event -> {
            // Crop the selected area
            int cropX = (int) selectionRect.getX();
            int cropY = (int) selectionRect.getY();
            int cropWidth = (int) selectionRect.getWidth();
            int cropHeight = (int) selectionRect.getHeight();

            if (cropWidth > 0 && cropHeight > 0) {
                WritableImage croppedImage = new WritableImage(
                        image.getPixelReader(), cropX, cropY, cropWidth, cropHeight
                );

                // Set cropped image on the button
                targetImageView.setImage(croppedImage);
                targetImageView.setFitWidth(200);
                targetImageView.setFitHeight(200);
                targetImageView.setPreserveRatio(true);

                cropStage.close();
            }
        });

        cropStage.setScene(cropScene);
        cropStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

