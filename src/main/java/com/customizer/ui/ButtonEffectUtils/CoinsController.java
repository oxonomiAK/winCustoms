package com.customizer.ui.ButtonEffectUtils;

public class CoinsController {

    private final CoinsManager coinsManager;

    public CoinsController() {
        this.coinsManager = new CoinsManager();
        coinsManager.loadCoinsFromFile(); 
    }

    public void saveCoins() {
        coinsManager.saveCoinsToFile();
    }

    public void addCoins(int amount) {
        coinsManager.addCoins(amount);
    }

    public int getCoins() {
        return coinsManager.getCoins();
    }
    
    public void spendCoins(int amount){
        coinsManager.spendCoins(amount);
    }
}