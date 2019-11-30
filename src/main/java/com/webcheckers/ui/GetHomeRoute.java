package com.webcheckers.ui;

import java.util.*;
import java.util.logging.Logger;

import com.webcheckers.appl.GameServer;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import spark.*;

import com.webcheckers.util.Message;

import static spark.Spark.halt;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 * @author John Licitra
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
  private final TemplateEngine templateEngine;

  // Strings and attributes
  static final String TITLE_ATTR = "title";
  static final String TITLE = "Welcome!";
  static final String NUM_PLAYER_ATTR = "numPlayers";
  static final String PLAYER_LIST_ATTR = "playerList";
  static final String MESSAGE_ATTR = "message";
  static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
  static final String NEW_USER_ATTR = "currentUser";
  static final String VIEW_NAME = "home.ftl";
  static final String GAME_LIST_ATTR = "gameList";

  private PlayerLobby playerLobby;
  private GameServer gameServer;


  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameServer gameServer) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");

    this.playerLobby = playerLobby;
    this.gameServer = gameServer;

    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");

    Map<String, Object> vm = new HashMap<>();

    // Display a welcome message and stats
    vm.put(TITLE_ATTR, TITLE);
    vm.put(MESSAGE_ATTR, WELCOME_MSG);
    vm.put(NUM_PLAYER_ATTR, playerLobby.numPlayers());
    vm.put(PLAYER_LIST_ATTR, playerLobby.getPlayers());
    vm.put(GAME_LIST_ATTR, gameServer.getGamesInProgress());

    // Display Player info if signed in
    Player player = playerLobby.getPlayer(request.session().id());
    if (player != null){
      vm.put(NEW_USER_ATTR, player);
      if (gameServer.getGame(player) != null && !gameServer.getGame(player).isGameEnded()){
        // Redirect player to their game
        response.redirect(WebServer.GAME_URL);

        //End the else block as normal.
        halt();
        return null;
      }
    }


    // render the View
    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }




}
