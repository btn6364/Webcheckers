package com.webcheckers.ui.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Row implements Iterable<Space> {
    private List<Space> spaces;
    private int index;

    public Row(List<Space> spaces, int index){
        this.spaces = spaces; // = new ArrayList<Space>()
        this.index = index;
    }

    public Iterator<Space> iterator(){
        return this.spaces.iterator();
    }

    public int getIndex(){
        return this.index;
    }
}
