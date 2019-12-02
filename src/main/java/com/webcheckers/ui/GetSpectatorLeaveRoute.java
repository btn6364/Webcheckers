package com.webcheckers.ui;

import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.logging.Logger;

import static spark.Spark.halt;

public class GetSpectatorLeaveRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetSpectatorRoute.class.getName());
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param engine
     *   the HTML template rendering engine
     */
    public GetSpectatorLeaveRoute(TemplateEngine engine, PlayerLobby playerLobby, GameServer gameServer){
        this.templateEngine = engine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
    }

    @Override
    public Object handle(Request request, Response response) {

        Player player = playerLobby.getPlayer(request.session().id());
        Game game = gameServer.getGameFromGameID(request.queryParams("gameID"));
        game.removeSpectator(player);
        playerLobby.removeSpectator(player);
        response.redirect(WebServer.HOME_URL);
        halt();

        return null;
    }
}
