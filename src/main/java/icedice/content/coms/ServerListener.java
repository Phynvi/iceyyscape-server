package icedice.content.coms;

import java.net.*;
import java.io.*;

import icedice.Engine;
import icedice.players.*;

public class ServerListener extends Thread {
    private Socket socket = null;
    private Thread thread = new Thread(this);

    public ServerListener(Socket socket) {
        super("ServerListener");
        this.socket = socket;
        thread.start();
    }

    public void run() {
        PrintWriter out = null;
        PrintWriter allout = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;

            if ((inputLine = in.readLine()) != null) {
                for (Player p : Engine.players) {
                    if (p != null) {
                        p.frames.sendMessage(p, inputLine);
                    }
                }
                System.out.println(inputLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
