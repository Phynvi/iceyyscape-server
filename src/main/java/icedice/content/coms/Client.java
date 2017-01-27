package icedice.content.coms;

import java.io.*;
import java.net.*;

public class Client {

    private static PrintWriter out = null;
    private static Socket socket = null;

    public static void Send(String s) throws IOException {

        BufferedReader in = null;
        String ip = "127.0.0.1";
        int port = 4445;

        try {
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Bad host: " + ip + ":" + port + ".");
        } catch (IOException e) {
            System.out.println("Error conencting to: " + ip + ":" + port + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println(s);
    }
}
