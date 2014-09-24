package graphics;

import java.awt.Color;
import java.awt.Graphics;
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
	private static final int SCALE = 30;
	private static final float INTERPOLATION_STEP = (float) 0.1;

	private static BaseApproximation approximationMethod;

	/**
	 * Drawing form.
	 *
	 * @param g
	 *            graphics object
	 */
	public void paint(final Graphics g) {
		double[] points = new double[(int) (approximationMethod.getArgumentsRange() / INTERPOLATION_STEP)];
		for (double i = approximationMethod.getMin(); i < approximationMethod.getArgumentsRange() - INTERPOLATION_STEP; i += INTERPOLATION_STEP) {
			points[(int) (i / INTERPOLATION_STEP)] = (float) approximationMethod.approximazeFunction(i);
		}

		for (int i = 0; i < points.length - 1; i++) {
			g.drawOval((int) (i * INTERPOLATION_STEP * SCALE), (int) points[i] * SCALE + screenSize / 2, circleRadius, circleRadius);
			g.drawLine((int) (i * INTERPOLATION_STEP * SCALE), (int) points[i] * SCALE + screenSize / 2, (int) ((i + 1) * INTERPOLATION_STEP * SCALE),
					(int) points[i + 1] * SCALE + screenSize / 2);
		}
		g.drawOval((int) ((points.length - 1) * INTERPOLATION_STEP * SCALE), (int) points[points.length - 1] * SCALE + screenSize / 2, circleRadius,
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
