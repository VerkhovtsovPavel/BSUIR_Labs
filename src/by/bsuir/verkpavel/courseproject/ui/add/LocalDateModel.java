package by.bsuir.verkpavel.courseproject.ui.add;

import java.util.Calendar;
import java.util.Date;

import org.jdatepicker.AbstractDateModel;

public class LocalDateModel extends AbstractDateModel<Date> {

	@Override
	protected Date fromCalendar(Calendar cal) {
		if(cal==null){
			return new Date();
		}
		return cal.getTime();
	}

	@Override
	protected Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}
