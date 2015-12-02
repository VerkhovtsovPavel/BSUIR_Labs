package by.bsuir.verkpavel.db.dao.db.threadpool;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DBDaoThreadPool {

    private BlockingQueue<DBDaoTask> tasks;
    private int threadsCount = 5;
    private ArrayList<DBDaoThread> threads = new ArrayList<DBDaoThread>();

    public void addTask(DBDaoTask task) {
        tasks.offer(task);
    }

    public DBDaoThreadPool(int countOfThreads, String connectionString, String userName, String password){
        this.threadsCount = countOfThreads;
        tasks = new LinkedBlockingQueue<DBDaoTask>();
        startTreads(connectionString, userName, password);
    }

    private void startTreads(String connectionString, String userName, String password) {
        for (int i = 0; i < threadsCount; i++) {
            DBDaoThread thread = new DBDaoThread(tasks, connectionString, userName, password);
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
