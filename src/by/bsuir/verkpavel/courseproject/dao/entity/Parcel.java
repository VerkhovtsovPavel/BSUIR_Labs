package by.bsuir.verkpavel.courseproject.dao.entity;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

import by.bsuir.verkpavel.courseproject.dao.Entity;

public class Parcel implements Entity {

    @DatabaseField(generatedId = true)
    private int idParcel;
    @DatabaseField
    private int depth;
    @DatabaseField
    private int height;
    @DatabaseField
    private int weight;
    @DatabaseField
    private int width;
    
    @DatabaseField
    private Date acceptanceDate;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idClient")
    private Client client;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idMarkParcel")
    private MarkParcel markparcel;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idPayments")
    private Payment payment;

    public Parcel() {
    }

    public int getIdParcel() {
        return this.idParcel;
    }

    public void setIdParcel(int idParcel) {
        this.idParcel = idParcel;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public MarkParcel getMarkParcel() {
        return this.markparcel;
    }

    public void setMarkparcel(MarkParcel markparcel) {
        this.markparcel = markparcel;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }
}