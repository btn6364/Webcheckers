package com.webcheckers.model;

import java.util.ArrayList;

/**
 * A backend-server which maintains a list of logged-in players and in-progress games.
 * @author Bao Nguyen
 * @author Liam Obrochta
 */
public class GameServer {

    // list of in-progress games.
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
  

}
