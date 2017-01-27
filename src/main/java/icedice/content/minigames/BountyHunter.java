package icedice.content.minigames;

import java.util.ArrayList;
import java.util.List;

import icedice.util.Misc;
import icedice.Engine;
import icedice.content.geList;
import icedice.players.Player;

public class BountyHunter {

    public static List<Integer> lowCrator = new ArrayList<Integer>(200);
    public static List<Integer> midCrator = new ArrayList<Integer>(200);
    public static List<Integer> highCrator = new ArrayList<Integer>(200);

    public int getTargetLow(Player p) {
        System.out.println(lowCrator.size());
        if (lowCrator.size() == 0) {
            return 0;
        }
        int i = Misc.random(lowCrator.size());
        i--;
        if (i == -1) {
            i++;
        }
        if (lowCrator.get(i) == p.playerId) {
            for (int i2 = 0; i2 < lowCrator.size(); i2++) {
                if (lowCrator.get(i2) != p.playerId) {
                    return lowCrator.get(i2);
                }
            }
        } else {
            return lowCrator.get(i);
        }
        return 0;
    }

    public int getTargetMid(Player p) {
        System.out.println(midCrator.size());
        if (midCrator.size() == 0) {
            return 0;
        }
        int i = Misc.random(midCrator.size());
        i--;
        if (i == -1) {
            i++;
        }
        if (midCrator.get(i) == p.playerId) {
            for (int i2 = 0; i2 < midCrator.size(); i2++) {
                if (midCrator.get(i2) != p.playerId) {
                    return midCrator.get(i2);
                }
            }
        } else {
            return midCrator.get(i);
        }
        return 0;
    }

    public int getTargetHigh(Player p) {
        System.out.println(highCrator.size());
        if (highCrator.size() == 0) {
            return 0;
        }
        int i = Misc.random(highCrator.size());
        i--;
        if (i == -1) {
            i++;
        }
        if (highCrator.get(i) == p.playerId) {
            for (int i2 = 0; i2 < highCrator.size(); i2++) {
                if (highCrator.get(i2) != p.playerId) {
                    return highCrator.get(i2);
                }
            }
        } else {
            return highCrator.get(i);
        }
        return 0;
    }

    /*have to do it this way to prevent nullpointer*/
    public void removeLow(Player p) {
        for (int i = 0; i < lowCrator.size(); i++) {
            if (lowCrator.get(i) == p.playerId) {
                lowCrator.remove(lowCrator.get(i));
            }
        }
    }

    public void removeMid(Player p) {
        for (int i = 0; i < midCrator.size(); i++) {
            if (midCrator.get(i) == p.playerId) {
                midCrator.remove(midCrator.get(i));
            }
        }
    }

    public void removeHigh(Player p) {
        for (int i = 0; i < highCrator.size(); i++) {
            if (highCrator.get(i) == p.playerId) {
                highCrator.remove(highCrator.get(i));
            }
        }
    }

    public void enterLow(Player p) {
        teleEnter(p, 0);
        lowCrator.add(p.playerId);
    }

    public void enterMid(Player p) {
        teleEnter(p, 4);
        midCrator.add(p.playerId);
    }

    public void enterHigh(Player p) {
        teleEnter(p, 8);
        highCrator.add(p.playerId);
    }

    public void enter(Player p, int i) {
        p.ActionTimer = 1;
        if (i == 1) {
            p.bhTarget = getTargetLow(p);
            Player p2 = Engine.players[p.bhTarget];
            enterLow(p);
        } else if (i == 2) {
            p.bhTarget = getTargetMid(p);
            Player p2 = Engine.players[p.bhTarget];
            enterMid(p);
        } else {
            p.bhTarget = getTargetHigh(p);
            Player p2 = Engine.players[p.bhTarget];
            enterHigh(p);
        }
        p.pkIcon = setSkull(p);
        p.updateReq = p.appearanceUpdateReq = true;
        if (p.ActionTimer == 0) {
            p.frames.setOverlay(p, 653);
            p.Overlay = 1;
        }
        p.frames.setInterfaceConfig(p, 653, 9, true);
        p.inBounty = true;
        Player p2 = Engine.players[p.bhTarget];
        if (p2 != null) {
            p.frames.setString(p, "" + p2.username, 653, 8);
        } else {
            p.frames.setString(p, "None", 653, 8);
        }
    }

