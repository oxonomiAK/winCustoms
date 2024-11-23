package com.customizer.core;

public class PlatformVersion {

    public static String osName;
    public static String osVersion;
    public static String osArch;

    public static void WindowsVersion() {
        osName = System.getProperty("os.name");
        osVersion = System.getProperty("os.version");
        osArch = System.getProperty("os.arch");
    }

    public static void WindowsVersionInfo() {
        System.out.println("os name: " + osName);
        System.out.println("version: " + osVersion);
        System.out.println("architecture: " + osArch);
    }
}
