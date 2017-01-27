package icedice.io.packets;

import icedice.Engine;
import icedice.npcs.NPC;
import icedice.util.Misc;
import icedice.players.Magic;
import icedice.players.items.PlayerItems;
import icedice.Config;
import icedice.players.Player;

public class ActionButtons implements Packet {

    public void GetSkillCape(Player p, int Dialogue, int Npc, String Name, String Skill) {
        p.Dialogue = Dialogue;
        p.frames.showChatboxInterface(p, 241);
        p.frames.animateInterfaceId(p, 9850, 241, 2);
        p.frames.setNPCId(p, Npc, 241, 2);
        p.frames.setString(p, Name, 241, 3);
        p.frames.setString(p, "Aha! it seems you are a master of " + Skill + "...", 241, 4);
    }

    public void OnlyLevel(Player p, int Dialogue, int Npc, String Name, int Skill, String Nskill) {
        p.Dialogue = Dialogue;
        p.frames.showChatboxInterface(p, 241);
        p.frames.animateInterfaceId(p, 9845, 241, 2);
        p.frames.setNPCId(p, Npc, 241, 2);
        p.frames.setString(p, Name, 241, 3);
        p.frames.setString(p, "Your " + Nskill + " level is only level " + p.skillLvl[Skill] + ". You should..", 241, 4);
    }

    public void makeBars(Player p, int BarId, int BarAmount, int FirstOre, int FirstAmount, int SecondOre, int SecondAmount, int LevelReq, double xpMulti) {
        PlayerItems pi = new PlayerItems();
        int XP2 = (int) (100 * xpMulti);
        int XP = (XP2 * BarAmount);

        p.frames.removeChatboxInterface(p);
        if (pi.invItemCount(p, FirstOre) >= FirstAmount && pi.invItemCount(p, SecondOre) >= SecondAmount) {
            if (p.skillLvl[13] >= LevelReq) {
                p.addSkillXP(XP, 13);
                p.requestAnim(3243, 0);
                for (int i = 0; i < FirstAmount; i++) {
                    pi.deleteItem(p, FirstOre, findItemSlot(p, FirstOre), 1);
                }
                for (int i = 0; i < SecondAmount; i++) {
                    pi.deleteItem(p, SecondOre, findItemSlot(p, SecondOre), 1);
                }
                for (int i = 0; i < BarAmount; i++) {
                    pi.addItem(p, BarId, 1);
                }

                p.frames.sendMessage(p, "You make " + Engine.items.getItemName(BarId));
            } else {
                p.frames.sendMessage(p, "You need " + LevelReq + " Smithing to do this");
            }
        } else {
            p.frames.sendMessage(p, "You need " + FirstAmount + " " + Engine.items.getItemName(FirstOre) + "(s) and " + SecondAmount + " " + Engine.items.getItemName(SecondOre) + "(s)");
        }
    }

    public void makeBar(Player p, int BarId, int BarAmount, int FirstOre, int FirstAmount, int LevelReq, double xpMulti) {
        PlayerItems pi = new PlayerItems();
        int XP2 = (int) (100 * xpMulti);
        int XP = (XP2 * BarAmount);
        p.frames.removeChatboxInterface(p);
        if (pi.invItemCount(p, FirstOre) >= FirstAmount) {
            if (p.skillLvl[13] >= LevelReq) {
                p.addSkillXP(XP, 13);
                p.requestAnim(3243, 0);
                for (int i = 0; i < FirstAmount; i++) {
                    pi.deleteItem(p, FirstOre, findItemSlot(p, FirstOre), 1);
                }
                for (int i = 0; i < BarAmount; i++) {
                    pi.addItem(p, BarId, 1);
                }
                p.frames.sendMessage(p, "You make " + Engine.items.getItemName(BarId));
            } else {
                p.frames.sendMessage(p, "You need " + LevelReq + " Smithing to do this");
            }
        } else {
            p.frames.sendMessage(p, "You need " + FirstAmount + " " + Engine.items.getItemName(FirstOre) + "(s)");
        }
    }

