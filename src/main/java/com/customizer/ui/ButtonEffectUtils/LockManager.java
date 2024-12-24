package com.customizer.ui.ButtonEffectUtils;

import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;


public class LockManager {



    public static boolean CheckForLock(String wallpaperName, String conditionVariableName, int howMuchForUnlock){
        int currentValue = ReadFromJson.ReadFromJsonJSON(conditionVariableName);

        boolean isLocked = ReadFromJson.ReadFromJsonJSONBoolean(wallpaperName);

        if(((currentValue >= howMuchForUnlock) && (isLocked == false)) || isLocked == true){
            isLocked = true;
            WriteToJson.WriteToJSON(wallpaperName, isLocked);
            return true;
        }
        else{
            isLocked = false;
            WriteToJson.WriteToJSON(wallpaperName, isLocked);
            return false;
        }
    }

}