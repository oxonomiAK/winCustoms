package com.customizer.core.AchievementLogic.AchievementBin;

import com.customizer.core.AchievementLogic.AchievementReader.*;
import java.io.*;

public class BinAchievement {
    private int counter = 0;
    // Json realization

    public BinAchievement() {

    }

    public void incrementCounter() throws IOException {
        counter++;
        // json.put("counter", counter); // Обновляем JSON

        if (counter >= 50) {

        }
    }

}
