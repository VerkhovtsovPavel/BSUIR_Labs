package by.bsuir.verkpavel.courseproject.dao.entity;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;

public class Rate implements Entity {

    @DatabaseField(generatedId = true)
    private int idRate;
    @DatabaseField
    private int depth;
    @DatabaseField
    private int heigth;
    @DatabaseField
    private int weigth;
    @DatabaseField
    private int width;

    public Rate() {
    }

    public int getIdRate() {
        return this.idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getHeigth() {
        return this.heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getWeigth() {
        return this.weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}