package com.webcheckers.util;

/**
 * handle the validity of the username.
 * @author Bao Nguyen
 */
public class PlayerLobby {

    public boolean isAlphaNumeric(String name){
        return (name!=null) && (name.matches("^[a-zA-Z0-9]*$") || name.contains(" "));
    }

    public static void main(String[] args) {
        PlayerLobby playerLobby = new PlayerLobby();
        String s = "***";
        System.out.println(playerLobby.isAlphaNumeric(s));
    }


}
