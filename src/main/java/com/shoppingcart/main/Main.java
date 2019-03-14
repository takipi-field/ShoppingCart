package com.shoppingcart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.domain.ShoppingCartProperties;
import com.shoppingcart.manager.ShoppingCartPropertyManager;
import com.shoppingcart.workflow.MultiThreadEngine;
import com.shoppingcart.workflow.SingleThreadEngine;

public class Main {

	private final static Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		log.info("Starting Retail Application ...Waiting for 15 seconds for Overops to Initialize");
		waiting(15000);

		log.info("Reading Property Variables ...");
		ShoppingCartProperties scProperties = ShoppingCartPropertyManager.populate(args);
		scProperties.print();

		log.info("Lets start by creating 2 random Exceptions ...");
		// Every run will create 2 random exceptions (used by Jenkins builds for new errors)
		Main2RandomExceptions.main(args);
		log.info("Generation of 2 random Exceptions complete");
		
		log.info("Lets start the runs");
		if (scProperties.runMultiThreadEngine()) {
			log.info("Entering Uncaught or Swallowed exceptions loop");
			MultiThreadEngine engine = new MultiThreadEngine(scProperties);
			log.info("Running the multi thread engine");
			engine.run();
		} else {
			SingleThreadEngine engine = new SingleThreadEngine(scProperties);
			engine.run();
		}
		log.info("We are done ...");
		if (scProperties.isContinueRunning()) {
			while (true) {
				log.info("Waiting for a minute to simulate a longer running JVM ...");
				waiting(60000);
			}
		}
	}

	private static void waiting(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}