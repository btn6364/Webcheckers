package com.webcheckers.ui;

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
    //private final PlayerLobby playerLobby = new PlayerLobby(); //object to handle sign-in actions.


    protected PostSignoutRoute(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    /**
     * handle post route for signing out.
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String handle(Request request, Response response) {
        //final Map<String, Object> vm = new HashMap<>();

        /*
        String input = request.queryParams("button");
        if (input == null){
            return input; // the button wasn't clicked, sth went wrong.
        }
        */
        String sessionID = request.session().id();
        PlayerLobby.removePlayerBySessionID(sessionID);

        //Place the key-val pair of Lobby and the playerLobby object
        //vm.put("Lobby", playerLobby);
        response.redirect(WebServer.SIGNOUT_URL);

        //End the else block as normal.
        halt();
        return null;

    }


}
