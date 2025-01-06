package com.customizer.core.AchievementLogic.AchievementBin;

import com.customizer.core.AchievementLogic.AchievementReader.*;
import java.io.*;

public class BinAchievement {

    // logic to call this function after every changes

    public BinAchievement() throws IOException {
        
        int achId = 1;

        AchievementChecker achChecker = new AchievementChecker();
        boolean status = achChecker.isAchEarned(achId);
        achChecker.close();

        boolean reqComplited;
        if (status == false)
        {
            AchReqChecker achReq = new AchReqChecker();
            //AchReqChecker.progressParser = ...;
            reqComplited = achReq.isReqComplited(achId);
            achReq.close();

            if(reqComplited)
            {
                AchievementUnlocker achUnlck = new AchievementUnlocker();
                achUnlck.unlockAchievement(achId);
                achUnlck.close();

                XpParser xp_parser = new XpParser();
                // double xp = xp_parser.getXp(achId);

                // logic to convert xp to lvl
                //...........................
                //...........................

                xp_parser.close();
            }

        }

    }

}
