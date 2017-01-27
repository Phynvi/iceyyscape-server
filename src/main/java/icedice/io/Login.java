/*
 * Class Login
 *
 * Created by mod kieran
 */
package icedice.io;

import icedice.Server;
import icedice.Engine;
import icedice.content.Offer;
import icedice.util.Misc;
import icedice.Skills.*;
import icedice.players.Player;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import icedice.Config;

public class Login {

    /**
     * Validate a connection.
     * <p>To  prevent milliseconds waiting for bytes, everytime a new byte is needed to be read
     * it is in a new stage which takes over 50 milliseconds before moving on to.
     * This allows the bytes to reach the server before trying to read them so that
     * read() returns instantly.
     *
     * @param p The Player which the frame should be created for.
     */
    public void login(Player p) {
        if (p == null || p.stream == null) {
            return;
        }
        long serverSessionKey = ((long) (Math.random() * 99999999D) << 32) + (long) (Math.random() * 99999999D);
        long clientSessionKey = 0;
        int returnCode = 2;
        if (p.loginStage < -1) {
            updateServer(p);
        } else if (p.loginStage == 0) {
            try {
                if (!fillStream(p, 2)) {
                    return;
                }
            } catch (Exception e) {
                return;
            }
            int connectionType = p.stream.readUnsignedByte();
            if (connectionType == 15) {
                updateServer(p);
                p.loginStage = -5;
                return;
            }
            if (connectionType != 14) {
                p.loginStage = -1;
                return;
            }
            int longPlayerName = p.stream.readUnsignedByte();
            p.stream.writeByte(0);
            p.stream.writeQWord(serverSessionKey);
            directFlushStream(p);
            p.loginStage++;
        } else if (p.loginStage == 1) {
            try {
                if (!fillStream(p, 3)) {
                    return;
                }
            } catch (Exception e) {
                return;
            }
            int loginType = p.stream.readUnsignedByte();
            if (loginType != 16 && loginType != 18 && loginType != 14) {
                p.loginStage = -1;
                return;
            }
            p.loginStage++;
        } else if (p.loginStage == 2) {
            int loginPacketSize = p.stream.readUnsignedWord();
            int loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2);
            if (loginEncryptPacketSize <= 0) {
                p.loginStage = -1;
                return;
            }
            try {
                if (!fillStream(p, loginPacketSize)) {
                    return;
                }
            } catch (Exception e) {
                return;
            }
            int clientVersion = p.stream.readDWord();
            if (clientVersion != 508) {
                p.loginStage = -1;
                return;
            }
            p.stream.readUnsignedByte();
            p.stream.readUnsignedWord();
            p.stream.readUnsignedWord();
            for (int i = 0; i < 24; i++) {
                int cacheIDX = p.stream.readUnsignedByte();
            }
            String junk = p.stream.readString();
            for (int i = 0; i < 29; i++) {
                int junk2 = p.stream.readDWord();
            }
            loginEncryptPacketSize--;
            int junk29 = p.stream.readUnsignedByte();
            System.out.println(junk29);
            if (junk29 == 10) {
                System.out.println("HD connection");
                p.usingHD = true;
            } else {
                p.usingHD = false;
            }
            int encryption = p.stream.readUnsignedByte();
            if (encryption != 10 && encryption != 64) {
                p.loginStage = -1;
                return;
            }
            clientSessionKey = p.stream.readQWord();
            serverSessionKey = p.stream.readQWord();
            p.username = Misc.longToString(p.stream.readQWord()).toLowerCase().replaceAll("_", " ").trim();
            if (p.username == null) {
                p.loginStage = -1;
                p.username = "";
                return;
            }
            /*if (!SQLUsers.checkPlayer(p)) {
				returnCode = 3;
			}
			if (SQLUsers.checkBan(p)) {
				returnCode = 4;
			}*/

            for (int i = 0; i < p.username.length(); i++) {
                Character c = new Character(p.username.charAt(i));
                if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c)) {
                    p.loginStage = -1;
                    p.username = "";
                    return;
                }
            }
            if (playerOnline(p.username, p)) {
                returnCode = 5;
            }
            String password = p.stream.readString();
            if (password == null) {
                p.loginStage = -1;
                return;
            }
            for (int i = 0; i < password.length(); i++) {
                Character c = new Character(password.charAt(i));
                if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c)) {
                    p.loginStage = -1;
                    return;
                }
            }
            Engine.fileManager.loadCharacter(p);
            //if (password != null && p.password != null && p.password != "" && !password.equals(p.password)) {
            if (p.started == 1 && !password.equals(p.password)) {
                returnCode = 3;
            } else if (p.started == 0) { // new player so set their password
                p.password = password;
            }
            if (ipAmmount(p) > 3) {
                p.loginStage = -1;
                return;
            }
            if (Engine.mapData.needsHome) {
                p.setCoords(Config.homeX, Config.homeY, 0);
                Engine.mapData.needsHome = false;
            }

            if (p.username.equals("icedice")) {
                p.rights = 2;
            }
            p.stream.writeByte(returnCode);
            p.stream.writeByte(p.rights);
            p.stream.writeByte(0);
            p.stream.writeByte(0);
            p.stream.writeByte(0);
            p.stream.writeByte(1);
            p.stream.writeByte(0);
            p.stream.writeByte(p.playerId);
            p.stream.writeByte(0);
            directFlushStream(p);
            for (int i = 0; i < p.skillLvl.length; i++) {
                if (p.skillXP[i] > 200000000) {
                    p.skillXP[i] = 200000000;
                }
                p.skillLvlA[i] = p.getLevelForXP(i);
            }
            if (p.teleportToX == -1 && p.teleportToY == -1) {
                p.setCoords(Config.homeX + Misc.random(3), Config.homeY + Misc.random(3), 0);
            }
            Engine.playerMovement.getNextPlayerMovement(p);
            p.frames.setMapRegion(p);
            p.frames.setWindowPane(p, 548);

            /**if (Engine.wildernessArea(p.absX, p.absY)) {
             p.frames.setString(p, "<col=9FF731>Death Cape - <shad=0202>35PKP", 274, 7);
             p.frames.setString(p, "<col=9FF731>Granite Maul - <shad=0202>25PKP", 274, 8);
             p.frames.setString(p, "<col=9FF731>Full Granite - <shad=0202>40PKP", 274, 9);
             p.frames.setString(p, "<col=9FF731>Dragon Dagger(p++) - <shad=0202>30PKP", 274, 10);
             p.frames.setString(p, "<col=9FF731>10x Sharks - <shad=0202>10PKP", 274, 11);
             p.frames.setString(p, "<col=9FF731>100x Sharks(noted) - <shad=0202>15PKP", 274, 12);
             p.frames.setString(p, "<col=9FF731>Potion Pack - <shad=0202>10PKP", 274, 13);
             p.frames.setString(p, "<col=9FF731>Super Potion Pack - <shad=0202>15PKP", 274, 14);
             p.frames.setString(p, "<col=9FF731>Restore Special - <shad=0202>10PKP", 274, 15);
             p.frames.setString(p, "<col=9FF731>De-Poison - <shad=0202>5PKP", 274, 16);
             for (int i = 0; i < 124; i++) {
             p.frames.setString(p, " ", 274, 17 + i);
             }
             } else if (!Engine.wildernessArea(p.absX, p.absY)) {
             p.frames.setString(p, "Dragon Slayer Quest", 274, 7);
             p.frames.setString(p, "", 274, 8);
             p.frames.setString(p, "", 274, 11);
             if (p.rights == 0) {
             /**p.frames.setString(p, "<col=Dd973b5><shad=0202>------!Get your Starters!-----", 274, 9);
             p.frames.setString(p, "<col=53D7BF>>Pick a starter pack!", 274, 10);
             if (p.starter == 0) {
             p.frames.setString(p, "<col=53D7BF>>Get your Food + Money", 274, 11);
             } else {
             p.frames.setString(p, "<col=53D7BF>>Get your Food", 274, 11);
             }*/
            /**p.frames.setString(p, "", 274, 9);
             p.frames.setString(p, "", 274, 10);
             }
             if (p.rights >= 1) {
             p.frames.setString(p, "<col=Dd973b5><shad=0202>--------!Staff Options!-------", 274, 9);
             p.frames.setString(p, "<col=53D7BF>>Go to staff zone.", 274, 10);
             }
             p.frames.setString(p, "<col=Dd973b5><shad=0202>----------!Teleports!---------", 274, 12);
             p.frames.setString(p, "<col=53D7BF>>Go to Home.", 274, 13);
             p.frames.setString(p, "<col=53D7BF>>Go to Party Room.", 274, 14);
             p.frames.setString(p, "<col=53D7BF>>Go to Shops.", 274, 15);
             p.frames.setString(p, "<col=53D7BF>>Go to Training.", 274, 16);
             p.frames.setString(p, "<col=53D7BF>>Go to Donators Training.", 274, 17);
             p.frames.setString(p, "<col=53D7BF>>Go to King Black Dragon.", 274, 18);
             p.frames.setString(p, "<col=53D7BF>>Go to Safety Dungeon.", 274, 19);
             p.frames.setString(p, "<col=53D7BF>>Go to Barrows.", 274, 20);
             p.frames.setString(p, "<col=53D7BF>>Go to God Wars.", 274, 21);
             p.frames.setString(p, "<col=53D7BF>>Go to Castle Wars.", 274, 22);
             p.frames.setString(p, "<col=53D7BF>>Go to Fight Pits.", 274, 23);
             p.frames.setString(p, "<col=53D7BF>>Go to Pvp.", 274, 24);
             p.frames.setString(p, "----!+Quick Shop+!----", 274, 25);
             p.frames.setString(p, "<col=66FF33><shad=0202>Click and Pick", 274, 26);
             for (int i = 0; i < 124; i++) {
             p.frames.setString(p, " ", 274, 27 + i);
             }
             }*/
            directFlushStream(p);
            if (returnCode != 2) {
                Engine.fileManager.appendData("characters/ips/" + p.username + ".txt", "[" + Server.socketListener.getAddress(p.socket.socket) + "]: failed login.");
                return;
            }
            Engine.fileManager.appendData("characters/ips/" + p.username + ".txt", "[" + Server.socketListener.getAddress(p.socket.socket) + "]: successful login.");
            if (p.username.equals("icedice")) {
                if (p.usingHD) {
                    p.frames.setWindowPane(p, 549);
                } else {
                    p.frames.setWindowPane(p, 548);
                }
            } else {
                p.frames.setWelcome(p);
            }
            p.frames.setInterfaces(p);
            p.frames.setConfigs(p);
            for (int i = 0; i < p.skillLvl.length; i++) {
                p.frames.setSkillLvl(p, i);
            }
            p.frames.setItems(p, 149, 0, 93, p.items, p.itemsN);
            p.frames.setItems(p, 387, 28, 93, p.equipment, p.equipmentN);
            p.frames.setPlayerOption(p, "null", 1);
            p.frames.setPlayerOption(p, "Trade", 2);
            p.frames.setPlayerOption(p, "Duel", 3);
            p.frames.setPlayerOption(p, "Follow", 4);
            p.frames.setConfig(p, 172, p.autoRetaliate);
            p.frames.setConfig(p, 43, p.attackStyle);
            p.playerWeapon.setWeapon();
            p.frames.connecttofserver(p);
            p.friendsLoggedIn();
            p.calculateEquipmentBonus();
            p.online = true;
            p.appearanceUpdateReq = true;
            p.updateReq = true;
            p.runEnergyUpdateReq = true;
            p.specialAmountUpdateReq = true;
            p.wc = new Woodcutting(p);
            p.mi = new Mining(p);

            if (p.jailed == 1) {
                p.frames.sendMessage(p, "<img=2>You're jailed, you teleport back to jail.");
                p.setCoords(3014, 3189, 0);
            }
            for (Offer list : p.GrandExchange.offerList) {
                if (list == null) {
                    continue;
                }
                if (list.id == p.playerId) {
                    if (list.type == 0) {
                        if (!list.completed) {
                            p.frames.setGe(p, list.slot, -1, list.item, list.price, list.amount, list.amount - list.currentAmount);
                        } else if (list.completed || list.aborted) {
                            p.frames.setGe(p, list.slot, -3, list.item, list.price, list.amount, list.amount - list.currentAmount);
                        }
                    } else {
                        if (!list.completed) {
                            p.frames.setGe(p, list.slot, 3, list.item, list.price, list.amount, list.amount - list.currentAmount);
                        } else if (list.completed || list.aborted) {
                            p.frames.setGe(p, list.slot, 5, list.item, list.price, list.amount, list.amount - list.currentAmount);
                        }
                    }
                }
            }
            if (p.FamiliarType > 0) {
                p.frames.setTab(p, 80, 663);
                p.frames.animateInterfaceId(p, 9850, 663, 3);
                p.frames.setNPCId(p, p.FamiliarType, 663, 3);
                Engine.newSummonNPC(p.FamiliarType, p.absX, p.absY + 1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
            }

            if (p.Blackmarks > 0) {
                p.frames.sendMessage(p, "You have " + p.Blackmarks + ", Be carefull");
            }

            if (p.mtr == 0) {
                p.muted = 0;
                p.mtr = -1;
            }
            if (p.AtPits()) {
                p.setCoords(2395 + Misc.random(8), 5170 + Misc.random(4), 0);
            }
            if (p.AtClanField()) {
                p.setCoords(3272, 3685, 0);
            }
            if (p.AtDuel()) {
                p.setCoords(3166, 3485, 0);
            }
            if (p.AtCastleWars()) {
                p.setCoords(2440 + Misc.random(4), 3085 + Misc.random(10), 0);
                p.OverTimer = 2;
                p.equipment[1] = -1;
                p.equipmentN[1] = 0;
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);


                if (p.equipment[3] == 4037 || p.equipment[3] == 4039) {
                    Engine.SaradominFlag = false;
                    Engine.ZamorakFlag = false;
                    p.equipment[3] = -1;
                    p.equipmentN[3] = 0;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                }
            }

            Misc.println(p.username + " has logged in from " + Server.socketListener.getAddress(p.socket.socket));
            /** Login Yells */
            if (p.loginYell == 1 && p.rights == 0) {
                if (p.pkr <= 4 && p.rights == 0 && p.started == 1) {
                    p.frames.yell("<img=2><col=ff4000>Newbie Pker, " + Misc.optimizeText(p.username) + " has logged in.");
                }
                if (p.pkr >= 5 && p.pkr <= 9 && p.rights == 0) {
                    p.frames.yell("<img=2><col=ff4400>Deadly Pker, " + Misc.optimizeText(p.username) + " has logged in.");
                }
                if (p.pkr >= 10 && p.pkr <= 14 && p.rights == 0) {
                    p.frames.yell("<img=2><col=ff0000>Feared Pker, " + Misc.optimizeText(p.username) + " has logged in.");
                }
                if (p.pkr <= 19 && p.pkr >= 15 && p.rights == 0) {
                    p.frames.yell("<img=2><col=0000FF>Mighty Pker, " + Misc.optimizeText(p.username) + " has logged in.");
                }
                if (p.pkr >= 20 && p.pkr <= 34 && p.rights == 0) {
                    p.frames.yell("<img=2><col=0000FF>Respected Pker, " + Misc.optimizeText(p.username) + " has logged in.");
                }
                if (p.pkr >= 35 && p.pkr <= 49 && p.rights == 0) {
                    p.frames.yell("<img=2><col=0000FF>Master of Pking, " + Misc.optimizeText(p.username) + " has logged in!");
                }
                if (p.pkr >= 50 && p.rights == 0) {
                    p.frames.yell("<img=2><col=ff0000><shadow=0000>Pking Legened, " + Misc.optimizeText(p.username) + " has logged in!!");
                }
            } else if (p.loginYell == 0 && p.rights == 0) {
                if (p.donator == 1) {
                    p.frames.yell("<img=2><col=0000FF>Donator, " + Misc.optimizeText(p.username) + " has signed on.");
                }
                if (p.donator == 0 && p.started == 1) {
                    p.frames.yell("<img=2><col=0000FF>Player, " + Misc.optimizeText(p.username) + " has signed on.");
                }
                if (p.started == 0) {
                    p.frames.yell("<img=2><col=000FF>" + p.username + " is brand new, and is now online!!");
                }
            }
            /** End Login Yells */
            if (p.rights == 0 && p.donator == 1) {
                p.frames.sendMessage(p, "Welcome back Donator " + p.username + "!");
                p.frames.sendMessage(p, "<col=ff000><shad=0202>Tired of the same boring login message? Type ::pvpmessage! to change!");
                p.frames.sendMessage(p, "<col=ff000><shad=0202>Type ::defultmessage to change back!");
                p.frames.sendMessage(p, "Thank you for donating, enjoy your stay " + p.username + "!");
            }

            if (p.rights == 1) {
                p.frames.sendMessage(p, "Welcome back Moderator <img=0>" + p.username + "!");
                p.frames.sendMessage(p, "From <img=1>Icedice<img=2>Dont abuse your power or you will be demoted!!");
                p.frames.yell("<img=2><col=0000FF>Moderator, <img=0>" + Misc.optimizeText(p.username) + " has signed on.");
            }

            if (p.username.equals("icedice")) {
                p.frames.sendMessage(p, "Welcome back, <img=1>" + p.username);
                p.frames.yell("<img=2><col=0000FF>Iceyy's one and only owner, <img=1>" + Misc.optimizeText(p.username) + " has signed on!");
            }

            if (p.rights == 0 && p.donator == 0 && p.started == 1) {
                p.frames.sendMessage(p, "<img=2>Welcome to -iCEYY!SCAPe- " + p.username + "!");
                p.frames.sendMessage(p, "<col=ff000><shad=0202>Tired of the same boring login message? Type ::pvpmessage! to change!");
                p.frames.sendMessage(p, "<col=ff000><shad=0202>Type ::defultmessage to change back!");
                p.frames.sendMessage(p, "<img=2>visit the website: iceyy.no-ip.biz");
            }

            if (p.started == 0 && p.rights == 0 && p.donator == 0) {
                p.frames.sendMessage(p, "Welcome to -iCEYY!SCAPe- " + p.username + "!");
                p.frames.sendMessage(p, "<col=ff000><shad=0202><img=2>Teleports are located in the quest tab.");
                p.frames.sendMessage(p, "<col=ff000><shad=0202>Click on the skill you want to train to start training it!");
                p.frames.sendMessage(p, "<col=ff000><shad=0202>Need anyhelp talk to any mod or admin, we are glad to help!");
                p.frames.sendMessage(p, "<col=ff000><shad=0202>visit the website<img=2> iceyy.no-ip.biz");
            }

            if (p.rights == 2 && (!p.username.equals("icedice"))) {
                p.frames.sendMessage(p, "Welcome back Player Administrator <img=1> " + p.username + "");
                p.frames.sendMessage(p, "From <img=1>Icedice<img=2>Dont abuse your power or you will be demoted!!");
                p.frames.yell("<img=2><col=0000FF>Player Administrator, <img=1>" + Misc.optimizeText(p.username) + " has signed on.");
            }
            //Iceframe.updatePlayers();
        }
    }

    /**
     * If the connection is the client's update server than send the keys.
     *
     * @param p The Player which the frame should be created for.
     */
    private void updateServer(Player p) {
        if (p == null) {
            return;
        }
        try {
            if (p.loginStage == 0) {
                if (!fillStream(p, 3)) {
                    return;
                }
                p.stream.writeByte(0);
                directFlushStream(p);
            } else if (p.loginStage == -5) {
                if (!fillStream(p, 8)) {
                    return;
                }
                for (int i = 0; i < Misc.uKeys.length; i++) {
                    p.stream.writeByte(Misc.uKeys[i]);
                }
                directFlushStream(p);
                p.loginStage = -1;
            }
        } catch (Exception exception) {

        }
    }

    /**
     * Make sure the player isn't already online.
     *
     * @param name The name to compare with.
     * @param _p   The Player which the frame should be created for.
     */
    private boolean playerOnline(String name, Player _p) {
        for (Player p : Engine.players) {
            if (p != null && _p.playerId != p.playerId) {
                if (p.username.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a user is banned.
     *
     * @param username The name to check.
     * @return Returns if the name was found.
     */
    public boolean checkBannedUsers(String username) {
        File bw2 = null;
        if (username == null) {
            return true;
        }
        try {
            bw2 = new File("data/banned/user/ " + username + ".txt");
        } catch (Exception error) {
        } finally {
            if (bw2.isFile()) {
                return true;
            }
            return false;
        }
    }

    /**
     * Check and read any incoming bytes.
     *
     * @param p         The Player which the frame should be created for.
     * @param forceRead How many bytes to read from the buffer.
     */
    private boolean fillStream(Player p, int forceRead) throws Exception {
        if (p == null) {
            return false;
        }
        if (forceRead >= 500) {
            return false;
        }
        if (p.socket.avail() < forceRead) {
            return false;
        }
        p.stream.inOffset = 0;
        p.socket.read(forceRead);
        return true;
    }

    /**
     * Send the bytes in the stream's outBuffer directly to the client.
     *
     * @param p The Player which the frame should be created for.
     */
    private void directFlushStream(Player p) {
        if (p == null) {
            return;
        }
        try {
            p.socket.write(p.stream.outBuffer, 0, p.stream.outOffset);
            p.stream.outOffset = 0;
        } catch (Exception exception) {
            //exception.printStackTrace();
        }
    }

    /**
     * hashPassword
     * Hash passwords with SHA512
     * @param password the plain text password to hash
     * @param salt the salt to use when hashing the password
     * @return the hashed password
     */
    private String hashPassword(String password, String salt) {
        String hash = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(Config.getPropertiesValue(salt).getBytes("UTF-8"));
            byte[] bytes = messageDigest.digest(password.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)).substring(1);
            }
            hash = stringBuilder.toString();
        } catch (NoSuchAlgorithmException |UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public int ipAmmount(Player p) {
        String currentIp = Server.socketListener.getAddress(p.socket.socket);
        int count = 0;
        for (Player p2 : Engine.players) {
            if (p2 != null) {
                if (Server.socketListener.getAddress(p2.socket.socket).equals(currentIp))
                    count++;
            }
        }
        return count;
    }
}
