package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route {


    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());

    private TemplateEngine templateEngine;

    public PostValidateMoveRoute(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }


    @Override
    public Object handle(Request request, Response response){
        Player player = PlayerLobby.getPlayerFromSessionID(request.session().id());
        Game game = PlayerLobby.getGameFromPlayer(player);
        String actionData = request.queryParams("actionData");
        Gson gson = new Gson();
        Move move = gson.fromJson(actionData, Move.class);
        System.out.println(move);


        return null;
    }
}
