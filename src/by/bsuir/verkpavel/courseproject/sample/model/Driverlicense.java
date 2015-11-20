package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the driverlicense database table.
 * 
 */
@Entity
public class Driverlicense implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDriverLicense;

	private String number;

	//bi-directional many-to-one association to Driver
	@OneToMany(mappedBy="driverlicense")
	private List<Driver> drivers;

	//bi-directional many-to-one association to Driverlicencecategory
	@ManyToOne
	@JoinColumn(name="idDriverLicenceCategory")
	private Driverlicencecategory driverlicencecategory;

	public Driverlicense() {
	}

	public int getIdDriverLicense() {
		return this.idDriverLicense;
	}

	public void setIdDriverLicense(int idDriverLicense) {
		this.idDriverLicense = idDriverLicense;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<Driver> getDrivers() {
		return this.drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public Driver addDriver(Driver driver) {
		getDrivers().add(driver);
		driver.setDriverlicense(this);

		return driver;
	}

	public Driver removeDriver(Driver driver) {
		getDrivers().remove(driver);
		driver.setDriverlicense(null);

		return driver;
	}

	public Driverlicencecategory getDriverlicencecategory() {
		return this.driverlicencecategory;
	}

	public void setDriverlicencecategory(Driverlicencecategory driverlicencecategory) {
		this.driverlicencecategory = driverlicencecategory;
	}

}