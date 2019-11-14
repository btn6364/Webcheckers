package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostCheckTurnRoute implements Route {

    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;

    public PostCheckTurnRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer){
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
         Player player = playerLobby.getPlayer(request.session().id());
        Game game = gameServer.getGame(player);

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
