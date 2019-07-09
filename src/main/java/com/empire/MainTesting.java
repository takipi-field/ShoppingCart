package com.empire;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTesting {

	public static final Logger log = LoggerFactory.getLogger(MainTesting.class);
	private static AtomicLong count = new AtomicLong(5);
	private static final DateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
	private static long storedDate = 0;

	public static void main(String[] args) {
		for (int i=0; i<=50; i++) {
			determineCount();
			log.info(""+count);
		}
	}
	
	public synchronized static void determineCount() {
		Date nowDate = Calendar.getInstance().getTime();
		long now = Long.parseLong(simpleDateFormat.format(nowDate));
		
		if (storedDate == 0 || storedDate < now) {
			storedDate = now;
			log.info("Its a brand new day - lets increase the no of errors by 10 percent");
			count.set(Math.round(count.floatValue()*1.10));
			if (count.get() > 500) {
				count.set(5);
			}
		}
	}
}