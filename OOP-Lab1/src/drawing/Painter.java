package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import figure.BaseFigure;

/**
 * Class containing methods to visualize classes.
 * @author Pavel_Verkhovtsov
 */
public class Painter extends JPanel{
	private static final long serialVersionUID = 1L; //service variable
	private static final int SCREEN_SIZE = 1000;
	private static List <BaseFigure> figureList = new ArrayList();

	/**
	 * Drawing form.
	 * @param g graphics object
	 */
	public void paint(final Graphics g){
		for (int i = 0; i<figureList.size(); i++){
			figureList.get(i).draw(g);
		}
	}

	/**
	 * Show form with visualization classes.
	 */
	public static void drawObjects(){
		JPanel panel = new Painter();
        panel.setOpaque(true);

        JFrame mainFrame = new JFrame("Visualization division classes ");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(panel);
        mainFrame.setSize(SCREEN_SIZE, SCREEN_SIZE);
        mainFrame.setVisible(true);
        mainFrame.setBackground(Color.white);
	}
	
	/**
	 * Add object in collection.
	 * @param object added object 
	 */
	public static void addObjectInList(BaseFigure object){
		figureList.add(object);
	}
}
