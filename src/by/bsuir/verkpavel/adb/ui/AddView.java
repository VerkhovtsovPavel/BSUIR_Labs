package by.bsuir.verkpavel.adb.ui;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.JFormattedTextField;

import javax.swing.JComboBox;

public class AddView extends JFrame {
	private static final long serialVersionUID = 2883993883146596569L;
	private JPanel mainPanel;
	private JTextField nameField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField realAddressTextField;
	private JTextField officialAddressTextField;

	private static void initialaze() {
		AddView frame = new AddView();
		frame.setSize(490, 420);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}

	public static void create() {
		initialaze();
	}

	/**
	 * Create the frame.
	 */
	public AddView() {
		configureDefaultLayot();
	}

	private int verifyFields(String name, String timeRequired, String ingredientsList, String recipe) {
		int time = 0;
		if (name.isEmpty() || timeRequired.isEmpty() || ingredientsList.isEmpty() || recipe.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Не все обязательные поля заполнены!", "Error", JOptionPane.PLAIN_MESSAGE);
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
	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("\u0414\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043A\u043B\u0438\u0435\u043D\u0442\u0430");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JButton saveBtn = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = nameField.getText();

				int time = verifyFields(name, null, null, null);

				if (time != 0) {
					cleanFields();
				}
			}

		});
		saveBtn.setBounds(216, 367, 216, 23);
		mainPanel.add(saveBtn);

		JLabel firstNameLbl = new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F");
		firstNameLbl.setBounds(10, 11, 66, 14);
		mainPanel.add(firstNameLbl);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(79, 8, 210, 23);
		mainPanel.add(nameField);
		
		JLabel FirstNamelbl = new JLabel("\u0418\u043C\u044F");
		FirstNamelbl.setBounds(299, 14, 49, 14);
		mainPanel.add(FirstNamelbl);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(325, 8, 92, 23);
		mainPanel.add(textField);
		
		JLabel MiddleNamelbl = new JLabel("\u041E\u0442\u0447\u0435\u0441\u0442\u0432\u043E");
		MiddleNamelbl.setBounds(427, 11, 66, 14);
		mainPanel.add(MiddleNamelbl);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(498, 8, 172, 23);
		mainPanel.add(textField_1);
		
		JRadioButton man_radioButton = new JRadioButton("\u041C\u0443\u0436\u0441\u043A\u043E\u0439");
		man_radioButton.setBounds(27, 61, 109, 23);
		mainPanel.add(man_radioButton);
		
		JRadioButton woman_radioButton = new JRadioButton("\u0416\u0435\u043D\u0441\u043A\u0438\u0439");
		woman_radioButton.setBounds(27, 86, 109, 23);
		mainPanel.add(woman_radioButton);
		
		JLabel sex_label = new JLabel("\u041F\u043E\u043B");
		sex_label.setBounds(27, 43, 46, 14);
		mainPanel.add(sex_label);
		
		JLabel birthday_label = new JLabel("\u0414\u0430\u0442\u0430 \u0440\u043E\u0436\u0434\u0435\u043D\u0438\u044F");
		birthday_label.setBounds(131, 40, 102, 14);
		mainPanel.add(birthday_label);
		
		JFormattedTextField dateField = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		dateField.setValue(new java.util.Date()); // today
		dateField.setBounds(131, 58, 92, 28);
		mainPanel.add(dateField);
		
		JComboBox<String> city_comboBox = new JComboBox<String>();
		city_comboBox.setBounds(252, 60, 126, 26);
		mainPanel.add(city_comboBox);
		
		JLabel city_label = new JLabel("\u0413\u043E\u0440\u043E\u0434 \u043F\u0440\u043E\u0436\u0438\u0432\u0430\u043D\u0438\u044F");
		city_label.setBounds(252, 42, 126, 16);
		mainPanel.add(city_label);
		
		JLabel realAddress_lbl = new JLabel("\u0410\u0434\u0440\u0435\u0441 \u043F\u0440\u043E\u0436\u0438\u0432\u0430\u043D\u0438\u044F");
		realAddress_lbl.setBounds(399, 42, 148, 16);
		mainPanel.add(realAddress_lbl);
		
		realAddressTextField = new JTextField();
		realAddressTextField.setBounds(398, 58, 272, 28);
		mainPanel.add(realAddressTextField);
		realAddressTextField.setColumns(10);
		
		officialAddressTextField = new JTextField();
		officialAddressTextField.setColumns(10);
		officialAddressTextField.setBounds(398, 114, 272, 28);
		mainPanel.add(officialAddressTextField);
		
		JLabel officialAddressLabel = new JLabel("\u0410\u0434\u0440\u0435\u0441 \u043F\u0440\u043E\u043F\u0438\u0441\u043A\u0438");
		officialAddressLabel.setBounds(399, 98, 148, 16);
		mainPanel.add(officialAddressLabel);
	}
}
