package by.bsuir.verkpavel.adb.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

import by.bsuir.verkpavel.adb.data.entity.Account;
import by.bsuir.verkpavel.adb.data.entity.Deposit;

public class AccountProvider {
    private static AccountProvider instance;
    private Connection connection;

    private AccountProvider(Connection connection) {
        this.connection = connection;
    }

    public static AccountProvider getInstance(Connection connection) {
        if (instance == null) {
            instance = new AccountProvider(connection);
        }
        return instance;
    }

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet accountsSet = statement.executeQuery("SELECT * FROM `account`");
            while (accountsSet.next()) {
                // TODO Fill all fields
                Account account = new Account();
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public ArrayList<Double> getTransactionByAccount(Account account) {
        ArrayList<Double> operations = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet transactions = statement
                    .executeQuery("SELECT `sum` FROM `transaction` WHERE `account_id` = "
                            + account.number);
            while (transactions.next()) {
                operations.add(transactions.getDouble("sum"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operations;
    }

    public void addTransaction(Account from, Account to, double sum) {
        // TODO Don't think about account type, just add to record in
        // `transaction` from(-) to(+) sum
        Statement statement;
        try {
            statement = connection.createStatement();
            // TODO No transactional
            statement.executeUpdate(String.format(Locale.ENGLISH,
                    "INSERT INTO `transaction` (`id`, `account_id`, sum) VALUES (NULL, %d, %f",
                    from.number, -sum));
            statement.executeUpdate(String.format(Locale.ENGLISH,
                    "INSERT INTO `transaction` (`id`, `account_id`, sum) VALUES (NULL, %d, %f",
                    to.number, sum));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountByDeposit(Deposit deposit) {
        Account accountByDeposit = null;
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet accounts = statement
                    .executeQuery("SELECT * FROM `account` WHERE `deposit_id` = " + deposit.id);
            while (accounts.next()) {
                // TODO Fill all fields
                // TODO Queue return 2 records
                // TODO Create method to check deposit account sub-type (main or percents) 
                accountByDeposit = new Account();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountByDeposit;
    }

    public Account getCashBoxAccount() {
        Account cashBox = null;
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet accounts = statement
                    .executeQuery("SELECT * FROM `account` WHERE `deposit_id` IS NULL");
            while (accounts.next()) {
                if (accounts.getString("number").startsWith("1010")) {
                    // TODO Fill all fields
                    cashBox = new Account();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cashBox;
    }

    public Account getFDBAccount() {
        Account fdba = null;
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet accounts = statement
                    .executeQuery("SELECT * FROM `account` WHERE `deposit_id` IS NULL");
            while (accounts.next()) {
                if (accounts.getString("number").startsWith("7327")) {
                    // TODO Fill all fields
                    fdba = new Account();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fdba;
    }

    public void createAccountByDeposit(Deposit deposit) {
        Statement statement;
        try {
            statement = connection.createStatement();
            // TODO Maybe change generate number process
            statement
                    .executeUpdate(String
                            .format("INSERT INTO `bank_users`.`account` (`id`, `number`, `type`, `deposit_id`) VALUES (NULL, '3014%s', '%d', '%d'",
                                    generateNumber(), 1, deposit.id));
            statement
                    .executeUpdate(String
                            .format("INSERT INTO `bank_users`.`account` (`id`, `number`, `type`, `deposit_id`) VALUES (NULL, '3014%s', '%d', '%d'",
                                    generateNumber(), 1, deposit.id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateNumber() {
        // TODO Implement
        return "65466645646554";
    }
}
