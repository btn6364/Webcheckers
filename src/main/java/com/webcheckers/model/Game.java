package com.webcheckers.model;

public class Game {

    private CheckersGame game;
    private Player player1;
    private Player player2;
    private String gameID;

    private Game(Player player1, Player player2) {
        this.game = new CheckersGame();
        this.player1 = player1;
        this.player2 = player2;
        this.gameID = gameID;
    }

    public CheckersGame getGame() {
        return game;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getGameID() {
        return gameID;
    }

    public boolean contains(Player player){
        if (this.player1.equals(player)|| (this.player2.equals(player))){
            return true;
        }
        return false;
    }

    public static Game createNewGame(Player player1, Player player2){
        return new Game(player1, player2);
    }


}
