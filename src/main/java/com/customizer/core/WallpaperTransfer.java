package com.customizer.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.customizer.Main;

public class WallpaperTransfer {
    public static void Transfer(String wallpaperPath, String wallpaperName){
        
        String destinationPath = "NewLookResources/"+wallpaperName+".jpg";
        //Getting file as inputStream
        try (InputStream inputStream = Main.class.getResourceAsStream(wallpaperPath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("File not found in resources");
            }

            // Creating folder if it not exist
            Files.createDirectories(Paths.get(destinationPath).getParent());

            // Copying file from internal resources
            try (FileOutputStream outputStream = new FileOutputStream(destinationPath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
