package by.bsuir.verkpavel.adb.data.entity;

public class Account {
    public String number;
    public AccountType type;

    
    public enum AccountType {
        ACTIVE, PASSIVE,ACTIVE_PASSIVE
    }
}



