package com.webcheckers.ui.board;

public class Piece {
    private enum Type {
        SINGLE, KING
    }
    private enum Color {
        RED, WHITE
    }

    private Type type;
    private Color color;

    public Piece(Type type, Color color){
        this.type = type;
        this.color = color;
    }

    public Type getType(){
        return this.type;
    }

    public Color getColor(){
        return this.color;
    }
}
