package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;

public class Driverlicencecategory implements Entity {

    @DatabaseField(generatedId = true)
    private int idDriverLicenceCategory;
    @DatabaseField
    private String description;

    public Driverlicencecategory() {
    }

    public int getIdDriverLicenceCategory() {
        return this.idDriverLicenceCategory;
    }

    public void setIdDriverLicenceCategory(int idDriverLicenceCategory) {
        this.idDriverLicenceCategory = idDriverLicenceCategory;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}