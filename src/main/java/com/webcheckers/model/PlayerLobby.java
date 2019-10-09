package com.webcheckers.model;

import java.util.ArrayList;


/**
 * Keeps track of all online players and provides functions for accessing that information
 *
 * @author Liam Obrochta
 */
public class PlayerLobby {

    private static ArrayList<Player> players = new ArrayList<>();

    public boolean isPlayerOnline(String username){
        for (Player player : players){
            if (player.getName().equals(username)){
                return true;
            }
        }
        return false;
    }


    public static Player getPlayerFromSessionID(String sessionID){
        for (Player player : players){
            if (player.getSessionId().equals(sessionID)){
                return player;
            }
        }
        return null;
    }


    public void addPlayerToServer(String username, String sessionID){
        Player player = new Player(username, sessionID);
        addPlayer(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}
