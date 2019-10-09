package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.ArrayList;

/**
 * Keeps track of all online players and provides functions for accessing that information
 * Handling sign-in actions (checking existing users and add new users) using model.
 *
 * @author Liam Obrochta
 * @author Bao Nguyen
 * @author John Licitra
 */
public class PlayerLobby {

    private static GameServer server = new GameServer();

    public static int numPlayers(){
        return server.getPlayers().size();
    }

    public static ArrayList<Player> getPlayers() { return server.getPlayers(); }

    public static Game getGameFromPlayer(Player p) { return server.getGameFromPlayer(p); }

    public static boolean isPlayerOnline(String username) {
        for (Player player : server.getPlayers()) {
            if (player.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static Player getPlayerFromSessionID(String sessionID){
        for (Player player : server.getPlayers()){
            if (player.getSessionId().equals(sessionID)){
                return player;
            }
        }
        return null;
    }

    public static void addPlayerToServer(String username, String sessionID){
        Player player = new Player(username, sessionID);
        server.addPlayer(player);
    }

    // check if the username is valid.
    public static boolean isAlphaNumeric(String name){
        return (name!=null) && (name.matches("^[a-zA-Z0-9]*$") || name.contains(" "));
    }
}
