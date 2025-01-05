package com.customizer.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadFromJson {
    // Метод для чтения числовых значени
    private static File folder = new File("NewLookResources");
    private static File JsonFile = new File("NewLookResources/ProgrammVariables.json");
    private static void CheckForExist(){
        if(!folder.exists())
        folder.mkdirs();
    }

    public static int ReadFromJSONint(String variableName) {
        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();
        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
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

    public static boolean ReadFromJSONBooleanT(String variableName) {
        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();
        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }

            if (!jsonData.containsKey(variableName)) {
                WriteToJson.WriteToJSON(variableName, true);
                return true;
            }

            String data = jsonData.get(variableName).toString();
            return Boolean.parseBoolean(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean ReadFromJSONBooleanF(String variableName) {
        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();
        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
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
    public static String ReadFromJSONString(String variableName) {
        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();
        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }

            if (!jsonData.containsKey(variableName)) {
                WriteToJson.WriteToJSON(variableName, null);
                return null;
            }

            return jsonData.get(variableName).toString();
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}