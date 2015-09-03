package by.bsuir.verkpavel.adb.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataProvider {
    static DataProvider instance;
    private Connection connection;

    private static final String DB_PATH = "jdbc:mysql://localhost:3306/menu";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "123456";

    private static final String SELECT_MENU = "SELECT `idDish`, `dishName`, `cost`, `dishClass`, GROUP_CONCAT(`Name`) AS `products` FROM `dish` JOIN `dish_has_products` ON `Dish_idDish`=`idDish` JOIN `products` ON `Products_idProducts` = `idProducts` GROUP BY (`idDish`);";

    private DataProvider() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_PATH, DB_USER_NAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    };

    public static DataProvider getInstance() {
        if (instance == null) {
            instance = new DataProvider();
        }
        return instance;
    }

    public Object getData() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_MENU);

            // while (rs.next()) {
            // int id = rs.getInt("idDish");
            // String dishName = rs.getString("dishName");
            // int cost = rs.getInt("cost");
            // String dishClass = rs.getString("dishClass");
            // }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<String> getCityList() {
        ArrayList<String> cities = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `RealCity` FROM `city`;");
            while (rs.next()) {
                cities.add(rs.getString("RealCity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public ArrayList<String> getFamilyStatuses() {
        ArrayList<String> familyStatuses = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `Status` FROM `familystatus`;");
            while (rs.next()) {
                familyStatuses.add(rs.getString("Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return familyStatuses;
    }

    public ArrayList<String> getNationalitys() {
        ArrayList<String> nationalitys = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `Nationality` FROM `nationality`;");
            while (rs.next()) {
                nationalitys.add(rs.getString("Nationalitys"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nationalitys;
    }

    public ArrayList<String> getDisabilitys() {
        ArrayList<String> disabilitys = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `Disability` FROM `disability`;");
            while (rs.next()) {
                disabilitys.add(rs.getString("Disability"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disabilitys;
    }
}
