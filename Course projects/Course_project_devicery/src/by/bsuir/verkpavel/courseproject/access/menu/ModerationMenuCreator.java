package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.DeliveryStatus;
import by.bsuir.verkpavel.courseproject.dao.entity.DriverLicenceCategory;
import by.bsuir.verkpavel.courseproject.dao.entity.MarkParcel;
import by.bsuir.verkpavel.courseproject.dao.entity.PaymentsSystemType;
import by.bsuir.verkpavel.courseproject.dao.entity.Permission;
import by.bsuir.verkpavel.courseproject.dao.entity.Position;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;

public class ModerationMenuCreator extends BaseMenuCreator {

    private JMenu moderationMenu;

    public ModerationMenuCreator(BaseMenuCreator next) {
        super(next);
        moderationMenu = new JMenu("Доп. списки");
    }

    @Override
    public void showMenu(JMenuBar menuBar) {
        JMenuItem showDeliveryStatus = new JMenuItem("Просмотр статуса доставки");
        showDeliveryStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<DeliveryStatus> entity = DeliveryServiceDao.getInstance().getAllRecord(DeliveryStatus.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
            }
        });

        JMenuItem showDriverLicenceCategory = new JMenuItem("Просмотр категорий прав");
        showDriverLicenceCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<DriverLicenceCategory> entity = DeliveryServiceDao.getInstance().getAllRecord(DriverLicenceCategory.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
            }
        });

        JMenuItem showMarkParcel = new JMenuItem("Просмотр типов посылок");
        showMarkParcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<MarkParcel> entity = DeliveryServiceDao.getInstance().getAllRecord(MarkParcel.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
            }   
        });
        
        JMenuItem showPaymentsSystemType = new JMenuItem("Просмотр способов оплаты");
        showPaymentsSystemType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<PaymentsSystemType> entity = DeliveryServiceDao.getInstance().getAllRecord(PaymentsSystemType.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
            }   
        });
        
        JMenuItem showPermission = new JMenuItem("Просмотр прав доступа");
        showPermission.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Permission> entity = DeliveryServiceDao.getInstance().getAllRecord(Permission.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
            }   
        });
        
        JMenuItem showPossition = new JMenuItem("Просмотр должностей");
        showPossition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Position> entity = DeliveryServiceDao.getInstance().getAllRecord(Position.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
            }   
        });

        moderationMenu.add(showDeliveryStatus);
        moderationMenu.add(showDriverLicenceCategory);
        moderationMenu.add(showMarkParcel);
        moderationMenu.add(showPaymentsSystemType);
        moderationMenu.add(showPermission);
        moderationMenu.add(showPossition);

        menuBar.add(moderationMenu); 

    }

    @Override
    public void editMenu(JMenuBar menuBar) {
        // Not need implement

    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        // Not need implement

    }

}
