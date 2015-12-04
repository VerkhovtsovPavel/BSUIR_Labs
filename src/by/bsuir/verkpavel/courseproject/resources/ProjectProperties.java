package by.bsuir.verkpavel.courseproject.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;

public class ProjectProperties {
    private static Logger log = Logger.getLogger(ProjectProperties.class);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static String RUSSIAN_ALPHABET = "йцукенгшщзхъфывапролджэячсмитьбюё";

    public static SimpleDateFormat getDateFormatter() {
        return simpleDateFormat;
    }

    public static String getRussianAlphabet() {
        return RUSSIAN_ALPHABET;
    }

    public static MaskFormatter getPhoneNumberMask() {
        try {
            return new MaskFormatter("+(###)-##-###-####");
        } catch (ParseException e) {
            log.error("Error while parse phone format", e);
        }
        return null;
    }

}
