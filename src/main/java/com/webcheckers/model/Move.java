package com.webcheckers.model;

public class Move {

    private Player player;

    private Position start, end;

    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
    }


    public Position getInitialPosition() {
        return start;
    }

    public Position getFinalPosition() {
        return end;
    }

}
