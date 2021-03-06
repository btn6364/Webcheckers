package com.webcheckers.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

/**
 * The unit test suite for the {@link GetGameRoute} component.
 *
 * @author Liam Obrochta
 *
 */
@Tag("UI-Tier")
public class GetGameRouteTest {
    //The component-under-test (CuT)
    private GetGameRoute CuT;

    //create mock objects
    //Request made by first player
    private Request request1;
    //Request made by second player
    private Request request2;
    //Session for first
    private Session session1;
    //Session for second
    private Session session2;
    private TemplateEngine engine;
    private Response response;

    private PlayerLobby playerLobby;
    private GameServer gameServer;
    private Gson gson;


    @BeforeEach
    public void setup(){
        request1 = mock(Request.class);
        request2 = mock(Request.class);
        session1 = mock(Session.class);
        session2 = mock(Session.class);
        when(request1.session()).thenReturn(session1);
        when(request2.session()).thenReturn(session2);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        playerLobby = mock(PlayerLobby.class);
        gameServer = mock(GameServer.class);
        gson = mock(Gson.class);

        //Create a unique CuT for each test.
        CuT = new GetGameRoute(engine, playerLobby, gameServer, gson);
    }

    @Test
    public void new_session(){
        final TemplateEngineTester helper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(helper.makeAnswer());

        //Invoke the test
        CuT.handle(request1, response);
        //Analyze the results:
        // *model is a non-null Map

        //Create player objects with mock session ID's
        Player player1 = playerLobby.getPlayer(session1.id());
        Player player2 = playerLobby.getPlayer(session2.id());

        //Assert model exists & is a map
        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();

        //Assert that all map values are correctly established
        helper.assertViewModelAttribute("title", GetGameRoute.TITLE);
        helper.assertViewModelAttribute("currentUser", player1);
        helper.assertViewModelAttribute("redPlayer", player1);
        helper.assertViewModelAttribute("whitePlayer", player2);
        // *test view name
        helper.assertViewName(GetGameRoute.VIEW_NAME);
    }

}
