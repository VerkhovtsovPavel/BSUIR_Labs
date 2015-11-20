package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the permissions database table.
 * 
 */
@Entity
@Table(name="permissions")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPermissions;

	private String description;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="permission")
	private List<Employee> employees;

	public Permission() {
	}

	public int getIdPermissions() {
		return this.idPermissions;
	}

	public void setIdPermissions(int idPermissions) {
		this.idPermissions = idPermissions;
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
		employee.setPermission(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setPermission(null);

		return employee;
	}

}