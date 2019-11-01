package com.webcheckers.model;

/**
 * A class representing a Player in the Checkers model
 * @author John Licitra
 */
public class Player {
    /** The player's name **/
    private String name;
    /** The session ID for the player **/
    private String sessionId;


    /**
     * Construct a new player
     * @param name The name of the player
     */
    public Player(String name, String sessionId){
        this.name = name;
        this.sessionId = sessionId;
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

}
