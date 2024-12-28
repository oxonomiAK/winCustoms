package com.customizer.services;

import java.io.File;



public class RequestAdmin {
    

    public static void RequestAdminRights() throws Exception {
        if (!isAdmin()) {
            System.out.println("Запуск с повышенными правами...");
            
            // Получение пути к текущему JAR-файлу
            String path = new File(System.getProperty("java.class.path")).getAbsolutePath();
            path = path.replace(";", "");
            path = path.replace("'", "''"); // Экранирование кавычек для PowerShell
    
            // Команда PowerShell для запуска с повышенными правами
            String[] command = {
                "powershell",
                "Start-Process", "\"" + path + "\"", "-Verb", "RunAs"
            };
    
           
    
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO(); // Выводить stdout и stderr в консоль для отладки
            pb.start().waitFor();

            System.exit(0); // Возвращение в вызывающий метод (не завершая процесс принудительно)
        }
        
        // Если программа уже запущена с правами администратора
        System.out.println("Программа запущена с правами администратора.");
        runFunctionality();
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
    public static void runFunctionality() {
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