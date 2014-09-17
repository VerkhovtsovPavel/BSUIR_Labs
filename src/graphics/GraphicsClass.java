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
	private static int screenSize;
	private static final int SCALE = 15;

	private static BaseApproximation approximationMethod;


	/**
	 * Drawing form.
	 * @param g graphics object
	 */
	public void paint(final Graphics g){
		float[] points = new float[approximationMethod.getArraySize()*2];
		for(float i=0; i< approximationMethod.getArraySize(); i+=0.5){
			points[(int)(i*2)]=(float) approximationMethod.approximazeFunction(i);
		}

		for (int i=0; i<points.length-1; i++){
			g.drawOval(i*SCALE, (int) points[i]*SCALE+screenSize/2, circleRadius, circleRadius);
			g.drawLine(i*SCALE, (int) points[i]*SCALE+screenSize/2, (i+1)*SCALE, (int) points[i+1]*SCALE+screenSize/2);
		}
	}

	/**
	 * Show form with visualization classes.
	 * @param apprMethod Method for approximation function
	 * @param header Form header
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
