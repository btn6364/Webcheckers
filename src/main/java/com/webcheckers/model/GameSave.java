package com.webcheckers.model;

import com.webcheckers.ui.board.BoardView;

import java.util.ArrayList;
import java.util.Date;

public class GameSave {

    private Game game;
    private BoardView current;
    private ArrayList<BoardView> viewSaves;

    public GameSave(Game game, ArrayList<BoardView> saves){
        this.game = game;
        this.viewSaves = saves;
        this.current = saves.get(0);
    }

    public Game getGame(){
        return this.game;
    }

    public String getName(){
        return this.game.getName();
    }

    public boolean hasNext(){
        int index = this.viewSaves.indexOf(current);
        if (index + 1 > this.viewSaves.size() - 1){
            return false;
        } else if (this.viewSaves.get(index + 1) == null){
            return false;
        }
        return true;
    }

    public boolean hasPrevious(){
        int index = this.viewSaves.indexOf(current);

        if (index - 1 < 0){
            return false;
        } else if (this.viewSaves.get(index - 1) == null){
            return false;
        }
        return true;
    }

    public BoardView getCurrent(){
        return this.current;
    }

    public void next(){
        int index = this.viewSaves.indexOf(current);
        this.current = this.viewSaves.get(index + 1);
    }

    public void previous(){
        int index = this.viewSaves.indexOf(current);
        this.current = this.viewSaves.get(index - 1);
    }

    public String getGameID(){
        return this.game.getGameID();
    }

    public ArrayList<BoardView> getViewSaves(){
        return this.viewSaves;
    }
}
