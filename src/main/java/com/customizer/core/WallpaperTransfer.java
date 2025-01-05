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

        try (InputStream inputStream = Main.class.getResourceAsStream(wallpaperPath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Файл не найден в ресурсах!");
            }

            // Создаем целевую папку, если её нет
            Files.createDirectories(Paths.get(destinationPath).getParent());

            // Копируем файл из ресурса
            try (FileOutputStream outputStream = new FileOutputStream(destinationPath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                int count = 0;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    count++;
                }
                System.out.println(count);
            }

            System.out.println("Файл успешно скопирован в: " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
