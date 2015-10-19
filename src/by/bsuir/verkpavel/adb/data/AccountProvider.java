package by.bsuir.verkpavel.adb.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    	Account cashBox = null;
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet accounts =  statement.executeQuery("SELECT * FROM `account` WHERE `deposit_id` IS NULL");
            while (accounts.next()){
            	if(accounts.getString("number").startsWith("1010")){
            		//TODO Fill all fields
            		cashBox = new Account();
            	}
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cashBox;
    }
    
    public Account getFDBAccount() {
        // TODO Auto-generated method stub
        // 7327
        return null;
    }
}
