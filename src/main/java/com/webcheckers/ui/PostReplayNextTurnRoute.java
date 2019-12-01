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

public class PostReplayNextTurnRoute implements Route {
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;

    public PostReplayNextTurnRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer){
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        Player player = playerLobby.getPlayer(request.session().id());
        Game game = gameServer.getGame(player);
        //TODO: need to check this
        GameSave gameSave = gameServer.getSavedGames().get(player);
        ArrayList<BoardView> viewSaves = gameSave.getViewSaves();
        if (viewSaves != null){
            //TODO don't know how to increment count and save count.
            BoardView boardView = viewSaves.get(0);
            if (boardView != null){
                return new Gson().toJson(Message.info("true"));
            }
        }
        return new Gson().toJson(Message.info("false"));
//
//        if (game.getResign()){
//            return new Gson().toJson(Message.info("true"));
//        } else {
//            if (game.getPlayerWithTurn().equals(player)){
//                return new Gson().toJson(Message.info("true"));
//            } else {
//                return new Gson().toJson(Message.info("false"));
//            }
//        }

    }

}
