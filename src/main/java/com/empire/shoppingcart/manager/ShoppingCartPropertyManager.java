package com.empire.shoppingcart.manager;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.shoppingcart.domain.ShoppingCartProperties;
import com.empire.shoppingcart.util.ShoppingCartPropertyReader;

public class ShoppingCartPropertyManager {

	private static final Logger log = LoggerFactory.getLogger(ShoppingCartPropertyManager.class);

	private ShoppingCartPropertyManager() {
		//Do nothing
	}
	
	public static ShoppingCartProperties populate(String[] args) {
		
		Properties scProperties = ShoppingCartPropertyReader.getInstance();

		//Get default values from property file.
		int noOfThreads = Integer.parseInt(scProperties.
			getProperty("SHOPPING_CART.NO.OF.THREADS"));
		int numberOfIterations = Integer.parseInt(scProperties.
			getProperty("SHOPPING_CART.NO.OF.ITERATIONS"));
		String runMode = scProperties.
			getProperty("SHOPPING_CART.RUN_MODE");
		boolean continueRunning = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.CONTINUE_RUNNING"));
		int waitTime = Integer.parseInt(scProperties.
			getProperty("SHOPPING_CART.WAIT_TIME"));
		String urlConnectString = scProperties.
			getProperty("SHOPPING_CART.URL_CONNECT_STRING");

		boolean workflow1Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW1.ENABLED"));
		boolean workflow2Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW2.ENABLED"));
		boolean workflow3Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW3.ENABLED"));
		boolean workflow4Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW4.ENABLED"));
		boolean workflow5Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW5.ENABLED"));
		boolean workflow6Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW6.ENABLED"));
		boolean workflow7Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW7.ENABLED"));
		boolean workflow8Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW8.ENABLED"));
		boolean workflow9Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW9.ENABLED"));
		boolean workflow10Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW10.ENABLED"));
		boolean workflow11Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW11.ENABLED"));
		boolean workflow12Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW12.ENABLED"));
		boolean workflow13Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW13.ENABLED"));
		boolean workflow14Enabled = Boolean.parseBoolean(
			scProperties.getProperty("SHOPPING_CART.WORKFLOW14.ENABLED"));


		//Override the default properties from the command line program arguments (if passed)
		if (args != null && args.length != 0) {
			int i = 0;
			while (i < args.length) {
				if (args[i].contains("run_mode")) {
					runMode = args[i].substring(9);
					log.info("Overriding runMode with: {}", runMode);
				}
				if (args[i].contains("no_of_threads")) {
					noOfThreads = Integer.parseInt(args[i].substring(14));
					log.info("Overriding noOfThreads with: {}", noOfThreads);
				}
				if (args[i].contains("no_of_iterations")) {
					numberOfIterations = Integer.parseInt(args[i].substring(17));
					log.info("Overriding numberOfIterations with: {}", numberOfIterations);
				}
				if (args[i].contains("continue_running")) {
					continueRunning = Boolean.parseBoolean(args[i].substring(17));
					log.info("Overriding continue_running with: {}", continueRunning);
				}
				if (args[i].contains("wait_time")) {
					waitTime = Integer.parseInt(args[i].substring(10));
					log.info("Overriding wait_time with: {}", waitTime);
				}
				if (args[i].contains("url_connect_string")) {
					urlConnectString = args[i].substring(19);
					log.info("Overriding url_connect_string with: {}", urlConnectString);
				}
				
				if (args[i].contains("workflow1_enabled")) {
					workflow1Enabled = Boolean.parseBoolean(args[i].substring(18));
					log.info("Overriding workflow1_enabled with: {}", workflow1Enabled);
				}
				if (args[i].contains("workflow2_enabled")) {
					workflow2Enabled = Boolean.parseBoolean(args[i].substring(18));
					log.info("Overriding workflow2_enabled with: {}", workflow2Enabled);
				}
				if (args[i].contains("workflow3_enabled")) {
					workflow3Enabled = Boolean.parseBoolean(args[i].substring(18));
					log.info("Overriding workflow3_enabled with: {}", workflow3Enabled);
				}
				if (args[i].contains("workflow4_enabled")) {
					workflow4Enabled = Boolean.parseBoolean(args[i].substring(18));
					log.info("Overriding workflow4_enabled with: {}", workflow4Enabled);
				}
				if (args[i].contains("workflow5_enabled")) {
					workflow5Enabled = Boolean.parseBoolean(args[i].substring(18));
					log.info("Overriding workflow5_enabled with: {}", workflow5Enabled);
				}
				if (args[i].contains("workflow6_enabled")) {
					workflow6Enabled = Boolean.parseBoolean(args[i].substring(18));
					log.info("Overriding workflow6_enabled with: {}", workflow6Enabled);
				}
				if (args[i].contains("workflow7_enabled")) {
					workflow7Enabled = Boolean.parseBoolean(args[i].substring(18));
					log.info("Overriding workflow7_enabled with: {}", workflow7Enabled);
				}
				if (args[i].contains("workflow8_enabled")) {
					workflow8Enabled = Boolean.parseBoolean(args[i].substring(18));
					log.info("Overriding workflow8_enabled with: {}", workflow8Enabled);
				}
				if (args[i].contains("workflow9_enabled")) {
					workflow9Enabled = Boolean.parseBoolean(args[i].substring(18));
					log.info("Overriding workflow9_enabled with: {}", workflow9Enabled);
				}
				if (args[i].contains("workflow10_enabled")) {
					workflow10Enabled = Boolean.parseBoolean(args[i].substring(19));
					log.info("Overriding workflow10_enabled with: {}", workflow10Enabled);
				}
				if (args[i].contains("workflow11_enabled")) {
					workflow11Enabled = Boolean.parseBoolean(args[i].substring(19));
					log.info("Overriding workflow11_enabled with: {}", workflow11Enabled);
				}
				if (args[i].contains("workflow12_enabled")) {
					workflow12Enabled = Boolean.parseBoolean(args[i].substring(19));
					log.info("Overriding workflow12_enabled with: {}", workflow12Enabled);
				}
				if (args[i].contains("workflow13_enabled")) {
					workflow13Enabled = Boolean.parseBoolean(args[i].substring(19));
					log.info("Overriding workflow13_enabled with: {}", workflow13Enabled);
				}
				if (args[i].contains("workflow14_enabled")) {
					workflow13Enabled = Boolean.parseBoolean(args[i].substring(19));
					log.info("Overriding workflow14_enabled with: {}", workflow14Enabled);
				}
				
				i++;
			}
		}

		ShoppingCartProperties cartProperties = new ShoppingCartProperties();
		cartProperties.setNoOfThreads(noOfThreads);
		cartProperties.setRunMode(runMode);
		cartProperties.setNumberOfIterations(numberOfIterations);
		cartProperties.setContinueRunning(continueRunning);
		cartProperties.setWaitTime(waitTime);
		cartProperties.setUrlConnectString(urlConnectString);
		
		cartProperties.setWorkflow1Enabled(workflow1Enabled);
		cartProperties.setWorkflow2Enabled(workflow2Enabled);
		cartProperties.setWorkflow3Enabled(workflow3Enabled);
		cartProperties.setWorkflow4Enabled(workflow4Enabled);
		cartProperties.setWorkflow5Enabled(workflow5Enabled);
		cartProperties.setWorkflow6Enabled(workflow6Enabled);
		cartProperties.setWorkflow7Enabled(workflow7Enabled);
		cartProperties.setWorkflow8Enabled(workflow8Enabled);
		cartProperties.setWorkflow9Enabled(workflow9Enabled);
		cartProperties.setWorkflow10Enabled(workflow10Enabled);
		cartProperties.setWorkflow11Enabled(workflow11Enabled);
		cartProperties.setWorkflow12Enabled(workflow12Enabled);
		cartProperties.setWorkflow13Enabled(workflow13Enabled);
		cartProperties.setWorkflow14Enabled(workflow14Enabled);

		return cartProperties;
	}
}