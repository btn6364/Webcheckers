package com.webcheckers.model;

//import com.sun.tools.javac.comp.Check;
import org.junit.jupiter.api.Test;

/**
 * A Test module for Game class
 *
 * @author Angela Hudak
 */
public class GameTest {
    private CheckersGame checkGame;
    private  Player player1;
    private  Player player2;
    private final String gameID = "Test6969";


    /**
     * Test a new Game instance
     * @param p1 the first player
     * @param p2 the second player
     */
    @Test
    private void GameTest (Player p1, Player p2) {
        CheckersGame checkGame = new CheckersGame();
        this.player1 = p1;
        this.player2 = p2;

    }
    /**
     * Test the game model
     * @return game model
     */
    @Test
    private void getGameTest() {
        CheckersGame checkGame = new CheckersGame();
        assert (this.checkGame.equals(checkGame));

    }


    @Test
    private void containsTest(){
        String sessionId = player1.getSessionId();
        Player p1 = new Player(gameID, sessionId);
        Player p2 = new Player(gameID, sessionId);
        assert (this.player1.equals(p1) || (this.player2.equals(p2)));
    }


}
