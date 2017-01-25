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
 * Global is used to populate the database at application startup using JSON files in public/jsonFiles.
 */
public class Global extends GlobalSettings {
  private BasicDAO<Place, Datastore> basicDAO;

  public Global(){
    basicDAO = new BasicDAO<>(Place.class, Place.datastore);
  }


  public void onStart(Application app) {
    Global loadDataApp = new Global();
    DBCollection collection = Place.datastore.getCollection(Place.class);
    collection.drop();

    File resFolder = new File("./public/jsonFiles");
    File[] jsonFiles = resFolder.listFiles(f -> f.canRead() && f.getName().endsWith(".json"));
    loadDataApp.saveEntities(jsonFiles);
  }


  // Loads data from each JSON file in public/jsonFiles into the database
  private void saveEntities(File[] jsonFiles){
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
      this.basicDAO.save(new Place(parsedDocument));
    }
  }


  // Prints id of all Places stored in database - used to check that Places has been loaded
  // into the database correctly (for debugging purposes)
  private void displayEntities(int numberOfJsonFiles){
    for(int i = 1; i <= numberOfJsonFiles; i++) {
      System.out.println(Place.findById(i));
    }
  }
}
