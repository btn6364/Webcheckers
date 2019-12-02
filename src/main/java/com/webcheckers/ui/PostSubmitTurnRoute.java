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

/**
 * Handle Submit action in Play mode
 * @author Liam Obrochta
 */
public class PostSubmitTurnRoute implements Route {

    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;

    public PostSubmitTurnRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer){
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
    }

    /**
     * Submit the move
     * @param request the HTTP request
     * @param response the HTTP response
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Player player = playerLobby.getPlayer(request.session().id());
        Game game = gameServer.getGame(player);
        Gson gson = new Gson();

        return gson.toJson(game.submitMove(player));
    }
}
