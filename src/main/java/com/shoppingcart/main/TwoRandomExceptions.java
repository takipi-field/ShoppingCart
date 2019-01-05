package com.shoppingcart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.util.ExceptionListReader;

@SuppressWarnings("unchecked")
public class TwoRandomExceptions {

	private final static Logger log = LoggerFactory.getLogger(TwoRandomExceptions.class);
	
	public <T> void createTwoRandomExceptions() {
		createRandomException("RandomException1");
		createRandomException("RandomException2");
	}
	
	private <T> void createRandomException(String message) {
	
		log.info("Creating " + message + " ...");
		try {			
			int i = RandomUtil.getRandomNumberInRange(1, ExceptionListReader.getInstance().size() + 1);
			log.info("Random Number Generated is: " + i);
			String className = (String) ExceptionListReader.getInstance().get(i + "");
			Class<Throwable> c = (Class<Throwable>) Class.forName(className);
			myFunc(c);
		}
		catch (Throwable e) {
			e.printStackTrace();
		}

		log.info("Completed generating " + message );
	}
	
	private <T extends Throwable> void myFunc(Class<T> exceptionType) throws Exception, T {
	    final String message = RandomUtil.generateRandomText(10) + " some message " + RandomUtil.generateRandomText(10);
	    throw exceptionType.getConstructor(String.class).newInstance(message);
	}
}