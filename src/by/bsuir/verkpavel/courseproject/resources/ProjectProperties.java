package by.bsuir.verkpavel.courseproject.resources;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ProjectProperties {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public static SimpleDateFormat getDateFormatter(){
       return simpleDateFormat;
    }
    
    public static LocalDate getMaxDate(){
        return LocalDate.of(2100, 1, 1);
    }

}
