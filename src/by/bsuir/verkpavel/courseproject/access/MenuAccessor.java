package by.bsuir.verkpavel.courseproject.access;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.dao.entity.Employee;

public class MenuAccessor {

    public MenuAccessor() {
        // TODO Auto-generated constructor stub
    }

    public static JMenuBar createMenu(Employee currentUser) {
        JMenuBar menuBar = new JMenuBar();
        createPersonalMenu(menuBar, currentUser);
        createEmployeeMenu(menuBar, currentUser);
        createUsersMenu(menuBar, currentUser);
        createDeliveryMenu(menuBar, currentUser);
        createParcelMenu(menuBar, currentUser);
        createCorporateCarMenu(menuBar, currentUser);
        
        Font font = new Font("Verdana", Font.PLAIN, 11);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);
        JMenuItem addRecipe = new JMenuItem("Add recipe");
        addRecipe.setFont(font);
        fileMenu.add(addRecipe);
        addRecipe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        JMenuItem myRecipe = new JMenuItem("Recipe-book");
        myRecipe.setFont(font);
        myRecipe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ShowRecipe.create("Recipe-book", dbDriver.getAll());
            }
        });
        fileMenu.add(myRecipe);
        JMenu otherSearches = new JMenu("More searches");
        otherSearches.setFont(font);
        fileMenu.add(otherSearches);
        ButtonGroup directionGroup = new ButtonGroup();
        JMenuItem timeRequired = new JMenuItem("Time required");
        timeRequired.setFont(font);
        timeRequired.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ModalForm.create("Time required", true);
            }
        });
        otherSearches.add(timeRequired);
        directionGroup.add(timeRequired);
        JMenuItem recipeName = new JMenuItem("Recipe name");
        recipeName.setFont(font);
        recipeName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ModalForm.create("Recipe name", false);
            }
        });
        otherSearches.add(recipeName);
        directionGroup.add(recipeName);
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        
        return menuBar;
    }

	private static void createPersonalMenu(JMenuBar menuBar,
			Employee currentUser) {
		// TODO Auto-generated method stub
		
	}

	private static void createCorporateCarMenu(JMenuBar menuBar,
			Employee currentUser) {
		// TODO Auto-generated method stub
		
	}

	private static void createParcelMenu(JMenuBar menuBar, Employee currentUser) {
		// TODO Auto-generated method stub
		
	}

	private static void createDeliveryMenu(JMenuBar menuBar,
			Employee currentUser) {
		// TODO Auto-generated method stub
		
	}

	private static void createUsersMenu(JMenuBar menuBar, Employee currentUser) {
		// TODO Auto-generated method stub
		
	}

	private static void createEmployeeMenu(JMenuBar menuBar,
			Employee currentUser) {
		// TODO Auto-generated method stub
		
	}

}
