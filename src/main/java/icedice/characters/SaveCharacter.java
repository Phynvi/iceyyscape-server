package icedice.characters;

/**
 * Josh Artuso
 * This is where players get saved and stuff
 */

import icedice.Config;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import icedice.players.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SaveCharacter {

    private final String charPath = Config.getPropertiesValue("CHARACTER_PATH");

    @SuppressWarnings("unchecked")
    public void savePlayer(Player p) {

        // Return if player is a null;
        if (p == null)
            return;

        JSONObject charObject = new JSONObject();
        JSONArray charCords = new JSONArray();
        JSONArray charLook = new JSONArray();
        JSONArray charColor = new JSONArray();
        JSONArray items = new JSONArray();
        JSONArray equipment = new JSONArray();
        JSONArray bankItems = new JSONArray();
        JSONArray skills = new JSONArray();
        JSONArray friends = new JSONArray();
        JSONArray ignores = new JSONArray();

        // Player look
        for (int style : p.look)
            charLook.add(style);

        // Player colors
        for (int color : p.color)
            charColor.add(color);

        // Player inventory items
        for (int i = 0; i < p.items.length; i++) {
            JSONObject itemsObject = new JSONObject();
            itemsObject.put(p.items[i], p.itemsN[i]);
            items.add(itemsObject);
        }

        // Player equipped items
        for (int i = 0; i < p.equipment.length; i++) {
            JSONObject equipObject = new JSONObject();
            equipObject.put(p.equipment[i], p.equipmentN[i]);
            equipment.add(equipObject);
        }

        // Player banked items
        for (int i = 0; i < p.bankItems.length; i++) {
            JSONObject itemObject = new JSONObject();
            itemObject.put(p.bankItems[i], p.bankItemsN[i]);
            bankItems.add(itemObject);
        }

        // Player skills and xp
        for (int i = 0; i < 24; i++) {
            JSONObject skillObject = new JSONObject();
            skillObject.put(p.skillLvl[i], p.skillXP[i]);
            skills.add(skillObject);
        }

        // Player friends list
        for (Long friend : p.friends)
            friends.add(friend);

        // Player ignores list
        for (Long ignore : p.ignores)
            ignores.add(ignore);

        // Player position
        charCords.add(p.absX);
        charCords.add(p.absY);
        charCords.add(p.heightLevel);

        // Add all attributes to players json object
        charObject.put("username", p.username);
        charObject.put("password", p.password);
        charObject.put("rights", p.rights);
        charObject.put("cords", charCords);
        charObject.put("gender", p.gender);
        charObject.put("look", charLook);
        charObject.put("color", charColor);
        charObject.put("runEnergy", p.runEnergy);
        charObject.put("items", items);
        charObject.put("equipment", equipment);
        charObject.put("bank", bankItems);
        charObject.put("skills", skills);
        charObject.put("friends", friends);
        charObject.put("ignores", ignores);
        charObject.put("spec", p.specialAmount);
        charObject.put("attackStyle", p.attackStyle);

        try {
            File charFile = new File(this.charPath + p.username + ".json");
            Boolean fileCreated = true;
            if (!charFile.exists()) {
                fileCreated = charFile.createNewFile();
            }

            if (fileCreated) {
                FileWriter fileWriter = new FileWriter(charFile);
                fileWriter.write(charObject.toJSONString());
                fileWriter.flush();
                fileWriter.close();
                System.out.println("Saved player: " + p.username);
            }

        } catch (IOException e) {
            System.err.println("Error saving player");
            e.printStackTrace();
        }

    }

}
