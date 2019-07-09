package com.empire.shoppingcart.newexceptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.mockdata.generate.utils.RandomUtil;
import com.empire.shoppingcart.util.ExceptionListReader;

@SuppressWarnings("unchecked")
public class NewExceptions7 {

	private static final Logger log = LoggerFactory.getLogger(NewExceptions7.class);
	private static final DateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
	
	private static long storedDate = 0;
		
	public synchronized void createNewRandomException() {
		Date nowDate = Calendar.getInstance().getTime();
		long now = Long.parseLong(simpleDateFormat.format(nowDate));
		
		if (storedDate == 0 || storedDate < now) {
			storedDate = now;
			log.info("Its a brand new day - lets create a brand new exceptions");
			int i = RandomUtil.getRandomNumberInRange(1, 2);
			if (i == 1) {
				log.info("Won the lottery, lets create the exception");
				create();
			}
		}
	}
	
	private void create() {
		create("New Random Exception1");
	}

	private void create(String message) {
	
		log.info("Creating {} ...", message);
		try {			
			int i = RandomUtil.getRandomNumberInRange(1, ExceptionListReader.getInstance().size() + 1);
			log.info("Random Number Generated is: {}", i);
			String className = (String) ExceptionListReader.getInstance().get(i + "");
			log.info("Calling ClassForName with className: {}", className);
			Class<Throwable> c = (Class<Throwable>) Class.forName(className);
			log.info("Received Class");
			myFunc(c);
		}
		catch (Throwable e) {
			log.error("Error occured", e);
		}
		log.info("Completed generating {}", message);
	}
	
	private <T extends Throwable> void myFunc(Class<T> exceptionType) throws Exception, T {
		log.info("Inside myFunc class");
		
	    final String message = "Random Exception with custom message " + RandomUtil.generateRandomAlphaString(10);
	    log.info("Message is: {}", message);
	    throw exceptionType.getConstructor(String.class).newInstance(message);
	}
}