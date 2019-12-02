package com.webcheckers.ui;

import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Handle GET spectator/game URL
 * @author Bao Nguyen
 * @author Liam Obrochta
 */
public class GetSpectatorRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectatorRoute.class.getName());
    public static final String TITLE = "Spectator";
    public static final String VIEW_NAME = "game.ftl";
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param engine
     *   the HTML template rendering engine
     */
    public GetSpectatorRoute(TemplateEngine engine, PlayerLobby playerLobby, GameServer gameServer){
        this.templateEngine = engine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
    }


    /**
     * Render the WebCheckers Game page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Game page
     */
    @Override
    public Object handle(Request request, Response response){
        LOG.finer("GetSpectatorRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", TITLE);

        Player spectator = playerLobby.getPlayer(request.session().id());
        String gameID = request.queryParams("gameID");
        Game game = gameServer.getGameFromGameID(gameID);
        if (game != null) {
            Player first = game.getPlayer1();
            Player second = game.getPlayer2();
            vm.put("currentUser", spectator);
            vm.put("viewMode", "SPECTATOR");
            vm.put("redPlayer", first);
            vm.put("whitePlayer", second);
            vm.put("activeColor", game.getActiveColor());
            vm.put("board", game.getBoardView());


            // render the view model
            return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
        }

        response.redirect(WebServer.HOME_URL);
        return null;
    }


}
