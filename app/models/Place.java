package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.mvc.PathBindable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Place - a point of interest that the user would like to store in the gallery.
 * A name, country and a picture must be provided. A description is optional.
 */

@Entity
public class Place extends Model implements PathBindable<Place> {
    public static Finder<Long, Place> find = new Finder<>(Long.class, Place.class);

    @Id
    public Long id;

    @Constraints.Required
    public String name;
    @Constraints.Required
    public String country;

    @Lob
    public byte[] picture;
    public String contentType;

    public String description;

    public Place(){
        // Left empty - this is for the PathBindable interface
    }

    /**
     * Returns a place stored in the database using the unique ID provided
     */
    public static Place findById(long id){
        return find.where().eq("id", id).findUnique();
    }

    public String toString() {
        return Long.toString(id);
    }


    @Override
    public Place bind(String key, String value){
        return findById(new Long(value));
    }

    @Override
    public String javascriptUnbind(){
        return Long.toString(this.id);
    }

    @Override
    public String unbind(String key){
        return Long.toString(this.id);
    }
}
