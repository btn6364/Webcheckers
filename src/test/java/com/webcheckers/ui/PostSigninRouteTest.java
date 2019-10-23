package com.webcheckers.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the PostSigninRoute component.
 *
 * @author John Licitra
 */
public class PostSigninRouteTest {
    private PostSigninRoute CuT;

    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;

    /**
     * Set up mock request, session, response, and engine to test route with
     */
    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);


        // Create CuT for the test
        CuT = new PostSigninRoute(engine);
    }

    /**
     * Test that the Sign-in Route has the correct attributes
     */
    @Test
    public void postSigninRouteTest() {
        // Handle the request and response to create a model
        final TemplateEngineTester helper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(helper.makeAnswer());
        CuT.handle(request, response);
        // Assert that the model exists and is valid
        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();
        // Check model attribute
        helper.assertViewModelAttribute("Player", request.session().id());
    }
}
