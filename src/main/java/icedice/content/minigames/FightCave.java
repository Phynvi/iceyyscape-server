package icedice.content.minigames;

import icedice.Server;
import icedice.npcs.NPC;
import icedice.players.Player;


public class FightCave {

    Player player;

    /**
     * Class constructor
     */
    public FightCave(Player player) {
        this.player = player;
    }

    /**
     * Fight cave System
     */

    public void fightSystem() {
        switch (player.waveCount) {

            case 0:
                player.waveType[0] = 2735;
                player.waveType[1] = 2408;
                player.waveType[2] = 5100;
                player.waveType[3] = player.heightLevel;
                player.waveType[4] = 1;
                break;

            case 1:
                player.waveType[0] = 2735;
                player.waveType[1] = 2405;
                player.waveType[2] = 5095;
                player.waveType[3] = player.heightLevel;
                player.waveType[4] = 2;
                break;

            case 2:
                player.waveType[0] = 2737;
                player.waveType[1] = 2400;
                player.waveType[2] = 5093;
                player.waveType[3] = player.heightLevel;
                player.waveType[4] = 3;
                break;

            case 5:
                player.waveType[0] = 2739;
                player.waveType[1] = 2387;
                player.waveType[2] = 5104;
                player.waveType[3] = player.heightLevel;
                player.waveType[4] = 4;
                break;

            case 6:
                player.waveType[0] = 2741;
                player.waveType[1] = 2390;
                player.waveType[2] = 5099;
                player.waveType[3] = player.heightLevel;
                player.waveType[4] = 5;
                break;

            case 7:
                player.waveType[0] = 2743;
                player.waveType[1] = 2391;
                player.waveType[2] = 5089;
                player.waveType[3] = player.heightLevel;
                player.waveType[4] = 6;
                break;

            case 8:
                player.waveType[0] = 2744;
                player.waveType[1] = 2391;
                player.waveType[2] = 5089;
                player.waveType[3] = player.heightLevel;
                player.waveType[4] = 7;
                break;

            case 9:
                player.frames.showChatboxInterface(player, 241);
                player.frames.animateInterfaceId(player, 9847, 241, 2);
                player.frames.setNPCId(player, 2617, 241, 2);
                player.frames.setString(player, "TzHaar-Mej-Jal", 241, 3);
                player.frames.setString(player, "Watch out here comes Tztok-Jad!", 241, 4);
                player.waveType[0] = 2745;
                player.waveType[1] = 2415;
                player.waveType[2] = 5081;
                player.waveType[3] = player.heightLevel;
                player.waveType[4] = 8;
                break;
        }
        if (hasNeededKills())
            Server.engine.newNPC(player.waveType[0], player.waveType[1], player.waveType[2], player.waveType[3], 0, 0, 0, player.playerId, false);
        player.waveDelay = -1;
    }

    boolean hasNeededKills() {
        switch (player.waveType[4]) {

            case 1:
                if (player.waveCount == 0)
                    return true;

            case 2:
                if (player.waveCount == 1)
                    return true;

            case 3:
                if (player.waveCount == 2)
                    return true;

            case 4:
                if (player.waveCount == 5)
                    return true;

            case 5:
                if (player.waveCount == 6)
                    return true;

            case 6:
                if (player.waveCount == 7)
                    return true;

            case 7:
                if (player.waveCount == 8)
                    return true;

            case 8:
                if (player.waveCount == 9)
                    return true;

            default:
                return false;
        }
    }

    /**
     * Gets height for Fight cave (Checking for an available height level)
     */
    public int getCaveHeight() {
        for (Player p : Server.engine.players) {
            if (p != null) {
                if (p.heightLevel == player.heightLevel)
                    player.heightLevel += 4;
            }
        }
        return player.heightLevel;
    }

    /**
     * Checks if players are in the Fight cave.
     */
    public boolean playersInCave() {
        for (Player p : Server.engine.players) {
            if (p != null) {
                if (p.inJadCave())
                    return true;
            }
        }
        return false;
    }

    /**
     * Kill of NPCs in Fight cave.
     */
    public void deleteFightNPCs() {
        for (int i = 0; i < 10000; i++) {
            if (Server.engine.npcs[i] == null)
                continue;
            NPC n = Server.engine.npcs[i];
            if (n.spawnedFor == player.playerId) {
                n.absX = 0;
                n.absY = 0;
                n.currentHP = 0;
                Server.engine.npcs[i] = null;
            }
        }
    }

}