package com.shoppingcart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.workflow.ShoppingCartWorkflow;


public class SwallowedWorkflowMain {

	private final static Logger log = LoggerFactory.getLogger(SwallowedWorkflowMain.class);

	public static void main(String[] args) {
		log.info("Starting Retail Application ...");
		ShoppingCartWorkflow workflow = new ShoppingCartWorkflow();
		workflow.workflow10();
		log.info("We are done ...");
	}
}