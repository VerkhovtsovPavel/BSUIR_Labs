package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class MainForm extends JFrame {
	private static final long serialVersionUID = -7109709456008554142L;

	private JPanel mainPanel;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setSize(465, 140);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
					frame.setLocation(x, y);
					frame.setVisible(true);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		configureDefaultLayot();
	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("Course project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("\u041F\u043E\u0434\u043E\u0431\u0440\u0430\u0442\u044C \u0440\u0435\u0446\u0435\u043F\u0442");
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
