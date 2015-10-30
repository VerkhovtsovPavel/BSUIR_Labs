package by.bsuir.verkpavel.adb.logic.credit;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import by.bsuir.verkpavel.adb.data.entity.AnnuityPayment;
import by.bsuir.verkpavel.adb.data.entity.Credit;

public class SimpleAnnuityCalculator implements AnnuityCalculator {
    private Credit credit;

    public SimpleAnnuityCalculator(Credit credit) {
        this.credit = credit;
    }

    @Override
    public ArrayList<AnnuityPayment> paymentsSheduling() {
        double creditSum = credit.sum;
        long creditPeriod = ChronoUnit.MONTHS.between(LocalDate.parse(credit.startDate),
                LocalDate.parse(credit.endDate));

        double fixPaymentsSum = Math.floor(credit.sum / creditPeriod * 100) / 100;

        ArrayList<AnnuityPayment> payments = new ArrayList<AnnuityPayment>();

        double midProcent = 0;
        for (int i = 0; i < creditPeriod; i++) {
            payments.add(new AnnuityPayment(fixPaymentsSum, creditSum * credit.persent / 12));
            midProcent += creditSum * credit.persent / 12;
            creditSum -= fixPaymentsSum;
        }

        midProcent /= creditPeriod;
        midProcent = Math.floor(midProcent * 100) / 100;

        for (AnnuityPayment payment : payments) {
            payment.percents = midProcent;
        }

        return payments;
    }
}
