package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the authentication database table.
 * 
 */
@Entity
public class Authentication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAuthentication;

	private String password;

	private String userName;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="authentication")
	private List<Employee> employees;

	public Authentication() {
	}

	public int getIdAuthentication() {
		return this.idAuthentication;
	}

	public void setIdAuthentication(int idAuthentication) {
		this.idAuthentication = idAuthentication;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setAuthentication(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setAuthentication(null);

		return employee;
	}

}