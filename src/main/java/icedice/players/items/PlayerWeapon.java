

package icedice.players.items;


import icedice.Engine;
import icedice.players.Player;

public class PlayerWeapon {
    public Player p;

    public PlayerWeapon(Player _p) {
        p = _p;
    }

    public void setWeapon() {
        if (p == null) {
            return;
        }

        p.walkEmote = getWalkEmote(p.equipment[3]);
        p.runEmote = getRunEmote(p.equipment[3]);
        p.BlockEmote = getBlockEmote(p.equipment[3]);
        p.xpType = getXpType(p.equipment[3]);
        p.standEmote = getStandEmote(p.equipment[3]);
        p.attackEmote = getAttackEmote(p.equipment[3]);
        p.attackDelay = getAttackDelay(p.equipment[3]);
        String weapon = Engine.items.getItemName(p.equipment[3]);

        if (p.equipment[3] == -1) {
            p.frames.setTab(p, 73, 92);
            p.frames.setString(p, weapon, 92, 0);
            if (p.attackStyle == 3) {
                p.attackStyle = 2;
                p.frames.setConfig(p, 43, 2);
            }
        } else if (weapon.equals("Abyssal whip")
                || weapon.equals("Bandos Whip")) {
            p.frames.setTab(p, 73, 93);
            p.frames.setString(p, weapon, 93, 0);
            if (p.attackStyle == 3) {
                p.attackStyle = 2;
                p.frames.setConfig(p, 43, 2);
            }
        } else if (weapon.equals("Granite maul")
                || weapon.equals("Tzhaar-ket-om")
                || weapon.equals("Torags hammers")) {
            p.frames.setTab(p, 73, 76);
            p.frames.setString(p, weapon, 76, 0);
            if (p.attackStyle == 3) {
                p.attackStyle = 2;
                p.frames.setConfig(p, 43, 2);
            }
        } else if (weapon.equals("Veracs flail") || weapon.endsWith("Vesta's longsword") || weapon.endsWith("mace") || weapon.endsWith("anchor")) {
            p.frames.setTab(p, 73, 88);
            p.frames.setString(p, weapon, 88, 0);
        } else if (weapon.endsWith("crossbow") || weapon.endsWith("cbow") || weapon.endsWith(" c'bow")) {
            p.frames.setTab(p, 73, 79);
            p.frames.setString(p, weapon, 79, 0);
            if (p.attackStyle == 3) {
                p.attackStyle = 2;
                p.frames.setConfig(p, 43, 2);
            }
        } else if (weapon.endsWith("bow") || weapon.endsWith("bow full")
                || weapon.equals("Seercull")) {
            p.frames.setTab(p, 73, 77);
            p.frames.setString(p, weapon, 77, 0);
            if (p.attackStyle == 3) {
                p.attackStyle = 2;
                p.frames.setConfig(p, 43, 2);
            }
        } else if (weapon.startsWith("Staff") || weapon.endsWith("staff")
                || weapon.equals("Toktz-mej-tal")) {
            p.frames.setTab(p, 73, 90);
            p.frames.setString(p, weapon, 90, 0);
        } else if (weapon.endsWith("dart") || weapon.endsWith("knife")
                || weapon.endsWith("thrownaxe") || weapon.equals("Toktz-xil-ul")) {
            p.frames.setTab(p, 73, 91);
            p.frames.setString(p, weapon, 91, 0);
            if (p.attackStyle == 3) {
                p.attackStyle = 2;
                p.frames.setConfig(p, 43, 2);
            }
        } else if (weapon.endsWith("dagger") || weapon.endsWith("dagger(s)")
                || weapon.endsWith("dagger(+)") || weapon.endsWith("dagger(p)")) {
            p.frames.setTab(p, 73, 89);
            p.frames.setString(p, weapon, 89, 0);
        } else if (weapon.endsWith("pickaxe")) {
            p.frames.setTab(p, 73, 83);
            p.frames.setString(p, weapon, 83, 0);
        } else if (weapon.endsWith("axe") || weapon.endsWith("battleaxe")) {
            p.frames.setTab(p, 73, 75);
            p.frames.setString(p, weapon, 75, 0);
        } else if (weapon.endsWith("halberd")) {
            p.frames.setTab(p, 73, 84);
            p.frames.setString(p, weapon, 84, 0);
            if (p.attackStyle == 3) {
                p.attackStyle = 2;
                p.frames.setConfig(p, 43, 2);
            }
        } else if (weapon.endsWith("spear") || weapon.equals("Guthans warspear")) {
            p.frames.setTab(p, 73, 85);
            p.frames.setString(p, weapon, 85, 0);
            if (p.attackStyle == 3) {
                p.attackStyle = 2;
                p.frames.setConfig(p, 43, 2);
            }
        } else if (weapon.endsWith("claws")) {
            p.frames.setTab(p, 73, 78);
            p.frames.setString(p, weapon, 78, 0);
        } else if (weapon.endsWith("2h sword") || weapon.endsWith("godsword")
                || weapon.equals("Saradomin sword")) {
            p.frames.setTab(p, 73, 81);
            p.frames.setString(p, weapon, 81, 0);
        } else if (weapon.contains("scimi") || weapon.contains("sword") || weapon.contains("long sword")) {
            p.frames.setTab(p, 73, 82);
            p.frames.setString(p, weapon, 82, 0);
        } else {
            p.frames.setTab(p, 73, 92);
            p.frames.setString(p, weapon, 92, 0);
        }
        checkSpecials(p);
    }

