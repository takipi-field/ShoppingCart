package com.mockdata.generate.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mockdata.generate.DataTypeGenerator;

public class DateUtils {

	private DateUtils() {
		//Do nothing
	}
	
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	public static String getRandomDateString() {
		int day = RandomUtil.getRandomNumberInRange(1, 31);
		int month = RandomUtil.getRandomNumberInRange(1, 12);
		int year = RandomUtil.getRandomNumberInRange(1950, 2018);
		String separator = DataTypeGenerator.getRandomDataType("DATE_SEPARATOR");
		
		return month + separator + day + separator + year;
	}
	
	public static Date getRandomDate(int afterDays) {
		int noOfDays = RandomUtil.getRandomNumberInRange(1, afterDays + 1);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, noOfDays);
		return c.getTime();
	}
	
	public static String getRandomDateString(int afterDays) {
		Date date = getRandomDate(afterDays);
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		return format.format(date);
	}
}