package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "driverlicense")
public class DriverLicense implements Entity {

    @DatabaseField(generatedId = true)
    private int idDriverLicense;

    @DatabaseField
    private String number;

    @DatabaseField(foreign = true, columnName = "idDriverLicenceCategory")
    private DriverLicenceCategory driverlicencecategory;

    public DriverLicense() {
    }

    public int getIdDriverLicense() {
        return this.idDriverLicense;
    }

    public void setIdDriverLicense(int idDriverLicense) {
        this.idDriverLicense = idDriverLicense;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public DriverLicenceCategory getDriverlicencecategory() {
        return this.driverlicencecategory;
    }

    public void setDriverlicencecategory(DriverLicenceCategory driverlicencecategory) {
        this.driverlicencecategory = driverlicencecategory;
    }

}