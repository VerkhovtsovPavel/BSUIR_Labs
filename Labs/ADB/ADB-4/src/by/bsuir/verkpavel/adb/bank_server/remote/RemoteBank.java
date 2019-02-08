package by.bsuir.verkpavel.adb.bank_server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import by.bsuir.verkpavel.adb.bank_server.data.PaymentProvider;
import by.bsuir.verkpavel.adb.bank_server.data.PlasticCardProvider;
import by.bsuir.verkpavel.adb.bank_server.data.WithdrawalProvider;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;
import by.bsuir.verkpavel.adb.shared.OperationList;
import by.bsuir.verkpavel.adb.shared.OperationType;

public class RemoteBank extends UnicastRemoteObject implements IRemoteBank {
    private static final long serialVersionUID = 4330392012601578065L;

    public RemoteBank() throws RemoteException {
    }

    @Override
    public boolean isAuthenticate(OperationList operationList) {
        String cardNumber = operationList.getOperation(OperationType.CardNumber);
        String pinCode = operationList.getOperation(OperationType.PinCode);

        return PlasticCardProvider.getInstance().verifyCardData(cardNumber, pinCode);
    }

    @Override
    public boolean executePayments(OperationList operationList) {
        String organization = operationList.getOperation(OperationType.Operator);
        String cardNumber = operationList.getOperation(OperationType.CardNumber);
        double sum = operationList.getOperation(OperationType.OperationSum);
        return PaymentProvider.getInstance().executePaymentByOrganization(organization, cardNumber, sum);
    }

    @Override
    public boolean executeWithdrawal(OperationList operationList) throws RemoteException {
        String cardNumber = operationList.getOperation(OperationType.CardNumber);
        double sum = operationList.getOperation(OperationType.OperationSum);
        return   WithdrawalProvider.getInstance().executeWithdrawal(cardNumber, sum);
    }

    @Override
    public double getBalance(OperationList operationList) throws RemoteException {
        String cardNumber = operationList.getOperation(OperationType.CardNumber);
        String type = operationList.getOperation(OperationType.AccountType);
        return PlasticCardProvider.getInstance().getBalanceByCardAndType(cardNumber, type);
        
    }

}
