package com.webcheckers.appl;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the PlayerLobby component.
 *
 * @author John Licitra
 */
public class PlayerLobbyTest {


    private PlayerLobby CuT;

    public PlayerLobbyTest(){
        this.CuT = new PlayerLobby();
    }


    @Test
    public void playerListNotNull(){
        assertNotNull(CuT.getPlayers());
    }

    /**
     * Verify that PlayerLobby.numPlayers() does not fail and returns the same size as list.size()
     */
    @Test
    public void numPlayersWorks(){
        int num = CuT.numPlayers();
        ArrayList list = CuT.getPlayers();
        assertEquals(num, list.size());
    }

    @Test
    public void addPlayerCorrectly(){
        int size = CuT.getPlayers().size();
        Player player = new Player("Test", "Test");
        CuT.addPlayer(player);
        assertEquals(size + 1, CuT.getPlayers().size());
    }

    /**
     * Verify that PlayerLobby.getPlayers() does not fail
     */
    @Test
    public void getPlayersWorks(){
        Object list = CuT.getPlayers();
        assertTrue(list instanceof ArrayList);
    }

    /**
     * Verify that getPlayerFromUsername, getPlayerFromSessionID, addPlayerToServer, isPlayerOnline work
     * Also checks numPlayers before and after adding player
     */
    @Test
    public void testPlayerFuncs(){
        // Before adding player
        assertNull(CuT.getPlayer("Bob"));
        assertNull(CuT.getPlayer("h"));
        assertFalse(CuT.isPlayerOnline("Bob"));
        assertEquals(CuT.numPlayers(), 0);

        // After adding player
        CuT.addPlayerToLobby("Bob", "h");
        Player bob = CuT.getPlayer("Bob");
        assertNotNull(CuT.getPlayer("Bob"));
        assertNotNull(CuT.getPlayer("h"));
        assertEquals(bob.getName(), "Bob");
        assertEquals(bob.getSessionId(), "h");
        assertTrue(CuT.isPlayerOnline("Bob"));
        assertEquals(CuT.numPlayers(), 1);
    }
}
