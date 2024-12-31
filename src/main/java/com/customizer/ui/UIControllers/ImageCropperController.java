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

    private double imageX = 0, imageY = 0; // Координаты изображения
    private double dragStartX, dragStartY; // Начало перемещения
    private double scale = 1.0; // Масштаб изображения
    private Image image;
    static File outputFile = new File("NewLookResources/user.png");
    private final double CIRCLE_RADIUS = 150; // Радиус круга обрезки
    public static String UserProfilePic = outputFile.toURI().toString();;
    private MainUI mainApp;
    public void setMainApp(MainUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Обработчики для перемещения изображения
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

        // Обработчик для прокрутки колесика мыши для изменения масштаба
        canvas.setOnScroll(event -> {
            if (event.getDeltaY() > 0) {
                scale *= 1.1; // Увеличиваем масштаб
            } else {
                scale /= 1.1; // Уменьшаем масштаб
            }
            draw(gc);
        });

        // Выбор изображения
        String profilePicPath = ProfileController.picImage;
        image = new Image(profilePicPath);
        imageX = (canvas.getWidth() - image.getWidth()) / 2;
        imageY = (canvas.getHeight() - image.getHeight()) / 2;
        draw(gc);

        // Сохранение изображения
        saveImageButton.setOnAction(event -> saveCroppedImage());
    }

    public static String chooseImage() {
        
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose your profile picture");//Имя окна
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")); //Фильтр расширения файлов
    File selectedFile = fileChooser.showOpenDialog(null);//Открытие диалогового окна с выбором файла
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
            double centerX = canvas.getWidth() / 2;
            double centerY = canvas.getHeight() / 2;

            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, writableImage);

            // Создаем вырезанное круговое изображение
            WritableImage croppedImage = new WritableImage((int) (2 * CIRCLE_RADIUS), (int) (2 * CIRCLE_RADIUS));

            for (int y = 0; y < 2 * CIRCLE_RADIUS; y++) {
                for (int x = 0; x < 2 * CIRCLE_RADIUS; x++) {
                    double distance = Math.sqrt(Math.pow(x - CIRCLE_RADIUS, 2) + Math.pow(y - CIRCLE_RADIUS, 2));
                    if (distance <= CIRCLE_RADIUS) {
                        int sourceX = (int) (centerX - CIRCLE_RADIUS + x);
                        int sourceY = (int) (centerY - CIRCLE_RADIUS + y);
                        croppedImage.getPixelWriter().setColor(x, y,
                                writableImage.getPixelReader().getColor(sourceX, sourceY));
                    }
                }
            }



            if(!outputFile.exists())
            outputFile.mkdirs();


            ImageIO.write(SwingFXUtils.fromFXImage(croppedImage, null), "png", outputFile);

            if(MainUI.FirstProfilePicChange){
                WriteToJson.WriteToJSON("FirstProfilePicChange", false);
                MainUI.FirstProfilePicChange = false;
            }
            System.out.println("Image saved: " + outputFile.getAbsolutePath());
            Thread.sleep(600);
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

        // Полупрозрачный фон
        gc.setFill(Color.color(0, 0, 0, 0.5)); // Полупрозрачный черный фон
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Прозрачный круг в центре
        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;

        // Рисуем круг с маской прозрачности
        gc.setFill(Color.WHITE); // Белый цвет для круга
        gc.setGlobalBlendMode(BlendMode.OVERLAY); // Используем Overlay для создания эффекта маски
        gc.fillOval(centerX - CIRCLE_RADIUS, centerY - CIRCLE_RADIUS, 2 * CIRCLE_RADIUS, 2 * CIRCLE_RADIUS);

        // Восстанавливаем режим наложения
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
    }
     @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }
}
