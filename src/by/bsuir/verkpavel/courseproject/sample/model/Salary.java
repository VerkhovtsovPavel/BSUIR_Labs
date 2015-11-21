package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the salary database table.
 * 
 */
@Entity
public class Salary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idSalary;

	private double baseRate;

	private double raisingFactor;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="salary")
	private List<Employee> employees;

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

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setSalary(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setSalary(null);

		return employee;
	}

}