package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.ArrayList;

/**
 * A server backend which maintains a list of logged-in players and in-progress games.
 * @author Bao Nguyen
 * @author Liam Obrochta
 */
public class GameServer {

    // Games in progress
    private ArrayList<Game> gamesInProgress = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();


    public ArrayList<Player> getPlayers () {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }


    public Game getGameFromPlayer(Player player){
        for (Game game : gamesInProgress){
            if (game.contains(player)){
                return game;
            }
        }
        return null;
    }

    public ArrayList getGamesInProgress() {
        return gamesInProgress;
    }

    public void addGame(Game game) {
        this.gamesInProgress.add(game);
    }


    //remove a game from the gamesInProgress collection.
    public void removeGame(Game game) {
        this.gamesInProgress.remove(game);
    }

}
