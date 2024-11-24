package com.customizer.core.utils;

import java.io.IOException;

import com.customizer.core.User32F.User32;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class RegistryUtils {

        public static void restartExplorer() {
            try {
                // Указываем путь к вашему батнику
                String batFilePath = "src/main/java/com/customizer/RestartExplorer.bat";
                
                // Создаем процесс для запуска батника
                Process process = new ProcessBuilder(batFilePath).start();
                
                // Ждем завершения процесса
                process.waitFor();
                
                System.out.println("Explorer restarted via batch file.");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }



    public static void setIconSize(int iconSize) {
        String registryPath = "SOFTWARE\\Microsoft\\Windows\\Shell\\Bags\\1\\Desktop";
        String registryKey = "IconSize";

        // Устанавливаем новый размер значков в реестре
        Advapi32Util.registrySetIntValue(
            WinReg.HKEY_CURRENT_USER,
            registryPath,
            registryKey,
            iconSize
        );
        


    }
    
}
