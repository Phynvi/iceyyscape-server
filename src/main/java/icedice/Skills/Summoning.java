/**
 * package icedice.Skills;
 * <p/>
 * public class Summoning {
 * <p/>
 * import icedice.Engine;
 * import icedice.players.Player;
 * import icedice.util.Misc;
 * import icedice.Config;
 * import icedice.io.packets.*;
 * <p/>
 * public void handlePacket(Player p, int packetId, int packetSize) {
 * public ItemOption2 itemOption2 = new ItemOption2();
 * if (p == null || p.stream == null) {
 * return;
 * }
 * <p/>
 * case 12175://Spirit Wolf
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12047, itemSlot, 1); // Summoning: Spirt Wolf
 * p.frames.sendMessage(p, "You summon a Spirit Wolf.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 6830, 663, 3);
 * Engine.newSummonNPC(6830, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 1.1, 23);
 * }
 * break;
 * case 12171://DreadFowl
 * if (p.skillLvl[23] > 3) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12043, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Dread Fowl");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 6826, 663, 3);
 * Engine.newSummonNPC(6826, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 1.2, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 4 summoning to summon that.");
 * }
 * break;
 * case 12187://Spirit Spider
 * if (p.skillLvl[23] > 9) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12059, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Spirit Spider");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 6842, 663, 3);
 * Engine.newSummonNPC(6842, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 1.3, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 10 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 11891://Thorny Snail
 * if (p.skillLvl[23] > 12) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12019, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Thorny snail.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 6807, 663, 3);
 * Engine.newSummonNPC(6807, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 1.4, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 13 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12936://Spirit Tz-Kih
 * if (p.skillLvl[23] > 21) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12808, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Spirit Tz-Kih.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 7362, 663, 3);
 * Engine.newSummonNPC(7362, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 1.5, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 22 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12201://Bronze Minotour
 * if (p.skillLvl[23] > 35) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12073, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Bronze Minotour.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 6854, 663, 3);
 * Engine.newSummonNPC(6854, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 1.6, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 36 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12203://Iron Minotour
 * if (p.skillLvl[23] > 45) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12075, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon an Iron Minotour.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 6856, 663, 3);
 * Engine.newSummonNPC(6856, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 1.7, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 46 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12205://Steel Minotour
 * if (p.skillLvl[23] > 55) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12077, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Steel Minotour.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 6858, 663, 3);
 * Engine.newSummonNPC(6858, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 1.8, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 56 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12207://Mithril Minotour
 * if (p.skillLvl[23] > 65) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12079, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Mithril Minotour.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 6860, 663, 3);
 * Engine.newSummonNPC(6860, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 1.9, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 66 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12209://Adamant Minotour
 * if (p.skillLvl[23] > 75) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12081, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Adamant Minotour.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 6862, 663, 3);
 * Engine.newSummonNPC(6862, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 2, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 76 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12211://Rune Minotour
 * if (p.skillLvl[23] > 85) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12083, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Rune Minotour.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 6864, 663, 3);
 * Engine.newSummonNPC(6864, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 2.1, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 86 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12930://Fire Titan
 * if (p.skillLvl[23] > 78) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12802, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Fire Titan.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 7356, 663, 3);
 * Engine.newSummonNPC(7356, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 2.2, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 79 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12932://Moss Titan
 * if (p.skillLvl[23] > 78) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12804, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Moss Titan.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 7358, 663, 3);
 * Engine.newSummonNPC(7358, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 2.3, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 79 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12934://Ice Titan
 * if (p.skillLvl[23] > 78) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12806, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Ice Titan.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 7360, 663, 3);
 * Engine.newSummonNPC(7360, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 2.4, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 79 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12660://Lava Titan
 * if (p.skillLvl[23] > 82) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12788, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Lava Titan.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 7342, 663, 3);
 * Engine.newSummonNPC(7342, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 2.5, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 83 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12648://Swamp Titan
 * if (p.skillLvl[23] > 84) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12776, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Swamp Titan.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 7330, 663, 3);
 * Engine.newSummonNPC(7330, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 2.6, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 85 summoning to summon that.");
 * }
 * break;
 * <p/>
 * <p/>
 * <p/>
 * case 12658://Geyser Titan
 * if (p.skillLvl[23] > 88) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12786, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Geyser Titan.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 7340, 663, 3);
 * Engine.newSummonNPC(7340, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 2.7, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 89 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12668://Abyssal Titan
 * if (p.skillLvl[23] > 92) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12796, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Abyssal Titan.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 7350, 663, 3);
 * Engine.newSummonNPC(7350, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 2.8, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 93 summoning to summon that.");
 * }
 * break;
 * <p/>
 * case 12950://Iron Titan
 * if (p.skillLvl[23] > 94) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12822, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon an Iron Titan.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 7376, 663, 3);
 * Engine.newSummonNPC(7376, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 2.9, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 95 summoning to summon that.");
 * }
 * break;
 * case 12662://Steel Titan
 * if (p.skillLvl[23] > 98) {
 * if(p.FamiliarID > 0)
 * {
 * p.frames.sendMessage(p, "You cannot summon more than one familiar at a time.");
 * }
 * else
 * {
 * Engine.playerItems.deleteItem(p, 12790, itemSlot, 1);
 * p.frames.sendMessage(p, "You summon a Steel Titan.");
 * p.frames.setTab(p, 80, 663);
 * p.frames.animateInterfaceId(p, 9850, 663, 3);
 * p.frames.setNPCId(p, 7344, 663, 3);
 * Engine.newSummonNPC(7344, p.absX, p.absY+1, p.heightLevel, p.absX + 1, p.absY + 1, p.absX + -1, p.absY + -1, false, p.playerId);
 * p.addSkillXP(Config.Summoning_XP * 3, 23);
 * }
 * }
 * else
 * {
 * p.frames.sendMessage(p, "You need at least level 99 summoning to summon that.");
 * }
 * break;
 * }
 * }
 */