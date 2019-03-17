package com.shoppingcart.workflow;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.domain.ShoppingCartProperties;
import com.shoppingcart.util.ShoppingCartPropertyReader;
import com.shoppingcart.workflow.tasks.ShoppingCartTask;
import com.shoppingcart.workflow.tasks.ShoppingCartTaskExecutor;

public class ShoppingCartThread implements Callable<Object> {

	private final static Logger log = LoggerFactory.getLogger(ShoppingCartThread.class);
	private AtomicLong count = new AtomicLong(1);
	private ShoppingCartProperties scProperties;
	
	public ShoppingCartThread(ShoppingCartProperties scProperties) {
		log.info("In the constructor");
		this.scProperties = scProperties;
		log.info("Completed ...");
	}

	@Override
	public Object call() throws Exception {
		while (count.incrementAndGet() < scProperties.getNumberOfIterations()) {
			try {
				ShoppingCartTaskExecutor.get().execute(new ShoppingCartTask());
				
				int waitTime = new Integer(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WAIT_TIME")).intValue();
				if (waitTime != 0) {
					log.info("Waiting for " + waitTime/1000 + " seconds between runs ....");
					waiting(waitTime);
				}
			} catch (Exception e) {
				log.error("Exception in executing workflows " + e.getMessage());
			}
		}
		return null;
	}
	
	private static void waiting(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}