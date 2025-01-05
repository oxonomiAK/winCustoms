package com.customizer.ui.ButtonEffectUtils;

public class CoinsController {
    // CoinsManager handles the actual coin data and operations.
    private final CoinsManager coinsManager;
    // Constructor initializes CoinsManager and loads coins from a file
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