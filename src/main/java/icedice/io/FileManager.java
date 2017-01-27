package icedice.io;

import java.io.*;

import icedice.characters.LoadCharacter;
import icedice.util.Stream;
import icedice.characters.SaveCharacter;
import icedice.players.Player;

public class FileManager {

    /**
     * Byte buffer for storing bytes to be loaded or saved.
     */
    private Stream stream = new Stream(20000, 20000);

    /**
     * Saves a character file.
     *
     * @param p The player to save.
     */
    public void saveCharacter(Player p) throws Exception {
        if (p == null) {
            return;
        }
        /*// p.saveScores();
        p.savePlayer();
        stream.outOffset = 0;
        //stream.writeString("username:" + p.username);
        // stream.writeString("password:" + Misc.stringToLong(p.password));
        //stream.writeString("rights:" + p.rights);
        //stream.writeString("absx:" + p.absX);
        //stream.writeString("absy:" + p.absY);
        stream.writeString("heightLevel:" + p.heightLevel);
        stream.writeString("starter:" + p.starter);
        stream.writeString("started:" + p.started);
        stream.writeString("loginYell:" + p.loginYell);
        stream.writeString("savedBackup:" + p.savedBackup);
        stream.writeString("runenergy:" + p.runEnergy);
        stream.writeString("poison:" + p.poisonTicks);
        stream.writeString("attackstyle:" + p.attackStyle);
        stream.writeString("autoretaliate:" + p.autoRetaliate);
        stream.writeString("specialamount:" + p.specialAmount);
        stream.writeString("skulltimer:" + p.skulledDelay);
        stream.writeString("gender:" + p.gender);
        stream.writeString("Blackmarks:" + p.Blackmarks);
        stream.writeString("SlayerTask:" + p.SlayerTask);
        stream.writeString("SlayerAm:" + p.SlayerAm);
        stream.writeString("Room0:" + p.Room0);
        stream.writeString("Room1:" + p.Room1);
        stream.writeString("Room2:" + p.Room2);
        stream.writeString("Room3:" + p.Room3);
        stream.writeString("Room4:" + p.Room4);
        stream.writeString("Room0Type:" + p.Room0Type);
        stream.writeString("Room1Type:" + p.Room1Type);
        stream.writeString("Room2Type:" + p.Room2Type);
        stream.writeString("Room3Type:" + p.Room3Type);
        stream.writeString("Room4Type:" + p.Room4Type);
        stream.writeString("ZKC:" + p.zkc);
        stream.writeString("BKC:" + p.bkc);
        stream.writeString("AKC:" + p.akc);
        stream.writeString("SKC:" + p.skc);
        stream.writeString("SDC:" + p.sdc);
        stream.writeString("Firecape:" + p.firecape);
        stream.writeString("PKP:" + p.pkp);
        stream.writeString("PKR:" + p.pkr);
        for (int i = 0; i < p.tabStartSlot.length; i++) {
            stream.writeString("tab" + i + ":" + p.tabStartSlot[i]);
        }
        stream.writeString("bankx:" + p.bankX);
        stream.writeString("note:" + (p.withdrawNote ? 1 : 0));
        stream.writeString("insert:" + (p.insertMode ? 1 : 0));
        stream.writeString("HasBankPin:" + p.hasBankPin);
        stream.writeString("BankPin1:" + p.pinNumOne);
        stream.writeString("BankPin2:" + p.pinNumTwo);
        stream.writeString("BankPin3:" + p.pinNumThree);
        stream.writeString("BankPin4:" + p.pinNumFour);
        stream.writeString("xpLock:" + p.xpLock);
        stream.writeString("backuptimer" + p.backuptimer);
        stream.writeString("Ancients:" + p.isAncients);
        stream.writeString("Jailed:" + p.jailed);
        stream.writeString("Pestpoints:" + p.pestpoints);
        stream.writeString("Garden:" + p.Garden);
        stream.writeString("Garden1:" + p.Garden1);
        stream.writeString("Garden2:" + p.Garden2);
        stream.writeString("Garden3:" + p.Garden3);
        stream.writeString("Garden4:" + p.Garden4);
        stream.writeString("DragonSlayer:" + p.DragonSlayer);
        stream.writeString("QuestPoints:" + p.QuestPoints);
        stream.writeString("LoadedBackup:" + p.LoadedBackup);
        stream.writeString("FamiliarType:" + p.FamiliarType);
        stream.writeString("HouseDecor:" + p.HouseDecor);
        stream.writeString("farmingtimer:" + p.FarmingTimer);
        stream.writeString("farmingtype:" + p.FarmingType);
        stream.writeString("farmtype:" + p.FarmType);
        for (int i = 0; i < p.look.length; i++) {
            stream.writeString("look" + i + ":" + p.look[i]);
        }
        for (int i = 0; i < p.color.length; i++) {
            if (p.color[i] > 0) {
                stream.writeString("color" + i + ":" + p.color[i]);
            }
        }
        /*for (int i = 0; i < p.skillLvl.length; i++) {
            stream.writeString(
                    "skill" + i + ":" + p.skillLvl[i] + "," + p.skillXP[i]);
        for (int i = 0; i < p.equipment.length; i++) {
            if (p.equipment[i] > -1 && p.equipmentN[i] > 0) {
                stream.writeString(
                        "equipment" + i + ":" + p.equipment[i] + ","
                                + p.equipmentN[i]);
            }
        }
        for (int i = 0; i < p.items.length; i++) {
            if (p.items[i] > -1 && p.itemsN[i] > 0) {
                stream.writeString(
                        "item" + i + ":" + p.items[i] + "," + p.itemsN[i]);
            }
        }
        for (int i = 0; i < p.bankItems.length; i++) {
            if (p.bankItems[i] > -1 && p.bankItemsN[i] > 0) {
                stream.writeString(
                        "bankitem" + i + ":" + p.bankItems[i] + ","
                                + p.bankItemsN[i]);
            }
        }
        for (int i = 0; i < 200; i++) {
            if (i < p.friends.size()) {
                stream.writeString("friend" + i + ":" + p.friends.get(i));
            }
        }
        for (int i = 0; i < 100; i++) {
            if (i < p.ignores.size()) {
                stream.writeString("ignores" + i + ":" + p.ignores.get(i));
            }
        }
        stream.writeString("null");
        FileOutputStream out = new FileOutputStream("data/characters/mainsave/" + p.username + ".dat");
        out.write(stream.outBuffer, 0, stream.outOffset);
        out.flush();
        out.close();
        out = null;*/
        new SaveCharacter().savePlayer(p);
    }

