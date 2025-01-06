package com.customizer.core.AchievementLogic.AchievementReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AchievementUnlocker {
    private BufferedWriter writer;
    private BufferedReader reader;

    private Process process;

    public AchievementUnlocker() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\kirill\\Desktop\\DBemulation\\dbemulation.exe");

        process = processBuilder.start();
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
    }

    public void unlockAchievement(int achievementId) throws IOException {
        writer.write("CHANGE_STATUS ach " + achievementId + " true" + "\n");
        writer.flush();

        String response = reader.readLine();

        if (response != null) {
            response = response.replaceAll("\"", "").trim();
            System.out.println("Server response: " + response);
        }
    }

    public void close() throws IOException {
        writer.write("EXIT\n");
        writer.flush();

        if (writer != null)
            writer.close();

        if (process != null)
            process.destroy();
    }
}
