package com.customizer.ui.ButtonEffectUtils;

import com.customizer.services.ReadFromJson;

import javafx.scene.control.Button;

public class ProfileNameController {
    public static void ProfileName(Button BtnProfile){
        
        if(ReadFromJson.ReadFromJSONString("Username").equals("null")){
            String username = System.getProperty("user.name");
            BtnProfile.setText(username); 
        }
        else{
            String username = ReadFromJson.ReadFromJSONString("Username");
            BtnProfile.setText(username);
            
        }
    }
}
