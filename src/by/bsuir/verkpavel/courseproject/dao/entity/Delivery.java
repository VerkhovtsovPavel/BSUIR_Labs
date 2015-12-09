package by.bsuir.verkpavel.courseproject.dao.entity;

import java.util.Date;

import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;

public class Delivery implements Entity, Describable {

    @DatabaseField(generatedId = true)
    private int idDelivery;

    @DatabaseField
    private Date endDate;

    @DatabaseField
    private Date startDate;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idCorporateCar")
    private CorporateCar corporatecar;

    @DatabaseField(foreign = true, columnName = "fromOffice")
    private Office fromOffice;

    @DatabaseField(foreign = true, columnName = "toOffice")
    private Office toOffice;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idDeliveryStatus")
    private DeliveryStatus deliverystatus;
    
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idDriver")
    private Driver driver;
    
    public Delivery() {
    }

    public int getIdDelivery() {
        return this.idDelivery;
    }

    public void setIdDelivery(int idDelivery) {
        this.idDelivery = idDelivery;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public CorporateCar getCorporateCar() {
        return this.corporatecar;
    }

    public void setCorporatecar(CorporateCar corporatecar) {
        this.corporatecar = corporatecar;
    }

    public Office getFromOffice() {
        return this.fromOffice;
    }

    public void setFromOffice(Office office) {
        this.fromOffice = office;
    }

    public Office getToOffice() {
        return this.toOffice;
    }

    public void setToOffice(Office office) {
        this.toOffice = office;
    }

    public DeliveryStatus getDeliveryStatus() {
        return this.deliverystatus;
    }

    public void setDeliverystatus(DeliveryStatus deliverystatus) {
        this.deliverystatus = deliverystatus;
    }
    
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String getDescription() {
        return String.format("%d %s", getIdDelivery(), getCorporateCar().getNumber(), getDriver().getEmployee().getFullName());
    }

}