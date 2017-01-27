package icedice.content;

import icedice.players.items.PlayerItems;
import icedice.Engine;
import icedice.players.Player;

/**
 * Created by IntelliJ IDEA.
 * User:     Serenty
 * Date:     3-march-2009
 * Project:  Boneyard
 * Time:     19:51:15
 */
public class ShopHandler {

    Player p;
    PlayerItems pi = new PlayerItems();
    public int maxItems = 40;
    public int[] items = new int[maxItems];
    public int[] itemsN = new int[maxItems];
    public int[] maxItemAmount = new int[maxItems];
    public boolean generalStore = false;
    public boolean shopShouldBuy = true;
    public long lastRestock = System.currentTimeMillis();
    int shopid = 0;
    public boolean mainstock = false;

    public ShopHandler() {


        for (int i = 0; i < 40; i++) {
            items[i] = -1;
        }

        this.items = items;
        this.itemsN = itemsN;
        maxItemAmount = itemsN;
    }

    public void process(Player p) {
        if (System.currentTimeMillis() - lastRestock >= 60000) {
            clearSlots(p);
            for (int i = 0; i < items.length; i++) {
                if (itemsN[i] < maxItemAmount[i]) {
                    itemsN[i]++;
                }
            }
            lastRestock = System.currentTimeMillis();
        }
    }

    public void sell(Player p, int item, int amnt) {
        PlayerItems pi = new PlayerItems();
        if (!generalStore) {
            p.frames.sendMessage(p, "You cannot sell items to this shop.");
            return;
        }
        if (item == 995) {
            p.frames.sendMessage(p, "You cannot sell this item to this shop.");
            return;
        }
        if (item == 1277 || item == 6245 || item == 6243 || item == 12866 || item == 12887 || item == 12894 || item == 12969 || item == 12971 || item == 12978 || item == 6959 || item == 1031 || item == 9096 || item == 9097 || item == 9098 || item == 9099 || item == 9100 || item == 9102 || item == 9104 || item == 4215 || item == 4214 || item == 1007 || item == 4375 || item == 9084 || item == 13095 || item == 13097 || item == 13099 || item == 13636 || item == 3767 || item == 2904 || item == 2906 || item == 2908 || item == 2910 || item == 12873 || item == 12880 || item == 12886 || item == 6611 || item == 1009 || item == 78 || item == 4217 || item == 4218 || item == 4219) {
            if ((p.rights < 1) && (p.donator < 1)) {
                p.frames.sendMessage(p, "You cannont sell this item.");
                return;
            }
        }
        int free = findFreeSlot();
        if (!isItemOnShop(item) && (shopShouldBuy || generalStore)) {
            if (free == -1) {
                p.frames.sendMessage(p, "This shop is full.");
                return;
            }
            items[free] = item;
            itemsN[free] = 0;
        }
        int slot = findItemSlot(item);
        if (amnt <= pi.invItemCount(p, item)) {
            if (itemStacks(item)) {
                items[slot] = item;
                itemsN[slot] += amnt;
                pi.deleteItem(p, item, pi.getItemSlot(p, item), amnt);
                pi.addItem(p, 995, amnt * buyingItemValue(item));
            } else {
                items[slot] = item;
                for (int notused = amnt; notused > 0; notused--) {
                    pi.deleteItem(p, item, pi.getItemSlot(p, item), 1);
                    pi.addItem(p, 995, buyingItemValue(item));
                    itemsN[slot]++;
                }
            }
        } else {
            amnt = pi.invItemCount(p, item);
            if (itemStacks(item)) {
                items[slot] = item;
                itemsN[slot] += amnt;
                pi.deleteItem(p, item, pi.getItemSlot(p, item), amnt);
                pi.addItem(p, 995, amnt * buyingItemValue(item));
            } else {
                items[slot] = item;
                for (int notused = amnt; notused > 0; notused--) {
                    pi.deleteItem(p, item, pi.getItemSlot(p, item), 1);
                    pi.addItem(p, 995, buyingItemValue(item));
                    itemsN[slot]++;
                }
            }
        }


        sendShopItems(p);
        sendPlayerInventory(p);
    }

