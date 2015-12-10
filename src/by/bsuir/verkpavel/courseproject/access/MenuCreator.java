package by.bsuir.verkpavel.courseproject.access;

import javax.swing.JMenuBar;

import by.bsuir.verkpavel.courseproject.access.menu.BaseMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.ClientMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.CorporateCarMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.DeliveryMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.EmployeeMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.ModerationMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.MoneyMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.ParcelMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.PersonalMenuCreator;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;

public class MenuCreator {

    public static JMenuBar createPermissibleMenu(Employee currentUser) {
        JMenuBar menuBar = new JMenuBar();

        BaseMenuCreator menuCreator = new PersonalMenuCreator(null);
        switch(currentUser.getPosition().getIdPosition()){
        case 1:case 2:
            menuCreator.setNext(new MoneyMenuCreator(null));
            break;
        case 3:
            menuCreator.setNext(new EmployeeMenuCreator(null));
            break;
        case 5:case 10:
            menuCreator.setNext(new ClientMenuCreator(new EmployeeMenuCreator(
            new CorporateCarMenuCreator(new ParcelMenuCreator(new DeliveryMenuCreator(new MoneyMenuCreator(new ModerationMenuCreator(null))))))));
            break;
        case 6:
            menuCreator.setNext(new ClientMenuCreator(new ParcelMenuCreator(null)));
            break;
        case 7:case 8: case 9:
            menuCreator.setNext(new CorporateCarMenuCreator(new ParcelMenuCreator(new DeliveryMenuCreator(null))));
        }

        menuCreator.process(currentUser, menuBar);
        return menuBar;
    }
}
