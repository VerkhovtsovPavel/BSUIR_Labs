package graphics;

import java.awt.Color;
import java.awt.Graphics;

import objects.Object;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Pavel_Verkhovtsov
 *
 */
public class GraphicsClass extends JPanel
{
	private static final long serialVersionUID = 1L; //service variable
	private final int circleRadius = 5;
	public static Object[] objectArray;
	public static int screenSize; //TODO Add setters.

	/**
	 * 
	 */
	public void paint(Graphics g)
	{
		for (int i = 0; i<objectArray.length; i++){
			switch (objectArray[i].getAreaNumber()){
				case 0: g.setColor(Color.RED); break;
				case 1: g.setColor(Color.BLUE); break;
				case 2: g.setColor(Color.GREEN); break;
				default: g.setColor(Color.ORANGE);
			}
			g.fillOval(objectArray[i].getX(), objectArray[i].getY(), circleRadius, circleRadius);

		}

	/*	Color oldColor = g.getColor();
		Color newColor = new Color(0, 0, 255);
		g.setColor(newColor);
		g.drawLine(20, 30, 360, 30);
		g.setColor(oldColor);
		g.drawRect(20, 40, 340, 20);
		newColor = new Color(0, 215, 255);
		g.setColor(newColor);
		g.fillRect(21, 41, 339, 19);
		g.setColor(oldColor);
		g.drawRoundRect(20, 70, 340, 30, 20, 15);
		g.drawOval(20, 110, 150, 60);
		g.drawOval(200, 110, 60, 60);
		g.drawArc(280, 110, 80, 60, 0, 180);
		int[] arrayX = {20, 100, 100, 250, 250, 20, 20, 50};
		int[] arrayY = {180, 180, 200, 200, 220, 200, 200, 190};
		Polygon poly = new Polygon(arrayX, arrayY, 8);
		g.drawPolygon(poly);
		Point aPoint = new Point(50, 190);
		if(poly.contains(aPoint))
		{
			g.drawString("Yes", 50, 190);
		}
		newColor = new Color(0, 0, 255);
		g.setColor(newColor);
		Font font = new Font("Tahoma", Font.BOLD|Font.ITALIC, 40);
		Font oldFont = g.getFont();
		g.setFont(font);
		g.drawString("SBP", 270, 220);
		g.setFont(oldFont);
		g.setColor(oldColor);
		// Draw axes;
		g.drawLine(20, 220, 20, 350);
		g.drawLine(20, 350, 360, 350);
		g.drawString("Y", 25, 230);
		g.drawString("X", 350, 346);
		// Draw a curve;
		int[] xArray = {20, 40, 60, 80, 100, 120, 130, 140, 280, 332};
		int[] yArray = {350, 345, 340, 310, 290, 280, 275, 273, 271, 269};
		int nPoint = 10;
		g.setColor(newColor);
		g.drawPolyline(xArray, yArray, nPoint);
		g.setColor(oldColor);
		g.drawString("y = f(x)", 180, 267);*/
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
