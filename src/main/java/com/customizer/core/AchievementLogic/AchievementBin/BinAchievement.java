package com.customizer.core.AchievementLogic.AchievementBin;


import com.customizer.core.AchievementLogic.AchievementReader.*;
import java.io.*;

public class BinAchievement {
    private int counter = 0;
    //Json realization
    


    public BinAchievement()
    {
        
    }

    public void incrementCounter() throws IOException {
        counter++;
    //  json.put("counter", counter); // Обновляем JSON

        if (counter >= 50) {
            checkAndUnlockAchievement("counter>=50");
        }
    }

    private void checkAndUnlockAchievement(String condition) throws IOException {
    
        if ("NO".equals(response)) {
            // Если достижение ещё не получено, добавляем его
            writer.write("ADD_EARNED " + achievementId + "\n");
            writer.flush();
            String addResponse = reader.readLine();
            if (!"OK".equals(addResponse)) {
                throw new IOException("Failed to add achievement: " + addResponse);
            }
        }
    }

    
}

