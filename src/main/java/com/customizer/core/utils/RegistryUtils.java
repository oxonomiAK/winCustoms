package com.customizer.core.utils;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class RegistryUtils {

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

    public static int getIconSize() {
        String registryPath = "SOFTWARE\\Microsoft\\Windows\\Shell\\Bags\\1\\Desktop";
        String registryKey = "IconSize";

        
        return Advapi32Util.registryGetIntValue(
            WinReg.HKEY_CURRENT_USER,
            registryPath,
            registryKey    
            );

    }

    
}
