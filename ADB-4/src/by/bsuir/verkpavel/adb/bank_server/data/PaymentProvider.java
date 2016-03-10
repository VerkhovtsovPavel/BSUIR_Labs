package by.bsuir.verkpavel.adb.bank_server.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.bsuir.verkpavel.adb.bank_server.data.entity.Account;
import by.bsuir.verkpavel.adb.bank_server.data.entity.Credit;

public class PaymentProvider {
    private static PaymentProvider instance;
    private Connection connection;

    private PaymentProvider(Connection connection) {
        this.connection = connection;
    }

    public static PaymentProvider getInstance() {
        if (instance == null) {
            instance = new PaymentProvider(ConnectionManager.getInstance().getConnection());
        }
        return instance;
    }

    public boolean executePaymentByOrganization(String organization, String cardNumber, double sum) {
        Account orgAccount = AccountProvider.getInstance().getAccountByOrganization(organization);

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

            AccountProvider.getInstance().addTransaction(userAccount, orgAccount, sum, 3);

        } catch (SQLException | NullPointerException e) {
            return false;
        }

        return true;
    }
}
