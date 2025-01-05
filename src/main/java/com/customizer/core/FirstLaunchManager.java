package com.customizer.core;

import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;

public class FirstLaunchManager {
    public static void ifFisrtLaunch(){
        if(ReadFromJson.ReadFromJSONBooleanT("FirstLaunch")){

        WriteToJson.WriteToJSON("FirstLaunch", false);
        }
    }
}
