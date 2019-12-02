package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.GameSave;
import com.webcheckers.model.Player;
import com.webcheckers.ui.board.BoardView;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.ArrayList;

/**
 * Handle Post request for /replay/nextTurn
 * @author Bao Nguyen
 */
public class PostReplayNextTurnRoute implements Route {
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;
    private Gson gson;

    public PostReplayNextTurnRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer, Gson gson){
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
        this.gson = gson;
    }

    /**
     * Get the next turn from the game save.
     * @param request the HTTP Request
     * @param response the HTTP response
     * @return the Json object with a message of true or false
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Player player = playerLobby.getPlayer(request.session().id());
        String input = request.queryParams("gameID");
        GameSave gameSave = gameServer.getSaveFromID(input, player);
        if (gameSave != null){
            gameSave.next();
            return gson.toJson(Message.info("true"));
        }
        return gson.toJson(Message.info("false"));


    }

}
