package com.webcheckers.ui;


import com.google.gson.Gson;
import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import com.webcheckers.util.NameUtils;
import org.eclipse.jetty.util.StringUtil;
import spark.*;
import spark.utils.StringUtils;


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

        Player currentPlayer = playerLobby.getPlayer(request.session().id());
        String input = request.queryParams("button");
        if (input == null) {
            return input; // button wasn't clicked.. something went wrong
        }

        if (!NameUtils.isNumeric(input)) {

            Player secondPlayer = playerLobby.getPlayer(input);

            if (secondPlayer == null) { //player didn't exist
                response.redirect(WebServer.HOME_URL + "?error=Player does not exist!");
                halt();
                return null;
            } else if (gameServer.getGame(secondPlayer) != null) {
                response.redirect(WebServer.HOME_URL + "?error=Player is already in a game!");
                halt();
                return null;
            } else if (!playerLobby.isPlayerAvailable(secondPlayer)){
                response.redirect(WebServer.HOME_URL + "?error=Player is not available to play!");
                halt();
                return null;
            }

            gameServer.newGame(currentPlayer, secondPlayer);
            //add a new game to both player 1 and player 2
            Game game = gameServer.getGame(currentPlayer);
            currentPlayer.addPlayedGames(game);
            secondPlayer.addPlayedGames(game);


            response.redirect(WebServer.GAME_URL);

            //End the else block as normal.
            halt();
            return null;

        } else { //This means that the input from query params shows the player wants to spectate or replay
            Game game = gameServer.getGameFromGameID(input);

            if (game == null || game.isGameEnded()){ // If this is true, the game doesn't exist and the player is trying to replay a previous game
                playerLobby.addReplayer(currentPlayer);
                response.redirect(WebServer.REPLAY_GAME_URL + "?gameID=" + input);
                halt();
                return null;
            } else {
                playerLobby.addSpectator(currentPlayer);
                game.addSpectator(currentPlayer);
                response.redirect(WebServer.SPECTATOR_GAME_URL + "?gameID=" + game.getGameID());
                halt();
                return null;
            }

        }

    }
}