    public int getRunEmote(int id) {
        String weapon = Engine.items.getItemName(id);

        if (id == 4718 || weapon.endsWith("2h sword") || id == 6528
                || weapon.endsWith("godsword")
                || weapon.equals("Saradomin sword")) {
            return 7039;
        } else if (weapon.equals("Saradomin staff")
                || weapon.equals("Guthix staff")
                || weapon.equals("Zamorak staff")) {
            return 0x338;
        } else if (id == 4755) {
            return 1831;
        } else if (id == 11259) {
            return 0x680;
        } else if (id == 4734) {
            return 2077;
        } else if (id == 4726 || weapon.contains("Spear")
                || weapon.endsWith("halberd") || weapon.contains("Staff")
                || weapon.contains("staff")) {
            return 1210;
        } else if (weapon.equals("Abyssal whip") || weapon.equals("Bandos Whip")) {
            return 1661;
        } else if (weapon.equals("Barrelchest anchor")) {
            return 5868;
        } else if (id == 4153) {
            return 1664;
        }
        return 0x338;
    }

    public int getBlockEmote(int id) {
        int emote = 424;
        String weapon = Engine.items.getItemName(id);
        String shield = Engine.items.getItemName(p.equipment[5]);
        if (p.equipment[5] != -1) {
            if (shield.contains("defender")) {
                return emote = 4177;
            } else if (!shield.contains("defender")) {
                return emote = 403;
            }
        } else {
            if (weapon.equals("Abyssal whip")) {
                return emote = 1659;
            } else if (weapon.contains("2h") || weapon.endsWith("godsword") || weapon.equals("Saradomin sword")) {
                return emote = 7050;
            } else if (weapon.equals("Veracs flail")) {
                return emote = 2063;
            } else if (weapon.equals("Granite maul")) {
                return emote = 1666;
            } else if (weapon.contains("staff") || weapon.contains("Staff")) {
                return emote = 2079;
            } else if (weapon.contains("battleaxe")) {
                return emote = 383;
            }
        }
        return emote;
    }

    public int getWalkEmote(int id) {
        String weapon = Engine.items.getItemName(id);

        if (weapon.equals("Saradomin staff") || weapon.equals("Guthix staff")
                || weapon.equals("Zamorak staff")) {
            return 0x333;
        } else if (id == 4755) {
            return 2060;
        } else if (id == 10887) {
            return 5867;
        } else if (id == 11259) {
            return 0x67F;
        } else if (id == 4734) {
            return 2076;
        } else if (id == 4153) {
            return 1663;
        } else if (weapon.equals("Abyssal whip") || weapon.equals("Bandos Whip")) {
            return 1660;
        } else if (id == 4718 || weapon.endsWith("2h sword") || id == 6528
                || weapon.endsWith("godsword")
                || weapon.equals("Saradomin sword")) {
            return 7046;
        } else if (id == 4726 || weapon.contains("spear")
                || weapon.endsWith("halberd") || weapon.contains("Staff")
                || weapon.contains("staff")) {
            return 1146;
        }
        return 0x333;
    }

