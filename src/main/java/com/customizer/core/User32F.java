package com.customizer.core;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

/*                                      DONT TOUCH THIS FILE
 *                                             KIRILL
 *                                              GOES
 *                                    C++ MARATHON FOR 2 MONTHS
 */

public class User32F {
        // Interface for User32 with SystemParametersInfo methods
        public interface User32 extends Library {
                User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

                // constant to set desktop wallpaper
                // constant to update it in registry
                // constant to notify other software about changes
                // constant to change icon size
                final int SPI_SETDESKWALLPAPER = 0x0014;
                final int SPIF_UPDATEINIFILE = 0x01;
                final int SPIF_SENDCHANGE = 0x02;

                // constant to get desktop wallpapers path
                final int SPI_GETDESKWALLPAPER = 0x0073;
                final int MAX_PATH = 260;

                // constant to set shadow
                final int SPI_SETDROPSHADOW = 0x1025;

                // Methodes SystemParametersInfoW with Unicode

                boolean SystemParametersInfoW(int uiAction, int uiParam, String pvParam, int fWinIni);

                boolean SystemParametersInfoW(int uiAction, int uiParam, int pvParam, int fWinIni);

                boolean SystemParametersInfoW(int uiAction, int uiParam, char[] pvParam, int fWinIni);

                boolean SystemParametersInfoW(int uiAction, int uiParam, boolean pvParam, int fWinIni);

        }
}
