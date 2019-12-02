package com.webcheckers.model;

/**
 * Represents the position of the piece.
 */
public class Position {

    private int row;
    private int cell;

    public Position(int row, int cell){
        this.row = row;
        this.cell = cell;
    }

    /**
     * Get the row of the piece.
     * @return the row of the piece.
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the cell of the piece.
     * @return the cell of the piece.
     */
    public int getCell() {
        return cell;
    }
}
