package com.webcheckers.ui;


import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.util.NameUtils;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

/**
 * Handle POST route for sign-in
 * @author Bao Nguyen
 * @author John Licitra
 */
public class PostSigninRoute implements Route {
    private final String USERNAME_PARAM = "username";

    private final TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;

    protected static String makeInvalidUserName(final String username){
        return String.format("You entered %s; your username should contain at least one alphanumeric character.", username);
    }

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /} HTTP requests.
     * @param templateEngine the HTML template rendering engine.
     */
    protected PostSigninRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer){
        Objects.requireNonNull(playerLobby, "playerLobby must not be null!");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null!");
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
    }


    /**
     * handle route sign-in
     * @param request
     * @param response
     * @return
     */
    @Override
    public String handle(Request request, Response response){
        final Map<String, Object> vm = new HashMap<>();

        String inputUsername = request.queryParams(USERNAME_PARAM);
        if ((!NameUtils.isAlphaNumeric(inputUsername))){
            //redirect to the sign-in page again.
            response.redirect(WebServer.SIGNIN_URL + "?error=User names must be alphanumeric");
            halt();
            return null;

        } else if (playerLobby.isPlayerOnline(inputUsername)) {
            //redirect to the sign-in page again.
            response.redirect(WebServer.SIGNIN_URL + "?error=User name is already taken!");
            halt();
            return null;
        } else { //If player has submitted a valid name
            //reserve the name of the user and return to the home page.
            playerLobby.addPlayerToLobby(inputUsername, request.session().id());

            //Place the key-val pair of Lobby and the playerLobby object
            vm.put("Lobby", playerLobby);

            //Place the key-val pair of "Player" and the player's session id
            vm.put("Player", request.session().id());

            //Render the list to the view.
            response.redirect(WebServer.HOME_URL);

            //End the else block as normal.
            halt();
            return null;
        }



    }

}
