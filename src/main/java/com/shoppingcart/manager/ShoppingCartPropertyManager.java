package com.shoppingcart.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.domain.ShoppingCartProperties;
import com.shoppingcart.util.ShoppingCartPropertyReader;

public class ShoppingCartPropertyManager {

	private final static Logger log = LoggerFactory.getLogger(ShoppingCartPropertyManager.class);

	public static ShoppingCartProperties populate(String[] args) {
		//Get default values from property file.
		int noOfThreads = new Integer(ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.NO.OF.THREADS")).intValue();
		int numberOfIterations = new Integer(ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.NO.OF.ITERATIONS")).intValue();
		String runMode = ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.RUN_MODE");
		boolean continueRunning = new Boolean(ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.CONTINUE_RUNNING")).booleanValue();
		int waitTime = new Integer(ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.WAIT_TIME")).intValue();
		
		//Override the default properties from the command line program arguments (if passed)
		if (args != null && args.length != 0) {
			int i = 0;
			while (i < args.length) {
				if (args[i].contains("run_mode")) {
					runMode = args[i].substring(9);
					log.info("Overriding runMode with: " + runMode);
				}
				if (args[i].contains("no_of_threads")) {
					noOfThreads = new Integer(args[i].substring(14)).intValue();
					log.info("Overriding noOfThreads with: " + noOfThreads);
				}
				if (args[i].contains("no_of_iterations")) {
					numberOfIterations = new Integer(args[i].substring(17)).intValue();
					log.info("Overriding numberOfIterations with: " + numberOfIterations);
				}
				if (args[i].contains("continue_running")) {
					continueRunning = new Boolean(args[i].substring(17)).booleanValue();
					log.info("Overriding continue_running with: " + continueRunning);
				}
				if (args[i].contains("wait_time")) {
					waitTime = new Integer(args[i].substring(10)).intValue();
					log.info("Overriding wait_time with: " + waitTime);
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