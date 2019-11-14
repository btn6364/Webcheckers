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

public class PostBackupMoveRoute implements Route {

    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;
    private Gson gson;

    public PostBackupMoveRoute(TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer, Gson gson) {
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Player player = playerLobby.getPlayer(request.session().id());
        Game game = gameServer.getGame(player);

        boolean backupResult = game.backupMove(player);

        if (backupResult){
            return gson.toJson(Message.info("Move backed up successfully"));
        } else {
            return  gson.toJson(Message.error("No moves to back up!"));
        }
    }
}
