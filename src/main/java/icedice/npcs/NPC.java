package icedice.npcs;

import icedice.Config;
import icedice.Engine;
import icedice.io.Frames;
import icedice.util.Stream;
import icedice.players.Player;

import java.io.*;
import java.util.*;

import icedice.util.Misc;

public class NPC {
    public Stream update = new Stream(500, 5000);
    public int followPlayer = 0;
    public int gfxHeight;
    public int RanText = 0;

    public void appendPlayerFollowing(NPC n) {
        for (Player p : Engine.players) {

            if (p == null)
                return;

            int i = p.absX;
            int j = p.absY;

            if ((j < n.absY) && (i == n.absX)) {
                n.moveX = 0;
                n.moveY = Engine.npcMovement.getMove(n.absY, j + 1);
            } else if ((j > n.absY) && (i == n.absX)) {
                n.moveX = 0;
                n.moveY = Engine.npcMovement.getMove(n.absY, j - 1);
            } else if ((i < n.absX) && (j == n.absY)) {
                n.moveX = Engine.npcMovement.getMove(n.absX, i + 1);
                n.moveY = 0;
            } else if ((i > n.absX) && (j == n.absY)) {
                n.moveX = Engine.npcMovement.getMove(n.absX, i - 1);
                n.moveY = 0;
            } else if ((i < absX) && (j < n.absY)) {
                n.moveX = Engine.npcMovement.getMove(n.absX, i + 1);
                n.moveY = Engine.npcMovement.getMove(n.absY, j + 1);
            } else if ((i > n.absX) && (j > n.absY)) {
                n.moveX = Engine.npcMovement.getMove(n.absX, i - 1);
                n.moveY = Engine.npcMovement.getMove(n.absY, j - 1);
            } else if ((i < n.absX) && (j > n.absY)) {
                n.moveX = Engine.npcMovement.getMove(n.absX, i + 1);
                n.moveY = Engine.npcMovement.getMove(n.absY, j - 1);
            } else if ((i > n.absX) && (j < n.absY)) {
                n.moveX = Engine.npcMovement.getMove(n.absX, i - 1);
                n.moveY = Engine.npcMovement.getMove(n.absY, j + 1);
            }
            Engine.npcMovement.getNextNPCMovement(n);
        }
    }

    /**
     * 1 V 1
     */
    public int spawnedFor;

