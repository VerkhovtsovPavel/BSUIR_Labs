package main;

import ui.MainView;
import util.LUT;

public class Main {

    public static void main(String[] args) {
        LUT.initialize();
        new MainView(null).showView();
    }
}
