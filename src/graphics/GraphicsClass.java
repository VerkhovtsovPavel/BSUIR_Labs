package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Probability;

/**
 * Class containing methods to visualize classes.
 * 
 * @author Pavel_Verkhovtsov
 */
public class GraphicsClass extends JPanel {
	private static final long serialVersionUID = 1L; // service variable
	private static final int screenSize = 600;
	private static final int X_SCALE = 10;
	private static final int Y_SCALE = 20000;
	private static Probability firstProbability;
	private static Probability secondProbability;
	private static int median;

	/**
	 * Drawing form.
	 * 
	 * @param g
	 *            graphics object
	 */
	public void paint(final Graphics g) {
		drawProbabilityDensity(g, firstProbability, Color.RED);
		drawProbabilityDensity(g, secondProbability, Color.BLUE);
		g.setColor(Color.BLACK);
		g.drawLine(median * X_SCALE, 0, median * X_SCALE, screenSize);
	}

	private void drawProbabilityDensity(Graphics g, Probability probability,
			Color color) {
		g.setColor(color);
		for (int i = 0; i < probability.randomVariable.size() - 1; i++) {
			g.drawLine(
					probability.randomVariable.get(i) * X_SCALE,
					(int) (screenSize / (2.5 - probability.probalityOfClass) + screenSize / 2)
							- (int) (probability
									.probabilityDensity(probability.randomVariable
											.get(i))
									* Y_SCALE * probability.probalityOfClass),
					probability.randomVariable.get(i + 1) * X_SCALE,
					(int) (screenSize / (2.5 - probability.probalityOfClass) + screenSize / 2)
							- (int) (probability
									.probabilityDensity(probability.randomVariable
											.get(i + 1))
									* Y_SCALE * probability.probalityOfClass));
		}
	}

	public static void buildGraph(final Probability firstProbability,
			final Probability secondProbability, final int median) {
		GraphicsClass.firstProbability = firstProbability;
		GraphicsClass.secondProbability = secondProbability;
		GraphicsClass.median = median;
		JPanel panel = new GraphicsClass();
		panel.setOpaque(true);

		JFrame mainFrame = new JFrame("MiAPR-Lab3");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setContentPane(panel);
		mainFrame.setSize((int) (screenSize * 2), screenSize);
		mainFrame.setVisible(true);
		mainFrame.setBackground(Color.white);
	}

}
