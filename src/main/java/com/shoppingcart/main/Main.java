package com.shoppingcart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.util.ShoppingCartPropertyReader;
import com.shoppingcart.workflow.MultiThreadEngine;
import com.shoppingcart.workflow.SingleThreadEngine;


public class Main {

	private final static Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		log.info("Starting Retail Application ...");
		
		//Get default values from property file.
		String noOfThreads = ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.NO.OF.THREADS");
		String numberOfIterations = ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.NO.OF.ITERATIONS");
		String runMode = ShoppingCartPropertyReader.
			getInstance().getProperty("SHOPPING_CART.RUN_MODE");

		//Override the default properties from the command line program arguments (if passed)
		if (args != null && args.length != 0) {
			int i = 0;
			while (i < args.length) {
				if (args[i].contains("run_mode")) {
					runMode = args[i].substring(9);
					log.info("Overriding runMode with: " + runMode);
				}
				if (args[i].contains("no_of_threads")) {
					noOfThreads = args[i].substring(14);
					log.info("Overriding noOfThreads with: " + noOfThreads);
				}
				if (args[i].contains("no_of_iterations")) {
					numberOfIterations = args[i].substring(17);
					log.info("Overriding numberOfIterations with: " + numberOfIterations);
				}				
				i++;
			}
		} else {
			log.info("No of threads is: " + noOfThreads);
			log.info("No of iterations is: " + numberOfIterations);
			log.info("RunMode is: " + runMode);
		}
		
		if (!runMode.equalsIgnoreCase("UNCAUGHT_EXCEPTIONS") && !runMode.equalsIgnoreCase("SWALLOWED_EXCEPTION")) {
			MultiThreadEngine engine = new MultiThreadEngine(
				new Integer(noOfThreads), new Integer(numberOfIterations), runMode);
			engine.run();
		} else {
			SingleThreadEngine engine = new SingleThreadEngine(
				new Integer(numberOfIterations), runMode);
			engine.run();
		}
		log.info("We are done ...");
	}
}