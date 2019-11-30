package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Set;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class PostSpectatorCheckTurn implements Route {

    private static final Logger LOG = Logger.getLogger(GetSpectatorRoute.class.getName());
    public static final String TITLE = "Spectator";
    public static final String VIEW_NAME = "game.ftl";
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;
    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param engine
     *   the HTML template rendering engine
     */
    public PostSpectatorCheckTurn(TemplateEngine engine, PlayerLobby playerLobby, GameServer gameServer, Gson gson){
        this.templateEngine = engine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) {
        Game game = gameServer.getGameFromGameID(request.queryParams("gameID"));

        if (game.isGameEnded()){
            return gson.toJson(Message.info("true"));
        }

        if (game.isUnseenTurn()){
            return gson.toJson(Message.info("true"));
        } else {
            return gson.toJson(Message.info("false"));
        }

    }
}
