package by.bsuir.verkpavel.courseproject.ui.add;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.entity.Driver;
import by.bsuir.verkpavel.courseproject.dao.entity.DriverLicenceCategory;
import by.bsuir.verkpavel.courseproject.dao.entity.DriverLicense;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.resources.Messages;
import by.bsuir.verkpavel.courseproject.resources.ProjectProperties;

public class AddDriverView extends JFrame {
	private static final long serialVersionUID = 2883993883146596569L;
	private static Logger log = Logger.getLogger(AddDriverView.class);

	private JPanel mainPanel;
	private JTextField driverLicenceTextField;
	private JComboBox<String> employeeComboBox;
	private JComboBox<String> driverLicenceCategoryComboBox;

	private List<DriverLicenceCategory> driverLicenceCategorys;
	private List<Employee> employees;
	private JLabel label;

	public AddDriverView() {
		configureDefaultLayot();
	}

	public void showView() {
		this.setSize(730, 340);
		this.setTitle("Второй этап добавления водителя");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
		this.setVisible(true);
	}

	private void createLabels() {
		JLabel addDateLabel = new JLabel();
		addDateLabel.setText("Категория прав");
		addDateLabel.setBounds(21, 83, 121, 14);
		mainPanel.add(addDateLabel);

		JLabel userNameLabel = new JLabel("Номер удостоверения");
		userNameLabel.setBounds(23, 25, 213, 14);
		mainPanel.add(userNameLabel);
	}

	private void configureDefaultLayot() {
		initialazeLayout();
		createElements();
		fillComboBoxes();
	}

	private void initialazeLayout() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
	}

	private void createElements() {
		createLabels();
		createActionElements();
	}

	private Driver getDriver() {
		if (employeeComboBox.getSelectedIndex() != -1) {
			DriverLicenceCategory licenceCategory = driverLicenceCategorys
					.get(driverLicenceCategoryComboBox.getSelectedIndex());
			String licenceNumber = driverLicenceTextField.getText();

			DriverLicense driverLicense = new DriverLicense();
			driverLicense.setDriverlicencecategory(licenceCategory);
			driverLicense.setNumber(licenceNumber);

			DeliveryServiceDao.getInstance().addRecord(driverLicense);

			Driver driver = new Driver();
			driver.setDriverlicense(driverLicense);
			Employee employee = employees.get(employeeComboBox
					.getSelectedIndex());

			driver.setEmployee(employee);
			driver.setOffice(employee.getOffice());

			return driver;
		} else {
			JOptionPane.showMessageDialog(null, "Не заполнен работник",
					"Error", JOptionPane.PLAIN_MESSAGE);
			return null;
		}
	}

	private void createActionElements() {
		driverLicenceTextField = new JFormattedTextField(
				ProjectProperties.getDriverLicenceMask());
		driverLicenceTextField.setBounds(238, 14, 478, 38);
		driverLicenceTextField.setColumns(10);
		mainPanel.add(driverLicenceTextField);

		driverLicenceCategoryComboBox = new JComboBox<String>();
		driverLicenceCategoryComboBox.setBounds(23, 102, 126, 26);
		mainPanel.add(driverLicenceCategoryComboBox);

		employeeComboBox = new JComboBox<String>();
		employeeComboBox.setBounds(23, 173, 565, 26);
		mainPanel.add(employeeComboBox);

		JButton addButton = new JButton("Добавить");
		addButton.setBounds(165, 235, 374, 41);
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Driver driver = getDriver();
				if (driverLicenceTextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Заполнены не все обязательные поля", "Error",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					boolean isSuccessfully = DeliveryServiceDao.getInstance()
							.addRecord(driver);
					if (isSuccessfully) {
						JOptionPane.showMessageDialog(null,
								Messages.DRIVER_SUCCESSFULLY_ADDED.get(),
								"Message", JOptionPane.PLAIN_MESSAGE);
						log.info(Messages.DRIVER_SUCCESSFULLY_ADDED.get());
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								Messages.ERROR_WHILE_ADD_RECORD.get(), "Error",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		mainPanel.add(addButton);

		label = new JLabel();
		label.setText("Работник");
		label.setBounds(20, 152, 121, 14);
		mainPanel.add(label);
	}

	private void fillComboBox(JComboBox<String> target,
			List<? extends Describable> source) {
		for (Describable item : source) {
			target.addItem(item.getDescription());
		}
	}

	private void fillComboBoxes() {
		driverLicenceCategorys = DeliveryServiceDao.getInstance().getAllRecord(
				DriverLicenceCategory.class);
		employees = DeliveryServiceDao.getInstance().getDrivers();

		List<Driver> drivers = DeliveryServiceDao.getInstance().getAllRecord(
				Driver.class);

		List<Employee> deleted = new ArrayList<>();

		for (Driver driver : drivers) {
			for (Employee employee : employees) {
				if (driver.getEmployee().getIdEmployee() == employee
						.getIdEmployee()) {
					deleted.add(employee);
				}
			}
		}

		employees.removeAll(deleted);

		fillComboBox(employeeComboBox, employees);
		fillComboBox(driverLicenceCategoryComboBox, driverLicenceCategorys);
	}
}
