package com.shoppingcart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.takipi.sdk.v1.api.Takipi;
import com.takipi.sdk.v1.api.core.events.TakipiEvent;

public class MainTesting {

	private final static Logger log = LoggerFactory.getLogger(MainTesting.class);

	public static void main(String[] args) {
		
		log.info("Waiting for 15 seconds for Overops to Initialize");
		waiting(15000);

		log.info("Starting Custom events");
		for (int i = 0; i<=10; i++) {
			Takipi takipi = Takipi.create("NewCustomerEvent " + i);
			TakipiEvent customEvent = takipi.events().createEvent("Creating a new Customer with CustomerNumber:" + i);
			customEvent.fire();
		}
		log.info("We are done");
	}
	
	private static void waiting(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}