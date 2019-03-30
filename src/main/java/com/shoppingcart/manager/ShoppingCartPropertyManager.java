package com.shoppingcart.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.domain.ShoppingCartProperties;
import com.shoppingcart.util.ShoppingCartPropertyReader;

public class ShoppingCartPropertyManager {

	private static final Logger log = LoggerFactory.getLogger(ShoppingCartPropertyManager.class);

	private ShoppingCartPropertyManager() {
		//Do nothing
	}
	
	public static ShoppingCartProperties populate(String[] args) {
		//Get default values from property file.
		int noOfThreads = Integer.parseInt(ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.NO.OF.THREADS"));
		int numberOfIterations = Integer.parseInt(ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.NO.OF.ITERATIONS"));
		String runMode = ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.RUN_MODE");
		boolean continueRunning = Boolean.parseBoolean(ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.CONTINUE_RUNNING"));
		int waitTime = Integer.parseInt(ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.WAIT_TIME"));
		
		//Override the default properties from the command line program arguments (if passed)
		if (args != null && args.length != 0) {
			int i = 0;
			while (i < args.length) {
				log.info("Checking for run Mode");
				if (args[i].contains("run_mode")) {
					runMode = args[i].substring(9);
					log.info("Overriding runMode with: {}", runMode);
				}
				log.info("Checking for No Of Threads");
				if (args[i].contains("no_of_threads")) {
					noOfThreads = Integer.parseInt(args[i].substring(14));
					log.info("Overriding noOfThreads with: {}", noOfThreads);
				}
				log.info("Checking for No Of Iterations");
				if (args[i].contains("no_of_iterations")) {
					numberOfIterations = Integer.parseInt(args[i].substring(17));
					log.info("Overriding numberOfIterations with: {}", numberOfIterations);
				}
				log.info("Checking for Continue Running");
				if (args[i].contains("continue_running")) {
					continueRunning = Boolean.parseBoolean(args[i].substring(17));
					log.info("Overriding continue_running with: {}", continueRunning);
				}
				log.info("Checking for Wait time");
				if (args[i].contains("wait_time")) {
					waitTime = Integer.parseInt(args[i].substring(10));
					log.info("Overriding wait_time with: {}", waitTime);
				}
				i++;
			}
		}

		ShoppingCartProperties scProperties = new ShoppingCartProperties();
		scProperties.setNoOfThreads(noOfThreads);
		scProperties.setRunMode(runMode);
		scProperties.setNumberOfIterations(numberOfIterations);
		scProperties.setContinueRunning(continueRunning);
		scProperties.setWaitTime(waitTime);
		return scProperties;
	}
}