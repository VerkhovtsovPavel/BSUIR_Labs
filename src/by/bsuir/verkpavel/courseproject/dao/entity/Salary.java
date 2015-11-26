package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Salary")
public class Salary implements Entity {

    @DatabaseField(generatedId = true)
    private int idSalary;
    @DatabaseField
    private double baseRate;
    @DatabaseField
    private double raisingFactor;
    //FIXME Check work ASAP!!! 
    @DatabaseField(foreign = true, columnName = "idEmployee")
    private Employee employee;

    public Salary() {
    }

    public int getIdSalary() {
        return this.idSalary;
    }

    public void setIdSalary(int idSalary) {
        this.idSalary = idSalary;
    }

    public double getBaseRate() {
        return this.baseRate;
    }

    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    public double getRaisingFactor() {
        return this.raisingFactor;
    }

    public void setRaisingFactor(double raisingFactor) {
        this.raisingFactor = raisingFactor;
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}