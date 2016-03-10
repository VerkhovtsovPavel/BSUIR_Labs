package by.bsuir.verkpavel.adb.data.entity;

import java.time.LocalDate;

public class AnnuityPayment {
    public double principalAmount;
    public double percents;
    public LocalDate payDate;
    
    public AnnuityPayment(double principalAmount, double percents, LocalDate payDate){
        this.principalAmount = principalAmount;
        this.percents = percents;
        this.payDate = payDate;
    }
}
