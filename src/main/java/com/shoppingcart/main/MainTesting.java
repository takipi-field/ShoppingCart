package com.shoppingcart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.DelayGenerator;
import com.takipi.sdk.v1.api.Takipi;
import com.takipi.sdk.v1.api.core.events.TakipiEvent;

public class MainTesting {

	private static final Logger log = LoggerFactory.getLogger(MainTesting.class);

	public static void main(String[] args) {
		
		log.info("Waiting for 15 seconds for Overops to Initialize");
		DelayGenerator.introduceDelay(15000);


		log.info("Starting Custom events");
		for (int i = 0; i<=10; i++) {
			Takipi takipi = Takipi.create("NewCustomerEvent " + i);
			TakipiEvent customEvent = takipi.events().createEvent("Creating a new Customer with CustomerNumber:" + i);
			customEvent.fire();
		}
		log.info("We are done");
	}
}