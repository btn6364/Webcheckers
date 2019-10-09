package com.webcheckers.model;

import java.util.ArrayList;

/**
 * A backend-server which maintains a list of logged-in players and in-progress games.
 * @author Bao Nguyen
 * @author Liam Obrochta
 */
public class GameServer {
    //list of current players.
    private ArrayList<Player> players = new ArrayList<>();

    // list of in-progress games.
    private ArrayList<Game> gamesInProgress = new ArrayList<>();


    public boolean isPlayerOnline(String username){
        for (Player player : players){
            if (player.getName().equals(username)){
                return true;
            }
        }
        return false;
    }

    public Player getPlayerFromSessionID(String sessionID){
        for (Player player : players){
            if (player.getSessionId().equals(sessionID)){
                return player;
            }
        }
        return null;
    }

    public Game getGameFromPlayer(Player player){
        for (Game game : gamesInProgress){
            if (game.contains(player)){
                return game;
            }
        }
        return null;
    }

    public void addPlayerToServer(String username, String sessionID){
        Player player = new Player(username, sessionID);
        addPlayer(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public ArrayList getGamesInProgress() {
        return gamesInProgress;
    }

    public void addGame(Game game) {
        this.gamesInProgress.add(game);
    }
}
