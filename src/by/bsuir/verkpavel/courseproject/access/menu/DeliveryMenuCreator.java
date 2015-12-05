package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DeliveryMenuCreator extends BaseMenuCreator {

    private JMenu personalMenu;

    public DeliveryMenuCreator(BaseMenuCreator next) {
        super(next);
        personalMenu = new JMenu("Доставки");
    }

    @Override
    public void showMenu(JMenuBar menuBar) {
        JMenuItem showDelivery = new JMenuItem("Просмотреть доставки");
        showDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        personalMenu.add(showDelivery);
        menuBar.add(personalMenu);
    }

    @Override
    public void editMenu(JMenuBar menuBar) {
        JMenuItem addDelivery = new JMenuItem("Создать доставку");
        addDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        JMenuItem addParcelToDelivery = new JMenuItem("Добавить товар в достаку");
        addParcelToDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        personalMenu.add(addDelivery);
        personalMenu.add(addParcelToDelivery);
    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        JMenuItem deleteParcelToDelivery = new JMenuItem("Удалить товар в достаку");
        deleteParcelToDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        personalMenu.add(deleteParcelToDelivery);
    }
}
