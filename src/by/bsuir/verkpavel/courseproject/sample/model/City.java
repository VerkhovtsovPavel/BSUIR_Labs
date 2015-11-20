package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCity;

	private String name;

	//bi-directional many-to-one association to Street
	@OneToMany(mappedBy="city")
	private List<Street> streets;

	public City() {
	}

	public int getIdCity() {
		return this.idCity;
	}

	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Street> getStreets() {
		return this.streets;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}

	public Street addStreet(Street street) {
		getStreets().add(street);
		street.setCity(this);

		return street;
	}

	public Street removeStreet(Street street) {
		getStreets().remove(street);
		street.setCity(null);

		return street;
	}

}