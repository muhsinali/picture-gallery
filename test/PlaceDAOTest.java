import com.google.common.io.Files;
import controllers.PlaceDAO;
import models.Place;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;


public class PlaceDAOTest {
  @Test
  public void beAddable() {
    running(fakeApplication(), () -> {
      int numPlaces = PlaceDAO.getNumberOfPlaces();

      try {
        Place example = new Place();
        example.setId(PlaceDAO.generateId());
        example.setName("London");
        example.setCountry("United Kingdom");
        example.setDescription("This is an example description.");
        example.setPicture(Files.toByteArray(new File("../../public/images/places/london.jpg")));
        PlaceDAO.save(example);
      }catch (IOException e){
        e.printStackTrace();
      }

      assertThat(PlaceDAO.getNumberOfPlaces()).isEqualTo(numPlaces + 1);
    });
  }

  @Test
  public void beDeletable() {
    running(fakeApplication(), () -> {
      int numPlaces = PlaceDAO.getNumberOfPlaces();
      assertThat(numPlaces).isGreaterThan(0);
      List<Place> places = PlaceDAO.getAllPlaces();
      PlaceDAO.delete(places.get(0));
      assertThat(PlaceDAO.getNumberOfPlaces()).isEqualTo(numPlaces - 1);
    });
  }
}
