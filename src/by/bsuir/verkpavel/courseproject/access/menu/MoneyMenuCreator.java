package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.Payment;
import by.bsuir.verkpavel.courseproject.dao.entity.Rate;
import by.bsuir.verkpavel.courseproject.dao.entity.Salary;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;
import by.bsuir.verkpavel.courseproject.ui.change.ChangeRateView;
import by.bsuir.verkpavel.courseproject.ui.change.ChangeSalaryView;

public class MoneyMenuCreator extends BaseMenuCreator {

    private JMenu moneyMenu;

    public MoneyMenuCreator(BaseMenuCreator next) {
        super(next);
        moneyMenu = new JMenu("Деньги");
    }

    @Override
    public void showMenu(JMenuBar menuBar) {
        JMenuItem showSalary = new JMenuItem("Просмотреть зарплаты");
        showSalary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Salary> entity = DeliveryServiceDao.getInstance().getAllRecord(Salary.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
            }
        });
        moneyMenu.add(showSalary);
       
        JMenuItem showRate = new JMenuItem("Просмотреть расценки");
        showRate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Rate> entity = DeliveryServiceDao.getInstance().getAllRecord(Rate.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
            }
        });
        moneyMenu.add(showRate);
        
        JMenuItem showPayments = new JMenuItem("Просмотреть платежи");
        showRate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Payment> entity = DeliveryServiceDao.getInstance().getAllRecord(Payment.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
            }
        });
        moneyMenu.add(showPayments);

        menuBar.add(moneyMenu);
    }

    @Override
    public void editMenu(JMenuBar menuBar) {
        JMenuItem editSalary = new JMenuItem("Изменить зарплаты");
        editSalary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangeSalaryView changeSalaryView = new ChangeSalaryView();
                changeSalaryView.showView();
            }
        });
        moneyMenu.add(editSalary);
       
        JMenuItem editRate = new JMenuItem("Изменить расценки");
        editRate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangeRateView changeRateView = new ChangeRateView();
                changeRateView.showView();
            }
        });
        moneyMenu.add(editRate);

    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        // Not need implement
    }

}
