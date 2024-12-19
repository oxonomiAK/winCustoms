package com.customizer.services;

import java.io.IOException;

public class RestartExplorer {
    public static void restartExplorer() {
        try {
         Runtime.getRuntime().exec("taskkill /f /im explorer.exe");
         // Небольшая задержка, чтобы процесс успел завершиться
         Thread.sleep(500);
 
         // Перезапуск explorer.exe
         Runtime.getRuntime().exec("explorer.exe");
         
         // Еще одна задержка перед следующей итерацией
         Thread.sleep(500);
         } catch (IOException | InterruptedException e) {
             e.printStackTrace();
         }
     }
}
