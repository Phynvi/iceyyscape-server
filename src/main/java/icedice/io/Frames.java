
package icedice.io;


import icedice.Engine;
import icedice.util.Misc;
import icedice.players.Player;


public class Frames {

    public void systemUpdate(Player p, int val) {
        p.stream.createFrame(207);
        p.stream.writeWordBigEndian(val);
    }

    /**
     * Creates a Item on interface.
     *
     * @param p           The Player which the frame should be created for.
     * @param interfaceid The id of the interface to put to item on.
     * @param child       The child id on the interface.
     * @param itemsize    The size of the item model (used for itemam on smithing interface).
     * @param itemid      id of the item to print onto the interface
     */

    public void ItemOnInterface(Player p, int interfaceid, int child, int itemsize, int itemid) {
        int inter = ((interfaceid * 65536) + child);
        p.stream.createFrame(35);
        p.stream.writeDWord_v2(inter);
        p.stream.writeDWordBigEndian(itemsize);
        p.stream.writeWordBigEndianA(itemid);
    }

    public void resetGe(Player p, int slot) {
        p.stream.createFrame(137);
        p.stream.writeUnsignedByte(slot);
        p.stream.writeUnsignedByte(0);
        p.stream.writeWord(0);
        p.stream.writeDWord(0);
        p.stream.writeDWord(0);
        p.stream.writeDWord(0);
        p.stream.writeDWord(0);
    }

    public void setGe(Player p, int slot, int progress, int item, int price, int amount, int currentAmount) {
        p.stream.createFrame(137);
        p.stream.writeUnsignedByte(slot);
        p.stream.writeUnsignedByte(progress);
        p.stream.writeWord(item);
        p.stream.writeDWord(price);
        p.stream.writeDWord(amount);
        p.stream.writeDWord(currentAmount);
        p.stream.writeDWord(price * currentAmount);
    }

    public void setGeOnLogin(final Player p) {

    }

    public void setGeSearch(Player p, Object[] o) {
        setConfig1(p, 1109, -1);
        setConfig1(p, 1112, 0);
        setConfig1(p, 1113, 0);
        setInterface(p, 6, 752, 6, 389);
        p.stream.createFrameVarSizeWord(152);
        p.stream.writeString("s");
        String valstring = "s";
        int j = 0;
        for (int i = (valstring.length() - 1); i >= 0; i--) {
            if (valstring.charAt(i) == 115) {
                p.stream.writeString((String) o[j]);
            } else {
                p.stream.writeDWord((Integer) o[j]);
            }
            j++;
        }
        p.stream.writeDWord(570);
        p.stream.endFrameVarSize();
    }


    public void resetItemSlot(Player p, int slot) {
        int item = -1;
        int amount = 0;
        switch (slot) {
            case 0:
                p.frames.setItems(p, -1, -1757, 523, new int[]{item}, new int[]{amount});
                break;
            case 1:
                p.frames.setItems(p, -1, -1758, 524, new int[]{item}, new int[]{amount});
                break;
            case 2:
                p.frames.setItems(p, -1, -1759, 525, new int[]{item}, new int[]{amount});
                break;
            case 3:
                p.frames.setItems(p, -1, -1760, 526, new int[]{item}, new int[]{amount});
                break;
            case 4:
                p.frames.setItems(p, -1, -1761, 527, new int[]{item}, new int[]{amount});
                break;
            case 5:
                p.frames.setItems(p, -1, -1762, 528, new int[]{item}, new int[]{amount});
                break;
        }
    }

    public void setItemSlot(Player p, int slot, int item, int amount) {
        if (amount == 0) {
            return;
        }
        switch (slot) {
            case 0:
                p.frames.setItems(p, -1, -1757, 523, new int[]{item}, new int[]{amount});
                break;
            case 1:
                p.frames.setItems(p, -1, -1758, 524, new int[]{item}, new int[]{amount});
                break;
            case 2:
                p.frames.setItems(p, -1, -1759, 525, new int[]{item}, new int[]{amount});
                break;
            case 3:
                p.frames.setItems(p, -1, -1760, 526, new int[]{item}, new int[]{amount});
                break;
            case 4:
                p.frames.setItems(p, -1, -1761, 527, new int[]{item}, new int[]{amount});
                break;
            case 5:
                p.frames.setItems(p, -1, -1762, 528, new int[]{item}, new int[]{amount});
                break;
        }
    }


    public static int random(int range) { //0 till range (range INCLUDED)
        return (int) (java.lang.Math.random() * (range + 1));
    }

