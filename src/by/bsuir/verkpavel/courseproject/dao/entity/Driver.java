package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;

public class Driver implements Entity {

    @DatabaseField(generatedId = true)
    private int idDriver;

    @DatabaseField(foreign = true, columnName = "idDriverLicense")
    private DriverLicense driverlicense;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idEmployee")
    private Employee employee;

    @DatabaseField(foreign = true, columnName = "currentLocation")
    private Office office;

    public Driver() {
    }

    public int getIdDriver() {
        return this.idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public DriverLicense getDriverlicense() {
        return this.driverlicense;
    }

    public void setDriverlicense(DriverLicense driverlicense) {
        this.driverlicense = driverlicense;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Office getOffice() {
        return this.office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

}