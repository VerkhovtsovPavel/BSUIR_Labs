package ui.forms;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Utils;
import algorithms.Sorter;
import driver.DataBaseDriver;

public class MainForm extends JFrame {
	private static final long serialVersionUID = -7109709456008554142L;

	private JPanel mainPanel;
	private JTextField textField;
	private static JMenuBar menuBar;
	private static DataBaseDriver dbDriver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		initialaze();
	}

	private static void initialaze() {
		MainForm frame = new MainForm();
		frame.setSize(465, 150);
		createMenu(frame);

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

	private static void createMenu(JFrame frame) {
		menuBar = new JMenuBar();
		Font font = new Font("Verdana", Font.PLAIN, 11);
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(font);

		JMenuItem addRecipe = new JMenuItem("Add recipe");
		addRecipe.setFont(font);
		fileMenu.add(addRecipe);
		addRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRecipeForm.create();
			}
		});

		JMenuItem myRecipe = new JMenuItem("Recipe-book");
		myRecipe.setFont(font);
		myRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowRecipe.create("Recipe-book", dbDriver.getAll());
			}
		});
		fileMenu.add(myRecipe);

		JMenu otherSearches = new JMenu("More searches");
		otherSearches.setFont(font);
		fileMenu.add(otherSearches);

		ButtonGroup directionGroup = new ButtonGroup();

		JMenuItem timeRequired = new JMenuItem("Time required");
		timeRequired.setFont(font);
		timeRequired.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModalForm.create("Time required", true);
			}
		});
		otherSearches.add(timeRequired);
		directionGroup.add(timeRequired);

		JMenuItem recipeName = new JMenuItem("Recipe name");
		recipeName.setFont(font);
		recipeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModalForm.create("Recipe name", false);
			}
		});
		otherSearches.add(recipeName);
		directionGroup.add(recipeName);

		fileMenu.addSeparator();

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setFont(font);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);

		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		configureDefaultLayot();
	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("ISeeChease");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JButton btnNewButton = new JButton("Find recipes");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Result.create(new Sorter(Utils.makeList(textField.getText())));
			}
		});
		btnNewButton.setBounds(120, 67, 216, 23);
		mainPanel.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(28, 36, 421, 20);
		mainPanel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("Ingredients list");
		label.setBounds(28, 11, 135, 14);
		mainPanel.add(label);
	}
}
