package com.webcheckers.model;

/**
 * A class representing a model of a Checkers game
 * @author John Licitra
 */
public class CheckersGame {
    /** 2D Piece array representing the board state **/
    private Piece[][] board;
    /** Whose turn is it? True = Red, False = White **/
    private boolean currentPlayer;

    /**
     * An enum representing the various things that can be on a tile
     */
    enum Piece {
        EMPTY, RED, WHITE, RED_KING, WHITE_KING
    }

    /**
     * Generate new CheckersGame with starting board position and red to move
     */
    public CheckersGame() {
        this.currentPlayer = true;
        this.board = new Piece[8][8];
        for (int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board.length; x++) {
                if((y == 0 || y == 2) && x % 2 == 1){
                    this.board[y][x] = Piece.RED;
                }
                else if(y == 1 && x % 2 == 0){
                    this.board[y][x] = Piece.RED;
                }
                else if((y == 5 || y == 7) && x % 2 == 0){
                    this.board[y][x] = Piece.WHITE;
                }
                else if(y == 6 && x % 2 == 1){
                    this.board[y][x] = Piece.WHITE;
                }
                else{
                    this.board[y][x] = Piece.EMPTY;
                }
            }
        }
    }

    /**
     * Get the current board state
     * @return 2D array of Pieces representing the board
     */
    public Piece[][] getBoard(){
        return this.board;
    }

    /**
     * Return whose turn it is
     * @return true if it's red's turn, false if it's white's turn
     */
    public boolean getCurrentTurn(){
      return this.currentPlayer;
    }

    /**
     * Does the piece have valid jumps?
     * @param x X coordinate of the piece
     * @param y Y coordinate of the piece
     * @return An int array specifying which jumps are legal in form {nw, ne, se, sw}
     */
    private int[] jumpExists(int x, int y){
        int[] jumps = {0, 0, 0, 0};
        boolean isKing = (this.board[y][x] == Piece.WHITE_KING || this.board[y][x] == Piece.RED_KING);
        //TODO
        // Check the forward two tiles. If isKing, check back two tiles.

        return jumps;
    }

    /**
     * Validate the given move
     * @param sx X position of the piece to move
     * @param sy Y position of the piece to move
     * @param dx X position of the destination tile
     * @param dy Y position of the destination tile
     * @return true if the move is valid, else false
     */
    public boolean validateMove(int sx, int sy, int dx, int dy){
        // Validate coords on board
        if (sx > this.board.length || sy > this.board.length || sx < 0 || sy < 0
                || dx > this.board.length || dy > this.board.length || dx < 0 || dy < 0){
            // IntelliJ says this check can be simplified but it DOESN'T SAY HOW
            return false;
        }
        // Validate solution and destination are both black tiles
        else if (sx + sy % 2 == 0 || dx + dy % 2 == 0){
            return false;
        }
        // Validate tile has a piece on it
        else if (this.board[sy][sx] == Piece.EMPTY){
            return false;
        }
        // Validate moving piece is owned (red player)
        else if (this.currentPlayer && (this.board[sy][sx] == Piece.WHITE || this.board[sy][sx] == Piece.WHITE_KING)){
            return false;
        }
        // Validate moving piece is owned (white player)
        else if (!this.currentPlayer && (this.board[sy][sx] == Piece.RED || this.board[sy][sx] == Piece.RED_KING)){
            return false;
        }
        //TODO
        // Validate that piece is a valid move
        // First, we check if there is a valid jump. If there is a valid jump, the move cannot be simple
        // If there is a valid jump, validate only a valid jump. If there isn't, validate only a simple move

        return false;
    }

    /**
     * Debug function to print the board state to console
     */
    public void printBoard() {
        for (int y = 0; y < this.board.length; y++){
            for (int x = 0; x < this.board.length; x++){
                if (this.board[y][x] == Piece.EMPTY){
                    System.out.print("_");
                }
                else if (this.board[y][x] == Piece.RED){
                    System.out.print("r");
                }
                else if (this.board[y][x] == Piece.WHITE){
                    System.out.print("w");
                }
                else if (this.board[y][x] == Piece.RED_KING){
                    System.out.print("R");
                }
                else if (this.board[y][x] == Piece.WHITE_KING){
                    System.out.print("W");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Main function to test CheckersGame class
     * @param args
     */
    public static void main(String[] args){
        CheckersGame checkers = new CheckersGame();
        checkers.printBoard();
    }
}
