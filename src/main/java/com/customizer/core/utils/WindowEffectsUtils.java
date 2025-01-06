package com.customizer.core.utils;

import com.customizer.core.User32F;

public class WindowEffectsUtils {

    public static void enableShadowEff() {
        User32F.User32.INSTANCE.SystemParametersInfoW(
                User32F.User32.SPI_SETDROPSHADOW,
                0,
                true,
                User32F.User32.SPIF_UPDATEINIFILE | User32F.User32.SPIF_SENDCHANGE);
    }

    public static void disableShadowEff() {
        User32F.User32.INSTANCE.SystemParametersInfoW(
                User32F.User32.SPI_SETDROPSHADOW,
                0,
                false,
                User32F.User32.SPIF_UPDATEINIFILE | User32F.User32.SPIF_SENDCHANGE);
    }
}
