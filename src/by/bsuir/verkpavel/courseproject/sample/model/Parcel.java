package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the parcel database table.
 * 
 */
@Entity
public class Parcel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idParcel;

	private int depth;

	private int height;

	private double weight;

	private int width;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;

	//bi-directional many-to-one association to Markparcel
	@ManyToOne
	@JoinColumn(name="idMarkParcel")
	private Markparcel markparcel;

	//bi-directional many-to-one association to Payment
	@ManyToOne
	@JoinColumn(name="idPayments")
	private Payment payment;

	//bi-directional many-to-one association to ParcelM2mDelivery
	@OneToMany(mappedBy="parcel")
	private List<ParcelM2mDelivery> parcelM2mDeliveries;

	public Parcel() {
	}

	public int getIdParcel() {
		return this.idParcel;
	}

	public void setIdParcel(int idParcel) {
		this.idParcel = idParcel;
	}

	public int getDepth() {
		return this.depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Markparcel getMarkparcel() {
		return this.markparcel;
	}

	public void setMarkparcel(Markparcel markparcel) {
		this.markparcel = markparcel;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<ParcelM2mDelivery> getParcelM2mDeliveries() {
		return this.parcelM2mDeliveries;
	}

	public void setParcelM2mDeliveries(List<ParcelM2mDelivery> parcelM2mDeliveries) {
		this.parcelM2mDeliveries = parcelM2mDeliveries;
	}

	public ParcelM2mDelivery addParcelM2mDelivery(ParcelM2mDelivery parcelM2mDelivery) {
		getParcelM2mDeliveries().add(parcelM2mDelivery);
		parcelM2mDelivery.setParcel(this);

		return parcelM2mDelivery;
	}

	public ParcelM2mDelivery removeParcelM2mDelivery(ParcelM2mDelivery parcelM2mDelivery) {
		getParcelM2mDeliveries().remove(parcelM2mDelivery);
		parcelM2mDelivery.setParcel(null);

		return parcelM2mDelivery;
	}

}