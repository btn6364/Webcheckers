package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import static spark.Spark.halt;

public class PostSignoutRoute implements Route {
    private final TemplateEngine templateEngine;


    protected PostSignoutRoute(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    /**
     * handle post route for signing out.
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String handle(Request request, Response response) {

        String input = request.queryParams("button");
        if (input == null){
            return input; // the button wasn't clicked, sth went wrong.
        }


//        TODO
//        remove the session id of the user



        response.redirect(WebServer.SIGNOUT_URL);

        //End the else block as normal.
        halt();
        return null;

    }


}
