package com.customizer.ui.ButtonEffectUtils;

import com.customizer.ui.UIControllers.MainUI;

import javafx.scene.control.Label;

public class UpdateCoins {
    //Updating coins on display after changing
    public void updateCoinsDisplay(Label coinsLabel, MainUI mainApp) {
            coinsLabel.setText("Coins: " + mainApp.getCoins());
    }
}
