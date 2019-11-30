package com.webcheckers.util;

public class NameUtils {

    /**
     * Check whether a username is valid or not.
     * @param name the name passed in.
     * @return true if the name is valid and false otherwise.
     */
    public static boolean isAlphaNumeric(String name){
        return (name!=null) && (name.matches("^[a-zA-Z0-9\\s]+$") && !name.matches("\\s+"));
    }

    public static boolean isNumeric(String name) {
        return name.matches("-?\\d+(\\.\\d+)?");
    }
}
