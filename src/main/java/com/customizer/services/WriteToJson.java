package com.customizer.services;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WriteToJson {
         // Создаем объект для записи
    ;

    static String filePath = "DeletedFileCount.json";
    static File file = new File(filePath);

        // Пишем в JSON-файл
    static ObjectMapper mapper = new ObjectMapper();

    public static void WriteToJSON(int FileCount, String variableName) throws InterruptedException{
        // Значение переменной
         
        try {
            Map<String, Object> jsonData = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
            // Проверяем, существует ли файл
            if (file.exists()) {
                // Читаем JSON-файл в объект Data
                jsonData.put(variableName, FileCount);
            } else {
                // Если файл не существует, создаем новый объект с начальным значением
                jsonData.put(variableName, 0);
            }

            // Записываем измененный объект обратно в JSON-файл
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonData);
            System.out.println("Значение обновлено и сохранено в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
