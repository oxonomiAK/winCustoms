package com.customizer.core.GameUtils;

import com.customizer.Main;
import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;
import com.customizer.ui.UIControllers.WallpapersController2;
import com.customizer.ui.UIControllers.WallpapersController3;
import com.customizer.ui.UIControllers.WallpapersController4;
import com.customizer.ui.UiManagers.DesktopNotificationController;
import com.customizer.ui.UiManagers.UpdateCoins;

import javafx.scene.control.Label;

public class AchievementManager extends Main {
    private int changedWallpaperCount = ReadFromJson.ReadFromJSONint("changedWallpaperCount");
    UpdateCoins updateCoins = new UpdateCoins();

    public void WallpaperCount(Main mainApp, Label coinsLabel){
        changedWallpaperCount++;
        WriteToJson.WriteToJSON("changedWallpaperCount", changedWallpaperCount);
        switch (changedWallpaperCount) {
            case 1:
                if(!ReadFromJson.ReadFromJSONBooleanF("Wallpaper1Ach")){
                    WriteToJson.WriteToJSON("Wallpaper1Ach", true);
                    DesktopNotificationController.SendNotif("Refresh", "You have installed a new wallpaper!");
                    LevelController.GiveExp(0.2);
                    mainApp.addCoins(60);
                    updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
                    
                }
                break;
            case 10:
                if(!ReadFromJson.ReadFromJSONBooleanF("Wallpaper10Ach")){
                    WriteToJson.WriteToJSON("Wallpaper10Ach", true);
                    DesktopNotificationController.SendNotif("You Have Taste", "You have changed the wallpaper 10 times");
                    LevelController.GiveExp(0.8);
                    mainApp.addCoins(60);
                    updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
                }
                break;
            case 16:
                if(!ReadFromJson.ReadFromJSONBooleanF("Wallpaper100Ach")){
                    WriteToJson.WriteToJSON("Wallpaper100Ach", true);
                    DesktopNotificationController.SendNotif("Aren`t You Bored?", "You have changed the wallpaper 16 times!");
                    LevelController.GiveExp(0.5);
                    mainApp.addCoins(60);
                    updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
                }
                break;
            default: break;
        }
    }

    public void AllWallpaperUnlock(){
        boolean wall2page = WallpapersController2.Wallpaper2page;
        boolean wall3page = WallpapersController3.Wallpaper3page;
        boolean wall4page = WallpapersController4.Wallpaper4page;
        if(wall2page && wall3page && wall4page && (!ReadFromJson.ReadFromJSONBooleanF("adventurerAch"))){
            WriteToJson.WriteToJSON("adventurerAch", true);
            DesktopNotificationController.SendNotif("Indiana Jones", "Unlock all wallpapers!");
            LevelController.GiveExp(1);
        }
    }

    public void RecycleCheckAndUnlock(){
        if(!ReadFromJson.ReadFromJSONBooleanF("binAch")){
            WriteToJson.WriteToJSON("binAch", true);
            DesktopNotificationController.SendNotif("Recycler", "You have emptied the recycle garbage can!");
            LevelController.GiveExp(0.5);
        }
    }
    public void iconchangeCheckAndUnlock(Main mainApp, Label coinsLabel){
        if(!ReadFromJson.ReadFromJSONBooleanF("iconchangeAch")){
            WriteToJson.WriteToJSON("iconchangeAch", true);
            DesktopNotificationController.SendNotif("Measure Twice...", "You resized the icons!");
            LevelController.GiveExp(0.3);
            mainApp.addCoins(60);
            updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
        }
    }
    public void icondefaultCheckAndUnlock(Main mainApp, Label coinsLabel){
        if(!ReadFromJson.ReadFromJSONBooleanF("icondefaultAch")){
            WriteToJson.WriteToJSON("icondefaultAch", true);
            DesktopNotificationController.SendNotif("...Cut Once", "You resized the icons back to their original size!");
            LevelController.GiveExp(0.2);
            mainApp.addCoins(60);
            updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
        }
    }

    public void growupCheckAndUnlock(Main mainApp, Label coinsLabel){
        if(!ReadFromJson.ReadFromJSONBooleanF("growupAch")){
            WriteToJson.WriteToJSON("growupAch", true);
            DesktopNotificationController.SendNotif("Size Wizard", "You resized the icons to the maximum possible size!");
            LevelController.GiveExp(0.7);
            mainApp.addCoins(60);
            updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
        }
    }
    public void growdownCheckAndUnlock(Main mainApp, Label coinsLabel){
        if(!ReadFromJson.ReadFromJSONBooleanF("growdownAch")){
            WriteToJson.WriteToJSON("growdownAch", true);
            DesktopNotificationController.SendNotif("Size Doesn't Matter", "You resized the icons to the minimum possible size!");
            LevelController.GiveExp(0.7);
            mainApp.addCoins(60);
            updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
        }
    }
    public void maxlvlCheckAndUnlock(){
        if(!ReadFromJson.ReadFromJSONBooleanF("maxlvlAch")){
            WriteToJson.WriteToJSON("maxlvlAch", true);
            DesktopNotificationController.SendNotif("Fashionista?", "You reached the maximum level!");
        }
    }
    public void boostCheckAndUnlock(Main mainApp, Label coinsLabel){
        if(!ReadFromJson.ReadFromJSONBooleanF("boostAch")){
            WriteToJson.WriteToJSON("boostAch", true);
            DesktopNotificationController.SendNotif("Boost!", "You changed the performance settings!");
            LevelController.GiveExp(0.5);
            mainApp.addCoins(60);
            updateCoins.updateCoinsDisplay(coinsLabel, mainApp);
        }
    }
    public void ProfilePicCheckAndUnlock(){
        if(!ReadFromJson.ReadFromJSONBooleanF("ProfilePicAch")){
            WriteToJson.WriteToJSON("ProfilePicAch", true);
            DesktopNotificationController.SendNotif("New Face", "You have changed your profile picture!");
            LevelController.GiveExp(0.6);
           
        }
    }
}
