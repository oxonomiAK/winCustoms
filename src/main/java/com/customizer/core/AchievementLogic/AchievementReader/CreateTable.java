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

public class CreateTable {

    private BufferedWriter writer;
    private BufferedReader reader;

    private Process process;

    public CreateTable() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("C:/Users/kirill/Desktop/DBemulation/dbemulation.exe");

        process = processBuilder.start();
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

    }

    public void createTable() throws IOException {
        writer.write("CREATE ach id name req status xp\n");
        writer.flush();

        long startTime = System.currentTimeMillis();
        long timeout = 1000;

        String line;
        while (System.currentTimeMillis() - startTime < timeout) {
            if (reader.ready() && (line = reader.readLine()) != null) {
                System.out.println("OUTPUT: " + line);
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