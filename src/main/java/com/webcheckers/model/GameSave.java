package com.webcheckers.model;

import com.webcheckers.ui.board.BoardView;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class representing a game save.
 *
 * @author Liam Obrochta
 * @author Bao Nguyen
 */
public class GameSave {

    private Game game;
    private BoardView current;
    private ArrayList<BoardView> viewSaves;

    public GameSave(Game game, ArrayList<BoardView> saves){
        this.game = game;
        this.viewSaves = saves;
        this.current = saves.get(0);
    }

    /**
     * Get the game
     * @return the game
     */
    public Game getGame(){
        return this.game;
    }

    /**
     * Get the name of the game.
     * @return the name of the game.
     */
    public String getName(){
        return this.game.getName();
    }

    /**
     * Check if there is a next move.
     * @return true if there is and false otherwise.
     */
    public boolean hasNext(){
        int index = this.viewSaves.indexOf(current);
        if (index + 1 > this.viewSaves.size() - 1){
            return false;
        } else if (this.viewSaves.get(index + 1) == null){
            return false;
        }
        return true;
    }

    /**
     * Check if there is a previous move.
     * @return true if there is and false otherwise.
     */
    public boolean hasPrevious(){
        int index = this.viewSaves.indexOf(current);

        if (index - 1 < 0){
            return false;
        } else if (this.viewSaves.get(index - 1) == null){
            return false;
        }
        return true;
    }

    /**
     * Get the current BoardView state.
     * @return the current BoardView state.
     */
    public BoardView getCurrent(){
        return this.current;
    }

    /**
     * Set the next move.
     */
    public void next(){
        int index = this.viewSaves.indexOf(current);
        this.current = this.viewSaves.get(index + 1);
    }

    /**
     * Set the previous move.
     */
    public void previous(){
        int index = this.viewSaves.indexOf(current);
        this.current = this.viewSaves.get(index - 1);
    }

    /**
     * Get the id of the game.
     * @return the id of the game.
     */
    public String getGameID(){
        return this.game.getGameID();
    }

    /**
     * Get the list of all states of the game.
     * @return the list of all states of the game.
     */
    public ArrayList<BoardView> getViewSaves(){
        return this.viewSaves;
    }
}
