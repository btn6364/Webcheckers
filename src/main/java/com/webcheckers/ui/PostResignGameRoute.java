package com.webcheckers.ui;

import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

/**
 * Handle POST route for resignation
 *
 * @author Liam Obrochta
 * @author John Licitra
 * @author Bao Nguyen
 */
public class PostResignGameRoute implements Route {

    private final TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;
    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /} HTTP requests.
     * @param templateEngine the HTML template rendering engine.
     */
    protected PostResignGameRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer, Gson gson){
        Objects.requireNonNull(templateEngine, "templateEngine must not be null!");
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
        this.gson = gson;
    }

    /**
     * Handle Resign functionality when a player resigns from a game.
     * @param request the HTTP request.
     * @param response the HTTP response.
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        //resign the player who clicked the resign button from the current game
        Player player = playerLobby.getPlayer(request.session().id());
        boolean success = gameServer.resignGame(player);

        if (success){
            return gson.toJson(Message.info("Resign"));
        } else {
            return gson.toJson(Message.error("You can only resign on your turn!"));
        }


    }
}
