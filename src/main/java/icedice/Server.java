

package icedice;


import java.io.*;

import icedice.net.SocketListener;
import icedice.players.PlayerSave;
import icedice.util.Misc;
import icedice.util.HandleServerClient;
import icedice.content.GrandExchangeLoader;
import icedice.Iceframe;
import icedice.content.coms.*;
import icedice.io.*;
import icedice.characters.*;


public class Server {

    /**
     * Class is using native Input/Output library
     */


    public static GrandExchangeLoader GrandExchangeLoader;

    /**
     * The engine used to update almost everything, such as players, items, and NPCs.
     */
    public static Engine engine;

    /**
     * Listens for incoming connections and accepts them.
     */
    public static SocketListener socketListener;

    /**
     * Save character files.
     */
    private static PlayerSave playerSave;

    /**
     *
     */
    public static HandleServerClient serverClient;

    /**
     * Main method for running the server.
     * <p>While specifying port 0 will select a random open port, it is not suggested as
     * you will not be informed on what port was selected. If you pick a port already
     * in use, the server will shut down.
     *
     * @param args The port to run the server on, or 0 for a random port.
     */
    public static void main(String[] args) {
        try {
            socketListener = new SocketListener(Integer.parseInt(args[0]));
        } catch (Exception e) {
            try {
                socketListener = new SocketListener(43594);
            } catch (Exception a) {
                //a.printStackTrace();
            }
        }
        //Iceframe form = new Iceframe();
        //form.setVisible(true);
        engine = new Engine();
        GrandExchangeLoader = new GrandExchangeLoader();
        GrandExchangeLoader.loadOffers();
    /*ry {
		Coms coms = new Coms();
		coms.start();
		Listener listener = new Listener();
		listener.listenForCommand();
	} catch (Exception e) {}*/
        playerSave = new PlayerSave();
        socketListener.run();
    }
}
