package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.GameSave;
import com.webcheckers.model.Player;
import com.webcheckers.ui.board.BoardView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

/**
 * A server backend which maintains a list of logged-in players and in-progress games.
 *
 * @author Bao Nguyen
 * @author Liam Obrochta
 * @author John Licitra
 */
public class GameServer {
    private ArrayList<Game> gamesInProgress = new ArrayList<>();
    private HashMap<Player, GameSave> savedGames = new HashMap<>();
    private int gameCounter = 1;

    /**
     * Create a new game for 2 players.
     *
     * @param player1 player 1.
     * @param player2 player 2.
     */
    public void newGame(Player player1, Player player2) {
        Game g = new Game(player1, player2, gameCounter);
        gameCounter++;
        addGame(g);
    }

    /**
     * Add a new Game to the list of games being played
     *
     * @param game the game to add
     */
    public void addGame(Game game) {
        this.gamesInProgress.add(game);
    }

    /**
     * Remove a game from the gameInProgress collection.
     *
     * @param game the game to remove.
     */
    public boolean removeGame(Game game) {
        return this.gamesInProgress.remove(game);
    }

    /**
     * Get the Game that a Player is playing
     *
     * @param player the player whose game will be returned
     * @return null if the player is not in a game, otherwise, their game
     */
    public Game getGame(Player player) {
        for (Game game : gamesInProgress) {
            if (game.contains(player) || game.getSpectators().contains(player)) {
                return game;
            }
        }
        return null;
    }

    public Game getGameByName(String name){
        for (Game game : gamesInProgress){
            if (game.getName().equals(name)){
                return game;
            }
        }
        return null;
    }

    /**
     * Get the ArrayList of games currently being played
     *
     * @return gamesInProgress
     */
    public ArrayList<Game> getGamesInProgress() {
        return gamesInProgress;
    }

    /**
     * Resign a player from the game.
     *
     * @param player the player in the game.
     */
    public boolean resignGame(Player player) {
        Game g = getGame(player);
        return g.setResign(player);

    }

    public void saveGame(Player saver, Game game){
        GameSave save = new GameSave(saver, game, game.getGameSave());
        this.savedGames.put(saver, save);
    }

    public ArrayList<GameSave> getSavesForPlayer(Player player){
        ArrayList<GameSave> games = new ArrayList<>();
        for (Player p: savedGames.keySet()){
            if (player.equals(p)){
                games.add(savedGames.get(player));
            }

        }
        return games;
    }



    /**
     * Game the game from the game ID>
     * @param gameID the unique ID of a game.
     * @return the game with an associated game id.
     */
    public Game getGameFromGameID(String gameID){
        for (Game game: gamesInProgress){
            if (game.getGameID().equals(gameID)){
                return game;
            }
        }
        return null;
    }


}
