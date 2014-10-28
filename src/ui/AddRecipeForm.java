package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

//TODO Refactor element name
//TODO Fix closing application where close this window 
public class AddRecipeForm extends JFrame {
	private static final long serialVersionUID = 2883993883146596569L;
	private JPanel mainPanel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	private static JScrollPane textAreaScroll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		AddRecipeForm frame = new AddRecipeForm();
		frame.setSize(490, 420);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public AddRecipeForm() {
		configureDefaultLayot();
	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("\u0414\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u0440\u0435\u0446\u0435\u043F\u0442\u0430");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JButton btnNewButton = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0440\u0435\u0446\u0435\u043F\u0442");
		btnNewButton.setBounds(109, 351, 216, 23);
		mainPanel.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(27, 174, 421, 20);
		mainPanel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("\u0421\u043F\u0438\u0441\u043E\u043A \u0438\u043D\u0433\u0440\u0435\u0434\u0438\u0435\u043D\u0442\u043E\u0432");
		label.setBounds(27, 149, 148, 14);
		mainPanel.add(label);

		JLabel label_1 = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435");
		label_1.setBounds(27, 11, 148, 14);
		mainPanel.add(label_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(27, 36, 421, 20);
		mainPanel.add(textField_1);

		JLabel label_2 = new JLabel("\u041D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E\u0435 \u0432\u0440\u0435\u043C\u044F");
		label_2.setBounds(27, 83, 148, 14);
		mainPanel.add(label_2);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(27, 108, 421, 20);
		mainPanel.add(textField_2);

		JLabel label_3 = new JLabel("\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435");
		label_3.setBounds(27, 216, 148, 14);
		mainPanel.add(label_3);

		textArea = new JTextArea();
		textArea.setBounds(27, 241, 421, 100);
		textArea.setLineWrap(true);
		mainPanel.add(textArea);

		textAreaScroll = new JScrollPane(textArea);
		textAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textAreaScroll.setBounds(27, 241, 421, 100);
		mainPanel.add(textAreaScroll);
	}
}
