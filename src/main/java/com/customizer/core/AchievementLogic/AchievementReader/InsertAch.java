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

public class InsertAch {

    private BufferedWriter writer;
    private BufferedReader reader;

    private Process process;

    public InsertAch() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("C:/Users/kirill/Desktop/DBemulation/dbemulation.exe");

        process = processBuilder.start();
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

    }

    public void insertAchs() throws IOException, InterruptedException {
        String[] commands = {
                "INSERT ach 1 RecycleBin 50 false 1.0",
                "INSERT ach 2 WallpaperChanged 1 false 0.2",
                "INSERT ach 3 WallpaperChanged 10 false 0.8",
                "INSERT ach 4 WallpaperChanged 16 false 1.0",
                "INSERT ach 5 IconSizeChange 1 false 0.3",
                "INSERT ach 6 IconSizeDefault 1 false 0.2",
                "INSERT ach 7 IconSizeMax 1 false 0.7",
                "INSERT ach 8 IconSizeMin 1 false 0.7",
                "INSERT ach 9 UnlockAllWallpapers 16 false 1.0",
                "INSERT ach 10 GetMaxLvl 10 false 0.0",
                "INSERT ach 11 ChangePerformances 1 false 0.5",
                "INSERT ach 12 ChangeYourProfile 1 false 0.6"
        };

        for (String command : commands) {
            long startTime = System.currentTimeMillis();
            long timeout = 100;

            writer.write(command + "\n");
            writer.flush();

            while (System.currentTimeMillis() - startTime < timeout) {
                if (reader.ready()) {
                    String line = reader.readLine();
                    System.out.println("OUTPUT: " + line);

                }
            }
        }
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