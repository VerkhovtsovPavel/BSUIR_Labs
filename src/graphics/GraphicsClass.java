package graphics;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import approximation.BaseApproximation;
import approximation.Lagrange;
import approximation.Newton;



/**
 * Class containing methods to visualize classes.
 * @author Pavel_Verkhovtsov
 */
public class GraphicsClass extends JPanel{
	private static final long serialVersionUID = 1L; //service variable
	private final int circleRadius = 5;
	//private static Lagrange lagrange;
	//private static String header;
	private static int screenSize;
	private static int scale = 15;

	private static BaseApproximation approximationMethod;


	/**
	 * Drawing form.
	 * @param g graphics object
	 */
	public void paint(final Graphics g){
		float[] points = new float[40];
		for(float i=0; i< 20; i+=0.5){
			points[(int)(i*2)]=(float) approximationMethod.approximazeFunction(i);
		}

		for (int i=0; i<points.length-1; i++){
			g.drawOval(i*scale, (int) points[i]*scale+screenSize/2, circleRadius, circleRadius);
			g.drawLine(i*scale, (int) points[i]*scale+screenSize/2, (i+1)*scale, (int) points[i+1]*scale+screenSize/2);
		}
	}

	/**
	 * Show form with visualization classes.
	 */
	/*public static void buildGraph(Lagrange lagrange, String header){
		points = new float[40];
		for(float i=0; i<20; i+=0.5){
			points[(int)(i*2)]=(float) lagrange.approximazeFunction(i);
		}

		JPanel panel = new GraphicsClass();
        panel.setOpaque(true);

        JFrame mainFrame = new JFrame(header);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(panel);
        mainFrame.setSize(screenSize, screenSize);
        mainFrame.setVisible(true);
        mainFrame.setBackground(Color.white);
	}
	
	public static void buildGraph(Newton newton, String header){
		

		JPanel panel = new GraphicsClass();
        panel.setOpaque(true);

        JFrame mainFrame = new JFrame(header);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(panel);
        mainFrame.setSize(screenSize, screenSize);
        mainFrame.setVisible(true);
        mainFrame.setBackground(Color.white);
	}*/


	/**
	 * Show form with visualization classes.
	 */
	public static void buildGraph(final BaseApproximation apprMethod, final String header){
		approximationMethod = apprMethod;
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
