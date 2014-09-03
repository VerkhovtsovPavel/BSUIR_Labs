package graphics;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.Object;

/**
 * Class containing methods to visualize classes.
 * @author Pavel_Verkhovtsov
 */
public class GraphicsClass extends JPanel{
	private static final long serialVersionUID = 1L; //service variable
	private final int circleRadius = 5;
	public static Object[] objectArray;
	public static int screenSize;
	//TODO Add setters.

	/**
	 * Drawing form.
	 * @param g graphics object
	 */
	public void paint(final Graphics g){
		for (int i = 0; i<objectArray.length; i++){
			switch (objectArray[i].getAreaNumber()){
				case 0: g.setColor(Color.RED); break;
				case 1: g.setColor(Color.BLUE); break;
				case 2: g.setColor(Color.GREEN); break;
				default: g.setColor(Color.ORANGE); //TODO Automation color selected HSB + RGBtoHSB (255/areaCount*i)
			}
			g.fillOval(objectArray[i].getX(), objectArray[i].getY(), circleRadius, circleRadius);

		}
	}

	/**
	 * Show form with visualization classes.
	 */
	public static void visualizeClasses(){
		JPanel panel = new GraphicsClass();
        panel.setOpaque(true);

        JFrame mainFrame = new JFrame("Визуализация разбиения на классы");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(panel);
        mainFrame.setSize(screenSize, screenSize);
        mainFrame.setVisible(true);
        mainFrame.setBackground(Color.white);
	}

}
