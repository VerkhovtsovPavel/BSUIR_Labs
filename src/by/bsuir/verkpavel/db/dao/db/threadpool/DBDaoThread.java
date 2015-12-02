package by.bsuir.verkpavel.db.dao.db.threadpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class DBDaoThread extends Thread {
    private Logger log = Logger.getLogger(DBDaoThread.class);

    private static final String DB_PREF = "jdbc:jtds:sqlserver://";

    private Connection connection;

    private BlockingQueue<DBDaoTask> taskQueue;

    public DBDaoThread(BlockingQueue<DBDaoTask> tasks, String connectionString, String userName, String password){
        this.taskQueue = tasks;
        try {
            this.connection = DriverManager.getConnection(DB_PREF+connectionString, userName, password);
        } catch (SQLException e) {
           log.fatal("Can't create connection", e);
        }
    }

    @Override
    public void run() {
        DBDaoTask task = null;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                task = taskQueue.poll(30, TimeUnit.SECONDS);
                if (task != null) {
                    log.info("Get new task");
                    if (task.getIsTransaction()) {
                        executeTransaction(task);
                    } else {
                        executeWithOutTransaction(task);
                    }
                    synchronized (task) {
                        task.notify();
                    }
                }
            } catch (SQLException e) {
                log.error("Error while execute request", e);
                synchronized (task) {
                    task.notify();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void executeTransaction(DBDaoTask task) throws SQLException {
        String keyWord = task.getRequest().split(" ")[0].trim().toLowerCase();
        Statement statement = connection.createStatement();
        if (keyWord.equals("select")) {
            ResultSet rs = statement.executeQuery(task.getRequest());
            task.setResultSet(rs);
        } else {
            statement.executeUpdate(task.getRequest());
        }

    }

    private void executeWithOutTransaction(DBDaoTask task) {
        String keyWord = task.getRequest().split(" ")[0].trim().toLowerCase();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            if (keyWord.equals("select")) {
                ResultSet rs = statement.executeQuery(task.getRequest());
                task.setResultSet(rs);
            } else {
                statement.executeUpdate(task.getRequest());
            }
        } catch (SQLException e) {
            try {
                log.error("Transaction be abourted", e);
                connection.rollback();
            } catch (SQLException e1) {
                log.error("Transaction can't be abourted", e1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.fatal("Can't restore autocommit", e);
            }
        }

    }

}
