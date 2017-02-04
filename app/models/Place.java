package models;

import controllers.PlaceDAO;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import play.data.validation.Constraints;
import play.mvc.PathBindable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



/**
 * Place - a point of interest that the user would like to store in the gallery.
 * A name, country and a picture must be provided. A description is optional.
 */

@Entity("places")
public class Place implements PathBindable<Place> {
    @Id
    private Integer id;
    @Constraints.Required
    private String name;
    @Constraints.Required
    private String country;
    private byte[] picture;
    private String contentType;
    private String description;


    public Place(){
        // Left empty - this is for the PathBindable interface
    }

    // Used for converting JSON data and storing it in the database
    public Place(org.bson.Document document){
        id =  PlaceDAO.generateId();
        name = document.getString("name");
        country = document.getString("country");
        description = document.getString("description");
        contentType = document.getString("content_type");
        try {
            picture = Files.readAllBytes(Paths.get(document.getString("picture")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public Integer getId(){return id;}

    public String getCountry(){return country;}

    public String getName(){return name;}

    public String getDescription(){return description;}

    public byte[] getPicture(){return picture;}

    public void setId(Integer id){this.id = id;}

    public void setName(String name){this.name = name;}

    public void setCountry(String country){this.country = country;}

    public void setDescription(String description){this.description = description;}

    public void setContentType(String contentType){this.contentType = contentType;}

    public void setPicture(byte[] pictureArray){picture = pictureArray;}

    public String toString() {return Integer.toString(id);}

    @Override
    public Place bind(String key, String value){return PlaceDAO.findById(new Integer(value));}

    @Override
    public String javascriptUnbind(){return Integer.toString(id);}

    @Override
    public String unbind(String key){return Integer.toString(id);}
}
