package icedice;

import icedice.Iceframe;
import icedice.clanchat.*;
import icedice.players.*;
import icedice.content.GrandExchangeLoader;
import icedice.content.geLoader;
import icedice.players.items.*;
import icedice.players.update.*;
import icedice.players.combat.*;
import icedice.world.mapdata.MapData;
import icedice.world.items.Items;
import icedice.io.*; // so this will call all these methods...
import icedice.Skills.*;
import icedice.util.Misc;
import icedice.npcs.*;
import icedice.npcs.update.*;
import icedice.npcs.loading.*;
import icedice.npcs.combat.*;
import icedice.content.*;
import icedice.content.minigames.*;
import icedice.event.*;
import icedice.util.Censor;
import icedice.characters.*;

import java.net.Socket;
import java.io.*;

public class Engine implements Runnable {

    public static Herby herby = new Herby();
    public static geLoader geLoader = new geLoader();
    public static GrandExchangeLoader GrandExchangeLoader;
    public static Poison poison = new Poison();

    /**
     * When a new player logs in, a new player is created and stored in this array.
     * <p>The server cannot exceed 2000 players.
     * The id 0 cannot be used as it is not handled by the client corrently.
     */
    public static Player[] players = new Player[2000];

    public static boolean fakeAdmin = false;

    private int playerCount = 0;
    public static Censor Censor = new Censor();
    public static ClanChat clanChat = new ClanChat();
    public static ClanWars clanWars = new ClanWars();

    // Party Room!!!
    public static PartyRoom partyRoom = new PartyRoom();

    /**
     * Max id an NPC can have loaded out of the npclist Config.
     */
    public static int maxListedNPCs = 8041;
    /**
     * When a new NPC is created, it is stored in this array.
     * <p>There can not be more than 5000 NPCs spawned at a time.
     * The id 0 cannot be used as it is not handled by the client corrently.
     */
    public static NPC[] npcs = new NPC[5000];
    public static int RegenTimer = 10;
    public static int FightPitTimer = 120;
    public static int RestartTimer = 7200;
    public static int SqlCheckTimer = 30;
    public static int playersingame = 0;
    public static int SaradominP = 0;
    public static int ZamorakP = 0;
    public static int CWarsTimer = 240;
    public static int CWGameTime = -1;
    public static int SaradominScore = 0;
    public static int ZamorakScore = 0;
    public static int ZamorakTeam = 0;
    public static int PlayerUpdateTimer = 0;
    public static int checkTimer = 10;
    public static int SaradominTeam = 0;
    public static boolean SaradominFlag = false;
    public static boolean ZamorakFlag = false;
    public static BountyHunter BountyHunter = new BountyHunter();
    /**
     * Contains NPC data such as respawn times, names, examines, etc.
     */
    public static NPCList[] npcLists = new NPCList[maxListedNPCs];
    /**
     * The class thread.
     */
    private Thread engineThread = new Thread(this);
    /**
     * Set true if the engine should run.
     */
    public static boolean engineRunning = true;
    /**
     * Handles packets recieved from the server.
     */
    public static Packets packets = new Packets();
    public static objectLoader oL = new objectLoader();
    /**
     * Loads and stores map data for map regions.
     */
    public static MapData mapData = new MapData();
    /**
     * Contains a method for every known frame.
     */
    public static Frames frames = new Frames();
    /**
     * Handles player movement.
     */
    public static PlayerMovement playerMovement = new PlayerMovement();
    /**
     * Contains all item information.
     */
    public static Items items;
    /**
     * Handles player updates.
     */
    public static PlayerUpdate playerUpdate = new PlayerUpdate();
    /**
     * Handles NPC movement.
     */
    public static NPCMovement npcMovement = new NPCMovement();
    /**
     * Handles NPC updates.
     */
    public static NPCUpdate npcUpdate = new NPCUpdate();
    /**
     * Handles players items.
     */
    public static PlayerItems playerItems = new PlayerItems();
    /**
     * Handles file saving and loading.
     */
    public static FileManager fileManager = new FileManager();
    /**
     * Reads console lines
     */
    public String consoleText = "";

    public static PlayerBank playerBank = new PlayerBank();
    public static ShopHandler shopHandler = new ShopHandler();
    public static Shops shops = new Shops();
    public static PlayerCombat playerCombat = new PlayerCombat();
    public static PlayerFollow playerFollow = new PlayerFollow();
    public static PlayerNPCCombat playerNPCCombat = new PlayerNPCCombat();
    public static NPCPlayerCombat npcPlayerCombat = new NPCPlayerCombat();
    public static Smithing smithing = new Smithing();
    public static ConsoleCommands consoleCommand = new ConsoleCommands();
    public static NoclipHandler noclipHandler = new NoclipHandler();

