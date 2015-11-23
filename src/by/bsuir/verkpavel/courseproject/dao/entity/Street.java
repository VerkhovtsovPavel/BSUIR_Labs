package by.bsuir.verkpavel.courseproject.dao.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "street")
public class Street implements Serializable {
    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId = true)
    private int idStreet;
    @DatabaseField
    private String name;

    @DatabaseField(foreign = true, columnName = "idCity")
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