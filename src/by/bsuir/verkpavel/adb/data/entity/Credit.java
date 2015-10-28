package by.bsuir.verkpavel.adb.data.entity;

public class Credit {

    public int creditType;
    public String contractNumber;
    public int currency;
    public String startDate;
    public String endDate;
    public double sum;
    public double persent;
    public int client;
    public int id;

    public Credit(String contractNumber, int creditType, String startDate,
            String endDate, int currency, double persent, double sum,
            int client) {
        this.contractNumber = contractNumber;
        this.creditType = creditType;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sum = sum;
        this.persent = persent;
        this.client = client;
    }

    public Credit() {
    }

}
