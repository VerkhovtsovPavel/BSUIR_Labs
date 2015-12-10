package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.Delivery;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;
import by.bsuir.verkpavel.courseproject.ui.add.AddDeliveryView;
import by.bsuir.verkpavel.courseproject.ui.add.AddParcelToDeliveryView;

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
                List<Delivery> entity = DeliveryServiceDao.getInstance().getAllRecord(Delivery.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
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
                AddDeliveryView addDeliveryView = new AddDeliveryView();
                addDeliveryView.showView();
            }
        });

        JMenuItem addParcelToDelivery = new JMenuItem("Добавить товар в доставку");
        addParcelToDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddParcelToDeliveryView addParcelToDeliveryView = new AddParcelToDeliveryView(false);
                addParcelToDeliveryView.showView();
            }
        });
        personalMenu.add(addDelivery);
        personalMenu.add(addParcelToDelivery);
    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        JMenuItem deleteParcelToDelivery = new JMenuItem("Удалить товар в доставки");
        deleteParcelToDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddParcelToDeliveryView addParcelToDeliveryView = new AddParcelToDeliveryView(true);
                addParcelToDeliveryView.showView();
            }
        });
        personalMenu.add(deleteParcelToDelivery);
    }
}
