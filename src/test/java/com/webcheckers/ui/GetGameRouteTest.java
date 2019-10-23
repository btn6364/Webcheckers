package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.GameServer;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.ui.board.BoardView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.HaltException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;
import spark.http.matching.Halt;

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

        //Create a unique CuT for each test.
        CuT = new GetGameRoute(engine);
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
        Player player1 = PlayerLobby.getPlayerFromSessionID(session1.id());
        Player player2 = PlayerLobby.getPlayerFromSessionID(session2.id());

        //Assert model exists & is a map
        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();

        //Assert that all map values are correctly established
        helper.assertViewModelAttribute("title", GetGameRoute.TITLE);
        helper.assertViewModelAttribute("currentUser", player1);
        helper.assertViewModelAttribute("viewMode", "play");
        helper.assertViewModelAttribute("redPlayer", player1);
        helper.assertViewModelAttribute("whitePlayer", player2);
        helper.assertViewModelAttribute("activeColor", "RED");
        // *test view name
        helper.assertViewName(GetGameRoute.VIEW_NAME);
    }

}
