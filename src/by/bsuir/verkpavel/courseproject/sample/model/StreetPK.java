package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the street database table.
 * 
 */
@Embeddable
public class StreetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idStreet;

	private int idCity;

	public StreetPK() {
	}
	public int getIdStreet() {
		return this.idStreet;
	}
	public void setIdStreet(int idStreet) {
		this.idStreet = idStreet;
	}
	public int getIdCity() {
		return this.idCity;
	}
	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StreetPK)) {
			return false;
		}
		StreetPK castOther = (StreetPK)other;
		return 
			(this.idStreet == castOther.idStreet)
			&& (this.idCity == castOther.idCity);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idStreet;
		hash = hash * prime + this.idCity;
		
		return hash;
	}
}