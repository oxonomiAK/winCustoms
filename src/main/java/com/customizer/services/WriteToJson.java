package com.customizer.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WriteToJson {
    // Метод для записи числовых значений
    private static File JsonFile = new File("NewLookResources/ProgrammVariables.json");
    private static File folder = new File("NewLookResources");
    private static void CheckForExist(){
        if(!folder.exists())
        folder.mkdirs();
    }
    public static void WriteToJSON(String variableName, int value) {

        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();

        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }

            jsonData.put(variableName, value);

            // Записываем измененный объект обратно в JSON-файл
            mapper.writerWithDefaultPrettyPrinter().writeValue(JsonFile, jsonData);
            System.out.println("Значение обновлено и сохранено в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriteToJSON(String variableName, boolean value) {
        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();

        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }

            jsonData.put(variableName, value);

            // Записываем измененный объект обратно в JSON-файл
            mapper.writerWithDefaultPrettyPrinter().writeValue(JsonFile, jsonData);
            System.out.println("Значение обновлено и сохранено в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void WriteToJSON(String variableName, String value) {
        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();

        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }

            jsonData.put(variableName, value);

            // Записываем измененный объект обратно в JSON-файл
            mapper.writerWithDefaultPrettyPrinter().writeValue(JsonFile, jsonData);
            System.out.println("Значение обновлено и сохранено в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
