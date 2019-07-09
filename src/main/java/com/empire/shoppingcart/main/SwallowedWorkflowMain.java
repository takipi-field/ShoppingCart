package com.empire.shoppingcart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.mockdata.generate.DelayGenerator;
import com.empire.shoppingcart.workflow.ShoppingCartWF;


public class SwallowedWorkflowMain {

	private static final Logger log = LoggerFactory.getLogger(SwallowedWorkflowMain.class);

	public static void main(String[] args) {
		log.info("Starting Retail Application SwallowedWorkflowMain ...Waiting for 15 seconds for Overops to Initialize");
		DelayGenerator.introduceDelay(15000);
		
		ShoppingCartWF workflow = new ShoppingCartWF();
		workflow.workflow10();
	}
}