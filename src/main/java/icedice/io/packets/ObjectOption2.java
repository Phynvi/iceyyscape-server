/*
 * Class ObjectOption2
 *
 * Version 1.0
 *
 * Saturday, August 23, 2008
 *
 * Created by Palidino76
 */


package icedice.io.packets;


import icedice.util.Misc;
import icedice.Engine;
import icedice.players.Player;


public class ObjectOption2 implements Packet {

    /*
     * make sure to document EVERY coordinate to go with each object unless an un-important object(wilderness ditch lol).
     * This will prevent people from spawning an object client side and actually using it.
     * So make sure to include with the id, objectX == # && objectY == #
     */

    /**
     * Handles the second option on objects.
     *
     * @param p          The Player which the frame should be handled for.
     * @param packetId   The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        if (!p.objectOption2) {
            p.clickY = p.stream.readUnsignedWordA();
            p.clickId = p.stream.readUnsignedWordBigEndian();
            p.clickX = p.stream.readUnsignedWordBigEndianA();
            if (Misc.getDistance(p.absX, p.absY, p.clickX, p.clickY) > 30) {
                return;
            }
            p.objectOption2 = true;
        }
        int distance = Misc.getDistance(p.clickX, p.clickY, p.absX, p.absY);

        if (p.walkDir != -1 || p.runDir != -1
                || distance > objectSize(p.clickId)) {
            return;
        }
        p.objectOption2 = false;
        switch (p.clickId) {
            default:
                //Misc.println("[" + p.username + "] Unhandled object 2: " + p.clickId);
                break;
            case 28089:
                p.GrandExchange.openMainInterface();
                break;
            case 3045:
            case 5276:
            case 6084:
            case 10517:
            case 11338:
            case 11402:
            case 11758:
            case 12798:
            case 12799:
            case 12800:
            case 12801:
            case 14367:
            case 14368:
            case 16700:
            case 18491:
            case 19230:
            case 20325:
            case 20326:
            case 20327:
            case 20328:
            case 22819:
            case 2213:
            case 24914:
            case 25808:
            case 26972:
            case 29085:
            case 30015:
            case 30016:
            case 34205:
            case 34752:
            case 35647:
            case 35648:
            case 36262:
            case 36786:
            case 2214:
                if (p.alreadyBanked) {
                    p.openBank();
                } else if (p.hasBankPin == 1) {
                    p.frames.showInterface(p, 13);
                    p.bankpin();
                    p.frames.sendMessage(p, "Please enter in your bank pin.");
                } else if (p.hasBankPin == 0) {
                    p.openBank();
                    p.frames.sendMessage(p, "You do not have a bank pin, do ::bankpin to set one.");
                }
                break;
//==================================thiefing stalls===========================================
            case 34384:
                if (p.skillLvl[17] > 89) {
                    if (p.ActionTimer == 0) {
                        p.ActionTimer = 3;
                        p.requestAnim(881, 0);
                        Engine.playerItems.addItem(p, 995, 1000 + Misc.random(1000));
                        p.frames.sendMessage(p, "You steal from the Bakers Stall.");
                        p.addSkillXP(400 * p.skillLvl[17], 17);
                    }
                } else {
                    p.frames.sendMessage(p, "You need 90 Thieving to steal from this stall.");
                }
                break;
            case 34382:
                if (p.skillLvl[17] > 94) {
                    if (p.ActionTimer == 0) {
                        p.ActionTimer = 3;
                        p.requestAnim(881, 0);
                        Engine.playerItems.addItem(p, 995, 5000 + Misc.random(10000));
                        p.frames.sendMessage(p, "You steal from the Silver Stall.");
                        p.addSkillXP(450 * p.skillLvl[17], 17);
                    }
                } else {
                    p.frames.sendMessage(p, "You need 95 Thieving to steal from this stall.");
                }
                break;

            case 34383:
                if (p.skillLvl[17] > 96) {
                    if (p.ActionTimer == 0) {
                        p.ActionTimer = 3;
                        p.requestAnim(881, 0);
                        Engine.playerItems.addItem(p, 995, 8000 + Misc.random(6000));
                        p.frames.sendMessage(p, "You steal from the Silk Stall.");
                        p.addSkillXP(300 * p.skillLvl[17], 17);
                    }
                } else {
                    p.frames.sendMessage(p, "You need 97 Thieving to steal from this stall.");
                }
                break;
            case 34387:
                if (p.skillLvl[17] > 98) {
                    if (p.ActionTimer == 0) {
                        p.ActionTimer = 3;
                        p.requestAnim(881, 0);
                        Engine.playerItems.addItem(p, 995, 10000 + Misc.random(15000));
                        p.frames.sendMessage(p, "You steal from the Fur Stall.");
                        p.addSkillXP(300 * p.skillLvl[17], 17);
                    }
                } else {
                    p.frames.sendMessage(p, "You need 99 Thieving to steal from this stall.");
                }
                break;
//==================================end of thiefing stalls===========================================
            case 11666:
                p.frames.ItemOnInterface(p, 311, 4, 125, 2349);
                p.frames.ItemOnInterface(p, 311, 5, 125, 9467);
                p.frames.ItemOnInterface(p, 311, 6, 125, 2351);
                p.frames.ItemOnInterface(p, 311, 7, 125, 2353);
                p.frames.ItemOnInterface(p, 311, 8, 125, 2357);
                p.frames.ItemOnInterface(p, 311, 9, 125, 2355);
                p.frames.ItemOnInterface(p, 311, 10, 125, 2359);
                p.frames.ItemOnInterface(p, 311, 11, 125, 2361);
                p.frames.ItemOnInterface(p, 311, 12, 125, 2363);
                p.frames.showChatboxInterface(p, 311);
                break;
        }
    }

    private int objectSize(int id) {
        switch (id) {
            default:
                return 1;
        }
    }
}
