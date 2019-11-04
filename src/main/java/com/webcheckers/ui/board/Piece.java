package com.webcheckers.ui.board;

/**
 * A class representing a Piece in the UI BoardView model.
 *
 * @author John Licitra
 */
public class Piece {
    /** An enum representing the type of a Piece **/
    public enum Type {
        SINGLE, KING
    }
    /** An enum representing the color of a Piece **/
    public enum Color {
        RED, WHITE
    }

    /** The Type of this Piece **/
    private Type type;
    /** The Color of this Piece **/
    private Color color;

    /**
     * Construct a new Piece of the given type and color
     * @param type a Type value
     * @param color a Color value
     */
    public Piece(Type type, Color color){
        this.type = type;
        this.color = color;
    }

    /**
     * Get the Type of the Piece
     * @return the Type of the Piece
     */
    public Type getType(){
        return this.type;
    }

    /**
     * Get the Color of the Piece
     * @return the Color of the Piece
     */
    public Color getColor(){
        return this.color;
    }
}
