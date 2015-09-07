package by.bsuir.verkpavel.adb.ui;

import java.awt.Component;
import java.text.ParseException;

public class ShowView extends ActionView {
    protected ShowView() throws ParseException {
        super();
    }

    private static final long serialVersionUID = 5886161954641963633L;

    @Override
    protected void customActions() throws ParseException {
        fillFields(currentClient);
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(false);
        }
    }
}
