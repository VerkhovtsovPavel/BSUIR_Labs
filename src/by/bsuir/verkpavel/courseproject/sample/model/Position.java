package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the position database table.
 * 
 */
@Entity
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPosition;

	private String description;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="position")
	private List<Employee> employees;

	public Position() {
	}

	public int getIdPosition() {
		return this.idPosition;
	}

	public void setIdPosition(int idPosition) {
		this.idPosition = idPosition;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setPosition(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setPosition(null);

		return employee;
	}

}