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
		frame.setSize(480, 500);
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

		JLabel label_4 = new JLabel("Рецепт №");
		label_4.setBounds(126, 23, 85, 14);
		mainPanel.add(label_4);

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(221, 20, 31, 20);
		mainPanel.add(textField);
		textField.setColumns(10);

		ingredientsField = new JTextField();
		ingredientsField.setBackground(Color.WHITE);
		ingredientsField.setEditable(false);
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
		nameField.setBackground(Color.WHITE);
		nameField.setEditable(false);
		nameField.setColumns(10);
		nameField.setBounds(25, 76, 421, 20);
		mainPanel.add(nameField);

		JLabel label_2 = new JLabel("\u041D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E\u0435 \u0432\u0440\u0435\u043C\u044F");
		label_2.setBounds(25, 123, 148, 14);
		mainPanel.add(label_2);

		timeRequiredField = new JTextField();
		timeRequiredField.setBackground(Color.WHITE);
		timeRequiredField.setEditable(false);
		timeRequiredField.setColumns(10);
		timeRequiredField.setBounds(25, 148, 421, 20);
		mainPanel.add(timeRequiredField);

		JLabel label_3 = new JLabel("\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435");
		label_3.setBounds(25, 256, 148, 14);
		mainPanel.add(label_3);

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
