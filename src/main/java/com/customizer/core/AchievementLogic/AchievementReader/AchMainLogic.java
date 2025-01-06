package com.customizer.core.AchievementLogic.AchievementReader;

public class AchMainLogic {

    public AchMainLogic(int achievementId, int data) throws Exception {
        int achId = achievementId;

        AchievementChecker achChecker = new AchievementChecker();
        boolean status = achChecker.isAchEarned(achId);
        achChecker.close();

        boolean reqComplited;
        if (status == false) {
            AchReqChecker achReq = new AchReqChecker();
            AchReqChecker.progressParser = data;
            reqComplited = achReq.isReqComplited(achId);
            achReq.close();

            if (reqComplited) {
                AchievementUnlocker achUnlck = new AchievementUnlocker();
                achUnlck.unlockAchievement(achId);
                achUnlck.close();

                XpParser xp_parser = new XpParser();
                double xp = xp_parser.getXp(achId);

                // logic to convert xp to lvl
                // ...........................
                // ...........................

                xp_parser.close();
            }

        }

    }

}
