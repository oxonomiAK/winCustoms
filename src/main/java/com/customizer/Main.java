package com.customizer;

import java.io.IOException;

import com.customizer.core.FirstLaunchManager;
import com.customizer.core.AchievementLogic.AchievementReader.AchReqChecker;
import com.customizer.core.AchievementLogic.AchievementReader.AchievementChecker;
import com.customizer.core.AchievementLogic.AchievementReader.AchievementUnlocker;
import com.customizer.core.AchievementLogic.AchievementReader.CreateTable;
import com.customizer.core.AchievementLogic.AchievementReader.InsertAch;
import com.customizer.core.AchievementLogic.AchievementReader.XpParser;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        CreateTable crTable = new CreateTable();

        crTable.createTable();
        crTable.close();

        // InsertAch insAch = new InsertAch();
        // insAch.insertAchs();
        // insAch.close();

        XpParser xpparser = new XpParser();
        System.out.println(xpparser.getXp(7));
        xpparser.close();

        XpParser xpparserr = new XpParser();
        System.out.println(xpparserr.getXp(1));
        xpparser.close();

        AchReqChecker reqCh = new AchReqChecker();
        System.out.println(reqCh.isReqComplited(1));
        reqCh.close();

        AchReqChecker reqChe = new AchReqChecker();
        System.out.println(reqChe.isReqComplited(2));
        reqChe.close();

        AchievementChecker achCh = new AchievementChecker();
        System.out.println(achCh.isAchEarned(1));
        achCh.close();

        AchievementUnlocker achUnl = new AchievementUnlocker();
        achUnl.unlockAchievement(1);
        achUnl.close();

        AchievementChecker achChe = new AchievementChecker();
        System.out.println(achChe.isAchEarned(1));
        achChe.close();

    }
}
