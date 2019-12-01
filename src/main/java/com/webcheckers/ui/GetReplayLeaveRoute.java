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

/**
 * Handle Get replay/stopWatching URL
 * @author Bao Nguyen
 */
public class GetReplayLeaveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetReplayLeaveRoute.class.getName());
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param engine
     *   the HTML template rendering engine
     */
    public GetReplayLeaveRoute(TemplateEngine engine, PlayerLobby playerLobby, GameServer gameServer){
        this.templateEngine = engine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object handle(Request request, Response response) {
        Player replayer = playerLobby.getPlayer(request.session().id());
        Game game = gameServer.getGameFromGameID(request.queryParams("gameID"));
        game.removeReplayer(replayer);
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }

}
