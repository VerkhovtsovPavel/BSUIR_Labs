package ui.forms;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import recipes.Recipe;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowRecipe extends JFrame {

	private static final long serialVersionUID = 2377892044773977952L;
	private static int currentRecipe = 0;

	private static ArrayList<Recipe> allRecipe;

	private JPanel mainPanel;
	private static JTextField ingredientsField;
	private static JTextField nameField;
	private static JTextField timeRequiredField;
	private static JTextArea recipeArea;
	private static JScrollPane textAreaScroll;
	private static JTextField textField;
	private static JButton nextRecipe;
	private static JButton prevRecipe;
	private static JButton buildGraphBtn;
	private static JProgressBar progressBar;
	private static ShowRecipe frame;

	public ShowRecipe(String title) {
		configureDefaultLayot(title);
	}

	public static void create(String title, ArrayList<Recipe> recipes) {
		allRecipe = recipes;
		initialaze(title);
		showAll();
	}

	private static void initialaze(String title) {
		frame = new ShowRecipe(title);
		frame.setSize(480, 525);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}

	private void configureDefaultLayot(String title) {
		setResizable(false);
		setTitle(title);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JLabel recipeNumber = new JLabel("Recipe №");
		recipeNumber.setBounds(126, 23, 85, 14);
		mainPanel.add(recipeNumber);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(Color.WHITE);
		textField.setBounds(221, 20, 31, 20);
		mainPanel.add(textField);
		textField.setColumns(10);

		ingredientsField = new JTextField();
		ingredientsField.setBackground(Color.WHITE);
		ingredientsField.setEditable(false);
		ingredientsField.setBounds(25, 214, 421, 20);
		mainPanel.add(ingredientsField);
		ingredientsField.setColumns(10);

		JLabel ingredientListLbl = new JLabel("Ingredient list");
		ingredientListLbl.setBounds(25, 189, 187, 14);
		mainPanel.add(ingredientListLbl);

		JLabel label_1 = new JLabel("Title");
		label_1.setBounds(25, 51, 148, 14);
		mainPanel.add(label_1);

		nameField = new JTextField();
		nameField.setBackground(Color.WHITE);
		nameField.setEditable(false);
		nameField.setColumns(10);
		nameField.setBounds(25, 76, 421, 20);
		mainPanel.add(nameField);

		JLabel timeRequiredLbl = new JLabel("Time required");
		timeRequiredLbl.setBounds(25, 123, 148, 14);
		mainPanel.add(timeRequiredLbl);

		timeRequiredField = new JTextField();
		timeRequiredField.setBackground(Color.WHITE);
		timeRequiredField.setEditable(false);
		timeRequiredField.setColumns(10);
		timeRequiredField.setBounds(25, 148, 421, 20);
		mainPanel.add(timeRequiredField);

		JLabel descriptionLbl = new JLabel("Description");
		descriptionLbl.setBounds(25, 256, 148, 14);
		mainPanel.add(descriptionLbl);

		recipeArea = new JTextArea();
		recipeArea.setEditable(false);
		recipeArea.setBounds(27, 241, 421, 100);
		recipeArea.setLineWrap(true);
		mainPanel.add(recipeArea);

		textAreaScroll = new JScrollPane(recipeArea);
		textAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textAreaScroll.setBounds(25, 281, 421, 100);
		mainPanel.add(textAreaScroll);

		prevRecipe = new JButton("Prev");
		prevRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentRecipe--;
				showCurrentRecipe();
			}
		});

		prevRecipe.setBounds(35, 393, 157, 25);
		mainPanel.add(prevRecipe);

		nextRecipe = new JButton("Next");
		nextRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentRecipe++;
				showCurrentRecipe();
			}
		});
		nextRecipe.setBounds(270, 393, 176, 25);
		mainPanel.add(nextRecipe);

		progressBar = new JProgressBar();
		progressBar.setBounds(25, 430, 421, 14);
		mainPanel.add(progressBar);
		
		buildGraphBtn = new JButton("Build graph");
		buildGraphBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Graph.create();
			}
		});
		buildGraphBtn.setBounds(182, 456, 117, 25);
		mainPanel.add(buildGraphBtn);
	}

	private static void showAll() {
		progressBar.setMaximum(allRecipe.size());
		if (allRecipe.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Recipe list is empty", "Warning", JOptionPane.CLOSED_OPTION);
			frame.dispose();
		} else {
			showCurrentRecipe();
		}
	}

	private static void showCurrentRecipe() {
		buttonEnables();
		progressBar.setValue(currentRecipe + 1);
		textField.setEnabled(true);
		textField.setText(String.valueOf(currentRecipe + 1));
		textField.setEnabled(false);

		nameField.setText(allRecipe.get(currentRecipe).getName());
		timeRequiredField.setText(String.valueOf(allRecipe.get(currentRecipe).getTimeRequired()));
		ingredientsField.setText(String.valueOf(allRecipe.get(currentRecipe).getIngredients()));
		recipeArea.setText(String.valueOf(allRecipe.get(currentRecipe).getRecipe()));
	}

	private static void buttonEnables() {
		if (currentRecipe == 0) {
			prevRecipe.setEnabled(false);
		} else {
			prevRecipe.setEnabled(true);
		}
		if (currentRecipe == allRecipe.size() - 1) {
			nextRecipe.setEnabled(false);
		} else {
			nextRecipe.setEnabled(true);
		}
	}
}