    public void buy(Player p, int item, int amnt) {
        clearSlots(p);
        int slot = findItemSlot(item);
        if (slot == -1) {
            return;
        }
        if (itemsN[slot] < amnt) {
            amnt = itemsN[slot];
        }
        if (amnt == 0) {
            if (generalStore) {
                items[slot] = -1;
            }
            p.frames.sendMessage(p, "That item's stock has run out.");
            return;
        }
        if (itemStacks(item)) {                                                 // item stackable, easier...
            if (pi.freeSlotCount(p) < 1) {
                items[slot] = -1;
                p.frames.sendMessage(p, "Not enough space on inventory");
                return;
            }
            if (pi.invItemCount(p, 995) < (amnt * sellingItemValue(item))) {
                p.frames.sendMessage(p, "Not enough coins to buy that many.");
                return;
            }
            pi.deleteItem(p, 995, pi.getItemSlot(p, 995), amnt * sellingItemValue(item));
            pi.addItem(p, item, amnt);
            if (generalStore) {
                if (itemsN[slot] > amnt) {
                    itemsN[slot] -= amnt;
                } else {
                    itemsN[slot] = -1;
                }
            } else {
                if (itemsN[slot] > amnt) {
                    itemsN[slot] -= amnt;
                } else {
                    itemsN[slot] = 0;
                }
            }

        } else {                                                                // item not stackable
            for (int i = amnt; i > 0; i--) {
                int price = sellingItemValue(item);
                clearSlots(p);
                if (pi.invItemCount(p, 995) < price) {
                    p.frames.sendMessage(p, "Not enough coins to buy that many.");
                    break;
                }
                if (pi.findInvSlot(p) == -1) {
                    p.frames.sendMessage(p, "Not enough space on inventory");
                    break;
                }
                if (itemsN[slot] < 1) {
                    clearSlots(p);
                    p.frames.sendMessage(p, "The shop has run out of stock from this item!");
                    break;
                }
                if (itemsN[slot] == 1) {
                    pi.deleteItem(p, 995, pi.getItemSlot(p, 995), price);
                    pi.addItem(p, item, 1);
                    if (generalStore) {
                        items[slot] = -1;
                    } else {
                        itemsN[slot] = 0;
                    }
                    break;
                }
                itemsN[slot]--;

                pi.deleteItem(p, 995, pi.getItemSlot(p, 995), price);
                pi.addItem(p, item, 1);
                clearSlots(p);

            }


        }

        sendShopItems(p);
        sendPlayerInventory(p);
        clearSlots(p);
    }

    public void buystock(Player p, int ite, int amnt) {
        int item = ite;
        if (itemStacks(item)) {                                                 // item stackable, easier...
            if (pi.freeSlotCount(p) < 1) {
                p.frames.sendMessage(p, "Not enough space on inventory");
                return;
            }
            if (pi.invItemCount(p, 995) < (amnt * sellingItemValue(item))) {
                p.frames.sendMessage(p, "Not enough coins to buy that many.");
                return;
            }
            pi.deleteItem(p, 995, pi.getItemSlot(p, 995), amnt * sellingItemValue(item));
            pi.addItem(p, item, amnt);

        } else {
            // item not stackable
            for (int i = amnt; i > 0; i--) {
                int price = sellingItemValue(item);
                if (pi.invItemCount(p, 995) < price) {
                    p.frames.sendMessage(p, "Not enough coins to buy that many.");
                    break;
                }
                if (pi.findInvSlot(p) == -1) {
                    p.frames.sendMessage(p, "Not enough space on inventory");
                    break;
                }
                pi.deleteItem(p, 995, pi.getItemSlot(p, 995), price);
                pi.addItem(p, item, 1);
            }
        }
        //sendShopItems(p);
        sendPlayerInventory(p);

    }

    public int findItemSlot(int item) {
        for (int i = 0; i < items.length; i++) {
            if (item == items[i]) {
                return i;
            }
        }
        return -1;
    }

