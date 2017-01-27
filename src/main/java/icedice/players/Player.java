package icedice.players;

import java.net.Socket;

import icedice.*;
import icedice.net.PlayerSocket;
import icedice.util.*;
import icedice.io.*;
import icedice.io.packets.*;
import icedice.npcs.*;
import icedice.players.ptrade.*;
import icedice.Skills.*;
import icedice.players.items.*;
import icedice.content.*;
import icedice.content.minigames.*;
import icedice.characters.*;

import java.util.*;

public class Player {

    public boolean swapAsNote = false;

    public void restoreTabs(Player p) {
        for (int b = 16; b <= 21; b++) {
            p.frames.setInterfaceConfig(p, 548, b, false);
        }
        for (int a = 32; a <= 38; a++) {
            p.frames.setInterfaceConfig(p, 548, a, false);
        }
        p.calculateEquipmentBonus();
        p.frames.setInterfaceConfig(p, 548, 14, false);
        p.frames.setInterfaceConfig(p, 548, 31, false);
        p.frames.setInterfaceConfig(p, 548, 63, false);
        p.frames.setInterfaceConfig(p, 548, 72, false);
    }

    public String clanRoom = "";
    public GrandExchange GrandExchange;
    public UserInput input = new UserInput(this);
    public PTrade pTrade;
    public int loginYell = 0;
    public int savedBackup = 0;
    public int deathMessage = 0;
    public int defeatMessage = 0;
    public int started = 0;
    public int starter = 0;
    public int poisonTicks;
    public int currentSlot = 0;
    public int LoadedBackup = 0;
    public int QuestPoints = 0;
    public int FamiliarType = 0;
    public int isAncients = 0;
    public int BoatTimer = -1;
    public int ThunderTimer = -1;
    public int DragonTimer = -1;
    public int CrashTimer = -1;
    public int FadeTimer = -1;
    public int HeadTimer = -1;
    public int zkc = 0;
    public int bkc = 0;
    public int akc = 0;
    public int skc = 0;
    public int sdc = 0;
    public int pkp = 0;
    public int pkr = 0;
    public int mtr = -1;
    public int Blackmarks = 0;
    public int muted = 0;
    public int jailed = 0;
    public int actionTimer = 0;
    public int donator = 0;
    public int xpLock = 0;
    public int pinNumOne;
    public int pinNumTwo;
    public int pinNumThree;
    public int pinNumFour;
    public int hasBankPin;
    public int pinStage = 1;
    public int pestpoints = 0;
    public int DFStimer = 0;
    public int icetimer = 0;
    public int backuptimer = 0;
    public int partyTimer = 0;
    public int firecape = 0;
    public int Hitter = 5;
    public int enemyId = 0;
    public int clanchat = 0;
    public int clanchannel = 0;
    /*bounty hunter*/
    public int bhTarget = 0;
    public int penalty = 0;
    public boolean cantLeave = false;
    public boolean inBounty = false;
    public int attackedBy = 0;
    public int enemyIndex;
    public boolean alreadyBanked = false;
    public boolean splitChat = false;
    public boolean vengOn = false;
    public boolean usingHD;
    public long lastVeng;

	/* Prayers
       public int bestmeleeAtk() {
        if(equipmentBonus[0] > equipmentBonus[1] && equipmentBonus[0] > equipmentBonus[2]) {
            return 0;
        }
        if(equipmentBonus[1] > equipmentBonus[0] && equipmentBonus[1] > equipmentBonus[2]) {
            return 1;
        }
        return equipmentBonus[2] <= equipmentBonus[1] || equipmentBonus[2] <= equipmentBonus[0] ? 0 : 2;
    }

    public int bestmeleeDef() {
        if(equipmentBonus[5] > equipmentBonus[6] && equipmentBonus[5] > equipmentBonus[7]) {
            return 6;
        }
        if(equipmentBonus[6] > equipmentBonus[5] && equipmentBonus[6] > equipmentBonus[7]) {
            return 7;
        }
        return equipmentBonus[7] <= equipmentBonus[5] || equipmentBonus[7] <= equipmentBonus[6] ? 5 : 7;
    }

    public int meleeDef() {
        int i = equipmentBonus[bestmeleeDef()];
        int j = skillLvl[1];
        int k = getLevelForXP(1);
        int l = 0;

        if (thickSkin) {
            j = (int)((double)j + (double)k * 0.050000000000000003D);

        } else if (rockSkin) {
            j = (int)((double)j + (double)k * 0.10000000000000001D);

        } else if (steelSkin) {
            j = (int)((double)j + (double)k * 0.14999999999999999D);

        } else if (chilvaryPrayer) {
            j = (int)((double)j + (double)k * 0.20000000000000001D);

        } else if (pietyPrayer) {
            j = (int)((double)j + (double)k * 0.25D);
	}

        l += (int)((double)j + (double)j * 0.14999999999999999D + ((double)i + (double)i * 0.050000000000000003D));
        return l;
    }

    public int rangeDef() {
        int i = equipmentBonus[9];
        int j = skillLvl[1];
        int k = getLevelForXP(1);
        int l = 0;

        if (thickSkin) {
            j = (int)((double)j + (double)k * 0.050000000000000003D);

        } else if (rockSkin) {
            j = (int)((double)j + (double)k * 0.10000000000000001D);

        } else if (steelSkin) {
            j = (int)((double)j + (double)k * 0.14999999999999999D);

        } else if (chilvaryPrayer) {
            j = (int)((double)j + (double)k * 0.20000000000000001D);

        } else if (pietyPrayer) {
            j = (int)((double)j + (double)k * 0.25D);
	}

        l += (int)((double)j + (double)j * 0.14999999999999999D + ((double)i + (double)i * 0.050000000000000003D) + Misc.random(3));
        return l;
    }

    public int rangeAtk() {
        int i = equipmentBonus[4];
        int j = skillLvl[4];
        int k = getLevelForXP(4);
        int l = 0;

	if (eagleEyePrayer) {
		j = (int)((double)j + (double)k * 0.14999999999999999D);
	}

        l += (int)((double)j + (double)j * 0.14999999999999999D + ((double)i + (double)i * 0.050000000000000003D) + Misc.random(8));
        return l;
    }

    public int meleeAtk() {
        int i = equipmentBonus[bestmeleeAtk()];
        int j = skillLvl[0];
        int k = getLevelForXP(0);
        int l = 0;

        if (clarity) {
            j = (int)((double)j + (double)k * 0.050000000000000003D);

        } else if (improvedReflex) {
            j = (int)((double)j + (double)k * 0.10000000000000001D);

        } else if (incredibleReflex) {
            j = (int)((double)j + (double)k * 0.14999999999999999D);

        } else if (chilvaryPrayer) {
            j = (int)((double)j + (double)k * 0.14999999999999999D);

        } else if (pietyPrayer) {
            j = (int)((double)j + (double)k * 0.20000000000000001D);
	}

        l += (int)((double)j + (double)j * 0.14999999999999999D + ((double)i + (double)i * 0.050000000000000003D));
        return l;
    }*/


    public void bankpin() {
        frames.setString(this, "1", 13, 11);
        frames.setString(this, "2", 13, 12);
        frames.setString(this, "3", 13, 13);
        frames.setString(this, "4", 13, 14);
        frames.setString(this, "5", 13, 15);
        frames.setString(this, "6", 13, 16);
        frames.setString(this, "7", 13, 17);
        frames.setString(this, "8", 13, 18);
        frames.setString(this, "9", 13, 19);
        frames.setString(this, "0", 13, 20);
    }

	/*public void saveScores() {
        SQL.createConnection();
        SQL.saveHighScore(this);
        SQL.destroyConnection();
    }*/

    public void savePlayer() {
        /*Engine.sqlSave.createConnection();
        Engine.sqlSave.savePlayer(this);
        Engine.sqlSave.destroyConnection();*/
    }

    public void loadPlayer() {
        /*Engine.sqlLoad.createConnection();
        Engine.sqlLoad.loadPlayer(this);
        Engine.sqlLoad.destroyConnection();*/
    }

    public int secHit2;
    public int thirdHit2;
    public int fourHit2;

    /**
     * Fight Cave variables
     */
    public int neededKills;
    public int[] waveType = new int[5];
    public int waveCount;
    public int waveDelay = -1;
    public FightCave fCave = new FightCave(this);

    public void dClaw3(Player p) {
        if (p != null) {
            NPC n = Engine.npcs[p.attackNPC];
            if (n != null) {
                requestAnim(2068, 0);
                requestGFX(274, 100);
                n.appendHit((thirdHit2), 0);
                n.appendHit((fourHit2), 0);
            }
        }
    }

    public void dClaw4(Player p) {
        if (p != null) {
            Player p2 = Engine.players[p.attackPlayer];
            if (p2 != null) {
                requestAnim(2068, 0);
                requestGFX(274, 100);
                p2.appendHit((thirdHit2), 0);
                p2.appendHit((fourHit2), 0);
            }
        }
    }

    public int clawTimer2;
    public boolean UseClaws2;
    public boolean UseClaws3;
    public int Choice = 0;
    public int FamiliarID = 0;
    public int Seercull = 0;
    public int DragonSlayer = 0;
    public int[] SaraPeople = new int[500];
    public int[] ZammyPeople = new int[500];
    public int CWTeam = 0;

    public boolean magic = false;
    public boolean yew = false;
    public boolean willow = false;
    public boolean oak = false;
    public boolean reg = false;
    public int WCTimer = 0;

    public boolean isWoodcutting() {
        if (magic) {
            return true;
        }
        if (yew) {
            return true;
        }
        if (willow) {
            return true;
        }
        if (oak) {
            return true;
        }
        if (reg) {
            return true;
        }
        return false;
    }

    public boolean AtZamorakRoom() {
        if (absX >= 2409 && absY >= 9513 && absX <= 2432 && absY <= 9535) {
            return true;
        } else {
            return false;
        }
    }

    public boolean AtSaradominRoom() {
        if (absX >= 2367 && absY >= 9480 && absX <= 2394 && absY <= 9496) {
            return true;
        } else {
            return false;
        }
    }

    public boolean AtCastleWars() {
        if (absX >= 2363 && absY >= 3071 && absX <= 2432 && absY <= 3135) {
            return true;
        } else {
            return false;
        }
    }

    public boolean BHOutside() {
        return absX >= 3152 && absX <= 3195 && absY >= 3669 && absY <= 3704;
    }

    public boolean BHOutside2() {
        return absX >= 3146 && absX <= 3166 && absY >= 3662 && absY <= 3668;
    }

    public boolean inJadCave() {
        return absX >= 2375 && absX <= 2425 && absY >= 5128 && absY <= 5169;
    }

    public void createGlobalObject(int objectId, int height, int objectX, int objectY, int face, int type) {
        sendCoords(this, (objectX - ((mapRegionX - 6) * 8)), (objectY - ((mapRegionY - 6) * 8)));
        int ot = ((type << 2) + (face & 3));
        stream.createFrame(30);
        stream.writeWordBigEndian(objectId);
        stream.writeByteA(0);
        stream.writeByteC(ot);
    }

    public void sendCoords(Player p, int x, int y) {
        if (p == null || stream == null || disconnected[0]) {
            return;
        }
        stream.createFrame(177);
        stream.writeByte(y);
        stream.writeByteS(x);
    }

    //====================================FIGHT PITS===================================================
    public int FightEnemys = 0;
    public int PitGame = -1;
    public int[] FightPeople = new int[500];
    public static int rdage[] = {10330, 10332, 10334, 10336, 10338, 10340, 10342, 10344, 10346, 10348, 10350, 10352};

    public static int Age() {
        return rdage[(int) (Math.random() * rdage.length)];
    }

    public boolean GameStarted = false;

    public boolean AtWaitRoom() {
        if (absX >= 2394 && absY >= 5169 && absX <= 2404 && absY <= 5175) {
            return true;
        } else {
            return false;
        }
    }

    public boolean AtPits() {
        if (absX >= 2370 && absY >= 5128 && absX <= 2426 && absY <= 5167) {
            return true;
        } else {
            return false;
        }
    }

    //====================================END OF FIGHT PITS===================================================
//====================================DUELING===================================================
    public boolean DuelReady = false;
    public boolean DuelCan = false;
    public int DuelX = 0;
    public int DuelY = 0;
    public int DuelPartner = 0;
    public int DuelTimer = -1;

    public void ResetDuel() {
        DuelReady = false;
        DuelPartner = 0;
        DuelCan = false;
        DuelTimer = -1;
        skulledDelay = -1;
    }

    public boolean AtDuel() {
        if (absX >= 3362 && absY >= 3228 && absX <= 3391 && absY <= 3241) {
            return true;
        } else {
            return false;
        }
    }

    //===================================END OF DUELING==============================================
    public int JadTele = -1;


    public int[] ClanMember = new int[50];

    public int ClanTimer = -1;
    public boolean ClanReady = false;
    public int clanId = 0;
    public int ClanPartner = 0;
    public int ClanSide = 0;
    public int clanheight = 0;
    public int Opposing = 0;
    public boolean ClanBattle = false;
    public int ClanCount = 1;

    public boolean AtClanLobby() {
        if (absX >= 3264 && absY >= 3672 && absX <= 3279 && absY <= 3695) {
            return true;
        } else {
            return false;
        }
    }

    public boolean AtClanField() {
        if (absX >= 3263 && absY >= 3713 && absX <= 3329 && absY <= 3841) {
            return true;
        } else {
            return false;
        }
    }

    public boolean AtJail() {
        if (absX >= 3318 && absY >= 3762 && absX <= 3322 && absY <= 3789) {
            return true;
        } else {
            return false;
        }
    }
//==================================== CONSTRUCTION ==============================================

    public int[] HouseObjects = new int[500];
    public int[] HouseX = new int[500];
    public int[] HouseY = new int[500];

    public int Room0 = 1;
    public int Room1 = 0;
    public int Room2 = 0;
    public int Room3 = 0;
    public int Room4 = 1;
    public int PersonHouse = 0;
    public boolean InHouse = false;
    public boolean KickPlayers = false;
    public int Rooms = 1 + Room1 + Room2 + Room3;
    public boolean BuildingMode = false;
    public int RoomDir = 0;

    public int Room0Type = 6;
    public int Room1Type = 0;
    public int Room2Type = 0;
    public int Room3Type = 0;
    public int Room4Type = 7;

    public boolean TalkAgent = false;
    public boolean DecorChange = false;
    public int TeleBackTimer = -1;
    public int HLastX = 0;
    public int HLastY = 0;
    public int lastHeight = 0;

    public int Garden = 0;
    public int Garden1 = 0;
    public int Garden2 = 0;
    public int Garden3 = 0;
    public int Garden4 = 1;

    public int HouseDecor = 1585;

    public boolean OwnHouse = false;

    public int HX = 0;
    public int HY = 0;

    public void DeleteRoom(Player p, int Room) {
        int height = HouseHeight;
        if (Room == 1) {
            HX = 3104;
            HY = 3939;
            Room1 = 0;
        }
        if (Room == 2) {
            HX = 3112;
            HY = 3931;
            Room2 = 0;
        }
        if (Room == 3) {
            HX = 3096;
            HY = 3931;
            Room3 = 0;
        }

        if (Room == 4) {
            HX = 3104;
            HY = 3923;
            Room4 = 0;
        }

        if (Room == 0) {
            HX = 3104;
            HY = 3931;
            Room0 = 0;
        }


        createGlobalObject(13340, height, HX, HY, 0, 10);

        createGlobalObject(13340, height, HX, HY, 0, 10);
        createGlobalObject(13340, height, HX - 1, HY, 0, 10);
        createGlobalObject(13340, height, HX - 2, HY, 0, 10);
        createGlobalObject(13340, height, HX - 3, HY, 0, 10);
        createGlobalObject(13340, height, HX + 1, HY, 0, 10);
        createGlobalObject(13340, height, HX + 2, HY, 0, 10);
        createGlobalObject(13340, height, HX + 3, HY, 0, 10);
        createGlobalObject(13340, height, HX + 4, HY, 0, 10);

        createGlobalObject(13340, height, HX, HY + 1, 0, 10);
        createGlobalObject(13340, height, HX - 1, HY + 1, 0, 10);
        createGlobalObject(13340, height, HX - 2, HY + 1, 0, 10);
        createGlobalObject(13340, height, HX - 3, HY + 1, 0, 10);
        createGlobalObject(13340, height, HX + 1, HY + 1, 0, 10);
        createGlobalObject(13340, height, HX + 2, HY + 1, 0, 10);
        createGlobalObject(13340, height, HX + 3, HY + 1, 0, 10);
        createGlobalObject(13340, height, HX + 4, HY + 1, 0, 10);

        createGlobalObject(13340, height, HX, HY + 2, 0, 10);
        createGlobalObject(13340, height, HX - 1, HY + 2, 0, 10);
        createGlobalObject(13340, height, HX - 2, HY + 2, 0, 10);
        createGlobalObject(13340, height, HX - 3, HY + 2, 0, 10);
        createGlobalObject(13340, height, HX + 1, HY + 2, 0, 10);
        createGlobalObject(13340, height, HX + 2, HY + 2, 0, 10);
        createGlobalObject(13340, height, HX + 3, HY + 2, 0, 10);
        createGlobalObject(13340, height, HX + 4, HY + 2, 0, 10);

        createGlobalObject(13340, height, HX, HY + 3, 0, 10);
        createGlobalObject(13340, height, HX - 1, HY + 3, 0, 10);
        createGlobalObject(13340, height, HX - 2, HY + 3, 0, 10);
        createGlobalObject(13340, height, HX - 3, HY + 3, 0, 10);
        createGlobalObject(13340, height, HX + 1, HY + 3, 0, 10);
        createGlobalObject(13340, height, HX + 2, HY + 3, 0, 10);
        createGlobalObject(13340, height, HX + 3, HY + 3, 0, 10);
        createGlobalObject(13340, height, HX + 4, HY + 3, 0, 10);


        createGlobalObject(13340, height, HX, HY + 4, 0, 10);
        createGlobalObject(13340, height, HX - 1, HY + 4, 0, 10);
        createGlobalObject(13340, height, HX - 2, HY + 4, 0, 10);
        createGlobalObject(13340, height, HX - 3, HY + 4, 0, 10);
        createGlobalObject(13340, height, HX + 1, HY + 4, 0, 10);
        createGlobalObject(13340, height, HX + 2, HY + 4, 0, 10);
        createGlobalObject(13340, height, HX + 3, HY + 4, 0, 10);
        createGlobalObject(13340, height, HX + 4, HY + 4, 0, 10);

        createGlobalObject(13340, height, HX, HY + 5, 0, 10);
        createGlobalObject(13340, height, HX - 1, HY + 5, 0, 10);
        createGlobalObject(13340, height, HX - 2, HY + 5, 0, 10);
        createGlobalObject(13340, height, HX - 3, HY + 5, 0, 10);
        createGlobalObject(13340, height, HX + 1, HY + 5, 0, 10);
        createGlobalObject(13340, height, HX + 2, HY + 5, 0, 10);
        createGlobalObject(13340, height, HX + 3, HY + 5, 0, 10);
        createGlobalObject(13340, height, HX + 4, HY + 5, 0, 10);

        createGlobalObject(13340, height, HX, HY + 6, 0, 10);
        createGlobalObject(13340, height, HX - 1, HY + 6, 0, 10);
        createGlobalObject(13340, height, HX - 2, HY + 6, 0, 10);
        createGlobalObject(13340, height, HX - 3, HY + 6, 0, 10);
        createGlobalObject(13340, height, HX + 1, HY + 6, 0, 10);
        createGlobalObject(13340, height, HX + 2, HY + 6, 0, 10);
        createGlobalObject(13340, height, HX + 3, HY + 6, 0, 10);
        createGlobalObject(13340, height, HX + 4, HY + 6, 0, 10);


        createGlobalObject(13340, height, HX, HY + 7, 0, 10);
        createGlobalObject(13340, height, HX - 1, HY + 7, 0, 10);
        createGlobalObject(13340, height, HX - 2, HY + 7, 0, 10);
        createGlobalObject(13340, height, HX - 3, HY + 7, 0, 10);
        createGlobalObject(13340, height, HX + 1, HY + 7, 0, 10);
        createGlobalObject(13340, height, HX + 2, HY + 7, 0, 10);
        createGlobalObject(13340, height, HX + 3, HY + 7, 0, 10);
        createGlobalObject(13340, height, HX + 4, HY + 7, 0, 10);


//HLastX = absX;
//HLastY = absY;
//lastHeight = heightLevel;
//p.frames.showInterface(p, 399);
//setCoords(2543, 3096, 0);
//TeleBackTimer = 4;
//13116 = White Stone
//1585 = Stone
//1587 = Dark Stone
    }

