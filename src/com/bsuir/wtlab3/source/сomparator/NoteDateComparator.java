package com.bsuir.wtlab3.source.ñomparator;

import java.util.Calendar;
import java.util.Comparator;

import com.bsuir.wtlab3.source.entity.Note;


public class NoteDateComparator implements Comparator<Note> {

	@Override
	public int compare(Note o1, Note o2) {
		return getCalendar(o1.getCreateDate()).compareTo(getCalendar(o2.getCreateDate()));
	}
	
	private Calendar getCalendar(String time){
		String[] times = time.split("[\\- \\t\\/]");
		
		int day = Integer.valueOf(times[0]);
		int month = Integer.valueOf(times[1]);
		int year = Integer.valueOf(times[2]);
		
		int hour = Integer.valueOf(times[3]);
		int minute = Integer.valueOf(times[4]);
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hour, minute);
		return cal;
	}
}
