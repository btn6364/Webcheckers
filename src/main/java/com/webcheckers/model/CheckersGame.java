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
     * @return An int array specifying which jumps are legal in form {nw, ne, se, sw, total}
     */
    private boolean[] jumpExists(int x, int y){
        boolean[] jumps = {false, false, false, false, false};
        boolean isKing = (this.board[y][x] == Piece.WHITE_KING || this.board[y][x] == Piece.RED_KING);
        boolean isRed = (this.board[y][x] == Piece.RED || this.board[y][x] == Piece.RED_KING);

        if (isRed || isKing){
            //higher y
            if (y < 6){
                if (x > 1){
                    // sw
                    // First check if destination is empty
                    jumps[3] = this.board[y+2][x-2] == Piece.EMPTY;
                    // Then check if the piece being jumped over is the opposite color (skip check if destination is full)
                    if(isRed && jumps[3]){
                        jumps[3] = this.board[y+1][x-1] == Piece.WHITE || this.board[y+1][x-1] == Piece.WHITE_KING;
                    }
                    else if (jumps[3]){
                        jumps[3] = this.board[y+1][x-1] == Piece.RED || this.board[y+1][x-1] == Piece.RED_KING;
                    }
                }
                if (x < 6){
                    // se
                    // First check if destination is empty
                    jumps[2] = this.board[y+2][x+2] == Piece.EMPTY;
                    // Then check if the piece being jumped over is the opposite color (skip check if destination is full)
                    if(isRed && jumps[2]){
                        jumps[2] = this.board[y+1][x+1] == Piece.WHITE || this.board[y+1][x+1] == Piece.WHITE_KING;
                    }
                    else if (jumps[2]){
                        jumps[2] = this.board[y+1][x+1] == Piece.RED || this.board[y+1][x+1] == Piece.RED_KING;
                    }
                }
            }
        }
        if (!isRed || isKing){
            //lower y
            if (y > 1){
                if (x > 1){
                    // nw
                    // First check if destination is empty
                    jumps[0] = this.board[y-2][x-2] == Piece.EMPTY;
                    // Then check if the piece being jumped over is the opposite color
                    if(isRed && jumps[0]){
                        jumps[0] = this.board[y-1][x-1] == Piece.WHITE || this.board[y-1][x-1] == Piece.WHITE_KING;
                    }
                    else if (jumps[0]){
                        jumps[0] = this.board[y-1][x-1] == Piece.RED || this.board[y-1][x-1] == Piece.RED_KING;
                    }
                }
                if (x < 6){
                    // ne
                    // First check if destination is empty
                    jumps[1] = this.board[y-2][x+2] == Piece.EMPTY;
                    // Then check if the piece being jumped over is the opposite color
                    if(isRed && jumps[1]){
                        jumps[1] = this.board[y-1][x+1] == Piece.WHITE || this.board[y-1][x+1] == Piece.WHITE_KING;
                    }
                    else if (jumps[1]){
                        jumps[1] = this.board[y-1][x+1] == Piece.RED || this.board[y-1][x+1] == Piece.RED_KING;
                    }
                }
            }
        }
        // jumps[4] signifies that one of the jumps is valid
        for (boolean b : jumps){if (b){jumps[4] = true;}}

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
        // Validate destination tile has no piece on it
        else if (this.board[dy][dx] != Piece.EMPTY){
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
        // Validate that destination is a valid move
        boolean[] jumps = jumpExists(sx, sy);
        if (jumps[4]){
            // If there is a valid jump, only moves which jump are valid
            if(dy == sy-2 && dx == sx-2){
                return jumps[0];
            }
            else if(dy == sy-2 && dx == sx+2){
                return jumps[1];
            }
            else if(dy == sy+2 && dx == sx+2){
                return jumps[2];
            }
            else if(dy == sy+2 && dx == sx-2){
                return jumps[3];
            }
        }
        else{
            // If there isn't a valid jump, only simple moves are valid
            if((Math.abs(dx-sx) == 1 ) && (Math.abs(dy-sy) == 1)){
                return true;
            }
        }
        // We've checked every possible legal move, so if we get to this point, return false
        return false;
    }

    public void attemptMove(){
        //Ask the player where they'd like to move their piece
        //Get instruction from the player and then use validateMove to
        //check and see if it is a valid move.
    }

    /**
     * Debug function to print the board state to console
     */
    private void printBoard() {
        System.out.print(" 01234567\n");
        for (int y = 0; y < this.board.length; y++){
            System.out.print(y);
            for (int x = 0; x < this.board.length; x++){
                printTile(x,y);
            }
            System.out.print("\n");
        }
    }

    /**
     * Debug function to assign a status to a given tile
     * @param x X coordinate of tile to assign to
     * @param y Y coordinate of tile to assign to
     * @param type Status to assign
     */
    private void assignToTile(int x, int y, Piece type){
        this.board[y][x] = type;
    }

    /**
     * Debug function to clear the board
     */
    private void clearBoard(){
        for (int y=0; y<this.board.length; y++){
            for (int x=0; x<this.board.length; x++){
                this.board[y][x] = Piece.EMPTY;
            }
        }
    }

    /**
     * Print a tile, for printBoard and printValidMoves
     * @param x X coordinate of the tile
     * @param y Y coordinate of the tile
     */
    private void printTile(int x, int y){
        if (this.board[y][x] == Piece.EMPTY){
            System.out.print("-");
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

    /**
     * Debug function to display valid moves for the given tile
     * @param tx X coordinate of the tile
     * @param ty Y coordinate of the tile
     */
    private void printValidMoves(int tx, int ty){
        System.out.println(" 01234567");
        for (int y = 0; y < this.board.length; y++){
            System.out.print(y);
            for (int x = 0; x < this.board.length; x++){
                if(validateMove(tx, ty, x, y)){
                    System.out.print("X");
                }
                else{
                    printTile(x, y);
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Main function to test CheckersGame class
     * @param args Main takes no args
     */
    public static void main(String[] args){
        CheckersGame checkers = new CheckersGame();
        checkers.clearBoard();
        checkers.assignToTile(1,4, Piece.RED_KING);
        checkers.assignToTile(2,3, Piece.WHITE_KING);
        checkers.assignToTile(2,5, Piece.WHITE);
        checkers.assignToTile(5,6, Piece.RED_KING);
        checkers.assignToTile(6,1, Piece.RED);
        checkers.printValidMoves(1,4);
        System.out.print("\n");
        checkers.printValidMoves(5,6);
        System.out.print("\n");
        checkers.printValidMoves(6,1);
        System.out.print("\n");
        checkers.printBoard();
    }
}
