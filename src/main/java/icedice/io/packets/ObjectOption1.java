package icedice.io.packets;

import icedice.Engine;
import icedice.util.Misc;
import icedice.Skills.*;
import icedice.content.minigames.*;
import icedice.Config;
import icedice.players.Player;

public class ObjectOption1 implements Packet {

    ClanWars clanWars = new ClanWars();

    /**
     * make sure to document EVERY coordinate to go with each object unless an
     * un-important object(wilderness ditch lol). This will prevent people from
     * spawning an object client side and actually using it. So make sure to
     * include with the id, objectX == # && objectY == #
     */

    // party room items
    public static int Multi[] = {78, 995, 882, 884, 886, 888, 890, 892, 1060,
            1062, 380, 387, 384, 390, 392, 362, 352, 350, 334, 437, 439, 441,
            443, 445, 448, 450, 452, 527, 543, 545, 554, 555, 556, 557, 558,
            559, 563, 560, 561, 562, 564, 565, 566, 9075, 114};

    public static int randomMulti() {
        return Multi[(int) (Math.random() * Multi.length)];
    }

    public static int ArmourDrop[] = {74, 75, 626, 627, 629, 1067, 1069, 1071,
            1073, 1075, 1077, 1079, 1081, 1083, 1085, 1087, 1089, 1091, 1093,
            1095, 1097, 1099, 1011, 1103, 1105, 1107, 1109, 1111, 1113, 1115,
            1117, 1119, 1121, 1123, 1125, 1127, 1129, 1131, 1133, 1135, 1137,
            1139, 1141, 1143, 1145, 1147, 1149, 1151, 1153, 1155, 1157, 1159,
            1161, 1163, 1165, 1167, 1169, 1171, 1173, 1175, 1177, 1179, 1181,
            1183, 1185, 1187, 1189, 1191, 1193, 1195, 1197, 1199, 1201, 2904,
            2906, 2908, 2910, 1540, 4224, 4225, 4119, 4121, 4123, 4125, 4127,
            4129, 4131, 11732};

    public static int randomArmourDrop() {
        return ArmourDrop[(int) (Math.random() * ArmourDrop.length)];
    }

    public static int WepDrop[] = {746, 747, 837, 839, 841, 843, 845, 847,
            849, 851, 853, 855, 857, 859, 861, 1203, 1205, 1207, 1209, 1211,
            1213, 1215, 1217, 1237, 1239, 1241, 1243, 1245, 1247, 1249, 1277,
            1279, 1281, 1283, 1285, 1287, 1289, 1291, 1293, 1295, 1297, 1299,
            1301, 1303, 1305, 1307, 1309, 1311, 1313, 1315, 1317, 1319, 1321,
            1323, 1325, 1327, 1329, 1331, 1333, 1335, 1337, 1339, 1341, 1343,
            1345, 1347, 1363, 1365, 1367, 1369, 1371, 1373, 1375, 1377, 1379,
            1381, 1383, 1385, 1387, 1389, 1391, 1393, 1395, 1397, 1399, 1401,
            1403, 1405, 1407, 1419, 1420, 1422, 1424, 1426, 1428, 1430, 1434,
            4151, 4153, 4214, 4215, 9174, 9177, 9179, 9181, 9183, 9185, 13636,
            10840};

    public static int randomWepDrop() {
        return WepDrop[(int) (Math.random() * WepDrop.length)];
    }

    public static int RobeDrop[] = {538, 540, 542, 544, 546, 548, 577, 579,
            581, 1033, 1035, 1025, 1027, 1029, 1031, 1033, 1035, 1833, 1835,
            1837, 1844, 1845, 1846, 4089, 4091, 4093, 4095, 4097, 4099, 7398,
            7399, 7400, 10836, 10837, 10838, 10839};

    public static int randomRobeDrop() {
        return RobeDrop[(int) (Math.random() * RobeDrop.length)];
    }

    public static int tDrop[] = {2583, 2585, 2578, 2589, 2591, 2593, 2595,
            2597, 2589, 2591, 2593, 2595, 2597, 2599, 2601, 2603, 2605, 2607,
            2609, 2611, 2613, 2615, 2617, 2619, 2621, 2623, 2625, 2627, 2629,
            2653, 2655, 2657, 2659, 2661, 2663, 2665, 2667, 2669, 2671, 2673,
            2675, 3472, 3474, 3476, 3477, 3478, 3480, 3481, 3483, 3485, 3486,
            3488, 7332, 7334, 7336, 7338, 7340, 7342, 7344, 7346, 7348, 7350,
            7352, 7354, 7356, 7358, 7360, 7362, 7364, 7366, 7368, 7370, 7372,
            7374, 7376, 7378, 7380, 7382, 7384, 7386, 7388, 7390, 7392, 7394,
            7396};

    public static int randomtDrop() {
        return tDrop[(int) (Math.random() * tDrop.length)];
    }

    public static int cDrop[] = {6611, 11690, 11718, 11720, 11722, 11724,
            11726, 11728, 11756, 11757, 11758, 11789, 10551, 10547, 10548,
            10549, 10550, 10552, 10589, 10719, 10714, 10715, 10716, 10717,
            10718, 10720, 10364, 10366, 10392, 10368, 10370, 10372, 10374,
            10376, 10378, 10380, 10382, 10384, 10386, 10388, 10390, 10440,
            10442, 10444, 10446, 10440, 10442, 10444, 10446, 10448, 10450,
            10452, 10454, 10456, 10458, 10460, 10462, 10464, 10466, 10468,
            10470, 10472, 10474, 6585, 6733, 6735, 6737};

    public static int randomcDrop() {
        return cDrop[(int) (Math.random() * cDrop.length)];
    }

    public static int sDrop[] = {1265, 1267, 1269, 1271, 1273, 1275, 1349,
            1351, 1353, 1355, 1357, 1359, 1361, 10010, 5096, 5098, 12047,
            12043, 12786, 12788, 12790};

    public static int randomsDrop() {
        return sDrop[(int) (Math.random() * sDrop.length)];
    }

    public static int dtItem[] = {4278, 386, 385, 161, 163, 169, 2446, 2447};

    public static int randomdtItem() {
        return dtItem[(int) (Math.random() * dtItem.length)];
    }

    public static int capes[] = {1052, 6570, 9747, 9748, 9750, 9751};

