package controllers;

import com.google.common.io.Files;
import models.Place;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.io.IOException;


public class Application extends Controller {
    private static final Form<Place> addPlaceForm = Form.form(Place.class);


    public static Result showGrid(){
        int numPlaces = Place.find.all().size();
        return ok(grid.render(Place.find.all(), (int) Math.ceil(numPlaces / 3.0), 3));
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


    public static Result upload() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Form<Place> boundForm = addPlaceForm.bindFromRequest();
        if(boundForm.hasErrors()){
            flash("error", "Please correct the form below.");
            return badRequest(placeForm.render(boundForm));
        }

        Http.MultipartFormData.FilePart filePart = body.getFile("picture");
        Place place = boundForm.get();

        // Check to see if there's no picture for the place
        if (filePart == null && place.id == null) {
            flash("error", "Error: Missing picture. Please provide a picture when adding a place.");
            return showGrid();
        }

        // If a new picture was provided, execute this code
        if(filePart != null) {
            place.contentType = filePart.getContentType();
            try {
                place.picture = Files.toByteArray(filePart.getFile());
            } catch (IOException e) {
                return internalServerError("Could not save place");
            }
        }

        // Check whether the place is new or is a pre-existing one
        if(place.id == null){
            place.save();
        } else {
            place.update();
        }

        return showGrid();
    }
}


