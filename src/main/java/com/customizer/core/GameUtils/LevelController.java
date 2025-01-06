package com.customizer.core.GameUtils;

public class LevelController {
    private static LevelManager levelManager = new LevelManager();
    public static void GiveExp(double amount){
        levelManager.giveExperience(amount);
    }
}
