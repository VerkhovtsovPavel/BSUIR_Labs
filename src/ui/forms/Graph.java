package ui.forms;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

import algorithms.Plotter;

public class Graph extends JFrame {
	private static final long serialVersionUID = -108427149258734371L;
	private static Plotter plotter;

	public Graph() {
		configureDefaultLayot();
	}

	public static void create(Plotter graphPlotter) {
		plotter = graphPlotter;
		initialaze();
	}
	
	public void paint(final Graphics g){
		plotter.buildGraph();
	}

	private static void initialaze(){
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
		setTitle("Graph");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
