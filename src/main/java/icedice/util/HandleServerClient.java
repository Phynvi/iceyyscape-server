
package icedice.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import icedice.net.SocketListener;

public class HandleServerClient {

    Socket client;
    DataInputStream in;
    DataOutputStream out;

    private SocketListener socketl;

    public boolean varifyClient() {
        if (socketl.clientCommand.equals(777)) {
            return true;
        }
        return false;
    }

}
