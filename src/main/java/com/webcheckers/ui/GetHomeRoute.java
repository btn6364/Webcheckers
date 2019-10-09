package com.webcheckers.ui;

import java.util.*;
import java.util.logging.Logger;

import com.webcheckers.model.GameServer;
import com.webcheckers.model.Player;
import com.webcheckers.util.PlayerLobby;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  private final TemplateEngine templateEngine;

  static String PLAYERSERVICES_KEY = "playerServices";

  private PlayerLobby playerLobby;


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
    //
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");

    // display a user message in the Home page
    vm.put("message", WELCOME_MSG);


    // retrieve the HTTP session
    final Session httpSession = request.session();

    // if this is a brand new browser session or a session that timed out
    if(httpSession.attribute(PLAYERSERVICES_KEY) == null) {
      vm.put(PLAYERSERVICES_KEY, new Player(PLAYERSERVICES_KEY));
      vm.put("lobby", playerLobby);
      // No one is logged in
    }
    else{
      vm.put("lobby", playerLobby);
      //someone is logged int
    }

    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));


  }

  public void displayPlayers(List<Player> players){
    //functionality here
  }



}
