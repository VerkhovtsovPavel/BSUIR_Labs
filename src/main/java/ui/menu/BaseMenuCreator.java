package ui.menu;

import javax.swing.*;

public abstract class BaseMenuCreator {

    private BaseMenuCreator next;

    protected BaseMenuCreator(BaseMenuCreator next) {
        this.next = next;
    }

    private void next(JMenuBar menuBar) {
        if (next != null) {
            next.process(menuBar);
        }
    }

    public void process(JMenuBar menuBar) {
        menuBar.add(createMenuTab());
        next(menuBar);
    }

    public BaseMenuCreator setNext(BaseMenuCreator next){
        this.next = next;
        return next;
    }

    public abstract JMenu createMenuTab();
}
