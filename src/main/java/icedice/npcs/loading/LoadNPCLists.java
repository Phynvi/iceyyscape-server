package icedice.npcs.loading;

import java.io.*;

import icedice.Config;
import icedice.Engine;
import icedice.util.Misc;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoadNPCLists {

    /**
     * Constructs a new LoadNPCLists class.
     */
    public LoadNPCLists() {
        loadNPCList();
        loadNPCs();
    }

    /**
     * Loads NPCs which will be spawned.
     */
    private boolean loadNPCs() {
        String line = "", token = "", token2 = "", token2_2 = "", token3[] = new String[10];
        BufferedReader cfgFile = null;

        try {
            cfgFile = new BufferedReader(
                    new FileReader(Config.getPropertiesValue("NPC_SPAWN_PATH")));
            line = cfgFile.readLine().trim();
        } catch (Exception e) {
            Misc.println("Error loading NPCs.");
            line = token = token2 = token2_2 = null;
            token3 = null;
            return false;
        }
        while (line != null) {
            int index = line.indexOf("=");

            if (index > -1) {
                token = line.substring(0, index).trim();
                token2 = line.substring(index + 1).trim();
                token2_2 = token2.replaceAll("\t\t", "\t");
                token3 = token2_2.split("\t");
                if (token.equals("spawn")) {
                    Engine.newNPC(Integer.parseInt(token3[0]),
                            Integer.parseInt(token3[1]),
                            Integer.parseInt(token3[2]),
                            Integer.parseInt(token3[3]),
                            Integer.parseInt(token3[4]),
                            Integer.parseInt(token3[5]),
                            Integer.parseInt(token3[6]),
                            Integer.parseInt(token3[7]), true);
                }
            } else if (line.equals("[ENDOFSPAWNLIST]")) {
                try {
                    cfgFile.close();
                } catch (Exception ioe) {
                }
                cfgFile = null;
                line = token = token2 = token2_2 = null;
                token3 = null;
                return true;
            }
            try {
                line = cfgFile.readLine();
            } catch (Exception ioe) {
                line = null;
                line = token = token2 = token2_2 = null;
                token3 = null;
                cfgFile = null;
                return false;
            }
        }
        try {
            cfgFile.close();
            cfgFile = null;
        } catch (Exception ioe) {
        }
        return false;
    }

    /**
     * Loads NPC hp, max hits, spawn times, etc.
     */
    private boolean loadNPCList() {
        JSONParser parser = new JSONParser();
        boolean successful = true;
        try {
            JSONArray parentObject = (JSONArray) parser.parse(new FileReader(Config.getPropertiesValue("NPC_STAT_PATH")));
            for (Object obj : parentObject) {
                int id = Integer.parseInt(((JSONObject) obj).get("ID").toString());
                int lvl = Integer.parseInt(((JSONObject) obj).get("LVL").toString());
                int hp = Integer.parseInt(((JSONObject) obj).get("HP").toString());
                int maxHit = Integer.parseInt(((JSONObject) obj).get("MaxHit").toString());
                int attType = Integer.parseInt(((JSONObject) obj).get("AttackType").toString());
                int weakness = Integer.parseInt(((JSONObject) obj).get("Weakness").toString());
                int spawnTime = Integer.parseInt(((JSONObject) obj).get("RespawnTime").toString());
                int attEmote = Integer.parseInt(((JSONObject) obj).get("Attack").toString());
                int defEmote = Integer.parseInt(((JSONObject) obj).get("Block").toString());
                int dieEmote = Integer.parseInt(((JSONObject) obj).get("Die").toString());
                int attDelay = Integer.parseInt(((JSONObject) obj).get("AttackDelay").toString());
                String name = ((JSONObject) obj).get("Name").toString();
                String description = ((JSONObject) obj).get("Description").toString();
                newNPCList(id, lvl, hp, maxHit, attType, weakness, spawnTime, attEmote, defEmote, dieEmote, attDelay, name, description);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            successful = false;
        }

        return successful;
    }
    /*private boolean loadNPCList() {
        String line = "", token = "", token2 = "", token2_2 = "", token3[] = new String[13];
        BufferedReader list = null;

        try {
            list = new BufferedReader(new FileReader(Config.getPropertiesValue("NPC_STAT_PATH")));
            line = list.readLine().trim();
        } catch (Exception e) {
            Misc.println("Error loading NPC Lists.");
            return false;
        }
        while (line != null) {
            int spot = line.indexOf("=");

            if (spot > -1) {
                token = line.substring(0, spot).trim();
                token2 = line.substring(spot + 1).trim();
                token2_2 = token2.replaceAll("\t\t", "\t");
                token2_2 = token2_2.replaceAll("\t\t", "\t");
                token2_2 = token2_2.replaceAll("\t\t", "\t");

                token3 = token2_2.split("\t");
                if (token.equals("npc")) {
                    newNPCList(Integer.parseInt(token3[0]),
                            Integer.parseInt(token3[1]),
                            Integer.parseInt(token3[2]),
                            Integer.parseInt(token3[3]),
                            Integer.parseInt(token3[4]),
                            Integer.parseInt(token3[5]),
                            Integer.parseInt(token3[6]),
                            Integer.parseInt(token3[7]),
                            Integer.parseInt(token3[8]),
                            Integer.parseInt(token3[9]),
                            Integer.parseInt(token3[10]),
                            token3[11].replaceAll("_", " "),
                            token3[12].replaceAll("_", " "));
                }
            } else {
                if (line.equals("[ENDOFNPCLIST]")) {
                    try {
                        list.close();
                    } catch (Exception ioexception) {
                    }
                    list = null;
                    return true;
                }
            }
            try {
                line = list.readLine().trim();
            } catch (Exception ioexception1) {
                try {
                    list.close();
                } catch (Exception ioexception) {
                }
                list = null;
                return true;
            }
        }
        return false;
    }*/

    /**
     * Creates a class for storing an NPC's stats.
     */
    private void newNPCList(int npcType, int cbLevel, int maxHP, int maxHit, int atkType, int weakness, int spawnTime, int attEmote, int defEmote, int deadEmote, int attDelay, String name, String examine) {
        if (npcType >= Engine.maxListedNPCs) {
            Misc.println(
                    "In NPCList.cfg you cannot exceed " + Engine.maxListedNPCs
                            + ".");
            return;
        }

        Engine.npcLists[npcType] = new NPCList(npcType, name, examine, cbLevel,
                maxHP, maxHit, atkType, weakness, spawnTime, attEmote, defEmote,
                deadEmote, attDelay);
    }
}
