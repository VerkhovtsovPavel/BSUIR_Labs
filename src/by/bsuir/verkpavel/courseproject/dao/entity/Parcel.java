package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;

public class Parcel implements Entity {

    @DatabaseField(generatedId = true)
    private int idParcel;
    @DatabaseField
    private int depth;
    @DatabaseField
    private int height;
    @DatabaseField
    private double weight;
    @DatabaseField
    private int width;

    @DatabaseField(foreign = true, columnName = "idClient")
    private Client client;

    @DatabaseField(foreign = true, columnName = "idMarkParcel")
    private Markparcel markparcel;

    @DatabaseField(foreign = true, columnName = "idPayments")
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

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
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

    public Markparcel getMarkparcel() {
        return this.markparcel;
    }

    public void setMarkparcel(Markparcel markparcel) {
        this.markparcel = markparcel;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}