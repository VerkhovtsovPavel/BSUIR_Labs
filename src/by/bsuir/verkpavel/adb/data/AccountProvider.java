package by.bsuir.verkpavel.adb.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

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
    
    private Account getAccountFromResultSet(ResultSet resultSet) throws SQLException{
       return new Account(resultSet.getInt("id"),resultSet.getString("number"), resultSet.getInt("type"), resultSet.getInt("deposit_id"));
    }

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet accountsSet = statement.executeQuery("SELECT * FROM `account`");
            while (accountsSet.next()) {
                Account account = getAccountFromResultSet(accountsSet);
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
        Statement statement;
        try {
            statement = connection.createStatement();
            // TODO No transactional
            statement.executeUpdate(String.format(Locale.ENGLISH,
                    "INSERT INTO `transaction` (`id`, `account_id`, sum) VALUES (NULL, %d, %f",
                    from.id, -sum));
            statement.executeUpdate(String.format(Locale.ENGLISH,
                    "INSERT INTO `transaction` (`id`, `account_id`, sum) VALUES (NULL, %d, %f",
                    to.id, sum));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account[] getAccountByDeposit(Deposit deposit) {
        Account[] personalAccounts = new Account[2];
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet accounts = statement
                    .executeQuery("SELECT * FROM `account` WHERE `deposit_id` = " + deposit.id);
                accounts.next();
                personalAccounts[0] = getAccountFromResultSet(accounts);
                accounts.next();
                personalAccounts[1] = getAccountFromResultSet(accounts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personalAccounts;
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
                    cashBox = getAccountFromResultSet(accounts);
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
                    fdba =  getAccountFromResultSet(accounts);
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
            statement
                    .executeUpdate(String
                            .format("INSERT INTO `account` (`id`, `number`, `type`, `deposit_id`) VALUES (NULL, '3014%s', '%d', '%d')",
                                    generateNumber(deposit,0), 1, deposit.id));
            statement
                    .executeUpdate(String
                            .format("INSERT INTO `account` (`id`, `number`, `type`, `deposit_id`) VALUES (NULL, '3014%s', '%d', '%d')",
                                    generateNumber(deposit,1), 1, deposit.id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateNumber(Deposit deposit, int type) {
        // TODO Maybe change generate number process
        return deposit.contractNumber.substring(0, 4)+(1000+new Random().nextInt(8999))+type;
    }
}
