package icedice.content.minigames;

import icedice.Engine;
import icedice.Server;
import icedice.npcs.NPC;
import icedice.players.Player;

import java.util.Random;

/**
 * Josh Artuso
 * January 29, 2016
 *
 * A Zombie Survival Mini Game.
 *
 */


public class Survival extends Thread {

    // Zombies with a lvl < 20
    private final int[] LOW_LEVEL_ZOMBIES = {73, 74, 419, 420, 76};

    // Zombies with a lvl < 100
    private final int[] MID_LEVEL_ZOMBIES = {421, 422, 1467, 1465};

    // Zombies with a lvl > 99
    private final int[] HIGH_LEVEL_ZOMBIES = {423, 424, 129};

    // The wave count
    public int wave = 1;

    // How many zombies are left to kill
    public int zombiesLeft = 0;

    // Etceteria spawn zones

    int[][] etceteriaSpawnZones = {{2617, 3861}, {2589, 3871}, {2607, 3893}};

    // The player
    private Player player;

    public static void startSurvival(Player p) {
        new Survival(p).start();
    }

    public Survival(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        player.teleportTo(2613, 3842, player.playerId*4, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
        while (!this.player.isDead) {
            if (zombiesLeft == 0) {
                this.spawnZombies();
            }
        }
        player.frames.sendMessage(player, "Game Over.");
    }

    private void spawnZombies() {
        int max_zombies = 50;

        Random random = new Random();
        int zombieId = this.LOW_LEVEL_ZOMBIES[random.nextInt(this.LOW_LEVEL_ZOMBIES.length-1)];
        NPC zombie = Engine.npcs[Server.engine.newNPC(zombieId, 2617, 3861, player.heightLevel, 0, 0, 0, 0, false)];
        zombie.goToPlayer(player, zombie);

        this.zombiesLeft += 1;
    }

}
