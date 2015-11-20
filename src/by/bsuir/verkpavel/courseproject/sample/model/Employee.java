package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idEmployee;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String fullName;

	private String hireDate;

	//bi-directional many-to-one association to Driver
	@OneToMany(mappedBy="employee")
	private List<Driver> drivers;

	//bi-directional many-to-one association to Authentication
	@ManyToOne
	@JoinColumn(name="idAuthentication")
	private Authentication authentication;

	//bi-directional many-to-one association to Office
	@ManyToOne
	@JoinColumn(name="idOffice")
	private Office office;

	//bi-directional many-to-one association to Permission
	@ManyToOne
	@JoinColumn(name="idPermissions")
	private Permission permission;

	//bi-directional many-to-one association to Position
	@ManyToOne
	@JoinColumn(name="idPosition")
	private Position position;

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

	public String getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public List<Driver> getDrivers() {
		return this.drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public Driver addDriver(Driver driver) {
		getDrivers().add(driver);
		driver.setEmployee(this);

		return driver;
	}

	public Driver removeDriver(Driver driver) {
		getDrivers().remove(driver);
		driver.setEmployee(null);

		return driver;
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

}