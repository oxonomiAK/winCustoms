package com.customizer.ui.ButtonEffectUtils;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class CoinsManager {

    private int coins;

    public void saveCoinsToFile() {
        String directoryPath = System.getProperty("user.home") + "/Desktop/winCustoms";
        Path directory = Paths.get(directoryPath);
    
        try {
            // Убедимся, что директория существует
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
    
            Path filePath = directory.resolve("coins.txt");
            Files.write(filePath, String.valueOf(coins).getBytes());
            System.out.println("Монеты успешно сохранены в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
    
    public void loadCoinsFromFile() {
        String filePath = System.getProperty("user.home") + "/Desktop/winCustoms/coins.txt";
        Path path = Paths.get(filePath);
    
        try {
            // Считываем содержимое файла
            List<String> lines = Files.readAllLines(path);
            if (!lines.isEmpty()) {
                coins = Integer.parseInt(lines.get(0));
                System.out.println("Монеты успешно загружены: " + coins);
            } else {
                coins = 0;
                System.out.println("Файл пуст. Установлено значение по умолчанию: 0");
            }
        } catch (IOException | NumberFormatException e) {
            coins = 0; // Если файл не найден или поврежден
            System.err.println("Файл не найден или повреждён. Установлено значение по умолчанию: 0");
        }
    }
    

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
        System.out.println("Монет добавлено: " + amount + ". Всего монет: " + coins);
    }
    
}
