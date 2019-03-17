package com.shoppingcart.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.domain.ShoppingCartProperties;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.workflow.tasks.ShoppingCartTaskExecutor;

public class MultiThreadEngine {
	
	private ShoppingCartProperties scProperties;

	public final static Logger log = 
		LoggerFactory.getLogger(MultiThreadEngine.class);

	public MultiThreadEngine(ShoppingCartProperties scProperties) {
		log.info("Initializing Constructor");
		this.scProperties = scProperties;
		log.info("Completed initializing Constructor");
	}
	
	public void run() {
		log.info("Entering run method");
		ExecutorService executor = Executors.
			newFixedThreadPool(scProperties.getNoOfThreads());
		log.info("Creating a future List ...");
		List<Future<?>> futureList = new ArrayList<Future<?>>();
		for(int i = 0; i < scProperties.getNoOfThreads(); i++) {
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
		
		try {
			ShoppingCartTaskExecutor.get().awaitTermination(10, TimeUnit.MINUTES);
			ShoppingCartTaskExecutor.get().shutdownNow();
		} catch (Exception e) {
			throw new ShoppingCartException(e);
		}
	}
}