package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the office database table.
 * 
 */
@Entity
public class Office implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idOffice;

	//bi-directional many-to-one association to Corporatecar
	@OneToMany(mappedBy="office")
	private List<Corporatecar> corporatecars;

	//bi-directional many-to-one association to Delivery
	@OneToMany(mappedBy="office1")
	private List<Delivery> deliveries1;

	//bi-directional many-to-one association to Delivery
	@OneToMany(mappedBy="office2")
	private List<Delivery> deliveries2;

	//bi-directional many-to-one association to Driver
	@OneToMany(mappedBy="office")
	private List<Driver> drivers;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="office")
	private List<Employee> employees;

	//bi-directional many-to-one association to Street
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Street_idCity", referencedColumnName="idCity"),
		@JoinColumn(name="Street_idStreet", referencedColumnName="idStreet")
		})
	private Street street;

	public Office() {
	}

	public int getIdOffice() {
		return this.idOffice;
	}

	public void setIdOffice(int idOffice) {
		this.idOffice = idOffice;
	}

	public List<Corporatecar> getCorporatecars() {
		return this.corporatecars;
	}

	public void setCorporatecars(List<Corporatecar> corporatecars) {
		this.corporatecars = corporatecars;
	}

	public Corporatecar addCorporatecar(Corporatecar corporatecar) {
		getCorporatecars().add(corporatecar);
		corporatecar.setOffice(this);

		return corporatecar;
	}

	public Corporatecar removeCorporatecar(Corporatecar corporatecar) {
		getCorporatecars().remove(corporatecar);
		corporatecar.setOffice(null);

		return corporatecar;
	}

	public List<Delivery> getDeliveries1() {
		return this.deliveries1;
	}

	public void setDeliveries1(List<Delivery> deliveries1) {
		this.deliveries1 = deliveries1;
	}

	public Delivery addDeliveries1(Delivery deliveries1) {
		getDeliveries1().add(deliveries1);
		deliveries1.setOffice1(this);

		return deliveries1;
	}

	public Delivery removeDeliveries1(Delivery deliveries1) {
		getDeliveries1().remove(deliveries1);
		deliveries1.setOffice1(null);

		return deliveries1;
	}

	public List<Delivery> getDeliveries2() {
		return this.deliveries2;
	}

	public void setDeliveries2(List<Delivery> deliveries2) {
		this.deliveries2 = deliveries2;
	}

	public Delivery addDeliveries2(Delivery deliveries2) {
		getDeliveries2().add(deliveries2);
		deliveries2.setOffice2(this);

		return deliveries2;
	}

	public Delivery removeDeliveries2(Delivery deliveries2) {
		getDeliveries2().remove(deliveries2);
		deliveries2.setOffice2(null);

		return deliveries2;
	}

	public List<Driver> getDrivers() {
		return this.drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public Driver addDriver(Driver driver) {
		getDrivers().add(driver);
		driver.setOffice(this);

		return driver;
	}

	public Driver removeDriver(Driver driver) {
		getDrivers().remove(driver);
		driver.setOffice(null);

		return driver;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setOffice(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setOffice(null);

		return employee;
	}

	public Street getStreet() {
		return this.street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

}