package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostCheckTurnRoute implements Route {

    private TemplateEngine templateEngine;

    public PostCheckTurnRoute(TemplateEngine engine){
        this.templateEngine = engine;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
         Player player = PlayerLobby.getPlayerFromSessionID(request.session().id());
        Game game = PlayerLobby.getGameFromPlayer(player);

        if (game.getResign()){
            return new Gson().toJson(Message.info("true"));
        } else {
            if (game.getPlayerWithTurn().equals(player)){
                return new Gson().toJson(Message.info("true"));
            } else {
                return new Gson().toJson(Message.info("false"));
            }
        }

    }
}