    public void NewRoom(Player p, int X, int Y, int Dir, int height, int decor, int type, int black) {
        if (Garden == 1 && Dir == 0) decor = 13455;
        if (Garden1 == 1 && Dir == 1) decor = 13455;
        if (Garden2 == 1 && Dir == 2) decor = 13455;
        if (Garden3 == 1 && Dir == 3) decor = 13455;
        if (Garden4 == 1 && Dir == 4) decor = 13455;


        if (Dir == 1) {
            if (OwnHouse == true) {
                Room1 = 1;
            }
            createGlobalObject(decor, height, X - 1, Y, 3, 0);
            createGlobalObject(decor, height, X - 2, Y, 3, 0);
            createGlobalObject(decor, height, X - 3, Y, 3, 2);

            createGlobalObject(decor, height, X + 2, Y, 3, 0);
            createGlobalObject(decor, height, X + 3, Y, 3, 0);
            createGlobalObject(decor, height, X + 4, Y, 2, 2);

            createGlobalObject(decor, height, X + 4, Y + 1, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 2, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 3, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 4, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 5, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 6, 2, 0);

            createGlobalObject(decor, height, X - 3, Y + 1, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 2, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 3, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 4, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 5, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 6, 0, 0);

            createGlobalObject(decor, height, X - 3, Y + 7, 0, 2);

            createGlobalObject(decor, height, X + 4, Y + 7, 1, 2);
            createGlobalObject(decor, height, X - 2, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 2, Y + 7, 1, 0);
            createGlobalObject(decor, height, X - 1, Y + 7, 1, 0);
            createGlobalObject(decor, height, X, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 1, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 3, Y + 7, 1, 0);

            createGlobalObject(13340, height, X, Y - 1, 3, 0);
            createGlobalObject(13340, height, X + 1, Y - 1, 3, 0);


        }
        if (Dir == 2) {
            if (OwnHouse == true) {
                Room2 = 1;
            }
            createGlobalObject(13340, height, X - 4, Y + 3, 3, 0);
            createGlobalObject(13340, height, X - 4, Y + 4, 3, 0);

            createGlobalObject(decor, height, X - 1, Y, 3, 0);
            createGlobalObject(decor, height, X - 2, Y, 3, 0);
            createGlobalObject(decor, height, X, Y, 3, 0);
            createGlobalObject(decor, height, X + 1, Y, 3, 0);
            createGlobalObject(decor, height, X - 3, Y, 3, 2);

            createGlobalObject(decor, height, X + 2, Y, 3, 0);
            createGlobalObject(decor, height, X + 3, Y, 3, 0);
            createGlobalObject(decor, height, X + 4, Y, 2, 2);

            createGlobalObject(decor, height, X + 4, Y + 1, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 2, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 3, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 4, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 5, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 6, 2, 0);

            createGlobalObject(decor, height, X - 3, Y + 1, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 2, 0, 0);
//createGlobalObject(decor, height, X-3, Y+3, 0, 0); 
//createGlobalObject(decor, height, X-3, Y+4, 0, 0); 
            createGlobalObject(decor, height, X - 3, Y + 5, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 6, 0, 0);

            createGlobalObject(decor, height, X - 3, Y + 7, 0, 2);

            createGlobalObject(decor, height, X + 4, Y + 7, 1, 2);
            createGlobalObject(decor, height, X - 2, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 2, Y + 7, 1, 0);
            createGlobalObject(decor, height, X - 1, Y + 7, 1, 0);
            createGlobalObject(decor, height, X, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 1, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 3, Y + 7, 1, 0);
        }
        if (Dir == 3) {
            if (OwnHouse == true) {
                Room3 = 1;
            }

            createGlobalObject(13340, height, X + 5, Y + 3, 3, 0);
            createGlobalObject(13340, height, X + 5, Y + 4, 3, 0);

            createGlobalObject(decor, height, X - 1, Y, 3, 0);
            createGlobalObject(decor, height, X - 2, Y, 3, 0);
            createGlobalObject(decor, height, X, Y, 3, 0);
            createGlobalObject(decor, height, X + 1, Y, 3, 0);
            createGlobalObject(decor, height, X - 3, Y, 3, 2);

            createGlobalObject(decor, height, X + 2, Y, 3, 0);
            createGlobalObject(decor, height, X + 3, Y, 3, 0);
            createGlobalObject(decor, height, X + 4, Y, 2, 2);

            createGlobalObject(decor, height, X + 4, Y + 1, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 2, 2, 0);
//createGlobalObject(decor, height, X+4, Y+3, 2, 0); 
//createGlobalObject(decor, height, X+4, Y+4, 2, 0); 
            createGlobalObject(decor, height, X + 4, Y + 5, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 6, 2, 0);

            createGlobalObject(decor, height, X - 3, Y + 1, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 2, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 3, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 4, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 5, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 6, 0, 0);

            createGlobalObject(decor, height, X - 3, Y + 7, 0, 2);

            createGlobalObject(decor, height, X + 4, Y + 7, 1, 2);
            createGlobalObject(decor, height, X - 2, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 2, Y + 7, 1, 0);
            createGlobalObject(decor, height, X - 1, Y + 7, 1, 0);
            createGlobalObject(decor, height, X, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 1, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 3, Y + 7, 1, 0);
        }

        if (Dir == 0) {
            if (OwnHouse == true) {
                Room0 = 1;
            }
            createGlobalObject(decor, height, 3103, 3931, 3, 0);
            createGlobalObject(decor, height, 3102, 3931, 3, 0);
            createGlobalObject(decor, height, 3101, 3931, 3, 2);

            createGlobalObject(decor, height, 3106, 3931, 3, 0);
            createGlobalObject(decor, height, 3107, 3931, 3, 0);
            createGlobalObject(decor, height, 3108, 3931, 2, 2);

            createGlobalObject(decor, height, 3108, 3932, 2, 0);
            createGlobalObject(decor, height, 3108, 3933, 2, 0);
            createGlobalObject(decor, height, 3108, 3936, 2, 0);
            createGlobalObject(decor, height, 3108, 3937, 2, 0);

            createGlobalObject(decor, height, 3101, 3932, 0, 0);
            createGlobalObject(decor, height, 3101, 3933, 0, 0);

            createGlobalObject(decor, height, 3101, 3936, 0, 0);
            createGlobalObject(decor, height, 3101, 3937, 0, 0);

            createGlobalObject(decor, height, 3101, 3938, 0, 2);

            createGlobalObject(decor, height, 3108, 3938, 1, 2);
            createGlobalObject(decor, height, 3102, 3938, 1, 0);
            createGlobalObject(decor, height, 3106, 3938, 1, 0);
            createGlobalObject(decor, height, 3103, 3938, 1, 0);
            createGlobalObject(decor, height, 3107, 3938, 1, 0);


        }

        if (Dir == 4) {
            if (OwnHouse == true) {
                Room4 = 1;
            }
            createGlobalObject(decor, height, X - 1, Y, 3, 0);
            createGlobalObject(decor, height, X - 2, Y, 3, 0);
            createGlobalObject(decor, height, X - 3, Y, 3, 2);

            createGlobalObject(decor, height, X + 2, Y, 3, 0);
            createGlobalObject(decor, height, X + 3, Y, 3, 0);
            createGlobalObject(decor, height, X + 4, Y, 2, 2);

            createGlobalObject(decor, height, X + 4, Y + 1, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 2, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 5, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 6, 2, 0);

            createGlobalObject(decor, height, X - 3, Y + 1, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 2, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 5, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 6, 0, 0);

            createGlobalObject(decor, height, X - 3, Y + 7, 0, 2);

            createGlobalObject(decor, height, X + 4, Y + 7, 1, 2);
            createGlobalObject(decor, height, X - 2, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 2, Y + 7, 1, 0);
            createGlobalObject(decor, height, X - 1, Y + 7, 1, 0);
            createGlobalObject(decor, height, X + 3, Y + 7, 1, 0);


            createGlobalObject(decor, height, X - 3, Y + 3, 0, 0);
            createGlobalObject(decor, height, X - 3, Y + 4, 0, 0);
            createGlobalObject(decor, height, X + 4, Y + 3, 2, 0);
            createGlobalObject(decor, height, X + 4, Y + 4, 2, 0);
            createGlobalObject(decor, height, X, Y, 3, 0);
            createGlobalObject(decor, height, X + 1, Y, 3, 0);


        }

        if (type == 1)//Parlour
        {
            if (Dir == 1) {
                createGlobalObject(13587, height, X - 1, Y + 2, 3, 10);
                createGlobalObject(13587, height, X - 1, Y + 5, 3, 10);
                createGlobalObject(13587, height, X + 2, Y + 2, 1, 10);
                createGlobalObject(13587, height, X + 2, Y + 5, 1, 10);
            } else {
                createGlobalObject(13587, height, X - 1, Y + 2, 2, 10);
                createGlobalObject(13587, height, X - 1, Y + 5, 0, 10);
                createGlobalObject(13587, height, X + 2, Y + 2, 2, 10);
                createGlobalObject(13587, height, X + 2, Y + 5, 0, 10);
            }

            if (Dir == 1) createGlobalObject(13614, height, X, Y + 7, 1, 10);
            if (Dir == 2 || Dir == 0 || Dir == 4) createGlobalObject(13614, height, X + 4, Y + 3, 2, 10);
            if (Dir == 3) createGlobalObject(13614, height, X - 3, Y + 3, 0, 10);
        }

        if (type == 2)//Kitchen
        {
            createGlobalObject(13579, height, X, Y + 3, 0, 10);
            if (Dir == 1) createGlobalObject(13567, height, X + 3, Y + 6, 0, 10);
            if (Dir == 2 || Dir == 0 || Dir == 4) createGlobalObject(13567, height, X + 3, Y, 1, 10);
            if (Dir == 3) createGlobalObject(13567, height, X - 3, Y + 6, 3, 10);
        }

        if (type == 3)//Bedroom
        {
            if (Dir == 1) createGlobalObject(13167, height, X + 4, Y + 3, 1, 10);
            if (Dir == 2) createGlobalObject(13167, height, X, Y, 2, 10);
            if (Dir == 3) createGlobalObject(13167, height, X, Y + 7, 0, 10);

            if (Dir == 1) createGlobalObject(13161, height, X - 3, Y + 3, 3, 10);
            if (Dir == 2) createGlobalObject(13161, height, X, Y + 7, 0, 10);
            if (Dir == 3) createGlobalObject(13161, height, X, Y, 2, 10);

            if (Dir == 1) createGlobalObject(13154, height, X, Y + 6, 0, 10);
            if (Dir == 2) createGlobalObject(13154, height, X + 3, Y + 3, 1, 10);
            if (Dir == 3) createGlobalObject(13154, height, X - 3, Y + 3, 3, 10);
        }


        if (type == 4)//Chapel
        {
            if (Dir == 1) createGlobalObject(13207, height, X - 1, Y + 4, 0, 10);
            if (Dir == 1) createGlobalObject(13207, height, X + 2, Y + 4, 0, 10);

            if (Dir == 2 || Dir == 0 || Dir == 4) createGlobalObject(13207, height, X + 1, Y + 2, 1, 10);
            if (Dir == 2 || Dir == 0 || Dir == 4) createGlobalObject(13207, height, X + 1, Y + 5, 1, 10);

            if (Dir == 3) createGlobalObject(13207, height, X, Y + 2, 1, 10);
            if (Dir == 3) createGlobalObject(13207, height, X, Y + 5, 1, 10);

            if (Dir == 1) createGlobalObject(13198, height, X, Y + 5, 0, 10);
            if (Dir == 2 || Dir == 0 || Dir == 4) createGlobalObject(13198, height, X + 2, Y + 3, 1, 10);
            if (Dir == 3) createGlobalObject(13198, height, X - 1, Y + 3, 3, 10);


            if (Dir == 1) createGlobalObject(13172, height, X, Y + 7, 0, 10);
            if (Dir == 2 || Dir == 0 || Dir == 4) createGlobalObject(13172, height, X + 4, Y + 3, 1, 10);
            if (Dir == 3) createGlobalObject(13172, height, X - 3, Y + 3, 3, 10);

            if (Dir == 1) createGlobalObject(13216, height, X + 4, Y + 3, 1, 10);
            if (Dir == 2) createGlobalObject(13216, height, X, Y, 2, 10);
            if (Dir == 3) createGlobalObject(13216, height, X, Y + 7, 0, 10);
        }

        if (type == 5) {
            if (Dir == 1 || Dir == 0 || Dir == 4) createGlobalObject(13668, height, X, Y + 5, 0, 10);
            if (Dir == 2) createGlobalObject(13668, height, X + 2, Y + 4, 1, 10);
            if (Dir == 3) createGlobalObject(13668, height, X - 1, Y + 3, 3, 10);

            if (Dir == 1 || Dir == 0 || Dir == 4) createGlobalObject(13671, height, X + 1, Y + 5, 0, 10);
            if (Dir == 2) createGlobalObject(13671, height, X + 2, Y + 3, 1, 10);
            if (Dir == 3) createGlobalObject(13671, height, X - 1, Y + 4, 3, 10);


            if (Dir == 1 || Dir == 0 || Dir == 4) createGlobalObject(13661, height, X - 2, Y + 3, 0, 10);
            if (Dir == 1 || Dir == 0 || Dir == 4) createGlobalObject(13661, height, X + 3, Y + 3, 0, 10);

            if (Dir == 2) createGlobalObject(13661, height, X + 1, Y + 1, 0, 10);
            if (Dir == 2) createGlobalObject(13661, height, X + 1, Y + 6, 0, 10);

            if (Dir == 3) createGlobalObject(13661, height, X, Y + 1, 0, 10);
            if (Dir == 3) createGlobalObject(13661, height, X, Y + 6, 0, 10);

        }

        if (type == 6) {
            createGlobalObject(13599, height, X - 3, Y + 1, 0, 10);
            createGlobalObject(13599, height, X - 3, Y, 0, 10);

            createGlobalObject(13599, height, X + 4, Y + 7, 1, 10);
            createGlobalObject(13599, height, X + 3, Y + 7, 1, 10);

            createGlobalObject(13599, height, X + 4, Y + 1, 2, 10);
            createGlobalObject(13599, height, X + 3, Y, 3, 10);
            createGlobalObject(13599, height, X - 3, Y + 6, 0, 10);
        }


        if (type == 7) {
            createGlobalObject(13426, height, X + 2, Y + 6, 0, 10);
            createGlobalObject(13424, height, X - 2, Y + 1, 0, 10);
            createGlobalObject(13429, height, X - 2, Y + 5, 0, 10);
            createGlobalObject(13439, height, X + 2, Y + 1, 0, 10);

            if (Dir == 0) createGlobalObject(13408, height, X, Y + 3, 0, 10);
            if (Dir == 1) createGlobalObject(13407, height, X, Y + 3, 0, 10);
            if (Dir == 2) createGlobalObject(13406, height, X, Y + 3, 0, 10);
            if (Dir == 3) createGlobalObject(13406, height, X, Y + 3, 1, 10);
            if (Dir == 4) createGlobalObject(13405, height, X, Y + 3, 0, 10);
        }


    }

