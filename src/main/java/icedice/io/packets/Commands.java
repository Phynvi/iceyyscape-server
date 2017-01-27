/*
Created by icedice
 */
package icedice.io.packets;

import icedice.Server;
import icedice.Engine;
import icedice.content.minigames.Survival;
import icedice.npcs.NPC;
import icedice.util.Misc;
import icedice.players.Player;

import java.util.*;

import icedice.Config;

public class Commands implements Packet {

    /**
     * Handles commands, chat text that starts with ::.
     *
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public int[] quests = new int[500];

    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        try {
            String playerCommand = p.stream.readString().toLowerCase();
            String[] cmd = playerCommand.split(" ");

            if (cmd[0].equals("unjail") && p.rights >= 1 && p.jailed == 0) {
                String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                Player p2 = Engine.players[Engine.getIdFromName(person)];
                if (p2 != null) {
                    p.frames.sendMessage(p, "You have just Un-Jailed " + p2.username);
                    p2.frames.sendMessage(p2, "You have just been Un-Jailed by " + p.username);
                    p2.jailed = 0;
                    p2.setCoords(2966, 3378, 0);
                }
            }

            if (p.jailed == 1) {
                p.frames.sendMessage(p, "You are jailed and cannot use commands");
            } else {

                if (p.rights >= 0) {

					/* if (cmd[0].equals("yell")) {
						Client c = new Client();
						
						String message;
						message = "<col=ff0000>[WORLD1] <col=000000>" + p.username + ": <col=0000ff>" + playerCommand.substring(6);
						if (p.rights == 1) {
							message = "<col=ff0000>[WORLD1]<col=0000ff>[MOD]<col=000000>" + p.username + ": <col=0000ff>" + playerCommand.substring(6);
						}
						
						if (p.rights == 2) {
							message = "<col=ff0000>[WORLD1]<col=0000ff>[ADMIN]<col=000000>" + p.username + ": <col=0000ff>" + playerCommand.substring(6);
						}
						
						if (p.username.equals("mod kieran") || p.username.equals("icedice") || p.username.equals("server")) {
							message = "<col=ff0000>[WORLD1]<col=0000ff>[DEV]<col=000000>" + p.username + ": <col=0000ff>" + playerCommand.substring(6);
						}
						
						c.Send(message);
					} */

                    if (cmd[0].equals("gettime")) {
                        if ((Engine.RestartTimer / 2) < 60) {
                            p.frames.sendMessage(p, "Time left: " + ((Engine.RestartTimer / 2)) + " Seconds");
                        } else {
                            p.frames.sendMessage(p, "Time left: " + ((Engine.RestartTimer / 2) / 60) + " Minutes");
                        }
                    }

                    if (cmd[0].equals("maxhits")) {
                        p.frames.sendMessage(p, "Melee:" + Engine.playerCombat.maxMeleeHit(p) + ", Range:" + Engine.playerCombat.maxRangeHit(p));
                    }

