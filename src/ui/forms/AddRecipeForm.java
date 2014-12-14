package ui.forms;

import static utils.Utils.makeList;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import recipes.Recipe;
import driver.DataBaseDriver;

public class AddRecipeForm extends JFrame {
	private static final long serialVersionUID = 2883993883146596569L;
	private JPanel mainPanel;
	private JTextField ingredientsField;
	private JTextField nameField;
	private JTextField timeRequiredField;
	private JTextArea recipeArea;
	private static JScrollPane textAreaScroll;
	private static DataBaseDriver dbDriver;

	private static void initialaze() {
		AddRecipeForm frame = new AddRecipeForm();
		frame.setSize(490, 420);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}

	public static void create() {
		dbDriver = DataBaseDriver.getInstanse();
		initialaze();
	}

	/**
	 * Create the frame.
	 */
	public AddRecipeForm() {
		configureDefaultLayot();
	}

	private int verifyFields(String name, String timeRequired, String ingredientsList, String recipe) {
		int time = 0;
		if (name.isEmpty() || timeRequired.isEmpty() || ingredientsList.isEmpty() || recipe.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Not all fields filled", "Error", JOptionPane.PLAIN_MESSAGE);
		} else {

			try {
				time = Integer.valueOf(timeRequired);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Value in field \"Time required\" is not number", "Error", JOptionPane.PLAIN_MESSAGE);
			}
		}
		return time;
	}

	private void cleanFields() {
		nameField.setText("");
		timeRequiredField.setText("");
		ingredientsField.setText("");
		recipeArea.setText("");
	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("Add recipe");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JButton btnNewButton = new JButton("Add recipe");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = nameField.getText();
				String timeRequired = timeRequiredField.getText();
				String ingredientsList = ingredientsField.getText();
				String recipe = recipeArea.getText();

				int time = verifyFields(name, timeRequired, ingredientsList, recipe);

				if (time != 0) {
					dbDriver.insert(new Recipe(name, time, makeList(ingredientsList), recipe));
					cleanFields();
				}
			}

		});
		btnNewButton.setBounds(109, 351, 216, 23);
		mainPanel.add(btnNewButton);
		ingredientsField = new JTextField();
		ingredientsField.setBounds(27, 174, 421, 20);
		mainPanel.add(ingredientsField);
		ingredientsField.setColumns(10);

		JLabel lblIngredientList = new JLabel("ingredient List");
		lblIngredientList.setBounds(27, 149, 187, 14);
		mainPanel.add(lblIngredientList);

		JLabel titleLbl = new JLabel("Title");
		titleLbl.setBounds(27, 11, 148, 14);
		mainPanel.add(titleLbl);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(27, 36, 421, 20);
		mainPanel.add(nameField);

		JLabel lblTimeReq = new JLabel("Time required");
		lblTimeReq.setBounds(27, 83, 148, 14);
		mainPanel.add(lblTimeReq);

		timeRequiredField = new JTextField();
		timeRequiredField.setColumns(10);
		timeRequiredField.setBounds(27, 108, 421, 20);
		mainPanel.add(timeRequiredField);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(27, 216, 148, 14);
		mainPanel.add(lblDescription);

		recipeArea = new JTextArea();
		recipeArea.setBounds(27, 241, 421, 100);
		recipeArea.setLineWrap(true);
		mainPanel.add(recipeArea);

		textAreaScroll = new JScrollPane(recipeArea);
		textAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textAreaScroll.setBounds(27, 241, 421, 100);
		mainPanel.add(textAreaScroll);
	}
}
