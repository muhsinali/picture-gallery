package controllers;

import com.google.common.io.Files;
import models.Place;
import play.data.Form;
import play.mvc.*;
import views.html.list;
import views.html.placeForm;

import java.io.File;
import java.io.IOException;


public class Application extends Controller {
    private static final Form<Place> addPlaceForm = Form.form(Place.class);

    public static Result list() {
        return ok(list.render(Place.find.all()));
    }

    public static Result addPlace(){
        return ok(placeForm.render(addPlaceForm));
    }


    public static Result deletePlace(long id){
        Place foundPlace = Place.findById(id);
        if(foundPlace == null){
            return notFound(String.format("Product with id %d does not exist", id));
        }
        foundPlace.delete();
        return ok(list.render(Place.find.all()));
    }

    public static Result details(Place place) {
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

        // TODO: get a regression here when you try to update a picture. Why? bc when you show the filled form, the picture part
        // TODO: isn't selected. Check if it is selected in the Warehouse application and copy from that.
        Http.MultipartFormData.FilePart filePart = body.getFile("picture");
        if (filePart == null) {
            flash("error", "Missing file");
            return notFound();
        }

        Place place = boundForm.get();
        place.contentType = filePart.getContentType();
        try {
            place.picture = Files.toByteArray(filePart.getFile());
        } catch (IOException e){
            return internalServerError("Could not save place");
        }

        // Check whether you need to edit or add the Place
        if(place.id == null){
            place.save();
        } else {
            place.update();
        }

        return ok(list.render(Place.find.all()));
    }
}
