package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the street database table.
 * 
 */
@Entity
public class Street implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StreetPK id;

	private String name;

	//bi-directional many-to-one association to Office
	@OneToMany(mappedBy="street")
	private List<Office> offices;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="idCity")
	private City city;

	public Street() {
	}

	public StreetPK getId() {
		return this.id;
	}

	public void setId(StreetPK id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Office> getOffices() {
		return this.offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	public Office addOffice(Office office) {
		getOffices().add(office);
		office.setStreet(this);

		return office;
	}

	public Office removeOffice(Office office) {
		getOffices().remove(office);
		office.setStreet(null);

		return office;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}