    private void npcAggression(int j) {
        for (Player p3 : Engine.players) {
            if (p3 != null && !p3.isDead) {
                if (!p3.isDead && !Engine.npcs[j].isDead) {
                    if (Misc.getDistance(Engine.npcs[j].absX, Engine.npcs[j].absY, p3.absX, p3.absY) <= 1) {
                        Engine.npcs[j].attackPlayer = p3.playerId;
                        Engine.npcs[j].attackingPlayer = true;
                        p3.attackNPC = j;
                        p3.attackingNPC = true;
                        this.appendPlayerFollowing(p3, Engine.npcs[j]);
                        if (Engine.npcs[j].absX == p3.absX && Engine.npcs[j].absY == p3.absY)
                            Engine.npcs[j].moveY = Engine.npcMovement.getMove(Engine.npcs[j].absY, p3.absY + 1);
                    } else if (Misc.getDistance(Engine.npcs[j].absX, Engine.npcs[j].absY, p3.absX, p3.absY) >= 5 && Misc.getDistance(Engine.npcs[j].absX, Engine.npcs[j].absY, p3.absX, p3.absY) < 6) {
                        if (j != 2745) {
                        } else if (Misc.getDistance(Engine.npcs[j].absX, Engine.npcs[j].absY, p3.absX, p3.absY) >= 6) {
                            Engine.npcs[j].attackingPlayer = false;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkNPC() {
        switch (npcType) {
            case 6222:
            case 6218:
            case 6269:
            case 6210:
            case 6212:
            case 5253:
            case 6220:
            case 6229:
            case 6230:
            case 6231:
            case 6998:
            case 3752:
            case 3753:
            case 3754:
            case 3755:
            case 3756:
            case 3757:
            case 3758:
            case 3759:
            case 3760:
            case 3761:
            case 3747:
            case 3748:
            case 3749:
            case 3750:
            case 3751:
            case 3727:
            case 3728:
            case 3729:
            case 3730:
            case 3731:
            case 3762:
            case 7997:
            case 3763:
            case 3764:
            case 3765:
            case 3766:
            case 3767:
            case 3768:
            case 3769:
            case 3770:
            case 3771:
            case 3732:
            case 3733:
            case 3734:
            case 3735:
            case 3736:
            case 3737:
            case 3738:
            case 3739:
            case 3740:
            case 3741:
            case 3772:
            case 3773:
            case 3774:
            case 3775:
            case 3776:
            case 3742:
            case 3743:
            case 3744:
            case 3745:
            case 3746:
            case 6999:
            case 7995:
            case 5421:
            case 6729:
            case 6625:
            case 6691:
            case 1153:
            case 1154:
            case 1156:
            case 1157:
            case 1155:
            case 1158:
            case 6223:
            case 6225:
            case 6227:
            case 6239:
            case 6203:
            case 6204:
            case 6206:
            case 6208:
            case 6247:
            case 6248:
            case 6250:
            case 6252:
            case 6257:
            case 6255:
            case 6260:
            case 6261:
            case 50:
            case 6263:
            case 6265:
            case 115:
            case 4701://Greater Demon
            case 5293://zombie
            case 117://Hill Giant
            case 63://Deadly red spider
            case 1019://fire elemental
            case 6713://Revenant ork
            case 6710://Revenant hobgoblin
            case 6726://Revenant Knight
            case 4673://Black Dragon
            case 4674://Black Dragon
            case 4675://Black Dragon
            case 3376://Baby Black Dragon
            case 2745:
                npcAggression(npcId);
                break;
            default:
                return;
        }
    }

    public boolean npccanloot = false;

    public void npcDied(Player p, int npcID, int abSX, int abSY) {
        Random rand = new Random();
        try {

            BufferedReader in = new BufferedReader(new FileReader(Config.getPropertiesValue("NPC_DROP_PATH")));
            String input;
            int on = 0;
            this.followPlayer = -1;
            String[] splitEQL;
            String[] splitCOM;
            String[] splitDSH;
            String[] splitCLN;
            String[] splitSCL;
            while ((input = in.readLine()) != null) {
                splitEQL = null;
                splitEQL = null;
                splitDSH = null;
                splitCLN = null;
                splitSCL = null;
                if (!input.startsWith("/") && input.contains("=") && input.contains(",") && input.contains("-") && input.contains(":")) {
                    try {
                        splitEQL = input.split("=");
                        if (Integer.parseInt(splitEQL[0]) == npcID) {
                            splitSCL = splitEQL[1].split(";");
                            int Wealth = 0;

                            for (int i = Wealth; i < splitSCL.length; i++) {
                                splitCOM = splitSCL[i].split(",");
                                splitDSH = splitCOM[1].split("-");
                                splitCLN = splitCOM[2].split(":");
                                int item = Integer.parseInt(splitCOM[0]);

                                int chance;
                                int min = Integer.parseInt(splitDSH[0]);
                                int max = Integer.parseInt(splitDSH[1]);
                                int chance2 = Misc.random(100);
                                int outOf = Integer.parseInt(splitCLN[1]);
                                int amount = rand.nextInt((max - min) + 1) + min;
                                int ifDrop = 85;
                                if (item == 1048) {
                                    chance = Misc.random(chance2);
                                } else {
                                    chance = chance2;
                                }
                                if (ifDrop <= chance) {
                                    Engine.items.createGroundItem(item, amount, abSX, abSY, heightLevel, "");

                                }
                            }
                        }
                    } catch (Exception e) {
                        //System.out.println("Exception dropping item:\n"+e);
                    }
                    ++on;
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    /**
     * The position this NPC is stored in the npc array in the Engine class.
     */
    public int npcId = -1;

    /**
     * The NPC's id, such as 50 for king black dragon.
     */
    public int npcType = 0;

    /**
     * The name of this NPC.
     */
    public String name = "";

    /**
     * If it should hit 0 HP, how long it should take to respawn.
     */
    public int respawnDelay = 60;

    /**
     * Emotes attacking, blocking and death.
     */
    public int attackEmote = 0;
    public int defendEmote = 0;
    public int deathEmote = 0;

    /**
     * Delay between attacking.
     */
    public int attackDelay = 5;

    /**
     * Its combat level, used for calculating its accuracy and defence.
     */
    public int combatLevel = 0;

    /**
     * Max hitpoints it can have.
     */
    public int maxHP = 100;

    /**
     * The basic max hit it can have, with an attack method you  can get more specific.
     */
    public int maxHit = Misc.random(3);

    /**
     * Attack type, 0 for melee, 1 for range, 2 for magic.
     */
    public int atkType = 0;

    /**
     * The weakness, same setup as atkType.
     */
    public int weakness = 0;

    /**
     * The current HP this NPC has.
     */
    public int currentHP = 100;

    /**
     * The direction this NPC is moving in.
     */
    public int direction = -1;

    /**
     * The height level its at.
     */
    public int heightLevel = 0;

    /**
     * Request for updating facing.
     */
    public boolean faceToUpdateReq = false;
    public int faceToRequest = -1;

    /**
     * If the NPC is dead or not.
     */
    public boolean isDead = false;

    /**
     * If the death emote has been requested, move on to the next part of the death process.
     */
    public boolean deadEmoteDone = false;

    /**
     * Hide the NPC until it is ready to respawn.
     */
    public boolean hiddenNPC = false;

    /**
     * Absolute positioning.
     */
    public int absX = 0, absY = 0;

    /**
     * If false, the NPC will not respawn if it dies.
     */
    public boolean needsRespawn = false;

    /**
     * If set to true, this NPC will randomly walk around.
     */
    public boolean randomWalk = true;

    /**
     * If an update is needed.
     */
    public boolean updateReq = false;

    /**
     * If the NPC should speak.
     */
    public boolean speakTextUpdateReq = false;
    public String speakText = "";


    /**
     * Hit requests.
     */
    public boolean hit1UpdateReq = false;
    public boolean hit2UpdateReq = false;
    public int hitDiff1 = 0;
    public int posionHit1 = 0;
    public int hitDiff2 = 0;
    public int posionHit2 = 0;

    /**
     * Animation request.
     */
    public boolean animUpdateReq = false;
    public int animRequest = 65535;
    public int animDelay = 0;

    /**
     * Graphic request.
     */
    public boolean gfxUpdateReq = false;
    public int gfxRequest = 65535;
    public int gfxDelay = 0;

    /**
     * Facing request.
     */
    public boolean faceCoordsUpdateReq = false;
    public int faceCoordsX = -1;
    public int faceCoordsY = -1;

    /**
     * The area this NPC can randomly walk around.
     */
    public int moveRangeX1 = 0;
    public int moveRangeY1 = 0;
    public int moveRangeX2 = 0;
    public int moveRangeY2 = 0;

    /**
     * What should be added onto the absolute positioning if this NPC moves.
     */
    public int moveX = 0;
    public int moveY = 0;

    /**
     * The original position the NPC spawned at.
     */
    public int makeX = 0;
    public int makeY = 0;

    /**
     * Delay before the NPC can attack again.
     */
    public int combatDelay = 0;
    public int attackPlayer = 0;
    public boolean attackingPlayer = false;

    /**
     * Gives quick access to the frame class.
     */
    public Frames frames = Engine.frames;


    /**
     * Constructs a new NPC class.
     *
     * @param type  The type of NPC.
     * @param index The position the NPC is at.
     */
    public NPC(int type, int index) {
        npcType = type;
        npcId = index;
    }

    /**
     * This method is called every 600 milliseconds.
     */
    public void appendPlayerFollowing(Player p, NPC n) {
        if (p == null) {
            return;
        }
        int pX = p.absX;
        int pY = p.absY;

        requestFaceCoords(pX, pY);

        // Don't teleport if the npc is attacking the player
        if (!n.attackingPlayer) {
            if (n.absX > pX + 15 || n.absY > pY + 15 || n.absX < pX - 15 || n.absY < pY - 15 || n.heightLevel < p.heightLevel || n.heightLevel > p.heightLevel) {
                if (p.FamiliarID > 0 || p.FamiliarType > 0) {
                    requestGFX(1315, 0);
                }
                n.absX = pX;
                n.absY = pY + 1;
                heightLevel = p.heightLevel;
            }
        } else {
            if (n.absX > pX + 10 || n.absY > pY + 10 || n.absX < pX - 10 || n.absY < pY - 10 || n.heightLevel < p.heightLevel || n.heightLevel > p.heightLevel) {
                n.attackingPlayer = false;
                n.followPlayer = 0;
                requestFaceCoords(n.absX, n.absY);
            }
        }

        goToPlayer(p, n);

        Engine.npcMovement.getNextNPCMovement(n);
    }

    /**
     * make the npc go to the player
     */
    public void goToPlayer(Player p, NPC n) {
        if (p == null) {
            return;
        }

        int pX = p.absX;
        int pY = p.absY;

        requestFaceCoords(pX, pY);
        System.out.println("following player");

        if (pY < n.absY && pX == n.absX) {
            n.moveX = 0;
            n.moveY = Engine.npcMovement.getMove(n.absY, pY + 1);
        } else if (pY > n.absY && pX == n.absX) {
            n.moveX = 0;
            n.moveY = Engine.npcMovement.getMove(n.absY, pY - 1);
        } else if (pX < n.absX && pY == n.absY) {
            n.moveX = Engine.npcMovement.getMove(n.absX, pX + 1);
            n.moveY = 0;
        } else if (pX > n.absX && pY == n.absY) {
            n.moveX = Engine.npcMovement.getMove(n.absX, pX - 1);
            n.moveY = 0;
        } else if (pX < n.absX && pY < n.absY) {
            n.moveX = Engine.npcMovement.getMove(n.absX, pX + 1);
            n.moveY = Engine.npcMovement.getMove(n.absY, pY + 1);
        } else if (pX > n.absX && pY > n.absY) {
            n.moveX = Engine.npcMovement.getMove(n.absX, pX - 1);
            n.moveY = Engine.npcMovement.getMove(n.absY, pY - 1);
        } else if (pX < n.absX && pY > n.absY) {
            n.moveX = Engine.npcMovement.getMove(n.absX, pX + 1);
            n.moveY = Engine.npcMovement.getMove(n.absY, pY - 1);
        } else if (pX > n.absX && pY < n.absY) {
            n.moveX = Engine.npcMovement.getMove(n.absX, pX - 1);
            n.moveY = Engine.npcMovement.getMove(n.absY, pY + 1);
        }

        Engine.npcMovement.getNextNPCMovement(n);
    }

    public void moveNowToPlayer(Player p) {
        this.speakText = "Moving to player";
        this.speakTextUpdateReq = true;
        requestFaceCoords(p.absX, p.absY);
        this.moveX = Engine.npcMovement.getMove(this.absX, p.absX);
        this.moveY = Engine.npcMovement.getMove(this.absY, p.absY);
        Engine.npcMovement.getNextNPCMovement(this);
    }

    /*public void followPlayerWhileInCombat(Player p, NPC n) {
        if (p == null) {
            return;
        }
        int pX = p.absX;
        int pY = p.absY;

        requestFaceCoords(pX, pY);



        Engine.npcMovement.getNextNPCMovement(n);
    }*/

    public void process() {


        /**
         * Npc Text Above Head
         * Extra Npc Emotes
         * Extra Npc Grafics
         */
        switch (npcType) {

            case 2574: // Bank Gaurd
            case 1780: // lary
                RanText = Misc.random(12);

                if (RanText == 0) {
                    requestText("Welcome to -iCEYY!SCAPe-");
                } else if (RanText == 1) {
                    requestText("website: http://iceyy.no-ip.biz");
                } else if (RanText == 2) {
                    requestText("Like to pk? Then what are you waiting for?! Go kill some noobs!");
                } else if (RanText == 3) {
                    requestText("<img=1>Icedice is the creater of iceyyscape!");
                } else if (RanText == 4) {
                    requestText("Teleports are located in your quest tab!");
                } else if (RanText == 5) {
                    requestText("Read the rule book!");
                } else if (RanText == 5) {
                    requestText("Have a great idea for an update? Post it on the forums!!");
                } else if (RanText == 6) {
                    requestText("Please donate! We need your suport!");
                } else if (RanText == 7) {
                    requestText("Try out Dragon Slayer! Its life changing!");
                } else if (RanText == 8) {
                    requestText("Shops teleport is in your quest tab!!");
                } else if (RanText == 9) {
                    requestText("Need something to do? Go to the party room!");
                } else if (RanText == 10) {
                    requestText("Check out the highscores at http://icey.no-ip.biz!");
                } else if (RanText == 11) {
                    requestText("Upload and browes screen shots on the webiste!!!");
                } else if (RanText == 12) {
                    requestText("There are shops found in the quest tab that you can't find anywere else!!");
                }
                break;
        }
        checkNPC();

        if (followPlayer != 0 && followPlayer != -1) {
            Player fp = Engine.players[followPlayer];
            if (fp != null) {
                appendPlayerFollowing(fp, this);
            } else {
                isDead = true;
            }

        }
        if (respawnDelay > 0 && isDead) {
            respawnDelay--;
        }
        if (combatDelay > 0) {
            combatDelay--;
        }
        if (npcType == 2253) {
            requestAnim(1351, 1);
        }
        if (attackingPlayer) {
            Engine.npcPlayerCombat.attackPlayer(this);
        }
    }

    /**
     * Request an animation for this NPC.
     *
     * @param animId The amination to perform.
     * @param animD  The delay before doing the animation.
     */
    public void requestAnim(int animId, int animD) {
        animRequest = animId;
        animDelay = animD;
        animUpdateReq = true;
        updateReq = true;
    }

    /**
     * Request text for this NPC.
     *
     * @param message The message to make the NPC say.
     */
    public void requestText(String message) {
        speakText = message;
        animUpdateReq = true;
        speakTextUpdateReq = true;
    }

    /**
     * Request an graphic for this NPC.
     *
     * @param gfxId The graphic to perform.
     * @param gfxD  The delay or height or the gfx depending on the value.
     */
    public void requestGFX(int gfxId, int gfxD) {
        if (gfxD >= 100) {
            gfxD += 6553500;
        }
        gfxRequest = gfxId;
        gfxDelay = gfxD;
        gfxUpdateReq = true;
        updateReq = true;
    }

    /**
     * Request this NPC faces two coordinates.
     *
     * @param x The x coordinate to face.
     * @param y The y coordinate to face.
     */
    public void requestFaceCoords(int x, int y) {
        faceCoordsX = 2 * x + 1;
        faceCoordsY = 2 * y + 1;
        faceCoordsUpdateReq = true;
        updateReq = true;
    }

    /**
     * Request this NPC faces another NPC or player.
     *
     * @param faceId The target to face.
     */
    public void requestFaceTo(int faceId) {
        faceToRequest = faceId;
        faceToUpdateReq = true;
        updateReq = true;
    }

    /**
     * Add damage to this NPC.
     *
     * @param damage To amount of damage.
     * @param posion 0 for normal damage, 1 for posion.
     */
    public void appendHit(int damage, int posion) {
        if (damage > currentHP) {
            damage = currentHP;
        }
        currentHP -= damage;
        if (currentHP <= 0) {
            currentHP = 0;
            attackingPlayer = false;
            isDead = true;
        }
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
}
