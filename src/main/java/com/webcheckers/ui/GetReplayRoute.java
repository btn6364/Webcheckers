package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.GameSave;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Handle GET replay/game URL
 * @author Liam Obrochta
 */
public class GetReplayRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetSpectatorRoute.class.getName());
    public static final String TITLE = "Replay Game";
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
    public GetReplayRoute(TemplateEngine engine, PlayerLobby playerLobby, GameServer gameServer){
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
        LOG.finer("GetReplayRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", TITLE);

        Player replayer = playerLobby.getPlayer(request.session().id());

        String gameID = request.queryParams("gameID");
        GameSave gameSave = gameServer.getSaveFromID(gameID, replayer);
        if (gameSave != null) {
            Game game = gameSave.getGame();
            Player first = game.getPlayer1();
            Player second = game.getPlayer2();
            vm.put("currentUser", replayer);
            vm.put("viewMode", "REPLAY");

            HashMap<String, Object> replayAtt = new HashMap<>();
            if (gameSave.hasNext()){
                replayAtt.put("hasNext", true);
            } else {
                replayAtt.put("hasNext", false);
            }

            if (gameSave.hasPrevious()){
                replayAtt.put("hasPrevious", true);
            } else {
                replayAtt.put("hasPrevious", false);
            }

            vm.put("modeOptionsAsJSON", new Gson().toJson(replayAtt));

            vm.put("redPlayer", first);
            vm.put("whitePlayer", second);
            vm.put("activeColor", game.getActiveColor());
            vm.put("board", gameSave.getCurrent());
            // render the view model
            return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
        }

        response.redirect(WebServer.HOME_URL);
        return null;
    }
}
