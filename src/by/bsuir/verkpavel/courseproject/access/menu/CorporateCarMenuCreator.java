package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CorporateCarMenuCreator extends BaseMenuCreator {

    private JMenu corporateCarMenu;

    public CorporateCarMenuCreator(BaseMenuCreator next) {
        super(next);
        corporateCarMenu = new JMenu("Служебные машины");
    }

    @Override
    public void showMenu(JMenuBar menuBar) {
        JMenuItem showCar = new JMenuItem("Просмотреть работников");
        showCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        corporateCarMenu.add(showCar);

        menuBar.add(corporateCarMenu);
    }

    @Override
    public void editMenu(JMenuBar menuBar) {
        JMenuItem addCar = new JMenuItem("Добавить машины");
        addCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        corporateCarMenu.add(addCar);
    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        JMenuItem cancelСar = new JMenuItem("Списать машины");
        cancelСar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        corporateCarMenu.add(cancelСar);
    }

}
