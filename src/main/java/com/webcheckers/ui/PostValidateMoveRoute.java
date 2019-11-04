package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.awt.*;
import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route {


    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());
    private Gson gson = new Gson();
    private TemplateEngine templateEngine;

    public PostValidateMoveRoute(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }


    @Override
    public Object handle(Request request, Response response){
        Player player = PlayerLobby.getPlayerFromSessionID(request.session().id());
        Game game = PlayerLobby.getGameFromPlayer(player);
        String actionData = request.queryParams("actionData");
        Move move = gson.fromJson(actionData, Move.class);
        Position initial = move.getInitialPosition();
        Position fin = move.getFinalPosition();
        Message message;



        if (game.getGame().attemptMove(initial.getRow(), initial.getCell(), fin.getRow(), fin.getCell())){
            message = Message.info("Move is valid");
        } else {
            message = Message.error("Move is invalid");

        }

        return gson.toJson(message);

    }
}
