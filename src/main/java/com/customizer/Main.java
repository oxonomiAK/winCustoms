package com.customizer;



import com.customizer.core.GameUtils.RecycleBinMonitor;
import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;
import com.customizer.ui.ButtonEffectUtils.CoinsController;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        if(ReadFromJson.ReadFromJSONBooleanT("FirstLaunch")){




            WriteToJson.WriteToJSON("FirstLaunch", false);
        }

        RecycleBinMonitor.StartMonitoring();
        CoinsController controller = new CoinsController();

        // Добавляем монеты
        controller.addCoins(10);

        // Сохраняем монеты
        controller.saveCoins();
    }
}
