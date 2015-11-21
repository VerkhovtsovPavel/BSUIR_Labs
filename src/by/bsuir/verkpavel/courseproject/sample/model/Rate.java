package by.bsuir.verkpavel.courseproject.sample.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rate database table.
 * 
 */
@Entity
public class Rate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idRate;

	private float depth;

	private float heigth;

	private float weigth;

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