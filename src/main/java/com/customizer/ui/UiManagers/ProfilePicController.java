package com.customizer.ui.UiManagers;

import com.customizer.Main;
import com.customizer.ui.UIControllers.ImageCropperController;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfilePicController {
    //Method to take profile picture from another location
    public static void CheckProfilePic(ImageView dynamicImageView1){
        if(!Main.FirstProfilePicChange)
            dynamicImageView1.setImage(new Image(ImageCropperController.UserProfilePic));
    }
}