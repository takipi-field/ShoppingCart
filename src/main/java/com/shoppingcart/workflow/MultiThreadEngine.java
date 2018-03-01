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
		this.numberOfThreads = numberOfThreads;
		this.numberOfIterations = numberOfIterations;
	}
	
	public void run() {
		ExecutorService executor = Executors.
			newFixedThreadPool(numberOfThreads);
		List<Future<?>> futureList = new ArrayList<Future<?>>();
		for(int i = 0; i < numberOfThreads; i++) {
			Future<?> future = executor.submit(
				new ShoppingCartThread(this.numberOfIterations));
			futureList.add(future);
		}
		handleShutdown(executor, futureList);
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