import controllers.PlaceDAO;
import org.junit.Test;
import play.mvc.Result;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;


public class ApplicationTest {
    @Test
    public void showList(){
        running(fakeApplication(), () -> {
            Result result = callAction(
                controllers.routes.ref.Application.showList(),
                fakeRequest()
            );
            assertThat(status(result)).isEqualTo(OK);
        });
    }

    @Test
    public void deleteAPlace(){
        running(fakeApplication(), () -> {
            int numPlaces = PlaceDAO.getNumberOfPlaces();
            assertThat(numPlaces).isGreaterThan(0);
            Result result = callAction(
                controllers.routes.ref.Application.deletePlace(1),
                fakeRequest()
            );
            assertThat(status(result)).isEqualTo(OK);
            assertThat(PlaceDAO.getNumberOfPlaces()).isEqualTo(numPlaces - 1);
        });
    }

    @Test
    public void getPictureOfPlace(){
        running(fakeApplication(), () -> {
            Result result = callAction(
                controllers.routes.ref.Application.getPictureOfPlace(1),
                fakeRequest()
            );
            assertThat(status(result)).isEqualTo(OK);
        });
    }
}