                    if (cmd[0].equals("token")) {
                        if (Engine.playerItems.HasItemAmount(p, 995, 2146000000)) {
                            Engine.playerItems.addItem(p, 8851, 1);
                            Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), 2146000000);
                            p.frames.sendMessage(p, "You have changed 2147m to a token.");
                        } else {
                            p.frames.sendMessage(p, "You need max cash to do this");
                        }
                    }

                    if (cmd[0].equals("cash")) {
                        if (Engine.playerItems.HasItemAmount(p, 8851, 1)) {
                            Engine.playerItems.addItem(p, 995, 2146000000);
                            Engine.playerItems.deleteItem(p, 8851, Engine.playerItems.getItemSlot(p, 8851), 1);
                            p.frames.sendMessage(p, "You have changed a token to 2147m.");
                        } else {
                            p.frames.sendMessage(p, "You need a token to do this");
                        }
                    }

                    if (cmd[0].equals("time")) {
                        Calendar cal = Calendar.getInstance();
                        int Hour = cal.get(Calendar.HOUR_OF_DAY);
                        p.frames.sendMessage(p, "Server Time: " + Hour);

                    }

                    if (cmd[0].equals("started")) {
                        p.started = 1;
                        p.frames.sendMessage(p, "You will no longer get rule books when you login");
                    }


                    if (cmd[0].equals("xplock")) {
                        p.xpLock = 1;
                        p.updateReq = true;
                        p.frames.sendMessage(p, "You will no longer gain xp. Type ::xpunlock to get xp again.");
                    }

                    if (cmd[0].equals("xpunlock")) {
                        p.xpLock = 0;
                        p.updateReq = true;
                        p.frames.sendMessage(p, "You can now gain xp again. Type ::xplock to change this.");
                    }

                    if (cmd[0].equals("afk")) {
                        p.frames.sendMessage(p, "type ::back when you are back");
                        p.requestForceChat("Dont talk to me im afk!");
                        p.requestAnim(1353, 1);
                        p.updateReq = true;
                    }

                    if (cmd[0].equals("back")) {
                        p.frames.sendMessage(p, "Welcome back!");
                        p.requestForceChat("Im back, Talk to me noobs!");
                        p.requestAnim(-1, 1);
                        p.updateReq = true;
                    }

                    if (cmd[0].equals("bankpin")) {
                        if (p.hasBankPin == 1) {
                            p.frames.sendMessage(p, "You allready have a bank pin!");
                        } else {
                            p.frames.showInterface(p, 13);
                            p.bankpin();
                            //p.frames.sendMessage(p, "Your bank pin will activate in 6 days.");
                        }
                    }

                    if (cmd[0].equals("resetpin")) {
                        p.hasBankPin = 0;
                        p.frames.sendMessage(p, "You have just reset your bank pin number");
                        //p.frames.sendMessage(p, "Your bank pin will be deleted in 6 days.");
                    }

                    if (cmd[0].equals("whereis")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Server.engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, person + " is located at: " + p2.LocatedAt);
                        } else {
                            p.frames.sendMessage(p, person + " is offline.");
                        }
                    }

                    if (cmd[0].equals("savebackup")) {
                        Engine.fileManager.savebackup(p);
                        p.savedBackup = 1;
                        p.frames.sendMessage(p, "<img=2><col=77FF77>Your backup has been saved!");
                        Misc.println("Save Monitor: [" + p.username + "] Saved account backup file successfully.");
                    }

                    if (cmd[0].equals("loadbackup")) {
                        if (p.savedBackup >= 1) {
                            p.frames.sendMessage(p, "You have no need to load your backup");
                            Misc.println("Backup Minitor: [" + p.username + "] Attempted Item Druping Suspected...");
                        } else {
                            p.frames.sendMessage(p, "<img=2><col=77FF77>Your backup has been loaded!");
                            Engine.fileManager.loadbackup(p);
                            p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                            p.appearanceUpdateReq = true;
                            p.updateReq = true;
                            Misc.println("Backup Minitor: [" + p.username + "] Loaded account backup file successfully.");
                        }
                    }

                    if (cmd[0].equals("pvpmessage")) {
                        p.loginYell = 1;
                        p.frames.sendMessage(p, "[Example]<img=2>Pking Legend, " + p.username + " has logged in!!");
                    }

                    if (cmd[0].equals("defultmessage")) {
                        p.loginYell = 0;
                        p.frames.sendMessage(p, "[Example]<img=2>Player, " + p.username + " has signed on.");
                    }

                    if (cmd[0].equals("yell")) {
                        String yellText = playerCommand.substring(5);
                        Misc.println("Yell Monitor: [" + Misc.getDate() + "] " + p.username + ": " + Misc.optimizeText(yellText));
                        Engine.fileManager.appendData("Chatlog.txt", "Yell Monitor: [" + Misc.getDate() + "] " + p.username + ": " + Misc.optimizeText(yellText));
                        //Iceframe.updateChat();
                        for (Player pz : Engine.players) {
                            if (p.muted == 1) {
                                p.frames.sendMessage(p, "<col=fff000><shad=0202>You are muted and cannot yell!");
                            } else if (p.username.equals("icedice")) {
                                p.frames.sendMessage(pz, "[Owner] <img=1>" + Misc.optimizeText(p.username) + " <img=2> <col=ff0000>" + Misc.optimizeText(yellText));
                                /**} else if (p.username.equals("dexo")) {
                                 p.frames.sendMessage(pz, "[Graphics Mod] <img=0>" + Misc.optimizeText(p.username) + " <img=2> <col=ff0000>" + Misc.optimizeText(yellText));*/
                            } else if (p.jailed == 1) {
                                p.frames.sendMessage(p, "<col=fff000><shad=0202>You are jailed and cannot yell!");
                            } else if (p.donator == 1 && p.rights < 1) {
                                p.frames.sendMessage(pz, "[Donator] " + Misc.optimizeText(p.username) + " <img=2> <col=ff0000>" + Misc.optimizeText(yellText));
                            } else if (p.rights == 1) {
                                p.frames.sendMessage(pz, "[Player Mod] <img=0>" + Misc.optimizeText(p.username) + " <img=2> <col=ff0000>" + Misc.optimizeText(yellText));
                            } else if (p.rights == 2) {
                                p.frames.sendMessage(pz, "[Player Admin] <img=1>" + Misc.optimizeText(p.username) + " <img=2> <col=ff0000>" + Misc.optimizeText(yellText));
                            } else {
                                p.frames.sendMessage(pz, "[Player] " + Misc.optimizeText(p.username) + " <img=2> <col=ff0000>" + Misc.optimizeText(yellText));
                            }
                        }
                    }

                    // Construction


                    if (cmd[0].equals("height")) {
                        p.HouseHeight = p.playerId * 4;
                        p.frames.sendMessage(p, "Your house Height is: " + p.HouseHeight);
                    }


                    //End of Construction


                    if (cmd[0].equals("help")) {
                        p.frames.showInterface(p, 255);
                        p.frames.setString(p, "::home ::yell ::players ::whereis (playername) ::joinchat (playername) ::newname (new clan name) Click on the book which u get its cyan in ur invent! ::cw for castlewars!.", 255, 3);
                    }

                    if (cmd[0].equals("home")) {
                        if (p.AtDuel()) {
                            p.frames.sendMessage(p, "You cannot teleport out of a duel!");
                        } else {
                            p.frames.removeShownInterface(p);
                            p.teleportTo(Config.homeX + Misc.random(2), Config.homeY + Misc.random(2), 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                            p.ResetSkillSuff();
                            p.frames.removeChatboxInterface(p);
                            p.frames.sendMessage(p, "<img=2> You have teleported Home!");
                        }
                    }

                    if (cmd[0].equals("fixskill")) {
                        p.addSkillXP(200000000, 22);
                        p.frames.sendMessage(p, "<img=2>You get 99 construction");
                    }

                    if (cmd[0].equals("head")) {
                        p.prayerIcon = Integer.parseInt(cmd[1]);
                        p.updateReq = p.appearanceUpdateReq = true;
                    }

                    if (cmd[0].equals("ge")) {
                        p.teleportTo(3166, 3481, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                        p.frames.sendMessage(p, "<img=2>Welcome to the Grand Exchange!");
                    }

                    if (cmd[0].equals("revive")) {
                        p.npcType = -1;
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                        p.frames.sendMessage(p, "<img=2>Your turn back into yourself!");
                    }

                    if (cmd[0].startsWith("cw")) {
                        p.setCoords(2442, 3090, 0);
                    }

                    if (cmd[0].equals("kbd")) {
                        p.teleportTo(3140, 3819, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                        p.frames.sendMessage(p, "<img=2>You have teleported to kbd area! Watch out!");
                        p.frames.sendMessage(p, "<img=2>King Black Dragon straight ahead!! Just walk forward!");
                        p.frames.sendMessage(p, "<img=2>NO PKING HERE OR YOU WILL BE BANNED OR IPBANNED!");
                    }


                    if (cmd[0].equals("godwars")) {
                        p.teleportTo(2874, 5307, 2, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                        p.frames.sendMessage(p, "<img=2>Welcome to Godwars Dungeon! Be careful!");

                    }

                    if (cmd[0].equals("edge")) {
                        p.teleportTo(3093, 3493, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                        p.frames.sendMessage(p, "<img=2>You have teleported to Edgeville! Go to Wild and pk!");

                    }

                    if (cmd[0].equals("pvp")) {
                        p.setCoords(3127 + Misc.random(8), 3689 + Misc.random(8), 0);
                        p.requestGFX(482, 0);
                        p.requestAnim(1914, 0);
                        p.frames.sendMessage(p, "<img=2>You Teleport to Pvp! On death items are: SAFE!");
                        p.requestForceChat("RRRRRHHHHHAAAAAAA!!");
                    }

                    if (cmd[0].equals("bandits")) {
                        p.teleportTo(2996, 3468, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                        p.frames.sendMessage(p, "<img=2>You teleport to Bandits Area! Awesome for Training!");
                        p.frames.sendMessage(p, "<img=2>Select Talk to Bandit to get gear for here!");
                    }

                    if (cmd[0].equals("pits")) {
                        p.teleportTo(2399, 5178, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                        p.frames.sendMessage(p, "<img=2>Welcome to Fightpits...");
                    }

                    if (cmd[0].equals("empty")) {
                        for (int y = 0; y < 28; y++) {
                            for (int x = 0; x < 15000; x++) {
                                Engine.playerItems.deleteItem(p, x, y, 2147000000);
                            }
                        }
                        p.frames.sendMessage(p, "<img=2>You have deleted all your items in your inv!");
                    }

                    if (cmd[0].equals("walk")) {
                        p.standEmote = 0x328;
                        p.walkEmote = 0x333;
                        p.runEmote = 0x338;
                        p.runEnergy = 100;
                        p.skillLvl[3] = 99;
                        p.frames.sendMessage(p, "<img=2>I think im going to walk...");
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                    }

                    if (cmd[0].equals("fly")) {
                        p.requestAnim(1500, 0);
                        p.runEmote = 1851;
                        p.walkEmote = 1851;
                        p.standEmote = 1501;
                        p.runEnergy = 99999999;
                        p.frames.sendMessage(p, "Hey! I can see my house from here!");
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                    }

                    if (cmd[0].equals("kq")) {
                        p.teleportTo(3475, 9494, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                        p.frames.sendMessage(p, "Kill kalphiete Queen for Drag-full helm chinebody and d.platelegs!");
                    }

                    if (cmd[0].equals("female")) {
                        p.look[0] = 48; // Hair
                        p.look[1] = 1000; // Beard
                        p.look[2] = 57; // Torso
                        p.look[3] = 64; // Arms
                        p.look[4] = 68; // Bracelets
                        p.look[5] = 77; // Legs
                        p.look[6] = 80; // Shoes
                        p.gender = 1;
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                    }

                    if (cmd[0].equals("male")) {
                        p.look[0] = 5;
                        p.look[1] = 14;
                        p.look[2] = 18;
                        p.look[3] = 27;
                        p.look[4] = 33;
                        p.look[5] = 87;
                        p.look[6] = 42;
                        p.gender = 0;
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                    }

                    if (cmd[0].equals("barrows")) {
                        p.teleportTo(3567, 3290, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                        p.frames.sendMessage(p, "<img=2>Barrows drop rate NOW is <img=2>80%");
                    }

                    if (cmd[0].equals("manual")) {
                        Engine.playerItems.addItem(p, 9003, 1);
                        Engine.playerItems.addItem(p, 600, 1);
                        p.frames.sendMessage(p, "<img=2>Read the Rule Book and Information book");
                    }

                    if (cmd[0].equals("hair")) {
                        p.frames.showInterface(p, 596);
                    }

                    if (cmd[0].equals("hair2")) {
                        p.frames.showInterface(p, 592);
                    }

                    if (cmd[0].equals("char")) {
                        p.frames.showInterface(p, 591);
                    }

                    if (cmd[0].equals("commands")) {
                        p.frames.showInterface(p, 275);
                        p.frames.setString(p, "Command List", 275, 10);

                        p.frames.setString(p, "----NORMAL COMMANDS----", 275, 11);
                        p.frames.setString(p, ", ::Yell, ::Kc, ::Players, ::pm, ::fix, ::revive, ::count, ::time", 275, 12);
                        p.frames.setString(p, "", 275, 13);

                        p.frames.setString(p, "----Account Management----", 275, 14);
                        p.frames.setString(p, "::loadbackup, ::savebackup, ::hair, ::hair2, ::male, ::female", 275, 15);
                        p.frames.setString(p, "::bankpin, ::resetpin, ::xplock, ::xpunlock, ::Char, ::empty", 275, 16);
                        p.frames.setString(p, "::pass, ::look", 275, 17);
                        p.frames.setString(p, "", 275, 18);

                        p.frames.setString(p, "----Information----", 275, 19);
                        p.frames.setString(p, "::Suggestion, ::Reportabuse, ::reportbug, ::note, ::zammyscore", 275, 20);
                        p.frames.setString(p, "::whereis, ::Rep, ::Ancients, ::Modern, ::Lunar, ::myip, ::myinfo", 275, 21);
                        p.frames.setString(p, "::kc, ::sd", 275, 22);
                        p.frames.setString(p, "", 275, 23);

                        p.frames.setString(p, "----Fun Commands----", 275, 24);
                        p.frames.setString(p, "::fly, ::walk, ::afk, ::back, ::skull", 275, 25);
                        p.frames.setString(p, "", 275, 26);

                        p.frames.setString(p, "----House Management----", 275, 27);
                        p.frames.setString(p, "::newroom, ::deleteroom, ::height, ::goinhouse, ::buildingon", 275, 28);
                        p.frames.setString(p, "::buildingoff", 275, 29);
                        p.frames.setString(p, "", 275, 30);

                        p.frames.setString(p, "----Teleports----", 275, 31);
                        p.frames.setString(p, "::Pits, ::Pvp, ::Home, ::kbd, ::cw, ::kq, ::barrows", 275, 32);
                        p.frames.setString(p, "::frozen, ::ge, ::edge, ::ground, ::bandits", 275, 33);
                        p.frames.setString(p, "", 275, 34);

                        p.frames.setString(p, "----Item Spawns----", 275, 35);
                        p.frames.setString(p, "::manual", 275, 36);
                        p.frames.setString(p, "", 275, 37);

                        p.frames.setString(p, "----DONATOR COMMANDS----", 275, 38);
                        p.frames.setString(p, "::Dzone, ::Jad", 275, 39);


                        p.frames.setString(p, "<img=0>----MOD+ COMMANDS----<img=0>", 275, 40);
                        p.frames.setString(p, "::Addblackmark, ::#(5-9)Blackmark, ::Checkblackmarks, ::jail, ::unjail", 275, 41);
                        p.frames.setString(p, "::mute, ::unmute, ::minmute, ::5minmute, ::10min mute, ::15minmute", 275, 42);
                        p.frames.setString(p, "::32minmute, ::kick", 275, 43);
                        p.frames.setString(p, ":<img=1>----ADMIN COMMANDS----<img=1>", 275, 44);

                        p.frames.setString(p, "::teletome, ::Teleto, ::Spec, ::Emptyspec, ::Ipban", 275, 45);
                        p.frames.setString(p, "::Emote, ::Gfx, ::Showinterface, ::Bank", 275, 46);
                        p.frames.setString(p, " ", 275, 47);
                        p.frames.setString(p, " ", 275, 48);
                        p.frames.setString(p, " ", 275, 49);
                        p.frames.setString(p, " ", 275, 50);
                        p.frames.setString(p, " ", 275, 51);
                        p.frames.setString(p, " ", 275, 52);
                        p.frames.setString(p, " ", 275, 53);
                        p.frames.setString(p, " ", 275, 54);
                        p.frames.setString(p, " ", 275, 55);
                        p.frames.setString(p, " ", 275, 56);
                        p.frames.setString(p, " ", 275, 57);
                        p.frames.setString(p, " ", 275, 58);
                    }

                    if (cmd[0].equals("regemote")) {
                        p.requestAnim(-1, 1);
                        p.requestGFX(-1, 1);
                        p.frames.sendMessage(p, "You have gone back to regular emote");
                    }

                    if (cmd[0].equals("fix")) {
                        p.teleportTo(3186, 3440, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                        p.frames.sendMessage(p, "<img=2>Use the banks to get back to your invintory after trading!");
                    }

                    if (cmd[0].equals("ancients")) {
                        p.frames.sendMessage(p, "<img=2> Use the alter at home to convert to ancients!");
                    }

                    if (cmd[0].equals("modern")) {
                        p.frames.sendMessage(p, "<img=2> Use the alter at home to convert to the modern spell book!");
                    }

                    if (cmd[0].equals("lunar")) {
                        p.frames.sendMessage(p, "<img=2> Lunar spells are located in your music tab!");
                    }

                    if (cmd[0].equals("pass")) {
                        p.password = cmd[1];
                        p.frames.sendMessage(p, "Your new password is " + playerCommand.substring(5));
                    }

                    if (cmd[0].equals("self")) {
                        p.npcType = -1;
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                    }

                    if (cmd[0].startsWith("sd")) {
                        p.frames.sendMessage(p, "Your Safety Dungeon Count is: " + (p.sdc));
                    }

                    if (cmd[0].startsWith("kc")) {
                        p.frames.sendMessage(p, "Your Saradomin KC is: " + (p.skc));
                        p.frames.sendMessage(p, "Your Zamorak KC is: " + (p.zkc));
                        p.frames.sendMessage(p, "Your Bandos KC is: " + (p.bkc));
                        p.frames.sendMessage(p, "Your Aramdyl KC is: " + (p.akc));
                    }

                    if (cmd[0].equals("look")) {
                        p.look[Integer.parseInt(cmd[1])] = Integer.parseInt(cmd[2]);
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                    }

                    if (cmd[0].equals("ground")) {
                        p.teleportTo(p.absX, p.absY, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                    }

                    if (cmd[0].equals("myinfo")) {
                        p.frames.sendMessage(p, "<col=ff0000>------------------");
                        p.frames.sendMessage(p, "<col=ff0000><img=1>Your Information<img=1>");
                        p.frames.sendMessage(p, "Your username is:  " + p.username);
                        p.frames.sendMessage(p, "Your pass is:  " + p.password);
                        p.frames.sendMessage(p, "Your combat level is:  " + p.combatLevel);
                        p.frames.sendMessage(p, "Players: " + Server.engine.getPlayerCount() + "");
                        p.frames.sendMessage(p, "Your Saradomin KC is: " + (p.skc));
                        p.frames.sendMessage(p, "Your Zamorak KC is: " + (p.zkc));
                        p.frames.sendMessage(p, "Your Bandos KC is: " + (p.bkc));
                        p.frames.sendMessage(p, "Your Aramdyl KC is: " + (p.akc));
                        p.frames.sendMessage(p, "Your Safety Dungeon Count is: " + (p.sdc));
                        p.frames.sendMessage(p, "Your pk points is: " + (p.pkp));
                        p.frames.sendMessage(p, "If you were temp muted this is how long for: " + (p.mtr));
                        p.frames.sendMessage(p, "This is out of 5, on how far you have come in the Dragon Slayer Quest: " + (p.DragonSlayer));
                        p.frames.sendMessage(p, "You currently have: " + (p.Blackmarks) + " Blackmarks.");
                        p.frames.sendMessage(p, "You currently have: " + (p.pestpoints) + " Pest points.");
                        p.frames.sendMessage(p, "Your bank pin is: " + p.pinNumOne + "" + p.pinNumTwo + "" + p.pinNumThree + "" + p.pinNumFour + ".");
                        if (p.firecape == 0) {
                            p.frames.sendMessage(p, "You cannot wear a fire cape.");
                        }
                        if (p.firecape == 1) {
                            p.frames.sendMessage(p, "You can wear a fire cape.");
                        }
                        if (p.rights == 0) {
                            p.frames.sendMessage(p, "Rank: Player");
                        }
                        if (p.rights == 1) {
                            p.frames.sendMessage(p, "Rank: Mod");
                        }
                        if ((p.rights == 2) && (!p.username.equals("icedice"))) {
                            p.frames.sendMessage(p, "Rank: Admin");
                        }
                        if (p.username.equals("icedice")) {
                            p.frames.sendMessage(p, "Rank: Owner");
                        }
                        if (p.donator == 0) {
                            p.frames.sendMessage(p, "Donator: No");
                        }
                        if (p.donator == 1) {
                            p.frames.sendMessage(p, "Donator: Yes");
                        }
                        p.frames.sendMessage(p, "Your gender is:  " + p.gender);
                        p.frames.sendMessage(p, "You last logged in from: " + Server.socketListener.getAddress(p.socket.socket) + ".");
                        p.frames.sendMessage(p, "<col=ff0000>------------------");

                    }

                    if (cmd[0].equals("party")) {
                        Engine.partyRoom.testInterface(p, Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
                    }

                    if (cmd[0].equals("intitem")) {
                        int[] items = {995};
                        int[] itemsN = {100};
                        try {
                            p.frames.setItems(p, -1, Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), items, itemsN);
                        } catch (Exception ignore) {
                        }
                    }

                    if (cmd[0].equals("myip")) {
                        p.frames.sendMessage(p, "You last logged in from: " + Server.socketListener.getAddress(p.socket.socket) + ".");
                    }

                    if (cmd[0].equals("players")) {
                        p.frames.sendMessage(p, "[system]<img=2><shad=0202>There are curently " + Engine.getPlayerCount() + " Player(s) Online!");

                        int number = 0;
                        for (Player p5 : Engine.players) {
                            if (p5 != null) {
                                number++;
                                p.frames.setString(p, "-iCEYY!SCAPe- Players", 275, 10);
                                p.frames.setString(p, "Players Online: " + number, 275, 11);
                                p.frames.showInterface(p, 275);
                                p.frames.setString(p, "Player's Online", 275, 2);
                                p.frames.setString(p, "(" + p5.playerId + ") " + p5.username + " Combat: " + p5.combatLevel, 275, (11 + number));
                            }
                        }
                    }

                    if (cmd[0].equals("draynor")) {
                        p.teleportTo(3087, 3238, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                    }
                }
                if (p.donator == 1 || p.username.equals("icedice")) {

                    if (cmd[0].equals("jad")) {
                        p.teleportTo(2508, 5177, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                    }

                    if (cmd[0].equals("dzone")) {
                        p.teleportTo(3047, 3383, 1, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                    }

                }

                if (p.rights >= 1) {

                    if (cmd[0].equals("checkblackmarks")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, p2.username + " has " + p2.Blackmarks + " blackmarks.");
                        }
                    }

                    if (cmd[0].equals("jail")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "You have just Jailed " + p2.username);
                            p2.frames.sendMessage(p2, "You have just been Jailed by " + p.username);
                            p2.frames.sendMessage(p2, "All your runes and teleport tabs were deleted to prevent teleporting out.");
                            p2.frames.sendMessage(p2, "<img=1>Icedice<img=2>Spend this time reading the rules and thinking about what you did!");
                            Engine.playerItems.addItem(p2, 9003, 1);
                            Engine.playerItems.addItem(p2, 600, 1);
                            p.frames.setTab(p, 79, 192);
                            p2.setCoords(3014, 3189, 0);
                            p2.jailed = 1;
                        }
                    }

                    if (cmd[0].equals("unjail")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "You have just Un-Jailed " + p2.username);
                            p2.frames.sendMessage(p2, "You have just been Un-Jailed by " + p.username);
                            p2.jailed = 0;
                            p2.setCoords(2966, 3378, 0);
                        }
                    }

                    if (cmd[0].equals("mute")) {
                        if (p.muted == 0) {
                            String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                            if (person == ("icedice")) {
                                p.frames.sendMessage(p, "You have been muted for trying to mute icedice!");
                                p.muted = 1;
                            } else if ((p.muted == 0) && (person.toLowerCase()) != ("icedice")) {
                                Player p2 = Engine.players[Engine.getIdFromName(person)];
                                if (p2 != null) {
                                    p2.muted = 1;
                                    p2.frames.sendMessage(p2, "You have been muted by " + p.username);
                                    p.frames.sendMessage(p, "You have just muted " + p2.username);
                                    p2.updateReq = true;
                                    p2.appearanceUpdateReq = true;
                                }
                            }
                        } else if (p.muted == 1) {
                            p.frames.sendMessage(p, "You cant use this command! You've been muted!");
                        }
                    }

                    if (cmd[0].equals("unmute")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p.muted == 0) {
                            if (p2 != p) {
                                if (p2 != null) {
                                    p2.muted = 0;
                                    p2.frames.sendMessage(p2, "You have been unmuted by " + p.username);
                                    p.frames.sendMessage(p, "You have just unmuted " + p2.username);
                                    p2.updateReq = true;
                                    p2.appearanceUpdateReq = true;
                                }
                            } else if (p2 == p) {
                                p.frames.sendMessage(p, "You cannot unmute yourself!");
                            }
                        } else if (p.muted == 1) {
                            p.frames.sendMessage(p, "You cannot unmute because you ARE muted!");
                        }
                    }

                    if (cmd[0].equals("kick")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Server.engine.players[Engine.getIdFromName(person)];
                        p.frames.logout(p2);
                        p.frames.sendMessage(p, "You just kicked " + p2.username);
                    }

                    if (cmd[0].equals("allquests")) {
                        p.DragonSlayer = 5;
                        p.QuestPoints = 5;
                        p.frames.sendMessage(p, "<img=2>You complete all quests.");
                    }

                    if (cmd[0].equals("lunar") && p.jailed != 1) {
                        p.frames.setTab(p, 79, 430);
                    }

                    if (cmd[0].equals("ancients") && p.jailed != 1) {
                        p.frames.setTab(p, 79, 193);
                    }

                    if (cmd[0].equals("modern") && p.jailed != 1) {
                        p.frames.setTab(p, 79, 192);
                    }

                    if (cmd[0].equals("addblackmark")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "You have just given " + p2.username + " a blackmark.");
                            p2.frames.sendMessage(p2, "<img=2>You have just been given a blackmark!");
                            p2.Blackmarks += 1;
                            p2.updateReq = true;
                        }
                    }

                    if (cmd[0].equals("5blackmark")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "You have just given " + p2.username + " a blackmark.");
                            p2.frames.sendMessage(p2, "<img=2>You have just been given a blackmark!");
                            p2.Blackmarks = 5;
                            if (p.mtr == 0) ;
                            p2.muted = 1;
                            p2.mtr += 86400;
                            p2.updateReq = true;
                        }
                    }

                    if (cmd[0].equals("6blackmark")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "You have just given " + p2.username + " a blackmark.");
                            p2.frames.sendMessage(p2, "<img=2>You have just been given a blackmark!");
                            p2.Blackmarks = 6;
                            if (p.mtr == 0) ;
                            p2.muted = 1;
                            p2.mtr += 172800;
                            p2.updateReq = true;
                        }
                    }

                    if (cmd[0].equals("7blackmark")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "You have just given " + p2.username + " a blackmark.");
                            p2.frames.sendMessage(p2, "<img=2>You have just been given a blackmark!");
                            p2.Blackmarks = 7;
                            if (p.mtr == 0) ;
                            p2.muted = 1;
                            p2.mtr += 259200;
                            p2.updateReq = true;
                        }
                    }

                    if (cmd[0].equals("8blackmark")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "You have just given " + p2.username + " a blackmark.");
                            p2.frames.sendMessage(p2, "<col=ff0000><img=2>You have just been given a blackmark!");
                            p2.Blackmarks = 8;
                            if (p.mtr == 0) ;
                            p2.muted = 1;
                            p2.mtr += 345600;
                            p2.updateReq = true;
                        }
                    }

                    if (cmd[0].equals("9blackmark")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "You have just given " + p2.username + " a blackmark.");
                            p2.frames.sendMessage(p2, "<img=2>You have just been given a blackmark!");
                            p2.Blackmarks = 9;
                            if (p.mtr == 0) ;
                            p2.muted = 1;
                            p2.mtr += 432000;
                            p2.updateReq = true;
                        }
                    }

                    if (cmd[0].equals("minmute")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            if (p.mtr == 0) {
                                p2.muted = 1;
                                p2.mtr += 60;
                                p.frames.sendMessage(p, "Successfuly muted " + p2.username + " for 1 minute.");
                                p2.frames.sendMessage(p2, "You have been muted for 1 minute of playing time.");
                            }
                        }
                    }

                    if (cmd[0].equals("5minmute")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            if (p.mtr == 0) {
                                p2.muted = 1;
                                p2.mtr += 300;
                                p.frames.sendMessage(p, "Successfuly muted " + p2.username + " for 5 minutes.");
                                p2.frames.sendMessage(p2, "You have been muted for 5 minutes of playing time.");
                            }
                        }
                    }

                    if (cmd[0].equals("10minmute")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            if (p.mtr == 0) {
                                p2.muted = 1;
                                p2.mtr += 600;
                                p.frames.sendMessage(p, "Successfuly muted " + p2.username + " for 10 minutes.");
                                p2.frames.sendMessage(p2, "You have been muted for 10 minutes of playing time.");
                            }
                        }
                    }

                    if (cmd[0].equals("15minmute")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            if (p.mtr == 0) {
                                p2.muted = 1;
                                p2.mtr += 900;
                                p.frames.sendMessage(p, "Successfuly muted " + p2.username + " for 15 minutes.");
                                p2.frames.sendMessage(p2, "You have been muted for 15 minutes of playing time.");
                            }
                        }
                    }

                    if (cmd[0].equals("30minmute")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            if (p.mtr == 0) {
                                p2.muted = 1;
                                p2.mtr += 1800;
                                p.frames.sendMessage(p, "Successfuly muted " + p2.username + " for 30 minutes.");
                                p2.frames.sendMessage(p2, "You have been muted for 30 minutes of playing time.");
                            }
                        }
                    }
                }

                if (p.rights >= 2) {

                    if (cmd[0].equals("teletome")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p2.frames.setTab(p2, 7, 208);
                            p2.frames.sendMessage(p2, "You have been Teleported to " + p.username);
                            p2.teleportTo(p.absX, p.absY, p.heightLevel, 3, 0, 8939, 8941, 1576, 0, 388, 0);
                        }
                    }

                    if (cmd[0].equals("teleto")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.setTab(p, 7, 208);
                            p.teleportTo(p2.absX, p2.absY, p2.heightLevel, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
                            p.frames.sendMessage(p, "You Teleport to " + p2.username);
                        }
                    }

                    if (cmd[0].equals("unpnpc")) {
                        p.npcType = -1;
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                    }

                    if (cmd[0].equals("bank")) {
                        p.openBank();
                    }

                    if (cmd[0].equals("showinterface")) {
                        p.frames.showInterface(p, Integer.parseInt(cmd[1]));
                    }

                    if (cmd[0].equals("emote")) {
                        p.requestAnim(Integer.parseInt(cmd[1]), 0);
                    }

                    if (cmd[0].equals("gfx")) {
                        p.requestGFX(Integer.parseInt(cmd[1]), 0);
                    }

                    if (cmd[0].equals("tele")) {
                        int x = Integer.parseInt(cmd[1]);
                        int y = Integer.parseInt(cmd[2]);
                        int h = Integer.parseInt(cmd[3]);
                        p.setCoords(x, y, h);
                    }

                    if (cmd[0].equals("rebuildnpclist")) {
                        p.rebuildNPCList = true;
                    }

                    if (cmd[0].equals("rsstats")) {
                        for (int i1 = 0; i1 < p.skillLvl.length; i1++) {
                            p.skillLvl[i1] = p.getLevelForXP(i1);
                            p.frames.setSkillLvl(p, i1);
                        }
                    }

                    if (cmd[0].equals("energy")) {
                        p.runEnergy = 1000;
                        p.runEnergyUpdateReq = true;
                    }

                    if (cmd[0].equals("spec")) {
                        p.specialAmount = 1000000;
                        p.specialAmountUpdateReq = true;
                    }

                    if (cmd[0].equals("emptyspec")) {
                        p.specialAmount = 0;
                        p.specialAmountUpdateReq = true;
                    }

                    if (cmd[0].equals("coords")) {
                        p.frames.sendMessage(p, "x: " + p.absX + ", y: " + p.absY + ", height : " + p.heightLevel);
                    }


                    if (cmd[0].equals("ipban")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2.rights < 1) {
                            if (p2 != null) {
                                Engine.appendBan(p2.username, p.username);
                                Engine.appendIpBan(p2);
                                p.frames.sendMessage(p, "You have just IpBanned " + p2.username);
                            }
                        }
                    }


                    if (cmd[0].equals("ban")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2.rights < 1) {
                            if (p2 != null) {
                                Engine.appendBan(p2.username, p.username);
                                p.frames.logout(p2);
                                p.frames.sendMessage(p, "You have just banned " + p2.username);
                            }
                        }
                    }

                    if (cmd[0].equalsIgnoreCase("unban")) {
                        String user = playerCommand.substring(6);
                        Engine.appendUnBan(user);
                        p.frames.sendMessage(p, "You have just unbanned " + user);
                    }

                    if (cmd[0].equals("pnpc")) {
                        p.npcType = (Integer.parseInt(cmd[1]));
                        p.appearanceUpdateReq = true;
                        p.updateReq = true;
                    }

                    if (cmd[0].equals("copy")) {
                        String victim = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Server.engine.players[Engine.getIdFromName(victim)];
                        if (p != null) {
                            for (int i = 0; i < p.equipment.length; i++) {
                                p.equipment[i] = p2.equipment[i];
                                p.updateReq = true;
                                p.appearanceUpdateReq = true;
                            }
                        }
                    }

                    if (cmd[0].equals("saveall")) {
                        for (Player pz : Engine.players) {
                            if (pz != null) {
                                Engine.fileManager.savebackup(pz);
                                p.frames.sendMessage(pz, "<img=2><col=77FF77>" + p.username + " has backed up your account!");
                            }
                        }
                        p.frames.sendMessage(p, "<img=2><col=77FF77>You saved all the players backups!");
                    }

                }

                if (p.username.equals("icedice") || p.username.equals("ronnie124") || p.username.equals("ghostpker")) {

                    if (cmd[0].equals("orange")) {
                        p.addSkillXP(200000000, 0);
                        p.addSkillXP(200000000, 1);
                        p.addSkillXP(200000000, 2);
                        p.addSkillXP(200000000, 3);
                        p.addSkillXP(200000000, 4);
                        p.addSkillXP(200000000, 5);
                        p.addSkillXP(200000000, 6);
                        p.addSkillXP(200000000, 7);
                        p.addSkillXP(200000000, 8);
                        p.addSkillXP(200000000, 9);
                        p.addSkillXP(200000000, 10);
                        p.addSkillXP(200000000, 11);
                        p.addSkillXP(200000000, 12);
                        p.addSkillXP(200000000, 13);
                        p.addSkillXP(200000000, 14);
                        p.addSkillXP(200000000, 15);
                        p.addSkillXP(200000000, 16);
                        p.addSkillXP(200000000, 17);
                        p.addSkillXP(200000000, 18);
                        p.addSkillXP(200000000, 19);
                        p.addSkillXP(200000000, 20);
                        p.addSkillXP(200000000, 21);
                        p.addSkillXP(200000000, 22);
                        p.addSkillXP(200000000, 23);
                        p.frames.sendMessage(p, "<img=2>You get 99 everything!");
                    }

                    if (cmd[0].startsWith("sound")) {
                        p.frames.playSound(p, Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
                    }

                    if (cmd[0].startsWith("areasound")) {
                        p.frames.playSoundInArea(p.absX, p.absY, Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), 1, 0);
                    }

                    if (cmd[0].startsWith("globalsound")) {
                        p.frames.playGlobalSound(Integer.parseInt(cmd[1]), 1, 0);
                    }

                    if (cmd[0].equals("npc")) {
                        Server.engine.newNPC(Integer.parseInt(cmd[1]), p.absX,
                                p.absY, p.heightLevel, p.absX + 1, p.absY + 1,
                                p.absX + -1, p.absY + -1, false);
                    }

                    if (cmd[0].equals("anpc")) {
                        NPC npc = Server.engine.newTargetNPC(7, p.absX-7, p.absY, p);
                        //npc.moveNowToPlayer(p);
                    }

                    if (cmd[0].equals("zombiegame")) {
                        Survival.startSurvival(p);
                    }

                    if (cmd[0].equals("object")) {
                        p.frames.createGlobalObject(Integer.parseInt(cmd[1]), 0, p.absX, p.absY, Integer.parseInt(cmd[2]), 10);
                    }

                    if (cmd[0].equals("update")) {
                        for (Player ap : Engine.players) {
                            if (ap != null) {
                            }
                            p.frames.sendMessage(ap, "<col=FF0000>[SYSTEM UPDATE]: <img=2> The server will restart shortly.");
                        }
                    }

                    if (cmd[0].equals("item")) {
                        Engine.playerItems.addItem(p, Integer.parseInt(cmd[1]),
                                Integer.parseInt(cmd[2]));
                    }

                    if (cmd[0].equals("pkskull")) {
                        p.pkIcon = 0;
                        p.updateReq = p.appearanceUpdateReq = true;
                    }

                    if (cmd[0].equals("pkskull2")) {
                        p.pkIcon = 1;
                        p.updateReq = p.appearanceUpdateReq = true;
                    }

                    if (cmd[0].equals("pkskull3")) {
                        p.pkIcon = 2;
                        p.updateReq = p.appearanceUpdateReq = true;
                    }

                    if (cmd[0].equals("pkskull4")) {
                        p.pkIcon = 3;
                        p.updateReq = p.appearanceUpdateReq = true;
                    }

                    if (cmd[0].equals("pkskull5")) {
                        p.pkIcon = 4;
                        p.updateReq = p.appearanceUpdateReq = true;
                    }

                    if (cmd[0].equals("pkskull6")) {
                        p.pkIcon = 5;
                        p.updateReq = p.appearanceUpdateReq = true;
                    }

                    if (cmd[0].equals("pkskull7")) {
                        p.pkIcon = 6;
                        p.updateReq = p.appearanceUpdateReq = true;
                    }

                    if (cmd[0].equals("pkskull8")) {
                        p.pkIcon = 7;
                        p.updateReq = p.appearanceUpdateReq = true;
                    }

                    if (cmd[0].equals("givedonator")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "You have just given donator to " + p2.username + ".");
                            p2.frames.sendMessage(p2, "You have just been made a Donator! Thank you for donating!");
                            p2.donator = 1;
                            Engine.playerItems.addItem(p2, 3101, 1);
                            Engine.playerItems.addItem(p2, 1007, 1);
                        }
                    }
                    if (cmd[0].startsWith("staffmeeting")) {
                        for (Player p5 : Engine.players) {
                            if (p5 == null) {
                                continue;
                            }
                            if (p5.rights >= 1) {
                                p5.teleportTo(p.absX, p.absY, p.heightLevel, 3, 0, 8939, 8941, 1118, 0, 1119, 0);

                            } else {
                            }
                        }
                    }
                    if (cmd[0].startsWith("announce")) {
                        for (Player p5 : Engine.players) {
                            if (p5 == null) {
                                continue;
                            }
                            p5.frames.sendMessage(p5, "<col=980000><shad=0202> Announcement <img=2> " + playerCommand.substring(9));
                        }
                    }
                    if (cmd[0].equals("own")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p2.skillLvl[3] = 99;
                            p2.updateReq = true;
                            p.frames.sendMessage(p, "You just owned their ass!");
                            p2.frames.sendMessage(p2, "<col=fff000><shad=0202>ZZZAAAAAAPPPP!!!!!! You were hit by a giant electric current!");
                            p.requestAnim(2927, 0);
                            p2.requestGFX(1555, 0);
                            p.requestGFX(1601, 0);
                            p2.requestAnim(3171, 0);
                            p2.appendHit(10000, 0);
                            p2.appearanceUpdateReq = true;
                        }
                    }

                    if (cmd[0].equals("removedonator")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p2.donator = 0;
                            p.frames.sendMessage(p, "You have just removed " + p2.username + "'s donator.");
                            p2.frames.sendMessage(p2, "icedice has just removed your donator! You can no longer use the donator shop!");
                        }
                    }

                    if (cmd[0].equals("allgs") && p.username.equalsIgnoreCase("icedice")) {
                        for (Player ap : Engine.players) {
                            if (ap != null) {
                                int id = Integer.parseInt(cmd[1]);
                                int[] anims = {7070, 7071, 7074, 7073, 7072};
                                int[] gfxs = {1221, 1220, 1222, 1223, 1224};
                                ap.requestAnim(anims[id], 0);
                                ap.requestGFX(gfxs[id], 0);
                            }
                        }
                    }

                    if (cmd[0].equals("killall") && p.username.equalsIgnoreCase("icedice")) {
                        for (Player ap : Engine.players) {
                            if (ap != null) {
                                ap.requestGFX(1621, 0);
                                ap.appendHit(255, 0);
                                ap.updateReq = true;
                                ap.appearanceUpdateReq = true;
                            }
                        }
                    }

                    if (cmd[0].equals("gayall")) {
                        for (Player ap : Engine.players) {
                            if (ap != null) {
                                ap.requestForceChat("OMFG! I cant lie any longer!!! I AM GAY!!!");
                                p.requestForceChat("Everyone around me is gay!!!");
                            }
                        }
                    }

                    if (cmd[0].equals("hail")) {
                        for (Player ap : Engine.players) {
                            if (ap != null) {
                                ap.requestForceChat("OMFG!! YOU'RE THE BEST icedice!!");
                                p.requestForceChat("And they all bow to me!!");
                                p.frames.sendMessage(ap, "icedice IS THE BEST!! ALL HAIL icedice!!");
                            }
                        }
                    }

                    if (cmd[0].equals("giveall")) {
                        for (Player ap : Engine.players) {
                            if (ap != null) {
                                Engine.playerItems.addItem(ap, Integer.parseInt(cmd[1]),
                                        Integer.parseInt(cmd[2]));
                            }
                        }
                    }


                    if (cmd[0].equals("giveadmin")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p2.rights = 2;
                            p.frames.sendMessage(p, "You have made " + p2.username + " An Administrator!");
                            p2.frames.sendMessage(p2, "You have been promoted to Administrator by " + p.username);
                            //Engine.playerItems.addItem(p2, 1021, 1);
                            p2.updateReq = true;
                            p2.appearanceUpdateReq = true;
                        }
                    }

                    if (cmd[0].equals("givemod")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p2.rights = 1;
                            p.frames.sendMessage(p, "You have made " + p2.username + " A Moderator!");
                            p2.frames.sendMessage(p2, "You have been promoted to Moderator by " + p.username);
                            //Engine.playerItems.addItem(p2, 1019, 1);
                            p2.updateReq = true;
                            p2.appearanceUpdateReq = true;
                        }
                    }

                    if (cmd[0].equals("getip")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "They last logged in from: " + Server.socketListener.getAddress(p2.socket.socket) + ".");
                        }
                    }

                    if (cmd[0].equals("getpass")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p.frames.sendMessage(p, "There pass is:  " + p2.password);
                        }
                    }

                    if (cmd[0].equals("nick")) {
                        p.username = playerCommand.substring(5);
                        p.frames.sendMessage(p, "Your new username is " + playerCommand.substring(5));
                        p.updateReq = true;
                        p.appearanceUpdateReq = true;
                    }

                    if (cmd[0].equals("demote")) {
                        String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
                        Player p2 = Engine.players[Engine.getIdFromName(person)];
                        if (p2 != null) {
                            p2.rights = 0;
                            p.frames.sendMessage(p, "You have demoted " + p2.username + " to a Player!");
                            p2.frames.sendMessage(p2, "You have been Demoted by  " + p.username);
                            p2.updateReq = true;
                            p2.appearanceUpdateReq = true;
                        }
                    }

                    if (cmd[0].equals("alltome")) {
                        for (Player pz : Engine.players) {
                            if (pz != null) {
                                pz.setCoords(p.absX, p.absY, p.heightLevel);
                            }
                        }
                    }

                    if (cmd[0].equals("console")) {
                        if (Engine.consoleCommand.isCommand(playerCommand.substring(8))) {
                            Engine.consoleCommand.handleCommand(playerCommand.substring(8));
                        }
                    }

                    if (cmd[0].equals("restart")) {
                        System.exit(1);
                    }

                    if (cmd[0].equals("fakeadmin")) {
                        if (cmd[1].equals("on"))
                            Engine.fakeAdmin = true;
                        else
                            Engine.fakeAdmin = false;
                    }
                }
            }

        } catch (Exception error) {
        }
    }
}
