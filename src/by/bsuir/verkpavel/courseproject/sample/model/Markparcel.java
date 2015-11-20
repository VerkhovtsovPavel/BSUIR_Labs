package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the markparcel database table.
 * 
 */
@Entity
public class Markparcel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idMarkParcel;

	private String description;

	//bi-directional many-to-one association to Parcel
	@OneToMany(mappedBy="markparcel")
	private List<Parcel> parcels;

	public Markparcel() {
	}

	public int getIdMarkParcel() {
		return this.idMarkParcel;
	}

	public void setIdMarkParcel(int idMarkParcel) {
		this.idMarkParcel = idMarkParcel;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Parcel> getParcels() {
		return this.parcels;
	}

	public void setParcels(List<Parcel> parcels) {
		this.parcels = parcels;
	}

	public Parcel addParcel(Parcel parcel) {
		getParcels().add(parcel);
		parcel.setMarkparcel(this);

		return parcel;
	}

	public Parcel removeParcel(Parcel parcel) {
		getParcels().remove(parcel);
		parcel.setMarkparcel(null);

		return parcel;
	}

}