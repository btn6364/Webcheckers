package com.webcheckers.model;

import java.util.ArrayList;

/**
 * A backend-server which maintains a list of logged-in players and in-progress games.
 * @author Bao Nguyen
 */
public class GameServer {
    //list of current players.
    ArrayList<Player> players = new ArrayList<Player>();

    // list of in-progress games.
    ArrayList inprogressGames = new ArrayList();



}
