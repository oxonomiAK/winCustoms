package com.customizer.services;

import java.io.IOException;

public class RestartExplorer {
    public static void restartExplorer() {
        try {
        //Executing command for closing explorer.exe
        Runtime.getRuntime().exec("taskkill /f /im explorer.exe");
        // A short delay to allow this process to complete
        Thread.sleep(1000);

        //Restarting explorer.exe
        Runtime.getRuntime().exec("explorer.exe");
        
        //One more delay before next iteration
        Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
     }
}
