package com.webcheckers.model;

import com.webcheckers.ui.board.BoardView;

import java.util.ArrayList;

public class GameSave {

    private Player player;
    private Game game;
    private ArrayList<BoardView> viewSaves;

    public GameSave(Player player, Game game, ArrayList<BoardView> saves){
        this.player = player;
        this.game = game;
        this.viewSaves = saves;
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
