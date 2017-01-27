package icedice.io.packets;

import icedice.util.Misc;
import icedice.Engine;
import icedice.players.Player;

public class PublicChat implements Packet {

    /**
     * Handles player chatting.
     *
     * @param p          The Player which the frame should be handled for.
     * @param packetId   The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        if (p.muted == 1) {
            p.frames.sendMessage(p, "You are muted and cannot talk!");
            return;
        }

        int chatTextEffects = p.stream.readUnsignedWord();
        int numChars = p.stream.readUnsignedByte();
        String chatText = Misc.decryptPlayerChat(p.stream, numChars);
        if (chatText.startsWith("/")) {
            if (p.clanRoom.length() > 0) {
                Engine.clanChat.sendMessage(p, chatText.substring(1));
            }
        } else if (chatText.startsWith("\\")) {
            for (Player p5 : Engine.players) {
                if (p5 == null) {
                    continue;
                }

                if (p5.rights >= 1) {
                    p5.frames.sendMessage(p5, "<col=980000>[StaffChat] <img=" + (p.rights - 1) + ">" + p.username + ": " + chatText.substring(1));
                } else {
                }
            }
        } else {

            p.chatTextEffects = chatTextEffects;
            p.chatText = chatText;
            p.chatTextUpdateReq = true;
            p.updateReq = true;
        }

        Misc.println("Chat Monitor: [" + Misc.getDate() + "] " + p.username + ": " + chatText);
        Engine.fileManager.appendData("Chatlog.txt", "[" + Misc.getDate() + "] " + Misc.optimizeText(p.username) + ": " + Misc.optimizeText(chatText));
        //Iceframe.updateChat();
    }
}
