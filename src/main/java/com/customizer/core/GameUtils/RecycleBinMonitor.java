package com.customizer.core.GameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.customizer.core.utils.GetSID;
import com.customizer.services.WriteToJson;

public class RecycleBinMonitor {

    public static void main(String[] args) throws InterruptedException {
        // Получаем SID текущего пользователя
        String currentSID = GetSID.GetCurrentSID();
        if (currentSID == null) {
            System.out.println("Не удалось определить SID текущего пользователя.");
            return;
        }

        // Определяем путь к корзине для текущего пользователя
        String recycleBinPath = System.getenv("SystemDrive") + "\\$Recycle.Bin\\" + currentSID;
        File recycleBin = new File(recycleBinPath);

        // Проверяем, существует ли корзина
        if (recycleBin.exists() && recycleBin.isDirectory()) {
            System.out.println("Мониторинг корзины на изменения...");
            monitorRecycleBin(recycleBin);
        } else {
            System.out.println("Корзина не найдена или ОС не поддерживается.");
        }
    }


    // Метод для мониторинга корзины
    public static void monitorRecycleBin(File recycleBin) throws InterruptedException {
        Path path = recycleBin.toPath();

        try {

            int DeletedFileCount = 0;
            WatchService watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_DELETE);
            
    
            while (!((DeletedFileCount) >= 100)) {
                WatchKey key = watchService.take();
                Thread.sleep(100);

                Pattern pattern = Pattern.compile("^\\$R.*", Pattern.CASE_INSENSITIVE);
                   
                // Обрабатываем события

                for (WatchEvent<?> event : key.pollEvents()) {
                        Matcher matcher = pattern.matcher(event.context().toString());
                        if(matcher.matches()) {
                            ++DeletedFileCount;
                            System.out.println(DeletedFileCount);

                        }
                        
                } 
                // Сброс ключа и готовность для следующих событий
                
                WriteToJson.WriteToJSON(DeletedFileCount, "DeletedFileCount");
                key.reset();
            }

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


