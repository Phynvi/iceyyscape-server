package icedice;

import java.io.*;
import java.util.Properties;

public class Config {

    boolean limitips = true;

    /**
     * Bunos XP
     * Never set to '0' there will be NO XP!
     */
    public static double bonusXP = 2;

    /**
     * Combat EXP Rates
     */
    public static int combatXP = 170;

    /**
     * BONES
     * All bone xp raters go here
     */
    public static int regbone = 75;
    public static int burntbone = 50;
    public static int batbone = 50;
    public static int wolfbone = 50;
    public static int monkeybone = 50;
    public static int bigbone = 500;
    public static int shaikbone = 500;
    public static int jogrebone = 500;
    public static int burntjogrebone = 500;
    public static int babydragonbone = 450;
    public static int dragonbone = 600;
    public static int zogrebone = 750;
    public static int fayrgbone = 750;
    public static int raurgbone = 750;
    public static int ourgbone = 750;
    public static int dagbone = 1000;
    public static int wyvbone = 1000;

    /**
     * CRAFTING
     * All gems and necklace xp rates go here
     */
    public static int sapphire = 125;
    public static int emerald = 172;
    public static int ruby = 250;
    public static int diamond = 350;
    public static int dragstone = 450;
    public static int onyx = 550;

    /**
     * COMBAT XP RATES
     * All combat xp rates go here
     */
    public static int Prayer_XP = 75;
    public static int Summoning_XP = 1000;

    /**
     * SKILL XP RATES
     * All non combat xp rates go here
     */
    public static int Herblore_XP = 600;
    public static int Crafting_XP = 100;
    public static int Feltching_XP = 150;
    public static int Smithing_XP = 100;

    /**
     * COORDNATES
     * Coords for all important places here
     */

    public static int homeX = 2945;
    public static int homeY = 3370;

    public static int hShopsX = 2963;
    public static int hShopsY = 3378;

    /** Npc Generic Drops
     * Generic drop lists go here
     */

    /**
     * Low level npcs lvl 1-65
     */
    public static int lowFood[] = {315, 319, 329, 333, 351};

    public static int lowFood() {
        return lowFood[(int) (Math.random() * lowFood.length)];
    }

    public static int lowItems[] = {1277, 1279, 1281, 1283, 1285, 1291, 1293, 1295, 1297, 1299, 1307, 1309, 1311, 1313, 1315,
            1321, 1323, 1325, 1327, 1329, 1379, 1381, 1725, 1727, 1729, 1731, 1718, 2631, 1067, 1069, 1071, 1075, 1077, 1027, 1029,
            1025, 839, 841, 843, 845, 847, 849, 1115, 1117, 1119, 1121, 1125, 1129, 1131, 1133, 1153, 1155, 1157, 1159, 1165, 1167,
            1169, 1189, 1191, 1193, 1195, 1197, 1506};

    public static int lowItems() {
        return lowItems[(int) (Math.random() * lowItems.length)];
    }

    public static int lowMulti[] = {554, 555, 556, 557, 558, 559, 562, 882, 884, 886, 888};

    public static int lowMulti() {
        return lowMulti[(int) (Math.random() * lowMulti.length)];
    }

    public static String getPropertiesValue(String key) {
        Properties prop = new Properties();
        InputStream inputStream = null;
        String propertyValue = null;
        try {
            inputStream = Config.class.getClassLoader().getResourceAsStream("IceyyScape.properties");
            prop.load(inputStream);
            propertyValue = prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }
}