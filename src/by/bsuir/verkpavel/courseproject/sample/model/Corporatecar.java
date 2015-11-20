package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the corporatecar database table.
 * 
 */
@Entity
public class Corporatecar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCorporateCar;

	@Temporal(TemporalType.DATE)
	private Date buyDate;

	private String mark;

	private int maxDepth;

	private int maxHeight;

	private int maxWeigth;

	private int maxWidth;

	private String number;

	//bi-directional many-to-one association to Driverlicencecategory
	@ManyToOne
	@JoinColumn(name="requiredDriverLicenceCategory")
	private Driverlicencecategory driverlicencecategory;

	//bi-directional many-to-one association to Office
	@ManyToOne
	@JoinColumn(name="currentLocation")
	private Office office;

	//bi-directional many-to-one association to Delivery
	@OneToMany(mappedBy="corporatecar")
	private List<Delivery> deliveries;

	public Corporatecar() {
	}

	public int getIdCorporateCar() {
		return this.idCorporateCar;
	}

	public void setIdCorporateCar(int idCorporateCar) {
		this.idCorporateCar = idCorporateCar;
	}

	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getMaxDepth() {
		return this.maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public int getMaxHeight() {
		return this.maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getMaxWeigth() {
		return this.maxWeigth;
	}

	public void setMaxWeigth(int maxWeigth) {
		this.maxWeigth = maxWeigth;
	}

	public int getMaxWidth() {
		return this.maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Driverlicencecategory getDriverlicencecategory() {
		return this.driverlicencecategory;
	}

	public void setDriverlicencecategory(Driverlicencecategory driverlicencecategory) {
		this.driverlicencecategory = driverlicencecategory;
	}

	public Office getOffice() {
		return this.office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public List<Delivery> getDeliveries() {
		return this.deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public Delivery addDelivery(Delivery delivery) {
		getDeliveries().add(delivery);
		delivery.setCorporatecar(this);

		return delivery;
	}

	public Delivery removeDelivery(Delivery delivery) {
		getDeliveries().remove(delivery);
		delivery.setCorporatecar(null);

		return delivery;
	}

}