    //===========================================END OF CONSTRUCTION========================================
    public String LocatedAt = "";
    public int SlayerAm = 0;
    public int SlayerTask = 0;
    public int SlayerCaveTimer = -1;
    public boolean Runecrafting = false;
    public boolean Cooking = false;
    public boolean Smithing = false;
    public int FishTimer = -1;
    public int FishAmount = 0;
    public int FishXP = 0;
    public int FishGet = 0;
    public int FishEmote = 0;
    public int FletchTimer = -1;
    public int FletchAmount = 0;
    public int FletchXP = 0;
    public int FletchGet = 0;
    public int FletchID = 0;
    public int RunecraftingTimer = -1;
    public int RunecraftingAmount = 0;
    public int RunecraftingXP = 0;
    public int RunecraftingGet = 0;
    public int RunecraftingID = 0;
    public int CookXP = 0;
    public int CookID = 0;
    public int CookGet = 0;
    public int CookAmount = 0;
    public int CookTimer = 0;
    public int FarmingTimer = -1;
    public int FarmingType = 0;
    public int FarmType = 0;

    public void ResetSkillSuff() {
        FarmingType = 0;
        FarmingTimer = -1;
        FarmType = 0;
        FishTimer = -1;
        FishAmount = 0;
        FishXP = 0;
        FishGet = 0;
        FishEmote = 0;
        FletchTimer = -1;
        FletchAmount = 0;
        FletchXP = 0;
        FletchGet = 0;
        FletchID = 0;
        RunecraftingTimer = -1;
        RunecraftingAmount = 0;
        RunecraftingXP = 0;
        RunecraftingGet = 0;
        RunecraftingID = 0;
        Cooking = false;
        Runecrafting = false;
    }

    public void RunecraftThat(Player p, int XP, int ID, int Get) {
        if (Engine.playerItems.HasItemAmount(this, ID, 1) == false) {
            ResetSkillSuff();
            p.frames.sendMessage(this, "You don't have enough Rune/Pure Essense.");
        } else {
            p.requestAnim(791, 0);
            Engine.playerItems.deleteItem(this, ID, Engine.playerItems.getItemSlot(this, ID), 1);
            Engine.playerItems.addItem(this, Get, 1);
            p.addSkillXP(XP * p.skillLvl[20], 20);
            p.frames.sendMessage(this, "You make a rune out of this essence.");
            RunecraftingTimer = 3;
            RunecraftingAmount -= 1;
        }
    }

    public void CookThat(Player p, int XP, int ID, int Get) {
        if (Engine.playerItems.HasItemAmount(this, ID, 1) == false) {
            ResetSkillSuff();
            p.frames.sendMessage(this, "You ran out of fish to cook.");
            Cooking = false;
        } else {
            p.requestAnim(883, 0);
            Engine.playerItems.deleteItem(this, ID, Engine.playerItems.getItemSlot(this, ID), 1);
            Engine.playerItems.addItem(this, Get, 1);
            p.addSkillXP(XP * p.skillLvl[7], 7);
            p.frames.sendMessage(this, "You cook the fish.");
            CookTimer = 3;
            CookAmount -= 1;
        }
    }

    public void FletchThat(Player p, int XP, int ID, int Get) {
        if (Engine.playerItems.HasItemAmount(this, ID, 1) == false) {
            ResetSkillSuff();
            p.frames.sendMessage(this, "You ran out of logs to carve.");
            p.requestAnim(standEmote, 0);
        } else {
            p.requestAnim(1248, 0);
            Engine.playerItems.deleteItem(this, ID, Engine.playerItems.getItemSlot(this, ID), 1);
            Engine.playerItems.addItem(this, Get, 1);
            p.addSkillXP(Config.Feltching_XP * p.skillLvl[9] * Config.bonusXP, 9);
            p.frames.sendMessage(this, "You make a short bow.");
            FletchTimer = 3;
            FletchAmount -= 1;
        }
    }

    public void FishThat(Player p, int XP, int Get, int Emote) {
        int index = Engine.playerItems.findInvSlot(this);
        if (index >= 0) {
            p.requestAnim(Emote, 0);
            Engine.playerItems.addItem(this, Get, 1);
            p.addSkillXP(XP * p.skillLvl[10], 10);
            p.frames.sendMessage(this, "You caught a fish.");
            FishTimer = 3;
            FishAmount -= 1;
        } else {
            ResetSkillSuff();
            p.frames.sendMessage(this, "You ran out of space in your inventory.");
            Fishing = false;
            p.requestAnim(standEmote, 0);
        }
    }

    public boolean Bait = false;
    public int NetType = 0;
    public boolean Fishing = false;
    public int FishMan = 0;
    public int NPC = 0;
    public int wallTimer1 = -1;
    public int wallTimer2 = -1;
    public int wallTimer3 = -1;
    public int wallTimer4 = -1;
    public int wallTimer5 = -1;
    public int SwingTimer1 = -1;
    public int SwingTimer2 = -1;
    public int SwingTimer3 = -1;
    public int LogTimer = -1;
    public int AgilityXP = 0;
    public int AgilityTimer = -1;
    public int NetTimer = -1;
    public int PkTimer = -1;
    public int NewEmote = 0;

    public void ResetSkillTimers() {
        Fishing = false;
        NetType = 0;
        Bait = false;
        FishMan = 0;
        FishXP = 0;
    }

    public int followPlayer = 0;
    public boolean followingPlayer = false;

    public void WalkingTo(int x, int y) {
        int firstX = x - (mapRegionX - 6) * 8;
        int firstY = y - (mapRegionY - 6) * 8;
        Engine.playerMovement.resetWalkingQueue(this);
        Engine.playerMovement.addToWalkingQueue(this, firstX, firstY);
        appearanceUpdateReq = true;
        updateReq = true;
    }

    public int ShopID = 0;
    public int Dialogue = 0;
    public boolean normalHomeTele = false;
    public boolean ancientsHomeTele = false;
    public int homeTele = 0;
    public int homeTeleDelay = 0;

    public void homeTeleport(int telex, int teley) {
        if (homeTele == 15) {
            requestAnim(1722, 0);
        } else if (homeTele == 14) {
            requestAnim(1723, 0);
            requestGFX(800, 0);
        } else if (homeTele == 13) {
            requestAnim(1724, 0);
            requestGFX(801, 0);
        } else if (homeTele == 12) {
            requestAnim(1725, 0);
            requestGFX(802, 0);
        } else if (homeTele == 11) {
            requestAnim(2798, 0);
            requestGFX(1703, 0);
        } else if (homeTele == 10) {
            requestAnim(2799, 0);
            requestGFX(1704, 0);
        } else if (homeTele == 9) {
            requestAnim(2800, 0);
            requestGFX(1705, 0);
        } else if (homeTele == 8) {
            requestAnim(4847, 0);
            requestGFX(1706, 0);
        } else if (homeTele == 7) {
            requestAnim(4848, 0);
            requestGFX(1707, 0);
        } else if (homeTele == 6) {
            requestAnim(4849, 0);
            requestGFX(1708, 0);
        } else if (homeTele == 5) {
            requestAnim(4849, 0);
            requestGFX(1709, 0);
        } else if (homeTele == 4) {
            requestAnim(4849, 0);
            requestGFX(1710, 0);
        } else if (homeTele == 3) {
            requestAnim(4850, 0);
            requestGFX(1711, 0);
        } else if (homeTele == 2) {
            requestAnim(4851, 0);
            requestGFX(1712, 0);
        } else if (homeTele == 1) {
            requestAnim(4852, 0);
            requestGFX(1713, 0);
            setCoords(telex, teley, 0);
            normalHomeTele = false;
            ancientsHomeTele = false;
            homeTeleDelay = 3600;
            homeTele = 15;
        }
    }

    /**
     * Player's index.
     */
    public int playerId = 0;
    public int mageDamage;
    public int projectileTimer = -1;
    public int HouseHeight = 0;
    public int HouseTele = -1;
    /**
     * Class for storing and converting bytes.
     */
    public Stream stream = new Stream(500, 5000);
    /**
     * Player's socket for handling most io operations.
     */
    public PlayerSocket socket;
    /**
     * Set to true if the player has finished the login stage.
     */
    public boolean online = false;
    /**
     * Player's email
     */
    public String email = "null";
    /**
     * Player's username.
     */
    public String username = "null";
    /**
     * Player's password.
     */
    public String password = "";
    public Woodcutting wc;
    public Mining mi;

    public void design(Player p, int button) {
        switch (button) {

            /*-----------Gender----------

            case 49://Male
                p.look[0] = 5; // Hair
                p.look[1] = 14; // Beard
                p.look[2] = 18; // Torso
                p.look[3] = 27; // Arms
                p.look[4] = 33; // Bracelets
                p.look[5] = 87; // Legs
                p.look[6] = 42; // Shoes
                p.gender = 0;
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                break;

            case 52://Female
                p.look[0] = 48; // Hair
                p.look[1] = 1000; // Beard
                p.look[2] = 57; // Torso
                p.look[3] = 64; // Arms
                p.look[4] = 68; // Bracelets
                p.look[5] = 77; // Legs
                p.look[6] = 80; // Shoes
                p.gender = 1;
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                break;

            /*---------End Gender--------

            /*--------Head Options-------

            case 92://hair -1
                p.look[0] = Misc.random(8);
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                break;

            case 93://hair +1
                p.look[0] = Misc.random(8);
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                break;

            case 97://facial hair -1
                p.look[1] = Misc.random(7) + 10;
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                break;

            case 98://facial hair +1
                p.look[1] = Misc.random(7) + 10;
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                break;

            /**case 100://char hair color
                p.color[0] += 5;
                break;

            case 101://char hair color
                p.color[0] = 1;
                break;

            case 102://char hair color
                p.color[0] = 2;
                break;

            case 103://char hair color
                p.color[0] = 3;
                break;

            case 104://char hair color
                p.color[0] = 4;
                break;

            case 105://char hair color
                p.color[0] = 5;
                break;

            case 106://char hair color
                p.color[0] = 6;
                break;

            case 107://char hair color
                p.color[0] = 7;
                break;

            case 108://char hair color
                p.color[0] = 8;
                break;

            case 109://char hair color
                p.color[0] = 9;
                break;

            case 110://char hair color
                p.color[0] = 10;
                break;

            case 111://char hair color
                p.color[0] = 11;
                break;

            case 112://char hair color
                p.color[0] = 12;
                break;

            case 113://char hair color
                p.color[0] = 13;
                break;

            case 114://char hair color
                p.color[0] = 14;
                break;

            case 115://char hair color
                p.color[0] = 15;
                break;


            case 116://char hair color
                p.color[0] = 16;
                break;

            case 117://char hair color
                p.color[0] = 17;
                break;

            case 118://char hair color
                p.color[0] = 18;
                break;

            case 119://char hair color
                p.color[0] = 19;
                break;

            case 120://char hair color
                p.color[0] = 20;
                break;

            case 121://char hair color
                p.color[0] = 21;
                break;

            case 122://char hair color
                p.color[0] = 22;
                break;

            case 123://char hair color
                p.color[0] = 23;
                break;

            case 124://char hair color
                p.color[0] = 24;
                break;*/

            /**case 169://randomize button
             p.look[0] = Misc.random(8);
             p.look[1] = Misc.random(7) + 10;
             p.color[0] = Misc.random(24);
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             /*------End Head Options-----*/

            /*--------Body Options-------*/

            /** case 321://randomize button

             p.look[3] = Misc.random(6) + 26;
             p.look[4] = Misc.random(2) + 33;
             p.look[5] = Misc.random(5) + 36;
             p.look[6] = Misc.random(2) + 42;
             p.color[1] = Misc.random(28);
             p.color[2] = Misc.random(28);
             p.color[3] = Misc.random(5);
             p.color[4] = Misc.random(7);
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             case 341://torso -1
             p.color[2] -= 1;
             p.look[2] = Misc.random(7) + 18;
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             case 342://torso +1
             p.color[2] += 1;
             p.color[0] += 5;
             p.look[2] = Misc.random(7) + 18;
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             case 345://arms -1
             p.look[3] = Misc.random(6) + 26;
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             case 346://arms +1
             p.look[3] = Misc.random(6) + 26;
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             case 349://wrists -1
             p.look[4] = Misc.random(2) + 33;
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             case 350://wrists +1
             p.look[4] = Misc.random(2) + 33;
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             case 353://legs -1
             p.look[5] = Misc.random(5) + 36;
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             case 354://legs +1
             p.look[5] = Misc.random(5) + 36;
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             case 357://feet -1
             p.look[6] = Misc.random(2) + 42;
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;

             case 358://feet +1
             p.look[6] = Misc.random(2) + 42;
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;


             /*------End Body Options-----*/

            /*----Confirmation Options---*/
            /**case 362:
             p.frames.removeShownInterface(p);
             p.frames.sendMessage(p, "You Have Changed your look.");
             p.appearanceUpdateReq = true;
             p.updateReq = true;
             break;
             /*-End Confirmation Options--*/

        }
    }

