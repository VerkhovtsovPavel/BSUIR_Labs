package by.bsuir.verkpavel.adb.main;

import by.bsuir.verkpavel.adb.ui.ActionMode;
import by.bsuir.verkpavel.adb.ui.ActionView;
import by.bsuir.verkpavel.adb.ui.ShowUsersView;

public class Main {

    public static void main(String[] args){
        ActionView.create(ActionMode.EDIT);
        ShowUsersView.create();
    }
}
