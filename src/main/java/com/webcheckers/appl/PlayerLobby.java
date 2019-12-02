package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.GameSave;
import com.webcheckers.model.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Keeps track of all online players and provides functions for accessing that information
 * Handling sign-in actions (checking existing users and add new users) using model.
 *
 * @author Liam Obrochta
 * @author Bao Nguyen
 * @author John Licitra
 */
public class PlayerLobby {

    private ArrayList<Player> players = new ArrayList<Player>();

    private ArrayList<Player> replayers = new ArrayList<>();
    private ArrayList<Player> spectators = new ArrayList<>();

    /**
     * Retrieves a player based on the supplied UUID or Player name
     * @param identifier
     * @return
     */
    public Player getPlayer(String identifier){
        for (Player player : players){
            if (player.getSessionId().equals(identifier) || player.getName().equals(identifier)){
                return player;
            }
        }
        return null;
    }


    /**
     * Get the ArrayList of players currently connected to the server
     * @return players list
     */
    public ArrayList<Player> getPlayers () {
        return players;
    }

    /**
     * Add a Player object to the server
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Remove a Player object from the server
     * @param player the player to remove
     */
    public void removePlayer(Player player) {
        this.players.remove(player);
    }


    /**
     * Get the number of players signed in.
     * @return the number of players signed in.
     */
    public int numPlayers(){
        return players.size();
    }


    /**
     * Check if the player is online.
     * @param username the username of the player.
     * @return true if the player is online and false otherwise.
     */
    public boolean isPlayerOnline(String username) {
        for (Player player : players) {
            if (player.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add the player to the server.
     * @param username the username of the player.
     * @param sessionID the session ID of the player.
     */
    public void addPlayerToLobby(String username, String sessionID){
        Player player = new Player(username, sessionID);
        addPlayer(player);
    }

    /**
     * Remove a player's session ID.
     * @param identifier the session ID of a player.
     */
    public void removePlayer(String identifier){
        removePlayer(getPlayer(identifier));
    }

    /**
     * Add a replayer
     * @param player the replayer
     */
    public void addReplayer(Player player){
        this.replayers.add(player);
    }

    /**
     * Remove a replayer from the collection.
     * @param player the replayer who is leaving the game.
     */
    public void removeReplayer(Player player){
        this.replayers.remove(player);
    }

    /**
     * Add a new spectator to the collection.
     * @param player the spectator.
     */
    public void addSpectator(Player player){
        this.spectators.add(player);
    }

    /**
     * Remove a spectator from the collection.
     * @param player the spectator who is leaving the game.
     */
    public void removeSpectator(Player player){
        this.spectators.remove(player);
    }

    /**
     * Check if the player is watching a game or replaying a game
     * @param player the player
     * @return true if he is and false otherwise.
     */
    public boolean isPlayerAvailable(Player player){
        if (this.replayers.contains(player) || this.spectators.contains(player)){
            return false;
        }
        return true;
    }


}
