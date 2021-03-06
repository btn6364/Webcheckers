package com.webcheckers.ui.board;

import com.webcheckers.model.CheckersGame;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class representing the UI BoardView model for a game of Checkers
 *
 * @author John Licitra
 */
public class BoardView implements Iterable<Row> {
    /** A List containing the Rows of the game board **/
    private ArrayList<Row> rows;
    private CheckersGame game;

    /**
     * Construct a new BoardView instance
     * @param rows a List containing the Rows of the board
     */
    public BoardView(ArrayList<Row> rows){
        this.rows = rows;
    }

    /**
     * Construct a new BoardView instance corresponding to a CheckersGame model
     * @param model the model to turn into a BoardView
     */
    public BoardView(CheckersGame model){
        this.game = model;
        CheckersGame.Piece[][] board = model.getBoard();
        this.rows = new ArrayList<>();
        for (int y = 0; y < board.length; y++){
            ArrayList<Space> spaces = new ArrayList<>();
            for (int x = 0; x < board.length; x++){
                CheckersGame.Piece cgPiece = board[y][x];
                if(cgPiece == CheckersGame.Piece.RED){
                    spaces.add(new Space(y, x, new Piece(Piece.Type.SINGLE, Piece.Color.RED)));
                }
                else if(cgPiece == CheckersGame.Piece.WHITE){
                    spaces.add(new Space(y, x, new Piece(Piece.Type.SINGLE, Piece.Color.WHITE)));
                }
                else if(cgPiece == CheckersGame.Piece.WHITE_KING){
                    spaces.add(new Space(y, x, new Piece(Piece.Type.KING, Piece.Color.WHITE)));
                }
                else if(cgPiece == CheckersGame.Piece.RED_KING){
                    spaces.add(new Space(y, x, new Piece(Piece.Type.KING, Piece.Color.RED)));
                }
                else{
                    spaces.add(new Space(y, x, null));
                }
            }
            rows.add(new Row(spaces, y));
        }
    }

    public BoardView getReverseBoard(){
        BoardView bView = new BoardView(game);
        bView.rows.clear();
        for (int x = 0; x < 8; x++) {
            bView.rows.add(x, rows.get(7 -x).getReverseRow(7- x));
        }
        return bView;
    }

    /**
     * Get an iterator of the board's Rows
     * @return an iterator of the board's Rows
     */
    public Iterator<Row> iterator(){
        return this.rows.iterator();
    }
}
