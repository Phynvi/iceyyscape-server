package icedice.io.packets;

import icedice.players.items.PlayerItems;
import icedice.Engine;
import icedice.players.Player;

public class ItemOnObject implements Packet {

    /**
     * Handles item on item packet.
     *
     * @param Player     p The player which the packet will be created for.
     * @param packetId   the packet id which is activated (Which handles this class)
     * @param packetSize the amount of bytes being received for the packet.
     */
    public void handlePacket(Player player, int packetId, int packetSize) {
        if (player == null) {
            return;
        }
        /**
         * These are the correct stream methods
         * for item on item packet.
         */
        int objectId = player.stream.readUnsignedWordA();
        int junk = player.stream.readUnsignedWord();
        int itemUsed = junk;
        PlayerItems pi = new PlayerItems();
        player.wc.resetWoodcutting();
        player.mi.resetMining();

		/* Cooking */
        if (itemUsed == 317 && objectId == 28301) {
            player.frames.setString(player, "Cook 1", 458, 1);
            player.frames.setString(player, "Cook 5", 458, 2);
            player.frames.setString(player, "Cook All", 458, 3);
            player.frames.showChatboxInterface(player, 458);
            player.CookXP = 50;
            player.CookID = 317;
            player.CookGet = 315;
            player.Cooking = true;
            player.Smithing = false;
            player.TalkAgent = false;
            player.DecorChange = false;
        }


        if (itemUsed == 335 && objectId == 28301) {
            if (player.skillLvl[7] < 15) {
                player.frames.sendMessage(player, "You need level 15 Cooking to cook this.");
            } else {
                player.frames.setString(player, "Cook 1", 458, 1);
                player.frames.setString(player, "Cook 5", 458, 2);
                player.frames.setString(player, "Cook All", 458, 3);
                player.frames.showChatboxInterface(player, 458);
                player.CookXP = 100;
                player.CookID = 335;
                player.CookGet = 333;
                player.Cooking = true;
                player.Smithing = false;
                player.TalkAgent = false;
                player.DecorChange = false;
            }
        }
        if (itemUsed == 491 && objectId == 28301) {
            if (player.skillLvl[7] < 30) {
                player.frames.sendMessage(player, "You need level 30 Cooking to cook this.");
            } else {
                player.frames.setString(player, "Cook 1", 458, 1);
                player.frames.setString(player, "Cook 5", 458, 2);
                player.frames.setString(player, "Cook All", 458, 3);
                player.frames.showChatboxInterface(player, 458);
                player.CookXP = 200;
                player.CookID = 363;
                player.CookGet = 365;
                player.Cooking = true;
                player.Smithing = false;
                player.TalkAgent = false;
                player.DecorChange = false;
            }
        }

        if (itemUsed == 377 && objectId == 28301) {
            if (player.skillLvl[7] < 50) {
                player.frames.sendMessage(player, "You need level 50 Cooking to cook this.");
            } else {
                player.frames.setString(player, "Cook 1", 458, 1);
                player.frames.setString(player, "Cook 5", 458, 2);
                player.frames.setString(player, "Cook All", 458, 3);
                player.frames.showChatboxInterface(player, 458);
                player.CookXP = 350;
                player.CookID = 377;
                player.CookGet = 379;
                player.Cooking = true;
                player.Smithing = false;
                player.TalkAgent = false;
                player.DecorChange = false;
            }
        }

        if (itemUsed == 383 && objectId == 28301) {
            if (player.skillLvl[7] < 79) {
                player.frames.sendMessage(player, "You need level 79 Cooking to cook this.");
            } else {
                player.frames.setString(player, "Cook 1", 458, 1);
                player.frames.setString(player, "Cook 5", 458, 2);
                player.frames.setString(player, "Cook All", 458, 3);
                player.frames.showChatboxInterface(player, 458);
                player.CookXP = 550;
                player.CookID = 383;
                player.CookGet = 385;
                player.Cooking = true;
                player.Smithing = false;
                player.TalkAgent = false;
                player.DecorChange = false;
            }
        }

        // if (itemUsed == 261 && objectId == 28301) {
        // if (player.skillLvl[7] < 90) {
        // player.frames.sendMessage(player, "You need level 90 Cooking to cook this.");
        // } else {
        // player.frames.setString(player, "Cook 1", 458, 1);
        // player.frames.setString(player, "Cook 5", 458, 2);
        // player.frames.setString(player, "Cook All", 458, 3);
        // player.frames.showChatboxInterface(player, 458);
        // player.CookXP = 700;
        // player.CookID = 389;
        // player.CookGet = 391;
        // player.Cooking = true;
        // player.Smithing = false;
        // player.TalkAgent = false;
        // player.DecorChange = false;
        // }
        // }
		
		/* Smithing */
        if (objectId == 10381 && pi.invItemCount(player, 2347) == 0) {
            player.frames.sendMessage(player, "You need a hammer to smith!");
            return;
        } else {
            if (itemUsed == 2349 && objectId == 10381) {
                Engine.smithing.smithing(player, 1);
            } else if (itemUsed == 2351 && objectId == 10381) {
                Engine.smithing.smithing(player, 2);
            } else if (itemUsed == 2353 && objectId == 10381) {
                Engine.smithing.smithing(player, 3);
            } else if (itemUsed == 2359 && objectId == 10381) {
                Engine.smithing.smithing(player, 4);
            } else if (itemUsed == 2361 && objectId == 10381) {
                Engine.smithing.smithing(player, 5);
            } else if (itemUsed == 2363 && objectId == 10381) {
                Engine.smithing.smithing(player, 6);
            }
        }
		
		/* Farming */
        if (player.absX < 2811 && player.absY > 2808) {
            if (itemUsed == 5096 && objectId == 34701) { //Marigold
                if (player.FarmType > 0) {
                    player.frames.sendMessage(player, "You can't plant more than one thing at a time.");
                } else {
                    player.FarmingTimer = 30;
                    player.FarmingType = 7867;
                    player.FarmType = 1;
                    Engine.playerItems.deleteItem(player, 5096, Engine.playerItems.getItemSlot(player, 5096), 1);
                    player.requestAnim(5212, 0);
                    player.frames.createObject(player, 0, player.heightLevel, 2809, 3463, 1, 10);
                    player.frames.createObject(player, 7867, player.heightLevel, 2810, 3464, 1, 10);

                }
            }
            if (itemUsed == 5100 && objectId == 34701) { //Limpwurt
                if (player.FarmType > 0) {
                    player.frames.sendMessage(player, "You can't plant more than one thing at a time.");
                } else {
                    player.FarmingTimer = 30;
                    player.FarmingType = 7851;
                    player.FarmType = 1;
                    Engine.playerItems.deleteItem(player, 5100, Engine.playerItems.getItemSlot(player, 5100), 1);
                    player.requestAnim(5212, 0);
                    player.frames.createObject(player, 0, player.heightLevel, 2809, 3463, 1, 10);
                    player.frames.createObject(player, 7851, player.heightLevel, 2810, 3464, 1, 10);
                }
            }
        }
        if (itemUsed == 5083 && objectId == 34701) { //AppleTree
            if (player.FarmType > 0) {
                player.frames.sendMessage(player, "You can't plant more than one thing at a time.");
            } else {
                player.FarmingTimer = 30;
                player.FarmingType = 1436;
                player.FarmType = 2;
                Engine.playerItems.deleteItem(player, 5283, Engine.playerItems.getItemSlot(player, 5283), 1);
                player.requestAnim(5212, 0);
                player.frames.createObject(player, 1436, player.heightLevel, 2814, 3464, 0, 10);
            }
        }
        if (itemUsed == 5288 && objectId == 34701) { //PapayaTree
            if (player.FarmType > 0) {
                player.frames.sendMessage(player, "You can't plant more than one thing at a time.");
            } else {
                player.FarmingTimer = 30;
                player.FarmingType = 8106;
                player.FarmType = 2;
                Engine.playerItems.deleteItem(player, 5288, Engine.playerItems.getItemSlot(player, 5288), 1);
                player.requestAnim(5212, 0);
                player.frames.createObject(player, 8106, player.heightLevel, 2814, 3464, 0, 10);
            }
        }
        //System.out.println("itemUsed: " + itemUsed + "    ObjectId:" + objectId);
    }
}
