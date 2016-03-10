package by.bsuir.verkpavel.adb.ui;

import java.time.LocalDate;
import java.util.Calendar;

import org.jdatepicker.AbstractDateModel;

public class LocalDateModel extends AbstractDateModel<LocalDate> {

	@Override
	protected LocalDate fromCalendar(Calendar cal) {
		if(cal==null){
			return LocalDate.now();
		}
		return LocalDate
				.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1,
						cal.get(Calendar.DAY_OF_MONTH));
	}

	@Override
	protected Calendar toCalendar(LocalDate ld) {
		Calendar cal = Calendar.getInstance();
		cal.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth());
		return cal;
	}
}
