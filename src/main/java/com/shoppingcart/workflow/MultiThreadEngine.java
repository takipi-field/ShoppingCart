package com.shoppingcart.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.util.exception.ShoppingCartException;

public class MultiThreadEngine {
	
	private int numberOfThreads;
	private int numberOfIterations;

	public final static Logger log = 
		LoggerFactory.getLogger(MultiThreadEngine.class);

	public MultiThreadEngine(int numberOfThreads, 
			int numberOfIterations, String runMode) {
		log.info("Initializing Constructor");
		this.numberOfThreads = numberOfThreads;
		this.numberOfIterations = numberOfIterations;
		log.info("Completed initializing Constructor");
	}
	
	public void run() {
		log.info("Entering run method");
		ExecutorService executor = Executors.
			newFixedThreadPool(numberOfThreads);
		log.info("Creating a future List ...");
		List<Future<?>> futureList = new ArrayList<Future<?>>();
		for(int i = 0; i < numberOfThreads; i++) {
			log.info("Lets submit a shopping cart Job ...");
			Future<?> future = executor.submit(
				new ShoppingCartThread(this.numberOfIterations));
			log.info("Add it to the furute list ...");
			futureList.add(future);
		}
		log.info("Lets handle the shutdown ...");
		handleShutdown(executor, futureList);
		log.info("Completed handling shutdown ...");
	}

	private void handleShutdown(ExecutorService 
			executor, List<Future<?>> futureList) {
		executor.shutdown();
		for (Future<?> future : futureList) {
			while (true) {
				try {
					//Wait for all threads to complete
					future.get();
				} catch (java.util.concurrent.ExecutionException e) {
					log.error("Exception in thread: " + e.getMessage());
					e.printStackTrace(); 
				} catch (Exception e) {
					log.error("Exception in thread: " + e.getMessage());
					throw new ShoppingCartException(e);
				}
				break;
			}
		}
		try {
			executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			throw new ShoppingCartException(e);
		}
		executor.shutdownNow();
	}
}