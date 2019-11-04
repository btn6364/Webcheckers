package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;

/**
 * Handle POST route for resignation
 *
 * @author Bao Nguyen
 */
public class PostResignGameRoute implements Route {
    private final TemplateEngine templateEngine;

    protected PostResignGameRoute(TemplateEngine templateEngine){
        Objects.requireNonNull(templateEngine, "templateEngine must not be null!");
        this.templateEngine = templateEngine;
    }
    @Override
    public Object handle(Request request, Response response) throws Exception {
        //resign the player who clicked the resign button from the current game
        Player player = PlayerLobby.getPlayerFromSessionID(request.session().id());
        PlayerLobby.resignGame(player);

        //TODO: for the other player, handle the resign by check if resigned is True or False in the PostBackUpMoveRoute and PostSubmitTurnRoute
        //TODO: Also modify window.GameData so that modeOptions array reads that the game was resigned

        response.redirect(WebServer.HOME_URL);

        return null;
    }
}
