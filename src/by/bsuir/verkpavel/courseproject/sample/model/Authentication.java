package by.bsuir.verkpavel.courseproject.sample.model;

import by.bsuir.verkpavel.courseproject.dao.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The persistent class for the authentication database table.
 * 
 */
@DatabaseTable(tableName = "authentication")
public class Authentication implements Entity {
    @DatabaseField(generatedId = true)
    private int idAuthentication;
    @DatabaseField
    private String password;
    @DatabaseField
    private String userName;

    public Authentication() {
    }

    public int getIdAuthentication() {
        return this.idAuthentication;
    }

    public void setIdAuthentication(int idAuthentication) {
        this.idAuthentication = idAuthentication;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}