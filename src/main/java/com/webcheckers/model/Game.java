package com.webcheckers.model;

/**
 * A class representing a game (containing a game model, players, and game ID)
 *
 * @author Liam Obrochta
 */
public class Game {
    /** The game model for the Game **/
    private CheckersGame game;
    /** The first player in the game **/
    private Player player1;
    /** The second player in the game **/
    private Player player2; //TODO: Which is Red and which is White?
    /** The unique ID for the game **/
    private String gameID;

    /**
     * Construct a new Game instance
     * @param player1 the first player
     * @param player2 the second player
     */
    private Game(Player player1, Player player2) {
        this.game = new CheckersGame();
        this.player1 = player1;
        this.player2 = player2;
        //this.gameID = gameID;
    }

    /**
     * Get the game model
     * @return game model
     */
    public CheckersGame getGame() {
        return game;
    }

    /**
     * Get the first player
     * @return player 1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Get the second player
     * @return player 2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Get the game ID
     * @return game ID
     */
    public String getGameID() {
        return gameID;
    }

    public boolean contains(Player player){
        if (this.player1.equals(player)|| (this.player2.equals(player))){
            return true;
        }
        return false;
    }
}
