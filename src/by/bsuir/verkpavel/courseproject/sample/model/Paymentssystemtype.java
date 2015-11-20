package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the paymentssystemtype database table.
 * 
 */
@Entity
public class Paymentssystemtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPaymentsSystemType;

	private String description;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="paymentssystemtype")
	private List<Payment> payments;

	public Paymentssystemtype() {
	}

	public int getIdPaymentsSystemType() {
		return this.idPaymentsSystemType;
	}

	public void setIdPaymentsSystemType(int idPaymentsSystemType) {
		this.idPaymentsSystemType = idPaymentsSystemType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setPaymentssystemtype(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setPaymentssystemtype(null);

		return payment;
	}

}