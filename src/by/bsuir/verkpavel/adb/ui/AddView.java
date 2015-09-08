package by.bsuir.verkpavel.adb.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import by.bsuir.verkpavel.adb.data.Client;
import by.bsuir.verkpavel.adb.data.DataProvider;

public class AddView extends ActionView {
    private static final long serialVersionUID = -5177836715738946733L;
    
    protected AddView() throws ParseException {
        super();
    }

	@Override
	protected void customActions() {
		setTitle("\u0414\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043A\u043B\u0438\u0435\u043D\u0442\u0430");
		
        JButton saveBtn = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String middleName = middleNameField.getText();
                String bornDate = bornDateField.getText();

                boolean isMan = manRadioButton.isSelected();

                String passportSeries = passportSeriesField.getText();
                String passportNumber = passportNumberField.getText();
                String whoGivePassport = whoGivePassportField.getText();
                String passportTakeDate = passportTakeDateField.getText();
                String identifyNumber = idertifyNumberField.getText();
                String bornPlace = bornPlaceField.getText();

                int realCity = realCityComboBox.getSelectedIndex();
                String realAddress = realAddressField.getText();
                String homePhone = homePhoneField.getText();
                String mobilePhone = mobilePhoneField.getText();
                String eMail = emailField.getText();

                String officialAddress = officialAddressTextField.getText();
                int familyStatus = familyStatusComboBox.getSelectedIndex();
                int nationality = nationalityComboBox.getSelectedIndex();
                int disability = disabilityComboBox.getSelectedIndex();
                boolean pensioner = pensionerCheckBox.isSelected();

                int salary = ((Double)salaryField.getValue()).intValue();

                if (checkRequiredFields(firstName, lastName, middleName, bornDate, passportSeries,
                        passportNumber, whoGivePassport, passportTakeDate, identifyNumber,
                        bornPlace, realCity, realAddress, officialAddress, familyStatus,
                        nationality, disability)) {
                	if(((Date)bornDateField.getValue()).after(new Date())|| ((Date)passportTakeDateField.getValue()).after(new Date())){
                		JOptionPane.showMessageDialog(null, "Дата позже сегодняжней!",
                                "Error", JOptionPane.PLAIN_MESSAGE);
                		return;
                	}
                	JOptionPane.showMessageDialog(null,  DataProvider.getInstance().saveClient(
                            new Client(firstName, lastName, middleName, bornDate, isMan,
                                    passportSeries, passportNumber, whoGivePassport,
                                    passportTakeDate, identifyNumber, bornPlace, realCity,
                                    realAddress, homePhone, mobilePhone, eMail, officialAddress,
                                    familyStatus, nationality, disability, pensioner, salary)), "Message", JOptionPane.PLAIN_MESSAGE);
                    ShowUsersView.create();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "\u0417\u0430\u043F\u043E\u043B\u043D\u0435\u043D\u044B \u043D\u0435 \u0432\u0441\u0435 \u043E\u0431\u044F\u0437\u0430\u0442\u0435\u043B\u044C\u043D\u044B\u0435 \u043F\u043E\u043B\u044F!",
                            "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }

            private boolean checkRequiredFields(Object... fields) {
                for (Object field : fields) {
                    if (field instanceof String) {
                        if (((String) field).trim().isEmpty())
                            return false;
                    } else {
                        if (((Integer) field).intValue() == -1) {
                            return false;
                        }
                    }
                }
                return true;
            }

        });
        saveBtn.setBounds(219, 416, 216, 23);
        mainPanel.add(saveBtn);
	}
    
    

}
