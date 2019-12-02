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

/**
 * Handle validate move
 * @author Liam Obrochta
 * @author John Licitra
 */
public class PostValidateMoveRoute implements Route {


    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;
    private Gson gson;

    public PostValidateMoveRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer, Gson gson){
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
        this.gson = gson;
    }

    /**
     * Validate move
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the Json object with a message if the move is valid.
     */
    @Override
    public Object handle(Request request, Response response){
        Player player = playerLobby.getPlayer(request.session().id());
        Game game = gameServer.getGame(player);
        String actionData = request.queryParams("actionData");
        Move move = gson.fromJson(actionData, Move.class);
        Position initial = move.getStart();
        Position fin = move.getEnd();
        Message message;

        if (game.getGame().attemptMove(initial.getRow(), initial.getCell(), fin.getRow(), fin.getCell())){
            message = Message.info("Move is valid");
        } else {
            message = Message.error("Move is invalid");

        }

        return gson.toJson(message);

    }
}
