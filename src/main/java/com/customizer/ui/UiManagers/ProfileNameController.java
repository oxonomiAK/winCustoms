package com.customizer.ui.UiManagers;

import com.customizer.services.ReadFromJson;

import javafx.scene.control.Button;

public class ProfileNameController {
    public static void ProfileName(Button BtnProfile){
        //Getting current Windows Username and setting it into profile name in case that name have never been changed
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
