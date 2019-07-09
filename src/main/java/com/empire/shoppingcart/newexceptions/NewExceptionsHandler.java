package com.empire.shoppingcart.newexceptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewExceptionsHandler {

	public static final Logger log = LoggerFactory.getLogger(NewExceptionsHandler.class);
	private static final DateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");

	private static NewExceptions1 newExceptions1 = new NewExceptions1();
	private static NewExceptions2 newExceptions2 = new NewExceptions2();
	private static NewExceptions3 newExceptions3 = new NewExceptions3();
	private static NewExceptions4 newExceptions4 = new NewExceptions4();
	private static NewExceptions5 newExceptions5 = new NewExceptions5();
	private static NewExceptions6 newExceptions6 = new NewExceptions6();
	private static NewExceptions7 newExceptions7 = new NewExceptions7();
	private static NewExceptions8 newExceptions8 = new NewExceptions8();
	private static NewExceptions9 newExceptions9 = new NewExceptions9();
	private static NewExceptions10 newExceptions10 = new NewExceptions10();

	public void create() {

		Date nowDate = Calendar.getInstance().getTime();
		long now = Long.parseLong(simpleDateFormat.format(nowDate));
		log.info("Now is: {}", now);

		newExceptions1.createNewRandomException();
		newExceptions2.createNewRandomException();
		newExceptions3.createNewRandomException();
		newExceptions4.createNewRandomException();
		newExceptions5.createNewRandomException();
		newExceptions6.createNewRandomException();
		newExceptions7.createNewRandomException();
		newExceptions8.createNewRandomException();
		newExceptions9.createNewRandomException();
		newExceptions10.createNewRandomException();

	}
}