package by.bsuir.verkpavel.adb.bank_server.data.entity;

public class Account {
    public int deposit_id;
    public int id;
    public String number;
    public AccountType type;

    public Account(int id, String number, int type, int deposit_id) {
        this.id = id;
        this.number = number;
        this.type = AccountType.values()[type-1];
        this.deposit_id = deposit_id;
    }

    public enum AccountType {
        ACTIVE, PASSIVE, ACTIVE_PASSIVE
    }
}
