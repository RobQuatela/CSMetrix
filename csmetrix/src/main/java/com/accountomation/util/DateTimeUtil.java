package com.accountomation.util;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeUtil {

	private static Pattern pattern;
	private static Matcher matcher;
	
	private static final String DATE_PATTERN = "(0?[1-9]|1[012])/(0?[1-9]|[12[0-9]|3[01])/((19|20)\\d\\d)";
	
	public static Date convertToDate(String date) {
		pattern = Pattern.compile(DATE_PATTERN);
		matcher = pattern.matcher(date);
		Date newDate = null;
		
		if(matcher.matches()) {
			matcher.reset();
			if(matcher.find()) {
				newDate = Date.valueOf(LocalDate.of(Integer.parseInt(matcher.group(3)),
						Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
			}
		}
		
		return newDate;
	}
}
