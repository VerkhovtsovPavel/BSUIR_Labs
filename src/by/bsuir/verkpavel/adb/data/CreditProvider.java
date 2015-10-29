package by.bsuir.verkpavel.adb.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.bsuir.verkpavel.adb.data.entity.Credit;

//TODO Add credit table
//TODO Add credit type table [id. type]

public class CreditProvider {

    private static CreditProvider instance;
    private Connection connection;
    
    
    private CreditProvider(Connection connection){
        this.connection = connection;
    }
    
    public static CreditProvider getInstance() {
        if (instance == null) {
            instance = new CreditProvider(ConnectionManager.getInstance().getConnection());
        }
        return instance;
    }

    public String saveCredit(Credit credit) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<Credit> getAllCredits() {
        ArrayList<Credit> credits = new ArrayList<Credit>();

        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `deposit`;");
            while (rs.next()) {
                Credit credit = new Credit();
                credit.id = rs.getInt("id");
                credit.contractNumber = rs.getString("depositNumber");
                credit.currency = rs.getInt("currency");
                credit.type = rs.getInt("deposittype");
                credit.sum = rs.getDouble("sum");
                credit.startDate = rs.getString("startDate");
                credit.endDate = rs.getString("endDate");
                credit.persent = rs.getDouble("persent");
                credit.client = rs.getInt("user_id");
                credits.add(credit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credits;
    }

    public ArrayList<String> getCreditTypeList() {
        ArrayList<String> creditTypes = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `type` FROM `credittype`;");
            while (rs.next()) {
                creditTypes.add(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return creditTypes;
    }
}
