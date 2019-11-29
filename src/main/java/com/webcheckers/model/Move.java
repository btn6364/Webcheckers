package com.webcheckers.model;

public class Move {

    private final Position start, end;
    private final CheckersGame.Piece type;
    
    public Move(Position start, Position end, CheckersGame.Piece type){
        this.start = start;
        this.end = end;
        this.type = type;
    }


    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public CheckersGame.Piece getType() {
        return type;
    }
}
