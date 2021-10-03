package models;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarClass {
	private Calendar calendar;
	
	public CalendarClass() {
		calendar = Calendar.getInstance();
	}
	
	public int getHour() {
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	
	public int getMinute() {
		int minute = calendar.get(Calendar.MINUTE);
		return minute;
	}
	
	public String getData() {
		Date date = calendar.getTime();
		DateFormat formatDate = DateFormat.getDateInstance();
		String formatedDate = formatDate.format(date);
		return formatedDate;
	}
	
}
