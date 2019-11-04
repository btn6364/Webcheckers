package com.webcheckers.model.board;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a Row in the UI BoardView model.
 *
 * @author John Licitra
 */
public class Row implements Iterable<Space> {
    /** A List containing the Spaces in the row **/
    private ArrayList<Space> spaces;
    /** The index of the row in the board **/
    private int index;

    /**
     * Construct a new Row
     * @param spaces the list of Spaces in the row
     * @param index the index of the row in the board
     */
    public Row(ArrayList<Space> spaces, int index){
        this.spaces = spaces;
        this.index = index;
    }

    /**
     * Get the iterator from the Spaces list
     * @return an iterator of Spaces
     */
    public Iterator<Space> iterator(){
        return this.spaces.iterator();
    }

    /**
     * Get the index of the row
     * @return the index of the row
     */
    public int getIndex(){
        return this.index;
    }
}
