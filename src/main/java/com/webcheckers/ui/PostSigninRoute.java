package com.webcheckers.ui;


import com.webcheckers.model.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

/**
 * Handle Post Route
 * @author Bao Nguyen
 */
public class PostSigninRoute implements Route {
    private final String USERNAME_PARAM = "username";

    private final PlayerLobby playerLobby; //object to handle sign-in actions.
    private final TemplateEngine templateEngine;

    protected static String makeInvalidUserName(final String username){
        return String.format("You entered %s; your username should contain at least one alphanumeric character.", username);
    }


    protected PostSigninRoute(PlayerLobby playerLobby, TemplateEngine templateEngine){
        Objects.requireNonNull(playerLobby, "playerLobby must not be null!");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null!");
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
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
        if ((!playerLobby.isAlphaNumeric(inputUsername)) || playerLobby.isPlayerOnline(inputUsername)){
            //redirect to the sign-in page again.
            response.redirect(WebServer.SIGNIN_URL);
            halt();
            return null;
        } else {
            //reserve the name of the user and return to the home page.
            playerLobby.addPlayerToServer(inputUsername, request.session().id());
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }

    }

}
