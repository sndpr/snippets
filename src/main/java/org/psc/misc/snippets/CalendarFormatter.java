package org.psc.misc.snippets;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarFormatter {
	
	
	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		
		System.out.println(dateFormat.format(now.getTime()));
		
		
	}
}
