package com.webcheckers.appl;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.ui.TemplateEngineTester;
import org.junit.jupiter.api.Test;
import spark.Session;

import java.util.ArrayList;

public class GameServerTest {

    private GameServer CuT;

    public GameServerTest(){
        this.CuT = new GameServer();
    }

    @Test
    public void addPlayerCorrectly(){
        int size = CuT.getPlayers().size();
        Player player = new Player("Test", "Test");
        CuT.addPlayer(player);
        assertEquals(size + 1, CuT.getPlayers().size());
    }

    @Test
    public void addGameCorrectly(){
        int size = CuT.getGamesInProgress().size();
        Player player1 = new Player("Test", "Test");
        Player player2 = new Player("Test1", "Test1");
        Game game = new Game(player1, player2);
        CuT.addGame(game);

        assertEquals(size + 1, CuT.getGamesInProgress().size());
    }

    @Test
    public void playerListNotNull(){
        assertNotNull(CuT.getPlayers());
    }

    @Test
    public void gameListNotNull(){
        assertNotNull(CuT.getGamesInProgress());
    }

    @Test
    public void getsCorrectGameByPlayer(){
        Player player1 = new Player("Test", "Test");
        Player player2 = new Player("Test1", "Test1");
        Game game = new Game(player1, player2);
        CuT.addGame(game);

        assertEquals(game, CuT.getGameFromPlayer(player1));
    }

}
