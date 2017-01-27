
package icedice.io.packets;


import icedice.Engine;
import icedice.util.Misc;
import icedice.players.Player;


public class PlayerOption4 implements Packet {

    /**
     * Handles the first player option.
     *
     * @param p          The Player which the frame should be handled for.
     * @param packetId   The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }

        if (!p.playerOption4) {
            int playerId = p.stream.readUnsignedWordBigEndianA();

            if (playerId <= 0 || playerId >= Engine.players.length
                    || Engine.players[playerId] == null) {
                return;
            }
            p.requestFaceTo(playerId + 32768);
            p.clickId = playerId;
            p.clickX = Engine.players[playerId].absX;
            p.clickY = Engine.players[playerId].absY;
            if (Misc.getDistance(p.absX, p.absY, p.clickX, p.clickY) > 30) {
                return;
            }
            p.playerOption4 = true;
        }

        Player p2 = Engine.players[p.clickId];
        p.followPlayer = p2.playerId;
        p.followingPlayer = true;
    }
}
