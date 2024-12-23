package com.customizer.ui.ButtonEffectUtils;



import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

public class CoinsManager {
    private int coins;
    private final String COINS_FILE = "coins.json";

    public CoinsManager() {
        loadCoins();
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        if (amount > 0) {
            coins += amount;
            saveCoins();
        }
    }

    public boolean spendCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
            saveCoins();
            return true;
        }
        return false;
    }

    void saveCoins() {
        try (FileWriter writer = new FileWriter(COINS_FILE)) {
            JSONObject json = new JSONObject();
            json.put("coins", coins);
            writer.write(json.toString());
        } catch (IOException e) {
            System.err.println("Ошибка сохранения монет: " + e.getMessage());
        }
    }

    void loadCoins() {
        File file = new File(COINS_FILE);
        if (!file.exists()) {
            coins = 0;
            return;
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(COINS_FILE)));
            JSONObject json = new JSONObject(content);
            coins = json.optInt("coins", 0);
        } catch (IOException e) {
            System.err.println("Ошибка загрузки монет: " + e.getMessage());
            coins = 0;
        }
    }
}
