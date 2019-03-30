package com.shoppingcart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.RandomUtil;
import com.shoppingcart.util.ExceptionListReader;

@SuppressWarnings("unchecked")
public class TwoRandomExceptions {

	private static final Logger log = LoggerFactory.getLogger(TwoRandomExceptions.class);
	
	public void createTwoRandomExceptions() {
		createRandomException("RandomException1");
		createRandomException("RandomException2");
	}
	
	private void createRandomException(String message) {
	
		log.info("Creating {} ...", message);
		try {			
			int i = RandomUtil.getRandomNumberInRange(1, ExceptionListReader.getInstance().size() + 1);
			log.info("Random Number Generated is: {}", i);
			String className = (String) ExceptionListReader.getInstance().get(i + "");
			log.info("Calling ClassForName with className: {}", className);
			Class<Throwable> c = (Class<Throwable>) Class.forName(className);
			log.info("Received Class");
			myFunc(c);
		}
		catch (Throwable e) {
			log.error("Error occured: {}", e.getMessage());
		}
		log.info("Completed generating {}", message);
	}
	
	private <T extends Throwable> void myFunc(Class<T> exceptionType) throws Exception, T {
		log.info("Inside myFunc class");
		
	    final String message = RandomUtil.generateRandomAlphaString(10) + " some message " + RandomUtil.generateRandomAlphaString(10);
	    log.info("Message is: {}", message);
	    throw exceptionType.getConstructor(String.class).newInstance(message);
	}
}