    public int findItemSlot(Player p, int item) {
        for (int i = 0; i < p.items.length; i++) {
            if (item == p.items[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Handles buttons on interfaces.
     *
     * @param p          The Player which the frame should be handled for.
     * @param packetId   The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        int interfaceId = p.stream.readUnsignedWord();
        int buttonId = p.stream.readUnsignedWord();
        int buttonId2 = p.stream.readUnsignedWord();
        Magic m = new Magic();
        // if (packetId == 233 || packetId == 21 || packetId == 169 || packetId == 232 || packetId == 214 || packetId == 90 || packetId == 173 || packetId == 214) {
        // buttonId2 = 
        // }
        if (buttonId2 == 65535) {
            buttonId2 = 0;
        }
        switch (interfaceId) {

            case 311:
                switch (buttonId) {
                    case 16:
                        makeBars(p, 2349, 1, 438, 1, 436, 1, 1, 1);
                        break;
                    case 15:
                        makeBars(p, 2349, 5, 438, 5, 436, 5, 1, 1);
                        break;
                    case 14:
                        makeBars(p, 2349, 10, 438, 10, 436, 10, 1, 1);
                        break;
                    case 24:
                        makeBar(p, 2351, 1, 440, 1, 15, 1.1);
                        break;
                    case 23:
                        makeBar(p, 2351, 5, 440, 5, 15, 1.1);
                        break;
                    case 22:
                        makeBar(p, 2351, 10, 440, 10, 15, 1.1);
                        break;
                    case 32:
                        makeBars(p, 2353, 1, 440, 1, 453, 2, 30, 1.2);
                        break;
                    case 31:
                        makeBars(p, 2353, 5, 440, 5, 453, 10, 30, 1.2);
                        break;
                    case 30:
                        makeBars(p, 2353, 9, 440, 9, 453, 18, 30, 1.2);
                        break;
                    case 40:
                        makeBars(p, 2359, 1, 447, 1, 453, 4, 50, 1.4);
                        break;
                    case 39:
                        makeBars(p, 2359, 5, 447, 5, 453, 20, 50, 1.4);
                        break;
                    case 38:
                        makeBars(p, 2359, 5, 447, 5, 453, 20, 50, 1.4);
                        break;
                    case 44:
                        makeBars(p, 2361, 1, 449, 1, 453, 6, 70, 1.5);
                        break;
                    case 43:
                        makeBars(p, 2361, 4, 449, 4, 453, 24, 70, 1.5);
                        break;
                    case 42:
                        makeBars(p, 2361, 4, 449, 4, 453, 24, 70, 1.5);
                        break;
                    case 48:
                        makeBars(p, 2363, 1, 451, 1, 453, 8, 85, 1.6);
                        break;
                    case 47:
                        makeBars(p, 2363, 3, 451, 3, 453, 24, 85, 1.6);
                        break;
                    case 46:
                        makeBars(p, 2363, 3, 451, 3, 453, 24, 85, 1.6);
                        break;


                    case 20:
                        makeBar(p, 9467, 1, 668, 1, 1, 1);
                        break;
                    case 19:
                        makeBar(p, 9467, 5, 668, 5, 1, 1);
                        break;
                    case 18:
                        makeBar(p, 9467, 10, 668, 10, 1, 1);
                        break;
                    case 28:
                        makeBar(p, 2355, 1, 442, 1, 1, 1);
                        break;
                    case 27:
                        makeBar(p, 2355, 5, 442, 5, 1, 1);
                        break;
                    case 26:
                        makeBar(p, 2355, 10, 442, 10, 1, 1);
                        break;
                    case 36:
                        makeBar(p, 2357, 1, 444, 1, 1, 1.5);
                        break;
                    case 35:
                        makeBar(p, 2357, 5, 444, 5, 1, 1.5);
                        break;
                    case 34:
                        makeBar(p, 2357, 10, 444, 10, 1, 1.5);
                        break;

                }
                break;

            case 300:
                if (Engine.playerItems.invItemCount(p, 11550) > 1 && Engine.playerItems.invItemCount(p, 11551) > 1 && Engine.playerItems.invItemCount(p, 11552) > 1) {
                    Engine.playerItems.deleteItem(p, 11550, 1);
                    Engine.playerItems.deleteItem(p, 11551, 1);
                    Engine.playerItems.deleteItem(p, 11552, 1);
                    Engine.playerItems.addItem(p, 1121, 1);

                }
                switch (buttonId) {

                    case 25:
                    case 33:
                    case 41:
                    case 49:
                    case 57:
                    case 81:
                    case 113:
                    case 121:
                    case 129:
                    case 137:
                    case 145:
                    case 153:
                    case 185:
                    case 193:
                    case 201:
                    case 225:
                    case 233:
                    case 241:
                    case 249:
                        Engine.smithing.smith(p, Engine.smithing.type, 1, buttonId);
                        break;

                    case 24:
                    case 32:
                    case 40:
                    case 48:
                    case 56:
                    case 80:
                    case 112:
                    case 120:
                    case 128:
                    case 136:
                    case 144:
                    case 152:
                    case 184:
                    case 192:
                    case 200:
                    case 224:
                    case 232:
                    case 240:
                    case 248:
                        Engine.smithing.smith(p, Engine.smithing.type, 5, buttonId);
                        break;

                    case 22:
                    case 30:
                    case 38:
                    case 46:
                    case 54:
                    case 78:
                    case 110:
                    case 118:
                    case 126:
                    case 134:
                    case 142:
                    case 150:
                    case 182:
                    case 190:
                    case 198:
                    case 222:
                    case 230:
                    case 238:
                    case 246:
                        Engine.smithing.smith(p, Engine.smithing.type, Engine.playerItems.invItemCount(p, Engine.smithing.Bar(Engine.smithing.type)), buttonId);
                        break;

                    default:
                        //Misc.println("[" + p.username + "] Unhandled button: " + buttonId + ":" + buttonId2);
                        break;
                }
                break;
            case 620:
            case 621:
                Engine.shopHandler.handleoption(p, interfaceId, buttonId, buttonId2, packetId);
                break;

            case 105:
                switch (buttonId) {
                    case 194:
                        Object[] o = new Object[]{"Grand Exchange Item Search"};
                        p.frames.setGeSearch(p, o);
                        break;
                }
                p.GrandExchange.handleButtons(buttonId);
                break;

            case 107:
                if (p.items[buttonId2] == 995) {
                    p.frames.sendMessage(p, "You cannot sell this item!");
                    return;
                }
                if (p.items[buttonId2] == 1019) {
                    p.frames.sendMessage(p, "You cannot sell this item!");
                    return;
                }
                if (p.items[buttonId2] == 1021) {
                    p.frames.sendMessage(p, "You cannot sell this item!");
                    return;
                }
                if (p.items[buttonId2] == 1023) {
                    p.frames.sendMessage(p, "You cannot sell this item!");
                    return;
                }
                if (p.items[buttonId2] == 3767) {
                    p.frames.sendMessage(p, "You cannot sell this item!");
                    return;
                }
                p.GrandExchange.offerItem(buttonId2);
                break;

            case 274:

                if (Engine.wildernessArea(p.absX, p.absY)) {
                    switch (buttonId) {
                        case 5://Updates
                            p.frames.showInterface(p, 255);
                            p.frames.setString(p, "<col=77FF77>The latest and greatest client has come out, v3.1! Make sure you download it so you can access the new pvp world!", 255, 3);
                            p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to read the rule book!");
                            Engine.playerItems.addItem(p, 9003, 1);
                            break;
                        case 7:
                            if (p.jailed == (0)) {
                                p.teleportTo(Config.homeX + Misc.random(2), Config.homeY + Misc.random(2), 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.frames.sendMessage(p, "<img=2>Theres no place like home!");
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 8://death cape
                            if (p.pkp <= 19) {
                                p.frames.sendMessage(p, "<img=2>You need more PKP to get this item!");
                            } else if (p.pkp >= 20) {
                                Engine.playerItems.addItem(p, 4353, 1);
                                p.frames.sendMessage(p, "You recive your death cape!");
                                p.pkp -= 20;
                            }
                            break;

                        case 9://granite maul
                            if (p.pkp <= 24) {
                                p.frames.sendMessage(p, "<img=2>You need more PKP to get this item!");
                            } else if (p.pkp >= 25) {
                                Engine.playerItems.addItem(p, 4153, 1);
                                p.frames.sendMessage(p, "You recive your granite maul!");
                                p.pkp -= 25;
                            }
                            break;

                        case 10://full granite
                            if (p.pkp <= 39) {
                                p.frames.sendMessage(p, "<img=2>You need more PKP to get this item!");
                            } else if (p.pkp >= 40) {
                                Engine.playerItems.addItem(p, 3122, 1);
                                Engine.playerItems.addItem(p, 6809, 1);
                                Engine.playerItems.addItem(p, 10564, 1);
                                Engine.playerItems.addItem(p, 10589, 1);
                                p.frames.sendMessage(p, "You recive your Granite Armour!");
                                p.pkp -= 40;
                            }
                            break;
                        case 11://dragon dagger (p++)
                            if (p.pkp <= 29) {
                                p.frames.sendMessage(p, "<img=2>You need more PKP to get this item!");
                            } else if (p.pkp >= 30) {
                                Engine.playerItems.addItem(p, 5698, 1);
                                p.frames.sendMessage(p, "You recive your Dragon dagger(p++)!");
                                p.pkp -= 30;
                            }
                            break;
                        case 12://10x Sharks
                            if (p.pkp <= 9) {
                                p.frames.sendMessage(p, "<img=2>You need more PKP to get this item!");
                            } else if (p.pkp >= 10) {
                                Engine.playerItems.addItem(p, 385, 10);
                                p.frames.sendMessage(p, "You recive your 10x Shakrs!");
                                p.pkp -= 10;
                            }
                            break;
                        case 13://100x Sharks
                            if (p.pkp <= 14) {
                                p.frames.sendMessage(p, "<img=2>You need more PKP to get this item!");
                            } else if (p.pkp >= 15) {
                                Engine.playerItems.addItem(p, 386, 100);
                                p.frames.sendMessage(p, "You recive your 100x Sharks!");
                                p.pkp -= 15;
                            }
                            break;
                        case 14://Potion Pack
                            if (p.pkp <= 9) {
                                p.frames.sendMessage(p, "<img=2>You need more PKP to get this item!");
                            } else if (p.pkp >= 10) {
                                Engine.playerItems.addItem(p, 2428, 1);
                                Engine.playerItems.addItem(p, 2432, 1);
                                Engine.playerItems.addItem(p, 2444, 1);
                                Engine.playerItems.addItem(p, 113, 1);
                                Engine.playerItems.addItem(p, 2446, 1);
                                Engine.playerItems.addItem(p, 3040, 1);
                                p.frames.sendMessage(p, "You recive your Potions!");
                                p.pkp -= 10;
                            }
                            break;
                        case 15://Super Potion Pack
                            if (p.pkp <= 14) {
                                p.frames.sendMessage(p, "<img=2>You need more PKP to get this item!");
                            } else if (p.pkp >= 15) {
                                Engine.playerItems.addItem(p, 2436, 1);
                                Engine.playerItems.addItem(p, 2440, 1);
                                Engine.playerItems.addItem(p, 2442, 1);
                                Engine.playerItems.addItem(p, 2446, 1);
                                Engine.playerItems.addItem(p, 3040, 1);
                                p.frames.sendMessage(p, "You recive your  Super Potions!");
                                p.pkp -= 15;
                            }
                            break;
                        case 16://Restore Special
                            if (p.pkp <= 9) {
                                p.frames.sendMessage(p, "<img=2>You need more PKP to get this item!");
                            } else if (p.pkp >= 10) {
                                p.specialAmount = 100;
                                p.specialAmountUpdateReq = true;
                                p.frames.sendMessage(p, "Your Spec has been restored!");
                                p.pkp -= 10;
                            }
                            break;
                        case 17://De-Poison
                            if (p.pkp <= 4) {
                                p.frames.sendMessage(p, "<img=2>You need more PKP to get this item!");
                            } else if (p.pkp >= 5) {
                                Engine.poison.startPoison(p, 0);
                                p.frames.sendMessage(p, "You are no longer poisoned!");
                                p.pkp -= 5;
                            }
                            break;
                    }
                } else if (!Engine.wildernessArea(p.absX, p.absY)) {
                    switch (buttonId) {

                        case 5://Updates
                            p.frames.showInterface(p, 255);
                            p.frames.setString(p, "<col=77FF77>The latest and greatest client has come out, v3.1! Make sure you download it so you can access the new pvp world!", 255, 3);
                            p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to read the rule book!");
                            Engine.playerItems.addItem(p, 9003, 1);
                            break;

                        case 7://Dragon Slayer
                            for (int i = 0; i < 30; i++) {
                                if (p.DragonSlayer == 0) {
                                    p.frames.showInterface(p, 275);
                                    p.frames.setString(p, "Dragon Slayer", 275, 2);
                                    p.frames.setString(p, "", 275, 11);
                                    p.frames.setString(p, "To start this quest, speak to the Guildmaster.", 275, 12);
                                    p.frames.setString(p, "He is located north of the home bank", 275, 13);
                                    p.frames.setString(p, "in falador park.", 275, 14);
                                    p.frames.setString(p, "", 275, 14 + i);
                                }
                                if (p.DragonSlayer == 1) {
                                    p.frames.showInterface(p, 275);
                                    p.frames.setString(p, "Dragon Slayer", 275, 2);
                                    p.frames.setString(p, "", 275, 11);
                                    p.frames.setString(p, "The Guildmaster told me to speak to Oziach.", 275, 12);
                                    p.frames.setString(p, "He is located in Edgeville.", 275, 13);
                                    p.frames.setString(p, "The fastest way to get there is ::edge.", 275, 14);
                                    p.frames.setString(p, "", 275, 14 + i);
                                }
                                if (p.DragonSlayer == 2) {
                                    p.frames.showInterface(p, 275);
                                    p.frames.setString(p, "Dragon Slayer", 275, 2);
                                    p.frames.setString(p, "", 275, 11);
                                    p.frames.setString(p, "Oziach put me on a quest to slay Elvarg the dragon.", 275, 12);
                                    p.frames.setString(p, "The guildmaster will give me more information.", 275, 13);
                                    p.frames.setString(p, "", 275, 13 + i);
                                }
                                if (p.DragonSlayer == 3) {
                                    p.frames.showInterface(p, 275);
                                    p.frames.setString(p, "Dragon Slayer", 275, 2);
                                    p.frames.setString(p, "", 275, 11);
                                    p.frames.setString(p, "The dragon is located on Crandor Island.", 275, 12);
                                    p.frames.setString(p, "", 275, 13);
                                    p.frames.setString(p, "I will need a map from the Oracle on Ice Mountain.", 275, 14);
                                    p.frames.setString(p, "I will need a shield to protect me.", 275, 15);
                                    p.frames.setString(p, "I will need a ship to get there.", 275, 16);
                                    p.frames.setString(p, "", 275, 16 + i);
                                }
                                if (p.DragonSlayer == 4) {
                                    p.frames.showInterface(p, 275);
                                    p.frames.setString(p, "Dragon Slayer", 275, 2);
                                    p.frames.setString(p, "", 275, 11);
                                    p.frames.setString(p, "I slayed the dragon!", 275, 12);
                                    p.frames.setString(p, "", 275, 13);
                                    p.frames.setString(p, "I should return to Oziach and tell him.", 275, 14);
                                    p.frames.setString(p, "", 275, 14 + i);
                                }
                                if (p.DragonSlayer == 5) {
                                    p.frames.showInterface(p, 275);
                                    p.frames.setString(p, "Dragon Slayer", 275, 2);
                                    p.frames.setString(p, "", 275, 11);
                                    p.frames.setString(p, "<col=66FF33>QUEST COMPLETE", 275, 12);
                                    p.frames.setString(p, "", 275, 13);
                                    p.frames.setString(p, "I now can wear rune platebodies, dragon platebodies,", 275, 14);
                                    p.frames.setString(p, "and dragon chainbodies", 275, 15);
                                    p.frames.setString(p, "Get your quest cape from the wise old man...", 275, 16);
                                    p.frames.setString(p, "He is sitting on the bench in falador park.", 275, 17);
                                    p.frames.setString(p, "", 275, 14 + i);
                                }
                            }
                            break;
                        case 10://starters & staff teleports
                            if (p.rights >= 1) {
                                p.teleportTo(2339, 3172, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                            }
                            break;
                        case 13://Home tele
                            if (p.jailed == (0)) {
                                p.teleportTo(Config.homeX + Misc.random(2), Config.homeY + Misc.random(2), 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.frames.sendMessage(p, "<img=2>Theres no place like home!");
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 14://Party Room
                            if (p.jailed == (0)) {
                                p.setCoords(3046 + Misc.random(6), 3381 + Misc.random(3), 0);
                                p.frames.sendMessage(p, "<img=2>Pull the lever to start a party!");
                                p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to type ::savebackup incase your account gets reset!");
                                p.appearanceUpdateReq = true;
                                p.updateReq = true;
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 15://Shops Tele
                            p.Choice = 6;
                            p.Dialogue = 0;
                            p.frames.setString(p, "Home Shops", 458, 1);
                            p.frames.setString(p, "Grand Exchange", 458, 2);
                            p.frames.setString(p, "Donator Shops", 458, 3);
                            p.frames.showChatboxInterface(p, 458);

                            p.Cooking = false;
                            p.TalkAgent = false;
                            p.DecorChange = false;
                            break;
                        case 16://Training tele
                            if (p.jailed == (0)) {
                                p.setCoords(3032 + Misc.random(4), 9582, 0);
                                p.requestGFX(1679, 0);
                                p.requestAnim(8941, 0);
                                p.frames.sendMessage(p, "<img=2>You have just teleported to training!");
                                p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to type ::savebackup incase your account gets reset!");
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 17://Donators Training tele
                            if (p.jailed == (0)) {
                                if ((p.donator == 0 && p.rights == 0)) {
                                    p.frames.sendMessage(p, "<img=2>Sorry but you must first become a donator to enter.");
                                } else if ((p.donator == 1 || p.rights >= 1)) {
                                    p.setCoords(2520 + Misc.random(4), 3041 + Misc.random(4), 0);
                                    p.requestGFX(1679, 0);
                                    p.requestAnim(8941, 0);
                                    p.frames.sendMessage(p, "<img=2>You have just teleported to training!");
                                    p.frames.sendMessage(p, "<img=2>Please note this isnt 100% done yet! Thank you - iceyyscape");
                                    p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to type ::savebackup incase your account gets reset!");
                                } else {
                                    p.frames.sendMessage(p, "You can't teleport while in jail.");
                                    p.frames.sendMessage(p, "Next time be more careful!");
                                }
                            }
                            break;
                        case 18://King black dragon
                            if (p.jailed == (0)) {
                                p.teleportTo(2270, 4690, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.frames.sendMessage(p, "<img=2>You have just teleported to the King's layer...");
                                p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to type ::savebackup incase your account gets reset!");
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 19://Safety Dungeon tele
                            if (p.jailed == (0)) {
                                p.teleportTo(3175, 4239, 2, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.frames.sendMessage(p, "<img=2>You have just teleported to Safety Dungeon's Mini Game!");
                                p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to type ::savebackup incase your account gets reset!");
                                Engine.playerItems.addItem(p, 13151, 1);
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 20://Barrows
                            if (p.jailed == (0)) {
                                p.teleportTo(3565, 3301, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.frames.sendMessage(p, "<img=2>Barrows drop rate is NOW <img=2>80%");
                                p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to type ::savebackup incase your account gets reset!");
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 21://God Wars tele
                            if (p.jailed == (0)) {
                                p.teleportTo(2882, 5309, 2, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.heightLevel = 2;
                                p.frames.sendMessage(p, "<img=2>You Teleport to Godwars!");
                                p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to type ::savebackup incase your account gets reset!");
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 22://Castle wars tele
                            if (p.jailed == (0)) {
                                p.teleportTo(2443, 3088, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.frames.sendMessage(p, "<img=2>You Teleport To Castle Wars");
                                p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to type ::savebackup incase your account gets reset!");
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 23://Fight Pits tele
                            if (p.jailed == (0)) {
                                p.teleportTo(2399, 5178, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.frames.sendMessage(p, "<img=2>Welcome to Fight Pits");
                                p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to type ::savebackup incase your account gets reset!");
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 24://Pvp
                            if (p.jailed == (0)) {
                                p.setCoords(3127 + Misc.random(8), 3689 + Misc.random(8), 0);
                                p.requestGFX(482, 0);
                                p.requestAnim(1914, 0);
                                p.frames.sendMessage(p, "<img=2>You Teleport to Pvp! On death items are:<col=77FF77>SAFE");
                                p.frames.sendMessage(p, "<img=2><col=77FF77>Dont forget to type ::savebackup incase your account gets reset!");
                                p.requestForceChat("RRRRRHHHHHAAAAAAA!!");
                            } else {
                                p.frames.sendMessage(p, "You can't teleport while in jail.");
                                p.frames.sendMessage(p, "Next time be more careful!");
                            }
                            break;
                        case 26://Quick Shop
                            p.Choice = 8;
                            p.Dialogue = 0;
                            p.frames.setString(p, "Food Shop", 458, 1);
                            p.frames.setString(p, "Skill Shop", 458, 2);
                            p.frames.setString(p, "-iCEYY!GENERAl-", 458, 3);
                            p.frames.showChatboxInterface(p, 458);
                            break;
                    }
                }
                break;


//=======================CONSTRUCTION BUTTONS======================================
            case 402:
                switch (buttonId) {
                    case 160://Parlour
                        if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.heightLevel == p.HouseHeight) {
                            if (Engine.playerItems.HasItemAmount(p, 995, 200) == false) {
                                p.frames.sendMessage(p, "You need 200 coins to build this.");
                            } else {
                                Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), 200);
                                if (p.RoomDir == 0) {
                                    p.Garden = 0;
                                    p.Room0Type = 1;
                                    if (p.Room0 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 1, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(3000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 4) {
                                    p.Garden4 = 0;
                                    p.Room4Type = 1;
                                    if (p.Room4 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3923, p.RoomDir, p.HouseHeight, p.HouseDecor, 1, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(3000 * p.skillLvl[22], 22);
                                    }
                                }

                                if (p.RoomDir == 1) {
                                    p.Garden1 = 0;
                                    p.Room1Type = 1;
                                    if (p.Room1 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3939, p.RoomDir, p.HouseHeight, p.HouseDecor, 1, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(3000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 2) {
                                    p.Garden2 = 0;
                                    p.Room2Type = 1;
                                    if (p.Room2 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3112, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 1, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(3000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 3) {
                                    p.Garden3 = 0;
                                    p.Room3Type = 1;
                                    if (p.Room3 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3096, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 1, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(3000 * p.skillLvl[22], 22);
                                    }
                                }
                            }
                        } else {
                            p.RoomDir = 0;
                            p.frames.sendMessage(p, "You are not in your house!");
                        }
                        break;

                    case 161: //Garden
                        if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.heightLevel == p.HouseHeight) {
                            if (Engine.playerItems.HasItemAmount(p, 995, 250) == false) {
                                p.frames.sendMessage(p, "You need 250 coins to build this.");
                            } else {
                                Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), 250);

                                if (p.RoomDir == 0) {
                                    p.Garden = 1;
                                    p.Room0Type = 7;
                                    if (p.Room0 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 7, 1);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(10000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 4) {
                                    p.Garden4 = 1;
                                    p.Room4Type = 7;
                                    if (p.Room4 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3923, p.RoomDir, p.HouseHeight, p.HouseDecor, 7, 1);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(10000 * p.skillLvl[22], 22);
                                    }
                                }

                                if (p.RoomDir == 1) {
                                    p.Garden1 = 1;
                                    p.Room1Type = 7;
                                    if (p.Room1 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3939, p.RoomDir, p.HouseHeight, p.HouseDecor, 7, 1);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(10000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 2) {
                                    p.Garden2 = 1;
                                    p.Room2Type = 7;
                                    if (p.Room2 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3112, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 7, 1);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(10000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 3) {
                                    p.Garden3 = 1;
                                    p.Room3Type = 7;
                                    if (p.Room3 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3096, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 7, 1);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(10000 * p.skillLvl[22], 22);
                                    }
                                }
                            }
                        } else {
                            p.RoomDir = 0;
                            p.frames.sendMessage(p, "You are not in your house!");
                        }
                        break;
                    case 162: //Kitchen
                        if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.heightLevel == p.HouseHeight) {
                            if (Engine.playerItems.HasItemAmount(p, 995, 350) == false) {
                                p.frames.sendMessage(p, "You need 350 coins to build this.");
                            } else {
                                Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), 350);

                                if (p.RoomDir == 0) {
                                    p.Garden = 0;
                                    p.Room0Type = 2;
                                    if (p.Room0 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 2, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(4000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 4) {
                                    p.Garden4 = 0;
                                    p.Room4Type = 2;
                                    if (p.Room4 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3923, p.RoomDir, p.HouseHeight, p.HouseDecor, 2, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(4000 * p.skillLvl[22], 22);
                                    }
                                }

                                if (p.RoomDir == 1) {
                                    p.Garden1 = 0;
                                    p.Room1Type = 2;
                                    if (p.Room1 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3939, p.RoomDir, p.HouseHeight, p.HouseDecor, 2, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(4000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 2) {
                                    p.Garden2 = 0;
                                    p.Room2Type = 2;
                                    if (p.Room2 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3112, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 2, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(4000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 3) {
                                    p.Garden3 = 0;
                                    p.Room3Type = 2;
                                    if (p.Room3 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3096, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 2, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(4000 * p.skillLvl[22], 22);
                                    }
                                }
                            }
                        } else {
                            p.RoomDir = 0;
                            p.frames.sendMessage(p, "You are not in your house!");
                        }
                        break;
                    case 163: //Dining Room
                        p.frames.sendMessage(p, "Not Available.");
                        break;
                    case 164: //Work Shop
                        p.frames.sendMessage(p, "Not Available.");
                        break;
                    case 165: //Bedroom
                        if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.heightLevel == p.HouseHeight) {
                            if (Engine.playerItems.HasItemAmount(p, 995, 450) == false) {
                                p.frames.sendMessage(p, "You need 1000 coins to build this.");
                            } else {
                                Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), 450);

                                if (p.RoomDir == 0) {
                                    p.Garden = 0;
                                    p.Room0Type = 3;
                                    if (p.Room0 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 3, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(5000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 4) {
                                    p.Garden4 = 0;
                                    p.Room4Type = 3;
                                    if (p.Room4 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3923, p.RoomDir, p.HouseHeight, p.HouseDecor, 3, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(5000 * p.skillLvl[22], 22);
                                    }
                                }

                                if (p.RoomDir == 1) {
                                    p.Garden1 = 0;
                                    p.Room1Type = 3;
                                    if (p.Room1 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3939, p.RoomDir, p.HouseHeight, p.HouseDecor, 3, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(5000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 2) {
                                    p.Garden2 = 0;
                                    p.Room2Type = 3;
                                    if (p.Room2 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3112, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 3, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(5000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 3) {
                                    p.Garden3 = 0;
                                    p.Room3Type = 3;
                                    if (p.Room3 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3096, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 3, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(5000 * p.skillLvl[22], 22);
                                    }
                                }
                            }
                        } else {
                            p.RoomDir = 0;
                            p.frames.sendMessage(p, "You are not in your house!");
                        }
                        break;
                    case 166: //Hall
                        p.frames.sendMessage(p, "Not Available.");
                        break;
                    case 167: //Games Room
                        p.frames.sendMessage(p, "Not Available.");
                        break;
                    case 168: //Combat Room
                        p.frames.sendMessage(p, "Not Available.");
                        break;
                    case 169: //Hall
                        p.frames.sendMessage(p, "Not Available.");
                        break;
                    case 170: //Study
                        if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.heightLevel == p.HouseHeight) {


                            if (p.RoomDir == 0) {
                                p.Garden = 0;
                                p.Room0Type = 6;
                                if (p.Room0 == 1) {
                                    p.frames.sendMessage(p, "You already have a room there.");
                                } else {
                                    p.NewRoom(p, 3104, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 6, 0);
                                    p.requestAnim(898, 0);
                                }
                            }
                            if (p.RoomDir == 4) {
                                p.Garden4 = 0;
                                p.Room4Type = 6;
                                if (p.Room4 == 1) {
                                    p.frames.sendMessage(p, "You already have a room there.");
                                } else {
                                    p.NewRoom(p, 3104, 3923, p.RoomDir, p.HouseHeight, p.HouseDecor, 6, 0);
                                    p.requestAnim(898, 0);
                                }
                            }


                            if (p.RoomDir == 1) {
                                p.Garden1 = 0;
                                p.Room1Type = 6;
                                if (p.Room1 == 1) {
                                    p.frames.sendMessage(p, "You already have a room there.");
                                } else {
                                    p.NewRoom(p, 3104, 3939, p.RoomDir, p.HouseHeight, p.HouseDecor, 6, 0);
                                    p.requestAnim(898, 0);
                                }
                            }
                            if (p.RoomDir == 2) {
                                p.Garden2 = 0;
                                p.Room2Type = 6;
                                if (p.Room2 == 1) {
                                    p.frames.sendMessage(p, "You already have a room there.");
                                } else {
                                    p.NewRoom(p, 3112, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 6, 0);
                                    p.requestAnim(898, 0);
                                }
                            }
                            if (p.RoomDir == 3) {
                                p.Garden3 = 0;
                                p.Room3Type = 6;
                                if (p.Room3 == 1) {
                                    p.frames.sendMessage(p, "You already have a room there.");
                                } else {
                                    p.NewRoom(p, 3096, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 6, 0);
                                    p.requestAnim(898, 0);
                                }
                            }
                        }
                        break;
                    case 171: //Costume
                        p.frames.sendMessage(p, "Not Available.");
                        break;
                    case 172: //Chapel
                        if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.heightLevel == p.HouseHeight) {
                            if (Engine.playerItems.HasItemAmount(p, 995, 600) == false) {
                                p.frames.sendMessage(p, "You need 600 coins to build this.");
                            } else {
                                Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), 600);

                                if (p.RoomDir == 0) {
                                    p.Garden = 0;
                                    p.Room0Type = 4;
                                    if (p.Room0 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 4, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(8000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 4) {
                                    p.Garden4 = 0;
                                    p.Room4Type = 4;
                                    if (p.Room4 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3923, p.RoomDir, p.HouseHeight, p.HouseDecor, 4, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(8000 * p.skillLvl[22], 22);
                                    }
                                }

                                if (p.RoomDir == 1) {
                                    p.Garden1 = 0;
                                    p.Room1Type = 4;
                                    if (p.Room1 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3939, p.RoomDir, p.HouseHeight, p.HouseDecor, 4, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(8000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 2) {
                                    p.Garden2 = 0;
                                    p.Room2Type = 4;
                                    if (p.Room2 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3112, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 4, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(8000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 3) {
                                    p.Garden3 = 0;
                                    p.Room3Type = 4;
                                    if (p.Room3 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3096, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 4, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(8000 * p.skillLvl[22], 22);
                                    }
                                }
                            }
                        } else {
                            p.RoomDir = 0;
                            p.frames.sendMessage(p, "You are not in your house!");
                        }
                        break;

                    case 173: //Portal
                        p.frames.sendMessage(p, "Not Available.");
                        break;
                    case 174: //Formal Garden
                        p.frames.sendMessage(p, "Not Available.");
                        break;

                    case 175: //Throne
                        if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.heightLevel == p.HouseHeight) {
                            if (Engine.playerItems.HasItemAmount(p, 995, 1500) == false) {
                                p.frames.sendMessage(p, "You need 1500 coins to build this.");
                            } else {
                                Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), 1500);

                                if (p.RoomDir == 0) {
                                    p.Garden = 0;
                                    p.Room0Type = 5;
                                    if (p.Room0 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 5, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(10000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 4) {
                                    p.Garden4 = 0;
                                    p.Room4Type = 5;
                                    if (p.Room4 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3923, p.RoomDir, p.HouseHeight, p.HouseDecor, 5, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(10000 * p.skillLvl[22], 22);
                                    }
                                }

                                if (p.RoomDir == 1) {
                                    p.Garden1 = 0;
                                    p.Room1Type = 5;
                                    if (p.Room1 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3104, 3939, p.RoomDir, p.HouseHeight, p.HouseDecor, 5, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(10000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 2) {
                                    p.Garden2 = 0;
                                    p.Room2Type = 5;
                                    if (p.Room2 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3112, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 5, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(10000 * p.skillLvl[22], 22);
                                    }
                                }
                                if (p.RoomDir == 3) {
                                    p.Garden3 = 0;
                                    p.Room3Type = 5;
                                    if (p.Room3 == 1) {
                                        p.frames.sendMessage(p, "You already have a room there.");
                                    } else {
                                        p.NewRoom(p, 3096, 3931, p.RoomDir, p.HouseHeight, p.HouseDecor, 5, 0);
                                        p.requestAnim(898, 0);
                                        p.addSkillXP(10000 * p.skillLvl[22], 22);
                                    }
                                }
                            }
                        } else {
                            p.RoomDir = 0;
                            p.frames.sendMessage(p, "You are not in your house!");
                        }
                        break;

                    case 176: //Oubliette
                        p.frames.sendMessage(p, "Not Available.");
                        break;

                    case 177: //Dungeon
                        p.frames.sendMessage(p, "Not Available.");
                        break;

                    case 178: //Dungeon
                        p.frames.sendMessage(p, "Not Available.");
                        break;

                    case 179: //Dungeon Stairs
                        p.frames.sendMessage(p, "Not Available.");
                        break;

                    case 180: //Treasure ROom
                        p.frames.sendMessage(p, "Not Available.");
                        break;


                }
                p.frames.removeShownInterface(p);
                p.frames.removeChatboxInterface(p);
                break;

//=================================END OF CONSTRUCTION BUTTONS============================
            case 589:
                if (buttonId == 9) {
                    p.frames.showInterface(p, 590);
                }
                break;
            case 669:
                if (buttonId == 18) {
                    p.frames.showInterface(p, 666);
                }
                break;
            case 45:
                if (buttonId == 87) { // fire
                    p.setCoords(2587, 4836, 0);
                }
                if (buttonId == 89) { // water
                    p.setCoords(3482, 4838, 0);
                }
                if (buttonId == 90) { // earth
                    p.setCoords(2657, 4830, 0);
                }
                if (buttonId == 91) { // air
                    p.frames.sendMessage(p, "The alter is near by");
                }
                if (buttonId == 92) { // mind
                    p.frames.sendMessage(p, "The alter is near by");
                }
                if (buttonId == 93) { // body
                    p.setCoords(2522, 4842, 0);
                }
                if (buttonId == 94) { // nature
                    p.setCoords(2400, 4844, 0);
                }
                if (buttonId == 95) { // chaos
                    p.setCoords(2269, 4840, 0);
                }
                if (buttonId == 96) { // law
                    p.setCoords(2466, 4833, 0);
                }
                if (buttonId == 97) { // cosmic
                    p.setCoords(2142, 4836, 0);
                }
                if (buttonId == 98) { // blood
                    p.frames.sendMessage(p, "The alter is near by");
                }
                if (buttonId == 99) { // soul
                    p.setCoords(2200, 4836, 0);
                }
                break;

            case 1024:
                if (buttonId == 1) {
                    p.requestForceChat("<img=3>My Agility level is, " + p.skillLvl[16] + ".");
                }
                if (buttonId == 8) {
                    p.requestForceChat("<img=3>My Attack level is, " + p.skillLvl[0] + ".");
                }
                if (buttonId == 13) {
                    p.requestForceChat("<img=3>My Construction level is, " + p.skillLvl[22] + ".");
                }
                if (buttonId == 16) {
                    p.requestForceChat("<img=3>My Cooking level is, " + p.skillLvl[7] + ".");
                }
                if (buttonId == 23) {
                    p.requestForceChat("<img=3>My Crafting level is, " + p.skillLvl[12] + ".");
                }
                if (buttonId == 30) {
                    p.requestForceChat("<img=3>My Defence level is, " + p.skillLvl[1] + ".");
                }
                if (buttonId == 34) {
                    p.requestForceChat("<img=3>My Farming level is, " + p.skillLvl[19] + ".");
                }
                if (buttonId == 41) {
                    p.requestForceChat("<img=3>My Firemaking level is, " + p.skillLvl[11] + ".");
                }
                if (buttonId == 47) {
                    p.requestForceChat("<img=3>My Fishing level is, " + p.skillLvl[10] + ".");
                }
                if (buttonId == 55) {
                    p.requestForceChat("<img=3>My Fletching level is, " + p.skillLvl[9] + ".");
                }
                if (buttonId == 62) {
                    p.requestForceChat("<img=3>My Herblore level is, " + p.skillLvl[15] + ".");
                }
                if (buttonId == 70) {
                    p.requestForceChat("<img=3>My Hitpoints level is, " + p.skillLvl[3] + ".");
                }
                if (buttonId == 74) {
                    p.requestForceChat("<img=3>My Hunter level is, " + p.skillLvl[21] + ".");
                }
                if (buttonId == 135) {
                    p.requestForceChat("<img=3>My Magic level is, " + p.skillLvl[6] + ".");
                }
                if (buttonId == 127) {
                    p.requestForceChat("<img=3>My Mining level is, " + p.skillLvl[14] + ".");
                }
                if (buttonId == 120) {
                    p.requestForceChat("<img=3>My Prayer level is, " + p.skillLvl[5] + ".");
                }
                if (buttonId == 116) {
                    p.requestForceChat("<img=3>My Range level is, " + p.skillLvl[4] + ".");
                }
                if (buttonId == 111) {
                    p.requestForceChat("<img=3>My Runecrafting level is, " + p.skillLvl[20] + ".");
                }
                if (buttonId == 103) {
                    p.requestForceChat("<img=3>My Slayer level is, " + p.skillLvl[18] + ".");
                }
                if (buttonId == 96) {
                    p.requestForceChat("<img=3>My Smithing level is, " + p.skillLvl[13] + ".");
                }
                if (buttonId == 92) {
                    p.requestForceChat("<img=3>My Strength level is, " + p.skillLvl[2] + ".");
                }
                if (buttonId == 85) {
                    p.requestForceChat("<img=3>My Summoning level is, " + p.skillLvl[23] + ".");
                }
                if (buttonId == 79) {
                    p.requestForceChat("<img=3>My Theiving level is, " + p.skillLvl[17] + ".");
                }
                if (buttonId == 142) {
                    p.requestForceChat("<img=3>My Woodcutting level is, " + p.skillLvl[80] + ".");
                }
                break;


            case 768:
                if (buttonId == 153) {
                    p.requestForceChat("<img=3>Yes");
                }
                if (buttonId == 154) {
                    p.requestForceChat("<img=3>No");
                }
                if (buttonId == 155) {
                    p.requestForceChat("<img=3>Okay");
                }
                if (buttonId == 156) {
                    p.requestForceChat("<img=3>Maybe");
                }
                if (buttonId == 157) {
                    p.requestForceChat("<img=3>I Don't Know");
                }
                if (buttonId == 158) {
                    p.requestForceChat("<img=3>Thank You!");
                }
                if (buttonId == 159) {
                    p.requestForceChat("<img=3>Your Welcome");
                }
                if (buttonId == 160) {
                    p.requestForceChat("<img=3>Sorry");
                }
                if (buttonId == 147) {
                    p.requestForceChat("<img=3>Hello!");
                }
                if (buttonId == 148) {
                    p.requestForceChat("<img=3>Hi");
                }
                if (buttonId == 149) {
                    p.requestForceChat("<img=3>Good Day");
                }
                if (buttonId == 150) {
                    p.requestForceChat("<img=3>Nice To Meet You!");
                }
                if (buttonId == 151) {
                    p.requestForceChat("<img=3>How Are you?");
                }
                if (buttonId == 161) {
                    p.requestForceChat("<img=3>Bye!");
                }
                if (buttonId == 162) {
                    p.requestForceChat("<img=3>See You Later.");
                }
                if (buttonId == 163) {
                    p.requestForceChat("<img=3>Be right back.");
                }
                if (buttonId == 164) {
                    p.requestForceChat("<img=3>I've got to go.");
                }
                if (buttonId == 165) {
                    p.requestForceChat("<img=3>Goodnight");
                }
                if (buttonId == 166) {
                    p.requestForceChat("<img=3>I have to log off.");
                }
                if (buttonId == 167) {
                    p.requestForceChat("<img=3>Please Stop that.");
                }
                if (buttonId == 168) {
                    p.requestForceChat("<img=3>That's Good.");
                }
                if (buttonId == 169) {
                    p.requestForceChat("<img=3>That's Bad.");
                }
                if (buttonId == 170) {
                    p.requestForceChat("<img=3>Please Run.");
                }
                if (buttonId == 171) {
                    p.requestForceChat("<img=3>Hang on a second.");
                }
                if (buttonId == 172) {
                    p.requestForceChat("<img=3>I can't do that.");
                }
                if (buttonId == 173) {
                    p.requestForceChat("<img=3>w00t!");
                }
                if (buttonId == 174) {
                    p.requestForceChat("<img=3>Not right now.");
                }
                if (buttonId == 175) {
                    p.requestForceChat("<img=3>I'm happy.");
                }
                if (buttonId == 176) {
                    p.requestForceChat("<img=3>I'm sad.");
                }
                if (buttonId == 177) {
                    p.requestForceChat("<img=3>I'm great!");
                }
                if (buttonId == 178) {
                    p.requestForceChat("<img=3>I'm good.");
                }
                if (buttonId == 179) {
                    p.requestForceChat("<img=3>I'm okay.");
                }
                if (buttonId == 180) {
                    p.requestForceChat("<img=3>Meh.");
                }
                if (buttonId == 181) {
                    p.requestForceChat("<img=3>I've been beter.");
                }
                if (buttonId == 182) {
                    p.requestForceChat("<img=3>I'm having a bad day.");
                }
                if (buttonId == 183) {
                    p.requestForceChat("<img=3>:-)");
                }
                if (buttonId == 184) {
                    p.requestForceChat("<img=3>:-(");
                }
                if (buttonId == 185) {
                    p.requestForceChat("<img=3>:-|");
                }
                if (buttonId == 186) {
                    p.requestForceChat("<img=3>:-D");
                }
                if (buttonId == 187) {
                    p.requestForceChat("<img=3>:-S");
                }
                if (buttonId == 188) {
                    p.requestForceChat("<img=3>:-O");
                }
                if (buttonId == 189) {
                    p.requestForceChat("<img=3>:-P.");
                }
                if (buttonId == 190) {
                    p.requestForceChat("<img=3>;-)");
                }
                if (buttonId == 191) {
                    p.requestForceChat("<img=3>I won!");
                }
                if (buttonId == 192) {
                    p.requestForceChat("<img=3>Aww, I lost.");
                }
                if (buttonId == 193) {
                    p.requestForceChat("<img=3>Good game, all.");
                }
                if (buttonId == 194) {
                    p.requestForceChat("<img=3>Who wants a rematch?");
                }
                if (buttonId == 195) {
                    p.requestForceChat("<img=3>Well done.");
                }
                if (buttonId == 196) {
                    p.requestForceChat("<img=3>Unlucky, maybe next time.");
                }
                if (buttonId == 197) {
                    p.requestForceChat("<img=3>Got you with that one!");
                }
                if (buttonId == 198) {
                    p.requestForceChat("<img=3>Buh bye.");
                }
                if (buttonId == 199) {
                    p.requestForceChat("<img=3>That's gotta hurt.");
                }
                if (buttonId == 200) {
                    p.requestForceChat("<img=3>Another one bites the dust.");
                }
                if (buttonId == 201) {
                    p.requestForceChat("<img=3>I have you now!");
                }
                if (buttonId == 202) {
                    p.requestForceChat("<img=3>Fear my leet skills.");
                }
                if (buttonId == 203) {
                    p.requestForceChat("<img=3>I'll get you!");
                }
                if (buttonId == 204) {
                    p.requestForceChat("<img=3>I'm coming for ya.");
                }
                if (buttonId == 205) {
                    p.requestForceChat("<img=3>Run and hide, pal.");
                }
                if (buttonId == 206) {
                    p.requestForceChat("<img=3>Don't hurt me!");
                }
                if (buttonId == 207) {
                    p.requestForceChat("<img=3>I love this game!");
                }
                if (buttonId == 208) {
                    p.requestForceChat("<img=3>I Don't like this game.");
                }
                if (buttonId == 210) {
                    p.requestForceChat("<img=3>Nicely done.");
                }
                if (buttonId == 211) {
                    p.requestForceChat("<img=3>Nice hit.");
                }
                if (buttonId == 212) {
                    p.requestForceChat("<img=3>I like yours familiar.");
                }
                if (buttonId == 213) {
                    p.requestForceChat("<img=3>I like your pet.");
                }
                if (buttonId == 214) {
                    p.requestForceChat("<img=3>You look cool.");
                }
                if (buttonId == 215) {
                    p.requestForceChat("<img=3>Nice levels.");
                }
                if (buttonId == 217) {
                    p.requestForceChat("<img=3>Help!");
                }
                if (buttonId == 219) {
                    p.requestForceChat("<img=3>Would you protect my gravestone,please?");
                }
                if (buttonId == 221) {
                    p.requestForceChat("<img=3>Try looking in the Game Guide.");
                }
                if (buttonId == 231) {
                    p.requestForceChat("<img=3>Where are you?");
                }
                if (buttonId == 235) {
                    p.requestForceChat("<img=3>What are you doing?");
                }
                if (buttonId == 239) {
                    p.requestForceChat("<img=3>I am doing a treasure trail.");
                }
                if (buttonId == 493) {
                    p.requestForceChat("<img=3>Bank sale!");
                }
                if (buttonId == 496) {
                    p.requestForceChat("<img=3>That's too much would you accept less?");
                }
                if (buttonId == 497) {
                    p.requestForceChat("<img=3>That's not enough would you offer more?");
                }
                if (buttonId == 498) {
                    p.requestForceChat("<img=3>No deal, sorry.");
                }
                if (buttonId == 499) {
                    p.requestForceChat("<img=3>It's a deal");
                }
                if (buttonId == 503) {
                    p.requestForceChat("<img=3>Could i please have some antipoision?");
                }
                if (buttonId == 504) {
                    p.requestForceChat("<img=3>Could i please have some food?");
                }
                if (buttonId == 505) {
                    p.requestForceChat("<img=3>Hang on; my inventory is full.");
                }
                if (buttonId == 545) {
                    p.requestForceChat("<img=3>I'm planning to log out; this will end the loan..");
                }


            case 666:
                if (buttonId == 18) {
                    p.frames.showInterface(p, 669);
                }
                break;
            case 458:

                switch (buttonId) {
                    case 1:
                        if (p.Choice == 1) {
                            p.Choice = 0;
                            p.Dialogue = 104;
                            p.frames.showChatboxInterface(p, 241);
                            p.frames.animateInterfaceId(p, 9850, 241, 2);
                            p.frames.setNPCId(p, 198, 241, 2);
                            p.frames.setString(p, "Guildmaster", 241, 3);
                            p.frames.setString(p, "I think the oracle on ice mountain has a map.", 241, 4);
                        } else if (p.Choice == 2) {
                            if (Engine.playerItems.HasItemAmount(p, 1538, 1) == true) {
                                p.Choice = 0;
                                p.Dialogue = 109;
                                p.frames.showChatboxInterface(p, 241);
                                p.frames.animateInterfaceId(p, 9850, 241, 2);
                                p.frames.setNPCId(p, 744, 241, 2);
                                p.frames.setString(p, "Klarense", 241, 3);
                                p.frames.setString(p, "Looks like you have a map! Lets go.", 241, 4);
                            } else {
                                p.Choice = 0;
                                p.Dialogue = 0;
                                p.frames.showChatboxInterface(p, 241);
                                p.frames.animateInterfaceId(p, 9827, 241, 2);
                                p.frames.setNPCId(p, 744, 241, 2);
                                p.frames.setString(p, "Klarense", 241, 3);
                                p.frames.setString(p, "Sorry mate, I need a map to do that.", 241, 4);
                            }
                        } else if (p.Choice == 3) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            p.setCoords(2399, 5178, 0);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Choice == 6) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            p.setCoords(Config.hShopsX, Config.hShopsY, 0);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Choice == 7) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            Engine.playerItems.addItem(p, 1075, 1);
                            Engine.playerItems.addItem(p, 1087, 1);
                            Engine.playerItems.addItem(p, 1117, 1);
                            Engine.playerItems.addItem(p, 1155, 1);
                            Engine.playerItems.addItem(p, 8844, 1);
                            Engine.playerItems.addItem(p, 4119, 1);
                            Engine.playerItems.addItem(p, 12985, 1);
                            Engine.playerItems.addItem(p, 10638, 1);
                            Engine.playerItems.addItem(p, 1321, 1);
                            p.frames.sendMessage(p, "<img=2>You recive your meele starter!");
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Choice == 8) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            Engine.shopHandler.openshop(p, 8);
                            p.frames.sendMessage(p, "<img=2>Welcome to Quest Tab Food");
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Cooking == true) {
                            p.CookAmount = 1;
                            p.CookThat(p, p.CookXP, p.CookID, p.CookGet);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Runecrafting == true) {
                            p.RunecraftingAmount = 1;
                            p.RunecraftThat(p, p.RunecraftingXP, p.RunecraftingID, p.RunecraftingGet);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.TalkAgent == true) {
                            if (p.skillLvl[22] > 98) {
                                GetSkillCape(p, 52, 4247, "Estate Agent", "Construction");
                            } else {
                                OnlyLevel(p, 53, 4247, "Estate Agent", 22, "Construction");
                            }
                        } else if (p.DecorChange == true) {
                            if (Engine.playerItems.HasItemAmount(p, 995, 500) == true) {
                                Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), 500);
                                p.HouseDecor = 1585;
                                p.frames.removeShownInterface(p);
                                p.frames.removeChatboxInterface(p);
                                p.frames.sendMessage(p, "You purchased Stone decoration!");
                            } else {
                                p.frames.removeShownInterface(p);
                                p.frames.removeChatboxInterface(p);
                                p.frames.sendMessage(p, "You do not have enough coins.");
                            }
                        } else {
                            if (p.skillLvl[18] > 98) {
                                GetSkillCape(p, 44, 1599, "Duradel", "Slayer");
                            } else {
                                OnlyLevel(p, 45, 1599, "Duradel", 18, "Slayer");
                            }
                        }

                        break;


                    case 2:

                        if (p.Choice == 1) {
                            p.Choice = 0;
                            p.Dialogue = 104;
                            p.frames.showChatboxInterface(p, 241);
                            p.frames.animateInterfaceId(p, 9850, 241, 2);
                            p.frames.setNPCId(p, 198, 241, 2);
                            p.frames.setString(p, "Guildmaster", 241, 3);
                            p.frames.setString(p, "Klarense at port sarim may have a boat for sale.", 241, 4);
                        } else if (p.Choice == 2) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            p.frames.showChatboxInterface(p, 241);
                            p.frames.animateInterfaceId(p, 9827, 241, 2);
                            p.frames.setNPCId(p, 744, 241, 2);
                            p.frames.setString(p, "Klarense", 241, 3);
                            p.frames.setString(p, "Uhh..yeah I guess they're pretty cool.", 241, 4);
                        } else if (p.Choice == 3) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            p.setCoords(2442, 3090, 0);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Choice == 6) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            p.setCoords(3165, 3484, 0);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Choice == 7) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            Engine.playerItems.addItem(p, 556, 1000);
                            Engine.playerItems.addItem(p, 558, 1000);
                            Engine.playerItems.addItem(p, 555, 1000);
                            Engine.playerItems.addItem(p, 557, 1000);
                            Engine.playerItems.addItem(p, 554, 1000);
                            Engine.playerItems.addItem(p, 1379, 1);
                            Engine.playerItems.addItem(p, 579, 1);
                            Engine.playerItems.addItem(p, 577, 1);
                            Engine.playerItems.addItem(p, 1011, 1);
                            p.frames.sendMessage(p, "<img=2>You recive your magic starter!");
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Choice == 8) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            Engine.shopHandler.openshop(p, 9);
                            p.frames.sendMessage(p, "<img=2>For everything a skiller needs to live.");
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Cooking == true) {
                            p.CookAmount = 5;
                            p.CookThat(p, p.CookXP, p.CookID, p.CookGet);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Runecrafting == true) {
                            p.RunecraftingAmount = 5;
                            p.RunecraftThat(p, p.RunecraftingXP, p.RunecraftingID, p.RunecraftingGet);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.TalkAgent == true) {
                            p.frames.showChatboxInterface(p, 241);
                            p.frames.animateInterfaceId(p, 9850, 241, 2);
                            p.frames.setNPCId(p, 4247, 241, 2);
                            p.frames.setString(p, "EstateAgent", 241, 3);
                            p.frames.setString(p, "Just type ::goinhouse [player name].", 241, 4);
                        } else if (p.DecorChange == true) {
                            if (p.skillLvl[22] < 50) {
                                p.frames.sendMessage(p, "You need level 50 construction for this.");
                                p.frames.removeShownInterface(p);
                                p.frames.removeChatboxInterface(p);
                            } else {
                                if (Engine.playerItems.HasItemAmount(p, 995, 1000) == true) {
                                    Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), 1000);
                                    p.HouseDecor = 1588;
                                    p.frames.removeShownInterface(p);
                                    p.frames.removeChatboxInterface(p);
                                    p.frames.sendMessage(p, "You purchased Dark Stone decoration!");
                                } else {
                                    p.frames.removeShownInterface(p);
                                    p.frames.removeChatboxInterface(p);
                                    p.frames.sendMessage(p, "You do not have enough coins.");
                                }
                            }
                        } else {
                            p.SlayerTask = Misc.random(4);
                            p.SlayerAm = 1 + Misc.random(30);
                            p.frames.showChatboxInterface(p, 241);
                            p.frames.animateInterfaceId(p, 9850, 241, 2);
                            p.frames.setNPCId(p, 1599, 241, 2);
                            p.frames.setString(p, "Duradel", 241, 3);
                            if (p.SlayerTask == 0) {
                                p.frames.setString(p, "You must slay " + p.SlayerAm + " Dragons.", 241, 4);
                            }
                            if (p.SlayerTask == 1) {
                                p.frames.setString(p, "You must slay " + p.SlayerAm + " Guards.", 241, 4);
                            }
                            if (p.SlayerTask == 2) {
                                p.frames.setString(p, "You must slay " + p.SlayerAm + " Giants.", 241, 4);
                            }
                            if (p.SlayerTask == 3) {
                                p.frames.setString(p, "You must slay " + p.SlayerAm + " Ghosts.", 241, 4);
                            }
                            if (p.SlayerTask == 4) {
                                p.frames.setString(p, "You must slay " + p.SlayerAm + " Heroes.", 241, 4);
                            }
                        }
                        break;

                    case 3:
                        if (p.Choice == 1) {
                            p.Choice = 0;
                            p.Dialogue = 104;
                            p.frames.showChatboxInterface(p, 241);
                            p.frames.animateInterfaceId(p, 9850, 241, 2);
                            p.frames.setNPCId(p, 198, 241, 2);
                            p.frames.setString(p, "Guildmaster", 241, 3);
                            p.frames.setString(p, "I think the Duke of lumbridge has some sort of shield.", 241, 4);
                        } else if (p.Choice == 2) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            p.setCoords(2442, 3090, 0);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Choice == 3) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            p.setCoords(3048, 3203, 0);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Choice == 6) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            if ((p.donator < 1) && (p.rights < 1)) {
                                p.frames.sendMessage(p, "<img=2>You must be a donator to use this teleport!");
                                p.frames.removeShownInterface(p);
                                p.frames.removeChatboxInterface(p);
                            } else if ((p.donator >= 1) || (p.rights >= 1)) {
                                p.teleportTo(3044, 3381, 1, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.frames.sendMessage(p, "Welcome to the Donator Pub!");
                                p.frames.removeShownInterface(p);
                                p.frames.removeChatboxInterface(p);
                            }
                            if (p.Choice == 7) {
                                p.Choice = 0;
                                p.Dialogue = 0;
                                Engine.playerItems.addItem(p, 556, 1000);
                                Engine.playerItems.addItem(p, 558, 1000);
                                Engine.playerItems.addItem(p, 555, 1000);
                                Engine.playerItems.addItem(p, 557, 1000);
                                Engine.playerItems.addItem(p, 554, 1000);
                                Engine.playerItems.addItem(p, 1379, 1);
                                Engine.playerItems.addItem(p, 579, 1);
                                Engine.playerItems.addItem(p, 577, 1);
                                Engine.playerItems.addItem(p, 1011, 1);
                                p.frames.sendMessage(p, "<img=2>You recive your range starter!");
                                p.frames.removeShownInterface(p);
                                p.frames.removeChatboxInterface(p);
                            }
                        } else if (p.Choice == 8) {
                            p.Choice = 0;
                            p.Dialogue = 0;
                            Engine.shopHandler.openshop(p, 2);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Cooking == true) {
                            p.CookAmount = 28;
                            p.CookThat(p, p.CookXP, p.CookID, p.CookGet);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.Runecrafting == true) {
                            p.RunecraftingAmount = 28;
                            p.RunecraftThat(p, p.RunecraftingXP, p.RunecraftingID, p.RunecraftingGet);
                            p.frames.removeShownInterface(p);
                            p.frames.removeChatboxInterface(p);
                        } else if (p.TalkAgent == true) {
                            p.Runecrafting = false;

                            p.Cooking = false;
                            p.TalkAgent = false;
                            p.DecorChange = true;
                            p.frames.setString(p, "Stone 500", 458, 1);
                            p.frames.setString(p, "Dark Stone 1000", 458, 2);
                            p.frames.setString(p, "White Stone 2500", 458, 3);
                            p.frames.showChatboxInterface(p, 458);
                        } else if (p.DecorChange == true) {
                            if (p.skillLvl[22] < 80) {
                                p.frames.sendMessage(p, "You need level 80 construction for this.");
                                p.frames.removeShownInterface(p);
                                p.frames.removeChatboxInterface(p);
                            } else {
                                if (Engine.playerItems.HasItemAmount(p, 995, 2500) == true) {
                                    Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), 2500);
                                    p.HouseDecor = 13116;
                                    p.frames.removeShownInterface(p);
                                    p.frames.removeChatboxInterface(p);
                                    p.frames.sendMessage(p, "You purchased White Stone decoration!");
                                } else {
                                    p.frames.removeShownInterface(p);
                                    p.frames.removeChatboxInterface(p);
                                    p.frames.sendMessage(p, "You do not have enough coins.");
                                }
                            }
                        } else {
                            p.frames.showInterface(p, 120);
                            p.SlayerCaveTimer = 4;
                            p.frames.showChatboxInterface(p, 241);
                            p.frames.animateInterfaceId(p, 9780, 241, 2);
                            p.frames.setNPCId(p, 1599, 241, 2);
                            p.frames.setString(p, "", 241, 3);
                            p.frames.setString(p, "Be careful in there...", 241, 4);
                        }
                        break;
                }
                break;

            case 596://MALES
                switch (buttonId) {

                    case 160:
                        p.frames.removeShownInterface(p);
                        p.frames.sendMessage(p, "You didn't pay a thing!.");
                        break;

                    case 245:
                        p.color[2] = 2;
                        break;
                    case 167: //Bald
                        p.look[0] = 0;
                        break;
                    case 168: //Dreadlochs
                        p.look[0] = 1;
                        break;
                    case 169: //Long Hair
                        p.look[0] = 2;
                        break;
                    case 170: //medium hair
                        p.look[0] = 3;
                        break;
                    case 171: //short
                        p.look[0] = 4;
                        break;
                    case 172: //monk
                        p.look[0] = 5;
                        break;
                    case 173: //Closed cropped
                        p.look[0] = 6;
                        break;
                    case 174: //wild spikes
                        p.look[0] = 7;
                        break;
                    case 175: //spikes
                        p.look[0] = 8;
                        break;
                    case 176: //mohawk
                        p.look[0] = 91;
                        break;
                    case 177: //Wind braids
                        p.look[0] = 92;
                        break;
                    case 178: //Quiff
                        p.look[0] = 93;
                        break;
                    case 179: //samurai
                        p.look[0] = 94;
                        break;
                    case 180: //prince
                        p.look[0] = 95;
                        break;
                    case 181: //curtains
                        p.look[0] = 96;
                        break;
                    case 182: //long curtains
                        p.look[0] = 97;
                        break;

                    case 183: //Goatee
                        p.look[1] = 10;
                        break;
                    case 184: //Long Beard
                        p.look[1] = 11;
                        break;
                    case 185: //Med Beard
                        p.look[1] = 12;
                        break;
                    case 186: //Mustouche
                        p.look[1] = 13;
                        break;
                    case 187: //Clean Shaven
                        p.look[1] = 14;
                        break;
                    case 188: //Short Beard
                        p.look[1] = 15;
                        break;
                    case 189: //Short Full
                        p.look[1] = 16;
                        break;
                    case 190: //Split
                        p.look[1] = 17;
                        break;
                    case 191: //Handle Bar 
                        p.look[1] = 98;
                        break;
                    case 192: //Mutton
                        p.look[1] = 99;
                        break;
                    case 193: //Full Mutton
                        p.look[1] = 100;
                        break;
                    case 194: //Full Mustouche
                        p.look[1] = 101;
                        break;
                    case 195: //Waxed
                        p.look[1] = 102;
                        break;
                    case 196: //Dali
                        p.look[1] = 103;
                        break;
                    case 197: //Visier
                        p.look[1] = 104;
                        break;


                }
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                break;
            case 592://FEMALES
                switch (buttonId) {

                    case 21:
                        p.frames.removeShownInterface(p);
                        p.frames.sendMessage(p, "You didn't pay a thing!.");
                        break;

                    case 168: //Bald
                        p.look[0] = 45;
                        break;
                    case 169: //Bun
                        p.look[0] = 46;
                        break;
                    case 170: //DreadLocks 
                        p.look[0] = 47;
                        break;
                    case 171: //Long 
                        p.look[0] = 48;
                        break;
                    case 172: //Short 51
                        p.look[0] = 51;
                        break;
                    case 173: //PigTails 50
                        p.look[0] = 50;
                        break;
                    case 174: //CrewCut
                        p.look[0] = 52;
                        break;
                    case 175: //ClosedCropped
                        p.look[0] = 53;
                        break;
                    case 176: //Wild Spikes 54
                        p.look[0] = 54;
                        break;
                    case 177: //Spikes 55
                        p.look[0] = 55;
                        break;
                    case 178: //Side pony tail
                        p.look[0] = 135;
                        break;
                    case 179: //curls
                        p.look[0] = 136;
                        break;
                    case 180: //Wind braids
                        p.look[0] = 137;
                        break;
                    case 181: //poneytail
                        p.look[0] = 138;
                        break;
                    case 182: //braids
                        p.look[0] = 139;
                        break;
                    case 183: //4 poneys 140
                        p.look[0] = 140;
                        break;
                    case 184: //bob
                        p.look[0] = 141;
                        break;
                    case 185: //layered
                        p.look[0] = 142;
                        break;
                    case 186: //straight
                        p.look[0] = 143;
                        break;
                    case 187: //long braids
                        p.look[0] = 144;
                        break;
                    case 188: //curtains
                        p.look[0] = 145;
                        break;
                    case 189: //ear muffs
                        p.look[0] = 146;
                        break;


                }


                p.appearanceUpdateReq = true;
                p.updateReq = true;
                break;

            case 591:
                switch (buttonId) {

                    case 180:
                        p.frames.removeShownInterface(p);
                        p.frames.sendMessage(p, "You didn't pay a thing!.");
                        break;

                    case 185: //Striped Sweater
                        p.look[2] = 111;
                        break;
                    case 186://Woollen Vest
                        p.look[2] = 113;
                        break;
                    case 187://Princely Vest
                        p.look[2] = 114;
                        break;
                    case 188://Tattered WaistCoat
                        p.look[2] = 115;
                        break;
                    case 189://Fine Shirt
                        p.look[2] = 112;
                        break;
                    case 190://Waist Coat
                        p.look[2] = 116;
                        break;
                    case 191://Plain Top 18
                        p.look[2] = 18;
                        break;
                    case 192://Light Buttons 19
                        p.look[2] = 19;
                        break;
                    case 193://Dark Buttons 20
                        p.look[2] = 20;
                        break;
                    case 194://Jacket 21
                        p.look[2] = 21;
                        break;
                    case 195://Shirt22
                        p.look[2] = 22;
                        break;
                    case 196://Stitching 23
                        p.look[2] = 23;
                        break;
                    case 197://Ragged Top 24
                        p.look[2] = 24;
                        break;
                    case 198://Two Toned 25
                        p.look[2] = 25;
                        break;

                    case 199: //Striped Arms
                        p.look[3] = 105;
                        break;
                    case 200: //Princley Sleeves 108
                        p.look[3] = 108;
                        break;
                    case 201: //Fine Cuffs 106
                        p.look[3] = 106;
                        break;
                    case 202: //Woollen Sleeves 107
                        p.look[3] = 107;
                        break;
                    case 203: //Ragged Arms
                        p.look[3] = 109;
                        break;

                    case 204: //Tattered Sleeves
                        p.look[3] = 110;
                        break;

                    case 205: //LooseSleeve 28
                        p.look[3] = 28;
                        break;

                    case 206: //Regular 26
                        p.look[3] = 26;
                        break;

                    case 207: //Muscle Bound 27
                        p.look[3] = 27;
                        break;

                    case 208: //Large Cuffed
                        p.look[3] = 29;
                        break;

                    case 209: //Thin Sleeved
                        p.look[3] = 30;
                        break;

                    case 210: //Shoulder Pads
                        p.look[3] = 31;
                        break;

                    case 211: //Plain Trousers
                        p.look[5] = 36;
                        break;
                    case 212: //Princley Breeches
                        p.look[5] = 85;
                        break;
                    case 213: //shorts 37
                        p.look[5] = 37;
                        break;
                    case 214: //Ragged Breeches 89
                        p.look[5] = 89;
                        break;
                    case 215: //Tattered Breeches90
                        p.look[5] = 90;
                        break;
                    case 216: //Torn Trousers
                        p.look[5] = 41;
                        break;
                    case 217: //Breeches
                        p.look[5] = 86;
                        break;
                    case 218: //Striped Trousers 88
                        p.look[5] = 88;
                        break;
                    case 219: //Turn ups 39
                        p.look[5] = 39;
                        break;
                    case 220: //Flares 38
                        p.look[5] = 38;
                        break;

                    case 221: //Fine Breeches 87
                        p.look[5] = 87;
                        break;

                }
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                break;
            case 751:
                switch (buttonId) {

                    case 27:
                        p.frames.showInterface(p, 553);
                        break;
                }
                break;

            case 320: // Skills Tab.
                boolean lvlup = false;

                if (p.AtDuel()) {
                    p.frames.sendMessage(p, "You cannot teleport out of a duel!");
                } else {
                    switch (buttonId) {
                        case 125: //Attack
                            p.setCoords(2846, 3542, 0);
                            break;
                        case 126: //Strength
                            p.setCoords(2846, 3542, 0);
                            break;
                        case 127: //Defence
                            p.setCoords(2846, 3542, 0);
                            break;
                        case 128: //Ranged
                            p.setCoords(2671, 3425, 0);
                            break;
                        case 129: //Prayer
                            p.setCoords(3052, 3480, 0);
                            break;
                        case 130: //Magic
                            p.setCoords(2595, 3087, 1);
                            break;
                        case 131: //Runecrafting
                            p.setCoords(2609, 3092, 0);
                            break;
                        case 132: //Construction
                            p.setCoords(2544, 3095, 0);
                            break;
                        case 133: //Hitpoints
                            p.setCoords(2846, 3542, 0);
                            break;
                        case 134: //Agility
                            p.setCoords(2552, 3556, 0);
                            break;
                        case 135: //Herblore
                            p.setCoords(2924, 3483, 0);
                            break;
                        case 136: //Thieving
                            p.setCoords(2663, 3307, 0);
                            break;
                        case 137: //Crafting
                            p.setCoords(2933, 3285, 0);
                            break;
                        case 138: //Fletching
                            p.setCoords(2822, 3440, 0);
                            break;
                        case 139: //Slayer
                            p.setCoords(2844, 3539, 0);
                            break;
                        case 140: //Hunter
                            p.setCoords(2199, 3224, 0);
                            break;
                        case 141: //Mining
                            p.setCoords(2941, 3281, 0);
                            break;
                        case 142: //Smithing
                            p.setCoords(2973, 3372, 0);
                            break;
                        case 143: //Fishing
                            p.setCoords(2507, 3519, 0);
                            break;
                        case 144: //Cooking
                            p.setCoords(2813, 3436, 0);
                            break;
                        case 145: //Firemaking
                            p.setCoords(2702, 3424, 0);
                            break;
                        case 146: //Woodcutting
                            if (p.skillLvl[8] < 30) {
                                p.setCoords(3165, 3415, 0);
                                p.frames.sendMessage(p, "<img=2> This location is best for Normal Trees and Oaks!");
                            } else if (p.skillLvl[8] >= 30 && p.skillLvl[8] < 45) {
                                p.setCoords(3086, 3234, 0);
                                p.frames.sendMessage(p, "<img=2> This location is best for Willow Trees!");
                            } else if (p.skillLvl[8] >= 45 && p.skillLvl[8] < 60) {
                                p.setCoords(2726, 3500, 0);
                                p.frames.sendMessage(p, "<img=2> This location is best for Maple Trees!");
                            } else if (p.skillLvl[8] >= 60 && p.skillLvl[8] < 75) {
                                p.setCoords(2710, 3463, 0);
                                p.frames.sendMessage(p, "<img=2> This location is best for Yew Tress!");
                            } else if (p.skillLvl[8] >= 75) {
                                p.setCoords(2694, 3426, 0);
                                p.frames.sendMessage(p, "<img=2> This location is best for Magic Tress!");
                            }
                            break;
                        case 147: //Farming
                            p.setCoords(2812, 3464, 0);
                            break;
                        case 148: //Summoning
                            p.setCoords(2924, 3444, 0);
                            break;
                    }
                }
                break;


            case 663:
                NPC np = Engine.npcs[p.FamiliarID];
                switch (buttonId) {
                    case 23://Dismiss
                        if (np != null) {
                            p.FamiliarType = 0;
                            np.isDead = true;
                            p.FamiliarID = 0;
                            p.frames.setTab(p, 80, 18);
                        }
                        break;

                    case 21://call
                        if (np != null) {
                            np.absX = p.absX;
                            np.absY = p.absY + 1;
                            np.heightLevel = p.heightLevel;
                            p.requestGFX(1315, 0);
                        }
                        break;
                }

                break;
            case 192:
                m.modernMagicAB(p, buttonId);
                break;
            case 193:
                m.ancientMagicAB(p, buttonId);
                break;
            case 387:

                /*
                 * Equipment tab.
                 */
                if (buttonId == 55) {
                    p.frames.showInterface(p, 667);
                    p.frames.setInventory(p, 763);
                    p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                    p.frames.setItems(p, -1, 64208, 94, p.equipment, p.equipmentN);
                    p.setEquipmentBonus();
                }
                break;

            case 154:
                Magic.MagicOnItemHandle(p, packetId, packetSize);
                break;
            case 70:
                Magic.magicAop(p, packetId, packetSize);
                break;


            case 771:
                if (buttonId == 92 && p.gender < 1) {
                    p.look[0] = p.look[0] - 1;
                    if (p.look[0] < 0)
                        p.look[0] = 260;
                    if (p.look[0] > 8 && p.look[0] < 91)
                        p.look[0] = 8;
                    if (p.look[0] > 97 && p.look[0] < 246)
                        p.look[0] = 97;
                    if (p.look[0] > 260 && p.look[0] < 1000)
                        p.look[0] = 260;
                }
                if (buttonId == 92 && p.gender > 0) {
                    p.look[0] = p.look[0] - 1;
                    if (p.look[0] < 45)
                        p.look[0] = 280;
                    if (p.look[0] > 55 && p.look[0] < 135)
                        p.look[0] = 55;
                    if (p.look[0] > 144 && p.look[0] < 269)
                        p.look[0] = 144;
                    if (p.look[0] > 280 && p.look[0] < 1000)
                        p.look[0] = 45;
                }
                if (buttonId == 93 && p.gender < 1) {
                    p.look[0] = p.look[0] + 1;
                    if (p.look[0] > 8 && p.look[0] < 91)
                        p.look[0] = 91;
                    if (p.look[0] > 97 && p.look[0] < 246)
                        p.look[0] = 246;
                    if (p.look[0] > 260 && p.look[0] < 1000)
                        p.look[0] = 0;
                }
                if (buttonId == 93 && p.gender > 0) {
                    p.look[0] = p.look[0] + 1;
                    if (p.look[0] > 55 && p.look[0] < 135)
                        p.look[0] = 135;
                    if (p.look[0] > 144 && p.look[0] < 269)
                        p.look[0] = 269;
                    if (p.look[0] > 280 && p.look[0] < 1000)
                        p.look[0] = 45;
                }
                if (buttonId == 97) {
                    p.look[1] = p.look[1] - 1;
                    if (p.look[1] < 10)
                        p.look[1] = 17;
                }
                if (buttonId == 98) {
                    p.look[1] = p.look[1] + 1;
                    if (p.look[1] > 17)
                        p.look[1] = 10;

                }
                if (buttonId == 100) {
                    p.color[0] = 20;
                }
                if (buttonId == 101) {
                    p.color[0] = 19;
                }
                if (buttonId == 102) {
                    p.color[0] = 10;
                }
                if (buttonId == 103) {
                    p.color[0] = 18;
                }
                if (buttonId == 104) {
                    p.color[0] = 4;
                }
                if (buttonId == 105) {
                    p.color[0] = 5;
                }
                if (buttonId == 106) {
                    p.color[0] = 15;
                }
                if (buttonId == 107) {
                    p.color[0] = 7;
                }
                if (buttonId == 108) {
                    p.color[0] = 26;
                }
                if (buttonId == 109) {
                    p.color[0] = 6;
                }
                if (buttonId == 110) {
                    p.color[0] = 21;
                }
                if (buttonId == 111) {
                    p.color[0] = 9;
                }
                if (buttonId == 112) {
                    p.color[0] = 22;
                }
                if (buttonId == 113) {
                    p.color[0] = 17;
                }
                if (buttonId == 114) {
                    p.color[0] = 8;
                }
                if (buttonId == 115) {
                    p.color[0] = 16;
                }
                if (buttonId == 116) {
                    p.color[0] = 11;
                }
                if (buttonId == 117) {
                    p.color[0] = 24;
                }
                if (buttonId == 118) {
                    p.color[0] = 23;
                }
                if (buttonId == 119) {
                    p.color[0] = 3;
                }
                if (buttonId == 120) {
                    p.color[0] = 2;
                }
                if (buttonId == 121) {
                    p.color[0] = 1;
                }
                if (buttonId == 122) {
                    p.color[0] = 14;
                }
                if (buttonId == 123) {
                    p.color[0] = 13;
                }
                if (buttonId == 124) {
                    p.color[0] = 12;
                }
                if (buttonId == 158) {
                    p.color[4] = 7;
                }
                if (buttonId == 151) {
                    p.color[4] = 8;
                }
                if (buttonId == 152) {
                    p.color[4] = 1;
                }
                if (buttonId == 153) {
                    p.color[4] = 2;
                }
                if (buttonId == 154) {
                    p.color[4] = 3;
                }
                if (buttonId == 155) {
                    p.color[4] = 4;
                }
                if (buttonId == 156) {
                    p.color[4] = 5;
                }
                if (buttonId == 157) {
                    p.color[4] = 6;
                }
                if (buttonId == 307) { //BOOT-COLOR
                    p.color[3] = 6;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 308) { //BOOT-COLOR
                    p.color[3] = 1;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 309) { //BOOT-COLOR
                    p.color[3] = 2;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 310) { //BOOT-COLOR
                    p.color[3] = 3;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 311) { //BOOT-COLOR
                    p.color[3] = 4;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 312) { //BOOT-COLOR
                    p.color[3] = 5;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }

                if (buttonId == 263) { //LEG-COLOR
                    p.color[2] = 0;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 258) { //LEG-COLOR
                    p.color[2] = 1;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 275) { //LEG-COLOR
                    p.color[2] = 2;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 251) { //LEG-COLOR
                    p.color[2] = 3;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 271) { //LEG-COLOR
                    p.color[2] = 4;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 257) { //LEG-COLOR
                    p.color[2] = 5;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 272) { //LEG-COLOR
                    p.color[2] = 6;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 255) { //LEG-COLOR
                    p.color[2] = 7;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 265) { //LEG-COLOR
                    p.color[2] = 8;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 262) { //LEG-COLOR
                    p.color[2] = 9;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 259) { //LEG-COLOR
                    p.color[2] = 10;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 268) { //LEG-COLOR
                    p.color[2] = 11;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 254) { //LEG-COLOR
                    p.color[2] = 12;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 253) { //LEG-COLOR
                    p.color[2] = 13;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 260) { //LEG-COLOR
                    p.color[2] = 14;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 267) { //LEG-COLOR
                    p.color[2] = 15;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 276) { //LEG-COLOR
                    p.color[2] = 16;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 274) { //LEG-COLOR
                    p.color[2] = 17;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 273) { //LEG-COLOR
                    p.color[2] = 18;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 256) { //LEG-COLOR
                    p.color[2] = 19;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 266) { //LEG-COLOR
                    p.color[2] = 20;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 264) { //LEG-COLOR
                    p.color[2] = 21;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 252) { //LEG-COLOR
                    p.color[2] = 22;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 250) { //LEG-COLOR
                    p.color[2] = 23;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 249) { //LEG-COLOR
                    p.color[2] = 24;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 261) { //LEG-COLOR
                    p.color[2] = 25;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 248) { //LEG-COLOR
                    p.color[2] = 26;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 270) { //LEG-COLOR
                    p.color[2] = 27;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 269) { //LEG-COLOR
                    p.color[2] = 28;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }

                if (buttonId == 198) { //TOP-COLOR
                    p.color[1] = 0;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 216) { //TOP-COLOR
                    p.color[1] = 1;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 191) { //TOP-COLOR
                    p.color[1] = 2;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 212) { //TOP-COLOR
                    p.color[1] = 3;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 197) { //TOP-COLOR
                    p.color[1] = 4;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 213) { //TOP-COLOR
                    p.color[1] = 5;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 195) { //TOP-COLOR
                    p.color[1] = 6;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 206) { //TOP-COLOR
                    p.color[1] = 7;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 202) { //TOP-COLOR
                    p.color[1] = 8;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 199) { //TOP-COLOR
                    p.color[1] = 9;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 209) { //TOP-COLOR
                    p.color[1] = 10;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 194) { //TOP-COLOR
                    p.color[1] = 11;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 193) { //TOP-COLOR
                    p.color[1] = 12;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 200) { //TOP-COLOR
                    p.color[1] = 13;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 208) { //TOP-COLOR
                    p.color[1] = 14;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 203) { //TOP-COLOR
                    p.color[1] = 15;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 217) { //TOP-COLOR
                    p.color[1] = 16;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 215) { //TOP-COLOR
                    p.color[1] = 17;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 214) { //TOP-COLOR
                    p.color[1] = 18;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 196) { //TOP-COLOR
                    p.color[1] = 19;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 207) { //TOP-COLOR
                    p.color[1] = 20;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 205) { //TOP-COLOR
                    p.color[1] = 21;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 192) { //TOP-COLOR
                    p.color[1] = 22;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 190) { //TOP-COLOR
                    p.color[1] = 23;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 189) { //TOP-COLOR
                    p.color[1] = 24;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 201) { //TOP-COLOR
                    p.color[1] = 25;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 204) { //TOP-COLOR
                    p.color[1] = 26;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 211) { //TOP-COLOR
                    p.color[1] = 27;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 210) { //TOP-COLOR
                    p.color[1] = 28;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 362) { //Confirm
                    p.frames.removeShownInterface(p);
                    p.frames.sendMessage(p, "You Have Changed your look.");
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 321) { //random body
                    p.look[3] = Misc.random(6) + 26;
                    p.look[4] = Misc.random(2) + 33;
                    p.look[5] = Misc.random(5) + 36;
                    p.look[6] = Misc.random(2) + 42;
                    p.color[1] = Misc.random(28);
                    p.color[2] = Misc.random(28);
                    p.color[3] = Misc.random(5);
                    p.color[4] = Misc.random(7);
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 169) { //random hair
                    p.look[0] = Misc.random(8);
                    p.look[1] = Misc.random(7) + 10;
                    p.color[0] = Misc.random(24);
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 49) { //male
                    p.look[0] = 5; // Hair
                    p.look[1] = 14; // Beard
                    p.look[2] = 18; // Torso
                    p.look[3] = 27; // Arms
                    p.look[4] = 33; // Bracelets
                    p.look[5] = 87; // Legs
                    p.look[6] = 42; // Shoes
                    p.gender = 0;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 52) { //male
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
                if (buttonId == 354 || buttonId == 353) { //leg + & -
                    p.look[5] = Misc.random(5) + 36;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }

            case 13:
                p.bankpin();
                if (buttonId == 10) {
                    buttonId = 0;
                }
                if (buttonId == 30) {
                    p.pinStage = 1;
                }
                if (buttonId >= 0 && buttonId <= 10) {
                    if (p.hasBankPin == 0) {
                        p.frames.setString(p, "1", 13, 11);


                        p.frames.setString(p, "2", 13, 12);
                        p.frames.setString(p, "3", 13, 13);
                        p.frames.setString(p, "4", 13, 14);
                        p.frames.setString(p, "5", 13, 15);
                        p.frames.setString(p, "6", 13, 16);
                        p.frames.setString(p, "7", 13, 17);
                        p.frames.setString(p, "8", 13, 18);
                        p.frames.setString(p, "9", 13, 19);
                        p.frames.setString(p, "0", 13, 20);
                        if (p.pinStage == 1) {
                            p.pinNumOne = buttonId;
                            p.frames.sendMessage(p, "You entered your first bank pin, enter your next one.");
                            p.frames.setString(p, "*", 13, 21);
                            p.frames.setString(p, "Now click the SECOND digit.", 13, 32);
                            p.pinStage++;
                        } else if (p.pinStage == 2) {
                            p.pinNumTwo = buttonId;
                            p.frames.sendMessage(p, "You entered your second bank pin, enter your next one.");
                            p.frames.setString(p, "*", 13, 22);
                            p.frames.setString(p, "Now click the THIRD digit.", 13, 32);
                            p.pinStage++;
                        } else if (p.pinStage == 3) {
                            p.pinNumThree = buttonId;
                            p.frames.sendMessage(p, "You entered your third bank pin, enter your next one.");
                            p.frames.setString(p, "*", 13, 23);
                            p.frames.setString(p, "Now click the FORTH digit.", 13, 32);
                            p.pinStage++;
                        } else if (p.pinStage == 4) {
                            p.pinNumFour = buttonId;
                            p.frames.sendMessage(p, "You entered all bank pins.");
                            p.pinStage = 1;
                            p.hasBankPin = 1;
                            p.alreadyBanked = false;
                            p.frames.removeShownInterface(p);
                        }
                    } else {
                        p.frames.setString(p, "1", 13, 11);
                        p.frames.setString(p, "2", 13, 12);
                        p.frames.setString(p, "3", 13, 13);
                        p.frames.setString(p, "4", 13, 14);
                        p.frames.setString(p, "5", 13, 15);
                        p.frames.setString(p, "6", 13, 16);
                        p.frames.setString(p, "7", 13, 17);
                        p.frames.setString(p, "8", 13, 18);
                        p.frames.setString(p, "9", 13, 19);
                        p.frames.setString(p, "0", 13, 20);
                        if (p.pinStage == 1) {
                            if (buttonId == p.pinNumOne) {
                                p.pinStage = 2;
                                p.frames.sendMessage(p, "You entered your first bank pin number correctly, enter your next one.");
                                p.frames.setString(p, "*", 13, 21);
                                p.frames.setString(p, "Now click the SECOND digit.", 13, 32);
                            } else {
                                p.pinStage = 1;
                                p.frames.sendMessage(p, "You entered your pin in wrong.");
                                p.frames.setString(p, "?", 13, 21);
                                p.frames.setString(p, "First click the FIRST digit.", 13, 32);
                            }
                        } else if (p.pinStage == 2) {
                            if (buttonId == p.pinNumTwo) {
                                p.pinStage = 3;
                                p.frames.sendMessage(p, "You entered your second bank pin number correctly, enter your next one.");
                                p.frames.setString(p, "*", 13, 22);
                                p.frames.setString(p, "Now click the THRID digit.", 13, 32);
                            } else {
                                p.pinStage = 1;
                                p.frames.sendMessage(p, "You entered your pin in wrong.");
                                p.frames.setString(p, "?", 13, 21);
                                p.frames.setString(p, "?", 13, 22);
                                p.frames.setString(p, "First click the FIRST digit.", 13, 32);
                            }
                        } else if (p.pinStage == 3) {
                            if (buttonId == p.pinNumThree) {
                                p.pinStage = 4;
                                p.frames.sendMessage(p, "You entered your third bank pin number correctly, enter your next one.");
                                p.frames.setString(p, "*", 13, 23);
                                p.frames.setString(p, "Now click the FORTH digit.", 13, 32);
                            } else {
                                p.pinStage = 1;
                                p.frames.sendMessage(p, "You entered your pin in wrong.");
                                p.frames.setString(p, "?", 13, 21);
                                p.frames.setString(p, "?", 13, 22);
                                p.frames.setString(p, "?", 13, 23);
                                p.frames.setString(p, "First click the FIRST digit.", 13, 32);
                            }
                        } else if (p.pinStage == 4) {
                            if (buttonId == p.pinNumFour) {
                                p.frames.sendMessage(p, "You entered all bank pins correctly.");
                                p.openBank();
                                p.pinStage = 1;
                                p.alreadyBanked = true;
                            } else {
                                p.pinStage = 1;
                                p.frames.sendMessage(p, "You entered your pin in wrong.");
                                p.frames.setString(p, "First click the FIRST digit.", 13, 32);
                            }
                        }
                    }
                }
                break;

            case 430:
                if (buttonId == 14) {
                    if (p.skillLvl[6] >= 94) {
                        if (Engine.playerItems.invItemCount(p, 557) > 10 && Engine.playerItems.invItemCount(p, 560) > 2 && Engine.playerItems.invItemCount(p, 9075) > 4) {
                            if (!p.vengOn) {
                                if (System.currentTimeMillis() - p.lastVeng >= 30) {
                                    p.requestAnim(4410, 0);
                                    p.requestGFX(726, 0);
                                    p.vengOn = true;
                                    p.lastVeng = System.currentTimeMillis();
                                    Engine.playerItems.deleteItem(p, 557, Engine.playerItems.getItemSlot(p, 557), 10);
                                    Engine.playerItems.deleteItem(p, 560, Engine.playerItems.getItemSlot(p, 560), 2);
                                    Engine.playerItems.deleteItem(p, 9075, Engine.playerItems.getItemSlot(p, 9075), 4);
                                } else {
                                    p.frames.sendMessage(p, "You can only cast vengeance spells every 30 seconds.");
                                }
                            } else {
                                p.frames.sendMessage(p, "You already have vengeance casted.");
                            }
                        } else {
                            p.frames.sendMessage(p, "You don't have enough runes to cast this spell.");
                        }
                    } else {
                        p.frames.sendMessage(p, "You need a magic level of 94 to cast this spell.");
                    }
                }

                break;
            case 398:

                switch (buttonId) {
                    case 1:
                        if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.OwnHouse == true) {
                            p.KickPlayers = false;
                            p.BuildingMode = false;
                            p.frames.sendMessage(p, "You leave building mode.");
                        } else {
                            p.frames.sendMessage(p, "You need to be in your house to do this.");
                        }
                        break;
                    case 14:
                        if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.OwnHouse == true) {
                            p.KickPlayers = true;
                            p.BuildingMode = true;
                            p.frames.sendMessage(p, "You are now in building mode.");
                            p.frames.sendMessage(p, "Start with ::newroom (0 1 2 3 or 4). or ::deleteroom (0 1 2 3 or 4)");
                            p.frames.sendMessage(p, "Room buildable: Parlour, Garden, Kitchen, Bedroom, Chapel, Throne Room.");
                        } else {
                            p.frames.sendMessage(p, "You need to be in your house to do this.");
                        }
                        break;
                    case 15:
                        if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.OwnHouse == true) {
                            p.KickPlayers = true;
                            p.frames.sendMessage(p, "You expel your guests.");
                        } else {
                            p.frames.sendMessage(p, "You need to be in your house to do this.");
                        }
                        break;
                    case 13:
                        p.KickPlayers = false;
                        p.BuildingMode = false;
                        p.frames.sendMessage(p, "You leave the house");
                        p.setCoords(2544, 3096, 0);
                        break;
                }
                break;

            case 750:

            /*
             * Running button next to minimap.
             */


            case 261:

                if (buttonId == 3) {
                    p.splitChat = !p.splitChat;
                    p.frames.setConfig(p, 287, p.splitChat ? 1 : 0);
                    if (p.splitChat) {
                        p.stream.createFrameVarSizeWord(152);
                        p.stream.writeString("s");
                        p.stream.writeDWord(83);
                        p.stream.endFrameVarSize();
                    }
                }


                /*
                 * Settings tab.
                 */
                if (buttonId == 1) {
                    if (!p.isRunning) {
                        p.isRunning = true;
                        p.frames.setConfig(p, 173, 1);
                    } else {
                        p.isRunning = false;
                        p.frames.setConfig(p, 173, 0);
                    }
                } else if (buttonId == 14) {
                    p.frames.showInterface(p, 742);
                } else if (buttonId == 6) {
                    if (p.absX >= 3093 && p.absY >= 3920 && p.absX <= 3120 && p.absY <= 3950 && p.OwnHouse == true) {
                        p.frames.setTab(p, 84, 398);
                    } else {
                        p.frames.sendMessage(p, "You need to be in your house to do this.");
                    }
                } else if (buttonId == 16) {
                    p.frames.showInterface(p, 743);
                }
                break;

            case 335:
            case 334:
            case 336:
                p.pTrade.buttons.handleButton(interfaceId, packetId, buttonId, buttonId2);
                break;

            case 271:

                /*
                 * Prayer tab.
                 */
                Prayer pr = new Prayer();

                pr.Prayer(p, buttonId);
                break;


            case 464:

                /*
                 * Emote tab.
                 */
                if (buttonId == 2) { // Yes
                    p.requestAnim(855, 0);
                } else if (buttonId == 3) { // No
                    p.requestAnim(856, 0);
                } else if (buttonId == 4) { // Bow
                    p.requestAnim(858, 0);
                } else if (buttonId == 5) { // Angry
                    p.requestAnim(859, 0);
                } else if (buttonId == 6) { // Think
                    p.requestAnim(857, 0);
                } else if (buttonId == 7) { // Wave
                    p.requestAnim(863, 0);
                } else if (buttonId == 8) { // Shrug
                    p.requestAnim(2113, 0);
                } else if (buttonId == 9) { // Cheer
                    p.requestAnim(862, 0);
                } else if (buttonId == 10) { // Beckon
                    p.requestAnim(864, 0);
                } else if (buttonId == 11) { // Laugh
                    p.requestAnim(861, 0);
                } else if (buttonId == 12) { // Joy jump
                    p.requestAnim(2109, 0);
                } else if (buttonId == 13) { // Yawn
                    p.requestAnim(2111, 0);
                } else if (buttonId == 14) { // Dance
                    p.requestAnim(866, 0);
                } else if (buttonId == 15) { // Jig
                    p.requestAnim(2106, 0);
                } else if (buttonId == 16) { // Spin
                    p.requestAnim(2107, 0);
                } else if (buttonId == 17) { // Headbang
                    p.requestAnim(2108, 0);
                } else if (buttonId == 18) { // Cry
                    p.requestAnim(860, 0);
                } else if (buttonId == 19) { // Blow kiss
                    p.requestGFX(574, 0);
                    p.requestAnim(0x558, 0);
                } else if (buttonId == 20) { // Panic
                    p.requestAnim(2105, 0);
                } else if (buttonId == 21) { // Raspberry
                    p.requestAnim(2110, 0);
                } else if (buttonId == 22) { // Clap
                    p.requestAnim(865, 0);
                } else if (buttonId == 23) { // Salute
                    p.requestAnim(2112, 0);
                } else if (buttonId == 24) { // Goblin bow
                    p.requestAnim(0x84F, 0);
                } else if (buttonId == 25) { // Goblin salute
                    p.requestAnim(0x850, 0);
                } else if (buttonId == 26) { // Glass box
                    p.requestAnim(1131, 0);
                } else if (buttonId == 27) { // Climb rope
                    p.requestAnim(1130, 0);
                } else if (buttonId == 28) { // Lean
                    p.requestAnim(1129, 0);
                } else if (buttonId == 29) { // Glass wall
                    p.requestAnim(1128, 0);
                } else if (buttonId == 30) { // Head slap
                    p.requestAnim(4275, 0);
                } else if (buttonId == 31) { // Stomp
                    p.requestAnim(1745, 0);
                } else if (buttonId == 32) { // Flap
                    p.requestAnim(4280, 0);
                } else if (buttonId == 33) { // Idea
                    p.requestAnim(4276, 0);
                } else if (buttonId == 34) { // Zombie walk
                    p.requestAnim(3544, 0);
                } else if (buttonId == 35) { // Dombie dance
                    p.requestAnim(3543, 0);
                } else if (buttonId == 36) { // Zombie hand grab
                    p.requestGFX(1244, 0);
                    p.requestAnim(7272, 0);
                } else if (buttonId == 37) { // Scared
                    p.requestAnim(2836, 0);
                } else if (buttonId == 38) { // Rabbit hop
                    p.requestAnim(6111, 0);
                } else if (buttonId == 39) {// Skillcape emotes
                    if (buttonId == 39 && p.equipment[1] == 9747) {
                        p.requestGFX(823, 1);
                        p.requestAnim(4959, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9748) {
                        p.requestGFX(823, 1);
                        p.requestAnim(4959, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9753) {
                        p.requestGFX(824, 1);
                        p.requestAnim(4961, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9754) {
                        p.requestGFX(824, 1);
                        p.requestAnim(4961, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9750) {
                        p.requestGFX(828, 1);
                        p.requestAnim(4981, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9751) {
                        p.requestGFX(828, 1);
                        p.requestAnim(4981, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9768) {
                        p.requestGFX(833, 1);
                        p.requestAnim(4971, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9769) {
                        p.requestGFX(833, 1);
                        p.requestAnim(4971, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9756) {
                        p.requestGFX(832, 1);
                        p.requestAnim(4973, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9757) {
                        p.requestGFX(832, 1);
                        p.requestAnim(4973, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9759) {
                        p.requestGFX(829, 1);
                        p.requestAnim(4979, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9760) {
                        p.requestGFX(829, 1);
                        p.requestAnim(4979, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9762) {
                        p.requestGFX(813, 1);
                        p.requestAnim(4939, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9763) {
                        p.requestGFX(813, 1);
                        p.requestAnim(4939, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9801) {
                        p.requestGFX(821, 1);
                        p.requestAnim(4955, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9802) {
                        p.requestGFX(821, 1);
                        p.requestAnim(4955, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9807) {
                        p.requestGFX(822, 1);
                        p.requestAnim(4957, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9808) {
                        p.requestGFX(822, 1);
                        p.requestAnim(4957, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9783) {
                        p.requestGFX(812, 1);
                        p.requestAnim(4937, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9784) {
                        p.requestGFX(812, 1);
                        p.requestAnim(4937, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9798) {
                        p.requestGFX(819, 1);
                        p.requestAnim(4951, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9799) {
                        p.requestGFX(819, 1);
                        p.requestAnim(4951, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9804) {
                        p.requestGFX(831, 1);
                        p.requestAnim(4975, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9805) {
                        p.requestGFX(831, 1);
                        p.requestAnim(4975, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9780) {
                        p.requestGFX(818, 1);
                        p.requestAnim(4949, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9781) {
                        p.requestGFX(818, 1);
                        p.requestAnim(4949, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9795) {
                        p.requestGFX(815, 1);
                        p.requestAnim(4943, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9796) {
                        p.requestGFX(815, 1);
                        p.requestAnim(4943, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9792) {
                        p.requestGFX(814, 1);
                        p.requestAnim(4941, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9793) {
                        p.requestGFX(814, 1);
                        p.requestAnim(4941, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9774) {
                        p.requestGFX(835, 1);
                        p.requestAnim(4969, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9775) {
                        p.requestGFX(835, 1);
                        p.requestAnim(4969, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9771) {
                        p.requestGFX(830, 1);
                        p.requestAnim(4977, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9772) {
                        p.requestGFX(830, 1);
                        p.requestAnim(4977, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9777) {
                        p.requestGFX(826, 1);
                        p.requestAnim(4965, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9778) {
                        p.requestGFX(826, 1);
                        p.requestAnim(4965, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9786) {
                        p.requestGFX(1656, 1);
                        p.requestAnim(4967, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9787) {
                        p.requestGFX(1656, 1);
                        p.requestAnim(4967, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9810) {
                        p.requestGFX(825, 1);
                        p.requestAnim(4963, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9811) {
                        p.requestGFX(825, 1);
                        p.requestAnim(4963, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9765) {
                        p.requestGFX(817, 1);
                        p.requestAnim(4947, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9766) {
                        p.requestGFX(817, 1);
                        p.requestAnim(4947, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9810) {
                        p.requestGFX(825, 1);
                        p.requestAnim(4963, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9811) {
                        p.requestGFX(825, 1);
                        p.requestAnim(4963, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9813) {
                        p.requestGFX(816, 1);
                        p.requestAnim(4945, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9814) {
                        p.requestGFX(816, 1);
                        p.requestAnim(4945, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9789) {
                        p.requestGFX(820, 1);
                        p.requestAnim(4953, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9790) {
                        p.requestGFX(820, 1);
                        p.requestAnim(4953, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9948) {
                        p.requestGFX(907, 1);
                        p.requestAnim(5158, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 9949) {
                        p.requestGFX(907, 1);
                        p.requestAnim(5158, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 12169) {
                        p.requestGFX(1515, 1);
                        p.requestAnim(8525, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 1007) {
                        p.requestGFX(547, 1);
                        p.requestAnim(2820, 1);
                        p.npcType = 2862;
                        p.frames.sendMessage(p, "Do ::regemote to get back to regular emote.");
                    }
                    if (buttonId == 39 && p.equipment[1] == 4375) {
                        p.requestGFX(59, 1);
                        p.requestAnim(2819, 1);
                        p.frames.sendMessage(p, "Do ::regemote to get back to regular emote.");
                    }
                    if (buttonId == 39 && p.equipment[1] == 1031) {
                        p.requestGFX(354, 1);
                        p.requestAnim(2753, 1);
                        p.requestForceChat("Omg! WWWWWWWWEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEDDDDDDDDDDDDDDDDDD!!!!");
                    }
                    if (buttonId == 39 && p.equipment[1] == 1023) {
                        for (Player p2 : Engine.players) {
                            if (p2 != null) {
                                p.setCoords(p.absX, p.absY, 0);
                                p2.setCoords(((p.absX) + Misc.random(2)), ((p.absY) + Misc.random(2)), 0);
                                p.setCoords(p.absX, p.absY, 0);
                                p2.requestForceChat("All hail <img=1>Icedice the mighty owner of the world and all who live in it!");
                                p.requestForceChat("Thats right noobs bow to me!");
                                p.requestAnim(861, 0);
                            }
                        }
                    }
                    if (buttonId == 39 && p.equipment[1] == 12170) {
                        p.requestGFX(1515, 1);
                        p.requestAnim(8525, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == 10662) {
                        p.requestGFX(816, 1);
                        p.requestAnim(4945, 1);
                    }
                    if (buttonId == 39 && p.equipment[1] == -1) {
                        p.frames.sendMessage(p, "You need to be wearing a skillcape in order to perform this emote.");
                    }
                } else if (buttonId == 40) { // Snowman dance
                    p.requestAnim(7531, 0);
                } else if (buttonId == 41) { // Air guitar
                    p.requestAnim(2414, 0);
                    p.requestGFX(1537, 0);
                } else if (buttonId == 42) { // Safety first
                    p.requestAnim(8770, 0);
                    p.requestGFX(1553, 0);
                } else if (buttonId == 43) { // Explore
                    p.requestAnim(9990, 0);
                    p.requestGFX(1734, 0);
                }
                break;


            case 75: // Dragon baxe special credit to Mojo Jojo for the idea
                if (buttonId == 10 && !p.usingSpecial && p.equipment[3] == 1377
                        && p.specialAmount >= 100) {
                    p.specialAmount -= 100;
                    p.specialAmountUpdateReq = true;
                    p.requestAnim(1914, 0);
                    p.requestGFX(246, 0);
                    p.skillLvl[2] = ((p.getLevelForXP(3) / 5) + p.skillLvl[2]);
                    if (p.skillLvl[2] >= 125) {
                        p.skillLvl[2] = 125;
                    }
                    p.chatText = "NOW YOU MADE ME MMMMAAAADDDD!!!!";
                    p.chatTextUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setSkillLvl(p, 2);
                } else if (buttonId == 10 && !p.usingSpecial
                        && p.equipment[3] == 1377 && p.specialAmount <= 99) {
                    p.frames.sendMessage(p, "<img=2> You don't have enough special energy.");
                }
                if (buttonId == 26 && p.autoRetaliate == 0) {
                    p.autoRetaliate = 1;
                    p.frames.setConfig(p, 172, 1);
                } else if (buttonId == 26 && p.autoRetaliate == 1) {
                    p.autoRetaliate = 0;
                    p.frames.setConfig(p, 172, 0);
                }
                break;
            case 77:
                if (buttonId == 11 && !p.usingSpecial) {
                    p.usingSpecial = true;
                    p.frames.setConfig(p, 301, 1);
                } else if (buttonId == 11 && p.usingSpecial) {
                    p.usingSpecial = false;
                    p.frames.setConfig(p, 301, 0);
                }
                if (buttonId == 24 && p.autoRetaliate == 0) {
                    p.autoRetaliate = 1;
                    p.frames.setConfig(p, 172, 1);
                } else if (buttonId == 24 && p.autoRetaliate == 1) {
                    p.autoRetaliate = 0;
                    p.frames.setConfig(p, 172, 0);
                }
                break;

            case 76:
            case 79:
            case 84:
            case 85:
            case 91:
            case 92:
            case 93:
                if (buttonId == 2) {
                    p.attackStyle = 0;
                    p.playerWeapon.setWeapon();
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 3) {
                    p.attackStyle = 1;
                    p.playerWeapon.setWeapon();
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 4) {
                    p.attackStyle = 2;
                    p.playerWeapon.setWeapon();
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 8 && !p.usingSpecial) {
                    p.usingSpecial = true;
                    p.frames.setConfig(p, 301, 1);
                } else if (buttonId == 8 && p.usingSpecial) {
                    p.usingSpecial = false;
                    p.frames.setConfig(p, 301, 0);
                }
                if (buttonId == 24 && p.autoRetaliate == 0) {
                    p.autoRetaliate = 1;
                    p.frames.setConfig(p, 172, 1);
                } else if (buttonId == 24 && p.autoRetaliate == 1) {
                    p.autoRetaliate = 0;
                    p.frames.setConfig(p, 172, 0);
                }
                break;

            case 78:
            case 81:
            case 82:
            case 83:
            case 86:
            case 87:
            case 88:
            case 89:
                if (buttonId == 2) {
                    p.attackStyle = 0;
                    p.playerWeapon.setWeapon();
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 3) {
                    p.attackStyle = 1;
                    p.playerWeapon.setWeapon();
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 4) {
                    p.attackStyle = 2;
                    p.playerWeapon.setWeapon();
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 5) {
                    p.attackStyle = 3;
                    p.playerWeapon.setWeapon();
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                if (buttonId == 10 && !p.usingSpecial) {
                    p.usingSpecial = true;
                    p.frames.setConfig(p, 301, 1);
                } else if (buttonId == 10 && p.usingSpecial) {
                    p.usingSpecial = false;
                    p.frames.setConfig(p, 301, 0);
                }
                if (buttonId == 26 && p.autoRetaliate == 0) {
                    p.autoRetaliate = 1;
                    p.frames.setConfig(p, 172, 1);
                } else if (buttonId == 26 && p.autoRetaliate == 1) {
                    p.autoRetaliate = 0;
                    p.frames.setConfig(p, 172, 0);
                }


            case 90:
                if (p.equipment[3] > 1378 && p.equipment[3] < 1408) {
                    if (buttonId == 4 || buttonId == 5) {
                        p.frames.setTab(p, 73, 319);
                    }
                }
                break;

            case 378:
                if (buttonId == 140) {
                    if (p.usingHD) {
                        p.frames.setWindowPane(p, 549);
                    } else {
                        p.frames.setWindowPane(p, 548);
                    }
                    if (p.started == 0) {
                        p.frames.showInterface(p, 771);
                        Engine.playerItems.addItem(p, 9003, 1);
                        Engine.playerItems.addItem(p, 380, 200);
                        Engine.playerItems.addItem(p, 995, 2000000);
                        Engine.playerItems.addItem(p, 1323, 1);
                        Engine.playerItems.addItem(p, 1155, 1);
                        Engine.playerItems.addItem(p, 1075, 1);
                        Engine.playerItems.addItem(p, 1117, 1);
                        Engine.playerItems.addItem(p, 1087, 1);
                        Engine.playerItems.addItem(p, 1061, 1);
                        Engine.playerItems.addItem(p, 1063, 1);
                        Engine.playerItems.addItem(p, 882, 200);
                        Engine.playerItems.addItem(p, 841, 1);
                        Engine.playerItems.addItem(p, 1323, 1);
                        Engine.playerItems.addItem(p, 556, 200);
                        Engine.playerItems.addItem(p, 558, 200);
                        p.started = 1;
                    }
                }
                break;

            case 182:


                if ((p.absY >= 3072) && (p.absY <= 3132) && (p.absX >= 2368) && (p.absX <= 2428)) {
                    p.setCoords(2442, 3090, 0);
                }
                if ((p.absY >= 5313) && (p.absY <= 5305) && (p.absX >= 2886) && (p.absX <= 2878)) {
                    p.setCoords(3221, 3218, 0);
                }

                /*
                 * Logout interface.
                 */

                p.frames.logout(p);
                break;


            case 549:
            case 548:
                switch (buttonId) {

                    case 60:
                        if (p.xpLock == 0) {
                            p.frames.sendMessage(p, "Your xp is now locked.");
                            p.xpLock = 1;
                        } else {
                            p.frames.sendMessage(p, "Your xp is now unlocked");
                            p.xpLock = 0;
                        }
                        break;
                }


                /*
                 * Main interface.
                 */
                break;
            case 755:
                switch (buttonId) {
                    case 9:
                        if (p.usingHD) {
                            p.frames.setWindowPane(p, 549);
                        } else {
                            p.frames.setWindowPane(p, 548);
                        }
                        break;
                }
                break;

            case 763:
                /*
                 * Inventory interface with banking.
                 */
                if (buttonId == 0) {
                    if (packetId == 233) //1
                    {
                        Engine.playerBank.bankItem(p, buttonId2, 1);
                    } else if (packetId == 21) //5
                    {
                        Engine.playerBank.bankItem(p, buttonId2, 5);
                    } else if (packetId == 169) //10
                    {
                        Engine.playerBank.bankItem(p, buttonId2, 10);
                    } else if (packetId == 214) //lastX
                    {
                        Engine.playerBank.bankItem(p, buttonId2, p.bankX);
                    } else if (packetId == 232) //all
                    {
                        Engine.playerBank.bankItem(p, buttonId2, Engine.playerItems.invItemCount(p, p.items[buttonId2]));
                    } else if (packetId == 173) //X
                    {
                        p.input.request(7, buttonId2);
                    } else if (packetId == 90) //examine
                    {
                        p.frames.sendMessage(p, Engine.items.getItemDescription(p.items[buttonId2]));
                    }
                }
                break;
            case 762:
                /*
                 * Bank interface.
                 */
                if (buttonId == 73) {
                    if (packetId == 233) //1
                    {
                        Engine.playerBank.bankWithdraw(p, buttonId2, 1);
                    } else if (packetId == 21) //5
                    {
                        Engine.playerBank.bankWithdraw(p, buttonId2, 5);
                    } else if (packetId == 169) //10
                    {
                        Engine.playerBank.bankWithdraw(p, buttonId2, 10);
                    } else if (packetId == 214) //lastX
                    {
                        Engine.playerBank.bankWithdraw(p, buttonId2, p.bankX);
                    } else if (packetId == 173) {    //X
                        p.input.request(6, buttonId2);
                    } else if (packetId == 232) //all
                    {
                        Engine.playerBank.bankWithdraw(p, buttonId2, p.bankItemsN[buttonId2]);
                    } else if (packetId == 133) //all but one
                    {
                        Engine.playerBank.bankWithdraw(p, buttonId2, p.bankItemsN[buttonId2] - 1);
                    } else if (packetId == 90) //examine
                    {
                        p.frames.sendMessage(p, Engine.items.getItemDescription(p.bankItems[buttonId2]));
                    }
                    break;
                } else if (buttonId == 22) {
                    //p.frames.restoreTabs(p); This is mine, close interface button, you might have other methods to show inventory again
                } else if (buttonId == 16) {
                    if (p.swapAsNote == false) {
                        p.swapAsNote = true;
                    } else {
                        p.swapAsNote = false;
                    }
                } else if (buttonId == 14) {
                    p.insertMode = !p.insertMode;
                } else if (buttonId == 41 || buttonId == 39 || buttonId == 37 || buttonId == 35 || buttonId == 33 || buttonId == 31 || buttonId == 29 || buttonId == 27 || buttonId == 25) { //Tab buttons
                    if (packetId == 21) { //Collapse
                        Engine.playerBank.collapseTab(p, Engine.playerBank.getArrayIndex(buttonId)); //This method will be added later, dont worry
                    } else if (packetId == 233) { //View tab
                        p.viewingBankTab = Engine.playerBank.getArrayIndex(buttonId);
                    }
                }
                break;
            case 659:
                if (buttonId == 2) {
                    if (packetId == 63) {
                        p.requestAnim(1745, 0);
                    }
                    p.frames.removeShownInterface(p);
                    p.requestAnim(1745, 0);
                    p.frames.createGlobalObject(28297, p.heightLevel, p.absX, p.absY, 0, 10);
                    p.frames.createGlobalObject(28297, p.heightLevel, p.absX - 1, p.absY + 2, 0, 10);
                    p.frames.createGlobalObject(28297, p.heightLevel, p.absX + 2, p.absY - 1, 0, 10);
                    p.requestAnim(1745, 0);
                    Engine.playerItems.addItem(p, 11951, 27);
                    p.frames.sendMessage(p, "The snow globe fills your inventory with snow!");
                }
                break;

            default:
                // Misc.println("[" + p.username + "] Unhandled button: " + interfaceId + ", " + buttonId + ":" + buttonId2);
                break;
        }
        //Misc.println("[" + p.username + "] Action button: " + interfaceId + ", " + buttonId + ":" + buttonId2);
    }
}
