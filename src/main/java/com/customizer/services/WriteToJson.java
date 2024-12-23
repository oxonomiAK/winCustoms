package com.customizer.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WriteToJson {
    // Метод для записи числовых значений
    public static void WriteToJSON(String variableName, int value) {
        String filePath = "ProgrammVariables.json";
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();

        try {
            if (file.exists() && file.length() > 0) {
                jsonData = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
            }

            jsonData.put(variableName, value);

            // Записываем измененный объект обратно в JSON-файл
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonData);
            System.out.println("Значение обновлено и сохранено в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для записи коллекций
    public static void WriteToJSON(String variableName, Set<String> purchasedWallpapers) {
        String filePath = "ProgrammVariables.json";
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();

        try {
            if (file.exists() && file.length() > 0) {
                jsonData = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
            }

            jsonData.put(variableName, purchasedWallpapers);

            // Записываем измененный объект обратно в JSON-файл
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonData);
            System.out.println("Коллекция обновлена и сохранена в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
