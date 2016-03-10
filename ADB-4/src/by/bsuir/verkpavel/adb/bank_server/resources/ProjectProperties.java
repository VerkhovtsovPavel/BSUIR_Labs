package by.bsuir.verkpavel.adb.bank_server.resources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProjectProperties {
    public static DateTimeFormatter getDateFormatter(){
       return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    public static LocalDate getMaxDate(){
        return LocalDate.of(2100, 1, 1);
    }

	public static String getSystemPassword() {
	 //FIXME Change on real password 
		return "Nice try.)";
	}
}
