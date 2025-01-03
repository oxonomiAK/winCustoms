package com.customizer.ui.ButtonEffectUtils;

import com.customizer.ui.UIControllers.ImageCropperController;
import com.customizer.ui.UIControllers.MainUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfilePicController {
    public static void CheckProfilePic(ImageView dynamicImageView1){
        if(!MainUI.FirstProfilePicChange)
            dynamicImageView1.setImage(new Image(ImageCropperController.UserProfilePic));
    }
}