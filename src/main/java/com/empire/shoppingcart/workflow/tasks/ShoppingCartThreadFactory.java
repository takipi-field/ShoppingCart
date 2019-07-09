package com.empire.shoppingcart.workflow.tasks;

import java.util.concurrent.ThreadFactory;
	import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingCartThreadFactory {
	
	private ShoppingCartThreadFactory() {
		//Do nothing
	}

	public static ThreadFactory getThreadFactory(String namePrefix, int threadPriority) {
		return new MyThreadFactory(namePrefix, "thread", false, threadPriority);
	}
	
	// Based on Executors$DefaultThreadFactory
	private static class MyThreadFactory implements ThreadFactory {
		private static AtomicInteger poolNumber = new AtomicInteger(1);
		private final AtomicInteger threadNumber;
		private final ThreadGroup group;
		private final String namePrefix;
		private final boolean threadsAreDaemons;
		private final int threadPriority;
		
		public MyThreadFactory(String baseName, String threadName, boolean threadsAreDaemons, int threadPriority) {
			this.threadNumber = new AtomicInteger(1);
			
			SecurityManager s = System.getSecurityManager();
			
			this.group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			this.namePrefix = baseName + "-" + poolNumber.getAndIncrement() + "-" + threadName + "-";
			this.threadsAreDaemons = threadsAreDaemons;
			this.threadPriority = threadPriority;
		}
		
		@Override
		public Thread newThread(Runnable runnable) {
			Thread thread = new Thread(group, runnable, namePrefix + threadNumber.getAndIncrement(), 0);
			thread.setDaemon(threadsAreDaemons);
			
			if (thread.getPriority() != threadPriority)
			{
				thread.setPriority(threadPriority);
			}
			
			return thread;
		}
	}
}