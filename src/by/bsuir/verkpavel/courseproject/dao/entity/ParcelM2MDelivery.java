package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "parcel_m2m_delivery")
public class ParcelM2MDelivery implements Entity {

    @DatabaseField(generatedId = true)
    private int idParcel_M2M_Delivery;

    @DatabaseField(foreign = true, columnName = "idDelivery")
    private Delivery delivery;

    @DatabaseField(foreign = true, columnName = "idParcel")
    private Parcel parcel;

    public ParcelM2MDelivery() {
    }

    public int getIdParcel_M2M_Delivery() {
        return this.idParcel_M2M_Delivery;
    }

    public void setIdParcel_M2M_Delivery(int idParcel_M2M_Delivery) {
        this.idParcel_M2M_Delivery = idParcel_M2M_Delivery;
    }

    public Delivery getDelivery() {
        return this.delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Parcel getParcel() {
        return this.parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

}