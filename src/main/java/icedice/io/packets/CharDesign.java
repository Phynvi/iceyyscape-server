package icedice.io.packets;

import icedice.players.Player;

public class CharDesign {

    public void charScreenTabs(Player p, int button) {
        switch (button) {
            case 14:
                resetCharScreenTabs(p);
                mouseOptions = true;
                sendMouseOptions(p, button);
                break;
            case 16:
                resetCharScreenTabs(p);
                genderOptions = true;
                sendGenderOptions(p, button);
                break;
            case 18:
                resetCharScreenTabs(p);
                headOptions = true;
                sendHeadOptions(p, button);
                break;
            case 20:
                resetCharScreenTabs(p);
                bodyOptions = true;
                sendBodyOptions(p, button);
                break;
        }
    }

    public void sendMouseOptions(Player p, int button) {
        switch (button) {
            case 37://two buttons
            case 40://one button
            case 46://next button
                break;
        }
    }

    public void sendGenderOptions(Player p, int button) {
        switch (button) {
            case 49://male
            case 52://female
            case 56://back button
            case 58://next button
                break;
        }
    }

    public void sendHeadOptions(Player p, int button) {
        switch (button) {
            case 61://Skin colour
            case 62://Hair colour
            case 92://hair -1
            case 93://hair +1
            case 97://facial hair -1
            case 98://facial hair +1
            case 100://char hair color
            case 101://char hair color
            case 102://char hair color
            case 103://char hair color
            case 104://char hair color
            case 105://char hair color
            case 106://char hair color
            case 107://char hair color
            case 108://char hair color
            case 109://char hair color
            case 110://char hair color
            case 111://char hair color
            case 112://char hair color
            case 113://char hair color
            case 114://char hair color
            case 115://char hair color
            case 116://char hair color
            case 117://char hair color
            case 118://char hair color
            case 119://char hair color
            case 120://char hair color
            case 121://char hair color
            case 122://char hair color
            case 123://char hair color
            case 124://char hair color
            case 167://next button
            case 169://randomize button
            case 171://back button
                break;
        }
    }

    public void sendBodyOptions(Player p, int button) {
        switch (button) {
            case 174://feet button
            case 176://legs button
            case 178://torso button
            case 189://char torso colour
            case 190://char torso colour
            case 191://char torso colour
            case 192://char torso colour
            case 193://char torso colour
            case 194://char torso colour
            case 195://char torso colour
            case 196://char torso colour
            case 197://char torso colour
            case 198://char torso colour
            case 199://char torso colour
            case 200://char torso colour
            case 201://char torso colour
            case 202://char torso colour
            case 203://char torso colour
            case 204://char torso colour
            case 205://char torso colour
            case 206://char torso colour
            case 207://char torso colour
            case 208://char torso colour
            case 209://char torso colour
            case 210://char torso colour
            case 211://char torso colour
            case 212://char torso colour
            case 213://char torso colour
            case 214://char torso colour
            case 215://char torso colour
            case 216://char torso colour
            case 217://char torso colour
            case 249://char legs colour
            case 250://char legs colour
            case 251://char legs colour
            case 252://char legs colour
            case 253://char legs colour
            case 254://char legs colour
            case 255://char legs colour
            case 256://char legs colour
            case 257://char legs colour
            case 258://char legs colour
            case 259://char legs colour
            case 260://char legs colour
            case 261://char legs colour
            case 262://char legs colour
            case 263://char legs colour
            case 264://char legs colour
            case 265://char legs colour
            case 267://char legs colour
            case 268://char legs colour
            case 269://char legs colour
            case 270://char legs colour
            case 271://char legs colour
            case 272://char legs colour
            case 273://char legs colour
            case 274://char legs colour
            case 275://char legs colour
            case 276://char legs colour
            case 307://char skin colour
            case 308://char skin colour
            case 309://char skin colour
            case 310://char skin colour
            case 311://char skin colour
            case 312://char skin colour
            case 319://confirm button
            case 321://randomize button
            case 323://back button
            case 341://torso -1
            case 342://torso +1
            case 345://arms -1
            case 346://arms +1
            case 349://wrists -1
            case 350://wrists +1
            case 353://legs -1
            case 354://legs +1
            case 357://feet -1
            case 358://feet +1
                break;
        }
    }

    public void sendConfirmOptions(Player p, int button) {
        switch (button) {
            case 360://no
            case 362://yes
                break;
        }
    }

    public void resetCharScreenTabs(Player p) {
        mouseOptions = false;
        genderOptions = false;
        headOptions = false;
        bodyOptions = false;
    }

    private boolean mouseOptions = false;
    private boolean genderOptions = false;
    private boolean headOptions = false;
    private boolean bodyOptions = false;
}