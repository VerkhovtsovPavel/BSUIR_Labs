package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ParcelMenuCreator extends BaseMenuCreator {

    private JMenu parcellMenu;

    public ParcelMenuCreator(BaseMenuCreator next) {
        super(next);
        parcellMenu = new JMenu("Посылки");
    }

    @Override
    public void showMenu(JMenuBar menuBar) {
        JMenuItem showParcel = new JMenuItem("Просмотреть посылки");
        showParcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        parcellMenu.add(showParcel);
        menuBar.add(parcellMenu);
    }

    @Override
    public void editMenu(JMenuBar menuBar) {
        JMenuItem addParcel = new JMenuItem("Добавить посылку");
        addParcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        parcellMenu.add(addParcel);
    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        JMenuItem deleteParcel = new JMenuItem("Удалить посылку");
        deleteParcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        parcellMenu.add(deleteParcel);
    }
}
