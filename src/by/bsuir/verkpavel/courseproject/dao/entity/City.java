package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The persistent class for the city database table.
 * 
 */
@DatabaseTable(tableName = "city")
public class City implements Entity {

    @DatabaseField(generatedId = true)
    private int idCity;
    @DatabaseField
    private String name;

    public City() {
    }

    public int getIdCity() {
        return this.idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}