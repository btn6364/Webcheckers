package com.webcheckers.model;

import com.webcheckers.ui.board.BoardView;
import com.webcheckers.util.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

    private boolean gameEnded;

    private String activeColor;

    private Player playerWithTurn;

    private Player winner;

    private Player loser;

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
        this.playerWithTurn = player1;
        this.activeColor = "RED";

        this.boardView = new BoardView(game);
    }

    public boolean backupMove(Player player){
        if (game.getMoves() != null){
            game.getMoves().pop();
            return true;
        }
        return false;
    }


    public Message submitMove(Player player){
        if (player.equals(this.playerWithTurn)) {
            game.submitMove();
            changePlayerWithTurn();
            this.boardView = new BoardView(game);
            return Message.info("Move submitted successfully!");
        }
        return Message.error("Error while submitting move!");
    }

    public void endGame(Player winner, Player loser){
        this.gameEnded = true;
        this.winner = winner;
        this.loser = loser;
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
    public boolean setResign(Player resigner){
        this.resigned = true;
        Player winner;
        if (resigner.equals(player1)){
            winner = player2;
        } else {
            winner = player1;
        }
        Player loser = resigner;


        endGame(winner, loser);
        return true;
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

    private void changePlayerWithTurn(){
        Player active = this.playerWithTurn;
        if (active.equals(player1)){
            this.activeColor = "WHITE";
            this.playerWithTurn = this.player2;
        } else {
            this.activeColor = "RED";
            this.playerWithTurn = this.player1;
        }
    }

    public Player getWinner(){
        return this.winner;
    }

    public Player getLoser(){
        return this.loser;
    }

    public Player getPlayerWithTurn(){
        return this.playerWithTurn;
    }

    public String getActiveColor(){
        return this.activeColor;
    }

    public boolean isGameEnded(){
        return this.gameEnded;
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
