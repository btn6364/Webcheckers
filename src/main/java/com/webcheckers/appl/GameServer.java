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
    private ArrayList<Player> players = new ArrayList<>();

    /**
     * Get the ArrayList of players currently connected to the server
     * @return players list
     */
    public ArrayList<Player> getPlayers () {
        return players;
    }

    /**
     * Add a Player object to the server
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Remove a Player object from the server
     * @param player the player to remove
     */
    public void removePlayer(Player player) {
        this.players.remove(player);
    }
    
    /**
     * Get the Game that a Player is playing
     * @param player the player whose game will be returned
     * @return null if the player is not in a game, otherwise, their game
     */
    public Game getGameFromPlayer(Player player) {
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
     * Add a new Game to the list of games being played
     * @param game the game to add 
     */
    public void addGame(Game game) {
        this.gamesInProgress.add(game);
    }


    //remove a game from the gamesInProgress collection.

    /**
     * Remove a game from the gameInProgress collection.
     * @param game the game to remove.
     */
    public void removeGame(Game game) {
        this.gamesInProgress.remove(game);
    }

}
