package com.webcheckers.ui.board;

import java.util.Iterator;
import java.util.List;

/**
 * A class representing the UI BoardView model for a game of Checkers
 *
 * @author John Licitra
 */
public class BoardView implements Iterable<Row> {
    /** A List containing the Rows of the game board **/
    private List<Row> rows;

    /**
     * Construct a new BoardView instance
     * @param rows a List containing the Rows of the board
     */
    public BoardView(List<Row> rows){
        this.rows = rows;
    }

    /**
     * Get an iterator of the board's Rows
     * @return an iterator of the board's Rows
     */
    public Iterator<Row> iterator(){
        return this.rows.iterator();
    }
}
