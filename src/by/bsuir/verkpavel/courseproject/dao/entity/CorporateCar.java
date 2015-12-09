package by.bsuir.verkpavel.courseproject.dao.entity;

import java.util.Date;

import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "corporatecar")
public class CorporateCar implements Entity, Describable {

    @DatabaseField(generatedId = true)
    private int idCorporateCar;

    @DatabaseField
    private Date buyDate;
    @DatabaseField
    private String mark;
    @DatabaseField
    private int maxDepth;
    @DatabaseField
    private int maxHeight;
    @DatabaseField
    private int maxWeigth;
    @DatabaseField
    private int maxWidth;
    @DatabaseField
    private String number;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "requiredDriverLicenceCategory")
    private DriverLicenceCategory driverlicencecategory;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "currentLocation")
    private Office office;

    public CorporateCar() {
    }

    public int getIdCorporateCar() {
        return this.idCorporateCar;
    }

    public void setIdCorporateCar(int idCorporateCar) {
        this.idCorporateCar = idCorporateCar;
    }

    public Date getBuyDate() {
        return this.buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getMark() {
        return this.mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getMaxDepth() {
        return this.maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public int getMaxHeight() {
        return this.maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxWeigth() {
        return this.maxWeigth;
    }

    public void setMaxWeigth(int maxWeigth) {
        this.maxWeigth = maxWeigth;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
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

    public Office getOffice() {
        return this.office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @Override
    public String getDescription() {
        return String.format("%s %s %s", getMark(), getDriverlicencecategory().getDescription(), getNumber());
    }
}