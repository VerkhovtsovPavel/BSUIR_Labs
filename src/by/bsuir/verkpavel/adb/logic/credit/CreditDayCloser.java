package by.bsuir.verkpavel.adb.logic.credit;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.verkpavel.adb.data.AccountProvider;
import by.bsuir.verkpavel.adb.data.CreditProvider;
import by.bsuir.verkpavel.adb.data.entity.AnnuityPayment;
import by.bsuir.verkpavel.adb.data.entity.Credit;
import by.bsuir.verkpavel.adb.data.entity.TransactionsInfo;
import by.bsuir.verkpavel.adb.logic.AbstractDayCloser;

//TODO Check work
public class CreditDayCloser extends AbstractDayCloser {

    @Override
    public void closeDay() {
        ArrayList<Credit> credits = CreditProvider.getInstance().getAllActiveCredits();
        for (Credit credit : credits) {
            if (credit.type == 2) {
                AccountProvider.getInstance().addTransaction(
                        AccountProvider.getInstance().getAccountByCredit(credit)[1],
                        AccountProvider.getInstance().getFDBAccount(),
                        credit.sum * credit.persent / 360, credit.currency);
            }

            if (LocalDate.now().getDayOfMonth() == LocalDate.now().lengthOfMonth()) {
                if (credit.type == 2) {
                    AccountProvider.getInstance().addMonoTransaction(
                            AccountProvider.getInstance().getAccountByCredit(credit)[1],
                            AccountProvider.getInstance().getCashBoxAccount(),
                            -getPersentsSum(credit), credit.currency);
                }
            }
            
            if(credit.type==1 && ChronoUnit.DAYS.between(LocalDate.parse(credit.startDate), LocalDate.now())%30==0 && !LocalDate.parse(credit.startDate).equals(LocalDate.now())){
            	int periodNumber = (int) (ChronoUnit.DAYS.between(LocalDate.parse(credit.startDate), LocalDate.now())/30);
                AnnuityPayment annuityPayment = credit.getAnnuityPayment().get(periodNumber-1);
                AccountProvider.getInstance().addMonoTransaction(
                        AccountProvider.getInstance().getCashBoxAccount(),
                        AccountProvider.getInstance().getAccountByCredit(credit)[0],
                        annuityPayment.principalAmount, credit.currency);
                AccountProvider.getInstance().addTransaction(
                        AccountProvider.getInstance().getAccountByCredit(credit)[1],
                        AccountProvider.getInstance().getFDBAccount(),
                        annuityPayment.percents, credit.currency);
                AccountProvider.getInstance().addMonoTransaction(
                        AccountProvider.getInstance().getCashBoxAccount(),
                        AccountProvider.getInstance().getAccountByCredit(credit)[1],
                        annuityPayment.percents, credit.currency);
            }

            if (LocalDate.parse(credit.endDate, dateMask).isEqual(LocalDate.now())) {
                if (credit.type == 2) {
                    AccountProvider.getInstance().addMonoTransaction(
                            AccountProvider.getInstance().getAccountByCredit(credit)[1],
                            AccountProvider.getInstance().getCashBoxAccount(),
                            -getPersentsSum(credit), credit.currency);
                    AccountProvider.getInstance().addMonoTransaction(
                            AccountProvider.getInstance().getAccountByCredit(credit)[0],
                            AccountProvider.getInstance().getCashBoxAccount(), credit.sum,
                            credit.currency);
                }
                AccountProvider.getInstance().addTransaction(
                        AccountProvider.getInstance().getAccountByCredit(credit)[0],
                        AccountProvider.getInstance().getFDBAccount(), credit.sum,
                        credit.currency);
                CreditProvider.getInstance().disableCredit(credit);
            }
        }
    }

    private static double getPersentsSum(Credit credit) {
        List<TransactionsInfo> transactions = AccountProvider.getInstance()
                .getTransactionsByAccount(
                        AccountProvider.getInstance().getAccountByCredit(credit)[1]);
        double totalPersentsSum = 0;
        for (TransactionsInfo transactionsInfo : transactions) {
            totalPersentsSum += transactionsInfo.sum;
        }
        return totalPersentsSum;
    }

}
