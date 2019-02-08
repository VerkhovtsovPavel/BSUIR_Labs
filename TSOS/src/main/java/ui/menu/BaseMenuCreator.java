package ui.menu;

import ui.MainView;

import javax.swing.*;

public abstract class BaseMenuCreator {

    protected MainView targetFrame;

    private BaseMenuCreator next;

    BaseMenuCreator(BaseMenuCreator next) {
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

    public void setTargetFrame(MainView frame) {
        targetFrame = frame;
        if (next != null) {
            next.setTargetFrame(frame);
        }
    }
}
