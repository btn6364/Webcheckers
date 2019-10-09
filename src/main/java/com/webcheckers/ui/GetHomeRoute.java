package com.webcheckers.ui;

import java.util.*;
import java.util.logging.Logger;

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
  private static final String MESSAGE_ATTR = "message";
  private static final String TITLE_ATTR = "title";
  private static final String NUM_PLAYER_ATTR = "numPlayers";
  private static final String PLAYER_LIST_ATTR = "playerList";
  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
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
    vm.put(TITLE_ATTR, "Welcome!");
    vm.put(MESSAGE_ATTR, WELCOME_MSG);
    vm.put(NUM_PLAYER_ATTR, PlayerLobby.numPlayers());
    vm.put(PLAYER_LIST_ATTR, PlayerLobby.getPlayers());

    // Display Player info if signed in
    Player player = PlayerLobby.getPlayerFromSessionID(request.session().id());
    if (player != null){
      vm.put("currentUser", player);
      if (PlayerLobby.getGameFromPlayer(player) != null){
        // Redirect player to their game
        response.redirect(WebServer.GAME_URL);

        //End the else block as normal.
        halt();
        return null;
      }
    }

    //if the player is in a game, redirect to game.
    if (PlayerLobby.getGameFromPlayer(player) == null){
      response.redirect(WebServer.GAME_URL);
      halt();
    }

    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }

  /**
   * Provides player list to PostRoute component for displaying
   * all players.
   *
   * @param players
   *  the list of players that the function uses.
   *
   * */
  public void displayPlayers(List<Player> players){
    //functionality here
    //function for displaying list of players
    //Uses game.ftl's window.playerData
  }



}
