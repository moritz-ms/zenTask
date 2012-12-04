package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*; //wichtig, um die Modellklassen zu importieren und nutzen zu können
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render( 
            Project.find.all(),
            Task.find.all()
        )); 
    }
}