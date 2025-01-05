package com.customizer.ui.UIControllers;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.scene.effect.*;
import javax.imageio.ImageIO;
import com.customizer.services.WriteToJson;
import java.io.File;


public class ImageCropperController  {

    @FXML
    private Canvas canvas;

    @FXML
    private Button closeButton;
    
    @FXML
    private Button chooseImageButton;

    @FXML
    private Button saveImageButton;

    private double imageX = 0, imageY = 0; // Image coordinates
    private double dragStartX, dragStartY; // Start moving
    private double scale = 1.0; // Image Scale
    private Image image;
    static File outputFile = new File("NewLookResources/user.png");
    private final double CIRCLE_RADIUS = 150; // Cutting circle radius
    public static String UserProfilePic = outputFile.toURI().toString();;
    private MainUI mainApp;
    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Handlers for moving the image
        canvas.setOnMousePressed(event -> {
            dragStartX = event.getX();
            dragStartY = event.getY();
        });

        canvas.setOnMouseDragged(event -> {
            double deltaX = event.getX() - dragStartX;
            double deltaY = event.getY() - dragStartY;
            imageX += deltaX;
            imageY += deltaY;
            dragStartX = event.getX();
            dragStartY = event.getY();
            draw(gc);
        });

        // Handler for scrolling the mouse wheel to change the scale
        canvas.setOnScroll(event -> {
            if (event.getDeltaY() > 0) {
                scale *= 1.1; // Zoom in
            } else {
                scale /= 1.1; // Zoom out
            }
            draw(gc);
        });

        // Select an image
        String profilePicPath = ProfileController.picImage;
        image = new Image(profilePicPath);
        imageX = (canvas.getWidth() - image.getWidth()) / 2;
        imageY = (canvas.getHeight() - image.getHeight()) / 2;
        draw(gc);

        // Saving the image
        saveImageButton.setOnAction(event -> saveCroppedImage());
        saveImageButton.setStyle("-fx-background-color: #5853583b; -fx-text-fill: white; "
        + "-fx-font-size: 14px; -fx-background-radius: 10;");
        saveImageButton.setOnMouseEntered(event -> {
            saveImageButton.setStyle("-fx-background-color: #424242; -fx-text-fill: white; "
        + "-fx-font-size: 14px; -fx-background-radius: 10;");
        });
        saveImageButton.setOnMouseExited(event -> {
            saveImageButton.setStyle("-fx-background-color: #5853583b; -fx-text-fill: white; "
        + "-fx-font-size: 14px; -fx-background-radius: 10;");
        });
    }

    public static String chooseImage() {
        
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose your profile picture");//Window name
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")); //File extension filter
    File selectedFile = fileChooser.showOpenDialog(null);//Open dialog box with file selection
    if(selectedFile == null){
        return null;
    }
    String absolute = selectedFile.getAbsolutePath(); 
    String absolutePathForImageInsert = "file:/" + absolute.replace('\\', '/');
    return absolutePathForImageInsert;
    }

    public void saveCroppedImage() {
        if (image == null) return;

        try {
            // Calculate the center of the canvas
            double centerX = canvas.getWidth() / 2;
            double centerY = canvas.getHeight() / 2;

            // Create an image from the current state of the canvas
            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, writableImage); 

            // Create a cut-out circular image
            WritableImage croppedImage = new WritableImage((int) (2 * CIRCLE_RADIUS), (int) (2 * CIRCLE_RADIUS));

            // Fill the image with pixels inside the circle
            for (int y = 0; y < 2 * CIRCLE_RADIUS; y++) {
                for (int x = 0; x < 2 * CIRCLE_RADIUS; x++) {
                    // Distance from the current point to the center of the circle
                    double distance = Math.sqrt(Math.pow(x - CIRCLE_RADIUS, 2) + Math.pow(y - CIRCLE_RADIUS, 2));

                    // If the point is inside the circle
                    if (distance <= CIRCLE_RADIUS) {
                        int sourceX = (int) (centerX - CIRCLE_RADIUS + x);
                        int sourceY = (int) (centerY - CIRCLE_RADIUS + y);

                        // Transfer a pixel from the default image to the new image
                        croppedImage.getPixelWriter().setColor(x, y,
                                writableImage.getPixelReader().getColor(sourceX, sourceY));
                    }
                }
            }


            // Check if the output folder exists and create it if necessary
            if(!outputFile.exists())
            outputFile.mkdirs();

            // Save the cut image to a file
            ImageIO.write(SwingFXUtils.fromFXImage(croppedImage, null), "png", outputFile);

            // If this is the first time the user changes the profile picture
            if(MainUI.FirstProfilePicChange){
                WriteToJson.WriteToJSON("FirstProfilePicChange", false);// Update the value in JSON
                MainUI.FirstProfilePicChange = false; // Update the variable in the program
            }
            // Pause while before updating the profile
            Thread.sleep(600);
            // Update user profile image
            UserProfilePic = outputFile.toURI().toString();
            mainApp.loadScene("/com/customizer/ui/fxml/Profile.fxml");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (image != null) {
            gc.save();
            gc.scale(scale, scale);
            gc.drawImage(image, imageX / scale, imageY / scale);
            gc.restore();
        }

        // Semi-transparent background
        gc.setFill(Color.color(0, 0, 0, 0.5)); 
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Transparent circle in the center
        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;

        // Draw a circle with a transparency mask
        gc.setFill(Color.WHITE); // White color for the circle
        gc.setGlobalBlendMode(BlendMode.OVERLAY); // Use Overlay to create a mask effect
        gc.fillOval(centerX - CIRCLE_RADIUS, centerY - CIRCLE_RADIUS, 2 * CIRCLE_RADIUS, 2 * CIRCLE_RADIUS);

        // Restore the blending mode
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
    }
     @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
}