package icedice.content.coms;

import java.net.*;
import java.io.*;

import icedice.content.coms.*;

public class Coms extends Thread {

    private Thread thread = new Thread(this);

    public void start() {
        thread.start();
    }

    public void run() {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Starting Chat Server on port: 4444");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(-1);
        }

        while (listening) {
            try {
                new ServerListener(serverSocket.accept()).start();

                serverSocket.close();
            } catch (Exception e) {
            }
        }
    }
}
