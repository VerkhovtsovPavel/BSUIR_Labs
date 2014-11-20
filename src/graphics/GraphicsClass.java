package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

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

	private static double[] WeightFactors;
	private static double minX, maxX, minY, maxY, ComX, ComY, it, jt;
	private static double[][] StudySample;
	private static Graphics graphics;

	/**
	 * Drawing form.
	 * 
	 * @param g
	 *            graphics object
	 */
	public void paint(final Graphics g) {
		graphics=g;
		axis(g);
		someDraw(g);
		g.setColor(Color.BLACK);
	}

	public static void graphicsInitialaze(double[] WeightFactors, double[][] StudySample, double minX, double maxX, double minY, double maxY, double ComX, double ComY, double it, double jt) {
		GraphicsClass.WeightFactors = WeightFactors;
		GraphicsClass.StudySample = StudySample;
		GraphicsClass.minX = minX;
		GraphicsClass.maxX = maxX;
		GraphicsClass.minY = minY;
		GraphicsClass.maxY = maxY;
		GraphicsClass.ComX = ComX;
		GraphicsClass.ComY = ComY;
		GraphicsClass.it = it;
		GraphicsClass.jt = jt;
	}
	

	public static void addPoint(Color color,int x,int y){
		graphics.setColor(color);
		graphics.drawOval(x, y, 5, 5);
	}
	
	
	private static void someDraw(Graphics graphics) {
		Point startPoint = new Point((int) ((it - minX) * ComX), (int) (screenSize - ((jt - minY) * ComY)));
		while (it <= maxX) {
			if (WeightFactors[3] != 0) {
				if (it != -WeightFactors[2] / WeightFactors[3]) {
					if ((WeightFactors[2] != 0) && (WeightFactors[3] != 0)) {
						jt = -(WeightFactors[0] + WeightFactors[1] * it) / (WeightFactors[2] + WeightFactors[3] * it);
					} else {
						jt = -(WeightFactors[0] + WeightFactors[1] * it);
					}
				}
			} else if (WeightFactors[2] != 0) {
				jt = -(WeightFactors[0] + WeightFactors[1] * it) / (WeightFactors[2] + WeightFactors[3] * it);
			} else {
				jt = -(WeightFactors[0] + WeightFactors[1] * it);
			}

			if ((jt <= maxY) && (jt >= minY)) {
				graphics.drawLine(startPoint.x, startPoint.y, (int) ((it - minX) * ComX), 370 - (int) ((jt - minY) * ComY));

			} else {
				startPoint = new Point((int) ((it - minX) * ComX), (int) (screenSize - ((jt - minY) * ComY)));
			}

			it = it + 0.001;
		}
		if (WeightFactors[0] / WeightFactors[2] == WeightFactors[1] / WeightFactors[3]) {

			graphics.drawLine((int) ((-minX + WeightFactors[1] / WeightFactors[3]) * ComX), 0,
					(int) ((-minX + WeightFactors[1] / WeightFactors[3]) * ComX), 370);

		}
	 	Color color =Color.GREEN;
		for (int i = 0; i < 4; i++) {
			if (i == 2) {
				color =Color.RED;
			}
			addPoint(color, (int)((StudySample[i][0]-minX)*ComX), 370-(int)((StudySample[i][1]-minY)*ComY));
		}
	}

	private static void axis(Graphics graphics) {
		graphics.setColor(Color.blue);
		graphics.drawLine(0,370-(int)(-ComY*minY),545,370-(int)(-ComY*minY));
		graphics.drawLine((int)(-minX*ComX),0,(int)(-minX*ComX),370);
		graphics.setColor(Color.black);
	}

	public static void buildGraph() {
		JPanel panel = new GraphicsClass();
		panel.setOpaque(true);
		
		JFrame mainFrame = new JFrame("MiAPR-Lab3");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setContentPane(panel);
		mainFrame.setSize((int) (screenSize * 2.3), screenSize);
		mainFrame.setVisible(true);
		mainFrame.setBackground(Color.white);
	}

}
