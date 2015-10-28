package by.bsuir.verkpavel.adb.data.entity;

public class Credit extends Deposit{


    public Credit(String contractNumber, int creditType, String startDate,
            String endDate, int currency, double persent, double creditSum,
            int client) {
        super(contractNumber, creditType, startDate, endDate, currency, persent, creditSum, client);
    }

    public Credit() {
    }

}
