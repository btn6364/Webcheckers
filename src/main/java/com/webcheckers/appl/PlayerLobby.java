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

    /**
     * Get the number of players signed in.
     * @return the number of players signed in.
     */
    public static int numPlayers(){
        return server.getPlayers().size();
    }

    /**
     * Get all the players signed in.
     * @return the list of all players signed in.
     */
    public static ArrayList<Player> getPlayers() { return server.getPlayers(); }

    /**
     * Get a game from a specific player.
     * @param p the player.
     * @return the game that the player is in.
     */
    public static Game getGameFromPlayer(Player p) { return server.getGameFromPlayer(p); }

    /**
     * Check if the player is online.
     * @param username the username of the player.
     * @return true if the player is online and false otherwise.
     */
    public static boolean isPlayerOnline(String username) {
        for (Player player : server.getPlayers()) {
            if (player.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a player from his/her session ID.
     * @param sessionID the session ID of a player.
     * @return the player with the session ID provided.
     */
    public static Player getPlayerFromSessionID(String sessionID){
        for (Player player : server.getPlayers()){
            if (player.getSessionId().equals(sessionID)){
                return player;
            }
        }
        return null;
    }

    /**
     * Get the player from a username.
     * @param username A username of a player.
     * @return the Player with a match username.
     */
    public static Player getPlayerFromUsername(String username){
        for (Player player : server.getPlayers()){
            if (player.getName().equals(username)){
                return player;
            }
        }
        return null;
    }

    /**
     * Add the player to the server.
     * @param username the username of the player.
     * @param sessionID the session ID of the player.
     */
    public static void addPlayerToServer(String username, String sessionID){
        Player player = new Player(username, sessionID);
        server.addPlayer(player);
    }

    /**
     * Remove a player's session ID.
     * @param sessionID the session ID of a player.
     */
    public static void removePlayerBySessionID(String sessionID){
        Player player = PlayerLobby.getPlayerFromSessionID(sessionID);
        server.removePlayer(player);
    }

    /**
     * Create a new game for 2 players.
     * @param player1 player 1.
     * @param player2 player 2.
     */
    public static void newGame(Player player1, Player player2){
        Game g = new Game(player1, player2);
        server.addGame(g);
    }

    /**
     * Resign a player from the game.
     * @param player the player in the game.
     */
    public static void resignGame(Player player){
        Game g = getGameFromPlayer(player);
        g.setResign(true);
        server.removeGame(g);
    }

    /**
     * Check whether a username is valid or not.
     * @param name the name passed in.
     * @return true if the name is valid and false otherwise.
     */
    public static boolean isAlphaNumeric(String name){
        return (name!=null) && (name.matches("^[a-zA-Z0-9\\s]+$") && !name.matches("\\s+"));
    }
}
