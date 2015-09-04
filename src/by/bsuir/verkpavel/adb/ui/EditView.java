package by.bsuir.verkpavel.adb.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import by.bsuir.verkpavel.adb.data.Client;
import by.bsuir.verkpavel.adb.data.DataProvider;

public class EditView extends ActionView {
    private static final long serialVersionUID = 844868122106482549L;

    protected EditView() throws ParseException {
        super();
    }

    @Override
    protected void customActions() throws ParseException {
        fillFields(currentClient);
        
        JButton editBtn = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
        editBtn.addMouseListener(new MouseAdapter() {
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

                int salary = (int) salaryField.getValue();

                if (checkRequiredFields(firstName, lastName, middleName, bornDate, passportSeries,
                        passportNumber, whoGivePassport, passportTakeDate, identifyNumber,
                        bornPlace, realCity, realAddress, officialAddress, familyStatus,
                        nationality, disability)) {
                    DataProvider.getInstance().updateClient(
                            new Client(currentClient.id, firstName, lastName, middleName, bornDate, isMan,
                                    passportSeries, passportNumber, whoGivePassport,
                                    passportTakeDate, identifyNumber, bornPlace, realCity,
                                    realAddress, homePhone, mobilePhone, eMail, officialAddress,
                                    familyStatus, nationality, disability, pensioner, salary));
                } else {
                    JOptionPane.showMessageDialog(null, "Не все обязательные поля заполнены!",
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
        editBtn.setBounds(219, 416, 216, 23);
        mainPanel.add(editBtn);
    }
    
    

}
