package com.customizer.core;

import java.io.IOException;

public class MicrophoneSettings {

    public static void openMicrophoneProperties() {
        try {
            // Opening the ‘Recording’ tab in the sound settings
            String command = "control mmsys.cpl,,1"; // This opens the device record tab
            Runtime.getRuntime().exec(command);
        } catch (IOException e) { //Error handling
            e.printStackTrace();
        }
    }

    
}
