package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;

/**
 * Handle POST route for resignation
 *
 * @author John Licitra
 * @author Bao Nguyen
 */
public class PostResignGameRoute implements Route {
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /} HTTP requests.
     * @param templateEngine the HTML template rendering engine.
     */
    protected PostResignGameRoute(TemplateEngine templateEngine){
        Objects.requireNonNull(templateEngine, "templateEngine must not be null!");
        this.templateEngine = templateEngine;
    }

    /**
     * Handle Resign functionality when a player resigns from a game.
     * @param request the HTTP request.
     * @param response the HTTP response.
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        //resign the player who clicked the resign button from the current game
        Player player = PlayerLobby.getPlayerFromSessionID(request.session().id());
        PlayerLobby.resignGame(player);

        //TODO: for the other player, handle the resign by check if resigned is True or False in the PostBackUpMoveRoute and PostSubmitTurnRoute
        //TODO: Also modify window.GameData so that modeOptions array reads that the game was resigned

        //response.redirect(WebServer.HOME_URL);
        return new Gson().toJson(Message.info("Message"));
    }
}
