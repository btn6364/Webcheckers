package com.webcheckers.ui;


import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

/**
 * Handle Post Route
 *
 * @author Bao Nguyen
 * @author John Licitra
 */
public class PostHomeRoute implements Route {


    private final TemplateEngine templateEngine;


    protected PostHomeRoute(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    /**
     * handle post route for joining a game
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String handle(Request request, Response response) {

        Player firstPlayer = PlayerLobby.getPlayerFromSessionID(request.session().id());
        String input = request.queryParams("button");
        if (input == null){
            return input; // button wasn't clicked.. something went wrong
        }

        Player secondPlayer = PlayerLobby.getPlayerFromUsername(input);

        if (secondPlayer == null){
            return null; //player didn't exist
        }

        PlayerLobby.newGame(firstPlayer, secondPlayer);

        response.redirect(WebServer.GAME_URL);

        //End the else block as normal.
        halt();
        return null;

    }


}

