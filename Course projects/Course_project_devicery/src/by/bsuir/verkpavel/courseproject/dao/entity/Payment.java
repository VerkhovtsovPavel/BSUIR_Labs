package by.bsuir.verkpavel.courseproject.dao.entity;

import java.util.Date;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



@DatabaseTable(tableName="payments")
public class Payment implements Entity {

	@DatabaseField(generatedId = true)
	private int idPayments;

	@DatabaseField
	private Date payDate;
	@DatabaseField
	private int sum;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "idPaymentsSystemType")
	private PaymentsSystemType paymentssystemtype;

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

	public PaymentsSystemType getPaymentsSystemType() {
		return this.paymentssystemtype;
	}

	public void setPaymentssystemtype(PaymentsSystemType paymentssystemtype) {
		this.paymentssystemtype = paymentssystemtype;
	}

}