package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.CorporateCar;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;
import by.bsuir.verkpavel.courseproject.ui.add.AddCorparateCar;

public class CorporateCarMenuCreator extends BaseMenuCreator {

    private JMenu corporateCarMenu;

    public CorporateCarMenuCreator(BaseMenuCreator next) {
        super(next);
        corporateCarMenu = new JMenu("Служебные машины");
    }

    @Override
    public void showMenu(JMenuBar menuBar) {
        JMenuItem showCar = new JMenuItem("Просмотреть машины");
        showCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<CorporateCar> entity = DeliveryServiceDao.getInstance().getAllRecord(CorporateCar.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
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
               AddCorparateCar addCorparateCar = new AddCorparateCar();
               addCorparateCar.showView();
            }
        });
        corporateCarMenu.add(addCar);
    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        JMenuItem cancelСar = new JMenuItem("Списать машины");
        cancelСar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<CorporateCar> entity = DeliveryServiceDao.getInstance().getAllRecord(CorporateCar.class);
                EntityShowView entityShowView = new EntityShowView(entity, true);
                entityShowView.showView();
            }
        });
        corporateCarMenu.add(cancelСar);
    }

}
