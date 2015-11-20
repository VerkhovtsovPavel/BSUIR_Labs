package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the driverlicencecategory database table.
 * 
 */
@Entity
public class Driverlicencecategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDriverLicenceCategory;

	private String description;

	//bi-directional many-to-one association to Corporatecar
	@OneToMany(mappedBy="driverlicencecategory")
	private List<Corporatecar> corporatecars;

	//bi-directional many-to-one association to Driverlicense
	@OneToMany(mappedBy="driverlicencecategory")
	private List<Driverlicense> driverlicenses;

	public Driverlicencecategory() {
	}

	public int getIdDriverLicenceCategory() {
		return this.idDriverLicenceCategory;
	}

	public void setIdDriverLicenceCategory(int idDriverLicenceCategory) {
		this.idDriverLicenceCategory = idDriverLicenceCategory;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Corporatecar> getCorporatecars() {
		return this.corporatecars;
	}

	public void setCorporatecars(List<Corporatecar> corporatecars) {
		this.corporatecars = corporatecars;
	}

	public Corporatecar addCorporatecar(Corporatecar corporatecar) {
		getCorporatecars().add(corporatecar);
		corporatecar.setDriverlicencecategory(this);

		return corporatecar;
	}

	public Corporatecar removeCorporatecar(Corporatecar corporatecar) {
		getCorporatecars().remove(corporatecar);
		corporatecar.setDriverlicencecategory(null);

		return corporatecar;
	}

	public List<Driverlicense> getDriverlicenses() {
		return this.driverlicenses;
	}

	public void setDriverlicenses(List<Driverlicense> driverlicenses) {
		this.driverlicenses = driverlicenses;
	}

	public Driverlicense addDriverlicens(Driverlicense driverlicens) {
		getDriverlicenses().add(driverlicens);
		driverlicens.setDriverlicencecategory(this);

		return driverlicens;
	}

	public Driverlicense removeDriverlicens(Driverlicense driverlicens) {
		getDriverlicenses().remove(driverlicens);
		driverlicens.setDriverlicencecategory(null);

		return driverlicens;
	}

}