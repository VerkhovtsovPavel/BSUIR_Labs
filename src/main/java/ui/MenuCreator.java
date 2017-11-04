package ui;

import ui.menu.BaseMenuCreator;
import ui.menu.ClientMenuCreator;
import ui.menu.PersonalMenuCreator;

import javax.swing.JMenuBar;


public class MenuCreator {

    public static JMenuBar createPermissibleMenu() {
        JMenuBar menuBar = new JMenuBar();
        BaseMenuCreator menuCreator = new PersonalMenuCreator(null);
        menuCreator.setNext(new ClientMenuCreator(null));
        menuCreator.process(menuBar);
        return menuBar;
    }
}
