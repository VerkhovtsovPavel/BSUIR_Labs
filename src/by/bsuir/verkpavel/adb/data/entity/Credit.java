package by.bsuir.verkpavel.adb.data.entity;

import java.util.ArrayList;

import by.bsuir.verkpavel.adb.logic.credit.SimpleAnnuityCalculator;

public class Credit extends Deposit{


    public ArrayList<AnnuityPayment> annuityPayment;

    public Credit(String contractNumber, int creditType, String startDate,
            String endDate, int currency, double persent, double creditSum,
            int client) {
        super(contractNumber, creditType, startDate, endDate, currency, persent, creditSum, client);
    }

    public Credit() {
    }
    
    
    public ArrayList<AnnuityPayment> getAnnuityPayment(){
        if(this.annuityPayment == null){
            this.annuityPayment = new SimpleAnnuityCalculator(this).paymentsSheduling();
        }
        return annuityPayment;
    }

}
