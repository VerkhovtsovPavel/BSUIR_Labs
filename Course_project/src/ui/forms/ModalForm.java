package ui.forms;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import driver.DataBaseDriver;

public class ModalForm extends JFrame {
	private static final long serialVersionUID = -7109709456008554142L;

	private JPanel mainPanel;
	private JTextField textField;

	private static DataBaseDriver dbDriver;

	private static String title;

	private static boolean mode;
	private static String labelValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		initialaze();
	}

	private static void initialaze() {
		ModalForm frame = new ModalForm();
		frame.setSize(465, 150);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}

	public static void create(String title, boolean mode) {
		dbDriver = DataBaseDriver.getInstanse();
		ModalForm.title = title;
		ModalForm.mode = mode;
		ModalForm.labelValue = "Enter " + title.toLowerCase();
		initialaze();
	}

	/**
	 * Create the frame.
	 */
	public ModalForm() {
		configureDefaultLayot();
	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JButton btnNewButton = new JButton("Find");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mode) {
					ShowRecipe.create("Search by time required", dbDriver.findByTimeRequired(Integer.valueOf(textField.getText())));
				} else {
					ShowRecipe.create("Search by title", dbDriver.findByRecipeName(textField.getText()));
				}

			}
		});
		btnNewButton.setBounds(120, 67, 216, 23);
		mainPanel.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(28, 36, 421, 20);
		mainPanel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel(labelValue);
		label.setBounds(28, 11, 135, 14);
		mainPanel.add(label);
	}
}
