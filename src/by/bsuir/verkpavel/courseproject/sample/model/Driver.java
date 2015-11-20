package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the driver database table.
 * 
 */
@Entity
public class Driver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDriver;

	//bi-directional many-to-one association to Driverlicense
	@ManyToOne
	@JoinColumn(name="idDriverLicense")
	private Driverlicense driverlicense;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	private Employee employee;

	//bi-directional many-to-one association to Office
	@ManyToOne
	@JoinColumn(name="currentLocation")
	private Office office;

	public Driver() {
	}

	public int getIdDriver() {
		return this.idDriver;
	}

	public void setIdDriver(int idDriver) {
		this.idDriver = idDriver;
	}

	public Driverlicense getDriverlicense() {
		return this.driverlicense;
	}

	public void setDriverlicense(Driverlicense driverlicense) {
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