    public void savebackup(Player p) throws Exception {
        if (p == null) {
            return;
        }
        //p.saveScores();
        //p.savePlayer();
        stream.outOffset = 0;

        //stream.writeString("username:" + p.username);
        // stream.writeString("password:" + Misc.stringToLong(p.password));
        // stream.writeString("Rights:" + p.rights);
        //stream.writeString("starter:" + p.starter);
        stream.writeString("started:" + p.started);
        // stream.writeString("absx:" + p.absX);
        //stream.writeString("absy:" + p.absY);
        stream.writeString("heightLevel:" + p.heightLevel);
        stream.writeString("runenergy:" + p.runEnergy);
        stream.writeString("poison:" + p.poisonTicks);
        stream.writeString("loginYell:" + p.loginYell);
        stream.writeString("savedBackup:" + p.savedBackup);
        stream.writeString("attackstyle:" + p.attackStyle);
        stream.writeString("autoretaliate:" + p.autoRetaliate);
        stream.writeString("specialamount:" + p.specialAmount);
        stream.writeString("skulltimer:" + p.skulledDelay);
        stream.writeString("gender:" + p.gender);
        stream.writeString("Blackmarks:" + p.Blackmarks);
        stream.writeString("SlayerTask:" + p.SlayerTask);
        stream.writeString("SlayerAm:" + p.SlayerAm);
        stream.writeString("Room0:" + p.Room0);
        stream.writeString("Room1:" + p.Room1);
        stream.writeString("Room2:" + p.Room2);
        stream.writeString("Room3:" + p.Room3);
        stream.writeString("Room4:" + p.Room4);
        stream.writeString("Room0Type:" + p.Room0Type);
        stream.writeString("Room1Type:" + p.Room1Type);
        stream.writeString("Room2Type:" + p.Room2Type);
        stream.writeString("Room3Type:" + p.Room3Type);
        stream.writeString("Room4Type:" + p.Room4Type);
        stream.writeString("ZKC:" + p.zkc);
        stream.writeString("BKC:" + p.bkc);
        stream.writeString("AKC:" + p.akc);
        stream.writeString("SKC:" + p.skc);
        stream.writeString("SDC:" + p.sdc);
        stream.writeString("Firecape:" + p.firecape);
        stream.writeString("PKP:" + p.pkp);
        stream.writeString("PKR:" + p.pkr);
        for (int i = 0; i < p.tabStartSlot.length; i++) {
            stream.writeString("tab" + i + ":" + p.tabStartSlot[i]);
        }
        stream.writeString("bankx:" + p.bankX);
        stream.writeString("note:" + (p.withdrawNote ? 1 : 0));
        stream.writeString("insert:" + (p.insertMode ? 1 : 0));
        stream.writeString("HasBankPin:" + p.hasBankPin);
        stream.writeString("BankPin1:" + p.pinNumOne);
        stream.writeString("BankPin2:" + p.pinNumTwo);
        stream.writeString("BankPin3:" + p.pinNumThree);
        stream.writeString("BankPin4:" + p.pinNumFour);
        stream.writeString("xpLock:" + p.xpLock);
        stream.writeString("backuptimer" + p.backuptimer);
        stream.writeString("Ancients:" + p.isAncients);
        stream.writeString("Jailed:" + p.jailed);
        stream.writeString("Pestpoints:" + p.pestpoints);
        stream.writeString("Garden:" + p.Garden);
        stream.writeString("Garden1:" + p.Garden1);
        stream.writeString("Garden2:" + p.Garden2);
        stream.writeString("Garden3:" + p.Garden3);
        stream.writeString("Garden4:" + p.Garden4);
        stream.writeString("DragonSlayer:" + p.DragonSlayer);
        stream.writeString("QuestPoints:" + p.QuestPoints);
        stream.writeString("LoadedBackup:" + p.LoadedBackup);
        stream.writeString("FamiliarType:" + p.FamiliarType);
        stream.writeString("HouseDecor:" + p.HouseDecor);
        stream.writeString("farmingtimer:" + p.FarmingTimer);
        stream.writeString("farmingtype:" + p.FarmingType);
        stream.writeString("farmtype:" + p.FarmType);
        for (int i = 0; i < p.look.length; i++) {
            stream.writeString("look" + i + ":" + p.look[i]);
        }
        for (int i = 0; i < p.color.length; i++) {
            if (p.color[i] > 0) {
                stream.writeString("color" + i + ":" + p.color[i]);
            }
        }
        /*for (int i = 0; i < p.skillLvl.length; i++) {
            stream.writeString(
                    "skill" + i + ":" + p.skillLvl[i] + "," + p.skillXP[i]);
        }*/
        for (int i = 0; i < p.equipment.length; i++) {
            if (p.equipment[i] > -1 && p.equipmentN[i] > 0) {
                stream.writeString(
                        "equipment" + i + ":" + p.equipment[i] + ","
                                + p.equipmentN[i]);
            }
        }
        for (int i = 0; i < p.items.length; i++) {
            if (p.items[i] > -1 && p.itemsN[i] > 0) {
                stream.writeString(
                        "item" + i + ":" + p.items[i] + "," + p.itemsN[i]);
            }
        }
        for (int i = 0; i < p.bankItems.length; i++) {
            if (p.bankItems[i] > -1 && p.bankItemsN[i] > 0) {
                stream.writeString(
                        "bankitem" + i + ":" + p.bankItems[i] + ","
                                + p.bankItemsN[i]);
            }
        }
        for (int i = 0; i < 200; i++) {
            if (i < p.friends.size()) {
                stream.writeString("friend" + i + ":" + p.friends.get(i));
            }
        }
        for (int i = 0; i < 100; i++) {
            if (i < p.ignores.size()) {
                stream.writeString("ignores" + i + ":" + p.ignores.get(i));
            }
        }
        stream.writeString("null");
        FileOutputStream out = new FileOutputStream("data/characters/backup/" + p.username + ".dat");
        out.write(stream.outBuffer, 0, stream.outOffset);
        out.flush();
        out.close();
        out = null;
    }

