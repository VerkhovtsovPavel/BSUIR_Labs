package by.bsuir.verkpavel.adb.ui;

import java.text.ParseException;

import javax.swing.JOptionPane;

public class EditView extends ActionView {
    private static final long serialVersionUID = 844868122106482549L;

    protected EditView() throws ParseException {
        super();
    }

    @Override
    protected void customActions() {
        JOptionPane.showMessageDialog(null, "Не все обязательные поля заполнены!",
                "Error", JOptionPane.PLAIN_MESSAGE);
    }
    
    

}
