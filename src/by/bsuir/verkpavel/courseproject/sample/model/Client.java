package by.bsuir.verkpavel.courseproject.sample.model;

import java.util.Date;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "client")
public class Client implements Entity {

    @DatabaseField(generatedId = true)
    private int idClient;

    @DatabaseField
    private Date addDate;
    @DatabaseField
    private String fullName;

    @ForeignCollectionField
    private ForeignCollection<Parcel> parcels;

    public Client() {
    }

    public int getIdClient() {
        return this.idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Date getAddDate() {
        return this.addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ForeignCollection<Parcel> getParcels() {
        return this.parcels;
    }
}