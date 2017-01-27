package icedice.io.packets;

import icedice.Engine;
import icedice.players.Player;

public class DropItem implements Packet {

    /**
     * Handles dropping items in your inventory.
     *
     * @param p          The Player which the frame should be handled for.
     * @param packetId   The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        int junk = p.stream.readDWord();
        int itemSlot = p.stream.readUnsignedWordBigEndianA();
        int itemId = p.stream.readUnsignedWord();

        if (itemSlot < 0 || itemSlot >= p.items.length
                || p.items[itemSlot] != itemId) {
            return;
        }
        if (!Engine.items.isUntradable(itemId)) {

            if (p.LoadedBackup > 0) {
                p.frames.sendMessage(p, "You must wait " + (p.LoadedBackup / 2) + " more seconds till you can drop an item after loading your backup.");
                return;
            }

            if (itemId == 6570) {
                Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
                return;
            }

            if (p.inBounty) {
                p.pkIcon = Engine.BountyHunter.setSkull(p);
                p.updateReq = p.appearanceUpdateReq = true;
            }

            if (itemId == 12490) {
                if (p.FamiliarID == 0) {
                    if (p.skillLvl[23] != 99) {
                        p.frames.sendMessage(p, "You need 99 summoning to drop this pet.");
                    } else {
                        p.requestAnim(827, 0);
                        p.frames.sendMessage(p, "You drop your Squirrel.");
                        p.frames.setTab(p, 80, 663);
                        p.frames.animateInterfaceId(p, 9850, 663, 3);
                        p.frames.setNPCId(p, 6920, 663, 3);
                        Engine.newSummonNPC(6920, p.absX, p.absY + 1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
                        p.FamiliarType = 6920;
                        Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
                    }
                }
                return;
            }

            if (itemId == 12510) {
                if (p.FamiliarID == 0) {
                    if (p.skillLvl[23] != 99) {
                        p.frames.sendMessage(p, "You need 99 summoning to drop this pet.");
                    } else {
                        p.requestAnim(827, 0);
                        p.frames.sendMessage(p, "You drop your Guthix Raptor.");
                        p.frames.setTab(p, 80, 663);
                        p.frames.animateInterfaceId(p, 9850, 663, 3);
                        p.frames.setNPCId(p, 6957, 663, 3);
                        Engine.newSummonNPC(6957, p.absX, p.absY + 1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
                        p.FamiliarType = 6957;
                        Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
                    }
                }
                return;
            }

            if (itemId == 12185) {
                if (p.FamiliarID == 0) {
                    if (p.skillLvl[23] != 99) {
                        p.frames.sendMessage(p, "You need 99 summoning to drop this pet.");
                    } else {
                        p.requestAnim(827, 0);
                        p.frames.sendMessage(p, "You drop your Saradomin Owl.");
                        p.frames.setTab(p, 80, 663);
                        p.frames.animateInterfaceId(p, 9850, 663, 3);
                        p.frames.setNPCId(p, 6951, 663, 3);
                        Engine.newSummonNPC(6951, p.absX, p.absY + 1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
                        p.FamiliarType = 6951;
                        Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
                    }
                }
                return;
            }

            if (itemId == 12508) {
                if (p.FamiliarID == 0) {
                    if (p.skillLvl[23] != 99) {
                        p.frames.sendMessage(p, "You need 99 summoning to drop this pet.");
                    } else {
                        p.requestAnim(827, 0);
                        p.frames.sendMessage(p, "You drop your Zamorak Hawk.");
                        p.frames.setTab(p, 80, 663);
                        p.frames.animateInterfaceId(p, 9850, 663, 3);
                        p.frames.setNPCId(p, 6954, 663, 3);
                        Engine.newSummonNPC(6954, p.absX, p.absY + 1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
                        p.FamiliarType = 6954;
                        Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
                    }
                }
                return;
            }

            if (itemId == 12471) {
                if (p.FamiliarID == 0) {
                    if (p.skillLvl[23] != 99) {
                        p.frames.sendMessage(p, "You need 99 summoning to drop this pet.");
                    } else {
                        p.requestAnim(827, 0);
                        p.frames.sendMessage(p, "You drop your dragon on the ground.");
                        p.frames.setTab(p, 80, 663);
                        p.frames.animateInterfaceId(p, 9850, 663, 3);
                        p.frames.setNPCId(p, 6903, 663, 3);
                        Engine.newSummonNPC(6903, p.absX, p.absY + 1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
                        p.FamiliarType = 6903;
                        Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
                    }
                }
                return;
            }

            if (itemId == 12473) {
                if (p.FamiliarID == 0) {
                    if (p.skillLvl[23] != 99) {
                        p.frames.sendMessage(p, "You need 99 summoning to drop this pet.");
                    } else {
                        p.requestAnim(827, 0);
                        p.frames.sendMessage(p, "You drop your dragon on the ground.");
                        p.frames.setTab(p, 80, 663);
                        p.frames.animateInterfaceId(p, 9850, 663, 3);
                        p.frames.setNPCId(p, 6905, 663, 3);
                        Engine.newSummonNPC(6905, p.absX, p.absY + 1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
                        p.FamiliarType = 6905;
                        Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
                    }
                }
                return;
            }

            if (itemId == 12476) {
                if (p.FamiliarID == 0) {
                    if (p.skillLvl[23] != 99) {
                        p.frames.sendMessage(p, "You need 99 summoning to drop this pet.");
                    } else {
                        p.requestAnim(827, 0);
                        p.frames.sendMessage(p, "You drop your !KING BLACK DRAGON! on the ground.");
                        p.frames.setTab(p, 80, 663);
                        p.frames.animateInterfaceId(p, 9850, 663, 3);
                        p.frames.setNPCId(p, 50, 663, 3);
                        Engine.newSummonNPC(50, p.absX, p.absY + 1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
                        p.FamiliarType = 50;
                        Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
                    }
                }
                return;
            }

            if (itemId == 12475) {
                if (p.FamiliarID == 0) {
                    if (p.skillLvl[23] != 99) {
                        p.frames.sendMessage(p, "You need 99 summoning to drop this pet.");
                    } else {
                        p.requestAnim(827, 0);
                        p.frames.sendMessage(p, "You drop your dragon on the ground.");
                        p.frames.setTab(p, 80, 663);
                        p.frames.animateInterfaceId(p, 9850, 663, 3);
                        p.frames.setNPCId(p, 6907, 663, 3);
                        Engine.newSummonNPC(6907, p.absX, p.absY + 1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
                        p.FamiliarType = 6907;
                        Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
                    }
                }
                return;
            }

            Engine.items.createGroundItem(itemId, p.itemsN[itemSlot], p.absX, p.absY, p.heightLevel, p.username);
            Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);

        }
    }
}
