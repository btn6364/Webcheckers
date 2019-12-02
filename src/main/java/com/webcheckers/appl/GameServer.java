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
    private static HashMap<Player, ArrayList<GameSave>> savedGames = new HashMap<Player, ArrayList<GameSave>>();
    private int gameCounter = 1;


//    public HashMap<Player, GameSave> getSavedGames(){
//        return this.savedGames;
//    }
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

    /**
     * Save the game for each player.
     * @param game
     */
    public static void saveGame(Game game){
        Player saver1 = game.getPlayer1();
        Player saver2 = game.getPlayer2();
        GameSave save = new GameSave(game, game.getGameSave());
        ArrayList<GameSave> gameSaves1 = savedGames.get(saver1);
        ArrayList<GameSave> gameSaves2 = savedGames.get(saver2);
        gameSaves1.add(save);
        gameSaves2.add(save);
        savedGames.put(saver1, gameSaves1);
        savedGames.put(saver2, gameSaves2);
    }

    public ArrayList<GameSave> getSavesForPlayer(Player player){

        if (savedGames.containsKey(player)){
            return savedGames.get(player);
        } else {
            savedGames.put(player, new ArrayList<>());
            return savedGames.get(player);
        }

    }

    public GameSave getSaveFromID(String id, Player player){
        ArrayList<GameSave> saves = getSavesForPlayer(player);
        for (GameSave save : saves){
            if (save.getGameID().equals(id)){
                return save;
            }
        }
        return null;
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
