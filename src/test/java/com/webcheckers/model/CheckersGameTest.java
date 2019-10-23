package com.webcheckers.model;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.webcheckers.model.CheckersGame.Piece; // friendly attribute
/**
 *
 *The unit test suite for the {@link CheckersGame} component.
 *
 * @author Bao Nguyen
 *
 */

public class CheckersGameTest {
    private static final int TEST_BOARD_SIZE = 8;
    /**
     * Test that the no-arg constructor works without failure
     */
    @Test
    public void ctor_noArg(){
        new CheckersGame();
    }

    /**
     * Test that when the game starts, the Red piece moves first.
     */
    @Test
    public void redStartFirst(){
        final CheckersGame CuT = new CheckersGame();
        assertTrue(CuT.getCurrentTurn());
    }

    /**
     * Test that when the game starts, the board generated without failure with all the pieces
     * in the right order.
     */
    @Test
    public void validStartBoard(){
        //create a starting sample board to test.
        Piece[][] testBoard = new Piece[TEST_BOARD_SIZE][TEST_BOARD_SIZE];
        for (int row = 0; row < testBoard.length; row++) {
            for (int col = 0; col < testBoard.length; col++) {
               if ((row == 0 || row == 2) && (col % 2 == 1)){
                   testBoard[row][col] = Piece.RED;
               } else if (row == 1 && col % 2 == 0){
                   testBoard[row][col] = Piece.RED;
               } else if ((row == 5 || row == 7) && (col % 2 == 0)){
                   testBoard[row][col] = Piece.WHITE;
               } else if (row == 6 && col % 2 == 1){
                   testBoard[row][col] = Piece.WHITE;
               } else {
                   testBoard[row][col] = Piece.EMPTY;
               }
            }
        }
        final CheckersGame CuT = new CheckersGame();
        for (int i = 0; i < testBoard.length; i++){
            for (int j = 0; j < testBoard.length; j++){
                assertEquals(testBoard[i][j], CuT.getBoard()[i][j]);
            }
        }
    }

    /**
     *Test that if the model recognizes the move is valid.
     */
    @Test
    public void makeAValidMove(){
        final CheckersGame CuT = new CheckersGame();
        assertTrue(CuT.validateMove(3,2,2,3));
        assertTrue(CuT.validateMove(3,2,4,3));
    }

    /**
     * Test that if the model recognizes the move is invalid
     */
    @Test
    public void makeAnInvalidMove(){
        final CheckersGame CuT = new CheckersGame();
        //try to move backward with a normal piece.
        assertFalse(CuT.validateMove(3,2,2,1));

        //try to move to spot with an existing piece.
        assertFalse(CuT.validateMove(4,1, 3,2));

        //try to move out of the board
        assertFalse(CuT.validateMove(7,2,8,3));
        assertFalse(CuT.validateMove(1,0,0, -1));
    }

    /**
     * Helper function to create an empty board
     * @return create an empty test board
     */
    private Piece[][] createEmptyBoard(){
        Piece[][] testBoard = new Piece[TEST_BOARD_SIZE][TEST_BOARD_SIZE];
        for (int i = 0; i < TEST_BOARD_SIZE; i++){
            for (int j = 0; j < TEST_BOARD_SIZE; j++){
                testBoard[i][j] = Piece.EMPTY;
            }
        }
        return testBoard;
    }
    /**
     * Test that if the model recognizes there is a jump existing.
     */
    @Test
    public void makeAValidJump(){
        //Initialize a case where it is the Red turn and there is an existing jump available.
        CheckersGame CuT = new CheckersGame();
        Piece[][] boardTesting = CuT.getBoard();

        // for example the white piece has moved from (5,4) to (3,4)
        boardTesting[5][4] = Piece.EMPTY;
        boardTesting[3][4] = Piece.WHITE;

        //assert if the red piece can jump over the white piece.
        assertTrue(CuT.validateMove(3,2,5,4));
    }



}