    public int getStandEmote(int id) {
        String weapon = Engine.items.getItemName(id);
        if (id == 4151 || id == 13636) {
            return 10080;
        }
        if (id == 4718) {
            return 2065;
        } else if (id == 11259) {
            return 0x811;
        } else if (id == 4755) {
            return 2061;
        } else if (id == 10887) {
            return 5869;
        } else if (id == 4734) {
            return 2074;
        } else if (id == 6528 || id == 1319) {
            return 0x811;
        } else if (weapon.equals("Saradomin staff")
                || weapon.equals("Guthix staff")
                || weapon.equals("Zamorak staff")) {
            return 0x328;
        } else if (id == 4726 || weapon.endsWith("spear")
                || weapon.endsWith("halberd") || weapon.contains("Staff")
                || weapon.contains("staff") || weapon.equals("Dragon longsword")) {
            return 809;
        } else if (weapon.endsWith("2h sword") || weapon.endsWith("godsword")
                || weapon.equals("Saradomin sword")) {
            return 7047;
        } else if (weapon.equals("Abyssal whip") || weapon.contains("Dragon dagger") || weapon.contains("claws")) {
            return 1832;
        } else if (id == 4153) {
            return 1662;
        } else if (weapon.endsWith("longsword") && !weapon.equals("Dragon longsword") || weapon.contains("scimitar")
                || weapon.endsWith("sword") || weapon.contains("claw") || weapon.endsWith("battleaxe")) {
            return 813;
        }
        return 0x328;
    }

    public int getAttackEmote(int id) {
        String weapon = Engine.items.getItemName(id);

        if (p.equipment[3] == -1) {
            if (p.attackStyle == 0 || p.attackStyle == 2) {
                return 422;
            }
            if (p.attackStyle == 1) {
                return 423;
            }
        } else if (weapon.contains("2h") || weapon.equals("Tzhaar-ket-om")
                || weapon.endsWith("godsword") || weapon.equals("Saradomin sword")) {
            if (p.attackStyle == 0 || p.attackStyle == 2) {
                return 7041;
            }
            if (p.attackStyle == 1) {
                return 7042;
            }
            if (p.attackStyle == 3) {
                return 410;
            }
        } else if (weapon.endsWith("longsword") || weapon.contains("scimitar") || weapon.endsWith("sword")) {
            if (p.attackStyle != 2) {
                return 451;
            } else {
                return 428;
            }
        } else if (weapon.contains("claw")) {
            if (p.attackStyle == 0 || p.attackStyle == 1 || p.attackStyle == 3) {
                return 451;
            }
            if (p.attackStyle == 2) {
                return 1064;
            }
        } else if (weapon.contains("whip")) {
            if (p.attackStyle == 0) {
                return 1658;
            }
            if (p.attackStyle == 1 || p.attackStyle == 2) {
                return 451;
            }
        } else if (weapon.equals("Barrelchest anchor")) {
            if (p.attackStyle >= 0 || p.attackStyle <= 3) {
                return 5865;
            }
        } else if (weapon.contains("Dragon dag")) {
            if (p.attackStyle != 2) {
                return 402;
            } else {
                return 401;
            }
        } else if (weapon.endsWith("dagger") && !weapon.contains("Dragon")) {
            if (p.attackStyle != 2) {
                return 400;
            } else {
                return 401;
            }
        } else if (weapon.contains("greataxe")) {
            if (p.attackStyle != 2) {
                return 2067;
            } else {
                return 2066;
            }
        } else if (weapon.equals("Granite maul") || weapon.equals("Ahrims staff")) {
            if (p.attackStyle >= 0 || p.attackStyle <= 3) {
                return 1665;
            }
        } else if (weapon.equals("Guthans warspear") || weapon.endsWith("spear")) {
            if (p.attackStyle == 0) {
                return 2081;
            }
            if (p.attackStyle == 1) {
                return 2082;
            }
            if (p.attackStyle == 2) {
                return 2080;
            }
        } else if (weapon.endsWith("halberd")) {
            if (p.attackStyle == 0 || p.attackStyle == 2) {
                return 438;
            }
            if (p.attackStyle == 1) {
                return 440;
            }
        } else if (weapon.endsWith("battleaxe")) {
            if (p.attackStyle == 2) {
                return 437;
            } else {
                return 395;
            }
        } else if (id == 9185) {
            return 427;
        } else if (id == 4734) {
            return 2075;
        } else if (id == 4747) {
            return 2068;
        } else if (id == 4755) {
            return 2062;
        } else if (weapon.endsWith("shortbow") || weapon.endsWith("bow full")) {
            return 426;
        }
        return 422;
    }

