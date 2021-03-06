package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "paymentssystemtype")
public class PaymentsSystemType implements Entity, Describable {

    @DatabaseField(generatedId = true)
    private int idPaymentsSystemType;
    @DatabaseField
    private String description;

    public PaymentsSystemType() {
    }

    public int getIdPaymentsSystemType() {
        return this.idPaymentsSystemType;
    }

    public void setIdPaymentsSystemType(int idPaymentsSystemType) {
        this.idPaymentsSystemType = idPaymentsSystemType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}