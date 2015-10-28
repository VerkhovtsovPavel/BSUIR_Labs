package by.bsuir.verkpavel.adb.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import by.bsuir.verkpavel.adb.data.entity.Account;
import by.bsuir.verkpavel.adb.data.entity.Client;
import by.bsuir.verkpavel.adb.data.entity.Deposit;
import by.bsuir.verkpavel.adb.data.entity.TransactionsInfo;

//MAYBE Remove this facade and use separate class or add three getInstance methods
public class DataProvider {
    private static DataProvider instance;
    private Connection connection;

    private ClientProvider clientProvider;
    private DepositProvider depositProvider;
    private AccountProvider accountProvider;

    private static final String DB_PATH = "jdbc:mysql://localhost:3306/bank_users?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "123456";

    private DataProvider() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_PATH, DB_USER_NAME, DB_PASSWORD);
            this.clientProvider = ClientProvider.getInstance(connection);
            this.depositProvider = DepositProvider.getInstance(connection);
            this.accountProvider = AccountProvider.getInstance(connection);
        } catch (ClassNotFoundException e) {
            System.out.println("DB driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public static DataProvider getInstance() {
        if (instance == null) {
            instance = new DataProvider();
        }
        return instance;
    }

    public ArrayList<String> getCityList() {
        return clientProvider.getCityList();
    }

    public ArrayList<String> getFamilyStatuses() {
        return clientProvider.getFamilyStatuses();
    }

    public ArrayList<String> getNationalitys() {
        return clientProvider.getNationalitys();
    }

    public ArrayList<String> getDisabilitys() {
        return clientProvider.getDisabilitys();
    }

    public String saveClient(Client client) {
        return clientProvider.saveClient(client);
    }

    public String updateClient(Client client) {
        return clientProvider.updateClient(client);
    }

    public ArrayList<Client> getAllClients() {
        return clientProvider.getAllClients();
    }

    public void deleteClient(Client client) {
        clientProvider.deleteClient(client);
    }

    public ArrayList<String> getUserFullNames() {
        return clientProvider.getUserFullNames();
    }

    public ArrayList<String> getCurrency() {
        return depositProvider.getCurrency();
    }

    public ArrayList<String> getDepositTypeList() {
        return depositProvider.getDepositTypeList();
    }

    public String saveDeposit(Deposit deposit) {
        return depositProvider.saveDeposit(deposit);
    }

    public ArrayList<Deposit> getAllDeposits() {
        return depositProvider.getAllDeposits();
    }

    public void updateDepositEndDate(Deposit deposit, String newDate) {
        depositProvider.updateDepositEndDate(deposit, newDate);
    }

    public ArrayList<Account> getAllAccounts() {
        return accountProvider.getAllAccounts();
    }

    public void addTransaction(Account from, Account to, double sum, int currency) {
        try {
            accountProvider.addTransaction(from, to, sum, currency);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMonoTransaction(Account from, Account to, double sum, int currency) {
        try {
            accountProvider.addMonoTransaction(from, to, sum, currency);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account[] getAccountByDeposit(Deposit deposit) {
        return accountProvider.getAccountByDeposit(deposit);
    }

    public Account getCashBoxAccount() {
        return accountProvider.getCashBoxAccount();
    }

    public void createAccountsByDeposit(Deposit deposit) {
        accountProvider.createAccountByDeposit(deposit);
    }

    public Account getFDBAccount() {
        return accountProvider.getFDBAccount();
    }

    public ArrayList<TransactionsInfo> getTransatcionsByAccount(Account account) {
        return accountProvider.getTransactionByAccount(account);

    }

    public ArrayList<Deposit> getAllActiveDeposits() {
        return depositProvider.getAllActiveDeposits();
    }

    public void disableDeposit(Deposit deposit) {
        depositProvider.disableDeposit(deposit);
    }
}
