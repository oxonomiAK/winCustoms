package com.customizer.core;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.UsbDevice;

public class DeviceInfo {

    public static String getMicrophoneInfo() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware(); //Obtain information about the hardware
        StringBuilder info = new StringBuilder();

        //Searches for a device with a specific name
        info.append("Microphones:\n");
        for (UsbDevice device : hardware.getUsbDevices(false)) { //Searching all USB devices
            if (device.getName().toLowerCase().contains("microphone")) { //Microphone filtering
                info.append("  Device Name: ").append(device.getName()).append("\n");
                info.append("\n");
            }
        }

        // Scanning devices through Java Sound API
        Mixer.Info[] mixers = AudioSystem.getMixerInfo(); //Getting a list of audio mixers
        for (Mixer.Info mixer : mixers) {
            if (mixer.getName().toLowerCase().contains("microphone")) { //Filtering mixers with microphones
                info.append("  Audio Mixer: ").append(mixer.getName()).append("\n");
            }
        }

        return info.length() > 12 ? info.toString() : "No microphones found.\n"; //Result return
    }

    public static String getHeadphonesInfo() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        StringBuilder info = new StringBuilder();

        //Searches for a device with a specific name
        info.append("Headphones:\n");
        for (UsbDevice device : hardware.getUsbDevices(false)) {
            if (device.getName().toLowerCase().contains("headphone") || device.getName().toLowerCase().contains("audio")) {
                info.append("  Device Name: ").append(device.getName()).append("\n");
                info.append("\n");
            }
        }

        // Scanning devices through Java Sound API
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for (Mixer.Info mixer : mixers) {
            if (mixer.getName().toLowerCase().contains("headphone") || mixer.getName().toLowerCase().contains("audio")) {
                info.append("  Audio Mixer: ").append(mixer.getName()).append("\n");
            }
        }

        return info.length() > 11 ? info.toString() : "No headphones found.\n";
    }
}