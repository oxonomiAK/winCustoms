package com.customizer.ui.ButtonEffectUtils;

public class CoinsController {

    private final CoinsManager coinsManager;

    public CoinsController() {
        this.coinsManager = new CoinsManager();
        coinsManager.loadCoins(); 
    }

    public void saveCoins() {
        coinsManager.saveCoins();
    }

    public void addCoins(int amount) {
        coinsManager.addCoins(amount);
    }

    public int getCoins() {
        return coinsManager.getCoins();
    }
    
}
