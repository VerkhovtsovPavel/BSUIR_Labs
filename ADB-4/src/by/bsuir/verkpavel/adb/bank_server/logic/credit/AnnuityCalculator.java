package by.bsuir.verkpavel.adb.bank_server.logic.credit;

import java.util.ArrayList;

import by.bsuir.verkpavel.adb.bank_server.data.entity.AnnuityPayment;

public interface AnnuityCalculator {
    public ArrayList<AnnuityPayment> paymentsSheduling();
}
