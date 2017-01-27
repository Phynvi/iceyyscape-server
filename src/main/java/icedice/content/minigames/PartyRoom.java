package icedice.content.minigames;

/**
 * Josh Artuso
 * <p/>
 * The Party Room Mother Fucker
 */

import java.util.HashMap;

import icedice.players.Player;

public class PartyRoom {

    public static HashMap<Integer, Integer> partyItems = new HashMap<>();

    public void addItemToDrops(int itemId, int quantity) {
        this.partyItems.put(itemId, quantity);
    }

    public void showDropInterface(Player p) {
        p.frames.showInterface(p, 647);
        p.frames.setInventory(p, 763);
        int[] items = {995};
        int[] itemsN = {100};
        //p.frames.setInterfaceConfig(p, 763, 16, true);
        //p.frames.setItems(p, -1, 16, 93, items, itemsN);
        //p.frames.setite
        //p.frames.ItemOnInterface(p, 647, 3, 1, 995);
    }

    public void testInterface(Player p, int childId, int itemType) {
        p.frames.showInterface(p, 647);
        p.frames.setInventory(p, 763);
        int[] items = {995};
        int[] itemsN = {100};
        //p.frames.setItems(p, -1, childId, itemType, items, itemsN);
        p.frames.ItemOnInterface(p, 647, childId, 1, itemType);
    }

}
