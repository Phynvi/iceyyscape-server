

package icedice.io.packets;


import icedice.Engine;
import icedice.util.Misc;
import icedice.npcs.NPC;
import icedice.Config;
import icedice.players.Player;

public class NPCOption1 implements Packet {


    public Player p;

    public int logs[] = new int[28];

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

    /**
     * Handles the first NPC option.
     *
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public NPC n;

    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        if (!p.npcOption1) {
            int npcId = p.stream.readUnsignedWordA();
            p.requestFaceTo(npcId);
            p.wc.resetWoodcutting();
            p.mi.resetMining();
            if (npcId <= 0 || npcId >= Engine.npcs.length
                    || Engine.npcs[npcId] == null) {
                return;
            }
            p.clickId = npcId;


            p.clickX = Engine.npcs[npcId].absX;
            p.clickY = Engine.npcs[npcId].absY;
            if (Misc.getDistance(p.absX, p.absY, p.clickX, p.clickY) > 30) {
                return;
            }
            p.npcOption1 = true;
        }
        if (p.clickId <= 0 || p.clickId >= Engine.npcs.length
                || Engine.npcs[p.clickId] == null) {
            p.npcOption1 = false;
            return;
        }
        if (Misc.getDistance(p.absX, p.absY, p.clickX, p.clickY) > 1) {
            return;
        }
        p.npcOption1 = false;

        NPC pnp = Engine.npcs[p.clickId];
        NPC np = Engine.npcs[p.FamiliarID];

        if (pnp.npcType == 2253)//Wise Old Man
        {
            if (p.QuestPoints < 2) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9827, 241, 2);
                p.frames.setNPCId(p, 2567, 241, 2);
                p.frames.setString(p, "Wise Old Man", 241, 3);
                p.frames.setString(p, "Ehh, you haven't completed all Quests...", 241, 4);
            } else {
                p.Dialogue = 111;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 2567, 241, 2);
                p.frames.setString(p, "Wise Old Man", 241, 3);
                p.frames.setString(p, "Looks like you completed all the quests...", 241, 4);
            }


        }

        if (pnp.npcType == 198)//GuildMaster
        {

            if (p.DragonSlayer == 0) {
                p.Dialogue = 100;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 198, 241, 2);
                p.frames.setString(p, "Guildmaster", 241, 3);
                p.frames.setString(p, "Looking for a quest are you?", 241, 4);
            }
            if (p.DragonSlayer == 1) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 198, 241, 2);
                p.frames.setString(p, "Guildmaster", 241, 3);
                p.frames.setString(p, "Go speak to Oziach in Edgeville...", 241, 4);
            }
            if (p.DragonSlayer == 2) {
                p.Dialogue = 102;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 198, 241, 2);
                p.frames.setString(p, "Guildmaster", 241, 3);
                p.frames.setString(p, "Ah yes, the dragon of Crandor Island...", 241, 4);
            }
            if (p.DragonSlayer == 3) {
                p.frames.setString(p, "How can I find a route to Crandor?", 458, 1);
                p.frames.setString(p, "Where can I find the right ship?", 458, 2);
                p.frames.setString(p, "How can I protect myself from the dragon's breath?", 458, 3);
                p.frames.showChatboxInterface(p, 458);

                p.Cooking = false;
                p.TalkAgent = false;
                p.DecorChange = false;
                p.Choice = 1;
            }

            if (p.DragonSlayer == 4) {
                Engine.playerItems.deleteItem(p, 11279, Engine.playerItems.getItemSlot(p, 11279), 28);
                p.Dialogue = 110;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 198, 241, 2);
                p.frames.setString(p, "Guildmaster", 241, 3);
                p.frames.setString(p, "Wow you slayed Elvarg! Accept this reward.", 241, 4);
            }

            if (p.DragonSlayer == 5) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 198, 241, 2);
                p.frames.setString(p, "Guildmaster", 241, 3);
                p.frames.setString(p, "Thank you for killing that dragon!", 241, 4);
            }
        }

        if (pnp.npcType == 6531) {
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9840, 242, 2);
            p.frames.setNPCId(p, 6531, 242, 2);
            p.frames.setString(p, "Grand Excange Clerk", 242, 3);
            p.frames.setString(p, "Welcome sir, what you wanna do", 242, 4);
            p.frames.setString(p, "me for you?", 242, 5);
        }
        if (pnp.npcType == 6529) {
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9840, 242, 2);
            p.frames.setNPCId(p, 6529, 242, 2);
            p.frames.setString(p, "Grand Excange Clerk", 242, 3);
            p.frames.setString(p, "Welcome sir, what you wanna do", 242, 4);
            p.frames.setString(p, "me for you?", 242, 5);
        }
        if (pnp.npcType == 6528) {
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9840, 242, 2);
            p.frames.setNPCId(p, 6528, 242, 2);
            p.frames.setString(p, "Grand Excange Clerk", 242, 3);
            p.frames.setString(p, "Welcome sir, what you wanna do", 242, 4);
            p.frames.setString(p, "me for you?", 242, 5);
        }
        if (pnp.npcType == 6530) {
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9840, 242, 2);
            p.frames.setNPCId(p, 6530, 242, 2);
            p.frames.setString(p, "Grand Excange Clerk", 242, 3);
            p.frames.setString(p, "Welcome sir, what you wanna do", 242, 4);
            p.frames.setString(p, "me for you?", 242, 5);
        }
        if (pnp.npcType == 599) {
            p.frames.showInterface(p, 771);
        }
        if (pnp.npcType == 6526) {
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9840, 242, 2);
            p.frames.setNPCId(p, 6526, 242, 2);
            p.frames.setString(p, "Relobo Blinyo", 242, 3);
            p.frames.setString(p, "Sorry, but I got a little much", 242, 4);
            p.frames.setString(p, "thing to do.", 242, 5);
        }
        if (pnp.npcType == 456) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9840, 241, 2);
            p.frames.setNPCId(p, 456, 241, 2);
            p.frames.setString(p, "Father Aereck", 241, 3);
            p.frames.setString(p, "If you believe in the gods, they will help you out.", 241, 4);
        }

        if (pnp.npcType == 747)//Oziach
        {

            if (p.DragonSlayer == 0) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9785, 241, 2);
                p.frames.setNPCId(p, 747, 241, 2);
                p.frames.setString(p, "Oziach", 241, 3);
                p.frames.setString(p, "What are you looking for?", 241, 4);
            }

            if (p.DragonSlayer == 1) {
                p.Dialogue = 105;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9827, 241, 2);
                p.frames.setNPCId(p, 747, 241, 2);
                p.frames.setString(p, "Oziach", 241, 3);
                p.frames.setString(p, "The guild master sent you right...", 241, 4);
            }

            if (p.DragonSlayer == 2) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 747, 241, 2);
                p.frames.setString(p, "Oziach", 241, 3);
                p.frames.setString(p, "Go speak to the guildmaster for help.", 241, 4);
            }

            if (p.DragonSlayer > 2 && p.DragonSlayer < 4) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9785, 241, 2);
                p.frames.setNPCId(p, 747, 241, 2);
                p.frames.setString(p, "Oziach", 241, 3);
                p.frames.setString(p, "You haven't killed Elvarg yet!", 241, 4);
            }

            if (p.DragonSlayer == 4) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9827, 241, 2);
                p.frames.setNPCId(p, 747, 241, 2);
                p.frames.setString(p, "Oziach", 241, 3);
                p.frames.setString(p, "Wow, you killed Elvarg? Go tell the Guildmaster!", 241, 4);
            }
            if (p.DragonSlayer == 5) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 747, 241, 2);
                p.frames.setString(p, "Oziach", 241, 3);
                p.frames.setString(p, "Good job killing that dragon.", 241, 4);
            }
        }

        if (pnp.npcType == 639) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 639, 242, 2);
            p.frames.setString(p, "Romeo", 242, 3);
            p.frames.setString(p, "Welcome to iCEYY!SCAPe", 242, 4);
            p.frames.setString(p, "Type ::manual for help it shows the rules!", 242, 5);
        }

        if (pnp.npcType == 2402) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 2402, 242, 2);
            p.frames.setString(p, "Mysterious Ghost", 242, 3);
            p.frames.setString(p, "Why do you come here?", 242, 4);
            p.frames.setString(p, "Coming here will just lead to your ultimate destruction!", 242, 5);
            p.frames.setString(p, "Be careful.. Don't wanna wind up like me and the rest of us!", 242, 6);
        }

        if (pnp.npcType == 2574) {
            p.Dialogue = 0;
            Engine.playerItems.addItem(p, 9003, 1);
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 2574, 242, 2);
            p.frames.setString(p, "Bank Gaurd", 242, 3);
            p.frames.setString(p, " " + p.username + ", Welcome to -iCEYY!SCAPe-", 242, 4);
            p.frames.setString(p, "Read all of the recent updates at www.iceyyscape.com.", 242, 5);
            p.frames.setString(p, "...Continue...", 242, 6);
            p.frames.sendMessage(p, "<img=2>" + p.username + ", Please read the rule book!");
        }

        if (pnp.npcType == 552) {
            p.Dialogue = 0;
            Engine.playerItems.addItem(p, 9003, 1);
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 552, 242, 2);
            p.frames.setString(p, "Shopkeeper", 242, 3);
            p.frames.setString(p, "Hey " + p.username + " would you like to buy some bows and arrows?", 242, 4);
            p.frames.setString(p, "They are state of the art!", 242, 5);
            p.frames.setString(p, "...Continue...", 242, 6);
        }

        if (pnp.npcType == 731) {
            p.Dialogue = 0;
            Engine.playerItems.addItem(p, 3801, 1);
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 731, 242, 2);
            p.frames.setString(p, "Bar Tender", 242, 3);
            p.frames.setString(p, " " + p.username + ", hey take a free beer...", 242, 4);
            p.frames.setString(p, "...just dont give it to Dr.Harlow...", 242, 5);
            p.frames.setString(p, "Thank You.", 242, 6);
            p.frames.sendMessage(p, "<img=2> Thank you for donating to iCEYY!SCAPe!");
        }

        if (pnp.npcType == 1780) {
            p.Dialogue = 0;
            Engine.playerItems.addItem(p, 9003, 1);
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 1780, 242, 2);
            p.frames.setString(p, "Larry", 242, 3);
            p.frames.setString(p, "Starters and teleports are located in your quest tab!", 242, 4);
            p.frames.setString(p, "Also Dragon Slayer works 100%", 242, 5);
        }

        if (pnp.npcType == 2398) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 2398, 242, 2);
            p.frames.setString(p, "Mysterious Ghost", 242, 3);
            p.frames.setString(p, "'Ello there mate!", 242, 4);
            p.frames.setString(p, "Beware of what lies ahead!!! It might just be your very own", 242, 5);
            p.frames.setString(p, "doom!!", 242, 6);
        }

        if (pnp.npcType == 6098) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 6098, 242, 2);
            p.frames.setString(p, "Ghost", 242, 3);
            p.frames.setString(p, "Your time has come to an ending!!", 242, 4);
            p.frames.setString(p, "Fear my leet powers!!!!!", 242, 5);
        }

        if (pnp.npcType == 6504) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 6504, 242, 2);
            p.frames.setString(p, "Ghost", 242, 3);
            p.frames.setString(p, "Get out of here!! Or i'll unleash my wrath on you!", 242, 4);
            p.frames.setString(p, "Your death has come!", 242, 5);
        }

        if (pnp.npcType == 1700) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 1700, 242, 2);
            p.frames.setString(p, "Ghost InnKeeper", 242, 3);
            p.frames.setString(p, "Welcome to the Ghost House", 242, 4);
            p.frames.setString(p, "Would you like to rent a room?", 242, 5);
        }

        if (pnp.npcType == 1704) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 1704, 242, 2);
            p.frames.setString(p, "Ghost Captain", 242, 3);
            p.frames.setString(p, "You dare enter my realm!! I will show no Mercy!", 242, 4);
            p.frames.setString(p, "The end for you has come!! I shall show you my power!", 242, 5);
            p.frames.setString(p, "Prepare to die!!", 242, 6);
        }

        if (pnp.npcType == 7467) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 7467, 242, 2);
            p.frames.setString(p, "Milknosugar", 242, 3);
            p.frames.setString(p, "Help us!!", 242, 4);
            p.frames.setString(p, "they invaded our lands!!", 242, 5);
        }

        if (pnp.npcType == 1886) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 1886, 242, 2);
            p.frames.setString(p, "Cowardly Bandit", 242, 3);
            p.frames.setString(p, "Ehh, I hate doing this kinda stuff.", 242, 4);
            p.frames.setString(p, "You know like the destroying lands and taking over.", 242, 5);
            p.frames.setString(p, "Can you help get me out of this?? Please!", 242, 6);
        }

        if (pnp.npcType == 1878) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 1878, 242, 2);
            p.frames.setString(p, "Bandits Leader", 242, 3);
            p.frames.setString(p, "Welcome to Bandits Training area!", 242, 4);
            p.frames.setString(p, "I'm The Owner of these lands and people!", 242, 5);
            p.frames.setString(p, "So beware don't do anything stupid!!", 242, 6);
        }

        if (pnp.npcType == 659) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 659, 242, 2);
            p.frames.setString(p, "Party Pete", 242, 3);
            p.frames.setString(p, "Like the new party room updates?!?!?!?", 242, 4);
            p.frames.setString(p, "Hey you look like me now!!", 242, 5);
            p.npcType = 659;
            p.appearanceUpdateReq = true;
            p.updateReq = true;
            p.frames.sendMessage(p, "<img=2>Type ::self to turn back into your self!!");
        }

        if (pnp.npcType == 660) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 660, 242, 2);
            p.frames.setString(p, "White Knight", 242, 3);
            p.frames.setString(p, "Hey budy walk over to the lever and pull it!!", 242, 4);
            p.frames.setString(p, "Give me somthing to dance about!", 242, 5);
        }

        if (pnp.npcType == 1835) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 1835, 242, 2);
            p.frames.setString(p, "Easter Bunny", 242, 3);
            p.frames.setString(p, "Happy Begining Server!", 242, 4);
            p.frames.setString(p, "This event is to celebrate the begining of the server!", 242, 5);
        }

        if (pnp.npcType == 7339) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 7339, 242, 2);
            p.frames.setString(p, "Geyser Titan", 242, 3);
            p.frames.setString(p, "Welcome " + p.username + " to FunZone!!", 242, 4);
            p.frames.setString(p, "Just sit back and relax!", 242, 5);
        }

        if (pnp.npcType == 7355) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 7355, 242, 2);
            p.frames.setString(p, "Fire Titan", 242, 3);
            p.frames.setString(p, "Welcome " + p.username + " to FunZone!!", 242, 4);
            p.frames.setString(p, "A place for all players to just relax", 242, 5);
        }

        if (pnp.npcType == 275) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 275, 242, 2);
            p.frames.setString(p, "Guardian of Armadyl", 242, 3);
            p.frames.setString(p, "Welcome " + p.username + " to the Chill Zone!!", 242, 4);
            p.frames.setString(p, "A place for all players to just come and chill out!!", 242, 5);
        }


        if (pnp.npcType == 7359) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 7359, 242, 2);
            p.frames.setString(p, "Ice Titan", 242, 3);
            p.frames.setString(p, "Welcome " + p.username + " to FunZone!!", 242, 4);
            p.frames.setString(p, "Your an amazing Player!!!", 242, 5);
        }
        if (pnp.npcType == 7360) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 7360, 242, 2);
            p.frames.setString(p, "Ice Titan", 242, 3);
            p.frames.setString(p, "Must Crush you!!!!", 242, 4);
            p.frames.setString(p, "You are FINISHED!", 242, 5);
        }
        if (pnp.npcType == 3788) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 3788, 242, 2);
            p.frames.setString(p, "Void knight", 242, 3);
            p.frames.setString(p, "You have chosen to leave..", 242, 4);
            p.frames.setString(p, "You will or are now teleported back.", 242, 5);
            p.requestForceChat("I have left the pest control game.");
            p.teleportTo(2352, 3171, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
        }
        if (pnp.npcType == 7001) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 7001, 242, 2);
            p.frames.setString(p, "Dark Squall", 242, 3);
            p.frames.setString(p, "You have entered the area of runecrafting", 242, 4);
            p.frames.setString(p, "Runecraft here to get experience.", 242, 5);
            p.requestForceChat("Hm.. How did I end up here?");
            p.teleportTo(2110, 3915, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
        }
        if (pnp.npcType == 398) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 398, 242, 2);
            p.frames.setString(p, "Legends Guard", 242, 3);
            p.frames.setString(p, "Welcome to iCEYY!SCAPe", 242, 4);
            p.frames.setString(p, "Best Private Server Ever! Owners are Vicky and Chris!", 242, 5);
        }
        if (pnp.npcType == 3100) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 3100, 242, 2);
            p.frames.setString(p, "Enchantment Guardian", 242, 3);
            p.frames.setString(p, "Welcome to The Bank!!", 242, 4);
            p.frames.setString(p, "This area is for shopping!!", 242, 5);
        }
        if (pnp.npcType == 3097) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 3097, 242, 2);
            p.frames.setString(p, "Entrance Guardian", 242, 3);
            p.frames.setString(p, "Welcome to Shops!", 242, 4);
            p.frames.setString(p, "More shops are going to be added!!", 242, 5);
        }

        if (pnp.npcType == 7343) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 7343, 242, 2);
            p.frames.setString(p, "Steel Titan", 242, 3);
            p.frames.setString(p, "Welcome " + p.username + " to FunZone!!", 242, 4);
            p.frames.setString(p, "You worked hard to get here! Enjoy!", 242, 5);
        }
        if (pnp.npcType == 274) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 274, 242, 2);
            p.frames.setString(p, "Guardian of Armadyl", 242, 3);
            p.frames.setString(p, "You're in jail!! Read the rule book and information book given to you!", 242, 4);
            p.frames.setString(p, "Then you may leave!", 242, 5);
        }
        if (pnp.npcType == 7002) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 7002, 242, 2);
            p.frames.setString(p, "Surok Magis", 242, 3);
            p.frames.setString(p, "Use ::join [name] to join a chat and play the game!", 242, 4);
            p.frames.setString(p, "Use the ::leave command to leave the chat!", 242, 5);
        }
        if (pnp.npcType == 6521) {
            p.Dialogue = 800;
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9850, 241, 2);
            p.frames.setNPCId(p, 6521, 241, 2);
            p.frames.setString(p, "Grand Exchange Tutor", 241, 3);
            p.frames.setString(p, "Welcome to the Grand Exchange!", 241, 4);
            p.frames.setString(p, "Currenty Under Construction!!", 231, 5);
        }

        if (pnp.npcType == 746) {
            if (p.DragonSlayer < 3) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9785, 241, 2);
                p.frames.setNPCId(p, 746, 241, 2);
                p.frames.setString(p, "Oracle", 241, 3);
                p.frames.setString(p, "Help us!!", 241, 4);
                p.frames.setString(p, "They destroyed my lands and tent!", 241, 5);
            } else {
                p.Dialogue = 108;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9827, 241, 2);
                p.frames.setNPCId(p, 746, 241, 2);
                p.frames.setString(p, "Oracle", 241, 3);
                p.frames.setString(p, "You are looking for a map to crandor?", 241, 4);
            }

        }


        if (pnp.npcType == 741) {
            p.Dialogue = 0;
            Engine.playerItems.addItem(p, 1540, 1);
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9850, 241, 2);
            p.frames.setNPCId(p, 741, 241, 2);
            p.frames.setString(p, "Duke", 241, 3);
            p.frames.setString(p, "Take this anti-dragon shield...", 241, 4);
        }
        if (pnp.npcType == 216) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9780, 242, 2);
            p.frames.setNPCId(p, 547, 242, 2);
            p.frames.setString(p, "DANGERUS", 242, 3);
            p.frames.setString(p, "this is serrius warning! DOn't fight dose np'c.", 242, 4);
            p.frames.setString(p, "IF U R Under 75 hp or u will die...", 242, 5);
        }
        if (pnp.npcType == 7952) {
            p.frames.showInterface(p, 779);
        }
        if (pnp.npcType == 7151) {
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 244);
            p.frames.animateInterfaceId(p, 9850, 244, 2);
            p.frames.setNPCId(p, 7151, 244, 2);
            p.frames.setString(p, "Student", 244, 3);
            p.frames.setString(p, "Welcome to iCEYY!SCAPe training road...!", 244, 4);
            p.frames.setString(p, "u can teleport whit difrent levels train...", 244, 5);
            p.frames.setString(p, "use. ::train1 (1-50cmb) ::train2 (50-90).", 244, 6);
            p.frames.setString(p, "and ::train3 for (90-126cmb)...BYE!", 244, 7);
        }

        if (pnp.npcType == 744) {
            if (p.DragonSlayer > 2) {
                p.Dialogue = 0;
                p.frames.setString(p, "Can you sail me to crandor Island?", 458, 1);
                p.frames.setString(p, "Do you like dragons?", 458, 2);
                p.frames.setString(p, "Never mind....", 458, 3);
                p.frames.showChatboxInterface(p, 458);

                p.Cooking = false;
                p.TalkAgent = false;
                p.DecorChange = false;
                p.Choice = 2;
            } else {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9827, 241, 2);
                p.frames.setNPCId(p, 744, 241, 2);
                p.frames.setString(p, "Klarense", 241, 3);
                p.frames.setString(p, "Sorry mate, I'm busy.", 241, 4);
            }
        }


        if (pnp.npcType == 6901) {
            if (np != null) {
                if (p.FamiliarID != p.clickId) {
                    p.frames.sendMessage(p, "This isn't your dragon.");
                } else {
                    p.requestAnim(827, 0);
                    p.FamiliarType = 0;
                    np.isDead = true;
                    p.FamiliarID = 0;
                    p.frames.sendMessage(p, "You pick up the dragon.");
                    Engine.playerItems.addItem(p, 12469, 1);
                    p.frames.setTab(p, 80, 18);
                }
            }
        }
        if (pnp.npcType == 6957) {
            if (np != null) {
                if (p.FamiliarID != p.clickId) {
                    p.frames.sendMessage(p, "This isn't your Guthix Raptor.");
                } else {
                    p.requestAnim(827, 0);
                    p.FamiliarType = 0;
                    np.isDead = true;
                    p.FamiliarID = 0;
                    p.frames.sendMessage(p, "You pick up your Raptor.");
                    Engine.playerItems.addItem(p, 12510, 1);
                    p.frames.setTab(p, 80, 18);
                }
            }
        }
        if (pnp.npcType == 6951) {
            if (np != null) {
                if (p.FamiliarID != p.clickId) {
                    p.frames.sendMessage(p, "This isn't your Saradomin Owl.");
                } else {
                    p.requestAnim(827, 0);
                    p.FamiliarType = 0;
                    np.isDead = true;
                    p.FamiliarID = 0;
                    p.frames.sendMessage(p, "You pick up your Owl.");
                    Engine.playerItems.addItem(p, 12185, 1);
                    p.frames.setTab(p, 80, 18);
                }
            }
        }
        if (pnp.npcType == 6954) {
            if (np != null) {
                if (p.FamiliarID != p.clickId) {
                    p.frames.sendMessage(p, "This isn't your Zamorak Hawk.");
                } else {
                    p.requestAnim(827, 0);
                    p.FamiliarType = 0;
                    np.isDead = true;
                    p.FamiliarID = 0;
                    p.frames.sendMessage(p, "You pick up your Hawk.");
                    Engine.playerItems.addItem(p, 12508, 1);
                    p.frames.setTab(p, 80, 18);
                }
            }
        }
        if (pnp.npcType == 6920) {
            if (np != null) {
                if (p.FamiliarID != p.clickId) {
                    p.frames.sendMessage(p, "This isn't your Squirrel.");
                } else {
                    p.requestAnim(827, 0);
                    p.FamiliarType = 0;
                    np.isDead = true;
                    p.FamiliarID = 0;
                    p.frames.sendMessage(p, "You pick up the Squirrel.");
                    Engine.playerItems.addItem(p, 12490, 1);
                    p.frames.setTab(p, 80, 18);
                }
            }
        }

        if (pnp.npcType == 6903) {
            if (np != null) {
                if (p.FamiliarID != p.clickId) {
                    p.frames.sendMessage(p, "This is not your dragon.");
                } else {
                    p.requestAnim(827, 0);
                    p.FamiliarType = 0;
                    np.isDead = true;
                    p.FamiliarID = 0;
                    p.frames.sendMessage(p, "You pick up the dragon.");
                    Engine.playerItems.addItem(p, 12471, 1);
                    p.frames.setTab(p, 80, 18);
                }
            }
        }

        if (pnp.npcType == 6905) {
            if (np != null) {
                if (p.FamiliarID != p.clickId) {
                    p.frames.sendMessage(p, "This is not your dragon.");
                } else {
                    p.requestAnim(827, 0);
                    p.FamiliarType = 0;
                    np.isDead = true;
                    p.FamiliarID = 0;
                    p.frames.sendMessage(p, "You pick up the dragon.");
                    Engine.playerItems.addItem(p, 12473, 1);
                    p.frames.setTab(p, 80, 18);
                }
            }
        }
        if (pnp.npcType == 6907) {
            if (np != null) {
                if (p.FamiliarID != p.clickId) {
                    p.frames.sendMessage(p, "This is not your dragon.");
                } else {
                    p.requestAnim(827, 0);
                    p.FamiliarType = 0;
                    np.isDead = true;
                    p.FamiliarID = 0;
                    p.frames.sendMessage(p, "You pick up the dragon.");
                    Engine.playerItems.addItem(p, 12475, 1);
                    p.frames.setTab(p, 80, 18);
                }
            }
        }

        if (pnp.npcType == 768) {
            if (np != null) {
                if (p.FamiliarID != p.clickId) {
                    p.frames.sendMessage(p, "This is not your cat.");
                } else {
                    p.requestAnim(827, 0);
                    p.FamiliarType = 0;
                    np.isDead = true;
                    p.FamiliarID = 0;
                    p.frames.sendMessage(p, "You pick your cat.");
                    Engine.playerItems.addItem(p, 1561, 1);
                    p.frames.setTab(p, 80, 18);
                }
            }
        }
        if (pnp.npcType == 6970) {
            if (p.skillLvl[23] > 98) {
                GetSkillCape(p, 54, 6970, "Pikkupstix", "Summoning");
            } else {
                OnlyLevel(p, 55, 6970, "Pikkupstix", 23, "Summoning");
            }
        }


        if (pnp.npcType == 6064) {
            if (p.skillLvl[21] >= 83) {
                if (p.equipment[3] != 11259) {
                    p.frames.sendMessage(p, "You need to be wielding a net to catch this with.");
                } else {
                    Engine.npcs[p.clickId].isDead = true;
                    Engine.npcs[p.clickId].absX = 1;
                    Engine.npcs[p.clickId].absY = 1;
                    p.frames.sendMessage(p, "You catch the dragon impling!");
                    p.addSkillXP(1250 * p.skillLvl[21] * Config.bonusXP, 21);
                    Engine.playerItems.addItem(p, 11256, 1);
                    p.requestAnim(5209, 0);

                }
            } else {
                p.frames.sendMessage(p, "You need level 83 Hunting to catch this.");
            }
        }

        if (pnp.npcType == 6063) {
            if (p.skillLvl[21] >= 70) {
                if (p.equipment[3] != 11259) {
                    p.frames.sendMessage(p, "You need to be wielding a net to catch this with.");
                } else {
                    Engine.npcs[p.clickId].isDead = true;
                    Engine.npcs[p.clickId].absX = 1;
                    Engine.npcs[p.clickId].absY = 1;
                    p.frames.sendMessage(p, "You catch the ninja impling!");
                    p.addSkillXP(800 * p.skillLvl[21] * Config.bonusXP, 21);
                    Engine.playerItems.addItem(p, 11254, 1);
                    p.requestAnim(5209, 0);

                }
            } else {
                p.frames.sendMessage(p, "You need level 70 Hunting to catch this.");
            }
        }

        if (pnp.npcType == 6062) {
            if (p.skillLvl[21] >= 60) {
                if (p.equipment[3] != 11259) {
                    p.frames.sendMessage(p, "You need to be wielding a net to catch this with.");
                } else {
                    Engine.npcs[p.clickId].isDead = true;
                    Engine.npcs[p.clickId].absX = 1;
                    Engine.npcs[p.clickId].absY = 1;
                    p.frames.sendMessage(p, "You catch the magpie impling!");
                    p.addSkillXP(600 * p.skillLvl[21] * Config.bonusXP, 21);
                    Engine.playerItems.addItem(p, 11252, 1);
                    p.requestAnim(5209, 0);

                }
            } else {
                p.frames.sendMessage(p, "You need level 60 Hunting to catch this.");
            }
        }

        if (pnp.npcType == 6056) {
            if (p.skillLvl[21] >= 40) {
                if (p.equipment[3] != 11259) {
                    p.frames.sendMessage(p, "You need to be wielding a net to catch this with.");
                } else {
                    Engine.npcs[p.clickId].isDead = true;
                    Engine.npcs[p.clickId].absX = 1;
                    Engine.npcs[p.clickId].absY = 1;
                    p.frames.sendMessage(p, "You catch the young impling!");
                    p.addSkillXP(300 * p.skillLvl[21] * Config.bonusXP, 21);
                    Engine.playerItems.addItem(p, 11240, 1);
                    p.requestAnim(5209, 0);

                }
            } else {
                p.frames.sendMessage(p, "You need level 40 Hunting to catch this.");
            }
        }


        if (pnp.npcType == 6055) {
            if (p.skillLvl[21] >= 1) {
                if (p.equipment[3] != 11259) {
                    p.frames.sendMessage(p, "You need to be wielding a net to catch this with.");
                } else {
                    Engine.npcs[p.clickId].isDead = true;
                    Engine.npcs[p.clickId].absX = 1;
                    Engine.npcs[p.clickId].absY = 1;
                    p.frames.sendMessage(p, "You catch the baby impling!");
                    p.addSkillXP(125 * p.skillLvl[21] * Config.bonusXP, 21);
                    Engine.playerItems.addItem(p, 11238, 1);
                    p.requestAnim(5209, 0);
                }
            } else {
                p.frames.sendMessage(p, "You need level 1 Hunting to catch this.");
            }
        }

        if (pnp.npcType == 460) {//magic shop
            Engine.shopHandler.openshop(p, 5);
        }

        if (pnp.npcType == 756) {//party shop
            Engine.shopHandler.openshop(p, 7);
//p.frames.sendMessage(p, "<img=2><img=2>NOTE: not all items in here are donators only!");
        }

        if (pnp.npcType == 6537) {//dragon shop
            Engine.shopHandler.openshop(p, 10);
        }

        if (pnp.npcType == 212) {//donators weps
            Engine.shopHandler.openshop(p, 11);
        }

        if (pnp.npcType == 2725) {//god shop
            Engine.shopHandler.openshop(p, 19);
        }

        if (pnp.npcType == 4900) {//crafting shop
            Engine.shopHandler.openshop(p, 24);
        }
        if (pnp.npcType == 223) {//prayer shop
            Engine.shopHandler.openshop(p, 26);
        }

		/*
        * Nps at home
		*/
        if (pnp.npcType == 949) {//Quest Guide
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 949, 242, 2);
            p.frames.setString(p, "Quest Guide", 242, 3);
            p.frames.setString(p, "Dragon Slayer Works 100% and has a great reward,", 242, 4);
            p.frames.setString(p, "walk north and talk to the Guild Master to get started!", 242, 5);
            p.frames.setString(p, "...Continue...", 242, 6);
            p.frames.sendMessage(p, "Remember teleports are located in your quest tab as well as quests!");
        }
        if (pnp.npcType == 944) {//Combat Instructor
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 944, 242, 2);
            p.frames.setString(p, "Combat Instructor", 242, 3);
            p.frames.setString(p, "Hey " + p.username + ", take these wepons...", 242, 4);
            p.frames.setString(p, "and this armour, you look like you will need it..", 242, 5);
            p.frames.setString(p, "...Continue...", 242, 6);
            Engine.playerItems.addItem(p, 1075, 1);
            Engine.playerItems.addItem(p, 1087, 1);
            Engine.playerItems.addItem(p, 1117, 1);
            Engine.playerItems.addItem(p, 1155, 1);
            Engine.playerItems.addItem(p, 8844, 1);
            Engine.playerItems.addItem(p, 4119, 1);
            Engine.playerItems.addItem(p, 12985, 1);
            Engine.playerItems.addItem(p, 10638, 1);
            Engine.playerItems.addItem(p, 1321, 1);
            p.frames.sendMessage(p, "In your skill tab, Click the skill you want to train to start training!");
        }
        if (pnp.npcType == 943) {//Suvival Expert
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 943, 242, 2);
            p.frames.setString(p, "Suvival Expert", 242, 3);
            p.frames.setString(p, "...Continue...", 242, 6);
            p.frames.sendMessage(p, "You can make money from npc drops, skilling, selling items, and just");
            p.frames.sendMessage(p, "playing the game. Enjoy!");
            if (p.starter == 0) {
                p.frames.setString(p, "What do you know, looks like i have some coins to spair", 242, 4);
                p.frames.setString(p, "and some food, today is your lucky day!", 242, 5);
                Engine.playerItems.addItem(p, 380, 100);
                Engine.playerItems.addItem(p, 362, 10);
                Engine.playerItems.addItem(p, 995, 2000000);
                p.starter = 1;
            } else if (p.starter == 1) {
                p.frames.setString(p, "I'm sorry " + p.username + " but i dont have any more coins", 242, 4);
                p.frames.setString(p, "i can spair, please take this food..", 242, 5);
                Engine.playerItems.addItem(p, 380, 100);
            }
        }
        if (pnp.npcType == 946) {//Magic Instructor
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 946, 242, 2);
            p.frames.setString(p, "Magic Instructor", 242, 3);
            p.frames.setString(p, "The art of magic can be very effective", 242, 4);
            p.frames.setString(p, "i suggest you use it.", 242, 5);
            p.frames.setString(p, "...Continue...", 242, 6);
            Engine.playerItems.addItem(p, 556, 1000);
            Engine.playerItems.addItem(p, 558, 1000);
            Engine.playerItems.addItem(p, 555, 1000);
            Engine.playerItems.addItem(p, 557, 1000);
            Engine.playerItems.addItem(p, 554, 1000);
            Engine.playerItems.addItem(p, 1379, 1);
            Engine.playerItems.addItem(p, 579, 1);
            Engine.playerItems.addItem(p, 577, 1);
            Engine.playerItems.addItem(p, 1011, 1);
            p.frames.sendMessage(p, "You can make runes by rune crafting, you dont need any essence!");
        }

        if (pnp.npcType == 211) {//donators training
            p.Dialogue = 0;
            p.frames.showChatboxInterface(p, 242);
            p.frames.animateInterfaceId(p, 9850, 242, 2);
            p.frames.setNPCId(p, 211, 242, 2);
            p.frames.setString(p, "Sir Percival", 242, 3);
            p.frames.setString(p, " " + p.username + ", Welcome to donators training!", 242, 4);
            p.frames.setString(p, "Heres how it all works...", 242, 5);
            p.frames.setString(p, "...Continue...", 242, 6);
            p.frames.showInterface(p, 255);
            p.frames.setString(p, "<col=77FF77>When you kill a npc you will get a  rondom amount donators tokens. Once you get 100 of them talk to the genie and you will be rewarded with a marvolis reward! Enjoy your time training.", 255, 3);
        }

        if (pnp.npcType == 409) //donators training
        {
            if (Engine.playerItems.HasItemAmount(p, 4278, 100) == false) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9827, 241, 2);
                p.frames.setNPCId(p, 409, 241, 2);
                p.frames.setString(p, "Genie", 241, 3);
                p.frames.setString(p, "Hmm.. you don't have 100 donators tokens?", 241, 4);
            } else if (Engine.playerItems.HasItemAmount(p, 4278, 100) == true) {
                p.Dialogue = 0;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 409, 241, 2);
                p.frames.setString(p, "Genie", 241, 3);
                p.frames.setString(p, "Take this box and open it for your prize!", 241, 4);
                Engine.playerItems.addItem(p, 6199, 1);
                Engine.playerItems.deleteItem(p, 4278, 100);
            }
        }


        if (pnp.npcType == 3299) {
            if (p.skillLvl[19] < 99) {
                p.Dialogue = 50;
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 3299, 241, 2);
                p.frames.setString(p, "Master Gardener", 241, 3);
                p.frames.setString(p, "Oh good you are here...", 241, 4);
            } else {
                GetSkillCape(p, 51, 3299, "Master Gardener", "Farming");
            }
        }
        if (pnp.npcType == 548) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9827, 241, 2);
            p.frames.setNPCId(p, 548, 241, 2);
            p.frames.setString(p, "Thessalia", 241, 3);
            p.frames.setString(p, "Hello. Do you want a change of clothes?", 241, 4);
        }
        if (pnp.npcType == 598) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9820, 241, 2);
            p.frames.setNPCId(p, 598, 241, 2);
            p.frames.setString(p, "Hairdresser", 241, 3);
            p.frames.setString(p, "How about I shape up your hair...", 241, 4);
        }
        if (pnp.npcType == 521) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9850, 241, 2);
            p.frames.setNPCId(p, 521, 241, 2);
            p.frames.setString(p, "Shop assistant", 241, 3);
            p.frames.setString(p, "Hey, I sell some good stuplies and weapons.", 241, 4);
        }
        if (pnp.npcType == 1526) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9850, 241, 2);
            p.frames.setNPCId(p, 1526, 241, 2);
            p.frames.setString(p, "Lanthus", 241, 3);
            p.frames.setString(p, "Welcome to Castle Wars!", 241, 4);
        }


        if (pnp.npcType == 538) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9850, 241, 2);
            p.frames.setNPCId(p, 538, 241, 2);
            p.frames.setString(p, "Shop assistant", 241, 3);
            p.frames.setString(p, "I have all the barrows armour for sale!", 241, 4);
        }
        if (pnp.npcType == 549) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9850, 241, 2);
            p.frames.setNPCId(p, 549, 241, 2);
            p.frames.setString(p, "Horvik", 241, 3);
            p.frames.setString(p, "I got a ton of armour if you want to buy some.", 241, 4);
        }
        if (pnp.npcType == 1779) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9850, 241, 2);
            p.frames.setNPCId(p, 1779, 241, 2);
            p.frames.setString(p, "Ian", 241, 3);
            p.frames.setString(p, "You want to buy some pk suplies?", 241, 4);
        }
        if (pnp.npcType == 1787) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9850, 241, 2);
            p.frames.setNPCId(p, 1787, 241, 2);
            p.frames.setString(p, "Sam", 241, 3);
            p.frames.setString(p, "I suply all Saradominers with Armor and Wepons!", 241, 4);
        }
        if (pnp.npcType == 1) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9785, 241, 2);
            p.frames.setNPCId(p, 1, 241, 2);
            p.frames.setString(p, "Man", 241, 3);
            p.frames.setString(p, "Hey noob, gonna steal from me?", 241, 4);
        }
        if (pnp.npcType == 316) {
//Net Shrimps
            if (p.ActionTimer == 0) {
                if (p.skillLvl[10] >= 1) {
                    if (Engine.playerItems.HasItemAmount(p, 305, 1) == true) {

                        p.FishXP = 25;
                        p.FishGet = 317;
                        p.FishEmote = 620;
                        p.FishThat(p, p.FishXP, p.FishGet, p.FishEmote);
                    } else {
                        p.frames.sendMessage(p, "You need a big fishing net to fish here.");
                    }
                } else {
                    p.frames.sendMessage(p, "You need level 1 fishing to fish here.");
                }
            }
        }

        if (pnp.npcType == 313) {
//Net Bass
            if (p.ActionTimer == 0) {
                if (p.skillLvl[10] > 29) {
                    if (Engine.playerItems.HasItemAmount(p, 305, 1) == true) {
                        p.FishXP = 45;
                        p.FishGet = 363;
                        p.FishEmote = 620;
                        p.FishThat(p, p.FishXP, p.FishGet, p.FishEmote);
                    } else {
                        p.frames.sendMessage(p, "You need a big fishing net to fish here.");
                    }
                } else {
                    p.frames.sendMessage(p, "You need level 30 fishing to fish here.");
                }
            }
        }


        if (pnp.npcType == 312) {
//Cage Lobsters
            if (p.ActionTimer == 0) {
                if (p.skillLvl[10] > 39) {
                    if (Engine.playerItems.HasItemAmount(p, 301, 1) == true) {
                        p.FishXP = 90;
                        p.FishGet = 377;
                        p.FishEmote = 619;
                        p.FishThat(p, p.FishXP, p.FishGet, p.FishEmote);
                    } else {
                        p.frames.sendMessage(p, "You need a lobster pot to fish here.");
                    }
                } else {
                    p.frames.sendMessage(p, "You need level 40 fishing to fish here.");
                }
            }
        }
        if (pnp.npcType == 495) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9827, 241, 2);
            p.frames.setNPCId(p, 495, 241, 2);
            p.frames.setString(p, "Banker", 241, 3);
            p.frames.setString(p, "Hello there, you can bank by selecting the bank option.", 241, 4);
        }

        if (pnp.npcType == 2270) {
            if (p.skillLvl[17] > 98) {
                GetSkillCape(p, 1, 2270, "Martin Thwait", "Thieving");
            } else {
                OnlyLevel(p, 2, 2270, "Martin Thwait", 17, "Thieving");
            }
        }
        if (pnp.npcType == 437) {
            if (p.skillLvl[16] > 98) {
                GetSkillCape(p, 32, 437, "Cap'n Izzy No-Beard", "Agility");
            } else {
                OnlyLevel(p, 33, 437, "Cap'n Izzy No-Beard", 16, "Agility");
            }
        }

        if (pnp.npcType == 455) {
            if (p.skillLvl[15] > 98) {
                GetSkillCape(p, 36, 455, "Kaqemeex", "Herblore");
            } else {
                if (Engine.playerItems.HasItemAmount(p, 249, 1) || Engine.playerItems.HasItemAmount(p, 257, 1)) {
                    p.Dialogue = 37;
                    Engine.playerItems.deleteItem(p, 249, Engine.playerItems.getItemSlot(p, 249), 28);
                    Engine.playerItems.deleteItem(p, 257, Engine.playerItems.getItemSlot(p, 257), 28);
                    p.frames.showChatboxInterface(p, 241);
                    p.frames.animateInterfaceId(p, 9850, 241, 2);
                    p.frames.setNPCId(p, 455, 241, 2);
                    p.frames.setString(p, "Kaqemeex", 241, 3);
                    p.frames.setString(p, "Let me take those cleaned herbs from you...", 241, 4);
                } else {
                    OnlyLevel(p, 37, 455, "Kaqemeex", 15, "Herblore");
                }
            }
        }
        if (pnp.npcType == 5113) {
            if (p.skillLvl[21] > 98) {
                GetSkillCape(p, 38, 5113, "Hunting Expert", "Hunting");
            } else {
                OnlyLevel(p, 39, 5113, "Hunting Expert", 21, "Hunting");
            }
        }
        if (pnp.npcType == 847) {
            if (p.skillLvl[7] > 98) {
                GetSkillCape(p, 40, 847, "Head Chef", "Cooking");
            } else {
                OnlyLevel(p, 41, 847, "Head Chef", 7, "Cooking");
            }
        }
        if (pnp.npcType == 604) {
            if (p.skillLvl[13] > 98) {
                GetSkillCape(p, 42, 604, "Thurgo", "Smithing");
            } else {
                OnlyLevel(p, 43, 604, "Thurgo", 13, "Smithing");
            }
        }
        if (pnp.npcType == 1658) {
            if (p.skillLvl[6] > 98) {
                GetSkillCape(p, 3, 1658, "Robe Store Owner", "Magic");
            } else {
                OnlyLevel(p, 4, 1658, "Robe Store Owner", 6, "Magic");
            }
        }
        if (pnp.npcType == 802) {
            if (p.skillLvl[5] > 98) {
                GetSkillCape(p, 19, 802, "Brother Jered", "Prayer");
            } else {
                OnlyLevel(p, 20, 802, "Brother Jered", 5, "Prayer");
            }
        }

        if (pnp.npcType == 4906) {
            if (Engine.playerItems.HasItemAmount(p, 1511, 1) == true) {
                Engine.playerItems.addItem(p, 995, 7000);
                Engine.playerItems.deleteItem(p, 1511, Engine.playerItems.getItemSlot(p, 1511), 1);
                p.frames.showChatboxInterface(p, 241);
                p.frames.animateInterfaceId(p, 9850, 241, 2);
                p.frames.setNPCId(p, 4906, 241, 2);
                p.frames.setString(p, "Woodcutting Tutor", 241, 3);
                p.frames.setString(p, "Thank you for the logs!", 241, 4);
            } else if (p.skillLvl[8] > 98) {
                GetSkillCape(p, 15, 4906, "Woodcutting Tutor", "Woodcutting");
            } else {
                OnlyLevel(p, 16, 4906, "Woodcutting Tutor", 8, "Woodcutting");
            }

        }

        if (pnp.npcType == 805) {
            if (p.skillLvl[12] > 98) {
                GetSkillCape(p, 34, 805, "Master Crafter", "Crafting");
            } else {
                OnlyLevel(p, 35, 805, "Master Crafter", 12, "Crafting");
            }
        }

        if (pnp.npcType == 553) {
            if (p.skillLvl[20] > 98) {
                GetSkillCape(p, 21, 553, "Aubury", "Runecrafting");
            } else {
                OnlyLevel(p, 22, 553, "Aubury", 20, "Runecrafting");
            }
        }

        if (pnp.npcType == 4946) {
            if (p.skillLvl[11] > 98) {
                GetSkillCape(p, 26, 4946, "Ignatius Vulcan", "Firemaking");
            } else {
                OnlyLevel(p, 27, 4946, "Ignatius Vulcan", 11, "Firemaking");
            }
        }

        if (pnp.npcType == 308) {
            if (p.skillLvl[10] > 98) {
                GetSkillCape(p, 13, 308, "Master Fisher", "Fishing");
            } else {
                OnlyLevel(p, 14, 308, "Master Fisher", 10, "Fishing");
            }
        }

        if (pnp.npcType == 3295) {
            if (p.skillLvl[14] > 98) {
                GetSkillCape(p, 17, 3295, "Dwarf", "Mining");
            } else {
                OnlyLevel(p, 18, 3295, "Dwarf", 14, "Mining");
            }
        }
        if (pnp.npcType == 575) {
            if (p.skillLvl[9] > 98) {
                GetSkillCape(p, 28, 575, "Hickton", "Fletching");
            } else {
                OnlyLevel(p, 29, 575, "Hickton", 9, "Fletching");
            }
        }

        if (pnp.npcType == 7950) {
            if (p.skillLvl[1] > 98) {
                GetSkillCape(p, 5, 7950, "Melee Tutor", "Defence");
            } else {
                OnlyLevel(p, 6, 7950, "Melee Tutor", 1, "Defence");
            }
        }


        if (pnp.npcType == 1599) {
            p.Runecrafting = false;

            p.Cooking = false;
            p.TalkAgent = false;
            p.DecorChange = false;

            p.frames.setString(p, "Slayer Cape", 458, 1);
            p.frames.setString(p, "New Slayer Task", 458, 2);
            p.frames.setString(p, "Teleport to slayer Cave", 458, 3);
            p.frames.showChatboxInterface(p, 458);
        }


        if (pnp.npcType == 4247) {
            p.Runecrafting = false;

            p.Cooking = false;
            p.TalkAgent = true;
            p.DecorChange = false;
            p.frames.setString(p, "Construction Cape", 458, 1);
            p.frames.setString(p, "How do I go in my friends house?", 458, 2);
            p.frames.setString(p, "Buy new house decoration", 458, 3);
            p.frames.showChatboxInterface(p, 458);
        }

        if (pnp.npcType == 4288) {
            if (p.skillLvl[0] > 98) {
                GetSkillCape(p, 7, 4288, "Ajjat", "Attack");
            } else {
                OnlyLevel(p, 8, 4288, "Ajjat", 0, "Attack");
            }
        }

        if (pnp.npcType == 4297) {
            if (p.skillLvl[2] > 98) {
                GetSkillCape(p, 9, 4297, "Sloane", "Strength");
            } else {
                OnlyLevel(p, 10, 4297, "Sloane", 2, "Strength");
            }
        }

        if (pnp.npcType == 961) {
            if (p.skillLvl[3] > 98) {
                GetSkillCape(p, 11, 961, "Surgeon General Tafani", "Hitpoints");
            } else {
                OnlyLevel(p, 12, 961, "Surgeon General Tafani", 3, "Hitpoints");
            }
        }


        if (pnp.npcType == 682) {
            if (p.skillLvl[4] > 98) {
                GetSkillCape(p, 23, 682, "Armour salesman", "Range");
            } else {
                OnlyLevel(p, 24, 682, "Armour salesman", 4, "Range");
            }
        }

        if (pnp.npcType == 1861) {
            p.Dialogue = 25;
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9760, 241, 2);
            p.frames.setNPCId(p, 1861, 241, 2);
            p.frames.setString(p, "Ranged Tutor", 241, 3);
            p.frames.setString(p, "Take these items...", 241, 4);
            Engine.playerItems.addItem(p, 882, 100);
            Engine.playerItems.addItem(p, 839, 1);
        }


        if (pnp.npcType == 494) {
            p.frames.showChatboxInterface(p, 241);
            p.frames.animateInterfaceId(p, 9850, 241, 2);
            p.frames.setNPCId(p, 494, 241, 2);
            p.frames.setString(p, "Banker", 241, 3);
            p.frames.setString(p, "Hello there, you can bank by selecting the bank option.", 241, 4);
        }


    }
}