    public int findFreeSlot() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    public void clearSlots(Player p) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] < 1) {
                items[i] = -1;
            } else {
            }
        }

    }

    public void sendShopItems(Player p) {
        p.frames.setItems(p, -1, 64271, 31, items, itemsN);
    }

    public boolean isItemOnShop(int item) {
        return findItemSlot(item) != -1;
    }

    public void sendPlayerInventory(Player p) {
        p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);

    }

    public int sellingItemValue(int item) {
        //return Engine.items.itemLists[item].shopValue;
        if (sellingItemValuePercent(item) <= 999999999)
            return (int) sellingItemValuePercent(item);
        else
            return 999999999;
    }

    public double sellingItemValuePercent(int item) {
        double value = 0;
        double percentage = value;
        int slot = findItemSlot(item);
        int free = findFreeSlot();
        if (itemsN[slot] > 0) {
            if (Engine.items.itemLists[item].shopValue + ((Engine.items.itemLists[item].shopValue * .15) - (itemsN[slot] * .03)) >= 1)
                return value = (double) (Engine.items.itemLists[item].shopValue + ((Engine.items.itemLists[item].shopValue * .15) - (itemsN[slot] * .03)));
            else
                return value = 1;
        } else if (itemsN[slot] < 1) {
            return value = (double) (Engine.items.itemLists[item].shopValue + (Engine.items.itemLists[item].shopValue * .15));
        }
        return percentage;
    }

    public int buyingItemValue(int item) {
        return (int) buyingItemVaulePercent(item);
    }

    public double buyingItemVaulePercent(int item) {
        double value = 0;
        double percentage = value;
        int slot = findItemSlot(item);
        int free = findFreeSlot();

        if (isItemOnShop(item)) {
            if (itemsN[slot] > 0)
                if (Engine.items.itemLists[item].shopValue - ((Engine.items.itemLists[item].shopValue * .15) + (itemsN[slot] * .30)) > 0) {
                    return value = (double) (Engine.items.itemLists[item].shopValue - ((Engine.items.itemLists[item].shopValue * .15) + (itemsN[slot] * .30)));
                } else {
                    return value = 0;
                }
            else
                return value = (double) (Engine.items.itemLists[item].shopValue - (Engine.items.itemLists[item].shopValue * .15));
        } else if (!isItemOnShop(item)) {
            if (itemsN[free] > 0)
                if (Engine.items.itemLists[item].shopValue - ((Engine.items.itemLists[item].shopValue * .15) + (itemsN[slot] * .30)) > 0) {
                    return value = (double) (Engine.items.itemLists[item].shopValue - ((Engine.items.itemLists[item].shopValue * .15) + (itemsN[slot] * .30)));
                } else {
                    return value = 0;
                }
            else
                return value = (double) (Engine.items.itemLists[item].shopValue - (Engine.items.itemLists[item].shopValue * .15));
        }

        return percentage;
    }

    public boolean itemStacks(int item) {
        return Engine.items.itemLists[item].itemStackable;
    }

    public void handleoption(Player p, int interfaceId, int buttonId, int buttonId2, int packetId) {
        switch (interfaceId) {
            case 620:
                if (buttonId == 25) {
                    p.frames.setInterfaceConfig(p, 620, 23, false);
                    p.frames.setInterfaceConfig(p, 620, 24, true);
                    p.frames.setInterfaceConfig(p, 620, 29, false);
                    p.frames.setInterfaceConfig(p, 620, 25, true);
                    p.frames.setInterfaceConfig(p, 620, 27, true);
                    p.frames.setInterfaceConfig(p, 620, 26, false);
                    p.frames.setAccessMask(p, 1278, 23, 620, 0, 40);
                    mainstock = true;
                }
                if (buttonId == 26) {
                    p.frames.setInterfaceConfig(p, 620, 23, true);
                    p.frames.setInterfaceConfig(p, 620, 24, false);
                    p.frames.setInterfaceConfig(p, 620, 29, true);
                    p.frames.setInterfaceConfig(p, 620, 25, false);
                    p.frames.setInterfaceConfig(p, 620, 27, false);
                    p.frames.setInterfaceConfig(p, 620, 26, true);
                    p.frames.setAccessMask(p, 1278, 24, 620, 0, 40);
                    mainstock = false;

                }
                PlayerItems pi = new PlayerItems();

                if (buttonId == 23) {
                    int itemid = returnItemId(shopid, buttonId2);
                    switch (packetId) {
                        case 233:
                            /* Value. */
                            //p.frames.sendMessage(p, "This item costs " + Engine.items.getItemValue(itemid) + " coins.");
                            p.frames.sendMessage(p, Engine.items.getItemName(itemid) + " currently costs " + sellingItemValue(itemid) + " coins.");
                            break;
                        case 21:
                            /* Buy 1. */
                            buystock(p, itemid, 1);
                            break;
                        case 169:
                            /* Buy 5. */
                            buystock(p, itemid, 5);
                            break;
                        case 214:
                            /* Buy 10. */
                            buystock(p, itemid, 10);
                            break;
                        case 173:
                            /* Buy X. */
                            p.input.request(3, itemid);
                            break;
                        case 232:
                            /*Examine. */
                            p.frames.sendMessage(p, Engine.items.getItemDescription(itemid));
                            break;
                    }
                }
                if (buttonId == 24) {
                    int itemid = items[buttonId2];


                    switch (packetId) {
                        case 233:
                            /* Value. */
                            p.frames.sendMessage(p, Engine.items.getItemName(itemid) + " currently costs " + sellingItemValue(itemid) + " coins.");
                            break;
                        case 21:
                            /* Buy 1. */
                            buy(p, itemid, 1);
                            break;
                        case 169:
                            /* Buy 5. */
                            buy(p, itemid, 5);
                            break;
                        case 214:
                            /* Buy 10. */
                            buy(p, itemid, 10);
                            break;
                        case 232:
                            /* Buy X. */
                            p.input.request(4, itemid);
                            break;
                        case 90:
                            /*Examine. */
                            p.frames.sendMessage(p, Engine.items.getItemDescription(itemid));
                            break;

                    }


                }
                break;
            case 621:
                if (buttonId == 0) {
                    int itemid = p.items[buttonId2];

                    switch (packetId) {
                        case 233:
                            /*Value.*/
                            //p.frames.sendMessage(p, "That item is Worth " + Engine.items.getItemValue(itemid) + " coins.");
                            p.frames.sendMessage(p, "This shop will buy " + Engine.items.getItemName(itemid) + " for " + buyingItemValue(itemid) + " coins.");
                            break;
                        case 21:
                            /*sell 1.*/
                            sell(p, itemid, 1);
                            break;
                        case 169:
                            /*sell 5.*/
                            sell(p, itemid, 5);
                            break;
                        case 214:
                            /*sell 10.*/
                            sell(p, itemid, 10);
                            break;
                        case 232:
                            /* Buy X. */
                            p.input.request(5, itemid);
                            break;
                        case 90:
                            /*
                             * Examine.
                             */
                            p.frames.sendMessage(p, Engine.items.getItemDescription(itemid));
                            break;
                    }
                }
                break;
        }
    }

    public void shopopen(Player p, int shopId) {
        p.frames.removeShownInterface(p);
        p.frames.showInterface(p, 620); //Shop
        p.frames.setInventory(p, 621);
        p.frames.setTab(p, 80, 621);//Inv
        shopid = shopId;
        Object[] setshopparams = new Object[]{shopId, 93};

        int shi = 621 << 16;
        int ship = (620 << 16) + 24;

        Object[] invparams = new Object[]{"", "", "", "Sell X", "", "Sell 10", "Sell 5", "Sell 1", "Value", -1, 0, 7, 4, 93, shi};
        Object[] shopparams = new Object[]{"", "", "", "Buy X", "", "Buy 10", "Buy 5", "Buy 1", "Value", -1, 0, 4, 10, 31, ship};

        p.frames.runScript(p, 25, setshopparams, "vg");//Loads main stock items
        p.frames.runScript(p, 150, invparams, "IviiiIsssssssss");
        p.frames.runScript(p, 150, shopparams, "IviiiIsssssssss");
        p.frames.setAccessMask(p, 1278, 0, 621, 0, 28);

        if (mainstock) {
            p.frames.setInterfaceConfig(p, 620, 23, false);
            p.frames.setInterfaceConfig(p, 620, 24, true);
            p.frames.setInterfaceConfig(p, 620, 29, false);
            p.frames.setInterfaceConfig(p, 620, 25, true);
            p.frames.setInterfaceConfig(p, 620, 27, true);
            p.frames.setInterfaceConfig(p, 620, 26, false);
            p.frames.setAccessMask(p, 1278, 23, 620, 0, 40);
        } else {
            p.frames.setInterfaceConfig(p, 620, 23, true);
            p.frames.setInterfaceConfig(p, 620, 24, false);
            p.frames.setInterfaceConfig(p, 620, 29, true);
            p.frames.setInterfaceConfig(p, 620, 25, false);
            p.frames.setInterfaceConfig(p, 620, 27, false);
            p.frames.setInterfaceConfig(p, 620, 26, true);
            p.frames.setAccessMask(p, 1278, 24, 620, 0, 40);

        }
    }

    /***************************************************************************************************************/
    /*                              ONLY EDIT THIS!                                                                */

    /***************************************************************************************************************/
    public int returnItemId(int shopid, int buttonId2) {
        switch (shopid) {
            case 868:
                switch (buttonId2) {
                    case 0:
                        return 1931;
                    case 1:
                        return 1935;
                    case 2:
                        return 1735;
                    case 3:
                        return 1925;
                    case 4:
                        return 1923;
                    case 5:
                        return 1887;
                    case 6:
                        return 590;
                    case 7:
                        return 1755;
                    case 8:
                        return 2347;
                    case 9:
                        return 550;
                    case 10:
                        return 9003;
                }
                break;
            case 2:
                switch (buttonId2) {
                    case 0:
                        return 1931;
                    case 1:
                        return 1935;
                    case 2:
                        return 1735;
                    case 3:
                        return 1925;
                    case 4:
                        return 1923;
                    case 5:
                        return 1887;
                    case 6:
                        return 590;
                    case 7:
                        return 1755;
                    case 8:
                        return 2347;
                    case 9:
                        return 550;
                    case 10:
                        return 9003;
                }
                break;
        }
        return -1;


    }

    public void openshop(Player p, int shopid) {
        switch (shopid) {
            case 1:
            case 2:
            case 3:
                shopopen(p, 868);
                shopShouldBuy = true;
                generalStore = true;
                mainstock = true;
                items = Engine.shops.Generalshop;
                itemsN = Engine.shops.GeneralshopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, items, itemsN);
                p.frames.setString(p, "-iCEYY!GENERAl-", 620, 22);
                break;
            case 4:
                shopopen(p, 700);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.beginnersShop;
                itemsN = Engine.shops.beginnersShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.beginnersShop, Engine.shops.beginnersShopN);
                p.frames.setString(p, "Beginners Shop", 620, 22);
                p.frames.setString(p, "", 620, 28);

                break;
            case 5:
                shopopen(p, 701);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.magicShop;
                itemsN = Engine.shops.magicShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.magicShop, Engine.shops.magicShopN);
                p.frames.setString(p, "Frumscone's Magical Magic Gear", 620, 22);
                break;
            case 6:
                shopopen(p, 702);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.meleeShop;
                itemsN = Engine.shops.meleeShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.meleeShop, Engine.shops.meleeShopN);
                p.frames.setString(p, "Melee Suplies", 620, 22);
                p.frames.setString(p, "Purchase and sell your melee items today!", 620, 28);
                break;

            case 7:
                shopopen(p, 703);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.partyShop;
                itemsN = Engine.shops.partyShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.partyShop, Engine.shops.partyShopN);
                p.frames.setString(p, "Party Shop", 620, 22);
                p.frames.setString(p, "Party! Party! Party! Party! Party!", 620, 28);
                break;

            case 8:
                shopopen(p, 704);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.food;
                itemsN = Engine.shops.foodN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.food, Engine.shops.foodN);
                p.frames.setString(p, "Quest Tab Food", 620, 22);
                p.frames.setString(p, "Buy or sell your food here!", 620, 28);
                break;
            case 9:
                shopopen(p, 705);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.skillShop;
                itemsN = Engine.shops.skillShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.skillShop, Engine.shops.skillShopN);
                p.frames.setString(p, "Skillers Love Store", 620, 22);
                p.frames.setString(p, "All the items skillers love/want/need rapped up in one amazing store...", 620, 28);
                break;
            case 10:
                shopopen(p, 706);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.DragonShop;
                itemsN = Engine.shops.DragonShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.DragonShop, Engine.shops.DragonShopN);
                p.frames.setString(p, "Mandrith's Dragon Shop", 620, 22);
                p.frames.setString(p, "The greatest dragon items under the sun", 620, 28);
                break;
            case 11:
                shopopen(p, 707);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.Dweps;
                itemsN = Engine.shops.DwepsN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.Dweps, Engine.shops.DwepsN);
                p.frames.setString(p, "Donators Weppons", 620, 22);
                p.frames.setString(p, "All the most powerfull and attractive wepons sold right here!", 620, 28);
                break;
            case 12:
                shopopen(p, 708);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.fancyShop;
                itemsN = Engine.shops.fancyShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.fancyShop, Engine.shops.fancyShopN);
                p.frames.setString(p, "Fancy Dressing", 620, 22);
                p.frames.setString(p, "Look like your worth somthing, buy my fancy clothing.", 620, 28);
                break;
            case 13:
                shopopen(p, 709);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.barrows;
                itemsN = Engine.shops.barrowsN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.barrows, Engine.shops.barrowsN);
                p.frames.setString(p, "The Barrows Store", 620, 22);
                p.frames.setString(p, "Don't feel like getting items from drops at barrows? No problem here is the lazy way to get barrows items!", 620, 28);
                break;
            case 14:
                shopopen(p, 710);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.clothing;
                itemsN = Engine.shops.clothingN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.clothing, Engine.shops.clothingN);
                p.frames.setString(p, "Thessalia's Clothing", 620, 22);
                p.frames.setString(p, "Pants on the ground, pants on the ground, looking like a fool with your pants on the ground. Buy my Clothing!", 620, 28);
                break;
            case 15:
                shopopen(p, 711);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.armour;
                itemsN = Engine.shops.armourN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.armour, Engine.shops.armourN);
                p.frames.setString(p, "Horvik's Amour Shop", 620, 22);
                p.frames.setString(p, "Always use protection", 620, 28);
                break;
            case 16:
                shopopen(p, 712);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.potionShop;
                itemsN = Engine.shops.potionShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.potionShop, Engine.shops.potionShopN);
                p.frames.setString(p, "Pot Shop", 620, 22);
                p.frames.setString(p, "", 620, 28);
                break;
            case 17:
                shopopen(p, 713);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.dArmour;
                itemsN = Engine.shops.dArmourN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.dArmour, Engine.shops.dArmourN);
                p.frames.setString(p, "Donators Armour Shop", 620, 22);
                p.frames.setString(p, "Feel like a donator, look like one to!", 620, 28);
                break;
            case 18:
                shopopen(p, 714);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.rangedShop;
                itemsN = Engine.shops.rangedShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.rangedShop, Engine.shops.rangedShopN);
                p.frames.setString(p, "The Archery Place", 620, 22);
                p.frames.setString(p, "Don't fight up close. Fight from a distance!", 620, 28);
                break;
            case 19:
                shopopen(p, 715);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.godShop;
                itemsN = Engine.shops.godShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.godShop, Engine.shops.godShopN);
                p.frames.setString(p, "The God Shop", 620, 22);
                p.frames.setString(p, "Wear the power of the God's!", 620, 28);
                break;
            case 20:
                shopopen(p, 716);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.herbShop;
                itemsN = Engine.shops.herbShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.herbShop, Engine.shops.herbShopN);
                p.frames.setString(p, "Jatix's Herblore Shop", 620, 22);
                p.frames.setString(p, "Get high, make some potionts!", 620, 28);
                break;
            case 21:
                shopopen(p, 717);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.dRangeShop;
                itemsN = Engine.shops.dRangeShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.dRangeShop, Engine.shops.dRangeShopN);
                p.frames.setString(p, "Donor's Bows", 620, 22);
                p.frames.setString(p, "Dont use the nooby bows, Get some real bows!!", 620, 28);
                break;
            case 22:
                shopopen(p, 718);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.rangedAShop;
                itemsN = Engine.shops.rangedAShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.rangedAShop, Engine.shops.rangedAShopN);
                p.frames.setString(p, "Ranged Armour", 620, 22);
                p.frames.setString(p, "Use Heavy Duty Protection", 620, 28);
                break;
            case 23:
                shopopen(p, 719);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.sumShop;
                itemsN = Engine.shops.sumShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.sumShop, Engine.shops.sumShopN);
                p.frames.setString(p, "Summoning Shop", 620, 22);
                p.frames.setString(p, "Summoning, Use it!", 620, 28);
                break;
            case 24:
                shopopen(p, 720);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.craftShop;
                itemsN = Engine.shops.craftShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.craftShop, Engine.shops.craftShopN);
                p.frames.setString(p, "Crafting Supplies", 620, 22);
                p.frames.setString(p, "Crafting. Make Stuff!", 620, 28);
                break;
            case 25:
                shopopen(p, 721);
                generalStore = false;
                shopShouldBuy = false;
                items = Engine.shops.slayerStore;
                itemsN = Engine.shops.slayerStoreN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.slayerStore, Engine.shops.slayerStoreN);
                p.frames.setString(p, "Duradels Slayer Store", 620, 22);
                p.frames.setString(p, "The Slayer Store!", 620, 28);
                break;
            case 26:
                shopopen(p, 721);
                generalStore = false;
                shopShouldBuy = true;
                items = Engine.shops.prayerShop;
                itemsN = Engine.shops.prayerShopN;
                p.frames.setItems(p, -1, 64209, 93, p.items, p.itemsN);
                p.frames.setItems(p, -1, 64271, 31, Engine.shops.prayerShop, Engine.shops.prayerShopN);
                p.frames.setString(p, "The Holy Brothers Bone Shop Store Market", 620, 22);
                p.frames.setString(p, "Buy and bury them bones bro!", 620, 28);
                break;
        }
    }
}
