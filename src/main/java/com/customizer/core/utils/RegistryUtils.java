package com.customizer.core.utils;

import com.customizer.core.acTemp;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class RegistryUtils {
    public static void setAccentColor(String color) {
        Advapi32Util.registrySetStringValue(
                WinReg.HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\DWM", "AccentColor", color);
    }

    public static void getAccentColor() {

        int rawValue = Advapi32Util.registryGetIntValue(
                WinReg.HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\DWM", "AccentColor");
        acTemp.defaultAccentColor = rawValue & 0xFFFFFFFFL;
    }
}
