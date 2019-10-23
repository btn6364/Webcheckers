package com.webcheckers.appl;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerLobbyTest {

    /**
     * Verify that PlayerLobby.numPlayers() does not fail and returns the same size as list.size()
     */
    @Test
    public void numPlayersWorks(){
        int num = PlayerLobby.numPlayers();
        ArrayList list = PlayerLobby.getPlayers();
        assertEquals(num, list.size());
    }

    /**
     * Verify that PlayerLobby.getPlayers() does not fail
     */
    @Test
    public void getPlayersWorks(){
        Object list = PlayerLobby.getPlayers();
        assertTrue(list instanceof ArrayList);
    }

    /**
     * Verify that getPlayerFromUsername, getPlayerFromSessionID, addPlayerToServer, isPlayerOnline work
     * Also checks numPlayers before and after adding player
     */
    @Test
    public void testPlayerFuncs(){
        // Before adding player
        assertNull(PlayerLobby.getPlayerFromUsername("Bob"));
        assertNull(PlayerLobby.getPlayerFromSessionID("h"));
        assertFalse(PlayerLobby.isPlayerOnline("Bob"));
        assertEquals(PlayerLobby.numPlayers(), 0);

        // After adding player
        PlayerLobby.addPlayerToServer("Bob", "h");
        Player bob = PlayerLobby.getPlayerFromUsername("Bob");
        assertNotNull(PlayerLobby.getPlayerFromUsername("Bob"));
        assertNotNull(PlayerLobby.getPlayerFromSessionID("h"));
        assertEquals(bob.getName(), "Bob");
        assertEquals(bob.getSessionId(), "h");
        assertTrue(PlayerLobby.isPlayerOnline("Bob"));
        assertEquals(PlayerLobby.numPlayers(), 1);
    }
}
