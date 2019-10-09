package com.webcheckers.ui;

import com.webcheckers.model.GameServer;
import com.webcheckers.model.Player;
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
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetHomeRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", TITLE);

        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }

    private Map<String, Object> updateGameView(Player currentPlayer, Player redPlayer, Player whitePlayer, String activeColor, String viewMode){
        return null;
    }


}
