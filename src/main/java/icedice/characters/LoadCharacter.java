package icedice.characters;

/**
 * Josh Artuso
 * <p/>
 * Load player attributes
 */

import icedice.Config;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import icedice.players.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class LoadCharacter {

    private final String charPath = Config.getPropertiesValue("CHARACTER_PATH");

    public void loadPlayer(Player p) {

        try {
            JSONParser parser = new JSONParser();
            JSONObject charObject = (JSONObject) parser.parse(new FileReader(charPath + p.username + ".json"));

            JSONArray cords = (JSONArray) charObject.get("cords");
            JSONArray look = (JSONArray) charObject.get("look");
            JSONArray color = (JSONArray) charObject.get("color");
            JSONArray items = (JSONArray) charObject.get("items");
            JSONArray equipment = (JSONArray) charObject.get("equipment");
            JSONArray bank = (JSONArray) charObject.get("bank");
            JSONArray skills = (JSONArray) charObject.get("skills");

            p.rights = Integer.parseInt(charObject.get("rights").toString());
            p.password = charObject.get("password").toString();
            p.teleportToX = Integer.parseInt(cords.get(0).toString());
            p.teleportToY = Integer.parseInt(cords.get(1).toString());
            p.heightLevel = Integer.parseInt(cords.get(2).toString());
            p.gender = Integer.parseInt(charObject.get("gender").toString());
            p.runEnergy = Integer.parseInt(charObject.get("runEnergy").toString());
            p.specialAmount = Integer.parseInt(charObject.get("spec").toString());
            p.attackStyle = Integer.parseInt(charObject.get("attackStyle").toString());
            p.friends = (List<Long>) charObject.get("friends");
            p.ignores = (List<Long>) charObject.get("ignores");

            // Load the players look
            for (int i = 0; i < look.size(); i++) {
                p.look[i] = Integer.parseInt(look.get(i).toString());
            }

            // Load the color of the players look
            for (int i = 0; i < color.size(); i++) {
                p.color[i] = Integer.parseInt(color.get(i).toString());
            }

            // Load the players inventory items
            for (int i = 0; i < items.size(); i++) {
                JSONObject itemObject = (JSONObject) items.get(i);
                for (Iterator iterator = itemObject.keySet().iterator(); iterator.hasNext(); ) {
                    Object itemId = iterator.next();
                    Object itemN = itemObject.get(itemId);
                    p.items[i] = Integer.parseInt(itemId.toString());
                    p.itemsN[i] = Integer.parseInt(itemN.toString());
                }
            }

            // Load the players equipped items
            for (int i = 0; i < equipment.size(); i++) {
                JSONObject itemObject = (JSONObject) equipment.get(i);
                for (Iterator iterator = itemObject.keySet().iterator(); iterator.hasNext(); ) {
                    Object itemId = iterator.next();
                    Object itemN = itemObject.get(itemId);
                    p.equipment[i] = Integer.parseInt(itemId.toString());
                    p.equipmentN[i] = Integer.parseInt(itemN.toString());
                }
            }

            // Load players banked items
            for (int i = 0; i < bank.size(); i++) {
                JSONObject itemObject = (JSONObject) bank.get(i);
                for (Iterator iterator = itemObject.keySet().iterator(); iterator.hasNext(); ) {
                    Object itemId = iterator.next();
                    Object itemN = itemObject.get(itemId);
                    p.bankItems[i] = Integer.parseInt(itemId.toString());
                    p.bankItemsN[i] = Integer.parseInt(itemN.toString());
                }
            }

            // Load player skills
            for (int i = 0; i < 24; i++) {
                JSONObject skillObject = (JSONObject) skills.get(i);
                for (Iterator iterator = skillObject.keySet().iterator(); iterator.hasNext(); ) {
                    Object skillLvl = iterator.next();
                    Object skillXp = skillObject.get(skillLvl);
                    p.skillLvl[i] = Integer.parseInt(skillLvl.toString());
                    p.skillXP[i] = Integer.parseInt(skillXp.toString());
                }
            }

            p.started = 1;

        } catch (FileNotFoundException e) {
            System.out.println("Error locating player save. New player? " + p.username);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
