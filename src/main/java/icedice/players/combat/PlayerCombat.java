package icedice.players.combat;

import icedice.Engine;
import icedice.util.Misc;
import icedice.Config;
import icedice.players.Player;

public class PlayerCombat {

    public int wildLvl(Player p) {
        return (p.absY - 3524) / 3 + 1;
        //return (p.absX >= 2363 && p.absY >= 3071 && p.absX <= 2432 && p.absY <= 3135) / 1 + 50;
    }

    private int getXpRate(Player p) {
        return p.xpLock == 0 ? 65 : 0;
    }

    boolean isInWildRange(Player p) {
        Player p2 = Engine.players[p.attackPlayer];
        if (p.combatLevel > p2.combatLevel) {
            if (p.combatLevel - p2.combatLevel <= wildLvl(p2)) {
                return true;
            }
        } else if (p.combatLevel < p2.combatLevel) {
            if (p2.combatLevel - p.combatLevel <= wildLvl(p2)) {
                return true;
            }
        } else if (p.combatLevel == p2.combatLevel) {
            return true;
        }
        return false;
    }

    public void attackPlayer(Player p) {

        Player p2 = Engine.players[p.attackPlayer];

        if (p2 == null) {
            return;
        }

        int hitDamage = Misc.random(maxMeleeHit(p));
        if (p2.vengOn) {
            p.appendHit((int) ((hitDamage / 4) * 3), 0);
            p2.chatText = "Taste Vengeance!";
            p2.chatTextUpdateReq = true;
            p2.updateReq = true;
            p2.vengOn = false;
            p2.appendHit(hitDamage, 0);
            Engine.poison.checkForPoison(p, p2);
        }
        int hitDamage2 = Misc.random(hitDamage);
        String weapon = Engine.items.getItemName(p.equipment[3]);
        int offsetX = (p.absX - p2.absX) * -1;
        int offsetY = (p.absY - p2.absY) * -1;


        if (p.attackPlayer <= 0 || p.isDead
                || p.attackPlayer >= Engine.players.length
                || Engine.players[p.attackPlayer] == null
                || Engine.players[p.attackPlayer].isDead || p2.disconnected[1]) {
            resetAttack(p);
        }
        if (p.combatDelay > 0) {
            return;
        }

        if (!p.AtDuel() && Engine.wildernessArea(p.absX, p.absY) && !isInWildRange(p)) {
            p.frames.sendMessage(p, "You must be farther into the wilderness!");
            resetAttack(p);
            return;
        }
        if (p.AtDuel() && p.DuelPartner != p2.playerId) {
            p.frames.sendMessage(p, "This is not your opponent");
            resetAttack(p);
            return;
        }

        if (p.AtCastleWars() && p.CWTeam == p2.CWTeam) {
            p.frames.sendMessage(p, "You can't attack your team mate.");
            resetAttack(p);
            return;
        }

        if (p.AtPits() && p.GameStarted == false) {
            p.frames.sendMessage(p, p.PitGame + " till game begins.");
            resetAttack(p);
            return;
        }

        if (p.DuelCan == false && p.AtDuel() == true) {
            p.frames.sendMessage(p, "You cannot fight just yet.");
            resetAttack(p);
            return;
        }
        if (p.AtJail() || p.AtClanLobby()) {
            p.frames.sendMessage(p, "You cannot attack some one here.");
            resetAttack(p);
            return;
        }

        if (!isInWildRange(p) && p.AtDuel() == false && p.AtClanField() == false && p.AtCastleWars() == false) {
            //p.frames.sendMessage(p, "You need to move deeper into the wilderness!");
            resetAttack(p);
            return;
        }
        if (p.canAttackPlayer(p)) {
            if (Misc.getDistance(p2.absX, p2.absY, p.absX, p.absY) >= 1 && UsingABow(p.equipment[3])) {
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
                                    p2.appendHit(Misc.random(hitDamage), 0);
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
                        p.frames.createGlobalProjectile(p.absY, p.absX, offsetY, offsetX, fetchArrowAir(p.equipment[13]), 43, 31, 70, p2.playerId);
                        p.combatDelay = p.attackDelay;
                        p.requestFaceTo(p2.playerId + 32768);

                    }

                    if (p2.prayerIcon == 1) {
                        if (p2.Hitter > 0) {
                            p2.Hitter -= 1;
                            hitDamage = 0;
                        } else {
                            p2.Hitter = 2 + Misc.random(4);
                        }
                    }

                    p2.appendHit(hitDamage, 0);
                    Engine.poison.checkForPoison(p, p2);
                    p.addSkillXP(4 * (hitDamage * 65), 4);
                    p.addSkillXP(2 * (hitDamage * 65), 3);
                    p.equipmentN[13] = p.equipmentN[13] - 1;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                    Engine.items.createGroundItem(p.equipment[13], 1, p2.absX, p2.absY, p2.heightLevel, p2.username);
                    p2.requestAnim(424, 0);
                    if (p2.autoRetaliate == 0) {
                        p2.requestFaceTo(p.playerId + 32768);
                        p2.attackPlayer = p.playerId;
                        p2.attackingPlayer = true;
                        p2.followPlayer = p.playerId;
                        p2.followingPlayer = true;
                    }

                } else {
                    p.frames.sendMessage(p, "You need some arrows to attack a player");
                    resetAttack(p);
                }
            } else if (Misc.getDistance(p2.absX, p2.absY, p.absX, p.absY) <= 1 && !UsingABow(p.equipment[3])) {
                p.followPlayer = p2.playerId;
                p.followingPlayer = true;
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
                    } else if (p.equipment[3] == 11730 && p.specialAmount >= 100) { // Saradomin Sword
                        p.usingSpecial = false;
                        p.specialAmount -= 100;
                        p.requestAnim(7072, 0);
                        p.requestGFX(1224, 100);
                        p2.requestGFX(1194, 100);
                        p2.appendHit(Misc.random(42), 0);
                        Engine.poison.checkForPoison(p, p2);
                        p.usingSpecial = false;
                    } else if (p.equipment[3] == 11730 && p.specialAmount >= 100) { // Saradomin Sword
                        p.usingSpecial = false;
                        p.specialAmount -= 100;
                        p.requestAnim(7072, 0);
                        p.requestGFX(1224, 100);
                        p2.requestGFX(1194, 100);
                        p2.appendHit(Misc.random(42), 0);
                        Engine.poison.checkForPoison(p, p2);
                        p.usingSpecial = false;
                    } else if (p.equipment[3] == 861 && p.specialAmount == 50) { // Magic Shortbow
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 0.75));
                        p.usingSpecial = true;
                        p.specialAmount -= 50;
                        p.requestAnim(426, 0);
                        p.requestGFX(472, 0);
                    } else if (p.equipment[3] == 11696 && p.specialAmount == 100) { // Bandos godsword.
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = false;
                        p.specialAmount -= 100;
                        p.requestAnim(7073, 0);
                        p.requestGFX(1223, 0);
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
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = false;
                        p.specialAmount -= 50;
                        p.requestAnim(7070, 0);
                        p.requestGFX(1221, 0);
                    } else if (p.equipment[3] == 4151 && p.specialAmount >= 50) { // Abyssal whip.
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 0.9));
                        p.usingSpecial = false;
                        p.specialAmount -= 50;
                        p.requestAnim(1658, 0);
                        p2.requestGFX(341, 100);
                    } else if (p.equipment[3] == 7158 && p.specialAmount >= 50) { // Dragon 2h.
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 0.9));
                        p.usingSpecial = false;
                        p.specialAmount -= 50;
                        p.requestAnim(3157, 0);
                        p2.requestGFX(559, 0);
                    } else if (p.equipment[3] == 1305 && p.specialAmount >= 25) { // Dragon longsword.
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 1.0));
                        p.usingSpecial = false;
                        p.specialAmount -= 25;
                        p.requestAnim(1058, 0);
                        p.requestGFX(248, 100);
                        p.requestAnim(1658, 0);
                    } else if (p.equipment[3] == 4587 && p.specialAmount >= 70) { // Dragon scimitar.
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 1.0));
                        p.usingSpecial = false;
                        p.specialAmount -= 70;
                        p.requestAnim(2081, 0);
                        p.requestGFX(347, 100);
                    } else if (p.equipment[3] == 1434 && p.specialAmount >= 40) { // Dragon mace.
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = false;
                        p.specialAmount -= 40;
                        p.requestAnim(1060, 0);
                        p.requestGFX(251, 100);
                    } else if (p.equipment[3] == 3204 && p.specialAmount >= 100) { // Dragon halberd.
                        hitDamage2 = Misc.random(
                                (int) (maxMeleeHit(p) * 1.1));
                        p.usingSpecial = false;
                        p.specialAmount -= 100;
                        p.requestAnim(1665, 0);
                        p.requestGFX(282, 100);
                        p2.appendHit(hitDamage2, 0);
                    } else if (p.equipment[3] == 1277 && p.specialAmount >= 5) { // Twin swords
                        p.usingSpecial = false;
                        p.specialAmount -= 5;
                        p.requestAnim(2068, 0);
                        p.requestGFX(4, 100);
                        p2.appendHit(Misc.random(42), 0);
                        p.usingSpecial = false;
                        p.frames.sendMessage(p,
                                "You don't have enough special energy.");
                    } else if (p.specialAmount >= 25 && p.equipment[3] == 5698 || p.equipment[3] == 1215 || p.equipment[3] == 1231 || p.equipment[3] == 5680) { // Dragon dagger(s).
                        p.usingSpecial = false;
                        p.specialAmount -= 25;
                        p.requestAnim(1062, 0);
                        p.requestGFX(252, 100);
                        p2.appendHit(Misc.random(42), 0);
                        p.usingSpecial = false;
                        p.frames.sendMessage(p,
                                "You don't have enough special energy.");
                    } else if (p.equipment[3] == 4153 && p.specialAmount >= 25) { // granite maul
                        p.usingSpecial = false;
                        p.specialAmount -= 25;
                        p.requestAnim(1667, 0);
                        p.requestGFX(340, 100);
                        p2.appendHit(Misc.random(42), 0);
                        p.usingSpecial = false;
                        p.frames.sendMessage(p,
                                "You don't have enough special energy.");
                    } else if (p.equipment[3] == 3101 && p.specialAmount >= 50) { // dragon claws
                        hitDamage = Misc.random(
                                (int) (maxMeleeHit(p) * 1.5));
                        p.usingSpecial = false;
                        p.secHit2 = hitDamage / 2;
                        p.thirdHit2 = Misc.random((int) (maxMeleeHit(p) * 0.75));
                        if (p.thirdHit2 != 0) {
                            p.fourHit2 = p.thirdHit2 + 2;
                        } else {
                            p.fourHit2 = 9;
                        }
                        p2.appendHit(p.secHit2, 0);
                        p.clawTimer2 = 1;
                        p.UseClaws3 = true;
                        p.specialAmount -= 50;
                        p.requestAnim(2876, 0);
                        p.requestGFX(333, 100);
                        p.frames.sendMessage(p,
                                "You don't have enough special energy.");
                    }
                }
                p.combatDelay = p.attackDelay;
                p.requestFaceTo(p2.playerId + 32768);
                if (p2.prayerIcon == 0) {
                    if (p2.Hitter > 0) {
                        p2.Hitter -= 1;
                        hitDamage = 0;
                    } else {
                        p2.Hitter = 2 + Misc.random(4);
                    }
                }

                if (Defence(p)) {
                    if (p2.vengOn) {
                        p.appendHit((int) ((hitDamage / 4) * 3), 0);
                        p2.chatText = "Taste Vengeance!";
                        p2.chatTextUpdateReq = true;
                        p.updateReq = true;
                        p2.vengOn = false;
                    }
                    Engine.poison.checkForPoison(p, p2);
                    p2.appendHit(hitDamage, 0);
                } else {
                    p2.appendHit(0, 0);
                }

                if (p.xpType != 30) {
                    p.addSkillXP(hitDamage * Config.combatXP * Config.bonusXP, p.xpType);
                } else {
                    p.addSkillXP((hitDamage * Config.combatXP * Config.bonusXP) / 3, 0);
                    p.addSkillXP((hitDamage * Config.combatXP * Config.bonusXP) / 3, 1);
                    p.addSkillXP((hitDamage * Config.combatXP * Config.bonusXP) / 3, 2);
                }
                p.addSkillXP((hitDamage * Config.combatXP * Config.bonusXP) / 2, 3);
                p2.requestAnim(p2.BlockEmote, 0);
                p.specialAmountUpdateReq = true;
                if (p2.autoRetaliate == 0) {
                    p2.requestFaceTo(p.playerId + 32768);
                    p2.attackPlayer = p.playerId;
                    p2.attackingPlayer = true;
                }
            }
        } else if (!Engine.wildernessArea(p.absX, p.absY)) {
            //p.frames.sendMessage(p, "You are not in the wild.");
            resetAttack(p);
        } else if (!Engine.wildernessArea(p2.absX, p2.absY)) {
            //p.frames.sendMessage(p, "This player is not in the wild.");
            resetAttack(p);
        } else {
            resetAttack(p);
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

    public int BestAttackBonus(Player p) {
        if (p.equipmentBonus[0] > p.equipmentBonus[1] && p.equipmentBonus[0] > p.equipmentBonus[2]) {
            return 0;
        }
        if (p.equipmentBonus[1] > p.equipmentBonus[0] && p.equipmentBonus[1] > p.equipmentBonus[2]) {
            return 1;
        }
        if (p.equipmentBonus[2] > p.equipmentBonus[1] || p.equipmentBonus[2] > p.equipmentBonus[0]) {
            return 2;
        }
        return 1;
    }

    public boolean Defence(Player p) {
        int att_def = BestAttackBonus(p);
        int BestAttack = p.equipmentBonus[BestAttackBonus(p)] + p.skillLvl[0] + Misc.random(25);
        int DefenceBonus = Engine.players[p.attackPlayer].equipmentBonus[att_def + 5] + (Engine.players[p.attackPlayer].skillLvl[1]);
        if ((Misc.random(BestAttack)) > Misc.random(DefenceBonus)) {
            return true;
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
        p.attackingPlayer = false;
        if (p.faceToReq != 65535) {
            p.requestFaceTo(65535);
        }
        if (p.inBounty) {
            Player p2 = Engine.players[p.playerId];
            Player p3 = Engine.players[p2.attackedBy];
            if (p2.attackedBy > 0 && p2.playerId != p.bhTarget && p2.bhTarget != p.playerId &&
                    p2.attackedBy != p.playerId && p2.attackedBy != p2.bhTarget &&
                    p3.bhTarget != p2.playerId) {
                p.frames.sendMessage(p, "This player is already in combat with another person.");
                p.enemyId = 0;
                return;
            }
        }
        Player p2 = Engine.players[p.playerId];
        p2.attackedBy = p.playerId;
    }
}
