package com.customizer.core.GameUtils;

import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;

public class LevelManager {
    public void giveExperience(double amount){
        double currentXP = Math.round(ReadFromJson.ReadFromJSONDouble("currentProgress")*10 ) /10.0;
        int currentRank = ReadFromJson.ReadFromJSONint("currentRank");
        currentXP = currentXP + amount;
        if(!(currentRank == 5 && currentXP >= 1.0)){
            while(currentXP >=1.0){
                currentXP -= 1.0;
                currentRank += 1;
                if(currentRank >= 5) currentRank = 5;
                
                WriteToJson.WriteToJSON("currentRank", currentRank);
            }
        }

        if(currentRank == 5 && currentXP >= 1)
        AchievementController.UnlockMaxlvl();
        System.out.println(Math.round(currentXP * 10) / 10.0);
        WriteToJson.WriteToJSON("currentProgress", Math.round(currentXP * 10) / 10.0);
    }
}
