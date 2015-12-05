package by.bsuir.verkpavel.courseproject.dao.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.Entity;

@DatabaseTable(tableName = "office")
public class Office implements Entity, Describable {

    @DatabaseField(generatedId = true)
    private int idOffice;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idStreet")
    private Street street;
    @DatabaseField
    private int home;
    @DatabaseField
    private String housing;
    @DatabaseField
    private int room;

    public Office() {
    }

    public int getIdOffice() {
        return this.idOffice;
    }

    public void setIdOffice(int idOffice) {
        this.idOffice = idOffice;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Street getStreet() {
        return this.street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    @Override
    public String getDescription() {
       return getStreet().getCity()+", " + getStreet().getName() + ", "+getHome();
    }

}