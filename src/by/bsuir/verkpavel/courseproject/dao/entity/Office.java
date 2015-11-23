package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The persistent class for the office database table.
 * 
 */
@DatabaseTable(tableName = "office")
public class Office implements Entity {

    @DatabaseField(generatedId = true)
    private int idOffice;

    @DatabaseField(foreign = true, columnName = "idStreet")
    private Street street;

    public Office() {
    }

    public int getIdOffice() {
        return this.idOffice;
    }

    public void setIdOffice(int idOffice) {
        this.idOffice = idOffice;
    }

  
   

    

   

   

   
    

   

   

    

   
    public Street getStreet() {
        return this.street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

}