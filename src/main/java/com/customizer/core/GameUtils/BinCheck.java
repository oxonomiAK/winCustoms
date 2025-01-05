package com.customizer.core.GameUtils;

import java.io.File;
import com.customizer.core.utils.GetSID;
public class BinCheck {
    

    
    public static void main(String[] args) {

        

        
        String recycleBinPath = System.getenv("SystemDrive") + "\\$Recycle.Bin\\"+GetSID.GetCurrentSID();
        
        File recycleBin = new File(recycleBinPath);
        System.out.println(recycleBinPath);
        if (recycleBin.exists() && recycleBin.isDirectory()) {
            boolean hasFiles = hasFilesInRecycleBin(recycleBin);
            System.out.println("Корзина " + (hasFiles ? "не пуста" : "пуста"));
        } else {
            System.out.println("Корзина не найдена или ОС не поддерживается.");
        }
    }



    private static boolean hasFilesInRecycleBin(File directory) {
        File[] directories = directory.listFiles();
        StringBuilder result = new StringBuilder();
        for (File file : directories) {
            result.append(file.getName()).append(", ");
        }
        System.out.println(result);
        if (directories != null) {
            return true;
        }
        return false;
    }



}
