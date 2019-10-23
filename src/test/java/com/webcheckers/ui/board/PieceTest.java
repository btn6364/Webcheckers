package com.webcheckers.ui.board;

import org.junit.jupiter.api.Test;


/**
 * Test module for a Piece object.
 * @author Sidney Mitchell <sm5069@rit.edu>
 *
 * */
public class PieceTest {
    /**
     * Check to see if a piece can successfully be made.
     * */
    @Test
    public void makeKingPiece(){

        //Make a red king
        Piece kingRed = new Piece(Piece.Type.KING, Piece.Color.RED);

        //Make a white king
        Piece kingWhite = new Piece(Piece.Type.KING, Piece.Color.RED);

        //Make a red single
        Piece singleRed = new Piece(Piece.Type.SINGLE, Piece.Color.RED);

        //Make a white single
        Piece singleWhite = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);

    }


    /**
     * Check to see if the getType function works.
     * */
    @Test
    public void testPieceTypes(){
        //Make a red king
        Piece kingRed = new Piece(Piece.Type.KING, Piece.Color.RED);

        //Make a red single
        Piece singleRed = new Piece(Piece.Type.SINGLE, Piece.Color.RED);

        Piece.Type piece1Type = kingRed.getType();
        Piece.Type piece2Type = singleRed.getType();

        //Check to see if each piece's type is accurate.
        assert(piece1Type.equals(Piece.Type.KING));
        assert(piece2Type.equals(Piece.Type.SINGLE));

    }


    /**
     *Check to see if the getColor function works.
     * */
    @Test
    public void testPieceColors(){
        //Make a red single
        Piece singleRed = new Piece(Piece.Type.SINGLE, Piece.Color.RED);

        //Make a white single
        Piece singleWhite = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);

        Piece.Color piece1Color = singleRed.getColor();
        Piece.Color piece2Color = singleWhite.getColor();

        //CHeck to see if each piece's color is accurate.
        assert(piece1Color.equals(Piece.Color.RED));
        assert(piece2Color.equals(Piece.Color.WHITE));
    }
}
