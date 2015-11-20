package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the client database table.
 * 
 */
@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idClient;

	@Temporal(TemporalType.DATE)
	private Date addDate;

	private String fullName;

	//bi-directional many-to-one association to Parcel
	@OneToMany(mappedBy="client")
	private List<Parcel> parcels;

	public Client() {
	}

	public int getIdClient() {
		return this.idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Parcel> getParcels() {
		return this.parcels;
	}

	public void setParcels(List<Parcel> parcels) {
		this.parcels = parcels;
	}

	public Parcel addParcel(Parcel parcel) {
		getParcels().add(parcel);
		parcel.setClient(this);

		return parcel;
	}

	public Parcel removeParcel(Parcel parcel) {
		getParcels().remove(parcel);
		parcel.setClient(null);

		return parcel;
	}

}