package com.shoppingcart.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.takipi.sdk.v1.api.Takipi;
import com.takipi.sdk.v1.api.core.events.TakipiEvent;

public class CustomEventManager {

	private static final Logger log = LoggerFactory.getLogger(CustomEventManager.class);

	public void generateEvent(String eventName, String eventDesc) {
	    Takipi takipi1 = Takipi.create(eventName);
        TakipiEvent customEvent1 = takipi1.events().createEvent(eventDesc);
        customEvent1.fire();
        log.info("Creating a custom event with eventName {} and eventDescription {}", eventName, eventDesc);
	}
}