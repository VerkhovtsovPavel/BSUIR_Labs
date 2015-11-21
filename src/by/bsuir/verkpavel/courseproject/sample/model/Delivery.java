package by.bsuir.verkpavel.courseproject.sample.model;

import java.util.Date;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;

public class Delivery implements Entity {

    @DatabaseField(generatedId = true)
    private int idDelivery;

    @DatabaseField
    private Date endDate;

    @DatabaseField
    private Date startDate;

    @DatabaseField(foreign = true, columnName = "idCorporateCar")
    private Corporatecar corporatecar;

    @DatabaseField(foreign = true, columnName = "fromOffice")
    private Office fromOffice;

    @DatabaseField(foreign = true, columnName = "toOffice")
    private Office toOffice;

    @DatabaseField(foreign = true, columnName = "idDeliveryStatus")
    private Deliverystatus deliverystatus;

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

    public Corporatecar getCorporatecar() {
        return this.corporatecar;
    }

    public void setCorporatecar(Corporatecar corporatecar) {
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

    public Deliverystatus getDeliverystatus() {
        return this.deliverystatus;
    }

    public void setDeliverystatus(Deliverystatus deliverystatus) {
        this.deliverystatus = deliverystatus;
    }

}