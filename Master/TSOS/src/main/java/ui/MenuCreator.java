package ui;

import ui.menu.ActionMenuCreator;
import ui.menu.BaseMenuCreator;
import ui.menu.FileMenuCreator;
import ui.menu.InfoMenuCreator;
import ui.menu.SettingMenuCreator;

import javax.swing.JMenuBar;

public class MenuCreator {

    public static JMenuBar createMenu(MainView frame) {
        JMenuBar menuBar = new JMenuBar();
        BaseMenuCreator menuCreator = new FileMenuCreator(new ActionMenuCreator(new SettingMenuCreator(new InfoMenuCreator(null))));
        menuCreator.setTargetFrame(frame);
        menuCreator.process(menuBar);
        return menuBar;
    }
}