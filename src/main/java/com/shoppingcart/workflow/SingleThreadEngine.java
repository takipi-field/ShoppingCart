package com.shoppingcart.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.RandomUtil;
import com.shoppingcart.domain.ShoppingCartProperties;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.main.SwallowedWorkflowMain;

public class SingleThreadEngine {
	
	private int numberOfIterations;
	private String runMode;

	public static final Logger log = 
		LoggerFactory.getLogger(SingleThreadEngine.class);

	public SingleThreadEngine(ShoppingCartProperties scProps) {
		this.numberOfIterations = scProps.getNumberOfIterations();
		this.runMode = scProps.getRunMode();
	}
	
	public void run() {
		for(int i = 0; i < numberOfIterations; i++) {
			if (runMode.equalsIgnoreCase("UNCAUGHT_EXCEPTIONS")) {
				executeUncaughtExceptions();
			} else {
				executeSwallowedWorkflow();
			}
		}
	}
	
	private void executeSwallowedWorkflow() {
		SwallowedWorkflowMain.main(null);
	}

	private void executeUncaughtExceptions() {
		CartWorkflow workflow = new CartWorkflow();
		
		int randomNo = RandomUtil.getRandomNumberInRange(1, 4);
		switch (randomNo) {
			case 1:		workflow.cartWorkflow1(); break;
			case 2: 	workflow.cartWorkflow2(); break;
			case 3:		workflow.cartWorkflow3(); break;
			default:	throw new ShoppingCartException("Unable to find workflow to call.");
		}
	}
}