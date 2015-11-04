package by.bsuir.verkpavel.adb.server.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance;
    private Connection connection;

    private static final String DB_PATH = "jdbc:mysql://localhost:3306/bank_users?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "123456";

    private ConnectionManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_PATH, DB_USER_NAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("DB driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
}
