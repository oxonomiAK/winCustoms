package com.customizer.core.AchievementLogic.AchievementReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AchievementChecker {

    private BufferedWriter writer;
    private BufferedReader reader;
    private Process process;

    public AchievementChecker() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\kirill\\Desktop\\DBemulation\\dbemulation.exe");

        process = processBuilder.start();

        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

    }

    public boolean isAchEarned(int achievementId) throws IOException {
        writer.write("CHECK_STATUS ach " + achievementId + "\n");
        writer.flush();

        String response = reader.readLine();
        return "true".equals(response);
    }

    public void close() throws IOException {

        if (writer != null)
            writer.close();
        if (reader != null)
            reader.close();

        if (process != null)
            process.destroy();
    }
}
