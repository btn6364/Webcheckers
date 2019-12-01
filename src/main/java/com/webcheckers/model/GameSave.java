package com.webcheckers.model;

import com.webcheckers.ui.board.BoardView;

import java.util.ArrayList;
import java.util.Date;

public class GameSave {

    private Game game;
    private ArrayList<BoardView> viewSaves;

    public GameSave(Game game, ArrayList<BoardView> saves){
        this.game = game;
        this.viewSaves = saves;
    }

    public Game getGame(){
        return this.game;
    }

    public String getName(){
        return this.game.getName();
    }

    public String getGameID(){
        return this.game.getGameID();
    }

    public ArrayList<BoardView> getViewSaves(){
        return this.viewSaves;
    }
}
