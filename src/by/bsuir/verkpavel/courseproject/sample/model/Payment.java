package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the payments database table.
 * 
 */
@Entity
@Table(name="payments")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPayments;

	@Temporal(TemporalType.DATE)
	private Date payDate;

	private int sum;

	//bi-directional many-to-one association to Parcel
	@OneToMany(mappedBy="payment")
	private List<Parcel> parcels;

	//bi-directional many-to-one association to Paymentssystemtype
	@ManyToOne
	@JoinColumn(name="idPaymentsSystemType")
	private Paymentssystemtype paymentssystemtype;

	public Payment() {
	}

	public int getIdPayments() {
		return this.idPayments;
	}

	public void setIdPayments(int idPayments) {
		this.idPayments = idPayments;
	}

	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public int getSum() {
		return this.sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public List<Parcel> getParcels() {
		return this.parcels;
	}

	public void setParcels(List<Parcel> parcels) {
		this.parcels = parcels;
	}

	public Parcel addParcel(Parcel parcel) {
		getParcels().add(parcel);
		parcel.setPayment(this);

		return parcel;
	}

	public Parcel removeParcel(Parcel parcel) {
		getParcels().remove(parcel);
		parcel.setPayment(null);

		return parcel;
	}

	public Paymentssystemtype getPaymentssystemtype() {
		return this.paymentssystemtype;
	}

	public void setPaymentssystemtype(Paymentssystemtype paymentssystemtype) {
		this.paymentssystemtype = paymentssystemtype;
	}

}