package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the delivery database table.
 * 
 */
@Entity
public class Delivery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDelivery;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	//bi-directional many-to-one association to Corporatecar
	@ManyToOne
	@JoinColumn(name="idCorporateCar")
	private Corporatecar corporatecar;

	//bi-directional many-to-one association to Office
	@ManyToOne
	@JoinColumn(name="fromOffice")
	private Office office1;

	//bi-directional many-to-one association to Office
	@ManyToOne
	@JoinColumn(name="toOffice")
	private Office office2;

	//bi-directional many-to-one association to ParcelM2mDelivery
	@OneToMany(mappedBy="delivery")
	private List<ParcelM2mDelivery> parcelM2mDeliveries;

	public Delivery() {
	}

	public int getIdDelivery() {
		return this.idDelivery;
	}

	public void setIdDelivery(int idDelivery) {
		this.idDelivery = idDelivery;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Corporatecar getCorporatecar() {
		return this.corporatecar;
	}

	public void setCorporatecar(Corporatecar corporatecar) {
		this.corporatecar = corporatecar;
	}

	public Office getOffice1() {
		return this.office1;
	}

	public void setOffice1(Office office1) {
		this.office1 = office1;
	}

	public Office getOffice2() {
		return this.office2;
	}

	public void setOffice2(Office office2) {
		this.office2 = office2;
	}

	public List<ParcelM2mDelivery> getParcelM2mDeliveries() {
		return this.parcelM2mDeliveries;
	}

	public void setParcelM2mDeliveries(List<ParcelM2mDelivery> parcelM2mDeliveries) {
		this.parcelM2mDeliveries = parcelM2mDeliveries;
	}

	public ParcelM2mDelivery addParcelM2mDelivery(ParcelM2mDelivery parcelM2mDelivery) {
		getParcelM2mDeliveries().add(parcelM2mDelivery);
		parcelM2mDelivery.setDelivery(this);

		return parcelM2mDelivery;
	}

	public ParcelM2mDelivery removeParcelM2mDelivery(ParcelM2mDelivery parcelM2mDelivery) {
		getParcelM2mDeliveries().remove(parcelM2mDelivery);
		parcelM2mDelivery.setDelivery(null);

		return parcelM2mDelivery;
	}

}