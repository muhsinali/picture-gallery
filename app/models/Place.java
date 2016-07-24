package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.mvc.PathBindable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by Muhsin Ali on 10/07/2016.
 */

// TODO: need a validator to check if the uploaded file is actually an image

@Entity
public class Place extends Model implements PathBindable<Place> {
    public static Finder<Long, Place> find = new Finder<>(Long.class, Place.class);

    @Id
    public Long id;

    @Constraints.Required
    public String name;
    @Lob
    public byte[] picture;
    public String contentType;

    public Place(){
        // Left empty - for the PathBindable interface
    }

    public static Place findById(long id){
        return find.where().eq("id", id).findUnique();
    }

    public String toString() {
        return Long.toString(id);
    }


    @Override
    public Place bind(String key, String value){
        System.out.println(key);
        System.out.println(value);
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
