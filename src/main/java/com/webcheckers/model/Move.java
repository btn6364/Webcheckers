package com.webcheckers.model;

public class Move {

    private Player player;

    private Position initialPosition;
    private Position finalPosition;

    public Move(Position initialPosition, Position finalPosition ){
        this.initialPosition = initialPosition;
        this.finalPosition = finalPosition;
    }



    public Position getInitialPosition() {
        return initialPosition;
    }

    public Position getFinalPosition() {
        return finalPosition;
    }



    public boolean isMoveValid(){
        return true;
    }
}
