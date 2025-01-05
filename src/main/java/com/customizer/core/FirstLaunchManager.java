package com.customizer.core;

import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;

import java.io.IOException;

import com.customizer.core.AchievementLogic.AchievementReader.CreateTable;

public class FirstLaunchManager {
    public static void ifFisrtLaunch() throws IOException {
        if (ReadFromJson.ReadFromJSONBooleanT("FirstLaunch")) {

            WriteToJson.WriteToJSON("FirstLaunch", false);
        }
    }
}