    public void teleEnter(Player p, int height) {
        int randomEnter = Misc.random(19);
        if (randomEnter == 0) {
            p.setCoords(3135, 3758, height);
        } else if (randomEnter == 1) {
            p.setCoords(3121, 3754, height);
        } else if (randomEnter == 2) {
            p.setCoords(3110, 3747, height);
        } else if (randomEnter == 3) {
            p.setCoords(3091, 3735, height);
        } else if (randomEnter == 4) {
            p.setCoords(3086, 3717, height);
        } else if (randomEnter == 5) {
            p.setCoords(3091, 3706, height);
        } else if (randomEnter == 6) {
            p.setCoords(3096, 3692, height);
        } else if (randomEnter == 7) {
            p.setCoords(3101, 3682, height);
        } else if (randomEnter == 8) {
            p.setCoords(3108, 3670, height);
        } else if (randomEnter == 9) {
            p.setCoords(3124, 3665, height);
        } else if (randomEnter == 10) {
            p.setCoords(3138, 3669, height);
        } else if (randomEnter == 11) {
            p.setCoords(3146, 3681, height);
        } else if (randomEnter == 12) {
            p.setCoords(3163, 3696, height);
        } else if (randomEnter == 13) {
            p.setCoords(3171, 3701, height);
        } else if (randomEnter == 14) {
            p.setCoords(3180, 3708, height);
        } else if (randomEnter == 15) {
            p.setCoords(3181, 3720, height);
        } else if (randomEnter == 16) {
            p.setCoords(3171, 3737, height);
        } else if (randomEnter == 17) {
            p.setCoords(3170, 3746, height);
        } else if (randomEnter == 18) {
            p.setCoords(3163, 3753, height);
        } else if (randomEnter == 19) {
            p.setCoords(3147, 3758, height);
        }
    }

    public void exit(Player p, int i) {
        if (p.penalty > 0 && p.cantLeave) {
            p.frames.sendMessage(p, "You can't leave the creator right now!");
            return;
        }
        if (i == 1) {
            p.setCoords(3152, 3672, 0);
            removeLow(p);
            for (Player p2 : Engine.players) {
                if (p2 == null || !p2.inBounty) {
                    continue;
                }
                if (p2.bhTarget == p.playerId) {
                    p2.bhTarget = getTargetLow(p2);
                    p2.frames.sendMessage(p2, "Your target has left the crator.");
                    Player p3 = Engine.players[p2.bhTarget];
                    if (p3 != null) {
                        p2.frames.setString(p2, "" + p3.username, 653, 8);
                    } else {
                        p2.frames.setString(p2, "None", 653, 8);
                    }
                }
            }
        } else if (i == 2) {
            p.setCoords(3158, 3680, 0);
            removeMid(p);
            for (Player p2 : Engine.players) {
                if (p2 == null || !p2.inBounty) {
                    continue;
                }
                if (p2.bhTarget == p.playerId) {
                    p2.bhTarget = getTargetMid(p2);
                    p2.frames.sendMessage(p2, "Your target has left the crator.");
                    Player p3 = Engine.players[p2.bhTarget];
                    if (p3 != null) {
                        p2.frames.setString(p2, "" + p3.username, 653, 8);
                    } else {
                        p2.frames.setString(p2, "None", 653, 8);
                    }
                }
            }
        } else {
            p.setCoords(3164, 3685, 0);
            removeHigh(p);
            for (Player p2 : Engine.players) {
                if (p2 == null || !p2.inBounty) {
                    continue;
                }
                if (p2.bhTarget == p.playerId) {
                    p2.bhTarget = getTargetHigh(p2);
                    p2.frames.sendMessage(p2, "Your target has left the crator.");
                    Player p3 = Engine.players[p2.bhTarget];
                    if (p3 != null) {
                        p2.frames.setString(p2, "" + p3.username, 653, 8);
                    } else {
                        p2.frames.setString(p2, "None", 653, 8);
                    }
                }
            }
        }
        p.inBounty = false;
        p.frames.removeOverlay(p);
        p.Overlay = 0;
        p.pkIcon = -1;
        p.updateReq = p.appearanceUpdateReq = true;
    }

    public int setSkull(Player p) {
        int skullId = 0;
        int wealth = wealth(p);
        if (wealth < 100000) {
            skullId = 6;
        } else if (wealth < 500000 && wealth > 100000) {
            skullId = 5;
        } else if (wealth < 1000000 && wealth > 500000) {
            skullId = 4;
        } else if (wealth < 10000000 && wealth > 1000000) {
            skullId = 3;
        } else {
            skullId = 2;
        }
        return skullId;
    }

    public int wealth(Player p) {
        int wealth = 0;
        for (int i = 0; i < p.items.length; i++) {
            if (p.items[i] == -1 || p.itemsN[i] == 0) {
                continue;
            }
            for (geList list : Engine.geLoader.geList) {
                if (list == null || list.itemId != p.items[i]) {
                    continue;
                }
                wealth += (int) list.low;
            }
        }
        for (int i = 0; i < p.equipment.length; i++) {
            if (p.equipment[i] == -1 || p.equipmentN[i] == 0) {
                continue;
            }
            for (geList list : Engine.geLoader.geList) {
                if (list == null || list.itemId != p.equipment[i]) {
                    continue;
                }
                wealth += (int) list.low;
            }
        }
        return wealth;
    }

}
