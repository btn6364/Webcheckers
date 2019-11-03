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
    private final PlayerLobby playerLobby = new PlayerLobby();
    private final TemplateEngine templateEngine;

    protected PostResignGameRoute(TemplateEngine templateEngine){
        Objects.requireNonNull(playerLobby, "playerLobby must not be null!");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null!");
        this.templateEngine = templateEngine;
    }
    @Override
    public Object handle(Request request, Response response) throws Exception {

        // if someone hit the resign button.
        String input = request.queryParams("resign");
        if (input == null) {
            return input; // the button wasn't clicked, sth went wrong.
        }

        //resign the player who clicked the resign button from the current game
        Player player = PlayerLobby.getPlayerFromSessionID(request.session().id());
        PlayerLobby.resignGame(player);

        //TODO for the other player, handle the resign by check if resigned is True or False in the PostBackUpMoveRoute and PostSubmitTurnRoute

        response.redirect(WebServer.HOME_URL);

        return null;
    }
}
