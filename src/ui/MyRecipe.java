package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import driver.DataBaseDriver;


public class MyRecipe extends JFrame {

	private static final long serialVersionUID = 2377892044773977952L;
	private static DataBaseDriver dbDriver;

	public MyRecipe() {
		configureDefaultLayot();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		initialaze();
	}
	
	public static void create(DataBaseDriver dbUtils){
		dbDriver = dbUtils;
		initialaze();
	}
	
	private static void initialaze(){
		MyRecipe frame = new MyRecipe();
		frame.setSize(465, 140);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("\u041C\u043E\u0438 \u0440\u0435\u0446\u0435\u043F\u0442\u044B");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

}
