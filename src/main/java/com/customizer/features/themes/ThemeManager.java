package com.customizer.features.themes;

import com.customizer.core.utils.RegistryUtils;
import com.customizer.core.utils.WindowEffectsUtils;

public class ThemeManager {

    public static void setShadows() {
        WindowEffectsUtils.enableShadowEff();
    }

    public static void disableShadows() {
        WindowEffectsUtils.disableShadowEff();
    }

}
