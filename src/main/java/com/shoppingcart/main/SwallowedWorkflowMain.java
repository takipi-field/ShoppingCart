package com.shoppingcart.main;

import com.shoppingcart.workflow.ShoppingCartWF;


public class SwallowedWorkflowMain {

//	private final static Logger log = LoggerFactory.getLogger(SwallowedWorkflowMain.class);

	public static void main(String[] args) {
//		log.info("Starting Retail Application SwallowedWorkflowMain ...Waiting for 15 seconds for Overops to Initialize");
//		waiting(15000);

		ShoppingCartWF workflow = new ShoppingCartWF();
		workflow.workflow10();
	}
	
	public static void waiting(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}