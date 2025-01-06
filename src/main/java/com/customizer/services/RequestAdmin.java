package com.customizer.services;

import java.io.File;

import com.customizer.Main;
import com.customizer.core.GameUtils.AchievementController;

import javafx.scene.control.Label;



public class RequestAdmin {
    
    public static void RequestAdminRights(Main mainApp, Label coinsLabel) throws Exception {
        if (!isAdmin()) {
            //Getting the path to the current EXE-file
            String path = new File(System.getProperty("java.class.path")).getAbsolutePath();
            path = path.replace(";", "");
            path = path.replace("'", "''"); //Shielding quotes for PowerShell
    
            // PowerShell command to run with elevated permissions
            String[] command = {"powershell", "Start-Process", "\"" + path + "\"", "-Verb", "RunAs"};
            
            //Creating new process with elevated permissions and closing old process
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.start().waitFor();
            System.exit(0); 
        }
        
        // If program already with elevated permissions
        runFunctionality(mainApp, coinsLabel);
    }


    // Checking for administrator rights
    private static boolean isAdmin() {
        try {
            Process process = Runtime.getRuntime().exec("net session");
            process.waitFor();
            return process.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }


    private static void runFunctionality(Main mainApp, Label coinsLabel) { 
        WriteToJson.WriteToJSON("PerformanceOpened", true);
        try {
            String systemPropertiesPath = "C:\\Windows\\System32\\SystemPropertiesPerformance.exe";
            ProcessBuilder sysPropPb = new ProcessBuilder(systemPropertiesPath);
            
            //Starting SystemPropertiesPerformance.exe
            Process sysPropProcess = sysPropPb.start();
            sysPropProcess.waitFor();
            AchievementController.UnlockBoost(mainApp, coinsLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}