    /**
     * Sets item options allowed
     *
     * @param p      The Player which the frame should be created for.
     * @param set    The access mask
     * @param window The window or child interface id
     * @param inter  The main interface id
     * @param off    The item offset to start with
     * @param len    The item count to set
     */
    public void setAccessMask(Player p, int set, int window, int inter, int off, int len) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(223);
        p.stream.writeWord(len);
        p.stream.writeWordBigEndianA(off);
        p.stream.writeWordBigEndian(window);
        p.stream.writeWordBigEndian(inter);
        p.stream.writeWordBigEndian(set);
        p.stream.writeWordBigEndian(0);
    }

    /**
     * Creates a GFX on an absolute X and Y
     *
     * @param p   The player for which the frame should be created for.
     * @param x   The X coordinate that the GFX should be created on.
     * @param y   The Y coordinate that the GFX should be created on.
     * @param gfx The GFX ID that should be shown.
     *            Made by Lumby http://**************
     */
    public void gfx(Player p, int x, int y, int gfx) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        sendCoords(p, (x - ((p.mapRegionX - 6) * 8)), (y - ((p.mapRegionY - 6) * 8)));
        p.stream.createFrame(248);
        p.stream.writeByte(0);
        p.stream.writeWord(gfx);
        p.stream.writeByte(0);
        p.stream.writeWord(0);
    }

    /**
     * Runs an ClientScript2 script
     *
     * @param p         The Player which the frame should be created for.
     * @param id        The script id
     * @param o         The script arguments
     * @param valstring The argument types
     */
    public void runScript(Player p, int id, Object[] o, String valstring) {
        if (valstring.length() != o.length) {
            throw new IllegalArgumentException("Argument array size mismatch");
        }
        p.stream.createFrameVarSizeWord(152);
        p.stream.writeString(valstring);
        int j = 0;
        for (int i = (valstring.length() - 1); i >= 0; i--) {
            if (valstring.charAt(i) == 115) {
                p.stream.writeString((String) o[j]);
            } else {
                p.stream.writeDWord((Integer) o[j]);
            }
            j++;
        }
        p.stream.writeDWord(id);
        p.stream.endFrameVarSize();
    }

    /**
     * Connects to the friend and ignore servers
     *
     * @param p The player which the frame should be created for.
     */
    public void yell(String s) {
        for (Player p5 : Engine.players) {
            if (p5 == null)
                continue;
            if (!p5.online)
                continue;
            sendMessage(p5, s);
        }
    }

    /**
     * Plays a sound effect.
     *
     * @param p       The player for which the frame should be created for.
     * @param soundId the ID of the sound which you want to play.
     * @param j       Not sure might be something with looping sound...
     * @param delay   How long it should take before the sound plays, 50 is approx 1 second, 100 is approx 2, etc
     */
    public void playSound(Player p, int soundId, int j, int delay) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(119);
        p.stream.writeWord(soundId);
        p.stream.writeByte(j);
        p.stream.writeWord(delay);
    }

    /**
     * Plays a sound on a absolute X and Y and XXX squares around that, can use p.absX, p.absY...
     *
     * @param distance The distance players must be to hear the sound
     */
    public void playSoundInArea(int playX, int playY, int distance, int soundId, int j, int delay) {
        for (Player p : Engine.players) {
            if (p == null || p.stream == null || p.disconnected[0]) {
                continue;
            }
            if (Misc.getDistance(playX, playY, p.absX, p.absY) <= distance) {
                p.stream.createFrame(119);
                p.stream.writeWord(soundId);
                p.stream.writeByte(j);
                p.stream.writeWord(delay);
            }
        }
    }

    /**
     * Plays a sound for everyone that is online
     */
    public void playGlobalSound(int soundId, int j, int delay) {
        for (Player p : Engine.players) {
            if (p == null || p.stream == null || p.disconnected[0]) {
                continue;
            }
            p.stream.createFrame(119);
            p.stream.writeWord(soundId);
            p.stream.writeByte(j);
            p.stream.writeWord(delay);
        }
    }

    public void deleteObject(Player p, int objectX, int objectY) {
        sendCoords(p, (objectX - ((p.mapRegionX - 6) * 8)), (objectY - ((p.mapRegionY - 6) * 8)));
        p.stream.createFrame(196);
        p.stream.writeByteC(0);
        p.stream.writeByteC(0);
    }

    public void createObject(Player p, int objectId, int height, int objectX, int objectY, int face, int type) {
        sendCoords(p, (objectX - ((p.mapRegionX - 6) * 8)), (objectY - ((p.mapRegionY - 6) * 8)));
        int ot = ((type << 2) + (face & 3));
        p.stream.createFrame(30);
        p.stream.writeWordBigEndian(objectId);
        p.stream.writeByteA(0);
        p.stream.writeByteC(ot);

    }

    public void createObjectD(Player p, int objectId, int height, double objectX, double objectY, int face, int type) {
        sendCoords(p, (int) (objectX - ((p.mapRegionX - 6) * 8)), (int) (objectY - ((p.mapRegionY - 6) * 8)));
        int ot = ((type << 2) + (face & 3));
        p.stream.createFrame(30);
        p.stream.writeWordBigEndian(objectId);
        p.stream.writeByteA(0);
        p.stream.writeByteC(ot);

    }

    public void createGlobalObject(int objectId, int height, int objectX, int objectY, int face, int type) {
        for (Player p : Engine.players) {
            if (p == null) {
                continue;
            }
            createObject(p, objectId, height, objectX, objectY, face, type);
        }
    }

    public void sendPlayerCoords(Player p, int x, int y) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(218);
        p.stream.writeByteA(x);
        p.stream.writeByte(y);
    }

    public int getDistance(int coordX1, int coordY1, int coordX2, int coordY2) {
        int deltaX = coordX2 - coordX1;
        int deltaY = coordY2 - coordY1;
        return ((int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
    }

    public void connecttofserver(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(115);
        p.stream.writeByte(2);
    }

    public void sendSentPrivateMessage(Player p, long name, String message) {
        byte[] bytes = new byte[message.length()];
        Misc.encryptPlayerChat(bytes, 0, 0, message.length(), message.getBytes());
        p.stream.createFrameVarSize(89);
        p.stream.writeQWord(name);
        p.stream.writeByte(message.length());
        p.stream.writeBytes(bytes, bytes.length, 0);
        p.stream.endFrameVarSize();
    }

    private static int messageCounter = 1;

    public void sendReceivedPrivateMessage(Player p, long name, int rights, String message) {
        int id = messageCounter++;
        if (id > 16000000) {
            id = 1;
        }
        byte[] bytes = new byte[message.length() + 1];
        bytes[0] = (byte) message.length();
        Misc.encryptPlayerChat(bytes, 0, 1, message.length(), message.getBytes());
        p.stream.createFrameVarSize(178);
        p.stream.writeQWord(name);
        p.stream.writeWord(1);
        p.stream.writeByte(((id << 16) & 0xFF));
        p.stream.writeByte(((id << 8) & 0xFF));
        p.stream.writeByte(((id) & 0xFF));
        p.stream.writeByte(rights);
        p.stream.writeBytes(bytes, bytes.length, 0);
        p.stream.endFrameVarSize();
    }

    public void sendFriend(Player p, long name, int world) {
        p.stream.createFrameVarSize(154);
        p.stream.writeQWord(name);
        p.stream.writeWord(world);
        p.stream.writeByte(1);
        if (world != 0) {
            if (world == 1) {
                p.stream.writeString("Offline");
            } else {
                p.stream.writeString("Online");
            }
        }
        p.stream.endFrameVarSize();
    }

    public void sendIgnores(Player p, long[] ignores) {
        p.stream.createFrameVarSizeWord(240);
        for (long ignore : ignores) {
            p.stream.writeQWord(ignore);
        }
        p.stream.endFrameVarSizeWord();
    }

    /**
     * Set either fullscreen or normal.
     *
     * @param p   The Player which the frame should be created for.
     * @param set The frame set, 548 for the default setup.
     */
    public void setWindowPane(Player p, int set) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(239);
        p.stream.writeWord(set);
        p.stream.writeByteA(0);
    }

    /**
     * Logs a player out.
     *
     * @param p The Player which the frame should be created for.
     */
    public void logout(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(104);
    }

    /**
     * Display an interface.
     * <p>The various ids determines how the interface is displayed, from an overlay, to covering the chatbox, etc.
     *
     * @param p           The Player which the frame should be created for.
     * @param showId      Sets the interface such as an overlay, etc.
     * @param windowId    What type of window you used, default should be 548.
     * @param interfaceId Where to display it on the screen.
     * @param childId     The interface id to display.
     */
    public void setInterface(Player p, int showId, int windowId, int interfaceId, int childId) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(93);
        p.stream.writeWord(childId);
        p.stream.writeByteA(showId);
        p.stream.writeWord(windowId);
        p.stream.writeWord(interfaceId);
    }

    /**
     * Set a players click option.
     * <p>The slot cannot be below 0 and cannot be above 8.
     *
     * @param p      The Player which the frame should be created for.
     * @param option The string to set the option to.
     * @param slot   The position to set the option on the player.
     */
    public void setPlayerOption(Player p, String option, int slot) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrameVarSize(252);
        p.stream.writeByteC(0);
        p.stream.writeString(option);
        p.stream.writeByteC(slot);
        p.stream.endFrameVarSize();
    }

    public void setTopPlayerOption(Player p, String option, int slot) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrameVarSize(252);
        p.stream.writeByteC(1);
        p.stream.writeString(option);
        p.stream.writeByteC(slot);
        p.stream.endFrameVarSize();
    }

    public void setNPCId(Player p, int npcId, int interfaceId, int childId) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(6);
        p.stream.writeWordBigEndian(interfaceId);
        p.stream.writeWordBigEndian(childId);
        p.stream.writeWordBigEndian(npcId);
    }


    public void animateInterfaceId(Player p, int emoteId, int interfaceId, int childId) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(245);
        p.stream.writeWordBigEndian(interfaceId);
        p.stream.writeWordBigEndian(childId);
        p.stream.writeWord(emoteId);
    }

    /**
     * Setting client Configs.
     * <p>This is used for setting prayers, running, etc.
     *
     * @param p   The Player which the frame should be created for.
     * @param id  The Config id to set.
     * @param set What to set the Config.
     */
    public void setConfig(Player p, int id, int set) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        if (set < 128) {
            setConfig1(p, id, set);
        } else {
            setConfig2(p, id, set);
        }
    }

    public void setConfig1(Player p, int id, int set) {
        p.stream.createFrame(100);
        p.stream.writeWordA(id);
        p.stream.writeByteA(set);
    }

    public void setConfig2(Player p, int id, int set) {
        p.stream.createFrame(161);
        p.stream.writeWord(id);
        p.stream.writeDWord_v1(set);
    }

    /**
     * Creates a projectile. Can be used for magic, range etc.
     *
     * @param p           The Player which the frame should be created for.
     * @param CasterY     The caster absY
     * @param CasterX     The caster absX
     * @param offsetY     The distance between caster and enemy Y
     * @param offsetX     The distance between caster and enemy X
     * @param angle       The starting place of the projectile
     * @param speed       The speed minus the distance making it set.
     * @param gfxMoving   The moving graphic ID
     * @param startHeight The starting height
     * @param endHeight   The destination height
     * @param lockon      The NPC the missile is locked onto.
     */
    public void createProjectile(Player p, int casterY, int casterX, int offsetY, int offsetX, int angle,
                                 int speed, int gfxMoving, int startHeight, int endHeight, int lockon) {
        if (p == null || p.stream == null) {
            return;
        }
        sendCoords(p, (casterX - ((p.mapRegionX - 6) * 8)) - 3,
                (casterY - ((p.mapRegionY - 6) * 8)) - 2);
        p.stream.createFrame(112);
        p.stream.writeByte(angle);
        p.stream.writeByte(offsetX);
        p.stream.writeByte(offsetY);
        p.stream.writeRShort(lockon);
        p.stream.writeWord(gfxMoving);
        p.stream.writeByte(startHeight);
        p.stream.writeByte(endHeight);
        p.stream.writeWord(51);
        p.stream.writeWord(speed);
        p.stream.writeByte(16);
        p.stream.writeByte(64);
    }

    /**
     * Creates a Global Projectiles.
     *
     * @param Objectid The Id of the Object to spawn.
     * @param Heigh    The Height to spawn the Object on.
     * @param ObjectX  The AbsX to spawn the Object on.
     * @param ObjectY  The AbsY to spawn the Object on.
     * @param Face     The Position for the OBject to face
     * @param Type     Object Type
     */
    public void createGlobalProjectile(int casterY, int casterX, int offsetY, int offsetX, int gfxMoving, int startHeight, int endHeight, int speed, int atkIndex) {
        for (Player p : Engine.players) {
            if (p == null || p.disconnected[0]) {
                continue;
            }
            // createProjectile(p, casterY, casterX, offsetY, offsetX, angle, speed, gfxMoving, startHeight, endHeight, lockon)
            p.frames.createProjectile(p, casterY, casterX, offsetY, offsetX, 50,
                    speed, gfxMoving, startHeight, endHeight, atkIndex);
        }
    }

    public void setBankOptions(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(223);
        p.stream.writeWord(496);
        p.stream.writeWordBigEndianA(0);
        p.stream.writeWordBigEndian(73);
        p.stream.writeWordBigEndian(762);
        p.stream.writeWordBigEndian(1278);
        p.stream.writeWordBigEndian(20);
        p.stream.createFrame(223);
        p.stream.writeWord(27);
        p.stream.writeWordBigEndianA(0);
        p.stream.writeWordBigEndian(0);
        p.stream.writeWordBigEndian(763);
        p.stream.writeWordBigEndian(1150);
        p.stream.writeWordBigEndian(18);
    }

    /**
     * Set the run energy on the client.
     *
     * @param p The Player which the frame should be created for.
     */
    public void setEnergy(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(99);
        p.stream.writeByte(p.runEnergy);
    }

    /**
     * Display an interface on the chatbox.
     *
     * @param p       The Player which the frame should be created for.
     * @param childId The interface to display on the chatbox.
     */
    public void showChatboxInterface(Player p, int childId) {
        setInterface(p, 0, 752, 12, childId);
        p.chatboxInterfaceId = childId;
    }

    /**
     * Set the chatbox back removing any interfaces on it.
     *
     * @param p The Player which the frame should be created for.
     */
    public void removeChatboxInterface(Player p) {
        setConfig(p, 334, 1);
        p.stream.createFrame(246);
        p.stream.writeWord(752);
        p.stream.writeWord(12);
        p.chatboxInterfaceId = -1;
    }

    /**
     * Set the inventory.
     * @param p The Player which the frame should be created for.
     * @param childId The interface to display on the inventory.
     */
    /**
     * Setting a tab.
     *
     * @param p       The Player which the frame should be created for.
     * @param tabId   Which tab to display the interface on.
     * @param childId The interface to display on the tab.
     */
    public void setTab(Player p, int tabId, int childId) {
        if (!p.usingHD)
            setInterface(p, 1, childId == 137 ? 752 : 548, tabId, childId);
        else
            setInterface(p, 1, 746, tabId, childId);
    }

    /**
     * Set the overlay to be displayed.
     *
     * @param p       The Player which the frame should be created for.
     * @param childId The interface id to display as an overlay.
     */
    public void setOverlay(Player p, int childId) {
        if (!p.usingHD)
            setInterface(p, 1, 548, 5, childId);
        else
            setInterface(p, 1, 746, 5, childId);
    }

    /**
     * Remove any overlays that might be set.
     *
     * @param p The Player which the frame should be created for.
     */
    public void removeOverlay(Player p) {
        if (!p.usingHD)
            setInterface(p, 1, 548, 5, 56);
        else
            setInterface(p, 1, 746, 5, 56);
    }

    /**
     * Display an interface on the main area in the screen.
     *
     * @param p       The Player which the frame should be created for.
     * @param childId the interface id to display.
     */
    public void showInterface(Player p, int childId) {
        p.interfaceId = childId;
        if (!p.usingHD)
            setInterface(p, 0, 548, 8, childId);
        else
            setInterface(p, 0, 746, 3, childId);
    }

    /**
     * Remove an interface on the main screen.
     *
     * @param p The Player which the frame should be created for.
     */
    public void removeShownInterface(Player p) {
        if (!p.usingHD)
            setInterface(p, 1, 548, 8, 56);
        else
            setInterface(p, 1, 746, 3, 56);
        p.interfaceId = -1;
        p.input.resetInput();
    }

    /**
     * Set the inventory.
     *
     * @param p       The Player which the frame should be created for.
     * @param childId The interface to display on the inventory.
     */

    public void setInventory(Player p, int childId) {
        if (!p.usingHD)
            setInterface(p, 0, 548, 71, childId);
        else {
            setInterface(p, 0, 746, 71, childId);
        }
    }

    public void restoreInventory(Player p) {
        if (p.usingHD)
            setInterface(p, 1, 746, 71, 56);
        else
            setInterface(p, 1, 548, 71, 56);
        showTabs(p);
    }

    public void showTabs(Player p) {
        int maininterface = 548;
        if (p.usingHD) {
            maininterface = 746; /*Need to get correct Configs for it, but it works*/
        }
        for (int b = 16; b <= 21; b++) {
            setInterfaceConfig(p, maininterface, b, false);
        }
        for (int a = 32; a <= 38; a++) {
            setInterfaceConfig(p, maininterface, a, false);
        }
        setInterfaceConfig(p, maininterface, 14, false);
        setInterfaceConfig(p, maininterface, 31, false);
        setInterfaceConfig(p, maininterface, 63, false);
        setInterfaceConfig(p, maininterface, 72, false);
    }

    /**
     * Set interface defaults at login.
     *
     * @param p The Player which the frame should be created for.
     */
    public void setInterfaces(Player p) {
        if (p == null || p.disconnected[0]) {
            return;
        }
        if (!p.usingHD) {
            setTab(p, 6, 745);
            setTab(p, 11, 751); // Chat options
            setTab(p, 68, 752); // Chatbox
            setTab(p, 64, 748); // HP bar
            setTab(p, 65, 749); // Prayer bar
            setTab(p, 66, 750); // Energy bar
            setTab(p, 67, 747); // Summoning bar
            setTab(p, 8, 137); // Playername on chat
            setTab(p, 73, 92); // Attack tab
            setTab(p, 74, 320); // Skill tab
            setTab(p, 75, 274); // Quest tab
            setTab(p, 76, 149); // Inventory tab
            setTab(p, 77, 387); // Equipment tab
            setTab(p, 78, 271); // Prayer tab
            if (p.isAncients >= 1 || p.equipment[3] == 4675) {
                setInterface(p, 1, 548, 79, 193); //Ancients tab
            } else {
                setInterface(p, 1, 548, 79, 192); //Magic tab
            }
            setTab(p, 81, 550); // Friend tab
            setTab(p, 82, 551); // Ignore tab
            setTab(p, 83, 589); // Clan tab
            setTab(p, 84, 261); // Setting tab
            setTab(p, 85, 464); // Emote tab
            if (p.donator >= 1 || p.rights >= 1) {
                setTab(p, 86, 430); // Lunar tab
            } else if (p.donator <= 0) {
                setTab(p, 86, 187); // Music tab
            }
            setTab(p, 87, 182); // Logout tab
            setString(p, "Friends List", 550, 3);
            setString(p, "Ignore List", 551, 3);
            setString(p, "Clan Chat", 589, 7);
            setString(p, "Please click the button below to logout safely! Thank you!", 182, 0);
            setString(p, "-iCEYY!SCAPe-", 182, 6);
        } else if (p.usingHD) {
            setInterface(p, 1, 549, 0, 746); //Main interface
            setInterface(p, 1, 752, 8, 137); //Playername on chat
            setInterface(p, 1, 746, 87, 92); //Attack tab
            setInterface(p, 1, 746, 88, 320); //Skill tab
            setInterface(p, 1, 746, 89, 274); //Quest tab
            setInterface(p, 1, 746, 90, 149); //Inventory tab
            setInterface(p, 1, 746, 91, 387); //Equipment tab
            setInterface(p, 1, 746, 92, 271); //Prayer tab
            if (p.equipment[3] != 4675) {
                setInterface(p, 1, 746, 93, 192); //Magic tab
            } else if (p.equipment[3] == 4675) {
                setInterface(p, 1, 746, 93, 193); //Magic tab
                p.isAncients = 1;
            }
            setInterface(p, 1, 746, 95, 550); //Friend tab
            setInterface(p, 1, 746, 96, 551); //Ignore tab
            setInterface(p, 1, 746, 97, 589); //Clan tab
            setInterface(p, 1, 746, 98, 261); //Settings tab
            setInterface(p, 1, 746, 99, 464); //Emote tab
            setInterface(p, 1, 746, 65, 752); //Chatbox
            setInterface(p, 1, 746, 18, 751); //Chat options
            setInterface(p, 1, 746, 13, 748); //HP orb
            setInterface(p, 1, 746, 14, 749); //Prayer orb
            setInterface(p, 1, 746, 15, 750); //Energy orb
            setInterface(p, 1, 746, 12, 747); //Summoning orb
            setInterface(p, 1, 746, 100, 187); // Music tab
            setInterface(p, 1, 746, 101, 182); // Logout tab
            setString(p, "Friends List", 550, 3);
            setString(p, "Ignore List", 551, 3);
            setString(p, "Clan Chat", 589, 7);
            setString(p, "Once you are finished playing iceyy!scape, please use the log out button to log out safetly.", 182, 0);
            setString(p, "Good bye.", 182, 6);
        }
    }
    /**
     * Set interface defaults at login.
     * @param p The Player which the frame should be created for.
     */


    /**
     * Set interface defaults at login.
     *
     * @param p The Player which the frame should be created for.
     */
    public void setConfigs(Player p) {
        if (p == null || p.disconnected[0]) {
            return;
        }
        // setConfig(p, 1021, 1); tab flashing
        setConfig(p, 1160, -1);
        setConfig(p, 173, 0);
        setConfig(p, 313, -1);
        setConfig(p, 465, -1);
        setConfig(p, 802, -1);
        setConfig(p, 1085, 249852);
    }

    /**
     * /**
     * Set welcome interface.
     *
     * @param p The Player which the frame should be created for.
     */
    public void setWelcome(Player p) {
        if (p == null || p.disconnected[0]) {
            return;
        }
        p.frames.setWindowPane(p, 549);
        p.frames.setInterface(p, 1, 549, 2, 378);
        p.frames.setInterface(p, 1, 549, 3, 447); // can use 15, 17 and 447.
        p.frames.setString(p, "There are curently " + Engine.getPlayerCount() + " player(s) Online", 378, 116);
        p.frames.setString(p, "-iCEYY!SCAPe-", 447, 0);
        p.frames.setString(p, "Read the rules", 378, 14);
        p.frames.setString(p, "Rules are important", 378, 37);
        p.frames.setString(p, "Know the rules of the game! Please read the rule book or read the rules online at icedice.tk!", 378, 38);
        p.frames.setString(p, "", 378, 39);
        p.frames.setString(p, "Click me to play", 378, 45);
        p.frames.setString(p, "508-iCEYY!SCAPe-508", 378, 115);
        p.frames.setString(p, "Know the rules!", 378, 129);


        if (p.donator == 0) {//what non donators will see
            p.frames.setString(p, "Donate", 378, 7);
            p.frames.setString(p, "<col=8F>Welcome Back to -iCEYY!SCAPe- " + p.username + "!", 447, 1);
            p.frames.setString(p, "We are in need of donations. Please donate $5+ proceeds go to iceyyscape to help develop your gaming experince!", 378, 93);
            p.frames.setString(p, "Become a donator!", 378, 94);
            p.frames.setString(p, "0" + "", 378, 96);
            p.frames.setString(p, "-iCEYY!SCAPE-", 378, 138);


        } else if (p.donator == 1) {//what donators will see
            p.frames.setString(p, "-iCEYY!SCAPe-", 447, 0);
            p.frames.setString(p, "Thank You!", 378, 7);
            p.frames.setString(p, "<col=8F>Welcome Back to -iCEYY!SCAPe- " + p.username + "!", 447, 1);
            p.frames.setString(p, "Thank you for your donations! We asure you that your donations will be used only for -iCEYY!SCAPe-. Thank you.", 378, 93);
            p.frames.setString(p, "Thank you for donating!", 378, 94);
            p.frames.setString(p, "UNL." + "", 378, 96);
            p.frames.setString(p, "$-iCEYY!SCAPE-$", 378, 138);
        }

    }

    /**
     * Send coordinates, used with other frames.
     *
     * @param p The Player which the frame should be created for.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public void sendCoords(Player p, int x, int y) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(177);
        p.stream.writeByte(y);
        p.stream.writeByteS(x);
    }

    /**
     * Creates an item on the ground at itemX and itemY.
     *
     * @param p          The Player which the frame should be created for.
     * @param itemId     The item id to be displayed.
     * @param itemAmt    The amount the item stack size is.
     * @param itemX      The absolute x coordinate to display the item.
     * @param itemY      The absolute y coordinate to display the item.
     * @param itemHeight The height level to set the item.
     */
    public void createGroundItem(Player p, int itemId, int itemAmt, int itemX, int itemY, int itemHeight) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        if (Misc.getDistance(itemX, itemY, p.absX, p.absY) <= 60
                && p.heightLevel == itemHeight) {
            sendCoords(p, (itemX - ((p.mapRegionX - 6) * 8)),
                    (itemY - ((p.mapRegionY - 6) * 8)));
            p.stream.createFrame(25);
            p.stream.writeWordBigEndianA(itemAmt);
            p.stream.writeByte(0);
            p.stream.writeWordBigEndianA(itemId);
        }
    }

    /**
     * Removes an item on the ground at itemX and itemY.
     *
     * @param p          The Player which the frame should be created for.
     * @param itemId     The item id to remove.
     * @param itemX      The absolute x coordinate to remove the item.
     * @param itemY      The absolute y coordinate to remove the item.
     * @param itemHeight The height level toe remove the item at.
     */
    public void removeGroundItem(Player p, int itemId, int itemX, int itemY, int itemHeight) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        if (Misc.getDistance(itemX, itemY, p.absX, p.absY) <= 60
                && p.heightLevel == itemHeight) {
            sendCoords(p, (itemX - ((p.mapRegionX - 6) * 8)),
                    (itemY - ((p.mapRegionY - 6) * 8)));
            p.stream.createFrame(201);
            p.stream.writeByte(0);
            p.stream.writeWord(itemId);
        }
    }

    /**
     * Send players stat.
     *
     * @param p     The Player which the frame should be created for.
     * @param lvlId The stat id to send.
     */
    public void setSkillLvl(Player p, int lvlId) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(217);
        p.stream.writeByteC(p.skillLvl[lvlId]);
        p.stream.writeDWord_v2(p.skillXP[lvlId]);
        p.stream.writeByteC(lvlId);
    }

    /**
     * Set item display on an interface.
     *
     * @param p           The Player which the frame should be created for.
     * @param interfaceId The interface to display the items on.
     * @param childId     The child interface on the main interface.
     * @param itemArray   The item id array to set on the interface.
     * @param itemAmt     The item array to go with the itemArray.
     */
    public void setItems(Player p, int interfaceId, int childId, int type, int[] itemArray, int[] itemAmt) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrameVarSizeWord(255);
        p.stream.writeWord(interfaceId);
        p.stream.writeWord(childId);
        p.stream.writeWord(type);
        p.stream.writeWord(itemArray.length);
        for (int i = 0; i < itemArray.length; i++) {
            if (itemAmt[i] > 254) {
                p.stream.writeByteS(255);
                p.stream.writeDWord_v2(itemAmt[i]);
            } else {
                p.stream.writeByteS(itemAmt[i]);
            }
            p.stream.writeWordBigEndian(itemArray[i] + 1);
        }
        p.stream.endFrameVarSizeWord();
    }


    /**
     * Set interface Configs.
     * <p>This is used to do things such as hiding and displaying the special attack bar.
     *
     * @param p           The Player which the frame should be created for.
     * @param interfaceId The interface to the set the Config with.
     * @param childId     The child that belongs to the interface to change.
     * @param 1           for true, 0 for false.
     */
    public void setInterfaceConfig(Player p, int interfaceId, int childId, boolean set) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(59);
        p.stream.writeByteC(set ? 1 : 0);
        p.stream.writeWord(childId);
        p.stream.writeWord(interfaceId);
    }

    /**
     * Display a message in the chatbox.
     *
     * @param p The Player which the frame should be created for.
     * @param s The message to display in the chatbox.
     */
    public void sendMessage(Player p, String s) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrameVarSize(218);
        p.stream.writeString(s);
        p.stream.endFrameVarSize();
    }

    /**
     * Set a string on an interface.
     *
     * @param p           The Player which the frame should be created for.
     * @param str         The string to set on the interface.
     * @param interfaceId The interface to set the text on.
     * @param childId     The interface's child to set the text on.
     */
    public void setString(Player p, String str, int interfaceId, int childId) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        int sSize = str.length() + 5;

        p.stream.createFrame(179);
        p.stream.writeByte(sSize / 256);
        p.stream.writeByte(sSize % 256);
        p.stream.writeString(str);
        p.stream.writeWord(childId);
        p.stream.writeWord(interfaceId);
    }

    /**
     * Send this player's updated coordinates.
     *
     * @param p The Player which the frame should be created for.
     */
    public void updateMovement(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrameVarSizeWord(216);
        p.stream.initBitAccess();
        p.stream.writeBits(1, 1);
        if (p.runDir == -1) {
            p.stream.writeBits(2, 1);
            p.stream.writeBits(3, p.walkDir);
            p.stream.writeBits(1, p.updateReq ? 1 : 0);
        } else {
            p.stream.writeBits(2, 2);
            p.stream.writeBits(3, p.runDir);
            p.stream.writeBits(3, p.walkDir);
            p.stream.writeBits(1, p.updateReq ? 1 : 0);
            if (p.runEnergy > 0) {
                p.runEnergyUpdateReq = true;
                p.runEnergy--;
            } else {
                p.isRunning = false;
            }
        }
    }

    /**
     * Tell the client this player isn't moving.
     *
     * @param p The Player which the frame should be created for.
     */
    public void noMovement(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrameVarSizeWord(216);
        p.stream.initBitAccess();
        p.stream.writeBits(1, p.updateReq ? 1 : 0);
        if (p.updateReq) {
            p.stream.writeBits(2, 0);
        }
    }

    /**
     * Changes the coordinates this player is standing at.
     *
     * @param p The Player which the frame should be created for.
     */
    public void teleport(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrameVarSizeWord(216);
        p.stream.initBitAccess();
        p.stream.writeBits(1, 1);
        p.stream.writeBits(2, 3);
        p.stream.writeBits(7, p.currentX);
        p.stream.writeBits(1, 1);
        p.stream.writeBits(2, p.heightLevel);
        p.stream.writeBits(1, p.updateReq ? 1 : 0);
        p.stream.writeBits(7, p.currentY);
    }

    /**
     * Send the map region and other positioning info to the client.
     *
     * @param p The Player which the frame should be created for.
     */
    public void setMapRegion(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrameVarSizeWord(142);
        p.stream.writeWordA(p.mapRegionX);
        p.stream.writeWordBigEndianA(p.currentY);
        p.stream.writeWordA(p.currentX);
        boolean forceSend = true;
        p.rebuildNPCList = true;

        if ((((p.mapRegionX / 8) == 48) || ((p.mapRegionX / 8) == 49))
                && ((p.mapRegionY / 8) == 48)) {
            forceSend = false;
        }
        if (((p.mapRegionX / 8) == 48) && ((p.mapRegionY / 8) == 148)) {
            forceSend = false;
        }
        for (int xCalc = (p.mapRegionX - 6) / 8; xCalc
                <= ((p.mapRegionX + 6) / 8); xCalc++) {
            for (int yCalc = (p.mapRegionY - 6) / 8; yCalc
                    <= ((p.mapRegionY + 6) / 8); yCalc++) {
                int region = yCalc + (xCalc << 1786653352);

                if (forceSend
                        || ((yCalc != 49) && (yCalc != 149) && (yCalc != 147)
                        && (xCalc != 50) && ((xCalc != 49) || (yCalc != 47)))) {
                    int[] mapData = Engine.mapData.getData(region);

                    p.stream.writeDWord(mapData[0]);
                    p.stream.writeDWord(mapData[1]);
                    p.stream.writeDWord(mapData[2]);
                    p.stream.writeDWord(mapData[3]);
                }
            }
        }
        p.stream.writeByteC(p.heightLevel);
        p.stream.writeWord(p.mapRegionY);
        p.stream.endFrameVarSizeWord();
    }
}
