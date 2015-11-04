package by.bsuir.verkpavel.adb.server.ui.client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import by.bsuir.verkpavel.adb.server.data.ClientProvider;
import by.bsuir.verkpavel.adb.server.data.entity.Client;

public class AddView extends ActionView {
    private static final long serialVersionUID = -5177836715738946733L;

    protected AddView() throws ParseException {
        super();
    }

    @Override
    protected void customActions() {
        setTitle("\u0414\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043A\u043B\u0438\u0435\u043D\u0442\u0430");

        JButton saveBtn = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Client client = getClient();
                if (client != null) {
                    JOptionPane.showMessageDialog(null,
                            ClientProvider.getInstance().saveClient(client), "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    ShowUsersView.create();
                    dispose();
                }
            }

        });
        saveBtn.setBounds(219, 416, 216, 23);
        mainPanel.add(saveBtn);
    }

}
