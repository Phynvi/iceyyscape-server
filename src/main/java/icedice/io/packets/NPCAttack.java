

package icedice.io.packets;


import icedice.Engine;
import icedice.npcs.*;
import icedice.players.Player;


public class NPCAttack implements Packet {

    /**
     * Handles selecting the attack option on NPCs.
     *
     * @param p          The Player which the frame should be handled for.
     * @param packetId   The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        int npcId = p.stream.readUnsignedWord();
        NPC n = Engine.npcs[npcId];

        p.attackNPC = npcId;
        p.attackingNPC = true;
        p.requestFaceTo(npcId);
    }
}
