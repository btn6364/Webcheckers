package com.webcheckers.model;

import com.webcheckers.appl.GameServer;
import com.webcheckers.ui.board.BoardView;
import com.webcheckers.util.Message;

import java.util.*;

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
    /** Check if the game is ended or not. **/
    private boolean gameEnded;
    /** The current active color piece. **/
    private String activeColor;

    private Player playerWithTurn;
    /** The winner of the game **/
    private Player winner;
    /** The loser of the game **/
    private Player loser;
    /** The board view of the game **/
    private BoardView boardView;
    /** The name of the game **/
    private String name;
    /** The list of all spectators **/
    private ArrayList<Player> spectators;
    /** Check unseen turn **/
    private boolean unseenTurn;
    /** The list of all game saves. **/
    private ArrayList<BoardView> gameSave = new ArrayList<>();


    /**
     * Construct a new Game instance
     * @param player1 the first player
     * @param player2 the second player
     */
    public Game(Player player1, Player player2, int id) {
        this.game = new CheckersGame();
        this.player1 = player1;
        this.player2 = player2;
        this.resigned = false;
        this.playerWithTurn = player1;
        this.activeColor = "RED";
        this.name = player1.getName() + " vs " + player2.getName();
        this.boardView = new BoardView(game);
        this.gameID = String.valueOf(id);
        this.unseenTurn = false;
        this.spectators = new ArrayList<Player>();
        this.gameSave.add(this.boardView);
    }


    /**
     * Get game ID.
     * @return the game ID.
     */
    public String getGameID(){
        return this.gameID;
    }

    public void setGameID(String gameID){
        this.gameID = gameID;
    }

    public boolean backupMove(Player player){
        if (game.getMoves() != null){
            game.getMoves().pop();
            return true;
        }
        return false;
    }

    /**
     * Submit move
     * @param player the player
     * @return the message whether or not the move was submitted.
     */
    public Message submitMove(Player player){
        if (player.equals(this.playerWithTurn)) {
            game.submitMove();
            changePlayerWithTurn();
            this.unseenTurn = true;
            this.boardView = new BoardView(game);
            this.gameSave.add(boardView);
            return Message.info("Move submitted successfully!");
        }
        return Message.error("Error while submitting move!");
    }

    /**
     * End the game and save it.
     * @param winner the winner
     * @param loser the loser
     */
    public void endGame(Player winner, Player loser){
        this.gameEnded = true;
        this.winner = winner;
        this.loser = loser;

        GameServer.saveGame(this);
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
     * @param resigner the resignation state of the game.
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

    /**
     * Get the name of the game
     * @return the name of the game.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get the board view of the game.
     * @return the board view of the game.
     */
    public BoardView getBoardView(){
        return this.boardView;
    }

    /**
     * Change player's turn
     */
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

    /**
     * Add new spectator to the collection
     * @param spectator the new spectator
     */
    public void addSpectator(Player spectator){
        this.spectators.add(spectator);
    }

    /**
     * Remove a spectator from the collection.
     * @param spectator the spectator who is leaving the game.
     */
    public void removeSpectator(Player spectator){
        this.spectators.remove(spectator);
    }

    /**
     * Get the list of all spectators.
     * @return the list of all spectators.
     */
    public ArrayList<Player> getSpectators(){
        return this.spectators;
    }

    /**
     * Get the winner of the game.
     * @return the winner of the game.
     */
    public Player getWinner(){
        return this.winner;
    }

    /**
     * Get the loser of the game.
     * @return the loser of the game.
     */
    public Player getLoser(){
        return this.loser;
    }

    /**
     * Get current player
     * @return the player.
     */
    public Player getPlayerWithTurn(){
        return this.playerWithTurn;
    }

    /**
     * Get the current active color.
     * @return the current active color.
     */
    public String getActiveColor(){
        return this.activeColor;
    }

    /**
     * Check if the game is over.
     * @return true if it is and false otherwise.
     */
    public boolean isGameEnded(){
        return this.gameEnded;
    }

    /**
     * Get the unseen turn.
     * @return the unseen turn.
     */
    public boolean isUnseenTurn(){
        return this.unseenTurn;
    }

    /**
     * Get the game save.
     * @return the game save.
     */
    public ArrayList<BoardView> getGameSave(){
        return gameSave;
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
