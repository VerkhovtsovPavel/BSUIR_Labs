package by.bsuir.verkpavel.db.dao.db.threadpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class DBThread extends Thread {
    private Logger log = Logger.getLogger(DBThread.class);

    private Connection connection;

    private BlockingQueue<DBTask> taskQueue;

    public DBThread(BlockingQueue<DBTask> tasks, Connection connection) {
        this.taskQueue = tasks;
        this.connection = connection;

    }

    @Override
    public void run() {
        DBTask task = null;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                task = taskQueue.poll(30, TimeUnit.SECONDS);
                if (task != null) {
                    log.info("Start execute query #"+task.getNumber());
                    if (task.getIsTransaction()) {
                        executeTransaction(task);
                    } else {
                        executeWithOutTransaction(task);
                    }
                    synchronized (task) {
                        task.notify();
                    }
                    log.info("Finish execute query #"+task.getNumber()+" without errors");
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

    private void executeTransaction(DBTask task) throws SQLException {
        String keyWord = task.getRequest().split(" ")[0].trim().toLowerCase();
        Statement statement = connection.createStatement();
        if (keyWord.equals("select")) {
            ResultSet rs = statement.executeQuery(task.getRequest());
            task.setResultSet(rs);
        } else {
            statement.executeUpdate(task.getRequest());
        }

    }

    private void executeWithOutTransaction(DBTask task) {
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
