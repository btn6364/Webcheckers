package com.webcheckers.ui;

import com.webcheckers.appl.GameServer;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The UI Controller to GET the Game page.
 *
 * @author Liam Obrochta
 * @author John Licitra
 */
public class GetGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    public static final String TITLE = "Game";
    public static final String VIEW_NAME = "game.ftl";
    public static final String MODE_PLAY = "PLAY";
    public static final String SPECTATE_MODE = "SPECTATOR";

    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param engine
     *   the HTML template rendering engine
     */
    public GetGameRoute(TemplateEngine engine, PlayerLobby playerLobby, GameServer gameServer){
        this.templateEngine = engine;
        this.gameServer = gameServer;
        this.playerLobby = playerLobby;
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
        //TODO: Make this throw an exception (if it needs to, otherwise remove that it can); fix NPE from getName()
        LOG.finer("GetGameRoute is invoked.");
        //

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", TITLE);

        Player currentPlayer = playerLobby.getPlayer(request.session().id());
        Game game = gameServer.getGame(currentPlayer);

        if (game != null){
            Player first = game.getPlayer1();
            Player second = game.getPlayer2();
            vm.put("currentUser", currentPlayer);
            vm.put("viewMode", "PLAY");
            vm.put("redPlayer", first);
            vm.put("whitePlayer", second);
            vm.put("activeColor", game.getActiveColor());
            if (currentPlayer.equals(game.getPlayer1())){
                vm.put("board", game.getBoardView());
            } else {
                vm.put("board", game.getBoardView().getReverseBoard());
            }

        }

        // render the view model

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }


}