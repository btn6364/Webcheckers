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

import java.util.ArrayList;

/**
 * Handle Replay mode.
 * @author Bao Nguyen
 */

public class PostReplayRoute implements Route {
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;
    private Gson gson;

    public PostReplayRoute(TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer, Gson gson) {
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Player player = playerLobby.getPlayer(request.session().id());
        ArrayList<Game> playedGames = player.getPlayedGames();
        // TODO process with games played.

        return null;
    }
}
