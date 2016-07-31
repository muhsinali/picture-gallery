package controllers;

import com.google.common.io.Files;
import models.Place;
import play.data.Form;
import play.mvc.*;
import views.html.grid;
import views.html.placeForm;
import views.html.showPlace;

import java.io.IOException;


public class Application extends Controller {
    private static final Form<Place> addPlaceForm = Form.form(Place.class);


    public static Result showGrid(){
        System.out.println("Entered showGrid()");
        return ok(grid.render(Place.find.all()));
    }


    public static Result addPlace(){
        return ok(placeForm.render(addPlaceForm));
    }


    public static Result deletePlace(long id){
        System.out.println("Entered deletePlace()");
        Place foundPlace = Place.findById(id);
        if(foundPlace == null){
            return notFound(String.format("Product with id %d does not exist", id));
        }
        System.out.println("Just about to delete the place");
        foundPlace.delete();

        for(Place p : Place.find.all()){
            System.out.println(p.id);
        }
        System.out.println(String.format("Deleted the place and there's %d places left over", Place.find.all().size()));
        System.out.println("Just about to render the grid");

        return showGrid();
    }


    public static Result details(Place place) {
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

        if (filePart == null && Place.findById(place.id).picture == null) {
            flash("error", "Missing picture");
            return notFound();
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