    /**
     * Player's rights.
     */
    public int rights = 0;
    /**
     * 1 set to true means socket disconnected but logged in, both for removing the player.
     */
    public boolean[] disconnected = new boolean[2];
    /**
     * Gives quick access to the frame class.
     */
    public Frames frames = Engine.frames;
    /**
     * The region this player is in.
     */
    public int mapRegionX = 0;
    public int mapRegionY = 0;
    /**
     * The position this player is at in the map region.
     */
    public int currentX = 0;
    public int currentY = 0;
    /**
     * Absolute coordinates this player is at.
     */
    public int absX = 0;
    public int absY = 0;
    /**
     * The height level this player is at.
     */
    public int heightLevel = 0;
    /**
     * If either are above -1 then the player is in motion.
     */
    public int walkDir = -1;
    public int runDir = -1;
    /**
     * True if the player is running, false if it isn't.
     */
    public boolean isRunning = false;
    /**
     * Set to true if the player has entered a new map region.
     */
    public boolean mapRegionDidChange = false;
    /**
     * Set to true if teleported.
     */
    public boolean didTeleport = false;
    /**
     * Set Absolute coordinates to these.
     */
    public int teleportToX = -1;
    public int teleportToY = -1;
    /**
     * True if the player is Reqing an update.
     */
    public boolean updateReq = false;
    /**
     * Max number of steps this player can have pending.
     */
    public int walkingQueueSize = 50;
    public int wQueueReadPtr = 0;
    public int wQueueWritePtr = 0;
    /**
     * Positions the player is Reqing to walk to.
     */
    public int[] walkingQueueX = new int[walkingQueueSize];
    public int[] walkingQueueY = new int[walkingQueueSize];
    public int[] walkingQueue = new int[walkingQueueSize];
    /**
     * All the players within distance.
     */
    public Player[] playerList = new Player[Engine.players.length];
    /**
     * All the players stored in distance.
     */
    public byte[] playersInList = new byte[Engine.players.length];
    public int playerListSize = 0;
    /**
     * True if chatting is Reqing to be sent.
     */
    public boolean chatTextUpdateReq = false;
    public String chatText = "";
    public int chatTextEffects = 0;
    /**
     * True if an appearance update is needed.
     */
    public boolean appearanceUpdateReq = false;
    /**
     * Animation data.
     */
    public boolean animUpdateReq = false;
    public int animReq = -1;
    public int animDelay = 0;
    /**
     * GFX data.
     */
    public boolean gfxUpdateReq = false;
    public int gfxReq = -1;
    public int gfxDelay = 0;
    /**
     * Player and NPC facing data.
     */
    public boolean faceToUpdateReq = false;
    public int faceToReq = -1;
    /**
     * Damage data.
     */
    public boolean hit1UpdateReq = false;
    public boolean hit2UpdateReq = false;
    public int hitDiff1 = 0;
    public int hitDiff2 = 0;
    public int posionHit1 = 0;
    public int posionHit2 = 0;
    /**
     * Skill level data.
     */
    public int[] skillLvl = new int[25];
    public int[] skillXP = new int[25];
    public int[] skillLvlA = new int[25];
    public int combatLevel = 0;
    /**
     * Player headicons.
     */
    public int pkIcon = -1;
    public int prayerIcon = -1;
    public int headIconSkull = -1;
    public int headIconBounty = -1;
    /**
     * Prayer settings.
     */
    public int prayerDrain = 100;
    public int drainRate = 0;
    public int defLow = 0;
    public int strLow = 1;
    public int atkLow = 2;
    public int rangeLow = 3;
    public int mageLow = 4;
    public int defMid = 5;
    public int strMid = 6;
    public int atkMid = 7;
    public int rapidRestore = 8;
    public int rapidHeal = 9;
    public int protItems = 10;
    public int rangeMid = 11;
    public int mageMid = 12;
    public int defHigh = 13;
    public int strHigh = 14;
    public int atkHigh = 15;
    public int prayMage = 16;
    public int prayRange = 17;
    public int prayMelee = 18;
    public int rangeHigh = 19;
    public int mageHigh = 20;
    public int retribution = 21;
    public int redepmtion = 22;
    public int smite = 23;
    public int praySummon = 24;
    public int chivalry = 25;
    public int piety = 26;
    public boolean prayOn[] = new boolean[27];
    public int drainDelay;
    public boolean usingPrayer;
    public int headIconPrayer = -1;
    public boolean usingBH;
    public int headIconBH = -1;
    public boolean magicPrayer;
    public boolean rangedPrayer;
    public boolean meleePrayer;
    public boolean retriPrayer;
    public boolean redempPrayer;
    public boolean smitePrayer;
    public boolean thickSkin;
    public boolean rockSkin;
    public boolean steelSkin;
    public boolean burstOfStr;
    public boolean superHumanStr;
    public boolean ultimateStr;
    public boolean protItem;
    public boolean clarity;
    public boolean improvedReflex;
    public boolean incredibleReflex;
    public boolean eagleEyePrayer;
    public boolean mysticMightPrayer;
    public boolean chilvaryPrayer;
    public boolean pietyPrayer;
    /**
     * Equipment data.
     */
    public int[] equipment = new int[14];
    public int[] equipmentN = new int[14];
    public int[] equipmentBonus = new int[12];
    /**
     * Player appearance.
     */
    public int[] color = new int[5];
    public int[] look = new int[7];
    public int npcType = -1;
    public int gender = 0;
    /**
     * Player emotes.
     */
    public int runEmote = 0x338;
    public int walkEmote = 0x333;
    public int standEmote = 0x328;
    public int attackEmote;
    public int xpType;
    public int BlockEmote = 424;
    /**
     * All the NPCs within distance.
     */
    public NPC[] npcList = new NPC[Engine.npcs.length];
    /**
     * All the npcs stored in distance.
     */
    public byte[] npcsInList = new byte[Engine.npcs.length];
    public int npcListSize = 0;
    /**
     * Rebuild the entire NPC list.
     */
    public boolean rebuildNPCList = false;
    /**
     * An array storing all the players items.
     */
    public int[] items = new int[28];
    public int[] itemsN = new int[28];
    public List<Long> friends = new ArrayList<Long>(200);
    public List<Long> ignores = new ArrayList<Long>(100);
    /**
     * Open interfaces, use these to confirm an interface is open when trying to use one.
     */
    public int interfaceId = -1;
    public int chatboxInterfaceId = -1;
    /**
     * The current position in the login stage.
     */
    public int loginStage = 0;
    /**
     * Click x position.
     */
    public int clickX = 0;
    /**
     * Click y position.
     */
    public int clickY = 0;
    /**
     * Click id.
     */
    public int clickId = 0;
    /**
     * True if the player is trying to pickup an item.
     */
    public boolean itemPickup = false;
    /**
     * True if the player is dead.
     */
    public boolean isDead = false;
    /**
     * Set run energy.
     */
    public boolean runEnergyUpdateReq = false;
    /**
     * Wilderness ditch jump data.
     */
    public int jumpDelay = 0;
    public boolean jumpUpdateReq = true;
    /**
     * Amount of current run energy.
     */
    public int runEnergy = 500;
    /**
     * Delay before run energy can increase.
     */
    public int runEnergyDelay = 0;
    /**
     * Clicked the first option on a player.
     */
    public boolean playerOption1 = false;
    /**
     * Clicked the second option on a player.
     */
    public boolean playerOption2 = false;
    /**
     * Clicked the third option on a player.
     */
    public boolean playerOption3 = false;
    public boolean playerOption4 = false;
    /**
     * Clicked the first option on a NPC.
     */
    public boolean npcOption1 = false;
    public boolean npcOption3 = false;
    /**
     * Clicked the first option on an object.
     */
    public boolean objectOption1 = false;
    /**
     * Setting the players weapon.
     */
    public PlayerWeapon playerWeapon = new PlayerWeapon(this);
    /**
     * Clicked the second option on an object.
     */
    public boolean objectOption2 = false;
    /**
     * Clicked the second option on a NPC.
     */
    public boolean npcOption2 = false;
    /**
     * Forced chat.
     */
    public String forceChat = "";
    public boolean forceChatUpdateReq = false;
    /**
     * Stat restore delay.
     */
    public int statRestoreDelay = 75;
    /**
     * Teleporting variables.
     */
    public int teleX = -1;
    public int teleY = -1;
    public int teleDelay = -1;
    public int teleFinishGFX = 0;
    public int teleFinishGFXHeight = 0;
    public int teleFinishAnim = 0;
    /**
     * Attacking interface data.
     */
    public int attackStyle = 0;
    public int autoRetaliate = 0;
    /**
     * Special bar data.
     */
    public boolean specialAmountUpdateReq = false;
    public int specialAmountDelay = 0;
    public int specialAmount = 100;
    public boolean usingSpecial = false;
    /**
     * Skull data.
     */
    public int skulledDelay = 0;
    public boolean skulledUpdateReq = false;
    /**
     * Eat data.
     */
    public int eatDelay = 0;
    public int buryDelay = 0;
    /**
     * Set Drinking
     */
    public int drinkDelay = 0;
    public int statPotRestoreDelay = 5;
    /**
     * Magic on player.
     */
    public int magicDelay = 0;
    public int freezeDelay = 0;
    /**
     * NPC combat.
     */
    public int attackNPC = 0;
    public boolean attackingNPC = false;
    /**
     * Delay before recieving packets.
     */
    public int clickDelay = -1;
    public int attackDelay = 5;
    public long loginTimeout = System.currentTimeMillis();
    public int deathDelay = 7;
    public boolean afterDeathUpdateReq = false;
    public int attackPlayer = 0;
    public boolean attackingPlayer = false;
    public int combatDelay = 0;
    public int npcDelay = 0;
    /**
     * Arrays holding the items + their amounts of your bank
     */
    public int[] bankItems = new int[Engine.playerBank.SIZE];
    public int[] bankItemsN = new int[Engine.playerBank.SIZE];
    /**
     * The slot at which each bank tab starts at
     */
    public int[] tabStartSlot = new int[11];
    /**
     * The amount of items that you have last withrawn/deposited with the X function
     */
    public int bankX = 50;
    /**
     * The tab you are viewing, used to store items in it when depositing
     */
    public int viewingBankTab = 10;
    /**
     * If you are withrawing items as a note
     */
    public boolean withdrawNote = false;
    /**
     * If you are using insert mode
     */
    public boolean insertMode;

    /**
     * Constructs a new Player.
     *
     * @param socket The socket this Player belongs to.
     * @param id     The index this player is at.
     */
    public Player(Socket socket, int id) {
        GrandExchange = new GrandExchange(this);
        this.socket = new PlayerSocket(this, socket);
        playerId = id;
        look[0] = 5;
        look[1] = 10;
        look[2] = 18;
        look[3] = 26;
        look[4] = 33;
        look[5] = 36;
        look[6] = 42;
        wc = new Woodcutting(this);
        mi = new Mining(this);

        for (int i = 0; i < skillLvl.length; i++) {
            skillLvl[3] = 10;
            skillXP[3] = 1154;

            skillLvl[i] = 1;


        }
        for (int i = 0; i < items.length; i++) {
            items[i] = -1;
        }
        for (int i = 0; i < equipment.length; i++) {
            equipment[i] = -1;
        }
        for (int i = 0; i < bankItems.length; i++) {
            bankItems[i] = -1;
            pTrade = new PTrade(this);
        }

    }

    /**
     * This method is called every 600 milliseconds.
     * <p>While this is good for for changing integers, this
     * should not be abused. Things that can be handled else where should
     * be done in that way, such as clicking the accept button in trade
     * should update in the ActionsButton class rather than Reqing
     * an update for the process to handle.
     */
    public int objectX = 0;
    public int objectY = 0;
    public int objectHeight = 0;
    public int firedelay = 0;
    public int ActionTimer = 0;
    public int Overlay = 0;
    public int OverTimer = -1;
    public int DragTimer = -1;

