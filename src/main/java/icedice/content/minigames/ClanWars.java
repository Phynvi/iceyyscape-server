package icedice.content.minigames;

/**
 * @author Icedice
 * <p/>
 * Date Created November 23, 2011 11:36 AM
 */

import icedice.players.Player;

public class ClanWars {


    public int clan1Members = 0;
    public int clan2Members = 0;
    public String clan1MembersCount = "Players remaining " + clan1Members;
    public String clan2MembersCount = "Players remaining " + clan2Members;

    public void resetClanWars(Player p) {
        p.ClanTimer = -1;
        p.ClanReady = false;
        p.ClanPartner = 0;
        p.ClanSide = 0;
        p.clanheight = 0;
        p.ClanBattle = false;
        p.ClanCount = 1;
        p.Opposing = 0;
        //p.GotThere = 0;
    }

    public void setupField(Player p) {
        if (p.ClanSide == 1) {
            p.setCoords(3320, 3781, p.clanheight);
            clan1Members++;
        } else {
            p.setCoords(3320, 3770, p.clanheight);
            clan1Members++;
        }
    }

    public void addToField(Player p) {
        if (p.ClanSide == 1) {
            p.setCoords(3320, 3781, p.clanheight);
            clan1Members++;
        } else {
            p.setCoords(3320, 3770, p.clanheight);
            clan2Members++;
        }
    }

    public void addLatePlayer(Player p) {
        if (p.ClanSide == 1) {
            p.setCoords(3320, 3781, p.clanheight);
            clan1Members -= 1;
        } else {
            p.setCoords(3320, 3770, p.clanheight);
            clan2Members -= 1;
        }
    }

    public void removeFromField(Player p) {
        if (p.ClanSide == 1) {
            clan1Members -= 1;
        } else {
            clan2Members -= 1;
        }
    }

    public void sendToJail(Player p) {
        if (p.ClanSide == 1) {
            p.setCoords(3320, 3781, p.clanheight);
            clan1Members -= 1;
        } else {
            p.setCoords(3320, 3770, p.clanheight);
            clan2Members -= 1;
        }
    }

}