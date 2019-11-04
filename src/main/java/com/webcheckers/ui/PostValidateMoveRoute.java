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

public class PostValidateMoveRoute implements Route {


    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());

    private GameServer gameServer;

    private TemplateEngine templateEngine;

    public PostValidateMoveRoute(TemplateEngine templateEngine, GameServer gameServer){
        this.gameServer = gameServer;
        this.templateEngine = templateEngine;
    }


    @Override
    public Object handle(Request request, Response response){
        LOG.warning("TEST");
        Player player = PlayerLobby.getPlayerFromSessionID(request.session().id());
        Game game = gameServer.getGameFromPlayer(player);

        System.out.println("TEST");


        return null;
    }
}
