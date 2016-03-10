package by.bsuir.verkpavel.adb.bank_server.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.bsuir.verkpavel.adb.bank_server.data.entity.Account;
import by.bsuir.verkpavel.adb.bank_server.data.entity.Credit;

public class WithdrawalProvider {
    private static WithdrawalProvider instance;
    private Connection connection;

    private WithdrawalProvider(Connection connection) {
        this.connection = connection;
    }

    public static WithdrawalProvider getInstance() {
        if (instance == null) {
            instance = new WithdrawalProvider(ConnectionManager.getInstance().getConnection());
        }
        return instance;
    }

    public boolean executeWithdrawal(String cardNumber, double sum) {
        Account cashBox = AccountProvider.getInstance().getCashBoxAccount();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet plasticCardSet = statement
                    .executeQuery("SELECT `credit_id` FROM `plasticcard` WHERE `number` = '" + cardNumber + "'");
            plasticCardSet.next();
            int account = plasticCardSet.getInt("credit_id");

            Credit credit = new Credit();
            credit.id = account;
            Account userAccount = AccountProvider.getInstance().getAccountByCredit(credit)[0];

            AccountProvider.getInstance().addTransaction(userAccount, cashBox, sum, 3);

        } catch (SQLException | NullPointerException e) {
            return false;
        }

        return true;
    }
}
