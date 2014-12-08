package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class containing methods to visualize classes.
 * 
 * @author Pavel_Verkhovtsov
 */
public class GraphicsClass extends JPanel {
	private static final long serialVersionUID = 1L; // service variable
	private static final int screenSize = 600;
	private static Graphics graphics;
	private static JFrame mainFrame;
	private static ArrayList<ColorPoint> colorPoints= new ArrayList<ColorPoint>();
	
	private static int xScale;
	private static int yScale;

	/**
	 * Drawing form.
	 * 
	 * @param g
	 *            graphics object
	 */
	public void paint(final Graphics g) {
		graphics=g;
		axis(g);
		for(ColorPoint colorPoint: colorPoints){
			g.setColor(colorPoint.color);
			g.drawOval(colorPoint.location.x, colorPoint.location.y, colorPoint.size, colorPoint.size);
		}
		g.setColor(Color.BLACK);
	}


	public static void addPoint(Color color,double x,double y, int radius){
		colorPoints.add(new ColorPoint(new Point((int)(screenSize/2+x*xScale),(int)(screenSize/2-y*yScale)), color, radius));
		if(graphics!=null){
			mainFrame.repaint();
		}
	}
	
	
	public static void axis(Graphics graphics) {
		graphics.drawLine(0, screenSize/2, screenSize, screenSize/2);
		graphics.drawLine(screenSize/2, 0, screenSize/2, screenSize);
	}

	public static void buildGraph() {
		JPanel panel = new GraphicsClass();
		panel.setOpaque(true);
		
		mainFrame = new JFrame("MiAPR-Lab5");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setContentPane(panel);
		mainFrame.setSize((int) (screenSize), screenSize);
		xScale = (int)(screenSize/20);
		yScale = (int)screenSize/20;
		mainFrame.setVisible(true);
		mainFrame.setBackground(Color.white);
	}

}
