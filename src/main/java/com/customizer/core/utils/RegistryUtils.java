package com.customizer.core.utils;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class RegistryUtils {

    public static void setIconSize(int iconSize) {
        //Path in Windows registry to icon sice
        String registryPath = "SOFTWARE\\Microsoft\\Windows\\Shell\\Bags\\1\\Desktop";
        String registryKey = "IconSize";

        //Setting the new icon size in the registry
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

        //Getting icon size from registry
        return Advapi32Util.registryGetIntValue(
            WinReg.HKEY_CURRENT_USER,
            registryPath,
            registryKey    
            );

    }

    
}
