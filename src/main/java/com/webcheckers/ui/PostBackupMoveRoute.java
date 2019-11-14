package com.webcheckers.ui;

import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

public class PostBackupMoveRoute implements Route {

    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;

    public PostBackupMoveRoute(TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer) {
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        return null;
    }
}
