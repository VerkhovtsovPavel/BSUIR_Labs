package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import approximation.BaseApproximation;

/**
 * Class containing methods to visualize classes.
 *
 * @author Pavel_Verkhovtsov
 */
public class GraphicsClass extends JPanel {
	private static final long serialVersionUID = 1L; // service variable
	private final int circleRadius = 5;
	private static int screenSize;
	private static int scale;
	private static final float INTERPOLATION_STEP = (float) 0.1;

	private static BaseApproximation approximationMethod;

	/**
	 * Drawing form.
	 *
	 * @param g
	 *            graphics object
	 */
	public void paint(final Graphics g) {
		ArrayList<Double> points = new ArrayList<Double>();
		for (double i = approximationMethod.getMin(); i < approximationMethod.getMax(); i += INTERPOLATION_STEP) {
			points.add(approximationMethod.approximazeFunction(i));
		}
		points.add(approximationMethod.approximazeFunction(approximationMethod.getMax()));

		double currentX = approximationMethod.getMin();
		for (int i = 0; i < points.size()-1; i++) {
			g.drawOval((int) (currentX * scale), (int) (points.get(i) * scale + screenSize / 2), circleRadius, circleRadius);
			g.drawLine((int) (currentX * scale), (int) (points.get(i) * scale + screenSize / 2), (int) ((currentX+INTERPOLATION_STEP) * scale),
					(int) (points.get(i + 1) * scale + screenSize / 2));
			currentX+=INTERPOLATION_STEP;
		}
		g.drawOval((int) (currentX * scale), (int) (points.get(points.size() - 1) * scale + screenSize / 2), circleRadius,
				circleRadius);
	}

	/**
	 * Show form with visualization classes.
	 *
	 * @param apprMethod
	 *            Method for approximation function
	 * @param header
	 *            Form header
	 */
	public static void buildGraph(final BaseApproximation apprMethod, final String header) {
		approximationMethod = apprMethod;
		scale = (int) ((screenSize-100)/apprMethod.getMax());
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
	 *
	 * @param scrSize
	 *            screen size
	 */
	public static void setScreenSize(final int scrSize) {
		screenSize = scrSize;
	}
}
