package com.customizer.core.GameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.customizer.core.utils.GetSID;
import com.customizer.services.ReadFromJson;
import com.customizer.services.WriteToJson;

public class RecycleBinMonitor {

    public static void StartMonitoring() throws InterruptedException {
        // Get SID of current user
        String currentSID = GetSID.GetCurrentSID();

        // Define path to recycle bin of current user
        String recycleBinPath = System.getenv("SystemDrive") + "\\$Recycle.Bin\\" + currentSID;
        File recycleBin = new File(recycleBinPath);

        //Starting recycle bin monitoring
        monitorRecycleBin(recycleBin);
    }


    // Method for recycle bin monitoring
    public static void monitorRecycleBin(File recycleBin) throws InterruptedException {
        //Define class path with path of recycle bin for further methods
        Path path = recycleBin.toPath();

        try {
            //Variable for counting deleted files
            int DeletedFileCount = ReadFromJson.ReadFromJSONint("DeletedFileCount");
            //Creating new object watchService for further monitoring
            WatchService watchService = FileSystems.getDefault().newWatchService();
            //Define that we need to monitor changes in directory "path" and watch for deletions only
            path.register(watchService, StandardWatchEventKinds.ENTRY_DELETE);
            //Start cycle for monitoring
            while (!(DeletedFileCount >= 100)) {
                //Event listener
                WatchKey key = watchService.take();
                //Little pause to let program take more than 1 event
                Thread.sleep(100);

                //Watch only files that starts with "$R"
                Pattern pattern = Pattern.compile("^\\$R.*", Pattern.CASE_INSENSITIVE);
                   
                //Handle events
                for (WatchEvent<?> event : key.pollEvents()) {
                        Matcher matcher = pattern.matcher(event.context().toString());
                        if(matcher.matches()) 
                            ++DeletedFileCount;
                } 
                
                //Writing to .json file how many files has been deleted
                WriteToJson.WriteToJSON("DeletedFileCount", DeletedFileCount);
                //reset listener for taking new events
                key.reset();
            }

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


