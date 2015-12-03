package by.bsuir.verkpavel.courseproject.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.text.MaskFormatter;

public class ProjectProperties {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static String RUSSIAN_ALPHABET = "йцукенгшщзхъфывапролджэячсмитьбюё";

    public static SimpleDateFormat getDateFormatter() {
        return simpleDateFormat;
    }

    public static LocalDate getMaxDate() {
        return LocalDate.of(2100, 1, 1);
    }

    public static String getRussianAlphabet() {
        return RUSSIAN_ALPHABET;
    }

    public static MaskFormatter getPhoneNumberMask() {
        try {
            return new MaskFormatter("+(###)-##-###-####");
        } catch (ParseException e) {
        }
        return null;
    }

}
