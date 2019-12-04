package com.webcheckers.ui.board;

import org.junit.jupiter.api.Test;

/**
 * Unit test for the Space board UI
 * author: Angela Hudak
 */

public class SpaceTest {
    private int cellIdx = 69;
    private int row = 420;
    private Piece piece;

    /**
     * Test a new Space.
     * @param cellIdx the col idx of the Space
     * @param row the row idx of the Space
     * @param piece the Piece that the Space contains (null if empty)
     */
    @Test
    private void SpaceTest(int cellIdx, int row, Piece piece){
        this.cellIdx = cellIdx;
        this.row = row;
        Piece.Color red = Piece.Color.RED;
        Piece.Color white = Piece.Color.WHITE;
        Piece.Type king = Piece.Type.KING;
        Piece.Type single = Piece.Type.SINGLE;
        Piece piece1 = new Piece(single, red);
        Piece piece2 = new Piece(single, white);
        Piece piece3 = new Piece(king, red);
        Piece piece4 = new Piece(king, white);
        this.piece = piece;

    }

    /**
     * Test if the Space is a valid place to put a Piece
     * @return true if the Space is empty and a dark tile, false otherwise
     */
    @Test
    private void isValidTest() {
        Piece.Color red = Piece.Color.RED;
        Piece.Color white = Piece.Color.WHITE;
        Piece.Type king = Piece.Type.KING;
        Piece.Type single = Piece.Type.SINGLE;
        Piece piece1 = new Piece(single, red);
        Piece piece2 = new Piece(single, white);
        Piece piece3 = new Piece(king, red);
        Piece piece4 = new Piece(king, white);

        assert (piece == null && (this.cellIdx + this.row % 2 == 0));

    }
}
