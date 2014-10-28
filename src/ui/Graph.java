package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Graph extends JFrame {
	private static final long serialVersionUID = -108427149258734371L;

	public Graph() {
		configureDefaultLayot();
	}

	public static void main(String[] args) {

		Graph frame = new Graph();
		frame.setSize(465, 140);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);

	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("\u0413\u0440\u0430\u0444");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
