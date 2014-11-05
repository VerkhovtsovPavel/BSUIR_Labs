package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import recipes.Recipe;
import driver.DataBaseDriver;


public class MyRecipe extends JFrame {

	private static final long serialVersionUID = 2377892044773977952L;
	private static DataBaseDriver dbDriver;
	private static int currentRecipe = 0;
	
	private static ArrayList<Recipe> allRecipe;
	
	private JPanel mainPanel;
	private static JTextField ingredientsField;
	private static JTextField nameField;
	private static JTextField timeRequiredField;
	private static JTextArea recipeArea;
	private static JScrollPane textAreaScroll;
	private static JTextField textField;

	public MyRecipe() {
		configureDefaultLayot();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		initialaze();
	}
	
	public static void create(DataBaseDriver dbUtils){
		dbDriver = dbUtils;
		initialaze();
		showAll();
	}
	
	private static void initialaze(){
		MyRecipe frame = new MyRecipe();
		frame.setSize(480, 450);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("\u041C\u043E\u0438 \u0440\u0435\u0446\u0435\u043F\u0442\u044B");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel label_4 = new JLabel("Рецепт №");
		label_4.setBounds(149, 23, 62, 14);
		mainPanel.add(label_4);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(221, 20, 31, 20);
		mainPanel.add(textField);
		textField.setColumns(10);

		ingredientsField = new JTextField();
		ingredientsField.setBounds(25, 214, 421, 20);
		mainPanel.add(ingredientsField);
		ingredientsField.setColumns(10);

		JLabel label = new JLabel("\u0421\u043F\u0438\u0441\u043E\u043A \u0438\u043D\u0433\u0440\u0435\u0434\u0438\u0435\u043D\u0442\u043E\u0432");
		label.setBounds(25, 189, 187, 14);
		mainPanel.add(label);

		JLabel label_1 = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435");
		label_1.setBounds(25, 51, 148, 14);
		mainPanel.add(label_1);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(25, 76, 421, 20);
		mainPanel.add(nameField);

		JLabel label_2 = new JLabel("\u041D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E\u0435 \u0432\u0440\u0435\u043C\u044F");
		label_2.setBounds(25, 123, 148, 14);
		mainPanel.add(label_2);

		timeRequiredField = new JTextField();
		timeRequiredField.setColumns(10);
		timeRequiredField.setBounds(25, 148, 421, 20);
		mainPanel.add(timeRequiredField);

		JLabel label_3 = new JLabel("\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435");
		label_3.setBounds(25, 256, 148, 14);
		mainPanel.add(label_3);

		recipeArea = new JTextArea();
		recipeArea.setBounds(27, 241, 421, 100);
		recipeArea.setLineWrap(true);
		mainPanel.add(recipeArea);

		textAreaScroll = new JScrollPane(recipeArea);
		textAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textAreaScroll.setBounds(25, 281, 421, 100);
		mainPanel.add(textAreaScroll);
	}
	
	
	private static void showAll(){
		allRecipe = dbDriver.convertCursorToArrayList(dbDriver.getAll());
		showCurrentRecipe();
	}
	
	private static void showCurrentRecipe(){
		textField.enable(true);
		textField.setText(String.valueOf(currentRecipe));
		textField.enable(false);
	
		nameField.setText(allRecipe.get(currentRecipe).getName());
		timeRequiredField.setText(String.valueOf(allRecipe.get(currentRecipe).getTimeRequired()));
		ingredientsField.setText(String.valueOf(allRecipe.get(currentRecipe).getIngredients()));
		recipeArea.setText(String.valueOf(allRecipe.get(currentRecipe).getRecipe()));
	}
}
