package com.empire.extra;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewExceptionsHandler {

	public static final Logger log = LoggerFactory.getLogger(NewExceptionsHandler.class);
	private static final DateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");


	private static NewExceptions newExceptions = new NewExceptions();

	public void createNewExcpetions() {
		Date nowDate = Calendar.getInstance().getTime();
		long now = Long.parseLong(simpleDateFormat.format(nowDate));
		log.info("Now is: {}", now);
		newExceptions.createNewRandomException();

	}
}