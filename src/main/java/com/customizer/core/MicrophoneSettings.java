package com.customizer.core;

import java.io.IOException;

public class MicrophoneSettings {

    public static void openMicrophoneProperties() {
        try {
            // Открытие вкладки "Запись" в настройках звука
            String command = "control mmsys.cpl,,1";
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
