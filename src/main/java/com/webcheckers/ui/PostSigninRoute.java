package com.webcheckers.ui;


import com.webcheckers.util.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

/**
 * handle post
 * @author Bao Nguyen
 */
public class PostSigninRoute implements Route {

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
        return null;
    }

}
