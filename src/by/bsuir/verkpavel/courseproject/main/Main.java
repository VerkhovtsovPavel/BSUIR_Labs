package by.bsuir.verkpavel.courseproject.main;

import java.sql.SQLException;

import by.bsuir.verkpavel.courseproject.ui.LoginView;

public class Main {

    public static void main(String[] args) throws SQLException {
        LoginView loginView = new LoginView();
        loginView.showView();
    }
}
