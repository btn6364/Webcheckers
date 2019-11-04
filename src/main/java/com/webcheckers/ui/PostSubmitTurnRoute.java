package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

public class PostSubmitTurnRoute implements Route {

    private TemplateEngine templateEngine;

    public PostSubmitTurnRoute(TemplateEngine engine){
        this.templateEngine = engine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Player player = PlayerLobby.getPlayerFromSessionID(request.session().id());
        Game game = PlayerLobby.getGameFromPlayer(player);
        Gson gson = new Gson();

        return gson.toJson(game.submitMove(player));
    }
}
