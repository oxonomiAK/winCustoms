package com.customizer.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadFromJson {
    // Метод для чтения числовых значений
    public static int ReadFromJsonJSON(String variableName) {
        String filePath = "ProgrammVariables.json";
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();
        try {
            if (file.exists() && file.length() > 0) {
                jsonData = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
            }

            if (!jsonData.containsKey(variableName)) {
                WriteToJson.WriteToJSON(variableName, 0);
                return 0;
            }

            String data = jsonData.get(variableName).toString();
            return Integer.parseInt(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Метод для чтения коллекций
    public static Set<String> ReadSetFromJson(String variableName) {
        String filePath = "ProgrammVariables.json";
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();
        try {
            if (file.exists() && file.length() > 0) {
                jsonData = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
            }

            if (!jsonData.containsKey(variableName)) {
                WriteToJson.WriteToJSON(variableName, new HashSet<>());
                return new HashSet<>();
            }

            return mapper.convertValue(jsonData.get(variableName), new TypeReference<Set<String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }
}
