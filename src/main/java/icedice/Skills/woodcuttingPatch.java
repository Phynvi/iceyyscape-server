package icedice.Skills;

import icedice.*;
import icedice.players.*;
import icedice.util.*;

public class woodcuttingPatch {

    public int WCType = 0;

    public int getAxeLvl(Player p) {
        int axe = p.equipment[3];
        switch (axe) {
            case 1351://Bronze Axe
                return 0;

            case 1349://Iron Axe
                return 0;

            case 1353://Steel Axe
                return 5;

            case 1361://Black Axe
                return 10;

            case 1355://Mithril Axe
                return 21;

            case 1357://Adamant Axe
                return 31;

            case 1359://Rune Axe
                return 41;


        }
        return -1;
    }

    public boolean hasAxe(Player p) {
        int axe = p.equipment[3];
        switch (axe) {
            case 1351://Bronze Axe
                return true;

            case 1349://Iron Axe
                return true;

            case 1353://Steel Axe
                return true;

            case 1361://Black Axe
                return true;

            case 1355://Mithril Axe
                return true;

            case 1357://Adamant Axe
                return true;

            case 1359://Rune Axe
                return true;

            case 6739://Dragon Axe
                return true;

        }
        return false;
    }


    public int reqLevels(Player p) {
        switch (WCType) {
            case 1:
                return 1;
            case 2:
                return 15;
            case 3:
                return 30;
            case 4:
                return 60;
            case 5:
                return 75;
        }
        return 1;
    }

    public boolean canUseAxe(Player p) {
        int wcLvl = p.skillLvl[8];
        if (wcLvl >= getAxeLvl(p)) {
            return true;
        }
        if (wcLvl < getAxeLvl(p)) {
            p.frames.sendMessage(p, "You need a woodcutting level of " + getAxeLvl(p) + " to use this axe.");
            return false;
        }
        return false;
    }

    public boolean hasLvlForWood(Player p) {
        if (p.skillLvl[8] >= reqLevels(p)) {
            return true;
        }
        if (p.skillLvl[8] < reqLevels(p)) {
            p.frames.sendMessage(p, "You need a woodcutting level of " + reqLevels(p) + " to cut this tree.");
            return false;
        }
        return false;
    }

    public void startWC(Player p) {
        if (!hasAxe(p)) {
            p.frames.sendMessage(p, "You need an axe to wood cut.");
        }
        if (hasAxe(p)) {
            if (canUseAxe(p)) {
                if (hasLvlForWood(p)) {
                    if (WCType == 1) {
                        p.magic = false;
                        p.yew = false;
                        p.willow = false;
                        p.oak = false;
                        p.reg = true;
                    }
                    if (WCType == 2) {
                        p.magic = false;
                        p.yew = false;
                        p.willow = false;
                        p.oak = true;
                        p.reg = false;
                    }
                    if (WCType == 3) {
                        p.magic = false;
                        p.yew = false;
                        p.willow = true;
                        p.oak = false;
                        p.reg = false;
                    }
                    if (WCType == 4) {
                        p.magic = false;
                        p.yew = true;
                        p.willow = false;
                        p.oak = false;
                        p.reg = false;
                    }
                    if (WCType == 5) {
                        p.magic = true;
                        p.yew = false;
                        p.willow = false;
                        p.oak = false;
                        p.reg = false;
                    }
                }
                p.WCTimer = wcTime(p, WCType);
                System.out.println("Time: " + p.WCTimer);
            }
        }
    }

    public void switchTree(Player p, int objId) {
        int[] magic = {1306};
        int[] yew = {1309};
        int[] willow = {1308};
        int[] oak = {1281};
        int[] reg = {1276, 1277, 1278, 1279, 1280};
        for (int i = 0; i < magic.length; i++) {
            if (objId == magic[i]) {
                WCType = 5;
            }
        }
        for (int i2 = 0; i2 < yew.length; i2++) {
            if (objId == yew[i2]) {
                WCType = 4;
            }
        }
        for (int i3 = 0; i3 < willow.length; i3++) {
            if (objId == willow[i3]) {
                WCType = 3;
            }
        }
        for (int i4 = 0; i4 < oak.length; i4++) {
            if (objId == oak[i4]) {
                WCType = 2;
            }
        }
        for (int i5 = 0; i5 < reg.length; i5++) {
            if (objId == reg[i5]) {
                WCType = 1;
            }
        }
        p.frames.sendMessage(p, "You swing you're axe at the tree.");
        startWC(p);
    }

