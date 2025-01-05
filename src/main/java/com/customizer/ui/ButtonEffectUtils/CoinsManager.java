package com.customizer.ui.ButtonEffectUtils;

import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;

public class CoinsManager {

    private int coins = 0;

    public void saveCoinsToFile() {
        WriteToJson.WriteToJSON("Coins", coins);
        System.out.println("Монеты успешно saved");
    }
    
    public void loadCoinsFromFile() {
        coins = ReadFromJson.ReadFromJSONint("Coins");
        System.out.println("Монеты успешно загружены: " + coins);
    }
    

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
        saveCoinsToFile();
        System.out.println("Монет добавлено: " + amount + ". Всего монет: " + coins);
    }
    
    public void spendCoins(int amount){
        coins -= amount;
        saveCoinsToFile();
    }
}