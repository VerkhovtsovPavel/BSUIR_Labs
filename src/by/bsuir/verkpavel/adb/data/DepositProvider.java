package by.bsuir.verkpavel.adb.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.bsuir.verkpavel.adb.data.entity.Deposit;
import by.bsuir.verkpavel.adb.resources.RussianStrings;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DepositProvider {
    private static DepositProvider instance;
    private Connection connection;

    private DepositProvider(Connection connection) {
        this.connection = connection;
    }

    public static DepositProvider getInstance(Connection connection) {
        if (instance == null) {
            instance = new DepositProvider(connection);
        }
        return instance;
    }

    public ArrayList<Deposit> getAllDeposits() {
        // TODO Auto-generated method stub
        return null;
    }

    public String saveDeposit(Deposit deposit) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(createInsertDepositQuery(deposit));
        } catch (MySQLIntegrityConstraintViolationException e) {
            return RussianStrings.DUBLICATE_CONTRACT_NUMBER.get();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return RussianStrings.CONTRACT_SUCCESSFULLY_ADDED.get();
    }

    // TODO Implement real insert
    private String createInsertDepositQuery(Deposit deposit) {
        return String
                .format("INSERT INTO `deposits`  (`id`, `FirstName`, `LastName`, `MidleName`, `Birthday`, `Sex`, `Address_id`, `MobilePhone`, `HomePhone`, `E-mail`, `FamilyStatus`, `Nationality_id`, `Disability_id`, `Pensioner`, `MonthProfit`, `Official Street`, `Real Street`) VALUES (NULL, '%s', '%s', '%s', '%s', '%d', '%d', '%s', '%s', '%s', '%d', '%d', '%d', '%d', '%d', '%s', '%s');",
                        deposit.contractNumber, deposit.client);
    }

    public ArrayList<String> getDepositTypeList() {
        ArrayList<String> depositTypes = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `type` FROM `deposittype`;");
            while (rs.next()) {
                depositTypes.add(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return depositTypes;
    }

    public ArrayList<String> getCurrency() {
        ArrayList<String> currencys = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `description` FROM `currency`;");
            while (rs.next()) {
                currencys.add(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencys;
    }
}
