package com.webcheckers.model;

/**
 * A class representing a Player in the Checkers model
 * @author John Licitra
 */
public class Player {
    /** The player's name **/
    private String name;

    /**
     * Construct a new player
     * @param name The name of the player
     */
    public Player(String name){
        this.name = name;
    }

    /**
     * Get the name of the player
     * @return the name of the player
     */
    public String getName(){
        return this.name;
    }
}
