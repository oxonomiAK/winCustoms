package com.customizer.core;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.FloatByReference;

public interface CoreAudio extends Library {
    CoreAudio INSTANCE = Native.load("Ole32", CoreAudio.class);

    interface IAudioEndpointVolume {
        int SetMasterVolumeLevelScalar(float fLevel, String eventContext);

        int GetMasterVolumeLevelScalar(FloatByReference pfLevel);
    }
}

