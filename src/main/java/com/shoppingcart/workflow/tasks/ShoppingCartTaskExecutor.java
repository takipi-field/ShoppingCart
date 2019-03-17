package com.shoppingcart.workflow.tasks;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ShoppingCartTaskExecutor {
	private static volatile ShoppingCartTaskExecutor instance;

	public static ShoppingCartTaskExecutor get() {
		if (instance == null) {
			synchronized (ShoppingCartTaskExecutor.class) {
				if (instance == null) {
					instance = new ShoppingCartTaskExecutor("Ordered", 1, Thread.MAX_PRIORITY);
				}
			}
		}

		return instance;
	}

	private final ThreadPoolExecutor threadPool;
	private final String name;

	private ShoppingCartTaskExecutor(String executorName, int poolSize, int threadPriority) {
		this.threadPool = new ThreadPoolExecutor(poolSize, poolSize, 0l, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(),
				ShoppingCartThreadFactory.getThreadFactory(executorName, threadPriority));

		this.name = executorName;
	}

	public void execute(Runnable task) {
		threadPool.execute(task);
	}

	public void shutdown() {
		threadPool.shutdown();
	}

	public void awaitTermination(long timeout, TimeUnit unit) {
		try {
			threadPool.awaitTermination(timeout, unit);
		} catch (InterruptedException e) {

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
}
