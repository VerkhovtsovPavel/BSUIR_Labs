package by.bsuir.verkpavel.adb.ui.credit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import by.bsuir.verkpavel.adb.data.AccountProvider;
import by.bsuir.verkpavel.adb.data.CreditProvider;
import by.bsuir.verkpavel.adb.data.entity.Account;
import by.bsuir.verkpavel.adb.data.entity.Credit;

public class AddView extends ActionView {
    private static final long serialVersionUID = -5177836715738946733L;

    protected AddView() throws ParseException {
        super();
    }

    @Override
	protected void customActions() {
		setTitle("Добавление кредита");
		
        JButton saveBtn = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Credit credit = getCredit();
                if(credit!=null){
                	JOptionPane.showMessageDialog(null,  CreditProvider.getInstance().saveCredit(
                			credit), "Message", JOptionPane.PLAIN_MESSAGE);
                	AnnuityPaymentScheduleView.create();
                	AccountProvider.getInstance().createAccountsByCredit(credit);
                	Account mainUserAccount = AccountProvider.getInstance().getAccountByCredit(credit)[0];
                	//TODO Add transaction from FDB to mainUserAccount
                	AccountProvider.getInstance().addMonoTransaction(AccountProvider.getInstance().getCashBoxAccount(), mainUserAccount, credit.sum, credit.currency);
                    ShowCreditsView.create();
                    dispose();
                } 
            }
        });
        saveBtn.setBounds(219, 280, 216, 23);
        mainPanel.add(saveBtn);
	}

}
