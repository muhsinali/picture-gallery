import controllers.PlaceDAO;
import models.Place;
import org.bson.Document;
import play.Application;
import play.GlobalSettings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by Muhsin Ali on 18/09/2016.
 */

/**
 * Global is used to populate/clear the database at application startup/shutdown
 */
public class Global extends GlobalSettings {
  @Override
  public void onStart(Application app) {
    Global global = new Global();
    PlaceDAO.drop();

    File resFolder = new File("./public/jsonFiles");
    File[] jsonFiles = resFolder.listFiles(f -> f.isFile() && f.canRead() && f.getName().endsWith(".json"));
    global.loadPlaces(jsonFiles);
  }

  @Override
  public void onStop(Application app){PlaceDAO.drop();}


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
      PlaceDAO.save(new Place(parsedDocument));
    }
  }
}
