package com.webcheckers.ui;

import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import static spark.Spark.halt;

/**
 * Handle POST route for sign-out
 * @author John Licitra
 * @author Bao Nguyen
 */
public class PostSignoutRoute implements Route {
    private final TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /} HTTP requests.
     * @param templateEngine the HTML template rendering engine.
     */
    protected PostSignoutRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer) {
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
    }


    /**
     * handle post route for signing out.
     *
     * @param request the HTTP request.
     * @param response the HTTP response.
     * @return
     */
    @Override
    public String handle(Request request, Response response) {
        String sessionID = request.session().id();
        playerLobby.removePlayer(sessionID);

        //redirect to the signout page.
        response.redirect(WebServer.SIGNOUT_URL);

        //End the else block as normal.
        halt();
        return null;

    }


}
