

package icedice.io.packets;


import icedice.Engine;
import icedice.players.items.PlayerItems;
import icedice.util.Misc;
import icedice.Config;
import icedice.players.Player;

public class ItemSelect implements Packet {
    public static int DoPrize[] = {664, 2900, 1173, 1007, 13636, 6611, 1277, 4214, 4215};

    public static int randomDoPrize() {
        return DoPrize[(int) (Math.random() * DoPrize.length)];
    }

    public void handlePacket(Player p, int packetId, int packetSize) {

        int getXpRate = p.xpLock == 0 ? 100 : 0;
        if (p == null || p.stream == null) {
            return;
        }
        PlayerItems pi = new PlayerItems();
        int junk = p.stream.readUnsignedByte();
        int interfaceId = p.stream.readUnsignedWord();

        junk = p.stream.readUnsignedByte();
        int itemId = p.stream.readUnsignedWordBigEndian();
        int itemSlot = p.stream.readUnsignedWordA();

        p.attackPlayer = 0;
        p.attackingPlayer = false;
        if (itemSlot < 0 || itemSlot > p.items.length
                || p.items[itemSlot] != itemId) {
            return;
        }
        if (p.isDead || p.skillLvl[3] < 1) {
            return;
        }

        if (interfaceId == 149) {
            switch (itemId) {
                case 8013: //Grand Exchange
                    p.teleportTo(3165, 3477, 0, 4, 4, 7391, 7391, 678, 0, 678, 0);
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    break;
                case 8008: //Lumby
                    p.teleportTo(3222, 3218, 0, 4, 4, 7391, 7391, 678, 0, 678, 0);
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    break;
                case 8010://Camelot
                    p.teleportTo(2757, 3477, 0, 4, 4, 7391, 7391, 678, 0, 678, 0);
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    break;
                case 8009://Falador
                    p.teleportTo(2964, 3378, 0, 4, 4, 7391, 7391, 678, 0, 678, 0);
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    break;
                case 8007://Varrock
                    p.teleportTo(3213, 3428, 0, 4, 4, 7391, 7391, 678, 0, 678, 0);
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    ;
                    break;
                case 8011://Ardougne
                    p.teleportTo(2661, 3305, 0, 4, 4, 7391, 7391, 678, 0, 678, 0);
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    break;
                case 8012://Watchtower
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    p.teleportTo(2548, 3114, 0, 4, 4, 7391, 7391, 678, 0, 678, 0);
                    break;
                case 526: //Regular Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.regbone * Config.Prayer_XP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 528: //Burnt Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.burntbone * Config.Prayer_XP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

//==========================gs disabling==========================
                case 11694://armadyl
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    pi.addItem(p, 11690, 1);//blade
                    pi.addItem(p, 11702, 1);//hilt
                    p.frames.sendMessage(p, "You dissamtle your Armadyl godsword.");
                    break;

                case 11696://bandos
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    pi.addItem(p, 11690, 1);//blade
                    pi.addItem(p, 11704, 1);//hilt
                    p.frames.sendMessage(p, "You dissamtle your Bandos godsword.");
                    break;

                case 11698://saradomin
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    pi.addItem(p, 11690, 1);//blade
                    pi.addItem(p, 11706, 1);//hilt
                    p.frames.sendMessage(p, "You dissamtle your Saradomin godsword.");
                    break;

                case 11700://zamorak
                    pi.deleteItem(p, itemId, itemSlot, 1);
                    pi.addItem(p, 11690, 1);//blade
                    pi.addItem(p, 11708, 1);//hilt
                    p.frames.sendMessage(p, "You dissamtle your Zamorak godsword.");
                    break;


//==========================end of gs disabling==========================

                case 1538:
                    p.frames.showInterface(p, 547);
                    break;

                case 7633:
                    p.frames.showInterface(p, 275);
                    p.frames.setString(p, "The Great War", 275, 2);
                    p.frames.setString(p, "Kill a Mithril Dragon, and get the dragon piece and use it on the chest", 275, 11);
                    break;

                case 2677:
                    p.frames.showInterface(p, 255);
                    p.frames.setString(p, "Dear " + p.username + ", this scroll will help you find the 4 snowmen. 1 is at varrock, 1 is at the training place, 1 is at godwars, 1 one is located at the saftey Dungeon.", 255, 3);
                    break;

                case 10890:
                    p.frames.showInterface(p, 275);
                    p.frames.setString(p, "The Great War", 275, 2);
                    p.frames.setString(p, "You must travel to the land of ice and kill the spirits", 275, 11);
                    p.frames.setString(p, "that lurk there. Maybe you will just meet your dimise.", 275, 12);
                    break;

                case 10562:
                    p.frames.showInterface(p, 275);
                    p.frames.setString(p, "The Great War", 275, 2);
                    p.frames.setString(p, "Okay well first head back to Netiznot, then go", 275, 11);
                    p.frames.setString(p, "through the paths and fight the Spirit Beast!", 275, 12);
                    p.frames.setString(p, "You should have plenty of prayer pots, food, super sets,", 275, 13);
                    p.frames.setString(p, "and pletny of weapons.", 275, 14);
                    break;

                case 9003:
                    p.frames.showInterface(p, 275);
                    p.frames.setString(p, "-iCEYY!SCAPe- Rule Book", 275, 2);
                    p.frames.setString(p, "<col=77FF77><shad=0202>-iCEYY!RULEs- ", 275, 11);
                    p.frames.setString(p, "<img=2> 1. No Spamming = ipbaned forever...", 275, 12);
                    p.frames.setString(p, "<img=2> 2. Respect All PLayers = mute or ban", 275, 13);
                    p.frames.setString(p, "<img=2> 3. Gliching = mute or ban!", 275, 14);
                    p.frames.setString(p, "<col=ff0000><shad=0202>(if you find a glich report it on forums!)", 275, 15);
                    p.frames.setString(p, "<img=2> 4. Advertizing = ipban", 275, 16);
                    p.frames.setString(p, "<col=ff0000><shad=0202>(report this on forums if you see any one doing it!)", 275, 17);
                    p.frames.setString(p, "<img=2> 5. Asking For Staff over&over = mute", 275, 18);
                    p.frames.setString(p, "<col=ff0000><shad=0202>(If you want staff post an application on forums!)", 275, 19);
                    p.frames.setString(p, "<img=2> 6. Starting Arguments/Fights = mute", 275, 20);
                    p.frames.setString(p, "<img=2> 7. Duping Items = account&and backup deleted and mute", 275, 21);
                    p.frames.setString(p, "<img=2> 8. Keep swearing to a minimum! Can result in mute", 275, 22);
                    p.frames.setString(p, "<img=2> 9. Password Scamming/account hacking = account and backup deleted...", 275, 23);
                    p.frames.setString(p, "<col=ff0000><shad=0202>  NOTE: Report Player breaking Rules", 275, 24);
                    p.frames.setString(p, "<col=ff0000><shad=0202>::reportabuse (username) (what they did)", 275, 25);
                    p.frames.setString(p, "<img=3>!Pvp and Pk rules!<img=3>", 275, 26);
                    p.frames.setString(p, "<img=2>No attacking players in non-pk areas = mute/ban/ipban", 275, 27);
                    p.frames.setString(p, "<col=ff0000><shad=0202>(This includes casting magic at home!)", 275, 28);
                    p.frames.setString(p, "<img=2>No taking ur own death/pvp drop = 1+hour mute", 275, 29);
                    p.frames.setString(p, "<img=2>Dont get mad over a fight = 1+hour mute", 275, 30);
                    p.frames.setString(p, "<col=ff0000><shad=0202>NOTE: If you are going to team, make them fair teams!", 275, 31);
                    p.frames.setString(p, "<img=0>!Mod and Admin Rules!<img=1>", 275, 32);
                    p.frames.setString(p, "<img=2> 1. Muting for no reason = warning(x2)/demote", 275, 33);
                    p.frames.setString(p, "<img=2> 2. Banning for no reason = warning(x1)/demote+mute", 275, 34);
                    p.frames.setString(p, "<img=2> 3. Starting Fights = warning(x1) 24hour mute", 275, 35);
                    p.frames.setString(p, "<img=2> 4. Ingoring People needing help = warning(x1)/demote", 275, 36);
                    p.frames.setString(p, "<img=2> 5. Telling players to break rules = demote+mute", 275, 37);
                    p.frames.setString(p, "<img=2> 6. Spawning+giving away valuble items = demote", 275, 38);
                    p.frames.setString(p, "<img=2> 7. Not be active in-game = demote", 275, 39);
                    p.frames.setString(p, "<img=2> 8. Not treating all player equaly = demote", 275, 40);
                    p.frames.setString(p, "<col=ff0000><shad=0202> NOTE: if you see a staff member break rules...", 275, 41);
                    p.frames.setString(p, "<col=ff0000><shad=0202>...Report them in-game and on forums!", 275, 42);
                    p.frames.setString(p, " ", 275, 43);
                    p.frames.setString(p, " ", 275, 44);
                    p.frames.setString(p, " ", 275, 45);
                    p.frames.setString(p, " ", 275, 46);
                    p.frames.setString(p, " ", 275, 47);
                    break;

                case 13151:
                    p.frames.showInterface(p, 275);
                    p.frames.setString(p, "Safety Dungeons Guide", 275, 2);
                    p.frames.setString(p, "<img=2>Please read this to get an understanding.", 275, 11);
                    p.frames.setString(p, "  If you don't read this book you will not get this new mini game.", 275, 12);
                    p.frames.setString(p, "<img=2>Okay, so for this you have to kill monsters to get Safety Dungeon", 275, 13);
                    p.frames.setString(p, "  Kill Count. You can check my doing ::sd or ::myinfo.", 275, 14);
                    p.frames.setString(p, "<img=2>There's a shortcut to the third floor BUT you would need more", 275, 15);
                    p.frames.setString(p, "  Safety Dungeon Kill Count to go down.", 275, 16);
                    p.frames.setString(p, "<img=2>To go to Second floor you need 10 kill count.", 275, 17);
                    p.frames.setString(p, "<img=2>To go to Third floor you need 20 kill count.", 275, 18);
                    p.frames.setString(p, "<img=2>On Second floor there's a leve, takes 30 kill count, you go to", 275, 19);
                    p.frames.setString(p, "<img=2>the Third floor. To recieve your item from chest takes 5 Safety Dungeon", 275, 20);
                    p.frames.setString(p, "  Kill Count.", 275, 21);
                    break;

                case 600:
                    p.frames.showInterface(p, 275);
                    p.frames.setString(p, "-iCEYY!SCAPe- information", 275, 2);
                    p.frames.setString(p, "<img=2>To train any skill, just click it to train", 275, 11);
                    p.frames.setString(p, "<img-2>All skills are trainable", 275, 12);
                    p.frames.setString(p, "<img=2>Dragon Slayer works %100", 275, 13);
                    p.frames.setString(p, "<img=2>To make money just kill things to get items", 275, 14);
                    p.frames.setString(p, "<img=2>Starters are located in the quest tab", 275, 15);
                    p.frames.setString(p, "<img=2>Teleports are located in the quest tab", 275, 16);
                    p.frames.setString(p, "<img=2>All new Qucik Shop is located in the quest tab", 275, 18);
                    p.frames.setString(p, "<img=2>Almost all npcs have drops", 275, 19);
                    p.frames.setString(p, "<img=2>Working Trade", 275, 20);
                    p.frames.setString(p, "<img=2>Most recent client: v2", 275, 21);
                    p.frames.setString(p, "<img=2>This server is up 24/7", 275, 22);
                    p.frames.setString(p, "<img=2>server ip: ", 275, 23);
                    p.frames.setString(p, "<img=2>Server port: ", 275, 24);
                    p.frames.setString(p, "<img=2>Icedice is the server owner and coder", 275, 25);
                    break;

                case 6199: // mystery box
                    if ((p.donator == 0 && p.rights == 1)) {
                        p.frames.sendMessage(p, "You must be a donator to open this box!!");
                    } else if ((p.donator == 1 || p.rights >= 1)) {

                        Engine.playerItems.addItem(p, randomDoPrize(), 1);
                        Engine.playerItems.deleteItem(p, 6199, 1);
                        p.frames.sendMessage(p, "Hope you enjoy your prize!");

                    }
                    break;

                case 3801:
                    Engine.playerItems.deleteItem(p, 3801, Engine.playerItems.getItemSlot(p, 3801), 1);
                    Engine.playerItems.addItem(p, 3711, 1);
                    p.requestAnim(1327, 0);
                    p.requestForceChat("WOW! Thats some good *burp* stuff man...");
                    p.requestAnim(2770, 0);
                    p.frames.sendMessage(p, "Despite What the bartender said you got drunk....");
                    p.frames.sendMessage(p, "Type ::regemote if you dont want to be drunk any more...");
                    break;

                case 4447:
                    Engine.playerItems.deleteItem(p, 4447, Engine.playerItems.getItemSlot(p, 4447), 1);
                    p.addSkillXP(8000 * p.skillLvl[0], 0);
                    p.addSkillXP(8000 * p.skillLvl[1], 1);
                    p.addSkillXP(8000 * p.skillLvl[2], 2);
                    p.frames.sendMessage(p, "You just gain exp in att,def,stre");
                    break;

                case 4155:
                    if (p.SlayerAm == 0) {
                        p.frames.showChatboxInterface(p, 241);
                        p.frames.animateInterfaceId(p, 9850, 241, 2);
                        p.frames.setNPCId(p, 1599, 241, 2);
                        p.frames.setString(p, "Duradel", 241, 3);
                        p.frames.setString(p, "Talk to me again for another task.", 241, 4);
                    } else {
                        p.frames.showChatboxInterface(p, 241);
                        p.frames.animateInterfaceId(p, 9850, 241, 2);
                        p.frames.setNPCId(p, 1599, 241, 2);
                        p.frames.setString(p, "Duradel", 241, 3);
                        if (p.SlayerTask == 0) {
                            p.frames.setString(p, "You still need to slay " + p.SlayerAm + " more Dragons.", 241, 4);
                        }
                        if (p.SlayerTask == 1) {
                            p.frames.setString(p, "You still need to slay " + p.SlayerAm + " more Guards.", 241, 4);
                        }
                        if (p.SlayerTask == 2) {
                            p.frames.setString(p, "You still need to slay " + p.SlayerAm + " more Giants.", 241, 4);
                        }
                        if (p.SlayerTask == 3) {
                            p.frames.setString(p, "You still need to slay " + p.SlayerAm + " more Ghosts.", 241, 4);
                        }
                        if (p.SlayerTask == 4) {
                            p.frames.setString(p, "You still need to slay " + p.SlayerAm + " more Heroes.", 241, 4);
                        }
                    }
                    break;
                case 12844:
                    p.requestAnim(8990, 0);
                    break;
                case 11256:
                    Engine.playerItems.deleteItem(p, 11256, Engine.playerItems.getItemSlot(p, 11256), 1);
                    Engine.playerItems.addItem(p, 995, Misc.random(30));
                    break;
                case 199:
                    if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                        Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                        Engine.playerItems.addItem(p, 249, 1);
                        p.frames.sendMessage(p, "You clean the guam leaf.");
                        p.addSkillXP(Config.Herblore_XP * 1.1 * Config.bonusXP, 15);
                    }
                    break;

                case 201:
                    if (p.skillLvl[15] >= 5) {
                        if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                            Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                            Engine.playerItems.addItem(p, 251, 1);
                            p.frames.sendMessage(p, "You clean the marrentill leaf.");
                            p.addSkillXP(Config.Herblore_XP * 1.2 * Config.bonusXP, 15);
                        }
                    } else {
                        p.frames.sendMessage(p, "You need level 5 herblore to do this.");
                    }
                    break;

                case 203:
                    if (p.skillLvl[15] >= 11) {
                        if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                            Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                            Engine.playerItems.addItem(p, 253, 1);
                            p.frames.sendMessage(p, "You clean the tarromin leaf.");
                            p.addSkillXP(Config.Herblore_XP * 1.3 * Config.bonusXP, 15);
                        }
                    } else {
                        p.frames.sendMessage(p, "You need level 11 herblore to do this.");
                    }
                    break;

