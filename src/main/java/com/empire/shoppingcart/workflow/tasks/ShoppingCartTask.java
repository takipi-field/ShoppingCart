package com.empire.shoppingcart.workflow.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.mockdata.generate.utils.RandomUtil;
import com.empire.shoppingcart.domain.ShoppingCartProperties;
import com.empire.shoppingcart.exception.ShoppingCartException;
import com.empire.shoppingcart.util.exception.SkuException;
import com.empire.shoppingcart.workflow.ShoppingCartWF;

public class ShoppingCartTask implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(ShoppingCartTask.class);
	private ShoppingCartProperties scProperties;
	private String parentThreadName;

	private static AtomicLong count = new AtomicLong(5);
	private static final DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	private static long storedDate = 0;
	
	public ShoppingCartTask(ShoppingCartProperties scProperties, String parentThreadName) {
		this.scProperties = scProperties;
		this.parentThreadName = parentThreadName;
	}

	public synchronized void determineCount() {
		Date nowDate = Calendar.getInstance().getTime();
		long now = Long.parseLong(simpleDateFormat.format(nowDate));
		
		if (storedDate == 0 || storedDate < now) {
			storedDate = now;
			log.info("Its a brand new day - lets increase the no of errors by 5");
			count.set(count.longValue()+5);
			if (count.get() > 500) {
				count.set(5);
			}
		}
	}
	
	@Override
	public void run() {
		try {
			log.info("Running ShoppingCart Task ...:Parent Thread {}", parentThreadName);
			for (int i=1; i<=count.intValue(); i++) {
				log.info("Running ShoppingCart Task ...Iteration is {}", i);
				executeMultipleWorkflows();
			}
		} catch (SkuException e) {
			log.error("SkuException in executing workflows", e);
		} catch (ParseException e) {
			log.error("Parse Exception in thread", e);
		} catch (NullPointerException e) {
			log.error("Null pointer exception in thread", e);
		} catch (ClassCastException e) {
			log.error("Class cast exception in thread", e);	
		} catch (ShoppingCartException e) {
			log.error("Exception in thread", e);
		} catch (Exception e) {
			log.error("Unexpected exception in thread", e);
		}
	}
	
	private void executeMultipleWorkflows() throws ParseException {
		log.info("Starting executing multiple workflows");
		ShoppingCartWF workflow = new ShoppingCartWF();

		int workflowSelected = RandomUtil.getRandomNumberInRange(1, 15);
		log.info("Workflow executed is: {}", workflowSelected);
		switch (workflowSelected) {
			case 1:	if (scProperties.isWorkflow1Enabled()) {	
						workflow.workflow1();
					}
					break;
			case 2: if (scProperties.isWorkflow2Enabled()) {	
						workflow.workflow2();
					}
					break;
			case 3: if (scProperties.isWorkflow3Enabled()) {	
						workflow.workflow3();
					}
					break;
			case 4: if (scProperties.isWorkflow4Enabled()) {	
						workflow.workflow4();
					}
					break;
			case 5:	if (scProperties.isWorkflow5Enabled()) {	
						workflow.workflow5();
					}
					break;
			case 6:	if (scProperties.isWorkflow6Enabled()) {	
						workflow.workflow6();
					}
					break;
			case 7:	if (scProperties.isWorkflow7Enabled()) {	
						workflow.workflow7();
					}
					break;
			case 8:	if (scProperties.isWorkflow8Enabled()) {	
						workflow.workflow8();
					}
					break;
			case 9: if (scProperties.isWorkflow9Enabled()) {	
						workflow.workflow9();
					}
					break;
			case 10: if (scProperties.isWorkflow10Enabled()) {	
						workflow.workflow10();
					}
					break;
			case 11: if (scProperties.isWorkflow11Enabled()) {	
						workflow.workflow11();
					}
					break;
			case 12: if (scProperties.isWorkflow12Enabled()) {
						workflow.workflow12();
					}
					break;
			case 13: if (scProperties.isWorkflow13Enabled()) {
						workflow.workflow13(scProperties.getUrlConnectString());
					}
					break;
			case 14: if (scProperties.isWorkflow14Enabled()) {
						workflow.workflow14();
					}
					break;	
			default: 	throw new ShoppingCartException("Could not find workflow to initiate: " + workflowSelected);
		}
	}
}