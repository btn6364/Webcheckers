package com.webcheckers.ui;


import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;


import static spark.Spark.halt;

/**
 * Handle POST route for home (ie joining a game)
 *
 * @author Liam Obrochta
 * @author Bao Nguyen
 * @author John Licitra
 */
public class PostHomeRoute implements Route {


    private final TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameServer gameServer;
    

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /} HTTP requests.
     * @param templateEngine the HTML template rendering engine.
     */
    protected PostHomeRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer) {
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameServer = gameServer;
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

        Player firstPlayer = playerLobby.getPlayer(request.session().id());
        String input = request.queryParams("button");
        if (input == null){
            return input; // button wasn't clicked.. something went wrong
        }

        Player secondPlayer = playerLobby.getPlayer(input);

        if (secondPlayer == null){
            return null; //player didn't exist
        }
        else if (gameServer.getGame(secondPlayer) != null){
            return null; //player already in a game
        }

        gameServer.newGame(firstPlayer, secondPlayer);

        response.redirect(WebServer.GAME_URL);

        //End the else block as normal.
        halt();
        return null;

    }


}

