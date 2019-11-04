package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 /**
 * The UI Controller to GET the Sign out page
 *
 * @author Bao Nguyen
 */
public class GetSignoutRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());

    static final String TITLE = "Sign out";
    static final String VIEW_NAME = "signout.ftl";
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     * @param templateEngine the HTML template rendering engine.
     */
    public GetSignoutRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");

        LOG.config("GetSignoutRoute is initialized.");
    }

    /**
     * Render the WebCheckers Sign Out page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Sign Out page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSignoutRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", TITLE);

        // render the View
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}
