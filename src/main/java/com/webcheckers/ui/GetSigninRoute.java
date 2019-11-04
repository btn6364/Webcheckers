package com.webcheckers.ui;

import java.util.HashMap;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;


/**
 * The UI Controller to GET the Sign in page
 *
 * @author Liam Obrochta
 */
public class GetSigninRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());

    static final String TITLE = "Sign in";
    static final String VIEW_NAME = "signin.ftl";
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetSigninRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetSigninRoute is initialized.");
    }

    /**
     * Render the WebCheckers SignIn page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Sign In page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSigninRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", TITLE);

        // render the View
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }


}
