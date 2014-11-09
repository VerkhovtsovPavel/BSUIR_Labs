package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import driver.DataBaseDriver;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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
	
	private static void initialaze(){
		MainForm frame = new MainForm();
		frame.setSize(465, 150);
		createMenu(frame);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}
	
	public static void create(DataBaseDriver dbUtils){
		dbDriver=dbUtils;
		initialaze();
	}
	

	private static void createMenu(JFrame frame) {
		menuBar = new JMenuBar();
		Font font = new Font("Verdana", Font.PLAIN, 11);
		JMenu fileMenu = new JMenu("Файл");
		fileMenu.setFont(font);

		JMenuItem addRecipe = new JMenuItem("Добавить рецепт");
		addRecipe.setFont(font);
		fileMenu.add(addRecipe);
		addRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRecipeForm.create(dbDriver);
			}
		});
		
		JMenuItem myRecipe = new JMenuItem("Мои рецепты");
		myRecipe.setFont(font);
		myRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyRecipe.create(dbDriver);
			}
		});
		fileMenu.add(myRecipe);

		fileMenu.addSeparator();

		JMenuItem exitItem = new JMenuItem("Выход");
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
		setTitle("ЯВижуСыр");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JButton btnNewButton = new JButton("\u041F\u043E\u0434\u043E\u0431\u0440\u0430\u0442\u044C \u0440\u0435\u0446\u0435\u043F\u0442");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Result.create(dbDriver);
			}
		});
		btnNewButton.setBounds(120, 67, 216, 23);
		mainPanel.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(28, 36, 421, 20);
		mainPanel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("\u0421\u043F\u0438\u0441\u043E\u043A \u043F\u0440\u043E\u0434\u0443\u043A\u0442\u043E\u0432");
		label.setBounds(28, 11, 135, 14);
		mainPanel.add(label);
	}
}
