
package icedice.io.packets;


import icedice.Engine;
import icedice.players.Player;

public class ItemOption2 implements Packet {


    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        int itemSlot = p.stream.readUnsignedWordBigEndianA();
        int interfaceId = p.stream.readUnsignedWord();
        int junk = p.stream.readUnsignedWord();
        int itemId = p.stream.readUnsignedWord();

        if (itemSlot < 0 || itemId < 0) {
            return;
        }
        switch (interfaceId) {


            case 11696:
                if (Engine.playerItems.HasItemAmount(p, 11696, 1) == false)
                    handlePacket(p, packetId, packetSize);
                p.frames.sendMessage(p, "You take apart your Bandos godsword.");
                Engine.playerItems.deleteItem(p, 11696, Engine.playerItems.getItemSlot(p, 11696), 1);
                Engine.playerItems.addItem(p, 11690, 1);
                Engine.playerItems.addItem(p, 11704, 1);
                break;

            case 387: // Unequip item.
                if (itemSlot < p.equipment.length && p.equipment[itemSlot] == itemId) {
                    if (!Engine.playerItems.addItem(p, p.equipment[itemSlot],
                            p.equipmentN[itemSlot])) {
                        break;
                    }
                    p.equipment[itemSlot] = -1;
                    p.equipmentN[itemSlot] = 0;
                    p.frames.setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                    p.playerWeapon.setWeapon();
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                    p.calculateEquipmentBonus();
                }
                break;

            default:
                //Misc.println("[" + p.username + "] Item option 2: " + interfaceId +" "+ itemSlot + " "+ itemId +" "+ packetId);
                break;
        }
    }
}