    /**
     * Constructs a new Engine and loads item and NPC data from Configs.
     */
    public Engine() {
        //GrandExchangeLoader = new GrandExchangeLoader();
        items = new Items(this);
        LoadNPCLists lnl = new LoadNPCLists();

        lnl = null;
        engineThread.start();
    }

    public static void appendUnBan(String p) {
        //SQLUsers.removeBan(p);
    }

    public static void appendBan(String p2username, String username) {
        //SQLUsers.addBan(p2username);
    }

    public static void appendIpBan(Player p) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("data/banned/bannedhosts.txt", true));
            bw.write("" + Server.socketListener.getAddress(p.socket.socket));
            bw.newLine();
            bw.flush();
            p.socket.socket.close();
        } catch (Exception error) {
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception error) {
                }
            }
        }
    }

    /**
     * The thread's process.
     * <p>This processes at a rate of 100 milliseconds, and the processing time
     * is subtracted from the amount of time it sleeps to keep at a rate of 100
     * milliseonds. Packets are checked every 100 milliseconds for faster response time,
     * and most other updates are processed every 600 milliseconds.
     */
    public void run() {
        long lastEntityUpdate = 0;
        long curTime;

        while (engineRunning) {
            curTime = System.currentTimeMillis();
            connectAwaitingPlayers();
            packets.parseIncomingPackets();
            if (curTime - lastEntityUpdate >= 600) {
               /* try {
                    new GrandExchangeLoader().saveOffers();
                } catch (java.lang.Exception e) {
                }*/
                items.process();

                if (FightPitTimer > 0) {
                    FightPitTimer -= 1;
                }
                if (FightPitTimer == 0) {
                    FightPitTimer = -1;
                }

                if (RegenTimer > 0) RegenTimer--;
                if (RegenTimer == 0) {
                    for (int i = 0; i < shops.beginnersShop.length; i++) {
                        if (shops.beginnersShopN[i] != shops.beginnersShopNMax[i]) {
                            shops.beginnersShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.magicShop.length; i++) {
                        if (shops.magicShopN[i] != shops.magicShopNMax[i]) {
                            shops.magicShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.meleeShop.length; i++) {
                        if (shops.meleeShopN[i] != shops.meleeShopNMax[i]) {
                            shops.meleeShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.partyShop.length; i++) {
                        if (shops.partyShopN[i] != shops.partyShopNMax[i]) {
                            shops.partyShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.food.length; i++) {
                        if (shops.foodN[i] != shops.foodNMax[i]) {
                            shops.foodN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.skillShop.length; i++) {
                        if (shops.skillShopN[i] != shops.skillShopNMax[i]) {
                            shops.skillShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.DragonShop.length; i++) {
                        if (shops.DragonShopN[i] != shops.DragonShopNMax[i]) {
                            shops.DragonShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.Dweps.length; i++) {
                        if (shops.DwepsN[i] != shops.DwepsNMax[i]) {
                            shops.DwepsN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.fancyShop.length; i++) {
                        if (shops.fancyShopN[i] != shops.fancyShopNMax[i]) {
                            shops.fancyShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.barrows.length; i++) {
                        if (shops.barrowsN[i] != shops.barrowsNMax[i]) {
                            shops.barrowsN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.clothing.length; i++) {
                        if (shops.clothingN[i] != shops.clothingNMax[i]) {
                            shops.clothingN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.armour.length; i++) {
                        if (shops.armourN[i] != shops.armourNMax[i]) {
                            shops.armourN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.potionShop.length; i++) {
                        if (shops.potionShopN[i] != shops.potionShopNMax[i]) {
                            shops.potionShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.dArmour.length; i++) {
                        if (shops.dArmourN[i] != shops.dArmourNMax[i]) {
                            shops.dArmourN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.rangedShop.length; i++) {
                        if (shops.rangedShopN[i] != shops.rangedShopNMax[i]) {
                            shops.rangedShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.godShop.length; i++) {
                        if (shops.godShopN[i] != shops.godShopNMax[i]) {
                            shops.godShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.herbShop.length; i++) {
                        if (shops.herbShopN[i] != shops.herbShopNMax[i]) {
                            shops.herbShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.dRangeShop.length; i++) {
                        if (shops.dRangeShopN[i] != shops.dRangeShopNMax[i]) {
                            shops.dRangeShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.rangedAShop.length; i++) {
                        if (shops.rangedAShopN[i] != shops.rangedAShopNMax[i]) {
                            shops.rangedAShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.sumShop.length; i++) {
                        if (shops.sumShopN[i] != shops.sumShopNMax[i]) {
                            shops.sumShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.craftShop.length; i++) {
                        if (shops.craftShopN[i] != shops.craftShopNMax[i]) {
                            shops.craftShopN[i]++;
                        }
                    }

                    for (int i = 0; i < shops.slayerStore.length; i++) {
                        if (shops.slayerStoreN[i] != shops.slayerStoreNMax[i]) {
                            shops.slayerStoreN[i]++;
                        }
                    }
                    RegenTimer = 30;
                }

                if (SqlCheckTimer > 0) SqlCheckTimer--;
                if (SqlCheckTimer == 0) {
                    //SQLUsers.check();
                    SqlCheckTimer = 30;
                }

                if (RestartTimer > 0) RestartTimer--;
                if (RestartTimer == 0) {
                    System.exit(1);
                }

                if (FightPitTimer == -1 && playersingame == 0) {
                    FightPitTimer = 120;
                }

                if (CWarsTimer > 0) {
                    CWarsTimer -= 1;
                }
                if (CWarsTimer == 0) {
                    CWarsTimer = -1;
                    CWGameTime = 600;
                    ZamorakScore = 0;
                    SaradominScore = 0;
                }

                if (CWGameTime > 0) {
                    CWGameTime -= 1;
                }
                if (CWGameTime == 0) {
                    CWarsTimer = 240;
                    CWGameTime = -1;
                }

                // if(PlayerUpdateTimer > 0) PlayerUpdateTimer -= 1;
                // if(PlayerUpdateTimer == 0){
                // PlayerUpdateTimer = 20;
                // Iceframe.updatePlayers();
                // }

                Player z = players[ZamorakP];
                Player s = players[SaradominP];
                if (z == null) {
                    ZamorakFlag = false;
                    ZamorakP = 0;
                }

                if (ZamorakP > 0) {
                    ZamorakFlag = true;
                }

                if (s == null) {
                    SaradominFlag = false;
                    SaradominP = 0;
                }

                if (SaradominP > 0) {
                    SaradominFlag = true;
                }

                for (Player p : players) {
                    // Proccess and player movement, removing disconnected players.
                    if (p == null || !p.online) {
                        continue;
                    }
                    if (p.disconnected[0] && p.disconnected[1]) {
                        removePlayer(p.playerId);
                        continue;
                    }

                    p.process();

                    playerMovement.getNextPlayerMovement(p);
                }
                for (Player p : players) {
                    // Update players.
                    if (p == null || !p.online) {
                        continue;
                    }
                    playerUpdate.update(p);
                }
                for (Player p : players) {
                    // Reset masks and send bytes.
                    if (p == null || !p.online) {
                        continue;
                    }
                    playerUpdate.clearUpdateReqs(p);
                    Server.socketListener.writeBuffer(p);
                }
                for (NPC n : npcs) {
                    if (n == null) {
                        continue;
                    }
                    npcUpdate.clearUpdateMasks(n);
                }
                for (NPC n : npcs) {
                    if (n == null) {
                        continue;
                    }
                    n.process();
                    if (!n.isDead) {
                        if (n.randomWalk && !n.attackingPlayer) {
                            npcMovement.randomWalk(n);
                        }
                    } else {
                        if (!n.deadEmoteDone) {
                            n.requestAnim(n.deathEmote, 0);
                            n.deadEmoteDone = true;
                            n.combatDelay = 3;
                        } else if (n.deadEmoteDone && !n.hiddenNPC && n.combatDelay <= 0) {
                            n.npcDied(Engine.players[1], n.npcType, n.absX, n.absY);
                            n.hiddenNPC = true;
                        } else if (n.deadEmoteDone && !n.hiddenNPC
                                && n.combatDelay <= 0) {
                            n.hiddenNPC = true;
                        } else if (n.hiddenNPC && n.respawnDelay <= 0) {
                            if (n.needsRespawn) {
                                newNPC(n.npcType, n.makeX, n.makeY,
                                        n.heightLevel, n.moveRangeX1,
                                        n.moveRangeY1, n.moveRangeX2,
                                        n.moveRangeY2, true);
                            }
                            npcs[n.npcId] = null;
                            rebuildNPCs();
                        }
                    }
                    lastEntityUpdate = curTime;
                }
            }
            try {
                Thread.sleep(100 - (System.currentTimeMillis() - curTime));
            } catch (Exception e) {

            }
        }
    }

    /**
     * Assign a player to the connection.
     * <p>When a new connection is recieved, a new Player class is created which waits for the run method
     * to run the Login class to finish the connection. If the id is 0, or the server is full, no
     * connection is made and the socket is closed.
     *
     * @param socket The socket the new player is connected to.
     * @param id     The id which the new player will be created at.
     */
    public void addConnection(Socket socket, int id) {
        if (id == 0) {
            Misc.println("Adding connection failed :/");
            Server.socketListener.removeSocket(id);
            return;
        }
        players[id] = new Player(socket, id);
    }

    /**
     * Run the login class to finish a new connection.
     * <p>Loops through all the players looking for any that are not online.
     * Because the socket can't cause the server to process slower, there
     * are multiple login stages, and the player will go through the login class
     * two or three times before fully logging in.
     */


    public void connectAwaitingPlayers() {
        for (Player p : players) {
            if (p == null || p.online) {
                continue;
            }
            Login login = new Login();
            login.login(p);
            if (!p.online && p.loginStage == -1 || (System.currentTimeMillis() - p.loginTimeout) >= 15000) {

                /*
                 * Remove the player if he failed to connect or it was the update server.
                 */
                removePlayer(p.playerId);
            }
        }
    }

    /**
     * Discard a player with the specified id in the player array.
     * <p>This method should only be called when the player is ready to be removed.
     * This nulls closes the players socket and then nulls the player.
     *
     * @param id The position in the player array that should be removed.
     */
    public void removePlayer(int id) {
        if (players[id] == null) {
            return;
        }
        if (players[id].online) {
            try {
                fileManager.saveCharacter(players[id]);
            } catch (Exception e) {

            }
        }
        //Iceframe.updatePlayers();
        players[id].destruct();
        players[id] = null;
        Server.socketListener.removeSocket(id);
    }

    /**
     * This method tells every player to re-add any NPCs within distance.
     * <p>By calling this method, with the next NPC update it will discard the NPC list and
     * re-loop through every NPC and re-add them if they fit the specifications.
     */
    public void rebuildNPCs() {
        for (Player p : players) {
            if (p == null) {
                continue;
            }
            p.rebuildNPCList = true;
        }
    }

    /**
     * Returns a players id based on their username, or 0 if the player is not online.
     * <p>This will check if a player is online based on searching every characters username,
     * and comparing it to playerName. Because index 0 is not used and won't throw an
     * ArrayoutofBounds exception error, 0 is returned if the username isn't found.
     *
     * @param playerName The name to compare with the other online player names.
     * @return Returns the index of the player with that username, or 0 if the username isn't found.
     */
    public static int getIdFromName(String playerName) {
        playerName.replaceAll("_", " ");
        for (Player p : players) {
            if (p == null) {
                continue;
            }
            if (p.username.equalsIgnoreCase(playerName)) {
                return p.playerId;
            }
        }
        return 0;
    }

    /**
     * boolean to check if player is in wild.
     */
    public static boolean wildernessArea(int absX, int absY) {
        return (absX >= 2363 && absY >= 3071 && absX <= 2432 && absY <= 3135 || absX >= 2370 && absY >= 5128 && absX <= 2426 && absY <= 5167 || absX >= 2940 && absX <= 3395 && absY >= 3524 && absY <= 4000 || absX >= 3362 && absY >= 3228 && absX <= 3391 && absY <= 3241 || absX >= 2363 && absY >= 3071 && absX <= 2432 && absY <= 3135);

    }

    public static boolean pvpArea(int absX, int absY) {
        return (absX >= 2940 && absX <= 3395 && absY >= 3524 && absY <= 4000);
    }

    public static boolean dropArea(int absX, int absY) {
        return (absX >= 2940 && absX <= 3395 && absY >= 3539 && absY <= 4000);
    }

    public static boolean safeArea(int absX, int absY) {
        return (absX >= 2940 && absX <= 3395 && absY >= 3523 && absY <= 3538);
    }

    public static boolean cwarea(int absX, int absY) {
        return (absX >= 2368 && absX <= 2428 && absY >= 3072 && absY <= 3132);
    }

    /**
     * Returns the description of npcId.
     *
     * @param npcId The NPC to get the examine for.
     * @return Returns the examine.
     */
    public String getNPCDescription(int npcId) {
        if (npcId == -1 || npcId >= maxListedNPCs) {
            return new String("An NPC.");
        }
        if (npcLists[npcId] != null) {
            return (npcLists[npcId].examine);
        }
        return new String("NPC " + npcId);
    }

    /**
     * Check player count.
     */
    public static int getPlayerCount() {
        int count = 0;

        for (Player p : players) {
            if (p != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Spawn a new NPC.
     * <p>This will create a new visible NPC, with the default values from its list and the parameters.
     *
     * @param type         The type of NPC this is, such as 50 for the King black dragon.
     * @param absX         The absolute x coordinate to spawn the NPC.
     * @param absY         The absolute y coordinate to spawn the NPC.
     * @param height       The height level to set the NPC.
     * @param mRXY1        The distance it can walk west; must be lower than absX to have any effect.
     * @param mRY1         The distance it can walk south; must be lower than absY to have any effect.
     * @param mRX2         The distance it can walk east; must be higher than absX to have any effect.
     * @param mRY2         The distance it can walk north; must be higher than absY to have any effect.
     * @param needsRespawn Set to true if the NPC should respawn after it dies.
     * @return Returns the index the NPC was placed at.
     */
    public static int newNPC(int type, int absX, int absY, int height, int mRX1, int mRY1, int mRX2, int mRY2, boolean needsRespawn) {
        int index = -1;

        for (int i = 1; i < npcs.length; i++) {
            if (npcs[i] == null) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            Misc.println("Max number of NPCs spawned.");
            return -1;
        }
        NPC n = npcs[index] = new NPC(type, index);

        n.absX = absX;
        n.absY = absY;
        n.makeX = absX;
        n.makeY = absY;
        n.heightLevel = height;
        n.moveRangeX1 = mRX1;
        n.moveRangeY1 = mRY1;
        n.moveRangeX2 = mRX2;
        n.moveRangeY2 = mRY2;
        n.needsRespawn = needsRespawn;
        NPCList nl = npcLists[type];

        if (nl != null) {
            n.name = nl.npcName;
            n.combatLevel = nl.combatLevel;
            n.maxHP = nl.maxHP;
            n.currentHP = n.maxHP;
            n.maxHit = nl.maxHit;
            n.atkType = nl.atkType;
            n.weakness = nl.weakness;
            n.respawnDelay = nl.respawnDelay;
            n.attackEmote = nl.attackEmote;
            n.defendEmote = nl.defendEmote;
            n.deathEmote = nl.deathEmote;
            n.attackDelay = nl.attackDelay;
        }
        return index;
    }

    public static NPC newTargetNPC(int id, int absX, int absY, Player p) {
        int index = -1;

        for (int i = 1; i < npcs.length; i++) {
            if (npcs[i] == null) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            Misc.println("Max number of NPCs spawned.");
            return null;
        }

        NPC n = npcs[index] = new NPC(id, index);
        n.absX = absX;
        n.absY = absY;
        n.moveY = p.absY;
        n.moveX = p.absX;
        n.heightLevel = p.heightLevel;

        //n.followPlayer = p.playerId;
        //n.moveNowToPlayer(p);

        NPCList nl = npcLists[id];

        if (nl != null) {
            n.name = nl.npcName;
            n.combatLevel = nl.combatLevel;
            n.maxHP = nl.maxHP;
            n.currentHP = n.maxHP;
            n.maxHit = nl.maxHit;
            n.atkType = nl.atkType;
            n.weakness = nl.weakness;
            n.respawnDelay = nl.respawnDelay;
            n.attackEmote = nl.attackEmote;
            n.defendEmote = nl.defendEmote;
            n.deathEmote = nl.deathEmote;
            n.attackDelay = nl.attackDelay;
        }

        return n;

    }

    public static int newSummonNPC(int type, int absX, int absY, int height, int mRX1, int mRY1, int mRX2, int mRY2, boolean needsRespawn, int dude) {
        int index = -1;

        for (int i = 1; i < npcs.length; i++) {
            if (npcs[i] == null) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            Misc.println("Max number of NPCs spawned.");
            return -1;
        }
        NPC n = npcs[index] = new NPC(type, index);

        n.absX = absX;
        n.absY = absY;
        n.makeX = absX;
        n.makeY = absY;

        n.heightLevel = height;
        NPCList nl = npcLists[type];
        n.followPlayer = dude;
        Player p = Engine.players[dude];
        p.FamiliarID = n.npcId;
        if (type != 6901 && type != 6903 && type != 6905 && type != 6907) {
            p.requestAnim(1979, 0);
            n.requestGFX(1315, 0);
        }

        if (nl != null) {

            n.name = nl.npcName;
            n.combatLevel = nl.combatLevel;
            n.maxHP = nl.maxHP;
            n.currentHP = n.maxHP;
            n.maxHit = nl.maxHit;
            n.atkType = nl.atkType;
            n.weakness = nl.weakness;
            n.attackEmote = nl.attackEmote;
            n.defendEmote = nl.defendEmote;
            n.deathEmote = nl.deathEmote;
            n.attackDelay = nl.attackDelay;
        }
        return index;
    }
}
