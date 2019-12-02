package com.webcheckers.model;

/**
 * Represents a move.
 */
public class Move {

    private final Position start, end;
    private final CheckersGame.Piece type;
    
    public Move(Position start, Position end, CheckersGame.Piece type){
        this.start = start;
        this.end = end;
        this.type = type;
    }

    /**
     * Get the starting position.
     * @return the starting position.
     */
    public Position getStart() {
        return start;
    }

    /**
     * Get the end position.
     * @return the ending position.
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Get the type of the piece.
     * @return the type of the piece.
     */
    public CheckersGame.Piece getType() {
        return type;
    }
}
