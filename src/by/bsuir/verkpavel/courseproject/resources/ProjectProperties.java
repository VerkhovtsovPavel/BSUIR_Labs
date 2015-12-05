package by.bsuir.verkpavel.courseproject.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;

import by.bsuir.verkpavel.courseproject.dao.entity.Rate;

public class ProjectProperties {
    private static Logger log = Logger.getLogger(ProjectProperties.class);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static String RUSSIAN_ALPHABET = "йцукенгшщзхъфывапролджэячсмитьбюё";
    private static final String NUMBERS = "1234567890";

    public static SimpleDateFormat getDateFormatter() {
        return simpleDateFormat;
    }

    public static String getRussianAlphabet() {
        return RUSSIAN_ALPHABET;
    }
    
    public static String getNumbers() {
        return NUMBERS;
    }

    public static MaskFormatter getPhoneNumberMask() {
        try {
            return new MaskFormatter("+(###)-##-###-####");
        } catch (ParseException e) {
            log.error("Error while parse phone format", e);
        }
        return null;
    }

    public static Rate getDefaultRate() {
        Rate rate = new Rate();
        rate.setDepth(100);
        rate.setHeigth(100);
        rate.setWeigth(100);
        rate.setWidth(100);
        
        return rate;
    }

}
