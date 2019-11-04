package com.webcheckers.ui.board;

/**
 * Represents a space in the UI BoardView model.
 *
 * @author John Licitra
 */
public class Space {
    /** The index of the Space in its row **/
    private int cellIdx;
    /** The index of the row the Space is in (THIS IS NECESSARY FOR isValid()) **/
    private int row;
    /** The piece that the Space contains, if any **/
    private Piece piece;

    /**
     * Construct a new Space.
     * @param cellIdx the col idx of the Space
     * @param row the row idx of the Space
     * @param piece the Piece that the Space contains (null if empty)
     */
    public Space(int cellIdx, int row, Piece piece){
        this.cellIdx = cellIdx;
        this.row = row;
        this.piece = piece;
    }

    /**
     * Get the column of the Space
     * @return the column index
     */
    public int getIndex(){
        return this.row; //TODO: spawn "cellIdx" variable name with "row"
    }

    /**
     * Check if the Space is a valid place to put a Piece
     * @return true if the Space is empty and a dark tile, false otherwise
     */
    public boolean isValid(){
        return piece == null && ((this.cellIdx + this.row) % 2 == 1);

    }

    /**
     * Get the Piece that the Space contains, if any
     * @return the Piece
     */
    public Piece getPiece(){
        return this.piece;
    }
}
