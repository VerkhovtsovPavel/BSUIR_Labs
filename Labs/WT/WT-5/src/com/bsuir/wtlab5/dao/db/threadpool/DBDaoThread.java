package com.bsuir.wtlab5.dao.db.threadpool;

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

	private static final String DB_PATH = "jdbc:mysql://localhost:3306/menu";
	private static final String DB_USER_NAME = "root";
	private static final String DB_PASSWORD = "123456";

	private Connection connection;

	private BlockingQueue<DBDaoTask> taskQueue;

	public DBDaoThread(BlockingQueue<DBDaoTask> tasks) throws SQLException {
		this.taskQueue = tasks;
		this.connection = DriverManager.getConnection(DB_PATH, DB_USER_NAME, DB_PASSWORD);
	}

	@Override
	public void run() {
		DBDaoTask task = null;
		while (true) {
			try {
				task = taskQueue.poll(30, TimeUnit.SECONDS);
				if (task != null) {
					log.info("Get new task");
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(task.getRequest());
					task.setResultSet(rs);
					synchronized (task) {
						task.notify();
					}
				}	
			} catch (InterruptedException | SQLException e) {
				log.error("Error while execute request");
				synchronized (task) {
					task.notify();
				}
			}
		}
	}

}
