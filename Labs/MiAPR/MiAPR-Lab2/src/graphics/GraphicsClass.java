package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
	private static Object[] objectArray;
	private static int screenSize;
	private static int areaCount;
	private static ArrayList<Object> classArray;

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
				case 3: g.setColor(Color.GRAY); break;
				default:{
					g.setColor(new Color(0, 255/areaCount*objectArray[i].getAreaNumber(), 255/areaCount*objectArray[i].getAreaNumber()));
				}
			}
			g.fillOval(objectArray[i].getX(), objectArray[i].getY(), circleRadius, circleRadius);
		}

		for(int i=0; i<classArray.size(); i++){
			g.setColor(Color.BLACK);
			g.fillOval(classArray.get(i).getX(), classArray.get(i).getY(), circleRadius*2, circleRadius*2);
		}
	}

	/**
	 * Show form with visualization classes.
	 */
	public static void visualizeClasses(){
		JPanel panel = new GraphicsClass();
        panel.setOpaque(true);

        JFrame mainFrame = new JFrame("Visualization division classes ");
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

	/**
	 * Setter to object array.
	 * @param objects object array
	 */
	public static void setObjectArray(final Object[] objects, ArrayList<Object> classes){
		objectArray = objects;
		classArray = classes;
	}

	public static void setAreaCount(final int classCount){
		areaCount=classCount;
	}

}
