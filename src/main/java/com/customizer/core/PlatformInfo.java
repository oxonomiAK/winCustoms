package com.customizer.core;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;

public class PlatformInfo {
    static SystemInfo si = new SystemInfo();
    static List<GraphicsCard> GPU = si.getHardware().getGraphicsCards();
        
        public static String GetCpuInfo(){
            CentralProcessor processor = si.getHardware().getProcessor();
            return processor.getProcessorIdentifier().getName();
        }
    
    public static String GetGpuInfo(){
            for(GraphicsCard graphicsCard : GPU) 
                return GpuInfo(graphicsCard);
                    return null;
    }



    public static String GpuInfo(GraphicsCard graphicsCard) {
        
        double memoryAsGB = graphicsCard.getVRam() / 1073741824;
        String string = "";
        
        string += " Graphics Card Name:" + graphicsCard.getName() + ", " + graphicsCard.getDeviceId() + "\n";
        string += " Graphics Card Vendor: " + graphicsCard.getVendor() + "\n";
        string += " Graphics Card VRAM: " + memoryAsGB + " GB\n";
        string += " Graphics Card Version Info: " + graphicsCard.getVersionInfo() + "\n";
                
        return string;
    }
}
