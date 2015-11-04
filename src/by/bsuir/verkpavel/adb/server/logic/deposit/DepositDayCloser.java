package by.bsuir.verkpavel.adb.server.logic.deposit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.verkpavel.adb.server.data.AccountProvider;
import by.bsuir.verkpavel.adb.server.data.DepositProvider;
import by.bsuir.verkpavel.adb.server.data.entity.Deposit;
import by.bsuir.verkpavel.adb.server.data.entity.TransactionsInfo;
import by.bsuir.verkpavel.adb.server.logic.AbstractDayCloser;

public class DepositDayCloser extends AbstractDayCloser {

    @Override
    public void closeDay() {
        ArrayList<Deposit> deposits = DepositProvider.getInstance().getAllActiveDeposits();
        for (Deposit deposit : deposits) {
            AccountProvider.getInstance().addTransaction(
                    AccountProvider.getInstance().getFDBAccount(),
                    AccountProvider.getInstance().getAccountByDeposit(deposit)[1],
                    deposit.sum * deposit.persent / 360, deposit.currency);

            if (LocalDate.parse(deposit.startDate, dateMask).isEqual(LocalDate.now())) {
                AccountProvider.getInstance().addTransaction(
                        AccountProvider.getInstance().getAccountByDeposit(deposit)[0],
                        AccountProvider.getInstance().getFDBAccount(), deposit.sum,
                        deposit.currency);
            }
            if (LocalDate.now().getDayOfMonth() == LocalDate.now().lengthOfMonth()) {
                if (deposit.type == 2) {
                    AccountProvider.getInstance().addMonoTransaction(
                            AccountProvider.getInstance().getAccountByDeposit(deposit)[1],
                            AccountProvider.getInstance().getCashBoxAccount(),
                            -getPersentsSum(deposit), deposit.currency);
                }
            }

            if (LocalDate.parse(deposit.endDate, dateMask).isEqual(LocalDate.now())) {
                if (deposit.type == 2) {
                    DepositProvider.getInstance().updateDepositEndDate(
                            deposit,
                            LocalDate.parse(deposit.endDate, dateMask).plusMonths(1)
                                    .format(dateMask));
                    AccountProvider.getInstance().addMonoTransaction(
                            AccountProvider.getInstance().getAccountByDeposit(deposit)[1],
                            AccountProvider.getInstance().getCashBoxAccount(),
                            -getPersentsSum(deposit), deposit.currency);
                }
                if (deposit.type == 1) {
                    AccountProvider.getInstance().addMonoTransaction(
                            AccountProvider.getInstance().getAccountByDeposit(deposit)[1],
                            AccountProvider.getInstance().getCashBoxAccount(),
                            -getPersentsSum(deposit), deposit.currency);

                    AccountProvider.getInstance().addTransaction(
                            AccountProvider.getInstance().getFDBAccount(),
                            AccountProvider.getInstance().getAccountByDeposit(deposit)[0],
                            deposit.sum, deposit.currency);

                    AccountProvider.getInstance().addMonoTransaction(
                            AccountProvider.getInstance().getAccountByDeposit(deposit)[0],
                            AccountProvider.getInstance().getCashBoxAccount(), -deposit.sum,
                            deposit.currency);

                    DepositProvider.getInstance().disableDeposit(deposit);
                }
            }
        }
    }

    private static double getPersentsSum(Deposit deposit) {
        List<TransactionsInfo> transactions = AccountProvider.getInstance()
                .getTransactionsByAccount(
                        AccountProvider.getInstance().getAccountByDeposit(deposit)[1]);
        double totalPersentsSum = 0;
        for (TransactionsInfo transactionsInfo : transactions) {
            totalPersentsSum += transactionsInfo.sum;
        }
        return totalPersentsSum;
    }
}
