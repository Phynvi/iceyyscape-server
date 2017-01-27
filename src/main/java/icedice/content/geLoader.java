/*
 * Class geLoader
 *
 * 90% COMPLETED
 *
 * 04/12/2009
 *
 * Created by GatYuri
 */

package icedice.content;

import icedice.util.Misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class geLoader {
    public int maxListedItems = 16000;
    public geList[] geList = new geList[maxListedItems];
    public String sell = "";
    public String buy = "";

    public geLoader() {
        System.out.println("[GrandExchange] - Loaded Prices");
        try {
            loadprices();
        } catch (IOException e) {

        }
    }

    public void loadprices() throws IOException {
        String line = "";
        FileReader fr = new FileReader("data/items/grandExchange.cfg");
        BufferedReader br = new BufferedReader(fr);
        String s = "";
        while ((s = br.readLine()) != null) {
            String Read[] = s.split("\t");
            if (Read[0].contains("[")) {
                break;
            }
            String itemId = Read[0].substring(7);
            if (Read[1].contains("m")) {
                sell = Read[1].replace("m", "00000");
            } else if (Read[1].contains("k")) {
                sell = Read[1].replace("k", "00");
            } else {
                sell = Read[1].replace(",", "");
            }
            if (Read[2].contains("m")) {
                buy = Read[2].replace("m", "00000");
            } else if (Read[2].contains("k")) {
                buy = Read[2].replace("k", "00");
            } else {
                buy = Read[2].replace(",", "");
            }
            sell = sell.replace(".", "").replace(" ", "");
            buy = buy.replace(".", "").replace(" ", "");

            if (sell.equals("null")) {
                sell = "0";
            }
            if (buy.equals("null")) {
                buy = "0";
            }
            newItemList(Integer.parseInt(itemId), Integer.parseInt(sell), Integer.parseInt(buy));
        }

    }

    private void newItemList(int ItemId, int buy, int sell) {
        if (ItemId > maxListedItems) {
            Misc.println("maxListedItems to low.");
            return;
        }
        geList[ItemId] = new geList(ItemId, buy, sell);
    }
}
