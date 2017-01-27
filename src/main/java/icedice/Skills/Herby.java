package icedice.Skills;

import icedice.players.items.PlayerItems;
import icedice.Engine;
import icedice.Config;
import icedice.players.Player;

/**
 * @author Kieran Anderson
 */

public class Herby {

    /**
     * Handles item on item packet.
     *
     * @param Player     p The player which the packet will be created for.
     * @param packetId   the packet id which is activated (Which handles this class)
     * @param packetSize the amount of bytes being received for the packet.
     */
    public void handlePacket(Player p, int itemUsed, int usedWith) {
        if (p == null)
            return;
        PlayerItems pi = new PlayerItems();
        int itemSlot1 = pi.getItemSlot(p, itemUsed);
        int itemSlot2 = pi.getItemSlot(p, usedWith);

        int getXpRate = p.xpLock == 0 ? 300 : 0;

        if (p.actionTimer == -1) {

            if ((itemUsed == 249 && usedWith == 227 || itemUsed == 227 && usedWith == 249) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                p.requestAnim(363, 0);
                Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                Engine.playerItems.addItem(p, 91, 1);
                p.frames.sendMessage(p, "You make a guam potion(unf)");
                p.addSkillXP(Config.Herblore_XP * 1.1 * Config.bonusXP, 15);
            } else if ((itemUsed == 251 && usedWith == 227 || itemUsed == 227 && usedWith == 251) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 5) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 93, 1);
                    p.frames.sendMessage(p, "You make a marrentill potion(unf).");
                    p.addSkillXP(Config.Herblore_XP * 1.2 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 5 herblore to do this.");
                }
            } else if ((itemUsed == 253 && usedWith == 227 || itemUsed == 227 && usedWith == 253) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 11) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 95, 1);
                    p.frames.sendMessage(p, "You make a tarromin potion(unf)");
                    p.addSkillXP(Config.Herblore_XP * 1.3 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 11 herblore to do this.");
                }
            } else if ((itemUsed == 255 && usedWith == 227 || itemUsed == 227 && usedWith == 255) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 20) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 97, 1);
                    p.frames.sendMessage(p, "You make a harralander potion(unf)");
                    p.addSkillXP(Config.Herblore_XP * 1.4 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 20 herblore to do this.");
                }
            } else if ((itemUsed == 257 && usedWith == 227 || itemUsed == 227 && usedWith == 257) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 25) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 99, 1);
                    p.frames.sendMessage(p, "You make a ranarr potion(unf)");
                    p.addSkillXP(Config.Herblore_XP * 1.5 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 25 herblore to do this.");
                }
            } else if ((itemUsed == 259 && usedWith == 227 || itemUsed == 227 && usedWith == 259) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 40) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 101, 1);
                    p.frames.sendMessage(p, "You make a irit potion(unf)");
                    p.addSkillXP(Config.Herblore_XP * 1.6 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 40 herblore to do this.");
                }
            } else if ((itemUsed == 261 && usedWith == 227 || itemUsed == 227 && usedWith == 261) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 48) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 103, 1);
                    p.frames.sendMessage(p, "You make a avantoe potion(unf)");
                    p.addSkillXP(Config.Herblore_XP * 1.7 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 48 herblore to do this.");
                }
            } else if ((itemUsed == 263 && usedWith == 227 || itemUsed == 227 && usedWith == 263) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 54) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 105, 1);
                    p.frames.sendMessage(p, "You make a kwuarm potion(unf)");
                    p.addSkillXP(Config.Herblore_XP * 1.8 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 54 herblore to do this.");
                }
            } else if ((itemUsed == 265 && usedWith == 227 || itemUsed == 227 && usedWith == 265) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 65) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 107, 1);
                    p.frames.sendMessage(p, "You make a cadantine potion(unf)");
                    p.addSkillXP(Config.Herblore_XP * 1.9 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 65 herblore to do this.");
                }
            } else if ((itemUsed == 267 && usedWith == 227 || itemUsed == 227 && usedWith == 267) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 70) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 109, 1);
                    p.frames.sendMessage(p, "You make a dwarf weed potion(unf)");
                    p.addSkillXP(Config.Herblore_XP * 2 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 70 herblore to do this.");
                }
            } else if ((itemUsed == 269 && usedWith == 227 || itemUsed == 227 && usedWith == 269) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 75) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 111, 1);
                    p.frames.sendMessage(p, "You make a torstol potion(unf)");
                    p.addSkillXP(Config.Herblore_XP * 2.1 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 75 herblore to do this.");
                }
            } else if ((itemUsed == 221 && usedWith == 91 || itemUsed == 91 && usedWith == 221) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                p.requestAnim(363, 0);
                Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                Engine.playerItems.addItem(p, 2428, 1);
                p.frames.sendMessage(p, "You make a attack potion");
                p.addSkillXP(Config.Herblore_XP * 1.1 * Config.bonusXP, 15);
            } else if ((itemUsed == 235 && usedWith == 93 || itemUsed == 93 && usedWith == 235) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 5) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2446, 1);
                    p.frames.sendMessage(p, "You make a antipoison");
                    p.addSkillXP(Config.Herblore_XP * 1.2 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 5 herblore to do this.");
                }
            } else if ((itemUsed == 225 && usedWith == 95 || itemUsed == 95 && usedWith == 225) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 12) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 113, 1);
                    p.frames.sendMessage(p, "You make a strength potion");
                    p.addSkillXP(Config.Herblore_XP * 1.3 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 12 herblore to do this.");
                }
            } else if ((itemUsed == 223 && usedWith == 96 || itemUsed == 96 && usedWith == 223) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 22) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2430, 1);
                    p.frames.sendMessage(p, "You make a restore potion");
                    p.addSkillXP(Config.Herblore_XP * 1.4 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 22 herblore to do this.");
                }
            } else if ((itemUsed == 239 && usedWith == 99 || itemUsed == 99 && usedWith == 239) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 30) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2432, 1);
                    p.frames.sendMessage(p, "You make a defence potion");
                    p.addSkillXP(Config.Herblore_XP * 1.5 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 30 herblore to do this.");
                }
            } else if ((itemUsed == 321 && usedWith == 99 || itemUsed == 99 && usedWith == 231) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 38) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2434, 1);
                    p.frames.sendMessage(p, "You make a prayer potion");
                    p.addSkillXP(Config.Herblore_XP * 1.6 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 38 herblore to do this.");
                }
            } else if ((itemUsed == 221 && usedWith == 101 || itemUsed == 101 && usedWith == 221) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 45) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2436, 1);
                    p.frames.sendMessage(p, "You make a super attack potion");
                    p.addSkillXP(Config.Herblore_XP * 1.7 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 45 herblore to do this.");
                }
            } else if ((itemUsed == 235 && usedWith == 101 || itemUsed == 101 && usedWith == 235) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 48) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2448, 1);
                    p.frames.sendMessage(p, "You make a super antipoison");
                    p.addSkillXP(Config.Herblore_XP * 1.8 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 48 herblore to do this.");
                }
            } else if ((itemUsed == 231 && usedWith == 103 || itemUsed == 103 && usedWith == 231) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 50) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2438, 1);
                    p.frames.sendMessage(p, "You make a fishing potion");
                    p.addSkillXP(Config.Herblore_XP * 1.9 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 50 herblore to do this.");
                }
            } else if ((itemUsed == 225 && usedWith == 105 || itemUsed == 105 && usedWith == 225) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 55) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2440, 1);
                    p.frames.sendMessage(p, "You make a super strength potion");
                    p.addSkillXP(Config.Herblore_XP * 2 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 55 herblore to do this.");
                }
            } else if ((itemUsed == 241 && usedWith == 105 || itemUsed == 105 && usedWith == 241) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 60) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 187, 1);
                    p.frames.sendMessage(p, "You make a weapon poison");
                    p.addSkillXP(Config.Herblore_XP * 2.1 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 60 herblore to do this.");
                }
            } else if ((itemUsed == 3000 && usedWith == 223 || itemUsed == 223 && usedWith == 3000) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 63) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 3024, 1);
                    p.frames.sendMessage(p, "You make a super restore potion");
                    p.addSkillXP(Config.Herblore_XP * 2.2 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 63 herblore to do this.");
                }
            } else if ((itemUsed == 239 && usedWith == 107 || itemUsed == 107 && usedWith == 239) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 66) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2442, 1);
                    p.frames.sendMessage(p, "You make a super defence potion");
                    p.addSkillXP(Config.Herblore_XP * 2.3 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 66 herblore to do this.");
                }
            } else if ((itemUsed == 245 && usedWith == 109 || itemUsed == 109 && usedWith == 245) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 72) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2444, 1);
                    p.frames.sendMessage(p, "You make a ranging potion");
                    p.addSkillXP(Config.Herblore_XP * 2.3 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 72 herblore to do this.");
                }
            } else if ((itemUsed == 247 && usedWith == 111 || itemUsed == 111 && usedWith == 247) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                if (p.skillLvl[15] >= 78) {
                    p.requestAnim(363, 0);
                    Engine.playerItems.deleteItem(p, itemUsed, itemSlot1, 1);
                    Engine.playerItems.deleteItem(p, usedWith, itemSlot2, 1);
                    Engine.playerItems.addItem(p, 2450, 1);
                    p.frames.sendMessage(p, "You make a zamorak brew");
                    p.addSkillXP(Config.Herblore_XP * 2.4 * Config.bonusXP, 15);
                } else {
                    p.frames.sendMessage(p, "You need level 78 herblore to do this.");
                }
            } else if ((itemUsed == 237 && usedWith == 233 || itemUsed == 233 && usedWith == 237) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                p.requestAnim(364, 0);
                Engine.playerItems.deleteItem(p, 237, pi.getItemSlot(p, 237), 1);
                Engine.playerItems.addItem(p, 235, 1);
                p.frames.sendMessage(p, "You make unicorn horn dust");
            } else if ((itemUsed == 243 && usedWith == 233 || itemUsed == 233 && usedWith == 243) && pi.HasItemAmount(p, itemUsed, 1) && pi.HasItemAmount(p, usedWith, 1)) {
                p.requestAnim(364, 0);
                Engine.playerItems.deleteItem(p, 243, pi.getItemSlot(p, 243), 1);
                Engine.playerItems.addItem(p, 241, 1);
                p.frames.sendMessage(p, "You make a dragon scale dust");
            } else {
                //System.out.println("Used with: " + usedWith + " itemUsed: " + itemUsed);
            }
            p.actionTimer = 4;
        }
    }
}