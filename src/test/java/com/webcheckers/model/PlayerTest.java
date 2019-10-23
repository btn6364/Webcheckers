package com.webcheckers.model;


import org.junit.jupiter.api.Test;

/**
 * Test module for a Player object.
 * @author Sidney Mitchell <sm5069@rit.edu>
 *
 * */
public class PlayerTest {

    private final String name = "TestName";
    private final String sessionID = "TestID127";

    /**Test to see if a new player can be made.*/
    @Test
    public void constructNewPlayer(){
        Player pl = new Player(name, sessionID);
    }

    /**Test that the player's name is properly set and can be received accurately.*/
    @Test
    public void checkPlayerName(){
        Player pl = new Player(name, sessionID);
        String playerName = pl.getName();
        assert(playerName.equals(name));
    }

    /**
     * Check to see if the player's ID can be received and that it is accurate.
     * */
    @Test
    public void checkPlayerID(){
        Player pl = new Player(name, sessionID);
        String playerID = pl.getSessionId();

        assert(playerID.equals(sessionID));
    }

}
