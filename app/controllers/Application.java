package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*; //wichtig, um die Modellklassen zu importieren und nutzen zu können
import views.html.*;

public class Application extends Controller {

    @Security.Authenticated(Secured.class) //### nicht so richtig klar.
    public static Result index() {
        return ok(index.render( 
            Project.find.all(),
            Task.find.all()
        )); 
    }
    public static Result login() {
	return ok(
	    login.render(form(Login.class))
	);
    }

    public static Result authenticate() {
	Form<Login> loginForm = form(Login.class).bindFromRequest();
	if (loginForm.hasErrors()) { //if validation fails we return a status of 400 Bad Request
	    return badRequest(login.render(loginForm));
	} else { // if validation was successful we put an attribute email with its values to the session
	    session().clear();
	    session("email", loginForm.get().email);
	    return redirect(
		routes.Application.index() // after setting the user in the session we issue an HTTP redirect to the dashboard.
	    );
	}
    }
    
    public static class Login {
	    public String email;
	    public String password;
	  
	    public String validate() {
		    if(User.authenticate(email, password) == null) {
			    return "Invalid user or password";
		    }
		    return null;
		  
	    }
    }
}