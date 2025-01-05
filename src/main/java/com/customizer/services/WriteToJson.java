package com.customizer.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WriteToJson {
    //Define folder and .json file location
    private static File folder = new File("NewLookResources");
    private static File JsonFile = new File("NewLookResources/ProgrammVariables.json");
    //Method to create folder if it doesn't exist
    private static void CheckForExist(){
        if(!folder.exists())
        folder.mkdirs();
    }
    //Method for writing int variable into .json file, using method overloading 
    public static void WriteToJSON(String variableName, int value) {

        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();

        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
            // Reading JSON data from the JsonFile file and deserialize it into a Map<String, Object> object
            // JsonFile is a file containing data in JSON format
            // ObjectMapper (mapper) converts the contents of the file into a Map, where keys are strings and values can be of any type
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }
            //Putting new key:value into jsonData object
            jsonData.put(variableName, value);

            //Write the modified object back to the JSON file
            mapper.writerWithDefaultPrettyPrinter().writeValue(JsonFile, jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Method for writing boolean variable into .json file, using method overloading 
    public static void WriteToJSON(String variableName, boolean value) {
        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();

        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }

            jsonData.put(variableName, value);

            mapper.writerWithDefaultPrettyPrinter().writeValue(JsonFile, jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method for writing String variable into .json file, using method overloading 
    public static void WriteToJSON(String variableName, String value) {
        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();

        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }

            jsonData.put(variableName, value);

            mapper.writerWithDefaultPrettyPrinter().writeValue(JsonFile, jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method for writing double variable into .json file, using method overloading 
    public static void WriteToJSON(String variableName, double value) {

        CheckForExist();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = new HashMap<>();

        try {
            if (JsonFile.exists() && JsonFile.length() > 0) {
                jsonData = mapper.readValue(JsonFile, new TypeReference<Map<String, Object>>() {});
            }

            jsonData.put(variableName, value);

            mapper.writerWithDefaultPrettyPrinter().writeValue(JsonFile, jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
