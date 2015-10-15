package by.bsuir.verkpavel.adb.data;

import java.sql.Connection;
import java.util.ArrayList;

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
        // TODO Auto-generated method stub
        return null;
    }
    
    public void addTransaction(Account from, Account to){
        // TODO Auto-generated method stub 
    }

    public Account getAccountByDeposit(Deposit deposit) {
        // TODO Auto-generated method stub
        return null;
    }

    public Account getCashBoxAccount() {
        // TODO Auto-generated method stub\
        // 1010
        return null;
    }
    
    public Account getFDBAccount() {
        // TODO Auto-generated method stub
        // 7327
        return null;
    }
}
