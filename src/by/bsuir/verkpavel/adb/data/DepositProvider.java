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
        ArrayList<Deposit> deposits = new ArrayList<Deposit>();

        Statement statement;
        try {
            statement = connection.createStatement();
            //TODO Add client
            ResultSet rs = statement.executeQuery("SELECT * FROM `deposit`;");
            while (rs.next()) {
                Deposit deposit = new Deposit();
                deposit.contractNumber = rs.getString("depositNumber");
                deposit.currency = rs.getInt("currency");
                deposit.depositType = rs.getInt("deposittype");
                deposit.depositSum = rs.getDouble("sum");
                deposit.startDate = rs.getString("startDate");
                deposit.endDate = rs.getString("endDate");
                deposit.persent = rs.getDouble("persent");
                
                deposits.add(deposit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deposits;
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

    private String createInsertDepositQuery(Deposit deposit) {
        return String
                .format("INSERT INTO `deposit`  (`id`, `deposittype`, `currency`, `startDate`, `endDate`, `sum`, `persent`, `depositNumber`) VALUES(NULL, %d, %d, %s, %s, %f, %f, %s);",
                        deposit.depositType, deposit.currency, deposit.startDate, deposit.endDate,
                        deposit.depositSum, deposit.persent, deposit.contractNumber);
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
