package icedice.npcs.combat;

import icedice.Engine;
import icedice.util.Misc;
import icedice.npcs.NPC;
import icedice.players.Player;

public class NPCPlayerCombat {

    public int DragAttack = Misc.random(1);
    public int AhrimAttack = Misc.random(1);

    public void attackPlayer(NPC n) {
        try {
            Player p = Engine.players[n.attackPlayer];
            int hitType = Misc.random(5);
            int maxHit = Misc.random(n.maxHit);
            int offsetX = (n.absX - p.absX) * -1;
            int offsetY = (n.absY - p.absY) * -1;

            if (n.attackPlayer <= 0 || n.isDead || p.isDead
                    || n.attackPlayer >= Engine.players.length || p == null
                    || p.disconnected[1]) {
                resetAttack(n);
            }
            if (n.combatDelay > 0) {
                return;
            }

            if (n.absX == p.absX && n.absY == p.absY) {
                System.out.println("NPC NEEDS TO MOVE!!");
                n.moveY = Engine.npcMovement.getMove(n.absY, p.absY + 1);
            }

            if (Misc.getDistance(p.absX, p.absY, n.absX, n.absY) <= 1) {
                n.requestFaceTo(p.playerId + 32768);

                if (n.npcType == 9 || n.npcType == 21 || n.npcType == 20) {
                    n.requestAnim(451, 0);
                } else if (n.npcType == 2 || n.npcType == 1) {
                    n.requestAnim(422, 0);
                } else if (n.npcType == 50 && DragAttack == 1) {
                    n.requestGFX(0, 0);
                    n.requestAnim(81, 0);
                    DragAttack = Misc.random(1);
                    if (p.equipment[5] == 1540 || p.equipment[5] == 11283) {
                        p.frames.sendMessage(p, "Your shield obsorbs some of the dragon fire.");
                        maxHit = Misc.random(5);
                    } else {
                        p.frames.sendMessage(p, "The dragons fire hits you and burns off your eye brows!");
                        maxHit = 10 + Misc.random(20);
                    }
                } else if (n.npcType == 2025 && AhrimAttack == 1) {
                    n.requestGFX(362, 0);
                    p.requestGFX(363, 0);
                    n.requestAnim(1978, 0);
                    AhrimAttack = Misc.random(1);
                    {
                        maxHit = 10 + Misc.random(20);
                    }
                } else if (n.npcType == 742 && DragAttack == 1 || n.npcType == 5363 && DragAttack == 1 || n.npcType == 55 && DragAttack == 1 || n.npcType == 53 && DragAttack == 1 || n.npcType == 941 && DragAttack == 1 || n.npcType == 4673 && DragAttack == 1 || n.npcType == 4674 && DragAttack == 1 || n.npcType == 4675 && DragAttack == 1 || n.npcType == 3376 && DragAttack == 1) {
                    n.requestGFX(1, 0);
                    n.requestAnim(81, 0);
                    DragAttack = Misc.random(1);
                    if (p.equipment[5] == 1540 || p.equipment[5] == 11283) {
                        p.frames.sendMessage(p, "Your shield obsorbs some of the dragon fire.");
                        maxHit = Misc.random(5);
                    } else {
                        p.frames.sendMessage(p, "You get hit by the dragon fire.");
                        maxHit = 10 + Misc.random(20);
                    }
                } else if (n.npcType == 742 && DragAttack == 0 || n.npcType == 5363 && DragAttack == 0 || n.npcType == 55 && DragAttack == 0 || n.npcType == 53 && DragAttack == 0 || n.npcType == 941 && DragAttack == 0) {
                    n.requestAnim(91, 0);
                    DragAttack = Misc.random(1);
                } else {
                    n.requestAnim(n.attackEmote, 0);
                }

                if (p.prayerIcon == 0) {
                    if (p.Hitter > 0) {
                        p.Hitter -= 1;
                        maxHit = 0;
                    } else {
                        p.Hitter = 2 + Misc.random(4);
                    }
                }

                p.appendHit(maxHit, 0);
                p.requestAnim(p.BlockEmote, 0);
                n.combatDelay = n.attackDelay;
                if (p.autoRetaliate == 0 && !p.attackingNPC) {
                    p.combatDelay += 3;
                    p.requestFaceTo(n.npcId);
                    p.attackNPC = n.npcId;
                    p.attackingNPC = true;
                }
            } else {
                n.followPlayer = 1;
                //System.out.println("NPC following " + p.username);
            }

            if (p.isDead && n.followPlayer == 1 || Misc.getDistance(p.absX, p.absY, n.absX, n.absY) >= 10) {
                //System.out.println("NPC stopped following " + p.username);
                n.followPlayer = 0;
                n.randomWalk = true;
                resetAttack(n);
            }

        } catch (Exception e) {

        }
    }

    public void resetAttack(NPC n) {
        Player p = Engine.players[n.attackPlayer];
        if (n == null) {
            return;
        }
        n.attackingPlayer = false;
        // need to cancel the npc facing here, somehow.
    }
}
