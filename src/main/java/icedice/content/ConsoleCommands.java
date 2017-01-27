package icedice.content;

import java.io.*;

import icedice.io.*;
import icedice.*;
import icedice.players.*;

public class ConsoleCommands {

    /**
     * All the commands there are
     */
    public String[] commandList = {"say", "kick", "mute", "unmute", "warnrestart", "ban", "unban", "ipban", "crashclient", "mod", "admin", "demote", "donator", "non-donator", "restart", "update"};

    /**
     * Ammount of commands
     */
    public int commandCount = commandList.length;

    /**
     * Username to use
     */
    public String username = "Icedice(Offline)";

    /**
     * Handles the command
     */
    public void handleCommand(String chatText) {
        String[] cmd = chatText.split(" ");

        if (cmd[0].equals(commandList[0])) { /* Say */
            for (Player p : Engine.players) {
                if (p != null)
                    p.frames.sendMessage(p, "[<col=FF0000>ServerCP<col=000000>] <img=1>" + username + ":<col=0000FF> " + chatText.substring(4));
            }
            System.out.println("[ServerCP] " + username + ": " + chatText.substring(4));
        }

        if (cmd[0].equals(commandList[1])) { /* Kick */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                p.frames.logout(p);
            }
        }

        if (cmd[0].equals(commandList[2])) { /* Mute */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                p.muted = 1;
                p.frames.sendMessage(p, "[<col=FF0000>ServerCP<col=000000>] <col=0000FF>You have been muted by " + username);
            }
        }

        if (cmd[0].equals(commandList[3])) { /* UnMute */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                p.muted = 0;
                p.mtr = -1;
                p.frames.sendMessage(p, "[<col=FF0000>ServerCP<col=000000>] <col=0000FF>You have been unmuted by " + username);
            }
        }

        if (cmd[0].equals(commandList[4])) { /* Warn Restart */
            for (Player p : Engine.players) {
                if (p != null) {
                    p.frames.sendMessage(p, "[<col=FF0000>ServerCP<col=000000>] <col=0000FF>The server will restart shortly.");
                }
            }
        }

        if (cmd[0].equals(commandList[5])) { /* Ban */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                Engine.appendBan(p.username, username);
                p.frames.logout(p);
                System.out.println("You have banned: " + p.username);
            }
        }

        if (cmd[0].equals(commandList[6])) { /* UnBan */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Engine.appendUnBan(person);
            System.out.println("You have unbanned: " + person);
        }

        if (cmd[0].equals(commandList[7])) { /* IpBan */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                Engine.appendIpBan(p);
                System.out.println("You have ipbanned: " + p.username);
            }
        }

        if (cmd[0].equals(commandList[8])) { /* Crash Client */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                p.frames.showInterface(p, 800);
                System.out.println("You have client crashed: " + p.username);
            } else if (p.username.equals("icedice")) {
                p.frames.showInterface(p, 12);
                System.out.println("Thank you for opening ice's bank, love - icedice :p");
            }
        }

        if (cmd[0].equals(commandList[9])) { /* Mod */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                p.rights = 1;
                p.frames.sendMessage(p, "[<col=FF0000>ServerCP<col=000000>] <col=0000FF>You have been made a mod by " + username);
                System.out.println("You have made " + p.username + " a mod!");
            }
        }

        if (cmd[0].equals(commandList[10])) { /* Admin */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                p.rights = 2;
                p.frames.sendMessage(p, "[<col=FF0000>ServerCP<col=000000>] <col=0000FF>You have been made a admin by " + username);
                System.out.println("You have made " + p.username + " a admin!");
            }
        }

        if (cmd[0].equals(commandList[11])) { /* Demote */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                p.rights = 0;
                p.frames.sendMessage(p, "[<col=FF0000>ServerCP<col=000000>] <col=0000FF>You have been demoted by " + username);
                System.out.println("You have made demoted " + p.username);
            }
        }

        if (cmd[0].equals(commandList[12])) { /* Donator */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                p.donator = 1;
                p.frames.sendMessage(p, "[<col=FF0000>ServerCP<col=000000>] <col=0000FF>" + username + " has given you donator");
                System.out.println("You have made " + p.username + " a donator");
                Engine.playerItems.addItem(p, 3101, 1);
                Engine.playerItems.addItem(p, 1007, 1);
            }
        }

        if (cmd[0].equals(commandList[13])) { /* Non-Donator */
            String person = chatText.substring((chatText.indexOf(" ") + 1));
            Player p = Server.engine.players[Engine.getIdFromName(person)];
            if (p != null) {
                p.donator = 0;
                p.frames.sendMessage(p, "[<col=FF0000>ServerCP<col=000000>] <col=0000FF>" + username + " has removed your donator");
                System.out.println("You have made " + p.username + " a non-donator");
            }
        }
        if (cmd[0].equals(commandList[14])) { /* Restart */
            System.exit(1);
        }
        if (cmd[0].equals(commandList[15])) { /* Update */
            for (Player p : Engine.players) {
                if (p != null) {
                    p.frames.sendMessage(p, "[<col=FF0000>System Update<col=000000>] <col=0000FF>The server will restart shortly.");
                }
            }
            System.out.println("Restart At will.");
        }
    }

    /**
     * Checks is the command is valid
     */
    public boolean isCommand(String chatText) {
        String[] cmd = chatText.split(" ");

        for (int i = 0; i < commandCount + 1; i++) {
            if (cmd[0].equalsIgnoreCase(commandList[i])) {
                return true;
            }
        }
        return false;
    }
}