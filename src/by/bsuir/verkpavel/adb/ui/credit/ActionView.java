package by.bsuir.verkpavel.adb.ui.credit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import by.bsuir.verkpavel.adb.data.ClientProvider;
import by.bsuir.verkpavel.adb.data.CreditProvider;
import by.bsuir.verkpavel.adb.data.DepositProvider;
import by.bsuir.verkpavel.adb.data.entity.Credit;
import by.bsuir.verkpavel.adb.resources.RussianStrings;
import by.bsuir.verkpavel.adb.ui.ActionMode;
import by.bsuir.verkpavel.adb.ui.DateLabelFormatter;
import by.bsuir.verkpavel.adb.ui.LocalDateModel;

public abstract class ActionView extends JFrame {
	private static final long serialVersionUID = 2883993883146596569L;
	private static final LocalDate maxDate = LocalDate.of(2100, 1, 1);

	protected static Credit currentCredit;

	protected JPanel mainPanel;

	private JTextField creditPeriodTextField;

	protected JDatePickerImpl startDateField;
	private DateModel<LocalDate> startDateModel;
	protected JDatePickerImpl endDateField;
	private DateModel<LocalDate> endDateModel;

	private JFormattedTextField contractNumberField;
	private JFormattedTextField creditSumField;
	private JFormattedTextField persentTextField;

	private JComboBox<String> creditTypeComboBox;
	private JComboBox<String> currencyComboBox;
	private JComboBox<String> clientComboBox;

	private DateTimeFormatter dateMask = DateTimeFormatter
			.ofPattern("yyyy-MM-dd");

