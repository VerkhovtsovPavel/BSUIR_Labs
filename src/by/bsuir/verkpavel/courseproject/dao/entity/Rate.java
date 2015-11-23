package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;


public class Rate implements Entity {

	@DatabaseField(generatedId = true)
	private int idRate;
	@DatabaseField
	private float depth;
	@DatabaseField
	private float heigth;
	@DatabaseField
	private float weigth;
	@DatabaseField
	private float width;

	public Rate() {
	}

	public int getIdRate() {
		return this.idRate;
	}

	public void setIdRate(int idRate) {
		this.idRate = idRate;
	}

	public float getDepth() {
		return this.depth;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}

	public float getHeigth() {
		return this.heigth;
	}

	public void setHeigth(float heigth) {
		this.heigth = heigth;
	}

	public float getWeigth() {
		return this.weigth;
	}

	public void setWeigth(float weigth) {
		this.weigth = weigth;
	}

	public float getWidth() {
		return this.width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

}