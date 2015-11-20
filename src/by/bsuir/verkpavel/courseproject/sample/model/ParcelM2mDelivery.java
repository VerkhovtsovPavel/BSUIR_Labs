package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parcel_m2m_delivery database table.
 * 
 */
@Entity
@Table(name="parcel_m2m_delivery")
public class ParcelM2mDelivery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idParcel_M2M_Delivery;

	//bi-directional many-to-one association to Delivery
	@ManyToOne
	@JoinColumn(name="idDelivery")
	private Delivery delivery;

	//bi-directional many-to-one association to Parcel
	@ManyToOne
	@JoinColumn(name="idParcel")
	private Parcel parcel;

	public ParcelM2mDelivery() {
	}

	public int getIdParcel_M2M_Delivery() {
		return this.idParcel_M2M_Delivery;
	}

	public void setIdParcel_M2M_Delivery(int idParcel_M2M_Delivery) {
		this.idParcel_M2M_Delivery = idParcel_M2M_Delivery;
	}

	public Delivery getDelivery() {
		return this.delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Parcel getParcel() {
		return this.parcel;
	}

	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
	}

}