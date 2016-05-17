package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;


public class DeliveryStatus implements Entity {

	@DatabaseField(generatedId = true)
	private int idDeliveryStatus;
	@DatabaseField
	private String description;

	public DeliveryStatus() {
	}

	public int getIdDeliveryStatus() {
		return this.idDeliveryStatus;
	}

	public void setIdDeliveryStatus(int idDeliveryStatus) {
		this.idDeliveryStatus = idDeliveryStatus;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}