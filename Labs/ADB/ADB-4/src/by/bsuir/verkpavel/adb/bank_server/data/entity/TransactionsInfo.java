package by.bsuir.verkpavel.adb.bank_server.data.entity;

public class TransactionsInfo {
    
    public double sum;
    public String currency;

    public TransactionsInfo(double sum, String currencu) {
        this.sum = sum;
        this.currency = currencu;
    }

}
