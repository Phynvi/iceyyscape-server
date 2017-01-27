
package icedice.net;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

import icedice.Config;
import icedice.Server;
import icedice.Engine;
import icedice.util.Misc;
import icedice.util.HandleServerClient;

public class SocketListener extends ConnectionManager {

    /**
     * Set true if the socket listener should run.
     */
    public static boolean serverRunning = true;

    /**
     * Server socket which intercepts incoming connections.
     */
    public ServerSocket serverChannel;

    /**
     * Client socket
     */
    public Socket socket = null;

    /**
     * Array of all banned addresses.
     */
    private String[] bannedHosts = new String[500];

    /**
     * Handles Input from "Server_Client"
     */
    public String clientCommand;
    private DataInputStream in;

    /**
     * Constructs a new SocketListener.
     *
     * @param port The port to run the server on.
     */
    public SocketListener(int port) throws Exception {
        serverChannel = new ServerSocket(port, 100);
        Misc.println("Starting server on port: " + port);
        loadBannedHosts();
    }

    /**
     * The thread's process.
     * <p>The serverChannel listens for incoming connections, and accepts them.
     * The timeout is set to 2 to prevent i/o blocking.
     */
    public void run() {
        //Socket socket = null;

        while (serverRunning) {
            try {
                socket = serverChannel.accept();
                socket.setSoTimeout(5);
                socket.setTcpNoDelay(true);
                appendConnection(getAddress(socket));
                Misc.println("Incoming Connection: " + getAddress(socket));
                /*in = new DataInputStream(socket.getInputStream());
				clientCommand = in.readUTF();
				System.out.println(clientCommand);
				Server.serverClient.varifyClient();
				if (!Server.serverClient.varifyClient()) {
					socket.close();
					System.out.println("Invaild Server Client");
				}*/
                if (checkBanned(getAddress(socket))) {
                    socket.close();
                    System.out.println("Banned");
                    continue;
                }
                int id = getFreeId();
                sockets[id] = socket;
                Server.engine.addConnection(socket, id);
            } catch (Exception e) {
            }
        }
    }

    public void sendToClient(String message) throws IOException {
        DataOutputStream out;
        socket = serverChannel.accept();
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(message);
    }

    /**
     * Logs every connection, extremely vital for reporting connections that are trying to
     * flood the server.
     */
    public void appendConnection(String host) {
        Engine.fileManager.appendData("connections/" + host + ".txt", "[" + Misc.getDate() + "] " + host + ": connection recieved.");
    }

    /**
     * Checks to make sure the host isnt IP banned.
     */
    public boolean checkBanned(String hostName) {
        if (hostName == null) {
            return true;
        }
        for (int i = 0; i < bannedHosts.length; i++) {
            if (bannedHosts[i] != null && hostName.equals(bannedHosts[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loads all banned hosts at startup.
     */
    private void loadBannedHosts() {
        int index = 0;

        try {
            BufferedReader in = new BufferedReader(new FileReader(Config.getPropertiesValue("BANNED_HOST_PATH")));
            String loggedIPs = null;

            while ((loggedIPs = in.readLine()) != null) {
                bannedHosts[index] = loggedIPs;
                index++;
            }
        } catch (Exception e) {

            Misc.println("Error loading banned hosts list.");
        }
    }
}
