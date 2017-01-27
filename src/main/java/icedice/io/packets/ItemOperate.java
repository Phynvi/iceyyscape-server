
package icedice.io.packets;


import icedice.util.Misc;
import icedice.Engine;
import icedice.players.Player;


public class ItemOperate implements Packet {

    /**
     * Handles operating equipped items.
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
        int itemId = p.stream.readUnsignedWordA();
        int itemSlot = p.stream.readUnsignedWordBigEndianA();

        if (itemSlot < 0 || itemSlot >= p.equipment.length
                || p.equipment[itemSlot] != itemId) {
            return;
        }

        switch (itemId) {


            case 1704:
                p.Choice = 3;
                p.Dialogue = 0;
                p.frames.setString(p, "Fight Pits", 458, 1);
                p.frames.setString(p, "Caslte Wars", 458, 2);
                p.frames.setString(p, "Port Sarim", 458, 3);
                p.frames.showChatboxInterface(p, 458);

                p.Cooking = false;
                p.TalkAgent = false;
                p.DecorChange = false;
                break;
            case 11283:
                Player p2 = Engine.players[p.attackPlayer];
                int offsetX = (p.absX - p2.absX) * -1;
                int offsetY = (p.absY - p2.absY) * -1;
                if (p.DFStimer == 0) {
                    if (p.attackingPlayer) {
                        p.requestAnim(6695, 0);
                        p.requestGFX(1164, 0);
                        p.DFStimer += 60;
                        p2.appendHit(Misc.random(50), 0);
                        p.frames.createGlobalProjectile(p.absY, p.absX, offsetY,
                                offsetX, 1166, 43, 31, 70, p2.playerId);
                    }
                }
                if (p.DFStimer > 0) {
                    p.frames.sendMessage(p, "You must wait before you can use the DFS special again.");
                }
                break;
            case 7676:
                if (p.icetimer == 0) {
                    p.specialAmount = 100;
                    p.specialAmountUpdateReq = true;
                    p.runEnergy = 1000;
                    p.runEnergyUpdateReq = true;
                    p.skillLvl[3] = p.getLevelForXP(3);
                    p.frames.setSkillLvl(p, 3);
                    p.requestAnim(1500, 0);
                    p.requestGFX(444, 0);
                    p.frames.sendMessage(p, "<img=2>Your Hitpoints, Special attack, and energy have been restored!");
                    p.icetimer += 300;
                } else {
                    if (p.icetimer > 1) {
                        p.frames.sendMessage(p, "<img=2>You must wait " + p.icetimer + " seconds before you can use this special again.");
                    }
                }
                break;
            case 12842:
                p.requestAnim(8956, 0);
                p.runEmote = 8961;
                p.walkEmote = 8961;
                p.standEmote = 8961;
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                break;
            case 11951:
                p.requestAnim(7385, 0);
                p.frames.sendMessage(p, "You pelt " + p.username + " with a snowball.");
                break;
            case 7927: //Easter Ring
                if (p.npcType == -1) {
                    int random = Misc.random(6);
                    if (random == 1) p.npcType = 3689;
                    if (random == 2) p.npcType = 3690;
                    if (random == 3) p.npcType = 3691;
                    if (random == 4) p.npcType = 3692;
                    if (random == 5) p.npcType = 3693;
                    if (random == 6) p.npcType = 3694;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.sendMessage(p, "You use your Easter Ring to turn into an egg.");
                } else {
                    p.npcType = -1;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                break;


            default:
                //Misc.println("[" + p.username + "] Operate item: " + itemId);
                break;
        }
    }
}
