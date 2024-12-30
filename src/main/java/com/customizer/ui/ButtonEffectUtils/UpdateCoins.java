package com.customizer.ui.ButtonEffectUtils;

import com.customizer.ui.UIControllers.MainUI;

import javafx.scene.control.Label;

public class UpdateCoins {
    public void updateCoinsDisplay(Label coinsLabel, MainUI mainApp) {
        if (coinsLabel != null && mainApp != null) {
            coinsLabel.setText("Coins: " + mainApp.getCoins());
        } else {
            System.err.println("coinsLabel или mainApp не инициализирован!");
        }
    }
}
