package com.shoppingcart.workflow;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.DelayGenerator;
import com.shoppingcart.domain.ShoppingCartProperties;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.workflow.tasks.ShoppingCartTask5;
import com.shoppingcart.workflow.tasks.ShoppingCartTaskExecutor;

public class ShoppingCartThread implements Callable<Object> {

	private static final Logger log = LoggerFactory.getLogger(ShoppingCartThread.class);
	private static AtomicLong count = new AtomicLong(0);
	private ShoppingCartProperties scProperties;

	public ShoppingCartThread(ShoppingCartProperties scProperties) {
		log.info("In the constructor");
		this.scProperties = scProperties;
		log.info("Completed ...");
	}
	
	@Override
	public Object call() throws Exception {
		log.info("No of iterations is: {} ", scProperties.getNumberOfIterations());
		log.info("No of threads is {}", scProperties.getNoOfThreads());
		
		int noOfLoops = scProperties.getNumberOfIterations();
		while (count.incrementAndGet() <= noOfLoops) {
			try {
				ShoppingCartTaskExecutor executor = ShoppingCartTaskExecutor.getInstance(1);
				log.info("In ShoppingCartThread: Thread Number is {}", count);
				executor.execute(new ShoppingCartTask5(scProperties));
				int waitTime = scProperties.getWaitTime();
				if (waitTime != 0) {
					log.info("Waiting for {} seconds between iterations ....", waitTime/1000);
					DelayGenerator.introduceDelay(waitTime);
				}
			} catch (Exception e) {
				log.error("Exception in executing workflows {}", e.getMessage());
			}
		}
		log.info("Lets shut down ShoppingCartThreads executor");
//		handleShutdown(executor, futureList);
//		shutdownExecutor(executor);
//		waitForFutures(futureList);
		log.info("Completed handling ShoppingCartThreads shutdown ...");

		return null;
	}
	
	public void handleShutdown(ShoppingCartTaskExecutor 
			executor, List<Future<?>> futureList) {
		waitForFutures(futureList);
		shutdownExecutor(executor);
	}

	private void waitForFutures(List<Future<?>> futureList) {
		for (Future<?> future : futureList) {
			while (true) {
				try {
					//Wait for all threads to complete
					log.info("Waiting for the ShoppingCartThreads executor to complete ...");
					future.get();
				} catch (java.util.concurrent.ExecutionException e) {
					log.error("Exception in ShoppingCartThreads thread: {}", e);
				} catch (Exception e) {
					log.error("Exception in ShoppingCartThreads thread: {}", e);
					throw new ShoppingCartException(e);
				}
				break;
			}
		}
	}

	private void shutdownExecutor(ShoppingCartTaskExecutor executor) {
		log.info("Lets shutdown the ShoppingCartThreads executor");
		executor.shutdown();
		log.info("Waiting for the ShoppingCartThreads executor to complete ...");
		executor.awaitTermination(10, TimeUnit.MINUTES);
		executor.shutdownNow();
		log.info("Completed shutting down ShoppingCartThreads executor");
	}
}