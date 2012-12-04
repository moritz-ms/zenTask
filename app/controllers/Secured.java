package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import models.*;

public class Secured extends Security.Authenticator {

    // used to get the username of the current logged in user.
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    // if ###which? method returns null, the authenticator will block the request and invoke this method, which will redirect to our login screen
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.login());
    }
}