    public int getAttackDelay(int id) {
        String weapon = Engine.items.getItemName(id);

        if (weapon.endsWith("2h sword") || weapon.endsWith("godsword")
                || weapon.equals("Saradomin sword")) {
            return 7;
        } else if (weapon.endsWith("battleaxe")) {
            return 6;
        } else if (weapon.endsWith("longsword")) {
            return 5;
        } else if (weapon.equals("Abyssal whip") || weapon.endsWith("scimitar")
                || weapon.endsWith("dagger") || weapon.endsWith("Bandos Whip")) {
            return 4;
        }
        return 5;
    }

    public int getXpType(int id) {
        int skill = -1;
        String weapon = Engine.items.getItemName(id);
        if (p.equipment[3] == -1 || weapon.equals("Granite maul") || weapon.equals("Guthans warspear")
                || weapon.endsWith("spear")) {
            if (p.attackStyle == 0) { // Attack
                return skill = 0;
            }
            if (p.attackStyle == 1) {// Stength
                return skill = 2;
            }
            if (p.attackStyle == 2) {// Defence
                return skill = 1;
            }
        } else if (weapon.contains("dagger") || weapon.endsWith("greataxe")
                || weapon.contains("2h") || weapon.endsWith("godsword") || weapon.equals("Saradomin sword")) {
            if (p.attackStyle == 0) { // Attack
                return skill = 0;
            }
            if (p.attackStyle == 1 || p.attackStyle == 2) {
                return skill = 2; // Strength x2
            }
            if (p.attackStyle == 3) {
                return skill = 1; // Defence
            }
        } else if (weapon.endsWith("longsword") || weapon.contains("scimitar") || weapon.endsWith("sword")) {
            if (p.attackStyle == 0) { // Attack
                return skill = 0;
            }
            if (p.attackStyle == 1) { // Strength
                return skill = 2;
            }
            if (p.attackStyle == 2) { // Shared
                return skill = 30;
            }
            if (p.attackStyle == 3) { // Defence
                return skill = 1;
            }
        } else if (weapon.endsWith("halberd")) {
            if (p.attackStyle == 0) {
                return skill = 30;
            }
            if (p.attackStyle == 1) {
                return skill = 2;
            }
            if (p.attackStyle == 2) {
                return skill = 1;
            }
        }
        return skill;
    }

    public void checkSpecials(Player p) {
        int weaponId = p.equipment[3];

        if (weaponId == 4151 || weaponId == 13636) {
            p.frames.setInterfaceConfig(p, 93, 10, false);

        } else if (weaponId == 1215 || weaponId == 1231 || weaponId == 5680
                || weaponId == 5698 || weaponId == 8872 || weaponId == 8874
                || weaponId == 8876 || weaponId == 8878) {
            p.frames.setInterfaceConfig(p, 89, 12, false);
        } else if (weaponId == 35 || weaponId == 1305 || weaponId == 4587
                || weaponId == 6746 || weaponId == 11037) {
            p.frames.setInterfaceConfig(p, 82, 12, false);
        } else if (weaponId == 7158 || weaponId == 10887 || weaponId == 1215 || weaponId == 11694 || weaponId == 11696
                || weaponId == 11698 || weaponId == 11700 || weaponId == 11730) {
            p.frames.setInterfaceConfig(p, 81, 12, false);
        } else if (weaponId == 859 || weaponId == 861 || weaponId == 6724
                || weaponId == 10284 || weaponId == 859 || weaponId == 11235) {
            p.frames.setInterfaceConfig(p, 77, 13, false);
        } else if (weaponId == 8880) {
            p.frames.setInterfaceConfig(p, 79, 10, false);
        } else if (weaponId == 3101 || weaponId == 1337) {
            p.frames.setInterfaceConfig(p, 78, 12, false);
        } else if (weaponId == 1434 || weaponId == 11061 || weaponId == 10887) {
            p.frames.setInterfaceConfig(p, 88, 12, false);
        } else if (weaponId == 1377 || weaponId == 6739) {
            p.frames.setInterfaceConfig(p, 75, 12, false);
        } else if (weaponId == 4153 || weaponId == 1215) {
            p.frames.setInterfaceConfig(p, 76, 10, false);
        } else if (weaponId == 3204) {
            p.frames.setInterfaceConfig(p, 84, 10, false);
        } else {// nothing
        }
    }
}
