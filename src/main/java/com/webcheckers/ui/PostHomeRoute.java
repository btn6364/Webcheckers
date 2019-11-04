package com.webcheckers.ui;


import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;


import static spark.Spark.halt;

/**
 * Handle POST route for home (ie joining a game)
 *
 * @author Bao Nguyen
 * @author John Licitra
 */
public class PostHomeRoute implements Route {


    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /} HTTP requests.
     * @param templateEngine the HTML template rendering engine.
     */
    protected PostHomeRoute(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    /**
     * Handle post route for joining a game.
     *
     * @param request HTTP request.
     * @param response HTTP response.
     * @return
     */
    @Override
    public String handle(Request request, Response response) {

        Player firstPlayer = PlayerLobby.getPlayerFromSessionID(request.session().id());
        String input = request.queryParams("button");
        if (input == null){
            return input; // button wasn't clicked.. something went wrong
        }

        Player secondPlayer = PlayerLobby.getPlayerFromUsername(input);

        if (secondPlayer == null){
            return null; //player didn't exist
        }
        else if (PlayerLobby.getGameFromPlayer(secondPlayer) != null){
            return null; //player already in a game
        }

        PlayerLobby.newGame(firstPlayer, secondPlayer);

        response.redirect(WebServer.GAME_URL);

        //End the else block as normal.
        halt();
        return null;

    }


}

