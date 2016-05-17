package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "markparcel")
public class MarkParcel implements Entity, Describable {

    @DatabaseField(generatedId = true)
    private int idMarkParcel;
    @DatabaseField
    private String description;
    @DatabaseField
    private float rate;

    public MarkParcel() {
    }

    public int getIdMarkParcel() {
        return this.idMarkParcel;
    }

    public void setIdMarkParcel(int idMarkParcel) {
        this.idMarkParcel = idMarkParcel;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

}