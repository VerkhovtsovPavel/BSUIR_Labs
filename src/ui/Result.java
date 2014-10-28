package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Result extends JFrame {

	private static final long serialVersionUID = 6946609135675030780L;

	public Result() {
		configureDefaultLayot();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Result frame = new Result();
		frame.setSize(465, 140);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);

	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("\u0420\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u044B \u043F\u043E\u0438\u0441\u043A\u0430");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
