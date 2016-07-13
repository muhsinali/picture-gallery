package models;

import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhsin Ali on 10/07/2016.
 */

// TODO: need a validator to check if the uploaded file is actually an image

@Entity
public class Place {
    public static List<Place> places = new ArrayList<>();

    @Id
    public Long id;

    @Constraints.Required
    public String name;
    @Constraints.Required
    public byte[] picture;

    public String contentType;
}
