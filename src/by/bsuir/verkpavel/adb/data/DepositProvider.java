package by.bsuir.verkpavel.adb.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

import by.bsuir.verkpavel.adb.data.entity.Deposit;
import by.bsuir.verkpavel.adb.resources.RussianStrings;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DepositProvider {
    private static DepositProvider instance;
    private Connection connection;

    private DepositProvider(Connection connection) {
        this.connection = connection;
    }

    public static DepositProvider getInstance() {
        if (instance == null) {
            instance = new DepositProvider(ConnectionManager.getInstance().getConnection());
        }
        return instance;
    }

    public ArrayList<Deposit> getAllDeposits() {
        ArrayList<Deposit> deposits = new ArrayList<Deposit>();

        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `deposit`;");
            while (rs.next()) {
                Deposit deposit = new Deposit();
                deposit.id = rs.getInt("id");
                deposit.contractNumber = rs.getString("depositNumber");
                deposit.currency = rs.getInt("currency");
                deposit.type = rs.getInt("deposittype");
                deposit.sum = rs.getDouble("sum");
                deposit.startDate = rs.getString("startDate");
                deposit.endDate = rs.getString("endDate");
                deposit.persent = rs.getDouble("persent");
                deposit.client = rs.getInt("user_id");
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

            ResultSet rs = statement.executeQuery("SELECT MAX(`id`) AS 'id' FROM `deposit`;");
            rs.next();
            deposit.id = rs.getInt("id");
        } catch (MySQLIntegrityConstraintViolationException e) {
            return RussianStrings.DUBLICATE_CONTRACT_NUMBER.get();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return RussianStrings.CONTRACT_SUCCESSFULLY_ADDED.get();
    }

    private String createInsertDepositQuery(Deposit deposit) {
        return String
                .format(Locale.ENGLISH,
                        "INSERT INTO `deposit`  (`id`, `deposittype`, `currency`, `startDate`, `endDate`, `sum`, `persent`, `depositNumber`, `user_id`, `isActive`) VALUES(NULL, '%d', '%d', '%s', '%s', %f, %f, '%s','%d', '1');",
                        deposit.type, deposit.currency, deposit.startDate, deposit.endDate,
                        deposit.sum, deposit.persent, deposit.contractNumber, deposit.client);
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

    public void updateDepositEndDate(Deposit deposit, String newDate) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(String
                    .format("UPDATE `deposit` SET `endDate` = '%s' WHERE `id` = '%d';", newDate,
                            deposit.id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Deposit> getAllActiveDeposits() {
        ArrayList<Deposit> deposits = new ArrayList<Deposit>();

        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `deposit` WHERE `isActive` = 1;");
            while (rs.next()) {
                Deposit deposit = new Deposit();
                deposit.id = rs.getInt("id");
                deposit.contractNumber = rs.getString("depositNumber");
                deposit.currency = rs.getInt("currency");
                deposit.type = rs.getInt("deposittype");
                deposit.sum = rs.getDouble("sum");
                deposit.startDate = rs.getString("startDate");
                deposit.endDate = rs.getString("endDate");
                deposit.persent = rs.getDouble("persent");
                deposit.client = rs.getInt("user_id");
                deposits.add(deposit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deposits;
    }

    public void disableDeposit(Deposit deposit) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(String.format(
                    "UPDATE `deposit` SET `isActive` = '0' WHERE `id` = '%d';", deposit.id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
