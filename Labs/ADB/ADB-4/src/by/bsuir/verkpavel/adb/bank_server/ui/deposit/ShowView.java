package by.bsuir.verkpavel.adb.bank_server.ui.deposit;

import java.awt.Component;
import java.text.ParseException;

public class ShowView extends ActionView {
    private static final long serialVersionUID = 5886161954641963633L;
    
    protected ShowView() throws ParseException {
        super();
    }

    @Override
    protected void customActions() throws ParseException {
        fillFields(currentDeposit);
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(false);
        }
        startDateField.getComponent(1).setEnabled(false);
        endDateField.getComponent(1).setEnabled(false);
    }
}
