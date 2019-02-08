package ui.menu;

import signals.Signal;
import ui.MainView;

import javax.swing.*;

public class SettingMenuCreator extends BaseMenuCreator {

    private JMenu settingMenu;

    public SettingMenuCreator(BaseMenuCreator next) {
        super(next);
    }

    @Override
    public JMenu createMenuTab() {
        settingMenu = new JMenu("Setting");
        JMenuItem changeLogin = new JMenuItem("Set N");
        changeLogin.addActionListener(e -> {
            Signal signal = targetFrame.currentSignal();
            int n = Integer.parseInt(JOptionPane.showInputDialog(targetFrame, "Please enter new N value: "));
            targetFrame.addSignal(signal.getValues(), n, signal.getF());
        });

        settingMenu.add(changeLogin);
        return settingMenu;
    }
}
