package controllers;

import com.mongodb.MongoClient;
import models.Place;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

/**
 * Created by Muhsin Ali on 04/02/2017.
 */
public class PlaceDAO {
  private static Morphia morphia = new Morphia();
  private static MongoClient client = new MongoClient("localhost", 27017);
  private static Datastore datastore = morphia.createDatastore(client, "places");

  private static int numPlacesGenerated = 0;

  // TODO check writeResult
  public static void delete(Place place){
    datastore.delete(place);
  }

  public static void drop(){
    datastore.getCollection(Place.class).drop();
  }

  /**
   * Returns a place stored in the database using the unique ID provided
   */
  public static Place findById(int id){return datastore.createQuery(Place.class).field("id").equal(id).get();}

  public static List<Place> getAllPlaces(){return datastore.createQuery(Place.class).asList();}

  public static Integer getNumberOfPlaces(){return (int) datastore.createQuery(Place.class).countAll();}

  public static int generateId(){
    numPlacesGenerated++;
    return numPlacesGenerated;
  }

  public static void save(Place place){
    datastore.save(place);
  }
}
