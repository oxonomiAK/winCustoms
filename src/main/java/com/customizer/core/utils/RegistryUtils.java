package com.customizer.core.utils;

import java.io.IOException;

import com.customizer.core.User32F.User32;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class RegistryUtils {

        public static void restartExplorer() {
        try {
            
            // Завершаем процесс explorer.exe
            Process killExplorer = Runtime.getRuntime().exec("taskkill /F /IM explorer.exe");
            killExplorer.waitFor(); // Ждём завершения процесса

        
            // Перезапускаем explorer.exe
            Process startExplorer = Runtime.getRuntime().exec("explorer.exe");
            startExplorer.waitFor(); // Ждём запуска процесса

            System.out.println("Explorer restarted successfully.");
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
        User32.INSTANCE.SendMessage(
            User32.HWND_BROADCAST,
            User32.WM_SETTINGCHANGE,
            0,
            0
        );
       
        


    }
    
}
