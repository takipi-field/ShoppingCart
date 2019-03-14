package com.shoppingcart.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.main.SwallowedWorkflowMain;
import com.shoppingcart.domain.ShoppingCartProperties;
import com.shoppingcart.exception.ShoppingCartException;

public class SingleThreadEngine {
	
	private int numberOfIterations;
	private String runMode;

	public final static Logger log = 
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
			case 1:		workflow.workflow1(); break;
			case 2: 		workflow.workflow2(); break;
			case 3:		workflow.workflow3(); break;
			default:		throw new ShoppingCartException("Unable to find workflow to call.");
		}
	}
}