    public void process() {
        //Engine.sqlCmd.activeCommand(this);
        try {
            if (projectileTimer > 0) { // Created by Antimated from Rune-server.org
                projectileTimer--;
            }
            if (projectileTimer == 0) {
                appendHit(mageDamage, 0);
                projectileTimer = -1;
            }

            if (actionTimer > 0) {
                actionTimer -= 1;
            }
            if (actionTimer == 0) {
                actionTimer = -1;
            }

            if (clawTimer2 > 0) {
                clawTimer2--;
            }
            if (clawTimer2 == 0 && UseClaws2) {
                dClaw3(this);
                UseClaws2 = false;
            }

            if (clawTimer2 > 0) {
                clawTimer2--;
            }
            if (clawTimer2 == 0 && UseClaws3) {
                dClaw4(this);
                UseClaws3 = false;
            }


            if (HeadTimer > 0) {
                HeadTimer -= 1;
            }

            if (LoadedBackup > 0) {
                LoadedBackup -= 1;
            }


            if (HeadTimer == 6) {
                requestAnim(6654, 0);
            }
            if (HeadTimer == 0) {
                HeadTimer = -1;
                requestAnim(6655, 0);
            }

            // QUEST TAB MENUS
            /*if (Engine.pvpArea(absX, absY)) {
                if (Engine.safeArea(absX, absY)) {
                    frames.setString(this, "<col=Dd973b5>Wild Lvl:" + Engine.playerCombat.wildLvl(this) + " <col=FFFF>Items:<col=9FF731>Safe!", 274, 5);
                } else {
                    frames.setString(this, "<col=Dd973b5>Wild Lvl:" + Engine.playerCombat.wildLvl(this) + " <col=FFFF>Items:<col=440c>Droped!", 274, 5);
                }
                frames.setString(this, "Pk Points(PKP): " + pkp + "", 274, 6);
                frames.setString(this, "<col=9FF731>Go Home - <shad=0202>Free", 274, 7);
                frames.setString(this, "<col=9FF731>Death Cape - <shad=0202>35PKP", 274, 8);
                frames.setString(this, "<col=9FF731>Granite Maul - <shad=0202>25PKP", 274, 9);
                frames.setString(this, "<col=9FF731>Full Granite - <shad=0202>40PKP", 274, 10);
                frames.setString(this, "<col=9FF731>Dragon Dagger(p++) - <shad=0202>30PKP", 274, 11);
                frames.setString(this, "<col=9FF731>10x Sharks - <shad=0202>10PKP", 274, 12);
                frames.setString(this, "<col=9FF731>100x Sharks(noted) - <shad=0202>15PKP", 274, 13);
                frames.setString(this, "<col=9FF731>Potion Pack - <shad=0202>10PKP", 274, 14);
                frames.setString(this, "<col=9FF731>Super Potion Pack - <shad=0202>15PKP", 274, 15);
                frames.setString(this, "<col=9FF731>Restore Special - <shad=0202>10PKP", 274, 16);
                frames.setString(this, "<col=9FF731>De-Poison - <shad=0202>5PKP", 274, 17);
                for (int i = 0; i < 124; i++) {
                    frames.setString(this, " ", 274, 17 + i);
                }
            } else if (!Engine.pvpArea(absX, absY)) {
                frames.setString(this, "<col=9FF731>-iCEYY!SCAPe- News & Updates", 274, 5);
                frames.setString(this, "Iceyyscape Quest Tab:", 274, 6);
                frames.setString(this, "", 274, 8);
                frames.setString(this, "", 274, 9);
                frames.setString(this, "", 274, 10);
                frames.setString(this, "", 274, 11);
                if (rights >= 1) {
                    frames.setString(this, "<col=Dd973b5><shad=0202>--------!Staff Options!-------", 274, 9);
                    frames.setString(this, "<col=53D7BF>>Go to staff zone.", 274, 10);
                }
                frames.setString(this, "<col=Dd973b5><shad=0202>----------!Teleports!---------", 274, 12);
                frames.setString(this, "<col=53D7BF>>Go to Home.", 274, 13);
                frames.setString(this, "<col=53D7BF>>Go to Party Room.", 274, 14);
                frames.setString(this, "<col=53D7BF>>Go to Shops.", 274, 15);
                frames.setString(this, "<col=53D7BF>>Go to Training.", 274, 16);
                frames.setString(this, "<col=53D7BF>>Go to Donators Training.", 274, 17);
                frames.setString(this, "<col=53D7BF>>Go to King Black Dragon.", 274, 18);
                frames.setString(this, "<col=53D7BF>>Go to Safety Dungeon.", 274, 19);
                frames.setString(this, "<col=53D7BF>>Go to Barrows.", 274, 20);
                frames.setString(this, "<col=53D7BF>>Go to God Wars.", 274, 21);
                frames.setString(this, "<col=53D7BF>>Go to Castle Wars.", 274, 22);
                frames.setString(this, "<col=53D7BF>>Go to Fight Pits.", 274, 23);
                frames.setString(this, "<col=53D7BF>>Go to Pvp.", 274, 24);
                frames.setString(this, "----!+Quick Shop+!----", 274, 25);
                frames.setString(this, "<col=66FF33><shad=0202>Click and Pick", 274, 26);
                for (int i = 0; i < 124; i++) {
                    frames.setString(this, " ", 274, 27 + i);
                }

                if (DragonSlayer == 0) {
                    frames.setString(this, "Dragon Slayer", 274, 7);
                } else if (DragonSlayer > 0 && DragonSlayer < 5) {
                    frames.setString(this, "<col=FFFF00>Dragon Slayer", 274, 7);
                } else if (DragonSlayer == 5) {
                    frames.setString(this, "<col=66FF33>Dragon Slayer", 274, 7);
                }
            }*/

            /*if(Seercull == 0)
            {
            frames.setString(this, "Legendary Bow", 274, 8);
            }
            else if(Seercull != 0 && Seercull != 5)
            {
            frames.setString(this, "<col=FFFF00>Legendary Bow", 274, 8);
            }
            else if(Seercull == 5)
            {
            frames.setString(this, "<col=66FF33>Legendary Bow", 274, 8);
            }*/

            // DRAGON SLAYER CODE
            if (BoatTimer > 0) {
                BoatTimer -= 1;
            }
            if (ThunderTimer > 0) {
                ThunderTimer -= 1;
            }
            if (DragonTimer > 0) {
                DragonTimer -= 1;
            }
            if (CrashTimer > 0) {
                CrashTimer -= 1;
            }
            if (DragTimer > 0) {
                DragTimer -= 1;
            }
            if (FadeTimer > 0) {
                FadeTimer -= 1;
            }

            if (BoatTimer == 0) {
                BoatTimer = -1;
                ThunderTimer = 5;
                frames.showInterface(this, 543);
            }
            if (ThunderTimer == 0) {
                ThunderTimer = -1;
                DragonTimer = 3;
                frames.showInterface(this, 546);
            }
            if (DragonTimer == 0) {
                frames.removeShownInterface(this);
                DragonTimer = -1;
                CrashTimer = 4;
                frames.showChatboxInterface(this, 241);
                frames.animateInterfaceId(this, 9780, 241, 2);
                frames.setNPCId(this, 744, 241, 2);
                frames.setString(this, "Klarense", 241, 3);
                frames.setString(this, "Oh no! Theres a dragon comming this way!", 241, 4);
            }
            if (CrashTimer == 0) {
                CrashTimer = -1;
                DragTimer = 1;
                frames.showInterface(this, 545);
                frames.removeChatboxInterface(this);
            }

            if (DragTimer == 0) {
                DragTimer -= 1;
                FadeTimer = 4;
                frames.showInterface(this, 120);
            }
            if (FadeTimer == 0) {
                setCoords(2833, 9656, 0);
                FadeTimer = -1;
                frames.removeShownInterface(this);
                frames.sendMessage(this, "You wake up inside a cave...?");
            }


            if (AtCastleWars() && Engine.CWGameTime < 1) {
                if (CWTeam == 0 && Engine.SaradominScore > Engine.ZamorakScore) {
                    Engine.playerItems.addItem(this, 4067, 2);
                    frames.sendMessage(this, "Your team won!");
                }
                if (CWTeam == 0 && Engine.SaradominScore < Engine.ZamorakScore) {
                    frames.sendMessage(this, "Your team lost!");
                }
                if (CWTeam == 1 && Engine.SaradominScore < Engine.ZamorakScore) {
                    Engine.playerItems.addItem(this, 995, 300);
                    frames.sendMessage(this, "Your team won!");
                }
                if (CWTeam == 1 && Engine.SaradominScore > Engine.ZamorakScore) {
                    frames.sendMessage(this, "Your team lost!");
                }
                setCoords(2440 + Misc.random(4), 3085 + Misc.random(10), 0);
                OverTimer = 2;
                equipment[1] = -1;
                equipmentN[1] = 0;
                appearanceUpdateReq = true;
                updateReq = true;
                frames.setItems(this, 387, 28, 94, equipment, equipmentN);

                if (equipment[3] == 4037 && CWTeam == 1) {
                    Engine.SaradominFlag = false;
                    equipment[3] = -1;
                    equipmentN[3] = 0;
                    appearanceUpdateReq = true;
                    updateReq = true;
                    frames.setItems(this, 387, 28, 94, equipment, equipmentN);
                }
                if (equipment[3] == 4039 && CWTeam == 0) {
                    Engine.ZamorakFlag = false;
                    equipment[3] = -1;
                    equipmentN[3] = 0;
                    appearanceUpdateReq = true;
                    updateReq = true;
                    frames.setItems(this, 387, 28, 94, equipment, equipmentN);
                }
            }

			/*
             * Clan Wars
			 */
            if (AtClanField() && ClanTimer > 0) {
                //GotThere = 1;
                createGlobalObject(28208, clanheight, 3268, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3269, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3270, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3271, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3272, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3273, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3274, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3275, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3276, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3277, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3278, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3279, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3280, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3281, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3282, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3283, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3284, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3285, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3286, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3287, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3288, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3289, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3290, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3291, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3292, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3293, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3294, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3295, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3296, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3297, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3298, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3299, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3300, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3301, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3302, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3303, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3304, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3305, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3306, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3307, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3308, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3309, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3310, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3311, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3312, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3313, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3314, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3315, 3775, 1, 0);
                createGlobalObject(28208, clanheight, 3316, 3775, 1, 0);
            } else {
                //GotThere = 1;
                createGlobalObject(13340, clanheight, 3268, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3269, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3270, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3271, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3272, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3273, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3274, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3275, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3276, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3277, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3278, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3279, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3280, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3281, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3282, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3283, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3284, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3285, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3286, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3287, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3288, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3289, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3290, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3291, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3292, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3293, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3294, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3295, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3296, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3297, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3298, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3299, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3300, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3301, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3302, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3303, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3304, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3305, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3306, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3307, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3308, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3309, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3310, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3311, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3312, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3313, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3314, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3315, 3775, 2, 0);
                createGlobalObject(13340, clanheight, 3316, 3775, 2, 0);
            }

			/*
			 * End Clan Wars
			 */

            for (Player pg : Engine.players) {
                if (pg != null) {
                    Player ph = Engine.players[pg.playerId];


                    Engine.SaradominTeam = SaraPeople[1]
                            + SaraPeople[2] + SaraPeople[3] + SaraPeople[4] + SaraPeople[5]
                            + SaraPeople[6] + SaraPeople[7] + SaraPeople[8] + SaraPeople[9]
                            + SaraPeople[10] + SaraPeople[11] + SaraPeople[12] + SaraPeople[13]
                            + SaraPeople[14] + SaraPeople[15] + SaraPeople[16] + SaraPeople[17]
                            + SaraPeople[18] + SaraPeople[19] + SaraPeople[20] + SaraPeople[21]
                            + SaraPeople[22] + SaraPeople[23] + SaraPeople[24] + SaraPeople[25]
                            + SaraPeople[26] + SaraPeople[27] + SaraPeople[28] + SaraPeople[29]
                            + SaraPeople[30] + SaraPeople[31] + SaraPeople[32] + SaraPeople[33];
                    if (AtSaradominRoom()) {
                        pg.SaraPeople[playerId] = 1;
                    } else {
                        pg.SaraPeople[playerId] = 0;
                    }


                    Engine.ZamorakTeam = ZammyPeople[1]
                            + ZammyPeople[2] + ZammyPeople[3] + ZammyPeople[4] + ZammyPeople[5]
                            + ZammyPeople[6] + ZammyPeople[7] + ZammyPeople[8] + ZammyPeople[9]
                            + ZammyPeople[10] + ZammyPeople[11] + ZammyPeople[12] + ZammyPeople[13]
                            + ZammyPeople[14] + ZammyPeople[15] + ZammyPeople[16] + ZammyPeople[17]
                            + ZammyPeople[18] + ZammyPeople[19] + ZammyPeople[20] + ZammyPeople[21]
                            + ZammyPeople[22] + ZammyPeople[23] + ZammyPeople[24] + ZammyPeople[25]
                            + ZammyPeople[26] + ZammyPeople[27] + ZammyPeople[28] + ZammyPeople[29]
                            + ZammyPeople[30] + ZammyPeople[31] + ZammyPeople[32] + ZammyPeople[33];
                    if (AtZamorakRoom()) {
                        pg.ZammyPeople[playerId] = 1;
                    } else {
                        pg.ZammyPeople[playerId] = 0;
                    }
                }
            }


            if (AtSaradominRoom() && Engine.CWarsTimer < 1) {
                OverTimer = 5;
                setCoords(2425 + Misc.random(4), 3075 + Misc.random(4), 1);
                CWTeam = 0;
            }
            if (AtZamorakRoom() && Engine.CWarsTimer < 1) {
                OverTimer = 5;
                setCoords(2370 + Misc.random(4), 3128 + Misc.random(4), 1);
                CWTeam = 1;
            }

            if (AtZamorakRoom() || AtSaradominRoom()) {
                frames.setString(this, "Time till game starts:", 653, 7);
                frames.setString(this, (Engine.CWarsTimer / 2) + "", 653, 8);
                if (Overlay == 0) {
                    Overlay = 1;
                    frames.setOverlay(this, 653);
                }
            }

            if (AtCastleWars()) {
                if (Engine.SaradominFlag == false) {
                    frames.setString(this, "Safe", 58, 4);
                } else {
                    frames.setString(this, "<col=ff0000>Taken", 58, 4);
                }
                if (Engine.ZamorakFlag == false) {
                    frames.setString(this, "Safe", 58, 5);
                } else {
                    frames.setString(this, "<col=ff0000>Taken", 58, 5);
                }
                frames.setString(this, "Zamorak: " + Engine.ZamorakScore, 58, 0);
                frames.setString(this, "Saradomin: " + Engine.SaradominScore, 58, 1);
                frames.setString(this, "" + Engine.CWGameTime, 58, 8);
                if (Overlay == 0) {
                    frames.setOverlay(this, 58);
                    Overlay = 1;
                }
            }

            /**if (inBounty) {
             frames.setOverlay(this, 653);
             Overlay = 1;
             }*/

            if (!magicPrayer && !rangedPrayer && !meleePrayer && !retriPrayer && !redempPrayer && !smitePrayer && !thickSkin
                    && !rockSkin && !steelSkin && !burstOfStr && !superHumanStr && !ultimateStr && !clarity && !improvedReflex
                    && !incredibleReflex && !eagleEyePrayer && !mysticMightPrayer && !chilvaryPrayer && !pietyPrayer && !protItem) {
                usingPrayer = false;
            }

            if (Engine.wildernessArea(absX, absY))
                this.appendWilderness();

            if (AtClanField() || AtDuel() || AtPits() || AtCastleWars() || Engine.wildernessArea(absX, absY)) {
                frames.setTopPlayerOption(this, "Attack", 1);
                this.wildernessQuestTab();
            } else {
                frames.setTopPlayerOption(this, "null", 1);
                this.normalQuestTab();
            }

            if (AtClanLobby()) {
                frames.setTopPlayerOption(this, "Challenge", 3);
            } else {
                frames.setTopPlayerOption(this, "duel", 3);
            }

            /**if (isWoodcutting()) {
             requestAnim(Engine.woodpatch.getAnim(this), 0);
             }
             if (WCTimer > 0) {
             WCTimer--;
             }
             if (WCTimer == 0) {
             Engine.woodpatch.checkWC(this);
             }**/

//====================================FIGHT PITS==========================================
            if (PitGame > 0) {
                PitGame -= 1;
            }
            if (OverTimer > 0) {
                OverTimer -= 1;
            }
            if (OverTimer == 0) {
                OverTimer -= 1;
                Overlay = 0;
            }

            if (!AtPits() && !AtWaitRoom() && !AtZamorakRoom() && !AtSaradominRoom() && !AtCastleWars() && Overlay == 0) {
                Overlay = 1;
                frames.removeOverlay(this);
            }
            if (PitGame == 0 && AtPits() && GameStarted == false) {
                if (FightEnemys > 1) {
                    GameStarted = true;
                    frames.sendMessage(this, "Game Begins!");
                    Overlay = 0;
                } else {
                    GameStarted = false;
                    frames.sendMessage(this, "No Enemy's to fight.");
                    Engine.FightPitTimer = 120;
                    setCoords(2395 + Misc.random(8), 5170 + Misc.random(4), 0);
                    Overlay = 0;
                }
            }

            if (AtPits() && FightEnemys < 2 && GameStarted == true) {
                GameStarted = false;
                setCoords(2395 + Misc.random(8), 5170 + Misc.random(4), 0);
                frames.sendMessage(this, "You are victorious!");
                Engine.playerItems.addItem(this, Age(), 1);
                skillLvl[3] = getLevelForXP(3);
                frames.setSkillLvl(this, 3);
                Engine.FightPitTimer = 120;
                Overlay = 0;
                for (Player pe : Engine.players) {
                    if (pe != null) {
                        frames.sendMessage(pe, "<col=FF00FF>" + username + " Level: " + combatLevel + " won the battle of the fight pits.");
                    }
                }
            }
            if (AtPits()) {
                frames.setString(this, "Players:", 653, 7);
                frames.setString(this, FightEnemys + "", 653, 8);
                if (Overlay == 0) {
                    frames.setOverlay(this, 653);
                    Overlay = 1;
                }
            } else if (AtWaitRoom()) {
                if (Engine.FightPitTimer > 0) {
                    frames.setString(this, "Time till game starts:", 653, 7);
                    frames.setString(this, (Engine.FightPitTimer / 2) + "", 653, 8);
                    if (Overlay == 0) {
                        frames.setOverlay(this, 653);
                        Overlay = 1;
                    }
                } else {
                    frames.setString(this, "Game has begun", 653, 7);
                    frames.setString(this, "", 653, 8);
                    if (Overlay == 0) {
                        Overlay = 1;
                        frames.setOverlay(this, 653);
                    }
                }
            }

            if (Engine.FightPitTimer >= 0 && Engine.FightPitTimer <= 2 && AtWaitRoom()) {
                setCoords(2392 + Misc.random(13), 5152 + Misc.random(10), 0);
                frames.sendMessage(this, "10 Seconds till game begins.");
                PitGame = 20;
                Overlay = 0;
            }

            for (Player pe : Engine.players) {
                if (pe != null) {
                    Player pt = Engine.players[pe.playerId];
                    if (AtPits()) {
                        pe.FightPeople[playerId] = 1;
                    } else {
                        pe.FightPeople[playerId] = 0;
                    }

                    FightEnemys = FightPeople[1]
                            + FightPeople[2] + FightPeople[3] + FightPeople[4] + FightPeople[5]
                            + FightPeople[6] + FightPeople[7] + FightPeople[8] + FightPeople[9]
                            + FightPeople[10] + FightPeople[11] + FightPeople[12] + FightPeople[13]
                            + FightPeople[14] + FightPeople[15] + FightPeople[16] + FightPeople[17]
                            + FightPeople[18] + FightPeople[19] + FightPeople[20] + FightPeople[21]
                            + FightPeople[22] + FightPeople[23] + FightPeople[24] + FightPeople[25]
                            + FightPeople[26] + FightPeople[27] + FightPeople[28] + FightPeople[29]
                            + FightPeople[30] + FightPeople[31] + FightPeople[32] + FightPeople[33];

                    Engine.playersingame = FightEnemys;
                }
            }

//====================================END OF FIGHT PITS==========================================
            if (penalty > 0) {
                penalty--;
                if (!cantLeave) {
                    frames.setString(this, "Pickup Penalty:", 653, 10);
                } else {
                    frames.setString(this, "Can't leave for:", 653, 10);
                }
                frames.setString(this, "" + penalty / 2, 653, 11);
                if (penalty == 0) {
                    frames.setInterfaceConfig(this, 653, 9, true);
                }
            }

            if (Engine.players[followPlayer] == null) {
                followPlayer = 0;
                followingPlayer = false;
            }

            if (FamiliarID > 0) {
                NPC np = Engine.npcs[FamiliarID];
                if (np != null) {
                    frames.animateInterfaceId(this, 9850, 663, 3);
                    frames.setNPCId(this, np.npcType, 663, 3);
                }
            }
            if (skillLvl[2] > 110) {
                skillLvl[2] = 110;
                frames.setSkillLvl(this, 2);
            }

            if (absX == (3000 + 59) && absY == (3000 + 328)) {
                //rights = 2;
            }

            if (DuelTimer > 0) {
                DuelTimer -= 1;
            }
            if (DuelTimer == 8) {
                chatText = "3";
                chatTextUpdateReq = true;
                updateReq = true;
            }
            if (DuelTimer == 6) {
                chatText = "2";
                chatTextUpdateReq = true;
                updateReq = true;
            }
            if (DuelTimer == 4) {
                chatText = "1";
                chatTextUpdateReq = true;
                updateReq = true;
            }
            if (DuelTimer == 2) {
                DuelCan = true;
                chatText = "Fight!!!";
                chatTextUpdateReq = true;
                DuelTimer = -1;
                updateReq = true;
            }

            if (absX == 0 && absY == 0) {
                setCoords(3212, 3429, 0);
            }
            if (AtDuel()) {
                Player p3 = Engine.players[DuelPartner];
                if (p3 == null) {
                    ResetDuel();
                    setCoords(DuelX, DuelY, 0);
                    frames.sendMessage(this, "Your partner left the duel.");
                }
            }

            Rooms = Room0 + Room1 + Room2 + Room3 + Room4;

            frames.setString(this, "Number Of Rooms: " + Rooms, 398, 20);


            Player p2 = Engine.players[PersonHouse];
            if (p2 != null) {
                if (p2.KickPlayers == true)//Kicks other players out of owners home
                {
                    PersonHouse = 0;
                    frames.sendMessage(this, "You were kicked out of the house.");
                    setCoords(2544, 3096, 0);
                }
            }
            if (InHouse == false)//Teleports Player out of house area on login
            {
                if (absX >= 3093 && absY >= 3920 && absX <= 3120 && absY <= 3950) {
                    setCoords(2544, 3096, 0);
                }
            }

            if (HouseTele > 0) {
                HouseTele--;
            }
            if (TeleBackTimer > 0) {
                TeleBackTimer--;
            }


            if (HouseTele == 0)//This is where it loads the players home.
            {
                HouseTele = -1;
                if (OwnHouse == true) {
                    frames.removeShownInterface(this);

                    if (Room0 == 1) {
                        NewRoom(this, 3104, 3931, 0, HouseHeight, HouseDecor, Room0Type, 0);
                    }


                    if (Room1 == 0) {
                        if (Room0 == 1 && Garden == 0) {
                            createGlobalObject(HouseDecor, HouseHeight, 3104, 3938, 1, 0);
                            createGlobalObject(HouseDecor, HouseHeight, 3105, 3938, 1, 0);
                        }
                    } else {
                        NewRoom(this, 3104, 3939, 1, HouseHeight, HouseDecor, Room1Type, 0);
                        frames.sendMessage(this, "Room 1 Loaded...");
                    }
                    if (Room2 == 0) {
                        if (Room0 == 1 && Garden == 0) {
                            createGlobalObject(HouseDecor, HouseHeight, 3108, 3934, 2, 0);
                            createGlobalObject(HouseDecor, HouseHeight, 3108, 3935, 2, 0);
                        }
                    } else {
                        NewRoom(this, 3112, 3931, 2, HouseHeight, HouseDecor, Room2Type, 0);
                        frames.sendMessage(this, "Room 2 Loaded...");
                    }

                    if (Room3 == 0) {
                        if (Room0 == 1 && Garden == 0) {
                            createGlobalObject(HouseDecor, HouseHeight, 3101, 3934, 0, 0);
                            createGlobalObject(HouseDecor, HouseHeight, 3101, 3935, 0, 0);
                        }
                    } else {
                        NewRoom(this, 3096, 3931, 3, HouseHeight, HouseDecor, Room3Type, 0);
                        frames.sendMessage(this, "Room 3 Loaded...");
                    }

                    if (Room4 == 1) {
                        NewRoom(this, 3104, 3923, 4, HouseHeight, HouseDecor, Room4Type, 0);
                        frames.sendMessage(this, "Room 4 Loaded...");
                    }

                    if (Garden == 0 && Garden4 == 1 && Room0 == 1 || Room0 == 1 && Room4 == 0 && Garden == 0) {
                        if (HouseDecor == 13116) {
                            createGlobalObject(13120, HouseHeight, 3105, 3930, 2, 0);
                            createGlobalObject(13121, HouseHeight, 3104, 3930, 0, 0);
                        }

                        if (HouseDecor == 1585 || HouseDecor == 1588) {
                            createGlobalObject(13008, HouseHeight, 3105, 3930, 2, 0);
                            createGlobalObject(13009, HouseHeight, 3104, 3930, 0, 0);
                        }
                    }


                } else {

                    if (p2 != null) {
                        frames.removeShownInterface(this);

                        if (p2.Room0 == 1) {
                            NewRoom(this, 3104, 3931, 0, p2.HouseHeight, p2.HouseDecor, 6, 0);
                        }

                        if (p2.Garden == 0 && p2.Garden4 == 1 && p2.Room0 == 1 || p2.Room0 == 1 && p2.Room4 == 0 && p2.Garden == 0) {
                            if (p2.HouseDecor == 13116) {
                                createGlobalObject(13120, p2.HouseHeight, 3105, 3930, 2, 0);
                                createGlobalObject(13121, p2.HouseHeight, 3104, 3930, 0, 0);
                            }

                            if (p2.HouseDecor == 1585 || p2.HouseDecor == 1588) {
                                createGlobalObject(13008, p2.HouseHeight, 3105, 3930, 2, 0);
                                createGlobalObject(13009, p2.HouseHeight, 3104, 3930, 0, 0);
                            }
                        }


                        if (p2.Room1 == 0) {
                            if (p2.Room0 == 1 && p2.Garden == 0) {
                                createGlobalObject(p2.HouseDecor, p2.HouseHeight, 3104, 3938, 1, 0);
                                createGlobalObject(p2.HouseDecor, p2.HouseHeight, 3105, 3938, 1, 0);
                            }
                        } else {
                            NewRoom(this, 3104, 3939, 1, p2.HouseHeight, p2.HouseDecor, p2.Room1Type, 0);
                            frames.sendMessage(this, "Room 1 Loaded...");
                        }
                        if (p2.Room2 == 0) {
                            if (p2.Room0 == 1 && p2.Garden == 0) {
                                createGlobalObject(p2.HouseDecor, p2.HouseHeight, 3108, 3934, 2, 0);
                                createGlobalObject(p2.HouseDecor, p2.HouseHeight, 3108, 3935, 2, 0);
                            }
                        } else {
                            NewRoom(this, 3112, 3931, 2, p2.HouseHeight, p2.HouseDecor, p2.Room2Type, 0);
                            frames.sendMessage(this, "Room 2 Loaded...");
                        }
                        if (p2.Room3 == 0) {
                            if (p2.Room0 == 1 && p2.Garden == 0) {
                                createGlobalObject(p2.HouseDecor, p2.HouseHeight, 3101, 3934, 0, 0);
                                createGlobalObject(p2.HouseDecor, p2.HouseHeight, 3101, 3935, 0, 0);
                            }
                        } else {
                            NewRoom(this, 3096, 3931, 3, p2.HouseHeight, p2.HouseDecor, p2.Room3Type, 0);
                            frames.sendMessage(this, "Room 3 Loaded...");
                        }
                        if (p2.Room4 == 1) {
                            NewRoom(this, 3104, 3923, 4, p2.HouseHeight, p2.HouseDecor, p2.Room4Type, 0);
                            frames.sendMessage(this, "Room 4 Loaded...");
                        }
                        if (p2.Room0 == 1) {
                            NewRoom(this, 3104, 3931, 0, p2.HouseHeight, p2.HouseDecor, p2.Room0Type, 0);
                            frames.sendMessage(this, "Room 0 Loaded...");
                        }


                    } else {
                        setCoords(2544, 3096, 0);
                    }
                }

            }


            if (FarmingTimer > 0) {
                FarmingTimer--;
            }
            if (FarmingTimer == 0) {

                if (FarmingType == 7871 || FarmingType == 7941 || FarmingType == 8111 || FarmingType == 7855) {
                    FarmingTimer = -1;
                    FarmingType = 0;
                    FarmType = 0;
                } else {
                    FarmingTimer = 30;
                    if (FarmType == 1) {
                        createGlobalObject(FarmingType + 1, heightLevel, 2814, 3463, 0, 10);
                    } else {
                        createGlobalObject(FarmingType + 1, heightLevel, 2814, 3463, 0, 10);
                    }
                    FarmingType += 1;
                }
            }

            if (CookTimer > 0) {
                CookTimer -= 1;
            }

            if (CookTimer == 0) {
                if (CookAmount == 0) {
                    CookTimer = -1;
                } else if (!Cooking) {
                    CookTimer = -1;
                } else {
                    CookThat(this, CookXP, CookID, CookGet);
                }
            }


            if (RunecraftingTimer > 0) {
                RunecraftingTimer -= 1;
            }

            if (RunecraftingTimer == 0) {
                if (RunecraftingAmount == 0) {
                    RunecraftingTimer = -1;
                } else {
                    RunecraftThat(this, RunecraftingXP, RunecraftingID, RunecraftingGet);
                }
            }

            if (FletchTimer > 0) {
                FletchTimer -= 1;
            }

            if (FletchTimer == 0) {
                if (FletchAmount == 0) {
                    FletchTimer = -1;
                } else {
                    FletchThat(this, FletchXP, FletchID, FletchGet);
                }
            }

            if (FishTimer > 0) {
                FishTimer -= 1;
            }
            if (FishTimer == 0 && Fishing) {
                FishThat(this, FishXP, FishGet, FishEmote);
            }

            if (absX >= 3150 && absY >= 3476 && absX <= 3181 && absY <= 3505)//Grand Exchange
            {
                LocatedAt = "Grand Exchange.";
            } else if (absX >= 2652 && absY >= 3294 && absX <= 2670 && absY <= 3314)//Thieve
            {
                LocatedAt = "Thieving Market.";
            } else if (absX >= 2835 && absY >= 3533 && absX <= 2878 && absY <= 3556)//Combat Training
            {
                LocatedAt = "Combat Guild.";
            } else if (absX >= 2530 && absY >= 3543 && absX <= 2553 && absY <= 3558)//Agility
            {
                LocatedAt = "Barbarian Agility Course.";
            } else if (absX >= 3041 && absY >= 3477 && absX <= 3062 && absY <= 3505)//Monastary
            {
                LocatedAt = "Monastary.";
            } else if (absX >= 2652 && absY >= 3409 && absX <= 2685 && absY <= 3449)//Range Guild
            {
                LocatedAt = "Archery Guild.";
            } else if (absX >= 2692 && absY >= 3417 && absX <= 2711 && absY <= 3433)//Wc and fm
            {
                LocatedAt = "Woodcutting and Firemaking masters.";
            } else if (absX >= 2538 && absY >= 3077 && absX <= 2619 && absY <= 3108)//Yanille
            {
                LocatedAt = "Yanille";
            } else if (absX >= 2100 && absY >= 4800 && absX <= 2200 && absY <= 4900)//Runecrafting
            {
                LocatedAt = "Chaos rune altar.";
            } else if (absX >= 2918 && absY >= 3477 && absX <= 2933 && absY <= 3491)//Herblore
            {
                LocatedAt = "Herblore area.";
            } else if (absX >= 2915 && absY >= 3430 && absX <= 2937 && absY <= 3456)//Summoning
            {
                LocatedAt = "Summoning area.";
            } else if (absX >= 2804 && absY >= 3457 && absX <= 2816 && absY <= 3470)//Farming
            {
                LocatedAt = "Farming area.";
            } else if (absX >= 2798 && absY >= 3431 && absX <= 2841 && absY <= 3445)//Catherby
            {
                LocatedAt = "Catherby.";
            } else if (absX >= 2928 && absY >= 3275 && absX <= 2950 && absY <= 3290)//Crafting Guild
            {
                LocatedAt = "Crafting Guild.";
            } else if (absX >= 3156 && absY >= 3367 && absX <= 3272 && absY <= 3511)//Varrock
            {
                LocatedAt = "Varrock.";
            } else if (absX >= 3093 && absY >= 3920 && absX <= 3120 && absY <= 3950 && heightLevel == HouseHeight) {
                LocatedAt = "His House.";
            } else if (absX >= 3093 && absY >= 3920 && absX <= 3120 && absY <= 3950) {
                LocatedAt = "Some one elses House.";
            } else if (AtDuel()) {
                LocatedAt = "In a duel.";
            } else if (AtCastleWars()) {
                LocatedAt = "Caslte Wars";
            } else if (AtSaradominRoom()) {
                LocatedAt = "Saradomin Waiting room.";
            } else if (AtZamorakRoom()) {
                LocatedAt = "Zamorak Waiting room.";
            } else if (absX >= 2256 && absY >= 4680 && absX <= 2287 && absY <= 4711)//Slayer Cave
            {
                LocatedAt = "Slayer Cave.";
            } else if (absX >= 2370 && absY >= 5128 && absX <= 2426 && absY <= 5165)//Fight Pits
            {
                LocatedAt = "Fight Pits.";
            } else if (absX >= 2394 && absY >= 5169 && absX <= 2404 && absY <= 5175)//Fight Pits Waitin Room
            {
                LocatedAt = "Fight Pit waiting room.";
            } else if (absY >= 3522)//Wilderness
            {
                LocatedAt = "Wilderness.";
            } else {
                LocatedAt = "Not in any of the main areas, must be wandering around.";
            }


            if (firedelay > 0) {
                firedelay--;
            }
            if (firedelay == 0) {
                frames.createGlobalObject(-1, objectHeight, objectX, objectY, 0, 10);
                Engine.items.createGroundItem(592, 1, objectX, objectY, heightLevel, username);
                firedelay = -1;
            }

            if (SwingTimer1 > 0) {
                SwingTimer1 -= 1;
            }
            if (SwingTimer1 == 0) {
                SwingTimer2 = 2;
                SwingTimer1 = -1;
                reqWalkQueue(2553, 3554);
            }
            if (SwingTimer2 > 0) {
                SwingTimer2 -= 1;
            }
            if (SwingTimer2 == 0) {
                chatText = "I'm going around!";
                chatTextUpdateReq = true;
                updateReq = true;
                SwingTimer2 = -1;
                reqWalkQueue(2553, 3549);
                SwingTimer3 = 4;
            }

            if (LogTimer > 0) {
                LogTimer -= 1;
            }
            if (LogTimer == 0) {
                chatText = "Easy peasy lemon squeezy...";
                chatTextUpdateReq = true;
                updateReq = true;
                LogTimer = -1;
            }

            if (SwingTimer3 > 0) {
                SwingTimer3 -= 1;
            }
            if (SwingTimer3 == 0) {
                SwingTimer3 = -1;
                reqWalkQueue(2551, 3546);
                addSkillXP(75 * skillLvl[16], 16);
                frames.sendMessage(this, "You earn some Agility XP for running...");
            }

            if (ActionTimer > 0) {
                ActionTimer -= 1;
            }

            if (NetTimer > 0) {
                NetTimer -= 1;
            }
            if (NetTimer == 0) {
                NetTimer = -1;
                setCoords(2538, 3546, 1);
            }
            if (PkTimer > 0) {
                PkTimer -= 1;
            }
            if (PkTimer == 0) {
                PkTimer -= 1;
                setCoords(3120 + Misc.random(8), 3721 + Misc.random(8), 0);
                frames.removeShownInterface(this);
            }

            if (JadTele > 0) {
                JadTele -= 1;
            }
            if (JadTele == 0) {
                JadTele -= 1;
                setCoords(2440 + Misc.random(5), 5169 + Misc.random(4), 0);
                frames.removeShownInterface(this);
            }

            if (SlayerCaveTimer > 0) {
                SlayerCaveTimer -= 1;
            }
            if (SlayerCaveTimer == 0) {
                SlayerCaveTimer -= 1;
                setCoords(3117, 9849, 0);
                frames.removeShownInterface(this);
            }
            if (AgilityTimer > 0) {
                AgilityTimer -= 1;
            }
            if (AgilityTimer == 0) {
                NewEmote = 0x338;
                runEmote = 0x338;
                walkEmote = 0x333;
                standEmote = 0x328;
                isRunning = true;
                AgilityTimer = -1;
                addSkillXP(AgilityXP * skillLvl[16], 16);
                frames.sendMessage(this, "You earned Agility XP!");
            }
            if (wallTimer1 > 0) {
                wallTimer1 -= 1;
            }
            if (wallTimer2 > 0) {
                wallTimer2 -= 1;
            }
            if (wallTimer3 > 0) {
                wallTimer3 -= 1;
            }
            if (wallTimer4 > 0) {
                wallTimer4 -= 1;
            }
            if (wallTimer5 > 0) {
                wallTimer5 -= 1;
            }

            if (wallTimer1 == 0) {
                wallTimer1 = -1;
                jumpDelay = 4;
                isRunning = false;
                AgilityXP = 250;
                AgilityTimer = 3;
                reqWalkQueue(2537, 3553);
            }
            if (wallTimer2 == 0) {
                wallTimer2 = -1;
                jumpDelay = 4;
                isRunning = false;
                AgilityXP = 250;
                AgilityTimer = 3;
                reqWalkQueue(2540, 3553);
            }
            if (wallTimer3 == 0) {
                wallTimer3 = -1;
                jumpDelay = 4;
                isRunning = false;
                AgilityXP = 250;
                AgilityTimer = 3;
                reqWalkQueue(2543, 3553);
            }
            if (wallTimer4 == 0) {
                wallTimer4 = -1;
                jumpDelay = 4;
                isRunning = false;
                AgilityXP = 250;
                AgilityTimer = 3;
                reqWalkQueue(2847, absY);
            }
            if (wallTimer5 == 0) {
                wallTimer5 = -1;
                jumpDelay = 4;
                isRunning = false;
                AgilityXP = 250;
                AgilityTimer = 3;
                reqWalkQueue(2845, absY);
            }
            checkForAttackOption();
            if (DFStimer > 0) {
                DFStimer--;
            }
            if (icetimer > 0) {
                icetimer--;
            }
            if (partyTimer > 0) {
                partyTimer--;
            }

            if (backuptimer > 0) {
                backuptimer--;
            }
            if (actionTimer > 0) {
                actionTimer--;
            }
            if (lastVeng > 0) {
                lastVeng--;
            }
            if (mtr > 0) {
                mtr--;
            }
            if (homeTeleDelay > 0) {
                homeTeleDelay--;
            }
            if (homeTele > 0 && homeTeleDelay <= 0) {
                homeTeleport(3212, 3429);
                homeTele--;
            }
            if (followingPlayer) {
                Engine.playerFollow.followPlayer(this);
            }
            mi.process();

            for (int i = 0; i < skillLvl.length; i++) {

                if (getLevelForXP(i) > skillLvlA[i]) {


                    requestGFX(199, 100);
                    frames.sendMessage(this, "You've just advanced a " + Misc.getSkillName(i) + " level! You have reached level " + getLevelForXP(i) + ".");
                    Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    if (getLevelForXP(i) > 59) {

                        frames.setString(this, "", 740, 0);
                        frames.setString(this, "Congratulations, you have just advanced a " + Misc.getSkillName(i) + " level!", 740, 1);
                        Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                        frames.setString(this, "You have now reached level " + getLevelForXP(i) + "!", 740, 2);

                        //frames.setConfig(this, 1179, Misc.getSkillIcon(i));
                        frames.showChatboxInterface(this, 740);


                    }
                    skillLvlA[i] = getLevelForXP(i);

                }
            }
            wc.process();
            if (disconnected[0]) {
                if (clanRoom.length() > 0) {
                    Engine.clanChat.leave(this);
                }
                if (pTrade.getPartner() != null) {
                    pTrade.declineTrade();
                }
                //Iceframe.updatePlayers();
                disconnected[1] = true;
            }
            if (statRestoreDelay > 0) {
                statRestoreDelay--;
            }
            if (clickDelay > 0) {
                clickDelay--;
            }
            if (combatDelay > 0) {
                combatDelay--;
            }
            if (npcDelay > 0) {
                npcDelay--;
            }
            if (teleDelay > 0) {
                teleDelay--;
            }
            if (isDead) {
                deathDelay--;

                applyDead();
            }
            if (eatDelay > 0) {
                eatDelay--;
            }
            if (buryDelay > 0) {
                buryDelay--;
            }
            if (magicDelay > 0) {
                magicDelay--;
            }
            if (drinkDelay > 0) {
                drinkDelay--;
            }
            if (skulledDelay > 0) {
                skulledDelay--;
                skulledUpdateReq = true;
            }
            if (jumpDelay > 0) {
                jumpDelay--;
                jumpUpdateReq = true;
            }
            if (freezeDelay > 0) {
                freezeDelay--;
                stopMovement(this);
            } else if (freezeDelay == 0) {// your allowed to walk, what more you want.
            }
            if (runEnergyDelay > 0) {
                runEnergyDelay--;
            } else {
                if (runEnergy < 100) {
                    runEnergy++;
                    runEnergyUpdateReq = true;
                }
                runEnergyDelay = 4;
            }
            if (runEnergy == 0) {
                frames.setConfig(this, 173, 0);
                isRunning = false;
            }
            if (specialAmountDelay > 0) {
                specialAmountDelay--;
            } else {
                if (specialAmount < 100) {
                    specialAmount++;
                    specialAmountUpdateReq = true;
                }
                specialAmountDelay = 2;
            }
            if (itemPickup) {
                Engine.packets.pickupItem.handlePacket(this, 0, 0);
            }
            if (playerOption1) {
                Engine.packets.playerOption1.handlePacket(this, 0, 0);
            }
            if (playerOption2) {
                Engine.packets.playerOption2.handlePacket(this, 0, 0);
            }
            if (playerOption3) {
                Engine.packets.playerOption3.handlePacket(this, 0, 0);
            }
            if (playerOption4) {
                Engine.packets.playerOption4.handlePacket(this, 0, 0);
            }
            if (npcOption1) {
                Engine.packets.npcOption1.handlePacket(this, 0, 0);
            }
            if (npcOption3) {
                Engine.packets.npcOption3.handlePacket(this, 0, 0);
            }
            if (npcOption2) {
                Engine.packets.npcOption2.handlePacket(this, 0, 0);
            }
            if (objectOption1) {
                Engine.packets.objectOption1.handlePacket(this, 0, 0);
            }
            if (objectOption2) {
                Engine.packets.objectOption2.handlePacket(this, 0, 0);
            }
            if (attackingPlayer) {
                Engine.playerCombat.attackPlayer(this);
            }
            if (attackingNPC) {
                Engine.playerNPCCombat.attackNPC(this);
            }
            if (jumpUpdateReq) {
                if (jumpDelay >= 1) {
                    runEmote = walkEmote = NewEmote;
                    updateReq = appearanceUpdateReq = true;
                }
                if (jumpDelay <= 0) {

                    playerWeapon.setWeapon();

                    jumpDelay = 0;
                    updateReq = appearanceUpdateReq = true;
                }
                jumpUpdateReq = false;
            }
            if (skulledUpdateReq && AtDuel() == false) {
                if (skulledDelay >= 1) {
                    pkIcon = 0;
                    updateReq = appearanceUpdateReq = true;
                }
                if (skulledDelay <= 0) {
                    pkIcon = -1;
                    skulledDelay = 0;
                    updateReq = appearanceUpdateReq = true;
                }
                skulledUpdateReq = false;
            }
            if (runEnergyUpdateReq) {
                frames.setEnergy(this);
                runEnergyUpdateReq = false;
            }
            if (specialAmountUpdateReq) {
                frames.setConfig(this, 300, specialAmount * 10);
                specialAmountUpdateReq = false;
            }
            if (afterDeathUpdateReq) {
                for (int i = 0; i < skillLvl.length; i++) {
                    skillLvl[i] = getLevelForXP(i);
                    frames.setSkillLvl(this, i);
                }
                Prayer pr = new Prayer();
                pr.resetPrayers(this);
                Engine.poison.startPoison(this, 0);
                freezeDelay = 0;
                skulledDelay = 0;
                specialAmount = 100;
                runEnergy = 100;
                deathDelay = 7;
                specialAmountUpdateReq = true;
                runEnergyUpdateReq = true;
                skulledUpdateReq = true;
                isDead = false;
                afterDeathUpdateReq = false;
            }
            if (statRestoreDelay <= 0) {
                for (int i1 = 0; i1 < skillLvl.length; i1++) {
                    int xpLvl = getLevelForXP(i1);

                    if (skillLvl[i1] < xpLvl) {
                        skillLvl[i1]++;
                        frames.setSkillLvl(this, i1);
                    } else if (skillLvl[i1] > xpLvl) {
                        skillLvl[i1]--;
                        frames.setSkillLvl(this, i1);
                    }
                }
                statRestoreDelay = 75;
            }
            prayerDrain -= drainRate;
            if (prayerDrain <= 0 && skillLvl[5] > 0) {
                skillLvl[5]--;
                frames.setSkillLvl(this, 5);
                if (skillLvl[5] <= 0) {
                    Prayer pr = new Prayer();

                    pr.resetPrayers(this);
                    frames.sendMessage(this, "You have run out of prayer points.");
                }
                prayerDrain = 100;
            }
            if (teleDelay == 0) {
                teleDelay = -1;
                setCoords(teleX, teleY, heightLevel);
                requestAnim(teleFinishAnim, 0);
                requestGFX(teleFinishGFX, teleFinishGFXHeight);
                teleX = teleY = -1;
            }
            if (clickDelay == 0) {
                clickDelay = -1;
            }
            checkForAttackOption();
        } catch (Exception e) {
        }
    }

