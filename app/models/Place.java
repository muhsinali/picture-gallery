package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhsin Ali on 10/07/2016.
 */

// TODO: need a validator to check if the uploaded file is actually an image

@Entity
public class Place extends Model {
    public static Finder<Long, Place> find = new Finder<>(Long.class, Place.class);

    @Id
    public Long id;

    @Constraints.Required
    public String name;
    @Lob
    public byte[] picture;
    public String contentType;
}