                case 205:
                    if (p.skillLvl[15] >= 20) {
                        if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                            Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                            Engine.playerItems.addItem(p, 255, 1);
                            p.frames.sendMessage(p, "You clean the harralader leaf.");
                            p.addSkillXP(Config.Herblore_XP * 1.4 * Config.bonusXP, 15);
                        }
                    } else {
                        p.frames.sendMessage(p, "You need level 20 herblore to do this.");
                    }
                    break;

                case 207:
                    if (p.skillLvl[15] >= 25) {
                        if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                            Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                            Engine.playerItems.addItem(p, 257, 1);
                            p.frames.sendMessage(p, "You clean the ranarr leaf.");
                            p.addSkillXP(Config.Herblore_XP * 1.5 * Config.bonusXP, 15);
                        }
                    } else {
                        p.frames.sendMessage(p, "You need level 25 herblore to do this.");
                    }
                    break;

                case 209:
                    if (p.skillLvl[15] >= 40) {
                        if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                            Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                            Engine.playerItems.addItem(p, 259, 1);
                            p.frames.sendMessage(p, "You clean the irit leaf.");
                            p.addSkillXP(Config.Herblore_XP * 1.6 * Config.bonusXP, 15);
                        }
                    } else {
                        p.frames.sendMessage(p, "You need level 40 herblore to do this.");
                    }
                    break;

                case 211:
                    if (p.skillLvl[15] >= 48) {
                        if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                            Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                            Engine.playerItems.addItem(p, 261, 1);
                            p.frames.sendMessage(p, "You clean the avantoe leaf.");
                            p.addSkillXP(Config.Herblore_XP * 1.7 * Config.bonusXP, 15);
                        }
                    } else {
                        p.frames.sendMessage(p, "You need level 48 herblore to do this.");
                    }
                    break;

                case 213:
                    if (p.skillLvl[15] >= 54) {
                        if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                            Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                            Engine.playerItems.addItem(p, 263, 1);
                            p.frames.sendMessage(p, "You clean the kwuarm leaf.");
                            p.addSkillXP(Config.Herblore_XP * 1.8 * Config.bonusXP, 15);
                        }
                    } else {
                        p.frames.sendMessage(p, "You need level 54 herblore to do this.");
                    }
                    break;

                case 215:
                    if (p.skillLvl[15] >= 65) {
                        if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                            Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                            Engine.playerItems.addItem(p, 265, 1);
                            p.frames.sendMessage(p, "You clean the cadantine leaf.");
                            p.addSkillXP(Config.Herblore_XP * 1.9 * Config.bonusXP, 15);
                        }
                    } else {
                        p.frames.sendMessage(p, "You need level 65 herblore to do this.");
                    }
                    break;

                case 217:
                    if (p.skillLvl[15] >= 70) {
                        if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                            Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                            Engine.playerItems.addItem(p, 267, 1);
                            p.frames.sendMessage(p, "You clean the dwarf weed leaf.");
                            p.addSkillXP(Config.Herblore_XP * 2 * Config.bonusXP, 15);
                        }
                    } else {
                        p.frames.sendMessage(p, "You need level 70 herblore to do this.");
                    }
                    break;

                case 219:
                    if (p.skillLvl[15] >= 75) {
                        if (Engine.playerItems.HasItemAmount(p, itemId, 1)) {
                            Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                            Engine.playerItems.addItem(p, 269, 1);
                            p.frames.sendMessage(p, "You clean the torstol leaf.");
                            p.addSkillXP(Config.Herblore_XP * 2.1 * Config.bonusXP, 15);
                        }
                    } else {
                        p.frames.sendMessage(p, "You need level 75 herblore to do this.");
                    }
                    break;


                case 530: //Bat Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.batbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 532: //Big Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.bigbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 534: //Baby Dragon Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.babydragonbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 536: //Dragon Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.dragonbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 2859: //Wolf Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.wolfbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 3123: //Shaikahan Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.shaikbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 3125: //Jogre Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.jogrebone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 3127: //Burnt Jogre Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.burntjogrebone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 3179: //Monkey Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.monkeybone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 4812: //Zogre Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.zogrebone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 4830: //Fayrg Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.fayrgbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 4832: //Raurg Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.raurgbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 4834: //Ourg Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.ourgbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 6729: //Dagganoth Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.dagbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;

                case 6812: //Wyvern Bone Burying
                    if (p.buryDelay <= 0) {
                        p.buryDelay = 3;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.addSkillXP(Config.wyvbone * Config.Prayer_XP * Config.bonusXP, 5);
                        p.requestAnim(827, 0);
                    }
                    break;
                case 391:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(22, true);
                        p.requestAnim(829, 0);
                    }
                    break;
                case 4558:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(3, true);
                        p.requestAnim(829, 0);
                    }
                    break;
                case 4559:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(4, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 4560:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(3, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 4561:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(4, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 4562:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(3, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 4563:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(4, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 4564:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(3, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 10476:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(4, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 385:
                case 397:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(20, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 315:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(3, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 319:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(1, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 325:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(4, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 329:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(9, true);
                        p.requestAnim(829, 0);
                    }
                    break;
                case 4049:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(8, true);
                    }
                    break;

                case 339:
                case 333:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(7, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 347:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(5, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 351:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(8, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 355:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(6, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 361:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(10, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 365:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(13, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 7946:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(16, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 379:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(12, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 373:
                    if (p.eatDelay <= 0) {
                        p.eatDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.updateHP(16, true);
                        p.requestAnim(829, 0);
                    }
                    break;

                case 3024:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 5, (int) (p.getLevelForXP(5) * 0.26) + 8, 0,
                                true);
                        pi.addItem(p, 3026, 1);
                    }
                    break;

                case 3026:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 5, (int) (p.getLevelForXP(5) * 0.26) + 8, 0,
                                true);
                        pi.addItem(p, 3028, 1);
                    }
                    break;

                case 3028:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 5, (int) (p.getLevelForXP(5) * 0.26) + 8, 0,
                                true);
                        pi.addItem(p, 3030, 1);
                    }
                    break;

                case 3030:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.26) + 8, 0,
                                true);
                        changeStat(p, 5, (int) (p.getLevelForXP(5) * 0.26) + 8, 0,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 11949:
                    p.frames.showInterface(p, 659);
                    p.frames.sendMessage(p, "You shake the snow globe.");
                    Engine.playerItems.addItem(p, 11951, 28);
                    break;

                case 2430:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.3) + 10, 0,
                                true);
                        pi.addItem(p, 127, 1);
                    }
                    break;

                case 127:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.3) + 10, 0,
                                true);
                        pi.addItem(p, 129, 1);
                    }
                    break;

                case 129:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.3) + 10, 0,
                                true);
                        pi.addItem(p, 131, 1);
                    }
                    break;

                case 131:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.3) + 10, 0,
                                true);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.3) + 10, 0,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 6685:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        p.updateHP((int) (p.getLevelForXP(3) * 0.15) + 2, true);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.2) + 2, 1,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.1) + 2, 0,
                                false);
                        pi.addItem(p, 6687, 1);
                    }
                    break;

                case 6687:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        p.updateHP((int) (p.getLevelForXP(3) * 0.15) + 2, true);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.2) + 2, 1,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.1) + 2, 0,
                                false);
                        pi.addItem(p, 6689, 1);
                    }
                    break;

                case 6689:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        p.updateHP((int) (p.getLevelForXP(3) * 0.15) + 2, true);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.2) + 2, 1,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.1) + 2, 0,
                                false);
                        pi.addItem(p, 6691, 1);
                    }
                    break;

                case 6691:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        p.updateHP((int) (p.getLevelForXP(3) * 0.15) + 2, true);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.2) + 2, 1,
                                true);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.1) + 2, 0,
                                false);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.1) + 2, 0,
                                false);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 113:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 115, 1);
                    }
                    break;

                case 2434:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 5, (int) (p.getLevelForXP(5) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 139, 1);
                    }
                    break;

                case 143:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 5, (int) (p.getLevelForXP(5) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 141:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 5, (int) (p.getLevelForXP(5) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 143, 1);
                    }
                    break;

                case 139:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 5, (int) (p.getLevelForXP(5) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 141, 1);
                    }
                    break;

                case 3040:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 3042, 1);
                    }
                    break;

                case 3042:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 3044, 1);
                    }
                    break;

                case 3044:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 3046, 1);
                    }
                    break;

                case 3046:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 6, (int) (p.getLevelForXP(6) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 115:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 117, 1);
                    }
                    break;
                case 117:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 119, 1);
                    }
                    break;

                case 119:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 2432:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 133, 1);
                    }
                    break;

                case 133:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 135, 1);
                    }
                    break;

                case 135:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 137, 1);
                    }
                    break;

                case 137:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 2446:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        Engine.poison.startPoison(p, 0);
                        p.frames.sendMessage(p, "The poison vanishs from your blood.");
                        pi.addItem(p, 175, 1);
                    }
                    break;
                case 175:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        Engine.poison.startPoison(p, 0);
                        p.frames.sendMessage(p, "The poison vanishs from your blood.");
                        pi.addItem(p, 177, 1);
                    }
                    break;

                case 177:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        Engine.poison.startPoison(p, 0);
                        p.frames.sendMessage(p, "The poison vanishs from your blood.");
                        pi.addItem(p, 179, 1);
                    }
                    break;

                case 179:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        Engine.poison.startPoison(p, 0);
                        p.frames.sendMessage(p, "The poison vanishs from your blood.");
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 2444:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.1) + 4, 1,
                                true);
                        pi.addItem(p, 169, 1);
                    }
                    break;

                case 169:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.1) + 4, 1,
                                true);
                        pi.addItem(p, 171, 1);
                    }
                    break;

                case 171:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.1) + 4, 1,
                                true);
                        pi.addItem(p, 173, 1);
                    }
                    break;

                case 173:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 4, (int) (p.getLevelForXP(4) * 0.1) + 4, 1,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 2428:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 121, 1);
                    }
                    break;

                case 121:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 123, 1);
                    }
                    break;

                case 123:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 125, 1);
                    }
                    break;

                case 125:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.1) + 3, 1,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 2440:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 157, 1);
                    }
                    break;

                case 157:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 159, 1);
                    }
                    break;

                case 159:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 161, 1);
                    }
                    break;

                case 161:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 2, (int) (p.getLevelForXP(2) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 2442:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 163, 1);
                    }
                    break;

                case 163:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 165, 1);
                    }
                    break;

                case 165:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 167, 1);
                    }
                    break;

                case 167:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 1, (int) (p.getLevelForXP(1) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                case 2436:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 145, 1);
                    }
                    break;

                case 145:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 147, 1);
                    }
                    break;

                case 147:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 149, 1);
                    }
                    break;

                case 149:
                    if (p.drinkDelay <= 0) {
                        p.drinkDelay = 3;
                        p.combatDelay += 2;
                        pi.deleteItem(p, itemId, itemSlot, 1);
                        p.requestAnim(829, 0);
                        changeStat(p, 0, (int) (p.getLevelForXP(0) * 0.15) + 5, 1,
                                true);
                        pi.addItem(p, 229, 1);
                    }
                    break;

                default:
                    // Misc.println(
                    // "[" + p.username + "] Unhandled item selected: "
                    // + interfaceId + ":" + itemId + ":" + itemSlot);
                    break;
            }
        } else {
            //Misc.println("[" + p.username + "] Unhandled item select " + interfaceId+ ":" + itemId);
        }

        pi = null;
    }

    public void changeStat(Player p, int stat, int amt, int type, boolean bol) {
        if (p == null) {
            return;
        }
        if (bol) {
            if (p.skillLvl[stat] >= (p.getLevelForXP(stat) + amt)) {
                return;
            }
            p.skillLvl[stat] += amt;
            if (p.skillLvl[stat] >= (p.getLevelForXP(stat) + amt)) {
                p.skillLvl[stat] = (p.getLevelForXP(stat) + amt);
            }
            if (type == 0) {
                if (p.skillLvl[stat] > p.getLevelForXP(stat)) {
                    p.skillLvl[stat] = p.getLevelForXP(stat);
                }
            }
        } else if (!bol) {
            p.skillLvl[stat] -= amt;
            if (p.skillLvl[stat] < 1) {
                p.skillLvl[stat] = 1;
            }
        }
        p.frames.setSkillLvl(p, stat);
    }
}
