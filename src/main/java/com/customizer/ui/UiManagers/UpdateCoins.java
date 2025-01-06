package com.customizer.ui.UiManagers;

import com.customizer.Main;

import javafx.scene.control.Label;

public class UpdateCoins {
    //Updating coins on display after changing
    public void updateCoinsDisplay(Label coinsLabel, Main mainApp) {
            coinsLabel.setText("Coins: " + mainApp.getCoins());
    }
}
