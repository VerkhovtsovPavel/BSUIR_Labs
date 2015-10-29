package by.bsuir.verkpavel.adb.data.entity;

public class AnnuityPayment {
    public double principalAmount;
    public double percents;
    
    public AnnuityPayment(double principalAmount, double percents){
        this.principalAmount = principalAmount;
        this.percents = percents;
    }
}