    /**
     * Loads a character file.
     *
     * @param p The player to save.
     */
    public void loadCharacter(Player p) {
        /*stream.inOffset = 0;
        try {
            FileInputStream in;
            File in2 = null;
            try {
                in = new FileInputStream("data/characters/mainsave/" + p.username + ".dat");
                in2 = new File("data/characters/mainsave/" + p.username + ".txt");
                in2.delete();
            } catch (Exception e3) {
                in = new FileInputStream("data/characters/mainsave/" + p.username + ".txt");
            }
            in.read(stream.inBuffer);
            in.close();
            in = null;
        } catch (Exception e) {
            // Misc.println("Error loading mainsave Folder.");
            return;
        }
        String line;

        try {
            p.loadPlayer();
            while ((line = stream.readString()) != null && line.length() > 0 && !line.equals("null")) {*/
              /*  if (line.startsWith("password:")) {
                    p.password = Misc.longToString(Long.parseLong(line.substring(9)));
                } else if (line.startsWith("rights:")) {
                    p.rights = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("absx:")) {
                    p.teleportToX = Integer.parseInt(line.substring(5));
                } else if (line.startsWith("absy:")) {
                    p.teleportToY = Integer.parseInt(line.substring(5));
                if (line.startsWith("height:")) {
                    p.heightLevel = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("starter:")) {
                    p.starter = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("started:")) {
                    p.started = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("loginYell:")) {
                    p.loginYell = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("savedBackup:")) {
                    p.savedBackup = Integer.parseInt(line.substring(12));
                /*} else if (line.startsWith("rights:")) {
                    p.rights = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("absx:")) {
                    p.teleportToX = Integer.parseInt(line.substring(5));
                } else if (line.startsWith("absy:")) {
                    p.teleportToY = Integer.parseInt(line.substring(5));
                } else if (line.startsWith("heightLevel:")) {
                    p.heightLevel = Integer.parseInt(line.substring(12));
                } else if (line.startsWith("runenergy:")) {
                    p.runEnergy = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("poison:")) {
                    p.poisonTicks = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("attackstyle:")) {
                    p.attackStyle = Integer.parseInt(line.substring(12));
                } else if (line.startsWith("friend")) {
                    long friendName = Long.parseLong(line.substring(line.indexOf(":") + 1));
                    p.friends.add(friendName);
                } else if (line.startsWith("ignore")) {
                    long ignoreName = Long.parseLong(line.substring(line.indexOf(":") + 1));
                    p.ignores.add(ignoreName);
                } else if (line.startsWith("autoretaliate:")) {
                    p.autoRetaliate = Integer.parseInt(line.substring(14));
                /*} else if (line.startsWith("Muted:")) {
                    p.muted = Integer.parseInt(line.substring(6));*/
                /*} else if (line.startsWith("Donator:")) {
                    p.donator = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("xpLock:")) {
                    p.xpLock = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("backuptimer:")) {
                    p.backuptimer = Integer.parseInt(line.substring(12));
                } else if (line.startsWith("Ancients:")) {
                    p.isAncients = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("Jailed:")) {
                    p.jailed = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("ZKC:")) {
                    p.zkc = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("Firecape:")) {
                    p.firecape = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("BKC:")) {
                    p.bkc = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("PKP:")) {
                    p.pkp = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("PKR:")) {
                    p.pkr = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("SKC:")) {
                    p.skc = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("AKC:")) {
                    p.akc = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("HasBankPin:")) {
                    p.hasBankPin = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("BankPin1:")) {
                    p.pinNumOne = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("BankPin2:")) {
                    p.pinNumTwo = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("BankPin3:")) {
                    p.pinNumThree = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("BankPin4:")) {
                    p.pinNumFour = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("bankx:")) {
                    p.bankX = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("note:")) {
                    p.withdrawNote = Integer.parseInt(line.substring(5)) == 1 ? true : false;
                } else if (line.startsWith("insert:")) {
                    p.insertMode = Integer.parseInt(line.substring(7)) == 1 ? true : false;
                } else if (line.startsWith("tab")) {
                    p.tabStartSlot[Integer.parseInt(line.substring(3, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1));
                } else if (line.startsWith("MTR:")) {
                    p.mtr = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("Pestpoints:")) {
                    p.pestpoints = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("SDC:")) {
                    p.sdc = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("specialamount:")) {
                    p.specialAmount = Integer.parseInt(line.substring(14));
                } else if (line.startsWith("skulltimer:")) {
                    p.skulledDelay = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("gender:")) {
                    p.gender = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("Blackmarks:")) {
                    p.Blackmarks = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("SlayerTask:")) {
                    p.SlayerTask = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("SlayerAm:")) {
                    p.SlayerAm = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("DragonSlayer:")) {
                    p.DragonSlayer = Integer.parseInt(line.substring(13));
                } else if (line.startsWith("QuestPoints:")) {
                    p.QuestPoints = Integer.parseInt(line.substring(12));
                } else if (line.startsWith("LoadedBackup:")) {
                    p.LoadedBackup = Integer.parseInt(line.substring(13));
                } else if (line.startsWith("HouseDecor:")) {
                    p.HouseDecor = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("farmingtimer:")) {
                    p.FarmingTimer = Integer.parseInt(line.substring(13));
                } else if (line.startsWith("farmingtype:")) {
                    p.FarmingType = Integer.parseInt(line.substring(12));
                } else if (line.startsWith("farmtype:")) {
                    p.FarmType = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("Room1:")) {
                    p.Room1 = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("Room2:")) {
                    p.Room2 = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("Room3:")) {
                    p.Room3 = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("Room4:")) {
                    p.Room4 = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("Room0:")) {
                    p.Room0 = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("FamiliarType:")) {
                    p.FamiliarType = Integer.parseInt(line.substring(13));
                } else if (line.startsWith("Garden:")) {
                    p.Garden = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("Garden1:")) {
                    p.Garden1 = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("Garden2:")) {
                    p.Garden2 = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("Garden3:")) {
                    p.Garden3 = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("Garden4:")) {
                    p.Garden4 = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("Room1Type:")) {
                    p.Room1Type = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("Room2Type:")) {
                    p.Room2Type = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("Room3Type:")) {
                    p.Room3Type = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("Room4Type:")) {
                    p.Room4Type = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("Room0Type:")) {
                    p.Room0Type = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("look")) {
                    p.look[Integer.parseInt(line.substring(4, 5))] = Integer.parseInt(
                            line.substring(6));
                } else if (line.startsWith("color")) {
                    p.color[Integer.parseInt(line.substring(5, 6))] = Integer.parseInt(
                            line.substring(7));
                /*} else if (line.startsWith("skill")) {
                    p.skillLvl[Integer.parseInt(line.substring(5, line.indexOf(":")))] = Integer.parseInt(
                            line.substring(line.indexOf(":") + 1,
                            line.indexOf(",")));
                    p.skillXP[Integer.parseInt(line.substring(5, line.indexOf(":")))] = Integer.parseInt(
                            line.substring(line.indexOf(",") + 1));
                } else if (line.startsWith("equipment")) {
                    p.equipment[Integer.parseInt(line.substring(9, line.indexOf(":")))] = Integer.parseInt(
                            line.substring(line.indexOf(":") + 1,
                                    line.indexOf(",")));
                    p.equipmentN[Integer.parseInt(line.substring(9, line.indexOf(":")))] = Integer.parseInt(
                            line.substring(line.indexOf(",") + 1));
                } else if (line.startsWith("item")) {
                    p.items[Integer.parseInt(line.substring(4, line.indexOf(":")))] = Integer.parseInt(
                            line.substring(line.indexOf(":") + 1,
                                    line.indexOf(",")));
                    p.itemsN[Integer.parseInt(line.substring(4, line.indexOf(":")))] = Integer.parseInt(
                            line.substring(line.indexOf(",") + 1));
                } else if (line.startsWith("bankitem")) {
                    p.bankItems[Integer.parseInt(line.substring(8, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.indexOf(",")));
                    p.bankItemsN[Integer.parseInt(line.substring(8, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));
                }
            }

        } catch (Exception e) {
        }*/
        new LoadCharacter().loadPlayer(p);
    }

