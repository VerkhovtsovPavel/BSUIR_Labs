package graphics;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import approximation.Lagrange;



/**
 * Class containing methods to visualize classes.
 * @author Pavel_Verkhovtsov
 */
public class GraphicsClass extends JPanel{
	private static final long serialVersionUID = 1L; //service variable
	private final int circleRadius = 5;
	private static Lagrange lagrange;
	private static String header;
	private static int screenSize;


	/**
	 * Drawing form.
	 * @param g graphics object
	 */
	public void paint(final Graphics g){
		float[] points = new float[40];
		for(float i=0; i<20; i+=0.5){
			points[(int)(i*2)]=(float) lagrange.approximazeFunction(i);
			//g.drawOval((int) i*30, screenSize/2+(int) lagrange.approximazeFunction(i)*30, circleRadius, circleRadius);
		}
		
		for (int i=0; i<points.length; i+=2){
			g.drawLine(i*30, (int) points[i]*30, (i+1)*30, (int) points[i+1]*30);
		}
	}

	/**
	 * Show form with visualization classes.
	 */
	public static void buildGraph(Lagrange lag){
		lagrange = lag;
		JPanel panel = new GraphicsClass();
        panel.setOpaque(true);

        JFrame mainFrame = new JFrame(header);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(panel);
        mainFrame.setSize(screenSize, screenSize);
        mainFrame.setVisible(true);
        mainFrame.setBackground(Color.white);
	}

	/**
	 * Setter to screen size.
	 * @param scrSize screen size
	 */
	public static void setScreenSize(final int scrSize){
		screenSize = scrSize;
	}

	
}
