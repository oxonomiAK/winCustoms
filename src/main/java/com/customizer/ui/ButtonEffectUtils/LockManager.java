package com.customizer.ui.ButtonEffectUtils;

import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;
import com.customizer.ui.UIControllers.MainUI;


public class LockManager {


    public static void CheckAndUnlock(boolean WallpaperStatus, boolean WallpaperUnlocked, int wallpaperCond, String wallpaperName, boolean Unlock, String scene, MainUI mainApp){
        if(WallpaperStatus && !WallpaperUnlocked){
            mainApp.spendCoins(wallpaperCond);
            unlock(wallpaperName, Unlock);
            mainApp.loadScene(scene);
        }
    }

    public static boolean CanUnlock(String wallpaperName, String conditionVariableName, int howMuchForUnlock){
        int currentValue = ReadFromJson.ReadFromJsonJSON(conditionVariableName);

        boolean isLocked = ReadFromJson.ReadFromJsonJSONBoolean(wallpaperName);

        if(((currentValue >= howMuchForUnlock) && (isLocked == false)) || isLocked == true)
            return true;
        
        else
            return false;
        
    }


    public static void unlock(String wallpaperName, boolean isLocked){
        WriteToJson.WriteToJSON(wallpaperName, isLocked);
    }

}
