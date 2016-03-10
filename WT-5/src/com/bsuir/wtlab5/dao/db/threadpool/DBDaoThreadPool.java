package com.bsuir.wtlab5.dao.db.threadpool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DBDaoThreadPool {

	private static DBDaoThreadPool instance;
	private BlockingQueue<DBDaoTask> tasks;
	private static int threadsCount = 5;
	private static ArrayList<DBDaoThread> threads = new ArrayList<DBDaoThread>();
	
	public static DBDaoThreadPool getInstance() throws SQLException{
		if(instance==null){
			instance = new DBDaoThreadPool();
		}
		return instance;
	}
	
	public void addTask(DBDaoTask task){
		tasks.offer(task);
	}
	
	private DBDaoThreadPool() throws SQLException{
		tasks = new LinkedBlockingQueue<DBDaoTask>();
		startTreads();
	}

	private void startTreads() throws SQLException {
		for(int i=0; i< threadsCount; i++){
			DBDaoThread thread = new DBDaoThread(tasks);
			threads.add(thread);
			thread.setDaemon(true);
			thread.start();
		}
	}
}
