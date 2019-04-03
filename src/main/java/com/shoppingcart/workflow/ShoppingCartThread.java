package com.shoppingcart.workflow;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.DelayGenerator;
import com.shoppingcart.domain.ShoppingCartProperties;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.workflow.tasks.ShoppingCartTask;
import com.shoppingcart.workflow.tasks.ShoppingCartTaskExecutor;

public class ShoppingCartThread implements Callable<Object> {

	private static final Logger log = LoggerFactory.getLogger(ShoppingCartThread.class);
	private ShoppingCartProperties scProperties;
	private static AtomicLong dummyCount = new AtomicLong(0);

	public ShoppingCartThread(ShoppingCartProperties scProperties) {
		log.info("In the constructor");
		this.scProperties = scProperties;
		log.info("Completed ...");
	}
	
	@Override
	public Object call() throws Exception {
		int noOfLoops = scProperties.getNumberOfIterations();
		int count = 0;
		while (count <= noOfLoops) {
			Future<?> future = null;
			try {
				dummyCount.incrementAndGet();
				ShoppingCartTaskExecutor executor = ShoppingCartTaskExecutor.getInstance(1);
				String threadName = Thread.currentThread().getName();
				log.info("In ShoppingCartThread: ThreadName is {}", threadName);
				
				future = executor.submit(new ShoppingCartTask(scProperties, threadName));
				letsWaitFor10seconds();

			} catch (Exception e) {
				log.error("Exception in executing workflows {}", e.getMessage());
			} finally {
				count++;
				handleFuture(future);
			}
		}
		log.info("Lets shut down ShoppingCartThreads executor");
		log.info("Completed handling ShoppingCartThreads shutdown ...");

		return null;
	}

	private void letsWaitFor10seconds() {
		int waitTime = scProperties.getWaitTime();
		if (waitTime != 0) {
			log.info("Waiting for {} seconds between shopping cart thread iterations ....", waitTime/(1000));
			DelayGenerator.introduceDelay(waitTime);
		}
	}
	
	private void handleFuture(Future<?> f) {
		if (f != null) {
			while (true) {
				try {
					//Wait for all threads to complete
					log.info("Waiting for the executor to complete ...");
					f.get();
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
}