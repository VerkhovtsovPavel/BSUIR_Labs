package by.bsuir.verkpavel.adb.bank_server.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import by.bsuir.verkpavel.adb.bank_server.data.entity.Credit;
import by.bsuir.verkpavel.adb.bank_server.data.entity.Deposit;
import by.bsuir.verkpavel.adb.bank_server.data.entity.TransactionsInfo;

public class PlasticCardProvider {
    private static PlasticCardProvider instance;
    private Connection connection;

    private PlasticCardProvider(Connection connection) {
        this.connection = connection;
    }

    public static PlasticCardProvider getInstance() {
        if (instance == null) {
            instance = new PlasticCardProvider(ConnectionManager.getInstance().getConnection());
        }
        return instance;
    }

    public double getBalanceByCardAndType(String cardNumber, String type) {
        double balance = 0;
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet plasticCardSet = statement.executeQuery("SELECT `" + type
                    + "_id` FROM `plasticcard` WHERE `number` = '" + cardNumber + "'");
            plasticCardSet.next();
            int account = plasticCardSet.getInt(type + "_id");
            List<TransactionsInfo> transactions;
            if (type.equals("debit")) {
                Deposit deposit = new Deposit();
                deposit.id = account;
                transactions = AccountProvider.getInstance().getTransactionsByAccount(
                        AccountProvider.getInstance().getAccountByDeposit(deposit)[1]);
            } else {
                Credit credit = new Credit();
                credit.id = account;
                transactions = AccountProvider.getInstance().getTransactionsByAccount(
                        AccountProvider.getInstance().getAccountByCredit(credit)[1]);
            }

            for (TransactionsInfo transactionsInfo : transactions) {
                balance += transactionsInfo.sum;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public boolean verifyCardData(String cardNumber, String pinCode) {
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet plasticCardSet = statement.executeQuery("SELECT * FROM `plasticcard` WHERE `number` = '" + cardNumber + "' AND `pincode` = '"+pinCode+"'");
            return plasticCardSet.first();

        } catch (SQLException e) {
           return false;
        }
    }
}