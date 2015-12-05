package by.bsuir.verkpavel.courseproject.access;

import javax.swing.JMenuBar;

import by.bsuir.verkpavel.courseproject.access.menu.BaseMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.ClientMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.CorporateCarMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.DeliveryMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.EmployeeMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.ParcelMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.PersonalMenuCreator;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;

public class MenuCreator {

    public static JMenuBar createPermissibleMenu(Employee currentUser) {
        JMenuBar menuBar = new JMenuBar();

        BaseMenuCreator menuCreator = new PersonalMenuCreator(new ClientMenuCreator(new EmployeeMenuCreator(
                new CorporateCarMenuCreator(new ParcelMenuCreator(new DeliveryMenuCreator(null))))));
        menuCreator.process(currentUser, menuBar);
        return menuBar;
    }
}
