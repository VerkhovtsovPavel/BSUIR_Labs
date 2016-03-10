package by.bsuir.verkpavel.adb.atm_client.resources;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

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
    
    public static NumberFormatter getBYRFormatter(){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("be-BY"));

        format.setMaximumFractionDigits(0);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setMinimum(1.0);
        formatter.setMaximum(10000000.0);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        
        return formatter;
    }

}
