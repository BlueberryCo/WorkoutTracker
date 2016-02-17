package blueberryco.entities;

import android.content.Context;

import java.io.Serializable;
import java.util.Date;

import blueberryco.database.DatabaseHelper;

/**
 * Created by boiko on 12/1/2015.
 */
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int CLIENT_TYPE_OWNER = 1;
    public static final int CLIENT_TYPE_CLIENT = 2;

    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Float height;
    private Float weight;
    private String phone;
    private String email;
    private int type;

    public Client(){
        this.type = Client.CLIENT_TYPE_CLIENT;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public Date getBirthDate(){
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }

    public Float getHeight(){
        return this.height;
    }

    public void setHeight(float height){
        this.height = height;
    }

    public Float getWeight(){
        return this.weight;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getType(){
        return this.type;
    }

    public void setType(int type){
        this.type = type;
    }

    public String birthDateAsString(){
        if(this.birthDate != null){
            return Util.dateToString(this.birthDate);
        }

        return Util.EMPTY_STRING;
    }

    @Override
    public String toString(){
        return this.firstName + " " + this.lastName;
    }

    public static boolean hasOwner(Context context){
        DatabaseHelper helper = new DatabaseHelper(context);

        Client owner = helper.getOwner();

        return owner != null;
    }
}
