package com.shoppingcart.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCartProperties extends BaseDomain {

	private int noOfThreads;
	private int numberOfIterations;
	private String runMode;
	private boolean continueRunning = false;
	private int waitTime;

	private boolean workflow1Enabled = true;
	private boolean workflow2Enabled = true;
	private boolean workflow3Enabled = true;
	private boolean workflow4Enabled = true;
	private boolean workflow5Enabled = true;
	private boolean workflow6Enabled = true;
	private boolean workflow7Enabled = true;
	private boolean workflow8Enabled = true;
	private boolean workflow9Enabled = true;
	private boolean workflow10Enabled = true;
	private boolean workflow11Enabled = true;
	private boolean workflow12Enabled = true;
	private boolean workflow13Enabled = true;
	
	private static final Logger log = LoggerFactory.getLogger(ShoppingCartProperties.class);
	
	public boolean isWorkflow1Enabled() {
		return workflow1Enabled;
	}
	public void setWorkflow1Enabled(boolean workflow1Enabled) {
		this.workflow1Enabled = workflow1Enabled;
	}
	public boolean isWorkflow2Enabled() {
		return workflow2Enabled;
	}
	public void setWorkflow2Enabled(boolean workflow2Enabled) {
		this.workflow2Enabled = workflow2Enabled;
	}
	public boolean isWorkflow3Enabled() {
		return workflow3Enabled;
	}
	public void setWorkflow3Enabled(boolean workflow3Enabled) {
		this.workflow3Enabled = workflow3Enabled;
	}
	public boolean isWorkflow4Enabled() {
		return workflow4Enabled;
	}
	public void setWorkflow4Enabled(boolean workflow4Enabled) {
		this.workflow4Enabled = workflow4Enabled;
	}
	public boolean isWorkflow5Enabled() {
		return workflow5Enabled;
	}
	public void setWorkflow5Enabled(boolean workflow5Enabled) {
		this.workflow5Enabled = workflow5Enabled;
	}
	public boolean isWorkflow6Enabled() {
		return workflow6Enabled;
	}
	public void setWorkflow6Enabled(boolean workflow6Enabled) {
		this.workflow6Enabled = workflow6Enabled;
	}
	public boolean isWorkflow7Enabled() {
		return workflow7Enabled;
	}
	public void setWorkflow7Enabled(boolean workflow7Enabled) {
		this.workflow7Enabled = workflow7Enabled;
	}
	public boolean isWorkflow8Enabled() {
		return workflow8Enabled;
	}
	public void setWorkflow8Enabled(boolean workflow8Enabled) {
		this.workflow8Enabled = workflow8Enabled;
	}
	public boolean isWorkflow9Enabled() {
		return workflow9Enabled;
	}
	public void setWorkflow9Enabled(boolean workflow9Enabled) {
		this.workflow9Enabled = workflow9Enabled;
	}
	public boolean isWorkflow10Enabled() {
		return workflow10Enabled;
	}
	public void setWorkflow10Enabled(boolean workflow10Enabled) {
		this.workflow10Enabled = workflow10Enabled;
	}
	public boolean isWorkflow11Enabled() {
		return workflow11Enabled;
	}
	public void setWorkflow11Enabled(boolean workflow11Enabled) {
		this.workflow11Enabled = workflow11Enabled;
	}
	public boolean isWorkflow12Enabled() {
		return workflow12Enabled;
	}
	public void setWorkflow12Enabled(boolean workflow12Enabled) {
		this.workflow12Enabled = workflow12Enabled;
	}
	public boolean isWorkflow13Enabled() {
		return workflow13Enabled;
	}
	public void setWorkflow13Enabled(boolean workflow13Enabled) {
		this.workflow13Enabled = workflow13Enabled;
	}
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
		
		log.info("Workflow1 enabled is: {}", workflow1Enabled);
		log.info("Workflow2 enabled is: {}", workflow2Enabled);
		log.info("Workflow3 enabled is: {}", workflow3Enabled);
		log.info("Workflow4 enabled is: {}", workflow4Enabled);
		log.info("Workflow5 enabled is: {}", workflow5Enabled);
		log.info("Workflow6 enabled is: {}", workflow6Enabled);
		log.info("Workflow7 enabled is: {}", workflow7Enabled);
		log.info("Workflow8 enabled is: {}", workflow8Enabled);
		log.info("Workflow9 enabled is: {}", workflow9Enabled);
		log.info("Workflow10 enabled is: {}", workflow10Enabled);
		log.info("Workflow11 enabled is: {}", workflow11Enabled);
		log.info("Workflow12 enabled is: {}", workflow12Enabled);
		log.info("Workflow13 enabled is: {}", workflow13Enabled);
	
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