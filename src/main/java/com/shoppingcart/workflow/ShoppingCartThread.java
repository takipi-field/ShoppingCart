package com.shoppingcart.workflow;

import java.text.ParseException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.util.ExceptionUtils;
import com.shoppingcart.util.exception.ShoppingCartException;
import com.shoppingcart.util.exception.SkuException;

public class ShoppingCartThread implements Callable<Object> {

	private final static Logger log = LoggerFactory.getLogger(ShoppingCartThread.class);
	private AtomicLong count = new AtomicLong(1);
	private int noOfIterations;
	
	public ShoppingCartThread(int noOfIterations) {
		this.noOfIterations = noOfIterations;
	}

	@Override
	public Object call() throws Exception {
		while (count.incrementAndGet() < this.noOfIterations) {
			try {
				executeMultipleWorkflows();
			} catch (SkuException e) {
				throw e;
			} catch (ParseException e) {
				log.error("Parse Exception in thread: " + ExceptionUtils.convertStackTraceToString(e));
			} catch (NullPointerException e) {
				log.error("Null pointer exception in thread: " + ExceptionUtils.convertStackTraceToString(e));
			} catch (ClassCastException e) {
				log.error("Class cast exception in thread: " + ExceptionUtils.convertStackTraceToString(e));				
			} catch (ShoppingCartException e) {
				log.error("Exception in thread: " + ExceptionUtils.convertStackTraceToString(e));
			}
		}
		return null;
	}

	private void executeMultipleWorkflows() throws ParseException {
		ShoppingCartWorkflow workflow = new ShoppingCartWorkflow();
		
		int randomNo = RandomUtil.getRandomNumberInRange(1, 12);
		switch (randomNo) {
			case 1:		workflow.workflow1(); break;
			case 2: 		workflow.workflow2(); break;
			case 3: 		workflow.workflow3(); break;
			case 4: 		workflow.workflow4(); break;
			case 5:		workflow.workflow5(); break;
			case 6:		workflow.workflow6(); break;
			case 7:		workflow.workflow7(); break;
			case 8:		workflow.workflow8(); break;
			case 9:		workflow.workflow9(); break;	
			case 10:		workflow.workflow10(); break;
			case 11:		workflow.workflow11(); break;			
			default: 	throw new ShoppingCartException("Could not find workflow to initiate: " + randomNo);
		}
	}
}