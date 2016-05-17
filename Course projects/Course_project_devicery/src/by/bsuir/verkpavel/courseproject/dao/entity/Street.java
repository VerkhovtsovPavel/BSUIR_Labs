package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Street")
public class Street implements Entity {

    @DatabaseField(generatedId = true)
    private int idStreet;
    @DatabaseField
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idCity")
    private City city;

    public Street() {
    }

    public int getIdStreet() {
        return this.idStreet;
    }

    public void setId(int idStreet) {
        this.idStreet = idStreet;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}