package by.bsuir.verkpavel.adb.data;

import java.sql.Connection;
import java.util.ArrayList;

import by.bsuir.verkpavel.adb.data.entity.Credit;

public class CreditProvider {

    private static CreditProvider instance;
    private Connection connection;
    
    
    private CreditProvider(Connection connection){
        this.connection = connection;
    }
    
    public static CreditProvider getInstance() {
        if (instance == null) {
            instance = new CreditProvider(ConnectionManager.getInstance().getConnection());
        }
        return instance;
    }

    public String saveCredit(Credit credit) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<Credit> getAllCredits() {
        // FIXME Auto-generated method stub
        return null;
    }

    public ArrayList<String> getCreditTypeList() {
        // TODO Auto-generated method stub
        return null;
    }
}
