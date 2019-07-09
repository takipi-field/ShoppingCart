package com.empire.shoppingcart.workflow.tasks;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCartTaskExecutor {

	private static ShoppingCartTaskExecutor instance;
	private final ThreadPoolExecutor threadPool;
	private final String name;
	private static final Logger log = 
			LoggerFactory.getLogger(ShoppingCartTaskExecutor.class);

	public static ShoppingCartTaskExecutor getInstance(int noOfThreads) {
		if (instance == null) {
			synchronized (ShoppingCartTaskExecutor.class) {
				instance = new ShoppingCartTaskExecutor("ShoppingCartTaskExecutor", noOfThreads, Thread.MAX_PRIORITY);
			}
		}
		return instance;
	}

	private ShoppingCartTaskExecutor(String executorName, int poolSize, int threadPriority) {
		this.threadPool = new ThreadPoolExecutor(poolSize, poolSize, 0l, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(),
				ShoppingCartThreadFactory.getThreadFactory(executorName, threadPriority));
		this.name = executorName;
	}

	public void execute(Runnable task) {
		if (isAlive()) {
			threadPool.execute(task);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Future submit(Runnable task) {
		if (isAlive()) {
			return threadPool.submit(task);
		}
		return null;
	}

	public void shutdown() {
		threadPool.shutdown();
	}

	public void awaitTermination(long timeout, TimeUnit unit) {
		try {
			threadPool.awaitTermination(timeout, unit);
		} catch (InterruptedException e) {
			log.error("An exception occured {}", e.getMessage());
		    Thread.currentThread().interrupt();
		}
	}

	public List<Runnable> shutdownNow() {
		return threadPool.shutdownNow();
	}

	public int getApproxAvailableThreads() {
		return (threadPool.getMaximumPoolSize() - threadPool.getActiveCount());
	}

	public String getName() {
		return name;
	}
	
	public boolean isAlive() {
		if (threadPool.isShutdown() || threadPool.isTerminated() || threadPool.isTerminating()) {
			return false;
		}
		return true;
	}
}