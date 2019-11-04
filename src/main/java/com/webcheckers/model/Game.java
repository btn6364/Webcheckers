package com.webcheckers.model;

import com.webcheckers.ui.board.BoardView;

/**
 * A class representing a game (containing a game model, players, and game ID)
 *
 * @author Liam Obrochta
 * @author Bao Nguyen
 */
public class Game {
    /** The game model for the Game **/
    private CheckersGame game;
    /** The first player in the game **/
    private Player player1; //Player Color: RED
    /** The second player in the game **/
    private Player player2; //Player Color: WHITE
    /** The unique ID for the game **/
    private String gameID;
    /** check if the game is resigned or not.**/
    private boolean resigned;


    private BoardView boardView;
    /**
     * Construct a new Game instance
     * @param player1 the first player
     * @param player2 the second player
     */
    public Game(Player player1, Player player2) {
        this.game = new CheckersGame();
        this.player1 = player1;
        this.player2 = player2;
        this.resigned = false;

        this.boardView = new BoardView(game);
    }


    public boolean validateMove(Move move){
        int sx = move.getInitialPosition().getCell();
        int sy = move.getInitialPosition().getRow();
        int dx = move.getFinalPosition().getCell();
        int dy = move.getFinalPosition().getRow();
        if (game.validateMove(sx, sy, dx, dy)){
            return true;
        }
        return false;
    }

    public boolean makeMove(Move move){
        return false;
    }

    /**
     * Get the current state of the game.
     * @return true if the game has been resigned and false otherwise.
     */
    public boolean getResign(){
        return resigned;
    }

    /**
     * Set the resignation state of the game.
     * @param resigned the resignation state of the game.
     */
    public void setResign(boolean resigned){
        this.resigned = resigned;
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

    public BoardView getBoardView(){
        return this.boardView;
    }


    /**
     * check if a player is in a game.
     * @param player the player to check.
     * @return true if this player is in the game and false otherwise.
     */
    public boolean contains(Player player) {
        if (this.player1.equals(player) || (this.player2.equals(player))) {
            return true;
        }
        return false;
    }
}
