import com.mongodb.DBCollection;
import models.Place;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import play.Application;
import play.GlobalSettings;

import java.io.*;


/**
 * Created by Muhsin Ali on 18/09/2016.
 */

/**
 * Global is used to populate/clear the database at application startup/shutdown
 */
public class Global extends GlobalSettings {
  private BasicDAO<Place, Datastore> basicDAO;

  public Global(){
    basicDAO = new BasicDAO<>(Place.class, Place.datastore);
  }

  @Override
  public void onStart(Application app) {
    Global global = new Global();
    DBCollection collection = Place.datastore.getCollection(Place.class);
    collection.drop();

    File resFolder = new File("./public/jsonFiles");
    File[] jsonFiles = resFolder.listFiles(f -> f.isFile() && f.canRead() && f.getName().endsWith(".json"));
    global.loadPlaces(jsonFiles);
  }

  @Override
  public void onStop(Application app){
    Place.datastore.getCollection(Place.class).drop();
  }


  // Loads data from each JSON file in public/jsonFiles into the database
  private void loadPlaces(File[] jsonFiles){
    for(File aJsonFile: jsonFiles){
      StringBuilder jsonToParse = new StringBuilder();
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(aJsonFile))) {
        String currentLine;
        while((currentLine = bufferedReader.readLine()) != null){
          jsonToParse.append(currentLine);
        }
      } catch (IOException e){
        e.printStackTrace();
      }
      Document parsedDocument = Document.parse(jsonToParse.toString());
      basicDAO.save(new Place(parsedDocument));
    }
  }
}
