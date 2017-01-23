import com.mongodb.DBCollection;
import models.Place;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.io.*;


/**
 * Created by Muhsin Ali on 18/09/2016.
 */

/**
 * This file is used to load the information in public/jsonFiles into the database.
 * It is to be run before application startup.
 */
public class LoadDatabase {
  private BasicDAO<Place, Datastore> basicDAO;

  private LoadDatabase(){
    basicDAO = new BasicDAO<>(Place.class, Place.datastore);
  }


  public static void main(String[] args) {
    LoadDatabase loadDataApp = new LoadDatabase();
    DBCollection collection = Place.datastore.getCollection(Place.class);
    collection.drop();

    File resFolder = new File("./public/jsonFiles");

    // Note: Initially used lambda expression, but that resulted in an "Unknown constant 18" compile-time error
    File[] jsonFiles = resFolder.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        return file.canRead() && file.getName().endsWith(".json");
      }
    });

    loadDataApp.saveEntities(jsonFiles);
    if(jsonFiles != null){
      loadDataApp.displayEntities(jsonFiles.length);
    }
  }


  // Saves all Places that are in JSON files in the res/ directory
  private void saveEntities(File[] jsonFiles){
    // Load JSON data from each file in res/ directory into the database
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


  // Retrieves all Places stored in database - used to confirm that info has been stored in the database
  private void displayEntities(int numberOfJsonFiles){
    for(int i = 1; i <= numberOfJsonFiles; i++) {
      System.out.println(Place.findById(i));
    }
  }
}
