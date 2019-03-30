package com.shoppingcart.workflow.tasks;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.RandomUtil;
import com.shoppingcart.domain.ShoppingCartProperties;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.util.ShoppingCartPropertyReader;
import com.shoppingcart.util.exception.SkuException;
import com.shoppingcart.workflow.ShoppingCartWF;

public class ShoppingCartTask5 implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(ShoppingCartTask5.class);
	private ShoppingCartProperties scProperties;
	
	public ShoppingCartTask5(ShoppingCartProperties scProperties) {
		this.scProperties = scProperties;
	}

	@Override
	public void run() {
		int count = 1;
//		while (count <= scProperties.getNumberOfIterations()) {
			try {
				log.info("Running ShoppingCart Task ...: {}", count);
				log.info("SCProperties NoOfIterations is: {}", scProperties.getNumberOfIterations());
				executeMultipleWorkflows();
			} catch (SkuException e) {
				log.error("SkuException in executing workflows ", e);
			} catch (ParseException e) {
				log.error("Parse Exception in thread: ", e);
			} catch (NullPointerException e) {
				log.error("Null pointer exception in thread: ", e);
			} catch (ClassCastException e) {
				log.error("Class cast exception in thread: ", e);	
			} catch (ShoppingCartException e) {
				log.error("Exception in thread: ", e);
			} catch (Exception e) {
				log.error("Unexpected exception in thread: ", e);
			} finally {
				count++;
			}
//			int waitTime = scProperties.getWaitTime();
//			if (waitTime != 0) {
//				log.info("Waiting for {} seconds between iterations ....", waitTime/1000);
//				DelayGenerator.introduceDelay(waitTime);
//			}
//		}
	}
	
	private void executeMultipleWorkflows() throws ParseException {
		log.info("Starting executing multiple workflows");
		ShoppingCartWF workflow = new ShoppingCartWF();
		
		boolean workflow1Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW1.ENABLED"));
		boolean workflow2Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW2.ENABLED"));
		boolean workflow3Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW3.ENABLED"));
		boolean workflow4Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW4.ENABLED"));
		boolean workflow5Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW5.ENABLED"));
		boolean workflow6Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW6.ENABLED"));
		boolean workflow7Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW7.ENABLED"));
		boolean workflow8Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW8.ENABLED"));
		boolean workflow9Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW9.ENABLED"));
		boolean workflow10Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW10.ENABLED"));
		boolean workflow11Enabled = Boolean.parseBoolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW11.ENABLED"));

		
		int randomNo = RandomUtil.getRandomNumberInRange(1, 12);
		switch (randomNo) {
			case 1:	if (workflow1Enabled) {	
						workflow.workflow1();
					}
					break;
			case 2: if (workflow2Enabled) {	
						workflow.workflow2();
					}
			break;
			case 3: if (workflow3Enabled) {	
						workflow.workflow3();
					}
					break;
			case 4: if (workflow4Enabled) {	
						workflow.workflow4();
					}
					break;
			case 5:	if (workflow5Enabled) {	
						workflow.workflow5();
					}
					break;
			case 6:	if (workflow6Enabled) {	
						workflow.workflow6();
					}
					break;
			case 7:	if (workflow7Enabled) {	
						workflow.workflow7();
					}
					break;
			case 8:	if (workflow8Enabled) {	
						workflow.workflow8();
					}
					break;
			case 9: if (workflow9Enabled) {	
						workflow.workflow9();
					}
					break;
			case 10: if (workflow10Enabled) {	
						workflow.workflow10();
					}
					break;
			case 11: if (workflow11Enabled) {	
						workflow.workflow11();
					}
					break;		
			default: 	throw new ShoppingCartException("Could not find workflow to initiate: " + randomNo);
		}
	}
}