package by.bsuir.verkpavel.adb.server.logic.credit;

import java.util.ArrayList;

import by.bsuir.verkpavel.adb.server.data.entity.AnnuityPayment;

public interface AnnuityCalculator {
    public ArrayList<AnnuityPayment> paymentsSheduling();
}
