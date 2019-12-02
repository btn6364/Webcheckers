package com.webcheckers.model;
import java.util.Stack;

/**
 * A class representing a model of a Checkers game
 * @author John Licitra
 * @author Bao Nguyen
 */
public class CheckersGame {
    /** 2D Piece array representing the board state **/
    private Piece[][] board;
    /** Whose turn is it? True = Red, False = White **/
    private boolean currentPlayer;
    /** Has the game been won? **/
    private boolean gameWon;
    /** Unpushed moves **/
    private Stack<Move> moves = new Stack<>();

    /**
     * An enum representing the various things that can be on a tile
     */
    public enum Piece {
        EMPTY, RED, WHITE, RED_KING, WHITE_KING
    }
    
    /**
     * Generate new CheckersGame with starting board position and red to move
     */
    public CheckersGame() {
        this.currentPlayer = true;
        this.gameWon = false;
        this.board = new Piece[8][8];
        for (int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board.length; x++) {
                if((y == 0 || y == 2) && x % 2 == 1){
                    this.board[y][x] = Piece.WHITE;
                }
                else if(y == 1 && x % 2 == 0){
                    this.board[y][x] = Piece.WHITE;
                }
                else if((y == 5 || y == 7) && x % 2 == 0){
                    this.board[y][x] = Piece.RED;
                }
                else if(y == 6 && x % 2 == 1){
                    this.board[y][x] = Piece.RED;
                }
                else{
                    this.board[y][x] = Piece.EMPTY;
                }
            }
        }
    }

    /**
     * Getter for whether the game is won
     * @return is the game won?
     */
    public boolean isGameWon(){
        return this.gameWon;
    }
    
    /**
     * Check if the game is won and update this.gameWon if it is
     */
    private void checkGameWon(){
        boolean whiteFound = false;
        boolean redFound = false;
        for (int y = 0; y < this.board.length; y++){
            for (int x = 0; x < this.board.length; x++){
                // Check if there's a piece
                if( this.board[y][x] != Piece.EMPTY ){
                    // If there's a piece, figure out what type of piece it is
                    if( this.board[y][x] == Piece.RED 
                            || this.board[y][x] == Piece.RED_KING){
                        redFound = true;
                    }
                    else{ whiteFound = true; }
                }
                // If both players have a piece, end early
                if( whiteFound && redFound ){ break; }
            }
            if( whiteFound && redFound ){ break; }
        }
        if( whiteFound && redFound ){ return; }
        // If we haven't exited yet, the game has been won!
        this.gameWon = true;
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

        if (!isRed || isKing){
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
        if (isRed || isKing){
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
     * Determine whether the current player has any jumps available
     * @return true if the player has a jump, false otherwise
     */
    private boolean playerHasJumps(){
        if(this.currentPlayer){
            // Red
            // Iterate over the entire board, looking for red pieces
            for (int y = 0; y < this.board.length; y++){
                for (int x = 0; x < this.board.length; x++){
                    // If the piece is red, check if it can jump
                    if(this.board[y][x] == Piece.RED 
                            || this.board[y][x] == Piece.RED_KING){
                        // If it can jump, the player has a jump!
                        if(jumpExists(x,y)[4]){ return true; }
                    }
                }
            }
        }
        else{
            // White
            // Same as red, but... white
            for (int y = 0; y < this.board.length; y++){
                for (int x = 0; x < this.board.length; x++){
                    if(this.board[y][x] == Piece.WHITE
                            || this.board[y][x] == Piece.WHITE_KING){
                        if(jumpExists(x,y)[4]){ return true; }
                    }
                }
            }
        }
        return false;
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
        if (sx >= this.board.length || sy >= this.board.length || sx < 0 || sy < 0
                || dx >= this.board.length || dy >= this.board.length || dx < 0 || dy < 0){
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
        else if (this.currentPlayer && 
                (this.board[sy][sx] == Piece.WHITE || this.board[sy][sx] == Piece.WHITE_KING)){
            return false;
        }
        // Validate moving piece is owned (white player)
        else if (!this.currentPlayer && 
                (this.board[sy][sx] == Piece.RED || this.board[sy][sx] == Piece.RED_KING)){
            return false;
        }
        // If the piece isn't a king, validate the move is in the correct direction
        if(this.board[sy][sx] == Piece.RED){
            // Red can move to lower y values
            if(sy < dy){ return false; }
        }
        else if(this.board[sy][sx] == Piece.WHITE){
            // White can move to higher y values
            if(sy > dy){ return false; }
        }        
        // Validate that destination is a valid move
        boolean[] jumps = jumpExists(sx, sy);
        if(playerHasJumps()){
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

    /**
     * Allows a player to attempt a move given the coordinates of a position and
     * uses the validateMove() function to check and see if the move input by
     * the player is possible.
     *
     * @param sx The starting x position
     * @param sy The starting y position
     * @param ex The starting x position
     * @param ey The starting y position
     * @return true if move added, false otherwise
     * */
    public boolean attemptMove(int sy, int sx, int ey, int ex){
        // If stack is empty: just validate move
        if(this.moves.empty()){
            if(validateMove(sx, sy, ex, ey)){
                Move move = new Move(new Position(sy, sx), new Position(ey, ex), this.board[sy][sx]);
                addMoveToStack(move);
                return true;
            }
        }
        // If stack has moves in it: make sure move is a valid multijump
        else{
            Piece type = this.moves.peek().getType();
            if(validateMultiJump(sx, sy, ex, ey)){
                Move move = new Move(new Position(sy, sx), new Position(ey, ex), type);
                addMoveToStack(move);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks whether a multijump is valid
     * @param sx Source tile x position
     * @param sy Source tile y position
     * @param ex Destination tile x position
     * @param ey Destination tile y position
     * @return True if the move is a valid multijump, else false
     */
    private boolean validateMultiJump(int sx, int sy, int ex, int ey){
        // Note: This function is separate from the usual validation functions
        // because it has to consider in-progress moves, while the other
        // functions simply consider the current board state (and are thus only
        // useful when no moves are in progress). Although this logic could be
        // fused into those, the added complexity would make already logic-dense
        // code completely unintelligible.
        Move top = this.moves.peek();
        Piece jumper = top.getType();
        Piece target;
        // Check if move on top of stack moves a piece to sx, sy
        if(top.getEnd().getRow() == sy && top.getEnd().getCell() == sx){
            // If so, return whether the move is a valid jump
            // Check if the destination is empty
            if(this.board[ey][ex] != Piece.EMPTY){ return false; }
            // Check if the move a proper diagonal jump
            if(ey == sy-2 && ex == sx-2){
                target = this.board[sy-1][sx-1];
            }
            else if(ey == sy-2 && ex == sx+2){
                target = this.board[sy-1][sx+1];
            }
            else if(ey == sy+2 && ex == sx+2){
                target = this.board[sy+1][sx+1];
            }
            else if(ey == sy+2 && ex == sx-2){
                target = this.board[sy+1][sx-1];
            }
            else{
                // Not a jump
                return false;
            }
            // Check if the move is jumping a piece of the opposite color
            if((jumper == Piece.RED || jumper == Piece.RED_KING) 
                    && !(target == Piece.WHITE || target == Piece.WHITE_KING)){
                // Red piece trying to jump red or empty
                return false;
            }
            else if((jumper == Piece.WHITE || jumper == Piece.WHITE_KING) 
                    && !(target == Piece.RED || target == Piece.RED_KING)){
                // White piece trying to jump white or empty
                return false;
            }
            // Check if piece type is allowed to jump in this direction
            // Kings can jump in every direction, so we don't check for those
            if(jumper == Piece.RED){
                // Red can jump to lower y values
                if(sy < ey){ return false; }
            }
            else if(jumper == Piece.WHITE){
                // White can jump to higher y values
                if(sy > ey){ return false; }
            }
            // If we reach this point, the jump must be valid!
            return true;
        }
        // If the move on top of the stack does not move a piece to sx, sy,
        // then this move can't possibly be valid
        return false;
    }
    
    /**
     * Add a move to the pending moves stack
     * @param move the move to add
     */
    public void addMoveToStack(Move move){
        this.moves.push(move);
    }

    /**
     * Submit all moves that are on the stack
     */
    public boolean submitMove(){
        // If no move is being made, return false
        if(this.moves.empty()){ return false; }
        // Alternatively, if there's a jump left, return false
        //TODO
        // Start and end pos of the piece to move
        // Java isn't smart enough to tell that these will always be initialized
        int sx = 0, sy = 0, ex = 0, ey = 0;
        boolean first = true;
        // Handle each move
        while(!moves.empty()) {
            // Get move info
            Move top = moves.pop();
            int ix = top.getStart().getCell();
            int iy = top.getStart().getRow();
            int fx = top.getEnd().getCell();
            int fy = top.getEnd().getRow();
                
            // If this is the first move, update ex and ey
            if(first){
                ex = fx;
                ey = fy;
                first = false;
            }
            // If this is the last move, update sx and sy
            if(moves.empty()){
                sx = ix;
                sy = iy;
            }
            // If this is a jump, remove the jumped piece
            if(iy % 2 == fy % 2){
                int tx, ty;
                if(ix > fx){tx = ix-1;}else{tx = ix+1;}
                if(iy > fy){ty = iy-1;}else{ty = iy+1;}
                this.board[ty][tx] = Piece.EMPTY;
            }
        }
        // Update the piece position
        Piece piece = this.board[sy][sx];
        this.board[ey][ex] = piece;
        this.board[sy][sx] = Piece.EMPTY;
        // If the piece is in place to be kinged, king it!
        if(ey == 0 && piece == Piece.RED){ this.board[ey][ex] = Piece.RED_KING; }
        if(ey == 7 && piece == Piece.WHITE){ this.board[ey][ex] = Piece.WHITE_KING; }
        // Check if game is won and update current player
        checkGameWon();
        this.currentPlayer = !this.currentPlayer;
        return true;
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
     * Get the stack of unpushed moves
     * @return the stack of moves
     */
    public Stack<Move> getMoves(){
        return this.moves;
    }

    /**
     * Main function to test CheckersGame class
     * @param args Main takes no args
     */
    public static void main(String[] args){
        CheckersGame checkers = new CheckersGame();
        checkers.printBoard();
        checkers.assignToTile(4,5,Piece.EMPTY);
        checkers.assignToTile(3,4,Piece.RED);
        checkers.assignToTile(2,3,Piece.WHITE);
        checkers.printBoard();
        System.out.println("Valid Moves:\n");
        checkers.printValidMoves(2,3);
        boolean[] jumps = checkers.jumpExists(2,3);
        for( boolean b : jumps){
            System.out.println(b);
        }
    }
}
