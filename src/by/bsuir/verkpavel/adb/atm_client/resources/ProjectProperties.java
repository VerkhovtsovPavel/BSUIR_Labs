package by.bsuir.verkpavel.adb.atm_client.resources;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class ProjectProperties {

    public static MaskFormatter getPhoneNumberFormatter() {
        MaskFormatter phoneNumberFormater = null;
        try {
            phoneNumberFormater = new MaskFormatter("+(###)-##-###-####");
        } catch (ParseException e) {
        }

        return phoneNumberFormater;
    }
    
    public static MaskFormatter getCardNumberFormatter() {
        MaskFormatter phoneNumberFormater = null;
        try {
            phoneNumberFormater = new MaskFormatter("#### #### #### ####");
        } catch (ParseException e) {
        }

        return phoneNumberFormater;
    }

}
