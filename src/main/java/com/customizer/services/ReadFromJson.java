package com.customizer.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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

    public static boolean ReadFromJsonJSONBoolean(String variableName) {
        String filePath = "ProgrammVariables.json";
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();
        try {
            if (file.exists() && file.length() > 0) {
                jsonData = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
            }

            if (!jsonData.containsKey(variableName)) {
                WriteToJson.WriteToJSON(variableName, false);
                return false;
            }

            String data = jsonData.get(variableName).toString();
            return Boolean.parseBoolean(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
