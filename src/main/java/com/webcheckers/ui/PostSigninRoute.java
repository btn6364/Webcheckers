package com.webcheckers.ui;


import com.webcheckers.appl.PlayerLobby;
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

    private final PlayerLobby playerLobby = new PlayerLobby(); //object to handle sign-in actions.
    private final TemplateEngine templateEngine;

    protected static String makeInvalidUserName(final String username){
        return String.format("You entered %s; your username should contain at least one alphanumeric character.", username);
    }


    protected PostSigninRoute(TemplateEngine templateEngine){
        Objects.requireNonNull(playerLobby, "playerLobby must not be null!");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null!");
        this.templateEngine = templateEngine;
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
        if ((!PlayerLobby.isAlphaNumeric(inputUsername)) || PlayerLobby.isPlayerOnline(inputUsername)){
            //redirect to the sign-in page again.
            response.redirect(WebServer.SIGNIN_URL);
            halt();
            return null;
           //If player has submitted a valid name
        } else {
            //reserve the name of the user and return to the home page.
            PlayerLobby.addPlayerToServer(inputUsername, request.session().id());

            //Place the key-val pair of Lobby and the playerLobby object
            vm.put("Lobby", playerLobby);

            //Place the key-val pair of "Player" and the player's session id
            vm.put("Player", request.session().id());

            //Create any new key-val pairs that you need
            //vm.put(...)

            //Render the list to the view.
            response.redirect(WebServer.HOME_URL);

            //End the else block as normal.
            halt();
            return null;
        }



    }

}
