package com.customizer.core.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetSID {

    public static String GetCurrentSID(){
        String line, result=null;
         try {
            // Выполняем команду whoami /user
            ProcessBuilder processBuilder = new ProcessBuilder("whoami", "/user");
            Process process = processBuilder.start();

            // Читаем результат выполнения команды
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            while ((line = reader.readLine()) != null) {
                // Выводим строку, содержащую SID
                if (line.contains("S-")) { // Проверяем наличие SID (начинается с "S-")
                     result = line.split("\\s+")[1];
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
