package com.customizer.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class RequestAdmin {
    

     public static void RequestAdminRights() throws IOException{

        if (!isAdmin()) {
            // Если не запущена с правами администратора, перезапустить с повышением прав
            System.out.println("Запуск с повышенными правами...");
            
            // Получение пути к текущему JAR-файлу
            String path = null;
            try {
                path = new File(RequestAdmin.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            

            // Команда PowerShell для запуска с повышенными правами
            String[] command = {
                "powershell", "-Command",
                "Start-Process", "javaw",
                "-ArgumentList", "'-jar', '" + path + "'",
                "-Verb", "RunAs"
            };
            // Создание процесса для запуска с правами администратора
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO(); // Выводить stdout и stderr в консоль для отладки

            // Запуск команды и завершение текущего процесса
            pb.start();
            
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
            
        }
        if(isAdmin()){
        // Если программа уже запущена с правами администратора
            System.out.println("Программа запущена с правами администратора.");
        // Ваш функционал
            runFunctionality();
        }
    }



    // Проверка на права администратора
    private static boolean isAdmin() {
        try {
            Process process = Runtime.getRuntime().exec("net session");
            process.waitFor();
            return process.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Основной функционал программы
    private static void runFunctionality() {
        try {
            System.out.println("Выполняется основной функционал программы...");
            
            // Пример: запуск SystemPropertiesPerformance.exe
            String systemPropertiesPath = "C:\\Windows\\System32\\SystemPropertiesPerformance.exe";
            ProcessBuilder sysPropPb = new ProcessBuilder(systemPropertiesPath);
            sysPropPb.inheritIO(); // Выводить stdout и stderr
            
            Process sysPropProcess = sysPropPb.start();
            sysPropProcess.waitFor();

            System.out.println("SystemPropertiesPerformance завершён.");
        } catch (Exception e) {
            System.err.println("Ошибка выполнения основного функционала: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
