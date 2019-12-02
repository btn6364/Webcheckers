package com.webcheckers.util;

/**
 * Utility to check valid username.
 * @author Bao Nguyen
 * @author Liam Obrochta
 */
public class NameUtils {

    /**
     * Check whether a username is valid or not.
     * @param name the name passed in.
     * @return true if the name is valid and false otherwise.
     */
    public static boolean isAlphaNumeric(String name){
        return (name!=null) && (name.matches("^[a-zA-Z0-9\\s]+$") && !name.matches("\\s+"));
    }

    /**
     * Check whether or not the username is numeric.
     * @param name the username passed in.
     * @return true if it is numeric and false otherwise.
     */
    public static boolean isNumeric(String name) {
        return name.matches("-?\\d+(\\.\\d+)?");
    }
}
