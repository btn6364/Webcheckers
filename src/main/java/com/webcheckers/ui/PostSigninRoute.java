package com.webcheckers.ui;


import com.webcheckers.util.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

/**
 * handle post
 * @author Bao Nguyen
 */
public class PostSigninRoute implements Route {
    static final String USERNAME_PARAM = "username";
    static final String HOME_VIEW = "home.ftl";
    static final String SIGNIN_VIEW = "signin.ftl";

    private final PlayerLobby playerLobby; //object to handle sign-in actions.
    private final TemplateEngine templateEngine;

    static String makeInvalidUserName(final String username){
        return String.format("You entered %s; your username should contain at least one alphanumeric character.", username);
    }


    PostSigninRoute(PlayerLobby playerLobby, TemplateEngine templateEngine){
        Objects.requireNonNull(playerLobby, "playerLobby must not be null!");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null!");
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }


    /**
     * handle route sign-in from here
     * @param request
     * @param response
     * @return
     */
    @Override
    public String handle(Request request, Response response){
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome to the signin page");
        //retrieve the signin object
        final Session session = request.session();

        //return null if the username is invalid (illegal request) or the name is used.
        // In either case, we will redirect back to sign-in page.
        //if the name is valid, redirect to the home page and save the name.

        //retrieve request parameter.
        final String username =request.queryParams(USERNAME_PARAM);
        if (!playerLobby.isAlphaNumeric(username)){
            //return to the signin page.
            //TODO also need an Server object to check if the username has already been used.
            response.redirect(WebServer.SIGNIN_URL);
            return null;

        } else {
            //return to the home page.
            //TODO save the username to the GameServer object.
            response.redirect(WebServer.HOME_URL);
            return null;
        }
    }

}
