package com.customizer.core.AchievementLogic.AchievementReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*                              BOTH VLADS                  
 *                       DONT TOUCH FCNG CORE FILES
 *                                 !!!!
 */

public class AchReqChecker {

    private BufferedWriter writer;
    private BufferedReader reader;
    private Process process;

    public static int progressParser = 0;

    public AchReqChecker() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\kirill\\Desktop\\DBemulation\\dbemulation.exe");

        process = processBuilder.start();

        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

    }

    public boolean isReqComplited(int achievementId) throws IOException {
        writer.write("CHECK_REQ ach " + achievementId + "\n");
        writer.flush();
        String response = reader.readLine();

        if (response != null) {
            response = response.replaceAll("\"", "").trim();
        }
        int ans = Integer.parseInt(response);
        return progressParser >= ans;
    }

    public void close() throws IOException {
        writer.write("EXIT\n");
        writer.flush();

        if (writer != null)
            writer.close();
        if (reader != null)
            reader.close();

        if (process != null)
            process.destroy();
    }
}