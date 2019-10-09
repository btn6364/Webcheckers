package com.webcheckers.ui;

import com.webcheckers.model.Game;
import com.webcheckers.model.GameServer;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GetGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
    private final String TITLE = "Game";

    private GameServer gameServer;

    private TemplateEngine templateEngine;

    public GetGameRoute(TemplateEngine engine, GameServer server){
        this.templateEngine = engine;
        this.gameServer = server;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        //TODO: Make this throw an exception (if it needs to, otherwise remove that it can); fix NPE from getName()
        LOG.finer("GetHomeRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", TITLE);

        // render the View
        Player handler = PlayerLobby.getPlayerFromSessionID(request.session().id());
        Game gametoRender = gameServer.getGameFromPlayer(handler);
        Player first = gametoRender.getPlayer1();
        Player second = gametoRender.getPlayer2();
        vm.put("currentUser", handler.getName());
        vm.put("viewMode", "play");
        vm.put("redPlayer", first.getName());
        vm.put("whitePlayer", second.getName());
        vm.put("activeColor", "RED");

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }


}
