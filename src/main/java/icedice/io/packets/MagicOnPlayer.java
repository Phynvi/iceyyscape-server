
package icedice.io.packets;


import icedice.Engine;
import icedice.util.Misc;
import icedice.Server;
import icedice.players.Player;


public class MagicOnPlayer implements Packet {

    /**
     * Handles magic on players.
     *
     * @param p          The Player which the frame should be handled for.
     * @param packetId   The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {

        p.attackPlayer = p.stream.readSignedWordA();
        int playerId = p.stream.readSignedWordBigEndian();
        int interfaceId = p.stream.readUnsignedWord();

        p.clickId = p.stream.readUnsignedWord();
        Player p2 = Server.engine.players[playerId];

        if (p.AtDuel() && p.DuelPartner != p2.playerId) {
            p.frames.sendMessage(p, "This is not your opponent");
        } else if (p.DuelCan == false && p.AtDuel() == true) {
            p.frames.sendMessage(p, "You cannot fight just yet.");
        } else if (p.AtJail() || p.AtClanLobby()) {
            p.frames.sendMessage(p, "You cannot attack some one here.");
        } else {

            switch (interfaceId) {

                case 430:
                    if (p.magicDelay == 0) {
                        if (p.clickId == 19 && p.skillLvl[6] >= 93) {
                            if (Engine.playerItems.invItemCount(p, 9075) > 3
                                    && Engine.playerItems.invItemCount(p, 560) > 2
                                    && Engine.playerItems.invItemCount(p, 557) > 10) {
                                if (!p2.vengOn) {
                                    p.stopMovement(p);
                                    p.magicDelay = 7;
                                    p.requestAnim(4411, 0);
                                    p2.requestGFX(725, 0);
                                    p2.vengOn = true;
                                    Engine.playerItems.deleteItem(p, 9075,
                                            Engine.playerItems.getItemSlot(p, 9075), 3);
                                    Engine.playerItems.deleteItem(p, 560,
                                            Engine.playerItems.getItemSlot(p, 560), 2);
                                    Engine.playerItems.deleteItem(p, 557,
                                            Engine.playerItems.getItemSlot(p, 557), 10);
                                } else {
                                    p.frames.sendMessage(p,
                                            "That person already has vengeance casted.");
                                }
                            } else {
                                p.frames.sendMessage(p,
                                        "You don't have enough runes to cast this spell.");
                            }
                        } else {
                            p.frames.sendMessage(p,
                                    "You need a magic level of 93 to cast this spell.");
                        }
                    } else {
                        p.stopMovement(p);
                    }
                    break;


                case 193:

                    if (p.magicDelay == 0) {

                        if (p.clickId == 3 || p.skillLvl[6] >= 94) {
                            if (Engine.playerItems.invItemCount(p, 560) > 3
                                    && Engine.playerItems.invItemCount(p, 555) > 5
                                    && Engine.playerItems.invItemCount(p, 565) > 1) {
                                p.requestFaceTo(p2.playerId + 32768);
                                p.stopMovement(p);
                                p.magicDelay = 5;
                                p.requestAnim(1979, 0);
                                p.frames.sendMessage(p, "You cast ice barrage on this failure");
                                p2.requestGFX(369, 0);
                                p2.appendHit(Misc.random(30), 0);
                                p2.requestFaceTo(p.playerId + 32768);
                                p2.freezeDelay = 10;
                                p2.frames.sendMessage(p2, " You are Frozen!!");
                                p.combatDelay += p.attackDelay;
                                Engine.playerItems.deleteItem(p, 560,
                                        Engine.playerItems.getItemSlot(p, 560), 4);
                                Engine.playerItems.deleteItem(p, 555,
                                        Engine.playerItems.getItemSlot(p, 555), 6);
                                Engine.playerItems.deleteItem(p, 556,
                                        Engine.playerItems.getItemSlot(p, 556), 2);
                            } else {
                                p.frames.sendMessage(p,
                                        "You don't have enough runes to cast this spell.");
                            }
                        } else {
                            p.frames.sendMessage(p,
                                    "You need a magic level of 94 to cast this spell.");
                        }
                    } else {
                        p.stopMovement(p);
                    }

                    break;

                default:
                    // Misc.println(
                    // "PlayerID " + playerId + " - InterfaceID " + interfaceId
                    // + " - ButtonID " + p.clickId + ".");
                    break;

            }
        }
    }
}
