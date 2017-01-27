package icedice.players.combat;

import icedice.util.Misc;
import icedice.event.Event;
import icedice.event.EventManager;
import icedice.event.EventContainer;
import icedice.players.Player;

public class Poison {

    public void checkForPoison(Player p, Player p2) {
        if (Misc.random(3) == 1) {
            int dmg = 0;
            switch (p.equipment[3]) {
//=============================================begin weps. (p++)=============================================
                case 5736:
                case 5730:
                case 5728:
                case 5726:
                case 5724:
                case 5722:
                case 5720:
                case 5718:
                case 5702:
                case 5700:
                case 5694:
                case 5692:
                case 5690:
                case 5688:
                case 5686:
                case 5698:
                    dmg = 6;
                    break;
//=============================================begin weps. (p+)=============================================
                case 5734:
                case 5716:
                case 5714:
                case 5712:
                case 5710:
                case 5708:
                case 5706:
                case 5704:
                case 5684:
                case 5682:
                case 5680:
                case 5678:
                case 5676:
                case 5674:
                case 5672:
                case 5670:
                case 5668:
                    dmg = 4;
                    break;
//=============================================begin weps. (p)=============================================
                case 1263:
                case 1261:
                case 1259:
                case 1257:
                case 1255:
                case 1253:
                case 1251:
                case 1235:
                case 1233:
                case 1231:
                case 1229:
                case 1227:
                case 1225:
                case 1223:
                case 1221:
                case 1219:
                    dmg = 2;
                    break;
            }
            switch (p.equipment[13]) {
//========================================================start arrows (p++)===========================================
                case 5622:
                case 5623:
                case 5624:
                case 5625:
                case 5626:
                case 5627:
                    dmg = 6;
                    break;
//========================================================start arrows (p+)===========================================
                case 5616:
                case 5617:
                case 5618:
                case 5619:
                case 5620:
                case 5621:
                    dmg = 4;
                    break;
//========================================================start arrows (p)===========================================
                case 883:
                case 885:
                case 887:
                case 889:
                case 891:
                case 893:
                    dmg = 2;
                    break;
            }
            startPoison(p2, dmg);
        }
    }

    public void startPoison(Player target, int strength) {
        if (strength == 0 && target.poisonTicks > 0) {
            target.poisonTicks = 0;
        }
        if (strength * 3 > target.poisonTicks) {
            target.poisonTicks = strength * 3;
            target.frames.sendMessage(target, "You have been poisoned.");
            startPoisonEvent(target, strength);
        }
    }

    public void startPoisonEvent(final Player target, int strength) {
        EventManager.getSingleton().addEvent(new Event() {
            public void execute(EventContainer c) {
                /* Check if the variable has been changed by something outside the event (anti pots) */
                if (target.poisonTicks == 0) {
                    c.stop();
                    return;
                }
                target.appendHit(target.poisonTicks % 3 == 0 ? target.poisonTicks / 3 : target.poisonTicks / 3 + 1, 1);
                target.poisonTicks--;
                if (target.poisonTicks == 0) {
                    target.frames.sendMessage(target, "The poison fades away.");
                    c.stop();
                }
            }
        }, 60000);
    }
}