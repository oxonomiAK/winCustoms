package com.customizer.ui.UiManagers;

import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;


public class CoinsManager {



    UpdateCoins updateCoins = new UpdateCoins();
    private int coins = 0;
    //Coins saving to file
    public void saveCoinsToFile() {
        WriteToJson.WriteToJSON("Coins", coins);
    }
    //Reading coins from file
    public void loadCoinsFromFile() {
        coins = ReadFromJson.ReadFromJSONint("Coins");
    }
    
    //Method to get coins
    public int getCoins() {
        return ReadFromJson.ReadFromJSONint("Coins");
    }
    //Method to add coins
    public void addCoins(int amount) {
        coins += amount;
        saveCoinsToFile();
    }
    //Method to spend coins
    public void spendCoins(int amount){
        coins -= amount;
        saveCoinsToFile();
    }
}