    public void loadbackup(Player p) {
        stream.inOffset = 0;
        try {
            FileInputStream in;
            File in2 = null;
            try {
                in = new FileInputStream("data/characters/backup/" + p.username + ".dat");
                in2 = new File("data/characters/backup/" + p.username + ".txt");
                in2.delete();
            } catch (Exception e3) {
                in = new FileInputStream("data/characters/backup/" + p.username + ".txt");
            }
            in.read(stream.inBuffer);
            in.close();
            in = null;
        } catch (Exception e) {
            //Misc.println("Error loading backup Folder.");
            return;
        }
        String line;

        try {
            p.loadPlayer();
            while ((line = stream.readString()) != null && line.length() > 0 && !line.equals("null")) {
                /*if (line.startsWith("password:")) {
                    p.password = Misc.longToString(Long.parseLong(line.substring(9)));
				} else if (line.startsWith("rights:")) {
                    p.rights = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("absx:")) {
                    p.teleportToX = Integer.parseInt(line.substring(5));
                } else if (line.startsWith("absy:")) {
                    p.teleportToY = Integer.parseInt(line.substring(5));*/
                if (line.startsWith("height:")) {
                    p.heightLevel = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("starter:")) {
                    p.starter = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("started:")) {
                    p.started = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("loginYell:")) {
                    p.loginYell = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("savedBackup:")) {
                    p.savedBackup = Integer.parseInt(line.substring(12));
                /*} else if (line.startsWith("rights:")) {
                    p.rights = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("absx:")) {
                    p.teleportToX = Integer.parseInt(line.substring(5));
                } else if (line.startsWith("absy:")) {
                    p.teleportToY = Integer.parseInt(line.substring(5));*/
                } else if (line.startsWith("heightLevel:")) {
                    p.heightLevel = Integer.parseInt(line.substring(12));
                } else if (line.startsWith("runenergy:")) {
                    p.runEnergy = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("poison:")) {
                    p.poisonTicks = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("attackstyle:")) {
                    p.attackStyle = Integer.parseInt(line.substring(12));
                } else if (line.startsWith("friend")) {
                    long friendName = Long.parseLong(line.substring(line.indexOf(":") + 1));
                    p.friends.add(friendName);
                } else if (line.startsWith("ignore")) {
                    long ignoreName = Long.parseLong(line.substring(line.indexOf(":") + 1));
                    p.ignores.add(ignoreName);
                } else if (line.startsWith("autoretaliate:")) {
                    p.autoRetaliate = Integer.parseInt(line.substring(14));
                /*} else if (line.startsWith("Muted:")) {
                    p.muted = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("Donator:")) {
                    p.donator = Integer.parseInt(line.substring(8));*/
                } else if (line.startsWith("xpLock:")) {
                    p.xpLock = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("backuptimer:")) {
                    p.backuptimer = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("Ancients:")) {
                    p.isAncients = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("Jailed:")) {
                    p.jailed = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("ZKC:")) {
                    p.zkc = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("Firecape:")) {
                    p.firecape = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("BKC:")) {
                    p.bkc = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("PKP:")) {
                    p.pkp = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("PKR:")) {
                    p.pkr = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("SKC:")) {
                    p.skc = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("AKC:")) {
                    p.akc = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("HasBankPin:")) {
                    p.hasBankPin = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("BankPin1:")) {
                    p.pinNumOne = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("BankPin2:")) {
                    p.pinNumTwo = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("BankPin3:")) {
                    p.pinNumThree = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("BankPin4:")) {
                    p.pinNumFour = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("bankx:")) {
                    p.bankX = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("note:")) {
                    p.withdrawNote = Integer.parseInt(line.substring(5)) == 1 ? true : false;
                } else if (line.startsWith("insert:")) {
                    p.insertMode = Integer.parseInt(line.substring(7)) == 1 ? true : false;
                } else if (line.startsWith("tab")) {
                    p.tabStartSlot[Integer.parseInt(line.substring(3, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1));
                } else if (line.startsWith("MTR:")) {
                    p.mtr = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("Pestpoints:")) {
                    p.pestpoints = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("SDC:")) {
                    p.sdc = Integer.parseInt(line.substring(4));
                } else if (line.startsWith("specialamount:")) {
                    p.specialAmount = Integer.parseInt(line.substring(14));
                } else if (line.startsWith("skulltimer:")) {
                    p.skulledDelay = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("gender:")) {
                    p.gender = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("Blackmarks:")) {
                    p.Blackmarks = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("SlayerTask:")) {
                    p.SlayerTask = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("SlayerAm:")) {
                    p.SlayerAm = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("DragonSlayer:")) {
                    p.DragonSlayer = Integer.parseInt(line.substring(13));
                } else if (line.startsWith("QuestPoints:")) {
                    p.QuestPoints = Integer.parseInt(line.substring(12));
                } else if (line.startsWith("LoadedBackup:")) {
                    p.LoadedBackup = Integer.parseInt(line.substring(13));
                } else if (line.startsWith("HouseDecor:")) {
                    p.HouseDecor = Integer.parseInt(line.substring(11));
                } else if (line.startsWith("farmingtimer:")) {
                    p.FarmingTimer = Integer.parseInt(line.substring(13));
                } else if (line.startsWith("farmingtype:")) {
                    p.FarmingType = Integer.parseInt(line.substring(12));
                } else if (line.startsWith("farmtype:")) {
                    p.FarmType = Integer.parseInt(line.substring(9));
                } else if (line.startsWith("Room1:")) {
                    p.Room1 = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("Room2:")) {
                    p.Room2 = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("Room3:")) {
                    p.Room3 = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("Room4:")) {
                    p.Room4 = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("Room0:")) {
                    p.Room0 = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("FamiliarType:")) {
                    p.FamiliarType = Integer.parseInt(line.substring(13));
                } else if (line.startsWith("Garden:")) {
                    p.Garden = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("Garden1:")) {
                    p.Garden1 = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("Garden2:")) {
                    p.Garden2 = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("Garden3:")) {
                    p.Garden3 = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("Garden4:")) {
                    p.Garden4 = Integer.parseInt(line.substring(8));
                } else if (line.startsWith("Room1Type:")) {
                    p.Room1Type = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("Room2Type:")) {
                    p.Room2Type = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("Room3Type:")) {
                    p.Room3Type = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("Room4Type:")) {
                    p.Room4Type = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("Room0Type:")) {
                    p.Room0Type = Integer.parseInt(line.substring(10));
                } else if (line.startsWith("look")) {
                    p.look[Integer.parseInt(line.substring(4, 5))] = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("color")) {
                    p.color[Integer.parseInt(line.substring(5, 6))] = Integer.parseInt(line.substring(7));
                /*} else if (line.startsWith("skill")) {
                    p.skillLvl[Integer.parseInt(line.substring(5, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1,line.indexOf(",")));
                    p.skillXP[Integer.parseInt(line.substring(5, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));*/
                } else if (line.startsWith("equipment")) {
                    p.equipment[Integer.parseInt(line.substring(9, line.indexOf(":")))] = Integer.parseInt(
                            line.substring(line.indexOf(":") + 1,
                                    line.indexOf(",")));
                    p.equipmentN[Integer.parseInt(line.substring(9, line.indexOf(":")))] = Integer.parseInt(
                            line.substring(line.indexOf(",") + 1));
                } else if (line.startsWith("item")) {
                    p.items[Integer.parseInt(line.substring(4, line.indexOf(":")))] = Integer.parseInt(
                            line.substring(line.indexOf(":") + 1,
                                    line.indexOf(",")));
                    p.itemsN[Integer.parseInt(line.substring(4, line.indexOf(":")))] = Integer.parseInt(
                            line.substring(line.indexOf(",") + 1));
                } else if (line.startsWith("bankitem")) {
                    p.bankItems[Integer.parseInt(line.substring(8, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.indexOf(",")));
                    p.bankItemsN[Integer.parseInt(line.substring(8, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));
                }
            }
        } catch (Exception e) {
        }
    }

    public void appendData(String file, String text) {
        BufferedWriter bw = null;

        try {
            FileWriter fileWriter = new FileWriter("data/" + file, true);

            bw = new BufferedWriter(fileWriter);
            bw.write(text);
            bw.newLine();
            bw.flush();
            bw.close();
            fileWriter = null;
            bw = null;
        } catch (Exception exception) {
            // Misc.println("Critical error while writing data: " + file);
        }
    }
}
