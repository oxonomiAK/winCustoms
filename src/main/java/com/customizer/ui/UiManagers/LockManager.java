package com.customizer.ui.UiManagers;

import com.customizer.Main;
import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;


public class LockManager {

    //Final check before unlocking
    public static void CheckAndUnlock(boolean WallpaperStatus, boolean WallpaperUnlocked, int wallpaperCond, String wallpaperName, String scene, Main mainApp){
        if(WallpaperStatus && !WallpaperUnlocked){
            //Spending coins
            mainApp.spendCoins(wallpaperCond);
            //unlocking wallpapers
            unlock(wallpaperName, true);
            //reload scene to show unlocked wallpaper
            mainApp.loadScene(scene);
        }
    }
    //Getting all status about wallpaper and check if it can be unlocked
    public static boolean CanUnlock(String wallpaperName, String conditionVariableName, int howMuchForUnlock){
        //Getting current coins
        int currentValue = ReadFromJson.ReadFromJSONint(conditionVariableName);
        //Check if this wallpaper unlocked
        boolean isLocked = ReadFromJson.ReadFromJSONBooleanF(wallpaperName);
        
        if(((currentValue >= howMuchForUnlock) && (!isLocked)) || isLocked)
            return true;
        else
            return false;
    }


    public static void unlock(String wallpaperName, boolean isLocked){
        WriteToJson.WriteToJSON(wallpaperName, isLocked);
    }

}
