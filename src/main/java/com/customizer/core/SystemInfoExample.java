package com.customizer.core;

import oshi.SystemInfo;
import oshi.hardware.ComputerSystem;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.software.os.OperatingSystem;

import java.util.List;

public class SystemInfoExample {
    public static String displaySystemInfo() {
        SystemInfo systemInfo = new SystemInfo();

        // Информация об ОС
        OperatingSystem os = systemInfo.getOperatingSystem();
        StringBuilder info = new StringBuilder(); //
        info.append("Operating System: ").append(os).append("\n");

        // Информация о процессоре
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        info.append("\nCPU:\n  ").append(processor.getProcessorIdentifier().getName()).append("\n");
        info.append("  Architecture: ").append(processor.getProcessorIdentifier().getMicroarchitecture()).append("\n");

        // Информация об оперативной памяти
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        info.append("  Total RAM: ").append(memory.getTotal() / 1024 / 1024 / 1024 + 1  ).append(" GB\n");

        // Информация о материнской плате
        ComputerSystem computerSystem = systemInfo.getHardware().getComputerSystem();
        info.append("\nMotherboard:\n");
        info.append("  Manufacturer: ").append(computerSystem.getBaseboard().getManufacturer()).append("\n");
        info.append("  Model: ").append(computerSystem.getBaseboard().getModel()).append("\n");
        info.append("  Serial Number: ").append(computerSystem.getBaseboard().getSerialNumber()).append("\n");

        // Информация о видеокартах
        List<GraphicsCard> graphicsCards = systemInfo.getHardware().getGraphicsCards();
        for (GraphicsCard gpu : graphicsCards) {
            info.append("\nGPU:\n");
            info.append("  Model: ").append(gpu.getName()).append("\n");
            info.append("  Manufacturer: ").append(gpu.getVendor()).append("\n");
            info.append("  Driver Version: ").append(gpu.getVersionInfo()).append("\n");
            
            
        }
        return info.toString();
        }
}
