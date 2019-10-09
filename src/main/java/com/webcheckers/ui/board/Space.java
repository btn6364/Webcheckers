package com.webcheckers.ui.board;

public class Space {
    private int cellIdx;
    private int row;
    private Piece piece;

    public Space(int cellIdx, int row, Piece piece){
        this.cellIdx = cellIdx;
        this.row = row;
        this.piece = piece;
    }

    public int getCellIdx(){
        return this.cellIdx;
    }

    public boolean isValid(){
        return (piece == null && (this.cellIdx + this.row % 2 == 0));
    }

    public Piece getPiece(){
        return this.piece;
    }
}
