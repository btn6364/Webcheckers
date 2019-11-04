package com.webcheckers.ui;

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

    private TemplateEngine templateEngine;

    public GetGameRoute(TemplateEngine engine){
        this.templateEngine = engine;
    }

    @Override
    public Object handle(Request request, Response response){
        //TODO: Make this throw an exception (if it needs to, otherwise remove that it can); fix NPE from getName()
        LOG.finer("GetGameRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", TITLE);


        Player player1 = PlayerLobby.getPlayerFromSessionID(request.session().id());

        if (PlayerLobby.getGameFromPlayer(player1) != null){
            Game gameToRender = PlayerLobby.getGameFromPlayer(player1);
            Player first = gameToRender.getPlayer1();
            Player second = gameToRender.getPlayer2();
            vm.put("currentUser", player1);
            vm.put("viewMode", "PLAY");
            vm.put("redPlayer", first);
            vm.put("whitePlayer", second);
            vm.put("activeColor", "RED");
            vm.put("board", gameToRender.getBoardView());
        }

        // render the view model

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }


}
