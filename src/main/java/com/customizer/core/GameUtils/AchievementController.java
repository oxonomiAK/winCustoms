package com.customizer.core.GameUtils;

import com.customizer.Main;

import javafx.scene.control.Label;

public class AchievementController {
    private static AchievementManager achievementManager = new AchievementManager();
    public static void CountWallpaper(Main mainApp, Label coinsLabel){
        achievementManager.WallpaperCount(mainApp, coinsLabel);
    }
    public static void UnlockAllWallp(){
        achievementManager.AllWallpaperUnlock();
    }
    public static void UlockBin(){
        achievementManager.RecycleCheckAndUnlock();
    }
    public static void UnlockIconchange(Main mainApp, Label coinsLabel){
        achievementManager.iconchangeCheckAndUnlock(mainApp, coinsLabel);
    } 
    public static void UnlockIcondefault(Main mainApp, Label coinsLabel){
        achievementManager.icondefaultCheckAndUnlock(mainApp, coinsLabel);
    }
    public static void UnlockGrowup(Main mainApp, Label coinsLabel){
        achievementManager.growupCheckAndUnlock(mainApp, coinsLabel);
    }
    public static void UnlockGrowdown(Main mainApp, Label coinsLabel){
        achievementManager.growdownCheckAndUnlock(mainApp, coinsLabel);
    }
    public static void UnlockMaxlvl(){
        achievementManager.maxlvlCheckAndUnlock();
    }
    public static void UnlockBoost(Main mainApp, Label coinsLabel){
        achievementManager.boostCheckAndUnlock(mainApp, coinsLabel);
    }
    public static void UnlockProfilePic(){
        achievementManager.ProfilePicCheckAndUnlock();
    }
}
