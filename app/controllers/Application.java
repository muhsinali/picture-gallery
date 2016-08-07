package controllers;

import com.google.common.io.Files;
import models.Place;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.io.IOException;

/**
 * This web application stores places of interest in a database and displays them either using a list or a grid layout.
 * The user can add, edit or delete places from the database.
 *
 * This controller handles all HTTP requests for this application.
 */
public class Application extends Controller {
    private static final Form<Place> addPlaceForm = Form.form(Place.class);


    public static Result showGrid(){
        int numColumns = 3;
        int numRows = (int) Math.ceil(Place.find.all().size() / (double) numColumns);
        return ok(grid.render(Place.find.all(), numRows, numColumns));
    }

    public static Result showList(){
        return ok(list.render(Place.find.all()));
    }


    public static Result addPlace(){
        return ok(placeForm.render(addPlaceForm));
    }


    public static Result deletePlace(long id){
        Place foundPlace = Place.findById(id);
        if(foundPlace == null){
            flash("error", String.format("Error: Product with id %d does not exist.", id));
            return showGrid();
        }
        foundPlace.delete();

        flash("success", "Place successfully deleted.");
        return showGrid();
    }

    /**
     * Shows the details of a particular place to the user.
     */
    public static Result details(Place place) {
        if(place == null){
            flash("error", "Error: Place could not be found.");
            return showGrid();
        }
        return ok(showPlace.render(place));
    }

    public static Result editForm(Place place) {
        Form<Place> filledForm = addPlaceForm.fill(place);
        return ok(placeForm.render(filledForm));
    }


    public static Result getPictureOfPlace(long id){
        Place foundPlace = Place.find.byId(id);
        return foundPlace != null ? ok(foundPlace.picture) : badRequest();
    }

    /**
     * Adds/updates a place according to the information placed in the form by the user.
     */
    public static Result upload() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Form<Place> boundForm = addPlaceForm.bindFromRequest();
        if(boundForm.hasErrors()){
            flash("error", "Please correct the form below.");
            return badRequest(placeForm.render(boundForm));
        }

        Http.MultipartFormData.FilePart filePart = body.getFile("picture");
        Place place = boundForm.get();

        // This checks that a picture was chosen for new places that are going to be stored in the database
        if (filePart == null && place.id == null) {
            flash("error", "Error: Missing picture. Please provide a picture when adding a place.");
            return showGrid();
        }

        // If a new picture was provided, assign it to the picture member variable
        if(filePart != null) {
            place.contentType = filePart.getContentType();
            try {
                place.picture = Files.toByteArray(filePart.getFile());
            } catch (IOException e) {
                return internalServerError("Could not save place");
            }
        }

        // Checks whether the place already exists in the database or is a new place to be added to the database
        if(place.id == null){
            place.save();
        } else {
            place.update();
        }

        return showGrid();
    }
}


