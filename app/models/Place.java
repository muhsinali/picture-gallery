package models;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import play.data.validation.Constraints;
import play.mvc.PathBindable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



// TODO remove ebean dependencies - use that BasicDAO to store/retrieve stuff from the database
// TODO get the jsonFiles in public/ automatically loaded into the database at application startup
// TODO sort out that id issue that you get when you add a new place if it's still there in MongoDB

/**
 * Place - a point of interest that the user would like to store in the gallery.
 * A name, country and a picture must be provided. A description is optional.
 */

@Entity("places")
public class Place implements PathBindable<Place> {
    private static Morphia morphia = new Morphia();
    private static MongoClient client = new MongoClient("localhost", 27017);
    public static Datastore datastore = morphia.createDatastore(client, "places");


    @Id
    public Integer id;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String country;
    public byte[] picture;
    public String contentType;
    public String description;

    public Place(){
        // Left empty - this is for the PathBindable interface
    }


    // Used for converting JSON data and storing it into the database
    public Place(org.bson.Document document){
        id =  document.getInteger("_id");
        name = document.getString("name");
        country = document.getString("country");
        description = document.getString("description");
        contentType = document.getString("content_type");
        try {
            this.picture = Files.readAllBytes(Paths.get(document.getString("picture")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    /**
     * Returns a place stored in the database using the unique ID provided
     */
    public static Place findById(int id){
        return datastore.createQuery(Place.class).field("id").equal(id).get();
    }


    public static List<Place> getAllPlaces(){
        return datastore.createQuery(Place.class).asList();
    }

    public String toString() {
        return Integer.toString(id);
    }


    public Integer getId(){return id;}

    @Override
    public Place bind(String key, String value){
        return findById(new Integer(value));
    }

    @Override
    public String javascriptUnbind(){
        return Integer.toString(this.id);
    }

    @Override
    public String unbind(String key){
        return Integer.toString(this.id);
    }
}
