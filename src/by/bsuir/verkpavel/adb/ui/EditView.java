package by.bsuir.verkpavel.adb.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import by.bsuir.verkpavel.adb.data.Client;
import by.bsuir.verkpavel.adb.data.DataProvider;

public class EditView extends ActionView {
    private static final long serialVersionUID = 844868122106482549L;

    protected EditView() throws ParseException {
        super();
    }

    @Override
    protected void customActions() throws ParseException {
        fillFields(currentClient);
        
        JButton editBtn = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
        editBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Client client = getClient();
                if(client!=null){
                    client.setId(currentClient.id);
                    JOptionPane.showMessageDialog(null,  DataProvider.getInstance().updateClient(client), "Message", JOptionPane.PLAIN_MESSAGE);
                    ShowUsersView.create();
                    dispose();
                }
            }
        });
        editBtn.setBounds(219, 416, 216, 23);
        mainPanel.add(editBtn);
    }
    
    

}
