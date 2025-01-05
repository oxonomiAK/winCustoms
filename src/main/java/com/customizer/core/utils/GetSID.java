package com.customizer.core.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetSID {

    public static String GetCurrentSID(){
        String line, result=null;
         try {
            //Executing command whoami /user
            ProcessBuilder processBuilder = new ProcessBuilder("whoami", "/user");
            Process process = processBuilder.start();

            //Reading the result of command execution
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            while ((line = reader.readLine()) != null) {
                // Output the string containing SID
                if (line.contains("S-")) // Check for SID (starts with "S-")
                    result = line.split("\\s+")[1];
                
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