	private static void initialaze(ActionView actionView) {
		ActionView frame = null;
		frame = actionView;
		frame.setSize(750, 350);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				ShowCreditsView.create();
			}
		});
	}

	public static void create(ActionMode mode, Credit credit) {
		try {
			currentCredit = credit;
			ActionView actionView = null;
			switch (mode) {
			case ADD:
				actionView = new AddView();
				break;
			case SHOW:
				actionView = new ShowView();
				break;
			case EDIT:
				break;
			default:
				break;
			}
			initialaze(actionView);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected ActionView() throws ParseException {
		configureDefaultLayot();
	}

	private void createLabels() {
		JLabel startDate_label = new JLabel("Дата начала");
		startDate_label.setBounds(12, 193, 92, 14);
		mainPanel.add(startDate_label);

		JLabel depositType_label = new JLabel("Вид кредита");
		depositType_label.setBounds(23, 16, 141, 16);
		mainPanel.add(depositType_label);

		JLabel contractNumberlbl = new JLabel("Номер договора");
		contractNumberlbl.setBounds(183, 87, 138, 16);
		mainPanel.add(contractNumberlbl);

		JLabel depositPeriodLabel = new JLabel("Срок договора");
		depositPeriodLabel.setBounds(488, 192, 114, 16);
		mainPanel.add(depositPeriodLabel);

		JLabel currencyLabel = new JLabel("Вид валюты");
		currencyLabel.setBounds(23, 69, 126, 16);
		mainPanel.add(currencyLabel);

		JLabel depositSumLabel = new JLabel("Сумма кредита");
		depositSumLabel.setBounds(12, 247, 103, 16);
		mainPanel.add(depositSumLabel);

		JLabel endDatelabel = new JLabel("Дата окончания");
		endDatelabel.setBounds(237, 193, 126, 14);
		mainPanel.add(endDatelabel);

		JLabel persentlabel = new JLabel("Проценты");
		persentlabel.setBounds(268, 247, 72, 16);
		mainPanel.add(persentlabel);

		JLabel clientLabel = new JLabel("Клиент");
		clientLabel.setBounds(23, 124, 58, 14);
		mainPanel.add(clientLabel);
	}

	private void configureDefaultLayot() throws ParseException {
		initialazeLayout();
		createElements();
		fillComboBoxes();
		customActions();
	}

	protected void customActions() throws ParseException {

	}

	protected void fillFields(Credit credit) throws ParseException {
		creditTypeComboBox.setSelectedIndex(credit.type - 1);
		contractNumberField.setText(credit.contractNumber);
		currencyComboBox.setSelectedIndex(credit.currency - 1);

		startDateModel.setValue(LocalDate.parse(credit.startDate, dateMask));
		endDateModel.setValue(LocalDate.parse(credit.endDate, dateMask));
		creditPeriodTextField.setText(""
				+ ChronoUnit.DAYS.between(startDateModel.getValue(),
						endDateModel.getValue()));

		creditSumField.setValue((double) credit.sum);
		persentTextField.setValue((double) credit.persent);
	}

	private void initialazeLayout() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
	}

	private void createElements() throws ParseException {
		createLabels();
		createActionElements();
	}

	protected Credit getCredit() {
		LocalDate startDate = (LocalDate) startDateField.getModel().getValue();

		LocalDate endDate = null;

		if (endDateField.getComponent(1).isEnabled()) {
			endDate = (LocalDate) endDateField.getModel().getValue();
		} else {
			endDate = startDate.plusMonths(1);
		}
		int depositType = creditTypeComboBox.getSelectedIndex() + 1;
		String contractNumber = contractNumberField.getText();
		int currency = currencyComboBox.getSelectedIndex() + 1;
		int depositSum = ((Double) creditSumField.getValue()).intValue();

		String clientSrt = (String) clientComboBox.getSelectedItem();
		int client = Integer.parseInt(clientSrt.replaceAll("[^0-9]", ""));
		float persent = (float) persentTextField.getValue();

		if (checkRequiredFields(contractNumber)) {
			if (startDate.isBefore(LocalDate.now())
					|| endDate.isBefore(startDate)) {
				JOptionPane.showMessageDialog(null,
						RussianStrings.STARTDATEBEFORENOW_OR_ENDDATEBEFORESTART
								.get(), "Error", JOptionPane.PLAIN_MESSAGE);
				return null;
			}
			if (startDate.isAfter(maxDate) || endDate.isAfter(maxDate)) {
				JOptionPane.showMessageDialog(null,
						RussianStrings.DATE_AFTER_MAX_DATE.get(), "Error",
						JOptionPane.PLAIN_MESSAGE);
				return null;
			}
			return new Credit(contractNumber, depositType,
					startDate.format(dateMask), endDate.format(dateMask),
					currency, persent, depositSum, client);
		} else {
			JOptionPane.showMessageDialog(null,
					"Заполнены не все обязательные поля", "Error",
					JOptionPane.PLAIN_MESSAGE);
			return null;
		}
	}

	private boolean checkRequiredFields(String... fields) {
		for (String field : fields) {
			if (field.trim().isEmpty())
				return false;
		}
		return true;
	}

	private void createActionElements() throws ParseException {
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		startDateModel = new LocalDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(startDateModel, p);
		startDateField = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		startDateModel.setValue(LocalDate.now());
		startDateField.setBounds(114, 186, 114, 28);
		startDateModel.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				LocalDate endDate = endDateModel.getValue();
				LocalDate startDate = startDateModel.getValue();
				if (endDateField.getComponent(1).isEnabled()) {
					if (!endDate.isAfter(startDate)) {
						endDateModel.setValue(startDate.plusDays(1));
					}
					creditPeriodTextField.setText(""
							+ ChronoUnit.DAYS.between(startDate, endDate));
				}
			}
		});
		mainPanel.add(startDateField);

		endDateModel = new LocalDateModel();
		JDatePanelImpl endDatePanel = new JDatePanelImpl(endDateModel, p);
		endDateField = new JDatePickerImpl(endDatePanel,
				new DateLabelFormatter());
		endDateModel.setValue(LocalDate.now().plusDays(1));
		endDateField.setBounds(361, 193, 115, 28);
		endDateModel.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				LocalDate endDate = endDateModel.getValue();
				LocalDate startDate = startDateModel.getValue();
				if (!endDate.isAfter(startDate)) {
					endDateModel.setValue(startDate.plusDays(1));
				}
				creditPeriodTextField.setText(""
						+ ChronoUnit.DAYS.between(startDate, endDate));
			}
		});
		mainPanel.add(endDateField);

		creditTypeComboBox = new JComboBox<String>();
		creditTypeComboBox.setBounds(23, 34, 680, 26);
		creditTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = (String) creditTypeComboBox.getSelectedItem();
				switch (type) {
				case "До востредования с ежемесячной выплатой процентов":
					endDateField.getComponent(1).setEnabled(false);
					creditPeriodTextField.setText("");
					break;
				case "Срочный с процентами с конце срока":
					endDateField.getComponent(1).setEnabled(true);
					startDateModel.setValue(LocalDate.now());
					endDateModel.setValue(LocalDate.now().plusDays(1));
					break;
				default:
					break;
				}
			}
		});
		mainPanel.add(creditTypeComboBox);

		// MAYBE change to real contact number mask
		MaskFormatter contractNumberMask = new MaskFormatter("#######U###UU#");
		contractNumberField = new JFormattedTextField(contractNumberMask);
		contractNumberField.setBounds(322, 82, 381, 28);
		mainPanel.add(contractNumberField);
		contractNumberField.setColumns(10);

		creditPeriodTextField = new JTextField();
		creditPeriodTextField.setColumns(10);
		creditPeriodTextField.setBounds(601, 187, 134, 28);
		creditPeriodTextField.setText("1");
		creditPeriodTextField.setEditable(false);
		mainPanel.add(creditPeriodTextField);

		creditSumField = new JFormattedTextField();
		creditSumField.setBounds(136, 246, 114, 20);
		creditSumField.setColumns(10);
		creditSumField.setValue(1.0);
		mainPanel.add(creditSumField);

		NumberFormat persentFormat = NumberFormat.getPercentInstance();
		persentFormat.setMaximumFractionDigits(2);
		NumberFormatter persentsFormatter = new NumberFormatter(persentFormat);
		persentsFormatter.setMinimum((float) 0.0001);
		persentsFormatter.setMaximum((float) 3);
		persentTextField = new JFormattedTextField(persentsFormatter);
		persentTextField.setColumns(10);
		persentTextField.setValue(new Float(0.056));
		persentTextField.setBounds(372, 246, 114, 20);
		mainPanel.add(persentTextField);

		clientComboBox = new JComboBox<String>();
		clientComboBox.setBounds(79, 121, 624, 26);
		mainPanel.add(clientComboBox);

		currencyComboBox = new JComboBox<String>();
		currencyComboBox.setBounds(23, 87, 126, 26);
		currencyComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = (String) currencyComboBox.getSelectedItem();
				NumberFormat format = null;
				switch (type) {
				case "EUR":
					format = NumberFormat.getCurrencyInstance(Locale.GERMANY);
					persentTextField.setValue(new Float(0.075));
					break;
				case "USD":
					format = NumberFormat.getCurrencyInstance(Locale.US);
					persentTextField.setValue(new Float(0.075));
					break;
				case "BYR":
					format = NumberFormat.getCurrencyInstance(Locale
							.forLanguageTag("be-BY"));
					persentTextField.setValue(new Float(0.435));
					break;
				default:
					format = NumberFormat.getCurrencyInstance();
				}
				format.setMaximumFractionDigits(0);
				NumberFormatter formatter = new NumberFormatter(format);
				formatter.setMinimum(1.0);
				formatter.setMaximum(10000000.0);
				formatter.setAllowsInvalid(false);
				formatter.setOverwriteMode(true);
				creditSumField.setFormatterFactory(new DefaultFormatterFactory(
						formatter));
			}
		});
		mainPanel.add(currencyComboBox);
	}

	private void fillComboBoxes() {
		fillComboBox(creditTypeComboBox, CreditProvider.getInstance()
				.getCreditTypeList());
		fillComboBox(currencyComboBox, DepositProvider.getInstance()
				.getCurrency());
		fillComboBox(clientComboBox, ClientProvider.getInstance()
				.getUserFullNames());
	}

	private void fillComboBox(JComboBox<String> target, ArrayList<String> source) {
		for (String item : source) {
			target.addItem(item);
		}
	}
}