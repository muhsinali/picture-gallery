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
import java.util.Comparator;
import java.util.List;



/**
 * Place - a point of interest that the user would like to store in the gallery.
 * A name, country and a picture must be provided. A description is optional.
 */

@Entity("places")
public class Place implements PathBindable<Place> {
    private static Morphia morphia = new Morphia();
    private static MongoClient client = new MongoClient("localhost", 27017);
    public static Datastore datastore = morphia.createDatastore(client, "places");


    // Note: Form only works properly if these member variables are public
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


    // Used for converting JSON data and storing it in the database
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

    public Integer getId(){return id;}

    public static List<Place> getAllPlaces(){
        return datastore.createQuery(Place.class).asList();
    }

    public static Integer getNumberOfPlaces(){
        return (int) datastore.createQuery(Place.class).countAll();
    }


    public String getCountry(){return country;}

    public String getName(){return name;}

    public String getDescription(){return description;}

    //public String getContentType(){return contentType;}

    public byte[] getPicture(){return this.picture;}

    //public void setId(Integer id){this.id = id;}

    public void generateId(){
        List<Place> allPlaces = datastore.createQuery(Place.class).field("id").exists().asList();
        allPlaces.sort(new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                return (o1.id < o2.id) ? -1 : ((o1.id.equals(o2.id)) ? 0 : 1);
            }
        });
        int newId = 1;
        for(Place place : allPlaces){
            if(newId == place.id){
                newId++;
            }
        }
        this.id = newId;
    }

    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    public void setPicture(byte[] pictureArray){this.picture = pictureArray;}

    public String toString() {
        return Integer.toString(id);
    }

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
