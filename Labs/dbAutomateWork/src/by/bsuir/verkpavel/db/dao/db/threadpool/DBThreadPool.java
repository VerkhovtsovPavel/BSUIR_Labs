package by.bsuir.verkpavel.db.dao.db.threadpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class DBThreadPool {

    private static final String DB_PREF = "jdbc:jtds:sqlserver://";
    
    private BlockingQueue<DBTask> tasks;
    private int threadsCount = 5;
    private ArrayList<DBThread> threads = new ArrayList<DBThread>();
    private ArrayList<Connection> connections = new ArrayList<Connection>();
    private Logger log = Logger.getLogger(DBThreadPool.class);
    
    public boolean isSuccessfullyCreated;

    public void addTask(DBTask task) {
        tasks.offer(task);
    }

    public DBThreadPool(int countOfThreads, String connectionString, String userName, String password) {
        this.isSuccessfullyCreated = true;
        this.threadsCount = countOfThreads;
        tasks = new LinkedBlockingQueue<DBTask>();
        loadDriver();
        try {
            for (int i = 0; i < threadsCount; i++) {
                connections.add(DriverManager.getConnection(DB_PREF+connectionString, userName, password));
                log.info("Connection № "+1+"created");
            }
        } catch (SQLException e) {
            log.fatal("Can't create connection", e);
            JOptionPane.showMessageDialog(null, "Невозможно создать соединение",
                    "Error", JOptionPane.PLAIN_MESSAGE);
            isSuccessfullyCreated = false;
        }
        
    }

    private void loadDriver() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.fatal("Can't load db driver", e);
        }

    }

    public void startTreads() {
        for (int i = 0; i < threadsCount; i++) {
            DBThread thread = new DBThread(tasks, connections.get(i));
            threads.add(thread);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @Override
    protected void finalize() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