    public boolean timerIsOver(Player p) {
        if (p.WCTimer == 0) {
            return true;
        }
        return false;
    }


    public int getAxeTime(Player p) {
        int axe = p.equipment[3];
        int time = -1;
        switch (axe) {
            case 1351: // Bronze
                time = 2;
                return time;
            case 1349: // Iron
                time = 3;
            case 1353: // Steel
                time = 4;
                return time;
            case 1355: // Mith
                time = 5;
                return time;
            case 1357: // Addy
                time = 7;
                return time;
            case 1359: // Rune
                time = 10;
                return time;
        }
        return time;
    }

    public int wcTime(Player p, int logType) { // 1 normal, 2 oak, 3 willow, 4 yew, 5 magic
        int time = 0;
        int treeTime = 0;
        int wcLvl = p.skillLvl[8];
        int newTime = -1;
        boolean debugReq = false;
        switch (logType) {
            case 1: // Regular Tree
                treeTime = 20;
                time = treeTime - wcLvl - Misc.random(getAxeTime(p)) + Misc.random(3);
                if (time < 0) {
                    newTime = 0 + Misc.random(5);
                } else {
                    newTime = time;
                }
                return newTime;
            case 2: // Oak Tree
                treeTime = 30;
                time = treeTime - wcLvl - -Misc.random(getAxeTime(p));
                if (time < 0) {
                    newTime = 0 + Misc.random(5);
                } else {
                    newTime = time;
                }
                return newTime;
            case 3: // Willow Tree
                treeTime = 60;
                time = treeTime - wcLvl - -Misc.random(getAxeTime(p));
                if (time < 0) {
                    newTime = 0 + Misc.random(5);
                } else {
                    newTime = time;
                }
                return newTime;
            case 4: // Yew Tree
                treeTime = 120;
                time = treeTime - wcLvl - Misc.random(getAxeTime(p));
                if (time < 0) {
                    newTime = 0 + Misc.random(5);
                } else {
                    newTime = time;
                }
                return newTime;
            case 5: // Magic Tree
                treeTime = 150;
                time = treeTime - wcLvl - Misc.random(getAxeTime(p));
                if (time < 0) {
                    newTime = 0 + Misc.random(5);
                } else {
                    newTime = time;
                }
                return newTime;
        }
        return newTime;
    }

    public void checkWC(Player player) {
        if (player.isWoodcutting()) {
            if (hasAxe(player)) {
                if (timerIsOver(player)) {
                    if (player.magic) {
                        Engine.playerItems.addItem(player, 1513, 1);
                        player.frames.sendMessage(player, "You cut some Magic logs.");
                        player.addSkillXP(231, 8);
                        startWC(player);
                    }
                    if (player.yew) {
                        Engine.playerItems.addItem(player, 1515, 1);
                        player.frames.sendMessage(player, "You cut some Yew logs.");
                        player.addSkillXP(175, 8);
                        startWC(player);
                    }
                    if (player.willow) {
                        Engine.playerItems.addItem(player, 1519, 1);
                        player.frames.sendMessage(player, "You cut some Willow logs.");
                        player.addSkillXP(64, 8);
                        startWC(player);
                    }
                    if (player.oak) {
                        Engine.playerItems.addItem(player, 1521, 1);
                        player.frames.sendMessage(player, "You cut some Oak logs.");
                        player.addSkillXP(23, 8);
                        startWC(player);
                    }
                    if (player.reg) {
                        Engine.playerItems.addItem(player, 1511, 1);
                        player.frames.sendMessage(player, "You cut some logs.");
                        player.addSkillXP(11, 8);
                        startWC(player);
                    }
                }
            }
        }
    }

    public void cancelWC(Player p) {
        p.magic = false;
        p.yew = false;
        p.willow = false;
        p.oak = false;
        p.reg = false;
    }

    public int getAnim(Player p) {
        int anim = -1;
        int axe = p.equipment[3];
        switch (axe) {
            case 1351://Bronze Axe
                anim = 879;
                break;
            case 1349://Iron Axe
                anim = 877;
                break;
            case 1353://Steel Axe
                anim = 875;
                break;
            case 1361://Black Axe
                anim = 873;
                break;
            case 1355://Mithril Axe
                anim = 871;
                break;
            case 1357://Adamant Axe
                anim = 869;
                break;
            case 1359://Rune Axe
                anim = 867;
                break;
            case 6739://Dragon Axe
                anim = 2846;
                break;
        }
        return anim;
    }

}