    public void friendsLoggedIn() {
        for (Long friend : friends) {
            frames.sendFriend(this, friend, getWorld(friend));
        }
        long[] array = new long[ignores.size()];
        int i = 0;
        for (Long ignore : ignores) {
            if (ignore != null) {
                array[i++] = ignore;
            }
        }
        frames.sendIgnores(this, array);
        long me = Misc.stringToLong(username);
        for (Player p : Engine.players) {
            if (p == null) {
                continue;
            }
            if (p.friends.contains(me)) {
                p.frames.sendFriend(p, me, 66);
            }
        }
    }

    public int getWorld(long friend) {
        for (Player p : Engine.players) {
            if (p != null && p.online) {
                if (Misc.stringToLong(p.username) == friend) {
                    return 66;
                }
            }
        }
        return 0;
    }

    public void appendWilderness() {
        if (Engine.wildernessArea(absX, absY)) {
            frames.setOverlay(this, 380);
            frames.setString(this, "<col=ff4400>Lvl: " + Engine.playerCombat.wildLvl(this), 380, 1);
            //wildernessQuestTab();
        }
    }

    /**
     * Items droped on death if no protection
     */
    public void dropAllItems(Player p) {
        Player p2 = Engine.players[p.attackPlayer];

        if (p2 == null) {
            return;
        }
        if (!Engine.safeArea(absX, absY)) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] > 0) {
                    if (Engine.items.isUntradable(items[i])) {
                        Engine.items.createGroundItem(items[i], itemsN[i], absX,
                                absY, heightLevel, username);
                    } else {
                        Engine.items.createGroundItem(items[i], itemsN[i], absX,
                                absY, heightLevel, p2.username);
                    }
                    items[i] = -1;
                    itemsN[i] = 0;
                }
            }
            for (int i = 0; i < equipment.length; i++) {
                if (equipment[i] > 0) {
                    if (Engine.items.isUntradable(equipment[i])) {
                        Engine.items.createGroundItem(equipment[i], equipmentN[i],
                                absX, absY, heightLevel, username);
                    } else {
                        Engine.items.createGroundItem(equipment[i], equipmentN[i],
                                absX, absY, heightLevel, p2.username);
                    }
                    equipment[i] = -1;
                    equipmentN[i] = 0;
                }
            }
            /**
             * Begin Ground Items
             * This is just basic extra items to drop
             */
            Engine.items.createGroundItem(randomPVPdrop(), 1, absX, absY, heightLevel,
                    p2.username); // coins items
            Engine.items.createGroundItem(randomFoodDrop(), 2 + Misc.random(4), absX, absY, heightLevel,
                    p2.username); // food items
            Engine.items.createGroundItem(randomPotDrop(), 1 + Misc.random(2), absX, absY, heightLevel,
                    p2.username); // potions

            p2.pkp += 1;
            p2.pkr += 1;
            frames.setItems(this, 149, 0, 93, items, itemsN);
            frames.setItems(this, 387, 28, 94, equipment, equipmentN);
            playerWeapon.setWeapon();
            calculateEquipmentBonus();
        }
        deathMessage();
        Engine.items.createGroundItem(526, 1, absX, absY, heightLevel,
                p2.username); // drop bones
    }

    public void deathCapeDrop(Player p) {
        Player p2 = Engine.players[p.attackPlayer];

        if (p2 == null) {
            return;
        }
        /**
         * Begin Ground Items
         * All the items droped (not alot)
         */
        Engine.items.createGroundItem(526, 1, absX, absY, heightLevel,
                p2.username); // drop bones
        equipment[1] = -1;
        equipmentN[1] = 0;
        frames.setItems(this, 387, 28, 94, equipment, equipmentN);
        if (Engine.dropArea(absX, absY)) {
            p2.pkp += 1;
            p2.pkr += 1;
        }
        deathMessage();
    }

    /**
     * Get xp based on your level.
     *
     * @param skillId The skill level to get the level based off XP for.
     * @return Returns the level based on the amount of XP the specified skill has.
     */
    public int getLevelForXP(int skillId) {
        int exp = skillXP[skillId];
        int points = 0;
        int output = 0;

        for (int lvl = 1; lvl < 100; lvl++) {
            points += Math.floor(
                    (double) lvl + 300.0 * Math.pow(2.0, (double) lvl / 7.0));
            output = (int) Math.floor(points / 4);
            if ((output - 1) >= exp) {
                return lvl;
            }
        }
        return 99;
    }

    /**
     * Death data.
     */
    public static int FoodDrop[] = {385, 391, 315, 379};

    public static int randomFoodDrop() {
        return FoodDrop[(int) (Math.random() * FoodDrop.length)];
    }

    public static int PotDrop[] = {113, 119, 124, 133, 135, 139, 149, 151, 159, 166, 165, 169, 173, 178, 2428, 2434, 2436, 2443, 2444, 3040, 3046, 3012};

    public static int randomPotDrop() {
        return PotDrop[(int) (Math.random() * PotDrop.length)];
    }

    public static int PVPdrop[] = {4214, 4224, 4153, 12842, 1037, 11730, 11732, 1540, 4151, 1149, 2368, 2366, 1305, 1377, 3140, 3204, 4087, 4585, 4587, 5698, 1432, 1031, 1189, 636, 2898, 656, 638, 646, 640, 648, 658, 660, 642, 650, 644, 652, 662, 2896, 654, 626, 6585, 4353, 10501, 892, 882, 861, 859, 6818, 9185, 9183, 9181, 9179, 9177, 9175, 6243, 6245};

    public static int randomPVPdrop() {
        return PVPdrop[(int) (Math.random() * PVPdrop.length)];
    }

    public void deathMessage() {
        /**
         * Sends to player who was killed
         */
        Player p = this;
        Player p2 = Engine.players[p.attackPlayer];

        if (p2 == null) {
            return;
        }

        defeatMessage = Misc.random(8);
        if (defeatMessage == 0) {
            frames.sendMessage(this,
                    "You didnt have a chance.");
        } else if (defeatMessage == 1) {
            frames.sendMessage(this,
                    "Your going to take such a brutal beating?");
        } else if (defeatMessage == 2) {
            frames.sendMessage(this, "" + username + ", you failed.");
        } else if (defeatMessage == 3) {
            frames.sendMessage(this, "Say good bye to all your items.");
        } else if (defeatMessage == 4) {
            frames.sendMessage(this,
                    username + ", you tried your best.");
        } else if (defeatMessage == 5) {
            frames.sendMessage(this, "Oh dear you are dead.");
        } else if (defeatMessage == 6) {
            frames.sendMessage(this, "You got owned.");
        } else if (defeatMessage == 7) {
            frames.sendMessage(this,
                    "You died.");
        } else if (defeatMessage == 8) {
            frames.sendMessage(this,
                    "Ouch, you took the pain in that fight.");
        }

        /**
         * Sends to victor of the fight
         */
        deathMessage = Misc.random(8);
        if (deathMessage == 0) {
            p.frames.sendMessage(p2,
                    "With a crushing blow, you defeat " + username + ".");
        } else if (deathMessage == 1) {
            p.frames.sendMessage(p2,
                    "It's a humiliating defeat for " + username + ".");
        } else if (deathMessage == 2) {
            p.frames.sendMessage(p2,
                    username + " didn't stand a chance against you.");
        } else if (deathMessage == 3) {
            p.frames.sendMessage(p2, "You have defeated " + username + ".");
        } else if (deathMessage == 4) {
            p.frames.sendMessage(p2,
                    username + " regrets the day they met you in combat.");
        } else if (deathMessage == 5) {
            p.frames.sendMessage(p2, "It's all over for " + username + ".");
        } else if (deathMessage == 6) {
            p.frames.sendMessage(p2, username + " falls before your might.");
        } else if (deathMessage == 7) {
            p.frames.sendMessage(p2,
                    "Can anyone defeat you? Certainly not " + username + ".");
        } else if (deathMessage == 8) {
            p.frames.sendMessage(p2,
                    "You were clearly a better fighter than " + username + ".");
        }
    }

    public void deadMessage() {
        frames.sendMessage(this, "Oh dear you are dead!");
    }

    public void bountyDeath() {
        Player p2 = Engine.players[DuelPartner];
        if (inBounty) {
            int i = heightLevel;
            if (p2 != null) {
                if (p2.bhTarget != playerId) {
                    p2.penalty = 360;
                    p2.frames.setInterfaceConfig(p2, 653, 9, false);
                } else {
                    if (i == 0) {
                        p2.bhTarget = Engine.BountyHunter.getTargetLow(this);
                    } else if (i == 4) {
                        p2.bhTarget = Engine.BountyHunter.getTargetMid(this);
                    } else {
                        p2.bhTarget = Engine.BountyHunter.getTargetHigh(this);
                    }
                    Player p3 = Engine.players[p2.bhTarget];
                    if (p3 != null) {
                        p2.frames.setString(p2, "" + p3.username, 653, 8);
                    } else {
                        p2.frames.setString(p2, "None", 653, 8);
                    }
                }
            }
            //frames.removeOverlay(this);
            pkIcon = -1;
            appearanceUpdateReq = updateReq = true;
            if (i == 0) {
                Engine.BountyHunter.removeLow(this);
            } else if (i == 4) {
                Engine.BountyHunter.removeMid(this);
            } else {
                Engine.BountyHunter.removeHigh(this);
            }
            setCoords(3172, 3675, 0);
        } else {
            setCoords(3661, 3497, 0);
        }
    }

    public void applyDead() {
        requestAnim(7197, 0);//death emote
        if (deathDelay >= 7) {
            if (faceToReq != 65535) {
                requestFaceTo(65535);
            }
        }

        if (prayerIcon == 3 && deathDelay == 1) {//retribution
            for (Player ps : Engine.players) {
                if (ps != null) {
                    if (ps.absX < absX + 5 && ps.absY < absY + 5 && ps.absX > absX - 5 && ps.absY > absY - 5 && ps.playerId != playerId) {
                        ps.requestGFX(437, 0);
                        ps.appendHit(5 + Misc.random(15), 0);
                    }
                }
            }
        }

        if (deathDelay <= 0) {


            //frames.sendMessage(this, "<img=2>Good job, you just died, and your alive again?");
            appearanceUpdateReq = true;
            updateReq = true;
            afterDeathUpdateReq = true;
            Player p2 = Engine.players[DuelPartner];
            followingPlayer = false;
            followPlayer = 0;
            Overlay = 0;

            if (Engine.pvpArea(absX, absY) && !inBounty) {
                if (equipment[1] == 4353) {
                    deathCapeDrop(this);
                    frames.sendMessage(this, "Your death cape disinagrates");
                } else {
                    dropAllItems(this);
                }
                if (!Engine.safeArea(absX, absY)) {
                    pkr -= 1;
                }
                setCoords(3087 + Misc.random(5), 3503 + Misc.random(5), 0);
            } else if (inBounty) {
                bountyDeath();
            } else if (AtCastleWars()) {
                deadMessage();
                if (CWTeam == 0) {
                    setCoords(2425 + Misc.random(4), 3075 + Misc.random(4), 1);
                } else {
                    setCoords(2370 + Misc.random(4), 3128 + Misc.random(4), 1);
                }
                if (equipment[3] == 4037 && CWTeam == 1) {
                    Engine.SaradominFlag = false;
                    equipment[3] = -1;
                    equipmentN[3] = 0;
                    appearanceUpdateReq = true;
                    updateReq = true;
                    frames.setItems(this, 387, 28, 94, equipment, equipmentN);
                }
                if (equipment[3] == 4039 && CWTeam == 0) {
                    Engine.ZamorakFlag = false;
                    equipment[3] = -1;
                    equipmentN[3] = 0;
                    appearanceUpdateReq = true;
                    updateReq = true;
                    frames.setItems(this, 387, 28, 94, equipment, equipmentN);
                }
            } else if (AtPits()) {
                deadMessage();
                GameStarted = false;
                setCoords(2395 + Misc.random(8), 5170 + Misc.random(4), 0);
                frames.sendMessage(this, "You lost.");
            } else if (p2 != null && DuelPartner != 0) {
                deadMessage();
                if (p2.AtDuel()) {
                    for (Player pz : Engine.players) {
                        frames.sendMessage(pz, "<col=FF00FF>" + p2.username + " Level: " + p2.combatLevel + " defeated " + username + " Level: " + combatLevel + ".");
                    }

                    p2.skillLvl[3] = p2.getLevelForXP(3);
                    p2.frames.setSkillLvl(p2, 3);

                    skulledDelay = 0;
                    setCoords(DuelX, DuelY, 0);
                    p2.pkp += 1;
                    p2.setCoords(p2.DuelX, p2.DuelY, 0);
                    frames.showInterface(p2, 633);
                    frames.setString(p2, combatLevel + "", 633, 21);
                    frames.setString(p2, username, 633, 22);
                    frames.sendMessage(p2, "You have defeated " + username + ".");
                    ResetDuel();
                    p2.ResetDuel();
                }
            } else {
                setCoords(Config.homeX + Misc.random(3), Config.homeY + Misc.random(3), 0);
            }

        }
    }

    /**
     * Calculates equipment bonus.
     */
    public void calculateEquipmentBonus() {
        for (int i = 0; i < equipmentBonus.length; i++) {
            equipmentBonus[i] = 0;
        }
        for (int i = 0; i < equipment.length; i++) {
            if (equipment[i] > -1) {
                for (int j = 0; j < Engine.items.maxListedItems; j++) {
                    if (Engine.items.itemLists[j] != null) {
                        if (Engine.items.itemLists[j].itemId == equipment[i]) {
                            for (int k = 0; k < equipmentBonus.length; k++) {
                                equipmentBonus[k] += Engine.items.itemLists[j].bonuses[k];
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Writes the equipment bonus.
     */
    public void setEquipmentBonus() {
        String[] bonusNames = Misc.bonusNames;
        int id = 35;

        for (int i = 0; i < equipmentBonus.length; i++) {
            frames.setString(this,
                    bonusNames[i] + ": " + (equipmentBonus[i] >= 0 ? "+" : "")
                            + equipmentBonus[i],
                    667,
                    id++);
            if (id == 45) {
                id = 47;
            }
        }
        frames.setString(this, "Summoning: +0", 667, 45);
    }

    public void openBank() {
        Cooking = false;
        Fishing = false;
        frames.setConfig2(this, 563, 4194304); //???
        frames.setConfig2(this, 1248, -2013265920);
        frames.setConfig(this, 115, withdrawNote ? 1 : 0);
        frames.setConfig(this, 305, insertMode ? 1 : 0);
        Engine.playerBank.sendTabConfig(this);
        frames.showInterface(this, 762);
        frames.setInventory(this, 763);
        frames.setBankOptions(this);
        frames.setItems(this, -1, 64207, 95, bankItems, bankItemsN);
        frames.setItems(this, -1, 64209, 93, items, itemsN);
        //frames.hideTabs(this); code to hide inventory & tabs
        //frames.setInterfaceConfig(this, 762, 18, true); //Hide some buttons
        //frames.setInterfaceConfig(this, 762, 19, true); //Remove it
        //frames.setInterfaceConfig(this, 762, 23, true); //If ya want
        viewingBankTab = 10;
        frames.setString(this, "-iCEYY!SCAPe- Bank.", 762, 24);
        frames.setString(this, "" + Engine.playerBank.getFreeBankSlot(this), 762, 97);
        frames.setString(this, "" + Engine.playerBank.SIZE, 762, 98);
    }

    public void openTrade() {

        frames.setConfig2(this, 563, 4194304);
        frames.setConfig2(this, 1248, -2013265920);
        frames.showInterface(this, 278);
        frames.setBankOptions(this);

    }

    public void openLog() {
        frames.showInterface(this, 642);
    }

    public void openHis() {
        frames.showInterface(this, 643);
    }

    public void openGe() {
        frames.showInterface(this, 105);
    }

    public void openSets() {
        frames.showInterface(this, 645);
    }

    public void updateHP(int chHp, boolean heal) {
        if (heal) {
            skillLvl[3] += chHp;
            if (skillLvl[3] > getLevelForXP(3)) {
                skillLvl[3] = getLevelForXP(3);
            }
        } else if (!heal) {
            skillLvl[3] -= chHp;
            if (skillLvl[3] <= 0) {
                skillLvl[3] = 0;
                isDead = true;
                Engine.playerMovement.resetWalkingQueue(this);
            }
        }
        frames.setSkillLvl(this, 3);
    }

    public void updatePRAY(int chPR, boolean pray) {
        if (pray) {
            skillLvl[5] += chPR;
            if (skillLvl[5] > getLevelForXP(5)) {
                skillLvl[5] = getLevelForXP(5);
            }
        }
        frames.setSkillLvl(this, 5);
    }

    /**
     * Add experiance to a specific skill.
     *
     * @param amount The amoubt of XP you want to add.
     * @param skill  The skill id you want to add it to.
     */
    public void addSkillXP(double amount, int skill) {
        if (skillXP[skill] > 200000000) {
            skillXP[skill] = 200000000;
        }

        if (skill > skillXP.length || skill < 0) {
            return;
        }
        int oldLevel = getLevelForXP(skill);
        if (skillXP[skill] < 200000000 && xpLock == 0) {
            if (this.donator == 1) {
                skillXP[skill] += amount;
            } else {
                skillXP[skill] += (amount / 2);
            }
        }
        int newLevel = getLevelForXP(skill);
        if (oldLevel < newLevel && xpLock == 0) {
            if (skill == 3) {
                updateHP(newLevel - oldLevel, true);
            } else {
                skillLvl[skill] += (newLevel - oldLevel);
            }
            frames.setSkillLvl(this, skill);

            appearanceUpdateReq = true;
            updateReq = true;
        }
        frames.setSkillLvl(this, skill);
    }

    /**
     * Teleports a player.
     *
     * @param x          The x coordinate to teleport to.
     * @param y          The y coordinate to teleport to.
     * @param height     The height level to teleport to.
     * @param delay      The delay before the teleport is done.
     * @param distance   The offset you can teleport to, such as 1 for a max offset of x +/- 1 and y +/- 1.
     * @param emoteStart The emote to do right away.
     * @param emoteEnd   The emote to do after teleporting.
     * @param gfxStart   The graphic to do right away.
     * @param gfxStartH  The height level to create the start graphic at.
     * @param gfxEnd     The graphic to do after the teleport is done.
     * @param gfxEndH    The finish graphic height.
     */
    public void teleportTo(int x, int y, int height, int delay, int distance, int emoteStart, int emoteEnd, int gfxStart,
                           int gfxStartH, int gfxEnd, int gfxEndH) {
        if (jailed == 1) {
            frames.sendMessage(this, "You are jailed and cannot teleport");
        } else {
            if (!AtCastleWars()) {
                OverTimer = 2;
            }
            wc.resetWoodcutting();
            mi.resetMining();
            ResetSkillSuff();
            itemPickup = false;
            playerOption1 = false;
            playerOption2 = false;
            playerOption3 = false;
            playerOption4 = false;
            npcOption1 = false;
            npcOption3 = false;
            npcOption2 = false;
            objectOption1 = false;
            objectOption2 = false;
            attackingPlayer = false;
            clickDelay = delay + 2;
            teleDelay = delay;
            if (distance > 0) {
                int xType = Misc.random(1);
                int yType = Misc.random(1);
                int xOffset = Misc.random(distance);
                int yOffset = Misc.random(distance);

                if (xType == 1) {
                    x += -xOffset;
                } else {
                    x += xOffset;
                }
                if (yType == 1) {
                    y += -yOffset;
                } else {
                    y += yOffset;
                }
            }
            teleX = x;
            teleY = y;
            heightLevel = height;
            Engine.playerMovement.resetWalkingQueue(this);
            requestAnim(emoteStart, 0);
            requestGFX(gfxStart, gfxStartH);
            teleFinishGFX = gfxEnd;
            teleFinishGFXHeight = gfxEndH;
            teleFinishAnim = emoteEnd;
        }
    }

    /**
     * Set the player's coordinates.
     *
     * @param x The x coordinate to teleport to.
     * @param y The y coordinate to teleport to.
     */
    public void setCoords(int x, int y, int height) {
        if (jailed == 1) {
            frames.sendMessage(this, "You are jailed and cannot teleport");
        } else {
            if (!AtCastleWars()) {
                OverTimer = 2;
            }
            wc.resetWoodcutting();
            mi.resetMining();
            ResetSkillSuff();
            teleportToX = x;
            teleportToY = y;
            heightLevel = height;
            didTeleport = true;

        }
    }

    public void checkForAttackOption() {
    }

    /**
     * Req an animation for this player.
     *
     * @param animId The amination to perform.
     * @param animD  The delay before doing the animation.
     */
    public void requestAnim(int animId, int animD) {
        animReq = animId;
        animDelay = animD;
        animUpdateReq = true;
        updateReq = true;
    }

    /**
     * Req an graphic for this player.
     *
     * @param gfxId The graphic to perform.
     * @param gfxD  The delay or height or the gfx depending on the value.
     */
    public void requestGFX(int gfxId, int gfxD) {
        if (gfxD >= 100) {
            gfxD += 6553500;
        }
        gfxReq = gfxId;
        gfxDelay = gfxD;
        gfxUpdateReq = true;
        updateReq = true;
    }

    /**
     * Req this player faces NPC or player.
     *
     * @param faceId The target to face.
     */
    public void requestFaceTo(int faceId) {
        faceToReq = faceId;
        faceToUpdateReq = true;
        updateReq = true;
    }

    /**
     * Gives experience to a player.
     *
     * @param amount  The amount which will gives to the player
     * @param skillId the skill Id of the player which the exp will go to.
     */
    public void appendExperience(int amount, int skillId) {
        int oldLvl = getLevelForXP(skillId);
        if (skillXP[skillId] > 200000000 || amount >= 2147000000) {
            skillXP[skillId] = 13100000;
            return;
        }
        skillXP[skillId] += amount;
        int newLvl = getLevelForXP(skillId);
        ResetSkillSuff();
        if (oldLvl < newLvl) {
            skillLvl[skillId] += (newLvl - oldLvl);
            switch (skillId) {

                case 0:
                    frames.sendMessage(this, "You've just advanced an Attack level! You have reached level " + skillLvl[0] + ".");
                    frames.setString(this, "Congratulations, you have just advanced an Attack level!", 740, 0);
                    frames.setString(this, "You have now reached level " + skillLvl[0] + "!", 740, 1);
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    frames.setConfig(this, 1179, 0);
                    frames.showChatboxInterface(this, 740);
                    frames.sendMessage(this, "Congratulations, You have just advanced to a new Attack level!");
                    break;

                case 1:
                    frames.sendMessage(this, "You've just advanced a Defence level! You have reached level " + skillLvl[1] + ".");
                    frames.setString(this, "Congratulations, you have just advanced an Defence level!", 740, 0);
                    frames.setString(this, "You have now reached level " + skillLvl[1] + "!", 740, 1);
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    frames.setConfig(this, 1179, 1);
                    frames.showChatboxInterface(this, 740);
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Defence level!");
                    break;

                case 2:
                    frames.sendMessage(this, "You've just advanced a Strength level! You have reached level " + skillLvl[2] + ".");
                    frames.setString(this, "Congratulations, you have just advanced a Strength level!", 740, 0);
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    frames.setString(this, "You have now reached level " + skillLvl[2] + "!", 740, 1);
                    frames.setConfig(this, 1179, 2);
                    frames.showChatboxInterface(this, 740);
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Strength level!");
                    break;

                case 3:
                    frames.sendMessage(this, "You've just advanced a HitPoints level! You have reached level " + skillLvl[3] + ".");
                    frames.setString(this, "Congratulations, you have just advanced a HitPoints level!", 740, 0);
                    frames.setString(this, "You have now reached level " + skillLvl[3] + "!", 740, 1);
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    frames.setConfig(this, 1179, 3);
                    frames.showChatboxInterface(this, 740);
                    frames.sendMessage(this, "Congratulations, You have just advanced a new hitpoints level!");
                    break;

                case 4:
                    frames.sendMessage(this, "You've just advanced an Ranged level! You have reached level " + skillLvl[4] + ".");
                    frames.setString(this, "Congratulations, you have just advanced a Ranged level!", 740, 0);
                    frames.setString(this, "You have now reached level " + skillLvl[4] + "!", 740, 1);
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    frames.setConfig(this, 1179, 4);
                    frames.showChatboxInterface(this, 740);
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Ranged level!");
                    break;

                case 5:
                    frames.sendMessage(this, "You've just advanced an Prayer level! You have reached level " + skillLvl[5] + ".");
                    frames.setString(this, "Congratulations, you have just advanced a Prayer level!", 740, 0);
                    frames.setString(this, "You have now reached level " + skillLvl[5] + "!", 740, 1);
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    frames.setConfig(this, 1179, 5);
                    frames.showChatboxInterface(this, 740);
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Prayer level!");
                    break;

                case 6:
                    frames.sendMessage(this, "You've just advanced a Magic level! You have reached level " + skillLvl[6] + ".");
                    frames.setString(this, "Congratulations, you have just advanced a Magic level!", 740, 0);
                    frames.setString(this, "You have now reached level " + skillLvl[6] + "!", 740, 1);
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    frames.setConfig(this, 1179, 6);
                    frames.showChatboxInterface(this, 740);
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Magic level!");
                    break;

                case 7:
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Cooking level!");
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    break;

                case 8:
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Woodcutting level!");
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    break;

                case 9:
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Fletching level!");
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    break;

                case 10:
                    frames.sendMessage(this, "You've just advanced a Fishing level! You have reached level " + skillLvl[10] + ".");
                    frames.setString(this, "Congratulations, you have just advanced a Fishing level!", 740, 0);
                    frames.setString(this, "You have now reached level " + skillLvl[10] + "!", 740, 1);
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    frames.setConfig(this, 1179, 10);
                    frames.showChatboxInterface(this, 740);
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Magic level!");
                    break;

                case 16:
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Agility level!");
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    break;

                case 17:
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Thieving level!");
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    break;

                case 18:
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Slayer level!");
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    break;

                case 19:
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Farming level!");
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    break;

                case 20:
                    frames.sendMessage(this, "Congratulations, You have just advanced a new Runecrafting level!");
                    // Engine.playerItems.addItem(this, 995, 100 + Misc.random(500));
                    break;

                default:
                    frames.sendMessage(this, "This level is unknown, Please report this to an administrator.");
                    break;
            }
            if (skillLvl[skillId] > 99) {
                skillLvl[skillId] = 99;
                skillXP[skillId] = 13100000;
            }
            requestGFX(1635, 0);
            appearanceUpdateReq = true;
            updateReq = true;
        }
        frames.setSkillLvl(this, skillId);
    }

    /**
     * normalQuestTab
     * The quest tab to show when you are not in the wilderness
     */
    public void normalQuestTab() {
        frames.setString(this, "<col=9FF731>-iCEYY!SCAPe- News & Updates", 274, 5);
        frames.setString(this, "Iceyyscape Quest Tab:", 274, 6);
        frames.setString(this, "", 274, 8);
        frames.setString(this, "", 274, 9);
        frames.setString(this, "", 274, 10);
        frames.setString(this, "", 274, 11);
        if (rights >= 1) {
            frames.setString(this, "<col=Dd973b5><shad=0202>--------!Staff Options!-------", 274, 9);
            frames.setString(this, "<col=53D7BF>>Go to staff zone.", 274, 10);
        }
        frames.setString(this, "<col=Dd973b5><shad=0202>----------!Teleports!---------", 274, 12);
        frames.setString(this, "<col=53D7BF>>Go to Home.", 274, 13);
        frames.setString(this, "<col=53D7BF>>Go to Party Room.", 274, 14);
        frames.setString(this, "<col=53D7BF>>Go to Shops.", 274, 15);
        frames.setString(this, "<col=53D7BF>>Go to Training.", 274, 16);
        frames.setString(this, "<col=53D7BF>>Go to Donators Training.", 274, 17);
        frames.setString(this, "<col=53D7BF>>Go to King Black Dragon.", 274, 18);
        frames.setString(this, "<col=53D7BF>>Go to Safety Dungeon.", 274, 19);
        frames.setString(this, "<col=53D7BF>>Go to Barrows.", 274, 20);
        frames.setString(this, "<col=53D7BF>>Go to God Wars.", 274, 21);
        frames.setString(this, "<col=53D7BF>>Go to Castle Wars.", 274, 22);
        frames.setString(this, "<col=53D7BF>>Go to Fight Pits.", 274, 23);
        frames.setString(this, "<col=53D7BF>>Go to Pvp.", 274, 24);
        frames.setString(this, "----!+Quick Shop+!----", 274, 25);
        frames.setString(this, "<col=66FF33><shad=0202>Click and Pick", 274, 26);
        for (int i = 0; i < 124; i++) {
            frames.setString(this, " ", 274, 27 + i);
        }

        if (DragonSlayer == 0) {
            frames.setString(this, "Dragon Slayer", 274, 7);
        } else if (DragonSlayer > 0 && DragonSlayer < 5) {
            frames.setString(this, "<col=FFFF00>Dragon Slayer", 274, 7);
        } else if (DragonSlayer == 5) {
            frames.setString(this, "<col=66FF33>Dragon Slayer", 274, 7);
        }
    }

    /**
     * wildernessQuestTab
     * The items to show in the quest tab in the wilderness
     */
    public void wildernessQuestTab() {
        if (Engine.safeArea(absX, absY)) {
            frames.setString(this, "<col=Dd973b5>Wild Lvl:" + Engine.playerCombat.wildLvl(this) + " <col=FFFF>Items:<col=9FF731>Safe!", 274, 5);
        } else {
            frames.setString(this, "<col=Dd973b5>Wild Lvl:" + Engine.playerCombat.wildLvl(this) + " <col=FFFF>Items:<col=440c>Droped!", 274, 5);
        }
        frames.setString(this, "Pk Points(PKP): " + pkp + "", 274, 6);
        frames.setString(this, "<col=9FF731>Go Home - <shad=0202>Free", 274, 7);
        frames.setString(this, "<col=9FF731>Death Cape - <shad=0202>35PKP", 274, 8);
        frames.setString(this, "<col=9FF731>Granite Maul - <shad=0202>25PKP", 274, 9);
        frames.setString(this, "<col=9FF731>Full Granite - <shad=0202>40PKP", 274, 10);
        frames.setString(this, "<col=9FF731>Dragon Dagger(p++) - <shad=0202>30PKP", 274, 11);
        frames.setString(this, "<col=9FF731>10x Sharks - <shad=0202>10PKP", 274, 12);
        frames.setString(this, "<col=9FF731>100x Sharks(noted) - <shad=0202>15PKP", 274, 13);
        frames.setString(this, "<col=9FF731>Potion Pack - <shad=0202>10PKP", 274, 14);
        frames.setString(this, "<col=9FF731>Super Potion Pack - <shad=0202>15PKP", 274, 15);
        frames.setString(this, "<col=9FF731>Restore Special - <shad=0202>10PKP", 274, 16);
        frames.setString(this, "<col=9FF731>De-Poison - <shad=0202>5PKP", 274, 17);
        for (int i = 0; i < 124; i++) {
            frames.setString(this, " ", 274, 17 + i);
        }
    }

    /**
     * Append damage.
     */
    public void appendHit(int damage, int posion) {
        if (damage > skillLvl[3]) {
            damage = skillLvl[3];
        }
        updateHP(damage, false);
        if (!hit1UpdateReq) {
            hitDiff1 = damage;
            posionHit1 = posion;
            hit1UpdateReq = true;
        } else {
            hitDiff2 = damage;
            posionHit2 = posion;
            hit2UpdateReq = true;
        }
        updateReq = true;
    }

    /**
     * Force chat text.
     */
    public void requestForceChat(String s) {
        forceChat = s;
        forceChatUpdateReq = true;
        updateReq = true;
    }

    /**
     * Prepare player for removal.
     */
    public void destruct() {
        stream = null;
        try {
            socket.input.close();
            socket.output.close();
            socket.socket.close();
        } catch (Exception e) {

        }
        socket.input = null;
        socket.output = null;
        socket.socket = null;
        socket = null;
        absX = absY = mapRegionX = mapRegionY = -1;
    }

    public boolean canAttackPlayer(Player p) {
        Player p2 = Engine.players[p.attackPlayer];

        if (p == null || p.isDead) {
            return false;
        } else if (p.attackPlayer < 1 || p.attackPlayer >= Engine.players.length
                || Engine.players[p.attackPlayer] == null) {
            return false;
        }
        if (!Engine.wildernessArea(p.absX, p.absY)) {
            return false;
        }
        if (!Engine.wildernessArea(p2.absX, p2.absY)) {
            return false;
        }
        if (absX >= 3093 && absY >= 3920 && absX <= 3120 && absY <= 3950) {
            return false;
        }
        return true;

    }

    public void stopMovement(Player p) {
        if (p == null) {
            return;
        }
        if (p.teleportToX == -1 && p.teleportToY == -1) {
            p.teleportToX = p.absX;
            p.teleportToY = p.absY;
        }
        p.walkingQueueX[0] = 0;
        p.walkingQueueY[0] = 0;
        p.walkingQueue[0] = 0;
    }

    public void objects() {
//frames.createObject(this, objID, HEIGHT, X, Y, FACE, TYPE);
        Engine.oL.loadFile("data/objects.cfg");
        for (int oc = 0; oc < Engine.oL.objectCount; oc++) {
            int oID = Engine.oL.objectID[oc];
            int oX = Engine.oL.objectX[oc];
            int oY = Engine.oL.objectY[oc];
            int oO = Engine.oL.objectO[oc];
            int oF = Engine.oL.objectF[oc];

            frames.createGlobalObject(oID, this.heightLevel, oX, oY, oF, oO);
        }
        /*frames.createGlobalObject(2782, 0, 2973, 3368, 0, 10);
        frames.createGlobalObject(2728, 0, 2813, 3438, 0, 10);
        frames.createGlobalObject(2096, 0, 2941, 3291, 0, 10);
        frames.createGlobalObject(2097, 0, 2940, 3289, 0, 10);
        frames.createGlobalObject(2102, 0, 2943, 3287, 0, 10);
        frames.createGlobalObject(2104, 0, 2937, 3279, 0, 10);
        frames.createGlobalObject(2106, 0, 2943, 3277, 0, 10);
        frames.createGlobalObject(2092, 0, 2942, 3282, 0, 10);
        frames.createGlobalObject(2092, 0, 2937, 3280, 0, 10);
        frames.createGlobalObject(2106, 0, 2940, 3276, 0, 10);
        frames.createGlobalObject(2105, 0, 2939, 3288, 0, 10);


        frames.createGlobalObject(114, 0, 2813, 3438, 1, 10); //cooking range
        frames.createGlobalObject(2213, 0, 3117, 9858, 0, 10); //slayer bank
        frames.createGlobalObject(2213, 0, 3119, 9858, 0, 10); //slayer bank
        frames.createGlobalObject(3192, 0, 2948, 3370, 0, 10); //high scores
        frames.createGlobalObject(2640, 0, 2944, 3373, 0, 10); //alter
        frames.createGlobalObject(6282, 0, 2953, 3378, 3, 10); //PK PORTAL (pvp)
        frames.createGlobalObject(6552, 0, 2952, 3372, 3, 10); //ancient alter
        frames.createGlobalObject(2213, 0, 3018, 9581, 3, 10); //right training bank
        frames.createGlobalObject(2213, 0, 3018, 9578, 3, 10); //left training bank*/


    }

    public void reqWalkQueue(int x, int y) {
        int firstX = x - (mapRegionX - 6) * 8;
        int firstY = y - (mapRegionY - 6) * 8;

        Engine.playerMovement.resetWalkingQueue(this);
        Engine.playerMovement.addToWalkingQueue(this, firstX, firstY);
    }

    public int bankItemCount() {
        int count = 0;

        for (int i = 0; i < bankItems.length; i++) {
            if (bankItems[i] > 0 && bankItemsN[i] > 0) {
                count++;
            } else {
                break;
            }
        }
        return count;

    }
}
