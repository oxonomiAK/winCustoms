package com.customizer.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadFromJson {
    //Define folder and .json file location
    private static File folder = new File("NewLookResources");
    private static File JsonFile = new File("NewLookResources/ProgrammVariables.json");
    //Method to create folder if it doesn't exist
    private static void CheckForExist(){
        if(!folder.exists())
        folder.mkdirs();
    }
    //Method for reading int variable from .json file
    public static int ReadFromJSONint(String variableName) {
        //Checking if folder exist
        CheckForExist();
        //Defining ObjectMapper and Map<> objects for further reading from .json file
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();

        try {

            if (JsonFile.exists() && JsonFile.length() > 0) {
            // Reading JSON data from the JsonFile file and deserialize it into a Map<String, Object> object
            // JsonFile is a file containing data in JSON format
            // ObjectMapper (mapper) converts the contents of the file into a Map, where keys are strings and values can be of any type
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }
            //Checking for variable existing before return to avoid exception when variable name doesnt exist and cannot be readed
            if (!jsonData.containsKey(variableName)) {
                WriteToJson.WriteToJSON(variableName, 0);
                return 0;
            }
            //Reading data from Map by key "variableName" and converting it to String
            String data = jsonData.get(variableName).toString();
            return Integer.parseInt(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //Method for reading boolean variable from .json file and if it doesnt exist automatically set it to true
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

    //Method for reading boolean variable from .json file and if it doesnt exist automatically set it to false
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

    //Method for reading String variable from .json file
    public static String ReadFromJSONString(String variableName) {
        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();
        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }

            if (!jsonData.containsKey(variableName)) {
                WriteToJson.WriteToJSON(variableName, "null");
                return "null";
            }

            return jsonData.get(variableName).toString();
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Method for reading double variable from .json file
    public static double ReadFromJSONDouble(String variableName) {
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
            return Double.parseDouble(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}