    public static boolean cape(Player p) {

        if (Engine.playerItems.HasItemAmount(p, 1052, 1)
                || Engine.playerItems.HasItemAmount(p, 6570, 1)
                || Engine.playerItems.HasItemAmount(p, 6568, 1)
                || Engine.playerItems.HasItemAmount(p, 9747, 1)
                || Engine.playerItems.HasItemAmount(p, 9748, 1)
                || Engine.playerItems.HasItemAmount(p, 9750, 1)
                || Engine.playerItems.HasItemAmount(p, 9751, 1)
                || Engine.playerItems.HasItemAmount(p, 9753, 1)
                || Engine.playerItems.HasItemAmount(p, 9754, 1)
                || Engine.playerItems.HasItemAmount(p, 9756, 1)
                || Engine.playerItems.HasItemAmount(p, 9757, 1)
                || Engine.playerItems.HasItemAmount(p, 9759, 1)
                || Engine.playerItems.HasItemAmount(p, 9760, 1)
                || Engine.playerItems.HasItemAmount(p, 9762, 1)
                || Engine.playerItems.HasItemAmount(p, 9763, 1)
                || Engine.playerItems.HasItemAmount(p, 9765, 1)
                || Engine.playerItems.HasItemAmount(p, 9766, 1)
                || Engine.playerItems.HasItemAmount(p, 9767, 1)
                || Engine.playerItems.HasItemAmount(p, 9769, 1)
                || Engine.playerItems.HasItemAmount(p, 9771, 1)
                || Engine.playerItems.HasItemAmount(p, 9772, 1)
                || Engine.playerItems.HasItemAmount(p, 9774, 1)
                || Engine.playerItems.HasItemAmount(p, 9775, 1)
                || Engine.playerItems.HasItemAmount(p, 9777, 1)
                || Engine.playerItems.HasItemAmount(p, 9778, 1)
                || Engine.playerItems.HasItemAmount(p, 9780, 1)
                || Engine.playerItems.HasItemAmount(p, 9781, 1)
                || Engine.playerItems.HasItemAmount(p, 9783, 1)
                || Engine.playerItems.HasItemAmount(p, 9784, 1)
                || Engine.playerItems.HasItemAmount(p, 9786, 1)
                || Engine.playerItems.HasItemAmount(p, 9787, 1)
                || Engine.playerItems.HasItemAmount(p, 9789, 1)
                || Engine.playerItems.HasItemAmount(p, 9790, 1)
                || Engine.playerItems.HasItemAmount(p, 9792, 1)
                || Engine.playerItems.HasItemAmount(p, 9793, 1)
                || Engine.playerItems.HasItemAmount(p, 9795, 1)
                || Engine.playerItems.HasItemAmount(p, 9796, 1)
                || Engine.playerItems.HasItemAmount(p, 9798, 1)
                || Engine.playerItems.HasItemAmount(p, 9799, 1)
                || Engine.playerItems.HasItemAmount(p, 9801, 1)
                || Engine.playerItems.HasItemAmount(p, 9802, 1)
                || Engine.playerItems.HasItemAmount(p, 9804, 1)
                || Engine.playerItems.HasItemAmount(p, 9805, 1)
                || Engine.playerItems.HasItemAmount(p, 9807, 1)
                || Engine.playerItems.HasItemAmount(p, 9808, 1)
                || Engine.playerItems.HasItemAmount(p, 9810, 1)
                || Engine.playerItems.HasItemAmount(p, 9811, 1)
                || Engine.playerItems.HasItemAmount(p, 9813, 1)

                || Engine.playerItems.HasItemAmount(p, 1038, 1)
                || Engine.playerItems.HasItemAmount(p, 1040, 1)
                || Engine.playerItems.HasItemAmount(p, 1042, 1)
                || Engine.playerItems.HasItemAmount(p, 1044, 1)
                || Engine.playerItems.HasItemAmount(p, 1046, 1)
                || Engine.playerItems.HasItemAmount(p, 1048, 1)
                || Engine.playerItems.HasItemAmount(p, 1050, 1)
                || Engine.playerItems.HasItemAmount(p, 1053, 1)
                || Engine.playerItems.HasItemAmount(p, 1055, 1)
                || Engine.playerItems.HasItemAmount(p, 1057, 1)
                || Engine.playerItems.HasItemAmount(p, 1149, 1)
                || Engine.playerItems.HasItemAmount(p, 1155, 1)
                || Engine.playerItems.HasItemAmount(p, 1157, 1)
                || Engine.playerItems.HasItemAmount(p, 1159, 1)
                || Engine.playerItems.HasItemAmount(p, 1161, 1)
                || Engine.playerItems.HasItemAmount(p, 1163, 1)
                || Engine.playerItems.HasItemAmount(p, 1165, 1)
                || Engine.playerItems.HasItemAmount(p, 4716, 1)
                || Engine.playerItems.HasItemAmount(p, 4708, 1)
                || Engine.playerItems.HasItemAmount(p, 4724, 1)
                || Engine.playerItems.HasItemAmount(p, 4732, 1)
                || Engine.playerItems.HasItemAmount(p, 4745, 1)
                || Engine.playerItems.HasItemAmount(p, 4753, 1)
                || Engine.playerItems.HasItemAmount(p, 11335, 1)
                || Engine.playerItems.HasItemAmount(p, 10828, 1)
                || Engine.playerItems.HasItemAmount(p, 10334, 1)
                || Engine.playerItems.HasItemAmount(p, 10342, 1)
                || Engine.playerItems.HasItemAmount(p, 10350, 1)
                || Engine.playerItems.HasItemAmount(p, 2639, 1)
                || Engine.playerItems.HasItemAmount(p, 2641, 1)
                || Engine.playerItems.HasItemAmount(p, 2743, 1)
                || Engine.playerItems.HasItemAmount(p, 2581, 1)
                || Engine.playerItems.HasItemAmount(p, 12210, 1)
                || Engine.playerItems.HasItemAmount(p, 12213, 1)
                || Engine.playerItems.HasItemAmount(p, 12216, 1)
                || Engine.playerItems.HasItemAmount(p, 12219, 1)
                || Engine.playerItems.HasItemAmount(p, 12222, 1)
                || Engine.playerItems.HasItemAmount(p, 9749, 1)
                || Engine.playerItems.HasItemAmount(p, 9752, 1)
                || Engine.playerItems.HasItemAmount(p, 9755, 1)
                || Engine.playerItems.HasItemAmount(p, 9758, 1)
                || Engine.playerItems.HasItemAmount(p, 9761, 1)
                || Engine.playerItems.HasItemAmount(p, 9764, 1)
                || Engine.playerItems.HasItemAmount(p, 9767, 1)
                || Engine.playerItems.HasItemAmount(p, 9770, 1)
                || Engine.playerItems.HasItemAmount(p, 9773, 1)
                || Engine.playerItems.HasItemAmount(p, 9776, 1)
                || Engine.playerItems.HasItemAmount(p, 9779, 1)
                || Engine.playerItems.HasItemAmount(p, 9782, 1)
                || Engine.playerItems.HasItemAmount(p, 9785, 1)
                || Engine.playerItems.HasItemAmount(p, 9788, 1)
                || Engine.playerItems.HasItemAmount(p, 9791, 1)
                || Engine.playerItems.HasItemAmount(p, 9794, 1)
                || Engine.playerItems.HasItemAmount(p, 9797, 1)
                || Engine.playerItems.HasItemAmount(p, 9800, 1)
                || Engine.playerItems.HasItemAmount(p, 9803, 1)
                || Engine.playerItems.HasItemAmount(p, 9806, 1)
                || Engine.playerItems.HasItemAmount(p, 9809, 1)
                || Engine.playerItems.HasItemAmount(p, 9812, 1)
                || Engine.playerItems.HasItemAmount(p, 9814, 1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Handles the first option on objects.
     *
     * @param p          The Player which the frame should be handled for.
     * @param packetId   The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        if (!p.objectOption1) {
            p.wc.resetWoodcutting();
            p.mi.resetMining();
            p.clickX = p.stream.readUnsignedWordBigEndian();
            p.clickId = p.stream.readUnsignedWord();
            p.clickY = p.stream.readUnsignedWordBigEndianA();

            if (Misc.getDistance(p.absX, p.absY, p.clickX, p.clickY) > 30) {
                return;
            }
            p.objectOption1 = true;
        }
        int distance = 0;

        if (p.walkDir != -1 || p.runDir != -1
                || distance > objectSize(p.clickId)) {
            return;
        }
        p.objectOption1 = false;

        FightCave fCave = new FightCave(p);
        Misc.println("[" + p.username + "] object clicked: " + p.clickId);
        switch (p.clickId) {
            case 11666:
            case 2643:
                p.frames.ItemOnInterface(p, 311, 4, 125, 2349);
                p.frames.ItemOnInterface(p, 311, 5, 125, 9467);
                p.frames.ItemOnInterface(p, 311, 6, 125, 2351);
                p.frames.ItemOnInterface(p, 311, 7, 125, 2353);
                p.frames.ItemOnInterface(p, 311, 8, 125, 2357);
                p.frames.ItemOnInterface(p, 311, 9, 125, 2355);
                p.frames.ItemOnInterface(p, 311, 10, 125, 2359);
                p.frames.ItemOnInterface(p, 311, 11, 125, 2361);
                p.frames.ItemOnInterface(p, 311, 12, 125, 2363);
                p.frames.showChatboxInterface(p, 311);
                break;

            case 25213:
                p.setCoords(3048, 3203, 0);
                break;

            // PARTY ROOM
            case 26193:// chest
                /*if (p.donator == 1 || p.rights >= 1) {
                    p.frames.sendMessage(p, "Pull the lever to start a drop party!");
                    p.openBank();
                } else if (p.donator == 0 || p.rights == 0) {
                    p.frames.sendMessage(p,
                            "<img=2>Pull the lever to start a drop party!");
                    p.frames.sendMessage(p,
                            "<img=2><img=2>If you were a donator this would be a bank!");
                }*/
                Engine.partyRoom.showDropInterface(p);
                break;
            case 26194:// lever
                if ((Engine.playerItems.HasItemAmount(p, 995, 5000000) == true)
                        || (p.donator == 1 || p.rights >= 1) && (p.partyTimer == 0)) {
                    Engine.playerItems.deleteItem(p, 995,
                            Engine.playerItems.getItemSlot(p, 995), 5000000);
                    p.frames.sendMessage(p, "<img=2>W000t! Its time to party!");
                    p.frames.createGlobalObject(115, p.heightLevel, 3049, 3376, 0,
                            10);
                    p.frames.createGlobalObject(115, p.heightLevel, 3043, 3381, 0,
                            10);
                    p.frames.createGlobalObject(115, p.heightLevel, 3039, 3378, 0,
                            10);
                    p.frames.createGlobalObject(115, p.heightLevel, 3054, 3379, 0,
                            10);
                    p.frames.createGlobalObject(116, p.heightLevel, 3051, 2278, 0,
                            10);
                    p.frames.createGlobalObject(116, p.heightLevel, 3052, 3373, 0,
                            10);
                    p.frames.createGlobalObject(116, p.heightLevel, 3046, 3383, 0,
                            10);
                    p.frames.createGlobalObject(116, p.heightLevel, 3041, 3383, 0,
                            10);
                    p.frames.createGlobalObject(117, p.heightLevel, 3039, 3380, 0,
                            10);
                    p.frames.createGlobalObject(117, p.heightLevel, 3041, 3378, 0,
                            10);
                    p.frames.createGlobalObject(117, p.heightLevel, 3045, 3377, 0,
                            10);
                    p.frames.createGlobalObject(117, p.heightLevel, 3046, 3374, 0,
                            10);
                    p.frames.createGlobalObject(117, p.heightLevel, 3042, 3373, 0,
                            10);
                    p.frames.createGlobalObject(117, p.heightLevel, 3050, 3378, 0,
                            10);
                    p.frames.createGlobalObject(117, p.heightLevel, 3047, 3380, 0,
                            10);
                    p.frames.createGlobalObject(117, p.heightLevel, 3050, 3383, 0,
                            10);
                    p.frames.createGlobalObject(117, p.heightLevel, 3045, 3382, 0,
                            10);
                    p.frames.createGlobalObject(117, p.heightLevel, 3042, 3379, 0,
                            10);
                    p.frames.createGlobalObject(118, p.heightLevel, 3044, 3373, 0,
                            10);
                    p.frames.createGlobalObject(118, p.heightLevel, 3047, 3373, 0,
                            10);
                    p.frames.createGlobalObject(118, p.heightLevel, 3042, 3384, 0,
                            10);
                    p.frames.createGlobalObject(118, p.heightLevel, 3037, 3382, 0,
                            10);
                    p.frames.createGlobalObject(119, p.heightLevel, 3036, 3382, 0,
                            10);
                    p.frames.createGlobalObject(119, p.heightLevel, 3039, 3375, 0,
                            10);
                    p.frames.createGlobalObject(119, p.heightLevel, 3048, 3374, 0,
                            10);
                    p.frames.createGlobalObject(119, p.heightLevel, 3047, 3376, 0,
                            10);
                    p.frames.createGlobalObject(120, p.heightLevel, 3050, 3374, 0,
                            10);
                    p.frames.createGlobalObject(120, p.heightLevel, 3053, 3377, 0,
                            10);
                    p.frames.createGlobalObject(120, p.heightLevel, 3051, 3384, 0,
                            10);
                    p.frames.createGlobalObject(120, p.heightLevel, 3048, 3384, 0,
                            10);
                    p.frames.createGlobalObject(121, p.heightLevel, 3044, 3377, 0,
                            10);
                    p.frames.createGlobalObject(121, p.heightLevel, 3046, 3380, 0,
                            10);
                    p.frames.createGlobalObject(121, p.heightLevel, 3045, 3379, 0,
                            10);
                    p.frames.createGlobalObject(121, p.heightLevel, 3054, 3371, 0,
                            10);
                    p.frames.createGlobalObject(122, p.heightLevel, 3052, 3375, 0,
                            10);
                    p.frames.createGlobalObject(122, p.heightLevel, 3049, 3375, 0,
                            10);
                    p.frames.createGlobalObject(122, p.heightLevel, 3048, 3377, 0,
                            10);
                    p.frames.createGlobalObject(122, p.heightLevel, 3043, 3381, 0,
                            10);
                    p.partyTimer = 300;
                } else if ((Engine.playerItems.HasItemAmount(p, 995, 5000000) == false)
                        && (p.rights == 0 || p.donator == 0) && (p.partyTimer == 0)) {
                    p.frames.sendMessage(p,
                            "Sorry but if your not a donator its 5mil to start a drop party.");
                } else if (p.partyTimer >= 1) {
                    p.frames.sendMessage(p,
                            "Please wait to start another drop party. Thank you.");
                }
                break;

            case 115:
                if (p.ActionTimer >= 1) {
                    p.frames.sendMessage(p,
                            "Please do not spam the ballons. Thank you.");
                } else if (p.ActionTimer == 0) {
                    p.requestAnim(10017, 0);
                    p.frames.createGlobalObject(123, p.heightLevel, p.clickX,
                            p.clickY, 0, 10);
                    Engine.items.createGroundItem(randomtDrop(), 1, p.clickX,
                            p.clickY, p.heightLevel, p.username);
                    p.ActionTimer = 3;
                }
                break;

            case 116:
                if (p.ActionTimer >= 1) {
                    p.frames.sendMessage(p,
                            "Please do not spam the ballons. Thank you.");
                } else if (p.ActionTimer == 0) {
                    p.requestAnim(10017, 0);
                    p.frames.createGlobalObject(124, p.heightLevel, p.clickX,
                            p.clickY, 0, 10);
                    Engine.items.createGroundItem(randomRobeDrop(),
                            1 + Misc.random(2), p.clickX, p.clickY, p.heightLevel,
                            p.username);
                    p.ActionTimer = 3;
                }
                break;

            case 117:
                if (p.ActionTimer >= 1) {
                    p.frames.sendMessage(p,
                            "Please do not spam the ballons. Thank you.");
                } else if (p.ActionTimer == 0) {
                    p.requestAnim(10017, 0);
                    p.frames.createGlobalObject(125, p.heightLevel, p.clickX,
                            p.clickY, 0, 10);
                    Engine.items.createGroundItem(randomArmourDrop(), 1, p.clickX,
                            p.clickY, p.heightLevel, p.username);
                    p.ActionTimer = 3;
                }
                break;

            case 118:
                if (p.ActionTimer >= 1) {
                    p.frames.sendMessage(p,
                            "Please do not spam the ballons. Thank you.");
                } else if (p.ActionTimer == 0) {
                    p.requestAnim(10017, 0);
                    p.frames.createGlobalObject(126, p.heightLevel, p.clickX,
                            p.clickY, 0, 10);
                    Engine.items.createGroundItem(randomcDrop(), 1, p.clickX,
                            p.clickY, p.heightLevel, p.username);
                    p.ActionTimer = 3;
                }
                break;

            case 119:
                if (p.ActionTimer >= 1) {
                    p.frames.sendMessage(p,
                            "Please do not spam the ballons. Thank you.");
                } else if (p.ActionTimer == 0) {
                    p.requestAnim(10017, 0);
                    p.frames.createGlobalObject(127, p.heightLevel, p.clickX,
                            p.clickY, 0, 10);
                    Engine.items.createGroundItem(randomWepDrop(), 1, p.clickX,
                            p.clickY, p.heightLevel, p.username);
                    p.ActionTimer = 3;
                }
                break;

            case 120:
                if (p.ActionTimer >= 1) {
                    p.frames.sendMessage(p,
                            "Please do not spam the ballons. Thank you.");
                } else if (p.ActionTimer == 0) {
                    p.requestAnim(10017, 0);
                    p.frames.createGlobalObject(128, p.heightLevel, p.clickX,
                            p.clickY, 0, 10);
                    Engine.items.createGroundItem(randomsDrop(), 1, p.clickX,
                            p.clickY, p.heightLevel, p.username);
                    p.ActionTimer = 3;
                }
                break;

            case 121:

                if (p.ActionTimer >= 1) {
                    p.frames.sendMessage(p,
                            "Please do not spam the ballons. Thank you.");
                } else if (p.ActionTimer == 0) {
                    p.requestAnim(10017, 0);
                    p.frames.createGlobalObject(129, p.heightLevel, p.clickX,
                            p.clickY, 0, 10);
                    Engine.items.createGroundItem(randomMulti(),
                            1 + Misc.random(1000), p.clickX, p.clickY,
                            p.heightLevel, p.username);
                    p.ActionTimer = 3;
                }
                break;

            case 122:
                if (p.ActionTimer >= 1) {
                    p.frames.sendMessage(p,
                            "Please do not spam the ballons. Thank you.");
                } else if (p.ActionTimer == 0) {
                    p.requestAnim(10017, 0);
                    p.frames.createGlobalObject(130, p.heightLevel, p.clickX,
                            p.clickY, 0, 10);
                    Engine.items.createGroundItem(randomWepDrop(), 1, p.clickX,
                            p.clickY, p.heightLevel, p.username);
                    p.ActionTimer = 3;
                }
                break;

            // ===========================================END OF PARTY
            // ROOM===============================================

            case 3192:
                p.frames.showInterface(p, 275);
                p.frames.setString(p, "Highscores", 275, 2);
                p.frames.setString(p, "", 275, 11);
                p.frames.setString(p, "Highscore's are no longer in-game", 275, 12);
                p.frames.setString(p, "To see the highscores pealse vist.", 275, 13);
                p.frames.setString(p, "iceyy.no-ip.biz/highscores/highscores.php",
                        275, 14);
                p.frames.setString(p, "Or go to our website. iceyy.no-ip.biz", 275,
                        15);
                p.frames.setString(p, "Thank you.", 275, 16);
                break;

            case 29728:
                p.setCoords(2925, 5333, 2);
                break;

            case 26723:
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 242);
                p.frames.setString(p, "Old Spirit Tree", 242, 3);
                p.frames.setString(p,
                        "Talk to the Void Knight to exit the game if you wish.",
                        242, 4);
                break;
            // ============================================New Home
            // Objects===================================================
            case 11707:// hair dresser shop
                if (p.absX == 2949 && p.absY == 3379 && p.heightLevel == 0) {
                    p.setCoords(2948, 3379, 0);
                } else if (p.absX == 2948 && p.absY == 3379 && p.heightLevel == 0) {// inside
                    // to
                    // out
                    p.setCoords(2949, 3379, 0);
                }
                break;
            case 16776:// clothing store door out to in
                if (p.absX == 2104 && p.absY == 3911 && p.heightLevel == 0) {
                    p.setCoords(2104, 3910, 0);
                } else if (p.absX == 2104 && p.absY == 3910 && p.heightLevel == 0) {// inside
                    // to
                    // out
                    p.setCoords(2104, 3911, 0);
                } else if (p.absX == 2088 && p.absY == 3910 && p.heightLevel == 0) {// donator
                    // shop
                    // door
                    // out
                    // to
                    // in
                    p.setCoords(2088, 3909, 0);
                } else if (p.absX == 2088 && p.absY == 3909 && p.heightLevel == 0) {// inside
                    // to
                    // out
                    p.setCoords(2088, 3910, 0);
                } else if (p.absX == 2091 && p.absY == 3916 && p.heightLevel == 0) {// Mandriths
                    // shop
                    // door
                    // out
                    // to
                    // in
                    p.setCoords(2091, 3917, 0);
                } else if (p.absX == 2091 && p.absY == 3917 && p.heightLevel == 0) {// inside
                    // to
                    // out
                    p.setCoords(2091, 3916, 0);
                } else if (p.absX == 2082 && p.absY == 3913 && p.heightLevel == 0) {// Big
                    // room
                    // door
                    // outside
                    // to
                    // in
                    p.setCoords(2081, 3913, 0);
                } else if (p.absX == 2081 && p.absY == 3913 && p.heightLevel == 0) {// inside
                    // to
                    // out
                    p.setCoords(2082, 3913, 0);
                }
                break;
            case 16778:// Genral store door out to in
                if (p.absX == 2096 && p.absY == 3909 && p.heightLevel == 0) {
                    p.setCoords(2096, 3908, 0);
                } else if (p.absX == 2096 && p.absY == 3908 && p.heightLevel == 0) {// inside
                    // to
                    // out
                    p.setCoords(2096, 3909, 0);
                } else if (p.absX == 2083 && p.absY == 3920 && p.heightLevel == 0) {// hairdresser
                    // door
                    // outside
                    // to
                    // in
                    p.setCoords(2083, 3921, 0);
                } else if (p.absX == 2083 && p.absY == 3921 && p.heightLevel == 0) {// inside
                    // to
                    // out
                    p.setCoords(2083, 3920, 0);
                } else if (p.absX == 2076 && p.absY == 3920 && p.heightLevel == 0) {// wise
                    // old
                    // man
                    // door
                    // outside
                    // to
                    // in
                    p.setCoords(2075, 3920, 0);
                } else if (p.absX == 2075 && p.absY == 3920 && p.heightLevel == 0) {// inside
                    // to
                    // out
                    p.setCoords(2076, 3920, 0);
                } else if (p.absX == 2074 && p.absY == 3900 && p.heightLevel == 0) {// normuf
                    // shop
                    // door
                    // out
                    // to
                    // in
                    p.setCoords(2074, 3899, 0);
                } else if (p.absX == 2074 && p.absY == 3899 && p.heightLevel == 0) {// insdie
                    // to
                    // out
                    p.setCoords(2074, 3900, 0);
                } else if (p.absX == 2081 && p.absY == 3898 && p.heightLevel == 0) {// Fancy
                    // dress
                    // shop
                    // door
                    // outside
                    // to
                    // in
                    p.setCoords(2081, 3897, 0);
                } else if (p.absX == 2081 && p.absY == 3897 && p.heightLevel == 0) {// inside
                    // to
                    // out
                    p.setCoords(2081, 3898, 0);
                }
                break;
            case 26145:// right side going up
                if ((p.donator < 1) && (p.rights < 1)) {
                    p.frames.sendMessage(p, "<img=2> " + p.username
                            + ", you must be a donator to enter...");
                    p.frames.sendMessage(p,
                            "...You can donate on our website. iceyyscape.webs.com");
                } else if ((p.donator >= 1) || (p.rights >= 1)) {
                    p.frames.sendMessage(p, "<img=2> " + p.username
                            + ", Welcome to the donator pub!");
                    p.setCoords(3053, 3382, 1);
                }
                break;
            case 26144:// left side going up
                if ((p.donator < 1) && (p.rights < 1)) {
                    p.frames.sendMessage(p, "<img=2> " + p.username
                            + ", you must be a donator to enter...");
                    p.frames.sendMessage(p,
                            "...You can donate on our website. iceyyscape.webs.com");
                } else if ((p.donator >= 1) || (p.rights >= 1)) {
                    p.frames.sendMessage(p, "<img=2> " + p.username
                            + ", Welcome to the donator pub!");
                    p.setCoords(3039, 3383, 1);
                }
                break;
            case 26148:// left side going down
                p.setCoords(3038, 3382, 0);
                break;
            case 26150:// right side going down
                p.setCoords(3054, 3382, 0);
                break;
            case 26147:// right going up again
                p.setCoords(3053, 3374, 2);
                break;
            case 26146:// left going up again
                p.setCoords(3038, 3374, 2);
                break;
            case 26151:// right going down again
                p.setCoords(3052, 3372, 1);
                break;
            case 26149:// left going down again
                p.setCoords(3039, 3372, 1);
                break;
            // ===========================================End of Home
            // Objects=================================================
            // ===========================================lava
            // maze\halloween=================================================
            case 170:// chest
                Engine.playerItems.addItem(p, 6199, 1);
                p.frames.sendMessage(p, "<img=2>Here is your reward!");
                p.frames.sendMessage(p, "<img=2>Happy halloween from iceyy!scape");
                break;

            case 1558:// gate
                p.teleportTo(3075, 3866, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                break;

            case 1557:// gate
                p.teleportTo(3075, 3866, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                break;

            // =============================end of lava
            // maze/halloween=====================================

            case 3203:
                p.frames.sendMessage(p, "You cannot forfeit yet.");
                break;

            case 36774:
                if (p.absX == 3205 && p.absY == 3209 && p.heightLevel == 1) {
                    p.setCoords(3205, 3209, 0);
                }
                break;

            case 9398:
            case 11758:
            case 16700:
            case 27663:
                if (p.alreadyBanked) {
                    p.openBank();
                } else if (p.hasBankPin == 1) {
                    p.frames.showInterface(p, 13);
                    p.bankpin();
                    p.frames.sendMessage(p, "Please enter in your bank pin.");
                } else if (p.hasBankPin == 0) {
                    p.openBank();
                    p.frames.sendMessage(p,
                            "You do not have a bank pin, do ::bankpin to set one.");
                }
                break;
            case 26427:
                if (p.absX == 2908 && p.absY == 5265) {
                    if (p.skc > 19) {
                        p.teleportTo(2907, 5265, 0, 0, 0, 8939, 8941, 1678, 0,
                                1679, 0);
                        p.frames.sendMessage(p,
                                "You Enter Saradomins Lair and lose 20 kill count... Prepare to fight!");
                        p.skc = (p.skc - 20);
                    } else if (p.skc <= 19) {
                        p.frames.sendMessage(p,
                                "You must have a kill count of 20 to enter this lair.");
                    }
                }
                if (p.absX == 2907 && p.absY == 5265) {
                    p.teleportTo(2906, 5265, 0, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                    p.frames.sendMessage(p, "You left Saradomins Lair...");
                }
                break;

            case 26426:
                if (p.absX == 2839 && p.absY == 5295) {
                    if (p.akc > 19) {
                        p.teleportTo(2839, 5296, 2, 4, 0, 8939, 8941, 1678, 0,
                                1679, 0);
                        p.frames.sendMessage(p,
                                "You enter Armadyls Lair and lose 20 kill count... Prepare to fight!");
                        p.akc = (p.akc - 20);
                    } else if (p.akc <= 19) {
                        p.frames.sendMessage(p,
                                "You must have a kill count of 20 to enter this lair.");
                    }
                }
                if (p.absX == 2839 && p.absY == 5296) {
                    p.teleportTo(2839, 5295, 2, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                    p.frames.sendMessage(p, "You left Armadyls Lair...");
                }
                break;

            case 26428:
                if (p.absX == 2925 && p.absY == 5332) {
                    if (p.zkc > 19) {
                        p.teleportTo(2925, 5331, 2, 0, 0, 8939, 8941, 1678, 0,
                                1679, 0);
                        p.frames.sendMessage(p,
                                "You enter Zamoraks Lair and lose 20 kill count... Prepare to fight!");
                        p.zkc = (p.zkc - 20);
                    } else if (p.zkc <= 19) {
                        p.frames.sendMessage(p,
                                "You must have a kill count of 20 to enter this lair.");
                    }
                }
                if (p.absX == 2925 && p.absY == 5331) {
                    p.teleportTo(2925, 5332, 2, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                    p.frames.sendMessage(p, "You left Zamoraks Lair...");
                }
                break;

            case 2878: /* Walking into pool. (going to statues.) */
                if (p.skillLvl[6] >= 60) {
                    p.teleportTo(2508, 4689, 0, 2, 0, 7376, 7377, -1, 0, -1, 0);
                } else {
                    p.frames.sendMessage(p, "You need level 60 magic to enter..");
                }
                break;

            case 21505: {
                if (p.absX == 2328) {
                    p.setCoords(2329, 3804, 0);
                }
                if (p.absX == 2329) {
                    p.setCoords(2328, 3804, 0);
                }
            }
            break;

            case 21507: {
                if (p.absX == 2328) {
                    p.setCoords(2329, 3805, 0);
                }
                if (p.absX == 2329) {
                    p.setCoords(2328, 3805, 0);
                }
            }
            break;

            case 27254:
                p.setCoords(2410, 3879, 0);
                break;

            case 2879: /* Walking into pool. (going to bank.) */
                p.teleportTo(2542, 4718, 0, 2, 0, 7376, 7377, -1, 0, -1, 0);
                break;
            case 733: // Mage bank webs
            {
                if (p.absX == 3092) {
                    p.setCoords(3093, 3957, 0);
                }
                if (p.absX == 3093) {
                    p.setCoords(3092, 3957, 0);
                }
                if (p.absX == 3094) {
                    p.setCoords(3095, 3957, 0);
                }
                if (p.absX == 3029) {
                    p.setCoords(3031, 3852, 0);
                }
                if (p.absX == 3115) {
                    p.setCoords(3115, 3860, 0);
                }
                if (p.absX == 3095) {
                    p.setCoords(3094, 3957, 0);
                }
            }
            break;
            case 5960: // Mage bank lever to wildy
                if (p.absY == 4712) {
                    p.teleportTo(3090, 3956, 0, 4, 4, 8939, 8941, 1678, 0, 1679, 0);
                }
                break;
            case 5959: // Wildy lever to mage bank
                if (p.absY == 3956) {
                    p.teleportTo(2539, 4712, 0, 4, 4, 8939, 8941, 1678, 0, 1679, 0);
                }
                break;
            case 2873:
                Engine.playerItems.addItem(p, 2412, 1);
                break;

            case 2875:
                Engine.playerItems.addItem(p, 2413, 1);
                break;

            case 2874:
                Engine.playerItems.addItem(p, 2414, 1);
                break;

            case 36844:
                if (p.absX == 3207 && p.absY == 3222) {
                    p.setCoords(3208, 3222, 1);
                }
                if (p.absX == 3208 && p.absY == 3222) {
                    p.setCoords(3207, 3222, 1);
                }
                break;

            case 16154:
                if (p.absX == 3081 && p.absY == 3421) {
                    p.setCoords(2919, 5274, 0);
                }
                p.frames.sendMessage(p,
                        "You get in some place where it is cold....");
                p.frames.sendMessage(p, "The Orks seems to be angry...");
                break;

            case 26293:
                if (p.absX == 2882 && p.absY == 5311) {
                    p.setCoords(3081, 3421, 0);
                }
                if (p.absX == 2882 && p.absY == 5310) {
                    p.setCoords(3081, 3421, 0);
                }
                if (p.absX == 2881 && p.absY == 5310) {
                    p.setCoords(3081, 3421, 0);
                }
                if (p.absX == 2880 && p.absY == 5311) {
                    p.setCoords(3081, 3421, 0);
                }
                break;
            // God Wars Objects
            case 27661:
                p.skillLvl[5] = p.getLevelForXP(5);
                p.frames.setSkillLvl(p, 5);
                p.requestAnim(645, 1);
                p.frames.sendMessage(p, "You recharge your prayer points.");
                break;

            case 24343:
                p.skillLvl[5] = p.getLevelForXP(5);
                p.frames.setSkillLvl(p, 5);
                p.requestAnim(645, 1);
                p.frames.sendMessage(p, "You recharge your prayer points.");
                break;

            case 2640:
                p.skillLvl[5] = p.getLevelForXP(5);
                p.frames.setSkillLvl(p, 5);
                p.requestAnim(645, 1);
                p.frames.sendMessage(p, "You restore your prayer points.");
                break;

            case 26286:
                p.skillLvl[5] = p.getLevelForXP(5);
                p.frames.setSkillLvl(p, 5);
                p.requestAnim(645, 1);
                p.frames.sendMessage(p,
                        "The Zamorak Gods Restore Your Prayer Points.");
                break;

            case 26289:
                p.skillLvl[5] = p.getLevelForXP(5);
                p.frames.setSkillLvl(p, 5);
                p.requestAnim(645, 1);
                p.frames.sendMessage(p,
                        "The Bandos Gods Restore Your Prayer Points.");
                break;
            case 26439:
                if (p.absX == 2885 && p.absY == 5345) {
                    p.teleportTo(2885, 5332, 2, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You cross the icey bridge...");
                }
                if (p.absX == 2885 && p.absY == 5332) {
                    p.teleportTo(2885, 5345, 2, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You cross the icey bridge...");
                }
                break;

            case 26384:
                if (p.absX == 2850 && p.absY == 5333) {
                    p.teleportTo(2851, 5333, 2, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You left Bandos lair...");
                }
                if (p.absX == 2851 && p.absY == 5333) {
                    p.teleportTo(2850, 5333, 2, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p,
                            "You destroyed the door, and walked into Bandos lair...");
                }
                break;
            case 26303:
                if (p.absX == 2871 && p.absY == 5269) {
                    p.teleportTo(2871, 5279, 2, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You swing yourself over...");
                }
                if (p.absX == 2871 && p.absY == 5279) {
                    p.teleportTo(2871, 5269, 2, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You swing yourself over...");
                }
                break;
            // end of God wars objects

            case 6552:
                p.requestAnim(645, 1);
                p.requestGFX(435, 1);
                if (p.isAncients == 0) {// Switch to ancients
                    if (p.usingHD) {
                        p.frames.setInterface(p, 1, 746, 93, 193); // Magic tab
                        p.frames.sendMessage(p,
                                "<img=2>The power of the ancient ways is now running though your blood!");
                        p.isAncients = 1;
                    } else {
                        p.frames.setInterface(p, 1, 548, 79, 193); // Magic tab
                        p.frames.sendMessage(p,
                                "<img=2>The power of the ancient ways is now running though your blood!");
                        p.isAncients = 1;
                    }
                } else if (p.isAncients == 1) {// Switch to modern spell book
                    if (p.usingHD) {
                        p.frames.setInterface(p, 1, 746, 93, 192); // Magic tab
                        p.frames.sendMessage(p,
                                "<img=2>Was the power of ancients to strong for you?!?");
                        p.isAncients = 0;
                    } else {
                        p.frames.setInterface(p, 1, 548, 79, 192); // Magic tab
                        p.frames.sendMessage(p,
                                "<img=2>Was the power of ancients to strong for you?!?");
                        p.isAncients = 0;
                    }
                }
                break;

            case 6282:
                p.setCoords(3115 + Misc.random(8), 3722 + Misc.random(8), 0);
                p.requestGFX(482, 0);
                p.requestAnim(1914, 0);
                p.frames.sendMessage(p,
                        "<img=2>You Teleport to Pvp! On death items are: SAFE!");
                p.requestForceChat("RRRRRHHHHHAAAAAAA!!");
                break;

            // ============================================donators
            // training==================================================

            case 2830:
                p.setCoords(2530, 3026, 0);
                p.requestAnim(1115, 0);
                p.frames.sendMessage(p, "<img=2>You are alive!");
                p.requestForceChat("Not Again? I'm going to die!!");
                break;

            case 2831:
                p.setCoords(2530, 3029, 0);
                p.requestAnim(1115, 0);
                p.frames.sendMessage(p, "<img=2>You are alive!");
                p.requestForceChat("Oh my God!");
                break;

            case 2832:
                if (p.absX == 2506 && p.absY == 3011) {
                    p.setCoords(2508, 3011, 0);
                    p.requestAnim(839, 0);
                    p.frames.sendMessage(p, "<img=2>You climb over safly.");
                    p.requestForceChat("I LOVE ICEDICE!!");
                } else if (p.absX == 2506 && p.absY == 3012) {
                    p.setCoords(2508, 3012, 0);
                    p.requestAnim(839, 0);
                    p.frames.sendMessage(p, "<img=2>You climb over safly.");
                    p.requestForceChat("I LOVE ICEDICE!!");
                } else if (p.absX == 2508 && p.absY == 3012) {
                    p.setCoords(2506, 3012, 0);
                    p.requestAnim(839, 0);
                    p.frames.sendMessage(p, "<img=2>You climb over safly.");
                    p.requestForceChat("I LOVE ICEDICE!!");
                } else if (p.absX == 2508 && p.absY == 3011) {
                    p.setCoords(2506, 3011, 0);
                    p.requestAnim(839, 0);
                    p.frames.sendMessage(p, "<img=2>You climb over safly.");
                    p.requestForceChat("I LOVE ICEDICE!!");
                }
                break;

            case 7288:
                p.teleportTo(2349, 3099, 0, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 242);
                p.frames.animateInterfaceId(p, 9850, 242, 2);
                p.frames.setNPCId(p, 3788, 242, 2);
                p.frames.setString(p, "Void knight", 242, 3);
                p.frames.setString(p, "You Teleport to low level of pest control!",
                        242, 4);
                p.frames.setString(p,
                        "Defend us and keep them from reaching our city!", 242, 5);
                p.frames.sendMessage(p,
                        "You Teleport to low level of pest control!");
                p.frames.sendMessage(p,
                        "Defend us and keep them from reaching our city!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 11356:
                p.teleportTo(3212, 3429, 0, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p, "You Teleport Home!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 14829:
                p.teleportTo(3212, 3429, 0, 5, 0, 8939, 8941, 342, 0, 190, 0);
                p.frames.sendMessage(p, "You Teleport Home!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 409:
                p.requestAnim(645, 0);
                p.skillLvl[5] = p.getLevelForXP(5);
                p.frames.setSkillLvl(p, 5);
                p.frames.sendMessage(p, "You recharge your prayer!");
                p.appearanceUpdateReq = true;
                p.updateReq = true;

                break;

            case 14825:
                p.teleportTo(3212, 3429, 0, 5, 0, 8939, 8941, 342, 0, 190, 0);
                p.frames.sendMessage(p, "You Teleport Home!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 14826:
                p.teleportTo(3212, 3429, 0, 5, 0, 8939, 8941, 342, 0, 190, 0);
                p.frames.sendMessage(p, "You Teleport Home!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 14828:
                p.teleportTo(3212, 3429, 0, 5, 0, 8939, 8941, 342, 0, 190, 0);
                p.frames.sendMessage(p, "You Teleport Home!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 14827:
                p.teleportTo(3212, 3429, 0, 5, 0, 8939, 8941, 342, 0, 190, 0);
                p.frames.sendMessage(p, "You Teleport Home!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 14831:
                p.teleportTo(3212, 3429, 0, 5, 0, 8939, 8941, 342, 0, 190, 0);
                p.frames.sendMessage(p, "You Teleport Home!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 15641:
                p.teleportTo(2854, 3545, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 38012:
                p.teleportTo(2840, 3539, 2, 0, 0, 0, 0, 0, 0, 0, 0);
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 15638:
                p.teleportTo(2840, 3539, 1, 0, 0, 0, 0, 0, 0, 0, 0);
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 1738:
                p.teleportTo(2840, 3539, 1, 0, 0, 0, 0, 0, 0, 0, 0);
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 15644:
                p.teleportTo(2855, 3545, 2, 0, 0, 0, 0, 0, 0, 0, 0);
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 15653:
                if (p.skillLvl[0] >= 65)
                    if (p.skillLvl[2] >= 65) {
                        p.teleportTo(2876, 3546, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                        p.rebuildNPCList = true;
                        p.updateReq = true;
                        p.frames.sendMessage(p, "You Teleport inside the guild.!");
                    } else {
                        p.frames.sendMessage(p,
                                "You need level 65 Strength and Attack to enter..");
                    }
                break;

            case 2476:
                p.teleportTo(3212, 3429, 0, 4, 0, 8939, 8941, 342, 0, 190, 0);
                p.frames.sendMessage(p, "You Teleport to World 1!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 2477:
                p.teleportTo(3087, 3491, 4, 4, 0, 8939, 8941, 342, 0, 190, 0);
                p.frames.sendMessage(p, "You Teleport to World 2!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 14830:
                p.teleportTo(3212, 3429, 0, 4, 0, 8939, 8941, 342, 0, 190, 0);
                p.frames.sendMessage(p, "You Teleport Home!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 26425:
                if (p.absX == 2863 && p.absY == 5354) {
                    if (p.bkc > 19) {
                        p.teleportTo(2864, 5354, 2, 0, 0, 8939, 8941, 1678, 0,
                                1679, 0);
                        p.frames.sendMessage(p,
                                "You Enter The Bandos Lair and lose 20 kill count... Prepare to fight!");
                        p.bkc = (p.bkc - 20);
                    } else if (p.bkc <= 19) {
                        p.frames.sendMessage(p,
                                "You must have a kill count of 20 to enter this lair.");
                    }
                }
                if (p.absX == 2864 && p.absY == 5354) {
                    p.frames.sendMessage(p, "You left Bandos Lair...");
                }
                break;

            case 29734:
                Engine.playerItems.addItem(p, 12629, 1);
                p.frames.sendMessage(p, "You recieve some safety gloves");
                p.sdc = (p.sdc - 5);
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 29672:
                p.teleportTo(3170, 4272, 3, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p,
                        "You teleport to Safety Dungeons Second Floor.");
                p.sdc = (p.sdc - 10);
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 26444:
                p.teleportTo(2914, 5300, 1, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p, "You teleport closer to the Saradomin God.");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 33173:
                p.teleportTo(3056, 9555, 0, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p, "You teleport to high level training");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 33174:
                p.teleportTo(3056, 9562, 0, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p, "You teleport to low level training");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 26445:
                p.teleportTo(2920, 5274, 0, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p,
                        "You teleport down to the Saradomin God level!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 29624:
                p.teleportTo(3177, 4266, 4, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p,
                        "You teleport to Safety Dungeons Third Floor.");
                p.sdc = (p.sdc - 20);
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 29736:
                p.teleportTo(3177, 4266, 4, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p,
                        "You teleport to Safety Dungeons Third Floor.");
                p.sdc = (p.sdc - 30);
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 29663:
                p.teleportTo(3160, 4246, 1, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p,
                        "You teleport to Safety Dungeons, Dungeon Floor.");
                p.sdc = (p.sdc - 35);
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 13615:
                p.teleportTo(3212, 3429, 0, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p, "You teleport Home!");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 29664:
                p.teleportTo(3157, 4244, 2, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p,
                        "You teleport to Safety Dungeons First Floor.");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 29671:
                p.teleportTo(3174, 4273, 2, 0, 0, 8939, 8941, 1678, 0, 1679, 0);
                p.frames.sendMessage(p,
                        "You teleport to Safety Dungeons First Floor.");
                p.rebuildNPCList = true;
                p.updateReq = true;
                break;

            case 1805:
                if (p.absX == 3191 && p.absY == 3363) {
                    p.setCoords(3191, 3362, 0);
                }
                if (p.absX == 3191 && p.absY == 3362) {
                    p.setCoords(3191, 3363, 0);
                }
                break;

            case 26917:
                if (p.absX == 3069 && p.absY == 3513) {
                    p.setCoords(3069, 3514, 0);
                }
                if (p.absX == 3069 && p.absY == 3514) {
                    p.setCoords(3069, 3513, 0);
                }
                break;

            case 9369:
                if (p.absX == 2399 && p.absY == 5177) {
                    p.setCoords(2399, 5175, 0);
                    p.OverTimer = 5;
                    p.frames.setOverlay(p, 653);
                }
                if (p.absX == 2399 && p.absY == 5175) {
                    p.setCoords(2399, 5177, 0);
                    p.OverTimer = 5;
                    p.frames.removeOverlay(p);
                }

                break;
            case 9368:
                if (p.absX == 2399 && p.absY == 5167) {
                    p.setCoords(2399, 5169, 0);
                    p.OverTimer = 5;
                }
                break;

            case 9356:
                p.setCoords(2412, 5117, p.heightLevel * 4);
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9847, 241, 2);
                p.frames.setNPCId(p, 2617, 241, 2);
                p.frames.setString(p, "TzHaar-Mej-Jal", 241, 3);
                p.frames.setString(p, "Prepare to fight for your life!", 241, 4);
                p.waveDelay = 20;
                p.waveCount = 0;
                break;

            case 28214:

                if (!p.AtClanField()) {
                    p.frames.showInterface(p, 120);
                    p.JadTele = 4;
                    p.frames.showChatboxInterface(p, 241);
                    p.frames.animateInterfaceId(p, 9837, 241, 2);
                    p.frames.setNPCId(p, 6537, 241, 2);
                    p.frames.setString(p, "", 241, 3);
                    p.frames.setString(p, "Hah, you won't last 2 minutes...", 241,
                            4);
                    p.frames.sendMessage(p,
                            "Welcome to Tzhaar, fight Tztok-Jad or enjoy the fight pits.");
                } else if (p.AtClanField()) {
                    clanWars.removeFromField(p);
                }
                break;

            // =============================CONSTRUCTION===============================

            case 13161:
                p.frames.showInterface(p, 591);
                break;
            case 15482:
                p.InHouse = true;
                p.OwnHouse = true;
                p.HouseHeight = p.playerId;
                p.frames.showInterface(p, 399);
                p.HouseTele = 6;
                p.setCoords(3104, 3926, p.HouseHeight);
                p.frames.showInterface(p, 399);
                break;

            case 13405:
                p.KickPlayers = false;
                p.BuildingMode = false;
                p.setCoords(2544, 3096, 0);
                break;

            // =======================END OF CONSTRUCTION==========================
            case 7871:
                p.FarmType = 0;
                p.requestAnim(5212, 0);
                p.addSkillXP(250 * p.skillLvl[19] * Config.bonusXP, 19);
                Engine.playerItems.addItem(p, 6010, 1);
                p.frames.createGlobalObject(7843, p.heightLevel, 2810, 3464, 0, 10);
                break;

            case 7855:
                p.FarmType = 0;
                p.requestAnim(5212, 0);
                p.addSkillXP(750 * p.skillLvl[19] * Config.bonusXP, 19);
                Engine.playerItems.addItem(p, 225, 1);
                p.frames.createGlobalObject(7843, p.heightLevel, 2810, 3464, 0, 10);
                break;

            case 8111:
                p.FarmType = 0;
                p.requestAnim(5212, 0);
                p.addSkillXP(1000 * p.skillLvl[19] * Config.bonusXP, 19);
                Engine.playerItems.addItem(p, 5972, 1);
                p.frames.createGlobalObject(8135, p.heightLevel, 2814, 3464, 0, 10);
                break;

            case 7941:
                p.FarmType = 0;
                p.requestAnim(5212, 0);
                p.addSkillXP(500 * p.skillLvl[19] * Config.bonusXP, 19);
                Engine.playerItems.addItem(p, 1955, 1);
                p.frames.createGlobalObject(8135, p.heightLevel, 2814, 3464, 0, 10);
                break;

            case 28716:
                if (distance > 0 && (p.walkDir > 0 || p.runDir > 0)
                        || distance != 0 && p.walkDir <= 0 && p.runDir <= 0) {
                    p.frames.showInterface(p, 669);
                    return;
                }

            case 28714:
                if (distance > 0 && (p.walkDir > 0 || p.runDir > 0)
                        || distance != 0 && p.walkDir <= 0 && p.runDir <= 0) {
                    p.teleportTo(2926, 3444, 0, 4, 4, 8939, 8941, 1678, 0, 1679, 0);
                }
                break;

            case 28119:
                if (p.combatLevel > 67) {
                    p.frames.sendMessage(p, "Your combat level is too high for this crator.");
                    return;
                }
                Engine.BountyHunter.enter(p, 1);
                break;
            case 28120:
                if (p.combatLevel < 55) {
                    p.frames.sendMessage(p, "Your combat level is too low for this crator.");
                    return;
                }
                if (p.combatLevel > 113) {
                    p.frames.sendMessage(p, "Your combat level is too high for this crator.");
                    return;
                }
                Engine.BountyHunter.enter(p, 2);
                break;
            case 28121:
                if (p.combatLevel < 100) {
                    p.frames.sendMessage(p, "Your combat level is too low for this crator.");
                    return;
                }
                Engine.BountyHunter.enter(p, 1);
                break;
            case 28122:
                int ii = p.heightLevel;
                if (ii == 0) {
                    Engine.BountyHunter.exit(p, 1);
                } else if (ii == 4) {
                    Engine.BountyHunter.exit(p, 2);
                } else if (ii == 8) {
                    Engine.BountyHunter.exit(p, 3);
                }
                break;

            case 7289:
                p.frames.showInterface(p, 45);
                break;

            case 8132:

                p.Cooking = false;
                p.frames.setString(p, "Grow Apple Tree", 458, 1);
                p.frames.setString(p, "Grow Spirit Tree", 458, 2);
                p.frames.setString(p, "Grow Papaya Tree", 458, 3);
                p.frames.showChatboxInterface(p, 458);
                break;

            case 2282:// Rope Swing
                if (p.absX == 2551 && p.absY == 3554) {
                    p.chatText = "Heck no!";
                    p.chatTextUpdateReq = true;
                    p.updateReq = true;
                    p.SwingTimer1 = 2;
                    p.isRunning = true;
                }
                break;

            case 2294:// Log
                if (p.absX == 2551 && p.absY == 3546) {
                    p.LogTimer = 4;
                    p.AgilityXP = 250;
                    p.AgilityTimer = 12;
                    p.jumpDelay = 12;
                    p.NewEmote = 844;
                    p.isRunning = false;
                    p.reqWalkQueue(2541, 3546);
                }
                break;

            case 20211:// Obstacle Net
                if (p.absX == 2539 && p.absY == 3546 || p.absX == 2539
                        && p.absY == 3545) {
                    p.AgilityXP = 400;
                    p.AgilityTimer = 6;
                    p.NetTimer = 4;
                    p.requestAnim(3063, 0);
                }
                break;

            case 2302:// WallSlide
                if (p.absX == 2536 && p.absY == 3547) {
                    p.AgilityXP = 200;
                    p.AgilityTimer = 5;
                    p.jumpDelay = 5;
                    p.NewEmote = 756;
                    p.isRunning = false;
                    p.reqWalkQueue(2532, 3547);
                }
                break;

            case 3205:
                p.setCoords(2532, 3546, 0);
                break;

            case 1948:
                if (p.AgilityTimer == -1) {
                    if (p.absX == 2535 && p.absY == 3553) {
                        p.wallTimer1 = 1;
                        p.NewEmote = 839;
                    }
                    if (p.absX == 2538 && p.absY == 3553) {
                        p.wallTimer2 = 1;
                        p.NewEmote = 839;
                    }
                    if (p.absX == 2541 && p.absY == 3553) {
                        p.wallTimer3 = 1;
                        p.NewEmote = 839;
                    }
                }
                break;

            case 25161:
                if (p.AgilityTimer == -1) {
                    if (p.absX == 2845 && p.absY == 9635 || p.absX == 2845
                            && p.absY == 9636) {
                        p.wallTimer4 = 1;
                        p.NewEmote = 839;
                    }
                    if (p.absX == 2847 && p.absY == 9635 || p.absX == 2847
                            && p.absY == 9636) {
                        p.wallTimer5 = 1;
                        p.NewEmote = 839;
                    }
                }
                break;

            case 34383:
                if (p.ActionTimer == 0) {
                    p.ActionTimer = 3;
                    p.requestAnim(791, 0);
                    Engine.playerItems.addItem(p, 995, 2500);
                    p.addSkillXP(45 * p.skillLvl[17], 17);
                    p.frames.sendMessage(p,
                            "I add much runes but exp 3 times smaller");
                }
                break;

            case 29954:
                if (p.ActionTimer == 0) {
                    p.ActionTimer = 10;
                    p.requestAnim(791, 0);
                    p.addSkillXP(18 * p.skillLvl[23], 23);
                    p.frames.sendMessage(p, "You get summoning exp!");
                }
                break;

            case 4387:// cw sara portal
                if (p.equipment[1] > 0 && p.equipmentN[1] > 0) {
                    p.frames.sendMessage(p, "You cannot be wearing a cape.");
                } else if (p.equipment[0] > 0 && p.equipmentN[0] > 0) {
                    p.frames.sendMessage(p,
                            "You cannot be wearing anything on your head.");
                } else if (Engine.CWGameTime > 0) {
                    p.frames.sendMessage(p, "Game has already begun.");
                } else if (cape(p) == true) {
                    p.frames.sendMessage(p,
                            "You cannot bring capes or hats into the game.");
                } else if (Engine.ZamorakTeam < Engine.SaradominTeam) {
                    p.frames.sendMessage(p,
                            "This team has more players than the other.");
                } else {
                    p.equipment[1] = 4041;
                    p.equipmentN[1] = 1;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                    p.setCoords(2377 + Misc.random(4), 9486 + Misc.random(4), 0);
                    p.frames.sendMessage(p, "You join Saradomin's side.");
                }

                break;

            case 11402:
                p.frames.showChatboxInterface(p, 242);
                p.frames.animateInterfaceId(p, 495, 242, 2);
                p.frames.setNPCId(p, 1526, 242, 2);
                p.frames.setString(p, "Banker", 242, 3);
                p.frames.setString(p, "Welcome to the iceyyscape bank.", 242, 4);
                p.frames.setString(p, "You can acces your bank from here.", 242, 5);
                break;

            case 25808:
                p.frames.showChatboxInterface(p, 242);
                p.frames.animateInterfaceId(p, 9827, 242, 2);
                p.frames.setNPCId(p, 495, 242, 2);
                p.frames.setString(p, "Banker", 242, 3);
                p.frames.setString(p, "Welcome to the iceyyscape bank.", 242, 4);
                p.frames.setString(p, "You can access your bank from here.", 242, 5);

            case 4483:// castle wars bank
                p.frames.showChatboxInterface(p, 242);
                p.frames.animateInterfaceId(p, 9827, 242, 2);
                p.frames.setNPCId(p, 1526, 242, 2);
                p.frames.setString(p, "Lanthus", 242, 3);
                p.frames.setString(p,
                        "Hurry up and bank so you don't miss the game!", 242, 4);
                p.frames.setString(p, "That would suck...", 242, 5);
                if (p.alreadyBanked) {
                    p.openBank();
                } else if (p.hasBankPin == 1) {
                    p.frames.showInterface(p, 13);
                    p.bankpin();
                    p.frames.sendMessage(p, "Please enter in your bank pin.");
                } else if (p.hasBankPin == 0) {
                    p.openBank();
                    p.frames.sendMessage(p,
                            "You do not have a bank pin, do ::bankpin to set one.");
                }
                break;

            case 4408:// ==================================start of castle wars
                // objects=========================
                if (p.equipment[1] > 0 && p.equipmentN[1] > 0) {
                    p.frames.sendMessage(p, "You cannot be wearing a cape.");
                } else if (p.equipment[0] > 0 && p.equipmentN[0] > 0) {
                    p.frames.sendMessage(p,
                            "You cannot be wearing anything on your head.");
                } else if (Engine.CWGameTime > 0) {
                    p.frames.sendMessage(p, "Game has already begun.");
                } else if (cape(p) == true) {
                    p.frames.sendMessage(p,
                            "You cannot bring capes or hats into the game.");
                } else {
                    if (Engine.ZamorakTeam > Engine.SaradominTeam
                            || Engine.ZamorakTeam == Engine.SaradominTeam) {
                        p.equipment[1] = 4041;
                        p.equipmentN[1] = 1;
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                        p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                        p.setCoords(2377 + Misc.random(4), 9486 + Misc.random(4), 0);
                        p.frames.sendMessage(p, "You join Saradomin's side..");
                    } else if (Engine.ZamorakTeam < Engine.SaradominTeam) {
                        p.equipment[1] = 4042;
                        p.equipmentN[1] = 1;
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                        p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                        p.setCoords(2420 + Misc.random(4), 9521 + Misc.random(4), 0);
                        p.frames.sendMessage(p, "You join Zamorak's side..");
                    }
                }
                break;
            case 4388:
                if (p.equipment[1] > 0 && p.equipmentN[1] > 0) {
                    p.frames.sendMessage(p, "You cannot be wearing a cape.");
                } else if (p.equipment[0] > 0 && p.equipmentN[0] > 0) {
                    p.frames.sendMessage(p,
                            "You cannot be wearing anything on your head.");
                } else if (Engine.CWGameTime > 0) {
                    p.frames.sendMessage(p, "The game has already started.");
                } else if (cape(p) == true) {
                    p.frames.sendMessage(p,
                            "You cannot bring capes or hats into the game.");
                } else if (Engine.ZamorakTeam < Engine.SaradominTeam) {
                    p.frames.sendMessage(p, "This team has already enough players.");
                } else {
                    p.equipment[1] = 4042;
                    p.equipmentN[1] = 1;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                    p.setCoords(2420 + Misc.random(4), 9521 + Misc.random(4), 0);
                    p.frames.sendMessage(p, "You join Zamorak's side..");
                }
                break;

            case 4902:
                if (p.CWTeam == 0 && p.equipment[3] == 4039) {
                    Engine.SaradominScore += 1;
                    Engine.ZamorakFlag = false;
                    Engine.ZamorakP = 0;
                    p.equipment[3] = -1;
                    p.equipmentN[3] = 0;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                    for (Player pg : Engine.players) {
                        p.frames.sendMessage(pg, p.username
                                + " scored a point for Saradomin.");
                    }
                } else if (Engine.SaradominFlag == true) {
                    p.frames.sendMessage(p, "The flag has already been taken.");
                } else if (p.CWTeam == 1) {
                    Engine.SaradominP = p.playerId;
                    Engine.SaradominFlag = true;
                    Engine.playerItems.addItem(p, p.equipment[3], 1);
                    p.equipment[3] = 4037;
                    p.equipmentN[3] = 1;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                    p.frames.sendMessage(p, "You take Saradomin's flag.");
                } else {
                    p.frames.sendMessage(p, "This is your flag.");
                }
                break;

            case 4903:
                if (p.CWTeam == 1 && p.equipment[3] == 4037) {
                    Engine.ZamorakScore += 1;
                    Engine.SaradominFlag = false;
                    Engine.SaradominP = 0;
                    p.equipment[3] = -1;
                    p.equipmentN[3] = 0;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                    for (Player pg : Engine.players) {
                        p.frames.sendMessage(pg, p.username
                                + " scored a point for Zamorak.");
                    }
                } else if (Engine.ZamorakFlag == true) {
                    p.frames.sendMessage(p, "The flag has already been taken.");
                } else if (p.CWTeam == 0) {
                    Engine.ZamorakP = p.playerId;
                    Engine.ZamorakFlag = true;
                    Engine.playerItems.addItem(p, p.equipment[3], 1);
                    p.equipment[3] = 4039;
                    p.equipmentN[3] = 1;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                    p.frames.sendMessage(p, "You take Zamorak's flag.");
                } else {
                    p.frames.sendMessage(p, "This is your flag.");
                }
                break;

            case 4407:
            case 4390:
            case 4406:
            case 4389:
                p.setCoords(2440 + Misc.random(4), 3085 + Misc.random(10), 0);
                p.OverTimer = 2;
                p.equipment[1] = -1;
                p.equipmentN[1] = 0;
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                if (p.equipment[3] == 4037 && p.CWTeam == 1) {
                    Engine.SaradominFlag = false;
                    p.equipment[3] = -1;
                    p.equipmentN[3] = 0;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                }
                if (p.equipment[3] == 4039 && p.CWTeam == 0) {
                    Engine.ZamorakFlag = false;
                    p.equipment[3] = -1;
                    p.equipmentN[3] = 0;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                }
                break;

            case 4484:
                p.frames.showChatboxInterface(p, 55);
                p.frames.setString(p, "Saradomin: " + Engine.SaradominScore, 55, 1);
                p.frames.setString(p, "Zamorak: " + Engine.ZamorakScore, 55, 2);
                break;

            case 6281:// zammy ladder from z-room
                p.setCoords(2371 + Misc.random(0), 3133 + Misc.random(0), 2);
                p.requestAnim(828, 0);
                p.frames.sendMessage(p,
                        "You climb the ladder out of the zamorak room.");
                break;

            case 4472:
                if (p.CWTeam == 1) {
                    p.setCoords(2370 + Misc.random(0), 3132 + Misc.random(0), 1);
                    p.requestAnim(828, 0);
                    p.frames.sendMessage(p,
                            "You open the trap door and climb down to the zamorak room.");
                } else {
                    p.frames.sendMessage(p,
                            "You must be on the zamorak team to enter here!");
                }
                break;

            case 36365:// zammy ladder to eq.room
                p.setCoords(2378 + Misc.random(0), 3133 + Misc.random(0), 0);
                p.requestAnim(828, 0);
                p.frames.sendMessage(p, "You climb down the ladder.");
                break;

            case 36363:// zammy ladd up from eq.room
                p.setCoords(2378 + Misc.random(0), 3133 + Misc.random(0), 1);
                p.requestAnim(828, 0);
                p.frames.sendMessage(p, "You climb up the ladder.");
                break;

            case 6280:// ladder up from sara room
                p.setCoords(2428 + Misc.random(0), 3074 + Misc.random(0), 2);
                p.requestAnim(828, 0);
                p.frames.sendMessage(p, "You climb out of the saradomin room.");
                break;

            case 4471:// saradomin trap door
                if (p.CWTeam == 0) {
                    p.setCoords(2429 + Misc.random(0), 3074 + Misc.random(0), 1);
                    p.requestAnim(828, 0);
                    p.frames.sendMessage(p,
                            "You open the trap door and climb down to the saradomin room.");
                } else {
                    p.frames.sendMessage(p,
                            "You must be on the saradomin team to enter here!");
                }
                break;

            case 36349:// Sara ladder to eq.room
                p.setCoords(2421 + Misc.random(0), 3074 + Misc.random(0), 0);
                p.requestAnim(828, 0);
                p.frames.sendMessage(p, "You climb down the ladder.");
                break;

            case 36347:// sara ladder up from eq.room
                p.setCoords(2421 + Misc.random(0), 3074 + Misc.random(0), 1);
                p.requestAnim(828, 0);
                p.frames.sendMessage(p, "You climb up the ladder");
                break;
            // ====================end of castle wars
            // bojects=========================

		/*
         * Clan Wars
		 */

            case 28213:
                if (p.AtClanLobby()) {
                    if (p.ClanBattle) {
                        if (p.ClanTimer < 1) {
                            clanWars.addLatePlayer(p);
                            p.frames.sendMessage(p, "The game has already begun.");
                        } else {
                            clanWars.addToField(p);
                        }
                    }
                }
                break;

            case 26972:
            case 28089:
            case 2213:
                if (p.alreadyBanked) {
                    p.openBank();
                } else if (p.hasBankPin == 1) {
                    p.frames.showInterface(p, 13);
                    p.bankpin();
                    p.frames.sendMessage(p, "Please enter in your bank pin.");
                } else if (p.hasBankPin == 0) {
                    p.openBank();
                    p.frames.sendMessage(p,
                            "You do not have a bank pin, do '::bankpin to set one.");
                }
                break;

            case 36575:// Barricade table (saradomin)
                Engine.playerItems.addItem(p, 4053, 1);
                break;
            case 36582:// Barricade table (zamorak)
                Engine.playerItems.addItem(p, 4053, 1);
                break;
            case 36577:// Explode potion table(saradomin)
                Engine.playerItems.addItem(p, 4045, 1);
                break;

            case 36584:// Explode potion table(zamorak)
                Engine.playerItems.addItem(p, 4045, 1);
                break;

            case 36576:// Rope table(saradomin)
                Engine.playerItems.addItem(p, 954, 1);
                break;
            case 36583:// Rope table(zamorak)
                Engine.playerItems.addItem(p, 954, 1);
                break;

            case 36573:// Toolkit table (saradomin)
                Engine.playerItems.addItem(p, 4051, 1);
                break;
            case 36580:// Toolkit table (zamorak)
                Engine.playerItems.addItem(p, 4051, 1);
                break;

            case 36574:// Rock table(saradomin)
                Engine.playerItems.addItem(p, 4043, 1);
                break;
            case 36581:// Rock table(zamorak)
                Engine.playerItems.addItem(p, 4043, 1);
                break;
            case 36585:// pick table(zamorak)
                Engine.playerItems.addItem(p, 1265, 1);
                break;

            case 36578:
                Engine.playerItems.addItem(p, 1265, 1);
                break;

            case 2481: // * Earth Altar (level 9)
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)) {
                    p.addSkillXP(400, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 557, 28);
                    p.frames.sendMessage(p, "You make 28 Earth Runes.");
                } else if (p.skillLvl[20] < 9) {
                    p.frames.sendMessage(p,
                            "You need to be Level 9 Runecrafting to craft these runes.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2478: // * Air Altar
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)
                        && p.skillLvl[20] >= 1) {
                    p.addSkillXP(100, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 556, 28);
                    p.frames.sendMessage(p, "You make 28 Air Runes.");
                } else if (p.skillLvl[20] < 1) {
                    p.frames.sendMessage(p,
                            "You need to be Level 1 Runecrafting to craft these runes.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2479: // * Mind Altar
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)
                        && p.skillLvl[20] >= 5) {
                    p.addSkillXP(200, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 558, 28);
                    p.frames.sendMessage(p, "You make 28 Mind Runes.");
                } else if (p.skillLvl[20] < 5) {
                    p.frames.sendMessage(p,
                            "You need to be Level 1 Runecrafting to craft these runes.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2480: // * Water Altar
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)
                        && p.skillLvl[20] >= 15) {
                    p.addSkillXP(750, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 555, 28);
                    p.frames.sendMessage(p, "You make 28 Water Runes.");
                } else if (p.skillLvl[20] < 15) {
                    p.frames.sendMessage(p,
                            "You need to be Level 15 Runecrafting to craft these runes.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2468: // * Earth Portal
                p.teleportTo(3087, 3497, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 7130: // * Earth Rift
                p.teleportTo(2658, 4839, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                p.frames.sendMessage(p,
                        "The Air, Mind & Water Altars are bugged. You get all runes here.");

                break;

            case 7139: // * Air Rift
                p.teleportTo(2658, 4839, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                p.frames.sendMessage(p,
                        "The Air Altar is Bugged, You've been Redirected to the Earth Altar");
                p.frames.sendMessage(p,
                        "The Earth Altar Gives You - Air, Mind, Water & Earth Runes");

                break;

            case 7137: // * Water Rift
                p.teleportTo(2658, 4839, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                p.frames.sendMessage(p,
                        "The Water Altar is Bugged, You've been Redirected to the Earth Altar");
                p.frames.sendMessage(p,
                        "The Earth Altar Gives You - Air, Mind, Water & Earth Runes");

                break;

            case 7140: // * Mind Rift
                p.teleportTo(2658, 4839, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                p.frames.sendMessage(p,
                        "The Mind Altar is Bugged, You've been Redirected to the Earth Altar");
                p.frames.sendMessage(p,
                        "The Earth Altar Gives You - Air, Mind, Water & Earth Runes");

                break;

            case 2482: // * Fire Altar (level 14)
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)
                        && p.skillLvl[20] >= 14) {
                    p.addSkillXP(690, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 554, 28);
                    p.frames.sendMessage(p, "You make 28 Fire Runes.");
                } else if (p.skillLvl[20] < 14) {
                    p.frames.sendMessage(p,
                            "You need to be level 14 Runecrafting for this.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2469: // * Fire Portal
                p.teleportTo(3087, 3497, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 7129: // * Fire Rift
                p.teleportTo(2658, 4839, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 2483: // * Body Altar (level 20)
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)
                        && p.skillLvl[20] >= 20) {
                    p.addSkillXP(1000, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 558, 28);
                    p.frames.sendMessage(p, "You make 28 Body Runes.");
                } else if (p.skillLvl[20] < 20) {
                    p.frames.sendMessage(p,
                            "You need to be level 20 Runecrafting for this.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2470: // * Body Portal
                p.teleportTo(3087, 3497, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 7131: // * Body Rift
                p.teleportTo(2522, 4839, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 2484:// rune crafting
                if (p.ActionTimer == 0) {
                    p.requestAnim(791, 0);
                    p.ActionTimer = 5;
                    Engine.playerItems.addItem(p, 554 + Misc.random(12),
                            Misc.random(25));
                    p.addSkillXP(75 * p.skillLvl[20], 20);
                    p.frames.sendMessage(p, "You have crafted some runes...");
                } else if (p.skillLvl[20] >= 50 && p.ActionTimer == 0) {
                    p.requestAnim(791, 0);
                    p.ActionTimer = 5;
                    Engine.playerItems.addItem(p, 554 + Misc.random(12),
                            Misc.random(35));
                    p.addSkillXP(75 * p.skillLvl[20], 20);
                    p.frames.sendMessage(p, "You have crafted some runes...");
                } else if (p.skillLvl[20] >= 70 && p.ActionTimer == 0) {
                    p.requestAnim(791, 0);
                    p.ActionTimer = 5;
                    Engine.playerItems.addItem(p, 554 + Misc.random(12),
                            Misc.random(40));
                    Engine.playerItems.addItem(p, 9075, Misc.random(30));
                    p.addSkillXP(1200, 20);
                    p.frames.sendMessage(p, "You have crafted some runes...");
                } else if (p.skillLvl[20] >= 90 && p.ActionTimer == 0) {
                    p.requestAnim(791, 0);
                    p.ActionTimer = 5;
                    Engine.playerItems.addItem(p, 554 + Misc.random(12),
                            Misc.random(60));
                    Engine.playerItems.addItem(p, 9075, Misc.random(60));
                    p.addSkillXP(1100, 20);
                    p.frames.sendMessage(p, "You have crafted some runes...");
                } else if (p.skillLvl[20] >= 99 && p.ActionTimer == 0) {
                    p.requestAnim(791, 0);
                    p.ActionTimer = 5;
                    Engine.playerItems.addItem(p, 554 + Misc.random(12),
                            Misc.random(200));
                    Engine.playerItems.addItem(p, 9075, Misc.random(200));
                    p.addSkillXP(1500, 20);
                    p.frames.sendMessage(p, "You have crafted some runes...");
                } else if (p.ActionTimer >= 1) {
                    p.frames.sendMessage(p, "Dont spam the alter!");
                }
                break;

            case 2471: // * Cosmic Portals
                p.teleportTo(3087, 3497, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 7132: // * Cosmic rift
                p.teleportTo(2142, 4831, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 2487: // * Chaos Altar
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)
                        && p.skillLvl[20] >= 35) {
                    p.addSkillXP(1800, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 562, 28);
                    p.frames.sendMessage(p, "You make 28 Chaos Runes.");
                } else if (p.skillLvl[20] < 35) {
                    p.frames.sendMessage(p,
                            "You need to be Level 35 Runecrafting to craft these runes.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2474: // * Chaos Portals
                p.teleportTo(3087, 3497, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 7134: // * Chaos rift
                p.teleportTo(2271, 4840, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 2486: // * Nature Altar
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)
                        && p.skillLvl[20] >= 44) {
                    p.addSkillXP(2150, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 561, 28);
                    p.frames.sendMessage(p, "You make 28 Nature Runes.");
                } else if (p.skillLvl[20] < 44) {
                    p.frames.sendMessage(p,
                            "You need to be Level 44 Runecrafting to craft these runes.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2473: // * Nature Portals
                p.teleportTo(3087, 3497, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 7133: // * Nature rift
                p.teleportTo(2398, 4841, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 2485: // * Law Altar
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)
                        && p.skillLvl[20] >= 54) {
                    p.addSkillXP(2600, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 563, 28);
                    p.frames.sendMessage(p, "You make 28 Law Runes.");
                } else if (p.skillLvl[20] < 54) {
                    p.frames.sendMessage(p,
                            "You need to be Level 54 Runecrafting to craft these runes.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2472: // * Law Portals
                p.teleportTo(3087, 3497, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 7135: // * Law rift
                p.teleportTo(2464, 4830, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);

                break;

            case 2488: // * Death Altar
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)
                        && p.skillLvl[20] >= 65) {
                    p.addSkillXP(3000, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 560, 28);
                    p.frames.sendMessage(p, "You make 28 Death Runes.");
                } else if (p.skillLvl[20] < 65) {
                    p.frames.sendMessage(p,
                            "You need to be Level 65 Runecrafting to craft these runes.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2489: // * Soul Altar
                if (Engine.playerItems.HasItemAmount(p, 1436, 28)
                        && p.skillLvl[20] >= 90) {
                    p.addSkillXP(4000, 20);
                    p.requestAnim(791, 0);
                    Engine.playerItems.deleteItem(p, 1436, 28);
                    Engine.playerItems.addItem(p, 566, 28);
                    p.frames.sendMessage(p, "You make 28 Soul Runes.");
                } else if (p.skillLvl[20] < 90) {
                    p.frames.sendMessage(p,
                            "You need to be Level 90 Runecrafting to craft these runes.");
                    p.teleportTo(2110, 3915, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                    p.frames.sendMessage(p, "You teleport back to the Abyss.");
                } else {
                    p.frames.sendMessage(p, "You need 28 Rune Essence to do this.");
                }

                break;

            case 2475: // * Death Portals
                p.teleportTo(3087, 3497, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                break;

            case 7136: // * Death rift
                p.teleportTo(2205, 4834, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                break;

            case 1530: {
                if (p.absY == 3438) {
                    p.setCoords(2816, 3439, 0);
                }
                if (p.absY == 3439) {
                    p.setCoords(2816, 3438, 0);
                }
            }
            break;

            case 4469:// cw sara barrier
                if (p.absX == 2426 && p.absY == 3080) {
                    p.setCoords(2426, 3081, 1);
                }
                if (p.absX == 2426 && p.absY == 3081 && p.CWTeam == 0) {
                    p.setCoords(2426, 3080, 1);
                }
                if (p.absX == 2423 && p.absY == 3076) {
                    p.setCoords(2422, 3076, 1);
                }
                if (p.absX == 2422 && p.absY == 3076 && p.CWTeam == 0) {
                    p.setCoords(2423, 3076, 1);
                }
                break;

            case 36484:// cw sara stairs
                p.setCoords(2426, 3074, 3);
                break;
            case 4470:// cw zammy barrier
                if (p.absX == 2373 && p.absY == 3127) {
                    p.setCoords(2373, 3126, 1);
                }
                if (p.absX == 2373 && p.absY == 3126 && p.CWTeam == 1) {
                    p.setCoords(2373, 3127, 1);
                }
                if (p.absX == 2376 && p.absY == 3131) {
                    p.setCoords(2377, 3131, 1);
                }
                if (p.absX == 2377 && p.absY == 3131 && p.CWTeam == 1) {
                    p.setCoords(2376, 3131, 1);
                }
                break;

            case 4467:// zammy door
                if (p.absX == 2385 && p.absY == 3134) {
                    p.setCoords(2384, 3134, 0);
                }
                if (p.absX == 2384 && p.absY == 3134) {
                    p.setCoords(2385, 3134, 0);
                }
                break;
            case 36521:// cw zammy stairs
                p.setCoords(2369, 3127, 2);
                break;

            case 36540:
            case 4415:// cw sara down staircase
                if (p.absX == 2420 && p.absY == 3080) {
                    p.setCoords(2419, 3077, 0);
                }
                if (p.absX == 2430 && p.absY == 3080) {
                    p.setCoords(2427, 3081, 1);
                }
                if (p.absX == 2426 && p.absY == 3074) {
                    p.setCoords(2425, 3077, 2);
                }
                if (p.absX == 2416 && p.absY == 3075) {
                    p.setCoords(2417, 3078, 0);
                }
                if (p.absX == 2382 && p.absY == 3129) {
                    p.setCoords(2383, 3132, 0);
                }
                if (p.absX == 2379 && p.absY == 3127) {
                    p.setCoords(2380, 3130, 0);
                }
                if (p.absX == 2369 && p.absY == 3127) {
                    p.setCoords(2372, 3126, 1);
                }
                if (p.absX == 2373 && p.absY == 3133) {
                    p.setCoords(2374, 3130, 2);
                }
                if (p.absX == 2383 && p.absY == 3132) {
                    p.setCoords(2382, 3129, 0);
                }
                break;

            case 36532:// cw zammy up staircase
                p.setCoords(2379, 3127, 1);
                break;

            case 36523:// cw zammy stairs
                p.setCoords(2373, 3133, 3);
                break;

            case 36480:// cw sara up steps
                p.setCoords(2430, 3080, 2);
                break;

            case 4465:// cw sara door(unlock)
                if (p.absX == 2414 && p.absY == 3073) {
                    p.setCoords(2415, 3073, 0);
                }
                if (p.absX == 2415 && p.absY == 3073) {
                    p.setCoords(2414, 3073, 0);
                }
                break;

            case 36579:// cw sara bandages
                Engine.playerItems.addItem(p, 4049, 1);
                p.requestAnim(881, 0);
                break;
            case 36586:// cw zammy bandages
                Engine.playerItems.addItem(p, 4049, 1);
                p.requestAnim(881, 0);
                break;

            case 36481:// cw sara steps(to catapult)
                p.setCoords(2415, 3075, 0);
                break;

            case 36495:// cw sara steps(to starting room)
                p.setCoords(2420, 3080, 1);
                break;

            case 1276:
            case 1277:
            case 1278:
            case 1279:
            case 1280:
            case 1282:
            case 1283:
            case 1284:
            case 1285:
            case 1286:
            case 1289:
            case 1290:
            case 1291:
            case 1315:
            case 1316:
            case 1318:
            case 1319:
            case 1330:
            case 1331:
            case 1332:
            case 1365:
            case 1383:
            case 1384:
            case 2409:
            case 3033:
            case 3034:
            case 3035:
            case 3036:
            case 3881:
            case 3882:
            case 3883:
            case 5902:
            case 5903:
            case 5904: /* Normal */
            case 1308:
            case 5551:
            case 5552:
            case 5553:/* Willow */
            case 1281:
            case 3037:/* Oak */
            case 1292:
            case 1306:/* Magic */
            case 1307:
            case 4674:/* Maple */
            case 9034:/* Mahogany */
            case 9036:/* Teak */
            case 2023: /* Achey */
            case 1309: /* Yews */
                p.wc = new Woodcutting(p);
                p.wc.startCutting(p.clickId);
                break;
            case 2110:
            case 2090:
            case 11189:
            case 11190:
            case 11191:
            case 2091:/* Copper */
            case 2094:
            case 11186:
            case 11187:
            case 11188:
            case 2095:/* Tin */
            case 2092:
            case 2093:/* Iron */
            case 2100:
            case 2101:/* Silver */
            case 2098:
            case 11183:
            case 11184:
            case 11185:
            case 2099:/* Gold */
            case 2096:
            case 2097:/* Coal */
            case 2102:
            case 2103:/* Mithril */
            case 2104:
            case 2105:/* Adamantite */
            case 2106:
            case 2107:/* Rune */
            case 4028:
            case 4029:
            case 4030:/* Limestone( */
            case 6669:
            case 16687:
            case 6670:
            case 6671:/* Elemental */
                p.mi = new Mining(p);
                p.mi.startCutting(p.clickId);
                break;

            case 23271:
                p.NewEmote = 2750;
                p.jumpDelay = 4;
                if (p.absY > p.clickY) {
                    p.reqWalkQueue(p.absX, p.clickY - 1);
                }
                if (p.absY < p.clickY) {
                    p.reqWalkQueue(p.absX, p.clickY + 2);
                }
                break;

		/*default:
			Misc.println("[" + p.username + "] Unhandled object 1: " +p.clickId);
			break;*/
        }
    }

    private int objectSize(int id) {
        switch (id) {
            case 23271:
                return 2;

            default:
                return 1;
        }
    }
}
