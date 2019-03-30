package com.shoppingcart.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCartProperties extends BaseDomain {

	private int noOfThreads;
	private int numberOfIterations;
	private String runMode;
	private boolean continueRunning = false;
	private int waitTime;

	private static final Logger log = LoggerFactory.getLogger(ShoppingCartProperties.class);

	public int getNoOfThreads() {
		return noOfThreads;
	}
	public void setNoOfThreads(int noOfThreads) {
		this.noOfThreads = noOfThreads;
	}
	public int getNumberOfIterations() {
		if (isContinueRunning()) {
			numberOfIterations = Integer.MAX_VALUE;
		}
		return numberOfIterations;
	}
	public void setNumberOfIterations(int numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}
	public String getRunMode() {
		return runMode;
	}
	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}
	public boolean isContinueRunning() {
		return continueRunning;
	}
	public void setContinueRunning(boolean continueRunning) {
		this.continueRunning = continueRunning;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public void print() {
		log.info("No of threads is: {}", noOfThreads);
		log.info("No of iterations is: {}", numberOfIterations);
		log.info("RunMode is: {}", runMode);
		log.info("Continue Running is: {}", continueRunning);
		log.info("Wait Time is: {}", waitTime);
	}

	public boolean runMultiThreadEngine() {
		if (!runMode.equalsIgnoreCase("UNCAUGHT_EXCEPTIONS") && 
				!runMode.equalsIgnoreCase("SWALLOWED_EXCEPTION")) {
			return true;
		}
		log.info("RunMode is: {}", runMode);
		return false;
	}
}