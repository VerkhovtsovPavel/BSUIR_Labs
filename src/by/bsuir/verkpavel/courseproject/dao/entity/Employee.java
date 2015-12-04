package by.bsuir.verkpavel.courseproject.dao.entity;

import java.util.Date;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "employee")
public class Employee implements Entity {

    @DatabaseField(generatedId = true)
    private int idEmployee;

    @DatabaseField
    private Date birthday;

    @DatabaseField
    private String fullName;

    @DatabaseField
    private Date hireDate;
    
    @DatabaseField
    private String phoneNumber;
    
    @DatabaseField
    private String eMail;
    

    @DatabaseField(foreign = true, columnName = "idAuthentication")
    private Authentication authentication;

    @DatabaseField(foreign = true, columnName = "idOffice")
    private Office office;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idPermissions")
    private Permission permission;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idPosition")
    private Position position;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idSalary")
    private Salary salary;

    public Employee() {
    }

    public int getIdEmployee() {
        return this.idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Authentication getAuthentication() {
        return this.authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Office getOffice() {
        return this.office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Salary getSalary() {
        return this.salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

}