package icedice.io.packets;

import icedice.Engine;
import icedice.players.items.PlayerItems;
import icedice.Config;
import icedice.players.Player;

/**
 * @author Encouragin <ZLyricale@live.nl>
 */

public class ItemOnItem implements Packet {

    /**
     * Handles item on item packet.
     *
     * @param Player     p The player which the packet will be created for.
     * @param packetId   the packet id which is activated (Which handles this class)
     * @param packetSize the amount of bytes being received for the packet.
     */
    public void handlePacket(Player player, int packetId, int packetSize) {
        if (player == null)
            return;
        /**
         * These are the correct stream methods
         * for item on item packet.
         */
        int usedWith = player.stream.readSignedWordBigEndian();
        int itemUsed = player.stream.readSignedWordA();
        PlayerItems pi = new PlayerItems();
        player.wc.resetWoodcutting();
        player.mi.resetMining();


        if (itemUsed == 227 || itemUsed == 249 || itemUsed == 251 || itemUsed == 253 || itemUsed == 255 || itemUsed == 257 || itemUsed == 259 || itemUsed == 263 || itemUsed == 265 || itemUsed == 267 || itemUsed == 269 ||
                itemUsed == 221 || itemUsed == 223 || itemUsed == 225 || itemUsed == 231 || itemUsed == 233 || itemUsed == 235 || itemUsed == 237 || itemUsed == 239 || itemUsed == 241 || itemUsed == 243 || itemUsed == 245 ||
                itemUsed == 247 || itemUsed == 3000 || itemUsed == 3026) {
            Engine.herby.handlePacket(player, itemUsed, usedWith);
        }

//======================================= CRAFTING =====================================

        if (itemUsed == 1755 && usedWith == 1623 || itemUsed == 1623 && usedWith == 1755) {
            player.requestAnim(888, 0);
            pi.deleteItem(player, 1623, pi.getItemSlot(player, 1623), 1);
            pi.addItem(player, 1607, 1);
            player.addSkillXP(Config.Crafting_XP * Config.sapphire * Config.bonusXP, 12);
            player.frames.sendMessage(player, "You cut the sapphire.");
        }

        if (itemUsed == 1755 && usedWith == 1621 || itemUsed == 1621 && usedWith == 1755) {
            if (player.skillLvl[12] >= 30) {
                player.requestAnim(889, 0);
                pi.deleteItem(player, 1621, pi.getItemSlot(player, 1621), 1);
                pi.addItem(player, 1605, 1);
                player.addSkillXP(Config.Crafting_XP * Config.emerald * Config.bonusXP, 12);
                player.frames.sendMessage(player, "You cut the emerald.");
            } else {
                player.frames.sendMessage(player, "You need level 30 crafting to cut this gem.");
            }
        }

        if (itemUsed == 1755 && usedWith == 1619 || itemUsed == 1619 && usedWith == 1755) {
            if (player.skillLvl[12] >= 50) {
                player.requestAnim(887, 0);
                pi.deleteItem(player, 1619, pi.getItemSlot(player, 1619), 1);
                pi.addItem(player, 1603, 1);
                player.addSkillXP(Config.Crafting_XP * Config.ruby * Config.bonusXP, 12);
                player.frames.sendMessage(player, "You cut the ruby.");
            } else {
                player.frames.sendMessage(player, "You need level 50 crafting to cut this gem.");
            }
        }

        if (itemUsed == 1755 && usedWith == 1617 || itemUsed == 1617 && usedWith == 1755) {
            if (player.skillLvl[12] >= 60) {
                player.requestAnim(886, 0);
                pi.deleteItem(player, 1617, pi.getItemSlot(player, 1617), 1);
                pi.addItem(player, 1601, 1);
                player.addSkillXP(Config.Crafting_XP * Config.diamond * Config.bonusXP, 12);
                player.frames.sendMessage(player, "You cut the diamond.");
            } else {
                player.frames.sendMessage(player, "You need level 60 crafting to cut this gem.");
            }
        }
        if (itemUsed == 1755 && usedWith == 1631 || itemUsed == 1631 && usedWith == 1755) {
            if (player.skillLvl[12] >= 75) {
                player.requestAnim(885, 0);
                pi.deleteItem(player, 1631, pi.getItemSlot(player, 1631), 1);
                pi.addItem(player, 1615, 1);
                player.addSkillXP(Config.Crafting_XP * Config.dragstone * Config.bonusXP, 12);
                player.frames.sendMessage(player, "You cut the dragonstone.");
            } else {
                player.frames.sendMessage(player, "You need level 75 crafting to cut this gem.");
            }
        }

        if (itemUsed == 1755 && usedWith == 6571 || itemUsed == 6571 && usedWith == 1755) {
            if (player.skillLvl[12] >= 85) {
                player.requestAnim(892, 0);
                pi.deleteItem(player, 6571, pi.getItemSlot(player, 6571), 1);
                pi.addItem(player, 6573, 1);
                player.addSkillXP(Config.Crafting_XP * Config.onyx * Config.bonusXP, 12);
                player.frames.sendMessage(player, "You cut the onyx stone.");
            } else {
                player.frames.sendMessage(player, "You need level 85 crafting to cut this gem.");
            }
        }

//=========================================Start of Armour Sets=========================================
        if (itemUsed == 1755 && usedWith == 11814 || itemUsed == 11814 && usedWith == 1755) {//bronze (l)
            pi.deleteItem(player, 11814, pi.getItemSlot(player, 11814), 1);
            pi.addItem(player, 1075, 1);//legs
            pi.addItem(player, 1117, 1);//body
            pi.addItem(player, 1155, 1);//helm
            pi.addItem(player, 8844, 1);//defender
            pi.addItem(player, 4119, 1);//boots
            pi.addItem(player, 12985, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Bronze Armour Set (l)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11816 || itemUsed == 11816 && usedWith == 1755) {//bronze (sk)
            pi.deleteItem(player, 11816, pi.getItemSlot(player, 11816), 1);
            pi.addItem(player, 1087, 1);//skirt
            pi.addItem(player, 1117, 1);//body
            pi.addItem(player, 1155, 1);//helm
            pi.addItem(player, 8844, 1);//defender
            pi.addItem(player, 4119, 1);//boots
            pi.addItem(player, 12985, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Bronze Armour Set (sk)");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11818 || itemUsed == 11818 && usedWith == 1755) {//iron (l)
            pi.deleteItem(player, 11818, pi.getItemSlot(player, 11818), 1);
            pi.addItem(player, 1067, 1);//legs
            pi.addItem(player, 1115, 1);//body
            pi.addItem(player, 1153, 1);//helm
            pi.addItem(player, 8845, 1);//defender
            pi.addItem(player, 4121, 1);//boots
            pi.addItem(player, 12988, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Iron Armour Set (l)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11820 || itemUsed == 11820 && usedWith == 1755) {//iron (sk)
            pi.deleteItem(player, 11820, pi.getItemSlot(player, 11820), 1);
            pi.addItem(player, 1081, 1);//skirt
            pi.addItem(player, 1115, 1);//body
            pi.addItem(player, 1153, 1);//helm
            pi.addItem(player, 8845, 1);//defender
            pi.addItem(player, 4121, 1);//boots
            pi.addItem(player, 12988, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Iron Armour Set (sk)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11822 || itemUsed == 11822 && usedWith == 1755) {//steel(l)
            pi.deleteItem(player, 11822, pi.getItemSlot(player, 11822), 1);
            pi.addItem(player, 1069, 1);//legs
            pi.addItem(player, 1119, 1);//body
            pi.addItem(player, 1157, 1);//helm
            pi.addItem(player, 8846, 1);//defender
            pi.addItem(player, 4123, 1);//boots
            pi.addItem(player, 12991, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Steel Armour Set (l)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11824 || itemUsed == 11824 && usedWith == 1755) {//steel(sk)
            pi.deleteItem(player, 11824, pi.getItemSlot(player, 11824), 1);
            pi.addItem(player, 1083, 1);//skirt
            pi.addItem(player, 1119, 1);//body
            pi.addItem(player, 1157, 1);//helm
            pi.addItem(player, 8846, 1);//defender
            pi.addItem(player, 4123, 1);//boots
            pi.addItem(player, 12991, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Steel Armour Set (sk)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11826 || itemUsed == 11826 && usedWith == 1755) {//black(l)
            pi.deleteItem(player, 11826, pi.getItemSlot(player, 11826), 1);
            pi.addItem(player, 1077, 1);//legs
            pi.addItem(player, 1125, 1);//body
            pi.addItem(player, 1165, 1);//helm
            pi.addItem(player, 8847, 1);//defender
            pi.addItem(player, 4125, 1);//boots
            pi.addItem(player, 12994, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Black Armour Set (l)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11828 || itemUsed == 11828 && usedWith == 1755) {//black(sk)
            pi.deleteItem(player, 11828, pi.getItemSlot(player, 11828), 1);
            pi.addItem(player, 1089, 1);//skirt
            pi.addItem(player, 1125, 1);//body
            pi.addItem(player, 1165, 1);//helm
            pi.addItem(player, 8847, 1);//defender
            pi.addItem(player, 4125, 1);//boots
            pi.addItem(player, 12994, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Black Armour Set (sk)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11830 || itemUsed == 11830 && usedWith == 1755) {//Mithril(l)
            pi.deleteItem(player, 11830, pi.getItemSlot(player, 11830), 1);
            pi.addItem(player, 1071, 1);//legs
            pi.addItem(player, 1109, 1);//chainbody
            pi.addItem(player, 1159, 1);//helm
            pi.addItem(player, 8848, 1);//defender
            pi.addItem(player, 4127, 1);//boots
            pi.addItem(player, 12997, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Mithril Armour Set (l)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11832 || itemUsed == 11832 && usedWith == 1755) {//Mithril(sk)
            pi.deleteItem(player, 11832, pi.getItemSlot(player, 11832), 1);
            pi.addItem(player, 1085, 1);//skirt
            pi.addItem(player, 1109, 1);//chainbody
            pi.addItem(player, 1159, 1);//helm
            pi.addItem(player, 8848, 1);//defender
            pi.addItem(player, 4127, 1);//boots
            pi.addItem(player, 12997, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Mithril Armour Set (sk)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11834 || itemUsed == 11834 && usedWith == 1755) {//Adamant(l)
            pi.deleteItem(player, 11834, pi.getItemSlot(player, 11834), 1);
            pi.addItem(player, 1073, 1);//legs
            pi.addItem(player, 1124, 1);//body
            pi.addItem(player, 1161, 1);//helm
            pi.addItem(player, 8849, 1);//defender
            pi.addItem(player, 4129, 1);//boots
            pi.addItem(player, 13000, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Adamant Armour Set (l)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11836 || itemUsed == 11836 && usedWith == 1755) {//Adamant(sk)
            pi.deleteItem(player, 11836, pi.getItemSlot(player, 11836), 1);
            pi.addItem(player, 1091, 1);//skirt
            pi.addItem(player, 1124, 1);//body
            pi.addItem(player, 1161, 1);//helm
            pi.addItem(player, 8849, 1);//defender
            pi.addItem(player, 4129, 1);//boots
            pi.addItem(player, 13000, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Adamant Armour Set (sk)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11838 || itemUsed == 11838 && usedWith == 1755) {//Rune(l)
            pi.deleteItem(player, 11838, pi.getItemSlot(player, 11838), 1);
            pi.addItem(player, 1079, 1);//legs
            pi.addItem(player, 1127, 1);//body
            pi.addItem(player, 1163, 1);//helm
            pi.addItem(player, 8850, 1);//defender
            pi.addItem(player, 4131, 1);//boots
            pi.addItem(player, 13003, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Rune Armour Set (l)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11840 || itemUsed == 11840 && usedWith == 1755) {//Rune(sk)
            pi.deleteItem(player, 11840, pi.getItemSlot(player, 11840), 1);
            pi.addItem(player, 1093, 1);//skirt
            pi.addItem(player, 1127, 1);//body
            pi.addItem(player, 1163, 1);//helm
            pi.addItem(player, 8850, 1);//defender
            pi.addItem(player, 4131, 1);//boots
            pi.addItem(player, 13003, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Rune Armour Set (sk)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11842 || itemUsed == 11842 && usedWith == 1755) {//Dragon(l)
            pi.deleteItem(player, 11842, pi.getItemSlot(player, 11842), 1);
            pi.addItem(player, 4087, 1);//legs
            pi.addItem(player, 1121, 1);//body
            pi.addItem(player, 11335, 1);//helm
            pi.addItem(player, 1189, 1);//kiteshield
            pi.addItem(player, 11732, 1);//boots
            pi.addItem(player, 13006, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Dragon Armour Set (l)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11844 || itemUsed == 11844 && usedWith == 1755) {//Dragon(sk)
            pi.deleteItem(player, 11844, pi.getItemSlot(player, 11844), 1);
            pi.addItem(player, 4585, 1);//skirt
            pi.addItem(player, 1121, 1);//body
            pi.addItem(player, 11335, 1);//helm
            pi.addItem(player, 1189, 1);//kiteshield
            pi.addItem(player, 11732, 1);//boots
            pi.addItem(player, 13006, 1);//gauntlets
            player.frames.sendMessage(player, "You open your Dragon Armour Set (sk)...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11904 || itemUsed == 11904 && usedWith == 1755) {//wizard set(t)
            pi.deleteItem(player, 11904, pi.getItemSlot(player, 11904), 1);
            pi.addItem(player, 7388, 1);//skirt
            pi.addItem(player, 7392, 1);//body
            pi.addItem(player, 7396, 1);//hat
            pi.addItem(player, 2579, 1);//boots
            pi.addItem(player, 2932, 1);//gloves
            player.frames.sendMessage(player, "You open your trimmed blue wizard set...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11906 || itemUsed == 11906 && usedWith == 1755) {//wizard set(g)
            pi.deleteItem(player, 11906, pi.getItemSlot(player, 11906), 1);
            pi.addItem(player, 7386, 1);//skirt
            pi.addItem(player, 7390, 1);//body
            pi.addItem(player, 7394, 1);//hat
            pi.addItem(player, 2579, 1);//boots
            pi.addItem(player, 2932, 1);//gloves
            player.frames.sendMessage(player, "You open your gold-trimmed blue wizard set...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11908 || itemUsed == 11908 && usedWith == 1755) {//leather set(t)
            pi.deleteItem(player, 11908, pi.getItemSlot(player, 11908), 1);
            pi.addItem(player, 7368, 1);//chaps
            pi.addItem(player, 7364, 1);//body
            pi.addItem(player, 1169, 1);//coif
            pi.addItem(player, 1061, 1);//boots
            pi.addItem(player, 10077, 1);//vambrace
            player.frames.sendMessage(player, "You open your trimmed leather armour set...");
            player.frames.sendMessage(player, "...You recive your items");
        }
        if (itemUsed == 1755 && usedWith == 11910 || itemUsed == 11910 && usedWith == 1755) {//leather set(g)
            pi.deleteItem(player, 11910, pi.getItemSlot(player, 11910), 1);
            pi.addItem(player, 7366, 1);//chaps
            pi.addItem(player, 7362, 1);//body
            pi.addItem(player, 1169, 1);//coif
            pi.addItem(player, 1061, 1);//boots
            pi.addItem(player, 10077, 1);//vambrace
            player.frames.sendMessage(player, "You open your gold-trimmed leather armour set...");
            player.frames.sendMessage(player, "...You recive your items");
        }
//=========================================end of amour sets=========================================
//=========================================Start of Godsword Making======================================
        if (itemUsed == 11702 && usedWith == 11690 || itemUsed == 11690 && usedWith == 11702) {
            pi.deleteItem(player, 11702, pi.getItemSlot(player, 11702), 1);
            pi.deleteItem(player, 11690, pi.getItemSlot(player, 11690), 1);
            pi.addItem(player, 11694, 1);
            player.frames.sendMessage(player, "You attach the Godsword Blade and Hilt together...");
            player.frames.sendMessage(player, "...and get an Armadyl Godsword!");
        }
        if (itemUsed == 11704 && usedWith == 11690 || itemUsed == 11690 && usedWith == 11704) {
            pi.deleteItem(player, 11704, pi.getItemSlot(player, 11704), 1);
            pi.deleteItem(player, 11690, pi.getItemSlot(player, 11690), 1);
            pi.addItem(player, 11696, 1);
            player.frames.sendMessage(player, "You attach the Godsword Blade and Hilt together...");
            player.frames.sendMessage(player, "...and get a Bandos Godsword!");
        }
        if (itemUsed == 11706 && usedWith == 11690 || itemUsed == 11690 && usedWith == 11706) {
            pi.deleteItem(player, 11706, pi.getItemSlot(player, 11706), 1);
            pi.deleteItem(player, 11690, pi.getItemSlot(player, 11690), 1);
            pi.addItem(player, 11698, 1);
            player.frames.sendMessage(player, "You attach the Godsword Blade and Hilt together...");
            player.frames.sendMessage(player, "...and get a Saradomin Godsword!");
        }
        if (itemUsed == 11708 && usedWith == 11690 || itemUsed == 11690 && usedWith == 11708) {
            pi.deleteItem(player, 11708, pi.getItemSlot(player, 11708), 1);
            pi.deleteItem(player, 11690, pi.getItemSlot(player, 11690), 1);
            pi.addItem(player, 11700, 1);
            player.frames.sendMessage(player, "You attach the Godsword Blade and Hilt together...");
            player.frames.sendMessage(player, "...and get a Zamorak Godsword!");
        }
        /*Shards + Shards*/
        if (itemUsed == 11710 && usedWith == 11712 || itemUsed == 11712 && usedWith == 11710) {
            pi.deleteItem(player, 11710, pi.getItemSlot(player, 11710), 1);
            pi.deleteItem(player, 11712, pi.getItemSlot(player, 11712), 1);
            pi.addItem(player, 11686, 1);
            player.frames.sendMessage(player, "You put Shard one and two together...");
            player.frames.sendMessage(player, "...You recive half a godsword blade.");
        }
        if (itemUsed == 11710 && usedWith == 11714 || itemUsed == 11714 && usedWith == 11710) {
            pi.deleteItem(player, 11710, pi.getItemSlot(player, 11710), 1);
            pi.deleteItem(player, 11714, pi.getItemSlot(player, 11714), 1);
            pi.addItem(player, 11688, 1);
            player.frames.sendMessage(player, "You put Shard one and three together...");
            player.frames.sendMessage(player, "...You recive half a godsword blade.");
        }
        if (itemUsed == 11712 && usedWith == 11714 || itemUsed == 11714 && usedWith == 11712) {
            pi.deleteItem(player, 11712, pi.getItemSlot(player, 11712), 1);
            pi.deleteItem(player, 11714, pi.getItemSlot(player, 11714), 1);
            pi.addItem(player, 11692, 1);
            player.frames.sendMessage(player, "You put Shard two and three together...");
            player.frames.sendMessage(player, "...You recive 3/4 a godsword blade.");
        }
        if (itemUsed == 11686 && usedWith == 11714 || itemUsed == 11714 && usedWith == 11686) {
            pi.deleteItem(player, 11686, pi.getItemSlot(player, 11686), 1);
            pi.deleteItem(player, 11714, pi.getItemSlot(player, 11714), 1);
            pi.addItem(player, 11690, 1);
            player.frames.sendMessage(player, "You put 3/4 of a gs blade and shard three together...");
            player.frames.sendMessage(player, "...You recive a godsword blade.");
        }
        if (itemUsed == 11688 && usedWith == 11712 || itemUsed == 11712 && usedWith == 11688) {
            pi.deleteItem(player, 11688, pi.getItemSlot(player, 11688), 1);
            pi.deleteItem(player, 11712, pi.getItemSlot(player, 11712), 1);
            pi.addItem(player, 11690, 1);
            player.frames.sendMessage(player, "You put 3/4 of a gs blade and shard two together...");
            player.frames.sendMessage(player, "...You recive a godsword blade.");
        }
        if (itemUsed == 11692 && usedWith == 11710 || itemUsed == 11710 && usedWith == 11692) {
            pi.deleteItem(player, 11692, pi.getItemSlot(player, 11692), 1);
            pi.deleteItem(player, 11710, pi.getItemSlot(player, 11710), 1);
            pi.addItem(player, 11690, 1);
            player.frames.sendMessage(player, "You put 3/4 of a gs blade and shard one together...");
            player.frames.sendMessage(player, "...You recive a godsword blade.");
        }
//=============================================End of Godsword Making===================================================


        if (itemUsed == 11551 && usedWith == 11552 || itemUsed == 11552 && usedWith == 11551) {
            if (player.skillLvl[13] >= 92) {
                player.requestAnim(898, 0);
                pi.deleteItem(player, 11551, pi.getItemSlot(player, 11551), 1);
                pi.deleteItem(player, 11552, pi.getItemSlot(player, 11552), 1);
                pi.addItem(player, 1121, 1);
                player.addSkillXP(250 * player.skillLvl[13], 13);
                player.frames.sendMessage(player, "You make a Dragon PlateBody.");
            } else {
                player.frames.sendMessage(player, "You need level 92 Smithing to create a Dragon PlateBody.");
            }
        }


        if (itemUsed == 11286 && usedWith == 1540 || itemUsed == 1540 && usedWith == 11286) {
            if (player.skillLvl[13] >= 92) {
                player.requestAnim(898, 0);
                pi.deleteItem(player, 11286, pi.getItemSlot(player, 11286), 1);
                pi.deleteItem(player, 1540, pi.getItemSlot(player, 1540), 1);
                pi.addItem(player, 11283, 1);
                player.addSkillXP(250 * player.skillLvl[13], 13);
                player.frames.sendMessage(player, "You make a Dragonfire Shield.");
            } else {
                player.frames.sendMessage(player, "You need level 92 Smithing to create a Dragonfire Shield.");
            }
        }


        if (itemUsed == 2366 && usedWith == 2368 || itemUsed == 2368 && usedWith == 2366) {
            pi.deleteItem(player, 2366, pi.getItemSlot(player, 2366), 1);
            pi.deleteItem(player, 2368, pi.getItemSlot(player, 2368), 1);
            pi.addItem(player, 1187, 1);
        }

// ====================================== FLETCHING ==================================

        if (itemUsed == 946 && usedWith == 1511 || itemUsed == 1511 && usedWith == 946) {
            player.FletchID = 1511;
            player.FletchGet = 50;
            player.FletchXP = 50;
            player.FletchAmount = 28;
            player.FletchThat(player, Config.Feltching_XP, player.FletchID, player.FletchGet);

        }

        if (itemUsed == 946 && usedWith == 1521 || itemUsed == 1521 && usedWith == 946) {
            if (player.skillLvl[9] >= 15) {
                player.FletchID = 1521;
                player.FletchGet = 54;
                player.FletchXP = 75;
                player.FletchAmount = 28;
                player.FletchThat(player, Config.Feltching_XP, player.FletchID, player.FletchGet);
            } else {
                player.frames.sendMessage(player, "You need level 15 fletching to cut this log.");
            }
        }
        if (itemUsed == 946 && usedWith == 1519 || itemUsed == 1519 && usedWith == 946) {
            if (player.skillLvl[9] >= 30) {
                player.FletchID = 1519;
                player.FletchGet = 60;
                player.FletchXP = 100;
                player.FletchAmount = 28;
                player.FletchThat(player, Config.Feltching_XP, player.FletchID, player.FletchGet);
            } else {
                player.frames.sendMessage(player, "You need level 30 fletching to cut this log.");
            }
        }

        if (itemUsed == 946 && usedWith == 1517 || itemUsed == 1517 && usedWith == 946) {
            if (player.skillLvl[9] >= 45) {
                player.FletchID = 1517;
                player.FletchGet = 64;
                player.FletchXP = 150;
                player.FletchAmount = 28;
                player.FletchThat(player, Config.Feltching_XP, player.FletchID, player.FletchGet);
            } else {
                player.frames.sendMessage(player, "You need level 45 fletching to cut this log.");
            }
        }

        if (itemUsed == 946 && usedWith == 1515 || itemUsed == 1515 && usedWith == 946) {
            if (player.skillLvl[9] >= 65) {
                player.FletchID = 1515;
                player.FletchGet = 68;
                player.FletchXP = 200;
                player.FletchAmount = 28;
                player.FletchThat(player, Config.Feltching_XP, player.FletchID, player.FletchGet);
            } else {
                player.frames.sendMessage(player, "You need level 65 fletching to cut this log.");
            }
        }
        if (itemUsed == 946 && usedWith == 1513 || itemUsed == 1513 && usedWith == 946) {
            if (player.skillLvl[9] >= 75) {
                player.FletchID = 1513;
                player.FletchGet = 72;
                player.FletchXP = 250;
                player.FletchAmount = 28;
                player.FletchThat(player, Config.Feltching_XP, player.FletchID, player.FletchGet);
            } else {
                player.frames.sendMessage(player, "You need level 75 fletching to cut this log.");
            }
        }
//============================= FIRE MAKING ====================================
        if (itemUsed == 590 && usedWith == 1511 || itemUsed == 1511 && usedWith == 590) {


            player.addSkillXP(10 * player.skillLvl[11], 11);
            player.requestAnim(733, 0);
            player.frames.createGlobalObject(2732, player.heightLevel, player.absX, player.absY, 0, 10);
            player.objectX = player.absX;
            player.objectY = player.absY;
            player.objectHeight = player.heightLevel;
            pi.deleteItem(player, 1511, pi.getItemSlot(player, 1511), 1);
            player.frames.sendMessage(player, "You set the logs on fire.");
            // fmwalk(player, player.absY, player.absY);
            player.firedelay = 50;
        }
        if (itemUsed == 590 && usedWith == 1521 || itemUsed == 1521 && usedWith == 590) {
            if (player.skillLvl[11] >= 15) {
                player.addSkillXP(30 * player.skillLvl[11], 11);
                player.requestAnim(733, 0);
                player.frames.createGlobalObject(2732, player.heightLevel, player.absX, player.absY, 0, 10);
                player.objectX = player.absX;
                player.objectY = player.absY;
                player.objectHeight = player.heightLevel;
                pi.deleteItem(player, 1521, pi.getItemSlot(player, 1521), 1);
                player.frames.sendMessage(player, "You set the logs on fire.");
                // fmwalk(player, player.absY, player.absY);
                player.firedelay = 50;
            }
        }
        if (itemUsed == 590 && usedWith == 1519 || itemUsed == 1519 && usedWith == 590) {
            if (player.skillLvl[11] >= 30) {
                player.addSkillXP(40 * player.skillLvl[11], 11);
                player.requestAnim(733, 0);
                player.frames.createGlobalObject(2732, player.heightLevel, player.absX, player.absY, 0, 10);
                player.objectX = player.absX;
                player.objectY = player.absY;
                player.objectHeight = player.heightLevel;
                pi.deleteItem(player, 1519, pi.getItemSlot(player, 1519), 1);
                player.frames.sendMessage(player, "You set the logs on fire.");
                // fmwalk(player, player.absY, player.absY);
                player.firedelay = 50;
            }
        }
        if (itemUsed == 590 && usedWith == 1517 || itemUsed == 1517 && usedWith == 590) {
            if (player.skillLvl[11] >= 45) {
                player.addSkillXP(50 * player.skillLvl[11], 11);
                player.requestAnim(733, 0);
                player.frames.createGlobalObject(2732, player.heightLevel, player.absX, player.absY, 0, 10);
                player.objectX = player.absX;
                player.objectY = player.absY;
                player.objectHeight = player.heightLevel;
                pi.deleteItem(player, 1517, pi.getItemSlot(player, 1517), 1);
                player.frames.sendMessage(player, "You set the logs on fire.");
                // fmwalk(player, player.absY, player.absY);
                player.firedelay = 50;
            }
        }
        if (itemUsed == 590 && usedWith == 1515 || itemUsed == 1515 && usedWith == 590) {
            if (player.skillLvl[11] >= 60) {
                player.addSkillXP(75 * player.skillLvl[11], 11);
                player.requestAnim(733, 0);
                player.frames.createGlobalObject(2732, player.heightLevel, player.absX, player.absY, 0, 10);
                player.objectX = player.absX;
                player.objectY = player.absY;
                player.objectHeight = player.heightLevel;
                pi.deleteItem(player, 1515, pi.getItemSlot(player, 1515), 1);
                player.frames.sendMessage(player, "You set the logs on fire.");
                // fmwalk(player, player.absY, player.absY);
                player.firedelay = 50;
            }
        }
        if (itemUsed == 590 && usedWith == 1513 || itemUsed == 1513 && usedWith == 590) {
            if (player.skillLvl[11] >= 75) {
                player.addSkillXP(100 * player.skillLvl[11], 11);
                player.requestAnim(733, 0);
                player.frames.createGlobalObject(2732, player.heightLevel, player.absX, player.absY, 0, 10);
                player.objectX = player.absX;
                player.objectY = player.absY;
                player.objectHeight = player.heightLevel;
                pi.deleteItem(player, 1513, pi.getItemSlot(player, 1513), 1);
                player.frames.sendMessage(player, "You set the logs on fire.");
                // fmwalk(player, player.absY, player.absY);
                player.firedelay = 50;
            }
        }
        if (itemUsed == 7329 && usedWith == 1511 || itemUsed == 1511 && usedWith == 7329) { // red
            pi.deleteItem(player, 7329, pi.getItemSlot(player, 7329), 1);
            pi.deleteItem(player, 1511, pi.getItemSlot(player, 1511), 1);
            pi.addItem(player, 7404, 1);
            player.frames.sendMessage(player, "You rub the firelighter on the logs to make red logs.");
        }
        if (itemUsed == 7330 && usedWith == 1511 || itemUsed == 1511 && usedWith == 7330) { // green
            pi.deleteItem(player, 7330, pi.getItemSlot(player, 7330), 1);
            pi.deleteItem(player, 1511, pi.getItemSlot(player, 1511), 1);
            pi.addItem(player, 7405, 1);
            player.frames.sendMessage(player, "You rub the firelighter on the logs to make green logs.");
        }
        if (itemUsed == 7331 && usedWith == 1511 || itemUsed == 1511 && usedWith == 7331) { // blue
            pi.deleteItem(player, 7331, pi.getItemSlot(player, 7331), 1);
            pi.deleteItem(player, 1511, pi.getItemSlot(player, 1511), 1);
            pi.addItem(player, 7406, 1);
            player.frames.sendMessage(player, "You rub the firelighter on the logs to make blue logs.");
        }
        if (itemUsed == 10326 && usedWith == 1511 || itemUsed == 1511 && usedWith == 10326) { // purple
            pi.deleteItem(player, 10326, pi.getItemSlot(player, 10326), 1);
            pi.deleteItem(player, 1511, pi.getItemSlot(player, 1511), 1);
            pi.addItem(player, 10329, 1);
            player.frames.sendMessage(player, "You rub the firelighter on the logs to make purple logs.");
        }
        if (itemUsed == 10327 && usedWith == 1511 || itemUsed == 1511 && usedWith == 10327) { // white
            pi.deleteItem(player, 10327, pi.getItemSlot(player, 10327), 1);
            pi.deleteItem(player, 1511, pi.getItemSlot(player, 1511), 1);
            pi.addItem(player, 10328, 1);
            player.frames.sendMessage(player, "You rub the firelighter on the logs to make white logs.");
        }

        if (itemUsed == 590 && usedWith == 7404 || itemUsed == 7404 && usedWith == 590) { // red
            player.addSkillXP(50, 11);
            player.requestAnim(733, 0);
            player.frames.createGlobalObject(11404, player.heightLevel, player.absX, player.absY, 0, 10);
            player.objectX = player.absX;
            player.objectY = player.absY;
            player.objectHeight = player.heightLevel;
            pi.deleteItem(player, 7404, pi.getItemSlot(player, 7404), 1);
            player.frames.sendMessage(player, "You set the logs on fire.");
            // fmwalk(player, player.absY, player.absY);
            player.firedelay = 100;
        }
        if (itemUsed == 590 && usedWith == 7405 || itemUsed == 7405 && usedWith == 590) { // green
            player.addSkillXP(50, 11);
            player.requestAnim(733, 0);
            player.frames.createGlobalObject(11405, player.heightLevel, player.absX, player.absY, 0, 10);
            player.objectX = player.absX;
            player.objectY = player.absY;
            player.objectHeight = player.heightLevel;
            pi.deleteItem(player, 7405, pi.getItemSlot(player, 7405), 1);
            player.frames.sendMessage(player, "You set the logs on fire.");
            // fmwalk(player, player.absY, player.absY);
            player.firedelay = 100;
        }
        if (itemUsed == 590 && usedWith == 7406 || itemUsed == 7406 && usedWith == 590) { // blue
            player.addSkillXP(50, 11);
            player.requestAnim(733, 0);
            player.frames.createGlobalObject(11406, player.heightLevel, player.absX, player.absY, 0, 10);
            player.objectX = player.absX;
            player.objectY = player.absY;
            player.objectHeight = player.heightLevel;
            pi.deleteItem(player, 7406, pi.getItemSlot(player, 7406), 1);
            player.frames.sendMessage(player, "You set the logs on fire.");
            // fmwalk(player, player.absY, player.absY);
            player.firedelay = 100;
        }
        if (itemUsed == 590 && usedWith == 10329 || itemUsed == 10329 && usedWith == 590) { // blue
            player.addSkillXP(50, 11);
            player.requestAnim(733, 0);
            player.frames.createGlobalObject(20001, player.heightLevel, player.absX, player.absY, 0, 10);
            player.objectX = player.absX;
            player.objectY = player.absY;
            player.objectHeight = player.heightLevel;
            pi.deleteItem(player, 10329, pi.getItemSlot(player, 10329), 1);
            player.frames.sendMessage(player, "You set the logs on fire.");
            // fmwalk(player, player.absY, player.absY);
            player.firedelay = 100;
        }
        if (itemUsed == 590 && usedWith == 10328 || itemUsed == 10328 && usedWith == 590) { // blue
            player.addSkillXP(50, 11);
            player.requestAnim(733, 0);
            player.frames.createGlobalObject(20000, player.heightLevel, player.absX, player.absY, 0, 10);
            player.objectX = player.absX;
            player.objectY = player.absY;
            player.objectHeight = player.heightLevel;
            pi.deleteItem(player, 10328, pi.getItemSlot(player, 10328), 1);
            player.frames.sendMessage(player, "You set the logs on fire.");
            // fmwalk(player, player.absY, player.absY);
            player.firedelay = 100;
        }
        //System.out.println("Used with: "+usedWith+" itemUsed: "+itemUsed);
    }

    public void fmwalk(Player p, int x, int y) {
        int firstX = x - (p.mapRegionX - 6) * 8;
        int firstY = y - (p.mapRegionY - 6) * 8;
        Engine.playerMovement.resetWalkingQueue(p);
        Engine.playerMovement.addStepToWalkingQueue(firstX - 1, firstY, p);
    }

}