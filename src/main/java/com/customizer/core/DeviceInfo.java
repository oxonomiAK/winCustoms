package com.customizer.core;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
//import oshi.hardware.SoundCard;
import oshi.hardware.UsbDevice;

public class DeviceInfo {

    public static String getMicrophoneInfo() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        StringBuilder info = new StringBuilder();
        

        info.append("Microphones:\n");
        for (UsbDevice device : hardware.getUsbDevices(false)) {
            if (device.getName().toLowerCase().contains("microphone")) {
                info.append("  Device Name: ").append(device.getName()).append("\n");
                info.append("\n");
            }
        }

        // Сканируем устройства через Java Sound API
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for (Mixer.Info mixer : mixers) {
            if (mixer.getName().toLowerCase().contains("microphone")) {
                info.append("  Audio Mixer: ").append(mixer.getName()).append("\n");
            }
        }

        return info.length() > 12 ? info.toString() : "No microphones found.\n"; // 12 - "Microphones:\n" length
    }

    public static String getHeadphonesInfo() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        StringBuilder info = new StringBuilder();

        info.append("Headphones:\n");
        for (UsbDevice device : hardware.getUsbDevices(false)) {
            if (device.getName().toLowerCase().contains("headphone") || device.getName().toLowerCase().contains("audio")) {
                info.append("  Device Name: ").append(device.getName()).append("\n");
                info.append("\n");
            }
        }

        // Сканируем устройства через Java Sound API
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for (Mixer.Info mixer : mixers) {
            if (mixer.getName().toLowerCase().contains("headphone") || mixer.getName().toLowerCase().contains("audio")) {
                info.append("  Audio Mixer: ").append(mixer.getName()).append("\n");
            }
        }

        return info.length() > 11 ? info.toString() : "No headphones found.\n"; // 11 - "Headphones:\n" length
    }
   
    public static void main(String[] args) {
        System.out.println(getMicrophoneInfo());
        System.out.println(getHeadphonesInfo());
    }
}