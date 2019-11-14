package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.ArrayList;

/**
 * A server backend which maintains a list of logged-in players and in-progress games.
 * @author Bao Nguyen
 * @author Liam Obrochta
 * @author John Licitra
 */
public class GameServer {
    private ArrayList<Game> gamesInProgress = new ArrayList<>();

    /**
     * Add a new Game to the list of games being played
     * @param game the game to add
     */
    public void addGame(Game game) {
        this.gamesInProgress.add(game);
    }

    /**
     * Remove a game from the gameInProgress collection.
     * @param game the game to remove.
     */
    public void removeGame(Game game) {
        this.gamesInProgress.remove(game);
    }

    /**
     * Get the Game that a Player is playing
     * @param player the player whose game will be returned
     * @return null if the player is not in a game, otherwise, their game
     */
    public Game getGame(Player player) {
        for (Game game : gamesInProgress){
            if (game.contains(player)){
                return game;
            }
        }
        return null;
    }

    /**
     * Get the ArrayList of games currently being played
     * @return gamesInProgress
     */
    public ArrayList getGamesInProgress() {
        return gamesInProgress;
    }

    /**
     * Resign a player from the game.
     * @param player the player in the game.
     */
    public void resignGame(Player player){
        Game g = getGame(player);
        g.setResign(true);
        removeGame(g);
    }



}
