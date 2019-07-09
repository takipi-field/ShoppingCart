package com.empire.shoppingcart.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.shoppingcart.domain.ShoppingCartProperties;
import com.empire.shoppingcart.exception.ShoppingCartException;
import com.empire.shoppingcart.workflow.tasks.ShoppingCartTaskExecutor;

public class MultiThreadEngine {
	
	private ShoppingCartProperties scProperties;

	public static final Logger log = 
		LoggerFactory.getLogger(MultiThreadEngine.class);

	public MultiThreadEngine(ShoppingCartProperties scProperties) {
		log.info("Initializing Constructor");
		this.scProperties = scProperties;
		log.info("Completed initializing Constructor");
	}
	
	public void run() {
		log.info("Entering run method");
		int noOfRuns = scProperties.getNoOfThreads(); 
		ExecutorService executor = Executors.newFixedThreadPool(noOfRuns);
		log.info("Creating a future List ...");
		List<Future<?>> futureList = new ArrayList<>();
		for(int i = 0; i < noOfRuns; i++) {
			log.info("Lets submit a shopping cart Job ...");
			Future<?> future = executor.submit(
				new ShoppingCartThread(scProperties));
			log.info("Add it to the future list ...");
			futureList.add(future);
		}
		log.info("Lets handle the shutdown ...");
		handleShutdown(executor, futureList);
		log.info("Completed handling shutdown ...");
	}

	private void handleShutdown(ExecutorService 
			executor, List<Future<?>> futureList) {

		handleFutures(futureList);
		shutdownExecutor(executor);
		
		//shutdownExecutor(ShoppingCartTaskExecutor.getInstance(1));
	}

	public void shutdownExecutor(ShoppingCartTaskExecutor executor) {
		if (executor != null) {
			log.info("Lets shutdown the ShoppingCartTaskExecutor");
			executor.shutdown();
	
			log.info("Waiting for the ShoppingCartTaskExecutor to complete ...");
			executor.awaitTermination(10, TimeUnit.MINUTES);
			executor.shutdownNow();
			log.info("Completed shutting down ShoppingCartTaskExecutor");
		}
	}
	
	private void shutdownExecutor(ExecutorService executor) {
		log.info("Lets shutdown the executor");
		executor.shutdown();

		try {
			log.info("Waiting for the executor to complete ...");
			executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			log.error("Error occured: {}", e);
		    Thread.currentThread().interrupt();
		}
		executor.shutdownNow();
		log.info("Completed shutting down executor");
	}

	private void handleFutures(List<Future<?>> futureList) {
		for (Future<?> future : futureList) {
			while (true) {
				try {
					//Wait for all threads to complete
					log.info("Waiting for the executor to complete ...");
					future.get();
				} catch (java.util.concurrent.ExecutionException e) {
					log.error("Exception in thread: {}", e);
				} catch (Exception e) {
					log.error("Exception in thread: {}", e);
					throw new ShoppingCartException(e);
				}
				break;
			}
		}
	}
}