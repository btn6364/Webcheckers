package com.webcheckers.model;

import java.util.ArrayList;

/**
 * A class representing a Player in the Checkers model
 * @author John Licitra
 * @author Bao Nguyen
 */
public class Player {
    /** The player's name **/
    private String name;
    /** The session ID for the player **/
    private String sessionId;

    /** All the games that the player has played.**/
    private ArrayList<Game> playedGames;
    /**
     * Construct a new player
     * @param name The name of the player
     */
    public Player(String name, String sessionId){
        this.name = name;
        this.sessionId = sessionId;
        this.playedGames = new ArrayList<Game>();
    }

    /**
     * Get the name of the player
     * @return the name of the player
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get the session ID of the player
     * @return the session ID of the player
     */
    public String getSessionId(){ // Gets the session ID of the player
        return this.sessionId; // Writing docstrings for getters is very tedious. But I do it anyway. Out of love. <3
    }

    public ArrayList<Game> getPlayedGames(){
        return this.playedGames;
    }

    /**
     * add a new game to the player.
     * @param game the game that the player wants to play.
     */
    public void addPlayedGames(Game game){
        this.playedGames.add(game);
    }
}
