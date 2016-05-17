package by.bsuir.verkpavel.courseproject.main;

import javax.swing.UIManager;

import by.bsuir.verkpavel.courseproject.ui.LoginView;

public class Main {

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginView loginView = new LoginView();
        loginView.showView();
    }
}
