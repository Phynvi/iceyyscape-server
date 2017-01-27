
package icedice.players.combat;


import icedice.Engine;
import icedice.players.*;
import icedice.players.items.PlayerWeapon;
import icedice.util.Misc;
import icedice.npcs.NPC;
import icedice.Config;

public class PlayerNPCCombat {

    public int ranLow = 0;

    private int getXpRate(Player p) {
        return p.xpLock == 0 ? 125 : 0;
    }

    public void attackNPC(Player p) {
        try {
            NPC n = Engine.npcs[p.attackNPC];
            String weapon = Engine.items.getItemName(p.equipment[3]);
            int hitDamage = Misc.random(maxMeleeHit(p));
            int hitDamage2 = Misc.random(hitDamage);

            /**ranLow = Misc.random(2);

             if (ranLow == 0) {
             Engine.items.createGroundItem(Config.lowFood(), Misc.random(3), p.absX,
             p.absY, p.heightLevel, p.username);
             }
             if (ranLow == 1) {
             Engine.items.createGroundItem(Config.lowItems(), 1, p.absX,
             p.absY, p.heightLevel, p.username);
             }
             if (ranLow == 2) {
             Engine.items.createGroundItem(Config.lowMulti(), Misc.random(150), p.absX,
             p.absY, p.heightLevel, p.username);
             }*/

            if (p.attackNPC <= 0 || p.attackNPC >= Engine.npcs.length || n == null || n.isDead || p.isDead) {
                resetAttack(p);
            }

            int xOffset = (p.absX - n.absX);
            int yOffset = (p.absY - n.absY);
            int heightLevel = (p.absY - n.absY);


            /** Begin Npc Drops
             if(n.isDead == true)
             {
             if(n.npcType == 1)
             {
             Engine.items.createGroundItem(526, 1, p.absX,
             p.absY, p.heightLevel, p.username);
             Engine.items.createGroundItem(ranLow, 1, p.absX,
             p.absY, p.heightLevel, p.username);
             }
             }*/

            if (n.isDead == true) {
                if (n.npcType == 742 && p.DragonSlayer == 3) {
                    p.HeadTimer = 8;
                    p.DragonSlayer = 4;
                    Engine.playerItems.addItem(p, 11279, 1);
                    p.frames.sendMessage(p, "You slayed Elvarg and took his head!");
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 1619) || (n.npcType == 49) || (n.npcType == 6219)) {
                    p.zkc = (p.zkc + 1);
                    p.frames.sendMessage(p, "You now have a " + (p.zkc) + " Zamorak kill count!");
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 2745)) {
                    p.firecape = (p.firecape = 1);
                    Engine.playerItems.addItem(p, 6570, 1);
                    p.frames.sendMessage(p, "Congrats on killing the jad! You diserve this firecape!");
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 6144)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 6143)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }


            if (n.isDead == true) {
                if ((n.npcType == 3773)) {
                    p.pestpoints = (p.pestpoints += 2);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3772)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3734)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3732)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3743)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3742)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3764)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3762)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3728)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3727)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3748)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3747)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3754)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 3752)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 6142)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 6145)) {
                    p.pestpoints = (p.pestpoints += 1);
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 6255) || (n.npcType == 6254)) {
                    p.skc = (p.skc + 1);
                    p.frames.sendMessage(p, "You now have a " + (p.skc) + " Saradomin kill count!");
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 7158) || (n.npcType == 7159) || (n.npcType == 7160)) {
                    p.sdc = (p.sdc + 1);
                    p.frames.sendMessage(p, "You now have a " + (p.sdc) + " Safety Dungeon Count!");
                }
            }

            if (n.isDead == true) {
                if ((n.npcType == 6275) || (n.npcType == 6277) || (n.npcType == 6270)) {
                    p.bkc = (p.bkc + 1);
                    p.frames.sendMessage(p, "You now have a " + (p.bkc) + " Bandos kill count!");
                }
            }


            if (n.isDead == true) {
                if ((n.npcType == 6232) || (n.npcType == 6229)) {
                    p.akc = (p.akc + 1);
                    p.frames.sendMessage(p, "You now have a " + (p.akc) + " Armadyl kill count!");
                }
            }

            if (n.isDead == true && p.SlayerAm > 0) {
                if (p.SlayerTask == 0 && n.npcType == 941) {
                    p.SlayerAm -= 1;
                    p.addSkillXP(150 * p.skillLvl[18], 18);
                }
                if (p.SlayerTask == 0 && n.npcType == 55) {
                    p.SlayerAm -= 1;
                    p.addSkillXP(150 * p.skillLvl[18], 18);
                }
                if (p.SlayerTask == 0 && n.npcType == 53) {
                    p.SlayerAm -= 1;
                    p.addSkillXP(150 * p.skillLvl[18], 18);
                }
                if (p.SlayerTask == 0 && n.npcType == 5363) {
                    p.SlayerAm -= 1;
                    p.addSkillXP(500 * p.skillLvl[18], 18);
                }
                if (p.SlayerTask == 1 && n.npcType == 9) {
                    p.SlayerAm -= 1;
                    p.addSkillXP(250 * p.skillLvl[18], 18);
                }
                if (p.SlayerTask == 2 && n.npcType == 110 || p.SlayerTask == 2 && n.npcType == 111 || p.SlayerTask == 2 && n.npcType == 112) {
                    p.SlayerAm -= 1;
                    p.addSkillXP(250 * p.skillLvl[18], 18);
                }
                if (p.SlayerTask == 3 && n.npcType == 4387 || p.SlayerTask == 3 && n.npcType == 6998) {
                    p.SlayerAm -= 1;
                    p.addSkillXP(400 * p.skillLvl[18], 18);
                }
                if (p.SlayerTask == 4 && n.npcType == 21) {
                    p.SlayerAm -= 1;
                    p.addSkillXP(250 * p.skillLvl[18], 18);
                }
            }

            if (p.combatDelay > 0) {
                return;
            }
            if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) >= 1 && UsingABow(p.equipment[3])) {
                p.stopMovement(p);
                if (hasArrows(p.equipment[13])) {

                    if (p.usingSpecial) {

                        switch (p.equipment[3]) {

                            case 859:
                                if (p.specialAmount >= 40) {
                                    hitDamage = Misc.random(p.skillLvl[4] / 4);
                                    p.specialAmount -= 40;
                                    p.usingSpecial = false;
                                    p.requestGFX(472, 0);
                                    p.frames.setConfig(p, 301, 0);
                                    p.requestGFX(fetchArrowBack(p.equipment[13]), 100);
                                } else {
                                    p.frames.sendMessage(p, "You do not have enough special to do this!");
                                    p.usingSpecial = false;
                                    p.frames.setConfig(p, 301, 0);
                                }
                                break;

                            case 861:
                                if (p.specialAmount >= 40) {
                                    hitDamage = Misc.random(p.skillLvl[4] / 4);
                                    p.specialAmount -= 40;
                                    p.requestGFX(472, 0);
                                    p.usingSpecial = false;
                                    p.frames.setConfig(p, 301, 0);
                                    p.requestGFX(fetchArrowBack(p.equipment[13]), 100);
                                } else {
                                    p.frames.sendMessage(p, "You do not have enough special to do this!");
                                    p.usingSpecial = false;
                                    p.frames.setConfig(p, 301, 0);
                                }
                                break;

                            case 6724:
                                if (p.specialAmount >= 75) {
                                    hitDamage = Misc.random((int) (p.skillLvl[4] / 3.5));
                                    p.specialAmount -= 73;
                                    p.requestGFX(472, 0);
                                    p.usingSpecial = false;
                                    p.frames.setConfig(p, 301, 0);
                                    p.requestGFX(fetchArrowBack(p.equipment[13]), 100);
                                } else {
                                    p.frames.sendMessage(p, "You do not have enough special to do this!");
                                    p.usingSpecial = false;
                                    p.frames.setConfig(p, 301, 0);
                                }
                                break;

                            case 11235:
                                if (p.specialAmount >= 75) {
                                    hitDamage = Misc.random(p.skillLvl[4] / 3);
                                    n.appendHit(Misc.random(hitDamage), 0);
                                    p.specialAmount -= 75;
                                    p.usingSpecial = false;
                                    p.frames.setConfig(p, 301, 0);
                                    p.requestGFX(fetchArrowBack(p.equipment[13]), 100);
                                } else {
                                    p.frames.sendMessage(p, "You do not have enough special to do this!");
                                    p.usingSpecial = false;
                                    p.frames.setConfig(p, 301, 0);
                                }
                                break;

                            default:
                                p.usingSpecial = false;
                                p.frames.setConfig(p, 301, 0);
                                break;
                        }

                    } else {
                        hitDamage = p.skillLvl[4] / 10;

                        if (p.equipment[3] == 859 || p.equipment[3] == 861) {
                            hitDamage = hitDamage + 6;
                        }
                        if (p.equipment[3] == 6724 || p.equipment[3] == 11235) {
                            hitDamage = hitDamage + 7;
                        }
                        if (p.equipment[3] == 4212 || p.equipment[3] == 4212 || p.equipment[3] == 4214 || p.equipment[3] == 4215
                                || p.equipment[3] == 4216 || p.equipment[3] == 4217 || p.equipment[3] == 4218 || p.equipment[3] == 4219
                                || p.equipment[3] == 4220 || p.equipment[3] == 4221 || p.equipment[3] == 4222 || p.equipment[3] == 4223) {
                            hitDamage = hitDamage + 5;
                        }
                        if (p.equipment[3] == 855 || p.equipment[3] == 857) {
                            hitDamage = hitDamage + 4;
                        }
                        if (p.equipment[3] == 851 || p.equipment[3] == 853) {
                            hitDamage = hitDamage + 3;
                        }
                        if (p.equipment[3] == 847 || p.equipment[3] == 849) {
                            hitDamage = hitDamage + 2;
                        }
                        if (p.equipment[3] == 843 || p.equipment[3] == 845) {
                            hitDamage = hitDamage + 1;
                        }

                        if (p.equipment[13] == 882) {
                            hitDamage = hitDamage + 7;
                        }
                        if (p.equipment[13] == 883) {
                            hitDamage = hitDamage + 7;
                        }
                        if (p.equipment[13] == 884) {
                            hitDamage = hitDamage + 6;
                        }
                        if (p.equipment[13] == 885) {
                            hitDamage = hitDamage + 6;
                        }
                        if (p.equipment[13] == 886) {
                            hitDamage = hitDamage + 5;
                        }
                        if (p.equipment[13] == 887) {
                            hitDamage = hitDamage + 5;
                        }
                        if (p.equipment[13] == 888) {
                            hitDamage = hitDamage + 4;
                        }
                        if (p.equipment[13] == 889) {
                            hitDamage = hitDamage + 4;
                        }
                        if (p.equipment[13] == 890) {
                            hitDamage = hitDamage + 3;
                        }
                        if (p.equipment[13] == 891) {
                            hitDamage = hitDamage + 3;
                        }
                        if (p.equipment[13] == 892) {
                            hitDamage = hitDamage + 2;
                        }
                        if (p.equipment[13] == 893) {
                            hitDamage = hitDamage + 2;
                        }
                        if (p.equipment[13] == 78) {
                            hitDamage = hitDamage + 8;
                        }
                        hitDamage = Misc.random(hitDamage);
                        p.requestAnim(426, 0);
                        p.requestGFX(fetchArrowBack(p.equipment[13]), 100);
                        p.combatDelay = p.attackDelay;
                        p.requestFaceTo(n.npcId);

                    }

                    n.appendHit(hitDamage, 0);
                    p.addSkillXP(4 * (hitDamage * 65), 4);
                    p.addSkillXP(2 * (hitDamage * 65), 3);
                    p.equipmentN[13] = p.equipmentN[13] - 1;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                    Engine.items.createGroundItem(p.equipment[13], 1, n.absX, n.absY, n.heightLevel, p.username);
                    n.requestAnim(424, 0);
                    n.requestFaceTo(p.playerId + 32768);
                    if (!n.attackingPlayer) {
                        n.attackingPlayer = true;
                        n.attackPlayer = p.playerId;
                    }

                } else {
                    p.frames.sendMessage(p, "You need some arrows to attack a player");
                    resetAttack(p);
                }
            } else if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) <= 1 && !UsingABow(p.equipment[3])) {
                if (!p.usingSpecial) {
                    p.requestAnim(p.attackEmote, 0);
                } else if (p.usingSpecial) {
                    p.frames.setConfig(p, 301, 0);
                    if (p.equipment[3] == 11694 && p.specialAmount >= 50) { // Armadyl godsword.
                        hitDamage = Misc.random((int) (maxMeleeHit(p) * 1.25));
                        p.usingSpecial = false;
                        p.specialAmount -= 50;
                        p.requestAnim(7074, 0);
                        p.requestGFX(1222, 0);
                    } else if (p.equipment[3] == 7158 && p.specialAmount >= 50) { // Dragon godsword.
                        hitDamage = Misc.random((int) (maxMeleeHit(p) * 0.9));
                        p.usingSpecial = false;
                        p.specialAmount -= 50;
                        p.requestAnim(3157, 0);
                        n.requestGFX(559, 0);
                    } else if (p.equipment[3] == 861 && p.specialAmount == 50) { // Magic Shortbow
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = true;
                        p.specialAmount -= 50;
                        p.requestAnim(426, 0);
                        p.requestGFX(472, 0);
                    } else if (p.equipment[3] == 11730 && p.specialAmount >= 100) { // Saradomin Sword
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = true;
                        p.specialAmount -= 100;
                        p.requestAnim(7072, 0);
                        p.requestGFX(1224, 100);
                        n.requestGFX(1194, 100);
                        n.appendHit(hitDamage2, 0);
                    } else if (p.equipment[3] == 11696 && p.specialAmount == 100) { // Bandos godsword.
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = false;
                        p.specialAmount -= 100;
                        p.requestAnim(7073, 0);
                        p.requestGFX(1223, 0);
                    } else if (p.equipment[3] == 3101 && p.specialAmount >= 50) {   //Dragon Claws.
                        hitDamage = Misc.random((int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = false;
                        p.secHit2 = hitDamage / 2;
                        p.thirdHit2 = p.secHit2 / 2;
                        if (p.thirdHit2 != 0) {
                            p.fourHit2 = p.thirdHit2 - 1;
                        } else {
                            p.fourHit2 = 0;
                        }
                        n.appendHit(p.secHit2, 0);
                        p.clawTimer2 = 1;
                        p.UseClaws2 = true;
                        p.specialAmount -= 50;
                        p.requestAnim(2876, 0);
                        p.requestGFX(333, 100);
                    } else if (p.equipment[3] == 1337 && p.specialAmount >= 50) {   //new age Dragon Claws.
                        hitDamage = Misc.random((int) (maxMeleeHit(p) * 1.7));
                        p.usingSpecial = false;
                        p.secHit2 = hitDamage / 2;
                        p.thirdHit2 = p.secHit2 / 2;
                        if (p.thirdHit2 != 0) {
                            p.fourHit2 = p.thirdHit2 - 1;
                        } else {
                            p.fourHit2 = 0;
                        }
                        n.appendHit(p.secHit2, 0);
                        p.clawTimer2 = 1;
                        p.UseClaws2 = true;
                        p.specialAmount -= 50;
                        p.requestAnim(2876, 0);
                        p.requestGFX(333, 100);
                    } else if (p.equipment[3] == 11698 && p.specialAmount >= 50) { // Saradomin godsword.
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = false;
                        p.specialAmount -= 50;
                        p.requestAnim(7071, 0);
                        p.requestGFX(1220, 0);
                        p.updateHP((hitDamage / 2), true);
                        p.updatePRAY((hitDamage / 4), true);
                    } else if (p.equipment[3] == 11700 && p.specialAmount >= 75) { // Zamorak godsword.
                        hitDamage = Misc.random((int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = false;
                        p.specialAmount -= 50;
                        p.requestAnim(7070, 0);
                        p.requestGFX(1221, 0);
                    } else if (p.equipment[3] == 4151 && p.specialAmount >= 50) { // Abyssal whip.
                        hitDamage = Misc.random((int) (maxMeleeHit(p) * 0.9));
                        p.usingSpecial = false;
                        p.specialAmount -= 50;
                        p.requestAnim(1658, 0);
                        n.requestGFX(341, 100);
                    } else if (p.equipment[3] == 13636 && p.specialAmount >= 50) { // bandos whip.
                        hitDamage = Misc.random((int) (maxMeleeHit(p) * 1.9));
                        p.usingSpecial = false;
                        p.specialAmount -= 50;
                        p.requestAnim(1658, 0);
                        n.requestGFX(451, 0);
                        p.requestGFX(441, 0);
                    } else if (p.equipment[3] == 1305 && p.specialAmount >= 25) { // Dragon longsword.
                        hitDamage = Misc.random((int) (maxMeleeHit(p) * 1.0));
                        p.usingSpecial = false;
                        p.specialAmount -= 25;
                        p.requestAnim(1058, 0);
                        p.requestGFX(248, 100);
                        p.requestAnim(1658, 0);
                    } else if (p.equipment[3] == 4587 && p.specialAmount >= 70) { // Dragon scimitar.
                        hitDamage = Misc.random((int) (maxMeleeHit(p) * 1.0));
                        p.usingSpecial = false;
                        p.specialAmount -= 70;
                        p.requestAnim(2081, 0);
                        p.requestGFX(347, 100);
                    } else if (p.equipment[3] == 1434 && p.specialAmount >= 40) { // Dragon mace.
                        hitDamage = Misc.random((int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = false;
                        p.specialAmount -= 40;
                        p.requestAnim(1060, 0);
                        p.requestGFX(251, 100);
                    } else if (p.equipment[3] == 3204 && p.specialAmount >= 100) { // Dragon halberd.
                        hitDamage2 = Misc.random((int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = false;
                        p.specialAmount -= 100;
                        p.requestAnim(1665, 0);
                        p.requestGFX(282, 100);
                        n.appendHit(hitDamage2, 0);

                    } else if (p.equipment[3] == 5698 || p.equipment[3] == 1215 || p.equipment[3] == 1231 || p.equipment[3] == 5680 && p.specialAmount >= 25) { // Dragon dagger(s).
                        hitDamage2 = Misc.random((int) (maxMeleeHit(p) * 1.00));
                        p.usingSpecial = false;
                        p.specialAmount -= 25;
                        p.requestAnim(1062, 0);
                        p.requestGFX(252, 100);
                        n.appendHit(hitDamage2, 0);
                    } else {
                        p.usingSpecial = false;
                        p.frames.sendMessage(p,
                                "You don't have enough special energy.");
                    }
                }
                p.combatDelay = p.attackDelay;
                p.requestFaceTo(n.npcId);
                n.appendHit(hitDamage, 0);
                if (p.xpType != 30) {
                    p.addSkillXP(hitDamage * Config.combatXP * Config.bonusXP, p.xpType);
                } else {
                    p.addSkillXP((hitDamage * Config.combatXP * Config.bonusXP) / 3, 0);
                    p.addSkillXP((hitDamage * Config.combatXP * Config.bonusXP) / 3, 1);
                    p.addSkillXP((hitDamage * Config.combatXP * Config.bonusXP) / 3, 2);
                }
                p.addSkillXP((hitDamage * Config.combatXP * Config.bonusXP) / 2, 3);
                n.requestAnim(n.defendEmote, 0);
                p.specialAmountUpdateReq = true;
                if (!n.attackingPlayer) {
                    n.attackingPlayer = true;
                    n.attackPlayer = p.playerId;
                }
            }
        } catch (Exception e) {

        }
    }

    public static boolean UsingABow(int bow) {
        for (int i = 0; i < Bows.length; i++) {
            if (bow == Bows[i]) {
                return true;
            }
        }
        return false;
    }

    public static int Bows[] = {
            4212, 4214, 4215, 4216, 4217, 4218, 4219, 4220, 4221, 4222, 4223, 837,
            767, 4734, 9185, 839, 841, 843, 845, 847, 849, 851, 853, 855, 857, 859, 861,
            2883, 4827, 6724, 11235
    };

    public boolean hasArrows(int id) {
        if (id == 882) {
            return true;
        }
        if (id == 883) {
            return true;
        }
        if (id == 884) {
            return true;
        }
        if (id == 885) {
            return true;
        }
        if (id == 886) {
            return true;
        }
        if (id == 887) {
            return true;
        }
        if (id == 888) {
            return true;
        }
        if (id == 889) {
            return true;
        }
        if (id == 890) {
            return true;
        }
        if (id == 891) {
            return true;
        }
        if (id == 892) {
            return true;
        }
        if (id == 893) {
            return true;
        }
        if (id == 78) {
            return true;
        }
        return false;
    }


    public int fetchArrowAir(int id) {
        if (id == 882) {
            return 10;
        }
        if (id == 883) {
            return 10;
        }
        if (id == 884) {
            return 11;
        }
        if (id == 885) {
            return 11;
        }
        if (id == 886) {
            return 12;
        }
        if (id == 887) {
            return 12;
        }
        if (id == 888) {
            return 13;
        }
        if (id == 889) {
            return 13;
        }
        if (id == 890) {
            return 14;
        }
        if (id == 891) {
            return 14;
        }
        if (id == 892) {
            return 15;
        }
        if (id == 893) {
            return 15;
        }
        if (id == 78) {
            return 16;
        } else {
            return 500;
        }
    }

    public int fetchArrowBack(int id) {
        if (id == 882) {
            return 19;
        }
        if (id == 883) {
            return 19;
        }
        if (id == 884) {
            return 18;
        }
        if (id == 885) {
            return 18;
        }
        if (id == 886) {
            return 20;
        }
        if (id == 887) {
            return 20;
        }
        if (id == 888) {
            return 21;
        }
        if (id == 889) {
            return 21;
        }
        if (id == 890) {
            return 22;
        }
        if (id == 891) {
            return 22;
        }
        if (id == 892) {
            return 24;
        }
        if (id == 893) {
            return 24;
        }
        if (id == 78) {
            return 24;
        } else {
            return 500;
        }
    }

    public int maxMeleeHit(Player p) {
        int a = p.skillLvl[2];
        int b = p.equipmentBonus[10];
        double c = (double) a;
        double d = (double) b;
        double f = 0;
        double h = 0;

        f = (d * 0.00175) + 0.1;
        h = Math.floor(c * f + 2.05);
        return (int) h;
    }

    public int maxRangeHit(Player p) {
        int a = p.skillLvl[2];
        int b = p.equipmentBonus[5];
        double c = (double) a;
        double d = (double) b;
        double f = 0;
        double h = 0;

        f = (d * 0.00175) + 0.1;
        h = Math.floor(c * f + 2.05);
        return (int) h;
    }

    public void resetAttack(Player p) {
        if (p == null) {
            return;
        }
        p.attackingNPC = false;
        if (p.faceToReq != 65535) {
            p.requestFaceTo(65535);
        }
    }
}
