package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
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
 * The unit test suite for the {@link GetHomeRoute} component.
 *
 * @author Bao Nguyen
 *
 */
public class GetHomeRouteTest {
    //The component-under-test (CuT)
    private GetHomeRoute CuT;

    //create mock objects
    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;

    /**
     * Set up new mock objects for each test.
     */
    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);


        //Create a unique CuT for each test.
        CuT = new GetHomeRoute(engine);
    }

    /**
     * Test that CuT shows the Home View with all global stats when the session is brand new.
     */
    @Test
    public void new_session(){
        final TemplateEngineTester helper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(helper.makeAnswer());

        //Invoke the test
        CuT.handle(request, response);
        //Analyze the results:
        // *model is a non-null Map
        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();
        // *model contains all the necessary View-Model data
        helper.assertViewModelAttribute(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE);
        helper.assertViewModelAttribute(GetHomeRoute.MESSAGE_ATTR, GetHomeRoute.WELCOME_MSG);
        helper.assertViewModelAttribute(GetHomeRoute.NUM_PLAYER_ATTR, PlayerLobby.numPlayers());
        helper.assertViewModelAttribute(GetHomeRoute.PLAYER_LIST_ATTR, PlayerLobby.getPlayers());
        helper.assertViewModelAttribute(GetHomeRoute.NEW_USER_ATTR, PlayerLobby.getPlayerFromSessionID(request.session().id()));
        // *test view name
        helper.assertViewName(GetHomeRoute.VIEW_NAME);
    }


}
