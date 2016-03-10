package by.bsuir.verkpavel.adb.bank_server.ui.deposit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import by.bsuir.verkpavel.adb.bank_server.data.AccountProvider;
import by.bsuir.verkpavel.adb.bank_server.data.DepositProvider;
import by.bsuir.verkpavel.adb.bank_server.data.entity.Account;
import by.bsuir.verkpavel.adb.bank_server.data.entity.Deposit;

public class AddView extends ActionView {
    private static final long serialVersionUID = -5177836715738946733L;

    protected AddView() throws ParseException {
        super();
    }

    @Override
	protected void customActions() {
		setTitle("Добавление депозита");
		
        JButton saveBtn = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Deposit deposit = getDeposit();
                if(deposit!=null){
                	JOptionPane.showMessageDialog(null,  DepositProvider.getInstance().saveDeposit(
                			deposit), "Message", JOptionPane.PLAIN_MESSAGE);
                	AccountProvider.getInstance().createAccountsByDeposit(deposit);
                	Account mainUserAccount = AccountProvider.getInstance().getAccountByDeposit(deposit)[0];
                	AccountProvider.getInstance().addMonoTransaction(AccountProvider.getInstance().getCashBoxAccount(), mainUserAccount, deposit.sum, deposit.currency);
                    ShowDepositsView.create();
                    dispose();
                } 
            }
        });
        saveBtn.setBounds(219, 280, 216, 23);
        mainPanel.add(saveBtn);
	}

}
